/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  RowNumberController.java
 *
 * 功能描述：  排号表Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.rownumber.web;



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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfhc.bus.rownumber.IRowNumberConstants;
import com.dfhc.bus.rownumber.service.RowNumberService;
import com.dfhc.bus.rownumber.vo.RowNumberVo;
import com.dfhc.ISystemConstant;
import com.alibaba.fastjson.JSONObject;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * list                  /rownumber
 * insert page      GET  /rownumber/insert
 * insert action    POST /rownumber/insert
 * update page      GET  /rownumber/update/{id}
 * update action    POST /rownumber/update
 * delete action    POST /rownumber/delete
 * detail                /rownumber/detail/{id}

 * reference             /rownumber/reference
 * import page      GET  /rownumber/import
 * import action    POST /rownumber/import
 * export custom    GET  /rownumber/export
 * export action    POST /rownumber/export
 */

/**
 * 排号表 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/rownumber")
public class RowNumberController implements IRowNumberConstants {

    @Autowired
    /**
     * 排号表服务
     */
    private RowNumberService rowNumberService;
    
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
    	model.addAttribute("alreadySign", request.getParameter("alreadySign"));
    	model.addAttribute("queryString", request.getQueryString());
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request
        return "/yulinchemical/bus/rownumber/listRowNumber";
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
        
        String page=request.getParameter("page")==null?"1":request.getParameter("page");
        String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
        int rowsInt=Integer.parseInt(rows);//页大小
        int pageInt=Integer.parseInt(page);//当前页
        Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件

        //获取产品查询参数
        searchPara = getProductQueryParam(searchPara, request);
        
//        searchPara.put("rowDate", DateUtil.toSqlDate(DateUtil.getNowDate()));
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, rowNumberService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        //列表左关联提货单
        if(request.getAttribute("referenceTruckNo") == null){
        	
        	searchPara.put("leftLadingBill", "leftLadingBill");
        }
        
        List<RowNumberVo> beans = rowNumberService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        searchPara.clear();
        searchPara.put("page",  pageVo.getCurrentPage());
        searchPara.put("total",  pageVo.getPageCount());
        searchPara.put("records",  pageVo.getRecordCount());
        searchPara.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(searchPara));
    }

    /**
     * 获取产品查询参数
     * @param searchPara
     * @return
     */
    private Map<String, Object> getProductQueryParam(
			Map<String, Object> searchPara, HttpServletRequest request) {
		if(searchPara == null){
			searchPara = new HashMap<String, Object>();
		}
    	
		//产品id
    	String productId = request.getParameter("productId");
    	//产品类别id
    	String productTypeId = request.getParameter("productTypeId");
    	if(!StringHelper.isEmpty(productId) || !StringHelper.isEmpty(productTypeId)){
    		searchPara.put("sequenceId", StringHelper.isEmpty(productId)?productTypeId:productId);
    	}
    	String alreadySign = request.getParameter("alreadySign");
    	
    	if(!StringHelper.isEmpty(alreadySign)){
    		searchPara.put("alreadySign", "alreadySign");
    		if(StringHelper.isEmpty(request.getParameter("rowDate")) && "alreadySign".equals(alreadySign)){
    			searchPara.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
    		}
    	}
		return searchPara;
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid RowNumberVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        rowNumberService.insert(vo);  //插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid RowNumberVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        rowNumberService.update(vo);  //更新单条记录
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
        RowNumberVo bean = rowNumberService.get(id);
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
            deleteCount = rowNumberService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += rowNumberService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/rownumber?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = rowNumberService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += rowNumberService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/rownumber?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        RowNumberVo bean = rowNumberService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/bus/rownumber/detailRowNumber";
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
        return "/yulinchemical/bus/rownumber/util/referenceRowNumber";
    }
    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "referenceTruckNo")
    public String referenceTruckNo(Model model, HttpServletRequest request) {
    	model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
    	return "/yulinchemical/bus/rownumber/util/referenceRowNumberTruckNo";
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
     * 参照改为ajax
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "ajaxReferenceTruckNo")
    public void ajaxReferenceTruckNo(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	request.setAttribute("referenceTruckNo", "referenceTruckNo");
    	ajaxList(model, request, response);//ajax yemian 
    }
    /**
     * 跳转到导入页
     * @param model 模型
     * @return 返回导入页
     */
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importDataForm(Model model) {
        return "/yulinchemical/bus/rownumber/importRowNumber";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/bus/rownumber/importRowNumber";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/bus/rownumber/exportRowNumber_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/bus/rownumber/exportRowNumber_excel";
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
           searchMap.put("rowType", request.getParameter("rowType"));
           searchMap.put("rowDate", request.getParameter("rowDate"));
           searchMap.put("sequence", request.getParameter("sequence"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("truckNo", request.getParameter("truckNo"));
           searchMap.put("sequenceId", request.getParameter("sequenceId"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    /**
     * 签到（作废）
     * @param request
     * @return
     */
//    @RequestMapping(value = "sign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public Map<String, Object> sign(HttpServletRequest request){
    	
    	//手机号
    	String phoneNumber = request.getParameter("phoneNumber");
    	
    	
    	//验证
    	Map<String, Object> result = rowNumberService.checkedSignPhoneNumber(phoneNumber);
    	
    	if(result != null){
    		
    		return result;
    	}
    	//签到
//    	result = rowNumberService.updateSign(request, phoneNumber);
    	
    	return result;
    }
    
}
