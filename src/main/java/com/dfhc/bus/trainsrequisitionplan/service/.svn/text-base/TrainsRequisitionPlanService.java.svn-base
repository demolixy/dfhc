/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  TrainsRequisitionPlanService.java
 *
 * 功能描述：  铁运调拨计划服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.trainsrequisitionplan.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.trainsrequisitionplan.ITrainsRequisitionPlanConstants;
import com.dfhc.bus.trainsrequisitionplan.dao.TrainsRequisitionPlanDao;
import com.dfhc.bus.trainsrequisitionplan.vo.TrainsRequisitionPlanVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.service.SequenceService;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 铁运调拨计划服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class TrainsRequisitionPlanService implements ITrainsRequisitionPlanConstants {
	
	@Autowired
	private  PubParamService pubParamService;
    @Autowired
    /**
     * 铁运调拨计划数据访问对象
     */
    private TrainsRequisitionPlanDao trainsRequisitionPlanDao;
    @Autowired
    private LadingBillService  ladingBillService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(TrainsRequisitionPlanVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = trainsRequisitionPlanDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "铁运调拨计划插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(TrainsRequisitionPlanVo[] vos) {
        String[] ids = trainsRequisitionPlanDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "铁运调拨计划插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = trainsRequisitionPlanDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "铁运调拨计划删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = trainsRequisitionPlanDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "铁运调拨计划删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(TrainsRequisitionPlanVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = trainsRequisitionPlanDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "铁运调拨计划更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(TrainsRequisitionPlanVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "铁运调拨计划批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(TrainsRequisitionPlanVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<TrainsRequisitionPlanVo> lInsert = new ArrayList<TrainsRequisitionPlanVo>();
        List<TrainsRequisitionPlanVo> lUpdate = new ArrayList<TrainsRequisitionPlanVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new TrainsRequisitionPlanVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new TrainsRequisitionPlanVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public TrainsRequisitionPlanVo get(String id) {
        TrainsRequisitionPlanVo vo = trainsRequisitionPlanDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = trainsRequisitionPlanDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<TrainsRequisitionPlanVo> list(Map<String, Object> searchPara, String orderStr) {
        return list(searchPara, orderStr, 1, Integer.MAX_VALUE);
    }

    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<TrainsRequisitionPlanVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        return list(searchPara, orderStr, startIndex, size, false);
    }
    
    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @param allColumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<TrainsRequisitionPlanVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<TrainsRequisitionPlanVo> lResult = trainsRequisitionPlanDao.list(searchPara, orderStr, startIndex, size, allColumn);
        return lResult;
    }
    
    /**
     * 按条件搜索，走MyBatis的XML文件的search
     * 
     * @param searchPara 搜索参数的Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<TrainsRequisitionPlanVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<TrainsRequisitionPlanVo> lResult = trainsRequisitionPlanDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(TrainsRequisitionPlanVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
        if(vo.getMakeDate() == null){
           throw new PjException("制表日期为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(TrainsRequisitionPlanVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(vo.getMakeDate() == null){
           throw new PjException("制表日期为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<TrainsRequisitionPlanVo> vos) {
        for(TrainsRequisitionPlanVo vo:vos){
           verifyUpdateVo(vo);
        }
        trainsRequisitionPlanDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<TrainsRequisitionPlanVo> vos) {
        for(TrainsRequisitionPlanVo vo:vos){
           verifyInsertVo(vo);
        }
        trainsRequisitionPlanDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        TrainsRequisitionPlanVo vo = trainsRequisitionPlanDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(trainsRequisitionPlanDao.update(vo)!=1){
           throw new PjException("更新删除标志失败!");
        }
        return 1;
    }

    /**
     * 逻辑删除多条记录
     * @param request 页面请求
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String ids[]) {
        if(ids!=null && ids.length>0 ){
           for(String id:ids){
               deleteLogic(request,id);
           }
           return ids.length;
        }else{
           throw new PjException("ids 为空!");
        }
    }
    
    /**
     * 多条更改状态  
     */
    public void doUpdateListStatus(HttpServletRequest request,String ids[],String type) {
        if(ids!=null && ids.length>0 ){
           for(String id:ids){
        	   
        	   doUpdateStatus(request,id,type);
           }
        }else{
           throw new PjException("ids 为空!");
        }
        
        return ;
    }

    /**
     * 一条更改状态  
     */
    public void doUpdateStatus(HttpServletRequest request,String id,String type) {
    	TrainsRequisitionPlanVo  bean=get(id);
        	   
	   //改成已提交
	   if(ISystemConstant.DICTIONARY_TRAINS_REQUISITION_STATUS_01.equals(type)){
		   if(!ISystemConstant.DICTIONARY_TRAINS_REQUISITION_STATUS_00.equals(bean.getStatus())){
			   //不是未提交不能改
	           throw new PjException("其中有已提交的！");
		   }
	   }
	   
	   bean.setStatus(type);
   
       RmVoHelper.markModifyStamp(request,bean);  //打修改时间,IP戳

       update(bean);
       return ;
    }

    
    
    /**
     * 变车管理 验证 新计划单
     * @param  request
     * @param  planId  计划id
     */
    public void checkNewPlan(HttpServletRequest request,String planId) {

	   //汽运配送计划
	   TrainsRequisitionPlanVo  newTrainsRequisitionPlanVo=get(planId);
	   if(newTrainsRequisitionPlanVo==null){
           throw new PjException("没找到铁路调拨计划");
	   }
	   
	   //02   提交 待分配车辆  
	   String  status=newTrainsRequisitionPlanVo.getStatus();
	   if(!ISystemConstant.DICTIONARY_TRAINS_REQUISITION_STATUS_02.equals(status)  && !ISystemConstant.DICTIONARY_TRAINS_REQUISITION_STATUS_01.equals(status)){
           throw new PjException("新计划单非待分配车辆状态");
	   }
 	   
	   //查询改计划单下的有效提货单
       Long remainderCarNum=newTrainsRequisitionPlanVo.getRemainderCarNum();
       if(Integer.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_0) >= remainderCarNum){
           throw new PjException("新计划单已分配完车辆");
       }
 	   return;
    }

	
    /**
     * 回填铁运计划 剩余车数
     * @param request
     * @param vo
     * @param variable
     */
	public void doTrainsRequisitionUpdateCarNum(Map<String, Object> variable,String orderPlanId,
			String  type,int carNum,int num) {
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("id", orderPlanId);
		
		List<TrainsRequisitionPlanVo> list = list(variable, null);

		TrainsRequisitionPlanVo trainsRequisitionPlanVo = list.get(0);
		if(ILadingBillConstants.FORMULA_ADD.equals(type)){
			//加法
			trainsRequisitionPlanVo.setRemainderCarNum(trainsRequisitionPlanVo.getRemainderCarNum()+carNum);
			trainsRequisitionPlanVo.setRemainderNum(trainsRequisitionPlanVo.getRemainderNum()+num);
		}else{
			//减法
			trainsRequisitionPlanVo.setRemainderCarNum(trainsRequisitionPlanVo.getRemainderCarNum()-carNum);
			trainsRequisitionPlanVo.setRemainderNum(trainsRequisitionPlanVo.getRemainderNum()-num);
		}
		update(trainsRequisitionPlanVo);
	}
    
	
	
    /**
     * 作废提货单 回填铁运计划
     * @param request
     * @param vo
     * @param variable
     */
	public void updateBackfillTrainReqPlan(HttpServletRequest request,
			LadingBillVo vo, Map<String, Object> variable) {
		variable.put("id", vo.getOrderPlanId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		List<TrainsRequisitionPlanVo> list = list(variable, null);
		
		TrainsRequisitionPlanVo trainsRequisitionPlanVo = list.get(0);
		
		trainsRequisitionPlanVo.setRemainderCarNum(trainsRequisitionPlanVo.getRemainderCarNum()+1);
		trainsRequisitionPlanVo.setRemainderNum(trainsRequisitionPlanVo.getRemainderNum()+vo.getNuclearLoad().longValue());
		
		update(trainsRequisitionPlanVo);
	}
	

	
	/**
	 * 上传文件
	 * 
	 * @param file
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public String upload(MultipartFile file, String id, HttpServletRequest request)throws IllegalStateException, IOException {

		String rootPath=null;
		//上传文件
		rootPath=pubParamService.upload(file, id, request);
		
		//客户协同上传图片
		TrainsRequisitionPlanVo trainsRequisitionPlanVo = get(id);
		
		trainsRequisitionPlanVo.setSendCarPlanImage(StringHelper.isEmpty(trainsRequisitionPlanVo.getSendCarPlanImage())?rootPath:trainsRequisitionPlanVo.getSendCarPlanImage() + "," + rootPath);
		RmVoHelper.markModifyStamp(request, trainsRequisitionPlanVo);
		update(trainsRequisitionPlanVo);
		
		return  rootPath;
	}

}
