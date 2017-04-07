/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  GeneralBusinessProcessService.java
 *
 * 功能描述：  通用流程业务表服务
 * 
 * 版本历史：
 * 
 * 2016-12-06   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.generalbusinessprocess.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ctc.wstx.util.DataUtil;
import com.dfhc.wk.dataset.service.DataSetService;
import com.dfhc.wk.dataset.vo.DataSetVo;
import com.dfhc.wk.formtemplate.service.FormTemplateService;
import com.dfhc.wk.formtemplate.vo.FormTemplateVo;
import com.dfhc.wk.generalbusinessprocess.IGeneralBusinessProcessConstants;
import com.dfhc.wk.generalbusinessprocess.dao.GeneralBusinessProcessDao;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;
import com.dfhc.wk.processdataitems.service.ProcessDataItemsService;
import com.dfhc.wk.processdataitems.vo.ProcessDataItemsVo;
import com.dfhc.wk.processdataitems.vo.TaskForListVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.ActivitiService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
import com.dfhc.util.TemplateHelper;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.activiti.web.BusinessAppController;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.project.login.RmUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;

import freemarker.template.TemplateException;

import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 通用流程业务表服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class GeneralBusinessProcessService implements IGeneralBusinessProcessConstants {

    @Autowired
    /**
     * 通用流程业务表数据访问对象
     */
    private GeneralBusinessProcessDao generalBusinessProcessDao;
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private ProcessDataItemsService processDataItemsService;
    
    @Autowired
    private FormTemplateService formTemplateService;
    
    @Autowired
    private BusinessAppController businessAppController;

    @Autowired
	/**
	 * 数据集服务
	 */
	private DataSetService dataSetService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ActivitiService activitiService;
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(GeneralBusinessProcessVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = generalBusinessProcessDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "通用流程业务表插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(GeneralBusinessProcessVo[] vos) {
        String[] ids = generalBusinessProcessDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "通用流程业务表插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = generalBusinessProcessDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "通用流程业务表删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = generalBusinessProcessDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "通用流程业务表删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(GeneralBusinessProcessVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = generalBusinessProcessDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "通用流程业务表更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(GeneralBusinessProcessVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "通用流程业务表批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(GeneralBusinessProcessVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<GeneralBusinessProcessVo> lInsert = new ArrayList<GeneralBusinessProcessVo>();
        List<GeneralBusinessProcessVo> lUpdate = new ArrayList<GeneralBusinessProcessVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new GeneralBusinessProcessVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new GeneralBusinessProcessVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public GeneralBusinessProcessVo get(String id) {
        GeneralBusinessProcessVo vo = generalBusinessProcessDao.get(id);
        return vo;
    }
    
    /**
     * 根据上级id查询
     * @param parentId 上级id
     * @return 查询到的VO对象集合
     */
    public List<GeneralBusinessProcessVo> getByParentId(String parentId){
    	return generalBusinessProcessDao.getByParentId(parentId);
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = generalBusinessProcessDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<GeneralBusinessProcessVo> list(Map<String, Object> searchPara, String orderStr) {
        return list(searchPara, orderStr, 1, Integer.MAX_VALUE);
    }

    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<GeneralBusinessProcessVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        return list(searchPara, orderStr, startIndex, size, false);
    }
    
    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @param allColumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<GeneralBusinessProcessVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<GeneralBusinessProcessVo> lResult = generalBusinessProcessDao.list(searchPara, orderStr, startIndex, size, allColumn);
        return lResult;
    }
    
    /**
     * 按条件搜索，走MyBatis的XML文件的search
     * 
     * @param searchPara 搜索参数的Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<GeneralBusinessProcessVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<GeneralBusinessProcessVo> lResult = generalBusinessProcessDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(GeneralBusinessProcessVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(GeneralBusinessProcessVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<GeneralBusinessProcessVo> vos) {
        for(GeneralBusinessProcessVo vo:vos){
           verifyUpdateVo(vo);
        }
        generalBusinessProcessDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<GeneralBusinessProcessVo> vos) {
        for(GeneralBusinessProcessVo vo:vos){
           verifyInsertVo(vo);
        }
        generalBusinessProcessDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        GeneralBusinessProcessVo vo = generalBusinessProcessDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(generalBusinessProcessDao.update(vo)!=1){
           throw new PjException("更新删除标志失败!");
        }
        return 1;
    }

    /**
     * 逻辑删除多条记录
     * @param request 页面请求
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String ids[]) {
        if(ids!=null && ids.length>0 ){
           for(String id:ids){
               deleteLogic(request,id);
           }
           return ids.length;
        }else{
           throw new PjException("ids 为空!");
        }
    }
    /**
     * 查询待办任务
     */
    @Transactional(readOnly = true)
    public List<Task> findTodoTasks(String userId) {
        // 根据当前人的ID查询
        List<Task> todoList = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        // 根据当前人未签收的任务
        List<Task> unsignedTasks = taskService.createTaskQuery().taskCandidateUser(userId).orderByTaskCreateTime().desc().list();
        todoList.addAll(unsignedTasks);
        return todoList;
    }
    /**
     * 完成任务
     * @param generalBusinessProcessVo 通用流程业务表
     * @param taskId 任务id
     */
    public void insertComplete(GeneralBusinessProcessVo generalBusinessProcessVo, String taskId, HttpServletRequest request){
    	 if (generalBusinessProcessVo!=null && !StringHelper.isEmpty(generalBusinessProcessVo.getProcDefId())) {
             this.update(generalBusinessProcessVo);
         } 
    	//=========begin 判断是否存在文件上传到了临时目录=========
    	 businessAppController.uploadFormTemp(request, generalBusinessProcessVo.getId(), generalBusinessProcessVo);
		//=========end 判断是否存在文件上传到了临时目录============
    	 //判断是否是带有审核意见的
    	 //审核意见
    	 String isPass = request.getParameter(ISystemConstant.PREDEFINED_IS_PASS);
    	 Map<String, Object> temp = new HashMap<String, Object>();
    	 if(!StringHelper.isEmpty(isPass)){
    		//如果是带有审核意见的,查询下审核人员信息记录是否存在
    		 RmUserVo user = RmProjectHelper.getRmUserVo(request);
    		 String auditState = "";
    		 if(!StringHelper.isEmpty(isPass) && ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(isPass)){
    			 auditState = ISystemConstant.PREDEFINED_IS_PASS_YES_CHINESE;
        	 }else{
        		 auditState = ISystemConstant.PREDEFINED_IS_PASS_NOT_CHINESE;
        	 }
    		 temp.put("taskId", taskId);
    		 temp.put("userId", user.getId());
    		 temp.put("userName", user.getName());
    		 temp.put("auditstate", auditState);
    		 temp.put("auditTime", DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
    	 }		 
    	 Map<String, Object> variables = RmVoHelper.getMapFromVo(generalBusinessProcessVo);
    	 if(!StringHelper.isEmpty(isPass) && ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(isPass)){
    		 variables.put("isPass", true);
    	 }else{
    		 variables.put("isPass", false);
    	 }    	
    	 variables.putAll(temp);
    	 taskService.setVariablesLocal(taskId, temp);
         taskService.complete(taskId, variables);
    }
    
    /**
     * 根据流程定义id,业务类型,业务组查询数据
     * @param request
     */
    public void listPrice(HttpServletRequest request){
    	String freeMarkId = request.getParameter("fmId");
    	String procDefId = request.getParameter("pdId");//流程定义id
    	String businessType = request.getParameter("bt");//业务类型
    	String businessGroup = request.getParameter("bg");//业务组
    	//String parentId = request.getParameter("parentId");//上级id
    	Map<String, Object> searchMap = new HashMap<String, Object>();
    	searchMap.put("procDefId", procDefId);
    	searchMap.put("businessGroup", businessGroup);
    	
    	//先查询流程数据项
    	List<ProcessDataItemsVo> processDataItemsVos = processDataItemsService.list(searchMap, null);
    	searchMap.clear();
    	if(processDataItemsVos.size()>0){
    		for (ProcessDataItemsVo processDataItemsVo : processDataItemsVos) {
				searchMap.put(processDataItemsVo.getDataItemEnName(), processDataItemsVo.getDataItemCnName());
			}
    	}
    	//===========begin 获取模板================
		FormTemplateVo formTemplateVo = formTemplateService.get(freeMarkId);
		String templateFreemark = formTemplateVo.getTemplateFreemark();
		//===========end 获取模板================
		//TODO 未完待续
    }
    
    /**
     * 获取添加价格信息页面html
     * @param request
     * @param nextFreemarkId 添加价格信息页面freeMark模板id
     * @param id 新增后的id
     * @param vo 页面注入的vo(父vo)
     * @return html
     */
    public String getAddPriceHtml(HttpServletRequest request, String nextFreemarkId, GeneralBusinessProcessVo vo){
    	FormTemplateVo formTemplateVo = formTemplateService.get(nextFreemarkId);
		String templateFreemark = formTemplateVo.getTemplateFreemark();	
    	//查询价格列表
		List<GeneralBusinessProcessVo> generalBusinessProcessVos = this.getByParentId(vo.getId());
		
    	//获取流程定义id和业务组对应的流程数据项
    	Map<String, Object> tableMap = this.getProcessDataItems(vo.getProcDefId(), vo.getBusinessGroup());
    	tableMap.put("businessAttribute70", vo.getBusinessAttribute70());
    	tableMap.put("businessAttribute69", vo.getBusinessAttribute69());
    	tableMap.put("businessAttribute68", vo.getBusinessAttribute68());
    	tableMap.put("procDefId", vo.getProcDefId());
    	tableMap.put("businessType", vo.getBusinessType());
    	tableMap.put("businessGroup", vo.getBusinessGroup());
    	tableMap.put("parentId", vo.getId());
    	tableMap.put("generalBusinessProcessVos", generalBusinessProcessVos);
    	//预定义参数：网站路径
    	tableMap.put(ISystemConstant.PREDEFINED_BACK_CONTEXT_PATH, request.getContextPath());
    	try {
    		//替换流程数
			templateFreemark = TemplateHelper.createContentByString(tableMap, templateFreemark);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
    	return templateFreemark;
    }
    
    /**
     * 根据流程定义id和业务组查询流程数据项
     * @param processDefinitionId 流程定义id
     * @param businessGroup 业务组
     * @return map键值对
     */
    public Map<String, Object> getProcessDataItems(String processDefinitionId, String businessGroup){
    	Map<String, Object> searchMap = new HashMap<String, Object>();
    	//查询流程数据项
		searchMap.put("procDefId", processDefinitionId);
		searchMap.put("businessGroup", businessGroup);
		List<ProcessDataItemsVo> processDataItemsVos = processDataItemsService.list(searchMap, null);
		searchMap.clear();
		for (ProcessDataItemsVo processDataItemsVo : processDataItemsVos) {
			searchMap.put(processDataItemsVo.getDataItemEnName(), processDataItemsVo.getDataItemCnName());
		}
		return searchMap;
    }
    
    /**
     * 根据模板和流程定义id获取模板map
     * @param templateFreemark 模板字符串
     * @param processDefinitionId 流程定义id
     * @param taskDataMap 模板map
     * @return 模板map
     */
    public Map<String, Object> getFreemarkMap(String templateFreemark, String processDefinitionId, Map<String, Object> taskDataMap){
    	//判断模板是否有业务组,如果有业务组则查询流程数据项
		int isHave = templateFreemark.indexOf("businessGroup");
		if(isHave>-1){
			String tempString = templateFreemark.substring(isHave);
			int startIndex = tempString.indexOf("value=\"")+7;
			String businessGroup = tempString.substring(startIndex, startIndex+2);
			Map<String, Object> searchMap = new HashMap<String, Object>();
			//查询流程数据项
			searchMap.put("procDefId", processDefinitionId);
			searchMap.put("businessGroup", businessGroup);
			List<ProcessDataItemsVo> processDataItemsVos = processDataItemsService.list(searchMap, null);
			for (ProcessDataItemsVo processDataItemsVo : processDataItemsVos) {
				taskDataMap.put(processDataItemsVo.getDataItemEnName(), processDataItemsVo.getDataItemCnName());
			}
		}
		
		String pattern = "<select.*?name=\"(.*?)\".*?datasetid=\"(.*?)\".*?>";	//正则出来id
		Pattern ptId = Pattern.compile(pattern);
		Matcher mtId = ptId.matcher(templateFreemark);
		while(mtId.find()){
	    	String nameString = mtId.group(1);
	    	String dataSetId = mtId.group(2);
	    	
	    	DataSetVo dataSetVo = dataSetService.get(dataSetId);
	    	String sqlString = dataSetVo.getDataSetDefine();
	    	List<RmCommonVo> rmCommonVos = RmProjectHelper.getCommonServiceInstance().doQuery(sqlString);
	    	
	    	taskDataMap.put(nameString, rmCommonVos);
		}
		return taskDataMap;
    }
    
    /**
     * 根据任务id获取任务key
     * @param taskId 
     * @return
     */
    public String getTaskDefKeyByTaskId(String taskId){
    	return taskService.createTaskQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
    }
    /**
     * 根据任务key获取任务id
     * @param taskKey 
     * @return
     */
    public String getTaskIdByTaskDefKey(String taskDefKey, String procInstId){
    	List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().processInstanceId(procInstId).taskDefinitionKey(taskDefKey).orderByHistoricTaskInstanceEndTime().desc().list();
    	if(historicTaskInstances==null || historicTaskInstances.size()==0){
    		return "";
    	}else{
    		return historicTaskInstances.get(0).getId();
    	}
    }
    /**
     * 根据流程定义id和流程实例id查询
     * @param processDefinitionId 流程定义id
     * @param processInstanceId 流程实例id
     * @return 集合
     */
    public List<GeneralBusinessProcessVo> getListForProcDefIdAndProcInstId(String processDefinitionId, String processInstanceId){
    	Map<String, Object> searchMap = new HashMap<String, Object>();
    	searchMap.put("procDefId", processDefinitionId);
    	searchMap.put("procInstId", processInstanceId);
    	return this.list(searchMap, null);
    }
    /**
     * 根据流程实例id查询流程通用表数据
     * @param procInstId 流程实例id
     * @return vo对象
     */
    public GeneralBusinessProcessVo getGeneralBusinessProcessVoByProcInstId(String procInstId){
    	GeneralBusinessProcessVo generalBusinessProcessVo = null;
    	Map<String, Object> searchMap = new HashMap<String, Object>();
    	searchMap.put("procInstId", procInstId);
    	List<GeneralBusinessProcessVo> generalBusinessProcessVos = this.list(searchMap, null);
    	if(generalBusinessProcessVos.size()>0){
    		generalBusinessProcessVo = generalBusinessProcessVos.get(0);
    	}
    	return generalBusinessProcessVo;
    }
}
