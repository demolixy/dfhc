package com.dfhc.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.base.price.service.PriceService;
import com.dfhc.base.price.vo.PriceVo;
import com.dfhc.pub.service.BsTransaction;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;
import com.dfhc.ISystemConstant;

public class UpdatePrice implements BsTransaction {

	@Override
	@Transactional
	public void doSave(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> readTaskData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void doSaveTaskData(HttpServletRequest request,
			HttpServletResponse response) {
		 TaskService taskService = (TaskService) RmBeanFactory.getBean("taskService");
		 String taskId = (String) request.getAttribute("id");
	   	 String isPass = request.getParameter("isPass");
	   	 Map variables = new HashMap();
	   	 Map temp = new HashMap();
	   	 System.out.println("==================ispsss:"+isPass);
		 if(!StringHelper.isEmpty(isPass)){
			//如果是带有审核意见的,查询下审核人员信息记录是否存在
			 RmUserVo user = RmProjectHelper.getRmUserVo(request);
			 String auditState = "";
			 System.out.println("==================ispsss2:"+isPass);
			 if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(isPass)){
				 Task task = (Task) taskService.createTaskQuery().taskId(taskId).singleResult();
				 Map searchMap = new HashMap();
				 searchMap.put("procDefId", task.getProcessDefinitionId());
				 searchMap.put("procInstId", task.getProcessInstanceId());
				 GeneralBusinessProcessService generalBusinessProcessService = (GeneralBusinessProcessService) RmBeanFactory.getBean(GeneralBusinessProcessService.class.getName());
				 List generalBusinessProcessVos = generalBusinessProcessService.list(searchMap, null);
				 System.out.println("==================generalBusinessProcessVos:size:"+generalBusinessProcessVos.size());
				 if(generalBusinessProcessVos.size()>0){
					 GeneralBusinessProcessVo generalBusinessProcessVo = (GeneralBusinessProcessVo) generalBusinessProcessVos.get(0);
					 String id = generalBusinessProcessVo.getId();
					 List generalBusinessProcessVoList = generalBusinessProcessService.getByParentId(id);
					 System.out.println("generalBusinessProcessVoList size:"+generalBusinessProcessVoList.size());
					 List priceVos = new ArrayList();
					 PriceService priceService = (PriceService) RmBeanFactory.getBean(PriceService.class.getName());
					 for (Object object : generalBusinessProcessVoList) {
						 GeneralBusinessProcessVo gbpVo = (GeneralBusinessProcessVo) object;
						 System.out.println("gbpVo.getBusinessAttribute70():"+gbpVo.getBusinessAttribute70());
						 String productId = gbpVo.getBusinessAttribute70();
						 searchMap.clear();
						 searchMap.put("productId", productId);
						 searchMap.put("status", ISystemConstant.DICTIONARY_PRICE_STATUS_2);
						 List priceVoList = priceService.list(searchMap, null);
						 PriceVo priceVo = (PriceVo) priceVoList.get(0);
						 priceVo.setStatus(ISystemConstant.DICTIONARY_PRICE_STATUS_3);
						 priceVos.add(priceVo);
					 }
					 System.out.println("==================priceVos:size:"+priceVos.size());
					 if(priceVos.size()>0){
						 priceService.updateBatch(priceVos);
					 }
				 }
				 auditState = "审核通过";
		   	 }else{
		   		 auditState = "审核不通过";
		   	 }
			 temp.put("taskId", taskId);
			 temp.put("userId", user.getId());
			 temp.put("userName", user.getName());
			 temp.put("auditstate", auditState);
			 temp.put("auditTime", DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
		 }
		 if(!StringHelper.isEmpty(isPass) && ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(isPass)){
			 variables.put("isPass", true);
		 }else{
			 variables.put("isPass", false);
		 }    	
		 variables.putAll(temp);
		 taskService.setVariablesLocal(taskId, temp);
	     taskService.complete(taskId, variables);
	}

}
