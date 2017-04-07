/**
 * 软件著作权：中国金币总公司
 *
 * 系统名称：  中金国衡业务管理信息系统
 *
 * 文件名称：  PubParamService.java
 *
 * 功能描述：  公共参数服务
 * 
 * 版本历史：
 * 
 * 2014-06-07   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.pub.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.python.antlr.PythonParser.return_stmt_return;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.acc.businessusablechange.vo.BusinessUsableChangeVo;
import com.dfhc.acc.customeravailableamount.vo.CustomerAvailableAmountVo;
import com.dfhc.acc.customerbalancechange.vo.CustomerBalanceChangeVo;
import com.dfhc.acc.financialbookvalue.vo.FinancialBookValueVo;
import com.dfhc.base.logisticscompany.service.LogisticsCompanyService;
import com.dfhc.base.logisticscompany.vo.LogisticsCompanyVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.service.OrderProductDetailService;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.rm.user.service.UserService;
import com.dfhc.rm.user.vo.UserVo;
import com.dfhc.util.ConvertHelper;
import com.dfhc.util.DateUtil;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.MD5Util;
import com.dfhc.util.StringHelper;

/**
 * 公共参数服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
// 默认将类中的所有public函数纳入事务管理
@Transactional(readOnly = true)
public class PubParamService {

	@Autowired
	private  OrderProductDetailService  orderProductDetailService;
	@Autowired
	private  UserService  userService;
	@Autowired
	private   LogisticsCompanyService  logisticsCompanyService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 获取根目录
	 * 
	 * @return
	 */
	public String getRootPath() {
		String value = RmGlobalReference.get(ISystemConstant.DICTIONARY_PATH,
				ISystemConstant.DICTIONARY_ROOT_PATH);
		if (StringHelper.isEmpty(value)) {
			throw new PjException("根目录参数尚未配置!");
		} else {
			// 不是以路径分隔符结束，则连接分隔符
			if (!value.trim().endsWith(File.separator)) {
				return value.trim() + File.separator + File.separator;
			} else {
				return value.trim() + File.separator;
			}
		}

	}

	/**
	 * 获取图片的根路径
	 * 
	 * @return
	 */
	public String getPurRootPath(String type) {
		String rootPath = getRootPath();
		String value = RmGlobalReference.get(ISystemConstant.DICTIONARY_PATH,
				type);
		if (StringHelper.isEmpty(value)) {
			throw new PjException(type + "目录参数尚未配置!");
		} else {
			// 不是以路径分隔符结束，则连接分隔符
			if (!value.trim().endsWith(File.separator)) {
				return rootPath + value.trim() + File.separator;
			} else {
				return rootPath + value.trim();
			}
		}

	}

	/**
	 * 支付密码md5加密(用户ID+明文密码+附加串，MD5加密)
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public String md5PaymentPassword(String id, String password) {
		return MD5Util.md5Encode(id + password);
	}

	/**
	 * 验证手机号码（暂缺固定电话的验证）
	 */
	public boolean checkTelephone(String telephone) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(147)|(17[0,6-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher matcher = p.matcher(telephone);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证邮编
	 */
	public boolean checkPostCode(String postCode) {
		Pattern p = Pattern.compile("^([1-9])\\d{5}$");
		Matcher matcher = p.matcher(postCode);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证码短信
	 * 
	 * @return
	 */
	public String getTemplateYSMessage() {
		String value = RmGlobalReference.get(ISystemConstant.TEMPLET,
				ISystemConstant.TEMPLET_MESSAGE_YS);
		if (StringHelper.isEmpty(value)) {
			throw new PjException("短信模板参数尚未配置!");
		} else {
			return value.trim();
		}
	}

	/**
	 * 打印登录验证码md5加密(用户id+8位随机码)
	 * 
	 * @param userId
	 * @param code
	 * @return
	 */
	public String md5PrintLoginVirectyCode(String userId, String code) {
		return MD5Util.md5Encode(userId + code);
	}

	/**
	 * 获取rm.xml配置的webAppName.root.home目录
	 * 
	 * @return
	 */
	public String getConfigRootPath() {
		return System.getProperty(ISystemConstant.WEBAPPNAME_ROOT_HOME);
	}

	/**
	 * 数据转换json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public String objectToJsonString(Object obj) {

		return obj != null ? JSONObject.toJSONString(obj) : null;
	}

	/**
	 * 客户可用账户加密
	 * 
	 * @param vo
	 * @return
	 */
	public String signCuAvailableAmtVo(CustomerAvailableAmountVo vo) {

		// 客户ID+可用额度+冻结额度+附加串 作为签名原串

		StringBuilder sb = new StringBuilder();

		sb.append(vo.getCustomerId());

		BigDecimal avilableAmt = vo.getAvailableCredit();

		avilableAmt = avilableAmt == null ? BigDecimal.ZERO : avilableAmt;// 可用额度

		sb.append(avilableAmt.setScale(4, BigDecimal.ROUND_HALF_EVEN));// 可用额度改为4位精度

		BigDecimal freezeAmt = vo.getFreezingAmount();// 冻结金额

		freezeAmt = freezeAmt == null ? BigDecimal.ZERO : freezeAmt;// 冻结金额

		sb.append(freezeAmt.setScale(4, BigDecimal.ROUND_HALF_EVEN));// 冻结金额改为4位精度

		return MD5Util.md5Encode(sb.toString());

	}

	/**
	 * 验证账户的可用账户
	 * 
	 * @param vo
	 */

	public void vaildateCuAvailableAmtVo(CustomerAvailableAmountVo vo) {

		if (vo == null) {

			throw new PjException("用户账户不能为空！");
		}

		String sign = signCuAvailableAmtVo(vo);

		if (!sign.equals(vo.getSignature())) {

			throw new PjException("用户可用账户验证失败，账户异常！");
		}

	}

	/**
	 * 可用账户发生额度变化表加密
	 * 
	 * @param vo
	 * @return
	 */

	public String signBusiChangeVo(BusinessUsableChangeVo vo) {

		if (vo == null) {

			throw new PjException("可用额度账户变化表不可为空");
		}
		// 客户ID+原可用额度+可用额度变动+原冻结额度+冻结发生额+现冻结额度+摘要+金额方向+关联单据ID+附加串 作为签名原串
		StringBuilder sb = new StringBuilder();

		sb.append(vo.getCustomerId());
		BigDecimal amt = vo.getOldAvailableCredit() == null ? BigDecimal.ZERO
				: vo.getOldAvailableCredit();// 原可用额度
		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		// 变化额度
		amt = vo.getAvailableCreditChange() == null ? BigDecimal.ZERO : vo
				.getAvailableCreditChange();
		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		// 原冻结额度
		amt = vo.getOldFreezingAmount() == null ? BigDecimal.ZERO : vo
				.getOldFreezingAmount();
		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		// 冻结发生额
		amt = vo.getFreezingAccrual() == null ? BigDecimal.ZERO : vo
				.getFreezingAccrual();
		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		amt = vo.getNowFreezingAmount() == null ? BigDecimal.ZERO : vo
				.getNowFreezingAmount();// 现冻结额度
		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		sb.append(vo.getDigest());

		sb.append(vo.getAmountDirection());
		sb.append(vo.getRelevanceBillsId());

		return MD5Util.md5Encode(sb.toString());
	}

	/**
	 * 加密客户财务账面金额
	 * 
	 * @param vo
	 * @return
	 */
	public String signFinancialBookValueVo(FinancialBookValueVo vo) {

		if (vo == null)
			throw new PjException("客户财务账面金额vo为空！");

		StringBuilder sb = new StringBuilder();

		// 客户ID+账面余额+附加串 作为签名原串

		sb.append(vo.getCustomerId());

		BigDecimal amt = vo.getBalance() == null ? BigDecimal.ZERO : vo
				.getBalance();

		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));// 保留四位

		return MD5Util.md5Encode(sb.toString());

	}

	/**
	 * 验证财务账户
	 * 
	 * @param vo
	 */
	public void vaildFinancialBookValueVo(FinancialBookValueVo vo) {

		String md5Str = signFinancialBookValueVo(vo);

		String sign = vo.getSignature();// 获取加密信息
		if (!md5Str.equals(sign))
			throw new PjException("财务账户验签失败！");

	}

	/**
	 * 客户财务账户变动vo加密
	 * 
	 * @param vo
	 * @return
	 */

	public String signCustomerBalChange(CustomerBalanceChangeVo vo) {

		if (vo == null)
			throw new PjException("财务账户变动vo不可为空");

		StringBuilder sb = new StringBuilder();

		// 客户ID+业务类型+财务账面金额ID+上次余额+本次变动金额+财务余额+摘要+金额方向+关联单据ID+金额方向+附加串 作为签名原串

		sb.append(vo.getCustomerId());
		sb.append(vo.getBusinessType());
		sb.append(vo.getFinancialBookValueId());

		BigDecimal amt = vo.getOldBalance() == null ? BigDecimal.ZERO : vo
				.getOldBalance();// 上次余额

		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		amt = vo.getAccrual() == null ? BigDecimal.ZERO : vo.getAccrual();// 本次变动金额

		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		amt = vo.getNowBalance() == null ? BigDecimal.ZERO : vo.getNowBalance();// 财务余额

		sb.append(amt.setScale(4, BigDecimal.ROUND_HALF_EVEN));

		sb.append(vo.getDigest());// 加密摘要

		sb.append(vo.getAmountDirection());// 金额方向

		return MD5Util.md5Encode(sb.toString());

	}

	/**
	 * 获取单号序号
	 * 
	 **/
	public String getSequenceNumber(String numberName) {
		OracleSequenceService sequenceService = (OracleSequenceService) RmBeanFactory
				.getBean("SequenceService");
		Date date = new Date();
		return sequenceService.doGetNextSequenceValue(numberName, date, date,
				date);

	}

	/**
	 * 获取单号序号
	 * 
	 **/
	public String getSequenceNumber(String sequenceName, Object... args) {
		OracleSequenceService sequenceService = (OracleSequenceService) RmBeanFactory
				.getBean("SequenceService");
		return sequenceService.doGetNextSequenceValue(sequenceName, args);

	}

	/**
	 * 二次加密用户的密码
	 * 
	 * @param vo
	 *            ：该vo 的密码只在页面进行了一次加密
	 * @return
	 */
	public org.quickbundle.orgauth.rmuser.vo.RmUserVo signRmUserPwd(
			org.quickbundle.orgauth.rmuser.vo.RmUserVo vo) {

		String uuid = UUID.randomUUID().toString();// 获取一个随机数，作为加密的key

		String oldPwd = vo.getPassword();// 未加密的用户vo的密码

		String newPwd = MD5Util.md5Encode(oldPwd, uuid);// 加密后的密码

		vo.setAttribute2(uuid);// 存放随机数
		vo.setPassword(newPwd);// 设置新密密码

		return vo;

	}

	/**
	 * 登陆是加密
	 * 
	 * @param prePasword
	 * @param userVo
	 * @return
	 */
	public String signRmUserPwd(String prePasword, UserVo userVo) {

		return MD5Util.md5Encode(prePasword, userVo.getAttribute2());// 加密后的密码

	}

	/**
	 * 用户加密
	 * 
	 * @param uuid
	 * @param oldPwd
	 * @return
	 */
	public String signRmUserPwd(String uuid, String oldPwd) {

		uuid = uuid == null ? "" : uuid;
		if (StringHelper.isEmpty(oldPwd)) {

			throw new PjException("用户输入密码为空");
		}
		String newPwd = MD5Util.md5Encode(oldPwd, uuid);// 加密后的密码

		return newPwd;

	}

	/**
	 * 验证用户输入的登录密码
	 * 
	 * @param vo
	 *            ：从数据库中查出的用户vo
	 * @param pwd
	 *            :用户页面输入的密码
	 * @return true=密码正确
	 */

	public boolean VailRmUserPwd(org.quickbundle.orgauth.rmuser.vo.RmUserVo vo,
			String pwd) {

		String uuid = vo.getAttribute2();// 获取存放的随机数
		uuid = uuid == null ? "" : uuid;

		String rmPwd = vo.getPassword();// 获取用户密码

		String newPwd = MD5Util.md5Encode(pwd, uuid);// 加密后的密码

		if (rmPwd.equals(newPwd)) {

			return true;

		} else {

			return false;
		}

	}

	/**
	 * 协同操作
	 * 
	 * @param request
	 * @param model
	 */
	public String commonModel(HttpServletRequest request, Model model) {
		String operation = request
				.getParameter(ISystemConstant.DICTIONARY_OPERATION);
		model.addAttribute(ISystemConstant.DICTIONARY_OPERATION, operation);
		return operation;
	}

	/**
	 * 协同操作公共验证 是否是当前登录人
	 * 
	 * @param userId
	 * @param currentUserId
	 */
	public void commonCheck(String userId, HttpServletRequest request) {

		String operation = request
				.getParameter(ISystemConstant.DICTIONARY_OPERATION);

		if (ISystemConstant.DICTIONARY_OPERATION_COORDINATION.equals(operation)) {
			if (!userId.equals(RmProjectHelper.getRmUserId(request))) {
				throw new PjException("只能操作自己的信息！");
			}
		}

	}

	/**
	 * 客户协同过滤客户
	 * 
	 * @param request
	 * @param searchPara
	 */
	public void commonParamsCustomer(HttpServletRequest request,
			Map<String, Object> searchPara) {
		String operation = request
				.getParameter(ISystemConstant.DICTIONARY_OPERATION);

		if (ISystemConstant.DICTIONARY_OPERATION_COORDINATION.equals(operation)) {
			
			searchPara.put("customerId", RmProjectHelper.getRmUserId(request));

			
		}
	}

	/**
	 * 物流协同过滤物流公司
	 * 
	 * @param request
	 * @param searchPara
	 */
	public void commomParamsLogisticsCompany(HttpServletRequest request,
			Map<String, Object> searchPara) {
		String operation = request
				.getParameter(ISystemConstant.DICTIONARY_OPERATION);

		if (ISystemConstant.DICTIONARY_OPERATION_COORDINATION.equals(operation) || 
				ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_COMPANY.equals(operation) ||
				ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_COMPANY_HISTORY.equals(operation) 
				) {
			String  userId=RmProjectHelper.getRmUserId(request);
			UserVo  userVo=userService.get(userId);
			LogisticsCompanyVo  vo=logisticsCompanyService.get(userVo.getOrganizationId());
			searchPara.put("logisticsCompanyId",vo.getId());
		}
	}

	/**
	 * 操作标识
	 * 
	 * @param request
	 * @param model
	 */
	public String commonFlagModel(HttpServletRequest request, Model model) {
		String operation = request
				.getParameter(ISystemConstant.DICTIONARY_OPERATION_FLAG);
		model.addAttribute(ISystemConstant.DICTIONARY_OPERATION_FLAG, operation);
		return operation;
	}

	/**
	 * 获取默认系数
	 * 
	 * 距离系数 01 结算系数 02
	 * 
	 * @return
	 */
	public BigDecimal getDefaultCoefficient(String type) {
		String value = RmGlobalReference.get(
				ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT, type);
		if (StringHelper.isEmpty(value)) {
			throw new PjException("默认系数参数尚未配置!");
		}
		return new BigDecimal(value);
	}

	/**
	 * 验证是否登录
	 * 
	 * @param variable
	 * @param request
	 * @return
	 */
	public RmUserVo checkUser(Map<String, Object> variable,
			HttpServletRequest request) {
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		if (null == userVo) {
			// 统一返回2 重新登录
			variable.put(ISystemConstant.AJAX_STATUS, "2");
		}

		return userVo;

	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public String upload(MultipartFile file, String id, HttpServletRequest request)throws IllegalStateException, IOException {
		Date date = new Date();
		String nowDate = DateUtil.getDateStr(date, "yyyyMMdd");
		String originalFilename = file.getOriginalFilename();
		String path = nowDate + File.separator + File.separator + id + File.separator + File.separator;
		// 修改后的文件名称
		String fileName = DateUtil.getDateStr(date, "yyyyMMddHHmmss") + originalFilename.substring(originalFilename.lastIndexOf("."));

		String fullPath = getRootPath() + path;

		if (!FileOperateHelper.isExists(fullPath)) {
			try {
				FileOperateHelper.newFolder(fullPath);
			} catch (Exception e) {
				if (!FileOperateHelper.isExists(fullPath)) {
					throw new PjException(e.getMessage());
				}
			}
		}
		File targetFile = new File(fullPath+fileName);
		file.transferTo(targetFile);
		return path + fileName;
	}

	/**
	 * 删除图片
	 * @param request
	 * @param id
	 * @param path 图片相对路径 
	 * @param type 类型 可以为空
	 * @return
	 */
	public void deleteImg(HttpServletRequest request, String path, String type) {
		
		String rootPath = null;
		if(!StringHelper.isEmpty(type)){
			rootPath = getPurRootPath(type);
		}else{
			rootPath = getRootPath();
		}
		
		File targetFile = new File(rootPath + path);
		
		if (targetFile.exists()){
			targetFile.delete();
		}
		
		return ;
	}
	/**
	 * 获取心跳周期
	 * 
	 * @return
	 */
	public int getHeartbeatCycle() {
		String value = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN,
				ISystemConstant.DICTIONARY_HEARTBEAT_CYCLE);
		if (StringHelper.isEmpty(value)) {
			throw new PjException("发送心跳包周期尚未配置!");
		} else {
			try {
				return ConvertHelper.toInt(value.trim());
			} catch (Exception e) {
				throw new PjException("无效的发送心跳包周期参数:"+value);
			}
		}

	}

}
