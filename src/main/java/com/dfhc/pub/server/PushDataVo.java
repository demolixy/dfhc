/**
 * 
 */
package com.dfhc.pub.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

/**
 * @author longsebo
 * 推送数据vo
 */
public class PushDataVo {
	/**
	 * 推送设备列表(目前一个，可以为多个)
	 */
	private List<PushDataItem> pushDateItems = null;
	/**
	 * 查询的状态
	 */
	private String billStatus;
	/**
	 * 计划推送时间
	 */
	private Date plannedPushTime;	
	/**
	 * 放入推送数据
	 * @param clientIds 客户端id列表，用,分隔
	 * @param billStatus 业务状态
	 */
	public PushDataVo(String clientIds, String billStatus) {
		String[] clientIdArray = StringHelper.splitString(clientIds, ",");
		List<PushDataItem> items = getPushDateItems();
		for(String clientId:clientIdArray){
			PushDataItem item = new PushDataItem();
			item.setClientId(clientId);
			item.setRetryTimes(0);
			item.setFactPushTime(null);
			items.add(item);
		}
		this.billStatus = billStatus;
	}
	public List<PushDataItem> getPushDateItems() {
		if(pushDateItems==null){
			pushDateItems = new ArrayList<PushDataItem>();
		}
		return pushDateItems;
	}
	public void setPushDateItems(List<PushDataItem> pushDateItems) {
		this.pushDateItems = pushDateItems;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public Date getPlannedPushTime() {
		return plannedPushTime;
	}
	public void setPlannedPushTime(Date plannedPushTime) {
		this.plannedPushTime = plannedPushTime;
	}
	@Override
	public String toString() {
		
		String dateStr = plannedPushTime==null?"":DateUtil.getDateStr(plannedPushTime, "yyyy-MM-dd HH:mm:ss");
		return "PushDataVo [pushDateItems=" + pushDateItems + ", billStatus="
				+ billStatus + ", plannedPushTime=" + dateStr + "]";
	}

}
