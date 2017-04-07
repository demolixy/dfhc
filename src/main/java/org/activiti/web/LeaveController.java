/**
 * 
 */
package org.activiti.web;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.activiti.engine.ActivitiException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.service.LeaveService;
import org.activiti.service.LeaveWorkflowService;
import org.activiti.vo.LeaveVo;
import org.apache.commons.lang.BooleanUtils;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author longsebo
 * 请假控制器
 */
@Controller
@RequestMapping(value = "/sampleleave")
public class LeaveController {
	  private Logger logger = LoggerFactory.getLogger(getClass());

	    @Autowired
	    private LeaveService leaveService;

	    @Autowired
	    private LeaveWorkflowService leaveWorkflowService;

	    @Autowired
	    private TaskService taskService;

	    @Autowired
	    private RuntimeService runtimeService;

	    @RequestMapping(value = {"apply", ""})
	    public String createForm(Model model) {	    	
	        model.addAttribute("leave", new LeaveVo());
	        return "/activiti/leave/leave-apply";
	    }

	    /**
	     * 启动请假流程
	     */
	    @RequestMapping(value = "start", method = RequestMethod.POST)
	    public String startWorkflow(LeaveVo leave, RedirectAttributes redirectAttributes, HttpSession session,HttpServletRequest request) {
	        try {
	            RmUserVo user = RmProjectHelper.getRmUserVo(request);
	            Map<String, Object> variables = new HashMap<String, Object>();
	            ProcessInstance processInstance = leaveWorkflowService.startWorkflow(leave, user.getId(), variables);
	            redirectAttributes.addFlashAttribute("message", "流程已启动，流程ID：" + processInstance.getId());
	        } catch (ActivitiException e) {
	            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
	                logger.warn("没有部署流程!", e);
	                redirectAttributes.addFlashAttribute("error", "没有部署请假流程");
	            } else {
	                logger.error("启动请假流程失败：", e);
	                redirectAttributes.addFlashAttribute("error", "系统内部错误！");
	            }
	        } catch (Exception e) {
	            logger.error("启动请假流程失败：", e);
	            redirectAttributes.addFlashAttribute("error", "系统内部错误！");
	        }
	        return "redirect:/sampleleave/apply";
	    }

	    /**
	     * 任务列表
	     *
	     * @param leave
	     */
	    @RequestMapping(value = "task/list")
	    public ModelAndView taskList(HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView("/activiti/leave/leave-task-list");
	        String userId = RmProjectHelper.getRmUserId(request);
	        List<LeaveVo> results = leaveWorkflowService.findTodoTasks(userId);
	        mav.addObject("records", results);
	        return mav;
	    }

	    /**
	     * 签收任务
	     */
	    @RequestMapping(value = "task/claim/{id}")
	    public String claim(@PathVariable("id") String taskId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
	        String userId = RmProjectHelper.getRmUserId(request);
	        taskService.claim(taskId, userId);
	        redirectAttributes.addFlashAttribute("message", "任务已签收");
	        return "redirect:/sampleleave/task/list";
	    }

	    /**
	     * 任务列表
	     *
	     * @param leave
	     */
	    @RequestMapping(value = "task/view/{taskId}")
	    public ModelAndView showTaskView(@PathVariable("taskId") String taskId) {
	        Task task = (Task) taskService.createTaskQuery().taskId(taskId).singleResult();
	        String processInstanceId = task.getProcessInstanceId();
	        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	        LeaveVo leave = leaveService.get(new Long(processInstance.getBusinessKey()));
	        ModelAndView mav = new ModelAndView("/activiti/leave/task-" + ((TaskInfo) task).getTaskDefinitionKey());
	        mav.addObject("leave", leave);
	        mav.addObject("task", task);
	        return mav;
	    }

	    /**
	     * 完成任务
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping(value = "task/complete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
	    @SuppressWarnings("unchecked")
	    public String complete(@PathVariable("id") String taskId, @RequestParam(value = "saveEntity", required = false) String saveEntity,
	                           @ModelAttribute("preloadLeave") LeaveVo leave, HttpServletRequest request, RedirectAttributes redirectAttributes) {
	        boolean saveEntityBoolean = BooleanUtils.toBoolean(saveEntity);
	        Map<String, Object> variables = new HashMap<String, Object>();
	        Enumeration<String> parameterNames = request.getParameterNames();
	        try {
	            while (parameterNames.hasMoreElements()) {
	                String parameterName = (String) parameterNames.nextElement();
	                if (parameterName.startsWith("p_")) {
	                    // 参数结构：p_B_name，p为参数的前缀，B为类型，name为属性名称
	                    String[] parameter = parameterName.split("_");
	                    if (parameter.length == 3) {
	                        String paramValue = request.getParameter(parameterName);
	                        Object value = paramValue;
	                        if (parameter[1].equals("B")) {
	                            value = BooleanUtils.toBoolean(paramValue);
	                        } else if (parameter[1].equals("DT")) {
	                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                            value = sdf.parse(paramValue);
	                        }
	                        variables.put(parameter[2], value);
	                    } else {
	                        throw new RuntimeException("invalid parameter for activiti variable: " + parameterName);
	                    }
	                }
	            }
	            leaveWorkflowService.complete(leave, saveEntityBoolean, taskId, variables);
	            redirectAttributes.addFlashAttribute("message", "任务已完成");
	        } catch (Exception e) {
	            logger.error("error on complete task {}, variables={}", new Object[]{taskId, variables, e});
	            request.setAttribute("error", "完成任务失败");
	        }
	        return "redirect:/sampleleave/task/list";
	    }

	    /**
	     * 自动绑定页面字段
	     */
	    @ModelAttribute("preloadLeave")
	    public LeaveVo getLeave(@RequestParam(value = "id", required = false) Long id, HttpSession session) {
	        if (id != null) {
	            return leaveService.get(id);
	        }
	        return new LeaveVo();
	    }
	 
}
