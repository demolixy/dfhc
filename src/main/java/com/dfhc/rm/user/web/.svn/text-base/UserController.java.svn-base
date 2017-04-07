/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  activiti工作流
 *
 * 文件名称：  UserController.java
 *
 * 功能描述：  RM_USERController
 * 
 * 版本历史：
 * 
 * 2016-12-07   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.rm.user.web;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.config.RmConfig;
import org.quickbundle.orgauth.IOrgauthConstants;
import org.quickbundle.orgauth.cache.RmPartyTypeCache;
import org.quickbundle.orgauth.custom.impl.RmCustomOrgService;
import org.quickbundle.orgauth.rmfunctionnode.vo.RmFunctionNodeVo;
import org.quickbundle.orgauth.rmparty.service.IRmPartyService;
import org.quickbundle.orgauth.rmparty.util.IRmPartyConstants;
import org.quickbundle.orgauth.rmpartyrelation.service.IRmPartyRelationService;
import org.quickbundle.orgauth.rmpartyrelation.util.IRmPartyRelationConstants;
import org.quickbundle.orgauth.rmpartyrelation.vo.RmPartyRelationVo;
import org.quickbundle.orgauth.rmrole.service.IRmRoleService;
import org.quickbundle.orgauth.rmrole.util.IRmRoleConstants;
import org.quickbundle.orgauth.rmrole.vo.RmRoleVo;
import org.quickbundle.orgauth.rmuser.service.IRmUserService;
import org.quickbundle.orgauth.rmuser.util.IRmUserConstants;
import org.quickbundle.project.IGlobalConstants;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.project.login.IRmSessionService;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.project.login.RmUserVo.RmUserSessionVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmSqlHelper;
import org.quickbundle.tools.helper.RmStringHelper;
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
import com.dfhc.PjException;
import com.dfhc.pub.server.PushDataItem;
import com.dfhc.pub.server.PushDataVo;
import com.dfhc.pub.server.PushQueue;
import com.dfhc.pub.server.SocketCacheVo;
import com.dfhc.pub.server.SocketQueue;
import com.dfhc.rm.user.IUserConstants;
import com.dfhc.rm.user.service.UserService;
import com.dfhc.rm.user.vo.UserVo;
import com.dfhc.util.StringHelper;

/**
 * RM_USER Controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/user")
public class UserController implements IUserConstants {

    @Autowired
    /**
     * RM_USER服务
     */
    private UserService userService;
    
    IRmSessionService getSessionService() {
    	return (IRmSessionService)RmBeanFactory.getBean(IRmSessionService.class.getName());
    }
    
    /**
     * 得到RmPartyRelationService对象
     * @return 
     */
    IRmPartyRelationService getRmPartyRelationService(){
    	return (IRmPartyRelationService) RmBeanFactory.getBean(IRmPartyRelationConstants.SERVICE_KEY);  //得到Service对象,受事务控制	
    }
    
    /**
     * activiti分配用户使用
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "assigneeUser")
    public String assigneeUser(Model model, HttpServletRequest request) {
        list(model, request);
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        return "/qb5activiti/rm/user/util/assigneeUser";
    }
    
    
    /**
     * socketMonitor推送socket监控
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "socketMonitor")
    public String socketMonitor(Model model, HttpServletRequest request){
    	
    	List<SocketCacheVo> bean = SocketQueue.getList();//获取socket属性
    	if(bean.size()>0){
    		model.addAttribute(ISystemConstant.AJAX_BEAN, bean);
    	}
    	
    	return "/yulinchemical/system/socketMonitor";
    }
    
    /**
     * 推送数据队列信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "pushDatas")
    public String pushData(Model model, HttpServletRequest request){
    	List<PushDataVo> pushVo = PushQueue.getPushDataVos();
    	List<Map<String, Object>> list = new ArrayList<>();
    	Map<String, Object> map = new HashMap<>();
    	if(pushVo.size()>0){
    		for(PushDataVo vo:pushVo)
            {
               map.put("billStatus", vo.getBillStatus());
               map.put("plannedPushTime", vo.getPlannedPushTime());
               List<PushDataItem> pdata = vo.getPushDateItems();
               for(PushDataItem pd:pdata){
            	   map.put("clientId", pd.getClientId());
            	   map.put("factPushTime", pd.getFactPushTime());
            	   map.put("retryTimes", pd.getRetryTimes());
               }
               list.add(map);
            }
    		model.addAttribute(ISystemConstant.AJAX_BEAN, list);
    	}
    	return "/yulinchemical/system/pushData";
    }
    /**
     * 简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 跳转的列表jsp     
     */
    @RequestMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        Map<String, Object> searchPara = getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, userService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<UserVo> beans = userService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        RmJspHelper.saveOrderStr(orderStr, request);  //保存排序信息
        model.addAttribute(REQUEST_QUERY_CONDITION, searchPara);
        model.addAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
        return "/qb5activiti/rm/user/listUser";
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
    public Map<String,Object> insert(HttpServletRequest request, @Valid UserVo vo) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        userService.insert(vo);  //插入单条记录
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
    public Map<String,Object> update(HttpServletRequest request, @Valid UserVo vo) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        userService.update(vo);  //更新单条记录
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
        UserVo bean = userService.get(id);
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
            deleteCount = userService.delete(new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += userService.delete(ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/user";
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
            deleteCount = userService.deleteLogic(request,new String(id));
        } else {
            String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
            if (ids != null && ids.length != 0) {
                deleteCount += userService.deleteLogic(request,ids);  //删除多条记录
            }
        }
        redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/user";
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
        UserVo bean = userService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return "/qb5activiti/rm/user/detailUser";
    }

    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "reference")
    public String reference(Model model, HttpServletRequest request) {
        list(model, request);
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        return "/qb5activiti/rm/user/util/referenceUser";
    }

    /**
     * 跳转到导入页
     * @param model 模型
     * @return 返回导入页
     */
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importDataForm(Model model) {
        return "/qb5activiti/rm/user/importUser";
    }
    
    /**
     * 执行导入
     * @param model 模型
     * @return 返回导入页面
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importData(Model model) {
        model.addAttribute("isSubmit", "1");
        return "/qb5activiti/rm/user/importUser";
    }
    
    /**
     * 定制导出
     * @param model 模型
     * @return 返回导出页面
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public String exportCustomForm(Model model) {
        return "/qb5activiti/rm/user/exportUser_custom";
    }
    
    /**
     * 执行导出
     * @param model 模型
     * @return 返回导出xls页面
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportData(Model model) {
        return "/qb5activiti/rm/user/exportUser_excel";
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
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, userService.getCount(searchPara));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        if(StringHelper.isEmpty(orderStr)){
            String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
            String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
            orderStr=sidx+" "+sord;
        }
        
        List<UserVo> beans = userService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
         
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
    }
    
    /**
     * 参照信息查询，带简单查询，分页显示，支持表单回写
     * @param model 模型
     * @param request http请求对象
     * @return 返回参照列表页面
     */
    @RequestMapping(value = "referenceNew")
    public String referenceNew(Model model, HttpServletRequest request) {
        model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
        model.addAttribute(REQUEST_QUERY_STRING, request.getQueryString());  //传送输入方式,checkbox或radio
        return "/qb5activiti/rm/user/util/referenceUserAjax";
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
           searchMap.put("name", request.getParameter("name"));
           searchMap.put("lockStatus", request.getParameter("lockStatus"));
           searchMap.put("loginId", request.getParameter("loginId"));
           searchMap.put("password", request.getParameter("password"));
           searchMap.put("authenType", request.getParameter("authenType"));
           searchMap.put("organizationId", request.getParameter("organizationId"));
           searchMap.put("employeeId", request.getParameter("employeeId"));
           searchMap.put("email", request.getParameter("email"));
           searchMap.put("adminType", request.getParameter("adminType"));
           searchMap.put("description", request.getParameter("description"));
           searchMap.put("agentStatus", request.getParameter("agentStatus"));
           searchMap.put("loginStatus", request.getParameter("loginStatus"));
           searchMap.put("lastLoginDate", request.getParameter("lastLoginDate"));
           searchMap.put("lastLoginIp", request.getParameter("lastLoginIp"));
           searchMap.put("loginSum", request.getParameter("loginSum"));
           searchMap.put("lastCustomCss", request.getParameter("lastCustomCss"));
           searchMap.put("isAffix", request.getParameter("isAffix"));
           searchMap.put("functionPermission", request.getParameter("functionPermission"));
           searchMap.put("dataPermission", request.getParameter("dataPermission"));
           searchMap.put("custom1", request.getParameter("custom1"));
           searchMap.put("custom2", request.getParameter("custom2"));
           searchMap.put("custom3", request.getParameter("custom3"));
           searchMap.put("custom4", request.getParameter("custom4"));
           searchMap.put("custom5", request.getParameter("custom5"));
           searchMap.put("customXml", request.getParameter("customXml"));
           searchMap.put("usableStatus", request.getParameter("usableStatus"));
           searchMap.put("modifyDate", request.getParameter("modifyDate"));
           searchMap.put("modifyIp", request.getParameter("modifyIp"));
           searchMap.put("modifyUserId", request.getParameter("modifyUserId"));
           searchMap.put("createTime", request.getParameter("createTime"));
           searchMap.put("createUserName", request.getParameter("createUserName"));
           searchMap.put("attribute1", request.getParameter("attribute1"));
           searchMap.put("attribute2", request.getParameter("attribute2"));
           searchMap.put("attribute3", request.getParameter("attribute3"));
           searchMap.put("attribute4", request.getParameter("attribute4"));
           searchMap.put("attribute5", request.getParameter("attribute5"));
           searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
           searchMap.put("userType",request.getParameter("userType"));
        }
        return searchMap;
    }
    
    
    
    /**
     * 查看在线用户
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="listOnlineUser")
    public  String listOnlineUser(Model model, HttpServletRequest request){
    	
    	Map<String,Object> adminTypeMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_ADMIN_TYPE);
    	
    	model.addAttribute("adminTypeMap", adminTypeMap);//类型
    	
    	return "/yulinchemical/system/listOnlineUser";
    	
    }
    
    
    /**
     * 查询在线用户
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="ajaxListOnlineUser")
    public void ajaxListOnlineUser(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	 Map<String,Object> dataMap=new HashMap<String,Object>();
         
         String page=request.getParameter("page")==null?"1":request.getParameter("page");
         String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
         int rowsInt=Integer.parseInt(rows);//页大小
         int pageInt=Integer.parseInt(page);//当前页
         RmPageVo pageVo = RmJspHelper.transctPageVo(request, getSessionService().getRecordCount(null));
         pageVo.setPageSize(rowsInt);
         pageVo.setCurrentPage(pageInt);
         String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
         if(StringHelper.isEmpty(orderStr)){
             String sidx=request.getParameter("sidx")==null?"id":request.getParameter("sidx");
             String sord=request.getParameter("sord")==null?"desc":request.getParameter("sord");
             orderStr=sidx+" "+sord;
         }
         List<RmUserSessionVo> beans = getSessionService().queryByCondition(null, null, pageVo.getStartIndex(), pageVo.getPageSize());
         request.setAttribute(REQUEST_BEANS, beans);  //把结果集放入request
    
         dataMap.put("page",  pageVo.getCurrentPage());
         dataMap.put("total",  pageVo.getPageCount());
         dataMap.put("records",  pageVo.getRecordCount());
         dataMap.put("rows", beans);
          
         response.setCharacterEncoding("UTF-8"); 
         response.getWriter().write(JSONObject.toJSONString(dataMap));
    	
    }
    
    
    /**
     * 查询有效的角色
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="listRoles")
    public String listRoles(Model model, HttpServletRequest request){
    	
    	Map<String,Object> statusMap=RmGlobalReference.get(IRmRoleConstants.DICTIONARY_RM_ENABLE_STATUS);
    	Map<String,Object> yesNotMap=RmGlobalReference.get(IRmRoleConstants.DICTIONARY_RM_YES_NOT);
    	model.addAttribute("statusMap", statusMap);
    	model.addAttribute("yesNotMap", yesNotMap);
    	return "/yulinchemical/system/listRole";
    }
    
    /**
     * 展示角色列表
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="ajaxListRoles")
    public void ajaxListRoles(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
          Map<String,Object> dataMap=new HashMap<String,Object>();
          
          IRmRoleService service = userService.getRoleService();
          String queryCondition =getQueryConditionRole(request);  //从request中获得查询条件
         
          String page=request.getParameter("page")==null?"1":request.getParameter("page");
          String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
          int rowsInt=Integer.parseInt(rows);//页大小
          int pageInt=Integer.parseInt(page);//当前页
          RmPageVo pageVo = RmJspHelper.transctPageVo(request, userService.getRoleCount(queryCondition));
          pageVo.setPageSize(rowsInt);
          pageVo.setCurrentPage(pageInt);
          String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
          List<RmRoleVo> beans = service.queryByCondition(queryCondition, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        
          request.setAttribute(REQUEST_BEANS, beans);  //把结果集放入request
          dataMap.put("page",  pageVo.getCurrentPage());
          dataMap.put("total",  pageVo.getPageCount());
          dataMap.put("records",  pageVo.getRecordCount());
          dataMap.put("rows", beans);
           
          response.setCharacterEncoding("UTF-8"); 
          response.getWriter().write(JSONObject.toJSONString(dataMap));
    	
    }
    
    
    /**
     * 删除角色
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "ajaxDeleteRoles", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> ajaxDeleteRoles(Model model, HttpServletRequest request){
    	
    	Map<String,Object> result=new HashMap<String,Object>();
    	
    	 String[] id = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS);  //从request获取多条记录id
    	 String msg="";
         int deleteCount = 0;  //定义成功删除的记录数
         if (id != null && id.length != 0) {
         	if(IOrgauthConstants.Config.isRoleRelationParty.value()) {
         		List<RmCommonVo> roleList = RmProjectHelper.getCommonServiceInstance().doQuery("select id from RM_PARTY_ROLE where RM_PARTY_ROLE.ROLE_ID in("+RmStringHelper.parseToSQLString(id)+") ");
         	    msg = "删除失败！";
         		if(roleList != null && roleList.size() == 0){
         			deleteCount = userService.getRoleService().delete(id);  //删除多条记录
         			msg = "删除成功";
         		}
         	}
         }else{
        	 msg="没有合适的数据";
         }
         
         result.put("msg", msg);
         
         return result;
    	
    }
    
    /**
     * 去角色修改页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="toUpdateRole")
    public  String toUpdateRole(HttpServletRequest request,Model model){
    	
    	String id=request.getParameter("id");//
    	
    	
    	 RmRoleVo bean = userService.getRoleService().find(id);  //通过id获取vo
    	 
    	 model.addAttribute("bean", bean);//放入bean
    	 
	  	 Map<String,Object> statusMap=RmGlobalReference.get(IRmRoleConstants.DICTIONARY_RM_ENABLE_STATUS);//是否启用标志
    	 Map<String,Object> levelMap=RmGlobalReference.get(IRmRoleConstants.DICTIONARY_RM_YES_NOT);//是否全局
    	 model.addAttribute("statusMap", statusMap);
    	 model.addAttribute("levelMap",levelMap);
    	
    	 model.addAttribute("updateinsertFlag", "update");//表示新增过来的
    	return "/yulinchemical/system/updateRole";
    }
    
    
    /**
     * 跟新角色设置
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="updateRole", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> updateRole(HttpServletRequest request,Model model){
    	
    	Map<String,Object> result=new HashMap<String,Object>();
    	
    	 RmRoleVo vo = new RmRoleVo();
         RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
         RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
         //全局角色没有所属组织ID
         if("1".equals(request.getParameter("is_system_level"))){
         	vo.setOwner_org_id(null);
         }
         userService.getRoleService().update(vo);  //更新单条记录
         result.put("msg", "更新完成");		
    			
    	return result;
    }
    
    
    @RequestMapping(value="toInsert")
    public String toInsert(HttpServletRequest request,Model model){
    	
	   	 Map<String,Object> statusMap=RmGlobalReference.get(IRmRoleConstants.DICTIONARY_RM_ENABLE_STATUS);//是否启用标志
		 Map<String,Object> levelMap=RmGlobalReference.get(IRmRoleConstants.DICTIONARY_RM_YES_NOT);//是否全局
		 model.addAttribute("statusMap", statusMap);
		 model.addAttribute("levelMap",levelMap);
		 model.addAttribute("updateInsertFlag", "insert");//表示新增过来的
	
    	
    	return "/yulinchemical/system/updateRole";
    }
    
    /**
     * 新增角色
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="insertRole", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> insert(HttpServletRequest request,Model model){
    	  Map<String,Object> result=new HashMap<String,Object>();
    	  RmRoleVo vo = new RmRoleVo();
          RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
          RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
          //全局角色没有所属组织ID
          if("1".equals(request.getParameter("is_system_level"))){
          	vo.setOwner_org_id(null);
          }
          String id = userService.getRoleService().insert(vo);  //插入单条记录        
          request.setAttribute(IGlobalConstants.INSERT_FORM_ID, id);  //新增记录的id放入request属性
          result.put("msg", "新增成功");
          return result;
    }
    
    /**
     * 去角色授权页面
     * @param request
     * @param id
     * @param model
     * @return
     */
    
    @RequestMapping(value="toAuth/{id}/{times}",method=RequestMethod.GET)
    public String toAuth(HttpServletRequest request,@PathVariable String id,Model model){
    	
    	if(StringHelper.isEmpty(id)){
    		
    		throw new PjException("没找到对应的数据");
    	}
    	
    	RmRoleVo rmRoleVo=userService.getRoleService().find(id);
    	if(rmRoleVo==null){
    		throw new PjException("没找到对应的角色");
    	}
    	String partyTypeName = RmPartyTypeCache.getPartyType(IRmRoleConstants.TABLE_NAME).getName();
    	//取出已经赋给的功能菜单权限
    	String old_code_id = "";
    	List<RmCommonVo> authorizeResourceListId = RmProjectHelper.getCommonServiceInstance().doQuery("SELECT AR.OLD_RESOURCE_ID  FROM RM_AUTHORIZE_RESOURCE_RECORD ARR JOIN RM_AUTHORIZE_RESOURCE AR ON ARR.AUTHORIZE_RESOURCE_ID=AR.ID WHERE ARR.PARTY_ID="+id);
    	for(RmCommonVo vo :authorizeResourceListId){
    		old_code_id += vo.get("old_resource_id")+",";
    	}
    	if(old_code_id!=null&&!old_code_id.equals("")){
    		old_code_id = old_code_id.substring(0,old_code_id.length()-1);
    	}
    
    	 model.addAttribute("old_code_id", old_code_id);
    	 
    	 model.addAttribute("partyTypeName", partyTypeName);//团体类别
    	 
    	 model.addAttribute("rmRoleVo", rmRoleVo);
    	
    	return "/yulinchemical/system/toAuth";
    }
    
    /**
     * 角色授权
     * @param reuest
     * @param model
     * @return
     */
    @RequestMapping(value="doAuthRole",method=RequestMethod.POST)
    public String  doAuthRole(HttpServletRequest request,Model model){
    	
    	 String role_id = request.getParameter("role_id");
         String function_node_code = request.getParameter("function_node_ids");        
         //重新添加授权记录和授权资源
         String[] nodeCode = RmStringHelper.parseToArray(function_node_code);
         String[] nodeCode_name = RmStringHelper.parseToArray(request.getParameter("function_node_id_names"));
         if(nodeCode != null && role_id!=null && (nodeCode.length>0 || role_id.trim().length()>0)){
         	List<RmFunctionNodeVo> lvo = new ArrayList<RmFunctionNodeVo>();
         	for(int i=0; i<nodeCode.length; i++) {
         		RmFunctionNodeVo vo = new RmFunctionNodeVo();
         		vo.setTotal_code(nodeCode[i]);
         		if(nodeCode_name.length >= i+1) {
         			vo.setName(nodeCode_name[i]);
         		}
         		lvo.add(vo);
         	}
         	userService.getRoleService().insertPartyResource(lvo.toArray(new RmFunctionNodeVo[0]), role_id);
         }
    	
    	return "redirect:/user/listRoles";
    }
    
    
    /**
     * 角色查询 条件
     * @param request
     * @return
     */
    public String getQueryConditionRole(HttpServletRequest request) {
        String queryCondition = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null && !"".equals(request.getAttribute(REQUEST_QUERY_CONDITION))) {  //如果request.getAttribute中有，就不取request.getParameter
            queryCondition = request.getAttribute(REQUEST_QUERY_CONDITION).toString();
        } else {
			List<String> lQuery = new ArrayList<String>();
			
			if(IOrgauthConstants.Config.isUserRelationParty.value()) {
		    	RmUserVo userVo = (RmUserVo)RmProjectHelper.getRmUserVo(request);
		    	if(userVo !=null && !IOrgauthConstants.UserAdminType.ADMIN.value().equals(userVo.getAdmin_type()) && userVo.getParty_id_org()!=null){
		    		RmCommonVo cVo = RmProjectHelper.getCommonServiceInstance().doQuery("select distinct(PR.CHILD_PARTY_CODE) from RM_PARTY_RELATION PR where PR.CHILD_PARTY_ID="+userVo.getParty_id_org()).get(0);
		    		lQuery.add("exists(select * from RM_ROLE R2 left join RM_PARTY_RELATION PR on R2.OWNER_ORG_ID=" + RmSqlHelper.getFunction(RmSqlHelper.Function.TO_CHAR, RmConfig.getSingleton().getDatabaseProductName(), "PR.CHILD_PARTY_ID") + " where R2.USABLE_STATUS='1' and R2.ID=RM_ROLE.ID and PR.CHILD_PARTY_CODE like '" + cVo.getString("child_party_code") + "%') or IS_SYSTEM_LEVEL=1");
		        }
			}
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".usable_status", "1", RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".name", request.getParameter("name"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".role_code", request.getParameter("role_code"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".is_system_level", request.getParameter("is_system_level"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".owner_org_id", request.getParameter("owner_org_id"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".is_recursive", request.getParameter("is_recursive"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".matrix_code", request.getParameter("matrix_code"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".description", request.getParameter("description"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".function_permission", request.getParameter("function_permission"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(IRmRoleConstants.TABLE_NAME + ".data_permission", request.getParameter("data_permission"), RmSqlHelper.TYPE_CHAR_LIKE));
			

			queryCondition = RmSqlHelper.appendQueryStr(lQuery.toArray(new String[0]));
        }
        return queryCondition;
    }
    
    
    
    /**
     * 查询后台用户
     * @param request
     * @param mode
     * @return
     */
    @RequestMapping(value="listUserMana")
    public  String listUserMana(HttpServletRequest request,Model model){
    	
    	Map<String,Object> lockStatusMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_LOCK_STATUS);
    	
    	Map<String,Object> adminTypeMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_ADMIN_TYPE);//用户类型
    	
    	model.addAttribute("adminTypeMap", adminTypeMap);//用户类型
    	model.addAttribute("lockStatusMap", lockStatusMap);//锁定状态
    	
    	return "/yulinchemical/system/listUserMana";
    }
    
    /**
     * 异步加载用户数据
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="ajaxListUserMana")
    public  void ajaxListUserMana(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap=new HashMap<String,Object>();
        
        IRmUserService service = userService.getUserService();//用户服务
        
        String queryCondition =getQueryConditionUser(request);  //从request中获得查询条件
       
        String page=request.getParameter("page")==null?"1":request.getParameter("page");
        String rows=request.getParameter("rows")==null?ISystemConstant.DEFAULT_PAGE_SIZE:request.getParameter("rows");
        int rowsInt=Integer.parseInt(rows);//页大小
        int pageInt=Integer.parseInt(page);//当前页
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, userService.getUserCount(queryCondition));
        pageVo.setPageSize(rowsInt);
        pageVo.setCurrentPage(pageInt);
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<org.quickbundle.orgauth.rmuser.vo.RmUserVo> beans = service.queryByCondition(queryCondition, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
      
        request.setAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        dataMap.put("page",  pageVo.getCurrentPage());
        dataMap.put("total",  pageVo.getPageCount());
        dataMap.put("records",  pageVo.getRecordCount());
        dataMap.put("rows", beans);
         
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(JSONObject.toJSONString(dataMap));
   
    	
    }
    
    /**
     * 删除用户
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="ajaxDeleteUsers",method=RequestMethod.POST,
    		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    
    @ResponseBody
    public Map<String,Object> ajaxDeleteUsers(HttpServletRequest request,Model model){
    	
    	Map<String,Object> result=new HashMap<String,Object>();
    	
    	  String[] id = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS);  //从request获取多条记录id
          int deleteCount = 0;  //定义成功删除的记录数
          if (id != null && id.length != 0) {
          	if(IOrgauthConstants.Config.isUserRelationParty.value()) {
          		deleteCount = RmCustomOrgService.getInstance().deleteParty(id);  //删除多条记录
          	} else {
          		deleteCount =userService.getUserService().delete(id);  //删除多条记录
          	}
          }
        result.put("msg", "用户删除成功");
        
    	return result;
    	
    }
    
    
    /**
     * 新增用户页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="toInsertUser")
    public String  toInsertUser(HttpServletRequest request,Model model){
    	
        Map<String,Object> lockStatusMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_LOCK_STATUS);
    	
    	Map<String,Object> adminTypeMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_ADMIN_TYPE);//用户类型
    	
    	Map<String,Object> userTypeMap=RmGlobalReference.get(ISystemConstant.DICTIONARY_USER_TYPE);//用户类型
    	
    	model.addAttribute("userTypeMap", userTypeMap);//用户类型
    	model.addAttribute("adminTypeMap", adminTypeMap);//用户权限类型
    	model.addAttribute("lockStatusMap", lockStatusMap);//锁定状态
    	
    	model.addAttribute("updateInsertFlag", "insert");
    	
    	return "/yulinchemical/system/toInsertUser";
    }
    
    
    /**
     * 受控菜单新增用户
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="doInsertUser",method=RequestMethod.POST,
    		        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    
    @ResponseBody
    public Map<String,Object> doInsertUser(HttpServletRequest request,Model model){
    	
    	  Map<String,Object> result=new HashMap<String,Object>();
    	  org.quickbundle.orgauth.rmuser.vo.RmUserVo  vo = new org.quickbundle.orgauth.rmuser.vo.RmUserVo();
          RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
          RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
          Timestamp ts = new Timestamp(System.currentTimeMillis());  
          vo.setCreate_time(ts);
         
           String id=  userService.doInsertUser(vo, request);
           
           if(StringHelper.isEmpty(id)){
        	   result.put("msg", "用户新增失败");
           }else{
        	   result.put("msg", "新增用户成功");
           }
          
           return result;
    }
    
    /**
     * 受控菜单修改用户页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="toUpdateUser")
    public String  toUpdateUser(HttpServletRequest request,Model model){
    	
    	
    	org.quickbundle.orgauth.rmuser.vo.RmUserVo bean =userService.getUserService().find(request.getParameter(REQUEST_ID));  //通过id获取vo
    	
    	 String queryCondition="CHILD_PARTY_ID='"+bean.getId()+"'";
 		//查询用户所属的组织名称
         List<RmPartyRelationVo> vos = getRmPartyRelationService().queryByCondition(queryCondition, null);
         if(vos!=null && vos.size()>0){
         	bean.setAttribute1(vos.get(0).getParent_party_name());
         }
         
         model.addAttribute("bean", bean);  //把vo放入request
         
        Map<String,Object> lockStatusMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_LOCK_STATUS);
    	
    	Map<String,Object> adminTypeMap=RmGlobalReference.get(IRmUserConstants.DICTIONARY_RM_ADMIN_TYPE);//用户类型
    	
    	Map<String,Object> userTypeMap=RmGlobalReference.get(ISystemConstant.DICTIONARY_USER_TYPE);//用户类型
    	
    	model.addAttribute("userTypeMap", userTypeMap);//用户类型
    	model.addAttribute("adminTypeMap", adminTypeMap);//用户权限类型
    	model.addAttribute("lockStatusMap", lockStatusMap);//锁定状态
    	
    	model.addAttribute("updateInsertFlag", "update");
    	
    	return "/yulinchemical/system/toInsertUser";
    }
    
    
    /**
     * 受控菜单更新用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="doUpdateUser",method=RequestMethod.POST,
	        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	@ResponseBody
	public Map<String,Object> doUpdateUser(HttpServletRequest request,Model model){
	
      String id=request.getParameter("id");//用户id
	  Map<String,Object> result=new HashMap<String,Object>();
	  org.quickbundle.orgauth.rmuser.vo.RmUserVo  vo = userService.getUserService().find(id);//查询userid
	  RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
	  RmVoHelper.markModifyStamp(request,vo);  //打创建时间,IP戳
	 
	  int count=userService.doUpdateUser(vo, request);//更新用户信息
	  
	  if(count==0){
		  
		  result.put("msg", "用户信息更新失败");
	  }else{
		  result.put("msg", "用户信息更新成功");
	  }
	  
	  
	   return result;
	}
    
    /**
     * 角色设定页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="toSetRole/{id}/{times}")
    public  String toSetRole(@PathVariable String id,HttpServletRequest request,Model model){
    	
    	String parent_party_id = "" ;
    	if(!ISystemConstant.DICTIONARY_RM_ADMIN_TYPE_3.equals(RmProjectHelper.getRmUserVo(request).getAdmin_type())){
    		if(RmProjectHelper.getRmUserVo(request).getParty_id_org()!=null){
    			//公司id
    			parent_party_id = RmProjectHelper.getRmUserVo(request).getParty_id_org();
    		}
    	}
    	String party_id = id; //用户ID
    	String party_name="";
    	if(RmStringHelper.checkNotEmpty(party_id) && !ISystemConstant.DICTIONARY_RM_ADMIN_TYPE_3.equals(RmProjectHelper.getRmUserVo(request).getAdmin_type())){
    		party_name = ((IRmPartyService)RmBeanFactory.getBean(IRmPartyConstants.SERVICE_KEY)).find(party_id).getName();
    	}
    	//查询该员工赋予的角色
    	List<RmCommonVo> roleListVo = RmProjectHelper.getCommonServiceInstance().doQuery("SELECT R.ID,PR.OWNER_ORG_ID,P.NAME from RM_ROLE R JOIN RM_PARTY_ROLE PR on R.ID=PR.ROLE_ID left join RM_PARTY P on P.OLD_PARTY_ID=PR.OWNER_ORG_ID  where R.USABLE_STATUS = '1' and PR.OWNER_PARTY_ID="+party_id);
    	Map<String, List<RmCommonVo>> mResult = new HashMap();
    	for(RmCommonVo vo:roleListVo){
        	  if(mResult.get(vo.getString("id")) == null) {
        		  mResult.put(vo.getString("id"), new ArrayList<RmCommonVo>());
        	  }
        	  mResult.get(vo.getString("id")).add(vo);
    	}
    	Map<String,String[]> mRole = new HashMap<String,String[]>();
    	for(String key:mResult.keySet()){
    		String companyName = "";
    		String companyId = "";
        	List<RmCommonVo> listv = mResult.get(key);
        	RmCommonVo sVo = listv.get(0);
        	String[] strs = new String[2];
        	for(RmCommonVo bVo:listv){
        		if(RmStringHelper.checkNotEmpty(bVo.getString("name")) && RmStringHelper.checkNotEmpty(bVo.getString("owner_org_id"))){
        			companyName += bVo.getString("name")+",";
        			companyId += bVo.getString("owner_org_id")+",";
        		}
        	}
        	if(RmStringHelper.checkNotEmpty(companyName)){
        		strs[0]=companyName.substring(0,companyName.length()-1);
        		strs[1]=companyId.substring(0,companyId.length()-1);
        	}
        	mRole.put(sVo.getString("id"),strs);
        }
        String[] roleIds = new String[mRole.size()];
        int j = 0;
        Map sWriteBack = new HashMap();
        for(Map.Entry<String,String[]> mr:mRole.entrySet()){
        	roleIds[j]=mr.getKey();
    		sWriteBack.put("company_id_"+mr.getKey(),mr.getValue().length>1 ? RmStringHelper.prt(mr.getValue()[1]):"");
    		sWriteBack.put("company_name_"+mr.getKey(),mr.getValue().length>1 ? RmStringHelper.prt(mr.getValue()[0]):"");
    		j++;
        }
    	//根据权限查询角色
    	//RmCommonVo cVo = RmProjectHelper.getCommonServiceInstance().doQuery("select distinct(PR.CHILD_PARTY_CODE) from RM_PARTY_RELATION PR where PR.CHILD_PARTY_ID="+userVo.getCompany().getId()).get(0);
    	List<RmCommonVo> roleList = RmProjectHelper.getCommonServiceInstance().doQuery("SELECT ID,NAME,IS_SYSTEM_LEVEL, ROLE_CODE FROM RM_ROLE where USABLE_STATUS = '1'");
    	List<RmCommonVo> isSystemLevelList = new ArrayList<RmCommonVo>();
    	List<RmCommonVo> notSystemLevelList = new ArrayList<RmCommonVo>();
    	for(RmCommonVo vo:roleList){
    		if("1".equals(vo.getString("is_system_level"))){
    			isSystemLevelList.add(vo);
    		}else{
    			notSystemLevelList.add(vo);
    		}
    	}
    	
    	model.addAttribute("isSystemLevelList", isSystemLevelList);
    	
    	model.addAttribute("notSystemLevelList", notSystemLevelList);
    	
    	model.addAttribute("party_id", party_id);
    	
    	model.addAttribute("parent_party_id", parent_party_id);
    	model.addAttribute("party_name", party_name);//部门名称
    	
    	model.addAttribute("roleListVo", roleListVo);//该用户所拥有的角色
    	
    	return "/yulinchemical/system/toSetRole";
    }
     
    
    /**
     * 用户角色设置
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="doSetUserRole")
    public String doSetUserRole(HttpServletRequest request,Model model){
    	String id = request.getParameter("id");
    	String [] role_ids = request.getParameterValues("role_name");
    	if(RmStringHelper.checkNotEmpty(id)){
    		userService.getRoleService().insertRm_party_roles(id, role_ids);
    	}
    	
    	return "redirect:/user/listUserMana";
    }
    
    /**
     * 后台授权菜单的查询用户数据
     * @param request
     * @return
     */
    
    public String getQueryConditionUser(HttpServletRequest request) {
        String queryCondition = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
            queryCondition = request.getAttribute(REQUEST_QUERY_CONDITION).toString();
        } else {
			List<String> lQuery = new ArrayList<String>();
			
			if(IOrgauthConstants.Config.isUserRelationParty.value()) {
				org.quickbundle.project.login.RmUserVo userVo = (org.quickbundle.project.login.RmUserVo)RmProjectHelper.getRmUserVo(request);
				if(userVo !=null && !IOrgauthConstants.UserAdminType.ADMIN.value().equals(userVo.getAdmin_type()) && userVo.getParty_id_org()!=null){
					RmCommonVo cVo = RmProjectHelper.getCommonServiceInstance().doQuery("select distinct(PR.CHILD_PARTY_CODE) from RM_PARTY_RELATION PR where PR.CHILD_PARTY_ID=" + userVo.getParty_id_org()+" and PR.PARTY_VIEW_ID="+IOrgauthConstants.PartyView.DEFAULT.id()).get(0);
					lQuery.add("exists(select * from RM_USER U2 join RM_PARTY_RELATION PR on U2.ID=PR.CHILD_PARTY_ID where U2.USABLE_STATUS='1' and U2.ID=RM_USER.ID and PR.CHILD_PARTY_CODE like '" + cVo.getString("child_party_code") + "%')");
				}
			}
			if(request.getParameter("parent_party_id") != null && request.getParameter("parent_party_id").length() > 0) {
				lQuery.add("RM_PARTY_RELATION.CHILD_PARTY_CODE LIKE " +
						"" +
						"(select " +
						RmSqlHelper.getFunction(RmSqlHelper.Function.CONCAT, RmConfig.getSingleton().getDatabaseProductName(), 
								"PR9.CHILD_PARTY_CODE", "'%'") +
						" FROM RM_PARTY_RELATION PR9 WHERE PR9.CHILD_PARTY_ID=" + request.getParameter("parent_party_id") + 
						")");
			}
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".name", request.getParameter("name"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".lock_status", request.getParameter("lock_status"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".login_id", request.getParameter("login_id"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".password", request.getParameter("password"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".authen_type", request.getParameter("authen_type"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".organization_id", request.getParameter("organization_id"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".employee_id", request.getParameter("employee_id"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".email", request.getParameter("email"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".admin_type", request.getParameter("admin_type"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".description", request.getParameter("description"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".agent_status", request.getParameter("agent_status"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".login_status", request.getParameter("login_status"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".last_login_date", request.getParameter("last_login_date_from"), RmSqlHelper.TYPE_DATE_GREATER_EQUAL));
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".last_login_date", request.getParameter("last_login_date_to"), RmSqlHelper.TYPE_DATE_LESS_EQUAL));	
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".last_login_ip", request.getParameter("last_login_ip"), RmSqlHelper.TYPE_CHAR_LIKE));
			
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".login_sum", request.getParameter("login_sum_from"), RmSqlHelper.TYPE_NUMBER_GREATER_EQUAL));
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".login_sum", request.getParameter("login_sum_to"), RmSqlHelper.TYPE_NUMBER_LESS_EQUAL));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".last_custom_css", request.getParameter("last_custom_css"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".is_affix", request.getParameter("is_affix"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".function_permission", request.getParameter("function_permission"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".data_permission", request.getParameter("data_permission"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".custom1", request.getParameter("custom1"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".custom2", request.getParameter("custom2"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".custom3", request.getParameter("custom3"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".custom4", request.getParameter("custom4"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".custom5", request.getParameter("custom5"), RmSqlHelper.TYPE_CHAR_LIKE));
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".custom_xml", request.getParameter("custom_xml"), RmSqlHelper.TYPE_CHAR_LIKE));

			queryCondition = RmSqlHelper.appendQueryStr(lQuery.toArray(new String[0]));
        }
        return queryCondition;
    }
    
    

    
    
}

