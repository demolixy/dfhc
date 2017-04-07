/**
 * 
 */
package com.dfhc.pub.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.pub.vo.SequenceVo;
import com.dfhc.util.ConvertHelper;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * @author 龙色波 序列值服务
 */
@Service
// 默认将类中的所有public函数纳入事务管理
public class OracleSequenceService implements ISequenceService{
	private final String TABLE_NAME = "BASE_IMPULSE_SENDER";
	// 日期字段名
	private final String FIELD_DATE = "update_date";
	// 当期值字段名
	private final String FIELD_CURRENT_VALUE = "current_value";
	// 序列值长度
	private final String FIELD_SEQUENCE_LEN = "sequence_len";
	// 当期格式化后序列值
	private final String FIELD_CURRENT_FMT_VALUE = "current_fmt_value";
	// 序列化格式规则
	private final String FIELD_SEQUENCE_RULE = "sequence_rule";
	// 序列名称
	private final String FIELD_SEQUENCE = "sequence_name";
	// 周期类型
	private final String FIELD_CYCLE_TYPE = "cycle_type";
	// 格式序列化值串
	private final String FMT_SEQ_VALUE = "%value";
	// 备注
	private final String FIELD_REMARK = "remark";	
	/**
	 * 获取下一批序列值
	 * 
	 * @param size 序列个数
	 * @param sequenceName
	 *            序列名称
	 * @param args
	 *            参数列表
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW) 
	public String[] doGetNextSequenceValue(int size,String sequenceName, Object... args) {
		return getNextSequenceValue(size, sequenceName, args);
	}
	/**
	 * 获取下一批序列值
	 * 
	 * @param size 序列个数
	 * @param sequenceName
	 *            序列名称
	 * @param args
	 *            参数列表
	 * @return
	 */
	private String[] getNextSequenceValue(int size, String sequenceName,
			Object... args) {
		// 序列记录加锁
		String sql;
		//判断size的值
		if(size<=0){
			throw new PjException("无效获取序列个数:"+size);
		}
		sql = "select * from " + TABLE_NAME + " where " + FIELD_SEQUENCE + "='"
				+ sequenceName + "' for update";
		List<RmCommonVo> vos = RmProjectHelper.getCommonServiceInstance()
				.doQuery(sql);
		if (vos == null || vos.size() == 0) {
			throw new PjException("序列名称:" + sequenceName + "不存在!");
		}
		RmCommonVo vo = vos.get(0);

		Timestamp sqlDate;
		sqlDate = (Timestamp) vo.get(FIELD_DATE);
		Timestamp dbDate;
		dbDate = getDBTimestamp();
		if(sqlDate==null){
			sqlDate = dbDate;
		}
		// 取出周期类型
		String cycleDateType = ISystemConstant.CYCLE_TYPE_NO;
		if (vo.get(FIELD_CYCLE_TYPE) != null) {
			cycleDateType = vo.getString(FIELD_CYCLE_TYPE);
		}
		Integer curVal;
		String fmtCurVal="";
		BigDecimal currentValue;
		// 判断是否有周期
		if (!ISystemConstant.CYCLE_TYPE_NO.equals(cycleDateType)) {
			// 按年
			if (ISystemConstant.CYCLE_TYPE_YEAR.equals(cycleDateType)) {
				Integer curryear = DateUtil.getYear(dbDate);
				Integer dbYear = 0;
				try {
					dbYear = ConvertHelper.toInteger(DateUtil.getDateStr(
							sqlDate, "yyyy"));
				} catch (Exception e) {
					throw new PjException(e.getMessage());
				}
				if (!dbYear.equals(curryear)) {
					// 年份不同,需要从size开始
					curVal = size;
				} else {
					currentValue = (BigDecimal) vo.get(FIELD_CURRENT_VALUE);
					// 当期值加size
					if(currentValue!=null){
						curVal = currentValue.intValue();
					}else{
						curVal = 0;
					}
					if (curVal == null) {
						curVal = 0;
					}
					curVal = curVal + size;
				}
				// 按月
			} else if (ISystemConstant.CYCLE_TYPE_MONTH.equals(cycleDateType)) {
				//改成年和月联合比较 ，以免出现年份不同，月份相同的bug  modify at 2014-12-12
				String currMonth = DateUtil.getDateStr(dbDate,"yyyyMM");
				String dbMonth = "";
				dbMonth = DateUtil.getDateStr(sqlDate, "yyyyMM");
				
				if (!dbMonth.equals(currMonth)) {
					// 月份不同,需要从size开始
					curVal = size;
				} else {
					// 当期值加size
					currentValue = (BigDecimal) vo.get(FIELD_CURRENT_VALUE);
					// 当期值加size
					if(currentValue!=null){
						curVal = currentValue.intValue();
					}else{
						curVal = 0;
					}
					if (curVal == null) {
						curVal = 0;
					}
					curVal = curVal + size;
				}
				// 按天
			} else if (ISystemConstant.CYCLE_TYPE_DAY.equals(cycleDateType)) {
				//改成年，月和日联合比较 ，以免出现年份不同，月份不同，但日相同的bug modify at 2014-12-12
				String currDay = DateUtil.getDateStr(dbDate,"yyyyMMdd");
				String dbDay = "";
				dbDay = DateUtil.getDateStr(sqlDate, "yyyyMMdd");				
				if (!dbDay.equals(currDay)) {
					// 天不同,需要从size开始
					curVal = size;
				} else {
					// 当期值加size
					currentValue = (BigDecimal) vo.get(FIELD_CURRENT_VALUE);
					if(currentValue!=null){
						curVal = currentValue.intValue();
					}else{
						curVal = 0;
					}
					if (curVal == null) {
						curVal = 0;
					}
					curVal = curVal + size;
				}
				// 其他按无周期处理
			} else {
				// 当期值加size
				currentValue = (BigDecimal) vo.get(FIELD_CURRENT_VALUE);
				// 当期值加size
				if(currentValue!=null){
					curVal = currentValue.intValue();
				}else{
					curVal = 0;
				}
				if (curVal == null) {
					curVal = 0;
				}
				curVal = curVal + size;
			}
		} else {
			// 当期值加size
			currentValue = (BigDecimal) vo.get(FIELD_CURRENT_VALUE);
			// 当期值加size
			if(currentValue!=null){
				curVal = currentValue.intValue();
			}else{
				curVal = 0;
			}
			if (curVal == null) {
				curVal = 0;
			}
			curVal = curVal + size;
		}
		String retVals[] = new String[size];
		int startVal = curVal-size+1;
		// 格式当期值为字符串
		BigDecimal sequenceLen;
		sequenceLen = (BigDecimal)vo.get(FIELD_SEQUENCE_LEN);
		Integer valLen = sequenceLen.intValue(); 
		if (valLen == null) {
			throw new PjException("序列名称:" + sequenceName + "的长度尚未定义!");
		}
		// 获取序列格式化规则
		String sequenRule = vo.getString(FIELD_SEQUENCE_RULE);
		if (StringHelper.isEmpty(sequenRule)) {
			throw new PjException("序列名称:" + sequenceName + "的规则尚未定义!");
		}
		String curValFmt = "%0" + ConvertHelper.toString(valLen) + "d";
		
		for(int i=startVal;i<=curVal;i++){			
			String strCurVal = String.format(curValFmt, i);		
			// 将规则中的%value替换为strCurVal,然后再格式化
			sequenRule = StringHelper.replaceAll(vo.getString(FIELD_SEQUENCE_RULE), FMT_SEQ_VALUE,strCurVal).toString();
			fmtCurVal = String.format(sequenRule, args);
			retVals[i-startVal]=fmtCurVal;
		}
		// 更新数据库
		sql = "update " + TABLE_NAME + " set " + FIELD_CURRENT_VALUE + "="
				+ curVal + "," + FIELD_CURRENT_FMT_VALUE + "='" + fmtCurVal
				+ "'," + FIELD_DATE + "=sysDate";

		sql = sql + " where " + FIELD_SEQUENCE + "='" + sequenceName + "'";
		int rowNum = RmProjectHelper.getCommonServiceInstance().doUpdate(sql);
		if (rowNum < 1) {
			throw new PjException("产生序列名称:" + sequenceName
					+ "失败!原因为更新记录数小于1!");
		}		
		return retVals;
	}
	/**
	 * 获取下一个序列值
	 * 
	 * @param sequenceName
	 *            序列名称
	 * @param args
	 *            参数列表
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW) 
	public String doGetNextSequenceValue(String sequenceName, Object... args) {
		String seqValues[];
		seqValues = getNextSequenceValue(1, sequenceName, args);
		if(seqValues!=null && seqValues.length>0){
			return seqValues[0];
		}else{
			return null;
		}
	}
	
	/**
     * 生成随机数
     * 
     */ 
    
    public long doRandomNum(long max ,long min){
    
    	return Math.round(Math.random()*(max-min))+min;
    	
    }
	/**
	 * 获取数据库时间戳
	 * @return
	 */
	private Timestamp getDBTimestamp() {
		RmCommonVo vo = RmProjectHelper.getCommonServiceInstance().doQueryForObject("select sysdate from dual");
	    Timestamp t = (Timestamp) vo.get("sysdate");
		return t;
	}
	 /**
     * 判断序列名称是否存在
     * @param sequenceName
     * @return
     */
    public boolean isExistsSequence(String sequenceName){
    	String sql="select count(*) from "+TABLE_NAME+" where "+FIELD_SEQUENCE+"='"+sequenceName+"'";
    	return RmProjectHelper.getCommonServiceInstance().doQueryForInt(sql)>0;
    }
    /**
     * 插入序列
     * @param vo
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void insert(SequenceVo vo){
    	StringBuilder sqlTemp= new StringBuilder();
    	sqlTemp.append("insert into ").append(TABLE_NAME).append("(")
    	.append(FIELD_SEQUENCE).append(",").append(FIELD_CYCLE_TYPE).append(",").append(FIELD_CURRENT_VALUE).append(",")
    	.append(FIELD_SEQUENCE_RULE).append(",").append(FIELD_SEQUENCE_LEN).append(",").append(FIELD_REMARK).append(",")
    	.append(FIELD_DATE)
    	.append(") values('%s','%s',%d,'%s',%d,'%s',sysdate)");
    	String sql = String.format(sqlTemp.toString(), vo.getSequenceName(),vo.getCycleType(),vo.getCurrentValue(),
    			vo.getSequenceRule(),vo.getSequenceLen(),vo.getRemark());
    	RmProjectHelper.getCommonServiceInstance().doUpdate(sql);
    }
    /**
     * 获取表主键
     * @param tableName
     * @return
     */
    public  String getTablePK(String tableName){
    	java.util.Date data = new java.util.Date();
    	return doGetNextSequenceValue(tableName,data,data,data);
	}
    
}
