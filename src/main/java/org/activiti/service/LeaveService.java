/**
 * 
 */
package org.activiti.service;

import java.util.Date;
import java.util.List;

import org.activiti.vo.LeaveVo;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.pub.service.ISequenceService;
import com.dfhc.util.ConvertHelper;
import com.dfhc.util.DateUtil;

/**
 * @author longsebo
 * 请假服务
 */
@Service
@Transactional
public class LeaveService {
    final String DATE_FMT="yyyy-MM-dd HH:mm:ss";
    
    /**
     * 得到SequenceService对象
     * 
     * @return Service对象
     */
    private ISequenceService getSequenceService() {
    	return (ISequenceService)RmBeanFactory.getBean(ISystemConstant.SEQUENCE_SERVICE_BEAN);

    }

	 /**
     * 保存实体
	 * @return 
	 * @throws Exception 
     */
    public Long save(LeaveVo entity)  {
        String sql;
        if (entity.getId() == null) {
        	try {
				entity.setId(ConvertHelper.toLong(getSequenceService().getTablePK("LEAVE")));
			} catch (Exception e) {
				e.printStackTrace();
				throw new PjException(e);
			}
            entity.setApplyTime(new Date());
            sql ="INSERT INTO `leave` (ID, PROCESS_INSTANCE_ID, USER_ID, START_TIME, END_TIME, LEAVE_TYPE, REASON, APPLY_TIME, REALITY_START_TIME, REALITY_END_TIME)" +
            		" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] parameters={entity.getId(),entity.getProcessInstanceId(),entity.getUserId(),DateUtil.getDateStr(entity.getStartTime(),DATE_FMT),DateUtil.getDateStr(entity.getEndTime(),DATE_FMT),entity.getLeaveType(),
            		entity.getReason(),DateUtil.getDateStr(entity.getApplyTime(),DATE_FMT),DateUtil.getDateStr(entity.getRealityStartTime(),DATE_FMT),DateUtil.getDateStr(entity.getRealityEndTime(),DATE_FMT)};
			RmProjectHelper.getCommonServiceInstance().doUpdate(sql, parameters);
        }else{
        	sql ="UPDATE `leave` SET PROCESS_INSTANCE_ID = ?, USER_ID = ?, START_TIME = ?, END_TIME = ?, LEAVE_TYPE = ?, REASON = ?, APPLY_TIME = ?, REALITY_START_TIME = ?, REALITY_END_TIME = ? WHERE ID = ?";
            Object[] parameters={entity.getProcessInstanceId(),entity.getUserId(),DateUtil.getDateStr(entity.getStartTime(),DATE_FMT),DateUtil.getDateStr(entity.getEndTime(),DATE_FMT),entity.getLeaveType(),
            		entity.getReason(),DateUtil.getDateStr(entity.getApplyTime(),DATE_FMT),DateUtil.getDateStr(entity.getRealityStartTime(),DATE_FMT),DateUtil.getDateStr(entity.getRealityEndTime(),DATE_FMT),entity.getId()};
			RmProjectHelper.getCommonServiceInstance().doUpdate(sql, parameters);
        }
        return entity.getId();
    }
    /**
     * 删除
     * @param id
     */
    public void delete(Long id) {
        String sql ="delete from `leave` where id=?";
        Object[] parameter={id};
		RmProjectHelper.getCommonServiceInstance().doUpdate(sql,parameter);
    }

    @Transactional(readOnly = true)
    public LeaveVo get(Long id) {
    	String sql = "select * from `leave` where id="+ConvertHelper.toString(id.longValue());
    	
		List<RmCommonVo> commvos = RmProjectHelper.getCommonServiceInstance().doQuery(sql);
		LeaveVo  vo = null;
		
		if(commvos!=null && commvos.size()>0){
			RmCommonVo commvo = commvos.get(0);
			vo = new LeaveVo();
			try {
				vo.setId(ConvertHelper.toLong(commvo.getString("id")));
			} catch (Exception e) {
				e.printStackTrace();
				throw new PjException(e);
			}
			vo.setApplyTime((Date) commvo.get("apply_time"));
			vo.setEndTime((Date) commvo.get("end_time"));
			vo.setLeaveType(commvo.getString("leave_type"));
			vo.setProcessInstanceId(commvo.getString("process_instance_id"));
			vo.setReason(commvo.getString("reason"));
			vo.setRealityStartTime((Date) commvo.get("reality_start_time"));
			vo.setRealityEndTime((Date) commvo.get("reality_end_time"));
			vo.setUserId(commvo.getString("user_id"));
			vo.setStartTime((Date) commvo.get("start_time"));
		}
        return vo;
    }
}
