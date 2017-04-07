/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ReceiptConfLetterController.java
 *
 * 功能描述：  收货确认函Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.acc.receiptconfletter.web;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.quickbundle.base.beans.factory.RmIdFactory;
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
import com.dfhc.acc.receiptconfletter.IReceiptConfLetterConstants;
import com.dfhc.acc.receiptconfletter.service.ReceiptConfLetterService;
import com.dfhc.acc.receiptconfletter.vo.ReceiptConfLetterVo;
import com.dfhc.base.shipper.service.ShipperService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.StringHelper;

/**
 * list                  /receiptconfletter
 * insert page      GET  /receiptconfletter/insert
 * insert action    POST /receiptconfletter/insert
 * update page      GET  /receiptconfletter/update/{id}
 * update action    POST /receiptconfletter/update
 * delete action    POST /receiptconfletter/delete
 * detail                /receiptconfletter/detail/{id}

 * reference             /receiptconfletter/reference
 * import page      GET  /receiptconfletter/import
 * import action    POST /receiptconfletter/import
 * export custom    GET  /receiptconfletter/export
 * export action    POST /receiptconfletter/export
 */

/**
 * 收货确认函 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/receiptconfletter")
public class ReceiptConfLetterController implements IReceiptConfLetterConstants {

    @Autowired
    /**
     * 收货确认函服务
     */
    private ReceiptConfLetterService receiptConfLetterService;
    
    @Autowired
    private ShipperService shipperService;
    @Autowired
    private PubParamService pubParamService;
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
    	String requestId = RmIdFactory.requestId("DOCUMENT_NUMBER");
		System.out.println(requestId);
    	
    	shipperService.list(model);
    	
    	pubParamService.commonModel(request, model);
    	model.addAttribute(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS));
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        return "/yulinchemical/acc/receiptconfletter/listReceiptConfLetter";
    }
  
    
    /**
     * 副产品开发票申请
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "listApplySettlement")
    public String listApplySettlement(Model model, HttpServletRequest request) {
    	shipperService.list(model);
    	
    	pubParamService.commonModel(request, model);
    	model.addAttribute(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS, RmGlobalReference.get(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS));
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        return "/yulinchemical/acc/receiptconfletter/listApplySettlement";
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
        
        //副产品结算管理
        String FCPSettlement = request.getParameter("FCPSettlement");
        
        if(!StringHelper.isEmpty(FCPSettlement)){
        	searchPara.put("FCPSettlement", "FCPSettlement");
        	
        }
        pubParamService.commonParamsCustomer(request, searchPara);
        
        String statuss = request.getParameter("statuss");
        searchPara.put("statuss", statuss);
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, receiptConfLetterService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<ReceiptConfLetterVo> beans = receiptConfLetterService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid ReceiptConfLetterVo vo, @RequestParam(required=true,value="ladingBillId")String ladingBillId) {
          //打创建时间,IP戳
        return receiptConfLetterService.insert(vo, ladingBillId, request);  //插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid ReceiptConfLetterVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        receiptConfLetterService.update(vo);  //更新单条记录
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
        ReceiptConfLetterVo bean = receiptConfLetterService.get(id);
        Map<String, Object> result = new HashMap<String, Object>();

        
        if(bean==null){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
            return result;
        }
        
        if(!ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03.equals(bean.getStatus())){
        	result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "不是待提交不能修改!");
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
            deleteCount = receiptConfLetterService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += receiptConfLetterService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/receiptconfletter?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = receiptConfLetterService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += receiptConfLetterService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/receiptconfletter?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        ReceiptConfLetterVo bean = receiptConfLetterService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/acc/receiptconfletter/detailReceiptConfLetter";
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
        return "/yulinchemical/acc/receiptconfletter/util/referenceReceiptConfLetter";
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
        return "/yulinchemical/acc/receiptconfletter/importReceiptConfLetter";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/acc/receiptconfletter/importReceiptConfLetter";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/acc/receiptconfletter/exportReceiptConfLetter_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/acc/receiptconfletter/exportReceiptConfLetter_excel";
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
           searchMap.put("documentNumber", request.getParameter("documentNumber"));
           searchMap.put("customerId", request.getParameter("customerId"));
           searchMap.put("customerName", request.getParameter("customerName"));
           searchMap.put("settlementStartDate", request.getParameter("settlementStartDate"));
           searchMap.put("settlementEndDate", request.getParameter("settlementEndDate"));
           searchMap.put("applyPerson", request.getParameter("applyPerson"));
           searchMap.put("applyPersionId", request.getParameter("applyPersionId"));
           searchMap.put("applyTime", request.getParameter("applyTime"));
           searchMap.put("totalAmount", request.getParameter("totalAmount"));
           searchMap.put("bookBalance", request.getParameter("bookBalance"));
           searchMap.put("shipperCode", request.getParameter("shipperCode"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("actualSettlementAmount", request.getParameter("actualSettlementAmount"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    /**
     * 收货确认函详情
     * @param request
     * @return
     */
    @RequestMapping(value = "receiptConfLetterDetail")
    public String receiptConfLetterDetail(@RequestParam(required=true, value="id") String id, HttpServletRequest request, Model model){
    	
    	ReceiptConfLetterVo receiptConfLetterVo = receiptConfLetterService.get(id);
    	String operation = request.getParameter("operation");
    	
    	if(receiptConfLetterVo == null){
    		
    		return "redirect:/receiptconfletter?" + ISystemConstant.RM_PARENT_CODE + "=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&operation=" + operation;
    	}
    	if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(receiptConfLetterVo.getDelete_flag())){
    		return "redirect:/receiptconfletter?" + ISystemConstant.RM_PARENT_CODE + "=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&operation=" + operation;
    	}
    	
    	if("coordination".equals(operation)){
    		if(!receiptConfLetterVo.getCustomerId().equals(RmProjectHelper.getRmUserId(request))){
    			return "redirect:/receiptconfletter?" + ISystemConstant.RM_PARENT_CODE + "=" + request.getSession().getAttribute(ISystemConstant.RM_PARENT_CODE) + "&operation=" + operation;
    		}
    	}
    	
    	model.addAttribute("operation", operation);
    	model.addAttribute(REQUEST_BEAN, receiptConfLetterVo);
    	return "/yulinchemical/acc/receiptconfletter/toDetail";
    }
    
    /**
     * 修改收货确认函的状态
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> updateStatus(HttpServletRequest request, @RequestParam(value="id", required=true) String id, @RequestParam(required=false, value="flag") String flag){
    	ReceiptConfLetterVo vo = receiptConfLetterService.get(id);
    	Map<String, Object> result = new HashMap<String, Object>();
    	//退回
    	if(StringHelper.isEmpty(flag)){
    		returnStatus(result, vo, request);
    	}else{
    		
    		//确认提交
    		if("confirmSubmit".equals(flag)){
    			confirmSubmit(result, vo, request);
    		}
    		//确认结算
    		if("confirmPurchase".equals(flag)){
    			receiptConfLetterService.confirmSubmit(result, vo, request);
    		}
    		
    	}
    	
    	
    	return result;
    }

    /**
     * 待提交收货确认函确认提交
     * @param result
     * @param vo
     * @param request
     */
    private void confirmSubmit(Map<String, Object> result,
			ReceiptConfLetterVo vo, HttpServletRequest request) {
    	if(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03.equals(vo.getStatus())){//待提交
    		
    		vo.setStatus(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_00);
    		
    		RmVoHelper.markModifyStamp(request, vo);
    		receiptConfLetterService.update(vo);
    		
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		result.put(ISystemConstant.AJAX_MESSAGE, "提交成功！");
    		
    	}else{
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "只有待提交的才能提交！");
    	}
		
	}

	/**
     * 待结算的收货确认函可以退回状态
     * @param result
     * @param vo
     */
	private void returnStatus(Map<String, Object> result, ReceiptConfLetterVo vo, HttpServletRequest request) {
		if(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_00.equals(vo.getStatus()) || ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03.equals(vo.getStatus())){//待结算
    		
			receiptConfLetterService.deleteLogic(request, vo.getId());
    		
    		
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		result.put(ISystemConstant.AJAX_MESSAGE, "退回成功！");
    		
    	}else if(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_02.equals(vo.getStatus())){
    		String operation = request.getParameter("operation");
	        if("coordination".equals(operation)){
	        	if(!vo.getCustomerId().equals(RmProjectHelper.getRmUserId(request))){
	        		throw new PjException("只能操作自己的结算单！");
	        	}
	        }
    		vo.setStatus(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_01);
    		RmVoHelper.markModifyStamp(request, vo);
    		receiptConfLetterService.update(vo);
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		result.put(ISystemConstant.AJAX_MESSAGE, "退回成功！");
    	}else{
    		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "已结算的不能退回！");
    	}
		
	}
	
	
	
    /**
     *  开发票申请详情
     * @param request
     * @return
     */
    @RequestMapping(value = "applySettlementDetail")
    public String applySettlementDetail(@RequestParam(required=true, value="id") String id, HttpServletRequest request, Model model){
    	
    	ReceiptConfLetterVo receiptConfLetterVo = receiptConfLetterService.get(id);
    	
    	if(receiptConfLetterVo == null){
    		
    		return "redirect:/receiptconfletter";
    	}
    	model.addAttribute(REQUEST_BEAN, receiptConfLetterVo);
    	return "/yulinchemical/acc/receiptconfletter/toApplySettlementDetailDetail";
    }
    
    
    /**
     * 申请开发票  修改状态
     * @param request 请求对象
     * @param vo 值对象
     * @param
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "doApplySettlement/{id}/{dateTime}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> doApplySettlement(@PathVariable("id") String id, HttpServletRequest request) {
        ReceiptConfLetterVo bean = receiptConfLetterService.get(id);
        Map<String, Object> result = new HashMap<String, Object>();
        if(bean==null){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
            return result;
        }
        
        if(!ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_01.equals(bean.getStatus())){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "该结算单状态不是已结算");
            return result;
        }
        
        //已开发票
        bean.setStatus(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_02);
        receiptConfLetterService.update(bean);
        
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "申请成功");
        return result;			
    }   

    /**
     * 副产品结算
     * @param request
     * @param ladingBillIds 提货单id
     * @return
     */
    @RequestMapping(value="settlement", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> settlement(HttpServletRequest request, @RequestParam(value="ladingBillIds", required=true)String ladingBillIds){
    	
    	return receiptConfLetterService.insertSettlement(request, ladingBillIds);
    }
}
