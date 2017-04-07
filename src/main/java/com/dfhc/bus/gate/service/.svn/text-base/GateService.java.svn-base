/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  GateService.java
 *
 * 功能描述：  门岗服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.gate.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctc.wstx.util.DataUtil;
import com.dfhc.bus.gate.IGateConstants;
import com.dfhc.bus.gate.dao.GateDao;
import com.dfhc.bus.gate.vo.GateVo;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.rownumber.service.RowNumberService;
import com.dfhc.bus.rownumber.vo.RowNumberVo;
import com.dfhc.bus.weighingbill.service.WeighingBillService;
import com.dfhc.bus.weighingbill.vo.WeighingBillVo;
import com.dfhc.PjException;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 门岗服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class GateService implements IGateConstants {

    @Autowired
    /**
     * 门岗数据访问对象
     */
    private GateDao gateDao;
    @Autowired
    private RowNumberService rowNumberService;
    @Autowired
    private LadingBillService ladingBillService;

    @Autowired
    private WeighingBillService weighingBillService;
    
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(GateVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = gateDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "门岗插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(GateVo[] vos) {
        String[] ids = gateDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "门岗插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = gateDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "门岗删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = gateDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "门岗删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(GateVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = gateDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "门岗更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(GateVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "门岗批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(GateVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<GateVo> lInsert = new ArrayList<GateVo>();
        List<GateVo> lUpdate = new ArrayList<GateVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new GateVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new GateVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public GateVo get(String id) {
        GateVo vo = gateDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = gateDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<GateVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<GateVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<GateVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<GateVo> lResult = gateDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<GateVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<GateVo> lResult = gateDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(GateVo vo) {
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
    private void verifyInsertVo(GateVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<GateVo> vos) {
        for(GateVo vo:vos){
           verifyUpdateVo(vo);
        }
        gateDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<GateVo> vos) {
        for(GateVo vo:vos){
           verifyInsertVo(vo);
        }
        gateDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        GateVo vo = gateDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(gateDao.update(vo)!=1){
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

    public GateVo getGateVo(Map<String, Object> variable, boolean flag){
    	
    	List<GateVo> list = list(variable, null, 1, -1, flag);
    	
    	return CollectionUtils.isEmpty(list) ? null : list.get(0);
    	
    }
    public GateVo getGateVo(Map<String, Object> variable){
    	
    	return getGateVo(variable, false);
    	
    }
    
    /**
     * 门岗 暂停 或启用
     * @param request
     * @param flag 01暂停  02启用
     * @return
     */
    public Map<String, Object> updateSuspendOrStart(HttpServletRequest request, String flag){
    	Map<String, Object> variable = new HashMap<String, Object>();
    	
    	variable.put("isEnabled", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
    	variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	
    	GateVo gateVo = getGateVo(variable);
    	variable.clear();
    	
    	//验证
		variable = checkedStatus(flag, gateVo, variable);
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
			return variable;
		}
		
		Date nowDate = DateUtil.toSqlDate(DateUtil.getNowDate());
    	gateVo.setEndTime(nowDate);
    	gateVo.setIsEnabled(ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	
    	GateVo vo = createNewGateData(flag, nowDate);
    	
    	RmVoHelper.markModifyStamp(request, gateVo);
    	update(gateVo);
    	
    	RmVoHelper.markCreateStamp(request, vo);
    	insert(vo);
    	
    	variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    	variable.put(ISystemConstant.AJAX_MESSAGE, ISystemConstant.DICTIONARY_GATE_STATUS_01.equals(flag) ? "暂停成功！" : "启用成功！");
    	
    	return variable;
    	
    }

    /**
     * 创建门岗对象
     * @param flag 状态
     * @param nowDate 日期
     * @return
     */
    private GateVo createNewGateData(String flag, Date nowDate) {
    	GateVo vo = new GateVo();
    	vo.setStatus(flag);
    	vo.setStartTime(nowDate);
    	vo.setIsEnabled(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
		return vo;
	}

	/**
     * 验证
     * @param flag
     * @param gateVo
     * @param variable
     * @return
     */
	private Map<String, Object> checkedStatus(String flag, GateVo gateVo,
			Map<String, Object> variable) {
		if(gateVo.getStatus().equals(flag)){
			if(ISystemConstant.DICTIONARY_GATE_STATUS_01.equals(flag)){//暂停
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "门岗已暂停，无需再次暂停！");
				
			}else if(ISystemConstant.DICTIONARY_GATE_STATUS_02.equals(flag)){
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "门岗已启用，无需再次启用！");
			}else{
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "无法确定是启动还是暂停！");
			}
		}
		
		return variable;
	}

	/**
	 * 门岗对车辆出入厂操作
	 * @param request
	 * @param truckNumber 车牌号
	 * @param operationCode 1.	01入厂放行、02. 入厂不放行、03. 入厂弃号【注：若果接口四返回可以入厂，操作状态为放行。】
							2.	11.出厂放行、12. 出厂不放行【注：如果接口四返回可以出厂，操作状态为放行。】
	 * @return
	 */
	public Map<String, Object> updateGateOperation(HttpServletRequest request,
			String truckNumber, String operationCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(ISystemConstant.INTERFACE_OPERATION_CODE_01.equals(operationCode)){//入场放行
			
			return rowNumberService.carTicket(request, result, truckNumber);
			
		}else if(ISystemConstant.INTERFACE_OPERATION_CODE_02.equals(operationCode)){
			//入场不放行
			
		}else if(ISystemConstant.INTERFACE_OPERATION_CODE_03.equals(operationCode)){
			//入场弃号
			return rowNumberService.giveUpSequence(request, truckNumber, result);
		}else if(ISystemConstant.INTERFACE_OPERATION_CODE_11.equals(operationCode)){
			//出厂放行
			return ladingBillService.leaveFacetory(request, result, truckNumber);
		}else if(ISystemConstant.INTERFACE_OPERATION_CODE_12.equals(operationCode)){
			//出厂不放行
			
		}
		
		return null;
	}

	/**
     * 车辆是否可以出入厂
     * @param request
     * @param truckNumber 车牌号
     * @param type 入厂0，出厂1
     * @return
     */
	public Map<String, Object> whetherGoOutAndComeIn(
			HttpServletRequest request, String truckNumber, String type) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(type)){//入场
			variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_03);
			variable.put("truckNo", truckNumber);
			
			List<RowNumberVo> list = rowNumberService.list(variable, null);	
			variable.clear();
			
			if(CollectionUtils.isEmpty(list)){
				
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "车牌号为:"+truckNumber+",排队表状态为当前入厂(03)在当天排队表数据不存在!");
				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			}else{
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
			}
			
		}else if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(type)){//出厂
			/*
			 * 先看提货单是否需要过磅，如果需要过磅，则查询过磅单是否二次过磅完成，如果完成则可以出厂，否则不能出厂
			 * 如果不需要过磅，则判断是否装货完成，如果是则可以出厂，否则不能出厂 
			 */
			//取消日期过滤，可能有特殊情况 remove by longsebo 2017-04-01
			//variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
			variable.put("gateCar", "gateCar");
			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
			variable.put("ladingBillStatuses", StringHelper.arrayToStr(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06, ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07));
			variable.put("licensePlate", truckNumber);
			List<LadingBillVo> list = ladingBillService.list(variable, null);
			
			variable.clear();
			
			if(CollectionUtils.isEmpty(list)){//不允许出厂
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "车牌号为:"+truckNumber+",装车通知单状态为装货完成(06)或装货失败(07)在提货单不存在!");
				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
				return variable;
						
			}
			
			LadingBillVo ladingBillVo = list.get(0);
			
			if(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_07.equals(ladingBillVo.getLadingBillStatus())){//装货失败 直接出厂
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
				return variable;
			}
			
			if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(ladingBillVo.getIsPound())){
				//需要过磅 查询榜单
				
				variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
				variable.put("ladingBillId", ladingBillVo.getId());
				variable.put("status", ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS_02);
				
				List<WeighingBillVo> list2 = weighingBillService.list(variable, null);
				variable.clear();
				
				if(CollectionUtils.isEmpty(list2)){
					//找不到榜单 不允许出厂
					variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
					variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "车牌号为:"+truckNumber+",提货单id:"+ladingBillVo.getId()+"，磅单状态为二次过磅(02)的磅单记录找不到!");
					variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
					
				}else{
					variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
					variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
					variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
				}
				
			}else{
				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
			}
			
			
//			variable.put("rowDate", DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd"));
//			variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//			variable.put("status", ISystemConstant.DICTIONARY_ROW_STATUS_05);
//			variable.put("truckNo", truckNumber);
//			variable.put("weighingBill", "weighingBill");
//			
//			List<LadingBillVo> list = ladingBillService.list(variable, null);
//			variable.clear();
//			
//			
//			if(CollectionUtils.isEmpty(list)){
//				
//				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
//				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
//				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//			}else{
//				variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
//				variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "");
//				variable.put("isallow", ISystemConstant.DICTIONARY_RM_YES_NOT_1);
//			}
			
			
		}else{
			variable.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			variable.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "参数错误！非法参数 type:"+type);
		}
			
		
		return variable;
	}

	
}
