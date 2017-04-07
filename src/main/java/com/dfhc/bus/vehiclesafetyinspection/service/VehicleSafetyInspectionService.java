/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  VehicleSafetyInspectionService.java
 *
 * 功能描述：  车辆安全检查服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.vehiclesafetyinspection.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.vehiclesafetyinspection.IVehicleSafetyInspectionConstants;
import com.dfhc.bus.vehiclesafetyinspection.dao.VehicleSafetyInspectionDao;
import com.dfhc.bus.vehiclesafetyinspection.vo.VehicleSafetyInspectionVo;
import com.dfhc.util.StringHelper;
/**
 * 车辆安全检查服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class VehicleSafetyInspectionService implements IVehicleSafetyInspectionConstants {

    @Autowired
    /**
     * 车辆安全检查数据访问对象
     */
    private VehicleSafetyInspectionDao vehicleSafetyInspectionDao;
    @Autowired
    private LadingBillService ladingBillService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(VehicleSafetyInspectionVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = vehicleSafetyInspectionDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "车辆安全检查插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(VehicleSafetyInspectionVo[] vos) {
        String[] ids = vehicleSafetyInspectionDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "车辆安全检查插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = vehicleSafetyInspectionDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "车辆安全检查删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = vehicleSafetyInspectionDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "车辆安全检查删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(VehicleSafetyInspectionVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = vehicleSafetyInspectionDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "车辆安全检查更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(VehicleSafetyInspectionVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "车辆安全检查批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(VehicleSafetyInspectionVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<VehicleSafetyInspectionVo> lInsert = new ArrayList<VehicleSafetyInspectionVo>();
        List<VehicleSafetyInspectionVo> lUpdate = new ArrayList<VehicleSafetyInspectionVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new VehicleSafetyInspectionVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new VehicleSafetyInspectionVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public VehicleSafetyInspectionVo get(String id) {
        VehicleSafetyInspectionVo vo = vehicleSafetyInspectionDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = vehicleSafetyInspectionDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<VehicleSafetyInspectionVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<VehicleSafetyInspectionVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<VehicleSafetyInspectionVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<VehicleSafetyInspectionVo> lResult = vehicleSafetyInspectionDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<VehicleSafetyInspectionVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<VehicleSafetyInspectionVo> lResult = vehicleSafetyInspectionDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(VehicleSafetyInspectionVo vo) {
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
    private void verifyInsertVo(VehicleSafetyInspectionVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<VehicleSafetyInspectionVo> vos) {
        for(VehicleSafetyInspectionVo vo:vos){
           verifyUpdateVo(vo);
        }
        vehicleSafetyInspectionDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<VehicleSafetyInspectionVo> vos) {
        for(VehicleSafetyInspectionVo vo:vos){
           verifyInsertVo(vo);
        }
        vehicleSafetyInspectionDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        VehicleSafetyInspectionVo vo = vehicleSafetyInspectionDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(vehicleSafetyInspectionDao.update(vo)!=1){
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
     * 车辆检查信息展示
     * @param request
     * @param checkCarId 安全检查id
     * @return
     */
	public Map<String, Object> getCheckInfo(HttpServletRequest request,
			String checkCarId) {
		
		VehicleSafetyInspectionVo vehicleSafetyInspectionVo = get(checkCarId);
		
		Map<String, Object> variable = new HashMap<String, Object>();

		if(null == vehicleSafetyInspectionVo){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "没有找到记录！车辆安全检查id ： " + checkCarId);
			
			return variable;
		}
		
		if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vehicleSafetyInspectionVo.getDelete_flag())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "记录已被删除！车辆安全检查id ： " + checkCarId);
			
			return variable;
		}
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
		variable.put(ISystemConstant.AJAX_BEAN, vehicleSafetyInspectionVo);
		
		return variable;
	}

	/**
	 * 车辆安全检查
	 * @param request
	 * @param vo
	 * @return
	 */
	public void insertVo(HttpServletRequest request,
			VehicleSafetyInspectionVo vo) {
		
		
		RmVoHelper.markCreateStamp(request, vo);
		insert(vo);
		
		LadingBillVo billVo = ladingBillService.get(vo.getLadingBillId());
		
		if(ISystemConstant.DICTIONARY_CHECK_STATUS_1.equals(vo.getStatus())){//安检通过
			billVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05);
		}else if(ISystemConstant.DICTIONARY_CHECK_STATUS_0.equals(vo.getStatus())){//安检不通过
			
			//TODO  此处状态需要确认
			billVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07);
		}
		
		ladingBillService.update(billVo);
		
	}
	/**
	 * 磅端设置安检通过
	 * @param request
	 * @param billid  提货单号
	 * @param status  安检结果状态
	 * @return
	 */
	public Map<String, Object> updateLadingPass(HttpServletRequest request,
			String billid, String status) {
		Map<String,Object> result = new HashMap<String,Object>();
		if(StringHelper.isEmpty(billid)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "缺少订单号!");
			return result;
		}
		if(StringHelper.isEmpty(status)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "缺少状态!");
			return result;
		}
		//检查状态是否有效
		if(!ISystemConstant.DICTIONARY_CHECK_STATUS_0.equals(status) && !ISystemConstant.DICTIONARY_CHECK_STATUS_1.equals(status)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "无效的状态值:"+status);
			return result;
		}
		Map<String, Object> searchPara = new HashMap<String,Object>();
		searchPara.put("ladingBillCode", billid);
		//检查提货单是否存在
		List<VehicleSafetyInspectionVo> vos = list(searchPara, null);
		if(CollectionUtils.isEmpty(vos)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "订单号(提货单号):"+billid+"不存在!");
			return result;
		}
		VehicleSafetyInspectionVo vo = vos.get(0);
		vo.setStatus(status);
		RmVoHelper.markModifyStamp(request, vo);
		vehicleSafetyInspectionDao.update(vo);
		result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);		
		return result;
	}
	 /**
	    * 通过map查询单条vo
	    * @param params 查询条件
	    * @param flag  删除标志
	    * @return
	    */
	   public VehicleSafetyInspectionVo getVo(Map<String, Object> params, boolean flag){
	        List<VehicleSafetyInspectionVo> list = list(params, null, 1, -1, flag);
		 
	        return CollectionUtils.isEmpty(list) ? null : list.get(0);
	   }

}
