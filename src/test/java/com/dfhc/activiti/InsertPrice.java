package com.dfhc.activiti;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.beanutils.BeanUtils;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.orgauth.rmparty.service.impl.RmPartyService;
import org.quickbundle.orgauth.rmparty.vo.RmPartyVo;
import org.quickbundle.orgauth.rmuser.service.impl.RmUserService;
import org.quickbundle.orgauth.rmuser.util.IRmUserConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.transaction.annotation.Transactional;
import com.dfhc.ISystemConstant;
import com.dfhc.base.price.service.PriceService;
import com.dfhc.pub.service.BsTransaction;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;

public class InsertPrice implements BsTransaction {

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
		String parentId = (String) request.getAttribute("parentId");
		String processDefinitionId = (String) request.getAttribute("processDefinitionId");
		GeneralBusinessProcessVo generalBusinessProcessVo = (GeneralBusinessProcessVo) request.getAttribute("generalBusinessProcessVo");
		GeneralBusinessProcessService generalBusinessProcessService = (GeneralBusinessProcessService) RmBeanFactory.getBean(GeneralBusinessProcessService.class.getName());
		if(!StringHelper.isEmpty(parentId)){
			generalBusinessProcessVo.setId(parentId);
			generalBusinessProcessVo.setParentId("");
    	}
		
		PriceService priceService = (PriceService) RmBeanFactory.getBean(PriceService.class.getName());
		//修改价格表数据
		priceService.doInsertFromProc(request, parentId);
		//设置制表日期
		generalBusinessProcessVo.setBusinessAttribute67(DateUtil.getNowDateString());
		
		RepositoryService repositoryService = (RepositoryService) RmBeanFactory.getBean("repositoryService");
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
		RmUserService rmUserService = (RmUserService) RmBeanFactory.getBean(IRmUserConstants.SERVICE_KEY);
		org.quickbundle.orgauth.rmuser.vo.RmUserVo userVo = rmUserService.find(userId);
		String organizationId = userVo.getOrganization_id();
		generalBusinessProcessVo.setInitiatorOrganizationId(organizationId);//设置发起机构部门ID
		if(!StringHelper.isEmpty(organizationId)){
			RmPartyService rmPartyService = (RmPartyService) RmBeanFactory.getBean(RmPartyService.class.getName());
			RmPartyVo rmPartyVo = rmPartyService.find(organizationId);
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
			RmProjectHelper.logError("update generalBusinessProcessVo:"+generalBusinessProcessVo, "update generalBusinessProcessVo:"+generalBusinessProcessVo);
		}else{
			//插入通用流程业务表数据
			generalBusinessProcessId = generalBusinessProcessService.insert(generalBusinessProcessVo);
		}
		
		//获取插入的通用流程业务表数据,转换成map
		GeneralBusinessProcessVo generalBusinessProcessVo2 = generalBusinessProcessService.get(generalBusinessProcessId);
		//=========begin 判断是否存在文件上传=========
		String processTempPath = (String) request.getAttribute(ISystemConstant.PREDEFINED_TEMP_PATH);
    	Map fileParamMap = new HashMap();
    	Enumeration paramNames = request.getAttributeNames(); 
    	while (paramNames.hasMoreElements()) { 
    		String paramName = (String) paramNames.nextElement(); 
    		String paramValue = request.getAttribute(paramName).toString(); 
			if(!StringHelper.isEmpty(paramValue) && paramValue.indexOf(processTempPath+ISystemConstant.DICTIONARY_SYSTEM_SEPARATOR)>-1){
				fileParamMap.put(paramName, paramValue);
			}
    	} 
    	PubParamService pubParamService = (PubParamService) RmBeanFactory.getBean(PubParamService.class.getName());
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
    	//=========end 判断是否存在文件上传============
		Map voMap = RmVoHelper.getMapFromVo(generalBusinessProcessVo2);
		voMap.put(ISystemConstant.BUSINESS_KEY, generalBusinessProcessId);//可能页面会用到
		//启动流程,返回流程实例
		RuntimeService runtimeService = (RuntimeService) RmBeanFactory.getBean("runtimeService");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(procKey, generalBusinessProcessId, voMap);
		String processInstanceId = processInstance.getId();
		//回填流程实例id
		generalBusinessProcessVo2.setProcInstId(processInstanceId);
//		PubParamService pubParamService = (com.dfhc.pub.service.PubParamService) RmBeanFactory.getBean("PubParamService");
		Date da = new Date();
		generalBusinessProcessVo2.setBusinessAttribute66(pubParamService.getSequenceNumber("PRICE_APPLY_CODE")); //设置价格申请单号
		generalBusinessProcessService.update(generalBusinessProcessVo2);
	}
}
