package com.dfhc.pub.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;

import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
/**
 * Socket队列
 * @author longsebo
 *
 */
public class SocketQueue {

	private static final Logger logSocket = RmLogHelper.getLogger("rmSocket");
	
	private static List<SocketCacheVo> list = new ArrayList<SocketCacheVo>();
	
	public static List<SocketCacheVo> getList() {
		try {
			lock.lock();
			List<SocketCacheVo> newList = new  ArrayList<SocketCacheVo>();
			newList.addAll(list);
			return newList;
		}finally{
			lock.unlock();
		}
	}
	private static SocketQueue socketQueue = null;
	private static ReentrantLock lock = new ReentrantLock(true);
	//记录最后一次记录设备id没找到时间
	private Date lastWriteNoFoundDevLogTime = null;
	//公共参数服务
	private PubParamService pubParamService; 
	private SocketQueue(){
		pubParamService = (PubParamService) RmBeanFactory.getBean("com.dfhc.pub.service.PubParamService");
		
	}
	
	
	public static synchronized SocketQueue getInstance(){
		if(socketQueue==null){
			socketQueue = new SocketQueue();
		}
		return socketQueue;
	}
	
	public void put(long logGuid, SocketCacheVo vo){
		try {
			//logSocket.info("日志guid:"+logGuid+" 放入队里的数据：" + vo.toString());
			lock.lock();
			list.add(vo);
		} finally{
			lock.unlock();
		}
	}
	
	
	/**
	 * 获取长度
	 * @return
	 */
	public int size(){
		try {
			lock.lock();
			return list.size();
		}finally{
			lock.unlock();
		}
	}
	/**
	 * 根据客户端id获取对应socket(同时删除)
	 * @param logGuid 
	 * @param clientId
	 * @return
	 */
	public SocketCacheVo removeByClientId(long logGuid, String clientId) {
		try {
			lock.lock();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getClientId().equals(clientId)){
					logSocket.info("日志guid:"+logGuid+" 返回client id:"+clientId+"，对应socket:"+list.get(i).toString());
					return list.remove(i);
				}
			}
			//如果上次记日志时间为空或者达到或超过一小时，则记录
			if(lastWriteNoFoundDevLogTime==null){
				logSocket.info("日志guid:"+logGuid+" client id:"+clientId+"，对应socket没找到!");
				lastWriteNoFoundDevLogTime = DateUtil.getNowDate();
			}else {
				 if(DateUtil.getMinuteBetweenDate(DateUtil.getNowDate(), lastWriteNoFoundDevLogTime)>=60){
					 logSocket.info("日志guid:"+logGuid+" client id:"+clientId+"，对应socket没找到!");
					 lastWriteNoFoundDevLogTime = DateUtil.getNowDate();
				 }
			}
			
			return null;
		}finally{
			lock.unlock();
		}
	}
	/**
	 * 判断客户端id是否存在
	 * @param logGuid 
	 * @param clientId
	 * @return
	 */
	public boolean isExistsClientId(long logGuid, String clientId) {
		try {
			lock.lock();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getClientId().equals(clientId)){
					logSocket.info("日志guid:"+logGuid+" client id:"+clientId+"已经注册!");
					return true;
				}
			}
			return false;
		}finally{
			lock.unlock();
		}
	}

	/**
	 * 获取需要发送心跳的socket
	 * @return
	 */
	public List<SocketCacheVo> getNeedHeartbeatSockets() {
		Date nowTime= DateUtil.getNowDate();
		int heartbeatCycle=pubParamService.getHeartbeatCycle();
		
		try {
			lock.lock();
			List<SocketCacheVo> retList = new ArrayList<SocketCacheVo>();
			for(int i=list.size()-1;i>=0;i--){
				//是否到心跳时间
				if(list.get(i).getLastHeartbeatTime()!=null){
					if(DateUtil.getSecondBetweenDate(nowTime, list.get(i).getLastHeartbeatTime())>=heartbeatCycle){						
						retList.add(list.remove(i));
					}
				}
			}
			return retList;
		}finally{
			lock.unlock();
		}
	}

	/**
	 * vos列表放回队列
	 * @param logGuid  日志id
	 * @param vos socket列表
	 */
	public void putAll(long logGuid, List<SocketCacheVo> vos) {
		if(CollectionUtils.isEmpty(vos)){
			return;
		}
			
		try {
			lock.lock();
			list.addAll(vos);
		}finally{
			lock.unlock();
		}

		
	}
}

