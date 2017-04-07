/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  WeighingBillRestController.java
 *
 * 功能描述：  过磅单 rest controller
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.weighingbill.rest;

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
import com.dfhc.bus.weighingbill.IWeighingBillConstants;
import com.dfhc.bus.weighingbill.service.WeighingBillService;
import com.dfhc.bus.weighingbill.vo.WeighingBillVo;
import com.dfhc.bus.weighingbill.web.WeighingBillController;
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
 * list                 GET     /api/weighingbill
 * get                  GET     /api/weighingbill/{id}
 * insert action        POST    /api/weighingbill
 * update action        PUT     /api/weighingbill/{id}
 * delete action        DELETE  /api/weighingbill/{id}
 * delete multi action  POST    /api/weighingbill/delete
 * batch insert update  POST    /api/weighingbill/batch
 */

/**
 * 过磅单rest controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/api/weighingbill")
public class WeighingBillRestController implements IWeighingBillConstants {

    @Autowired
    private WeighingBillService weighingBillService;

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
        Map<String, Object> searchPara = WeighingBillController.getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, weighingBillService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<WeighingBillVo> beans = weighingBillService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
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
        WeighingBillVo task = weighingBillService.get(id);
        if (task == null) {
            return new ResponseEntity<WeighingBillVo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WeighingBillVo>(task, HttpStatus.OK);
    }
    
    /**
     * 插入单条记录，使用服务端管理的ID号
     * @param vo  值对象
     * @param uriBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody WeighingBillVo vo, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<WeighingBillVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        weighingBillService.insert(vo);
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = vo.getId();
        URI uri = uriBuilder.path("/api/weighingbill/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WeighingBillVo vo) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<WeighingBillVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        //保存
        weighingBillService.update(vo);
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
        weighingBillService.delete(id);
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
            return weighingBillService.delete(ids); //删除多条记录
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
        List<WeighingBillVo> lvo = RmPopulateHelper.populateAjax(WeighingBillVo.class, request);
        for(WeighingBillVo vo : lvo) {
            if(vo.getId() != null) {
                RmVoHelper.markModifyStamp(request, vo);
            } else {
                RmVoHelper.markCreateStamp(request, vo);
            }
        }
        int[] sum_insert_update = weighingBillService.insertUpdateBatch(lvo.toArray(new WeighingBillVo[0]));
        Map<String, int[]> result = new HashMap<String, int[]>();
        result.put(EXECUTE_ROW_COUNT, sum_insert_update);
        return new ResponseEntity<Map<String, int[]>>(result, HttpStatus.OK);
    }
    
    /**
     * 二次过磅
     * @param request
     * @param billid 订单号
     * @param truckNumber 车牌号
     * @param fistTime 第一次过磅时间 
     * @param firstWeight 第一次检斤重量
     * @param firstClerk 第一次司磅员
     * @param secondTime 第二次过磅时间
     * @param secondWeight 第二次检斤重量
     * @param secondClerk 第二次司磅员
     * @param weight 净重
     * @return
     */
    @RequestMapping(value="twoWeigh", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> twoWeigh(HttpServletRequest request, @RequestParam(value="billid", required=true)String billid,
    							@RequestParam(value="truckNumber", required=true)String truckNumber, @RequestParam(value="fistTime", required=true)String fistTime,
    							@RequestParam(value="firstWeight", required=true)String firstWeight, @RequestParam(value="firstClerk", required=true)String firstClerk,
    							@RequestParam(value="secondTime", required=true)String secondTime, @RequestParam(value="secondWeight", required=true)String secondWeight,
    							@RequestParam(value="secondClerk", required=true)String secondClerk, @RequestParam(value="weight", required=true)String weight){
    	long guid = System.currentTimeMillis();
    	log.info("二次过磅 guid:"+guid+",请求参数为:[billid="+billid+",truckNumber="+truckNumber+",fistTime="+fistTime+",firstWeight="+firstWeight+
    			",firstClerk="+firstClerk+",secondTime="+secondTime+",secondWeight="+secondWeight+
    			",secondClerk"+secondClerk+",weight="+weight+"]");
    	Map<String, Object> result = weighingBillService.updateTwoWeigh(request, billid, truckNumber, fistTime, firstWeight, firstClerk, secondTime, secondWeight, secondClerk, weight);
    	log.info("二次过磅 guid:"+guid+",应答为:"+JSON.toJSONString(result));
		return result;
    }
}
