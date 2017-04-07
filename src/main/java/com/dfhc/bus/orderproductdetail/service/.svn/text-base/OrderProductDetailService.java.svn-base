/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  OrderProductDetailService.java
 *
 * 功能描述：  销售订单产品明细服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.orderproductdetail.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.dfhc.bus.cardistributionplan.vo.CarDistributionPlanVo;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.IOrderProductDetailConstants;
import com.dfhc.bus.orderproductdetail.dao.OrderProductDetailDao;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.PjException;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 销售订单产品明细服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class OrderProductDetailService implements IOrderProductDetailConstants {

    @Autowired
    /**
     * 销售订单产品明细数据访问对象
     */
    private OrderProductDetailDao orderProductDetailDao;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private LadingBillService ladingBillService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(OrderProductDetailVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = orderProductDetailDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单产品明细插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(OrderProductDetailVo[] vos) {
        String[] ids = orderProductDetailDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单产品明细插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = orderProductDetailDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单产品明细删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = orderProductDetailDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单产品明细删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(OrderProductDetailVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = orderProductDetailDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单产品明细更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(OrderProductDetailVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单产品明细批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(OrderProductDetailVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<OrderProductDetailVo> lInsert = new ArrayList<OrderProductDetailVo>();
        List<OrderProductDetailVo> lUpdate = new ArrayList<OrderProductDetailVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new OrderProductDetailVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new OrderProductDetailVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public OrderProductDetailVo get(String id) {
        OrderProductDetailVo vo = orderProductDetailDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = orderProductDetailDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<OrderProductDetailVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<OrderProductDetailVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<OrderProductDetailVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<OrderProductDetailVo> lResult = orderProductDetailDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<OrderProductDetailVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<OrderProductDetailVo> lResult = orderProductDetailDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(OrderProductDetailVo vo) {
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
    private void verifyInsertVo(OrderProductDetailVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<OrderProductDetailVo> vos) {
        for(OrderProductDetailVo vo:vos){
           verifyUpdateVo(vo);
        }
        orderProductDetailDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<OrderProductDetailVo> vos) {
        for(OrderProductDetailVo vo:vos){
           verifyInsertVo(vo);
        }
        orderProductDetailDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        OrderProductDetailVo vo = orderProductDetailDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(orderProductDetailDao.update(vo)!=1){
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
	 * 根据页面的产品集合插入数据
	 */
	public BigDecimal doAddListProduct(HttpServletRequest request,String orderId,String orderNum) {
		BigDecimal sum=BigDecimal.ZERO;
		
	 	String jqData=request.getParameter("jqData");
    	System.out.println(jqData);
    	if(StringHelper.isEmpty(jqData)){
    		throw new PjException("没找到对应订单产品明细！");
    	}
    	
    	List<OrderProductDetailVo> listProDtl=JSONArray.parseArray(jqData, OrderProductDetailVo.class);
    	
    	System.out.println(listProDtl.size());
    	
    	for(OrderProductDetailVo orderProDtlVo:listProDtl){
    		orderProDtlVo.setId(null);
    		orderProDtlVo.setOrderId(orderId);
    		orderProDtlVo.setOrderNum(orderNum);
    		orderProDtlVo.setAssignedCarNumBer(BigDecimal.ZERO.longValue());
    		sum=sum.add(orderProDtlVo.getPlannedVolume().multiply(orderProDtlVo.getUnitPrice()));
    		
           RmVoHelper.markCreateStamp(request,orderProDtlVo);  //打创建时间,IP戳
    	}
		orderProductDetailDao.insertBatch(listProDtl);
  
		return sum;
		
	}

	/**
	 * 根据明细算金额
	 */
	public BigDecimal doSumListProduct(HttpServletRequest request,String id) {
		BigDecimal sum=BigDecimal.ZERO;
		
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("orderId",id);
        searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	List<OrderProductDetailVo> listProDtl=list(searchMap, null);
    	if(!listProDtl.isEmpty()){
        	for(OrderProductDetailVo orderProDtlVo:listProDtl){
        		sum=sum.add(orderProDtlVo.getPlannedVolume().multiply(orderProDtlVo.getUnitPrice()));
        	}
    	}
		return sum;
		
	}
	

	public OrderProductDetailVo getOrderProductDetailVo(Map<String, Object> params, boolean flag){
		
		List<OrderProductDetailVo> list = list(params, null, 1, -1, flag);
		
		return CollectionUtils.isEmpty(list)?null:list.get(0);
		
	}
	public OrderProductDetailVo getOrderProductDetailVo(Map<String, Object> params){
		
		
		return getOrderProductDetailVo(params, false);
		
	}

	/**
	 * 更新订单明细 创建提货单
	 * @param request
	 * @param orderDetailId 订单明细id
 	 * @return
	 */
	public Map<String, Object> updateOrderDetailToLadingBillList(
			HttpServletRequest request, String orderDetailId, String flag) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String jqData = request.getParameter("jqData");
		
		
		OrderProductDetailVo orderProductDetailVo = get(orderDetailId);
		
		//验证信息
		OrderVo orderVo = checkedInfo(orderProductDetailVo, result, jqData);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			
			return result;
		}

		ladingBillService.inserVoByOrderDetail(jqData, request, orderProductDetailVo, result, orderVo, flag);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return result;
		}
		update(orderProductDetailVo);
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "保存成功");
		
		return result;
	}

	/**
	 * 订单明细生成提货单验证
	 * @param orderProductDetailVo
	 * @param result
	 */
	private OrderVo checkedInfo(OrderProductDetailVo orderProductDetailVo,
			Map<String, Object> result, String jqData) {
		
		if(StringHelper.isEmpty(jqData)){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			result.put(ISystemConstant.AJAX_MESSAGE, "没有创建提货单，不需要保存提交！");
				
			return null;
		}
		
		if(orderProductDetailVo.getRemainderCarNum() == 0){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			result.put(ISystemConstant.AJAX_MESSAGE, "剩余车数为0！");
				
			return null;
		}
		
		OrderVo orderVo = orderService.get(orderProductDetailVo.getOrderId());
		
		if(!ISystemConstant.DICTIONARY_ORDER_STATUS_02.equals(orderVo.getStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			result.put(ISystemConstant.AJAX_MESSAGE, "订单状态不是待审核，不能创建提货单！");
				
			return null;
		}
		
		
		return orderVo;

	}
	/**
     * 查询销售订单明细的总金额和对应客户的可用余额=授信额度+业务可用余额-订单金额
     * @param params
     * @return
     */
    public Map<String, Object> getCustomerPrice(Map<String, Object> params){
    	return orderProductDetailDao.getCustomerPrice(params);
    }

    /**
     * 作废提货单回填销售单明细
     * @param request
     * @param vo
     * @param variable
     */
	public void updateBackfillOrder(HttpServletRequest request,
			LadingBillVo vo, Map<String, Object> variable) {
		variable.put("orderId", vo.getOrderPlanId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("productId", vo.getProductId());
		OrderProductDetailVo orderProductDetailVo = getOrderProductDetailVo(variable);
		variable.clear();
		
		orderProductDetailVo.setAssignedCarNumBer(orderProductDetailVo.getAssignedCarNumBer()-1);
		update(orderProductDetailVo);
		
	}
	
	
    /**
     * 回填销售计划   剩余车数
     * @param request
     * @param vo
     * @param variable
     */
	public void doOrderUpdateCarNum(Map<String, Object> variable,String orderDetailId,
			String  type,int carNum,int num) {
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("id", orderDetailId);
		
		List<OrderProductDetailVo> list = list(variable, null);

		OrderProductDetailVo orderProductDetailVo = list.get(0);
		if(ILadingBillConstants.FORMULA_ADD.equals(type)){
			//加法
			orderProductDetailVo.setAssignedCarNumBer(orderProductDetailVo.getAssignedCarNumBer()+carNum);
//			carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderNum()+num);
		}else{
			//减法
			orderProductDetailVo.setAssignedCarNumBer(orderProductDetailVo.getAssignedCarNumBer()-carNum);
//			carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderNum()-num);
		}
		update(orderProductDetailVo);
	}
 
}
