package org.activiti.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.beanutils.BeanUtils;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.orgauth.rmparty.service.impl.RmPartyService;
import org.quickbundle.orgauth.rmparty.util.IRmPartyConstants;
import org.quickbundle.orgauth.rmparty.vo.RmPartyVo;
import org.quickbundle.orgauth.rmuser.service.impl.RmUserService;
import org.quickbundle.orgauth.rmuser.util.IRmUserConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.tools.helper.RmVoHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfhc.ISystemConstant;
import com.dfhc.pub.service.ActivitiService;
import com.dfhc.pub.service.BeanShellService;
import com.dfhc.pub.service.FileUploadService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;
import com.dfhc.util.TemplateHelper;
import com.dfhc.wk.formtemplate.service.FormTemplateService;
import com.dfhc.wk.formtemplate.vo.FormTemplateVo;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;
import com.dfhc.wk.processdataitems.service.ProcessDataItemsService;
import com.dfhc.wk.processdataitems.vo.ProcessDataItemsVo;
import com.dfhc.wk.processdataitems.vo.TaskForListVo;
import com.dfhc.wk.tasknodeextconf.service.TaskNodeExtConfService;
import com.dfhc.wk.tasknodeextconf.vo.TaskNodeExtConfVo;

import freemarker.template.TemplateException;

/**
 * 业务申请控制器
 * 
 * @author henryyan
 */
@Controller
@RequestMapping(value = "/businessAppController")
public class BusinessAppController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	RuntimeService  runtimeService;
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
	 * 通用流程业务表服务
	 */
	private GeneralBusinessProcessService generalBusinessProcessService;
	@Autowired
	/**
	 * 流程数据项服务
	 */
	private ProcessDataItemsService processDataItemsService;
	@Autowired
	/**
	 * 公共参数服务
	 */
	private PubParamService pubParamService;
	@Autowired
	/**
	 * 仓库服务
	 */
	private RepositoryService repositoryService;
	
	@Autowired
	private  FileUploadService fileUploadService;
	private RmUserService getRmUserService(){
		return (RmUserService) RmBeanFactory.getBean(IRmUserConstants.SERVICE_KEY);
	}
	private RmPartyService getRmPartyService(){
		return (RmPartyService) RmBeanFactory.getBean(IRmPartyConstants.SERVICE_KEY);
	}
	/**
	 * 业务申请详情页面
	 * @param processDefinitionId 流程定义id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "detail/{processDefinitionId}")
	public String start(@PathVariable("processDefinitionId") String processDefinitionId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//===========begin 模板参数============
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("procDefId", processDefinitionId);
		List<TaskNodeExtConfVo> taskNodeExtConfVos = taskNodeExtConfService.list(searchMap, null);
		Map<String, Object> taskDataMap = new HashMap<String, Object>();
		if(taskNodeExtConfVos.size()>0){
			TaskNodeExtConfVo taskNodeExtConfVo = taskNodeExtConfVos.get(0);
			String readTaskDataScript = taskNodeExtConfVo.getReadTaskDataScript();
			if(!StringHelper.isEmpty(readTaskDataScript)){
				taskDataMap = beanShellService.readTaskData(request, readTaskDataScript);
			}
		}
		taskDataMap.put("procDefId", processDefinitionId);
		String ctx = request.getContextPath(); 
		//预定义参数：网站路径
		taskDataMap.put(ISystemConstant.PREDEFINED_BACK_CONTEXT_PATH, ctx);
		//预定义参数：流程定义id
		taskDataMap.put(ISystemConstant.PREDEFINED_PROCESS_DEFINITION_ID, processDefinitionId);
		//===========end 模板参数============
		
		//===========begin 获取模板================
		String formTemplateId = activitiService.getStartFormDataKey(processDefinitionId);
		if(StringHelper.isEmpty(formTemplateId)){
			redirectAttributes.addFlashAttribute("message", "当前选择的流程定义id:"+processDefinitionId+" 没有找到与之关联的开始表单的key!");
			return "redirect:/workflow/model/businessApplication";
		}
		FormTemplateVo formTemplateVo = formTemplateService.get(formTemplateId);
		if(formTemplateVo==null){
			redirectAttributes.addFlashAttribute("message", "根据表单模板id:"+formTemplateId+" 没有找到对应的表单模板!");
			return "redirect:/workflow/model/businessApplication";
		}
		String templateFreemark = formTemplateVo.getTemplateFreemark();		
		//===========end 获取模板================
		
		//判断模板是否有业务组,如果有业务组则查询流程数据项
		int isHave = templateFreemark.indexOf("businessGroup");
		if(isHave>-1){
			String tempString = templateFreemark.substring(isHave);
			int startIndex = tempString.indexOf("value=\"")+7;
			String businessGroup = tempString.substring(startIndex, startIndex+2);
			//System.out.println(businessGroup);
			searchMap.clear();
			//查询流程数据项
			searchMap.put("procDefId", processDefinitionId);
			searchMap.put("businessGroup", businessGroup);
			List<ProcessDataItemsVo> processDataItemsVos = processDataItemsService.list(searchMap, null);
			for (ProcessDataItemsVo processDataItemsVo : processDataItemsVos) {
				taskDataMap.put(processDataItemsVo.getDataItemEnName(), processDataItemsVo.getDataItemCnName());
			}
		}
		String parentId = RmIdFactory.requestId("wk_general_business_process");
		taskDataMap.put("parentId", parentId);
		String applyDate = DateUtil.getDateStr(new Date(), "MM月dd日");//申请日期
		String tableDate = DateUtil.getDateStr(new Date(), "yyyy-MM-dd");//制表时间
		taskDataMap.put("applyDate", applyDate);
		taskDataMap.put("tableDate", tableDate);
		try {
			templateFreemark = TemplateHelper.createContentByString(taskDataMap, templateFreemark);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		request.setAttribute("templateFreemark", templateFreemark);
		return "/activiti/wk/generalbusinessprocess/listPriceNullContent";
	}
	
	/**
     * 启动流程
	 * @throws Exception 
     */
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public String startWorkflow(GeneralBusinessProcessVo generalBusinessProcessVo, RedirectAttributes redirectAttributes, HttpSession session,HttpServletRequest request,HttpServletResponse response
    		,@RequestParam(value = "accessoryFile", required = false) MultipartFile accessoryFile) throws Exception {
    	String processDefinitionId = request.getParameter("processDefinitionId");
    	String parentId = request.getParameter("parentId");
    	
    	//附件上传
    	if(accessoryFile!=null && !accessoryFile.isEmpty()){
    		String accessory = fileUploadService.uploadFile(accessoryFile,parentId,ISystemConstant.DICTIONARY_ROOT_PATH_03);
        	request.setAttribute("accessory", accessory);
    	}
    	Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("procDefId", processDefinitionId);
		List<TaskForListVo> taskForListVos = activitiService.getTaskNodeListByProDefId(processDefinitionId);
		for (TaskForListVo taskForListVo : taskForListVos) {
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(taskForListVo.getIsStart())){
				searchMap.put("taskId", taskForListVo.getTaskId());
				break;
			}
		}
		List<TaskNodeExtConfVo> taskNodeExtConfVos = taskNodeExtConfService.list(searchMap, null);
		if(taskNodeExtConfVos.size()>0 && !StringHelper.isEmpty(taskNodeExtConfVos.get(0).getWriteTaskDataScript())){
			TaskNodeExtConfVo taskNodeExtConfVo = taskNodeExtConfVos.get(0);
			String writeTaskDataScript = taskNodeExtConfVo.getWriteTaskDataScript();
			try {
				Enumeration paramNames = request.getParameterNames(); 
		    	while (paramNames.hasMoreElements()) { 
		    		String paramName = (String) paramNames.nextElement(); 
		    		String[] paramValues = request.getParameterValues(paramName); 
		    		if (paramValues.length == 1) { 
		    			String paramValue = paramValues[0]; 
		    			request.setAttribute(paramName, paramValue);
		    		} 
		    	} 
				//request.setAttribute("generalBusinessProcessVo", generalBusinessProcessVo);
		    	
				beanShellService.doSaveTaskData(request, response, writeTaskDataScript);
			} catch (Exception e) {
				 logger.error("启动流程失败,执行启动BeanShell脚本错误：", e);
		         return "redirect:/businessAppController/detail/"+processDefinitionId+"?errmsg="+e.getMessage();
			}
		}else{
			if(!StringHelper.isEmpty(parentId)){
				generalBusinessProcessVo.setId(parentId);
				generalBusinessProcessVo.setParentId("");
	    	}
			ProcessDefinition proc = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
			String processDefinitionName = proc.getName();
			String procKey = proc.getKey();
			RmUserVo user = RmProjectHelper.getRmUserVo(request);
			String userId = user.getId();
			generalBusinessProcessVo.setProcDefId(processDefinitionId);//设置流程id
			generalBusinessProcessVo.setProcName(processDefinitionName);//设置流程名称
			generalBusinessProcessVo.setProcKey(procKey);//设置流程KEY
			generalBusinessProcessVo.setInitiatorId(userId);//设置发起人id
			generalBusinessProcessVo.setInitiator(user.getName());//设置发起人
			org.quickbundle.orgauth.rmuser.vo.RmUserVo userVo = getRmUserService().find(userId);
			String organizationId = userVo.getOrganization_id();
			generalBusinessProcessVo.setInitiatorOrganizationId(organizationId);//设置发起机构部门ID
			if(!StringHelper.isEmpty(organizationId)){
				RmPartyVo rmPartyVo = getRmPartyService().find(organizationId);
				if(rmPartyVo!=null){
					String organizationName = rmPartyVo.getName();
					generalBusinessProcessVo.setSendOrganizationDepartment(organizationName);//设置发起机构部门
				}
			}
			generalBusinessProcessVo.setInitiatorTime(DateUtil.getNowTimestamp());//设置发起时间
			String generalBusinessProcessId = "";
			if(!StringHelper.isEmpty(parentId)){
				generalBusinessProcessId = parentId;
				generalBusinessProcessService.update(generalBusinessProcessVo);
			}else{
				//插入通用流程业务表数据
				generalBusinessProcessId = generalBusinessProcessService.insert(generalBusinessProcessVo);
			}
			//获取插入的通用流程业务表数据,转换成map
			GeneralBusinessProcessVo generalBusinessProcessVo2 = generalBusinessProcessService.get(generalBusinessProcessId);
			//=========begin 判断是否存在文件上传到了临时目录=========
			this.uploadFormTemp(request, generalBusinessProcessId, generalBusinessProcessVo2);
			//=========end 判断是否存在文件上传到了临时目录============
			Map<String, Object> voMap = RmVoHelper.getMapFromVo(generalBusinessProcessVo2);
			voMap.put(ISystemConstant.BUSINESS_KEY, generalBusinessProcessId);//可能页面会用到
			//启动流程,返回流程实例
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(procKey, generalBusinessProcessId, voMap);
			String processInstanceId = processInstance.getId();
			//回填流程实例id
			generalBusinessProcessVo2.setProcInstId(processInstanceId);
			generalBusinessProcessService.update(generalBusinessProcessVo2);
		}
		return "redirect:/price?statusCode=1";
    }
    /**
     * 判断request里面是否包含临时目录路径标志
     * @param request 
     * @param generalBusinessProcessId 通用业务类型id
     * @param generalBusinessProcessVo2 通用业务类型vo
     */
    public void uploadFormTemp(HttpServletRequest request, String generalBusinessProcessId, GeneralBusinessProcessVo generalBusinessProcessVo2){
		String processTempPath = request.getParameter(ISystemConstant.PREDEFINED_TEMP_PATH);
    	Map<String, Object> fileParamMap = new HashMap<String, Object>();
    	Enumeration paramNames = request.getParameterNames(); 
    	while (paramNames.hasMoreElements()) { 
    		String paramName = (String) paramNames.nextElement(); 
    		String[] paramValues = request.getParameterValues(paramName); 
    		if (paramValues.length == 1) { 
    			String paramValue = paramValues[0]; 
    			if(!StringHelper.isEmpty(paramValue) && paramValue.indexOf(processTempPath+ISystemConstant.DICTIONARY_SYSTEM_SEPARATOR)>-1){
    				fileParamMap.put(paramName, paramValue);
    			}
    		} 
    	} 
    	if(fileParamMap.size()>0){
    		//获取随机数
    		String randomNum = request.getParameter(ISystemConstant.PREDEFINED_TEMP_PATH_RANDOM_NUM);
    		//获取临时目录
    		String tampPath = request.getParameter(ISystemConstant.PREDEFINED_TEMP_PATH);
    		//获取正式目录
    		String processOfficialPath = request.getParameter(ISystemConstant.PREDEFINED_OFFICIAL_PATH);
    		//文件存放根目录
    		String rootPath = pubParamService.getRootPath() ;
    		Iterator entries = fileParamMap.entrySet().iterator();  
    		while (entries.hasNext()) {  
    		    Map.Entry entry = (Map.Entry) entries.next();  
    		    String paramName = (String)entry.getKey();  
    		    String paramValue = rootPath + (String)entry.getValue();  
    		    String realPath = paramValue.replaceAll(tampPath, processOfficialPath);
    			realPath = realPath.replaceAll(randomNum, generalBusinessProcessId);
    			String newPath = realPath.substring(0, realPath.lastIndexOf(ISystemConstant.DICTIONARY_SYSTEM_SEPARATOR)+1);
    			newPath = newPath.replaceAll("\\\\", "/");
    			File file = new File(newPath);
    			if(!(file.exists())){
    				file.mkdirs();
    			}
    			try {
					FileOperateHelper.moveFile(paramValue, realPath);
					realPath = realPath.replaceAll(rootPath, "");
	    			BeanUtils.setProperty(generalBusinessProcessVo2, paramName, realPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}  
    	}
    }
}
