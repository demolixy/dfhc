/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  OrderService.java
 *
 * 功能描述：  销售订单服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.order.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.base.shipper.service.ShipperService;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.order.IOrderConstants;
import com.dfhc.bus.order.dao.OrderDao;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.service.OrderProductDetailService;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.bus.trainsrequisitionplan.vo.TrainsRequisitionPlanVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.AccountChangeService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.vo.FreezeAccVo;
import com.dfhc.util.DateUtil;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 销售订单服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class OrderService implements IOrderConstants {
	
	@Autowired
	private  PubParamService pubParamService;
	
	@Autowired
	private  OrderProductDetailService orderProductDetailService;
    @Autowired
    /**
     * 销售订单数据访问对象
     */
    private OrderDao orderDao;
    @Autowired
    private ShipperService shipperService;
    @Autowired
    private AccountChangeService accountChangeService;

    @Autowired
    private LadingBillService ladingBillService;


    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(OrderVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = orderDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(OrderVo[] vos) {
        String[] ids = orderDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = orderDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = orderDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(OrderVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = orderDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(OrderVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "销售订单批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(OrderVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<OrderVo> lInsert = new ArrayList<OrderVo>();
        List<OrderVo> lUpdate = new ArrayList<OrderVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new OrderVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new OrderVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public OrderVo get(String id) {
        OrderVo vo = orderDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = orderDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<OrderVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<OrderVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<OrderVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<OrderVo> lResult = orderDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<OrderVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<OrderVo> lResult = orderDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(OrderVo vo) {
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
    private void verifyInsertVo(OrderVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<OrderVo> vos) {
        for(OrderVo vo:vos){
           verifyUpdateVo(vo);
        }
        orderDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<OrderVo> vos) {
        for(OrderVo vo:vos){
           verifyInsertVo(vo);
        }
        orderDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        OrderVo vo = orderDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        
        pubParamService.commonCheck(String.valueOf(vo.getCustomerId()), request);
        
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(orderDao.update(vo)!=1){
           throw new PjException("更新删除标志失败!");
        }
        
        //删除产品表
//        Map<String, Object> searchMap = new HashMap<String, Object>();
//        searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//        searchMap.put("orderId", request.getParameter("orderId"));
//        List<OrderProductDetailVo>  list=orderProductDetailService.list(searchMap, null);
//        if(!list.isEmpty()){
//        	for(OrderProductDetailVo  orderProductDetailVo:list){
//        		orderProductDetailService.deleteLogic(request, orderProductDetailVo.getId());
//        	}
//        }
        
        
        
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
     * 销售订单管理   添加  修改
     */
    public void doAddOrUpdateOrder(HttpServletRequest request,RmUserVo loginUserVo,OrderVo  vo) {
    	//检验页面数据
    	OrderVo newVo=doCheckForm(request,vo);
    	
    	OrderVo oldVo=get(newVo.getId());

    	
    	if(oldVo==null){
    		//添加页面
    		//订单编号
    		newVo.setOrderNum(pubParamService.getSequenceNumber(IOrderConstants.ORDER_NUM));//订单号
        	//发货公司编码  默认为榆林库
    		newVo.setShipperCode(ISystemConstant.DICTIONARY_SHIPPER_CODE_01);
    		//下单时间
    		newVo.setPlaceOrderTime(new java.sql.Date(DateUtil.getNowDate().getTime()));
    		newVo.setInputPersonId(Long.valueOf(loginUserVo.getId()));//录入人Id
    		newVo.setInputPerson(loginUserVo.getName());//录入人
    		
        	//添加子表 
            BigDecimal sum =orderProductDetailService.doAddListProduct(request,newVo.getId(),newVo.getOrderNum());
            
            newVo.setAmountMoney(sum);//总金额
            newVo.setPlannedSurplus(sum);//计划剩余总金额

            //审核人
    		if(ISystemConstant.DICTIONARY_ORDER_STATUS_02.equals(newVo.getStatus())){
    	    	//审核人
    	    	vo.setAuditPersonId(Long.valueOf(loginUserVo.getId()));
    	    	vo.setAuditPerson(loginUserVo.getName());
    	    	vo.setAuditTime(new java.sql.Date(DateUtil.getNowDate().getTime()));
    	     	//冻结账户金额
    	        doFreezeAccVo(request, newVo, loginUserVo);
    	    }
            
            //添加主表
            RmVoHelper.markCreateStamp(request,newVo);  //打创建时间,IP戳
            orderDao.insert(newVo);
    		
    	}else{
    		//协同操作验证
    		pubParamService.commonCheck(String.valueOf(oldVo.getCustomerId()), request);
    		//修改页面
//    		if(!ISystemConstant.DICTIONARY_ORDER_STATUS_00.equals(oldVo.getStatus())){
// 	           throw new PjException("不能修改提交的");
//    		}
    		//删除子表
            Map<String, Object> searchMap = new HashMap<String, Object>();
            searchMap.put("orderId", newVo.getId());
            searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
            List<OrderProductDetailVo> beans=orderProductDetailService.list(searchMap, null);
            if(!beans.isEmpty()){
            	for(OrderProductDetailVo  orderProductDetailVo:beans){
            		orderProductDetailService.delete(orderProductDetailVo.getId());
            	}
            }
            
        	//添加子表 
            BigDecimal sum =orderProductDetailService.doAddListProduct(request,oldVo.getId(),oldVo.getOrderNum());
            
            newVo.setAmountMoney(sum);//总金额
            newVo.setPlannedSurplus(sum);//计划剩余总金额
            //更改主表
            
            //审核人
    		if(ISystemConstant.DICTIONARY_ORDER_STATUS_02.equals(newVo.getStatus())){
    	    	//审核人
    	    	vo.setAuditPersonId(Long.valueOf(loginUserVo.getId()));
    	    	vo.setAuditPerson(loginUserVo.getName());
    	    	vo.setAuditTime(new java.sql.Date(DateUtil.getNowDate().getTime()));
    	     	//冻结账户金额
    	        doFreezeAccVo(request, newVo, loginUserVo);
    	    }

            RmVoHelper.markModifyStamp(request,newVo);  //打修改时间,IP戳
        	orderDao.update(newVo);
    	}
    	
    	return ;
    }

    /**
     * 校验插入vo
     * @param vo
     */
    private OrderVo doCheckForm(HttpServletRequest request,OrderVo vo) {
        //id
    	String id=vo.getId();
		if (StringHelper.isEmpty(id)) {
	           throw new PjException("id不能为空!");
		}
		//客户id
		String customerName=vo.getCustomerName();
		if (StringHelper.isEmpty(customerName)) {
	           throw new PjException("客户不能为空!");
		}
 
		//提货开始时间
		Date deliveryStartDate=vo.getDeliveryStartDate();
		if (deliveryStartDate==null) {
	           throw new PjException("提货开始时间不能为空!");
		}

		int defaultDeleveryTime=Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_DEFAULT_TIME,ISystemConstant.DICTIONARY_DEFAULT_DELIVERY_END_TIME));
		
		java.util.Date date =DateUtil.addDays(deliveryStartDate, defaultDeleveryTime);
		Date  endDate=new  java.sql.Date(date.getTime());
		vo.setDeliveryEndDate(endDate);
		//提货结束时间
//		Date deliveryEndDate=vo.getDeliveryEndDate();
//		if (deliveryEndDate==null) {
//	           throw new PjException("提货结束时间不能为空!");
//		}
//		Date nowTime=new Date(System.currentTimeMillis());
//		Date now=java.sql.Date.valueOf(nowTime.toString());
//		Date start=java.sql.Date.valueOf(deliveryStartDate.toString());
//		Date end=java.sql.Date.valueOf(deliveryEndDate.toString());
//		if(end.before(now)){
//	           throw new PjException("提货结束时间不能小于当前时间!");
//		}
//		if(end.before(start)){
//	           throw new PjException("提货结束时间不能小于提货开始时间!");
//		}
		
		//发运方式
		String shipMode=request.getParameter("shipMode");
		if(StringHelper.isEmpty(shipMode)){
	           throw new PjException("发运方式不能为空!");
		}
		
		//是保存还是提交
    	String isSaveOrCommit=request.getParameter("isSaveOrCommit");
		
    	if("commit".equals(isSaveOrCommit)){
    		//提交待审核
    		vo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_01);
    	}else if("save".equals(isSaveOrCommit)){
    		//未提交
    		vo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_00);
    	}else if("audit".equals(isSaveOrCommit)){
    		//审核
    		vo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_02);
    	}

		return vo;
    }

    /**
     * 销售订单管理     审核
     */
    public void doAudit(HttpServletRequest request,String id,RmUserVo rmUserVo ) {
    	
    	OrderVo vo=get(id);
    	if(vo==null){
	           throw new PjException("未查到该销售单");
    	}
    	if(!ISystemConstant.DICTIONARY_ORDER_STATUS_01.equals(vo.getStatus())){
	           throw new PjException("不是未审核状态不能操作");
    	}
    	//已审核
    	vo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_02);
    	//审核人
    	vo.setAuditPersonId(Long.valueOf(rmUserVo.getId()));
    	vo.setAuditPerson(rmUserVo.getName());
    	vo.setAuditTime(new java.sql.Date(DateUtil.getNowDate().getTime()));
    	
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
     	orderDao.update(vo);
    	
     	//冻结账户金额
     	doFreezeAccVo(request,vo, rmUserVo);
    	return ;
    }

    /**
     * 审核  冻结账户金额
     */
    public void doFreezeAccVo(HttpServletRequest request,OrderVo  orderVo,RmUserVo rmUserVo) {
     	//冻结账户金额
     	FreezeAccVo freezeAccVo=new  FreezeAccVo();
     	freezeAccVo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_01);//销售订单类型
     	freezeAccVo.setPayerId(orderVo.getCustomerId().toString());//客户id
     	freezeAccVo.setBillsId(orderVo.getId());//单据id
     	freezeAccVo.setChangeAmt(orderVo.getAmountMoney());//金额
     	freezeAccVo.setRemark("销售订单审核");
     	freezeAccVo.setIp(null);
     	freezeAccVo.setOperaterId(rmUserVo.getId());
     	freezeAccVo.setOperaterName(rmUserVo.getName());//操作人
     	accountChangeService.doFreezeAcc(freezeAccVo);
    	return ;
    }

    
    
    /**
     * 销售订单管理     审核驳回
     */
    public void doNoAudit(HttpServletRequest request,String id) {
    	
    	OrderVo vo=get(id);
    	if(vo==null){
	           throw new PjException("未查到该销售单");
    	}
    	if(!ISystemConstant.DICTIONARY_ORDER_STATUS_01.equals(vo.getStatus())){
	           throw new PjException("不是未审核状态不能操作");
    	}
    	//已驳回
    	vo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_05);
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
     	orderDao.update(vo);
    	
    	return ;
    }
    
    public OrderVo getOrderVo(Map<String, Object> variable, boolean flag){
    	
    	List<OrderVo> list = list(variable, null, 1, -1, flag);
    	
    	return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public OrderVo getOrderVo(Map<String, Object> variable){
    	
    	return getOrderVo(variable, false);
    }
    
    
    
    

    /**
     * 超时的取消
     */
    public void doQu() {
    	
    	Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("deliveryEndDateIsMin", DateUtil.getNowDate());
        searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        searchMap.put("statuss","'"+ISystemConstant.DICTIONARY_ORDER_STATUS_01+"','"+ISystemConstant.DICTIONARY_ORDER_STATUS_02+"'");
        
        List<OrderVo>  listOrder=list(searchMap,null);
    	
        if(!listOrder.isEmpty()){
        	
        	for(OrderVo orderVo:listOrder){
        		
        		if(ISystemConstant.DICTIONARY_ORDER_STATUS_02.equals(orderVo.getStatus())){
        			//审核通过
        			FreezeAccVo  freezeAccVo=new FreezeAccVo();
        	     	freezeAccVo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_01);//销售订单类型
        	     	freezeAccVo.setPayerId(orderVo.getCustomerId().toString());//客户id
        	     	freezeAccVo.setBillsId(orderVo.getId());//单据id
        	     	freezeAccVo.setChangeAmt(orderVo.getAmountMoney());//金额
        	     	freezeAccVo.setRemark("销售订单超时取消");
        	     	freezeAccVo.setIp(null);
        	     	freezeAccVo.setOperaterId(null);
        	     	freezeAccVo.setOperaterName(null);//操作人
        			accountChangeService.doOrderCancel(freezeAccVo);
        		}
        		
        		//已过期
        		orderVo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_04);
                update(orderVo);  //更新单条记录
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
    	OrderVo  bean=get(id);
        	   
	   //改成已提交
	   if(ISystemConstant.DICTIONARY_ORDER_STATUS_01.equals(type)){
		   if(!ISystemConstant.DICTIONARY_ORDER_STATUS_00.equals(bean.getStatus())){
			   //不是未提交不能改
	           throw new PjException("其中有已提交的！");
		   }
	   }
	   
	   bean.setStatus(type);
   
       RmVoHelper.markModifyStamp(request,bean);  //打修改时间,IP戳

       update(bean);
       return ;
    }
    
    
    
	/**
	 * 上传文件
	 * 
	 * @param file
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public String upload(MultipartFile file, String id, HttpServletRequest request)throws IllegalStateException, IOException {

		String rootPath=null;
		//上传文件
		rootPath=pubParamService.upload(file, id, request);
		
		//客户协同上传图片
		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("id", id);
		OrderVo orderVo =getOrderVo(variable);
		variable.clear();
		if(orderVo == null){
			throw new PjException("未找到订单！");
		}
		
		orderVo.setOrderAttachment(StringHelper.isEmpty(orderVo.getOrderAttachment())?rootPath:orderVo.getOrderAttachment() + "," + rootPath);
		RmVoHelper.markModifyStamp(request, orderVo);
		update(orderVo);
		
		return  rootPath;
	}
	
	/**
	 * 删除图片
	 * **/
	public void deleteOrderImg(HttpServletRequest request,String id, String path,Map<String, Object> variable) {
		variable.put("id", id);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		OrderVo orderVo = getOrderVo(variable);
		variable.clear();
		
		if(orderVo == null){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "未找到订单，图片删除失败！");
			return ;
		}
		
		String orderAttachment = orderVo.getOrderAttachment();
		
		if(StringHelper.isEmpty(orderAttachment)){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "该订单未上传图片，你不需要删除！");
			
			return ;
		}
		
		String[] imgPathArr = orderAttachment.split(",");
		String imgName = path.substring(path.lastIndexOf(File.separator) + 1);
		int index = 0;
		List<String> list = Arrays.asList(imgPathArr);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).indexOf(imgName) != -1){
				index = i;
				break;
			}
		}

		list.remove(index);
		
		orderVo.setOrderAttachment(StringHelper.idsListToStr(list));
		
		RmVoHelper.markModifyStamp(request, orderVo);
		update(orderVo);
	}

	
	
	
    /**
     * 延期
     */
    public void updateYan(HttpServletRequest request,OrderVo vo) {
    	
    	Map<String, Object>  map=new HashMap<String, Object>();
    	map.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	map.put("orderPlanId", vo.getId());
    	List<LadingBillVo> list=ladingBillService.list(map, null);
    	if(!list.isEmpty()){
    		for(LadingBillVo ladingBillVo:list){
    			ladingBillVo.setEstimatedLoadingTime(vo.getDeliveryEndDate());
    			ladingBillService.update(ladingBillVo);
    		}
    	}
    	
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        update(vo);
        return ;
    }

    
    
    /**
     * 定时任务，销售订单作废   对应的提货单作废
     */
    public void clearSale(){
    	Map<String, Object> searchPara =new  HashMap<String, Object>();
    	searchPara.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	searchPara.put("notStatuss", "'"+ISystemConstant.DICTIONARY_ORDER_STATUS_03+"','"+ISystemConstant.DICTIONARY_ORDER_STATUS_04+"'");
    	searchPara.put("deliveryEndDateIsMin",DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));//系统当前时间
    	List<OrderVo> ListOrderVo = list(searchPara,null);
    	if(!ListOrderVo.isEmpty()){
        	for (OrderVo vo:ListOrderVo){
        		//作废对应的提货单
        		searchPara.clear();
            	searchPara.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
            	searchPara.put("ladingBillStatuses", "'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00+"','"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01+"','"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02+"','"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07+"'");
            	List<LadingBillVo> ListLadingBillVo = ladingBillService.list(searchPara,null);

            	if(!ListLadingBillVo.isEmpty()){
            		for(LadingBillVo  ladingBillVo:ListLadingBillVo){
            			//作废
            			ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09);
            			ladingBillService.update(ladingBillVo);
            		}
            	}
        		
        		if(ISystemConstant.DICTIONARY_ORDER_STATUS_02.equals(vo.getStatus())){
        			//审核  解冻账户金额
        			doFreezeAcc(vo);
        		}
        		
        		//过期
    		    vo.setStatus(ISystemConstant.DICTIONARY_ORDER_STATUS_04);
     		    orderDao.update(vo);
        	}
    	}
    	
    	return;
    	
    }
    
    
    /**
     * 订单取消      解冻账户金额
     */
    public void doFreezeAcc(OrderVo  orderVo) {
     	//冻结账户金额
     	FreezeAccVo freezeAccVo=new  FreezeAccVo();
     	freezeAccVo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_01);//销售订单类型
     	freezeAccVo.setPayerId(orderVo.getCustomerId().toString());//客户id
     	freezeAccVo.setBillsId(orderVo.getId());//单据id
     	freezeAccVo.setChangeAmt(orderVo.getAmountMoney()==null?BigDecimal.ZERO:orderVo.getAmountMoney());//金额
     	freezeAccVo.setRemark("销售订单取消");
     	freezeAccVo.setIp("");
     	freezeAccVo.setOperaterId("");
     	freezeAccVo.setOperaterName("");
     	accountChangeService.doOrderCancel(freezeAccVo);
    	return ;
    }

}
