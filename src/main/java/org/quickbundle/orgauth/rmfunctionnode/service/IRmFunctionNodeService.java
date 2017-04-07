//代码生成时,文件路径: e:/download/quickbundle-securityweb/src/main/java/orgauth/rmfunctionnode/service/IRmFunctionNodeService.java
//代码生成时,系统时间: 2010-11-27 22:08:40
//代码生成时,操作系统用户: Administrator

/*
 * 系统名称:单表模板 --> quickbundle-securityweb
 * 
 * 文件名称: org.quickbundle.orgauth.rmfunctionnode.service --> IRmFunctionNodeService.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2010-11-27 22:08:40 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.orgauth.rmfunctionnode.service;

import java.util.List;

import org.quickbundle.orgauth.rmfunctionnode.dao.IRmFunctionNodeDao;
import org.quickbundle.orgauth.rmfunctionnode.vo.RmFunctionNodeVo;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public interface IRmFunctionNodeService {

    /**
     * 设置数据访问接口
     * 
     * @return
     */
    public IRmFunctionNodeDao getDao();

    /**
     * 获取数据访问接口
     * 
     * @param dao
     */
    public void setDao(IRmFunctionNodeDao dao);

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的id
     */
    public String insert(RmFunctionNodeVo vo);
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(RmFunctionNodeVo[] vos);

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id);

    /**
     * 删除多条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id[]);

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public RmFunctionNodeVo find(String id);

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(RmFunctionNodeVo vo);

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
	public int update(RmFunctionNodeVo[] vos);
	
	/**
	 * 批量保存，没有主键的insert，有主键的update
	 * 
	 * @param vos 更新的VO对象数组
	 * @return new int[2]{insert的记录数, update的记录数}	
	 */
	public int[] insertUpdateBatch(RmFunctionNodeVo[] vos);

    /**
     * 查询总记录数，带查询条件
     * 
     * @param queryCondition 查询条件
     * @return 总记录数
     */
    public int getRecordCount(String queryCondition);
    
    /**
     * 功能: 通过查询条件获得所有的VO对象列表，不带翻页查全部
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<RmFunctionNodeVo> queryByCondition(String queryCondition, String orderStr);
    
    /**
     * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @return 查询到的VO列表
     */
    public List<RmFunctionNodeVo> queryByCondition(String queryCondition, String orderStr, int startIndex, int size);

    /**
     * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @param selectAllClumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<RmFunctionNodeVo> queryByCondition(String queryCondition, String orderStr, int startIndex, int size, boolean selectAllClumn);
    
    /**
     * 判断更新父节点
     * @param totalCode
     */
    public void updateFunctionNode(String totalCode);
    /**
     * 删除后更新父节点为子节点
     * @param totalCode
     */
    public void updateFunctionNodeParty(String totalCode,String id);
    /**
     * 删除当前节点和下级节点
     * @param id
     * @param totalCode
     */
    public void deleteFunctionNode(String id ,String totalCode);
}