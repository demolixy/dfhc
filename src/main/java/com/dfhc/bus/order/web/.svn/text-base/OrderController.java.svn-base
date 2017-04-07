/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  OrderController.java
 *
 * 功能描述：  销售订单Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.order.web;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
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
import com.dfhc.acc.creditmanager.service.CreditManagerService;
import com.dfhc.acc.creditmanager.vo.CreditManagerVo;
import com.dfhc.bus.order.IOrderConstants;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.orderproductdetail.service.OrderProductDetailService;
import com.dfhc.bus.orderproductdetail.vo.OrderProductDetailVo;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.StringHelper;

/**
 * list                  /order
 * insert page      GET  /order/insert
 * insert action    POST /order/insert
 * update page      GET  /order/update/{id}
 * update action    POST /order/update
 * delete action    POST /order/delete
 * detail                /order/detail/{id}

 * reference             /order/reference
 * import page      GET  /order/import
 * import action    POST /order/import
 * export custom    GET  /order/export
 * export action    POST /order/export
 */

/**
 * 销售订单 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController implements IOrderConstants {

    @Autowired
    /**
     * 销售订单服务
     */
    private OrderService orderService;
    @Autowired
    private  OrderProductDetailService  orderProductDetailService;
    
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private CreditManagerService creditManagerService;
    /**
     * 销售订单管理
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
    	model.addAttribute("queryString", request.getQueryString());
         model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
         //销售订单状态
         model.addAttribute(ISystemConstant.DICTIONARY_ORDER_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_ORDER_STATUS));  //把模块代码放入request     
        
         pubParamService.commonModel(request, model);
         return "/yulinchemical/bus/order/listOrder";
    }
   
    /**
     * 销售订单管理ajax 请求列表数据
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
        //客户协同
        pubParamService.commonParamsCustomer(request, searchPara);
        
        String  typeLu=request.getParameter("typeLu");
        if(!StringHelper.isEmpty(typeLu) && "saleOrderManager".equals(typeLu)){
            //销售订单管理菜单     关联子表 查出产品名称   总车辆
            searchPara.put("saleOrderManager","saleOrderManager");
        }
        
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, orderService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){

            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }

        if(!StringHelper.isEmpty(typeLu) && "saleOrderManager".equals(typeLu)){
        	if("id desc".equals(orderStr)){
            	orderStr="  DECODE(BUS_ORDER.STATUS,'"+ISystemConstant.DICTIONARY_ORDER_STATUS_00+"','1'" +
            			",'"+ISystemConstant.DICTIONARY_ORDER_STATUS_05+"','2','"+ISystemConstant.DICTIONARY_ORDER_STATUS_01+"','3'" +
            			",'"+ISystemConstant.DICTIONARY_ORDER_STATUS_02+"','4','"+ISystemConstant.DICTIONARY_ORDER_STATUS_03+"','5'," +
            					"'"+ISystemConstant.DICTIONARY_ORDER_STATUS_04+"','6') ,  BUS_ORDER.CREATE_TIME  desc";
        	}
        }

        List<OrderVo> beans = orderService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid OrderVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        orderService.insert(vo);  //插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid OrderVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        orderService.update(vo);  //更新单条记录
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
        OrderVo bean = orderService.get(id);
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
            deleteCount = orderService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += orderService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/order?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = orderService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += orderService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/order?typeLu=saleOrderManager&"+ISystemConstant.DICTIONARY_OPERATION+"="+request.getParameter(ISystemConstant.DICTIONARY_OPERATION)+"&"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        OrderVo bean = orderService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/order/detailOrder";
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
        return "/yulinchemical/bus/order/util/referenceOrder";
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
        return "/yulinchemical/bus/order/importOrder";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/order/importOrder";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/order/exportOrder_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/order/exportOrder_excel";
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
           searchMap.put("orderNum", request.getParameter("orderNum"));
           searchMap.put("customerId", request.getParameter("customerId"));
           searchMap.put("customerName", request.getParameter("customerName"));
           searchMap.put("deliveryStartDate", request.getParameter("deliveryStartDate"));
           searchMap.put("deliveryEndDate", request.getParameter("deliveryEndDate"));
           searchMap.put("amountMoney", request.getParameter("amountMoney"));
           searchMap.put("plannedSurplus", request.getParameter("plannedSurplus"));
           searchMap.put("actualLoadingAmount", request.getParameter("actualLoadingAmount"));
           searchMap.put("actualSurplusAmount", request.getParameter("actualSurplusAmount"));
           searchMap.put("shipMode", request.getParameter("shipMode"));
           searchMap.put("shipModeId", request.getParameter("shipModeId"));
           searchMap.put("receivingUnitId", request.getParameter("receivingUnitId"));
           searchMap.put("receivingUnit", request.getParameter("receivingUnit"));
           searchMap.put("province", request.getParameter("province"));
           searchMap.put("provinceId", request.getParameter("provinceId"));
           searchMap.put("city", request.getParameter("city"));
           searchMap.put("cityId", request.getParameter("cityId"));
           searchMap.put("address", request.getParameter("address"));
           searchMap.put("orderAttachment", request.getParameter("orderAttachment"));
           searchMap.put("shipperCode", request.getParameter("shipperCode"));
           searchMap.put("attorneyLetter", request.getParameter("attorneyLetter"));
           searchMap.put("placeOrderTime", request.getParameter("placeOrderTime"));
           searchMap.put("inputPerson", request.getParameter("inputPerson"));
           searchMap.put("inputPersonId", request.getParameter("inputPersonId"));
           searchMap.put("auditPersonId", request.getParameter("auditPersonId"));
           searchMap.put("auditPerson", request.getParameter("auditPerson"));
           searchMap.put("auditTime", request.getParameter("auditTime"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    
    
    /**
     * 进入添加页面 销售订单管理
     * @param model 模型toUpdateOrde
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "toAddOrder")
    public String toAddOrder(Model model, HttpServletRequest request) {
         model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
         //进入就生成一个id
         model.addAttribute("id",RmIdFactory.requestId(TABLE_NAME));  
         //协同
         pubParamService.commonModel(request, model);
 		 String operation = request.getParameter(ISystemConstant.DICTIONARY_OPERATION);
 		 if(!StringHelper.isEmpty(operation)){
 	    	RmUserVo rmUserVo = (RmUserVo)RmProjectHelper.getRmUserVo(request);
 	         model.addAttribute("loginUserVo",rmUserVo);  
 		 }
 		
         return "/yulinchemical/bus/order/toAddOrder";
    }
 
    /**
     * 进入修改页面 销售订单管理
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "toUpdateOrder")
    public String toUpdateOrder(Model model, HttpServletRequest request) {
         model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
         //销售订单id
         String id=request.getParameter("id");
         OrderVo  orderVo=orderService.get(id);
         model.addAttribute(ISystemConstant.AJAX_BEAN, orderVo);  //把模块代码放入request     
         
         Map<String, Object> searchMap = new HashMap<String, Object>();
         
         //查询可用额度  =授信额度+业务可用额度
         searchMap.put("customerId", orderVo.getCustomerId());
         searchMap.put("isStatus", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
         searchMap.put("delete_flag2", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
         searchMap.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
         searchMap.put("remainingAvailable", "remainingAvailable");
         
         CreditManagerVo creditManagerVo = creditManagerService.getCreditManagerVo(searchMap);
         if(creditManagerVo!=null){
             model.addAttribute("lineOfCredit", creditManagerVo.getLineOfCredit());
         }
         
         searchMap.clear();
         searchMap.put("orderId", id);
         searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
         List<OrderProductDetailVo> beans=orderProductDetailService.list(searchMap, null);
         model.addAttribute("beans", beans);  //把模块代码放入request     

         //协同
         pubParamService.commonModel(request, model);

         return "/yulinchemical/bus/order/toUpdateOrder";
    }

    
    /**
     *  添加页面保存提交、修改页面的保存提交
     * @param request http请求对象
     * @param vo 值对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "doAddOrUpdateOrder", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
      public Map<String,Object> doAddOrUpdateOrder(HttpServletRequest request, @Valid OrderVo vo) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	RmUserVo rmUserVo = (RmUserVo)RmProjectHelper.getRmUserVo(request);
    
        if(rmUserVo==null){
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "请先登录");
    		return result;
        }
	   	try {
	    	orderService.doAddOrUpdateOrder(request,rmUserVo,vo);
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
	        result.put(ISystemConstant.AJAX_MESSAGE, "操作成功");
			
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
		}
    	
        return result;

    }
    
    
    /**
     * 去详情页
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="toDetail")
    public String toDetail(Model model, HttpServletRequest request, @RequestParam(value="flag", required=false)String flag){
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
   	
    	//销售订单id
    	String id=request.getParameter("id");

    	OrderVo vo=orderService.get(id);
    	
    	model.addAttribute(ISystemConstant.AJAX_BEAN, vo);
    	
    	
        return "/yulinchemical/bus/order/toDetail";
    	
    }
    
    /**
     * 去审核页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="toAudit")
    public String toAudit(Model model, HttpServletRequest request){
    	//把模块代码放入request 
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));     
   	
    	//销售订单id
    	String id=request.getParameter("id");

    	OrderVo vo=orderService.get(id);
    	
    	model.addAttribute(ISystemConstant.AJAX_BEAN, vo);
    	
    	//查询订单总金额 业务余额=授信额度+可用业务余额
    	
    	Map<String, Object> variable = new HashMap<String, Object>();
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	variable.put("orderId", vo.getId());
    	variable.put("isStatus", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
    	
    	Map<String, Object> price = orderProductDetailService.getCustomerPrice(variable);
    	
    	model.addAttribute("price", price);
        return "/yulinchemical/bus/order/toAudit";
    	
    }
    
    
    /**
     * 审核
     */
    @RequestMapping(value = "doAudit", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
      public Map<String,Object> doAudit(HttpServletRequest request) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	RmUserVo rmUserVo = (RmUserVo)RmProjectHelper.getRmUserVo(request);
        if(rmUserVo==null){
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "请先登录");
    		return result;
        }
        
        //销售订单id
        String id=request.getParameter("id");
        
	   	try {
	    	orderService.doAudit(request,id,rmUserVo);
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
	        result.put(ISystemConstant.AJAX_MESSAGE, "审核成功");
			
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
		}
    	
        return result;

    }

    /**
     * 审核驳回
     */
    @RequestMapping(value = "doNoAudit", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
      public Map<String,Object> doNoAudit(HttpServletRequest request) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	RmUserVo rmUserVo = (RmUserVo)RmProjectHelper.getRmUserVo(request);
        if(rmUserVo==null){
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "请先登录");
    		return result;
        }
        
        //销售订单id
        String id=request.getParameter("id");
        
	   	try {
	    	orderService.doNoAudit(request,id);
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
	        result.put(ISystemConstant.AJAX_MESSAGE, "驳回成功");
			
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
		}
    	
        return result;

    }
    
    
    /**
     * 销售订单审核
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "listAudit")
    public String listAudit(Model model, HttpServletRequest request) {
         model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
         //销售订单状态
         model.addAttribute(ISystemConstant.DICTIONARY_ORDER_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_ORDER_STATUS));  //把模块代码放入request     
        return "/yulinchemical/bus/order/listAudit";
    }
 
    
    /**
     * 多条提交
     */
    @RequestMapping(value = "doListCommit", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> doListCommit(HttpServletRequest request) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
        	String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
            	//修改状态   已提交
                orderService.doUpdateListStatus(request,ids,ISystemConstant.DICTIONARY_ORDER_STATUS_01);  //删除多条记录
            }
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE,"提交成功!");
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
        return result;
    }
   
    
    
    
    /**
     *延期
     */
    @RequestMapping(value = "updateYan", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updateYan(HttpServletRequest request, @Valid OrderVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            orderService.updateYan(request,vo);  //更新单条记录
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE, "修改成功: " + vo.getId());
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
        return result;
    }

}
