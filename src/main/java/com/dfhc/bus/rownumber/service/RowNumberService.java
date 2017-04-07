/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  RowNumberService.java
 *
 * 功能描述：  排号表服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.rownumber.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.base.product.service.ProductService;
import com.dfhc.base.product.vo.ProductVo;
import com.dfhc.base.producttype.service.ProductTypeService;
import com.dfhc.base.producttype.vo.ProductTypeVo;
import com.dfhc.bus.gate.service.GateService;
import com.dfhc.bus.gate.vo.GateVo;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.rownumber.IRowNumberConstants;
import com.dfhc.bus.rownumber.dao.RowNumberDao;
import com.dfhc.bus.rownumber.vo.CarListInteVo;
import com.dfhc.bus.rownumber.vo.RowNumberVo;
import com.dfhc.pub.server.PushDataVo;
import com.dfhc.pub.server.PushQueue;
import com.dfhc.pub.server.SocketCacheVo;
import com.dfhc.pub.server.SocketQueue;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.ListSortUtil;
import com.dfhc.util.StringHelper;
/**
 * 排号表服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class RowNumberService implements IRowNumberConstants {

    @Autowired
    /**
     * 排号表数据访问对象
     */
    private RowNumberDao rowNumberDao;
    
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private GateService gateService;
    @Autowired
    private OrderService orderService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(RowNumberVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = rowNumberDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "排号表插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(RowNumberVo[] vos) {
        String[] ids = rowNumberDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "排号表插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = rowNumberDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "排号表删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = rowNumberDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "排号表删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(RowNumberVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = rowNumberDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "排号表更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(RowNumberVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "排号表批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(RowNumberVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<RowNumberVo> lInsert = new ArrayList<RowNumberVo>();
        List<RowNumberVo> lUpdate = new ArrayList<RowNumberVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new RowNumberVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new RowNumberVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public RowNumberVo get(String id) {
        RowNumberVo vo = rowNumberDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = rowNumberDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<RowNumberVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<RowNumberVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<RowNumberVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<RowNumberVo> lResult = rowNumberDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<RowNumberVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<RowNumberVo> lResult = rowNumberDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(RowNumberVo vo) {
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
    private void verifyInsertVo(RowNumberVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<RowNumberVo> vos) {
        for(RowNumberVo vo:vos){
           verifyUpdateVo(vo);
        }
        rowNumberDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<RowNumberVo> vos) {
        for(RowNumberVo vo:vos){
           verifyInsertVo(vo);
        }
        rowNumberDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        RowNumberVo vo = rowNumberDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(rowNumberDao.update(vo)!=1){
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
     * 验证签到手机号
     * @param phoneNumber
     * @return
     */
	public Map<String, Object> checkedSignPhoneNumber(String phoneNumber) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringHelper.isEmpty(phoneNumber)){
    		
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "手机号不能为空！");
    		
    		return result;
    	}
		
		if(!pubParamService.checkTelephone(phoneNumber)){

			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "手机号格式不正确！");
    		
    		return result;
		}
		
		
		//是否已签到
		result.put("phoneNumber", phoneNumber);
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		result.put("rowDate", DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		result.put("notEquals", ISystemConstant.DICTIONARY_ROW_STATUS_04);
		
		RowNumberVo numberVo = getNumberVo(result);
		
		if(numberVo != null){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "该手机号已签到！");
    		
    		return result;
		}
		
		result = null;
		
		return result;
	}
	
  /**
    * 查询排号表vo
    * @param params
    * @return
    */
	public RowNumberVo getNumberVo(Map<String, Object> params, boolean flag){
		
		 List<RowNumberVo> list = list(params, null, 1, -1, flag);
		 
		 return CollectionUtils.isEmpty(list) ? null : list.get(0);
		
	}
	/**
	 * 查询排号表vo
	 * @param params
	 * @return
	 */
	public RowNumberVo getNumberVo(Map<String, Object> params){
		
		List<RowNumberVo> list = list(params, null, 1, -1, false);
		
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
		
	}
	
	/**
	 * 签到
	 * @param request
	 * @param phoneNumber 手机号
	 * @return
	 */
	public Map<String, Object> updateSign(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		//提货单号
		String ladingBillCode = request.getParameter("billid");
		
		if(StringHelper.isEmpty(ladingBillCode)){
			
			params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "未选择提货单！");
			
			return params;
		}
		params.put("ladingBillCode", ladingBillCode);
		params.put("termOfValidity", "termOfValidity");
		//查询状态  已提交待审核   审核通过  装货失败出厂
		params.put("ladingBillStatuses", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_11));
		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		//查询当前日期在销售订单提货日期范围内的指定司机(手机号)的  提货单
		List<LadingBillVo> list = ladingBillService.list(params, null);
		params.clear();
		
		if(CollectionUtils.isEmpty(list)){
			params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "未找到提货单！提货单号 ：" + ladingBillCode);
			
			return params;
		}
		
		LadingBillVo ladingBillVo = list.get(0);
		
		
		//查询该车是否单天签到过其他提货单
		params.put("truckNo", ladingBillVo.getLicensePlate());
		params.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		//排除已作废（04），已出厂(06)
		params.put("notRowStatus",StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_04,
				ISystemConstant.DICTIONARY_ROW_STATUS_06));
		
		if(!CollectionUtils.isEmpty(list(params, null))){
			params.clear();
			
			params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "该车牌号已签到!");
			
			return params;
			
		}
		params.clear();
		//如果是销售订单提货单  则判断是否过期
		if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(ladingBillVo.getBillType())){
			
			
			Date sqlDate = DateUtil.toSqlDate(DateUtil.addDays(DateUtil.getNowDate(), Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_DEFAULT_TIME, ISystemConstant.DICTIONARY_DEFAULT_DELIVERY_END_TIME))));
			
			if(sqlDate.compareTo(ladingBillVo.getEstimatedLoadingTime()) < 0){
				params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "签到失败! 该提货单已过期.");
				return params;
			}
			
			OrderVo orderVo = orderService.get(ladingBillVo.getOrderPlanId());
			
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(orderVo.getDelete_flag())){
				params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "签到失败! 该提货单对应的订单已删除.");
				return params;
			}
			
			
			Date nowDate = DateUtil.toSqlDate(DateUtil.getNowDate());
			
			if(nowDate.compareTo(orderVo.getDeliveryStartDate())<0 || nowDate.compareTo(orderVo.getDeliveryEndDate()) >0){
				params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "签到失败! 该提货单对应的订单尚未开始或者已结束.");
				return params;
			}
			
		}
		
		//判断该提货单之前是否签到  避免重复
		params.put("ladingBillId", ladingBillVo.getId());
		params.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		params.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_00, ISystemConstant.DICTIONARY_ROW_STATUS_01, ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_03, ISystemConstant.DICTIONARY_ROW_STATUS_05, ISystemConstant.DICTIONARY_ROW_STATUS_07));
		RowNumberVo rowNumberVo = getRowNumberVo(params);
		params.clear();
		
		if(null != rowNumberVo){
			params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "该提货单已签到！");
			return params;
		}
		
		
		//创建排号记录
		RowNumberVo vo = createRowNumberVo(ladingBillVo);
		//设置排号类型  状态  次序
		getCarSequence(vo, ladingBillVo, params);
//			RmVoHelper.markCreateStamp(request, vo);
		insert(vo);
		ladingBillService.update(ladingBillVo);
		params.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
		params.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
		params.put("billid", ladingBillVo.getLadingBillCode());
		params.put("billStatus", ladingBillVo.getLadingBillStatus());
		params.put("truckNumber", ladingBillVo.getLicensePlate());
		params.put("bullType", ladingBillVo.getBillType());
		params.put("productName", ladingBillVo.getProductName());
		params.put("driverName", ladingBillVo.getDriverName());
		params.put("queueid", vo.getProductSequence());
		
			
		//签到完成  
		
		String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
		
//		SocketQueue.getInstance().put(new SocketCacheVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02, null, false, null, null));
		PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);		
		PushQueue.getInstance().put(cacheVo);
		return params;
	}

	/**
	 * 提货单审核 不全签到表记录
	 * @param result
	 * @param vo
	 * @param request
	 */
	public void ladingBillAuditSign(Map<String, Object> result, LadingBillVo vo, HttpServletRequest request){
		RowNumberVo numberVo = getRowNumberVo(result, vo.getId());
		
		//之前排队  提货单未审核
		if(numberVo != null){
			//补充装车单信息:产品名称，产品类型,产品id,产品类型id  add by longsebo 2017-03-21
			if(StringHelper.isEmpty(vo.getProductType())||StringHelper.isEmpty(vo.getProductName())||
				StringHelper.isEmpty(vo.getProductId())||StringHelper.isEmpty(vo.getProductTypeId())){
				LadingBillVo dbVo = ladingBillService.get(vo.getId());
				vo.setProductName(dbVo.getProductName());
				vo.setProductType(dbVo.getProductType());
				vo.setProductId(dbVo.getProductId());
				vo.setProductTypeId(dbVo.getProductTypeId());
			}
			//补充拼音简码,产品id,控制方式 add by longsebo 2017-03-21
			Map<String, Object> variable = getProductId(vo.getProductId(), vo.getProductTypeId());
			
			String productId = (String)variable.get("productId");
			numberVo.setSequenceId(productId); 
			
			numberVo.setControlNumberVehicles((String)variable.get(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE));
//			vo.setPhoneNumber(ladingBillVo.getPhoneNumber());
//			vo.setTruckNo(ladingBillVo.getLicensePlate());
			numberVo.setAttribute1((String)variable.get(ISystemConstant.SPELL_BREVITY_CODE));
			getCarSequence(numberVo, vo, result);
			
			
			RmVoHelper.markModifyStamp(request, numberVo);
			update(numberVo);
		}
	}
	
	
	/**
	 * 签到获取签到序号 排队状态  类型
	 * @param rowNumberVo
	 * @param ladingBillVo
	 */
	public void getCarSequence(RowNumberVo rowNumberVo, LadingBillVo ladingBillVo, Map<String, Object> variable){
		//清空map  避免调用方的变量影响
		variable.clear();
		
		//未审核
		if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01.equals(ladingBillVo.getLadingBillStatus())){
			
			rowNumberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_01);
			
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			variable.put(ISystemConstant.AJAX_STATUS, "签到成功!等待营业室审核提货单。");
			
			return ;
			
		//审核通过或装货失败出厂	 	
		}else if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(ladingBillVo.getLadingBillStatus())||ISystemConstant.DICTIONARY_LADING_BILL_STATUS_11.equals(ladingBillVo.getLadingBillStatus())){
			//先判断是否是铁运
			if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(ladingBillVo.getBillType())){//铁运
				rowNumberVo.setRowType(ISystemConstant.DICTIONARY_ROW_TYPE_03);
			}else{
				/*
				 * 审核通过要判断之前是否提货失败
				 * 装货失败 要判断是前一天失败 还是当天失败
				 */
				
				//查询提货单对应的作废的排号记录
				variable.put("ladingBillId", ladingBillVo.getId());
				variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_04);
				variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
				
				
				List<RowNumberVo> list = list(variable, null);
				
				//说明之前排号成功 但是提货失败  提货失败分两种情况，一种当日提货失败，此种情况不做特殊处理，再次排号即可， 一种是前一天提货失败，此种情况，排号类型优先
				if(!CollectionUtils.isEmpty(list)){
					//查询当天作废的排号记录
					variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
					
					list = list(variable, null);
					
					//说明是当天提货失败，此时类型沿用上一次排号类型
					if(!CollectionUtils.isEmpty(list)){
						
						rowNumberVo.setRowType(list.get(0).getRowType());
						
					}else{
						//前一天提货失败
						rowNumberVo.setRowType(ISystemConstant.DICTIONARY_ROW_TYPE_01);
					}
					
				}else{
					//该车第一次排号
					
					rowNumberVo.setRowType(ISystemConstant.DICTIONARY_ROW_TYPE_02);
				}
			}
			
			rowNumberVo.setStatus(getRowStatus(rowNumberVo, variable));
			//根据控制车数方式： 按产品类别控制则取产品类型名称做序列名称（拼音码为产品类别拼音码)，按产品控制，则去产品名称作为序列名称（拼音码为产品拼音码). 
			String productSequence =null;
			if(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_02.equals(rowNumberVo.getControlNumberVehicles())){
				productSequence = pubParamService.getSequenceNumber(ladingBillVo.getProductType());
			}else{
				productSequence = pubParamService.getSequenceNumber(ladingBillVo.getProductName());
			}
			//attribute1暂存拼音码
			rowNumberVo.setProductSequence((String)rowNumberVo.getAttribute1() + productSequence);
			rowNumberVo.setSequence(productSequence);
			rowNumberVo.setAttribute1("");
			//修改签到时间为审核通过时间,  原来按签到时间顺序入厂，改成按审核通过时间，即按顺序号入厂 add by longsebo 2017-03-21
			rowNumberVo.setRowDate(DateUtil.getNowTimestamp());
			ladingBillVo.setTakeNoTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03);
			variable.clear();
			
//			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
//			variable.put(ISystemConstant.AJAX_MESSAGE, "签到成功！排队号：" + productSequence);
			
		}
		
	}
	
	/**
	 * 是否达到最大车数
	 * @param variable
	 * @param productId
	 * @return true 为到最大车数  false  已到最大车数
	 */
	private boolean isMax(Map<String, Object> variable, String productId){
		//首先查询该产品已入场车辆是否达到最大数
		variable.put("sequenceId", productId);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
		
		int count = getCount(variable);
		long temp = count;
		long maxNum = (Long)getProductId(productId, productId).get(MAX_BUS_NUM_IN_FACTORY);
		
		//已入场车数小于最大车数或者不限制车数
		if(0 == maxNum || temp<maxNum){
			return true;
		}
		return false;
		
	}
	
	/**
	 * 返回排队状态
	 * @param productId
	 * @param variable
	 * @return 
	 */
	public String getRowStatus(RowNumberVo rowNumberVo, Map<String, Object> variable){
		String status = null;
		variable.clear();
//		//查询门岗
//		GateVo gateVo = gateService.getGateVo(variable);
//		
//		
//		//门岗暂停  都是等候入场
//		if(ISystemConstant.DICTIONARY_GATE_STATUS_01.equals(gateVo.getStatus())){
//			
//			status = ISystemConstant.DICTIONARY_ROW_STATUS_02;
//			return status;
//			
//		}		
		
		
		if(!isMax(variable, rowNumberVo.getSequenceId())){
			//已到最大车数
			//查询当前产品队列中是否有当前入场
			variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
			
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			
			variable.put("sequenceId", rowNumberVo.getSequenceId());
			
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_07);
			
			//查询该产品下准备入场车辆
			List<RowNumberVo> list = list(variable, null);
			
			
			//该产品下有准备入场车辆  则状态为等候入场
			if(!CollectionUtils.isEmpty(list)){
				
				status = ISystemConstant.DICTIONARY_ROW_STATUS_02;
				return status;
				
			}else{
				
				//没有 则准备入场
				status = ISystemConstant.DICTIONARY_ROW_STATUS_07;
				return status;
			}
			
			
			
		}
		
		variable.clear();

		//查询是否有当前入场车辆   并确定该产品是否有等待车辆
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_03);
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		RowNumberVo rowNumberVo2 = getRowNumberVo(variable);
		
		variable.clear();

		if(rowNumberVo2 == null){
			//没有当前入场
			//查询该产品是否有车辆等待
			variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
			
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_03);
			
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			
			variable.put("sequenceId", rowNumberVo.getSequenceId());
			
			List<RowNumberVo> list = list(variable, null);
			
			if(CollectionUtils.isEmpty(list)){
				//没有车辆 则是当前入场
				status = ISystemConstant.DICTIONARY_ROW_STATUS_03;
				return status;
			}
		}
		
		//查看前面是否车辆等待入场， 如果没有则当前入场
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07, ISystemConstant.DICTIONARY_ROW_STATUS_03));
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		
		if(CollectionUtils.isEmpty(list(variable, null))){
			variable.clear();
			return ISystemConstant.DICTIONARY_ROW_STATUS_03;
			
		}
		//如果前面有车辆等待 则分产品判断状态
		
		variable.put("sequenceId", rowNumberVo.getSequenceId());
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07, ISystemConstant.DICTIONARY_ROW_STATUS_03));
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		List<RowNumberVo> list = list(variable, null);
		
		if(CollectionUtils.isEmpty(list)){
			//前面没车 准备入场
			status = ISystemConstant.DICTIONARY_ROW_STATUS_07;
		}else if(list.size() == 1){//有一辆车  看当前车是否是当前入场状态
			
			if(ISystemConstant.DICTIONARY_ROW_STATUS_03.equals(list.get(0).getStatus())){
				//准备入场
				status = ISystemConstant.DICTIONARY_ROW_STATUS_07;
			}else if(ISystemConstant.DICTIONARY_ROW_STATUS_07.equals(list.get(0).getStatus())){
				//前面的车是准备入场
				status = ISystemConstant.DICTIONARY_ROW_STATUS_02;
			}else if(ISystemConstant.DICTIONARY_ROW_STATUS_02.equals(list.get(0).getStatus())){
				//前面的车是等候入场  则看当前车是否是优先
				
				//当前车优先
				if(ISystemConstant.DICTIONARY_ROW_TYPE_01.equals(rowNumberVo.getRowType())){
					status = ISystemConstant.DICTIONARY_ROW_STATUS_07;
				}else{
					status = ISystemConstant.DICTIONARY_ROW_STATUS_02;
				}
				
			}
			
		}else{
			//如果前面的车不止一辆  则查看是否有   准备入场  如果有 则不管是否优先  都是等候入场  如果没有 则看是否优先 决定排队状态
			variable.remove("statuss");
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_07);
			list = list(variable, null);
			if(!CollectionUtils.isEmpty(list)){
				status = ISystemConstant.DICTIONARY_ROW_STATUS_02;
			}else{
				//当前车优先
				if(ISystemConstant.DICTIONARY_ROW_TYPE_01.equals(rowNumberVo.getRowType())){
					status = ISystemConstant.DICTIONARY_ROW_STATUS_07;
				}else{
					status = ISystemConstant.DICTIONARY_ROW_STATUS_02;
				}
			}
			
		}
		
		return status;
	}
	
	
	/**
	 * 创建排号表对象 此处只创建一些基本信息  具体排号次序调用其他接口
	 * @param ladingBillVo
	 * @return
	 */
	private RowNumberVo createRowNumberVo(LadingBillVo ladingBillVo) {
		RowNumberVo vo = new RowNumberVo();
		
		vo.setLadingBillId(ladingBillVo.getId());
		vo.setRowDate(DateUtil.getNowTimestamp());
		
		Map<String, Object> variable = getProductId(ladingBillVo.getProductId(), ladingBillVo.getProductTypeId());
		
		String productId = (String)variable.get("productId");
		
		vo.setSequenceId(productId);
		
		vo.setControlNumberVehicles((String)variable.get(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE));
		vo.setPhoneNumber(ladingBillVo.getPhoneNumber());
		vo.setTruckNo(ladingBillVo.getLicensePlate());
		vo.setAttribute1((String)variable.get(ISystemConstant.SPELL_BREVITY_CODE));
		return vo;
	}
	/**
	 * 除了提货单待审核  其他情况下都是  排队(取号并更新提货单取号时间)
	 * @param rowType 排号类型 
	 */
//	public void createRowNumberSequence(String rowType, RowNumberVo vo, LadingBillVo ladingBillVo) {
//		
//		vo.setSequence(Long.valueOf(getRowSequence(rowType)));
//		
//		vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_02);
//		
//		ladingBillVo.setTakeNoTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
//		
//	}

	/**
	 * 返回排号状态  （作废）
	 * @param number 产内车辆最大数量 0则不限制
	 * @param rowType 排号类型
	 * @return
	 */
//	private String rowSequenceStatus(int number, String rowType, String productId, Map<String, Object> variable) {
//		if(0 != number){//限制
//			//查询产内最大车数量
//			if(StringHelper.isEmpty(productId)){
//				throw new PjException("限制车辆数量，但是产品id或者产品类别id为空！");
//			}
//			//拿到该产品已入场的车数量
//			int alreadyEnteredNumber = getAlreadyEnteredNumber(productId, variable);
//			 
//			//达到最大车数 此时不管优先还是普通  状态都是排队
//			if(alreadyEnteredNumber == number){
//				//状态  排队
//				return ISystemConstant.DICTIONARY_ROW_STATUS_02;
//				
//			}else if(alreadyEnteredNumber < number){//进场车辆小于最大车数量
//				
//				
//				if(getAlreadyEnteredCar(variable, productId) != null){//有带进场车辆
//					
//					return ISystemConstant.DICTIONARY_ROW_STATUS_02;
//				}else{
//					
//					return ISystemConstant.DICTIONARY_ROW_STATUS_03;
//				}
//				
//			}else{
//				throw new PjException("已进场车辆数大于最大限制数量！");
//			}
//			
//			
//			
//		}else{//不限制
//			
//			if(getAlreadyEnteredCar(variable, productId) != null){//有带进场车辆
//				
//				return ISystemConstant.DICTIONARY_ROW_STATUS_02;
//			}else{
//				
//				return ISystemConstant.DICTIONARY_ROW_STATUS_03;
//			}
//			
//		}
//		
//	}

	/**
	 * 查询准备入厂车辆
	 * @param variable
	 * @return
	 */
	public List<RowNumberVo> getAlreadyEnteredCar(Map<String, Object> variable){
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_07);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		return list(variable, null);
	}
	
	/**
	 * 更新排队列表
	 * @param rowNumberVo
	 */
	public synchronized void updateCarList(RowNumberVo rowNumberVo, String type){
		
		Map<String, Object> variable = new HashMap<String, Object>();
		
		
		if(ISystemConstant.CAR_ADMISSION.equals(type)){//入场
			
			//查询是否有铁运的签到
			variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_03);
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07));
			
			List<RowNumberVo> trainList = list(variable, null);
			variable.clear();
			
			
			//有铁运签到的
			if(!CollectionUtils.isEmpty(trainList)){
				
				//从铁运中找一个出来作为当前入场
				RowNumberVo rowNumberVo03 = null;
				if((rowNumberVo03 = getForTrain(trainList, variable))!=null){
				
					String status = (String) variable.get("status");
					if(ISystemConstant.DICTIONARY_ROW_STATUS_07.equals(status)){//如果改成当前入场的车之前是准备入场 则需要在队列中补上一个该产品的准备入场的车辆
						updateReadyFactory(rowNumberVo03, variable);
					}
				}else{
					//处理优先普通队列
					priorityOrdinaryList();
				}
//				if(ISystemConstant.DICTIONARY_ROW_STATUS_07.equals(status)){//如果改成当前入场的车之前是准备入场 则需要在队列中补上一个该产品的准备入场的车辆
//					String sequenceId = (String) variable.get("sequenceId");
//					variable.clear();
//					//查询等候入场的  补充一个进入准备入场
//					
//					variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_03);
//					variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//					variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_02);
//					variable.put("sequenceId", sequenceId);
//					
//					List<RowNumberVo> list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");
//					
//					if(!CollectionUtils.isEmpty(list)){
//						
//						RowNumberVo nuberVo = list.get(0);
//						
//						nuberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_07);
//						
//						update(nuberVo);
//					}else{
//						//没有铁运的 查询优先的
//						variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_01);
//						
//						list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");
//						
//						if(!CollectionUtils.isEmpty(list)){
//							
//							RowNumberVo nuberVo = list.get(0);
//							
//							nuberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_07);
//							
//							update(nuberVo);
//						}else{
//							//没有优先的 查询普通的
//							variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_02);
//							
//							list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");
//							if(!CollectionUtils.isEmpty(list)){
//								
//								RowNumberVo nuberVo = list.get(0);
//								
//								nuberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_07);
//								
//								update(nuberVo);
//							}
//						}
//						
//					}
//					
//				}
				
				
				return;
				//没有铁运的 查询优先的	
			}else{
				//处理优先普通队列
				priorityOrdinaryList();
			}
			
//			//查询准备入场车辆
//			List<RowNumberVo> list = getAlreadyEnteredCar(variable);
//			variable.clear();
//			
//			//如果没有则说明没有车辆排队
//			if(CollectionUtils.isEmpty(list)){
//				return;
//			}
//			//没有达到最大数量的
//			RowNumberVo vo = selectedOneEntrance(list, variable);
//			
//			if(vo == null){
//				return ;
//			}
//			
//			vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03);
//			
//			update(vo);
			
			//补充准备入场车辆队列
//			updateReadyFactory(vo, variable);
		}else{//出厂
			
			//出厂时触发队列的的前提是 进场队列不再变化（即产内各产品车辆都达到最大数量）
			
			variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			List<RowNumberVo> facCarNum = getInFacCarNum(variable);
			variable.clear();
			
			if(CollectionUtils.isEmpty(facCarNum)){
				return;
			}
			
			for (RowNumberVo rowNumberVo2 : facCarNum) {
				//不限制车辆 说明只要进场一辆车 就会修改一次队列
				if(rowNumberVo2.getMaxBusNumInFactory().equals(0L)){
					return;
				}
				//没有达到最大限制 说明只要进场一辆车 就会修改一次队列
				if(rowNumberVo2.getCarNum() < rowNumberVo2.getMaxBusNumInFactory()){
					return;
				}
			}
			
			//查看是否有当前入场车辆 在等着
			variable.put("rowDate", DateUtil.getDateStr(new java.util.Date(), "yyyy-MM-dd"));
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_03);
			
			RowNumberVo currentCar = getRowNumberVo(variable);
			variable.clear();
			
			if(currentCar != null){
				return;
			}
			
			
			
			variable = getProductId(rowNumberVo.getSequenceId(), rowNumberVo.getSequenceId());
			
			long maxCar = (long) variable.get(MAX_BUS_NUM_IN_FACTORY);
			
			if(maxCar == 0){
				return;
			}
			
//			variable.put("rowDate", DateUtil.getDateStr(new java.util.Date(), "yyyy-MM-dd"));
//			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//			variable.put("sequenceId", rowNumberVo.getSequenceId());
//			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
//			
//			int carNum = getCount(variable);
//			variable.clear();
//			//入场车辆小于最大车辆  直接返回 由入场操作触发修改列表
//			if(carNum<maxCar){
//				return;
//			}
			//查询准备入场车辆
			List<RowNumberVo> list = getAlreadyEnteredCar(variable);
			variable.clear();
			
			//如果没有则说明没有车辆排队
			if(CollectionUtils.isEmpty(list)){
				return;
			}
			
			//没有达到最大数量的
			RowNumberVo vo = selectedOneEntrance(list, variable);
			//拿到的车和出厂的车不是同一个产品
			if(null != vo){//不是所有车都达到最大数量
				if(!vo.getSequenceId().equals(rowNumberVo.getSequenceId())){
					for (RowNumberVo numberVo : list) {
						if(numberVo.getSequenceId().equals(rowNumberVo.getSequenceId())){
							if(numberVo.getRowDate().before(vo.getRowDate())){
								vo = numberVo;
							}
							break;
						}
					}
					vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03);
					update(vo);
				}
			}else{
				//所有车都达到最大数量
				for (RowNumberVo numberVo : list) {
					if(numberVo.getSequenceId().equals(rowNumberVo.getSequenceId())){
						if(numberVo.getRowDate().before(vo.getRowDate())){
							vo = numberVo;
						}
						break;
					}
				}
				
				vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03);
				update(vo);
				
			}
			
			//补充准备入场车辆队列
			updateReadyFactory(vo, variable);
			//签到完成  
			
			//推送门岗大屏
			String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
			
//			SocketQueue.getInstance().put(new SocketCacheVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02, null, false, null, null));
			PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
			PushQueue.getInstance().put(cacheVo);
		}
	}
	/**
	 * 处理优先普通队列
	 * @param variable
	 */
	private void priorityOrdinaryList() {
		Map<String,Object> variable = new HashMap<String,Object>();
		variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_01);
		variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07));
		List<RowNumberVo> list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");
		RowNumberVo nuberVo  = null;
		if(CollectionUtils.isEmpty(list)){
			//没有优先的 查询普通的
			variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_02);
			
			list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");					
		}
		//没有达到最大数量的
		RowNumberVo vo = selectedOneEntrance(list, variable);
		if(vo!=null){
			String oldStatus = vo.getStatus();
			
			vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03);
			
			update(vo);
			//判断修改前是否为准备入厂
			if(ISystemConstant.DICTIONARY_ROW_STATUS_07.equals(oldStatus)){
				//补充准备入场车辆队列
				updateReadyFactory(vo, variable);
			}
		}
	}
	
	

	
	/**
	 * 返回已入场的车辆数量 （作废  根据产品分类查询）
	 * @param productId
	 * @return
	 */
//	private int getAlreadyEnteredNumber(String productId, Map<String, Object> variable) {
//		
//		
//		variable.put("sequenceId", productId);
//		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
//		variable.put("rowDate", DateUtil.toSqlDate(DateUtil.getNowDate()));
//		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		
//		List<RowNumberVo> list = list(variable, null);
//		return CollectionUtils.isEmpty(list) ? 0 : list.size();
//	}

	/**
	 * 从铁运签到中找一个出来 作为当前入场
	 * @param trainList
	 * @return 
	 */
	private RowNumberVo getForTrain(List<RowNumberVo> trainList, Map<String, Object> variable) {
		
		//存放每个产品的最先签到的铁运
		Map<String, RowNumberVo> trainMap = new HashMap<String, RowNumberVo>();
		for (RowNumberVo trainVo : trainList) {
			
			RowNumberVo mapVo = trainMap.get(trainVo.getSequenceId());
			
			if(mapVo != null){
				
				//找同产品下产品序号最少
				if(mapVo.getRowDate().after(trainVo.getRowDate())){
					trainMap.put(trainVo.getSequenceId(), trainVo);
					
				}
			}else{
				trainMap.put(trainVo.getSequenceId(), trainVo);
			}
			
		}
		
		
		Collection<RowNumberVo> values = trainMap.values();
		
		List<RowNumberVo> list = new ArrayList<RowNumberVo>(values);
		
		//按照次序 升序排列  第一个就是最先签到的
		new ListSortUtil<RowNumberVo>().sort(list, ORDER_BY_FILED, ORDER_BY_MODE_ASC);
		
		//没有达到最大数量的
		RowNumberVo vo = selectedOneEntrance(list, variable);
		if(vo!=null){
			vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03);
			variable.put("status", vo.getStatus());
//			variable.put("sequenceId", vo.getSequenceId());			
			update(vo);
			return vo;
		}else{
			return null;
		}
		
		//当前入场的车辆
//		RowNumberVo rowNumberVo = list.get(0);
//
//		
//		variable.put("status", rowNumberVo.getStatus());
//		variable.put("sequenceId", rowNumberVo.getSequenceId());
//		
//		rowNumberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03);
//		update(rowNumberVo);
		
		
	}

	/**
	 * 补充准备入场车辆队列
	 * @param vo
	 */
	private void updateReadyFactory(RowNumberVo vo, Map<String, Object> variable) {
		variable.clear();
		//查询该产品下的车辆 铁运
		variable.put("rowDate", DateUtil.getDateStr(new java.util.Date(), "yyyy-MM-dd"));
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("sequenceId", vo.getSequenceId());
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_02);
		variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_03);
		
		List<RowNumberVo> list = list(variable, "BUS_ROW_NUMBER.ROW_DATE ASC");
		
		if(CollectionUtils.isEmpty(list)){
			variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_01);
			list = list(variable, "BUS_ROW_NUMBER.ROW_DATE ASC");
			
			if(CollectionUtils.isEmpty(list)){
				variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_02);
				list = list(variable, "BUS_ROW_NUMBER.ROW_DATE ASC");
			}
		}
		
		if(!CollectionUtils.isEmpty(list)){
			RowNumberVo numberVo = list.get(0);
			numberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_07);
			update(numberVo);
		}
		
	}

	/**
	 * @param rowType 排号类型
	 * @return 返回排号次序
	 */
	private String getRowSequence(String rowType){
		//优先
		if(ISystemConstant.DICTIONARY_ROW_TYPE_01.equals(rowType)){
			
			return pubParamService.getSequenceNumber(ROW_NUMBER_PRIOR);
		}else if(ISystemConstant.DICTIONARY_ROW_TYPE_02.equals(rowType)){//普通
			
			return pubParamService.getSequenceNumber(ROW_NUMBER_COMMON);
		}else{
			return pubParamService.getSequenceNumber("");
		}
	}	
	
	/**
	 * 获取控制车数方式 和产品id/产品类别id
	 * 
	 * 产品类别id不控制车数量  查询产品id是否控制车数量 如果不控制  状态为不控制， 如果控制  状态为产品类别控制
	 * 产品id控制车数量  状态为产品控制
	 * @param productId
	 * @param productTypeId
	 * @return 返回 控制车辆数量的 产品id 、 控制车辆状态  、最大车辆数量 （不限制返回0）
	 */
	private Map<String, Object> getProductId(String productId, String productTypeId){
		Map<String, Object> variable = new HashMap<String, Object>();
		ProductTypeVo productTypeVo = productTypeService.get(productTypeId);
		
		//产品类别不限制  查询产品类别
		if( productTypeVo == null || ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(productTypeVo.getIsCtrlBusNum())){
			//产品是否限制车数量
			ProductVo productVo = productService.get(productId);
			
			//产品类别不限制
			if(productVo == null || ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(productVo.getIsCtrlBusNum())){
				//限制车数量
				variable.put(MAX_BUS_NUM_IN_FACTORY, 0L);
				
				variable.put(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE, ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_00);
			}else{
				variable.put(MAX_BUS_NUM_IN_FACTORY, productVo.getMaxBusNumInFactory());
				
				variable.put(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE, ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_01);
				variable.put(ISystemConstant.SPELL_BREVITY_CODE, productVo.getSpellBrevityCode());
			}
			variable.put("productId", productId);
		}else{
			variable.put(ISystemConstant.SPELL_BREVITY_CODE, productTypeVo.getSpellBrevityCode());
			variable.put(MAX_BUS_NUM_IN_FACTORY, productTypeVo.getMaxBusNumInFactory());
			variable.put("productId", productTypeId);
			variable.put(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE, ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_02);
		}
		
		return variable;
	}


	public RowNumberVo getRowNumberVo(Map<String, Object> variable){
		
		return getRowNumberVo(variable, false);
	}
	
	public RowNumberVo getRowNumberVo(Map<String, Object> variable, boolean flag){
		List<RowNumberVo> list = list(variable, null, 1, -1, flag);
		
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	

	
	/**
	 * 查询 产品id下 第一个签到登记的车
	 * @param sequenceId 产品id
	 * @param variable
	 * @return
	 */
	private RowNumberVo productFirstCar(String sequenceId,
			Map<String, Object> variable) {
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		variable.put("sequenceId", sequenceId);
		
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_02);
		
		variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_01);
		
		
		//查询优先
		List<RowNumberVo> list = list(variable, "BUS_ROW_NUMBER.SEQUENCE asc");
		
		
		if(!CollectionUtils.isEmpty(list)){
			
			return list.get(0);
		}
		
		//产品id下没有优先的
		
		variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_02);
		
		list = list(variable, "BUS_ROW_NUMBER.SEQUENCE asc");
		
		if(!CollectionUtils.isEmpty(list)){
			
			return list.get(0);
		}
		
		return null;
	}

	/**
	 * 查询提货单对应的待审核的排号表
	 * @param result
	 * @param id
	 * @return
	 */
	public RowNumberVo getRowNumberVo(Map<String, Object> variable, String id) {
		variable.put("ladingBillId", id);
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_01);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		return getRowNumberVo(variable);
		
	} 

	
	/**
	 * 选择一辆车进场
	 * @param readlyList
	 * @param variable
	 */
	public RowNumberVo selectedOneEntrance(List<RowNumberVo> readlyList, Map<String, Object> carNumMap){
		
		List<RowNumberVo> list = new ArrayList<RowNumberVo>();
		if(CollectionUtils.isEmpty(readlyList)){
			return null;
		}
		
		separateList(readlyList, list);
		//按照次序 升序排列  第一个就是最先签到的
		new ListSortUtil<RowNumberVo>().sort(list, ORDER_BY_FILED, ORDER_BY_MODE_ASC);
		
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 将未达到最大车数的排队记录分离出来
	 * @param list
	 * @param carNumMap
	 */
	public void separateList(List<RowNumberVo> readlyList,  List<RowNumberVo> list){
		Map<String, Object> variable = new HashMap<String,Object>();
		for (RowNumberVo rowNumberVo : readlyList) {
			
			//按产品控制
			if(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_01.equals(rowNumberVo.getControlNumberVehicles())){
				//考虑特殊情况，比如：昨天入厂，今天还没出厂 注释日期
				//variable.put("rowDate", DateUtil.getDateStr(new java.util.Date(), "yyyy-MM-dd"));
				variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
				variable.put("sequenceId", rowNumberVo.getSequenceId());
				variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
				
				int carNum = getCount(variable);
				
				ProductVo productVo = productService.get(rowNumberVo.getSequenceId());
				
				if(carNum < productVo.getMaxBusNumInFactory()){
					list.add(rowNumberVo);
				}
				
			//按产品类别控制
			}else if(ISystemConstant.DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_02.equals(rowNumberVo.getControlNumberVehicles())){
				//考虑特殊情况，比如：昨天入厂，今天还没出厂 注释日期
				//variable.put("rowDate", DateUtil.getDateStr(new java.util.Date(), "yyyy-MM-dd"));
				variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
				variable.put("sequenceId", rowNumberVo.getSequenceId());
				variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
				
				int carNum = getCount(variable);
				ProductTypeVo productTypeVo = productTypeService.get(rowNumberVo.getSequenceId());
				
				if(carNum < productTypeVo.getMaxBusNumInFactory()){
					list.add(rowNumberVo);
				}
				
			//不控制
			}else{
				list.add(rowNumberVo);
			}
			
			
		}
	}

	/**'
	 * 查询当前入场车辆
	 * @param truckNumber
	 * @param variable
	 * @return
	 */
	public RowNumberVo getCarTicketr(String truckNumber, Map<String, Object> variable){
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_03);
		variable.put("truckNo", truckNumber);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		RowNumberVo rowNumberVo = getRowNumberVo(variable);
		variable.clear();
		return rowNumberVo;
	}
	
	
	/**
	 * 车辆入场操作
	 * @param request
	 * @param result
	 * @param truckNumber 车牌号
	 * @return
	 */
	public Map<String, Object> carTicket(HttpServletRequest request,
			Map<String, Object> result, String truckNumber) {
		
		//查询门岗 此处不考虑门岗  入关门岗暂停 则队列修改  车辆不入场就行
		GateVo gateVo = gateService.getGateVo(result);
		result.clear();
		
		//门岗暂停 
		if(ISystemConstant.DICTIONARY_GATE_STATUS_01.equals(gateVo.getStatus())){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "根据车牌号未找到对应车辆！");
			result.put("operationStatus", "0");
			return result;
		}	
		
		RowNumberVo rowNumberVo = getCarTicketr(truckNumber, result);
		
		if(null == rowNumberVo){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "根据车牌号未找到对应车辆！");
			result.put("operationStatus", "0");
			return result;
		}
		
		LadingBillVo ladingBillVo = ladingBillService.get(rowNumberVo.getLadingBillId());
		
		rowNumberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_05);
		ladingBillVo.setInTime(DateUtil.getNowTimestamp());
		
		//判断是否需要安检(过磅并且是罐装产品，安检，袋装不检)
		if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(ladingBillVo.getIsPound())){
			
			ProductVo productVo = productService.get(ladingBillVo.getProductId());
			
			if(ISystemConstant.DICTIONARY_STORE_MODE_00.equals(productVo.getStoreMode())){
				ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04);
			}else{
				ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05);
			}
		}else{
			//进门时设置状态为车辆安检
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05);
		}
		
		
		
		
		
		
//		RmVoHelper.markModifyStamp(request, ladingBillVo);
		ladingBillService.update(ladingBillVo);
		
//		RmVoHelper.markModifyStamp(request, rowNumberVo);
		update(rowNumberVo);
		
		result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
		result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
		result.put("operationStatus", "1");
		//修改队列
		updateCarList(rowNumberVo, ISystemConstant.CAR_ADMISSION);
		
		//触发socket推送
		String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
		PushDataVo vo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);		
		PushQueue.getInstance().put(vo);
		
		
		PushDataVo vo2 = new PushDataVo(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_02), ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04);
		PushQueue.getInstance().put(vo2);		
		return result;
	}

	/**
	 * 入场弃号
	 * @param request
	 * @param truckNumber 车牌号
	 * @return
	 */
	public Map<String, Object> giveUpSequence(HttpServletRequest request,
			String truckNumber, Map<String, Object> result) {
		RowNumberVo rowNumberVo = getCarTicketr(truckNumber, result);
		
		if(null == rowNumberVo){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "根据车牌号未找到对应车辆！");
			result.put("operationStatus", "0");
			return result;
		}
		
		rowNumberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_04);
		
		//修改提货单状态为审核通过 和签到所需状态保持一致
		LadingBillVo ladingBillVo = ladingBillService.get(rowNumberVo.getLadingBillId());
		
		ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
		
		ladingBillService.update(ladingBillVo);
		update(rowNumberVo);
		result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
		result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
		result.put("operationStatus", "1");
		
		//修改队列
		updateCarList(rowNumberVo, ISystemConstant.CAR_ADMISSION);
		
		//触发socket推送
		String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
		PushDataVo vo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);		
		PushQueue.getInstance().put(vo);
		return result;
	}

	/**
	 * 获取车辆队列
	 * @param request
	 * @param billStatus 提货单状态
	 * @return
	 */
	public Map<String, Object> getCarSequenceList(HttpServletRequest request,
			String billStatus) {
		Map<String, Object> result = new HashMap<String, Object>();
		//审核通过【门岗大屏排队列表】
		if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(billStatus)){
			//
			return getGateCarList(result);
		//车辆安检【磅房安检通过列表】
		}else if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04.equals(billStatus)){
			
			return carSecurityCheck(result);
		//装车待分配【仓库装车列表】	
		}else if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05.equals(billStatus)){			
			//确保最新分配的是队列的第一条记录 未分配的按照入厂时间排序  add by longsebo 2017-03-24
			result.put("orderStr", "BUS_LADING_BILL.ALLOCATION_VEHICLE_TIME DESC, BUS_LADING_BILL.IN_TIME ASC");
			return toBeDistribution(result);
		}else{
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "状态不对！");
			
			return result;
		}
		
	}

	/**
	 * 装车待分配车辆信息列表
	 * @param result
	 * @return
	 */
	private Map<String, Object> toBeDistribution(Map<String, Object> variable) {
		variable.put("rowDate", DateUtil.getNowDate("yyyy-MM-dd"));
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
		//remove by longsebo 2017-03-22
		//variable.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05);
		//是否装车为是
		variable.put("isLoading", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
		//remove by longsebo 2017-03-22
		//提货单状态=10
		//variable.put("ladingBillStatus",ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10);
		//提货单状态=05 或 提货单状态=10  add by longsebo 2017-03-22
		variable.put("ladingBillStatues",StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05,ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10));
		//装车单.状态 =01 or 装车单.状态 =02
		variable.put("loadingNoticeStatues", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01,ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_02));
		List<CarListInteVo> carList = rowNumberDao.getCarList(variable);
		variable.clear();
		
		if(CollectionUtils.isEmpty(carList)){
			
			variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "暂无车辆信息！");
			
			return variable;
		}
		
		carListHandle(variable, carList);
		
		return variable;
	}

	/**
	 * 车辆安全检查通过列表
	 * @param result
	 * @return
	 */
	private Map<String, Object> carSecurityCheck(Map<String, Object> variable) {
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("notCheckStatus", ISystemConstant.DICTIONARY_CHECK_STATUS_0);
		variable.put("securityCheck", "securityCheck");
		variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07, ISystemConstant.DICTIONARY_ROW_STATUS_03, ISystemConstant.DICTIONARY_ROW_STATUS_05));
		//提货单.是否过磅=1
		variable.put("isPound", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
		//提货单状态=04 或者 提货单状态=05 或 提货单状态=10
		variable.put("ladingBillStatues",StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04,ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05,ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10));
		List<CarListInteVo> carList = rowNumberDao.getCarList(variable);
		variable.clear();
		
		if(CollectionUtils.isEmpty(carList)){
			
			variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "暂无车辆信息！");
			
			return variable;
		}
		carListHandle(variable, carList);
		
		return variable;
		
	}

	/**
	 * 入场队列
	 * @param variable
	 * @return
	 */
	private Map<String, Object> getGateCarList(Map<String, Object> variable){
		variable.put("rowDate", DateUtil.getNowDate("yyyy-MM-dd"));
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("orderStr", "DECODE(truckStaus,'" + ISystemConstant.DICTIONARY_ROW_STATUS_03 + "','1','" + ISystemConstant.DICTIONARY_ROW_STATUS_07 + "','2','" + ISystemConstant.DICTIONARY_ROW_STATUS_02 + "','3')");
		variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_03, ISystemConstant.DICTIONARY_ROW_STATUS_07, ISystemConstant.DICTIONARY_ROW_STATUS_02));
		
		List<CarListInteVo> carList = rowNumberDao.getCarList(variable);
		variable.clear();
		
		if(CollectionUtils.isEmpty(carList)){
			
			variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "暂无排队队列");
			
			return variable;
		}
		carListHandle(variable, carList);
		
		return variable;
		
	}
	
	private void carListHandle(Map<String, Object> variable, List<CarListInteVo> carList){
		List<RowNumberVo> maxCarNumList = getMaxCarNum(variable);
		
		//不全场内车辆属性
		supplementCarNum(carList, maxCarNumList);
		
		//不全产品类别 取消 remove by longsebo 2017-03-17
		//supplementProductType(carList);
		
		variable.clear();
		variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
		variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
		variable.put("queues", carList);
		
	}
	
	/**
	 * 补全产品名称
	 * @param carList
	 */
	private void supplementProductType(List<CarListInteVo> carList) {
		Map<String, Object> searchPara = new HashMap<String,Object>();
		for (CarListInteVo vo : carList) {
			searchPara.put("ladingBillCode", vo.getBillid());
			List<LadingBillVo> lbVos = ladingBillService.list(searchPara, null);
			if(lbVos!=null && lbVos.size()>0){
				vo.setProductType(lbVos.get(0).getProductName());
			}
		}
		
	}

	
	
	/**
	 * 不全场内车辆属性
	 * @param carList
	 * @param maxCarNumList
	 */
	private void supplementCarNum(List<CarListInteVo> carList,
			List<RowNumberVo> maxCarNumList) {
		if(CollectionUtils.isEmpty(maxCarNumList)){
			for (CarListInteVo vo : carList) {
				vo.setTruckCounts(String.format("%02d", 0) + "/" + String.format("%02d", StringHelper.isEmpty(vo.getTruckCounts())?0:Integer.valueOf(vo.getTruckCounts())));
			}
		}else{
			for (CarListInteVo vo : carList) {
				RowNumberVo rowNumberVo = setTruckCounts(vo.getSequenceId(), maxCarNumList);
				
				if(null != rowNumberVo){
					vo.setTruckCounts(String.format("%02d", rowNumberVo.getCarNum()) + "/" + String.format("%02d", StringHelper.isEmpty(vo.getTruckCounts())?0:Integer.valueOf(vo.getTruckCounts())));
				}else{
					vo.setTruckCounts(String.format("%02d", 0) + "/" + String.format("%02d", StringHelper.isEmpty(vo.getTruckCounts())?0:Integer.valueOf(vo.getTruckCounts())));
				}
			}
		}
		
	}

	private RowNumberVo setTruckCounts(String sequenceId, List<RowNumberVo> maxCarNumList){
		for (RowNumberVo rowNumberVo : maxCarNumList) {
			
			if(sequenceId.equals(rowNumberVo.getSequenceId())){
				return rowNumberVo;
			}
		}
		return null;
		
	}
	
	private List<RowNumberVo> getMaxCarNum(Map<String, Object> variable){
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
		variable.put("statusNum", "statusNum");
		
		return rowNumberDao.listProduct(variable);
	
	}
	
	/**
     * 查询个产品进入产内的车数量和控制车辆的最大数量
     * @param params
     * @return
     */
    public List<RowNumberVo> getInFacCarNum(Map<String, Object> params){
    	return rowNumberDao.getInFacCarNum(params);
    }
	
    
	/**
	 * 开具提货单作废时 作废牌号表
	 * 
	 * */
	public void updateInvalidRowNumber(Map<String, Object> variable,HttpServletRequest request,String ladingBillId){
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("ladingBillId",ladingBillId);
		variable.put("notEquals", ISystemConstant.DICTIONARY_ROW_STATUS_04);
		List<RowNumberVo>  list=list(variable, null);
		if(!list.isEmpty()){
			for(RowNumberVo  vo:list){
				cancelRowNumber(vo);
				
				vo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_04);
				
				update(vo);
			}
		}
		
		
		
		return ;
	}
	
	
	/**
	 * 作废提货单时，调用该方法作废排号表
	 * @param vo
	 */
	public void cancelRowNumber(RowNumberVo vo ){
		if(ISystemConstant.DICTIONARY_ROW_STATUS_03.equals(vo.getStatus())){
			priorityOrdinaryListByStatus(ISystemConstant.DICTIONARY_ROW_STATUS_03,ISystemConstant.DICTIONARY_ROW_STATUS_07);
		}else if(ISystemConstant.DICTIONARY_ROW_STATUS_07.equals(vo.getStatus())){
			priorityOrdinaryListByStatus(ISystemConstant.DICTIONARY_ROW_STATUS_07,ISystemConstant.DICTIONARY_ROW_STATUS_02);
		}
	}
	/**
	 * 根据状态处理优先普通队列
	 * @param nowStatus 将要被取消的状态
	 * @param willStatus 候补的状态
	 */
	private void priorityOrdinaryListByStatus(String nowStatus,String willStatus) {
		Map<String,Object> variable = new HashMap<String,Object>();
		variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_01);
		if(nowStatus.equals(ISystemConstant.DICTIONARY_ROW_STATUS_03)){
			variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07));
		}else if(nowStatus.equals(ISystemConstant.DICTIONARY_ROW_STATUS_07)){
			variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02));
		}
		List<RowNumberVo> list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");
		if(CollectionUtils.isEmpty(list)){
			//没有优先的 查询普通的
			variable.put("rowType", ISystemConstant.DICTIONARY_ROW_TYPE_02);
			
			list = list(variable, "BUS_ROW_NUMBER.ROW_DATE asc");					
		}
		//改成当前入厂,则判断是否达到最大数量的
		if(ISystemConstant.DICTIONARY_ROW_STATUS_03.equals(nowStatus)){
			RowNumberVo vo = selectedOneEntrance(list, variable);
			if(vo!=null){
				String oldStatus = vo.getStatus();
				
				vo.setStatus(nowStatus);
				
				update(vo);
				//判断修改前是否为准备入厂
				if(ISystemConstant.DICTIONARY_ROW_STATUS_07.equals(oldStatus)){
					//补充准备入场车辆队列
					updateReadyFactory(vo, variable);
				}
			}
		}else{
			if(list!=null && list.size()>0){
				RowNumberVo vo = list.get(0);
				vo.setStatus(nowStatus);
				update(vo);
			}
		}
	}
}
