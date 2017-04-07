/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  SecurityIndexService.java
 *
 * 功能描述：  安检指标表服务
 * 
 * 版本历史：
 * 
 * 2017-04-01   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.securityindex.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dfhc.base.securityindex.ISecurityIndexConstants;
import com.dfhc.base.securityindex.dao.SecurityIndexDao;
import com.dfhc.base.securityindex.vo.SecurityIndexVo;
import com.dfhc.PjException;
import com.dfhc.util.StringHelper;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
import org.apache.commons.collections.CollectionUtils;
/**
 * 安检指标表服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class SecurityIndexService implements ISecurityIndexConstants {

    @Autowired
    /**
     * 安检指标表数据访问对象
     */
    private SecurityIndexDao securityIndexDao;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(SecurityIndexVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = securityIndexDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "安检指标表插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(SecurityIndexVo[] vos) {
        String[] ids = securityIndexDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "安检指标表插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = securityIndexDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "安检指标表删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = securityIndexDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "安检指标表删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(SecurityIndexVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = securityIndexDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "安检指标表更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(SecurityIndexVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "安检指标表批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(SecurityIndexVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<SecurityIndexVo> lInsert = new ArrayList<SecurityIndexVo>();
        List<SecurityIndexVo> lUpdate = new ArrayList<SecurityIndexVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new SecurityIndexVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new SecurityIndexVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public SecurityIndexVo get(String id) {
        SecurityIndexVo vo = securityIndexDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = securityIndexDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<SecurityIndexVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<SecurityIndexVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<SecurityIndexVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<SecurityIndexVo> lResult = securityIndexDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<SecurityIndexVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<SecurityIndexVo> lResult = securityIndexDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(SecurityIndexVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
        if(StringHelper.isEmpty(vo.getCheckType())){
           throw new PjException("检查类型为空!");
        }
        if(StringHelper.isEmpty(vo.getCheckItem())){
           throw new PjException("检查项目为空!");
        }
        if(StringHelper.isEmpty(vo.getCheckResultCtrlType())){
           throw new PjException("检查结果控件类型为空!");
        }
        if(StringHelper.isEmpty(vo.getCheckResultCtrlName())){
           throw new PjException("检查结果控件名称为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(SecurityIndexVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getCheckType())){
           throw new PjException("检查类型为空!");
        }	
        if(StringHelper.isEmpty(vo.getCheckItem())){
           throw new PjException("检查项目为空!");
        }	
        if(StringHelper.isEmpty(vo.getCheckResultCtrlType())){
           throw new PjException("检查结果控件类型为空!");
        }	
        if(StringHelper.isEmpty(vo.getCheckResultCtrlName())){
           throw new PjException("检查结果控件名称为空!");
        }	
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<SecurityIndexVo> vos) {
        for(SecurityIndexVo vo:vos){
           verifyUpdateVo(vo);
        }
        securityIndexDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<SecurityIndexVo> vos) {
        for(SecurityIndexVo vo:vos){
           verifyInsertVo(vo);
        }
        securityIndexDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        SecurityIndexVo vo = securityIndexDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(securityIndexDao.update(vo)!=1){
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
    * 通过map查询单条vo
    * @param params 查询条件
    * @param flag  删除标志
    * @return
    */
   public SecurityIndexVo getVo(Map<String, Object> params, boolean flag){
        List<SecurityIndexVo> list = list(params, null, 1, -1, flag);
	 
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
   }
}
