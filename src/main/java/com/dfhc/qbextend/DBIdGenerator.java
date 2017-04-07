/**
 * 
 */
package com.dfhc.qbextend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.itf.base.IRmIdGenerator;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.pub.service.SequenceService;
import com.dfhc.util.DateUtil;



/**
 * mysql版序号产生器
 * 作者:龙色波
 * 日期:2015-01-21
 */
public class DBIdGenerator implements IRmIdGenerator {
   
   private SequenceService sequenceService ;
   //序列缓存
   private Map<String,List<String>> sequenceCache;
   /* (non-Javadoc)
	 * @see org.quickbundle.itf.base.IRmIdGenerator#init()
	 */
	public void init() {
		sequenceCache = new HashMap<String,List<String>>();
	}

	/* (non-Javadoc)
	 * @see org.quickbundle.itf.base.IRmIdGenerator#requestIdInner(java.lang.String, int)
	 */
	public String[] requestIdInner(String sequenceName, int seqNum) {
        String[] seqNums = null;
        boolean isNeedSequence = false;
        List<String> sequences = null;
        int batchNum = 1000;
        //优化从缓存获取，如果序列个数不足或者缓存里没有，则从一次取1000条,如果seqNum超过1000条，则取seqNum
        synchronized (sequenceCache) {
        	sequences = sequenceCache.get(sequenceName);
        	if(sequences==null){
        		sequences = new ArrayList<String>();
        		sequenceCache.put(sequenceName,sequences);
        		isNeedSequence = true;
        	}else{
        		synchronized (sequences) {
        			if(sequences.size()<seqNum){
            			isNeedSequence = true;
            		}	
				}
        		
        	}
		}
        
        if(!isNeedSequence){
        	synchronized (sequences) {
        		 seqNums= new String[seqNum];
        		 for(int i=0;i<seqNum;i++){
        			 seqNums[i] = sequences.remove(0);
        		 }
        		 return seqNums;
			}
        }else{
	        try {
	        	//重新从数据库获取时间
				Timestamp dbTimestamp = getDBTimestamp();				
				
				if(batchNum<seqNum){
					batchNum = seqNum;
				}
				
				String[] tmpSeqNums=null;
				
				tmpSeqNums = getSequenceService().doGetNextSequenceValue(batchNum, sequenceName, dbTimestamp,dbTimestamp,dbTimestamp);
				//如果当前时间是晚上23点到隔天凌晨1点之间(考虑系统时间误差不超过2小时)，则可能有跨天问题，则需要重新取号，以免出现号码重复
				Integer currentHour = DateUtil.getHour(dbTimestamp);
				if(currentHour>=23||currentHour<=1){
					//重新从数据库获取时间
					dbTimestamp = getDBTimestamp();
					String currDate = DateUtil.getDateStr(dbTimestamp, ISystemConstant.DEFAULT_SHORT_DATE_FMT); 
					for(int i=0;i<batchNum;i++){
						String compDate = tmpSeqNums[i].substring(4, 12);
						if(!currDate.equals(compDate)){						
							try {
								tmpSeqNums[i] = getSequenceService().doGetNextSequenceValue(sequenceName,dbTimestamp,dbTimestamp,dbTimestamp);
							} catch (Exception e) {
								RmProjectHelper.logError("系统异常", e);
								throw new PjException(e);
							}
						}
					}
				}
				
				seqNums = new String[seqNum];
				int oldSequencesSize=0;
				//优先取旧列表				
				synchronized (sequences) {					
	        		 for(int i=0;i<sequences.size()&&i<seqNum ;i++){
	        			 seqNums[i] = sequences.remove(0);
	        			 oldSequencesSize = oldSequencesSize+1;
	        		 }
				
					for(int i=0;i<batchNum;i++){
						if(i+oldSequencesSize<seqNum){
							 seqNums[i+oldSequencesSize] = tmpSeqNums[i];
						}else{				
							 sequences.add(tmpSeqNums[i]);				
						}
					}
				}
				tmpSeqNums =null;
			} catch (Exception e) {
				RmProjectHelper.logError("系统异常", e);
				throw new PjException(e);
			}		
        }
       	return seqNums;
	}
	
	/**
	 * 获取数据库时间戳
	 * @return
	 */
	private Timestamp getDBTimestamp() {
		RmCommonVo vo = RmProjectHelper.getCommonServiceInstance().doQueryForObject("select sysdate() as sysdate");
	    Timestamp t = (Timestamp) vo.get("sysdate");
		return t;
	}
	
	public SequenceService getSequenceService(){
		if(sequenceService==null){
			sequenceService=   (SequenceService) RmBeanFactory.getBean(SequenceService.class.getName());  //得到Service对象,受事务控制
		}
		return sequenceService;
	}

}
