/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ShipModeDao.java
 *
 * 功能描述：  发运方式数据访问对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.shipmode.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.config.RmConfig;
import org.quickbundle.third.mybatis.ParaMap;
import org.quickbundle.third.mybatis.RmSqlSessionDaoSupport;
import org.quickbundle.tools.helper.RmSqlHelper;
import org.springframework.stereotype.Repository;
import com.dfhc.util.ConvertHelper;
import com.dfhc.base.shipmode.IShipModeConstants;
import com.dfhc.base.shipmode.vo.ShipModeVo;

/**
 * 发运方式数据访问对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
@Repository
public class ShipModeDao extends RmSqlSessionDaoSupport implements IShipModeConstants {

    /**
     * 插入单条记录，用id作主键
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的id
     */
    public String insert(ShipModeVo vo) {
        if(vo.getId() == null || vo.getId().length() == 0) {
            vo.setId(RmIdFactory.requestId(TABLE_NAME)); //获得id
        }
        getSqlSession().insert(namespace("insert"), vo);
        return vo.getId();
    }

    /**
     * 批更新插入多条记录，用id作主键
     * 
     * @param vos 添加的VO对象数组
     * @return 若添加成功，返回新生成的id数组
     */
    public String[] insert(ShipModeVo[] vos) {
        String[] ids =RmIdFactory.requestId(TABLE_NAME, vos.length); //批量获得id
        for(int i=0; i<vos.length; i++) {
            vos[i].setId(ids[i]);
        }
        SqlSession session = getSqlSessionTemplate().getSqlSessionFactory().openSession(ExecutorType.BATCH);
        for(ShipModeVo vo : vos) {
            session.insert(namespace("insert"), vo);
        }
        session.flushStatements();
        return ids;
    }
    
    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        return getSqlSession().delete(namespace("delete"), id);
    }

    /**
     * 删除多条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        if(ids == null || ids.length == 0) {
            return 0;
        }
        int result = 0;
        List<String[]> lIds = RmSqlHelper.splitPagingArray(ids, RmConfig.getSingleton().getMaxSqlInCount());
        for(String[] thisIds : lIds) {
            result += getSqlSession().delete(namespace("deleteMulti"), thisIds);
        }
        return result;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ShipModeVo vo) {
        return getSqlSession().update(namespace("update"), vo);
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 添加的VO对象数组
     * @return 成功更新的记录数组
     */
    public int[] update(ShipModeVo[] vos) {
        int[] result = new int[vos.length];
        SqlSession session = getSqlSessionTemplate().getSqlSessionFactory().openSession(ExecutorType.BATCH);
        int index = 0;
        for(ShipModeVo vo : vos) {
            result[index++] = session.update(namespace("update"), vo);
        }
        session.flushStatements();
        return result;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ShipModeVo get(String id) {
        return getSqlSession().selectOne(namespace("get"), id);
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        return (Integer)getSqlSession().selectOne(namespace("getCount"), searchPara);
    }
    
    /**
     * 功能: 通过组合后的查询条件获得所有的VO对象列表，带翻页，带排序字符
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @param selectAllClumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<ShipModeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allClumn) {
        searchPara.put("orderStr", orderStr);
        return getSqlSession().selectList(namespace(allClumn ? "listAllColumn" : "list"), searchPara, new RowBounds(startIndex-1, size));
    }
    
    /**
     * 功能: 传入查询参数Map，获得所有的VO对象列表，带翻页，带排序字符
     * 
     * @param searchPara 搜索参数的Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @return
     */
    public List<ShipModeVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        searchPara.put("orderStr", orderStr);
        return getSqlSession().selectList(namespace("search"), searchPara, new RowBounds(startIndex-1, size));
    }
    /**
     * 批量更新
     * @param vos vo列表
     */
    public void updateBatch(List<ShipModeVo> vos) {
        getSqlSession().update(namespace("updateBatch"), vos);
    }
    /**
     * 批量插入
     * @param vos vo列表
     */
    public void insertBatch(List<ShipModeVo> vos) {
        List<String> idList = null;
        //统计id为空的数量
        int requestIdNum = 0;
        for(int i=0;i<vos.size();i++) {
           ShipModeVo vo = vos.get(i);
           if(vo.getId() == null || vo.getId().length() == 0) {
               requestIdNum = requestIdNum +1;
           }
        }
        String[] ids =RmIdFactory.requestId(TABLE_NAME, requestIdNum); //批量获得id
        idList = ConvertHelper.toList(ids);
        for(int i=0;i<vos.size();i++) {
            ShipModeVo vo = vos.get(i);
            if(vo.getId() == null || vo.getId().length() == 0) {
              vo.setId(idList.remove(0)); //获得id
           }
        }
        getSqlSession().update(namespace("insertBatch"), vos);
    }
}
