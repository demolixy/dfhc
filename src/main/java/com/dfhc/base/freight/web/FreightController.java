/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  FreightController.java
 *
 * 功能描述：  运费维护Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.freight.web;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfhc.base.freight.IFreightConstants;
import com.dfhc.base.freight.service.FreightService;
import com.dfhc.base.freight.vo.FreightVo;
import com.dfhc.ISystemConstant;
import com.alibaba.fastjson.JSONObject;
import com.dfhc.util.StringHelper;

/**
 * list                  /freight
 * insert page      GET  /freight/insert
 * insert action    POST /freight/insert
 * update page      GET  /freight/update/{id}
 * update action    POST /freight/update
 * delete action    POST /freight/delete
 * detail                /freight/detail/{id}

 * reference             /freight/reference
 * import page      GET  /freight/import
 * import action    POST /freight/import
 * export custom    GET  /freight/export
 * export action    POST /freight/export
 */

/**
 * 运费维护 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/freight")
public class FreightController implements IFreightConstants {

    @Autowired
    /**
     * 运费维护服务
     */
    private FreightService freightService;
    
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
        
        return "/yulinchemical/base/freight/listFreight";
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
        //联查发货方名字
        if("typeString".equals("typeString")){
        	searchPara.put("freightManager", "freightManager");
        }
       
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, freightService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<FreightVo> beans = freightService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid FreightVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
//      freightService.insert(vo);
        freightService.insert(vo, request);//插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid FreightVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        freightService.update(vo);  //更新单条记录
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
        FreightVo bean = freightService.get(id);
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
            deleteCount = freightService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += freightService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/freight?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        	  deleteCount = freightService.deleteLogic(request,id);
          }else{
        	  String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
          
          if (ids != null && ids.length != 0) {
              deleteCount += freightService.deleteLogic(request,ids);  //删除多条记录
          } 
          }
        result.put("msg", "收货方删除成功");
        
    	return result;
    	
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
            deleteCount = freightService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += freightService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/freight?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        FreightVo bean = freightService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/base/freight/detailFreight";
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
        return "/yulinchemical/base/freight/util/referenceFreight";
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
        return "/yulinchemical/base/freight/importFreight";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/base/freight/importFreight";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/base/freight/exportFreight_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/base/freight/exportFreight_excel";
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
           searchMap.put("shipperCode", request.getParameter("shipperCode"));
           searchMap.put("targetId", request.getParameter("targetId"));
           searchMap.put("targetType", request.getParameter("targetType"));
           searchMap.put("yuanTon", request.getParameter("yuanTon"));
           searchMap.put("yuanKmTon", request.getParameter("yuanKmTon"));
           searchMap.put("mileage", request.getParameter("mileage"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
}
