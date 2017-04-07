/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LadingBillController.java
 *
 * 功能描述：  司机提货单Controller
 * 
 * 版本历史：
 * 
 * 2017-01-05   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.ladingbill.web;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.bus.carrequisitionplan.service.CarRequisitionPlanService;
import com.dfhc.bus.carrequisitionplan.vo.CarRequisitionPlanVo;
import com.dfhc.bus.distributionplan.IDistributionPlanConstants;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.loadingnotice.ILoadingNoticeConstants;
import com.dfhc.bus.loadingnotice.service.LoadingNoticeService;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.bus.orderproductdetail.service.OrderProductDetailService;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.bus.productbatchnumber.service.ProductBatchNumberService;
import com.dfhc.bus.productbatchnumber.vo.ProductBatchNumberVo;
import com.dfhc.bus.productinventory.service.ProductInventoryService;
import com.dfhc.bus.productinventory.vo.ProductInventoryVo;
import com.dfhc.bus.trainsrequisitionplan.service.TrainsRequisitionPlanService;
import com.dfhc.bus.trainsrequisitionplan.vo.TrainsRequisitionPlanVo;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * list                  /ladingbill
 * insert page      GET  /ladingbill/insert
 * insert action    POST /ladingbill/insert
 * update page      GET  /ladingbill/update/{id}
 * update action    POST /ladingbill/update
 * delete action    POST /ladingbill/delete
 * detail                /ladingbill/detail/{id}

 * reference             /ladingbill/reference
 * import page      GET  /ladingbill/import
 * import action    POST /ladingbill/import
 * export custom    GET  /ladingbill/export
 * export action    POST /ladingbill/export
 */

/**
 * 司机提货单 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/ladingbill")
public class LadingBillController implements ILadingBillConstants {

	
	@Autowired
	private   ProductBatchNumberService productBatchNumberService;
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private  ProductInventoryService  productInventoryService;
    @Autowired
    private CarRequisitionPlanService carRequisitionPlanService;
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private TrainsRequisitionPlanService trainsRequisitionPlanService;
    @Autowired
    private OrderProductDetailService orderProductDetailService;
    @Autowired
    private  LoadingNoticeService  loadingNoticeService;
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("queryString", request.getQueryString());
    	model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
    	String operation = pubParamService.commonModel(request, model);
    	if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05.equals(request.getParameter("ladingBillStatus"))){
        	return "/yulinchemical/bus/loadingnotice/listLadingBill";
        }
    	if(ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_COMPANY.equals(operation)){
    		return "/yulinchemical/bus/ladingbill/listLadingBillLogisticsCompany";
    	}else if(ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_COMPANY_HISTORY.equals(operation)){
    		return "/yulinchemical/bus/ladingbill/hisLogLadingBillList";
    	}else if(LADING_BILL_MANAGE_ZHU.equals(operation) ||  LADING_BILL_MANAGE_FU.equals(operation)){
        	//主产品提货单管理 //副产品提货单管理
        	return "/yulinchemical/bus/ladingbill/listLadingBillZhu";
        }
    	
        String loadingNoticeLoading = request.getParameter(ILoadingNoticeConstants.LOADING_NOTICE_LOADING);
        if(LOADING_MANAGEMENT.equals(loadingNoticeLoading)){
        	//装车管理
        	return "/yulinchemical/bus/ladingbill/loadingManagement";
        }else  if(BILL_LIST.equals(loadingNoticeLoading)){
        	//台账
            String billTypeOne = request.getParameter(ILoadingNoticeConstants.BILL_TYPE_ONE);
            if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(billTypeOne)){
            	//副产品台账
            	return "/yulinchemical/bus/ladingbill/billListByOrder";
            }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billTypeOne)){
            	//起运调拨
            	return "/yulinchemical/bus/ladingbill/billListByReq";
            }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billTypeOne)){
            	//铁运调拨
            	return "/yulinchemical/bus/ladingbill/billListByTra";
            }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billTypeOne)){
            	//汽运配送  
            	return "/yulinchemical/bus/ladingbill/billListByDis";
            }else{
            	//自提
            	return "/yulinchemical/bus/ladingbill/billListByDisIn";
            }
        }

    	return "/yulinchemical/bus/ladingbill/listLadingBill";
    }
    /**
     * 实现打印跳转
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp   
     */
    @RequestMapping(value = "printladingbill")
    public String printLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true)String ladingBillId, Model model){
    	String url = null;
    	LadingBillVo vf = ladingBillService.get(ladingBillId);
    	Map<String, Object> searchPara = getQueryCondition(request);//request获得查询条件
    	searchPara.put("id", ladingBillId);
    	if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(vf.getBillType())){//销售订单
    		searchPara.put("byProductPrinting", ISystemConstant.DICTIONARY_BILL_TYPE_01);
    		url = "/yulinchemical/bus/printladingbill/byProductPrinting";
    	
    	}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(vf.getBillType())){
    		searchPara.put("motorPrintting", ISystemConstant.DICTIONARY_BILL_TYPE_02);
    		if(vf.getOrderPlanCode().subSequence(0, 1).equals("D")){    //汽运配送
    			url = "/yulinchemical/bus/printladingbill/motorPrintting";
    			}
    		else{    //客户自提
    			url = "/yulinchemical/bus/printladingbill/customerMentionPrinting";
    			}
    	}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(vf.getBillType())){//汽运调拨计划
    		searchPara.put("remoteAllocationPrinting", ISystemConstant.DICTIONARY_BILL_TYPE_03);//汽运调拨计划
    		url = "/yulinchemical/bus/printladingbill/remoteAllocationPrinting";
    	}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(vf.getBillType())){//铁运调拨&配送计划
    		searchPara.put("trainsPrinting", ISystemConstant.DICTIONARY_BILL_TYPE_04);//铁运调拨条件
    		url = "/yulinchemical/bus/printladingbill/trainsPrinting";
    	}
    	LadingBillVo vo = ladingBillService.getListPrint(searchPara);
    	model.addAttribute(REQUEST_BEAN, vo);
    	return url;
    }
    /**
     * 作废提货单列表
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp   
     */
    @RequestMapping(value ="cancelLadingBillList")
    public String cancelLadingBillList(Model model,HttpServletRequest request){
    	model.addAttribute("queryString",request.getQueryString());
    	model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
    	return "/yulinchemical/bus/cancelladingbill/listCancelLadingBill";
    }
    /**
     * 电子提货单待审核列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "ladingBillList")
    public String ladingBillList(Model model, HttpServletRequest request) {
    	model.addAttribute("queryString", request.getQueryString());
    	model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
    	return "/yulinchemical/bus/ladingbill/listLadingBillWaitReviewed";
    }

    /**
     * 运费待结算  （汽配、汽调、铁路）
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "listWaitSettleByTrain")
    public String listApplySettlement(Model model, HttpServletRequest request) {
    	//汽配、汽调、铁路
    	model.addAttribute("billTypeLu", request.getParameter("billType"));
        model.addAttribute("queryString", request.getQueryString());
    	model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        return "/yulinchemical/bus/ladingbill/listWaitSettleByTrain";
    }
    /**
     * ajax 请求列表数据
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "ajaxList")
    public void ajaxList(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        
        String page=request.getParameter("page")==null?"1":request.getParameter("page");
        String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
        int rowsInt=Integer.parseInt(rows);//页大小
        int pageInt=Integer.parseInt(page);//当前页
        Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件
        String operation = request.getParameter(ISystemConstant.DICTIONARY_OPERATION);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        boolean flag = false;
        //查询销售订单提货单
        if("electronicAudit".equals(operation)){
        	//查询所有待审核的提货单  
        	flag = true;
//        	searchPara.put("billType", ISystemConstant.DICTIONARY_BILL_TYPE_01);
        	searchPara.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_01);
        	searchPara.put("electronicAudit", "electronicAudit");
        	searchPara.put("waitStatus", ISystemConstant.DICTIONARY_ROW_STATUS_04);
        	// 过滤副产品过期的
        	searchPara.put("isExpiredByLu", "isExpiredByLu");
        	searchPara.put("isExpiredByLuShow", "isExpiredByLuShow");
        	
        	orderStr = "BUS_ROW_NUMBER.ROW_DATE asc, BUS_LADING_BILL.CREATE_TIME asc";
        }else if(ISystemConstant.DICTIONARY_OPERATION_COORDINATION.equals(operation)){
        	//客户协同
        	orderStr = "BUS_LADING_BILL.LADING_BILL_STATUS asc";
        	searchPara.put(ISystemConstant.DICTIONARY_OPERATION_COORDINATION, ISystemConstant.DICTIONARY_OPERATION_COORDINATION);
        	searchPara.put("customerIdHou", RmProjectHelper.getRmUserId(request));
        }else if(ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_COMPANY.equals(operation)){
        	//协同物流公司
        	searchPara.put("logisticsCompanyId", RmProjectHelper.getRmUserId(request));
        	searchPara.put("coordinationLogisticsCompanyId", "coordinationLogisticsCompanyId");
        }else if(LADING_BILL_MANAGE_ZHU.equals(operation)){
        	//主产品提货单管理
        	searchPara.put("billTypeIn", "'"+ISystemConstant.DICTIONARY_BILL_TYPE_02+"','"+ISystemConstant.DICTIONARY_BILL_TYPE_03+"','"+ISystemConstant.DICTIONARY_BILL_TYPE_04+"'");
        }else if(LADING_BILL_MANAGE_FU.equals(operation)){
        	//副产品提货单管理
        	searchPara.put("billType",ISystemConstant.DICTIONARY_BILL_TYPE_01);
        }else if("cancelLadingBill".equals(operation)){
        	//已作废提货单
        	flag = true;
        	orderStr = "BUS_LADING_BILL.INVALID_TIME desc";
        	searchPara.put("cancelLadingBill",ISystemConstant.DICTIONARY_LADING_BILL_STATUS_09);
        }
        
        String loadingNoticeLoading = request.getParameter(ILoadingNoticeConstants.LOADING_NOTICE_LOADING);
        
        if(ILoadingNoticeConstants.LOADING_NOTICE_LOADING.equals(loadingNoticeLoading)){
//        	searchPara.put("ladingBillStatuses", request.getParameter("statuss"));
        	searchPara.put(ILoadingNoticeConstants.LOADING_NOTICE_LOADING, ILoadingNoticeConstants.LOADING_NOTICE_LOADING);
        	searchPara.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05);
        	searchPara.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
        	searchPara.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
        	searchPara.put("isLoading", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
        	searchPara.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//        	orderStr = "DECODE(BUS_LADING_BILL.LADING_BILL_STATUS,'" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05 + "','1','" + ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03 + "','2')";
        	orderStr = "BUS_LADING_BILL.IN_TIME asc";
        }else  if(LOADING_MANAGEMENT.equals(loadingNoticeLoading)){
        	//装车管理
        	searchPara.put("ladingBillNoticeStatus", ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS);
        	searchPara.put("ladingBillStatuses", "'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05+"','"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10+"'");
        	searchPara.put("isLoading",ISystemConstant.DICTIONARY_RM_YES_NOT_1);
        }else  if(BILL_LIST.equals(loadingNoticeLoading)){
        	//台账
        	String  billTypeOne=request.getParameter(ILoadingNoticeConstants.BILL_TYPE_ONE);
	    	searchPara.put(ILoadingNoticeConstants.LOADING_NOTICE_LOADING_LU,ILoadingNoticeConstants.LOADING_NOTICE_LOADING_LU);
	    	searchPara.put(ILoadingNoticeConstants.BILL_TYPE_ONE,billTypeOne);
	    	searchPara.put(ILoadingNoticeConstants.BILL_TYPE,billTypeOne);
	    	if(ILoadingNoticeConstants.BILL_TYPE_05.equals(billTypeOne)){
	    		searchPara.put(ILoadingNoticeConstants.BILL_TYPE,ISystemConstant.DICTIONARY_BILL_TYPE_02);
	    	}
	    	if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billTypeOne)){//汽运配送台账查询条件
	    		searchPara.put(ISystemConstant.DICTIONARY_SEQUENCE_NUMBER,request.getParameter("sequenceNumber") );
	    		searchPara.put(ISystemConstant.DICTIONARY_PROVINCE, request.getParameter("province"));
	    		searchPara.put(ISystemConstant.DICTIONARY_REGION, request.getParameter("region"));
	    	}
	    	if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billTypeOne)){//汽运调拨台账查询条件
	    		searchPara.put(ISystemConstant.DICTIONARY_RECEIVING_ADDRESS, request.getParameter("receivingAddress"));
	    	}
	    	if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billTypeOne)){//铁运调拨台账查询条件
	    		searchPara.put(ISystemConstant.DICTIONARY_DRIVER_IDCARD_NUMBER, request.getParameter("driverIdCardNumber"));
	    		searchPara.put(ISystemConstant.DICTIONARY_LOGISTICSCOMPANY, request.getParameter("logisticsCompany"));
	    	}

	    	searchPara.put("ladingBillStatuses","'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08+"'");
	    	
	    	searchPara.put(ILadingBillConstants.CUSTOMER_ID,request.getParameter(ILadingBillConstants.CUSTOMER_ID));
	    	searchPara.put(ILadingBillConstants.OUT_TIME_STR,request.getParameter(ILadingBillConstants.OUT_TIME_STR));
	    	searchPara.put(ILadingBillConstants.OUT_TIME_END,request.getParameter(ILadingBillConstants.OUT_TIME_END));
	    	searchPara.put(ILadingBillConstants.STATION_ID,request.getParameter(ILadingBillConstants.STATION_ID));
	    	searchPara.put(ILadingBillConstants.LOGISTICSCOMPANYID,request.getParameter(ILadingBillConstants.LOGISTICSCOMPANYID));

        
        }
        
        //生成收货确认函的参照 查询参数
//      ladingBillService.receiptConfLetterrReferenceParams(searchPara, request);
        String toBeSettlement = request.getParameter("toBeSettlement");
        //提货单   待结算的
        String listWaitSettleLu = request.getParameter(ILoadingNoticeConstants.LIST_WAIT_SETTLE_LU);
        searchPara.put(ILoadingNoticeConstants.LIST_WAIT_SETTLE_LU, listWaitSettleLu);
        if(ILoadingNoticeConstants.LIST_WAIT_SETTLE_LU.equals(listWaitSettleLu)){
        	String billTypeTwo = request.getParameter(ILoadingNoticeConstants.BILL_TYPE);
        	searchPara.put("billTypeTwo", billTypeTwo);
        }
        ladingBillService.receiptConfLetterrReferenceParamsLu(searchPara, request);
        if("toBeSettlement".equals(toBeSettlement)){
        	searchPara.put("toBeSettlement1", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        	searchPara.put("billType", ISystemConstant.DICTIONARY_BILL_TYPE_01);
        }else if("changeReference".equals(toBeSettlement)){
        	//变车管理参照页 过滤汽运配送的
        	searchPara.put("changeReference",IDistributionPlanConstants.PALN_CODE_SHOU);
        }
        
        
        
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, ladingBillService.getCount(searchPara));
        if(BILL_LIST.equals(loadingNoticeLoading)){
            pageVo.setPageSize(rowsInt-1);
            pageVo.setCurrentPage(pageInt);
        }else{
            pageVo.setPageSize(rowsInt);
            pageVo.setCurrentPage(pageInt);
        }
        
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<LadingBillVo> beans = ladingBillService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize(), flag);  //按条件查询全部,带排序
         
        if(BILL_LIST.equals(loadingNoticeLoading)){
        	//台账总计
        	ladingBillService.getBill(beans);
        }
        
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    /**
     * ajax 请求列表数据
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "ajaxList2")
    public void ajaxList2(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map<String,Object> dataMap=new HashMap<String,Object>();
    	
    	String page=request.getParameter("page")==null?"1":request.getParameter("page");
    	String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
    	int rowsInt=Integer.parseInt(rows);//页大小
    	int pageInt=Integer.parseInt(page);//当前页
    	Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件
    	String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
    	
		//协同物流公司
        pubParamService.commomParamsLogisticsCompany(request, searchPara);
    	
    	
    	RmPageVo pageVo = RmJspHelper.transctPageVo(request, ladingBillService.getPlantOrderCount(searchPara));
    	pageVo.setPageSize(rowsInt);
    	pageVo.setCurrentPage(pageInt);
    	
    	if(StringHelper.isEmpty(orderStr)){
    		String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
    		String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
    		orderStr=sidx+" "+sord;
    		searchPara.put("orderStr", orderStr);
    	}
    	List<LadingBillVo> beans = ladingBillService.getPlantOrderList(searchPara, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
    	
    	dataMap.put("page",  pageVo.getCurrentPage());
    	dataMap.put("total",  pageVo.getPageCount());
    	dataMap.put("records",  pageVo.getRecordCount());
    	dataMap.put("rows", beans);
    	
    	response.setCharacterEncoding("UTF-8"); 
    	response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 从页面表单获取信息注入vo，并插入单条记录
     * @param request http请求对象
     * @param vo 值对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(HttpServletRequest request, @Valid LadingBillVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        ladingBillService.insert(vo);  //插入单条记录
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "新增成功: " + vo.getId());
        return result;
    }
    
    /**
     * 从页面表单获取信息注入vo，并修改单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param errors Ajax错误对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request, @Valid LadingBillVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        LadingBillVo ladingBillVo = ladingBillService.get(vo.getId());
        
        if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00.equals(ladingBillVo.getLadingBillStatus())){
        	
        	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
        	
        	result.put(ISystemConstant.AJAX_MESSAGE, "不是未提交状态，不能修改！");
        	
        	return result;
        }
        
        //验证车牌号 身份证 电话的唯一
        ladingBillService.checkedPhoneNumberOnly(result, vo.getPhoneNumber(), vo.getId(), vo.getLicensePlate(), vo.getDriverIdCardNumber());
        
        
        if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
        	
        	return result;
        }
        
        
        
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        ladingBillService.update(vo);  //更新单条记录
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "修改成功: " + vo.getId());
        return result;
    }
    /**
     * 根据id获取单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "get2/{id}/{dateTime}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> get2(@PathVariable("id") String id, HttpServletRequest request) {
        LadingBillVo bean = ladingBillService.get(id);
       
        Map<String, Object> result = new HashMap<String, Object>();
        if(bean==null){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
            return result;
        }
        if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00.equals(bean.getLadingBillStatus())){
        	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "不是待提交，不能修改!");
            return result;
        }
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_BEAN, bean);
        return result;			
    }    
    /**
     * 根据id获取单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "get/{id}/{dateTime}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> get(@PathVariable("id") String id, HttpServletRequest request) {
    	LadingBillVo bean = ladingBillService.get(id);
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	if(bean==null){
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
    		return result;
    	}
    	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    	result.put(ISystemConstant.AJAX_BEAN, bean);
    	return result;			
    }    
    /**
     * 从页面的表单获取单条记录id并删除，如request.id为空则删除多条记录（request.ids）
     * @param request http请求对象
     * @param redirectAttributes 重定向属性
     * @return 返回列表页面
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int deleteCount = 0;  //定义成功删除的记录数
        String id = request.getParameter(REQUEST_ID);
        if(id != null && id.length() > 0) {
            deleteCount = ladingBillService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += ladingBillService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/ladingbill?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
    }
   /**
     * 从页面的表单获取单条记录id并逻辑删除，如request.id为空则删除多条记录（request.ids）
     * @param request http请求对象
     * @param redirectAttributes 重定向属性
     * @return 返回列表页面
     */
    @RequestMapping(value = "logicDelete", method = RequestMethod.POST)
    public String deleteLogic(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int deleteCount = 0;  //定义成功删除的记录数
        String id = request.getParameter(REQUEST_ID);
        if(id != null && id.length() > 0) {
            deleteCount = ladingBillService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += ladingBillService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/ladingbill?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
    }
    /**
     * 从页面的表单获取单条记录id，并察看这条记录的详细信息
     * @param id 待删除的id
     * @param model 模型
     * @request 请求对象
     * @return 返回详细信息页面
     */
    @RequestMapping(value = "detail/{id}")
    public String detail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        LadingBillVo bean = ladingBillService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/ladingbill/detailLadingBill";
    }

    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "reference")
    public String reference(Model model, HttpServletRequest request) {
    	
    	model.addAttribute(REQUEST_QUERY_STRING, request.getQueryString());
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        return "/yulinchemical/bus/ladingbill/util/referenceLadingBill";
    }
    /**
     * 参照改为ajax
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "referenceAjax")
    public void referenceAjax(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	ajaxList(model, request, response);//ajax yemian 
    }
    /**
     * 跳转到导入页
     * @param model 模型
     * @return 返回导入页
     */
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importDataForm(Model model) {
        return "/yulinchemical/bus/ladingbill/importLadingBill";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/ladingbill/importLadingBill";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/ladingbill/exportLadingBill_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/ladingbill/exportLadingBill_excel";
    }

    /**
     * 从request中获得查询条件
     * @param request http请求对象
     * @return 条件Map
     */
    public static Map<String, Object> getQueryCondition(HttpServletRequest request) {
        Map<String, Object> searchMap = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
            searchMap = (Map<String, Object>)request.getAttribute(REQUEST_QUERY_CONDITION);
        } else {
           searchMap = new HashMap<String, Object>();
           searchMap.put("id", request.getParameter("id"));
           searchMap.put("orderPlanId", request.getParameter("orderPlanId"));
           searchMap.put("orderPlanCode", request.getParameter("orderPlanCode"));
           searchMap.put("ladingBillCode", request.getParameter("ladingBillCode"));
           searchMap.put("billType", request.getParameter("billType"));
           searchMap.put("shipMode", request.getParameter("shipMode"));
           searchMap.put("shipModeId", request.getParameter("shipModeId"));
           searchMap.put("productType", request.getParameter("productType"));
           searchMap.put("productTypeId", request.getParameter("productTypeId"));
           searchMap.put("productId", request.getParameter("productId"));
           searchMap.put("productName", request.getParameter("productName"));
           searchMap.put("productModelNumber", request.getParameter("productModelNumber"));
           searchMap.put("phoneNumber", request.getParameter("phoneNumber"));
           searchMap.put("licensePlate", request.getParameter("licensePlate"));
           searchMap.put("carType", request.getParameter("carType"));
           searchMap.put("driverName", request.getParameter("driverName"));
           searchMap.put("driverIdCardNumber", request.getParameter("driverIdCardNumber"));
           searchMap.put("ladingBillStatus", request.getParameter("ladingBillStatus"));
           searchMap.put("auditTime", request.getParameter("auditTime"));
           searchMap.put("auditPerson", request.getParameter("auditPerson"));
           searchMap.put("auditPersonId", request.getParameter("auditPersonId"));
           searchMap.put("estimatedLoadingTime", request.getParameter("estimatedLoadingTime"));
           searchMap.put("takeNoTime", request.getParameter("takeNoTime"));
           searchMap.put("actualShippingWeight", request.getParameter("actualShippingWeight"));
           searchMap.put("nuclearLoad", request.getParameter("nuclearLoad"));
           searchMap.put("inGate", request.getParameter("inGate"));
           searchMap.put("inTime", request.getParameter("inTime"));
           searchMap.put("emptyWeight", request.getParameter("emptyWeight"));
           searchMap.put("emptyPoundTime", request.getParameter("emptyPoundTime"));
           searchMap.put("fullWeight", request.getParameter("fullWeight"));
           searchMap.put("fullPoundTime", request.getParameter("fullPoundTime"));
           searchMap.put("outGate", request.getParameter("outGate"));
           searchMap.put("outTime", request.getParameter("outTime"));
           searchMap.put("inputPersonId", request.getParameter("inputPersonId"));
           searchMap.put("inputPerson", request.getParameter("inputPerson"));
           searchMap.put("inputTime", request.getParameter("inputTime"));
           searchMap.put("invalidPersonId", request.getParameter("invalidPersonId"));
           searchMap.put("invalidPerson", request.getParameter("invalidPerson"));
           searchMap.put("invalidTime", request.getParameter("invalidTime"));
           searchMap.put("invalidReason", request.getParameter("invalidReason"));
           searchMap.put("isBringBelt", request.getParameter("isBringBelt"));
           searchMap.put("isLipolysis", request.getParameter("isLipolysis"));
           searchMap.put("isSince", request.getParameter("isSince"));
           searchMap.put("isUrgent", request.getParameter("isUrgent"));
           searchMap.put("plannedMileage", request.getParameter("plannedMileage"));
           searchMap.put("factMileage", request.getParameter("factMileage"));
           searchMap.put("valuationMileage", request.getParameter("valuationMileage"));
           searchMap.put("settlementFreight", request.getParameter("settlementFreight"));
           searchMap.put("yuanKmTon", request.getParameter("yuanKmTon"));
           searchMap.put("freight", request.getParameter("freight"));
           searchMap.put("receivingPerson", request.getParameter("receivingPerson"));
           searchMap.put("receivingAddress", request.getParameter("receivingAddress"));
           searchMap.put("receivingPersonPhone", request.getParameter("receivingPersonPhone"));
           searchMap.put("freightSettlementId", request.getParameter("freightSettlementId"));
           searchMap.put("invoiceSettlementId", request.getParameter("invoiceSettlementId"));
           searchMap.put("shipperCode", request.getParameter("shipperCode"));
           searchMap.put("printTime", request.getParameter("printTime"));
           searchMap.put("printTimes", request.getParameter("printTimes"));
           searchMap.put("remark", request.getParameter("remark"));
           searchMap.put("logisticsSpecialistId", request.getParameter("logisticsSpecialistId"));
           searchMap.put("logisticsSpecialistName", request.getParameter("logisticsSpecialistName"));
           searchMap.put("importTime", request.getParameter("importTime"));
           searchMap.put("distributionTime", request.getParameter("distributionTime"));
           searchMap.put("logisticsCompanyClerkId", request.getParameter("logisticsCompanyClerkId"));
           searchMap.put("logisticsCompanyClerk", request.getParameter("logisticsCompanyClerk"));
           searchMap.put("allocationVehicleTime", request.getParameter("allocationVehicleTime"));
           searchMap.put("isPound", request.getParameter("isPound"));
           searchMap.put("isLoading", request.getParameter("isLoading"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
           searchMap.put("logisticsCompany", request.getParameter("logisticsCompany"));
           searchMap.put("outTimeStart", request.getParameter("outTimeStart"));
           searchMap.put("outTimeEnd", request.getParameter("outTimeEnd"));
        }
        return searchMap;
    }
    
    
    /**
     * 审核提货单
     * @param request
     * @return
     */
    @RequestMapping(value = "auditLadingBill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Map<String, Object> auditLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true) String ladingBillId, @RequestParam(value="invalidReason", required=false) String invalidReason, @RequestParam(value="auditStatus", required=true) String auditStatus){
    	
    	
    	return ladingBillService.updateAuditLadingBill(request, ladingBillId, auditStatus, invalidReason);
    }
  
    /**
     * 作废提货单（针对主产品）
     * @param request
     * @param ladingBillId
     * @return
     */
    @RequestMapping(value="invalidLadingBill", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> invalidLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true)String ladingBillId, @RequestParam(value="invalidReason", required=true)String invalidReason){
    	
    	return ladingBillService.updateInvalidLadingBill(request, ladingBillId, invalidReason);
    }
    
    /**
     * 删除提货单
     * @param request
     * @param ladingBillId
     * @param orderDetailId
     * @return
     */
    @RequestMapping(value = "deleteLadingBill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Map<String, Object> deleteLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true) String ladingBillId, @RequestParam(value = "orderDetailId", required=true)String orderDetailId){
    	
    	return ladingBillService.deleteLadingBill(request, ladingBillId, orderDetailId);
    }
    
    

    /**
     * 只删除提货单
     * @param request
     * @param ladingBillId
     * @param orderDetailId
     * @return
     */
    @RequestMapping(value = "deleteLadingBills", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Map<String, Object> deleteLadingBills(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true) String ladingBillId){
    	
    	return ladingBillService.deleteLadingBills(request, ladingBillId);
    }
    
    
    /**
     * 只作废提货单
     * @param request
     * @param ladingBillId
     * @param orderDetailId
     * @return
     */
    @RequestMapping(value = "doInvalidLadingBills", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Map<String, Object> doInvalidLadingBills(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true) String ladingBillId){
    	
    	return ladingBillService.doInvalidLadingBills(request, ladingBillId);
    }
    
 
    
    /**
     * 提货单详情
     * @param request
     * @param ladingBillId
     * @return
     */
    @RequestMapping(value = "ladingBillDetail")
    public String ladingBillDetail(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true)String ladingBillId, Model model){
    	Map<String, Object> variable = new HashMap<String, Object>();
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	variable.put("id", ladingBillId);
    	String url = "/yulinchemical/bus/loadingnotice/toDetail";
    	String loadingNotice= request.getParameter("loadingNotice");
    	LadingBillVo  vo=null;
    	
    	if("loadingNotice".equals(loadingNotice)){
    		//装车分配
    		vo=ladingBillService.getLadingBillVo(variable);
    	}else  if("loadingNoticeLu".equals(loadingNotice)){
    		//开具装车通知单
    		//提货单
    		vo=ladingBillService.getLadingBillVo(variable);
    		//通知单
    		LoadingNoticeVo  loadingNoticeVo=loadingNoticeService.getLoadingNoticeVo(ladingBillId);
        	model.addAttribute("loadingNoticeVo", loadingNoticeVo);
    		variable.clear();
    		variable.put("deleteFlag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    		variable.put("productId",vo.getProductId());
    		String  orderStr=null;
    		if(loadingNoticeVo  ==  null){
        		//没有通知单  默认查询库存中间表   按剩余数量倒叙
//        		variable.put("remainderNum",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        		orderStr=" (BUS_PRODUCT_INVENTORY.STORAGE_NUMBER-BUS_PRODUCT_INVENTORY.OUTBOUND_NUMBER)  desc";
    		}else{
    			//有通知单  联查批次表的 库存表    查出的出库数量-批次的数量
        		variable.put("loadingNoticeId",loadingNoticeVo.getId());
//        		variable.put("remainderNum1",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        		orderStr=" (BUS_PRODUCT_INVENTORY.STORAGE_NUMBER-(BUS_PRODUCT_INVENTORY.OUTBOUND_NUMBER-nvl(a.num,'0')))   desc";
    	    	url = "/yulinchemical/bus/loadingnotice/updateDetail";
    		}
    		List<ProductInventoryVo>  list=productInventoryService.list(variable,orderStr);
        	model.addAttribute(REQUEST_BEANS, list);
        	
    	}else{
    		variable.put("ladingBillDetail", "ladingBillDetail");
    		url = "/yulinchemical/bus/ladingbill/ladingBillDetail";
    		List<LadingBillVo>  beans=ladingBillService.getPlantOrderList(variable, -1, -1);
    		vo=beans.get(0);
    	}
    	
    	model.addAttribute(REQUEST_BEAN, vo);
    	
    	return url;
    }
    /**
     * 提货单详情
     * @param request
     * @param ladingBillId
     * @return
     */
    @RequestMapping(value = "ladingBillDetailForBillType")
    public String ladingBillDetailForBillType(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true)String ladingBillId, Model model){
    	
//    	String operation=request.getParameter(ISystemConstant.DICTIONARY_OPERATION);
    	Map<String, Object> variable = new HashMap<String, Object>();
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	variable.put("id", ladingBillId);
    		
    	LadingBillVo vo = ladingBillService.getLadingBillVo(variable);
    	
    	if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(vo.getBillType())){//销售订单
    		variable.clear();
    		variable.put("orderId", vo.getOrderPlanId());
    		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    		variable.put("orderToLadingBill", "orderToLadingBill");
    		variable.put("productId", vo.getProductId());
    		OrderProductDetailVo orderProductDetailVo = orderProductDetailService.getOrderProductDetailVo(variable);
    		
    		return "redirect:/orderproductdetail/toDetail?id=" + orderProductDetailVo.getId() + "&orderToLadingBill=orderToLadingBill&pcode=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&ladingBIllId=" + vo.getId() + "&" + ISystemConstant.DICTIONARY_OPERATION_FLAG + "=" + request.getParameter(ISystemConstant.DICTIONARY_OPERATION_FLAG) + "&uri=" + request.getParameter("uri");
    	
    	}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(vo.getBillType())){//汽运配送计划
    		
    		return "redirect:/cardistributionplan/toUpdateCarDistributionLading?operation=&id=" + vo.getOrderPlanId() + "&pcode=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&ladingBIllId=" + vo.getId() + "&" + ISystemConstant.DICTIONARY_OPERATION_FLAG + "=" + request.getParameter(ISystemConstant.DICTIONARY_OPERATION_FLAG) + "&uri=" + request.getParameter("uri");
    	}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(vo.getBillType())){//汽运调拨计划
    		
    		return "redirect:/carrequisitionplan/toDetail?operation=ladingBill&carRequisitionPlanId=" + vo.getOrderPlanId() + "&pcode=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&ladingBIllId=" + vo.getId() + "&" + ISystemConstant.DICTIONARY_OPERATION_FLAG + "=" + request.getParameter(ISystemConstant.DICTIONARY_OPERATION_FLAG) + "&uri=" + request.getParameter("uri");
    	}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(vo.getBillType())){//铁运调拨&配送计划
    		
    		return "redirect:/trainsrequisitionplan/toDetail?operation=ladingBill&trainsRequisitionPlanId=" + vo.getOrderPlanId() + "&pcode=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&ladingBIllId=" + vo.getId() + "&" + ISystemConstant.DICTIONARY_OPERATION_FLAG + "=" + request.getParameter(ISystemConstant.DICTIONARY_OPERATION_FLAG) + "&uri=" + request.getParameter("uri");
    	}
    	
    	
    	
    	return null;
    }
    
    /**
     * 作废、审核通过
     * @param request
     * @param ladingBillId 提货单id
     * @param operateFlag 操作符
     * @return
     */
    @RequestMapping(value="updateLadingBill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> updateLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true)String ladingBillId, @RequestParam(value="operateFlag", required=true)String operateFlag){
    	
    	return ladingBillService.updateLadingBill(request, ladingBillId, operateFlag);
    }
    
    /**
     * 汽运调拨生成提货单
     * @param request
     * @param carRequisitionId 汽运调拨id
     * @param jqData 提货单数据
     * @return
     */
    @RequestMapping(value="insertOrUpdateCarrequisition", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertOrUpdateCarrequisition(HttpServletRequest request, @RequestParam(value="carRequisitionId", required=true)String carRequisitionId, @RequestParam(value="jqData", required=true)String jqData, @RequestParam(value="flag", required=true)String flag){
    	Map<String, Object> result = new HashMap<String, Object>();
		
    	CarRequisitionPlanVo carRequisitionPlanVo = carRequisitionPlanService.get(carRequisitionId);
    	
		
		//验证信息
		checkedInfo(carRequisitionPlanVo, result, jqData);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			
			return result;
		}

		ladingBillService.insertOrUpdateCarRequisition(jqData, request, carRequisitionPlanVo, result, flag);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return result;
		}
		
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "保存成功");
		
		return result;
    	
    }

	private void checkedInfo(CarRequisitionPlanVo carRequisitionPlanVo,
			Map<String, Object> result, String jqData) {
		if(StringHelper.isEmpty(jqData)){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有提货单数据，不需要提交！");
			
			return;
		}
		
		if(!ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS_01.equals(carRequisitionPlanVo.getStatus()) && !ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS_02.equals(carRequisitionPlanVo.getStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "该汽运调拨单状态不对！");
			
			return;
		}
		
		
		
	}
	
	/**
     * 铁运调拨生成提货单
     * @param request
     * @param carRequisitionId 汽运调拨id
     * @param jqData 提货单数据
     * @return
     */
    @RequestMapping(value="insertOrUpdateTrainsRequisition", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertOrUpdateTrainsRequisition(HttpServletRequest request, @RequestParam(value="trainsRequisitionPlanId", required=true)String trainsRequisitionPlanId, @RequestParam(value="jqData", required=true)String jqData, @RequestParam(value="flag", required=true)String flag){
    	Map<String, Object> result = new HashMap<String, Object>();
		
    	TrainsRequisitionPlanVo trainsRequisitionPlanVo = trainsRequisitionPlanService.get(trainsRequisitionPlanId);
    	
		
		//验证信息
		checkedInfo(trainsRequisitionPlanVo, result, jqData);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			
			return result;
		}

		ladingBillService.insertOrUpdateTrainRequisition(jqData, request, trainsRequisitionPlanVo, result, flag);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return result;
		}
		
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		result.put(ISystemConstant.AJAX_MESSAGE, "保存成功");
		
		return result;
    	
    }

	private void checkedInfo(TrainsRequisitionPlanVo trainsRequisitionPlanVo,
			Map<String, Object> result, String jqData) {
		if(StringHelper.isEmpty(jqData)){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有提货单数据，不需要提交！");
			
			return;
		}
		
		if(!ISystemConstant.DICTIONARY_TRAINS_REQUISITION_STATUS_01.equals(trainsRequisitionPlanVo.getStatus()) && !ISystemConstant.DICTIONARY_TRAINS_REQUISITION_STATUS_02.equals(trainsRequisitionPlanVo.getStatus())){
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "该铁运调拨单状态不对！");
			
			return;
		}
		
		
		
	}
	/**
	 * 待结算页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toBeSettlement")
	public String toBeSettlement(HttpServletRequest request){
		
		return "/yulinchemical/bus/ladingbill/toBeSettlement";
	}
	
	
	
    /**
     * 运算结算单子表修改实际里程   不用了这方法
     * @param request 请求对象
     * @param vo 值对象
     * @param errors Ajax错误对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "updateBySettler", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updateBySettler(HttpServletRequest request, @Valid LadingBillVo vo) {
        return ladingBillService.updateBySettler(request,vo);  //更新单条记录
    }
    /**
     * 根据司机手机号查询最后一次录入的信息
     * @param request
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value="getDriverInfo", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getDriverInfo(HttpServletRequest request, @RequestParam(value="phoneNumber", required=true)String phoneNumber){
    	Map<String, Object> variable = new HashMap<String, Object>();
    	
    	variable.put("phoneNumber", phoneNumber);
    	String orderStr = "BUS_LADING_BILL.ID DESC";
    	
    	List<LadingBillVo> list = ladingBillService.list(variable, orderStr, 1, 1);
    	variable.clear();
    	
    	if(!CollectionUtils.isEmpty(list)){
    		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		variable.put(ISystemConstant.AJAX_BEAN, list.get(0));
    	}else{
    		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    	}
    	
    	return variable;
    }
    
    /**
     * 批量审核提货单
     * @param request
     * @param ladingBillIds
     * @return
     */
    @RequestMapping(value="batchAuditLadingBill", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> batchAuditLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillIds", required=true)String ladingBillIds){
    	
    	return ladingBillService.updateBatchAuditLadingBill(request, ladingBillIds);
    }
    
    
    /**
     * 装货单管理  批量提货失败
     * @param request
     * @param ladingBillIds   提货单id
     * @return
     */
    @RequestMapping(value="doListFailPick", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> doListFailPick(HttpServletRequest request){
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
        	String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
            	//批量更改  提货失败
                ladingBillService.doFailLoading(request, ids,result);
            }
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE,"操作成功!");
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
        return result;
    }
    
   
    
    /**
     *待结算  多条修改实际里程
     * @param request
     * @param ladingBillIds
     * @return
     */
    @RequestMapping(value="updateFactMileages", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> updateFactMileages(HttpServletRequest request){
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
           //批量更改 
            ladingBillService.updateFactMileagesS(request);  //删除多条记录
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE,"操作成功!");
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
        return result;
    }
    
    
    
    
}
