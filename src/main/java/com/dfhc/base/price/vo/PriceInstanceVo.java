/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  PriceVo.java
 *
 * 功能描述：  价格管理值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.price.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 价格管理值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class PriceInstanceVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    /**
     * processDefinitionId 表示：流程定义id
     */
    private String processDefinitionId; 
    /**
     * processInstanceId 表示：流程实例id
     */
    private String processInstanceId;
    /**
     * doublePara 表示：流程定义id和流程实例id拼接的字符串
     */
    private String doubleParameter;
    /**
     * initiator 表示：申请人
     */
    private String initiator;
    /**
     * initiatorTime 表示：申请时间
     */
    private String initiatorTime;
    /**
     * generalBusinessProcessId 表示：通用表id
     */
    private String generalBusinessProcessId;
    /**
     * taskId 表示：任务id
     */
    private String taskId;
    /**
     * taskName 表示：任务名称
     */
    private String taskName;
    /**
     * taskCreateTime 表示：任务创建时间
     */
    private String taskCreateTime;
    /**
     * assignee 表示：是否签收
     */
    private String assignee; 
    /**
     * priceApplyNum 表示：价格申请单号
     */
    private String priceApplyNum;
    
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getDoubleParameter() {
		return doubleParameter;
	}
	public void setDoubleParameter(String doubleParameter) {
		this.doubleParameter = doubleParameter;
	}
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	public String getInitiatorTime() {
		return initiatorTime;
	}
	public void setInitiatorTime(String initiatorTime) {
		this.initiatorTime = initiatorTime;
	}
	public String getGeneralBusinessProcessId() {
		return generalBusinessProcessId;
	}
	public void setGeneralBusinessProcessId(String generalBusinessProcessId) {
		this.generalBusinessProcessId = generalBusinessProcessId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskCreateTime() {
		return taskCreateTime;
	}
	public void setTaskCreateTime(String taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getPriceApplyNum() {
		return priceApplyNum;
	}
	public void setPriceApplyNum(String priceApplyNum) {
		this.priceApplyNum = priceApplyNum;
	}
		
}
