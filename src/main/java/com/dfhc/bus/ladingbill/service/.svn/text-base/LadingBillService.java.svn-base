/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LadingBillService.java
 *
 * 功能描述：  司机提货单服务
 * 
 * 版本历史：
 * 
 * 2017-01-05   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.ladingbill.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.acc.logisticscostsettlement.service.LogisticsCostSettlementService;
import com.dfhc.acc.logisticscostsettlement.vo.LogisticsCostSettlementVo;
import com.dfhc.acc.receiptconfletter.vo.ReceiptConfLetterVo;
import com.dfhc.base.customer.service.CustomerService;
import com.dfhc.base.customer.vo.CustomerVo;
import com.dfhc.base.price.service.PriceService;
import com.dfhc.base.product.service.ProductService;
import com.dfhc.base.product.vo.ProductVo;
import com.dfhc.base.producttype.service.ProductTypeService;
import com.dfhc.base.producttype.vo.ProductTypeVo;
import com.dfhc.base.trainstation.service.TrainStationService;
import com.dfhc.bus.cardistributionplan.service.CarDistributionPlanService;
import com.dfhc.bus.cardistributionplan.vo.CarDistributionPlanVo;
import com.dfhc.bus.carrequisitionplan.service.CarRequisitionPlanService;
import com.dfhc.bus.carrequisitionplan.vo.CarRequisitionPlanVo;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.dao.LadingBillDao;
import com.dfhc.bus.ladingbill.vo.DoLadingBillVo;
import com.dfhc.bus.ladingbill.vo.ILadingBillVo;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.loadingnotice.service.LoadingNoticeService;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.service.OrderProductDetailService;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.bus.productbatchnumber.service.ProductBatchNumberService;
import com.dfhc.bus.rownumber.service.RowNumberService;
import com.dfhc.bus.rownumber.vo.RowNumberVo;
import com.dfhc.bus.trainsrequisitionplan.service.TrainsRequisitionPlanService;
import com.dfhc.bus.trainsrequisitionplan.vo.TrainsRequisitionPlanVo;
import com.dfhc.bus.weighingbill.service.WeighingBillService;
import com.dfhc.bus.weighingbill.vo.WeighingBillVo;
import com.dfhc.pub.server.PushDataVo;
import com.dfhc.pub.server.PushQueue;
import com.dfhc.pub.server.SocketCacheVo;
import com.dfhc.pub.server.SocketQueue;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.IDCard;
import com.dfhc.util.PatternUtil;
import com.dfhc.util.PinyinUtil;
import com.dfhc.util.StringHelper;
import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;
/**
 * 司机提货单服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class LadingBillService implements ILadingBillConstants {

	@Autowired
	private  LoadingNoticeService  loadingNoticeService;
	@Autowired
	private  LogisticsCostSettlementService   logisticsCostSettlementService;
    @Autowired
    /**
     * 司机提货单数据访问对象
     */
    private LadingBillDao ladingBillDao;
    
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private ProductService productService;
    
    @Autowired
    private RowNumberService rowNumberService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductDetailService orderProductDetailService;

    @Autowired
    private CarDistributionPlanService carDistributionPlanService;
    @Autowired
    private CarRequisitionPlanService  carRequisitionPlanService;
    @Autowired
    private  TrainsRequisitionPlanService  trainsRequisitionPlanService;
    
    @Autowired
    private  TrainStationService  trainStationService;
    
    @Autowired
    private PriceService  priceService;
    @Autowired
    private WeighingBillService  weighingBillService;  
    @Autowired
    private  ProductBatchNumberService  productBatchNumberService;
 
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(LadingBillVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = ladingBillDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(LadingBillVo[] vos) {
        String[] ids = ladingBillDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = ladingBillDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = ladingBillDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(LadingBillVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = ladingBillDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(LadingBillVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(LadingBillVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<LadingBillVo> lInsert = new ArrayList<LadingBillVo>();
        List<LadingBillVo> lUpdate = new ArrayList<LadingBillVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new LadingBillVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new LadingBillVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public LadingBillVo get(String id) {
        LadingBillVo vo = ladingBillDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = ladingBillDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<LadingBillVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<LadingBillVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<LadingBillVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<LadingBillVo> lResult = ladingBillDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<LadingBillVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<LadingBillVo> lResult = ladingBillDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(LadingBillVo vo) {
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
    private void verifyInsertVo(LadingBillVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<LadingBillVo> vos) {
        for(LadingBillVo vo:vos){
           verifyUpdateVo(vo);
        }
        ladingBillDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<LadingBillVo> vos) {
        for(LadingBillVo vo:vos){
           verifyInsertVo(vo);
        }
        ladingBillDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        LadingBillVo vo = ladingBillDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(ladingBillDao.update(vo)!=1){
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
    * 查询提货单vo
    * @param params
    * @return
    */
   public LadingBillVo getLadingBillVo(Map<String, Object> params, boolean flag){
	   
	   List<LadingBillVo> list = list(params, null, 1, -1, flag);
	 
	   return CollectionUtils.isEmpty(list) ? null : list.get(0);
   }
   /**
    * 查询提货单vo
    * @param params
    * @return
    */
   public LadingBillVo getLadingBillVo(Map<String, Object> params){
	   
	   List<LadingBillVo> list = list(params, null, 1, -1, false);
	   
	   return CollectionUtils.isEmpty(list) ? null : list.get(0);
   }

   /**
    * 收货确认函参照查询参数
    * @param searchPara
    * @param request
    */
	public void receiptConfLetterrReferenceParams(Map<String, Object> searchPara,
			HttpServletRequest request) {
		//客户id
		String customerId = request.getParameter("customerIdhou");
		
		if(StringHelper.isEmpty(customerId)){
			return;
		}
		//开始时间
		String settlementStartDate = request.getParameter("settlementStartDatehou");
		//结束时间
		String settlementEndDate = request.getParameter("settlementEndDatehou");

		searchPara.put("customerIdHou", customerId);
		
		if(!StringHelper.isEmpty(settlementStartDate)){
			
			searchPara.put("settlementStartDateHou", DateUtil.toSqlDate(DateUtil.parser8Date(settlementStartDate)));
		}
		if(!StringHelper.isEmpty(settlementEndDate)){
			
			searchPara.put("settlementEndDateHou", DateUtil.toSqlDate(DateUtil.parser8Date(settlementEndDate)));
		}
		
		searchPara.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
		
		searchPara.put("orderStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ORDER_STATUS_02, ISystemConstant.DICTIONARY_ORDER_STATUS_04));
		
		
	}
	/**
	 * 修改提货单结算id
	 * @param list
	 * @param value 收货确认函id
	 * @return 返回计划发货总金额和实际发货总金额
	 */
	public void updateLadingBillVo(List<LadingBillVo> list, String value, Map<String, Object> variable){
		//实际发货总金额
		BigDecimal actualSettlementAmount = BigDecimal.ZERO;
		//计划发货总金额
//		BigDecimal totalAmount = BigDecimal.ZERO;
		for (LadingBillVo ladingBillVo : list) {
			
			ladingBillVo.setInvoiceSettlementId(value);
			
			actualSettlementAmount = actualSettlementAmount.add(ladingBillVo.getActualShippingAmount());
			
//			totalAmount = totalAmount.add(ladingBillVo.getPlannedShipmentAmount());
		}
		variable.put("actualSettlementAmount", actualSettlementAmount);
//		variable.put("totalAmount", totalAmount);
		
	}
	
	/**
	 * 修改提货单运费结算id
	 * @param list
	 * @param value 收货确认函id
	 * @return 返回计划发货总金额和实际发货总金额
	 */
	public void updateLadingBillVoLu(List<LadingBillVo> list, String value, LogisticsCostSettlementVo  vo){
		//计价总里程==实际计价里程累加
		BigDecimal  totalValuationMileage = BigDecimal.ZERO;
		//总吨数  ==实际发货重量累加
		BigDecimal  totalQuantity=BigDecimal.ZERO;
		//产品总费用== （实际发货重量*产品单价）累加
		BigDecimal  productTotalCost= BigDecimal.ZERO;
		//应付款金额==运费累加
		BigDecimal totalAmount = BigDecimal.ZERO;
		//结算开始时间
		java.sql.Date settlementStartDate=null;
		//结算结束时间
		java.sql.Date settlementEndDate=null;

		for (LadingBillVo ladingBillVo : list) {
			//提货单的运费结算单
			ladingBillVo.setFreightSettlementId(value);
			//提货单的运费
			ladingBillVo.setFreight((ladingBillVo.getActualShippingWeight()==null?BigDecimal.ZERO:ladingBillVo.getActualShippingWeight()).multiply(ladingBillVo.getSettlementFreight()==null?BigDecimal.ZERO:ladingBillVo.getSettlementFreight()));
			
			totalValuationMileage=totalValuationMileage.add(ladingBillVo.getFactValuationMileage()==null?BigDecimal.ZERO:ladingBillVo.getFactValuationMileage());
			
			totalQuantity=totalQuantity.add(ladingBillVo.getActualShippingWeight()==null?BigDecimal.ZERO:ladingBillVo.getActualShippingWeight());
			
			productTotalCost=productTotalCost.add((ladingBillVo.getProductUnitPrice()==null?BigDecimal.ZERO:ladingBillVo.getProductUnitPrice()).multiply(ladingBillVo.getActualShippingWeight()==null?BigDecimal.ZERO:ladingBillVo.getActualShippingWeight()));

			totalAmount =totalAmount.add(ladingBillVo.getFreight()==null?BigDecimal.ZERO:ladingBillVo.getFreight());
			
		}
		//数量合计吨
		vo.setTotalQuantity(totalQuantity);
		//计价总里程
		vo.setTotalValuationMileage(totalValuationMileage.setScale(2, BigDecimal.ROUND_HALF_UP));
		//产品总费用
		vo.setProductTotalCost(productTotalCost.setScale(2, BigDecimal.ROUND_HALF_UP));
		//应付款金额
		vo.setAmountPayable(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		////应付款金额大写
		vo.setAmountPayableCapital(PinyinUtil.getChangeAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP)));

//		vo.setSettlementStartDate((java.sql.Date)variable.get("settlementStartDate"));
//		vo.setSettlementEndDate((java.sql.Date)variable.get("SettlementEndDate"));

	}
	/**
	 * 排除已生成收货确认函的提货单
	 * @param ladingBillId
	 * @param vo
	 */
	public List<String> excludeAlreadySettlement(String ladingBillId,
			ReceiptConfLetterVo vo, Map<String, Object> variable) {
		variable.put("invoiceSettlementId", vo.getId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		List<String> list = ladingBillDao.getIds(variable);
		
		List<String> list2 = Arrays.asList(ladingBillId.split(","));
		
		if(!CollectionUtils.isEmpty(list)){
			//排除已生成收货确认函的提货单
			list2.removeAll(list);
		}
		
		
		return list2;
		
		
		
	}

	/**
	 * 根据订单id, 产品id 查询提货单
	 * @param id 订单id
	 * @return
	 */
	public List<LadingBillVo> list(String id, String productId, String ladingBillId) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		variable.put("orderPlanId", id);
		variable.put("productId", productId);
		variable.put("id", ladingBillId);
		
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		return list(variable, "BUS_LADING_BILL.CREATE_TIME ASC");
		
	}
	public List<LadingBillVo> list(String id, String productId) {
		
		return list(id, productId, null);
	}

	/**
	 * 销售订单创建提货单  (可以直接审核通过)
	 * @param jqData
	 * @param request
	 * @param orderProductDetailVo
	 * @param result
	 */
	public void inserVoByOrderDetail(String jqData, HttpServletRequest request,
			OrderProductDetailVo orderProductDetailVo,
			Map<String, Object> result, OrderVo orderVo, String flag) {
		
		List<LadingBillVo> list = JSONObject.parseArray(jqData, LadingBillVo.class);
		
		
		//修改的提货单
		List<LadingBillVo> updateList = new ArrayList<LadingBillVo>();
		//新增的提货单
		List<LadingBillVo> insertList = new ArrayList<LadingBillVo>();
		
		
		//将更新的和新增的分开  验证输入的信息
		groupList(list, result, insertList, updateList);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return;
		}
		
		if((insertList.size() + orderProductDetailVo.getAssignedCarNumBer()) > orderProductDetailVo.getCarNum()){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "提货单数量大于车数量!");
			
			return ;
		}
		
		//用完map情况  避免影响后面
		result.clear();
		
		//验证 更新的状态
		if(!CollectionUtils.isEmpty(updateList)){
			
			checkedInfo(result, updateList, orderProductDetailVo.getProductId(), orderProductDetailVo.getAssignedCarNumBer()==null?0:orderProductDetailVo.getAssignedCarNumBer(), orderProductDetailVo.getOrderId());
			
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
				return;
			}
			
			
		}
		
		if(!CollectionUtils.isEmpty(insertList)){
			
			//批量补选提货单vo信息
			batchCompletionLadingBill(orderProductDetailVo, orderVo, insertList, request, flag);
			
			RmVoHelper.markCreateStamp(request, insertList);
			insertBatch(insertList);
			
//			if("audit".equals(flag)){
//				//审核完成  看是否有带取号的记录 有 取号 
//				//查询提货单对应的有效的排号表记录
//				doLadingBillAuditSign(result,insertList,request);
//			}
			
		}
		
		if(updateList.size() > 0){
			
			RmVoHelper.markModifyStamp(request, updateList);
			if("commit".equals(flag)){
				
				for (LadingBillVo vo : updateList) {
					vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
				}
			}else if("audit".equals(flag)){
				String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
				for (LadingBillVo vo : updateList) {
					
					//TODO 审核提货单状态已改
					if(isSign(result, vo.getId())){
						//审核完成  看是否有带取号的记录 有 取号 
						//查询提货单对应的有效的排号表记录
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						LadingBillVo ladingBillVo = get(vo.getId());
						vo.setProductName(ladingBillVo.getProductName());
						vo.setProductId(ladingBillVo.getProductId());
						rowNumberService.ladingBillAuditSign(result, vo, request);
						
						//触发socket推送
						PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						PushQueue.getInstance().put(cacheVo);
					}else{
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						
					}
					
				}
			}
			updateBatch(updateList);
			
			
		}
		
	}

	/**
	 * 批量批量不全提货单vo属性
	 * @param orderProductDetailVo
	 * @param orderVo
	 * @param insertList
	 * @param request
	 */
	private void batchCompletionLadingBill(
			OrderProductDetailVo orderProductDetailVo, OrderVo orderVo,
			List<LadingBillVo> insertList, HttpServletRequest request, String flag) {
		for (LadingBillVo ladingBillVo : insertList) {
			
			completionLadingBill(ladingBillVo, orderProductDetailVo, orderVo, request, flag);
		}
	}

	/**
	 * 不全提货单vo属性
	 * @param ladingBillVo
	 * @param orderProductDetailVo
	 * @param orderVo
	 * @param request
	 */
	private void completionLadingBill(LadingBillVo ladingBillVo,
			OrderProductDetailVo orderProductDetailVo, OrderVo orderVo,
			HttpServletRequest request, String flag) {
		ladingBillVo.setOrderPlanId(orderVo.getId());
		
		ladingBillVo.setOrderPlanCode(orderVo.getOrderNum());
		
		ProductVo productVo = productService.get(orderProductDetailVo.getProductId());
		Date date = new Date();
		//规则是单子名称汉语拼音首字母+产品汉语拼音首字母+日期+顺序号
		ladingBillVo.setLadingBillCode(pubParamService.getSequenceNumber("FCP_LADING_BILL_CODE",productTypeService.get(orderProductDetailVo.getProductTypeId()).getSpellBrevityCode(), productVo.getIndexCode(), date,date,date));
		ladingBillVo.setIsPound(productVo.getIsPound());
		ladingBillVo.setIsLoading(productVo.getIsLoading());
		
		ladingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_01);
		
		ladingBillVo.setShipMode(orderVo.getShipMode());
		
//		ladingBillVo.setShipModeId(orderVo.getShipModeId());
		
		ladingBillVo.setShipperCode(orderVo.getShipperCode());
		
		ladingBillVo.setShipperCode(orderVo.getShipperCode());
		
		ladingBillVo.setProductType(productTypeService.get(orderProductDetailVo.getProductTypeId()).getProductTypeName());
		
		ladingBillVo.setProductTypeId(orderProductDetailVo.getProductTypeId());
		
		ladingBillVo.setProductId(orderProductDetailVo.getProductId());
		
		ladingBillVo.setProductName(orderProductDetailVo.getProductName());
		
		if("commit".equals(flag)){
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
			
		}else if("audit".equals(flag)){
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
		}else{
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00);
		}
		
		ladingBillVo.setEstimatedLoadingTime(orderVo.getDeliveryStartDate());
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		ladingBillVo.setInputPerson(userVo.getName());
		
		ladingBillVo.setInputPersonId(Long.valueOf(userVo.getId()));
		
		ladingBillVo.setInputTime(DateUtil.toSqlDate(DateUtil.getNowDate()));

		ladingBillVo.setReceivingAddress(orderVo.getProvince() + orderVo.getCity() + orderVo.getAddress());
		
		ladingBillVo.setReceivingPerson(orderVo.getCustomerName());
		
		CustomerVo customer = customerService.getCustomerVo(String.valueOf(orderVo.getCustomerId()));
		
		ladingBillVo.setReceivingPersonPhone(customer.getTel());
		
		ladingBillVo.setProductUnitPrice(orderProductDetailVo.getUnitPrice());
		
		ladingBillVo.setPlannedShipmentAmount(ladingBillVo.getNuclearLoad().multiply(ladingBillVo.getProductUnitPrice().setScale(4, BigDecimal.ROUND_HALF_EVEN)));
		
		ladingBillVo.setRemark(StringHelper.isEmpty(orderVo.getRemark())? ladingBillVo.getRemark() : orderVo.getRemark() + ladingBillVo.getRemark());
		orderProductDetailVo.setAssignedCarNumBer((orderProductDetailVo.getAssignedCarNumBer()==null?0:orderProductDetailVo.getAssignedCarNumBer())+1);
	}

	/**
	 * 验证更新的提货单
	 * @param result
	 * @param updateList 提货单id集合 
	 * @param productId 产品id
	 * @param assignedCarNumBer
	 * @param orderId
	 */
	private void checkedInfo(Map<String, Object> result,
			List<LadingBillVo> updateList, String productId, Long assignedCarNumBer, String orderId) {
		
		if(null != assignedCarNumBer && assignedCarNumBer != updateList.size()){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			result.put(ISystemConstant.AJAX_MESSAGE, "提货单数量大于已分配车数量！");
			
			return;
		}
		
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		result.put("orderPlanId", orderId);
		result.put("productId", productId);
//		List<LadingBillVo> list = new ArrayList<LadingBillVo>();
		for (LadingBillVo ladingBillVo : updateList) {
			
			result.put("id", ladingBillVo.getId());
			
			LadingBillVo vo = getLadingBillVo(result);
			
			if(null == vo){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				
				result.put(ISystemConstant.AJAX_MESSAGE, "在产品下未找到提货单！");
				
				break;
			}
			//03-08 修改   在之前分开的时候就移除了
//			if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00.equals(vo.getLadingBillStatus())){
//				list.add(vo);
//			}
		}
		//移除掉不能更新的 
//		updateList.removeAll(list);
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return ;
		}
		
		
		
	}

	/**
	 * 将页面返回的提货单才分成新增的和更新的 并返回结果   更新过滤未提交的了
	 * @param list
	 * @param result
	 * @return
	 */
	//TODO 2017-01-20 已修改  不在限制车牌号 手机号 身份证号的唯一
	private void groupList(List<LadingBillVo> list, Map<String, Object> result, List<LadingBillVo> insertList, List<LadingBillVo> updateList){
		
		for (LadingBillVo ladingBillVo : list) {
	        
			checkedInfo(result, ladingBillVo);
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
				break;
			}
			//验证车牌号 身份证 电话的唯一
//	        checkedPhoneNumberOnly(result, ladingBillVo.getPhoneNumber(), ladingBillVo.getId(), ladingBillVo.getLicensePlate(), ladingBillVo.getDriverIdCardNumber());
//	        if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
//				break;
//			}
	        
	        //将 车牌号 手机号  身份证号  放入map  如果不同 则map的size为list。size*3
//			result.put(ladingBillVo.getPhoneNumber(), ladingBillVo.getPhoneNumber());
//			result.put(ladingBillVo.getLicensePlate(), ladingBillVo.getLicensePlate());
//			result.put(ladingBillVo.getDriverIdCardNumber(), ladingBillVo.getDriverIdCardNumber());
//	        
	        if(ladingBillVo.getId().length() > 19){
				ladingBillVo.setId(null);
				insertList.add(ladingBillVo);
			}else{
				if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00.equals(ladingBillVo.getLadingBillStatus())
						&& !ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01.equals(ladingBillVo.getLadingBillStatus())){
					continue;
				}
				updateList.add(ladingBillVo);
			}
		}
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return;
		}
//		if((list.size()*3) != result.size()){
//			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
//			
//			result.put(ISystemConstant.AJAX_MESSAGE, "存在重复的车牌号或者身份证号或者司机电话!");
//		}
	}
	
	/**
	 * 验证提货单信息
	 * @param result
	 * @param vo
	 */
	private void checkedInfo(Map<String, Object> result, LadingBillVo vo){
		if(StringHelper.isEmpty(vo.getLicensePlate())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "请输入车牌号！");
			
			return ;
		}else{
			//验证车牌号
			if(!PatternUtil.checkedCarNum(vo.getLicensePlate())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "车牌号错误！");
				
				return ;
			}
		}
		
		if(StringHelper.isEmpty(vo.getDriverName())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "请输入司机姓名！");
			
			return ;
		}else{
			if(!PatternUtil.checkedName(vo.getDriverName())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "司机姓名错误！");
				
				return ;
			}
		}
		
		if(StringHelper.isEmpty(vo.getPhoneNumber())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "请输入司机手机号！");
			
			return ;
		}else{
			if(!PatternUtil.checkTelephone(vo.getPhoneNumber())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "司机手机号错误！");
				
				return ;
			}
		}
			
		if(StringHelper.isEmpty(vo.getDriverIdCardNumber())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "请输入司机身份证号！");
			
			return ;
		}else{
			try {
				result = IDCard.IDCardValidate(vo.getDriverIdCardNumber());
				if(null != result.get("errorInfo")){
					result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
					result.put(ISystemConstant.AJAX_MESSAGE, result.get("errorInfo"));
					
					return ;
				}
				
			} catch (ParseException e) {
				RmProjectHelper.logError("验证司机身份证号时出错！", e);
				
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "验证司机身份证号时出错！");
				
				return ;
			}
			
		}
		if(null == vo.getNuclearLoad()){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "请输入车辆核载量");
			
			return ;
		}else{
			if(!PatternUtil.checkedNumber(String.valueOf(vo.getNuclearLoad()))){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "车辆核载量错误！");
				
				return ;
			}
		}
	}

	/**
	 * 审核提货单
	 * @param request
	 * @param ladingBillId 提货单id
	 * @param auditStatus 审核状态  0审核不通过 1审核通过
	 * @param invalidReason 审核不通过的作废原因
	 * @return
	 */
	public Map<String, Object> updateAuditLadingBill(
			HttpServletRequest request, String ladingBillId, String auditStatus, String invalidReason) {
		Map<String, Object> result = new HashMap<>();
		
		LadingBillVo vo = get(ladingBillId);
		//验证提货单信息
		checkedInfo(vo, result, auditStatus, invalidReason);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			
			return result;
		}
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(auditStatus)){//审核不通过
			vo.setInvalidReason(invalidReason);
			
			vo.setInvalidPerson(userVo.getName());
			
			vo.setInvalidPersonId(Long.valueOf(userVo.getId()));
			
			vo.setInvalidTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
			
			vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09);
			
		}else if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(auditStatus)){//审核通过
			
			vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
			
			vo.setAuditPerson(userVo.getName());
			
			vo.setAuditPersonId(Long.valueOf(userVo.getId()));
			
			vo.setAuditTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
			
			//审核完成  看是否有带取号的记录 有 取号 
			//查询提货单对应的有效的排号表记录
			rowNumberService.ladingBillAuditSign(result, vo, request);
			RmVoHelper.markModifyStamp(request, vo);
			update(vo);
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			result.put(ISystemConstant.AJAX_MESSAGE, "审核通过！");
		}else{
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有审核标识！");
		}
		
		return result;
	}

	/**
	 * 验证提货单信息
	 * @param vo
	 * @param result
	 */
	private void checkedInfo(LadingBillVo vo, Map<String, Object> result, String auditStatus, String invalidReason) {
		if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(auditStatus)){//审核不通过
			if(StringHelper.isEmpty(invalidReason)){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "作废请填写作废原因！");
				
				return;
			}
		}
		
		
		if(!ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(vo.getDelete_flag()) && !StringHelper.isEmpty(vo.getDelete_flag())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "提货单已被删除！");
			
			return;
			
		}
		
		if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01.equals(vo.getLadingBillStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "提货单状态不是待审核！");
			
			return;
		}
		
		
	}

	/**
	 * 删除提货单  修改订单明细
	 * @param request
	 * @param ladingBillId
	 * @param orderDetailId
	 * @return
	 */
	public Map<String, Object> deleteLadingBill(HttpServletRequest request,
			String ladingBillId, String orderDetailId) {
		LadingBillVo ladingBillVo = get(ladingBillId);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00.equals(ladingBillVo.getLadingBillStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "不是未提交，不能删除！");
			
			return result;
		}
		
		OrderVo orderVo = orderService.get(ladingBillVo.getOrderPlanId());
		
		if(!ISystemConstant.DICTIONARY_ORDER_STATUS_02.equals(orderVo.getStatus())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "订单状态不是已审核，不能删除！");
			
			return result;
		}
		
		result.put("id", orderDetailId);
		result.put("orderId", orderVo.getId());
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		OrderProductDetailVo vo = orderProductDetailService.getOrderProductDetailVo(result);
		
		result.clear();
		
		if(null == vo){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "未找到对应的订单明细！订单编号：" + orderVo.getOrderNum() + "， 明细id ： " + orderDetailId);
			
			return result;
		}
		
		RmVoHelper.markModifyStamp(request, ladingBillVo);
		
		ladingBillVo.setDelete_flag(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
		
		RmVoHelper.markModifyStamp(request, vo);
		vo.setAssignedCarNumBer(vo.getAssignedCarNumBer()-1);
		
		update(ladingBillVo);
		
		orderProductDetailService.update(vo);
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "删除成功！");
		
		return result;
	}
	/**
	 * 验证车牌号、身份证、电话 的唯一性(作废  车牌号可以对应多个提货单)
	 * @param variable
	 * @param phoneNumber 电话
	 * @param ladingBillId 提货单号 可以为空
	 * @param licensePlate 车牌号
	 * @param driverIdCardNumber 身份证号
	 */
	public void checkedPhoneNumberOnly(Map<String, Object> variable, String phoneNumber, String ladingBillId, String licensePlate, String driverIdCardNumber){
//		variable.put("phoneNumber", phoneNumber);
//		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		variable.put("notStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09));
//		
//		
//		LadingBillVo vo = getLadingBillVo(variable);
//		
//		if(vo != null){
//			if(!vo.getId().equals(ladingBillId)){
//				
//				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
//				variable.put(ISystemConstant.AJAX_MESSAGE, "已经存在该电话！");
//				return;
//			}
//		}
//		variable.clear();
//		variable.put("driverIdCardNumber", driverIdCardNumber);
//		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		variable.put("notStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09));
//		
//		
//		vo = getLadingBillVo(variable);
//		
//		if(vo != null){
//			if(!vo.getId().equals(ladingBillId)){
//				
//				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
//				variable.put(ISystemConstant.AJAX_MESSAGE, "已经存在该身份证号！");
//				return;
//			}
//		}
//		variable.clear();
//		
//		variable.put("licensePlate", licensePlate);
//		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		variable.put("notStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09));
//		
//		vo = getLadingBillVo(variable);
//		variable.clear();
//		if(vo != null){
//			if(!vo.getId().equals(ladingBillId)){
//				
//				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
//				variable.put(ISystemConstant.AJAX_MESSAGE, "已经存在该车牌号！");
//			}
//		}
	}

	/**
	 * 审核通过 、作废提货单
	 * @param request
	 * @param ladingBillId
	 * @param operateFlag
	 * @return
	 */
	public Map<String, Object> updateLadingBill(HttpServletRequest request,
			String ladingBillId, String operateFlag) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		if(StringHelper.isEmpty(ladingBillId)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			variable.put(ISystemConstant.AJAX_MESSAGE, "提货单id为空！ ");
		
			return variable;
		}
		
		variable.put("id", ladingBillId);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		LadingBillVo vo = getLadingBillVo(variable);
		
		variable.clear();
		
		checkedInfo(vo, request, variable, operateFlag);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
			
			return variable;
		}
		
		//作废
		if("invaild".equals(operateFlag)){
			String invalidReason = request.getParameter("invalidReason");
			
			invaildUpdateVo(request, vo, invalidReason, variable);
			
		}else if("audit".equals(operateFlag)){//审核通过
			
			auditPassUpdateVo(request, vo);
			//审核完成  看是否有带取号的记录 有 取号 
			//查询提货单对应的有效的排号表记录
			rowNumberService.ladingBillAuditSign(variable, vo, request);
		}
		RmVoHelper.markModifyStamp(request, vo);
		
		update(vo);
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		
		variable.put(ISystemConstant.AJAX_MESSAGE, "操作成功！ ");
	
		return variable;
	}
	
	private void checkedInfo(LadingBillVo vo, HttpServletRequest request,
			Map<String, Object> variable, String operateFlag) {
		if(StringHelper.isEmpty(operateFlag)){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			variable.put(ISystemConstant.AJAX_MESSAGE, "操作表示符为空，请刷新页面！ ");
		
			return ;
		}
		
		if(null == vo){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			variable.put(ISystemConstant.AJAX_MESSAGE, "未找到提货单。");
		
			return ;
		}
		
		if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01.equals(vo.getLadingBillStatus())){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			variable.put(ISystemConstant.AJAX_MESSAGE, "提货单状态不为待审核。 ");
		
			return ;
		}
		if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(vo.getBillType())){//销售订单 判断过期
			
			OrderVo orderVo = orderService.get(vo.getOrderPlanId());
			
			if(DateUtil.toSqlDate(DateUtil.getNowDate()).compareTo(orderVo.getDeliveryEndDate()) == 1){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				
				variable.put(ISystemConstant.AJAX_MESSAGE, "该订单已过期。 ");
			
				return ;
			}
		}
		
		//作废
		if("invaild".equals(operateFlag)){
			if(StringHelper.isEmpty(request.getParameter("invalidReason"))){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				
				variable.put(ISystemConstant.AJAX_MESSAGE, "作废原因不能是空的。 ");
			
				return ;
			}
			if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08.equals(vo.getLadingBillStatus())){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				
				variable.put(ISystemConstant.AJAX_MESSAGE, "该提货单已正常出厂，不能作废！ ");
			
				return ;
			}
			
			
		}
	}

	private void auditPassUpdateVo(HttpServletRequest request, LadingBillVo vo) {
		vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		
		vo.setAuditPerson(userVo.getName());
		
		vo.setAuditPersonId(Long.valueOf(userVo.getId()));
		
		vo.setAuditTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		
	}

	/**
	 * 作废修改vo属性
	 * @param request
	 * @param vo
	 */
	private void invaildUpdateVo(HttpServletRequest request, LadingBillVo vo, String invalidReason, Map<String, Object> variable) {
		
		
		vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09);
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		
		vo.setInvalidPerson(userVo.getName());
		
		vo.setInvalidPersonId(Long.valueOf(userVo.getId()));
		
		vo.setInvalidReason(invalidReason);
		
		vo.setInvalidTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		variable.put("orderId", vo.getOrderPlanId());
		variable.put("productId", vo.getProductId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		OrderProductDetailVo orderProductDetailVo = orderProductDetailService.getOrderProductDetailVo(variable);
		variable.clear();
		orderProductDetailVo.setAssignedCarNumBer(orderProductDetailVo.getAssignedCarNumBer()-1);
		orderProductDetailService.update(orderProductDetailVo);
	}
	
	
	
	
	
	
	
	/**
	 * 汽配创建提货单
	 * @param  jqData   提货单的json
	 * @param  request
	 * @param  doLadingBillVo  创建提货单的中间vo
	 * @author    dfhc-luy
	 */
	public void inserZhuToLadingBillList(String jqData, HttpServletRequest request,
			DoLadingBillVo  doLadingBillVo,Map<String, Object> result) {
		List<LadingBillVo> ladingBillList = JSONObject.parseArray(jqData, LadingBillVo.class);
		
		//修改的提货单
		List<LadingBillVo> updateList = new ArrayList<LadingBillVo>();
		//新增的提货单
		List<LadingBillVo> insertList = new ArrayList<LadingBillVo>();
		
		//将更新的和新增的分开  验证输入的信息
		groupList(ladingBillList, result, insertList, updateList);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return;
		}
		
		if((insertList.size() + updateList.size())== 0){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有要更新的数据!不用提交");
			
			return ;
		}

		//用完map情况  避免影响后面
		result.clear();

		//验证 更新的状态
		if(!CollectionUtils.isEmpty(updateList)){
			
			checkedInfo(result, updateList, doLadingBillVo.getProductId(),null,doLadingBillVo.getOrderPlanId());
			
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
				return;
			}
		}
		
		if(!CollectionUtils.isEmpty(insertList)){
			
			//批量补选提货单vo信息
			doZhuBatchCompletionLadingBill(doLadingBillVo,insertList, request);
			
			RmVoHelper.markCreateStamp(request, insertList);
			insertBatch(insertList);

//			if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(doLadingBillVo.getLadingBillStatus())){
//				//审核完成  看是否有带取号的记录 有 取号 
//				//查询提货单对应的有效的排号表记录
//				doLadingBillAuditSign(result,insertList,request);
//			}

			//回填剩余车数
			updateCarNumBySubtraction(request, insertList);
			
		}
		
		if(updateList.size() > 0){
			RmVoHelper.markModifyStamp(request, updateList);

			String status=doLadingBillVo.getLadingBillStatus();
			if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01.equals(status)){
				//提交
				for (LadingBillVo vo : updateList) {
					vo.setLadingBillStatus(status);
				}
			}else if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(status)){
				String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
				for (LadingBillVo vo : updateList) {
					
					//TODO 审核提货单状态已改
					if(isSign(result, vo.getId())){
						//审核完成  看是否有带取号的记录 有 取号 
						//查询提货单对应的有效的排号表记录
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						LadingBillVo ladingBillVo = get(vo.getId());
						vo.setProductName(ladingBillVo.getProductName());
						vo.setProductId(ladingBillVo.getProductId());
						rowNumberService.ladingBillAuditSign(result, vo, request);
						
						//触发socket推送
						PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);						
						PushQueue.getInstance().put(cacheVo);
					}else{
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						
					}
					
				}
				
			}
			updateBatch(updateList);
		}
		
	}

	/**
	 * 主产品添加页面 创建提货单
	 * @param  jqData   提货单的json
	 * @param  request
	 * @param  doLadingBillVo  创建提货单的中间vo
	 * @author    dfhc-luy
	 */
	public void inserZhuAddLadingBillList(String jqData, HttpServletRequest request,
			DoLadingBillVo  doLadingBillVo,Map<String, Object> result,CarDistributionPlanVo oldVo) {
		List<LadingBillVo> ladingBillList = JSONObject.parseArray(jqData, LadingBillVo.class);
		
		//修改的提货单
		List<LadingBillVo> updateList = new ArrayList<LadingBillVo>();
		//新增的提货单
		List<LadingBillVo> insertList = new ArrayList<LadingBillVo>();
		
		//将更新的和新增的分开  验证输入的信息
		groupList(ladingBillList, result, insertList, updateList);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return;
		}
		
		if((insertList.size() + updateList.size())== 0){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "请分配车辆");
			
			return ;
		}
		
		//用完map情况  避免影响后面
		result.clear();

		if(!CollectionUtils.isEmpty(insertList)){
			
			//批量补选提货单vo信息
			doZhuBatchCompletionLadingBill(doLadingBillVo,insertList, request);
			
			RmVoHelper.markCreateStamp(request, insertList);
			insertBatch(insertList);

//			if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(doLadingBillVo.getLadingBillStatus())){
//				//审核完成  看是否有带取号的记录 有 取号 
//				//查询提货单对应的有效的排号表记录
//				doLadingBillAuditSign(result,insertList,request);
//			}
			
			oldVo.setRemainderCarNum(oldVo.getPlanCarNum()-insertList.size());
			long remainderNum=0;
			for(LadingBillVo  vo:insertList){
				remainderNum   +=vo.getNuclearLoad().longValue();
			}
			oldVo.setRemainderNum(oldVo.getNum()-remainderNum);
		}
		
	}

	
	/**
	 * 查询提货单是否签到
	 * @param variable
	 * @param ladingBillId
	 * @return
	 */
	public boolean isSign(Map<String, Object> variable, String ladingBillId){
		variable.clear();
		variable.put("ladingBillId", ladingBillId);
		variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_01);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		RowNumberVo rowNumberVo = rowNumberService.getRowNumberVo(variable);
		if(null == rowNumberVo){
			return false;
		}
		return true;
	}
	
	/**
	 * 批量补全提货单vo属性  可以共用
	 * @param doLadingBillVo 创建提货单中间vo
	 * @param  insertList 新插入提货单listVo
	 * @param  request 
	 * @author luy
	 */
	private void doZhuBatchCompletionLadingBill(DoLadingBillVo doLadingBillVo,
			List<LadingBillVo> insertList,HttpServletRequest request) {
		for (LadingBillVo ladingBillVo : insertList) {
			
			doZhuCompletionLadingBill(ladingBillVo, doLadingBillVo, request);
		}
	}

	/**
	 * 补全提货单vo属性    可以共用 
	 * @param ladingBillVo
	 * @param doLadingBillVo 创建提货单中间vo
	 * @param request
	 * @author dfhc-luy
	 */
	private void doZhuCompletionLadingBill(LadingBillVo ladingBillVo,DoLadingBillVo doLadingBillVo,HttpServletRequest request) {
		
		ladingBillVo.setOrderPlanId(doLadingBillVo.getOrderPlanId());
		
		ladingBillVo.setOrderPlanCode(doLadingBillVo.getOrderPlanCode());
		
		if(StringHelper.isEmpty(doLadingBillVo.getProductId())){
	           throw new PjException("没有对应的产品");
		}
		ProductVo productVo = productService.get(doLadingBillVo.getProductId());

		Date date = new Date();
		//规则是单子名称汉语拼音首字母+产品汉语拼音首字母+日期+顺序号
		ladingBillVo.setLadingBillCode(pubParamService.getSequenceNumber("FCP_LADING_BILL_CODE",productTypeService.get(productVo.getProductTypeId()).getSpellBrevityCode(),productVo.getIndexCode(), date,date,date));
		
		ladingBillVo.setIsPound(productVo.getIsPound());
		ladingBillVo.setIsLoading(productVo.getIsLoading());

		ladingBillVo.setBillType(doLadingBillVo.getBillType());
		
		ladingBillVo.setShipMode(doLadingBillVo.getShipMode());
		
		ladingBillVo.setShipModeCode(doLadingBillVo.getShipModeCode());
		
		ladingBillVo.setShipperCode(doLadingBillVo.getShipperCode());
		
		ladingBillVo.setProductType(productTypeService.get(productVo.getProductTypeId()).getProductTypeName());
		
		ladingBillVo.setProductTypeId(productVo.getProductTypeId());
		
		ladingBillVo.setProductId(doLadingBillVo.getProductId());
		
		ladingBillVo.setProductName(doLadingBillVo.getProductName());
		
		ladingBillVo.setLadingBillStatus(doLadingBillVo.getLadingBillStatus());
		
		ladingBillVo.setEstimatedLoadingTime(doLadingBillVo.getEstimatedLoadingTime());
		
		ladingBillVo.setInputPerson(doLadingBillVo.getInputPerson());
		
		ladingBillVo.setInputPersonId(doLadingBillVo.getInputPersonId());
		
		ladingBillVo.setInputTime(DateUtil.toSqlDate(DateUtil.getNowDate()));

		ladingBillVo.setReceivingAddress(doLadingBillVo.getReceivingAddress());
		
		ladingBillVo.setReceivingPerson(doLadingBillVo.getReceivingPerson());
		
		CustomerVo customer = customerService.getCustomerVo(doLadingBillVo.getCustomerId());
		
		ladingBillVo.setReceivingPersonPhone(customer.getTel());
		
		//主产品价格不用维护
		ladingBillVo.setProductUnitPrice(doLadingBillVo.getProductUnitPrice());
		//计划里程
		ladingBillVo.setPlannedMileage(doLadingBillVo.getPlannedMileage());
		//实际里程  初始化==计划里程
		ladingBillVo.setFactMileage(doLadingBillVo.getPlannedMileage());
		//计价里程  初始化 ==计划里程*距离系数
		ladingBillVo.setValuationMileage(doLadingBillVo.getPlannedMileage().multiply(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01)));
		//实际计价里程  初始化 ==计划里程*距离系数
		ladingBillVo.setFactValuationMileage(doLadingBillVo.getPlannedMileage().multiply(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01)));
		//结算运价  初始化  == 计价里程*运价系数
		ladingBillVo.setSettlementFreight(ladingBillVo.getValuationMileage().multiply(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02)));
		//运价系数 
		ladingBillVo.setYuanKmTon(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02));

		//计划发货金额
		ladingBillVo.setPlannedShipmentAmount((ladingBillVo.getNuclearLoad()==null?BigDecimal.ZERO:ladingBillVo.getNuclearLoad()).multiply((ladingBillVo.getProductUnitPrice()==null?BigDecimal.ZERO:ladingBillVo.getProductUnitPrice()).setScale(4, BigDecimal.ROUND_HALF_EVEN)));
		
		ladingBillVo.setRemark(ladingBillVo.getRemark());
	}

	/**
	 * 汽运调拨创建或更新提货单（可以直接审核通过）
	 * @param jqData
	 * @param request
	 * @param carRequisitionPlanVo
	 * @param result
	 * @param flag
	 */
	public void insertOrUpdateCarRequisition(String jqData,
			HttpServletRequest request,
			CarRequisitionPlanVo carRequisitionPlanVo,
			Map<String, Object> result, String flag) {
		List<LadingBillVo> list = JSONObject.parseArray(jqData, LadingBillVo.class);
		
		
		//修改的提货单
		List<LadingBillVo> updateList = new ArrayList<LadingBillVo>();
		//新增的提货单
		List<LadingBillVo> insertList = new ArrayList<LadingBillVo>();
		
		
		//将更新的和新增的分开  验证输入的信息
		groupList(list, result, insertList, updateList);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return;
		}
		
		if((insertList.size() + updateList.size()) ==0){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有要更新的数据!不用提交");
			return ;
		}
		
		if(insertList.size() > carRequisitionPlanVo.getRemainderCarNum() ){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "提货单数量大于计划车数");
			return ;
		}
		
		//用完map情况  避免影响后面
		result.clear();
		
		//验证 更新的状态
		if(!CollectionUtils.isEmpty(updateList)){
			
			checkedInfo(result, updateList, carRequisitionPlanVo.getProductId(), null, carRequisitionPlanVo.getId());
			
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
				return;
			}
			
			
		}
		
		if(!CollectionUtils.isEmpty(insertList)){
			
			//批量补选提货单vo信息
			batchCompletionLadingBill(carRequisitionPlanVo, insertList, request, flag);
			
			RmVoHelper.markCreateStamp(request, insertList);
			insertBatch(insertList);
			
//			if("audit".equals(flag)){
//				//审核完成  看是否有带取号的记录 有 取号 
//				//查询提货单对应的有效的排号表记录
//				doLadingBillAuditSign(result,insertList,request);
//			}
			
			//回填剩余车数
			updateCarNumBySubtraction(request, insertList);
			
		}
		
		if(updateList.size() > 0){
			
			RmVoHelper.markModifyStamp(request, updateList);
			if("commit".equals(flag)){
				
				for (LadingBillVo vo : updateList) {
					vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
				}
			}else if("audit".equals(flag)){
				String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
				for (LadingBillVo vo : updateList) {
					
					//TODO 审核提货单状态已改
					if(isSign(result, vo.getId())){
						//审核完成  看是否有带取号的记录 有 取号 
						//查询提货单对应的有效的排号表记录
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						LadingBillVo ladingBillVo = get(vo.getId());
						vo.setProductName(ladingBillVo.getProductName());
						vo.setProductId(ladingBillVo.getProductId());
						rowNumberService.ladingBillAuditSign(result, vo, request);
						
						//触发socket推送
						PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);						
						PushQueue.getInstance().put(cacheVo);
					}else{
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						
					}
					
				}
			}
			updateBatch(updateList);
		}
		
	}

	/**
	 * 汽运调拨单生成提货单
	 * @param carRequisitionPlanVo
	 * @param insertList
	 * @param request
	 * @param flag
	 */
	private void batchCompletionLadingBill(
			CarRequisitionPlanVo carRequisitionPlanVo,
			List<LadingBillVo> insertList, HttpServletRequest request,
			String flag) {
		for (LadingBillVo ladingBillVo : insertList) {
			
			completionLadingBill(ladingBillVo, carRequisitionPlanVo, request, flag);
		}
		
	}
	/**
	 * 铁运调拨单生成提货单
	 * @param carRequisitionPlanVo
	 * @param insertList
	 * @param request
	 * @param flag
	 */
	private void batchCompletionLadingBill(
			TrainsRequisitionPlanVo trainsRequisitionPlanVo,
			List<LadingBillVo> insertList, HttpServletRequest request,
			String flag) {
		for (LadingBillVo ladingBillVo : insertList) {
			
			completionLadingBill(ladingBillVo, trainsRequisitionPlanVo, request, flag);
		}
		
	}
	/**
	 * 汽运调拨单生成提货单
     */
	private void completionLadingBill(LadingBillVo ladingBillVo,
			CarRequisitionPlanVo carRequisitionPlanVo,
			HttpServletRequest request, String flag) {
		ladingBillVo.setOrderPlanId(carRequisitionPlanVo.getId());
		
		ladingBillVo.setOrderPlanCode(carRequisitionPlanVo.getPlanCode());
		
		ProductVo productVo = productService.get(carRequisitionPlanVo.getProductId());
		ProductTypeVo productTypeVo = productTypeService.get(productVo.getProductTypeId());
		Date date = new Date();
		//规则是单子名称汉语拼音首字母+产品汉语拼音首字母+日期+顺序号
		ladingBillVo.setLadingBillCode(pubParamService.getSequenceNumber("FCP_LADING_BILL_CODE",productTypeVo.getSpellBrevityCode(), productVo.getIndexCode(), date,date,date));
		
		ladingBillVo.setIsLoading(productVo.getIsLoading());
		ladingBillVo.setIsPound(productVo.getIsPound());
		
		ladingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_03);
		
		ladingBillVo.setShipMode(carRequisitionPlanVo.getShipMode());
		
		ladingBillVo.setShipModeCode(carRequisitionPlanVo.getShipModeCode());
		
		
		ladingBillVo.setShipperCode(carRequisitionPlanVo.getShipperCode());
		
		ladingBillVo.setProductType(productTypeVo.getProductTypeName());
		
		ladingBillVo.setProductTypeId(productTypeVo.getId());
		
		ladingBillVo.setProductId(carRequisitionPlanVo.getProductId());
		
		ladingBillVo.setProductName(productVo.getProductName());
		
		if("commit".equals(flag)){
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
			
		}else if("audit".equals(flag)){
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
		}else{
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00);
		}
		
		ladingBillVo.setEstimatedLoadingTime(carRequisitionPlanVo.getSendCarDate());
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		ladingBillVo.setInputPerson(userVo.getName());
		
		ladingBillVo.setInputPersonId(Long.valueOf(userVo.getId()));
		
		ladingBillVo.setInputTime(DateUtil.toSqlDate(DateUtil.getNowDate()));

//		ladingBillVo.setReceivingAddress(orderVo.getProvince() + orderVo.getCity() + orderVo.getAddress());
		
//		ladingBillVo.setReceivingPerson(orderVo.getCustomerName());
		
//		CustomerVo customer = customerService.getCustomerVo(String.valueOf(orderVo.getCustomerId()));
		
//		ladingBillVo.setReceivingPersonPhone(customer.getTel());
		
		ladingBillVo.setProductUnitPrice(carRequisitionPlanVo.getUnitPrice());
		
		ladingBillVo.setPlannedShipmentAmount(ladingBillVo.getNuclearLoad().multiply(ladingBillVo.getProductUnitPrice().setScale(4, BigDecimal.ROUND_HALF_EVEN)));
		
		ladingBillVo.setRemark(StringHelper.isEmpty(carRequisitionPlanVo.getRemark())? ladingBillVo.getRemark() : carRequisitionPlanVo.getRemark() + ladingBillVo.getRemark());
		
	}
	/**
	 * 铁运单生成提货单
     */
	private void completionLadingBill(LadingBillVo ladingBillVo,
			TrainsRequisitionPlanVo trainsRequisitionPlanVo,
			HttpServletRequest request, String flag) {
		ladingBillVo.setOrderPlanId(trainsRequisitionPlanVo.getId());
		
		ladingBillVo.setOrderPlanCode(trainsRequisitionPlanVo.getPlanCode());
		
		ProductVo productVo = productService.get(trainsRequisitionPlanVo.getProductId());
		ProductTypeVo productTypeVo = productTypeService.get(productVo.getProductTypeId());
		Date date = new Date();
		//规则是单子名称汉语拼音首字母+产品汉语拼音首字母+日期+顺序号
		ladingBillVo.setLadingBillCode(pubParamService.getSequenceNumber("FCP_LADING_BILL_CODE",productTypeVo.getSpellBrevityCode(),productVo.getIndexCode(), date,date,date));

		ladingBillVo.setIsPound(productVo.getIsPound());
		ladingBillVo.setIsLoading(productVo.getIsLoading());

		ladingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_04);
		
		ladingBillVo.setShipMode(trainsRequisitionPlanVo.getShipMode());
		
		ladingBillVo.setShipModeCode(trainsRequisitionPlanVo.getShipModeCode());
		
		
		ladingBillVo.setShipperCode(trainsRequisitionPlanVo.getShipperCode());
		
		ladingBillVo.setProductType(productTypeVo.getProductTypeName());
		
		ladingBillVo.setProductTypeId(productTypeVo.getId());
		
		ladingBillVo.setProductId(trainsRequisitionPlanVo.getProductId());
		
		ladingBillVo.setProductName(productVo.getProductName());
		
		if("commit".equals(flag)){
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
			
		}else if("audit".equals(flag)){
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
		}else{
			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00);
		}
		
		ladingBillVo.setEstimatedLoadingTime(trainsRequisitionPlanVo.getSendCarDate());
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		ladingBillVo.setInputPerson(userVo.getName());
		
		ladingBillVo.setInputPersonId(Long.valueOf(userVo.getId()));
		
		ladingBillVo.setInputTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		ladingBillVo.setReceivingAddress(trainStationService.get(trainsRequisitionPlanVo.getStationId()).getStationName());
		
		ladingBillVo.setReceivingPerson(trainsRequisitionPlanVo.getReceivingParty());
		
//		CustomerVo customer = customerService.getCustomerVo(String.valueOf(orderVo.getCustomerId()));
		
//		ladingBillVo.setReceivingPersonPhone(customer.getTel());
		
		ladingBillVo.setProductUnitPrice(trainsRequisitionPlanVo.getUnitPrice());
		
		ladingBillVo.setPlannedShipmentAmount(ladingBillVo.getNuclearLoad().multiply(ladingBillVo.getProductUnitPrice().setScale(4, BigDecimal.ROUND_HALF_EVEN)));
		
		ladingBillVo.setRemark(StringHelper.isEmpty(trainsRequisitionPlanVo.getRemark())? ladingBillVo.getRemark() : trainsRequisitionPlanVo.getRemark() + ladingBillVo.getRemark());
		
	}

	/**
     * 铁运调拨生成提货单
     */
	public void insertOrUpdateTrainRequisition(String jqData,
			HttpServletRequest request,
			TrainsRequisitionPlanVo trainsRequisitionPlanVo,
			Map<String, Object> result, String flag) {
		List<LadingBillVo> list = JSONObject.parseArray(jqData, LadingBillVo.class);
		
		
		//修改的提货单
		List<LadingBillVo> updateList = new ArrayList<LadingBillVo>();
		//新增的提货单
		List<LadingBillVo> insertList = new ArrayList<LadingBillVo>();
		
		//将更新的和新增的分开  验证输入的信息
		groupList(list, result, insertList, updateList);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return;
		}
		
		
		if((insertList.size() + updateList.size()) ==0){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有要更新的数据!不用提交");
			return ;
		}
		
		if(insertList.size() > trainsRequisitionPlanVo.getRemainderCarNum() ){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "提货单数量大于计划车数");
			return ;
		}
		
		//用完map情况  避免影响后面
		result.clear();
		
		//验证 更新的状态
		if(!CollectionUtils.isEmpty(updateList)){
			
			checkedInfo(result, updateList, trainsRequisitionPlanVo.getProductId(), null, trainsRequisitionPlanVo.getId());
			
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
				return;
			}
		}
		
		if(!CollectionUtils.isEmpty(insertList)){
			if(insertList.size()>trainsRequisitionPlanVo.getRemainderCarNum()){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "新分配的车数已经大于剩余车数");
				return ;
			}
			
			//批量补选提货单vo信息
			batchCompletionLadingBill(trainsRequisitionPlanVo, insertList, request, flag);
			
			RmVoHelper.markCreateStamp(request, insertList);
			insertBatch(insertList);
			
//			if("audit".equals(flag)){
//				//审核完成  看是否有带取号的记录 有 取号 
//				//查询提货单对应的有效的排号表记录
//				doLadingBillAuditSign(result,insertList,request);
//			}
			//回填剩余车数
			updateCarNumBySubtraction(request, insertList);

		}
		
		if(updateList.size() > 0){
			RmVoHelper.markModifyStamp(request, updateList);
			if("commit".equals(flag)){
				
				for (LadingBillVo vo : updateList) {
					vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
				}
			}else if("audit".equals(flag)){
				String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_01);
				for (LadingBillVo vo : updateList) {
					
					//TODO 审核提货单状态已改
					if(isSign(result, vo.getId())){
						//审核完成  看是否有带取号的记录 有 取号 
						//查询提货单对应的有效的排号表记录
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						LadingBillVo ladingBillVo = get(vo.getId());
						vo.setProductName(ladingBillVo.getProductName());
						vo.setProductId(ladingBillVo.getProductId());
						rowNumberService.ladingBillAuditSign(result, vo, request);
						
						//触发socket推送
						PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);						
						PushQueue.getInstance().put(cacheVo);
					}else{
						vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
						
					}
					
				}
			}
			updateBatch(updateList);
		}
		
	}
	
	
	/**
	 * 只删除提货单
	 * @param request
	 * @param ladingBillId
	 * @param orderDetailId
	 * @return
	 */
	public Map<String, Object> deleteLadingBills(HttpServletRequest request,String ladingBillId) {
		LadingBillVo ladingBillVo = get(ladingBillId);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00.equals(ladingBillVo.getLadingBillStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "不是未提交，不能删除！");
			
			return result;
		}
		
		delete(ladingBillId);
		//回填剩余车数
		updateCarNumByAdd(request, ladingBillVo);
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "删除成功！");
		
		return result;
	}


	/**
	 * 只作废提货单
	 * @param request
	 * @param ladingBillId
	 * @param orderDetailId
	 * @return
	 */
	public Map<String, Object> doInvalidLadingBills(HttpServletRequest request,String ladingBillId) {
		LadingBillVo ladingBillVo = get(ladingBillId);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(ladingBillVo.getLadingBillStatus())
				&&  !ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03.equals(ladingBillVo.getLadingBillStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "该提货单不能作废");
			return result;
		}
		
		ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09);
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		ladingBillVo.setInvalidPerson(userVo.getName());
		ladingBillVo.setInvalidPersonId(Long.valueOf(userVo.getId()));
		ladingBillVo.setInvalidTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		update(ladingBillVo);
		//回填剩余车数
		updateCarNumByAdd(request, ladingBillVo);
		//作废签号表
		rowNumberService.updateInvalidRowNumber(result,request, ladingBillVo.getId());
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "作废成功！");
		
		return result;
	}

	   
	   /**
	    * 验证 旧提货单
	    */
	   public void checkOldLadingBill(HttpServletRequest request,LadingBillVo oldLadingBillVo) {

		   if(oldLadingBillVo==null){
	           throw new PjException("找不到该提货单");
		   }
		   //单据类型
		   if(!ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(oldLadingBillVo.getBillType())){
	           throw new PjException("非汽运配送计划类型的提货单不能变更");
		   }

		   if(!(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01.equals(oldLadingBillVo.getLadingBillStatus())
				   ||ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02.equals(oldLadingBillVo.getLadingBillStatus()))){
			   throw  new PjException("非待审核的和非审核通过的提货单不能变更");
		   }
		   CarDistributionPlanVo   oldCarDistributionPlanVo=carDistributionPlanService.get(oldLadingBillVo.getOrderPlanId());
		   if(oldCarDistributionPlanVo==null){
	           throw new PjException("该提货单没有对应的汽运配送计划单");
		   }
		   //业务类型  
		   if(!ISystemConstant.DICTIONARY_PLAN_TYPE_00.equals(oldCarDistributionPlanVo.getBusinessType())){
	           throw new PjException("非汽运配送计划类型的提货单不能变更");
		   }
		   //计划状态
		   if(!ISystemConstant.DICTIONARY_PLAN_STATUS_02.equals(oldCarDistributionPlanVo.getStatus())){
	           throw new PjException("该提货单对应的汽运配送计划单状态非待分配车辆状态");
		   }
		   
		   return;
	   }
	

	    /**
	     * 变车管理       汽运配送给提货单重新赋值
	     * @param  request
	     * @param  planId  计划id
	     */
	    public LadingBillVo getNewLadingBillVoForD(HttpServletRequest request,LadingBillVo  ladingBillVo,String planId) {
		    //汽运配送计划
		    CarDistributionPlanVo  vo=carDistributionPlanService.get(planId);

		    ladingBillVo.setOrderPlanId(vo.getId());
			
			ladingBillVo.setOrderPlanCode(vo.getPlanCode());
			
			ladingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_02);
			
			ladingBillVo.setShipMode(vo.getShipMode());
			
			ladingBillVo.setShipModeCode(vo.getShipModeCode());
			
			ladingBillVo.setShipperCode(vo.getShipperCode());
			
			ProductVo productVo = productService.get(vo.getProductId());
			
			ladingBillVo.setIsLoading(productVo.getIsLoading());
			ladingBillVo.setIsPound(productVo.getIsPound());
			
			ladingBillVo.setProductType(productTypeService.get(productVo.getProductTypeId()).getProductTypeName());
			
			ladingBillVo.setProductTypeId(productVo.getProductTypeId());
			
			ladingBillVo.setProductId(vo.getProductId());
			
			ladingBillVo.setProductName(productVo.getProductName());
			
			ladingBillVo.setEstimatedLoadingTime(vo.getSendCarDate());
			
			//---
			ladingBillVo.setPlannedMileage(vo.getPlannedMileage());
			
			ladingBillVo.setFactMileage(vo.getPlannedMileage());
			
			ladingBillVo.setValuationMileage(vo.getPlannedMileage().multiply(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01)));
			
			ladingBillVo.setFactValuationMileage(vo.getPlannedMileage().multiply(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01)));
			
			ladingBillVo.setSettlementFreight(ladingBillVo.getValuationMileage().multiply(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02)));
			
			ladingBillVo.setYuanKmTon(pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02));
			
			ladingBillVo.setReceivingAddress(vo.getShippingAddress());
			
			ladingBillVo.setReceivingPerson(vo.getCustomerName());
			
			CustomerVo customer = customerService.getCustomerVo(vo.getCustomerId()+"");
			
			ladingBillVo.setReceivingPersonPhone(customer.getTel());
			
			ladingBillVo.setProductUnitPrice((priceService.getProductPrice(request, vo.getProductId())).getPrice());
			
			ladingBillVo.setPlannedShipmentAmount(ladingBillVo.getNuclearLoad().multiply(ladingBillVo.getProductUnitPrice().setScale(4, BigDecimal.ROUND_HALF_EVEN)));
			
			ladingBillVo.setRemark(StringHelper.isEmpty(vo.getRemark())? ladingBillVo.getRemark() : vo.getRemark() + ladingBillVo.getRemark());
			
			//修改新计划对应的剩余车数
			Map<String, Object>  variable=new HashMap<String, Object>();
			carDistributionPlanService.doTrainsRequisitionUpdateCarNum(variable, planId, FORMULA_SUBTRACTION, Integer.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_1), ladingBillVo.getNuclearLoad().intValue());
	 	   return ladingBillVo;
	    }
	   
	
	    /**
	     * 变车管理   汽运调拨给提货单重新赋值
	     * @param  request
	     * @param  planId  计划id
	     */
	    public LadingBillVo getNewLadingBillVoForR(HttpServletRequest request,LadingBillVo  ladingBillVo,String planId) {

		    
		    CarRequisitionPlanVo  vo=carRequisitionPlanService.get(planId);

		    ladingBillVo.setOrderPlanId(vo.getId());
			
			ladingBillVo.setOrderPlanCode(vo.getPlanCode());
			
			ladingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_03);
			
			ladingBillVo.setShipMode(vo.getShipMode());
			
			ladingBillVo.setShipModeCode(vo.getShipModeCode());
			
			ladingBillVo.setShipperCode(vo.getShipperCode());
			
			ProductVo productVo = productService.get(vo.getProductId());
			
			ladingBillVo.setIsLoading(productVo.getIsLoading());
			ladingBillVo.setIsPound(productVo.getIsPound());

			ladingBillVo.setProductType(productTypeService.get(productVo.getProductTypeId()).getProductTypeName());
			
			ladingBillVo.setProductTypeId(productVo.getProductTypeId());
			
			ladingBillVo.setProductId(vo.getProductId());
			
			ladingBillVo.setProductName(productVo.getProductName());
			
			ladingBillVo.setEstimatedLoadingTime(vo.getSendCarDate());
			
			ladingBillVo.setReceivingAddress(vo.getShippingAddress());
			
//			ladingBillVo.setReceivingPerson(vo.getCustomerName());
			
//			CustomerVo customer = customerService.getCustomerVo(vo.getCustomerId());
			
//			ladingBillVo.setReceivingPersonPhone(customer.getTel());
			
			ladingBillVo.setProductUnitPrice((priceService.getProductPrice(request, vo.getProductId())).getPrice());
			
			ladingBillVo.setPlannedShipmentAmount(ladingBillVo.getNuclearLoad().multiply(ladingBillVo.getProductUnitPrice().setScale(4, BigDecimal.ROUND_HALF_EVEN)));
			
			ladingBillVo.setRemark(StringHelper.isEmpty(vo.getRemark())? ladingBillVo.getRemark() : vo.getRemark() + ladingBillVo.getRemark());
		   
		   
			//修改新计划对应的剩余车数
			Map<String, Object>  variable=new HashMap<String, Object>();
			carRequisitionPlanService.doTrainsRequisitionUpdateCarNum(variable, planId, FORMULA_SUBTRACTION, Integer.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_1), ladingBillVo.getNuclearLoad().intValue());
	 	   return ladingBillVo;
	    }   
	    
	    /**
	     * 变车管理  铁路调拨给提货单重新赋值
	     * @param  request
	     * @param  planId  计划id
	     */
	    public LadingBillVo getNewLadingBillVoForT(HttpServletRequest request,LadingBillVo  ladingBillVo,String planId) {

		    
		    TrainsRequisitionPlanVo  vo=trainsRequisitionPlanService.get(planId);

		    ladingBillVo.setOrderPlanId(vo.getId());
			
			ladingBillVo.setOrderPlanCode(vo.getPlanCode());
			
			ladingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_04);
			
			ladingBillVo.setShipMode(vo.getShipMode());
			
			ladingBillVo.setShipModeCode(vo.getShipModeCode());
			
			ladingBillVo.setShipperCode(vo.getShipperCode());
			
			ProductVo productVo = productService.get(vo.getProductId());
			
			ladingBillVo.setIsLoading(productVo.getIsLoading());

			ladingBillVo.setIsPound(productVo.getIsPound());

			ladingBillVo.setProductType(productTypeService.get(productVo.getProductTypeId()).getProductTypeName());
			
			ladingBillVo.setProductTypeId(productVo.getProductTypeId());
			
			ladingBillVo.setProductId(vo.getProductId());
			
			ladingBillVo.setProductName(productVo.getProductName());
			
			ladingBillVo.setEstimatedLoadingTime(vo.getSendCarDate());
			
			ladingBillVo.setReceivingAddress(vo.getShippingAddress());
			
			ladingBillVo.setReceivingPerson(vo.getReceivingParty());
			
//			CustomerVo customer = customerService.getCustomerVo(vo.getCustomerId());
			
//			ladingBillVo.setReceivingPersonPhone(customer.getTel());
			
			ladingBillVo.setProductUnitPrice((priceService.getProductPrice(request, vo.getProductId())).getPrice());
			
			ladingBillVo.setPlannedShipmentAmount(ladingBillVo.getNuclearLoad().multiply(ladingBillVo.getProductUnitPrice().setScale(4, BigDecimal.ROUND_HALF_EVEN)));
			
			ladingBillVo.setRemark(StringHelper.isEmpty(vo.getRemark())? ladingBillVo.getRemark() : vo.getRemark() + ladingBillVo.getRemark());

			//修改新计划对应的剩余车数
			Map<String, Object>  variable=new HashMap<String, Object>();
			trainsRequisitionPlanService.doTrainsRequisitionUpdateCarNum(variable, planId, FORMULA_SUBTRACTION, Integer.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_1),ladingBillVo.getNuclearLoad().intValue());
		   
		   
	 	   return ladingBillVo;
	    }    

	    
	    

	    /***
	     * 关联提货单审核时  新插入数据时调用 （作废）
	     * */
	    public  void  doLadingBillAuditSign(Map<String, Object> result,List<LadingBillVo> insertList,HttpServletRequest  request){
			for (LadingBillVo vo : insertList) {
				//审核完成  看是否有带取号的记录 有 取号 
				//查询提货单对应的有效的排号表记录
				rowNumberService.ladingBillAuditSign(result, vo, request);
			}
	    }
	    
	    /**
	     * 提货单接口查询
	     * @param params
	     * @return
	     */
	    public List<ILadingBillVo> getLadingBillInterfaceData(Map<String, Object> params){
	    	
	    	return ladingBillDao.getLadingBillInterfaceData(params);
	    }

	    /**
		 * 出厂放行
		 * @param request
		 * @param result
		 * @param truckNumber 车牌号
		 * @return
		 */
		public Map<String, Object> leaveFacetory(HttpServletRequest request,
				Map<String, Object> result, String truckNumber) {
			
			LadingBillVo ladingBillVo = getInplantCar(result, truckNumber);
			result.clear();
			if(null == ladingBillVo){
				result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "根据车牌号未找到对应车辆！");
				result.put("operationStatus", "0");
				return result;
			}
			//增加提货单.状态=装货完成（06）add by longsebo 2017-03-25
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(ladingBillVo.getIsPound()) &&
				ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06.equals(ladingBillVo.getLadingBillStatus())){
				result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
				result.put("ladingBillId", ladingBillVo.getId());
				result.put("create_time", DateUtil.toSqlDate(DateUtil.getNowDate()));
				
				List<WeighingBillVo> list = weighingBillService.list(result, null, 1, -1, false);
				
				if(CollectionUtils.isEmpty(list)){
					result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
					result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "未找到对应的磅单！");
					result.put("operationStatus", "0");
					return result;
				}
				WeighingBillVo weighingBillVo = list.get(0);
				
				if(!ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS_02.equals(weighingBillVo.getStatus())){
					result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
					result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "过磅单状态不是二次过磅！");
					result.put("operationStatus", "0");
					return result;
				}
				weighingBillVo.setTimeToGoOut(DateUtil.toSqlDate(DateUtil.getNowDate()));
				weighingBillService.update(weighingBillVo);
			}
			
			if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07.equals(ladingBillVo.getLadingBillStatus())){
				
				ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_11);
			}else{
				
				ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
			}
			
			ladingBillVo.setOutTime(DateUtil.getNowTimestamp());
			
			result.put("ladingBillId", ladingBillVo.getId());
			result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			
			RowNumberVo rowNumberVo = rowNumberService.getRowNumberVo(result);
			rowNumberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_06);
			
			
			update(ladingBillVo);
			
			rowNumberService.update(rowNumberVo);
			
			
			
			
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
			result.put("operationStatus", "1");
			
			
			//修改队列
			rowNumberService.updateCarList(rowNumberVo, ISystemConstant.CAR_LEAVE_FACTORY);
			return result;
		}

		/**
		 * 查询已入场的提货单
		 * @param variable
		 * @param truckNumber
		 * @return
		 */
		public LadingBillVo getInplantCar(Map<String, Object> variable, String truckNumber){
			//转货完成或装货失败
			variable.put("ladingBillStatuses", "'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06+"','"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07+"'");
			variable.put("truckNo", truckNumber);
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			//variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
			variable.put("outGateFlag", "outGateFlag");
			
			LadingBillVo ladingBillVo = getLadingBillVo(variable);
			variable.clear();
			
			return ladingBillVo;
		}
		 /**
	     * 运费待结算的 
	     * @param searchPara
	     * @param request
	     */
	 	public void receiptConfLetterrReferenceParamsLu(Map<String, Object> searchPara,HttpServletRequest request) {
	 		String listWaitSettleLu = request.getParameter("listWaitSettleLu");
	 		
	 		if(StringHelper.isEmpty(listWaitSettleLu)){
	 			return;
	 		}
	 		
	 		//查询待结算的提货单
	 		if("listWaitSettleLu".equals(listWaitSettleLu)){
		 		//已出厂
		 		searchPara.put("ladingBillStatus",ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
		 		
		 		//运算结算单id为空
		 		searchPara.put("notFreightSettlementId","notFreightSettlementId");
		 		
		 		//联查榜单
	        	searchPara.put("toBeSettlementLu1", ISystemConstant.DICTIONARY_RM_YES_NOT_0);

	        	//如果是  单据类型是汽配   
	        	if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(request.getParameter("billType"))){
		        	//在  汽配类型下  执行 过滤 主产品汽配类型的
		        	searchPara.put("doCheckOrderLu", ISystemConstant.DICTIONARY_PLAN_TYPE_00);
	        	}

	 		}
	 		
	 	}

		/**
		 * 查询提货单并按客户分组
		 * @param variable
		 * @param ladingBillIds
		 * @return
		 */
		public Map<String, Object> getListAndGroupByCustomer(
				Map<String, Object> variable, String ladingBillIds) {
			variable.put("ids", ladingBillIds);
//			variable.put("toBeSettlement", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("toBeSettlement1", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
			variable.put("toBeSettlementOrder", "toBeSettlementOrder");
			List<LadingBillVo> list = list(variable, null);
			
			if(CollectionUtils.isEmpty(list)){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "未找到提货单！");
				return variable;
			}
			
			if(ladingBillIds.split(",").length != list.size()){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "找到的提货单数量与所选不一致！");
				
				return variable;
			}
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			for (LadingBillVo vo : list) {
				
				List<LadingBillVo> vos = (List<LadingBillVo>) result.get(vo.getCustomerId());
				
				if(!CollectionUtils.isEmpty(vos)){
					
					vos.add(vo);
					
				}else{
					
					vos = new ArrayList<LadingBillVo>();
					
					vos.add(vo);
					
					result.put(vo.getCustomerId(), vos);
					
				}
				
			}
			
			return result;
		}
		/**
	     * 根据发票结算id查询提货单
	     * @param id
	     * @return
	     */
	    public List<LadingBillVo> getList(String id){
	    	return ladingBillDao.getList(id);
	    }
	    /**
	     * 根据id查询打印提货单
	     * @param id
	     * @return
	     */
	    public LadingBillVo getListPrint(Map<String, Object> searchPara){
	    	LadingBillVo vo = ladingBillDao.getListPrint(searchPara);
	    	return vo;
	    }
	    
	    /**
	     * 过磅或装车成功后 回填数据
	     * @param ladingBillId 提货单id
	     * @param netWeight 净重
	     */
	    public void supplement(String ladingBillId, BigDecimal netWeight, Map<String, Object> variable, HttpServletRequest request){
	    	//回填提货单
			LadingBillVo ladingBillVo = get(ladingBillId);
			
			ladingBillVo.setActualShippingAmount(netWeight.multiply(ladingBillVo.getProductUnitPrice()).setScale(4, BigDecimal.ROUND_HALF_EVEN));
	    
			variable.put("ladingBill", "ladingBill");
			variable.put("orderId", ladingBillVo.getOrderPlanId());
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			
			OrderProductDetailVo orderProductDetailVo = orderProductDetailService.getOrderProductDetailVo(variable);
			
			long carNum = (null == orderProductDetailVo.getRemainderCarNum()) ? orderProductDetailVo.getCarNum()-1 : orderProductDetailVo.getRemainderCarNum()-1;
			
			if(carNum < 0){
				throw new PjException("数据错误， 剩余车数小于0！");
			}
			orderProductDetailVo.setRemainderCarNum(carNum);
	   
			BigDecimal num = (null == orderProductDetailVo.getAssignedCarNumBer()) ? orderProductDetailVo.getPlannedVolume().subtract(netWeight) : new BigDecimal(orderProductDetailVo.getAssignedCarNumBer()).subtract(netWeight);
			
			orderProductDetailVo.setRemarkVolume(num);
			
//			RmVoHelper.markModifyStamp(request, orderProductDetailVo);
			orderProductDetailService.update(orderProductDetailVo);
//			RmVoHelper.markModifyStamp(request, ladingBillVo);
			update(ladingBillVo);
	    }
	    
	    
		
		
		/**
		 * 查询提货单并按物流公司分组
		 * @param variable
		 * @param ladingBillIds
		 * @return
		 */
		public Map<String, Object> getListAndGroupByLogistics(
				Map<String, Object> variable, String ladingBillIds,String billTypeLu) {
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("ids", ladingBillIds);//提货单ids
			variable.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
			variable.put("toBeSettlementLu1", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			//联查计划单
			variable.put("toBeSettlementOrderLu", "toBeSettlementOrderLu");
			//区分查哪张表
			variable.put("billTypeLu",billTypeLu);
			
			List<LadingBillVo> list = list(variable, null);
			
			if(CollectionUtils.isEmpty(list)){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "未找到提货单！");
				return variable;
			}
			
			if(ladingBillIds.split(",").length != list.size()){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "找到的提货单数量与所选不一致！");
				
				return variable;
			}
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			for (LadingBillVo vo : list) {
				
				List<LadingBillVo> vos = (List<LadingBillVo>) result.get(vo.getLogisticsCompanyId());
				
				if(!CollectionUtils.isEmpty(vos)){
					
					vos.add(vo);
					
				}else{
					
					vos = new ArrayList<LadingBillVo>();
					
					vos.add(vo);
					
					result.put(vo.getLogisticsCompanyId(), vos);
					
				}
				
			}
			
			return result;
		}
		
		
		
	    /**
	     * 更新实际里程  以及相关字段
	     * 
	     * @param vo 用于更新的VO对象
	     * @return 成功更新的记录数
	     */
	    public Map<String,Object> updateBySettler(HttpServletRequest request, LadingBillVo vo) {
	        Map<String, Object> result = new HashMap<String, Object>();
	        LadingBillVo ladingBillVo = get(vo.getId());
	        
	        //距离系数
	        BigDecimal oneMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01);
	        //实际计价里程==实际里程*距离系数
	        vo.setFactValuationMileage(vo.getFactMileage().multiply(oneMileage));
	        //运价系数（一般为0.31）
	        String twoMileage=RmGlobalReference.get(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT, ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02);
	        if(StringHelper.isEmpty(twoMileage)){
	            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	            result.put(ISystemConstant.AJAX_MESSAGE,"请配置运价系数");
	            return result;
	        }

	        //结算运费 == 计价里程*运价系数（一般为0.31）
	        vo.setSettlementFreight(vo.getValuationMileage().multiply(new BigDecimal(twoMileage)));
	        //总运费 == 结算运价*数量
	        vo.setFreight(vo.getSettlementFreight().multiply(ladingBillVo.getActualShippingWeight()));
	        
	        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
	        update(vo);  //更新单条记录

	        result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
	        result.put("freightSettlementId",ladingBillVo.getFreightSettlementId());
	        List<LadingBillVo>  listLadingBillVos=list(result, null);
	        
	        LogisticsCostSettlementVo  logisticsCostSettlementVo=logisticsCostSettlementService.get(ladingBillVo.getFreightSettlementId());

	        updateLadingBillVoLu(listLadingBillVos,ladingBillVo.getFreightSettlementId(), logisticsCostSettlementVo);

	        RmVoHelper.markModifyStamp(request,logisticsCostSettlementVo);  //打修改时间,IP戳
	        logisticsCostSettlementService.update(logisticsCostSettlementVo);

	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
	        result.put(ISystemConstant.AJAX_MESSAGE, "修改成功");
	        return result;
	    }
	    
	    /**
	     * 查询主产品销售计划下的提货单
	     * @param params
	     * @return
	     */
	    public List<LadingBillVo> getPlantOrderList(Map<String, Object> params, int startIndex, int size){
	    	return ladingBillDao.getPlantOrderList(params, startIndex, size);
	    }
	    /**
	     * 查询主产品销售计划下的提货单总记录数
	     * @param params
	     * @return
	     */
	    public int getPlantOrderCount(Map<String, Object> params){
	    	return ladingBillDao.getPlantOrderCount(params);
	    }

	    /**
	     * 作废提货单 （值针对主产品）
	     * @param request
	     * @param ladingBillId
	     * @return
	     */
		public Map<String, Object> updateInvalidLadingBill(
				HttpServletRequest request, String ladingBillId, String invalidReason) {
			Map<String, Object> variable = new HashMap<String, Object>();
			
			variable.put("id", ladingBillId);
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			
			LadingBillVo vo = getLadingBillVo(variable);
			variable.clear();
			
			if(null == vo){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "未找到提货单！");
				
				return variable;
			}
			
			if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(vo.getBillType())){
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "提货单类型不对！");
				
				return variable;
				
			}
			
			
			vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09);
			vo.setInvalidReason(invalidReason);
			RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
			
			vo.setInvalidPerson(userVo.getName());
			vo.setInvalidPersonId(Long.valueOf(userVo.getId()));
			vo.setInvalidTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
			
			RmVoHelper.markModifyStamp(request, vo);
			
			update(vo);
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			variable.put(ISystemConstant.AJAX_MESSAGE, "作废成功！");
			
			return variable;
		}

		/**
		 * 批量审核提货
		 * @param request
		 * @param ladingBillIds
		 * @return
		 */
		public Map<String, Object> updateBatchAuditLadingBill(
				HttpServletRequest request, String ladingBillIds) {
			Map<String, Object> variable = new HashMap<String, Object>();
			
			String[] split = ladingBillIds.split(",");
			
			for (String id : split) {
				variable = updateLadingBill(request, id, "audit");
				
				if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
					String lowLadingBill = (String) variable.get("lowLadingBill");
					
					if(StringHelper.isEmpty(lowLadingBill)){
						if(StringHelper.isEmpty(id)){
						variable.put("lowLadingBill", "审核失败的提货单号：" + get(id).getLadingBillCode());
						}
					}else{
						variable.put("lowLadingBill", lowLadingBill + "， " + get(id).getLadingBillCode());
					}
				}
			}
			
			return variable;
		}

		/**
		 * 接口获取提货单信息
		 * 
		 * 条件三选一  phoneNumber			当前签到司机手机号  idNumber			当前签到司机身份证号 truckNumber			当前签到司机车牌号
		 * @param request
		 * @param phoneNumber 电话号
		 * @param idNumber 身份证号
		 * @param truckNumber 车牌号
		 * @param billStatus 状态
		 * @param startDate 开始日期 为空就是当天
		 * @param endDate 结束日期 为空就是当天
		 * @return
		 */
		public Map<String, Object> getLadingBillList(
				HttpServletRequest request, String phoneNumber,
				String idNumber, String truckNumber, String billStatus,
				String startDate, String endDate) {
			Map<String, Object> variable = new HashMap<String, Object>();
			
			if(StringHelper.isEmpty(truckNumber) && StringHelper.isEmpty(phoneNumber) && StringHelper.isEmpty(idNumber)){
				
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "车牌号、身份证号、手机号不能全都为空！");
			
				return variable;
			}
			
			variable.put("phoneNumber", phoneNumber);
			variable.put("licensePlate", truckNumber);
			variable.put("driverIdCardNumber", idNumber);
			if(billStatus.split(",").length > 1){
				variable.put("ladingBillStatuses", billStatus);
			}else{
				variable.put("ladingBillStatus", billStatus);
				
			}
			Date date = DateUtil.toSqlDate(DateUtil.getNowDate());
			//订单默认提货结束时间
			variable.put("time", Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_DEFAULT_TIME, ISystemConstant.DICTIONARY_DEFAULT_DELIVERY_END_TIME)));			
			variable.put("estimatedLadingTimeStart", (StringHelper.isEmpty(startDate)?date:DateUtil.toSqlDate(DateUtil.parser8Date(startDate))).toString());
			variable.put("estimatedLadingTimeEnd", (StringHelper.isEmpty(endDate)?date:DateUtil.toSqlDate(DateUtil.parser8Date(endDate))).toString());
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag1", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag2", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag4", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag5", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag6", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag7", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			//排除已出厂(06),已作废(04)
			variable.put("notRowStatues",StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_04,ISystemConstant.DICTIONARY_ROW_STATUS_06));
//			variable.put("notRowStatus", ISystemConstant.DICTIONARY_ROW_STATUS_04);
			
//			variable.put("time", defaultDeliveryTime);
			//decode(lading.billStatus, '03','1','02','2','01','3'),lading.billid asc
			variable.put("orderStr", "decode(lading.billStatus, '" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03 + "','1','" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02 + "','2','" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01 + "','3'),lading.billid asc");
			
			List<ILadingBillVo> list = getLadingBillInterfaceData(variable);
			
			variable.clear();
			
			if(CollectionUtils.isEmpty(list)){
				
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "暂无数据！");
			}else{
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put("bills", list);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
			}
			
			return variable;
		}

		/**
		 * 作废提货单(提货失败)
		 * @param request
		 * @param billid
		 * @return
		 */
		public Map<String, Object> updateCancelLadingBill(
				HttpServletRequest request, String billid) {

			Map<String, Object> variable = new HashMap<String, Object>();
			String nowDate = DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd");
			variable.put("ladingBillCode", billid);
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			//只有(04=车辆安检，05=装车待分配，10=装车已分配)允许作废，不管时间  remove by longsebo 2017-03-24
//			variable.put("rowDate", nowDate);
			variable.put("ladingBillStatuses", 
					StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04,
							ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05,
							ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10));
			variable.put("cancelLadingBill", "cancelLadingBill");
			
			LadingBillVo vo = getLadingBillVo(variable);
			variable.clear();
			
			if(vo == null){
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "未找到提货单！只有(04=车辆安检，05=装车待分配，10=装车已分配)允许作废.");
				return variable;
			}
			
			vo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07);
			
			//作废签到记录
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("ladingBillId", vo.getId());
			variable.put("rowDate", nowDate);
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
			
			RowNumberVo numberVo = rowNumberService.getRowNumberVo(variable);
			variable.clear();
			numberVo.setStatus(ISystemConstant.DICTIONARY_ROW_STATUS_04);
			
			
			//回填车数 数量
//			updateBackfill(request, vo, variable);
			
			update(vo);
			rowNumberService.update(numberVo);
			
			variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
			variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
			
			
			String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_02);
			PushDataVo cacheVo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04);			
			PushQueue.getInstance().put(cacheVo);
			return variable;
		}

		/**
		 * 作废提货单 回填销售单明细 
		 * @param request
		 * @param vo
		 * @param variable
		 */
//		private void updateBackfill(HttpServletRequest request,
//				LadingBillVo vo, Map<String, Object> variable) {
//			//作废提货单 把数量加回主计划或者销售订单
//			if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(vo.getBillType())){//销售订单
//				
//				orderProductDetailService.updateBackfillOrder(request, vo, variable);
//				
//			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(vo.getBillType())){//汽运配送计划
//				carDistributionPlanService.updateBackfillCarDisPlan(request, vo, variable);
//				
//				
//			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(vo.getBillType())){//汽运调拨计划
//				carRequisitionPlanService.updateBackfillCarReqPlan(request, vo, variable);
//			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(vo.getBillType())){//铁运调拨&配送计划
//				trainsRequisitionPlanService.updateBackfillTrainReqPlan(request, vo, variable);
//			}
//			
//			
//		}
		
		
		
		/**
		 * 开具提货单   回填剩余车数    可添加多个车数
		 * @param request
		 * @param vo
		 * @param variable
		 */
		private void updateCarNumBySubtraction(HttpServletRequest request,List<LadingBillVo> insertList) {
			Map<String, Object> variable=new HashMap<String, Object>();
			String orderPlanId =insertList.get(0).getOrderPlanId();
			String billType =insertList.get(0).getBillType();
			int num=0;
			for(LadingBillVo  ladingBillVo:insertList){
				num=num+ladingBillVo.getNuclearLoad().intValue();
			}

			//把主计划或者销售订单  剩余车数减掉
			if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(billType)){//销售订单
				
//				orderProductDetailService.updateBackfillOrder(request, vo, variable);
				
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billType)){//汽运配送计划
				carDistributionPlanService.doTrainsRequisitionUpdateCarNum(variable, orderPlanId, FORMULA_SUBTRACTION,insertList.size(),num);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billType)){//汽运调拨计划
				carRequisitionPlanService.doTrainsRequisitionUpdateCarNum(variable, orderPlanId, FORMULA_SUBTRACTION,insertList.size(),num);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billType)){//铁运调拨&配送计划
				trainsRequisitionPlanService.doTrainsRequisitionUpdateCarNum(variable, orderPlanId, FORMULA_SUBTRACTION,insertList.size(),num);
			}
			
			
		}
		
		
		
		/**
		 * 删除提货单时  作废提货单  回填剩余车数 
		 * @param request
		 * @param vo
		 * @param variable
		 */
		private void updateCarNumByAdd(HttpServletRequest request,LadingBillVo  vo) {
			Map<String, Object> variable=new HashMap<String, Object>();
			String orderPlanId =vo.getOrderPlanId();
			String billType =vo.getBillType();
			int num=vo.getNuclearLoad().intValue();

			//把主计划或者销售订单  剩余车数减掉
			if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(billType)){//销售订单
	    		variable.clear();
	    		variable.put("orderId", vo.getOrderPlanId());
	    		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
	    		variable.put("orderToLadingBill", "orderToLadingBill");
	    		variable.put("productId", vo.getProductId());
	    		OrderProductDetailVo orderProductDetailVo = orderProductDetailService.getOrderProductDetailVo(variable);
				orderProductDetailService.doOrderUpdateCarNum(variable, orderProductDetailVo.getId(), FORMULA_SUBTRACTION,1,num);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billType)){//汽运配送计划
				carDistributionPlanService.doTrainsRequisitionUpdateCarNum(variable, orderPlanId, FORMULA_ADD,1,num);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billType)){//汽运调拨计划
				carRequisitionPlanService.doTrainsRequisitionUpdateCarNum(variable, orderPlanId, FORMULA_ADD,1,num);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billType)){//铁运调拨&配送计划
				trainsRequisitionPlanService.doTrainsRequisitionUpdateCarNum(variable, orderPlanId, FORMULA_ADD,1,num);
			}
			
			
		}

		
	    /**
	     *  装车管理  提货失败   更改提货单状态     以及相关
	     */
	    public void doFailLoading(HttpServletRequest request,String ids[],Map<String, Object> result) {
	    	
	        if(ids!=null && ids.length>0 ){
		           for(String id:ids){
		       		//装车通知单
		       		LoadingNoticeVo oldLoadingNoticeVo =loadingNoticeService.getLoadingNoticeVo(id);
		        	if(oldLoadingNoticeVo != null){
			        	   //有产品批次  就回填库存出库数量
			        	   productBatchNumberService.updateOutNum(request, result, oldLoadingNoticeVo);
		        	}   
		        	   
		        	//修改提货单的状态  装货失败
		         	doUpdateStatus(request,id,ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07);
		           }
		    }

	        return ;
	    }
	    
	    /**
	     * 多条更改状态  
	     */
	    public void doUpdateListStatus(HttpServletRequest request,String ids[],String type) {
	        if(ids!=null && ids.length>0 ){
	           for(String id:ids){
	        	   
	        	   doUpdateStatus(request,id,type);
	           }
	        }else{
	           throw new PjException("ids 为空!");
	        }
	        
	        return ;
	    }

	    /**
	     * 一条更改状态  
	     */
	    public void doUpdateStatus(HttpServletRequest request,String id,String type) {
	    	LadingBillVo    bean=get(id);
	        	   
		   //改成提货失败
		   if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07.equals(type)){
			   if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05.equals(bean.getLadingBillStatus())
					   && !ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10.equals(bean.getLadingBillStatus())){
				   //不是待分配  已分配
		           throw new PjException("不能修改");
			   }
		   }
		   
		   bean.setLadingBillStatus(type);
	   
	       RmVoHelper.markModifyStamp(request,bean);  //打修改时间,IP戳

	       update(bean);
	       return ;
	    }
	    
	    
	    
	    
	    /**
	     * 台账 总计
	     */
	    public void getBill(List<LadingBillVo> beans) {
	    	
	    	if(!beans.isEmpty()){
		    	LadingBillVo vo=new LadingBillVo();
		    	BigDecimal  actualShippingWeight=BigDecimal.ZERO;
		    	BigDecimal  actualShippingAmount=BigDecimal.ZERO;
		    	BigDecimal  factMileage=BigDecimal.ZERO;
		    	BigDecimal  freight=BigDecimal.ZERO;
		    	
		    	for(LadingBillVo  ladingBillVo:beans){
		    		actualShippingWeight =actualShippingWeight.add(ladingBillVo.getActualShippingWeight()==null?BigDecimal.ZERO:ladingBillVo.getActualShippingWeight());
		    		actualShippingAmount=actualShippingAmount.add(ladingBillVo.getActualShippingAmount()==null?BigDecimal.ZERO:ladingBillVo.getActualShippingAmount());
		    		factMileage=factMileage.add(ladingBillVo.getFactMileage()==null?BigDecimal.ZERO:ladingBillVo.getFactMileage());
		    		freight=freight.add(ladingBillVo.getFreight()==null?BigDecimal.ZERO:ladingBillVo.getFreight());
		    	}
		    	
		    	vo.setOrderPlanCode("总计");
		    	//实际发货金额
		    	vo.setActualShippingAmount(actualShippingAmount);
		    	//实际发货数量
		    	vo.setActualShippingWeight(actualShippingWeight);
		    	//实际里程
		    	vo.setFactMileage(factMileage);
		    	//运费
		    	vo.setFreight(freight);
		    	
		    	beans.add(vo);
	    	}
	    }
		
	    
	    
	    
	    
	    /**
	     * 生成运费结算单确认提交时   更新实际里程  以及相关字段
	     * 
	     * @param vo 用于更新的VO对象
	     * @return 成功更新的记录数
	     */
	    public void updateBySettlerBIll(HttpServletRequest request,LogisticsCostSettlementVo  logisticsCostSettlementVo) {
	        
	         List<LadingBillVo>  listLadingBillVos=updateFactMileagesS(request);
	    	
	         if(listLadingBillVos != null  && !listLadingBillVos.isEmpty()){
		        
		        updateLadingBillVoLu(listLadingBillVos,logisticsCostSettlementVo.getId(), logisticsCostSettlementVo);
	        	 
	         }

	         return ;
	    }
	    
	    
	    /***
	     * 二次过磅 调用   判断是否装车
	     * */
	    public  void  updateByIspon(LadingBillVo  ladingBillVo,String  weight){
	    	if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(StringHelper.isEmpty(ladingBillVo.getIsLoading())?ISystemConstant.DICTIONARY_RM_YES_NOT_0:ladingBillVo.getIsLoading())){
	    		return ;
	    	}
	    	//装车
	    	//过磅净重
	    	BigDecimal   actualWeight =new BigDecimal(weight); 
	    	//装车的实际发货重量
	    	BigDecimal   actualShippingWeight=ladingBillVo.getActualShippingWeight();
	    	
	    	//装车单vo
	    	LoadingNoticeVo  loadingNoticeVo  =loadingNoticeService.getLoadingNoticeVo(ladingBillVo.getId());
	    	if(null == loadingNoticeVo){
		          return;
	    	}
	    	
	    	if(actualWeight.compareTo(actualShippingWeight) == 0){
	    		return ;
	    	}

	    	//改批次  库存出库数量
	    	//净重-实际发货重量
	    	BigDecimal   subNum=actualWeight.subtract(actualShippingWeight);

	    	//更改批次
	    	productBatchNumberService.updateNum(loadingNoticeVo,subNum);
	    	
	    	//改为净重
	    	ladingBillVo.setActualShippingWeight(actualWeight);

	    }
	    
	    
	    
	    /**
	     * 待结算  更新实际里程  实际计价里程   运价系数 以及相关字段
	     * @return 成功更新的记录数
	     */
	    public List<LadingBillVo> updateFactMileagesS(HttpServletRequest request) {
	    	List<LadingBillVo>  list=new  ArrayList<LadingBillVo>();
	         String[] ladingbillIds = RmJspHelper.getArrayFromRequest(request, "ladingbillIds"); 
	         //实际里程
	         String[]  factMileages=RmJspHelper.getArrayFromRequest(request, "factMileages");
	         //实际计价里程
	         String[]  factValuationMileages=RmJspHelper.getArrayFromRequest(request, "factValuationMileages");
	         //运价系数
	         String[]  yuanKmTons=RmJspHelper.getArrayFromRequest(request, "yuanKmTons");
	         //结算运价
	         String[]  settlementFreights=RmJspHelper.getArrayFromRequest(request, "settlementFreights");
	         
	         Boolean  isUpdate=false;
	         
	         BigDecimal  settlement=BigDecimal.ZERO;
	         if (ladingbillIds != null && ladingbillIds.length != 0) {
		 	     //距离系数
		 	     BigDecimal oneMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01);
	        	 for(int i=0;i<ladingbillIds.length;i++){
		     	        LadingBillVo oldVo = get(ladingbillIds[i]);

		     	        //实际里程改变
		     	        if(new BigDecimal(factMileages[i]).compareTo(oldVo.getFactMileage()==null?BigDecimal.ZERO:oldVo.getFactMileage())  != 0){
		     	        	oldVo.setFactMileage(new BigDecimal(factMileages[i]));
		     	        	isUpdate=true;
		     	        }
		     	        //实际计价里程修改
		     	        if(new BigDecimal(factValuationMileages[i]).compareTo(oldVo.getFactValuationMileage()==null?BigDecimal.ZERO:oldVo.getFactValuationMileage()) != 0){
		     	        	oldVo.setFactValuationMileage(new BigDecimal(factValuationMileages[i]));
		     	        	isUpdate=true;
		     	        }
//		     	        else{
//			     	        //实际里程改变
//			     	        if(new BigDecimal(factMileages[i]).compareTo(oldVo.getFactMileage())  != 0){
//			 	    	        //实际计价里程==实际里程*距离系数
//				 	     	    oldVo.setFactValuationMileage(oldVo.getFactMileage().multiply(oneMileage));
//				 	     	    isUpdate=true;
//			     	        }
//		     	        }
		     	        
		     	        //运价系数
		     	        if(new BigDecimal(yuanKmTons[i]).compareTo(oldVo.getYuanKmTon()==null?BigDecimal.ZERO:oldVo.getYuanKmTon()) != 0){
		     	        	oldVo.setYuanKmTon(new BigDecimal(yuanKmTons[i]));
		     	        	isUpdate=true;
		     	        }
		     	        settlement=(oldVo.getFactValuationMileage()==null?BigDecimal.ZERO:oldVo.getFactValuationMileage()).multiply(oldVo.getYuanKmTon()==null?BigDecimal.ZERO:oldVo.getYuanKmTon());
		     	        //结算运价
		     	        if(new BigDecimal(settlementFreights[i]).compareTo(oldVo.getSettlementFreight()==null?BigDecimal.ZERO:oldVo.getYuanKmTon()) != 0){
		     	        	settlement=new BigDecimal(settlementFreights[i]);
		     	        	isUpdate=true;
		     	        }
		     	        
	 		   	        //结算运价 ==实际计价里程*运价系数（一般为0.31）
	 	     	       oldVo.setSettlementFreight(settlement);
	 		   	        //总运费 == 结算运价*数量
	 	     	       oldVo.setFreight(oldVo.getSettlementFreight().multiply(oldVo.getActualShippingWeight()==null?BigDecimal.ZERO:oldVo.getActualShippingWeight()));
	 	     	        
	 	     	       if(isUpdate){
		 		   	       RmVoHelper.markModifyStamp(request,oldVo);  //打修改时间,IP戳
		 			       update(oldVo);  //更新单条记录
	 	     	       }
	 	     	       
	 			       isUpdate=false;
	 			       list.add(oldVo);
	        	 }
	         }
	         

	         return list;
	    }
	    /**
	     * 八、多个状态获取提货单列表信息    和getLadingBillList区别：排队表不带状态过滤
	     * @param request
	     * @param phoneNumber
	     * @param idNumber
	     * @param truckNumber
	     * @param billStatus
	     * @param startDate
	     * @param endDate
	     * @param billid 提货单号
	     * @return
	     */
		public Map<String, Object> getLadingBillListForSign(
				HttpServletRequest request, String phoneNumber,
				String idNumber, String truckNumber, String billStatus,
				String startDate, String endDate, String billid) {
			Map<String, Object> variable = new HashMap<String, Object>();
			if(StringHelper.isEmpty(truckNumber) && StringHelper.isEmpty(phoneNumber) && StringHelper.isEmpty(idNumber)
					&& StringHelper.isEmpty(billid)){
				
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "车牌号、身份证号、手机号、提货单号不能全都为空！");
			
				return variable;
			}
			
			variable.put("phoneNumber", phoneNumber);
			variable.put("licensePlate", truckNumber);
			variable.put("driverIdCardNumber", idNumber);
			if(billStatus.split(",").length > 1){
				variable.put("ladingBillStatuses", billStatus);
			}else{
				variable.put("ladingBillStatus", billStatus);
				
			}
			Date date = DateUtil.toSqlDate(DateUtil.getNowDate());
			//如果没传提货单号,订单有默认提货结束时间
			if(StringHelper.isEmpty(billid)){
				//订单默认提货结束时间
				variable.put("time", Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_DEFAULT_TIME, ISystemConstant.DICTIONARY_DEFAULT_DELIVERY_END_TIME)));			
				variable.put("estimatedLadingTimeStart", (StringHelper.isEmpty(startDate)?date:DateUtil.toSqlDate(DateUtil.parser8Date(startDate))).toString());
				variable.put("estimatedLadingTimeEnd", (StringHelper.isEmpty(endDate)?date:DateUtil.toSqlDate(DateUtil.parser8Date(endDate))).toString());
			}else{
				variable.put("time", Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_DEFAULT_TIME, ISystemConstant.DICTIONARY_DEFAULT_DELIVERY_END_TIME)));
				if(!StringHelper.isEmpty(startDate)){
					variable.put("estimatedLadingTimeStart", DateUtil.toSqlDate(DateUtil.parser8Date(startDate)).toString());
				}
				if(!StringHelper.isEmpty(endDate)){
					variable.put("estimatedLadingTimeEnd", DateUtil.toSqlDate(DateUtil.parser8Date(endDate)).toString());
				}
			}
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag1", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag2", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag4", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag5", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag6", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("delete_flag7", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//			variable.put("notRowStatus", ISystemConstant.DICTIONARY_ROW_STATUS_04);
			variable.put("ladingBillCode", billid);
//			variable.put("time", defaultDeliveryTime);
			//decode(lading.billStatus, '03','1','02','2','01','3'),lading.billid asc
			variable.put("orderStr", "decode(lading.billStatus, '" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03 + "','1','" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02 + "','2','" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01 + "','3'),lading.billid asc");
			
			List<ILadingBillVo> list = getLadingBillInterfaceData(variable);
			
			variable.clear();
			
			if(CollectionUtils.isEmpty(list)){
				
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "暂无数据！");
			}else{
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put("bills", list);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
			}
			
			return variable;
		}
	    
}


