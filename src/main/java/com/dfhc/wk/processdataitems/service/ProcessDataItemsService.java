/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  ProcessDataItemsService.java
 *
 * 功能描述：  流程数据项服务
 * 
 * 版本历史：
 * 
 * 2016-12-05   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.processdataitems.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dfhc.wk.processdataitems.IProcessDataItemsConstants;
import com.dfhc.wk.processdataitems.dao.ProcessDataItemsDao;
import com.dfhc.wk.processdataitems.vo.ProcessDataItemsVo;
import com.dfhc.PjException;
import com.dfhc.util.StringHelper;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 流程数据项服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class ProcessDataItemsService implements IProcessDataItemsConstants {

    @Autowired
    /**
     * 流程数据项数据访问对象
     */
    private ProcessDataItemsDao processDataItemsDao;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(ProcessDataItemsVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = processDataItemsDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "流程数据项插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(ProcessDataItemsVo[] vos) {
        String[] ids = processDataItemsDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "流程数据项插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = processDataItemsDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "流程数据项删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = processDataItemsDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "流程数据项删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ProcessDataItemsVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = processDataItemsDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "流程数据项更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(ProcessDataItemsVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "流程数据项批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(ProcessDataItemsVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<ProcessDataItemsVo> lInsert = new ArrayList<ProcessDataItemsVo>();
        List<ProcessDataItemsVo> lUpdate = new ArrayList<ProcessDataItemsVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new ProcessDataItemsVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new ProcessDataItemsVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ProcessDataItemsVo get(String id) {
        ProcessDataItemsVo vo = processDataItemsDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = processDataItemsDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<ProcessDataItemsVo> list(Map<String, Object> searchPara, String orderStr) {
        return list(searchPara, orderStr, 1, Integer.MAX_VALUE);
    }

    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<ProcessDataItemsVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        return list(searchPara, orderStr, startIndex, size, false);
    }
    
    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @param allColumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<ProcessDataItemsVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<ProcessDataItemsVo> lResult = processDataItemsDao.list(searchPara, orderStr, startIndex, size, allColumn);
        return lResult;
    }
    
    /**
     * 按条件搜索，走MyBatis的XML文件的search
     * 
     * @param searchPara 搜索参数的Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<ProcessDataItemsVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<ProcessDataItemsVo> lResult = processDataItemsDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(ProcessDataItemsVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(ProcessDataItemsVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<ProcessDataItemsVo> vos) {
        for(ProcessDataItemsVo vo:vos){
           verifyUpdateVo(vo);
        }
        processDataItemsDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<ProcessDataItemsVo> vos) {
        for(ProcessDataItemsVo vo:vos){
           verifyInsertVo(vo);
        }
        processDataItemsDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        ProcessDataItemsVo vo = processDataItemsDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(processDataItemsDao.update(vo)!=1){
           throw new PjException("更新删除标志失败!");
        }
        return 1;
    }

    /**
     * 逻辑删除多条记录
     * @param request 页面请求
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String ids[]) {
        if(ids!=null && ids.length>0 ){
           for(String id:ids){
               deleteLogic(request,id);
           }
           return ids.length;
        }else{
           throw new PjException("ids 为空!");
        }
    }

    /**
     * 查询数据项业务组
     * @param result
     * @return
     */
	public List<Map<String, Object>> getBusinessGroupList(Map<String, Object> result) {
		
		
		return processDataItemsDao.getBusinessGroupList(result);
	}

}
