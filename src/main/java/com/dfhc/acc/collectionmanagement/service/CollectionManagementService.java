/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CollectionManagementService.java
 *
 * 功能描述：  收款管理服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.acc.collectionmanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dfhc.acc.collectionmanagement.ICollectionManagementConstants;
import com.dfhc.acc.collectionmanagement.dao.CollectionManagementDao;
import com.dfhc.acc.collectionmanagement.vo.CollectionManagementVo;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.AccountChangeService;
import com.dfhc.pub.vo.FreezeAccVo;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 收款管理服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class CollectionManagementService implements ICollectionManagementConstants {

    @Autowired
    /**
     * 收款管理数据访问对象
     */
    private CollectionManagementDao collectionManagementDao;
    @Autowired
    private AccountChangeService accountChangeService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(CollectionManagementVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = collectionManagementDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理插入了1条记录,id={}", id);
        return id;
    }
  
    
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insertOne(HttpServletRequest request,CollectionManagementVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = collectionManagementDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理插入了1条记录,id={}", id);
        
        doFreezeAccVo(request,vo);
        
        
        return id;
    }
    
    
    /**
     * 收款管理
     */
    public void doFreezeAccVo(HttpServletRequest request,CollectionManagementVo  vo) {
		RmUserVo rmUserVo = (RmUserVo) RmProjectHelper.getRmUserVo(request);
     	//冻结账户金额
     	FreezeAccVo freezeAccVo=new  FreezeAccVo();
     	freezeAccVo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_04);
     	freezeAccVo.setPayerId(vo.getCustomerId().toString());//客户id
     	freezeAccVo.setBillsId(vo.getId());//单据id
     	freezeAccVo.setChangeAmt(vo.getAmountCollected());//金额
     	freezeAccVo.setRemark("收款管理");
     	freezeAccVo.setIp(null);
     	freezeAccVo.setOperaterId(rmUserVo.getId());
     	freezeAccVo.setOperaterName(rmUserVo.getName());//操作人
     	accountChangeService.doCollectionAmt(freezeAccVo);
    	return ;
    }

 
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(CollectionManagementVo[] vos) {
        String[] ids = collectionManagementDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = collectionManagementDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = collectionManagementDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(CollectionManagementVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = collectionManagementDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(CollectionManagementVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "收款管理批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(CollectionManagementVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<CollectionManagementVo> lInsert = new ArrayList<CollectionManagementVo>();
        List<CollectionManagementVo> lUpdate = new ArrayList<CollectionManagementVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new CollectionManagementVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new CollectionManagementVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public CollectionManagementVo get(String id) {
        CollectionManagementVo vo = collectionManagementDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = collectionManagementDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<CollectionManagementVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<CollectionManagementVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<CollectionManagementVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<CollectionManagementVo> lResult = collectionManagementDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<CollectionManagementVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<CollectionManagementVo> lResult = collectionManagementDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(CollectionManagementVo vo) {
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
    private void verifyInsertVo(CollectionManagementVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<CollectionManagementVo> vos) {
        for(CollectionManagementVo vo:vos){
           verifyUpdateVo(vo);
        }
        collectionManagementDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<CollectionManagementVo> vos) {
        for(CollectionManagementVo vo:vos){
           verifyInsertVo(vo);
        }
        collectionManagementDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        CollectionManagementVo vo = collectionManagementDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(collectionManagementDao.update(vo)!=1){
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

    public CollectionManagementVo getCollectionManagementVo(Map<String, Object> result, boolean flag){
    	
    	List<CollectionManagementVo> list = list(result, null, 1, -1, flag);
    	
    	return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    public CollectionManagementVo getCollectionManagementVo(Map<String, Object> result){
    	
    	return getCollectionManagementVo(result, false);
    }
    
    /**
     * 创建  FreezeAccVo
     * doCollectionAmt 收款接口
     * @param managementVo
     * @return
     */
    public FreezeAccVo createFreezeAccVo(CollectionManagementVo managementVo){
    	FreezeAccVo vo = new FreezeAccVo();
    	vo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_04);
    	vo.setPayerId(String.valueOf(managementVo.getCustomerId()));
    	vo.setBillsId(managementVo.getId());
    	vo.setChangeAmt(managementVo.getAmountCollected());
    	vo.setRemark("收款");
    	return vo;
    }
}
