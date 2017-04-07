package com.dfhc.pub.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.acc.businessusablechange.dao.BusinessUsableChangeDao;
import com.dfhc.acc.businessusablechange.vo.BusinessUsableChangeVo;
import com.dfhc.acc.customeravailableamount.dao.CustomerAvailableAmountDao;
import com.dfhc.acc.customeravailableamount.vo.CustomerAvailableAmountVo;
import com.dfhc.acc.customerbalancechange.dao.CustomerBalanceChangeDao;
import com.dfhc.acc.customerbalancechange.vo.CustomerBalanceChangeVo;
import com.dfhc.acc.financialbookvalue.dao.FinancialBookValueDao;
import com.dfhc.acc.financialbookvalue.vo.FinancialBookValueVo;
import com.dfhc.pub.vo.CalculateVo;
import com.dfhc.pub.vo.FreezeAccVo;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * 账户变动服务
 * @author dfhc
 *
 */
@Service
@Transactional(readOnly = true)
public class AccountChangeService implements AccountChangeInter {
    
	
	@Autowired
	/**
	 * 业务可用金额
	 */
	private CustomerAvailableAmountDao customerAvailableAmountDao;
	
	@Autowired
	/**
	 * 账户变动服务
	 */
	private BusinessUsableChangeDao businessUsableChangeDao;
	
	@Autowired
	private PubParamService pubParamService;
	
	/**
	 * 财务账户
	 */
	@Autowired
	private FinancialBookValueDao financialBookValueDao;
	
	/**
	 * 财务账户变化
	 */
	@Autowired
	private CustomerBalanceChangeDao customerBalanceChangeDao;
	
	
	@Override
	/**
	 * 账户初始化（业务账户，财务账户）
	 * 
	 */
	public void doInitAcc(String userId,String userName) {
		// TODO Auto-generated method stub
		
       List<CustomerAvailableAmountVo> cusAvailableAmtVos=queryCusAvaiAmtVos(userId);//获取该用户的持有账户
       
       if(cusAvailableAmtVos.size()!=0)
    	   
    	   throw new PjException("该用户已有业务账户");
		
	   List<FinancialBookValueVo> finaBookValueVos=listFinaBookValVos(userId);
	   
	   if(finaBookValueVos.size()!=0)
		   throw new PjException("该用户已有财务账户，请查证");
	   
	   doInitCustAvailableVo(userId, userName);//新增业务账户
	   
	   doInitFinaBookValueVo(userId, userName);//初始化财务账户
	   
		
	}
	
	/**
	 * 初始化财务账户
	 * @param userId
	 * @param userName
	 */
	private void doInitFinaBookValueVo(String userId,String userName){
		
		FinancialBookValueVo finaVo=new FinancialBookValueVo();
		
		finaVo.setCustomerId(Long.valueOf(userId));
		finaVo.setCustomerName(userName);
		finaVo.setBalance(BigDecimal.ZERO);
		String sign=pubParamService.signFinancialBookValueVo(finaVo);
		
		finaVo.setSignature(sign);
		
		RmVoHelper.markCreateStamp(RmProjectHelper.getCurrentThreadRequest(), finaVo);//打时间戳
		
		financialBookValueDao.insert(finaVo);//新增财务账户
		
	
		
		
	}
	
	/**
	 * 初始化客户业务账户
	 * @param userId
	 * @param userName
	 */
	private void doInitCustAvailableVo(String userId,String userName){
		
		   CustomerAvailableAmountVo custVo=new CustomerAvailableAmountVo();
		   
		   custVo.setCustomerId(Long.valueOf(userId));
		   custVo.setCustomerName(userName);
		   custVo.setFreezingAmount(BigDecimal.ZERO);
		   custVo.setAvailableCredit(BigDecimal.ZERO);
		   String sign= pubParamService.signCuAvailableAmtVo(custVo);
		   custVo.setSignature(sign);
		   RmVoHelper.markCreateStamp(RmProjectHelper.getCurrentThreadRequest(), custVo);//打时间戳
		   
		   customerAvailableAmountDao.insert(custVo);//新增
		   
		   
		
	}
	

	/**
	 * 根据用户id 获取并并锁定	
	 * @param userId
	 * @return
	 */
	private CustomerAvailableAmountVo getCuAvailAmt(String userId){
      
		List<CustomerAvailableAmountVo>  list=queryCusAvaiAmtVos(userId);
		
		if(list==null || list.size()!=1){
			
			throw new PjException("根据用户id没有找到该用户对应的现金账户");
		}
	
		CustomerAvailableAmountVo cuAvailAmtVo= list.get(0);
		
		pubParamService.vaildateCuAvailableAmtVo(cuAvailAmtVo);//验证用户可用账户
	
		
		return cuAvailAmtVo;
	
       }

	/**
	 * 查询用户业务账户
	 * @param userId
	 * @return
	 */
	private  List<CustomerAvailableAmountVo>  queryCusAvaiAmtVos(String userId){
		
		  
			Map<String ,Object> searchPara=new HashMap<String,Object>();
			
	        if(StringHelper.isEmpty(userId)){
				
				throw new PjException("账户持有人不能为空");
			}
			
			searchPara.put("customerId", userId);
			searchPara.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);//删除标志
			List<CustomerAvailableAmountVo> list=customerAvailableAmountDao.getByUserForUpdate(searchPara, 1, -1);
			
			return list;
		
	}


	
	/**
	 * 获取用户账户
	 * @param userId
	 * @return
	 */
	private FinancialBookValueVo getFinaBookValVo(String userId){
	
		List<FinancialBookValueVo> list=listFinaBookValVos(userId);
		if(list.size()!=1)
			throw new PjException("根据用户id："+userId+"，没找到对应的额唯一财务账户");
		
		FinancialBookValueVo vo=list.get(0);
		
		pubParamService.vaildFinancialBookValueVo(vo);//验证加密信息
		
		return vo;
		
	}
	
	/**
	 * 根据用户id 查询所有的财务账户
	 * @param userId
	 * @return
	 */
	private List<FinancialBookValueVo> listFinaBookValVos(String userId){
		
		
		if(StringHelper.isEmpty(userId))
			throw new PjException("账户持有人id不可为空！");
		
		Map<String,Object> searchPara=new  HashMap<String,Object>();
		
		searchPara.put("customerId", userId);
		searchPara.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);//删除标志
		
		List<FinancialBookValueVo> list=financialBookValueDao.listByUserIdForLock(searchPara, 1, -1);
		
		return list;
		
	}

	

	
	/**
	 * 账户金额冻结
	 * 销售订单审核
	 */
	@Override
	public void doFreezeAcc(FreezeAccVo vo) {
	
	    BigDecimal busiAmt=vo.getChangeAmt();//变化金额
		
		if(busiAmt==null || busiAmt.compareTo(BigDecimal.ZERO)<=0){//账户变化金额为0抛出异常事务回滚
			
		   throw new PjException("业务变动变动金额不能为0");
			
		}
		String payerId=vo.getPayerId();//付款人id
		
		
		CustomerAvailableAmountVo cuAvailableAmtVo=getCuAvailAmt(payerId);//获取用户可用账户
		
	    String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_03;//账户变动方向，冻结
	    
		doReduceAvailbleAddFreeze(cuAvailableAmtVo, vo,amountDirection);//更新用的可用额度和插入账户变动信息
		

	}
	
	/**
	 * 订单取消
	 * 解冻该用户的冻结金额，增加用户的可用金额
	 */
	@Override
	public void doOrderCancel(FreezeAccVo vo) {
		// TODO Auto-generated method stub
		 BigDecimal busiAmt=vo.getChangeAmt();//变化金额
			
			if(busiAmt==null || busiAmt.compareTo(BigDecimal.ZERO)<=0){//账户变化金额为0抛出异常事务回滚
				
			   throw new PjException("业务变动变动金额不能为0");
				
			}
			String payerId=vo.getPayerId();//付款人id
			if(StringHelper.isEmpty(payerId)){
				
				throw new PjException("付款方id不能为空");
			}
			
			CustomerAvailableAmountVo cuAvailableAmtVo=getCuAvailAmt(payerId);//获取用户可用账户
			
		    String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_04;//账户变动方向，解冻
		    
			doReduceAvailbleAddFreeze(cuAvailableAmtVo, vo,amountDirection);//更新用的可用额度和插入账户变动信息
		
	}
	

	
    
    /**
     *  更新用的可用额度和插入账户变动信息
     * （减少用户的可用额度，增加用户的冻结额度）/(增加用户可用，减少用户冻结额度)
     * @param cuAvailableAmtVo
     * @param fvo
     * @param amountDirection:账户变动类型
     */
    private void doReduceAvailbleAddFreeze(CustomerAvailableAmountVo cuAvailableAmtVo,FreezeAccVo fvo,String amountDirection){
    	

		BusinessUsableChangeVo busChangeVo= createBusChangeVo(cuAvailableAmtVo, fvo,amountDirection);//账户变化vo
		
		createNewAvaliableAmt(cuAvailableAmtVo, fvo,amountDirection);//更新用户的可用账户
		
		businessUsableChangeDao.insert(busChangeVo);//插入账户变动
		
		customerAvailableAmountDao.update(cuAvailableAmtVo);//更新可用账户
    	
    }
    
    /**
     * 修改用户的业务账户
     * @param cuAvailableAmtVo 
     * @param fvo
     * @param amountDirection：账户资金变动方向
     * @return
     */
    private CustomerAvailableAmountVo createNewAvaliableAmt(CustomerAvailableAmountVo cuAvailableAmtVo,FreezeAccVo fvo,String amountDirection){
    	  
    	   BigDecimal  availableCredit= cuAvailableAmtVo.getAvailableCredit();//账户可以额度
   		
   		   availableCredit=availableCredit==null?BigDecimal.ZERO:availableCredit;//取出null
   		   
	       BigDecimal freezeAmt=cuAvailableAmtVo.getFreezingAmount();//获取用户的冻结金额
   		
   		   freezeAmt=freezeAmt==null?BigDecimal.ZERO:freezeAmt;
   		
   	    	  //减少用户的可用额度，增加用户的冻结度
//     		if(availableCredit.compareTo(busiAmt)<0){
// 			
// 			throw new PjException("当前用户的可用金额不足：剩余可用额度："+availableCredit.toPlainString()+"，实际发生金额："+busiAmt.toPlainString());
// 		}
   	    	  
   	       if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_03.equals(amountDirection)){//说明是账户冻结
   	    		
             availableCredit=availableCredit.subtract(fvo.getChangeAmt());//剩余可用额度,减少用户的可用额度
   	   		 freezeAmt=freezeAmt.add(fvo.getChangeAmt());//增加用户的冻结额度
   	   		 
   	    	}else if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_04.equals(amountDirection)){//账户解冻，增加可用额度，减少冻结金额
   	    		
   	    		freezeAmt=freezeAmt.subtract(fvo.getChangeAmt());//减少冻结额度，
   	    		availableCredit=availableCredit.add(fvo.getChangeAmt());//增加可用额度
   	  		
   	    	  
   	         }else if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_02.equals(amountDirection)){//账户支出，冻结金额减少，账户业务可用金额不变
   	        	 
   	        	freezeAmt=freezeAmt.subtract(fvo.getChangeAmt());//减少冻结额度，
   	        	
   	         }else  if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_01.equals(amountDirection)){//收入，账户冻结金额不变，业务可用额度增加
   	        	 
   	      	    availableCredit=availableCredit.add(fvo.getChangeAmt());//增加可用额度
   	        	 
   	         }else{
   	        	 
   	        	 throw new PjException("业务资金变动方向错误！");
   	         }
   	      


   		 cuAvailableAmtVo.setAvailableCredit(availableCredit);//可用的账户额度
   		
   		 cuAvailableAmtVo.setFreezingAmount(freezeAmt);//冻结的账户额度
   		
   		 cuAvailableAmtVo.setModify_time(DateUtil.getNowTimestamp());
   		
   		 cuAvailableAmtVo.setModify_user_id(fvo.getOperaterId());//修改人
   		 cuAvailableAmtVo.setModify_user_name(fvo.getOperaterName());
   		
   		 cuAvailableAmtVo.setModify_ip(fvo.getIp());
   		 
   		 String sign=pubParamService.signCuAvailableAmtVo(cuAvailableAmtVo);
   		 
   		 cuAvailableAmtVo.setSignature(sign);
   		
   		 return cuAvailableAmtVo;
    }
    
    /**
     * 组装账户变动vo
     * @param cuAvailableAmtVo:从数据库中查出的可用账户额度
     * @param fvo：账户变化操作vo
     * @param amountDirection:账户变动方向
     * @return
     */
   
    private BusinessUsableChangeVo createBusChangeVo(CustomerAvailableAmountVo cuAvailableAmtVo,FreezeAccVo fvo,String amountDirection){
    	
    	BusinessUsableChangeVo busChangeVo=new BusinessUsableChangeVo();
    	
    	BigDecimal avaliableChange=BigDecimal.ZERO;//可用额度变化
    	
    	BigDecimal freezeChange=BigDecimal.ZERO;//冻结额度变化
    	
	    BigDecimal  availableCredit= cuAvailableAmtVo.getAvailableCredit();//可用额度
		
		availableCredit=availableCredit==null?BigDecimal.ZERO:availableCredit;//取出null
		
		
		BigDecimal freezeAmt=cuAvailableAmtVo.getFreezingAmount();//获取用户的冻结金额
		
		freezeAmt=freezeAmt==null?BigDecimal.ZERO:freezeAmt;
		
	
        if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_03.equals(amountDirection)){//说明是账户冻结
    		
        	freezeAmt=freezeAmt.add(fvo.getChangeAmt());//增加用户的冻结额度
    		availableCredit=availableCredit.subtract(fvo.getChangeAmt());//剩余可用额度,减少用户的可用额度
    		freezeChange=fvo.getChangeAmt();//冻结发生额度
    	}else if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_04.equals(amountDirection)){//账户解冻，增加可用额度，减少冻结金额
    		
    		freezeAmt=freezeAmt.subtract(fvo.getChangeAmt());//减少冻结额度，
    		availableCredit=availableCredit.add(fvo.getChangeAmt());//增加可用额度
    		
    	}else  if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_02.equals(amountDirection)){//支出，减少用户的冻结额度，账户余额不变
    		
    		freezeAmt=freezeAmt.subtract(fvo.getChangeAmt());//减少冻结额度，
    	}else if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_01.equals(amountDirection)){//收入，账户冻结金额不变，增加可用额度
    		
    		availableCredit=availableCredit.add(fvo.getChangeAmt());//增加可用额度
    		
    	}else {
    		throw new PjException("账户资金变动类型不能为空");
    	}
    	
        busChangeVo.setBusinessType(fvo.getBusinessType());//业务类型
        
        busChangeVo.setBusinessAvailabilityId(cuAvailableAmtVo.getId());//可用账户id
        busChangeVo.setCustomerId(cuAvailableAmtVo.getCustomerId());
        
        busChangeVo.setCustomerName(cuAvailableAmtVo.getCustomerName());//账户名称
        
        busChangeVo.setOldAvailableCredit(cuAvailableAmtVo.getAvailableCredit());//原可用额度
        
        busChangeVo.setAvailableCreditChange(avaliableChange);//变动的额度
        
        busChangeVo.setOldFreezingAmount(cuAvailableAmtVo.getFreezingAmount());//原冻结额度
        busChangeVo.setFreezingAccrual(freezeChange);//冻结发生额度
        
        busChangeVo.setNowAvailableCredit(availableCredit);//当前可用额度
        busChangeVo.setDigest(fvo.getRemark());
        busChangeVo.setNowFreezingAmount(freezeAmt);//现在冻结额度
        busChangeVo.setRelevanceBillsId(fvo.getBillsId());//单据关联
        
        busChangeVo.setAmountDirection(amountDirection);//金额变动方向
        
        busChangeVo.setCreate_time(DateUtil.getNowTimestamp());
        busChangeVo.setCreate_user_id(fvo.getOperaterId());
        busChangeVo.setCreate_user_name(fvo.getOperaterName());
       
        String sign= pubParamService.signBusiChangeVo(busChangeVo);
        busChangeVo.setSignature(sign);//设置加密
        
    	return busChangeVo;
    }
	

	

	@Override
	
	/**
	 * TODO 20170208修改账户结算
	 * （账户结算只修改业务账户的可用余额和财务账户金额：业务账户余额=原业务账户余额+（应结算金额-实际结算金额）、财务账户余额=原财务账户余额-实际结算金额）
	 * 
	 * 多退少补原则，操作可用账户表，操作财务账户表扣减财务金额，
	 * 结算操作
	 * @param vo
	 */
	public void doOrderCalulate(CalculateVo vo) {
		// TODO Auto-generated method stub
		
		
		String payerId=vo.getPayerId();//账户持有人id
		
		
		BigDecimal oldChangeAmt=vo.getOldChangeAmt();//原计划花费金额
		
		if(oldChangeAmt==null || oldChangeAmt.compareTo(BigDecimal.ZERO)<0){
			
			throw new PjException("原计划花费金额不能小于0！");
			
		}
		
		BigDecimal actualChangeAmt=vo.getActualChangeAmt();//实际花费金额
		
		if(actualChangeAmt==null || actualChangeAmt.compareTo(BigDecimal.ZERO)<0){
			
			throw new PjException("实际花费额不能小于0");
			
		}
		
	
		
		 FinancialBookValueVo finaBookValueVo=getFinaBookValVo(payerId);
		 
		 doCalCustAvailbleAccVo(vo);//订单结算业务账户变化
		
		 doReduceFinaAccChange(finaBookValueVo, vo);//财务账户变化
		
		
	}
	
	/**
	 * 订单结算，业务账户变化
	 * @param cusAvailableAmtVo
	 * @param vo
	 */
	
	private void doCalCustAvailbleAccVo(CalculateVo vo){
		
		BigDecimal oldChangeAmt=vo.getOldChangeAmt();//原计划花费金额
		
		BigDecimal  actualChangeAmt=vo.getActualChangeAmt();//实际花费金额
		
		String businessType=vo.getBusinessType();//业务变动类型DICTIONARY_ACC_CHANGE_BUSTYPE_05
		
		String billsId=vo.getBillsId();
		String payerId=vo.getPayerId();//账户持有人id
		BigDecimal changeAmt= vo.getOldChangeAmt();//原冻结金额
		String remark=vo.getRemark();
		String ip=vo.getIp();
		String operaterId=vo.getOperaterId();//操作人id
		String operaterName=vo.getOperaterName();//操作人

		
		if(oldChangeAmt.compareTo(actualChangeAmt)==0){////计划花费=实际支付，直接返回
			return ;
		}
		
		changeAmt=vo.getOldChangeAmt().subtract(vo.getActualChangeAmt());//差价
		
		if(changeAmt.compareTo(BigDecimal.ZERO)>0){
			
			remark=remark+"。订单结算，应结算金额>实际结算金额，用户业务账户的业务余额增加";
			
		}else{
			
			remark=remark+"。订单结算，应结算金额<实际结算金额，用户业务账户的业务余额减少";
		}
		
		
		FreezeAccVo fvo=new FreezeAccVo(businessType, payerId, billsId, changeAmt, remark, ip, operaterId, operaterName);
	    
		doAddAvailbleAcc(fvo);
		
		
	}
	
	
	/**
	 * 减少用户的动机金额
	 * @param cuAvailableAmtVo
	 * @param fvo
	 */
    private void doReduceAvailbleFreeze(FreezeAccVo fvo){
    	
    	String userId=fvo.getPayerId();//账户持有人id
    	CustomerAvailableAmountVo cuAvailableAmtVo=getCuAvailAmt(userId);//获取该用户的持有账户
    	
    	String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_02;//支出
    	
		BusinessUsableChangeVo busChangeVo= createBusChangeVo(cuAvailableAmtVo, fvo,amountDirection);//账户变化vo
		
		createNewAvaliableAmt(cuAvailableAmtVo, fvo,amountDirection);//更新用户的可用账户
		
		businessUsableChangeDao.insert(busChangeVo);//插入账户变动
		
		customerAvailableAmountDao.update(cuAvailableAmtVo);//更新可用账户
    	
    }
    
    /**
     * 增加用户的可用额度
     * @param fvo
     */
    private void doAddAvailbleAcc(FreezeAccVo fvo){
    	
    	String userId=fvo.getPayerId();//账户持有人id
    	CustomerAvailableAmountVo cuAvailableAmtVo=getCuAvailAmt(userId);//获取该用户的持有账户
    	
    	String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_01;//收入
    	
		BusinessUsableChangeVo busChangeVo= createBusChangeVo(cuAvailableAmtVo, fvo,amountDirection);//账户变化vo
		
		createNewAvaliableAmt(cuAvailableAmtVo, fvo,amountDirection);//更新用户的可用账户
		
		businessUsableChangeDao.insert(busChangeVo);//插入账户变动
		
		customerAvailableAmountDao.update(cuAvailableAmtVo);//更新可用账户
    	
    }
    
    

	
	
	/**
	 * 资金结算，减少用户的财务金额（剩余的财务金额可以《0）
	 * @param finaBookValueVo:查出的用户财务账户表
	 * @param vo：业务操作vo
	 */
	private  void doReduceFinaAccChange(FinancialBookValueVo finaBookValueVo,CalculateVo vo){
		
		 String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_02;//支出
		
		 doCreateCustBlanceChange(finaBookValueVo, vo, amountDirection);//先插入账户变动变，在更改用户财务账户表
		 
		 doReduceOrAddFinaBookValVo(finaBookValueVo, vo, amountDirection);//减少用户的财务账户可用金额
		
	}
	
	/**
	 * 增加业务可用金额
	 * @param finaBookValueVo
	 * @param vo
	 */
	private void doAddFinaAccChange(FinancialBookValueVo finaBookValueVo,CalculateVo vo){
		
		 String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_01;//收入
			
		 doCreateCustBlanceChange(finaBookValueVo, vo, amountDirection);//先插入账户变动变，在更改用户财务账户表
		 
		 doReduceOrAddFinaBookValVo(finaBookValueVo, vo, amountDirection);//减少用户的财务账户可用金额
		
	}
	
	
	/**
	 * 财务账户变化  ，amountDirection=01：收款说明财务金额增加，amountDirection=02：支出：结算财务金额减少
	 * @param finaBookValueVo:财务账户
	 * @param vo
	 * @param amountDirection
	 */
	 private void doReduceOrAddFinaBookValVo( FinancialBookValueVo finaBookValueVo,CalculateVo vo,String amountDirection){
		 
		
		 
		 BigDecimal amt=finaBookValueVo.getBalance()==null?BigDecimal.ZERO:finaBookValueVo.getBalance();
		 
		 if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_01.equals(amountDirection)){//收入
			 
			 
			 BigDecimal actualChangeAmt=vo.getActualChangeAmt();//实际发生金额
			 
			 amt=amt.add(actualChangeAmt);//余额-实际发生=剩余
			 
			 
		 }else if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_02.equals(amountDirection)){//支出
			 
			 BigDecimal actualChangeAmt=vo.getActualChangeAmt();//实际发生金额
			 
			 amt=amt.subtract(actualChangeAmt);//余额-实际发生=剩余
			 
			 
		 }else {
			 throw new PjException("资金变动方向不能为空！");
		 }
		
		 finaBookValueVo.setBalance(amt);
		 
		 String sign=pubParamService.signFinancialBookValueVo(finaBookValueVo);
		 
		 finaBookValueVo.setSignature(sign);//设置加密信息

		 finaBookValueVo.setModify_time(DateUtil.getNowTimestamp());//插入当前时间
		 
		 finaBookValueVo.setModify_user_id(vo.getOperaterId());//操作人id
		 finaBookValueVo.setModify_user_name(vo.getOperaterName());//操作人
		 finaBookValueVo.setModify_ip(vo.getIp());
		 
		 financialBookValueDao.update(finaBookValueVo);
		 
	 }
	 
	 /**
	  * 插入财务变动表
	  * amountDirection=01：收款说明财务金额增加，amountDirection=02：支出：结算财务金额减少
	  * @param finaBookValueVo：未变化的财务账户
	  * @param vo：操作vo
	  * @param amountDirection:资金变动方向
	  */
	 private void  doCreateCustBlanceChange(FinancialBookValueVo finaBookValueVo,CalculateVo vo,String amountDirection){
		 
		 CustomerBalanceChangeVo custChangeVo=new CustomerBalanceChangeVo();
		 
		 custChangeVo.setAccrual(vo.getActualChangeAmt());//本次变动金额
		 
		 custChangeVo.setAmountDirection(amountDirection);//资金变动方向
		 custChangeVo.setBusinessType(vo.getBusinessType());//业务类型
		 custChangeVo.setCustomerId(finaBookValueVo.getCustomerId());
		 custChangeVo.setCustomerName(finaBookValueVo.getCustomerName());
		 custChangeVo.setDigest(vo.getRemark());
		 
		 custChangeVo.setOldBalance(finaBookValueVo.getBalance());//上次余额
		 
         BigDecimal amt=finaBookValueVo.getBalance()==null?BigDecimal.ZERO:finaBookValueVo.getBalance();//账户原来余额
		 
		 if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_01.equals(amountDirection)){//收入
			 
			 
			 BigDecimal actualChangeAmt=vo.getActualChangeAmt();//实际发生金额
			 
			 amt=amt.add(actualChangeAmt);//余额-实际发生=剩余
			 
			 
		 }else if(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_02.equals(amountDirection)){//支出
			 
			 BigDecimal actualChangeAmt=vo.getActualChangeAmt();//实际发生金额
			 
			 amt=amt.subtract(actualChangeAmt);//余额-实际发生=剩余
			 
			 
		 }else {
			 throw new PjException("资金变动方向不能为空！");
		 }
		 
		 custChangeVo.setNowBalance(amt);//变动后账户现在金额
		 
		 custChangeVo.setRelevanceBillsId(vo.getBillsId());
		 
		 custChangeVo.setCreate_user_id(vo.getOperaterId());
		 
		 custChangeVo.setCreate_user_name(vo.getOperaterName());
		 
		 custChangeVo.setCreate_time(DateUtil.getNowTimestamp());
		 
		 String sign =pubParamService.signCustomerBalChange(custChangeVo);
		 
		 custChangeVo.setSignature(sign);//设置加密信息
		 
		 customerBalanceChangeDao.insert(custChangeVo);//插入财务账户变化
		
		 
		 
	 }

	@Override
	
	/**
	 * 功能，财务收款，增加财务账户剩余金额，增加业务账户可用金额
	 * @param vo :操作vo
	 * 
	 */
	public void doCollectionAmt(FreezeAccVo vo) {
		// TODO Auto-generated method stub
		
		BigDecimal changeAmt=vo.getChangeAmt();//账户变化金额
		
		if(changeAmt==null || changeAmt.compareTo(BigDecimal.ZERO)<=0)
			
			throw new PjException("业务操作金额不能小于0");
		
		FinancialBookValueVo finaBookValueVo=getFinaBookValVo(vo.getPayerId());//
	
		CalculateVo cvo=freezeVoToCalculateVo(vo);//将freezevo转为CalculateVo
		
		doAddFinaAccChange(finaBookValueVo, cvo);//增加财务账户余额
		
		doAddAvailbleAcc(vo);//增加用户的业务账户可用金额
		
		
	}
	 
	
	/**
	 * 将冻结操作的vo 转换  结算操作vo
	 * @param vo
	 */
	private CalculateVo freezeVoToCalculateVo(FreezeAccVo vo){
		
		BigDecimal oldChangeAmt=vo.getChangeAmt();
		BigDecimal actualChangeAmt=vo.getChangeAmt();
		String payerId=vo.getPayerId();
		String billsId=vo.getBillsId();
		String businessType=vo.getBusinessType();
		String operaterId=vo.getOperaterId();
		String operaterName=vo.getOperaterName();
		String ip=vo.getIp();
		String remark=vo.getRemark();
		
		CalculateVo cvo=new CalculateVo(oldChangeAmt, actualChangeAmt, payerId, billsId, businessType, operaterId, operaterName, ip, remark);
		
		return cvo;
		
	}

	@Override
	/**
	 * 提货单完成
	 * 1、提货金额<=该用户的剩余冻结金额，直接减少用户业务账户的冻结金额，
	 * 2、提货金额>该用户的冻结金额，将冻结金额变为0，且 业务账户的可用额度-将多提部分金额（提货金额-用户冻结金额）
	 * @param vo
	 */
	public void doGoodsBillComplete(FreezeAccVo vo) {
		// TODO Auto-generated method stub
		
		String userId=vo.getPayerId();
		
		BigDecimal changeAmt=vo.getChangeAmt();//发生金额
		
		if(changeAmt==null || changeAmt.compareTo(BigDecimal.ZERO)<=0){
			
			throw new PjException("提货单应发生金额应该大于0");
			
		}
		CustomerAvailableAmountVo custAvaAmountVo=getCuAvailAmt(userId);//获取用户的业务账户
		
		BigDecimal freezeAmt=custAvaAmountVo.getFreezingAmount();//冻结金额
		
		freezeAmt=freezeAmt==null?BigDecimal.ZERO:freezeAmt;//冻结金额
		
		if(changeAmt.compareTo(freezeAmt)<=0){//提货单金额《=剩余冻结金额直接减少用户的冻结金额
			
			doReduceAvailbleFreeze(vo);//减少用户的冻结金额
			
		}else{//提货金额>该用户的冻结金额，将冻结金额变为0，且 业务账户的可用额度-将多提部分金额（提货金额-用户冻结金额）
			
			
			String remark=vo.getRemark();
			
//			1、先冻结多余花费金额，减少用户的可用额度，增加用户冻结金额
			remark=remark+"。实际花费金额大于计划花费金额，增加差价部分额度冻结。";
			BigDecimal surAmt=changeAmt.subtract(freezeAmt);//多发生的金额，先增加冻结
			
			vo.setRemark(remark);//修改备注
			
			vo.setChangeAmt(surAmt);//增加多余部分冻结；

			doFreezeAcc(vo);//账户金额冻结
			
//			2、解冻用户实际发生金额
			remark=remark+"。实际提货金额大于剩余冻结金额。已经冻结过用户差价部分金额，现减少冻结实际发生金额。";
	        vo.setRemark(remark);//修改备注
			vo.setChangeAmt(changeAmt);//增加多余部分冻结；
			doReduceAvailbleFreeze(vo);
			
			
		}
		
		
		
	}

	
	@Override
	/**
	 * 订单完成接口
	 * 1、如果应该发生金额<=实际发生金额：用户的业务账户不发生变化
	 *2、如果应该发生金额>实际发生金额，增加业务金额，减少冻结金额
	 * @param vo
	 */
	public void doOrderComplete(CalculateVo vo) {
		// TODO Auto-generated method stub
		
		BigDecimal oldChangeAmt=vo.getOldChangeAmt();
		
		oldChangeAmt=oldChangeAmt==null?BigDecimal.ZERO:oldChangeAmt;//应发生金额
		
		BigDecimal actChangeAmt=vo.getActualChangeAmt();//是发生金额
		
		actChangeAmt=actChangeAmt==null?BigDecimal.ZERO:actChangeAmt;//是发生金额
		
		if(oldChangeAmt.compareTo(actChangeAmt)<=0){
			
			return ;
		}
		
		
		BigDecimal optChangeAmt=actChangeAmt.subtract(oldChangeAmt);//将该多余部分增加业务余额，减少对应的冻结金额
		
        String userId=vo.getPayerId();
		
		CustomerAvailableAmountVo custAvaAmountVo=getCuAvailAmt(userId);//获取用户的业务账户
	
	    String amountDirection=ISystemConstant.DICTIONARY_AMOUNT_DIRECTION_04;//账户变动方向，解冻
        String businessType=vo.getBusinessType();//业务变动类型
		String billsId=vo.getBillsId();
		String payerId=vo.getPayerId();//账户持有人id
		String remark=vo.getRemark();
		String ip=vo.getIp();
		String operaterId=vo.getOperaterId();//操作人id
		String operaterName=vo.getOperaterName();//操作人
		
		FreezeAccVo fvo=new FreezeAccVo(businessType, payerId, billsId, optChangeAmt, remark, ip, operaterId, operaterName);
		
		doReduceAvailbleAddFreeze(custAvaAmountVo, fvo,amountDirection);//更新用的可用额度和插入账户变动信息
		
	}
	 
	
	
	
	
	




}
