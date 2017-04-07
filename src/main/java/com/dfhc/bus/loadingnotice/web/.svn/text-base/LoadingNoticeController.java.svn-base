/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LoadingNoticeController.java
 *
 * 功能描述：  装车通知单Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.loadingnotice.web;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.quickbundle.base.web.page.RmPageVo;
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

import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.loadingnotice.ILoadingNoticeConstants;
import com.dfhc.bus.loadingnotice.service.LoadingNoticeService;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.ISystemConstant;
import com.alibaba.fastjson.JSONObject;
import com.dfhc.util.StringHelper;

/**
 * list                  /loadingnotice
 * insert page      GET  /loadingnotice/insert
 * insert action    POST /loadingnotice/insert
 * update page      GET  /loadingnotice/update/{id}
 * update action    POST /loadingnotice/update
 * delete action    POST /loadingnotice/delete
 * detail                /loadingnotice/detail/{id}

 * reference             /loadingnotice/reference
 * import page      GET  /loadingnotice/import
 * import action    POST /loadingnotice/import
 * export custom    GET  /loadingnotice/export
 * export action    POST /loadingnotice/export
 */

/**
 * 装车通知单 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/loadingnotice")
public class LoadingNoticeController implements ILoadingNoticeConstants {

    @Autowired
    /**
     * 装车通知单服务
     */
    private LoadingNoticeService loadingNoticeService;
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request
        model.addAttribute("queryString", request.getQueryString());
        model.addAttribute("completionConfirm", request.getParameter("completionConfirm"));
        return "/yulinchemical/bus/loadingnotice/listLoadingNotice";
    }
    @RequestMapping(value = "loadingNoticeList")
    public String loadingNoticeList(Model model, HttpServletRequest request) {
    	model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request
    	return "/yulinchemical/bus/loadingnotice/loadingNoticeList";
    }
    /**
     * 实现打印跳转
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp   
     */
    @RequestMapping(value = "printladingbill")
    public String printLadingBill(HttpServletRequest request, @RequestParam(value="ladingBillId", required=true)String ladingBillId, Model model){
    	List<LoadingNoticeVo> vos = loadingNoticeService.getPrint(ladingBillId);
    	LoadingNoticeVo vo = loadingNoticeService.get(ladingBillId);
    	for(LoadingNoticeVo o:vos) {
    		System.out.println(o);
    	}
    	model.addAttribute(REQUEST_BEAN, vo);
    	model.addAttribute(REQUEST_BEANS, vos);
    	return "/yulinchemical/bus/printladingbill/loadingNoticePrint";
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
        
        String loadingLading = request.getParameter("loadingLading");
        
        if(!StringHelper.isEmpty(loadingLading)){
        	
        	searchPara.put("loadingLading", "loadingLading");
        }
        
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, loadingNoticeService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        if(!StringHelper.isEmpty(request.getParameter("completionConfirm"))){//装车完成确认
        	searchPara.put("completionConfirm", "completionConfirm");
        }
        List<LoadingNoticeVo> beans = loadingNoticeService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 开具装车通知单提交   修改
     * @param request http请求对象
     * @param vo 值对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(HttpServletRequest request,@Valid LoadingNoticeVo loadingNoticeVo, @RequestParam(value="jqData", required=true)String jqData) {
       
        return loadingNoticeService.insertLoadingNotice(request,loadingNoticeVo,jqData);
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
    public Map<String,Object> update(HttpServletRequest request, @Valid LoadingNoticeVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        loadingNoticeService.update(vo);  //更新单条记录
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
        LoadingNoticeVo bean = loadingNoticeService.get(id);
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
            deleteCount = loadingNoticeService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += loadingNoticeService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/loadingnotice?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = loadingNoticeService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += loadingNoticeService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/loadingnotice?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        LoadingNoticeVo bean = loadingNoticeService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/loadingnotice/detailLoadingNotice";
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
        return "/yulinchemical/bus/loadingnotice/util/referenceLoadingNotice";
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
        return "/yulinchemical/bus/loadingnotice/importLoadingNotice";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/loadingnotice/importLoadingNotice";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/loadingnotice/exportLoadingNotice_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/loadingnotice/exportLoadingNotice_excel";
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
           searchMap.put("ladingBillId", request.getParameter("ladingBillId"));
           searchMap.put("ladingBillCode", request.getParameter("ladingBillCode"));
           searchMap.put("jobTime", request.getParameter("jobTime"));
           searchMap.put("dispatchTime", request.getParameter("dispatchTime"));
           searchMap.put("licensePlate", request.getParameter("licensePlate"));
           searchMap.put("productId", request.getParameter("productId"));
           searchMap.put("productName", request.getParameter("productName"));
           searchMap.put("productModelNumber", request.getParameter("productModelNumber"));
           searchMap.put("productBatchNumber", request.getParameter("productBatchNumber"));
           searchMap.put("productLevel", request.getParameter("productLevel"));
           searchMap.put("productLevelId", request.getParameter("productLevelId"));
           searchMap.put("num", request.getParameter("num"));
           searchMap.put("gateNum", request.getParameter("gateNum"));
           searchMap.put("positionNum", request.getParameter("positionNum"));
           searchMap.put("toneNum", request.getParameter("toneNum"));
           searchMap.put("storemanId", request.getParameter("storemanId"));
           searchMap.put("storeman", request.getParameter("storeman"));
           searchMap.put("forkliftMonitorId", request.getParameter("forkliftMonitorId"));
           searchMap.put("forkliftMonitor", request.getParameter("forkliftMonitor"));
           searchMap.put("operationSupervisorId", request.getParameter("operationSupervisorId"));
           searchMap.put("operationSupervisor", request.getParameter("operationSupervisor"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("printDate", request.getParameter("printDate"));
           searchMap.put("forkliftTruckIsConfirm", request.getParameter("forkliftTruckIsConfirm"));
           searchMap.put("supervisorIsConfirm", request.getParameter("supervisorIsConfirm"));
           searchMap.put("forkliftTruckConfirmTime", request.getParameter("forkliftTruckConfirmTime"));
           searchMap.put("supervisorConfirmTime", request.getParameter("supervisorConfirmTime"));
           searchMap.put("truckInputBreakBagNum", request.getParameter("truckInputBreakBagNum"));
           searchMap.put("supervisorInputBreakBagNum", request.getParameter("supervisorInputBreakBagNum"));
           searchMap.put("actualDeliveryNumber", request.getParameter("actualDeliveryNumber"));
           searchMap.put("isNeedWeigh", request.getParameter("isNeedWeigh"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
   
}
