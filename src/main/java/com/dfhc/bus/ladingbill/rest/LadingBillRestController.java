/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LadingBillRestController.java
 *
 * 功能描述：  司机提货单 rest controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.ladingbill.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.third.spring.http.RmResponseEntityFactory;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.dfhc.ISystemConstant;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.ladingbill.web.LadingBillController;
import com.dfhc.bus.vehiclesafetyinspection.service.VehicleSafetyInspectionService;

/**
 * list                 GET     /api/ladingbill
 * get                  GET     /api/ladingbill/{id}
 * insert action        POST    /api/ladingbill
 * update action        PUT     /api/ladingbill/{id}
 * delete action        DELETE  /api/ladingbill/{id}
 * delete multi action  POST    /api/ladingbill/delete
 * batch insert update  POST    /api/ladingbill/batch
 */

/**
 * 司机提货单rest controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/api/ladingbill")
public class LadingBillRestController implements ILadingBillConstants {

    @Autowired
    private LadingBillService ladingBillService;

    @Autowired
    private Validator validator;    
    @Autowired
    private VehicleSafetyInspectionService vehicleSafetyInspectionService;
    private static final Logger log = RmLogHelper.getLogger("rmAPI");
    /**
     * 获取多条记录
     * @param request  http请求对象
     * @return 返回结果map
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> searchPara = LadingBillController.getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, ladingBillService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<LadingBillVo> beans = ladingBillService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
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
        LadingBillVo task = ladingBillService.get(id);
        if (task == null) {
            return new ResponseEntity<LadingBillVo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<LadingBillVo>(task, HttpStatus.OK);
    }
    
    /**
     * 插入单条记录，使用服务端管理的ID号
     * @param vo  值对象
     * @param uriBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody LadingBillVo vo, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<LadingBillVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        ladingBillService.insert(vo);
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = vo.getId();
        URI uri = uriBuilder.path("/api/ladingbill/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody LadingBillVo vo) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<LadingBillVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        //保存
        ladingBillService.update(vo);
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
        ladingBillService.delete(id);
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
            return ladingBillService.delete(ids); //删除多条记录
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
        List<LadingBillVo> lvo = RmPopulateHelper.populateAjax(LadingBillVo.class, request);
        for(LadingBillVo vo : lvo) {
            if(vo.getId() != null) {
                RmVoHelper.markModifyStamp(request, vo);
            } else {
                RmVoHelper.markCreateStamp(request, vo);
            }
        }
        int[] sum_insert_update = ladingBillService.insertUpdateBatch(lvo.toArray(new LadingBillVo[0]));
        Map<String, int[]> result = new HashMap<String, int[]>();
        result.put(EXECUTE_ROW_COUNT, sum_insert_update);
        return new ResponseEntity<Map<String, int[]>>(result, HttpStatus.OK);
    }
    /**
     * 多个状态获取提货单列表信息接口
     * @param request
     * @param phoneNumber
     * @param idNumber
     * @param truckNumber
     * @param billStatus
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value="getLadingBillListForSign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getLadingBillListForSign(HttpServletRequest request, @RequestParam(value="phoneNumber", 
								required=false, defaultValue="")String phoneNumber, 
								@RequestParam(value="idNumber", required=false, 
								defaultValue="")String idNumber, @RequestParam(value="truckNumber", required=false, defaultValue="")String truckNumber,
								@RequestParam(value="billStatus", required=true)String billStatus, @RequestParam(value="startDate", required=false, defaultValue="")String startDate,
								@RequestParam(value="endDate", required=false, defaultValue="")String endDate,
								@RequestParam(value="billid", required=false, defaultValue="")String billid
								){
    	//允许传单个状态 remove by longsebo 2017-03-28
//    	if(!(billStatus.split(",").length > 1)){
//    		Map<String, Object> result = new HashMap<String, Object>();
//    		result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
//    		result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "参数错误！");
//    		
//    		return result;
//    	}
    	//将第八接口:多个状态获取提货单列表信息
       	StringBuilder builder = new StringBuilder();
    	long guid = System.currentTimeMillis();
    	builder.append("请求guid:").append(guid).append(",参数列表:[phoneNumber=").append(phoneNumber).append(",")
    	.append("idNumber=").append(idNumber).append(",")
    	.append("truckNumber=").append(truckNumber).append(",")
    	.append("billStatus=").append(billStatus).append(",")
    	.append("startDate=").append(startDate).append(",")
    	.append("endDate=").append(endDate).append(",")
    	.append("billid=").append(billid).append("]")
    	;
    	log.info("多个状态获取提货单列表信息"+builder.toString());

		Map<String,Object> result =  ladingBillService.getLadingBillListForSign(request, phoneNumber, idNumber, truckNumber, billStatus, startDate, endDate,billid);
		log.info("多个状态获取提货单列表信息应答guid:"+guid+",应答信息:"+JSON.toJSONString(result));
		return  result;
    }
    
    /**
	 * 接口获取提货单信息
	 * 
	 * 条件三选一  phoneNumber			当前签到司机手机号  idNumber			当前签到司机身份证号 truckNumber			当前签到司机车牌号
	 * @param request
	 * @param phoneNumber 电话号
	 * @param idNumber 身份证号
	 * @param truckNumber 车牌号
	 * @param billStatus 状态
	 * @param startDate 开始日期 为空就是当天
	 * @param endDate 结束日期 为空就是当天
	 * @return
	 */
	@RequestMapping(value="getLadingBillList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getLadingBillList(HttpServletRequest request, @RequestParam(value="phoneNumber", 
								required=false, defaultValue="")String phoneNumber, 
								@RequestParam(value="idNumber", required=false, 
								defaultValue="")String idNumber, @RequestParam(value="truckNumber", required=false, defaultValue="")String truckNumber,
								@RequestParam(value="billStatus", required=true)String billStatus, @RequestParam(value="startDate", required=false, defaultValue="")String startDate,
								@RequestParam(value="endDate", required=false, defaultValue="")String endDate
								){
    	StringBuilder builder = new StringBuilder();
    	long guid = System.currentTimeMillis();
    	builder.append("请求guid:").append(guid).append(",参数列表:[phoneNumber=").append(phoneNumber).append(",")
    	.append("idNumber=").append(idNumber).append(",")
    	.append("truckNumber=").append(truckNumber).append(",")
    	.append("billStatus=").append(billStatus).append(",")
    	.append("startDate=").append(startDate).append(",")
    	.append("endDate=").append(endDate).append("]");
    	log.info("获取提货单列表信息"+builder.toString());

		Map<String,Object> result =  ladingBillService.getLadingBillList(request, phoneNumber, idNumber, truckNumber, billStatus, startDate, endDate);
		log.info("获取提货单列表信息应答guid:"+guid+",应答信息:"+JSON.toJSONString(result));
		return  result;
	}
	
	
	/**
	 * 作废提货单
	 * @param request
	 * @param billid
	 * @return
	 */
	@RequestMapping(value="cancelLadingBill", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> cancelLadingBill(HttpServletRequest request, @RequestParam(value="billid", required=true)String billid){
		long guid = System.currentTimeMillis();
    	StringBuilder builder = new StringBuilder();
		builder.append("请求guid:").append(guid).append(",参数列表:[billid=").append(billid).append("]");
    	log.info("作废车辆上传"+builder.toString());
		Map<String, Object> result = ladingBillService.updateCancelLadingBill(request, billid);
		log.info("作废车辆上传应答guid:"+guid+",应答信息:"+JSON.toJSONString(result));
		return result;
	}
	/**
	 * 磅端设置安检通过
	 * @param request
	 * @param billid
	 * @return
	 */
	@RequestMapping(value="updateLadingPass", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updateLadingPass(HttpServletRequest request, @RequestParam(value="billid", required=true)String billid,@RequestParam(value="status", required=true)String status){
		long guid = System.currentTimeMillis();
    	StringBuilder builder = new StringBuilder();
		builder.append("请求guid:").append(guid).append(",参数列表:[billid=").append(billid).append(",status=")
		.append(status).append("]");
    	log.info("磅端设置安检通过"+builder.toString());
		Map<String, Object> result = vehicleSafetyInspectionService.updateLadingPass(request, billid,status);
		log.info("磅端设置安检通过应答guid:"+guid+",应答信息:"+JSON.toJSONString(result));
		return result;
	}
}
