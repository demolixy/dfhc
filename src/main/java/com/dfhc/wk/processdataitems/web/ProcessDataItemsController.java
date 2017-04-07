/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  ProcessDataItemsController.java
 *
 * 功能描述：  流程数据项Controller
 * 
 * 版本历史：
 * 
 * 2016-12-05   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.processdataitems.web;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.web.PageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfhc.ISystemConstant;
import com.dfhc.pub.service.ActivitiService;
import com.dfhc.util.StringHelper;
import com.dfhc.wk.processdataitems.IProcessDataItemsConstants;
import com.dfhc.wk.processdataitems.service.ProcessDataItemsService;
import com.dfhc.wk.processdataitems.vo.ProcessDataItemsVo;
import com.dfhc.wk.processdataitems.vo.TaskForListVo;
/**
 * list                  /processdataitems
 * insert page      GET  /processdataitems/insert
 * insert action    POST /processdataitems/insert
 * update page      GET  /processdataitems/update/{id}
 * update action    POST /processdataitems/update
 * delete action    POST /processdataitems/delete
 * detail                /processdataitems/detail/{id}

 * reference             /processdataitems/reference
 * import page      GET  /processdataitems/import
 * import action    POST /processdataitems/import
 * export custom    GET  /processdataitems/export
 * export action    POST /processdataitems/export
 */

/**
 * 流程数据项 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/processdataitems")
public class ProcessDataItemsController implements IProcessDataItemsConstants {

    @Autowired
    /**
     * 流程数据项服务
     */
    private ProcessDataItemsService processDataItemsService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService  runtimeService;
    @Autowired
	protected TaskService taskService;
    @Autowired
    private ActivitiService activitiService;
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, processDataItemsService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
        	orderStr = "WK_PROCESS_DATA_ITEMS.CREATE_TIME DESC";
        }
        List<ProcessDataItemsVo> beans = processDataItemsService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        RmJspHelper.saveOrderStr(orderStr, request);  //保存排序信息
        model.addAttribute(REQUEST_QUERY_CONDITION, searchPara);
        model.addAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
        return "/activiti/wk/processdataitems/listProcessDataItems";
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid ProcessDataItemsVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        processDataItemsService.insert(vo);  //插入单条记录
        Map<String, Object> result = new HashMap<String, Object>();
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
    public Map<String,Object> update(HttpServletRequest request, @Valid ProcessDataItemsVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        processDataItemsService.update(vo);  //更新单条记录
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
        ProcessDataItemsVo bean = processDataItemsService.get(id);
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
            deleteCount = processDataItemsService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += processDataItemsService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/processdataitems";
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
            deleteCount = processDataItemsService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += processDataItemsService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/processdataitems";
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
        ProcessDataItemsVo bean = processDataItemsService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/activiti/wk/processdataitems/detailProcessDataItems";
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
        return "/activiti/wk/processdataitems/util/referenceProcessDataItems";
    }

    /**
    * 参照信息查询，带简单查询，分页显示，支持表单回写(流程定义的参照页面)
    * @param model 模型
    * @param request http请求对象
    * @return 返回参照列表页面
    */
   @RequestMapping(value = "referenceProcessDefinition")
   public String referenceProcessDefinition(Model model, HttpServletRequest request) {
	   //*************查询条件开始************
	   Map<String, Object> searchMap = null;
       if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
           searchMap = (Map<String, Object>)request.getAttribute(REQUEST_QUERY_CONDITION);
       } else {
    	   searchMap = new HashMap<String, Object>();
    	   searchMap.put("name", request.getParameter("name"));
    	   searchMap.put("key", request.getParameter("key"));
    	   searchMap.put("version", request.getParameter("version"));
       }
       ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
       String name = (String) searchMap.get("name");
       if(!StringHelper.isEmpty((String)searchMap.get("name"))){
    	   processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike(name);
       }
       String key = (String) searchMap.get("key");
       if(!StringHelper.isEmpty(key)){
    	   processDefinitionQuery = processDefinitionQuery.processDefinitionKeyLike(key);
       }
       String version = (String) searchMap.get("version");
       if(!StringHelper.isEmpty(version)){
    	   processDefinitionQuery = processDefinitionQuery.processDefinitionVersion(Integer.valueOf(version));
       }
       //*************查询条件结束******************
       //*************分页开始******************
	   org.activiti.web.Page<ProcessDefinition> page = new org.activiti.web.Page<ProcessDefinition>(PageUtil.PAGE_SIZE);
	   int[] pageParams = PageUtil.init(page, request);	  
	   RmPageVo pageVo = RmJspHelper.transctPageVo(request, processDefinitionQuery.list().size());
	   pageVo.setCurrentPage(pageParams[0]);
	   pageVo.setPageSize(pageParams[1]);
	   //*************分页结束******************
	   
	   List<ProcessDefinition> list = processDefinitionQuery.listPage(pageParams[0], pageParams[1]);
	   page.setTotalCount(runtimeService.createProcessInstanceQuery().count());
	   //request.setAttribute("page", page);
	   model.addAttribute(REQUEST_QUERY_CONDITION, searchMap);
       model.addAttribute(REQUEST_BEANS, list);  //把结果集放入request
       model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
       model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
       return "/activiti/wk/processdataitems/util/referenceProcessDefinition";
   }
   /**
    * 参照信息查询，带简单查询，分页显示，支持表单回写(流程定义的参照页面)
    * @param model 模型
    * @param request http请求对象
    * @return 返回参照列表页面
    */
   @RequestMapping(value = "referenceProcessTask")
   public String referenceProcessTask(Model model, HttpServletRequest request) {
	   String procDefId = request.getParameter("procDefId");
	   //*************查询条件开始************
	   //TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId(procDefId);
	   List<TaskForListVo> list = activitiService.getTaskNodeListByProDefId(procDefId);
       //*************查询条件结束******************
       //*************分页开始******************
	   org.activiti.web.Page<Task> page = new org.activiti.web.Page<Task>(PageUtil.PAGE_SIZE);
	   int[] pageParams = PageUtil.init(page, request);	  
	   RmPageVo pageVo = RmJspHelper.transctPageVo(request, list.size());
	   pageVo.setCurrentPage(pageParams[0]);
	   pageVo.setPageSize(pageParams[1]);
	   //*************分页结束******************
	   //page.setTotalCount(runtimeService.createProcessInstanceQuery().count());
	   //request.setAttribute("page", page);
       model.addAttribute(REQUEST_BEANS, list);  //把结果集放入request
       model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
       model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
       return "/activiti/wk/processdataitems/util/referenceProcessTask";
   }
    /**
     * 跳转到导入页
     * @param model 模型
     * @return 返回导入页
     */
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importDataForm(Model model) {
        return "/activiti/wk/processdataitems/importProcessDataItems";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/activiti/wk/processdataitems/importProcessDataItems";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/activiti/wk/processdataitems/exportProcessDataItems_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/activiti/wk/processdataitems/exportProcessDataItems_excel";
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
           searchMap.put("dataItemCnName", request.getParameter("dataItemCnName"));
           searchMap.put("dataItemEnName", request.getParameter("dataItemEnName"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    /**
     * 查询所有业务组
     * @param request
     * @return
     */
    @RequestMapping(value = "getBusinessGroupList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getBusinessGroupList(HttpServletRequest request){
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	result.put("procDefId", request.getParameter("procDefId"));
    	result.put("typeKeyword", ISystemConstant.DICTIONARY_BUSINESS_GROUP);
    	
    	List<Map<String, Object>> list = processDataItemsService.getBusinessGroupList(result);
    	
    	result.clear();
    	
    	if(!CollectionUtils.isNotEmpty(list)){
    		
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "该流程尚未定义业务组");
    	}else{
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		result.put(ISystemConstant.AJAX_LIST, list);
    	}
    	
    	return result;
    }
    
    /**
     * 查询所有数据项
     * @param request
     * @return
     */
    @RequestMapping(value = "getBusinessList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getBusinessList(HttpServletRequest request){
    	
    	String procDefId = request.getParameter("procDefId");
    	String businessGroup = request.getParameter("businessGroup");
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	//流程定义id
    	result.put("procDefId", procDefId);
    	//业务组
    	result.put("businessGroup", businessGroup);
    	
    	List<ProcessDataItemsVo> list = processDataItemsService.list(result, null);
    	
    	result.clear();
    	
    	if(!CollectionUtils.isNotEmpty(list)){
    		
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "未找到对应流程数据项。业务组：" + businessGroup + "流程定义id：" + procDefId);
    	}else{
    		
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		result.put(ISystemConstant.AJAX_LIST, list);
    	}
    	return result;
    }
}
