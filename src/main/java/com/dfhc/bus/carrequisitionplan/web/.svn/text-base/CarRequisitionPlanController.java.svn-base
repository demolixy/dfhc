/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CarRequisitionPlanController.java
 *
 * 功能描述：  汽运调拨计划Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.carrequisitionplan.web;



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
import com.dfhc.base.freight.service.FreightService;
import com.dfhc.base.freight.vo.FreightVo;
import com.dfhc.base.shipmode.service.ShipModeService;
import com.dfhc.base.shipmode.vo.ShipModeVo;
import com.dfhc.base.shipper.service.ShipperService;
import com.dfhc.bus.carrequisitionplan.ICarRequisitionPlanConstants;
import com.dfhc.bus.carrequisitionplan.service.CarRequisitionPlanService;
import com.dfhc.bus.carrequisitionplan.vo.CarRequisitionPlanVo;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * list                  /carrequisitionplan
 * insert page      GET  /carrequisitionplan/insert
 * insert action    POST /carrequisitionplan/insert
 * update page      GET  /carrequisitionplan/update/{id}
 * update action    POST /carrequisitionplan/update
 * delete action    POST /carrequisitionplan/delete
 * detail                /carrequisitionplan/detail/{id}

 * reference             /carrequisitionplan/reference
 * import page      GET  /carrequisitionplan/import
 * import action    POST /carrequisitionplan/import
 * export custom    GET  /carrequisitionplan/export
 * export action    POST /carrequisitionplan/export
 */

/**
 * 汽运调拨计划 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/carrequisitionplan")
public class CarRequisitionPlanController implements ICarRequisitionPlanConstants {
	@Autowired
	private  ShipModeService  shipModeService;

	@Autowired
	private  FreightService freightService;
	@Autowired
	private  PubParamService  pubParamService;
    @Autowired
    /**
     * 汽运调拨计划服务
     */
    private CarRequisitionPlanService carRequisitionPlanService;
    
    @Autowired
    private LadingBillService ladingBillService;

    @Autowired
    private  ShipperService shipperService;
    /**
     * 汽运调拨计划
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
       
        pubParamService.commonModel(request, model);
        
        model.addAttribute("shipperCodeLu", shipperService.list());
        //状态
        model.addAttribute(ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS));
        return "/yulinchemical/bus/carrequisitionplan/listCarRequisitionPlan";
    }
   
    /**
     * 汽运调拨计划ajax 请求列表数据
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
  
       	String operation = request.getParameter(ISystemConstant.DICTIONARY_OPERATION);
    	if(!StringHelper.isEmpty(operation)){
    		//除了未提交的
    		searchPara.put("notStatus", ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS_00);
    	}

    	//开具提货单列表
    	if(ILadingBillConstants.LADING_BILL.equals(operation)){
    		//剩余车数不为零的
    		searchPara.put("remainderCarNumIsNotZero", "'"+ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00+"'");
    	}
   	
    	
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, carRequisitionPlanService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<CarRequisitionPlanVo> beans = carRequisitionPlanService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid CarRequisitionPlanVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
    	
    	try {
        	//默认添加值
        	//计划单号
    		vo.setPlanCode(pubParamService.getSequenceNumber(ICarRequisitionPlanConstants.CAR_REQUISITION_PLAN_CODE));
        	//制表日期
        	vo.setMakeDate(new java.sql.Date(DateUtil.getNowDate().getTime()));
        	//计划车数==剩余车数
        	vo.setRemainderCarNum(vo.getPlanCarNum());
        	//剩余数量（吨）==数量（吨）
        	vo.setRemainderNum(vo.getNum());
        	//状态  未提交
        	vo.setStatus(ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS_00);
        	//发运方式  汽运配送
        	vo.setShipModeCode(ISystemConstant.DICTIONARY_SHIP_MODE_01);
        	ShipModeVo shipModeVo=shipModeService.list(ISystemConstant.DICTIONARY_SHIP_MODE_01);
        	vo.setShipMode(shipModeVo==null?"汽运配送":shipModeVo.getName());
 
        	//调拨仓库 运费
        	FreightVo  freightVo=freightService.doGetFreightVo(vo.getShipperCode(), vo.getRequisitionWarehouseId(),ISystemConstant.DICTIONARY_TARGET_TYPE_01);
        	//运价(元/吨)
        	vo.setYuanTon(freightVo.getYuanTon());
        	//运费=运价*数量
        	vo.setFreightYuanTon(vo.getYuanTon().multiply(new BigDecimal(vo.getNum())));

            RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
            carRequisitionPlanService.insert(vo);  //插入单条记录
            
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
    public Map<String,Object> update(HttpServletRequest request, @Valid CarRequisitionPlanVo vo) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
        	if(!ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS_00.equals(vo.getStatus())){
                result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
                result.put(ISystemConstant.AJAX_MESSAGE, "已提交的不能修改");
        		return  result;
        	}
        	
        	//计划车数==剩余车数
        	vo.setRemainderCarNum(vo.getPlanCarNum());
        	//剩余数量（吨）==数量（吨）
        	vo.setRemainderNum(vo.getNum());
        	//调拨仓库 运费
        	FreightVo  freightVo=freightService.doGetFreightVo(vo.getShipperCode(), vo.getRequisitionWarehouseId(),ISystemConstant.DICTIONARY_TARGET_TYPE_01);
        	//运价(元/吨)
        	vo.setYuanTon(freightVo.getYuanTon());
        	//运费=运价*数量
        	vo.setFreightYuanTon(vo.getYuanTon().multiply(new BigDecimal(vo.getNum())));
       	
            RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
            carRequisitionPlanService.update(vo);  //更新单条记录
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
     * 根据id获取单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "get/{id}/{dateTime}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> get(@PathVariable("id") String id, HttpServletRequest request) {
        CarRequisitionPlanVo bean = carRequisitionPlanService.get(id);
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
            deleteCount = carRequisitionPlanService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += carRequisitionPlanService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/carrequisitionplan?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = carRequisitionPlanService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += carRequisitionPlanService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/carrequisitionplan?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        CarRequisitionPlanVo bean = carRequisitionPlanService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/carrequisitionplan/detailCarRequisitionPlan";
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
        return "/yulinchemical/bus/carrequisitionplan/util/referenceCarRequisitionPlan";
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
        return "/yulinchemical/bus/carrequisitionplan/importCarRequisitionPlan";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/carrequisitionplan/importCarRequisitionPlan";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/carrequisitionplan/exportCarRequisitionPlan_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/carrequisitionplan/exportCarRequisitionPlan_excel";
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
           searchMap.put("makeDate", request.getParameter("makeDate"));
           searchMap.put("logisticsCompany", request.getParameter("logisticsCompany"));
           searchMap.put("logisticsCompanyId", request.getParameter("logisticsCompanyId"));
           searchMap.put("goodsName", request.getParameter("goodsName"));
           searchMap.put("productId", request.getParameter("productId"));
           searchMap.put("requisitionWarehouse", request.getParameter("requisitionWarehouse"));
           searchMap.put("requisitionWarehouseId", request.getParameter("requisitionWarehouseId"));
           searchMap.put("shippingAddress", request.getParameter("shippingAddress"));
           searchMap.put("num", request.getParameter("num"));
           searchMap.put("planCarNum", request.getParameter("planCarNum"));
           searchMap.put("plannedMileage", request.getParameter("plannedMileage"));
           searchMap.put("factMileage", request.getParameter("factMileage"));
           searchMap.put("valuationMileage", request.getParameter("valuationMileage"));
           searchMap.put("yuanTon", request.getParameter("yuanTon"));
           searchMap.put("freightYuanTon", request.getParameter("freightYuanTon"));
           searchMap.put("unitPrice", request.getParameter("unitPrice"));
           searchMap.put("remark", request.getParameter("remark"));
           searchMap.put("sendCarPlanImage", request.getParameter("sendCarPlanImage"));
           searchMap.put("remainderNum", request.getParameter("remainderNum"));
           searchMap.put("remainderCarNum", request.getParameter("remainderCarNum"));
           searchMap.put("shipperCode", request.getParameter("shipperCode"));
           searchMap.put("flowToId", request.getParameter("flowToId"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("sendCarDate", request.getParameter("sendCarDate"));
           searchMap.put("invalidCarNumber", request.getParameter("invalidCarNumber"));
           searchMap.put("invalidNumber", request.getParameter("invalidNumber"));
           searchMap.put("shippingVehiclesNumber", request.getParameter("shippingVehiclesNumber"));
           searchMap.put("shippingNumber", request.getParameter("shippingNumber"));
           searchMap.put("shipMode", request.getParameter("shipMode"));
           searchMap.put("shipModeCode", request.getParameter("shipModeCode"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
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
                carRequisitionPlanService.doUpdateListStatus(request,ids,ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS_01);  //删除多条记录
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
     * 汽运调拨详情
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="toDetail")
    public String toDetail(HttpServletRequest request, Model model, @RequestParam(value="carRequisitionPlanId", required=true)String carRequisitionPlanId){
    	
    	CarRequisitionPlanVo carRequisitionPlanVo = carRequisitionPlanService.get(carRequisitionPlanId);
    	
    	if(null == carRequisitionPlanVo){
    		
    		return "redirect:/carrequisitionplan?" + request.getQueryString();
    	}
    	Map<String, Object> variable = new HashMap<String, Object>();
    	
    	String ladingBIllId = request.getParameter("ladingBIllId");
    	
    	
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	variable.put("billType", ISystemConstant.DICTIONARY_BILL_TYPE_03);
    	variable.put("orderPlanId", carRequisitionPlanId);
    	variable.put("id", ladingBIllId);
    	List<LadingBillVo> list = ladingBillService.list(variable, null);
    	if(!StringHelper.isEmpty(carRequisitionPlanVo.getSendCarPlanImage())){
    		model.addAttribute("attacmentList", StringHelper.splitString(carRequisitionPlanVo.getSendCarPlanImage(), ","));
    	}
    	String url = "/yulinchemical/bus/carrequisitionplan/toDetail";
    	if(!StringHelper.isEmpty(ladingBIllId)){
    		
    		url = "/yulinchemical/bus/carrequisitionplan/ladingBillDetailForOrderType";
    		pubParamService.commonFlagModel(request, model);
    		model.addAttribute("ladingBillStatus", list.get(0).getLadingBillStatus());
    	}
    	
    	pubParamService.commonModel(request, model);
    	model.addAttribute(REQUEST_BEAN, carRequisitionPlanVo);
    	model.addAttribute(REQUEST_BEANS, list);
    	model.addAttribute("uri", request.getParameter("uri"));
    	return url;
    }
    
    
}

