package com.dfhc.pub.server;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.bus.rownumber.service.RowNumberService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
/**
 *  推送数据线程  定时检查推送队列是否有数据，且时间已到(并且有socket可用)则发送
 *  
 */ 
public class PushThread extends Thread {

	private static final Logger logSocket = RmLogHelper.getLogger("rmSocket");
	
	private RowNumberService rowNumberService;
	
	private Object waitObj = new Object();

	private SocketQueue socketQueue;
	/**
	 * 日志记录guid
	 */
	private long logGuid;
	
	@Override
	public void run() {
		//
		rowNumberService = (RowNumberService) RmBeanFactory.getBean("com.dfhc.bus.rownumber.service.RowNumberService");
		socketQueue = SocketQueue.getInstance();
		PushQueue pushQueue = PushQueue.getInstance();
		while(true){
			//睡眠1秒（不用sleep，减少cpu时间)
			synchronized (waitObj) {				
			    try {
					waitObj.wait(1000);					
				} catch (InterruptedException e) {
					e.printStackTrace();
					logSocket.info(StringHelper.exceptionToString(e));
					break;
				}	
			}
			//检查Socket队列是否有socket
			if(socketQueue.size()>0){
				logGuid = System.currentTimeMillis();
				//提取一个到期或过期推送数据
				PushDataVo vo = null;
				try{
					vo = pushQueue.getExpire(logGuid);
					if(vo!=null && vo.getPushDateItems().size()>0){
						String packingData = null;
						byte[] jsonBytes=null;						
						for(int i=vo.getPushDateItems().size()-1;i>=0;i--){
							PushDataItem item = vo.getPushDateItems().get(i);
							SocketCacheVo socketVo = socketQueue.removeByClientId(logGuid,item.getClientId());
							if(socketVo!=null){
								try {
									if(jsonBytes==null){
										packingData = SocketCommunicationUtil.packingData(JSONObject.toJSONString(rowNumberService.getCarSequenceList(null, vo.getBillStatus())));
										try {
											jsonBytes = packingData.getBytes(ISystemConstant.ENCODE_UTF_8);
										} catch (UnsupportedEncodingException e1) {						
											e1.printStackTrace();
											logSocket.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e1));
											break;
										}
									}
									pushData(jsonBytes,item,socketVo);
									//发送成功，从列表删除
									vo.getPushDateItems().remove(i);
									logSocket.info("日志guid:"+logGuid+" "+"设备信息:"+item.toString()+"数据推送成功：" + packingData);
								} catch (Exception e) {
									//e.printStackTrace();
								}
							}
						}
						//一个推送完，清空
						jsonBytes = null;
					}
				}finally{
					if(vo!=null){
						//如果尚有未发送成功，则放回队列
						if(!vo.getPushDateItems().isEmpty()){
							pushQueue.put(vo);
						}
					}
				}
				//获取需要发送心跳包的socket
				sendHeartbeatPack();
			}
		}
		
	}
	/**
	 * 发送心跳包
	 */
	private void sendHeartbeatPack() {
		List<SocketCacheVo> vos = null;
		
		vos = socketQueue.getNeedHeartbeatSockets();
		if(!CollectionUtils.isEmpty(vos)){
			//logSocket.info("日志guid:"+logGuid+" 需要发送心跳包个数:"+vos.size()+",发送数据:"+vos.toString());
			try {
				byte[] jsonBytes = SocketCommunicationUtil.packingHeartbeatData().getBytes(ISystemConstant.ENCODE_UTF_8);
				for(SocketCacheVo vo:vos){
					pushHeartbeatData(jsonBytes,vo);
				}
			} catch (UnsupportedEncodingException e) {
				logSocket.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e));
			}
		}
		
	}
	/**
	 * 推送心跳包
	 * @param jsonBytes  心跳包数据
	 * @param vo  socket
	 * @throws Exception 
	 */
	private void pushHeartbeatData(byte[] jsonBytes, SocketCacheVo socketVo)  {
		OutputStream out = socketVo.getOut();
		
		try {
//			logSocket.info("日志guid:"+logGuid+" "+"发送数据长度为:"+jsonBytes.length);
			out.write(jsonBytes);
			out.flush();
			//socketVo放回队列之前，检查是否存在相同的设备id,存在就不用放回队列，直接关闭
			if(!socketQueue.isExistsClientId(logGuid, socketVo.getClientId())){
				//更新最后一次心跳时间
				socketVo.setLastHeartbeatTime(DateUtil.getNowDate());
				socketQueue.put(logGuid,socketVo);		
			}else{
				logSocket.info("日志guid:"+logGuid+" socket放回队列前，发现已有相同的client id, 关闭 client id:"+socketVo.getClientId()+"，对应socket:"+socketVo.toString());
				SocketCommunicationUtil.close(logGuid,socketVo.getSocket());
			}
		} catch (Exception e) {
			SocketCommunicationUtil.close(logGuid,socketVo.getSocket());
			logSocket.info("日志guid:"+logGuid+" 心跳数据推送异常："+StringHelper.exceptionToString(e));			
		}
		
	}

	/**
	 * 推送数据
	 * @param jsonBytes 
	 * @param item
	 * @param socketVo
	 * @throws Exception 
	 */
	private void pushData(byte[] jsonBytes, PushDataItem item, SocketCacheVo socketVo) throws Exception {
		OutputStream out = socketVo.getOut();
		
		try {
			logSocket.info("日志guid:"+logGuid+" "+"发送数据长度为:"+jsonBytes.length);
			out.write(jsonBytes);
			out.flush();
			//socketVo放回队列之前，检查是否存在相同的设备id,存在就不用放回队列，直接关闭
			if(!socketQueue.isExistsClientId(logGuid, socketVo.getClientId())){
				//更新最后一次心跳时间
				socketVo.setLastHeartbeatTime(DateUtil.getNowDate());
				socketQueue.put(logGuid,socketVo);		
			}else{
				logSocket.info("日志guid:"+logGuid+" socket放回队列前，发现已有相同的client id, 关闭 client id:"+socketVo.getClientId()+"，对应socket:"+socketVo.toString());
				SocketCommunicationUtil.close(logGuid,socketVo.getSocket());
			}
		} catch (Exception e) {
			SocketCommunicationUtil.close(logGuid,socketVo.getSocket());
			//累加重试次数
			item.setRetryTimes(item.getRetryTimes()+1);
			item.setFactPushTime(DateUtil.getNowDate());
			logSocket.info("日志guid:"+logGuid+" "+"设备信息:"+item.toString()+"数据推送异常："+StringHelper.exceptionToString(e));
			throw e;
		}
		
	}
	
}
