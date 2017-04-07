/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LadingBillChangeService.java
 *
 * 功能描述：  司机提货单变车记录服务
 * 
 * 版本历史：
 * 
 * 2017-01-18   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.ladingbillchange.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.bus.cardistributionplan.service.CarDistributionPlanService;
import com.dfhc.bus.carrequisitionplan.service.CarRequisitionPlanService;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.ladingbillchange.ILadingBillChangeConstants;
import com.dfhc.bus.ladingbillchange.dao.LadingBillChangeDao;
import com.dfhc.bus.ladingbillchange.vo.LadingBillChangeVo;
import com.dfhc.bus.trainsrequisitionplan.service.TrainsRequisitionPlanService;
import com.dfhc.PjException;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
import org.apache.commons.collections.CollectionUtils;
/**
 * 司机提货单变车记录服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class LadingBillChangeService implements ILadingBillChangeConstants {

	@Autowired
	private CarDistributionPlanService  carDistributionPlanService;
    @Autowired
    /**
     * 司机提货单变车记录数据访问对象
     */
    private LadingBillChangeDao ladingBillChangeDao;

    @Autowired
    private  LadingBillService   ladingBillService;
    @Autowired
    private  CarRequisitionPlanService  carRequisitionPlanService;
    @Autowired
    private TrainsRequisitionPlanService trainsRequisitionPlanService;
    
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(LadingBillChangeVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = ladingBillChangeDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单变车记录插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(LadingBillChangeVo[] vos) {
        String[] ids = ladingBillChangeDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单变车记录插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = ladingBillChangeDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单变车记录删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = ladingBillChangeDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单变车记录删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(LadingBillChangeVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = ladingBillChangeDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单变车记录更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(LadingBillChangeVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "司机提货单变车记录批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(LadingBillChangeVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<LadingBillChangeVo> lInsert = new ArrayList<LadingBillChangeVo>();
        List<LadingBillChangeVo> lUpdate = new ArrayList<LadingBillChangeVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new LadingBillChangeVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new LadingBillChangeVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public LadingBillChangeVo get(String id) {
        LadingBillChangeVo vo = ladingBillChangeDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = ladingBillChangeDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<LadingBillChangeVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<LadingBillChangeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<LadingBillChangeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<LadingBillChangeVo> lResult = ladingBillChangeDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<LadingBillChangeVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<LadingBillChangeVo> lResult = ladingBillChangeDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(LadingBillChangeVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(LadingBillChangeVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<LadingBillChangeVo> vos) {
        for(LadingBillChangeVo vo:vos){
           verifyUpdateVo(vo);
        }
        ladingBillChangeDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<LadingBillChangeVo> vos) {
        for(LadingBillChangeVo vo:vos){
           verifyInsertVo(vo);
        }
        ladingBillChangeDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        LadingBillChangeVo vo = ladingBillChangeDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(ladingBillChangeDao.update(vo)!=1){
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
    * 通过map查询单条vo
    * @param params 查询条件
    * @param flag  删除标志
    * @return
    */
   public LadingBillChangeVo getVo(Map<String, Object> params, boolean flag){
        List<LadingBillChangeVo> list = list(params, null, 1, -1, flag);
	 
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
   }
   

   /**
    * 变车管理   变车
    */
   public void insert(HttpServletRequest request,LadingBillChangeVo newVo,RmUserVo userVo) {

	   //旧提货单id
	   String  ladingBillIdString=newVo.getOldLadingBillId();
	   //旧提货单
	   LadingBillVo  oldLadingBillVo=ladingBillService.get(ladingBillIdString);
	   
	   //验证旧的提货单
	   ladingBillService.checkOldLadingBill(request, oldLadingBillVo);
	   //验证新的计划单
	   checkNewLadingBill(request, oldLadingBillVo,newVo);
	   
	   //插入相关的表
	   doLadingBillChange(request, oldLadingBillVo,newVo,userVo);
	   
	   return;
   }

   
   
   
   /**
    * 验证 新计划单
    */
   public void checkNewLadingBill(HttpServletRequest request,LadingBillVo oldLadingBillVo,LadingBillChangeVo  newVo) {

	   //新的单据类型
	   String newBillType=newVo.getNewBillType();
	   //新计划id
	   String planId=newVo.getNewPlanId();
	   
	   if(oldLadingBillVo.getOrderPlanId().equals(planId)){
           throw new PjException("新计划跟提货单对应的计划不能相同");
	   }

	   if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(newBillType)){
		   //销售订单
           throw new PjException("新计划单据类型不能是销售订单类型");
		   
	   }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(newBillType)){
		   //汽运配送计划
		   carDistributionPlanService.checkNewPlan(request, planId);
		   
	   }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(newBillType)){
		   //汽运调拨计划
		   carRequisitionPlanService.checkNewPlan(request, planId);
		   
	   }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(newBillType)){
		   //铁路调拨计划
		   trainsRequisitionPlanService.checkNewPlan(request, planId);
	   }
	   
	   
	   return;
   }
   
   /**
    * 变车管理的相关操作
    */
   public void doLadingBillChange(HttpServletRequest request,LadingBillVo oldLadingBillVo,LadingBillChangeVo  newVo,RmUserVo userVo) {
	   //变更表字段补全
	   newVo.setOldPlanId(oldLadingBillVo.getOrderPlanId());
	   newVo.setOldOrderPlanCode(oldLadingBillVo.getOrderPlanCode());
	   newVo.setOldBillType(oldLadingBillVo.getBillType());
	   newVo.setModifyApplyPerson(userVo.getName());
	   newVo.setModifyOperator(userVo.getName());
	   newVo.setModifyOperatorId(userVo.getId());
	   newVo.setChangeTime(DateUtil.toSqlDate(DateUtil.getNowDate()));

	   //新的单据类型
	   String newBillType=newVo.getNewBillType();
	   //新计划id
	   String planId=newVo.getNewPlanId();

	   //把该提货单改到新计划下
	   if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(newBillType)){
		   //汽运配送计划
		   oldLadingBillVo=ladingBillService.getNewLadingBillVoForD(request, oldLadingBillVo, planId); 
	   }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(newBillType)){
		   //汽运调拨计划
		   oldLadingBillVo=ladingBillService.getNewLadingBillVoForR(request, oldLadingBillVo, planId); 
	   }else  if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(newBillType)){
		   //铁路调拨计划
		   oldLadingBillVo=ladingBillService.getNewLadingBillVoForT(request, oldLadingBillVo, planId); 
	   }
	   
		//修改旧计划对应的剩余车数
		Map<String, Object>  variable=new HashMap<String, Object>();
		carDistributionPlanService.doTrainsRequisitionUpdateCarNum(variable, oldLadingBillVo.getOrderPlanId(), ILadingBillConstants.FORMULA_ADD, Integer.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_1), oldLadingBillVo.getNuclearLoad().intValue());
	   
	   //更改提货单的信息
       RmVoHelper.markModifyStamp(request,oldLadingBillVo);  //打修改时间,IP戳
	   ladingBillService.update(oldLadingBillVo);
	   
	   //插入提货单变化记录
       RmVoHelper.markCreateStamp(request,newVo);  //打创建时间,IP戳
	   insert(newVo);
	   return;
   }

   
   
   
   /**
    * 查询便车管理列表时   的查询条件
    */
   public  void   getSearchPara( HttpServletRequest request,Map<String,Object> searchPara){
   	
	   String   flagLuy=request.getParameter("flagLuy");
	   if(!StringHelper.isEmpty(flagLuy)){
		   if("doList".equals(flagLuy)){
			   searchPara.put("oldLadingBillIdName", "oldLadingBillIdName");
		   }
	   }
	   
   }
}
