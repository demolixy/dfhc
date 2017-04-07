/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LoadingNoticeRestController.java
 *
 * 功能描述：  装车通知单 rest controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.loadingnotice.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.quickbundle.third.spring.http.RmResponseEntityFactory;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.dfhc.ISystemConstant;
import com.dfhc.bus.loadingnotice.ILoadingNoticeConstants;
import com.dfhc.bus.loadingnotice.service.LoadingNoticeService;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.bus.loadingnotice.web.LoadingNoticeController;
import com.dfhc.bus.productbatchnumber.service.ProductBatchNumberService;
import com.dfhc.bus.productbatchnumber.vo.ProductBatchNumberVo;
import com.dfhc.util.StringHelper;

/**
 * list                 GET     /api/loadingnotice
 * get                  GET     /api/loadingnotice/{id}
 * insert action        POST    /api/loadingnotice
 * update action        PUT     /api/loadingnotice/{id}
 * delete action        DELETE  /api/loadingnotice/{id}
 * delete multi action  POST    /api/loadingnotice/delete
 * batch insert update  POST    /api/loadingnotice/batch
 */

/**
 * 装车通知单rest controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/api/loadingnotice")
public class LoadingNoticeRestController implements ILoadingNoticeConstants {

    @Autowired
    private LoadingNoticeService loadingNoticeService;

    @Autowired
    private Validator validator;
    @Autowired
    private ProductBatchNumberService productBatchNumberService;

    /**
     * 获取多条记录
     * @param request  http请求对象
     * @return 返回结果map
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> searchPara = LoadingNoticeController.getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, loadingNoticeService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<LoadingNoticeVo> beans = loadingNoticeService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(RM_JSON_TOTAL_COUNT, pageVo.getRecordCount());
        result.put(REQUEST_BEANS, beans);
        return result;
    }
    
    /**
     * 获得单条记录
     * @param id  主键id
     * @return 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        LoadingNoticeVo task = loadingNoticeService.get(id);
        if (task == null) {
            return new ResponseEntity<LoadingNoticeVo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LoadingNoticeVo>(task, HttpStatus.OK);
    }
    
    /**
     * 插入单条记录，使用服务端管理的ID号
     * @param vo  值对象
     * @param uriBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody LoadingNoticeVo vo, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<LoadingNoticeVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        loadingNoticeService.insert(vo);
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = vo.getId();
        URI uri = uriBuilder.path("/api/loadingnotice/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<HttpHeaders>(headers, HttpStatus.CREATED);
    }

    /**
     * 修改单条记录
     * @param vo
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody LoadingNoticeVo vo) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<LoadingNoticeVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        //保存
        loadingNoticeService.update(vo);
        //按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * 删除单条记录
     * @param id 主键id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        loadingNoticeService.delete(id);
    }
    
    /**
     * 删除单条记录
     * @param id 主键id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public int delete(HttpServletRequest request) {
        String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
        if (ids != null && ids.length != 0) {
            return loadingNoticeService.delete(ids); //删除多条记录
        }
        return 0;
    }
    
    /**
     * 批量保存，没有主键的insert，有主键的update
     * @param request
     * @return
     */
    @RequestMapping(value = "batch", method = RequestMethod.POST)
    public ResponseEntity<Map<String, int[]>> batchInsertUpdate(HttpServletRequest request) {
        List<LoadingNoticeVo> lvo = RmPopulateHelper.populateAjax(LoadingNoticeVo.class, request);
        for(LoadingNoticeVo vo : lvo) {
            if(vo.getId() != null) {
                RmVoHelper.markModifyStamp(request, vo);
            } else {
                RmVoHelper.markCreateStamp(request, vo);
            }
        }
        int[] sum_insert_update = loadingNoticeService.insertUpdateBatch(lvo.toArray(new LoadingNoticeVo[0]));
        Map<String, int[]> result = new HashMap<String, int[]>();
        result.put(EXECUTE_ROW_COUNT, sum_insert_update);
        return new ResponseEntity<Map<String, int[]>>(result, HttpStatus.OK);
    }
    
    /**
     * 叉车班长抢单
     * @param request
     * @return
     */
    @RequestMapping(value="forkliftMonitorGrabBill", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> forkliftMonitorGrabBill(HttpServletRequest request, @RequestParam(value="loadingNoticeId", required=true)String loadingNoticeId){
    	
    	return loadingNoticeService.updateforkliftMonitorGrabBill(request, loadingNoticeId);
    	
    }
    /**
     * 装车单手持列表展示
     * @param request
     * @param operation 操作符
     * @return
     */
    @RequestMapping(value="handList", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> handList(HttpServletRequest request, @RequestParam(value="operation", required=true)String operation){
    	return loadingNoticeService.handList(request, operation);
    	
    }
    
    /**
     * 待确认装车单确认(叉车班长录入破袋数量)
     * @param request
     * @param loadingNoticeId 装车单id
     * @return
     */
    @RequestMapping(value="toBeIdentifiedConfirm", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> toBeIdentifiedConfirm(HttpServletRequest request, @RequestParam(value="loadingNoticeId", required=true)String loadingNoticeId){
    	
    	return loadingNoticeService.updateToBeIdentifiedConfirm(request, loadingNoticeId);
    }
    /**
     * 查看我的转车确认单
     * @param request
     * @param loadingNoticeId 装车确认单id
     * @return
     */
    @RequestMapping(value="myToBeLoadingBill", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> myToBeLoadingBill(HttpServletRequest request, @RequestParam(value="loadingNoticeId", required=true)String loadingNoticeId){
    	return loadingNoticeService.myToBeLoadingBill(request, loadingNoticeId);
    }
    
    /**
     * 保运主管跳转
     * @param request
     * @return
     */
    @RequestMapping(value="supervisor")
    public String supervisor(HttpServletRequest request, Model model){
    	String operation = request.getParameter("operation");
    	if(StringHelper.isEmpty(operation)){
    		
    		model.addAttribute("operation", ISystemConstant.DICTIONARY_OPERATION_02);
    	}else{
    		model.addAttribute("operation", operation);
    		
    	}
    	model.addAttribute("operator", ISystemConstant.DICTIONARY_USER_TYPE_2);
    	return "/android/operationSupervisor";
    }
    /**
     * 叉车班长跳转
     * @param request
     * @return
     */
    @RequestMapping(value="forklift")
    public String forklift(HttpServletRequest request, Model model){
    	String operation = request.getParameter("operation");
    	if(StringHelper.isEmpty(operation)){
    		
    		model.addAttribute("operation", ISystemConstant.DICTIONARY_OPERATION_01);
    	}else{
    		model.addAttribute("operation", operation);
    		
    	}
    	model.addAttribute("operator", ISystemConstant.DICTIONARY_USER_TYPE_1);
    	return "/android/operationForklift";
    }
    /**
     * 安检员跳转
     * @param request
     * @return
     */
    @RequestMapping(value="security")
    public String security(HttpServletRequest request, Model model){
    	
    	String operation = request.getParameter("operation");
    	if(StringHelper.isEmpty(operation)){
    		
    		model.addAttribute("operation", ISystemConstant.DICTIONARY_SECURITY_CHECK_TYPE_01);
    	}else{
    		model.addAttribute("operation", operation);
    		
    	}
    	model.addAttribute("operator", ISystemConstant.DICTIONARY_USER_TYPE_3);
    	return "/android/operationSecurity";
    }
    
    /**
     * 叉车班长装车确认数据展示
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="loadingCar")
    public String loadingCar(@RequestParam(value="ladingNoticeId", required=true)String ladingNoticeId, HttpServletRequest request, Model model){
    	
    	RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
    	
    	if(userVo == null){
    		return "redirect:/jsp/android/login.jsp";
    	}
    	
    	LoadingNoticeVo vo = loadingNoticeService.get(ladingNoticeId);
    	
    	Map<String, Object> variable = new HashMap<String, Object>();
    	
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	variable.put("loadingNoticeId", ladingNoticeId);
    	
    	List<ProductBatchNumberVo> list = productBatchNumberService.list(variable, null);
    	
    	model.addAttribute(REQUEST_BEAN, vo);
    	model.addAttribute(REQUEST_BEANS, list);
    	return "/android/loadingCar";
    }
    /**
     * 叉车班长装车
     * @param request
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value="driverLoadingCar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> driverLoadingCar(HttpServletRequest request, @Valid LoadingNoticeVo vo){
    	
    	
    	return loadingNoticeService.updateDriverLoadingCar(request, vo);
    }
    
    
    /**
     * 装车详情查看
     * @param ladingNoticeId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="loadingCarDetail")
    public String loadingCarDetail(@RequestParam(value="ladingNoticeId", required=true)String ladingNoticeId, HttpServletRequest request, Model model){
    	
    	RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
    	
    	if(userVo == null){
    		return "redirect:/jsp/android/login.jsp";
    	}
    	
    	if(!ISystemConstant.DICTIONARY_USER_TYPE_1.equals(userVo.getUser_type()) && !ISystemConstant.DICTIONARY_USER_TYPE_2.equals(userVo.getUser_type())){
    		return "redirect:/jsp/android/login.jsp";
    	}
    	LoadingNoticeVo vo = loadingNoticeService.get(ladingNoticeId);
    	
    	Map<String, Object> variable = new HashMap<String, Object>();
    	
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	variable.put("loadingNoticeId", ladingNoticeId);
    	
    	List<ProductBatchNumberVo> list = productBatchNumberService.list(variable, null, 1, -1, false);
    	
    	model.addAttribute(REQUEST_BEAN, vo);
    	model.addAttribute(REQUEST_BEANS, list);
    	if(ISystemConstant.DICTIONARY_USER_TYPE_2.equals(userVo.getUser_type())){
    		model.addAttribute("isOperation", "isOperation");	
    	}
    	return "/android/loadingCarDetail";
    	
    }
}
