/**
 * 
 */
package com.dfhc.pub.server;

import java.util.Date;

import com.dfhc.util.DateUtil;

/**
 * @author longsebo
 * 推送数据项
 */
public class PushDataItem {
	/**
	 * 客户端标识 :
	 * GATE_01,GATE_02...(门岗大屏排队列表)
	 * WEIGHING_01(磅房安检通过列表 )
	 * WAREHOUSE_01(仓库装车列表)
	 */
	private String clientId;
	/**
	 * 最后一次实际推送时间
	 */
	private Date factPushTime;
	
	
	/**
	 * 失败重发次数
	 */
	private int retryTimes;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public int getRetryTimes() {
		return retryTimes;
	}
	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
	public Date getFactPushTime() {
		return factPushTime;
	}
	public void setFactPushTime(Date factPushTime) {
		this.factPushTime = factPushTime;
	}
	@Override
	public String toString() {
		String dateStr = factPushTime==null?"":DateUtil.getDateStr(factPushTime, "yyyy-MM-dd HH:mm:ss");
		return "PushDataItem [clientId=" + clientId + ", factPushTime="
				+ dateStr + ", retryTimes=" + retryTimes + "]";
	}	
}
