/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  WeighingBillService.java
 *
 * 功能描述：  过磅单服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.weighingbill.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.order.vo.OrderVo;
import com.dfhc.bus.rownumber.service.RowNumberService;
import com.dfhc.bus.rownumber.vo.RowNumberVo;
import com.dfhc.bus.weighingbill.IWeighingBillConstants;
import com.dfhc.bus.weighingbill.dao.WeighingBillDao;
import com.dfhc.bus.weighingbill.vo.WeighingBillVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.AccountChangeService;
import com.dfhc.pub.vo.FreezeAccVo;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 过磅单服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class WeighingBillService implements IWeighingBillConstants {

    @Autowired
    /**
     * 过磅单数据访问对象
     */
    private WeighingBillDao weighingBillDao;

    @Autowired
    private AccountChangeService accountChangeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private RowNumberService rowNumberService;
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(WeighingBillVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = weighingBillDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "过磅单插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(WeighingBillVo[] vos) {
        String[] ids = weighingBillDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "过磅单插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = weighingBillDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "过磅单删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = weighingBillDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "过磅单删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(WeighingBillVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = weighingBillDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "过磅单更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(WeighingBillVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "过磅单批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(WeighingBillVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<WeighingBillVo> lInsert = new ArrayList<WeighingBillVo>();
        List<WeighingBillVo> lUpdate = new ArrayList<WeighingBillVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new WeighingBillVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new WeighingBillVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public WeighingBillVo get(String id) {
        WeighingBillVo vo = weighingBillDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = weighingBillDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<WeighingBillVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<WeighingBillVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<WeighingBillVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<WeighingBillVo> lResult = weighingBillDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<WeighingBillVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<WeighingBillVo> lResult = weighingBillDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(WeighingBillVo vo) {
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
    private void verifyInsertVo(WeighingBillVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<WeighingBillVo> vos) {
        for(WeighingBillVo vo:vos){
           verifyUpdateVo(vo);
        }
        weighingBillDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<WeighingBillVo> vos) {
        for(WeighingBillVo vo:vos){
           verifyInsertVo(vo);
        }
        weighingBillDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        WeighingBillVo vo = weighingBillDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(weighingBillDao.update(vo)!=1){
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
     * 验证过磅单
     * @param result
     * @param vo
     * @return
     */
	public Map<String, Object> verifyInsertVo(Map<String, Object> result,
			WeighingBillVo vo) {
		if(StringHelper.isEmpty(vo.getLadingBillId())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "未选择提货单！");
			return result;
		}
		
		if(StringHelper.isEmpty(vo.getTruckNo())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "未选择车牌号！");
			return result;
		}
		
		if(StringHelper.isEmpty(vo.getProductId())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "未选择产品！");
			return result;
		}
		
		if(StringHelper.isEmpty(vo.getProductModelNumber())){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "未输入产品型号！");
			return result;
		}
		
		
		BigDecimal zero = BigDecimal.ZERO;
		//第一次过磅
		if(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS_01.equals(vo.getStatus())){
			if(zero.equals(vo.getTare())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "请输入第一次检斤重量！");
				return result;
			}
			if(StringHelper.isEmpty(vo.getWeighmanId())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "请选择第一次司磅员！");
				return result;
			}
			
		}
		//第二次过磅
		if(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS_02.equals(vo.getStatus())){
			if(zero.equals(vo.getGrossWeight())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "请输入第二次检斤重量！");
				return result;
			}
			if(StringHelper.isEmpty(vo.getWeighmanId2())){
				result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				result.put(ISystemConstant.AJAX_MESSAGE, "请选择第二次司磅员！");
				return result;
			}
			
		}
		
		
		return result;
	}

	public WeighingBillVo getWeighingBillVo(Map<String, Object> variable, boolean flag){
		List<WeighingBillVo> list = list(variable, null, 1, -1, flag);
		
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}
	
	public WeighingBillVo getWeighingBillVo(Map<String, Object> variable){
		
		return getWeighingBillVo(variable, false);
	}
	 /**
     * 二次过磅
     * @param request
     * @param billid 订单号
     * @param truckNumber 车牌号
     * @param fistTime 第一次过磅时间 
     * @param firstWeight 第一次检斤重量
     * @param firstClerk 第一次司磅员
     * @param secondTime 第二次过磅时间
     * @param secondWeight 第二次检斤重量
     * @param secondClerk 第二次司磅员
     * @param weight 净重
     * @return
     */
	public Map<String, Object> updateTwoWeigh(HttpServletRequest request,
			String billid, String truckNumber, String fistTime,
			String firstWeight, String firstClerk, String secondTime,
			String secondWeight, String secondClerk, String weight) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> searchPara = new HashMap<String,Object>();
		//		result.put("ladingBillCode", billid);
//		result.put("rowTruckNo", truckNumber);
//		result.put("rowStatus", ISystemConstant.DICTIONARY_ROW_STATUS_05);
//		result.put("twoWeigh", "twoWeigh");
//		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//		
//		List<WeighingBillVo> list = list(result, null);
//		result.clear();
//		
//		if(CollectionUtils.isEmpty(list)){
//			
//			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
//			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "接口参数错误，未找到过磅单！");
//			
//			return result;
//			
//		}
		//查询提货单
		searchPara.put("ladingBillCode", billid);
		List<LadingBillVo> ladingBillVos = ladingBillService.list(searchPara, null);
		if(CollectionUtils.isEmpty(ladingBillVos)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "接口参数错误，未找到提货单:"+billid);
			return result;
		}
		LadingBillVo ladingBillVo = ladingBillVos.get(0);
		
		//验证提货单是否允许过磅,提货单状态为提货单状态=04 或者 提货单状态=05 或 提货单状态=10
		if(!ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(ladingBillVo.getIsPound())){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "提货单:"+billid+"不需要过磅!");
			return result;
		}
		String ladingBillStatus = ladingBillVo.getLadingBillStatus();
		if(!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04.equals(ladingBillStatus) &&
				!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05.equals(ladingBillStatus)&&
				!ISystemConstant.DICTIONARY_LADING_BILL_STATUS_10.equals(ladingBillStatus)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "提货单:"+billid+"状态不对，期望状态 为:车辆安检(04),装车待分配(05),装车已分配(10),实际状态为:"+
			RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS, ladingBillStatus));
			return result;
		}
		//排队表状态为05=已入厂
		searchPara.clear();
		searchPara.put("ladingBillId", ladingBillVo.getId());
		List<RowNumberVo> rowNumberVos = rowNumberService.list(searchPara, null);
		if(CollectionUtils.isEmpty(rowNumberVos)){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "提货单:"+billid+"对应排队表没找到!");
			return result;
		}
		if(!ISystemConstant.DICTIONARY_ROW_STATUS_05.equals(rowNumberVos.get(0).getStatus())){
			result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_FAIL);
			result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "提货单:"+billid+"对应车辆尚未入厂!");
			return result;
		}
		WeighingBillVo weighingBillVo = new WeighingBillVo();
		
		weighingBillVo.setFirstWeighingTime(DateUtil.toSqlDate(DateUtil.parser(fistTime)));
		weighingBillVo.setTare(new BigDecimal(firstWeight).setScale(4, BigDecimal.ROUND_HALF_EVEN));
		weighingBillVo.setWeighman(firstClerk);
		weighingBillVo.setTwoWeighingTime(DateUtil.toSqlDate(DateUtil.parser(secondTime)));
		weighingBillVo.setGrossWeight(new BigDecimal(secondWeight).setScale(4, BigDecimal.ROUND_HALF_EVEN));
		weighingBillVo.setWeighman2(secondClerk);
		weighingBillVo.setNetWeight(new BigDecimal(weight).setScale(4, BigDecimal.ROUND_HALF_EVEN));
		weighingBillVo.setStatus(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS_02);
		weighingBillVo.setLadingBillId(ladingBillVo.getId());
		weighingBillVo.setLadingBillCode(ladingBillVo.getLadingBillCode());
		weighingBillVo.setTruckNo(ladingBillVo.getLicensePlate());
		weighingBillVo.setPhoneNumber(ladingBillVo.getPhoneNumber());
		weighingBillVo.setProductId(ladingBillVo.getProductId());
		weighingBillVo.setProductName(ladingBillVo.getProductName());
		weighingBillVo.setProductModelNumber(ladingBillVo.getProductModelNumber());
		weighingBillVo.setShipperCode(ladingBillVo.getShipperCode());
		//weighingBillVo.setShipper(ladingBillVo.getshi)
		weighingBillVo.setReceivingUnit(ladingBillVo.getReceivingUnit());
		
		insert(weighingBillVo);
		
		
		
		if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(ladingBillVo.getBillType())){
			
			
			//二次过磅 调用接口结算
			accountChangeService.doGoodsBillComplete(createFreezeAccVo(weighingBillVo, result, request));
			
			//回填提货单
			ladingBillService.supplement(weighingBillVo.getLadingBillId(), weighingBillVo.getNetWeight(), result, request);
		}
		
		//判断是否装车   如果装车  修改提货单实际发货数量为净重
		ladingBillService.updateByIspon(ladingBillVo,weight);
		
		ladingBillVo.setLadingBillStatus(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06);
		
		ladingBillService.update(ladingBillVo);
		
		
		result.put(ISystemConstant.INTERFACE_RESULT_STATUS, ISystemConstant.INTERFACE_RESULT_SUCCESS);
		result.put(ISystemConstant.INTERFACE_RESULT_ERRMSG, "操作成功！");
		
		return result;
	}
	
	private FreezeAccVo createFreezeAccVo(WeighingBillVo billVo, Map<String, Object> variable, HttpServletRequest request){
		FreezeAccVo vo = new FreezeAccVo();
		vo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_02);
		vo.setBillsId(billVo.getId());
		
		variable.put("weighingBillId", billVo.getId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("weighingBillOrder", "weighingBillOrder");
		
		OrderVo orderVo = orderService.getOrderVo(variable);
		
		vo.setPayerId(String.valueOf(orderVo.getCustomerId()));
		
		vo.setChangeAmt(billVo.getNetWeight().multiply(orderVo.getProductUnitPrice()).setScale(4, BigDecimal.ROUND_HALF_EVEN));
		
		vo.setRemark("过磅");
		
		vo.setIp(RmProjectHelper.getIp(request));
		
		return vo;
		
	}
}
