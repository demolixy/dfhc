/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CarDistributionPlanService.java
 *
 * 功能描述：  汽运配送计划服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.cardistributionplan.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.bus.cardistributionplan.ICarDistributionPlanConstants;
import com.dfhc.bus.cardistributionplan.dao.CarDistributionPlanDao;
import com.dfhc.bus.cardistributionplan.vo.CarDistributionPlanVo;
import com.dfhc.bus.carrequisitionplan.ICarRequisitionPlanConstants;
import com.dfhc.bus.carrequisitionplan.vo.CarRequisitionPlanVo;
import com.dfhc.bus.distributionplan.IDistributionPlanConstants;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.DoLadingBillVo;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.ladingbillchange.vo.LadingBillChangeVo;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.bus.rownumber.service.RowNumberService;
import com.dfhc.bus.rownumber.vo.RowNumberVo;
import com.dfhc.bus.trainsrequisitionplan.vo.TrainsRequisitionPlanVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.service.SequenceService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.PinyinUtil;
import com.dfhc.util.StringHelper;

import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;

import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 汽运配送计划服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class CarDistributionPlanService implements ICarDistributionPlanConstants {

	@Autowired
	private SequenceService sequenceService;
    @Autowired
    /**
     * 汽运配送计划数据访问对象
     */
    private CarDistributionPlanDao carDistributionPlanDao;

    @Autowired
    private  LadingBillService ladingBillService;
    
    @Autowired
    private  PubParamService pubParamService;
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(CarDistributionPlanVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = carDistributionPlanDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "汽运配送计划插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(CarDistributionPlanVo[] vos) {
        String[] ids = carDistributionPlanDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "汽运配送计划插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = carDistributionPlanDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "汽运配送计划删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = carDistributionPlanDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "汽运配送计划删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(CarDistributionPlanVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = carDistributionPlanDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "汽运配送计划更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(CarDistributionPlanVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "汽运配送计划批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(CarDistributionPlanVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<CarDistributionPlanVo> lInsert = new ArrayList<CarDistributionPlanVo>();
        List<CarDistributionPlanVo> lUpdate = new ArrayList<CarDistributionPlanVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new CarDistributionPlanVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new CarDistributionPlanVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public CarDistributionPlanVo get(String id) {
        CarDistributionPlanVo vo = carDistributionPlanDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = carDistributionPlanDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<CarDistributionPlanVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<CarDistributionPlanVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<CarDistributionPlanVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<CarDistributionPlanVo> lResult = carDistributionPlanDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<CarDistributionPlanVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<CarDistributionPlanVo> lResult = carDistributionPlanDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(CarDistributionPlanVo vo) {
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
    private void verifyInsertVo(CarDistributionPlanVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<CarDistributionPlanVo> vos) {
        for(CarDistributionPlanVo vo:vos){
           verifyUpdateVo(vo);
        }
        carDistributionPlanDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<CarDistributionPlanVo> vos) {
        for(CarDistributionPlanVo vo:vos){
           verifyInsertVo(vo);
        }
        carDistributionPlanDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        CarDistributionPlanVo vo = carDistributionPlanDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(carDistributionPlanDao.update(vo)!=1){
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
    	CarDistributionPlanVo  bean=get(id);
        	   
	   //改成已提交
	   if(ISystemConstant.DICTIONARY_PLAN_STATUS_12.equals(type)){
		   if(!ISystemConstant.DICTIONARY_PLAN_STATUS_11.equals(bean.getStatus())){
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
     *  汽运配送关联车辆的保存提交
     *  @author  luy
     */
     public  Map<String, Object>  doZhuToLadingBillList(HttpServletRequest request,CarDistributionPlanVo  oldVo,String statusFlag){
    	Map<String, Object> result=new  HashMap<>();
    	
		RmUserVo userVo = (RmUserVo) RmProjectHelper.getRmUserVo(request);
    	
 		String jqData = request.getParameter("jqData");
 		if(StringHelper.isEmpty(jqData)){
 			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
 			result.put(ISystemConstant.AJAX_MESSAGE, "没有创建提货单，不需要保存提交！");
 			return result;
 		}

 		//获取提货单中间vo
 		DoLadingBillVo  doLadingBillVo=getDoLadingBillVo(request, userVo, oldVo, statusFlag);
 		
 		try {
 	 		//修改提货单信息
 			ladingBillService.inserZhuToLadingBillList(jqData, request, doLadingBillVo,result);

 			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
 				return result;
 			}
 			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
 			result.put(ISystemConstant.AJAX_MESSAGE, "操作成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
  	 	return  result;
     }
    
     /**
      * 转成提货单中间vo
      *  @param  request 
      *  @param  userVo
      *  @param  object  计划单
      *  @param  statusFlag  状态标识
      *  @return  doLadingBillVo  提货单中间vo
      *  @author  luy
      */
      public  DoLadingBillVo  getDoLadingBillVo(HttpServletRequest request,RmUserVo userVo,Object object,String statusFlag){
    	  DoLadingBillVo  doLadingBillVo=new DoLadingBillVo();
    	  doLadingBillVo.setInputPerson(userVo.getName());
    	  doLadingBillVo.setInputPersonId(Long.valueOf(userVo.getId()));
  		  if("commit".equals(statusFlag)){
  			  //提交
  			  doLadingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
  		  }else if("save".equals(statusFlag)){
  			  //保存
  			  doLadingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00);
  		  }else if("audit".equals(statusFlag)){
  			  //审核通过
  			  
  			  //TODO 此处提货单状态不能直接写死 需要判断是否签到  已签到03  未签到02  其他计划订单 同样
  			  doLadingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02);
  		  }
		
    	  if(object.getClass().equals(CarDistributionPlanVo.class)){
    		  //主产品自提       汽运配送
    		  CarDistributionPlanVo  carDistributionPlanVo=(CarDistributionPlanVo)object;
    		  
    		  doLadingBillVo.setOrderPlanId(carDistributionPlanVo.getId());
    		  doLadingBillVo.setOrderPlanCode(carDistributionPlanVo.getPlanCode());
    		  doLadingBillVo.setProductId(carDistributionPlanVo.getProductId());
    		  doLadingBillVo.setProductName(carDistributionPlanVo.getGoodsName());
    		  doLadingBillVo.setBillType(ISystemConstant.DICTIONARY_BILL_TYPE_02);//汽运配送
    		  doLadingBillVo.setShipMode(carDistributionPlanVo.getShipMode());
    		  doLadingBillVo.setShipModeCode(carDistributionPlanVo.getShipModeCode());
    		  doLadingBillVo.setShipperCode(carDistributionPlanVo.getShipperCode());
    		  doLadingBillVo.setEstimatedLoadingTime(carDistributionPlanVo.getSendCarDate());
    		  doLadingBillVo.setReceivingAddress(carDistributionPlanVo.getShippingAddress());
    		  doLadingBillVo.setReceivingPerson(carDistributionPlanVo.getCustomerName());
    		  doLadingBillVo.setRemark(carDistributionPlanVo.getRemark());
    		  doLadingBillVo.setCustomerId(carDistributionPlanVo.getCustomerId()+"");
    		  doLadingBillVo.setPlanCarNum(carDistributionPlanVo.getPlanCarNum());
    		  doLadingBillVo.setProductUnitPrice(carDistributionPlanVo.getUnitPrice());
    			//计划里程
    		  doLadingBillVo.setPlannedMileage(carDistributionPlanVo.getPlannedMileage()==null?BigDecimal.ZERO:carDistributionPlanVo.getPlannedMileage());

    	  }else{
    		  
    	  }

    	  return  doLadingBillVo;
      }
 
     
      
      /**
       *  主产品自提 添加
       *  @author  luy
       */
       public  Map<String, Object>  addZhuToLadingBillList(HttpServletRequest request,CarDistributionPlanVo  newVo,String statusFlag){
    	    //添加主表
    	    doSaleInsert(request, newVo, ISystemConstant.DICTIONARY_PLAN_STATUS_12);

    	    //添加提货单
	    	Map<String, Object> result=doZhuAddLadingBillList(request, newVo, statusFlag);
	  		
	    	if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
	  			return result;
	  		}
	  		
	    	//添加主表
        	RmVoHelper.markCreateStamp(request,newVo);  //打创建时间,IP戳
        	carDistributionPlanDao.insert(newVo);  //插入单条记录
 
	    	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
	  		result.put(ISystemConstant.AJAX_MESSAGE, "操作成功");
	      	return  result;
       }
 	
       
       
       /**
        *  主产品自提
        *  销售自提计划        初始化
        *  @author  luy
        */
        public  void   doSaleInsert(HttpServletRequest request,CarDistributionPlanVo  vo,String status){
        	//Id
            vo.setId(RmIdFactory.requestId(TABLE_NAME)); //获得id
            //状态
    		vo.setStatus(status);
    		//业务类型  ：  主产品自提计划
        	vo.setBusinessType(ISystemConstant.DICTIONARY_PLAN_TYPE_01);
        	//计划单号
    		vo.setPlanCode(pubParamService.getSequenceNumber(IDistributionPlanConstants.ZHU_PLAN_CODE));
        	//制表日期
        	vo.setMakeDate(new java.sql.Date(DateUtil.getNowDate().getTime()));
//        	//剩余车数==计划车数
//        	vo.setRemainderCarNum(vo.getPlanCarNum());
//        	//剩余数量（吨）==数量（吨）
//        	vo.setRemainderNum(vo.getNum());
        	//发货公司编码  默认为榆林库
        	vo.setShipperCode(ISystemConstant.DICTIONARY_SHIPPER_CODE_01);
    		//计划里程
        	vo.setPlannedMileage(BigDecimal.ZERO);
    		//实际里程
        	vo.setFactMileage(BigDecimal.ZERO);
    		//计价里程
        	vo.setValuationMileage(BigDecimal.ZERO);
    		//实际计价里程
        	vo.setFactValuationMileage(BigDecimal.ZERO);
    		//结算运价
        	vo.setSettlementFreight(BigDecimal.ZERO);
    		//主产品价格不用维护
    		vo.setPrice(BigDecimal.ZERO);
    		//主产品单价
    		vo.setUnitPrice(BigDecimal.ZERO);

        }
        
        
    /**
     * 变车管理 验证 新计划单
     * @param  request
     * @param  planId  计划id
     */
    public void checkNewPlan(HttpServletRequest request,String planId) {

	   //汽运配送计划
	   CarDistributionPlanVo  newCarDistributionPlanVo=get(planId);
	   if(newCarDistributionPlanVo==null){
           throw new PjException("没找到新汽运配送计划");
	   }
	   
	   //02  待分配车辆  12提交（客户自提）
	   String  status=newCarDistributionPlanVo.getStatus();
	   if(!(ISystemConstant.DICTIONARY_PLAN_STATUS_02.equals(status) ||ISystemConstant.DICTIONARY_PLAN_STATUS_12.equals(status)) ){
           throw new PjException("新计划单非待分配车辆状态");
	   }
 	   
	   //查询改计划单下的有效提货单
       Long remainderCarNum=newCarDistributionPlanVo.getRemainderCarNum();
       if(Integer.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_0) >= remainderCarNum){
           throw new PjException("新计划单已分配完车辆");
       }
 	   return;
    }

    /**
     * 作废提货单回填汽运配送计划
     * @param request
     * @param vo
     * @param variable
     */
	public void updateBackfillCarDisPlan(HttpServletRequest request,
			LadingBillVo vo, Map<String, Object> variable) {
		variable.put("id", vo.getOrderPlanId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		List<CarDistributionPlanVo> list = list(variable, null);
		
		CarDistributionPlanVo carDistributionPlanVo = list.get(0);
		
		carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderCarNum()+1);
		carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderNum()+vo.getNuclearLoad().longValue());
		
		update(carDistributionPlanVo);
	}
	
	
	
    /**
     * 回填汽运配送计划   剩余车数
     * @param request
     * @param vo
     * @param variable
     */
	public void doTrainsRequisitionUpdateCarNum(Map<String, Object> variable,String orderPlanId,
			String  type,int carNum,int num) {
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("id", orderPlanId);
		
		List<CarDistributionPlanVo> list = list(variable, null);

		CarDistributionPlanVo carDistributionPlanVo = list.get(0);
		if(ILadingBillConstants.FORMULA_ADD.equals(type)){
			//加法
			carDistributionPlanVo.setRemainderCarNum(carDistributionPlanVo.getRemainderCarNum()+carNum);
			carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderNum()+num);
		}else{
			//减法
			carDistributionPlanVo.setRemainderCarNum(carDistributionPlanVo.getRemainderCarNum()-carNum);
			carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderNum()-num);
		}
		update(carDistributionPlanVo);
	}
 
	
	
    /**
     *  主产品自提添加页面 保存提交
     *  @author  luy
     */
     public  Map<String, Object>  doZhuAddLadingBillList(HttpServletRequest request,CarDistributionPlanVo  oldVo,String statusFlag){
    	Map<String, Object> result=new  HashMap<>();
    	
		RmUserVo userVo = (RmUserVo) RmProjectHelper.getRmUserVo(request);
    	
 		String jqData = request.getParameter("jqData");
 		if(StringHelper.isEmpty(jqData)){
 			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
 			result.put(ISystemConstant.AJAX_MESSAGE, "没有创建提货单，不需要保存提交！");
 			return result;
 		}

 		//获取提货单中间vo
 		DoLadingBillVo  doLadingBillVo=getDoLadingBillVo(request, userVo, oldVo, statusFlag);
 		
 		try {
 	 		//修改提货单信息
 			ladingBillService.inserZhuAddLadingBillList(jqData, request, doLadingBillVo,result,oldVo);

 			if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
 				return result;
 			}
 			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
 			result.put(ISystemConstant.AJAX_MESSAGE, "操作成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
  	 	return  result;
     }

     
     
     /**
      * 更新单条记录
      * 
      * @param vo 用于更新的VO对象
      * @return 成功更新的记录数
      */
     public void doUpdateLogisticsIds(HttpServletRequest request) {
         String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); 
         String[] logisticsIds = RmJspHelper.getArrayFromRequest(request, "logisticsIds"); 
         String[]  logisticsIdNames=RmJspHelper.getArrayFromRequest(request, "logisticsIdNames");
         String[]  plannedMileages=RmJspHelper.getArrayFromRequest(request, "plannedMileages");
         //距离系数
         BigDecimal oneMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01);
	     //运价系数（一般为0.31）
	     BigDecimal twoMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02);
         if (ids != null && ids.length != 0) {
    		 for(int i=0;i<ids.length;i++){
    			 CarDistributionPlanVo   carDistributionPlanVo=get(ids[i]);
    			 if(logisticsIds[i].length()>0 && !ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(logisticsIds[i])){
        			 carDistributionPlanVo.setLogisticsCompanyId(logisticsIds[i]);
        			 carDistributionPlanVo.setLogisticsCompany(logisticsIdNames[i]);
        		     //待分配车辆
        			 carDistributionPlanVo.setStatus(ISystemConstant.DICTIONARY_PLAN_STATUS_02);
    			 }
    			 
    			 if(new BigDecimal(plannedMileages[i]).compareTo((carDistributionPlanVo.getPlannedMileage()==null?BigDecimal.ZERO:carDistributionPlanVo.getPlannedMileage())) != 0){
        			 //计划里程
        			 carDistributionPlanVo.setPlannedMileage(new BigDecimal(plannedMileages[i]));
        		        //实际里程==计划里程
        			 carDistributionPlanVo.setFactMileage(new BigDecimal(plannedMileages[i]));
        		        //计价里程==计划里程*距离系数
        			 carDistributionPlanVo.setValuationMileage(carDistributionPlanVo.getFactMileage().multiply(oneMileage));
        		        //实际计价里程==实际里程*距离系数
        			 carDistributionPlanVo.setValuationMileage(carDistributionPlanVo.getFactMileage().multiply(oneMileage));
        		        //结算运费 == 计价里程*运价系数（一般为0.31）
        			 carDistributionPlanVo.setSettlementFreight(carDistributionPlanVo.getValuationMileage().multiply(twoMileage));
        		        //总运费 == 结算运价*数量
        			 carDistributionPlanVo.setTotalFreight(carDistributionPlanVo.getSettlementFreight().multiply(new BigDecimal(carDistributionPlanVo.getNum())));
        			 
    			 }
    			 
    		     update(carDistributionPlanVo);
    		 }
    	 }
    	 
    	 return ;
     }

 	
    
}
