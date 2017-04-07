/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LogisticsCostSettlementService.java
 *
 * 功能描述：  物流费用结算审批单服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.acc.logisticscostsettlement.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.acc.customeravailableamount.vo.CustomerAvailableAmountVo;
import com.dfhc.acc.logisticscostsettlement.ILogisticsCostSettlementConstants;
import com.dfhc.acc.logisticscostsettlement.dao.LogisticsCostSettlementDao;
import com.dfhc.acc.logisticscostsettlement.vo.LogisticsCostSettlementVo;
import com.dfhc.acc.receiptconfletter.vo.ReceiptConfLetterVo;
import com.dfhc.base.logisticscompany.service.LogisticsCompanyService;
import com.dfhc.base.logisticscompany.vo.LogisticsCompanyVo;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 物流费用结算审批单服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class LogisticsCostSettlementService implements ILogisticsCostSettlementConstants {

	@Autowired
	private   LogisticsCompanyService  logisticsCompanyService;
	@Autowired
	private  PubParamService pubParamService;
	
	@Autowired
	private  LadingBillService ladingBillService;
    @Autowired
    /**
     * 物流费用结算审批单数据访问对象
     */
    private LogisticsCostSettlementDao logisticsCostSettlementDao;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(LogisticsCostSettlementVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = logisticsCostSettlementDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "物流费用结算审批单插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(LogisticsCostSettlementVo[] vos) {
        String[] ids = logisticsCostSettlementDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "物流费用结算审批单插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = logisticsCostSettlementDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "物流费用结算审批单删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = logisticsCostSettlementDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "物流费用结算审批单删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(LogisticsCostSettlementVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = logisticsCostSettlementDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "物流费用结算审批单更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(LogisticsCostSettlementVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "物流费用结算审批单批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(LogisticsCostSettlementVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<LogisticsCostSettlementVo> lInsert = new ArrayList<LogisticsCostSettlementVo>();
        List<LogisticsCostSettlementVo> lUpdate = new ArrayList<LogisticsCostSettlementVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new LogisticsCostSettlementVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new LogisticsCostSettlementVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public LogisticsCostSettlementVo get(String id) {
        LogisticsCostSettlementVo vo = logisticsCostSettlementDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = logisticsCostSettlementDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<LogisticsCostSettlementVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<LogisticsCostSettlementVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<LogisticsCostSettlementVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<LogisticsCostSettlementVo> lResult = logisticsCostSettlementDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<LogisticsCostSettlementVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<LogisticsCostSettlementVo> lResult = logisticsCostSettlementDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(LogisticsCostSettlementVo vo) {
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
    private void verifyInsertVo(LogisticsCostSettlementVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<LogisticsCostSettlementVo> vos) {
        for(LogisticsCostSettlementVo vo:vos){
           verifyUpdateVo(vo);
        }
        logisticsCostSettlementDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<LogisticsCostSettlementVo> vos) {
        for(LogisticsCostSettlementVo vo:vos){
           verifyInsertVo(vo);
        }
        logisticsCostSettlementDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        LogisticsCostSettlementVo vo = logisticsCostSettlementDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(logisticsCostSettlementDao.update(vo)!=1){
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
	 * 主产品结算
	 * @param request
	 * @param ladingBillIds
	 * @return
	*/
	public Map<String, Object> insertSettlement(HttpServletRequest request,String ladingBillIds) {
		
		Map<String, Object> variable = new HashMap<String, Object>();
		
		if(StringHelper.isEmpty(ladingBillIds)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "没有选择提货单！");
			
			return variable;
		}
		
		//结算类型
		String billTypeLu=request.getParameter("billTypeLu");
		
		//查询所有的提货单 并按物流公司分组
		Map<String, Object> result = ladingBillService.getListAndGroupByLogistics(variable, ladingBillIds,billTypeLu);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			
			return result;
		}
		if(result.entrySet().size()>1){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "请选择同一物流公司的提货单");
			return variable;
		}
		List<LogisticsCostSettlementVo> logisticsCostSettlementVos = new ArrayList<LogisticsCostSettlementVo>();
		List<LadingBillVo> ladingBillVos = new ArrayList<LadingBillVo>();
		
		String settlementType =null;
		if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billTypeLu)){
			//汽配
			settlementType=ISystemConstant.DICTIONARY_SETTLEMENT_TYPE_01;
		}else  if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billTypeLu)){
			//汽调
			settlementType=ISystemConstant.DICTIONARY_SETTLEMENT_TYPE_02;
		}else  if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billTypeLu)){
			//铁调
			settlementType=ISystemConstant.DICTIONARY_SETTLEMENT_TYPE_03;
		}
		
		String logisticsCostSettlementId=null;
		for (Map.Entry<String, Object> entry : result.entrySet()) {
			//循环取出每一个物流公司的提货单 进行结算
			List<LadingBillVo> list = (List<LadingBillVo>) entry.getValue();

			LogisticsCostSettlementVo vo = new LogisticsCostSettlementVo();
			
			createLogisticsCostSettlementVo(request, vo, list, variable,settlementType);
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
				continue;
			}
			logisticsCostSettlementVos.add(vo);
			ladingBillVos.addAll(list);
			
			logisticsCostSettlementId=vo.getId();
		}
		
		RmVoHelper.markCreateStamp(request, logisticsCostSettlementVos);
		insertBatch(logisticsCostSettlementVos);

		RmVoHelper.markCreateStamp(request, ladingBillVos);
		ladingBillService.updateBatch(ladingBillVos);
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "生成结算单成功！");
//		variable.put("url","logisticscostsettlement/toDetail?id="+logisticsCostSettlementId+"&pcode=100109&childSource=ch0code");
		variable.put("url","logisticscostsettlement/toDetail?pcode="+request.getParameter(ISystemConstant.RM_PARENT_CODE)+"&tofrom=doSettle&id="+logisticsCostSettlementId);
		
		return variable;
	}


	
	/**
	 * 创建运费结算单 并补全提货单属性
	 * @param request
	 * @param vo
	 * @param list
	 * @param variable
	 * */
	private void createLogisticsCostSettlementVo(HttpServletRequest request,
			LogisticsCostSettlementVo vo, List<LadingBillVo> list,
			Map<String, Object> variable,String settlementType) {
		
		vo.setId(RmIdFactory.requestId(TABLE_NAME));
		
		vo.setSettlementType(settlementType);
		
		vo.setDocumentNumber(pubParamService.getSequenceNumber("DOCUMENT_NUMBER"));

		vo.setLogisticsCompanyId(list.get(0).getLogisticsCompanyId());
		
		vo.setLogisticsCompany(list.get(0).getLogisticsCompany());

		vo.setStatus(ISystemConstant.DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS_00);
		
		ladingBillService.updateLadingBillVoLu(list, vo.getId(), vo);
		
		//物流公司详情
		LogisticsCompanyVo logisticsCompanyVo=logisticsCompanyService.get(list.get(0).getLogisticsCompanyId());
		vo.setTaxNo(StringHelper.isEmpty(logisticsCompanyVo.getTaxNo())?null:logisticsCompanyVo.getTaxNo());
		vo.setAddress(StringHelper.isEmpty(logisticsCompanyVo.getOfficeAddr())?null:logisticsCompanyVo.getOfficeAddr());
		vo.setTel(StringHelper.isEmpty(logisticsCompanyVo.getTel())?null:logisticsCompanyVo.getTel());
		vo.setBank(StringHelper.isEmpty(logisticsCompanyVo.getBank())?null:logisticsCompanyVo.getBank());
		vo.setAccountName(StringHelper.isEmpty(logisticsCompanyVo.getAccountName())?null:logisticsCompanyVo.getAccountName());
//		vo.setShipperCode("");
	}
	
	
    /**
     * 确认提交

     */
    public   Map<String, Object> doConfirmSubmit(LogisticsCostSettlementVo newVo,HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        LogisticsCostSettlementVo oldVo=get(newVo.getId());
        
        if(!ISystemConstant.DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS_00.equals(oldVo.getStatus())){
            result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
            result.put(ISystemConstant.AJAX_MESSAGE, "已提交");
            return result;
        }
        
        
//        result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
//        result.put("freightSettlementId",newVo.getId());
//        List<LadingBillVo>  listLadingBillVos=ladingBillService.list(result, null);
//
//        ladingBillService.updateLadingBillVoLu(listLadingBillVos, newVo.getId(), newVo);
        
        //已提交
        newVo.setStatus(ISystemConstant.DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS_01);
        
        //看子表是否修改了
        ladingBillService.updateBySettlerBIll(request, newVo);
        
        RmVoHelper.markModifyStamp(request,newVo);  //打修改时间,IP戳
        update(newVo);
        
        
        //更新单条记录
	    result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
	    result.put(ISystemConstant.AJAX_MESSAGE, "确认提交");
        return result;
   }

}
