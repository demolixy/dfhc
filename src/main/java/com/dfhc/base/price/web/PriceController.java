/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  PriceController.java
 *
 * 功能描述：  价格管理Controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.price.web;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.task.Task;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
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

import com.dfhc.base.price.IPriceConstants;
import com.dfhc.base.price.service.PriceService;
import com.dfhc.base.price.vo.PriceInstanceVo;
import com.dfhc.base.price.vo.PriceVo;
import com.dfhc.ISystemConstant;
import com.alibaba.fastjson.JSONObject;
import com.dfhc.rm.user.service.UserService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;

/**
 * list                  /price
 * insert page      GET  /price/insert
 * insert action    POST /price/insert
 * update page      GET  /price/update/{id}
 * update action    POST /price/update
 * delete action    POST /price/delete
 * detail                /price/detail/{id}

 * reference             /price/reference
 * import page      GET  /price/import
 * import action    POST /price/import
 * export custom    GET  /price/export
 * export action    POST /price/export
 */

/**
 * 价格管理 Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/price")
public class PriceController implements IPriceConstants {

    @Autowired
    /**
     * 价格管理服务
     */
    private PriceService priceService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private GeneralBusinessProcessService generalBusinessProcessService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserService userService;
    
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
        model.addAttribute("queryString", request.getQueryString());
        
        return "/yulinchemical/base/price/listPrice";
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
        String statusCode = request.getParameter("statusCode");
        if(!StringHelper.isEmpty(statusCode)){//查询待审核和已审核通过状态的数据
        	searchPara.put("statuss", "'"+ISystemConstant.DICTIONARY_PRICE_STATUS_2+"','"+ISystemConstant.DICTIONARY_PRICE_STATUS_3+"'");
        }
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, priceService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<PriceVo> beans = priceService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 展示流程实例
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "listPriceInstance")
    public String listPriceInstance(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        return "/yulinchemical/base/price/listPriceInstance";
    }
    /**
     * ajax 请求列表数据
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "ajaxListInstance")
    public void ajaxListInstance(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        
        String page=request.getParameter("page")==null?"1":request.getParameter("page");
        String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
        int rowsInt=Integer.parseInt(rows);//页大小
        int pageInt=Integer.parseInt(page);//当前页
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, (int) runtimeService.createProcessInstanceQuery().count());
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
       
        String sqlString = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( SELECT DISTINCT RES.* , P.KEY_ AS ProcessDefinitionKey,"
        					+ " P.ID_ AS ProcessDefinitionId, P.NAME_ AS ProcessDefinitionName, P.VERSION_ AS ProcessDefinitionVersion,"
        					+ " P.DEPLOYMENT_ID_ AS DeploymentId, WK.ID as wkId, WK.INITIATOR_TIME AS initiatorTime, WK.INITIATOR AS initiator, WK.BUSINESS_ATTRIBUTE5 AS priceApplyNum"
        					+ " FROM ACT_RU_EXECUTION RES INNER JOIN ACT_RE_PROCDEF P ON RES.PROC_DEF_ID_ = P.ID_"
        					+ " INNER JOIN WK_GENERAL_BUSINESS_PROCESS WK ON RES.PROC_INST_ID_ = WK.PROC_INST_ID"
        					+ " WHERE RES.PARENT_ID_ IS NULL ORDER BY initiatorTime DESC ) a"
        					+ " WHERE ROWNUM < "+ pageVo.getPageSize()+1 +" ) WHERE rnum >= " + pageVo.getStartIndex();
        
        List<RmCommonVo> listTemp = RmProjectHelper.getCommonServiceInstance().doQuery(sqlString);
        List<PriceInstanceVo> beans = new ArrayList<PriceInstanceVo>();
        for (RmCommonVo rmCommonVo : listTemp) {
        	PriceInstanceVo priceInstanceVo = new PriceInstanceVo();
        	String processDefinitionId = rmCommonVo.getString("ProcessDefinitionId");
  	  		String processInstanceId = rmCommonVo.getString("PROC_INST_ID_");
        	priceInstanceVo.setProcessDefinitionId(processDefinitionId);
        	priceInstanceVo.setProcessInstanceId(processInstanceId);
  	  		priceInstanceVo.setDoubleParameter(processDefinitionId+"@"+processInstanceId);
  	  		priceInstanceVo.setInitiator(rmCommonVo.getString("initiator"));
			String initiatorTime = rmCommonVo.getString("initiatorTime");
			if(!StringHelper.isEmpty(initiatorTime)){
				initiatorTime = initiatorTime.replaceAll("\\.0", "");
			}
			priceInstanceVo.setInitiatorTime(initiatorTime);
			priceInstanceVo.setGeneralBusinessProcessId(rmCommonVo.getString("wkId"));
			priceInstanceVo.setPriceApplyNum(rmCommonVo.getString("priceApplyNum"));
			beans.add(priceInstanceVo);
		}         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);

        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 展示通用待办列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "listTask")
    public String listTask(Model model, HttpServletRequest request) {
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));  //把模块代码放入request     
        return "/yulinchemical/base/price/listTask";
    }
    
    /**
     * ajax 请求列表数据
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "ajaxListTask")
    public void ajaxListTask(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        
        String page=request.getParameter("page")==null?"1":request.getParameter("page");
        String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
        int rowsInt=Integer.parseInt(rows);//页大小
        int pageInt=Integer.parseInt(page);//当前页
        Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件
        String userId = RmProjectHelper.getRmUserId(request);
        List<Task> results = generalBusinessProcessService.findTodoTasks(userId);
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, results.size());
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        List<Task> list = new ArrayList<Task>();
        int totalNum = pageVo.getStartIndex()-1+pageVo.getPageSize();
  	  	if(pageVo.getStartIndex()+pageVo.getPageSize()>pageVo.getRecordCount()){
  	  		totalNum = pageVo.getRecordCount();
  	  	}
  	  	for(int i=pageVo.getStartIndex()-1; i<totalNum; i++){
  	  		list.add(results.get(i));
  	  	}
  	  	List<PriceInstanceVo> beans = new ArrayList<PriceInstanceVo>();
  	  	for (Task task : list) {
  	  		PriceInstanceVo priceInstanceVo = new PriceInstanceVo();
  	  		String processDefinitionId = task.getProcessDefinitionId();
  	  		String processInstanceId = task.getProcessInstanceId();
  	  		priceInstanceVo.setTaskId(task.getId());
  	  		priceInstanceVo.setTaskName(task.getName());
  	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	  		priceInstanceVo.setTaskCreateTime(sdf.format(task.getCreateTime()));
  	  		priceInstanceVo.setProcessDefinitionId(processDefinitionId);
  	  		priceInstanceVo.setProcessInstanceId(processInstanceId);
  	  		priceInstanceVo.setDoubleParameter(task.getId()+"@"+task.getAssignee());
  	  		List<GeneralBusinessProcessVo> generalBusinessProcessVos = generalBusinessProcessService.getListForProcDefIdAndProcInstId(processDefinitionId, processInstanceId);
	  		if(generalBusinessProcessVos.size()>0){
	  			priceInstanceVo.setInitiator(generalBusinessProcessVos.get(0).getInitiator());
	  			priceInstanceVo.setInitiatorTime(DateUtil.timestamp2String(generalBusinessProcessVos.get(0).getInitiatorTime()));
	  			priceInstanceVo.setGeneralBusinessProcessId(generalBusinessProcessVos.get(0).getId());
	  		}
  	  		beans.add(priceInstanceVo);
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
    @RequestMapping(value = "ajaxListProductAndPrice")
    public void ajaxListProductAndPrice(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        String sql = "SELECT BASE_PRODUCT.ID AS id,BASE_PRODUCT.PRODUCT_NAME AS productName,BASE_PRICE.PRICE AS price FROM BASE_PRODUCT LEFT JOIN BASE_PRICE ON BASE_PRODUCT.ID=BASE_PRICE.PRODUCT_ID WHERE BASE_PRODUCT.MAIN_SECONDARY_PRODUCT_FLAG='02' AND NVL(BASE_PRODUCT.DELETE_FLAG,'0' )= '0' AND BASE_PRICE.STATUS IN('2','3')";
        List<RmCommonVo> listProductPrice = RmProjectHelper.getCommonServiceInstance().doQuery(sql);
        dataMap.put("listProductPrice", listProductPrice);
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    
    /**
     * 去审核页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="instanceToDetail")
    public String instanceToDetail(Model model, HttpServletRequest request){
    	//把模块代码放入request 
        model.addAttribute(ISystemConstant.RM_PARENT_CODE, request.getParameter(ISystemConstant.RM_PARENT_CODE));     
   	
    	//销售订单id
    	String id=request.getParameter("id");

    	GeneralBusinessProcessVo vo=generalBusinessProcessService.get(id);
    	
    	model.addAttribute(ISystemConstant.AJAX_BEAN, vo);
    	
        return "/yulinchemical/base/price/toPriceInstanceDetail";
    	
    }
    
    /**
     * ajax 请求列表数据
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "ajaxPriceInstanceList")
    public void ajaxPriceInstanceList(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        
        String page=request.getParameter("page")==null?"1":request.getParameter("page");
        String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
        int rowsInt=Integer.parseInt(rows);//页大小
        int pageInt=Integer.parseInt(page);//当前页
        Map<String, Object> searchPara = new HashMap<String, Object>();  //从request中获得查询条件
        String generalBusinessProcessId = request.getParameter("id");
        searchPara.put("parentId", generalBusinessProcessId);
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, generalBusinessProcessService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        
        List<GeneralBusinessProcessVo> beans = generalBusinessProcessService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    
    /**
     * ajax 根据产品id查询现行价格
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "ajaxGetPriceByProductId")
    public void ajaxGetPriceByProductId(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        
        Map<String, Object> searchMap = new HashMap<String, Object>();
        String price = "";
        String productId = request.getParameter("productId");
        if(!StringHelper.isEmpty(productId)){
        	searchMap.put("productId", productId);
            searchMap.put("statuss", "'"+ISystemConstant.DICTIONARY_PRICE_STATUS_2+"','"+ISystemConstant.DICTIONARY_PRICE_STATUS_3+"'");
            searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
            
            List<PriceVo> beans = priceService.list(searchMap, null);  //按条件查询全部,带排序
            
            if(beans.size()>0){
            	price = beans.get(0).getPrice().toString();
            }
        }
        dataMap.put("price", price);         
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid PriceVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        priceService.insert(vo);  //插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid PriceVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        priceService.update(vo);  //更新单条记录
        String updateGengeral = request.getParameter("updateGengeral");
        if(!StringHelper.isEmpty(updateGengeral)){
        	
        }else{
        	
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
        result.put(ISystemConstant.AJAX_MESSAGE, "修改成功: " + vo.getId());
        return result;
    }
    
    /**
     * 从页面表单获取信息注入vo，并修改单条记录
     * @param request 请求对象
     * @param vo 值对象
     * @param errors Ajax错误对象
     * @return 返回Ajax应答实体对象
     */
    @RequestMapping(value = "updateGengeral", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updateGengeral(HttpServletRequest request, @Valid PriceVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        priceService.doUpdateGengeral(vo);  //更新单条记录
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
        PriceVo bean = priceService.get(id);
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
            deleteCount = priceService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += priceService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/price?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
            deleteCount = priceService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += priceService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/price?"+ISystemConstant.RM_PARENT_CODE+"="+request.getParameter(ISystemConstant.RM_PARENT_CODE);
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
        PriceVo bean = priceService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/yulinchemical/base/price/detailPrice";
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
        return "/yulinchemical/base/price/util/referencePrice";
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
        return "/yulinchemical/base/price/importPrice";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/yulinchemical/base/price/importPrice";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/yulinchemical/base/price/exportPrice_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/yulinchemical/base/price/exportPrice_excel";
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
           searchMap.put("productId", request.getParameter("productId"));
           searchMap.put("productName", request.getParameter("productName"));
           searchMap.put("price", request.getParameter("price"));
           searchMap.put("startTime", request.getParameter("startTime"));
           searchMap.put("endTime", request.getParameter("endTime"));
           searchMap.put("status", request.getParameter("status"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        }
        return searchMap;
    }
    
    /**
     * 根据实例id删除流程实例并修改价格状态
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "deleteProcessInstanceAndUpdatePrice/{processInstanceId}")
    public String deleteProcessInstanceAndUpdatePrice(@PathVariable("processInstanceId") String processInstanceId) {
    	priceService.doDeleteProcessInstanceAndUpdatePrice(processInstanceId); 	
        return "/price/listPriceInstance";
    }
}
