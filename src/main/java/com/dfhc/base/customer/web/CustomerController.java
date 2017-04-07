/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CustomerController.java
 *
 * 功能描述：  客户管理Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.customer.web;



import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.base.customer.ICustomerConstants;
import com.dfhc.base.customer.service.CustomerService;
import com.dfhc.base.customer.vo.CustomerVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.StringHelper;

/**
 * list                  /customer
 * insert page      GET  /customer/insert
 * insert action    POST /customer/insert
 * update page      GET  /customer/update/{id}
 * update action    POST /customer/update
 * delete action    POST /customer/delete
 * detail                /customer/detail/{id}

 * reference             /customer/reference
 * import page      GET  /customer/import
 * import action    POST /customer/import
 * export custom    GET  /customer/export
 * export action    POST /customer/export
 */

/**
 * 客户管理 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController implements ICustomerConstants {

    @Autowired
    /**
     * 客户管理服务
     */
    private CustomerService customerService;
    
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
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        model.addAttribute("status", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));
        
        pubParamService.commonModel(request, model);
        return "/yulinchemical/base/customer/listCustomer";
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
        
        String typeString=request.getParameter("typeString");
        //默认关联rm_user表查询客户名称
        searchPara.put("customerManager", "customerManager");

        if("typeString".equals(typeString)){
        	searchPara.put("customerManager", "customerManager");
        }
        
        String operation = request.getParameter(ISystemConstant.DICTIONARY_OPERATION);
        if(ISystemConstant.DICTIONARY_OPERATION_COORDINATION.equals(operation)){
        	searchPara.put("userId", RmProjectHelper.getRmUserId(request));
        }else if(ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_CUSTOMER_FINANCE.equals(operation)){
        	searchPara.put(ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_CUSTOMER_FINANCE, ISystemConstant.DICTIONARY_OPERATION_LOGISTICS_CUSTOMER_FINANCE);
        	searchPara.put("delete_flag2", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        	searchPara.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        	searchPara.put("financeIsStatus", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
        	searchPara.put("delete_flag3", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        	searchPara.put("customerManager", "customerManager");
        	
        }
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, customerService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<CustomerVo> beans = customerService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        String jsonString = JSONObject.toJSONString(dataMap);
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid CustomerVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
//        customerService.insert(vo);  //插入单条记录
        customerService.insert(vo, request);
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
    public Map<String,Object> update(HttpServletRequest request, @Valid CustomerVo vo) {
    	 Map<String, Object> result = new HashMap<String, Object>();
    	String operation = request.getParameter("operation");
    	
    	if("coordination".equals(operation)){
    		if(!vo.getUserId().equals(RmProjectHelper.getRmUserId(request))){
    			 result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		        result.put(ISystemConstant.AJAX_MESSAGE, "只能修改自己的信息！");
    		        return result;
    		}
    	}
    	
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
//        customerService.update(vo);  //更新单条记录
        customerService.update(vo,request);  //更新单条记录
       
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
//        CustomerVo bean = customerService.get(id);
    	CustomerVo bean = customerService.get(id,request);
        Map<String, Object> result = new HashMap<String, Object>();
        if(bean==null){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "记录没找到(id:"+id+")!");
            return result;
        }
        
        String operation = request.getParameter("operation");
        if("coordination".equals(operation)){
        	if(!bean.getUserId().equals(RmProjectHelper.getRmUserId(request))){
        		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
                result.put(ISystemConstant.AJAX_MESSAGE, "只能修改自己的信息!");
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
            deleteCount = customerService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += customerService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/customer?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            //deleteCount = customerService.deleteLogic(request,new String(id));
        	deleteCount = customerService.doDelete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += customerService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/customer?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
    }
    /**
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="ajaxDeleteFreight",method=RequestMethod.POST,
    		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    
    @ResponseBody
    public Map<String,Object> ajaxDeleteFreight(HttpServletRequest request,Model model){
    	
    	Map<String,Object> result=new HashMap<String,Object>();
    	
    	  String id = request.getParameter(REQUEST_ID);
          int deleteCount = 0;  //定义成功删除的记录数
          if (id != null && id.length() > 0) {
        	  deleteCount = customerService.deleteLogic(request,id);
          }else{
        	  String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
          
          if (ids != null && ids.length != 0) {
              deleteCount += customerService.deleteLogic(request,ids);  //删除多条记录
          } 
          }
        result.put("msg", "发货方删除成功");
        
    	return result;
    	
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
        CustomerVo bean = customerService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/base/customer/detailCustomer";
    }

    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "reference")
    public String reference(Model model, HttpServletRequest request) {
    	model.addAttribute("queyString", request.getQueryString());
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        return "/yulinchemical/base/customer/util/referenceCustomer";
    }
    @RequestMapping(value = "referenceCustomerFinance")
    public String referenceCustomerFinance(Model model, HttpServletRequest request) {
    	model.addAttribute("queyString", request.getQueryString());
    	model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
    	return "/yulinchemical/base/customer/util/referenceCustomerFinance";
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
        return "/yulinchemical/base/customer/importCustomer";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/base/customer/importCustomer";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/base/customer/exportCustomer_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/base/customer/exportCustomer_excel";
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
           searchMap.put("userId", request.getParameter("userId"));
           searchMap.put("abbreviation", request.getParameter("abbreviation"));
           searchMap.put("indexCode", request.getParameter("indexCode"));
           searchMap.put("regAddr", request.getParameter("regAddr"));
           searchMap.put("officeAddr", request.getParameter("officeAddr"));
           searchMap.put("postAddr", request.getParameter("postAddr"));
           searchMap.put("postCode", request.getParameter("postCode"));
           searchMap.put("fax", request.getParameter("fax"));
           searchMap.put("tel", request.getParameter("tel"));
           searchMap.put("provinceId", request.getParameter("provinceId"));
           searchMap.put("provinceIdName", request.getParameter("provinceIdName"));
           searchMap.put("cityId", request.getParameter("cityId"));
           searchMap.put("cityName", request.getParameter("cityName"));
           searchMap.put("bank", request.getParameter("bank"));
           searchMap.put("accountName", request.getParameter("accountName"));
           searchMap.put("taxNo", request.getParameter("taxNo"));
           searchMap.put("legalPerson", request.getParameter("legalPerson"));
           searchMap.put("remark", request.getParameter("remark"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("regionId", request.getParameter("regionId"));
           searchMap.put("contacts", request.getParameter("contacts"));
           searchMap.put("contactPhone", request.getParameter("contactPhone"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
           searchMap.put("mainCode", request.getParameter("mainCode"));
           searchMap.put("isByProduct", request.getParameter("isByProduct"));
           searchMap.put("isMainProduct",request.getParameter("isMainProduct"));
        }
        return searchMap;
    }
    /**
     * 去运费维护页面
     * @param id
     * @param request
     * @param model
     * @return toFreight
     */
    @RequestMapping(value="toAddReceiptAddress/{id}",method=RequestMethod.GET)
    public String toAddReceiptAddress(@PathVariable  String id ,HttpServletRequest request ,Model model){
        
    	model.addAttribute("status", RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT));
        
    	if(StringHelper.isEmpty(id)){
    		
    		throw new PjException("没找到对应的收货方id");
    	}
    	Map< String, Object> searchPara=new HashMap<String ,Object>();
    	
    	searchPara.put("id",id );
    	
     	searchPara.put( "delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	
    	List<CustomerVo> listCustomerVo=customerService.getI(searchPara, "", 1, -1);
    	
    	if (listCustomerVo.size()!=1) {
    		
    		throw new PjException("该数据只能有一条");
		}
    	CustomerVo customerVo= listCustomerVo.get(0);
    	
    	searchPara.clear();
    
    	model.addAttribute("bean", customerVo);
    	
    	return  "/yulinchemical/base/customer/toAddReceiptAddress";
    	
    }
}
