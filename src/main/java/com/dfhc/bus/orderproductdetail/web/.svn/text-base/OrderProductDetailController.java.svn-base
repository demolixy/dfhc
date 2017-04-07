/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  OrderProductDetailController.java
 *
 * 功能描述：  销售订单产品明细Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.orderproductdetail.web;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.project.RmGlobalReference;
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
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.IOrderProductDetailConstants;
import com.dfhc.bus.orderproductdetail.service.OrderProductDetailService;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * list                  /orderproductdetail
 * insert page      GET  /orderproductdetail/insert
 * insert action    POST /orderproductdetail/insert
 * update page      GET  /orderproductdetail/update/{id}
 * update action    POST /orderproductdetail/update
 * delete action    POST /orderproductdetail/delete
 * detail                /orderproductdetail/detail/{id}

 * reference             /orderproductdetail/reference
 * import page      GET  /orderproductdetail/import
 * import action    POST /orderproductdetail/import
 * export custom    GET  /orderproductdetail/export
 * export action    POST /orderproductdetail/export
 */

/**
 * 销售订单产品明细 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/orderproductdetail")
public class OrderProductDetailController implements IOrderProductDetailConstants {

    @Autowired
    /**
     * 销售订单产品明细服务
     */
    private OrderProductDetailService orderProductDetailService;
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private  OrderService  orderService;
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        return "/yulinchemical/bus/orderproductdetail/listOrderProductDetail";
    }
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "orderDateil")
    public String listOrderDateil(Model model, HttpServletRequest request) {
    	model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
    	pubParamService.commonModel(request, model);
    	pubParamService.commonFlagModel(request, model);
    	return "/yulinchemical/bus/ladingbill/listOrder";
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
      //联查产品类别
        searchPara.put("productTypeIdName", "productTypeIdName");
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, orderProductDetailService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<OrderProductDetailVo> beans = orderProductDetailService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
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
    	
    	//提货单关联列表查询条件
    	createLadingBillPrevListParams(searchPara, request);
    	
    	String remainderCarNumIsNotZero=request.getParameter(IOrderProductDetailConstants.REMAINDER_CARNUM_ISNOTZERRO);
    	if(IOrderProductDetailConstants.REMAINDER_CARNUM_ISNOTZERRO.equals(remainderCarNumIsNotZero)){
    		//已分配==计划车数
    		searchPara.put("remainderCarNumIsNotZero", "'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00+"'");
    	}

    	
    	RmPageVo pageVo = RmJspHelper.transctPageVo(request, orderProductDetailService.getCount(searchPara));
    	pageVo.setPageSize(rowsInt);
    	pageVo.setCurrentPage(pageInt);
    	String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
    	if(StringHelper.isEmpty(orderStr)){
    		String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
    		String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
    		orderStr=sidx+" "+sord;
    	}
    	
    	List<OrderProductDetailVo> beans = orderProductDetailService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
    	
    	dataMap.put("page",  pageVo.getCurrentPage());
    	dataMap.put("total",  pageVo.getPageCount());
    	dataMap.put("records",  pageVo.getRecordCount());
    	dataMap.put("rows", beans);
    	
    	response.setCharacterEncoding("UTF-8"); 
    	response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 生成提货单详细页面
     */
    @RequestMapping(value = "toDetail")
    public String toDetail(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	
    	if(StringHelper.isEmpty(id)){
    		return "redirect:/orderproductdetail/orderDateil";
    	}
    	
    	Map<String, Object> variable = new HashMap<String, Object>();
    	//提货单关联列表查询条件
        createLadingBillPrevListParams(variable, request);
        
        if(variable.size() == 0){
        	return "redirect:/orderproductdetail/orderDateil";
        }
        
        variable.put("id", id);
        
        OrderProductDetailVo vo = orderProductDetailService.getOrderProductDetailVo(variable);
        
        if(vo == null){
        	return "redirect:/orderproductdetail/orderDateil";
        }
        
        OrderVo  orderVo=orderService.get(vo.getOrderId());
        if(!StringHelper.isEmpty(orderVo.getOrderAttachment())){
        	model.addAttribute("attacmentList", StringHelper.splitString(orderVo.getOrderAttachment(),","));
        }
        	
		//有提货单 展示
        String url = "/yulinchemical/bus/ladingbill/toDetail";
		if(null != vo.getAssignedCarNumBer() && 0 != vo.getAssignedCarNumBer()){
			String ladingBIllId = request.getParameter("ladingBIllId");
			//查询订单下的提货单
			List<LadingBillVo> list = ladingBillService.list(vo.getOrderId(), vo.getProductId(), ladingBIllId);
			
			if(!StringHelper.isEmpty(ladingBIllId)){
				url = "/yulinchemical/bus/ladingbill/toDetailForOrderType";
				model.addAttribute("ladingBillStatus", list.get(0).getLadingBillStatus());
			}
				
			model.addAttribute(REQUEST_BEANS, list);
		}
    	pubParamService.commonFlagModel(request, model);
    	pubParamService.commonModel(request, model);
		model.addAttribute("ladingBillStatusMap", RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS));
		model.addAttribute("yesNot", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));
        model.addAttribute(REQUEST_BEAN, vo);
    	model.addAttribute("uri", request.getParameter("uri"));
        return url;
    }
    
    /**
     * 提货单关联列表查询参数
     * @param searchPara
     * @param request
     */
    private void createLadingBillPrevListParams(Map<String, Object> searchPara,
			HttpServletRequest request) {
		String orderToLadingBill = request.getParameter("orderToLadingBill");
		if("orderToLadingBill".equals(orderToLadingBill)){
			searchPara.put("orderToLadingBill", "orderToLadingBill");
			
			searchPara.put("orderStatus", ISystemConstant.DICTIONARY_ORDER_STATUS_02);
			searchPara.put("deliveryEndDate", DateUtil.toSqlDate(DateUtil.getNowDate()).toString());
			
		}
		//客户协同
		pubParamService.commonParamsCustomer(request, searchPara);
		
    	
		
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid OrderProductDetailVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        orderProductDetailService.insert(vo);  //插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid OrderProductDetailVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        orderProductDetailService.update(vo);  //更新单条记录
        Map<String, Object> result = new HashMap<String, Object>();
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
    @RequestMapping(value = "get/{id}/{dateTime}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> get(@PathVariable("id") String id, HttpServletRequest request) {
        OrderProductDetailVo bean = orderProductDetailService.get(id);
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
            deleteCount = orderProductDetailService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += orderProductDetailService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/orderproductdetail?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = orderProductDetailService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += orderProductDetailService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/orderproductdetail?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        OrderProductDetailVo bean = orderProductDetailService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/orderproductdetail/detailOrderProductDetail";
    }

    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "reference")
    public String reference(Model model, HttpServletRequest request) {
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        return "/yulinchemical/bus/orderproductdetail/util/referenceOrderProductDetail";
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
        return "/yulinchemical/bus/orderproductdetail/importOrderProductDetail";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/orderproductdetail/importOrderProductDetail";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/orderproductdetail/exportOrderProductDetail_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/orderproductdetail/exportOrderProductDetail_excel";
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
           searchMap.put("orderId", request.getParameter("orderId"));
           searchMap.put("orderNum", request.getParameter("orderNum"));
           searchMap.put("productTypeId", request.getParameter("productTypeId"));
           searchMap.put("productId", request.getParameter("productId"));
           searchMap.put("productName", request.getParameter("productName"));
           searchMap.put("plannedVolume", request.getParameter("plannedVolume"));
           searchMap.put("unitPrice", request.getParameter("unitPrice"));
           searchMap.put("carNum", request.getParameter("carNum"));
           searchMap.put("remarkVolume", request.getParameter("remarkVolume"));
           searchMap.put("remainderCarNum", request.getParameter("remainderCarNum"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    /**
     * 根据订单明细   新增或修改提货单
     * @param request
     * @param orderDetailId 订单明细id
     * @return
     */
    @RequestMapping(value = "orderDetailToLadingBillList", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> orderDetailToLadingBillList(HttpServletRequest request, @RequestParam(required=true, value="orderDetailId") String orderDetailId, @RequestParam(value="flag",required=true)String flag){
    	
    	return orderProductDetailService.updateOrderDetailToLadingBillList(request, orderDetailId, flag);
    }
 

}
