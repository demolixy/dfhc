/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  FormTemplateRestController.java
 *
 * 功能描述：  表单模板 rest controller
 * 
 * 版本历史：
 * 
 * 2016-11-25   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.formtemplate.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.quickbundle.base.web.page.RmPageVo;
import com.dfhc.wk.formtemplate.IFormTemplateConstants;
import com.dfhc.wk.formtemplate.service.FormTemplateService;
import com.dfhc.wk.formtemplate.vo.FormTemplateVo;
import com.dfhc.wk.formtemplate.web.FormTemplateController;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * list                 GET     /api/formtemplate
 * get                  GET     /api/formtemplate/{id}
 * insert action        POST    /api/formtemplate
 * update action        PUT     /api/formtemplate/{id}
 * delete action        DELETE  /api/formtemplate/{id}
 * delete multi action  POST    /api/formtemplate/delete
 * batch insert update  POST    /api/formtemplate/batch
 */

/**
 * 表单模板rest controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/api/formtemplate")
public class FormTemplateRestController implements IFormTemplateConstants {

    @Autowired
    private FormTemplateService formTemplateService;

    @Autowired
    private Validator validator;

    /**
     * 获取多条记录
     * @param request  http请求对象
     * @return 返回结果map
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> searchPara = FormTemplateController.getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, formTemplateService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<FormTemplateVo> beans = formTemplateService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
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
        FormTemplateVo task = formTemplateService.get(id);
        if (task == null) {
            return new ResponseEntity<FormTemplateVo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FormTemplateVo>(task, HttpStatus.OK);
    }
    
    /**
     * 插入单条记录，使用服务端管理的ID号
     * @param vo  值对象
     * @param uriBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody FormTemplateVo vo, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<FormTemplateVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        formTemplateService.insert(vo);
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = vo.getId();
        URI uri = uriBuilder.path("/api/formtemplate/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody FormTemplateVo vo) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<FormTemplateVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        //保存
        formTemplateService.update(vo);
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
        formTemplateService.delete(id);
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
            return formTemplateService.delete(ids); //删除多条记录
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
        List<FormTemplateVo> lvo = RmPopulateHelper.populateAjax(FormTemplateVo.class, request);
        for(FormTemplateVo vo : lvo) {
            if(vo.getId() != null) {
                RmVoHelper.markModifyStamp(request, vo);
            } else {
                RmVoHelper.markCreateStamp(request, vo);
            }
        }
        int[] sum_insert_update = formTemplateService.insertUpdateBatch(lvo.toArray(new FormTemplateVo[0]));
        Map<String, int[]> result = new HashMap<String, int[]>();
        result.put(EXECUTE_ROW_COUNT, sum_insert_update);
        return new ResponseEntity<Map<String, int[]>>(result, HttpStatus.OK);
    }
}
