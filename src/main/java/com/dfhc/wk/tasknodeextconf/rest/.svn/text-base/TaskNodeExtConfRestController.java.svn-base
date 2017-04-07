/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  TaskNodeExtConfRestController.java
 *
 * 功能描述：  任务节点扩展配置 rest controller
 * 
 * 版本历史：
 * 
 * 2016-11-25   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.tasknodeextconf.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.quickbundle.base.web.page.RmPageVo;
import com.dfhc.wk.tasknodeextconf.ITaskNodeExtConfConstants;
import com.dfhc.wk.tasknodeextconf.service.TaskNodeExtConfService;
import com.dfhc.wk.tasknodeextconf.vo.TaskNodeExtConfVo;
import com.dfhc.wk.tasknodeextconf.web.TaskNodeExtConfController;
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
 * list                 GET     /api/tasknodeextconf
 * get                  GET     /api/tasknodeextconf/{id}
 * insert action        POST    /api/tasknodeextconf
 * update action        PUT     /api/tasknodeextconf/{id}
 * delete action        DELETE  /api/tasknodeextconf/{id}
 * delete multi action  POST    /api/tasknodeextconf/delete
 * batch insert update  POST    /api/tasknodeextconf/batch
 */

/**
 * 任务节点扩展配置rest controller
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Controller
@RequestMapping(value = "/api/tasknodeextconf")
public class TaskNodeExtConfRestController implements ITaskNodeExtConfConstants {

    @Autowired
    private TaskNodeExtConfService taskNodeExtConfService;

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
        Map<String, Object> searchPara = TaskNodeExtConfController.getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, taskNodeExtConfService.getCount(searchPara));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<TaskNodeExtConfVo> beans = taskNodeExtConfService.list(searchPara, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
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
        TaskNodeExtConfVo task = taskNodeExtConfService.get(id);
        if (task == null) {
            return new ResponseEntity<TaskNodeExtConfVo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TaskNodeExtConfVo>(task, HttpStatus.OK);
    }
    
    /**
     * 插入单条记录，使用服务端管理的ID号
     * @param vo  值对象
     * @param uriBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody TaskNodeExtConfVo vo, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<TaskNodeExtConfVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        taskNodeExtConfService.insert(vo);
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = vo.getId();
        URI uri = uriBuilder.path("/api/tasknodeextconf/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody TaskNodeExtConfVo vo) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<TaskNodeExtConfVo>> failures = validator.validate(vo);
        if (!failures.isEmpty()) {
            return RmResponseEntityFactory.build(failures, HttpStatus.BAD_REQUEST);
        }
        //保存
        taskNodeExtConfService.update(vo);
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
        taskNodeExtConfService.delete(id);
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
            return taskNodeExtConfService.delete(ids); //删除多条记录
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
        List<TaskNodeExtConfVo> lvo = RmPopulateHelper.populateAjax(TaskNodeExtConfVo.class, request);
        for(TaskNodeExtConfVo vo : lvo) {
            if(vo.getId() != null) {
                RmVoHelper.markModifyStamp(request, vo);
            } else {
                RmVoHelper.markCreateStamp(request, vo);
            }
        }
        int[] sum_insert_update = taskNodeExtConfService.insertUpdateBatch(lvo.toArray(new TaskNodeExtConfVo[0]));
        Map<String, int[]> result = new HashMap<String, int[]>();
        result.put(EXECUTE_ROW_COUNT, sum_insert_update);
        return new ResponseEntity<Map<String, int[]>>(result, HttpStatus.OK);
    }
}
