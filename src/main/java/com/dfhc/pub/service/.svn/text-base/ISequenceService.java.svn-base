/**
 * 
 */
package com.dfhc.pub.service;

import com.dfhc.pub.vo.SequenceVo;

/**
 * @author longsebo
 * 序列服务接口
 */
public interface ISequenceService {
	/**
	 * 批量获取序列
	 * @param size
	 * @param sequenceName
	 * @param args
	 * @return
	 */
	public String[] doGetNextSequenceValue(int size,String sequenceName, Object... args) ;
	/**
	 * 获取单个序列值
	 * @param sequenceName
	 * @param args
	 * @return
	 */
	public String doGetNextSequenceValue(String sequenceName, Object... args);
	/**
	 * 判断序列是否存在
	 * @param sequenceName
	 * @return
	 */
	public boolean isExistsSequence(String sequenceName);
	/**
	 * 插入序列
	 * @param vo
	 */
	public void insert(SequenceVo vo);
	/**
     * 获取表主键
     * @param tableName
     * @return
     */
    public  String getTablePK(String tableName);
}
