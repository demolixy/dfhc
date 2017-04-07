/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  GateRestController.java
 *
 * 功能描述：  门岗 rest controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.gate.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.quickbundle.base.web.page.RmPageVo;

import com.alibaba.fastjson.JSON;
import com.dfhc.bus.gate.IGateConstants;
import com.dfhc.bus.gate.service.GateService;
import com.dfhc.bus.gate.vo.GateVo;
import com.dfhc.bus.gate.web.GateController;
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

/**
 * list                 GET     /api/gate
 * get                  GET     /api/gate/{id}
 * insert action        POST    /api/gate
 * update action        PUT     /api/gate/{id}
 * delete action        DELETE  /api/gate/{id}
 * delete multi action  POST    /api/gate/delete
 * batch insert update  POST    /api/gate/batch
 */

/**
 * 门岗rest controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/api/gate")
public class GateRestController implements IGateConstants {

    @Autowired
    private GateService gateService;

    @Autowired
    private Validator validator;
    private static final Logger log = RmLogHelper.getLogger("rmAPI");
    /**
     * 获取多条记录
     * @param request  http请求对象
     * @return 返回结果map
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> searchPara = GateController.getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, gateService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<GateVo> beans = gateService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
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
        GateVo task = gateService.get(id);
        if (task == null) {
            return new ResponseEntity<GateVo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GateVo>(task, HttpStatus.OK);
    }
    
    /**
     * 插入单条记录，使用服务端管理的ID号
     * @param vo  值对象
     * @param uriBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody GateVo vo, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<GateVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        gateService.insert(vo);
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = vo.getId();
        URI uri = uriBuilder.path("/api/gate/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody GateVo vo) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<GateVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        //保存
        gateService.update(vo);
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
        gateService.delete(id);
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
            return gateService.delete(ids); //删除多条记录
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
        List<GateVo> lvo = RmPopulateHelper.populateAjax(GateVo.class, request);
        for(GateVo vo : lvo) {
            if(vo.getId() != null) {
                RmVoHelper.markModifyStamp(request, vo);
            } else {
                RmVoHelper.markCreateStamp(request, vo);
            }
        }
        int[] sum_insert_update = gateService.insertUpdateBatch(lvo.toArray(new GateVo[0]));
        Map<String, int[]> result = new HashMap<String, int[]>();
        result.put(EXECUTE_ROW_COUNT, sum_insert_update);
        return new ResponseEntity<Map<String, int[]>>(result, HttpStatus.OK);
    }
    
    /**
     * 门岗对车辆出入厂操作
     * @param request
     * @return
     */
    @RequestMapping(value="gateOperation", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> gateOperation(HttpServletRequest request, @RequestParam(value="truckNumber", required=true)String truckNumber, @RequestParam(value="operationCode", required=true)String operationCode){
    	long guid = System.currentTimeMillis();
    	log.info("门岗对车辆出入厂操作请求 guid:"+guid+",参数为:[truckNumber="+truckNumber+",operationCode="+operationCode+"]");
    	Map<String, Object> result = gateService.updateGateOperation(request, truckNumber, operationCode);
    	log.info("门岗对车辆出入厂操作应答 guid:"+guid+",返回值为:"+JSON.toJSONString(result));
		return result;   	
    	
    }
    
    /**
     * 车辆是否可以出入厂
     * @param request
     * @param truckNumber 车牌号
     * @param type 入厂0，出厂1
     * @return
     */
    @RequestMapping(value="whetherGoOutAndComeIn", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> whetherGoOutAndComeIn(HttpServletRequest request, @RequestParam(value="truckNumber", required=true)String truckNumber, @RequestParam(value="type", required=true)String type){
    	
    	return gateService.whetherGoOutAndComeIn(request, truckNumber, type);
    }
}
