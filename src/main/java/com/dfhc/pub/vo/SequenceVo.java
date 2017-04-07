/**
 * 
 */
package com.dfhc.pub.vo;

/**
 * @author longsebo
 * 序列vo
 */
public class SequenceVo {
	// 日期字段名
	private  String updateDate;
	// 当期值字段名
	private  int currentValue;
	// 序列值长度
	private  int sequenceLen;
	// 当期格式化后序列值
	private  String currentFmtValue;
	// 序列化格式规则
	private  String sequenceRule;
	// 序列名称
	private  String sequenceName;
	// 周期类型
	private  String cycleType;
	// 备注
	private  String remark;
	
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}
	public int getSequenceLen() {
		return sequenceLen;
	}
	public void setSequenceLen(int sequenceLen) {
		this.sequenceLen = sequenceLen;
	}
	public String getCurrentFmtValue() {
		return currentFmtValue;
	}
	public void setCurrentFmtValue(String currentFmtValue) {
		this.currentFmtValue = currentFmtValue;
	}
	public String getSequenceRule() {
		return sequenceRule;
	}
	public void setSequenceRule(String sequenceRule) {
		this.sequenceRule = sequenceRule;
	}
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public String getCycleType() {
		return cycleType;
	}
	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
