package com.dfhc.pub.service;

import com.dfhc.pub.vo.CalculateVo;
import com.dfhc.pub.vo.FreezeAccVo;


/**
 * 现金账户变动接口
 * @author dfhc
 *
 */
public interface AccountChangeInter {
	
	
	/**
	 * 初始化用户财务账户，业务账户
	 * @param userId：用户id
	 * @param userName:用户名
	 */
	public void doInitAcc(String userId,String userName);
	
	/**
	 * 订单审核冻结用户的账户可用额度，减少可用额度增加冻结额度
	 * 变化额度可以》账户剩余额度
	 * 
	 * @param vo
	 */
	public  void  doFreezeAcc(FreezeAccVo vo);
	
	/**
	 * 订单取消，减少冻结，增加可用
	 * @param vo
	 */
	public void doOrderCancel(FreezeAccVo vo);
	
	/**
	 * 提货单完成
	 * 1、提货金额<=该用户的剩余冻结金额，直接减少用户业务账户的冻结金额，
	 * 2、提货金额>该用户的冻结金额，将冻结金额变为0，且 业务账户的可用额度-将多提部分金额（提货金额-用户冻结金额）
	 * @param vo
	 */
	public void doGoodsBillComplete(FreezeAccVo vo);
	
	

	/**
	 * 订单完成接口
	 * 1、如果应该发生金额<=实际发生金额：用户的业务账户不发生变化
	 *2、如果应该发生金额>实际发生金额，增加业务金额，减少冻结金额
	 * @param vo
	 */
	public void doOrderComplete(CalculateVo vo);
	
	
	
	/**
	 * TODO 20170208修改账户结算
	 * （账户结算只修改业务账户的可用余额和财务账户金额：业务账户余额=原业务账户余额+（应结算金额-实际结算金额）、财务账户余额=原财务账户余额-实际结算金额）
	 * 
	 * 多退少补原则，操作可用账户表，操作财务账户表扣减财务金额，
	 * 结算操作
	 * @param vo
	 */
	public void doOrderCalulate(CalculateVo vo);
	
	/**
	 * 收款金额
	 * @param vo
	 */
	public void doCollectionAmt(FreezeAccVo vo);

}
