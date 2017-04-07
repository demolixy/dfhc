package com.dfhc.activiti;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.orgauth.rmparty.service.impl.RmPartyService;
import org.quickbundle.orgauth.rmparty.vo.RmPartyVo;
import org.quickbundle.orgauth.rmuser.service.impl.RmUserService;
import org.quickbundle.orgauth.rmuser.util.IRmUserConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.ISystemConstant;
import com.dfhc.base.price.service.PriceService;
import com.dfhc.base.price.vo.PriceVo;
import com.dfhc.pub.service.BsTransaction;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;

public class UpdateBatch implements BsTransaction {

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
		String parentId = request.getParameter("parentId");
		String procDefId = request.getParameter("procDefId");
		String procInstId = request.getParameter("procInstId");
		String taskId = request.getParameter("taskId");
		String dateTitle = request.getParameter("dateTitle");
		String companyName = request.getParameter("companyName");
		String priceUnit = request.getParameter("priceUnit");
		String tableDate = request.getParameter("tableDate");
		String remark = request.getParameter("remark");

		RmUserVo user = RmProjectHelper.getRmUserVo(request);
		String userId = user.getId();
		RepositoryService repositoryService = (RepositoryService) RmBeanFactory.getBean("repositoryService");
		ProcessDefinition proc = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
		String procName = proc.getName();
		String procKey = proc.getKey();
		PubParamService pubParamService = (PubParamService) RmBeanFactory.getBean(PubParamService.class.getName());
		GeneralBusinessProcessVo gbParentVo = new GeneralBusinessProcessVo();
		gbParentVo.setId(parentId);
		gbParentVo.setProcDefId(procDefId);
		gbParentVo.setProcName(procName);
		gbParentVo.setProcKey(procKey);
		gbParentVo.setInitiatorId(userId);//设置发起人id
		gbParentVo.setInitiator(user.getName());//设置发起人
		RmUserService rmUserService = (RmUserService) RmBeanFactory.getBean(IRmUserConstants.SERVICE_KEY);
		org.quickbundle.orgauth.rmuser.vo.RmUserVo userVo = rmUserService.find(userId);
		String organizationId = userVo.getOrganization_id();
		gbParentVo.setInitiatorOrganizationId(organizationId);//设置发起机构部门ID
		if(!StringHelper.isEmpty(organizationId)){
			RmPartyService rmPartyService = (RmPartyService) RmBeanFactory.getBean(RmPartyService.class.getName());
			RmPartyVo rmPartyVo = rmPartyService.find(organizationId);
			if(rmPartyVo!=null){
				String organizationName = rmPartyVo.getName();
				gbParentVo.setSendOrganizationDepartment(organizationName);//设置发起机构部门
			}
		}
		gbParentVo.setInitiatorTime(DateUtil.getNowTimestamp());//设置发起时间
		if(StringHelper.isEmpty(remark)){
			remark = "";
		}
		gbParentVo.setBusinessAttribute70(remark);
		gbParentVo.setBusinessAttribute1(dateTitle);
		gbParentVo.setBusinessAttribute2(companyName);
		gbParentVo.setBusinessAttribute3(priceUnit);
		gbParentVo.setBusinessAttribute4(tableDate);
		//gbParentVo.setBusinessAttribute5(pubParamService.getSequenceNumber("PRICE_APPLY_CODE")); //设置价格申请单号
		
		//批量插入通用表数据集合
		List gbVos = new ArrayList();
		//批量插入价格表数据集合
		List insertPriceVos = new ArrayList();
		//批量修改价格表数据集合
		List updatePriceVos = new ArrayList();
		PriceService priceService = (PriceService) RmBeanFactory.getBean(PriceService.class.getName());
		
		String productSql = "select ID as id,PRODUCT_NAME as productName from BASE_PRODUCT where MAIN_SECONDARY_PRODUCT_FLAG='02' and nvl(DELETE_FLAG,'0' )= '0'";
		List productList = RmProjectHelper.getCommonServiceInstance().doQuery(productSql);
		GeneralBusinessProcessService generalBusinessProcessService = (GeneralBusinessProcessService) RmBeanFactory.getBean(GeneralBusinessProcessService.class.getName());
		for (Object product : productList) {
			//接收form表单传过来的数据
			String productId = ((RmCommonVo) product).getString("id");
			String gbVoId = request.getParameter(productId+"id");
			String productName = request.getParameter(productId+"productName");
			String newPrice = request.getParameter(productId+"newPrice");
			String diff = request.getParameter(productId+"diff");
			String shenh = request.getParameter(productId+"shenh");
			String yanc = request.getParameter(productId+"yanc");
			String baof = request.getParameter(productId+"baof");
			String pux = request.getParameter(productId+"pux");
			String yana = request.getParameter(productId+"yana");
			String kurong = request.getParameter(productId+"kurong");
			String kucun = request.getParameter(productId+"kucun");
			String chanl = request.getParameter(productId+"chanl");
			String yewei = request.getParameter(productId+"yewei");
			String tianshu = request.getParameter(productId+"tianshu");
			
			//组装通用表数据
			GeneralBusinessProcessVo gbVo = generalBusinessProcessService.get(gbVoId);
			gbVo.setParentId(parentId);
			gbVo.setBusinessAttribute1(productId);//产品id
			gbVo.setBusinessAttribute2(productName);//产品名字
			gbVo.setBusinessAttribute3(newPrice);//新价格
			gbVo.setBusinessAttribute4(diff);//新价格和现有价格的差
			gbVo.setBusinessAttribute5(shenh);//神华
			gbVo.setBusinessAttribute6(yanc);//延长
			gbVo.setBusinessAttribute7(baof);//宝丰
			gbVo.setBusinessAttribute8(pux);//蒲城新能源
			gbVo.setBusinessAttribute9(yana);//延安炼厂
			gbVo.setBusinessAttribute10(kurong);//库容
			gbVo.setBusinessAttribute11(kucun);//库存
			gbVo.setBusinessAttribute12(chanl);//产量
			gbVo.setBusinessAttribute13(yewei);//液位
			gbVo.setBusinessAttribute14(tianshu);//天数
			gbVo.setBusinessAttribute70("");
			gbVos.add(gbVo);//添加到集合
			
			//组装新价格表数据
			PriceVo priceVo = new PriceVo();
			priceVo.setProductId(productId);//产品id
			priceVo.setProductName(productName);//产品名称
			priceVo.setPrice(new BigDecimal(newPrice));//价格
			priceVo.setStartTime(DateUtil.getNowTimestamp());//开始时间
			priceVo.setStatus(ISystemConstant.DICTIONARY_PRICE_STATUS_2);
			priceVo.setCreate_user_name(userVo.getName());
			priceVo.setAttribute1(parentId);//作为关联
			RmVoHelper.markCreateStamp(request, priceVo);
			insertPriceVos.add(priceVo);
			
			//修改之前的价格状态
			Map searchMap = new HashMap();
	    	searchMap.put("statuss", "'"+ISystemConstant.DICTIONARY_PRICE_STATUS_2+"','"+ISystemConstant.DICTIONARY_PRICE_STATUS_3+"'");
	    	searchMap.put("productId", productId);
			List oldPriceVos = priceService.list(searchMap, null);
			if(oldPriceVos.size()>0){
				for (Object priceVo2 : oldPriceVos) {
					((PriceVo) priceVo2).setStatus(ISystemConstant.DICTIONARY_PRICE_STATUS_4);
					((PriceVo) priceVo2).setEndTime(DateUtil.getNowTimestamp());
					RmVoHelper.markModifyStamp(request, priceVo2);
					updatePriceVos.add(priceVo2);
				}
			}
		}
		gbVos.add(gbParentVo);
		priceService.doUpdateAndUpdateForm(gbVos, insertPriceVos, updatePriceVos);
		
		System.out.println("开始启动流程。。。");
		//启动流程,返回流程实例
		RuntimeService runtimeService = (RuntimeService) RmBeanFactory.getBean("runtimeService");
		Map voMap = RmVoHelper.getMapFromVo(gbParentVo);
		TaskService taskService = (TaskService) RmBeanFactory.getBean("taskService");
		voMap.put("isPass", true);
		//删除之前的审核意见
		taskService.deleteComments(taskId, procInstId);
		//删除之前的审核信息
		String sql = "DELETE FROM ACT_HI_VARINST WHERE PROC_INST_ID_ = '"+procInstId+"'";
		System.out.println(sql);
		RmProjectHelper.getCommonServiceInstance().doUpdate(sql);
        taskService.complete(taskId, voMap);
		System.out.println("更新parentVo成功！");
	}

}
