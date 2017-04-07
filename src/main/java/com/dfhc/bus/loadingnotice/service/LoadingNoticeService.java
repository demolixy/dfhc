/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LoadingNoticeService.java
 *
 * 功能描述：  装车通知单服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.loadingnotice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.orgauth.rmuser.service.impl.RmUserService;
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
import com.dfhc.base.product.service.ProductService;
import com.dfhc.base.product.vo.ProductVo;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.loadingnotice.ILoadingNoticeConstants;
import com.dfhc.bus.loadingnotice.dao.LoadingNoticeDao;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.productbatchnumber.service.ProductBatchNumberService;
import com.dfhc.bus.productbatchnumber.vo.ProductBatchNumberVo;
import com.dfhc.pub.server.PushDataVo;
import com.dfhc.pub.server.PushQueue;
import com.dfhc.pub.service.AccountChangeService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.vo.FreezeAccVo;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
/**
 * 装车通知单服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class LoadingNoticeService implements ILoadingNoticeConstants {

    @Autowired
    /**
     * 装车通知单数据访问对象
     */
    private LoadingNoticeDao loadingNoticeDao;
    
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private AccountChangeService accountChangeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private ProductBatchNumberService productBatchNumberService;
    @Autowired 
    private ProductService productService;
    
    private RmUserService getRmUserService(){
    	return (RmUserService) RmBeanFactory.getBean(RmUserService.class.getName());
    }

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(LoadingNoticeVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = loadingNoticeDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "装车通知单插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(LoadingNoticeVo[] vos) {
        String[] ids = loadingNoticeDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "装车通知单插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = loadingNoticeDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "装车通知单删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = loadingNoticeDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "装车通知单删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(LoadingNoticeVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = loadingNoticeDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "装车通知单更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(LoadingNoticeVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "装车通知单批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(LoadingNoticeVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<LoadingNoticeVo> lInsert = new ArrayList<LoadingNoticeVo>();
        List<LoadingNoticeVo> lUpdate = new ArrayList<LoadingNoticeVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new LoadingNoticeVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new LoadingNoticeVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public LoadingNoticeVo get(String id) {
        LoadingNoticeVo vo = loadingNoticeDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = loadingNoticeDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<LoadingNoticeVo> list(Map<String, Object> searchPara, String orderStr) {
        return list(searchPara, orderStr, 1, Integer.MAX_VALUE);
    }

    /**
     * 通过查询条件获得所有的VO对象列表打印方法
     *
     * @return 查询到的VO列表
     */
    public List<LoadingNoticeVo> getPrint(String id) {
    	List<LoadingNoticeVo> lResult = loadingNoticeDao.getPrint(id);
        return lResult;
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
    public List<LoadingNoticeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<LoadingNoticeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<LoadingNoticeVo> lResult = loadingNoticeDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<LoadingNoticeVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<LoadingNoticeVo> lResult = loadingNoticeDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(LoadingNoticeVo vo) {
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
    private void verifyInsertVo(LoadingNoticeVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<LoadingNoticeVo> vos) {
        for(LoadingNoticeVo vo:vos){
           verifyUpdateVo(vo);
        }
        loadingNoticeDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<LoadingNoticeVo> vos) {
        for(LoadingNoticeVo vo:vos){
           verifyInsertVo(vo);
        }
        loadingNoticeDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        LoadingNoticeVo vo = loadingNoticeDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(loadingNoticeDao.update(vo)!=1){
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
     * 开具装车单
     * @param request
     * @param jqData
     * @return
     */
	public Map<String, Object> insertLoadingNotice(HttpServletRequest request,LoadingNoticeVo newLoadingNoticeVo,
			String jqData) {
		Map<String, Object> result = new HashMap<String, Object>();
		//提货单
		LadingBillVo  ladingBillVo=ladingBillService.get(newLoadingNoticeVo.getLadingBillId());
		if(ladingBillVo == null){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有找到对应的提货单");
			return result;
		}
		
		String isPondString=StringHelper.isEmpty(newLoadingNoticeVo.getIsNeedWeigh())?ISystemConstant.DICTIONARY_RM_YES_NOT_0:newLoadingNoticeVo.getIsNeedWeigh();
		
		//装车通知单
		LoadingNoticeVo oldLoadingNoticeVo =getLoadingNoticeVo(newLoadingNoticeVo.getLadingBillId());
		if(oldLoadingNoticeVo != null){
			//修改装车单  以及批次
			//生成批次  并修改对应中间表的出库数量
			productBatchNumberService.updateProductBath(request,result,ladingBillVo,oldLoadingNoticeVo,jqData);
			
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(result.get(ISystemConstant.AJAX_STATUS))){
				 return result;
			}

			oldLoadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01);
			oldLoadingNoticeVo.setIsNeedWeigh(isPondString);//是否需要过磅
			oldLoadingNoticeVo.setGateNum(newLoadingNoticeVo.getGateNum());//门号
			oldLoadingNoticeVo.setToneNum(ladingBillVo.getActualShippingWeight().longValue());//实际发货重量
			RmVoHelper.markModifyStamp(request, oldLoadingNoticeVo);
			update(oldLoadingNoticeVo);

		}else{
			//新增
			oldLoadingNoticeVo=new LoadingNoticeVo();
			oldLoadingNoticeVo.setId(RmIdFactory.requestId(TABLE_NAME));
			oldLoadingNoticeVo.setLadingBillId(ladingBillVo.getId());
			oldLoadingNoticeVo.setLadingBillCode(ladingBillVo.getLadingBillCode());
			oldLoadingNoticeVo.setLicensePlate(ladingBillVo.getLicensePlate());//车牌号码
			oldLoadingNoticeVo.setProductId(ladingBillVo.getProductId());
			oldLoadingNoticeVo.setProductName(ladingBillVo.getProductName());
			oldLoadingNoticeVo.setProductModelNumber(ladingBillVo.getProductModelNumber());
			RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
			oldLoadingNoticeVo.setStoremanId(Long.valueOf(userVo.getId()));
			oldLoadingNoticeVo.setStoreman(userVo.getName());//保管员
		
			//生成批次  并修改对应中间表的出库数量
			result=productBatchNumberService.insertProductBath(request,result,ladingBillVo,oldLoadingNoticeVo,jqData);
			
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(result.get(ISystemConstant.AJAX_STATUS))){
				 return result;
			}
			
			oldLoadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01);
			oldLoadingNoticeVo.setIsNeedWeigh(isPondString);//是否需要过磅
			oldLoadingNoticeVo.setGateNum(newLoadingNoticeVo.getGateNum());//门号
			oldLoadingNoticeVo.setToneNum(ladingBillVo.getActualShippingWeight().longValue());//实际发货重量
			RmVoHelper.markCreateStamp(request, oldLoadingNoticeVo);
			insert(oldLoadingNoticeVo);
			
		}
		
		//是否需要过磅
		ladingBillVo.setIsPound(isPondString);
		ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10);
		//设置分车时间    方便车辆队列接口排序 add by longsebo 2017-03-24
		ladingBillVo.setAllocationVehicleTime(DateUtil.getNowTimestamp());
		RmVoHelper.markModifyStamp(request, ladingBillVo);
		ladingBillService.update(ladingBillVo);

		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "操作成功！");
		
		//触发socket推送
		String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_03);
		PushDataVo vo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05);
		PushQueue.getInstance().put(vo);
		return result;
	}

	
	
	/**
     * 装车单手持列表展示
     * @param request
     * @param operation 操作符
     * @return
     */
	public Map<String, Object> handList(HttpServletRequest request,
			String operation) {
		//抢单
		if(ISystemConstant.DICTIONARY_OPERATION_01.equals(operation)){
			
			return grabList(request);
		}else if(ISystemConstant.DICTIONARY_OPERATION_02.equals(operation) || ISystemConstant.DICTIONARY_OPERATION_03.equals(operation)){//保运主管待确认装车（已分配待确认--》都未确认）
			
			return supervisor(request, operation);
		}else if(ISystemConstant.DICTIONARY_OPERATION_04.equals(operation)){//今日已完成装车单 
			
			return todayCompleteLoadingBill(request, operation);
		}else if(ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_01.equals(operation) || ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_02.equals(operation)
				|| ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_03.equals(operation)){//待检验车辆		
			return checkCarList(operation, request);
		}else if(ISystemConstant.DICTIONARY_OPERATION_05.equals(operation) || ISystemConstant.DICTIONARY_OPERATION_08.equals(operation)){//待确认装车 我的待装车（叉车）
			
			return forklift(request, operation);
			
		}else{
			//故意破坏  清空session
			request.getSession().invalidate();
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			return result;
		}
	}

	/**
	 * 待确认装车 我的待装车（叉车）
	 * @param request
	 * @param operation
	 * @return
	 */
	private Map<String, Object> forklift(HttpServletRequest request,
			String operation) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		RmUserVo userVo = pubParamService.checkUser(variable, request);
		
		if(null != variable.get(ISystemConstant.AJAX_STATUS)){
			
			return variable;
		}
		
		if(!ISystemConstant.DICTIONARY_USER_TYPE_1.equals(userVo.getUser_type())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			//故意破坏  清空session
			request.getSession().invalidate();
			return variable;
		}
		variable.put("forkliftMonitorId", userVo.getId());
		if(ISystemConstant.DICTIONARY_OPERATION_05.equals(operation)){
			variable.put("status", ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_03);
			variable.put("forkliftTruckIsConfirm", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
			variable.put("supervisorIsConfirm", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		}else {
			variable.put("status", ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_02);
		}
		String pageNo = request.getParameter(ISystemConstant.DICTIONARY_PAGE_INDEX);
		
		RmPageVo pageVo = RmJspHelper.transctPageVo(request, getCount(variable));
		
		pageVo.setCurrentPage(StringHelper.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo));
		
		List<LoadingNoticeVo> list = list(variable, null, pageVo.getStartIndex(), pageVo.getPageSize());
		variable.clear();
		
		if(CollectionUtils.isEmpty(list)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			variable.put(ISystemConstant.AJAX_MESSAGE, "暂无数据！");
		}else{
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			
			variable.put(ISystemConstant.AJAX_LIST, list);
		}
		
		return variable;
	}

	/**
	 * 保运主管手持列表（已确认待装车、待确认装车）
	 * @param request
	 * @return
	 */
	private Map<String, Object> supervisor(HttpServletRequest request, String operation) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		RmUserVo userVo = pubParamService.checkUser(variable, request);
		
		if(null != variable.get(ISystemConstant.AJAX_STATUS)){
			
			return variable;
		}
		
		if(!ISystemConstant.DICTIONARY_USER_TYPE_2.equals(userVo.getUser_type())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			//故意破坏  清空session
			request.getSession().invalidate();
			return variable;
		}
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
//		variable.put("dispatchTime", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		//保运主管待确认装车（保运主管未确认的）
		if(ISystemConstant.DICTIONARY_OPERATION_02.equals(operation)){
			variable.put("statuss", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01, ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_02, ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_03));
			variable.put("supervisorIsConfirm", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			
		}else{//已确认待装车（叉车班长未确认）
			variable.put("forkliftTruckIsConfirm", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("operationSupervisorId", userVo.getId());
			variable.put("supervisorIsConfirm", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
		}
		
		String pageNo = request.getParameter(ISystemConstant.DICTIONARY_PAGE_INDEX);
		
		RmPageVo pageVo = RmJspHelper.transctPageVo(request, getCount(variable));
		int currentPage= StringHelper.isEmpty(pageNo)?1:Integer.valueOf(pageNo);
		pageVo.setCurrentPage(currentPage);
		if(currentPage>pageVo.getPageCount()){
			
            variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			
			variable.put(ISystemConstant.AJAX_LIST, new ArrayList());
			
			return variable;
		}
		
		
		List<LoadingNoticeVo> list = list(variable, null, pageVo.getStartIndex(), pageVo.getPageSize());
		
		variable.clear();
		
		if(CollectionUtils.isEmpty(list)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			
			variable.put(ISystemConstant.AJAX_MESSAGE, "暂无数据!");
			variable.put("totalCount", "0");//存放总页数
			
		}else{
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			
			variable.put(ISystemConstant.AJAX_LIST, list);
			variable.put("totalCount", pageVo.getPageCount());//存放总页数
		}
		
		return variable;
	}

	/**
	 * 检查车辆列表
	 * @param operation
	 * @param request
	 * @return
	 */
	private Map<String, Object> checkCarList(String operation,
			HttpServletRequest request) {
		Map<String, Object> variable = new HashMap<String, Object>();
		RmUserVo userVo = pubParamService.checkUser(variable, request);
		
		if(null != variable.get(ISystemConstant.AJAX_STATUS)){
			
			return variable;
		}
		variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
		
		variable.put("delete_flag8", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		String orderStr = null;
		//待检验车辆
		if(ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_01.equals(operation) || ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_02.equals(operation)
				|| ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_03.equals(operation)){
			
			variable.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("waitCheck", "waitCheck");
			variable.put("storeMode", ISystemConstant.DICTIONARY_STORE_MODE_00);
//			variable.put("rowStatus", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_ROW_STATUS_02, ISystemConstant.DICTIONARY_ROW_STATUS_07, ISystemConstant.DICTIONARY_ROW_STATUS_03));
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
			variable.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04);
			variable.put("checkStatus", ISystemConstant.DICTIONARY_CHECK_STATUS_2);
			orderStr = "BUS_LADING_BILL.IN_TIME asc";
		}else{//已检验车辆
			variable.put("check", "check");
			variable.put("notCheckStatus", ISystemConstant.DICTIONARY_CHECK_STATUS_2);
			
			
		}
		
		String pageNo = request.getParameter(ISystemConstant.DICTIONARY_PAGE_INDEX);
		
		RmPageVo pageVo = RmJspHelper.transctPageVo(request, ladingBillService.getCount(variable));
		int currentPage = StringHelper.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo);
		pageVo.setCurrentPage(currentPage);
		List<LadingBillVo> list = ladingBillService.list(variable, null, pageVo.getStartIndex(), pageVo.getPageSize());
		
//		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		variable.put("check", "check");
//		
//		if(ISystemConstant.DICTIONARY_OPERATION_06.equals(operation)){
//			variable.put("rowNum", "rowNum");
//			variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
//			variable.put("checkStatus", ISystemConstant.DICTIONARY_CHECK_STATUS_2);
//			
//		}else if(ISystemConstant.DICTIONARY_OPERATION_07.equals(operation)){
//			
//			variable.put("notCheckStatus", ISystemConstant.DICTIONARY_CHECK_STATUS_2);
//			
//		}
//		List<LoadingNoticeVo> list = list(variable, null);
		
		if(CollectionUtils.isEmpty(list)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "暂无数据！");
			
			return variable;
		}
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_LIST, list);
		variable.put("totalCount", pageVo.getRecordCount());
		
		return variable;
	}

	

	/**
	 * 今日已完成装车单 (叉车班长和保运主管)
	 * @param request
	 * @return
	 */
	
	private Map<String, Object> todayCompleteLoadingBill(
			HttpServletRequest request, String operation) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		RmUserVo userVo = pubParamService.checkUser(result, request);
		
		if(null != result.get(ISystemConstant.AJAX_STATUS)){
			
			return result;
		}
		if(ISystemConstant.DICTIONARY_USER_TYPE_1.equals(userVo.getUser_type())){
			
			result.put("forkliftMonitorId", userVo.getId());
		}else if(ISystemConstant.DICTIONARY_USER_TYPE_2.equals(userVo.getUser_type())){
			
			result.put("operationSupervisorId", userVo.getId());
		}else{
			HttpSession session = request.getSession();
			
			//故意破坏  直接清空session
			session.invalidate();
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			return result;
		}
		
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		result.put("status", ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_04);
		
		
		result.put("jobTime", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));

		
		String pageNo = request.getParameter(ISystemConstant.DICTIONARY_PAGE_INDEX);
		
		RmPageVo pageVo = RmJspHelper.transctPageVo(request, getCount(result));
		
		pageVo.setCurrentPage(StringHelper.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo));
		
		List<LoadingNoticeVo> list = list(result, null, pageVo.getStartIndex(), pageVo.getPageSize());
		result.clear();
		
		
		if(CollectionUtils.isEmpty(list)){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "暂无数据！");
		}else{
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			result.put(ISystemConstant.AJAX_LIST, list);
		}
		return result;
	}

	

	/**
	 * 抢单列表
	 * @param request
	 * @return
	 */
	private Map<String, Object> grabList(HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		RmUserVo user = pubParamService.checkUser(result, request);
		
		if(null != result.get(ISystemConstant.AJAX_STATUS)){
			
			return result;
		}
		
		
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		result.put("status", ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01);
		
		List<LoadingNoticeVo> list = list(result, null);
		result.clear();
		
		
		if(CollectionUtils.isEmpty(list)){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "暂无装车单待抢！");
		}else{
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
			result.put(ISystemConstant.AJAX_LIST, list);
		}
		return result;
	}

	/**
	 * 叉车班长抢单
	 * @param request
	 * @param loadingNoticeId 装车单id
	 * @return
	 */
	public Map<String, Object> updateforkliftMonitorGrabBill(
			HttpServletRequest request, String loadingNoticeId) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		RmUserVo userVo = pubParamService.checkUser(variable, request);
		
		if(null != variable.get(ISystemConstant.AJAX_STATUS)){
			
			return variable;
		}
		
		LoadingNoticeVo loadingNoticeVo = get(loadingNoticeId);
		
		if(!StringHelper.isEmpty(loadingNoticeVo.getDelete_flag()) && !ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(loadingNoticeVo.getDelete_flag())){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "该装车通知单已删除！");
			
			return variable;
		}
		
		if(!ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01.equals(loadingNoticeVo.getStatus())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "该装车通知单不是待抢状态！");
			
			return variable;
		}
		loadingNoticeVo = loadingNoticeDao.getForLock(loadingNoticeId);
		
		if(!ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01.equals(loadingNoticeVo.getStatus())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "该装车通知单不是待抢状态！");
			
			return variable;
		}
		
		loadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_02);
		
		
		
		loadingNoticeVo.setForkliftMonitor(userVo.getName());
		
		loadingNoticeVo.setForkliftMonitorId(Long.valueOf(userVo.getId()));
		
		RmVoHelper.markModifyStamp(request, loadingNoticeVo);
		update(loadingNoticeVo);
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "抢单成功！");
		
		return variable;
	}

	/**
	 * 待确认装车单确认
	 * @param request
	 * @param loadingNoticeId
	 * @return
	 */
	public Map<String, Object> updateToBeIdentifiedConfirm(
			HttpServletRequest request, String loadingNoticeId) {
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		
		Map<String, Object> variable = new HashMap<String, Object>();
		if(!ISystemConstant.DICTIONARY_USER_TYPE_1.equals(userVo.getUser_type()) && !ISystemConstant.DICTIONARY_USER_TYPE_2.equals(userVo.getUser_type())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "不具备权限！");
			
			return variable;
		}
		LoadingNoticeVo loadingNoticeVo = get(loadingNoticeId);
		
		if(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_04.equals(loadingNoticeVo.getStatus())){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "该装车单已确认！");
			
			return variable;
		}
		
		if(!ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_03.equals(loadingNoticeVo.getStatus())){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "该装车单不是待确认状态！");
			
			return variable;
		}
		if(ISystemConstant.DICTIONARY_USER_TYPE_1.equals(userVo.getUser_type())){//叉车班长
			
			//************叉车班长在装车是确认  ************//
//			if(!userVo.getId().equals(loadingNoticeVo.getForkliftMonitorId() + "")){
//				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
//				variable.put(ISystemConstant.AJAX_MESSAGE, "不是抢单的叉车班长！");
//				
//				return variable;
//			}
//			if(loadingNoticeVo.getForkliftTruckConfirmTime() != null){
//				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
//				variable.put(ISystemConstant.AJAX_MESSAGE, "您已确认装车完成，无需再次确认！");
//				
//				return variable;
//			}
//			loadingNoticeVo.setForkliftTruckIsConfirm(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
//			loadingNoticeVo.setForkliftTruckConfirmTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
//			
//			//判断保运主管是否确认
//			if(null != loadingNoticeVo.getSupervisorConfirmTime()){
//				loadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_04);
//				
//				//二次过磅 调用接口结算
//				accountChangeService.doGoodsBillComplete(getOrderVoForSettlement(variable, loadingNoticeVo, request));
//				//回填提货单
//				ladingBillService.supplement(loadingNoticeVo.getLadingBillId(), new BigDecimal(loadingNoticeVo.getToneNum()), variable, request);
//			}
			
		}else{
			
			if(loadingNoticeVo.getSupervisorConfirmTime() != null){
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "您已确认装车完成，无需再次确认！");
				
				return variable;
			}
			loadingNoticeVo.setSupervisorIsConfirm(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
			loadingNoticeVo.setSupervisorConfirmTime(DateUtil.getNowTimestamp());
			loadingNoticeVo.setOperationSupervisorId(Long.valueOf(userVo.getId()));
			loadingNoticeVo.setOperationSupervisor(userVo.getName());
			//判断叉车班长是否确认
			if(null != loadingNoticeVo.getForkliftTruckConfirmTime()){
				loadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_04);
				
				LadingBillVo ladingBillVo = ladingBillService.get(loadingNoticeVo.getLadingBillId());
				
				//此处只考虑副产品
				if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(ladingBillVo.getBillType())){
					
					//二次过磅 调用接口结算
					accountChangeService.doGoodsBillComplete(getOrderVoForSettlement(variable, loadingNoticeVo, request));
					
					//回填提货单
					ladingBillService.supplement(loadingNoticeVo.getLadingBillId(), new BigDecimal(loadingNoticeVo.getToneNum()), variable, request);
					
				}
				
//				if(!ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(ladingBillVo.getIsPound())){
					
					//叉车班长保运主管全都确认后修改提货单状态
					ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06);
					
					ladingBillService.update(ladingBillVo);
//				}
				
				
			}
			
		}
		RmVoHelper.markModifyStamp(request, loadingNoticeVo);
		update(loadingNoticeVo);
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "确认装车完成成功！");
		
		return variable;
	}
	
	private FreezeAccVo getOrderVoForSettlement(Map<String, Object> variable, LoadingNoticeVo billVo, HttpServletRequest request){
		variable.put("loadingBillId", billVo.getId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("loadingNoticeOrder", "loadingNoticeOrder");
		
		OrderVo orderVo = orderService.getOrderVo(variable);
		
		FreezeAccVo vo = new FreezeAccVo();
		vo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_03);
		vo.setBillsId(billVo.getId());
		
		vo.setPayerId(String.valueOf(orderVo.getCustomerId()));
		
		vo.setChangeAmt(new BigDecimal(billVo.getToneNum()).multiply(orderVo.getProductUnitPrice()).setScale(4, BigDecimal.ROUND_HALF_EVEN));
		
		vo.setRemark("装车确认");
		
		vo.setIp(RmProjectHelper.getIp(request));
		
		return vo;
	}
	
	
	
	/**
	 * 我的装车确认单
	 * @param request
	 * @param loadingNoticeId
	 * @return
	 */
	public Map<String, Object> myToBeLoadingBill(HttpServletRequest request,
			String loadingNoticeId) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		
		variable.put("forkliftMonitorId", userVo.getId());
		variable.put("id", loadingNoticeId);
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("myToBeLoadingBill", "myToBeLoadingBill");
		
		LoadingNoticeVo loadingNoticeVo = getLoadingNoticeVo(variable);
		variable.clear();
		
		if(null == loadingNoticeVo){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "没有找到装车单");
			return variable;
		}
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_BEAN, loadingNoticeVo);
		return variable;
	}

	
	public LoadingNoticeVo getLoadingNoticeVo(Map<String, Object> params, boolean flag){
		
		List<LoadingNoticeVo> list = list(params, null, 1, -1, flag);
		
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
	public LoadingNoticeVo getLoadingNoticeVo(Map<String, Object> params){
		
		
		return getLoadingNoticeVo(params, false);
	}

	/**
	 * 叉车班长装车
	 * @param request
	 * @param vo
	 * @return
	 */
	public Map<String, Object> updateDriverLoadingCar(
			HttpServletRequest request, LoadingNoticeVo vo) {
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		Map<String, Object> variable = new HashMap<>();
		
		if(userVo == null){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "2");
			
			return variable;
		}
		LoadingNoticeVo loadingNoticeVo = null;
		if(ISystemConstant.DICTIONARY_USER_TYPE_1.equals(userVo.getUser_type())){//叉车班长
			
			loadingNoticeVo = get(vo.getId());
			
			if(!userVo.getId().equals(loadingNoticeVo.getForkliftMonitorId()+"")){
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "2");
				//恶意操作 清除session
				request.getSession().invalidate();
				return variable;
			}
			
			
			loadingNoticeVo.setTruckInputBreakBagNum(vo.getTruckInputBreakBagNum());
			loadingNoticeVo.setAttribute1(vo.getAttribute1());
			loadingNoticeVo.setAttribute2(vo.getAttribute2());
			
			loadingNoticeVo.setForkliftMonitor(userVo.getName());
			loadingNoticeVo.setForkliftMonitorId(Long.valueOf(userVo.getId()));
			loadingNoticeVo.setForkliftTruckConfirmTime(DateUtil.getNowTimestamp());
			loadingNoticeVo.setForkliftTruckIsConfirm(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
			loadingNoticeVo.setJobTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
			
			//判断保运主管是否确认 如果确认 状态修改已确认状态  提货单状态修改为提货完成 否则  不改状态
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(loadingNoticeVo.getSupervisorIsConfirm())){
				loadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_04);	
				
				LadingBillVo ladingBillVo = ladingBillService.get(loadingNoticeVo.getLadingBillId());
				//填写装车通知单实际发货件数(袋)=提货单.实际发货重量 除以产品管理.袋装标重  add by longsebo 2017-03-21
				
				BigDecimal bg[] =ladingBillVo.getActualShippingWeight().divideAndRemainder(getBagWeight(ladingBillVo.getProductId())) ;
				Long actualDeliveryNumber = bg[0].longValue() ;
				//如果余数大于0，则加1
				if(bg[1].compareTo(BigDecimal.ZERO)>0){
					actualDeliveryNumber = actualDeliveryNumber.longValue()+1;
				}
				
				loadingNoticeVo.setActualDeliveryNumber(actualDeliveryNumber);
				//此处只考虑副产品
				if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(ladingBillVo.getBillType())){
					
					//二次过磅 调用接口结算
					accountChangeService.doGoodsBillComplete(getOrderVoForSettlement(variable, loadingNoticeVo, request));
					
					//回填提货单
					ladingBillService.supplement(loadingNoticeVo.getLadingBillId(), new BigDecimal(loadingNoticeVo.getToneNum()), variable, request);
					
				}
				ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06);
				
				ladingBillService.update(ladingBillVo);
				//触发socket推送:接口九增加仓库数据推送条件：保运主管确认装车完成
				String pushTarget = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION, ISystemConstant.DICTIONARY_SOCKET_03);
				PushDataVo pushvo = new PushDataVo(pushTarget, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06);
				PushQueue.getInstance().put(pushvo);								
			}else{
				loadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_03);	
			}
			
		}else{
			//恶意操作 清除session 重新登录
			request.getSession().invalidate();
			variable.put(ISystemConstant.AJAX_STATUS, "2");
			return variable;
		}
		
		RmVoHelper.markModifyStamp(request, loadingNoticeVo);
		update(loadingNoticeVo);
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "装车成功！");
		return variable;
	}
	
	/**
	 * 根据产品id获取袋装标重	
	 * @param productId  产品id
	 * @return 袋装标重
	 */
	private BigDecimal getBagWeight(String productId) {
		ProductVo vo = productService.get(productId);
		if(vo==null){
			throw new PjException("产品id:"+productId+"不存在!");
		}		
		return vo.getBag_weight();
	}

	/**
	    * 获取有效的通知单vo
	    * @param id  提货单id
	    * @return
	    */
	   public LoadingNoticeVo getLoadingNoticeVo(String  id){
		   Map<String, Object> params  =new HashMap<String, Object>();
		   params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		   params.put("ladingBillId",id);
		   params.put("statuss","'"+ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_01+"','"+ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_02+"'" +
		   		",'"+ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_03+"','"+ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_04+"'");
		   
		   List<LoadingNoticeVo> list = list(params, null, 1, -1, false);
		   return CollectionUtils.isEmpty(list) ? null : list.get(0);
	   }
	   

}
