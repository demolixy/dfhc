/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CarDistributionPlanController.java
 *
 * 功能描述：  汽运配送计划Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.cardistributionplan.web;



import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.dfhc.PjException;
import com.dfhc.base.logisticscompany.service.LogisticsCompanyService;
import com.dfhc.base.shipmode.service.ShipModeService;
import com.dfhc.base.shipmode.vo.ShipModeVo;
import com.dfhc.base.shipper.service.ShipperService;
import com.dfhc.bus.cardistributionplan.ICarDistributionPlanConstants;
import com.dfhc.bus.cardistributionplan.service.CarDistributionPlanService;
import com.dfhc.bus.cardistributionplan.vo.CarDistributionPlanVo;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.rm.user.service.UserService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * list                  /cardistributionplan
 * insert page      GET  /cardistributionplan/insert
 * insert action    POST /cardistributionplan/insert
 * update page      GET  /cardistributionplan/update/{id}
 * update action    POST /cardistributionplan/update
 * delete action    POST /cardistributionplan/delete
 * detail                /cardistributionplan/detail/{id}

 * reference             /cardistributionplan/reference
 * import page      GET  /cardistributionplan/import
 * import action    POST /cardistributionplan/import
 * export custom    GET  /cardistributionplan/export
 * export action    POST /cardistributionplan/export
 */

/**
 * 汽运配送计划 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/cardistributionplan")
public class CarDistributionPlanController implements ICarDistributionPlanConstants {

    @Autowired
    /**
     * 汽运配送计划服务
     */
    private CarDistributionPlanService carDistributionPlanService;
       
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private  ShipModeService shipModeService;
    @Autowired
    private PubParamService pubParamService;
    @Autowired
    private  ShipperService  shipperService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  LogisticsCompanyService  logisticsCompanyService;
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  
        //汽运配送计划
        model.addAttribute(ISystemConstant.DICTIONARY_PLAN_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_PLAN_STATUS));     
        model.addAttribute("status", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));
        model.addAttribute("queryString", request.getQueryString());
        model.addAttribute("logisticsCompanyIdIsNull", request.getParameter("logisticsCompanyIdIsNull"));
        model.addAttribute("shipperCodeLu", shipperService.list());
        
        model.addAttribute("listLogic",logisticsCompanyService.listLogisticsCompany());
        
        return "/yulinchemical/bus/cardistributionplan/listCarDistributionPlan";
    }
    
    /**
     * 销售自提计划       过滤 业务类型  
     */
    @RequestMapping(value = "listSaleLift")
    public String listSaleLift(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  
        //汽运配送计划
        model.addAttribute(ISystemConstant.DICTIONARY_PLAN_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_PLAN_STATUS));     
        //发运方式
        shipModeService.list(model);
        return "/yulinchemical/bus/cardistributionplan/listSaleLift";
    }

    /**
     * 汽运配送提货单     过滤 业务类型  
     */
    @RequestMapping(value = "listCarDistributionLading")
    public String listCarDistributionLading(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  
        //汽运配送计划
        model.addAttribute(ISystemConstant.DICTIONARY_PLAN_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_PLAN_STATUS));     
        //发运方式
        shipModeService.list(model);
        
        //发货单位
        model.addAttribute("shipperCodeLu", shipperService.list());
        pubParamService.commonModel(request, model);
        return "/yulinchemical/bus/cardistributionplan/listCarDistributionLading";
    }
    
    /**
     * 主产品自提提货单       过滤 业务类型  
     */
    @RequestMapping(value = "listZhuProductLift")
    public String listZhuProductLift(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  
        //汽运配送计划
        model.addAttribute(ISystemConstant.DICTIONARY_PLAN_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_PLAN_STATUS));     
        //发运方式
        shipModeService.list(model);
        return "/yulinchemical/bus/cardistributionplan/listZhuProductLift";
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
        
		//协同物流公司
        pubParamService.commomParamsLogisticsCompany(request, searchPara);

        String logisticsCompanyIdIsNull = request.getParameter("logisticsCompanyIdIsNull");
        if(!StringHelper.isEmpty(logisticsCompanyIdIsNull)){
        	if(logisticsCompanyIdIsNull.equals("1")){
        		searchPara.put("businessType", ISystemConstant.DICTIONARY_PLAN_TYPE_00);
        		searchPara.put("logisticsCompanyIdIsNull", "logisticsCompanyIdIsNull");
        	}else{
        		searchPara.put("businessType", ISystemConstant.DICTIONARY_PLAN_TYPE_00);
        		searchPara.put("logisticsCompanyIdIsNotNull", "logisticsCompanyIdIsNotNull");
        	}
        }
		String operationLu = request.getParameter(ISystemConstant.DICTIONARY_OPERATIONLU);
		//开具提货单
		if("operationLu".equals(operationLu)){
    		//剩余车数不为零的
    		searchPara.put("remainderCarNumIsNotZero", "'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00+"'");
		}


        RmPageVo pageVo = RmJspHelper.transctPageVo(request, carDistributionPlanService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        if(!StringHelper.isEmpty(logisticsCompanyIdIsNull)){
        	if("id desc".equals(orderStr)){
            	orderStr=" BUS_CAR_DISTRIBUTION_PLAN.SEND_CAR_DATE  desc";
        	}
        }        
        List<CarDistributionPlanVo> beans = carDistributionPlanService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 汽运配送计划 添加
     * @param request http请求对象
     * @param vo 值对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(HttpServletRequest request, @Valid CarDistributionPlanVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
       	
    	try {
        	//默认添加值
        	//制表日期
        	vo.setMakeDate(new java.sql.Date(DateUtil.getNowDate().getTime()));
        	//剩余车数==计划车数
        	vo.setRemainderCarNum(vo.getPlanCarNum());
        	//剩余数量（吨）==数量（吨）
        	vo.setRemainderNum(vo.getNum());
        	//状态  已导入
    		vo.setStatus(ISystemConstant.DICTIONARY_PLAN_STATUS_00);
    		//业务类型  ：  主产品配送计划
        	vo.setBusinessType(ISystemConstant.DICTIONARY_PLAN_TYPE_00);
        	//发运编码
        	vo.setShipModeCode(ISystemConstant.DICTIONARY_SHIP_MODE_01);
        	//发运编码
        	ShipModeVo shipModeVo=shipModeService.list(ISystemConstant.DICTIONARY_SHIP_MODE_01);
        	vo.setShipMode(shipModeVo==null?"汽运配送":shipModeVo.getName());
        	
            RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
            carDistributionPlanService.insert(vo);  //插入单条记录
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE, "新增成功: " + vo.getId());
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
		}
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
    public Map<String,Object> update(HttpServletRequest request, @Valid CarDistributionPlanVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        carDistributionPlanService.update(vo);  //更新单条记录
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
        CarDistributionPlanVo bean = carDistributionPlanService.get(id);
        Map<String, Object> result = new HashMap<String, Object>();
        if(bean==null){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
            return result;
        }
        //判断是否存在提货单
        String isExist = request.getParameter("isExist");
        if(!StringHelper.isEmpty(isExist)){
        	Map<String, Object> searchMap = new HashMap<String, Object>();
        	searchMap.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        	searchMap.put("orderPlanId", id);
        	List<LadingBillVo> ladingBillVos = ladingBillService.list(searchMap, null);
        	if(ladingBillVos.size()>0){
        		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
                result.put(ISystemConstant.AJAX_MESSAGE, "该计划单已存在提货单,不允许修改!");
                return result;
        	}
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
            deleteCount = carDistributionPlanService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += carDistributionPlanService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/cardistributionplan?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = carDistributionPlanService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += carDistributionPlanService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/cardistributionplan?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        CarDistributionPlanVo bean = carDistributionPlanService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/cardistributionplan/detailCarDistributionPlan";
    }

    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "reference")
    public String reference(Model model, HttpServletRequest request) {
    	model.addAttribute("queryString", request.getQueryString());
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        
        if(ISystemConstant.DICTIONARY_PLAN_TYPE_00.equals(request.getParameter("businessType"))){
        	//汽运配送
            return "/yulinchemical/bus/cardistributionplan/util/referenceCarDistributionPlan2";
        }
        return "/yulinchemical/bus/cardistributionplan/util/referenceCarDistributionPlan";
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
        return "/yulinchemical/bus/cardistributionplan/importCarDistributionPlan";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/cardistributionplan/importCarDistributionPlan";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/cardistributionplan/exportCarDistributionPlan_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/cardistributionplan/exportCarDistributionPlan_excel";
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
           searchMap.put("planCode", request.getParameter("planCode"));
           searchMap.put("distributionPlanId", request.getParameter("distributionPlanId"));
           searchMap.put("makeDate", request.getParameter("makeDate"));
           searchMap.put("logisticsCompany", request.getParameter("logisticsCompany"));
           searchMap.put("region", request.getParameter("region"));
           searchMap.put("customerName", request.getParameter("customerName"));
           searchMap.put("goodsName", request.getParameter("goodsName"));
           searchMap.put("receivingPerson", request.getParameter("receivingPerson"));
           searchMap.put("receivingPersonPhone", request.getParameter("receivingPersonPhone"));
           searchMap.put("area", request.getParameter("area"));
           searchMap.put("shippingAddress", request.getParameter("shippingAddress"));
           searchMap.put("num", request.getParameter("num"));
           searchMap.put("plannedMileage", request.getParameter("plannedMileage"));
           searchMap.put("factMileage", request.getParameter("factMileage"));
           searchMap.put("valuationMileage", request.getParameter("valuationMileage"));
           searchMap.put("tonKm", request.getParameter("tonKm"));
           searchMap.put("settlementFreight", request.getParameter("settlementFreight"));
           searchMap.put("totalFreight", request.getParameter("totalFreight"));
           searchMap.put("price", request.getParameter("price"));
           searchMap.put("remark", request.getParameter("remark"));
           searchMap.put("sendCarDate", request.getParameter("sendCarDate"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("remainderNum", request.getParameter("remainderNum"));
           searchMap.put("remainderCarNum", request.getParameter("remainderCarNum"));
           searchMap.put("shipperCode", request.getParameter("shipperCode"));
           searchMap.put("shippingVehiclesNumber", request.getParameter("shippingVehiclesNumber"));
           searchMap.put("shippingNumber", request.getParameter("shippingNumber"));
           searchMap.put("flowToId", request.getParameter("flowToId"));
           searchMap.put("planCarNum", request.getParameter("planCarNum"));
           searchMap.put("province", request.getParameter("province"));
           searchMap.put("provinceId", request.getParameter("provinceId"));
           searchMap.put("businessType", request.getParameter("businessType"));
           searchMap.put("customerId", request.getParameter("customerId"));
           searchMap.put("productId", request.getParameter("productId"));
           searchMap.put("shipMode", request.getParameter("shipMode"));
           searchMap.put("logisticsCompanyId", request.getParameter("logisticsCompanyId"));
           searchMap.put("shipModeCode", request.getParameter("shipModeCode"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    
    /**
     *  汽运配送计划删除
     * @param request http请求对象
     * @param redirectAttributes 重定向属性
     * @return 返回列表页面
     */
    @RequestMapping(value = "doDelete", method = RequestMethod.POST)
    public String doDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int deleteCount = 0;  //定义成功删除的记录数
        //配送计划id
        String id = request.getParameter(REQUEST_ID);
        
        //删除的ids
        String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
        if (ids != null && ids.length != 0) {
            deleteCount += carDistributionPlanService.delete(ids);  //删除多条记录
        }
        
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/distributionplan/toDetail?id="+id+"&"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
    }
    
    
    
    /**
     * 销售自提计划  增加
     */
    @RequestMapping(value = "doSaleInsert", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> doSaleInsert(HttpServletRequest request, @Valid CarDistributionPlanVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
   	
    	try {
        	//默认添加值  未提交
            carDistributionPlanService.doSaleInsert(request, vo, ISystemConstant.DICTIONARY_PLAN_STATUS_11);
            
        	RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        	carDistributionPlanService.insert(vo);  //插入单条记录
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE, "新增成功: " + vo.getId());
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
		}
        return result;
    }
    
    
    /**
     *  销售自提计划  修改
     */
    @RequestMapping(value = "doSaleUpdate", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> doSaleUpdate(HttpServletRequest request, @Valid CarDistributionPlanVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
    		if(!ISystemConstant.DICTIONARY_PLAN_STATUS_11.equals(vo.getStatus())){
                result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
                result.put(ISystemConstant.AJAX_MESSAGE, "已提交的不能修改");
                return result;
    		}
        	//剩余车数==计划车数
        	vo.setRemainderCarNum(vo.getPlanCarNum());
        	//剩余数量（吨）==数量（吨）
        	vo.setRemainderNum(vo.getNum());
    		
            RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
            carDistributionPlanService.update(vo);  //更新单条记录
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE, "修改成功: " + vo.getId());
		} catch (Exception e) {
			// TODO: handle exception
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE,e.getMessage());
		}
        return result;
    }

    
    /**
     *  销售自提计划  删除
     */
    @RequestMapping(value = "doSaleDelete", method = RequestMethod.POST)
    public String doSaleDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int deleteCount = 0;  //定义成功删除的记录数
        String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
        if (ids != null && ids.length != 0) {
            deleteCount += carDistributionPlanService.delete(ids);  //删除多条记录
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        
        String  flag=request.getParameter("flag");
        if("listZhuProductLift".equals(flag)){
            return "redirect:/cardistributionplan/listZhuProductLift?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
        }
        return "redirect:/cardistributionplan/listSaleLift?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
    }

    /**
     * 销售自提计划  多条提交
     */
    @RequestMapping(value = "doSaleCommit", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> doSaleCommit(HttpServletRequest request) {
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
        	String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
            	//修改状态   已提交
                carDistributionPlanService.doUpdateListStatus(request,ids,ISystemConstant.DICTIONARY_PLAN_STATUS_12);  //删除多条记录
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
     * 主产品自提提货单   关联车辆页面  
     */
    @RequestMapping(value = "toUpdateZhuProductLift")
    public String toUpdateZhuProductLift(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  
        String id=request.getParameter("id");
    	CarDistributionPlanVo vo=carDistributionPlanService.get(id);
    	model.addAttribute(ISystemConstant.AJAX_BEAN, vo);
    	
        //是否
        model.addAttribute("yesNot", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));     
        //是否
        model.addAttribute("ladingBillStatusMap", RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS));     

//		//提货单list
		List<LadingBillVo> list = ladingBillService.list(vo.getId(), vo.getProductId());
		model.addAttribute(REQUEST_BEANS, list);

        return "/yulinchemical/bus/cardistributionplan/toUpdateZhuProductLift";
    }
    
    
    /**
     *  主产品自提关联车辆的保存提交  
     *  汽运配送关联车辆的保存提交
     */
    @RequestMapping(value = "doZhuToLadingBillList", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
      public Map<String,Object> doZhuToLadingBillList(HttpServletRequest request, @Valid CarDistributionPlanVo newVo, @RequestParam(value="flag",required=true)String flag) {
 
    	
 		CarDistributionPlanVo  oldCarDistributionPlanVo=carDistributionPlanService.get(newVo.getId());

 		pubParamService.commonCheck(userService.getLogis(oldCarDistributionPlanVo.getLogisticsCompanyId()), request);
 
		if(!ISystemConstant.DICTIONARY_PLAN_STATUS_02.equals(oldCarDistributionPlanVo.getStatus()) 
				&& !ISystemConstant.DICTIONARY_PLAN_STATUS_11.equals(oldCarDistributionPlanVo.getStatus())
			&& !ISystemConstant.DICTIONARY_PLAN_STATUS_12.equals(oldCarDistributionPlanVo.getStatus())){
    		throw new PjException("该计划状态不对！");
			
		}		

        return carDistributionPlanService.doZhuToLadingBillList(request,oldCarDistributionPlanVo,flag);

    }
 
    /**
     * 主产品自提提货单   添加页面  
     */
    @RequestMapping(value = "toAddZhuProductLift")
    public String toAddZhuProductLift(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  

        //是否
        model.addAttribute("yesNot", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));     
        //提货单状态
        model.addAttribute("ladingBillStatusMap", RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS));     
        //发运方式
        shipModeService.list(model);

        return "/yulinchemical/bus/cardistributionplan/toAddZhuProductLift";
    }
    
    
    
    /**
     *  主产品自提添加的保存提交
     */
    @RequestMapping(value = "addZhuToLadingBillList", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
      public Map<String,Object> addZhuToLadingBillList(HttpServletRequest request, @Valid CarDistributionPlanVo newVo, @RequestParam(value="flag",required=true)String flag) {
    	
        return carDistributionPlanService.addZhuToLadingBillList(request,newVo,flag);

    }

    
    
    /**
     * 汽运配送提货单   关联车辆页面  
     * 
     */
    @RequestMapping(value = "toUpdateCarDistributionLading")
    public String toUpdateCarDistributionLading(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request  
        String id=request.getParameter("id");
    	CarDistributionPlanVo vo=carDistributionPlanService.get(id);
    	
    	String operation = request.getParameter(ISystemConstant.DICTIONARY_OPERATION);
    	
    	model.addAttribute(ISystemConstant.DICTIONARY_OPERATION, operation);
    	if(ISystemConstant.DICTIONARY_OPERATION_COORDINATION.equals(operation)){
    		
//    		if(!vo.getLogisticsCompanyId().equals(RmProjectHelper.getRmUserId(request))){
//    			return "redirect:/cardistributionplan/listCarDistributionLading?" + request.getQueryString();
//    		}
    	}
    	
    	model.addAttribute(ISystemConstant.AJAX_BEAN, vo);
    	
        //是否
        model.addAttribute("yesNot", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));     
        //是否
        model.addAttribute("ladingBillStatusMap", RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS));     

        String ladingBillId = request.getParameter("ladingBIllId");
        
        String url = "/yulinchemical/bus/cardistributionplan/toUpdateCarDistributionLading";
        
        List<LadingBillVo> list = ladingBillService.list(vo.getId(), vo.getProductId(), ladingBillId);
        
        if(!StringHelper.isEmpty(ladingBillId)){
        	url = "/yulinchemical/bus/cardistributionplan/ladingBillDetailForOrderType";
        	pubParamService.commonFlagModel(request, model);
        	model.addAttribute("ladingBillStatus", list.get(0).getLadingBillStatus());
        }
//		//提货单list
		model.addAttribute(REQUEST_BEANS, list);

		pubParamService.commonModel(request, model);
		model.addAttribute("uri", request.getParameter("uri"));
        return url;
    }
    
    
    
    /**
     * 分配物流公司
     */
    @RequestMapping(value = "updateToLogis", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updateToLogis(HttpServletRequest request, @Valid CarDistributionPlanVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();

        //实际里程==计划里程
        vo.setFactMileage(vo.getPlannedMileage());
        //距离系数
        BigDecimal oneMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01);
        //计价里程==计划里程*距离系数
        vo.setValuationMileage(vo.getPlannedMileage().multiply(oneMileage));
        //实际计价里程==实际里程*距离系数
        vo.setValuationMileage(vo.getFactMileage().multiply(oneMileage));
        //运价系数（一般为0.31）
        BigDecimal twoMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02);
        //结算运费 == 计价里程*运价系数（一般为0.31）
        vo.setSettlementFreight(vo.getValuationMileage().multiply(twoMileage));
        //总运费 == 结算运价*数量
        vo.setTotalFreight(vo.getSettlementFreight().multiply(new BigDecimal(vo.getNum())));
    	//待分配车辆
        vo.setStatus(ISystemConstant.DICTIONARY_PLAN_STATUS_02);
    	RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        carDistributionPlanService.update(vo);  //更新单条记录
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "修改成功: " + vo.getId());
        return result;
    }

    /**
     * 分配物流公司  后改的
     */
    @RequestMapping(value = "doUpdateLogisticsIds", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> doUpdateLogisticsIds(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            carDistributionPlanService.doUpdateLogisticsIds(request);  //更新单条记录
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
            result.put(ISystemConstant.AJAX_MESSAGE, "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
	        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
	        result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
		}
        
        return result;
    }

    
}
