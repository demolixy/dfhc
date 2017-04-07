package com.dfhc.pub.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;

import com.dfhc.ISystemConstant;
import com.dfhc.util.ConvertHelper;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
/**
 *  推送队列
 *
 */
public class PushQueue {

	private static final Logger logSocket = RmLogHelper.getLogger("rmSocket");
	//推送数据队列
	private static List<PushDataVo> pushDataVos = new ArrayList<PushDataVo>();
	
	
	//队列锁
	private static ReentrantLock queueLock = new ReentrantLock(true);
	private static PushQueue pushQueue = null;
	private int delay;
	/**
	 * 日志记录guid
	 */
	private long logGuid;
	private PushQueue(){
		//获取延迟时间（单位秒)
		try {
			delay = ConvertHelper.toInt(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_08));
		} catch (Exception e) {
			e.printStackTrace();
			delay = ISystemConstant.DEFAULT_RELAY_TIME;
			logSocket.info("需要推送时延迟的时间 单位秒参数配置不合法:"+StringHelper.exceptionToString(e));
		}
	}
	
	
	
	public static synchronized PushQueue getInstance(){
		if(pushQueue==null){
			pushQueue = new PushQueue();
		}
		return pushQueue;
	}
	/**
	 * 获取推送数据队列
	 * @return
	 */
	public static List<PushDataVo> getPushDataVos() {
		try {			
			queueLock.lock();
			List<PushDataVo> retList = new ArrayList<PushDataVo>();
			retList.addAll(pushDataVos);
			return retList;
		} finally{
			queueLock.unlock();
		}
	}
	/**
	 * 加入队列
	 * @param vo
	 */
	public void put(PushDataVo vo){
		try {			
			queueLock.lock();
			logGuid = System.currentTimeMillis();
			//清空相同查询状态业务数据
			for(int i=pushDataVos.size()-1;i>=0;i--){
				String billStatus = vo.getBillStatus()==null?"":vo.getBillStatus();
				if(billStatus.equals(pushDataVos.get(i).getBillStatus())){
					//并且计划推送时间晚于队列的vo的计划推送时间
					if(vo.getPlannedPushTime()!=null){
						if(DateUtil.truncatedCompareTo(vo.getPlannedPushTime(), pushDataVos.get(i).getPlannedPushTime(), Calendar.MILLISECOND)>0){
							logSocket.info("日志guid:"+logGuid+" 移走队列的数据：" + pushDataVos.get(i).toString());
							pushDataVos.remove(i);
						}else{
							//计划推送时间早于队列的vo的计划推送时间 就不用再加回队列,就是调用get()发送后，不成功。放回队列时，发现队列已经有了比较新推送
							return;
						}
					}else{
						logSocket.info("日志guid:"+logGuid+" "+"移走队列的数据：" + pushDataVos.get(i).toString());
						pushDataVos.remove(i);
					}
				}
			}
			//设置计划推送时间
			if(vo.getPlannedPushTime()==null){
				Date plannedPushTime= DateUtil.getNowDate();
				plannedPushTime = DateUtil.addMilliseconds(plannedPushTime, delay);
				
				vo.setPlannedPushTime(plannedPushTime);
			}
			logSocket.info("日志guid:"+logGuid+" "+"放入队列的数据：" + vo.toString());
			pushDataVos.add(vo);
		} catch (Exception e) {			
			e.printStackTrace();
			logSocket.info(StringHelper.exceptionToString(e));
		} finally{
			queueLock.unlock();
		}
	}
	/**
	 * 获取一个计划推送时间到期或过期vo
	 * @return
	 */
	public PushDataVo getExpire(long logGuid){
		try {
			Date nowTime= DateUtil.getNowDate();
			queueLock.lock();
			for(int i=0;i<pushDataVos.size();i++){
				//是否过期
				if(DateUtil.truncatedCompareTo(nowTime, pushDataVos.get(i).getPlannedPushTime(), Calendar.MILLISECOND)>=0){
					//logSocket.info("日志guid:"+logGuid+" "+"获取队列的计划推送时间过期数据：" + pushDataVos.get(i).toString());
					return pushDataVos.remove(i);
				}
			}
		}finally{
			queueLock.unlock();
		}
		return null;
	}
}

