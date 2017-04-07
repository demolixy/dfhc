/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  GeneralBusinessProcessController.java
 *
 * 功能描述：  通用流程业务表Controller
 * 
 * 版本历史：
 * 
 * 2016-12-06   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.generalbusinessprocess.web;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.pub.service.ActivitiService;
import com.dfhc.pub.service.BeanShellService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;
import com.dfhc.util.TemplateHelper;
import com.dfhc.wk.dataset.service.DataSetService;
import com.dfhc.wk.dataset.vo.DataSetVo;
import com.dfhc.wk.formtemplate.service.FormTemplateService;
import com.dfhc.wk.formtemplate.vo.FormTemplateVo;
import com.dfhc.wk.generalbusinessprocess.IGeneralBusinessProcessConstants;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.AuditInfoVo;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;
import com.dfhc.wk.processdataitems.vo.TaskForListVo;
import com.dfhc.wk.tasknodeextconf.service.TaskNodeExtConfService;
import com.dfhc.wk.tasknodeextconf.vo.TaskNodeExtConfVo;
import com.dfhc.ISystemConstant;
/**
 * list                  /generalbusinessprocess
 * insert page      GET  /generalbusinessprocess/insert
 * insert action    POST /generalbusinessprocess/insert
 * update page      GET  /generalbusinessprocess/update/{id}
 * update action    POST /generalbusinessprocess/update
 * delete action    POST /generalbusinessprocess/delete
 * detail                /generalbusinessprocess/detail/{id}

 * reference             /generalbusinessprocess/reference
 * import page      GET  /generalbusinessprocess/import
 * import action    POST /generalbusinessprocess/import
 * export custom    GET  /generalbusinessprocess/export
 * export action    POST /generalbusinessprocess/export
 */

import freemarker.template.TemplateException;

/**
 * 通用流程业务表 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/generalbusinessprocess")
public class GeneralBusinessProcessController implements IGeneralBusinessProcessConstants {
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    /**
     * 通用流程业务表服务
     */
    private GeneralBusinessProcessService generalBusinessProcessService;
    @Autowired
   	/**
   	 * 任务节点扩展配置服务
   	 */
   	private TaskNodeExtConfService taskNodeExtConfService;
       @Autowired
   	/**
   	 * bean shell脚本服务
   	 */
   	private BeanShellService beanShellService;
       @Autowired
   	/**
   	 * Activiti工作流服务封装
   	 */
   	private ActivitiService activitiService;
       @Autowired
   	/**
   	 * 表单模板服务
   	 */
   	private FormTemplateService formTemplateService;
    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private PubParamService pubParamService;
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, generalBusinessProcessService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<GeneralBusinessProcessVo> beans = generalBusinessProcessService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        RmJspHelper.saveOrderStr(orderStr, request);  //保存排序信息
        model.addAttribute(REQUEST_QUERY_CONDITION, searchPara);
        model.addAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
        return "/activiti/wk/generalbusinessprocess/listGeneralBusinessProcess";
    }
   
    
    /**
     * 从页面表单获取信息注入vo，并插入单条记录
     * @param request http请求对象
     * @param vo 值对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(HttpServletRequest request, @Valid GeneralBusinessProcessVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        generalBusinessProcessService.insert(vo);  //插入单条记录
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ISystemConstant.AJAX_BEAN, vo);
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "新增成功: " + vo.getId());
        return result;
    }
    
    /**
     * 从页面表单获取信息注入vo，并修改单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param errors Ajax错误对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request, @Valid GeneralBusinessProcessVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        generalBusinessProcessService.update(vo);  //更新单条记录
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "修改成功: " + vo.getId());
        return result;
    }
    /**
     * 根据id获取单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "get/{id}/{dateTime}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> get(@PathVariable("id") String id, HttpServletRequest request) {
        GeneralBusinessProcessVo bean = generalBusinessProcessService.get(id);
        Map<String, Object> result = new HashMap<String, Object>();
        if(bean==null){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
            return result;
        }
        
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_BEAN, bean);
        return result;			
    }    
    /**
     * 从页面的表单获取单条记录id并删除，如request.id为空则删除多条记录（request.ids）
     * @param request http请求对象
     * @param redirectAttributes 重定向属性
     * @return 返回列表页面
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int deleteCount = 0;  //定义成功删除的记录数
        String id = request.getParameter(REQUEST_ID);
        if(id != null && id.length() > 0) {
            deleteCount = generalBusinessProcessService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += generalBusinessProcessService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/generalbusinessprocess";
    }
    /**
     * 从页面的表单获取单条记录id并删除，如request.id为空则删除多条记录（request.ids）
     * @param request http请求对象
     * @param redirectAttributes 重定向属性
     * @return 返回列表页面
     */
    @RequestMapping(value = "deleteAjax", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteAjax(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	Map<String, Object> result = new HashMap<String, Object>();
        int deleteCount = 0;  //定义成功删除的记录数
        String id = request.getParameter(REQUEST_ID);
        if(id != null && id.length() > 0) {
            deleteCount = generalBusinessProcessService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += generalBusinessProcessService.delete(ids);  //删除多条记录
            }
        }
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "删除成功: " + deleteCount);
        return result;
    }
   /**
     * 从页面的表单获取单条记录id并逻辑删除，如request.id为空则删除多条记录（request.ids）
     * @param request http请求对象
     * @param redirectAttributes 重定向属性
     * @return 返回列表页面
     */
    @RequestMapping(value = "logicDelete", method = RequestMethod.POST)
    public String deleteLogic(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int deleteCount = 0;  //定义成功删除的记录数
        String id = request.getParameter(REQUEST_ID);
        if(id != null && id.length() > 0) {
            deleteCount = generalBusinessProcessService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += generalBusinessProcessService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/generalbusinessprocess";
    }
    /**
     * 从页面的表单获取单条记录id，并察看这条记录的详细信息
     * @param id 待删除的id
     * @param model 模型
     * @request 请求对象
     * @return 返回详细信息页面
     */
    @RequestMapping(value = "detail/{id}")
    public String detail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        GeneralBusinessProcessVo bean = generalBusinessProcessService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/activiti/wk/generalbusinessprocess/detailGeneralBusinessProcess";
    }

    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "reference")
    public String reference(Model model, HttpServletRequest request) {
        list(model, request);
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        return "/activiti/wk/generalbusinessprocess/util/referenceGeneralBusinessProcess";
    }

    /**
     * 跳转到导入页
     * @param model 模型
     * @return 返回导入页
     */
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importDataForm(Model model) {
        return "/activiti/wk/generalbusinessprocess/importGeneralBusinessProcess";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/activiti/wk/generalbusinessprocess/importGeneralBusinessProcess";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/activiti/wk/generalbusinessprocess/exportGeneralBusinessProcess_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/activiti/wk/generalbusinessprocess/exportGeneralBusinessProcess_excel";
    }

    /**
     * 从request中获得查询条件
     * @param request http请求对象
     * @return 条件Map
     */
    public static Map<String, Object> getQueryCondition(HttpServletRequest request) {
        Map<String, Object> searchMap = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
            searchMap = (Map<String, Object>)request.getAttribute(REQUEST_QUERY_CONDITION);
        } else {
           searchMap = new HashMap<String, Object>();
           searchMap.put("id", request.getParameter("id"));
           searchMap.put("procDefId", request.getParameter("procDefId"));
           searchMap.put("procInstId", request.getParameter("procInstId"));
           searchMap.put("procName", request.getParameter("procName"));
           searchMap.put("procKey", request.getParameter("procKey"));
           searchMap.put("initiator", request.getParameter("initiator"));
           searchMap.put("initiatorId", request.getParameter("initiatorId"));
           searchMap.put("sendOrganizationDepartment", request.getParameter("sendOrganizationDepartment"));
           searchMap.put("initiatorOrganizationId", request.getParameter("initiatorOrganizationId"));
           searchMap.put("initiatorTime", request.getParameter("initiatorTime"));
           searchMap.put("businessType", request.getParameter("businessType"));
           searchMap.put("businessAttribute70", request.getParameter("businessAttribute70"));
           searchMap.put("businessAttribute69", request.getParameter("businessAttribute69"));
           searchMap.put("businessAttribute68", request.getParameter("businessAttribute68"));
           searchMap.put("businessAttribute67", request.getParameter("businessAttribute67"));
           searchMap.put("businessAttribute66", request.getParameter("businessAttribute66"));
           searchMap.put("businessAttribute65", request.getParameter("businessAttribute65"));
           searchMap.put("businessAttribute64", request.getParameter("businessAttribute64"));
           searchMap.put("businessAttribute63", request.getParameter("businessAttribute63"));
           searchMap.put("businessAttribute62", request.getParameter("businessAttribute62"));
           searchMap.put("businessAttribute61", request.getParameter("businessAttribute61"));
           searchMap.put("businessAttribute60", request.getParameter("businessAttribute60"));
           searchMap.put("businessAttribute59", request.getParameter("businessAttribute59"));
           searchMap.put("businessAttribute58", request.getParameter("businessAttribute58"));
           searchMap.put("businessAttribute57", request.getParameter("businessAttribute57"));
           searchMap.put("businessAttribute56", request.getParameter("businessAttribute56"));
           searchMap.put("businessAttribute55", request.getParameter("businessAttribute55"));
           searchMap.put("businessAttribute54", request.getParameter("businessAttribute54"));
           searchMap.put("businessAttribute53", request.getParameter("businessAttribute53"));
           searchMap.put("businessAttribute52", request.getParameter("businessAttribute52"));
           searchMap.put("businessAttribute51", request.getParameter("businessAttribute51"));
           searchMap.put("businessAttribute50", request.getParameter("businessAttribute50"));
           searchMap.put("businessAttribute49", request.getParameter("businessAttribute49"));
           searchMap.put("businessAttribute48", request.getParameter("businessAttribute48"));
           searchMap.put("businessAttribute47", request.getParameter("businessAttribute47"));
           searchMap.put("businessAttribute46", request.getParameter("businessAttribute46"));
           searchMap.put("businessAttribute45", request.getParameter("businessAttribute45"));
           searchMap.put("businessAttribute44", request.getParameter("businessAttribute44"));
           searchMap.put("businessAttribute43", request.getParameter("businessAttribute43"));
           searchMap.put("businessAttribute42", request.getParameter("businessAttribute42"));
           searchMap.put("businessAttribute41", request.getParameter("businessAttribute41"));
           searchMap.put("businessAttribute40", request.getParameter("businessAttribute40"));
           searchMap.put("businessAttribute39", request.getParameter("businessAttribute39"));
           searchMap.put("businessAttribute38", request.getParameter("businessAttribute38"));
           searchMap.put("businessAttribute37", request.getParameter("businessAttribute37"));
           searchMap.put("businessAttribute36", request.getParameter("businessAttribute36"));
           searchMap.put("businessAttribute35", request.getParameter("businessAttribute35"));
           searchMap.put("businessAttribute34", request.getParameter("businessAttribute34"));
           searchMap.put("businessAttribute33", request.getParameter("businessAttribute33"));
           searchMap.put("businessAttribute32", request.getParameter("businessAttribute32"));
           searchMap.put("businessAttribute31", request.getParameter("businessAttribute31"));
           searchMap.put("businessAttribute30", request.getParameter("businessAttribute30"));
           searchMap.put("businessAttribute29", request.getParameter("businessAttribute29"));
           searchMap.put("businessAttribute28", request.getParameter("businessAttribute28"));
           searchMap.put("businessAttribute27", request.getParameter("businessAttribute27"));
           searchMap.put("businessAttribute26", request.getParameter("businessAttribute26"));
           searchMap.put("businessAttribute25", request.getParameter("businessAttribute25"));
           searchMap.put("businessAttribute24", request.getParameter("businessAttribute24"));
           searchMap.put("businessAttribute23", request.getParameter("businessAttribute23"));
           searchMap.put("businessAttribute22", request.getParameter("businessAttribute22"));
           searchMap.put("businessAttribute21", request.getParameter("businessAttribute21"));
           searchMap.put("businessAttribute20", request.getParameter("businessAttribute20"));
           searchMap.put("businessAttribute19", request.getParameter("businessAttribute19"));
           searchMap.put("businessAttribute18", request.getParameter("businessAttribute18"));
           searchMap.put("businessAttribute17", request.getParameter("businessAttribute17"));
           searchMap.put("businessAttribute16", request.getParameter("businessAttribute16"));
           searchMap.put("businessAttribute15", request.getParameter("businessAttribute15"));
           searchMap.put("businessAttribute14", request.getParameter("businessAttribute14"));
           searchMap.put("businessAttribute13", request.getParameter("businessAttribute13"));
           searchMap.put("businessAttribute12", request.getParameter("businessAttribute12"));
           searchMap.put("businessAttribute11", request.getParameter("businessAttribute11"));
           searchMap.put("businessAttribute10", request.getParameter("businessAttribute10"));
           searchMap.put("businessAttribute9", request.getParameter("businessAttribute9"));
           searchMap.put("businessAttribute8", request.getParameter("businessAttribute8"));
           searchMap.put("businessAttribute7", request.getParameter("businessAttribute7"));
           searchMap.put("businessAttribute6", request.getParameter("businessAttribute6"));
           searchMap.put("businessAttribute5", request.getParameter("businessAttribute5"));
           searchMap.put("businessAttribute4", request.getParameter("businessAttribute4"));
           searchMap.put("businessAttribute3", request.getParameter("businessAttribute3"));
           searchMap.put("businessAttribute1", request.getParameter("businessAttribute1"));
           searchMap.put("businessAttribute2", request.getParameter("businessAttribute2"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    /**
     * 待办任务列表
     *
     * @param leave
     */
    @RequestMapping(value = "taskList")
    @Transactional(readOnly = true)
    public String taskList(HttpServletRequest request) {
        //ModelAndView mav = new ModelAndView("/activiti/leave/general-task-list");
        String userId = RmProjectHelper.getRmUserId(request);
        List<Task> results = generalBusinessProcessService.findTodoTasks(userId);
        //mav.addObject("records", results);
        //return mav;
    	RmPageVo pageVo = RmJspHelper.transctPageVo(request, results.size());
  	  	List<Task> list = new ArrayList<Task>();
  	  	int totalNum = pageVo.getStartIndex()-1+pageVo.getPageSize();
  	  	if(pageVo.getStartIndex()+pageVo.getPageSize()>pageVo.getRecordCount()){
  	  		totalNum = pageVo.getRecordCount();
  	  	}
  	  	for(int i=pageVo.getStartIndex()-1; i<totalNum; i++){
  	  		list.add(results.get(i));
  	  	}
  	  	request.setAttribute("beans", list);
  	  	String ctx = request.getContextPath(); 
  	  	request.setAttribute("ctx", ctx);
  	  	return "/activiti/wk/listGenneralTask";
    }
    /**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    public String claim(@PathVariable("id") String taskId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String userId = RmProjectHelper.getRmUserId(request);
        taskService.claim(taskId, userId);
        redirectAttributes.addFlashAttribute("message", "任务已签收");
        //return "redirect:/generalbusinessprocess/taskList";
        return "redirect:/price/listTask";
    }
    /**
     * 办理任务
     */
    @RequestMapping(value = "view/{taskId}")
    public String showTaskView(@PathVariable("taskId") String taskId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Task task = (Task) taskService.createTaskQuery().taskId(taskId).singleResult();
        String procInstId = task.getProcessInstanceId();
        String taskDefinitionKey = task.getTaskDefinitionKey();
        String processDefinitionId = task.getProcessDefinitionId();
        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Object> taskDataMap = new HashMap<String, Object>();
        searchMap.put("procInstId", procInstId);
        List<GeneralBusinessProcessVo> generalBusinessProcessVos = generalBusinessProcessService.list(searchMap, null);
        if(generalBusinessProcessVos.size()>0){
        	GeneralBusinessProcessVo generalBusinessProcessVo = generalBusinessProcessVos.get(0);
    		Map<String, Object> voMap = RmVoHelper.getMapFromVo(generalBusinessProcessVo);
    		taskDataMap.putAll(voMap);
    		//把该流程定义id和业务组对应的流程数据项也添加到map里面
    		Map<String, Object> tableMap = generalBusinessProcessService.getProcessDataItems(generalBusinessProcessVo.getProcDefId(), generalBusinessProcessVo.getBusinessGroup());
    		taskDataMap.putAll(tableMap);
    		taskDataMap.put("parentId", generalBusinessProcessVo.getId());
    		//查询子表对应的数据
    		List<GeneralBusinessProcessVo> generalBusinessProcessVos2 = generalBusinessProcessService.getByParentId(generalBusinessProcessVo.getId());
    		taskDataMap.put("generalBusinessProcessVos", generalBusinessProcessVos2);
        }else{
        	redirectAttributes.addFlashAttribute("message", "根据流程实例id没有找到与之对应的通用流程业务数据!");
            //return "redirect:/generalbusinessprocess/taskList";
        	return "redirect:/price/listTask";
        }
      //===========begin 模板参数============
		searchMap.clear();
  		searchMap.put("procDefId", processDefinitionId);
  		searchMap.put("taskId", task.getTaskDefinitionKey());
  		searchMap.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
  		List<TaskNodeExtConfVo> taskNodeExtConfVos = taskNodeExtConfService.list(searchMap, null);
  		if(taskNodeExtConfVos.size()>0){
  			TaskNodeExtConfVo taskNodeExtConfVo = taskNodeExtConfVos.get(0);
  			String readTaskDataScript = taskNodeExtConfVo.getReadTaskDataScript();
  			if(!StringHelper.isEmpty(readTaskDataScript)){
  				taskDataMap.putAll(beanShellService.readTaskData(request, readTaskDataScript));
  			}
  		}

  		String ctx = request.getContextPath(); 
  		//预定义参数：网站路径
  		taskDataMap.put(ISystemConstant.PREDEFINED_BACK_CONTEXT_PATH, ctx);
  		//预定义参数：模板定义id
  		taskDataMap.put(ISystemConstant.PREDEFINED_PROCESS_DEFINITION_ID, processDefinitionId);
  		//预定义参数：任务id
  		taskDataMap.put(ISystemConstant.PREDEFINED_TASK_ID, taskId);
  		//===========end 模板参数============
  		
  		//===========begin 查询审核意见=======
  		List<TaskForListVo> taskForListVos = activitiService.getTaskNodeListByProDefId(processDefinitionId);
  		List<AuditInfoVo> auditInfoVos = new ArrayList<AuditInfoVo>();
  		for (TaskForListVo taskForListVo : taskForListVos) {
			if(taskForListVo.getTaskId().equals(taskDefinitionKey)){
				break;
			}
			if(StringHelper.isEmpty(taskForListVo.getIsStart())||ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(taskForListVo.getIsStart())){
				String taskId2 = generalBusinessProcessService.getTaskIdByTaskDefKey(taskForListVo.getTaskId(),task.getProcessInstanceId());
				if(!StringHelper.isEmpty(taskId2)){
					AuditInfoVo auditInfoVo = new AuditInfoVo();
					List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery().processInstanceId(task.getProcessInstanceId()).taskId(taskId2).list();
					for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
						if("userName".equals(historicVariableInstance.getVariableName())){
							auditInfoVo.setAuditName(historicVariableInstance.getValue().toString());
						}
						if("auditTime".equals(historicVariableInstance.getVariableName())){
							auditInfoVo.setAuditTime(historicVariableInstance.getValue().toString());
						}
						if("auditstate".equals(historicVariableInstance.getVariableName())){
							auditInfoVo.setAuditState(historicVariableInstance.getValue().toString());
						}
					}
					List<Comment> comments = taskService.getTaskComments(taskId2);
					if(comments!=null && comments.size()>0){
						//审核意见
						auditInfoVo.setAuditOpinion(comments.get(0).getFullMessage());
					}
					if(!StringHelper.isEmpty(auditInfoVo.getAuditName())){
						auditInfoVos.add(auditInfoVo);
					}
				}
			}
  		}
  		
  		taskDataMap.put("auditVos", auditInfoVos);
  		//===========end 查询审核意见=========
  		
  		//===========begin 获取模板================
  		String formTemplateId = activitiService.getTaskFormDataKey(processDefinitionId,taskDefinitionKey);
  		if(StringHelper.isEmpty(formTemplateId)){
  			redirectAttributes.addFlashAttribute("message", "当前选择的流程定义id:"+task.getFormKey()+" 没有找到与之关联的开始表单的key!");
  			//return "redirect:/generalbusinessprocess/taskList";
  			return "redirect:/price/listTask";
  		}
  		FormTemplateVo formTemplateVo = formTemplateService.get(formTemplateId);
  		if(StringHelper.isEmpty(formTemplateId)){
  			redirectAttributes.addFlashAttribute("message", "根据表单模板id:"+formTemplateId+" 没有找到对应的表单模板!");
  			//return "redirect:/generalbusinessprocess/taskList";
  			return "redirect:/price/listTask";
  		}
  		String templateFreemark = formTemplateVo.getTemplateFreemark();
  		//===========end 获取模板================
  		try {
  			templateFreemark = TemplateHelper.createContentByString(taskDataMap, templateFreemark);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (TemplateException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		request.setAttribute("templateFreemark", templateFreemark);
  		//return "/activiti/explorer/business-application-detail";
  		return "/activiti/wk/generalbusinessprocess/listPriceNullContent";
    }
    /**
     * 完成任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "complete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String complete(@PathVariable("id") String taskId, GeneralBusinessProcessVo generalBusinessProcessVo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    	Task task = (Task) taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDefinitionId = task.getProcessDefinitionId();
        //审核意见
        String auditOpinion = request.getParameter(ISystemConstant.PREDEFINED_AUDIT_OPINION);
        if(!StringHelper.isEmpty(auditOpinion)){
        	taskService.addComment(taskId, task.getProcessInstanceId(), auditOpinion);
        }
    	Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("procDefId", processDefinitionId);
		searchMap.put("taskId", task.getTaskDefinitionKey());
		List<TaskNodeExtConfVo> taskNodeExtConfVos = taskNodeExtConfService.list(searchMap, null);
		if(taskNodeExtConfVos.size()>0 && !StringHelper.isEmpty(taskNodeExtConfVos.get(0).getWriteTaskDataScript())){
			TaskNodeExtConfVo taskNodeExtConfVo = taskNodeExtConfVos.get(0);
			String writeTaskDataScript = taskNodeExtConfVo.getWriteTaskDataScript();
			try {
				request.setAttribute("id", task.getId());
				beanShellService.doSaveTaskData(request, response, writeTaskDataScript);
			} catch (Exception e) {
				 logger.error("完成流程失败：", e);
		         return "redirect:/generalbusinessprocess/view/"+taskId+"?errmsg="+e.getMessage();
			}
		}else{
			generalBusinessProcessService.insertComplete(generalBusinessProcessVo, taskId, request);
		}
		return "redirect:/price/listTask";
    }
    
    /**
     * 用于根据指定模板id展示表单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "showForm", method = {RequestMethod.POST, RequestMethod.GET})
    public String showForm(HttpServletRequest request, HttpServletResponse response){
    	//要跳转的模板id
    	String formTemplateId = request.getParameter(ISystemConstant.PREDEFINED_NEXT_FREEMARK_ID);
    	FormTemplateVo formTemplateVo = formTemplateService.get(formTemplateId);
    	String freeMarkTemp = formTemplateVo.getTemplateFreemark();
    	Map<String, Object> tempMap = new HashMap<String, Object>();
    	String ctx = request.getContextPath(); 
  		//预定义参数：网站路径
    	tempMap.put(ISystemConstant.PREDEFINED_BACK_CONTEXT_PATH, ctx);
    	//把传过来的参数都添加到map里
    	//System.out.println(request.getParameter("parentId"));
    	Map<String, String[]> param = request.getParameterMap();
    	Set keSet=param.entrySet();  
        for(Iterator itr=keSet.iterator();itr.hasNext();){  
            Map.Entry me=(Map.Entry)itr.next();  
            Object ok=me.getKey();  
            Object ov=me.getValue();  
            String[] value=new String[1];  
            if(ov instanceof String[]){  
            	value=(String[])ov; 
            	if(((String[])ov).length>1){
                    tempMap.put((String)ok, StringHelper.arrayToStr(value));
            	}else{
            		tempMap.put((String)ok, value[0]);
            	}
              
            }else{  
                value[0]=ov.toString(); 
                tempMap.put((String)ok, value[0]);
            }  
         }  
    	//查询指定id返回给bean
        String zdId = request.getParameter(ISystemConstant.PREDEFINED_ZHIDING_ID);
        if(!StringHelper.isEmpty(zdId)){
    		GeneralBusinessProcessVo generalBusinessProcessVo = generalBusinessProcessService.get(zdId);
    		tempMap.put(ISystemConstant.AJAX_BEAN, generalBusinessProcessVo);
        }else{
        	//查询通用数据表
        	String generalBusinessProcessId = request.getParameter(ISystemConstant.PREDEFINED_PARENT_ID);
        	if(!StringHelper.isEmpty(generalBusinessProcessId)){
        		GeneralBusinessProcessVo generalBusinessProcessVo = generalBusinessProcessService.get(generalBusinessProcessId);
        		tempMap.put(ISystemConstant.AJAX_BEAN, generalBusinessProcessVo);
        	}
        }
        
    	//根据父id查询出来对应的通用表子集合
    	String parentId = request.getParameter(ISystemConstant.PREDEFINED_PARENT_ID);
    	if(!StringHelper.isEmpty(parentId)){
    		List<GeneralBusinessProcessVo> generalBusinessProcessVos = generalBusinessProcessService.getByParentId(parentId);
    		tempMap.put(ISystemConstant.AJAX_LIST, generalBusinessProcessVos);
    	}
    	
    	
    	//正则出来数据集
    	String patternString = "<select.*?name=\"(.*?)\".*?datasetid=\"(.*?)\"";    	 
        Pattern pattern = Pattern.compile(patternString);   
        Matcher matcher = pattern.matcher(freeMarkTemp);
        while (true) {
        	if(matcher.find()){
        		int count = matcher.groupCount();
        		if(count==2){
        			//数据集id(根据正则表达式位置确定取第几个)
        	        String dataSetId = matcher.group(2);
        	        DataSetVo dataSetVo = dataSetService.get(dataSetId);
        	        String dataSqlString = dataSetVo.getDataSetDefine();
        	        List<RmCommonVo> rmCommonVos = RmProjectHelper.getCommonServiceInstance().doQuery(dataSqlString);
        	        //接收数据集数据的变量名称(根据正则表达式位置确定取第几个)
        	        tempMap.put(matcher.group(1), rmCommonVos);
        		}
        	}else{
        		break;
        	}
        } 
        
        try {
			freeMarkTemp = TemplateHelper.createContentByString(tempMap, freeMarkTemp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
        
        request.setAttribute("templateFreemark", freeMarkTemp);
    	return "/activiti/wk/generalbusinessprocess/listPriceNullContent";
    }
    
    
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "listPrice")
    public String listPrice(Model model, HttpServletRequest request) {
    	//TODO 未完待续
    	generalBusinessProcessService.listPrice(request);    	
        return "/activiti/wk/generalbusinessprocess/listPriceNullContent";
    }
    
    /**
     * 文件上传
     * @param request
     * @param response
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("springUpload")
    public void  springUpload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	//long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, Object> result = new HashMap<String, Object>();
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
        	//将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext()){
            	//一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){//new String(request.getParameter("path").getBytes("iso-8859-1"), "utf-8")解决中文乱码问题
                	String realPath = pubParamService.getRootPath()+new String(request.getParameter("path").getBytes("iso-8859-1"), "utf-8");
					try {
						if (!FileOperateHelper.isExists(realPath)) {
							FileOperateHelper.newFolder(realPath);
							result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		            		result.put(ISystemConstant.AJAX_MESSAGE, "上传成功!");
						}
						file.transferTo(new File(realPath));
					} catch (Exception e) {
						result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	                	result.put(ISystemConstant.AJAX_MESSAGE, "上传失败!");
						// TODO Auto-generated catch block  
						e.printStackTrace();
					}			
                }else{
                	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
                	result.put(ISystemConstant.AJAX_MESSAGE, "上传失败!");
                }                 
            }           
        }else{
        	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
        	result.put(ISystemConstant.AJAX_MESSAGE, "上传失败!");
        }
        response.setContentType("text/html;charset=utf-8");
    	try {
			response.getWriter().print(JSONObject.toJSONString(result));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
