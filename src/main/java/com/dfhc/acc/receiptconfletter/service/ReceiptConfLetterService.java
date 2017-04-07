/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ReceiptConfLetterService.java
 *
 * 功能描述：  收货确认函服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.acc.receiptconfletter.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.acc.customeravailableamount.service.CustomerAvailableAmountService;
import com.dfhc.acc.customeravailableamount.vo.CustomerAvailableAmountVo;
import com.dfhc.acc.receiptconfletter.IReceiptConfLetterConstants;
import com.dfhc.acc.receiptconfletter.dao.ReceiptConfLetterDao;
import com.dfhc.acc.receiptconfletter.vo.ReceiptConfLetterVo;
import com.dfhc.bus.ladingbill.service.LadingBillService;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.PjException;
import com.dfhc.pub.service.AccountChangeService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.vo.CalculateVo;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
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
 * 收货确认函服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class ReceiptConfLetterService implements IReceiptConfLetterConstants {

    @Autowired
    /**
     * 收货确认函数据访问对象
     */
    private ReceiptConfLetterDao receiptConfLetterDao;
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private CustomerAvailableAmountService customerAvailableAmountService;
    @Autowired
    private PubParamService pubParamService;

    @Autowired
    private AccountChangeService accountChangeService;
    
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(ReceiptConfLetterVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = receiptConfLetterDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "收货确认函插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(ReceiptConfLetterVo[] vos) {
        String[] ids = receiptConfLetterDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "收货确认函插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = receiptConfLetterDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "收货确认函删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = receiptConfLetterDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "收货确认函删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ReceiptConfLetterVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = receiptConfLetterDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "收货确认函更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(ReceiptConfLetterVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "收货确认函批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(ReceiptConfLetterVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<ReceiptConfLetterVo> lInsert = new ArrayList<ReceiptConfLetterVo>();
        List<ReceiptConfLetterVo> lUpdate = new ArrayList<ReceiptConfLetterVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new ReceiptConfLetterVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new ReceiptConfLetterVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ReceiptConfLetterVo get(String id) {
        ReceiptConfLetterVo vo = receiptConfLetterDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = receiptConfLetterDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<ReceiptConfLetterVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<ReceiptConfLetterVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<ReceiptConfLetterVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<ReceiptConfLetterVo> lResult = receiptConfLetterDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<ReceiptConfLetterVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<ReceiptConfLetterVo> lResult = receiptConfLetterDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(ReceiptConfLetterVo vo) {
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
    private void verifyInsertVo(ReceiptConfLetterVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<ReceiptConfLetterVo> vos) {
        for(ReceiptConfLetterVo vo:vos){
           verifyUpdateVo(vo);
        }
        receiptConfLetterDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<ReceiptConfLetterVo> vos) {
        for(ReceiptConfLetterVo vo:vos){
           verifyInsertVo(vo);
        }
        receiptConfLetterDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        ReceiptConfLetterVo vo = receiptConfLetterDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        String operation = request.getParameter("operation");
        if("coordination".equals(operation)){
        	if(!vo.getCustomerId().equals(RmProjectHelper.getRmUserId(request))){
        		throw new PjException("只能操作自己的结算单！");
        	}
        }
        
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        
        if(!ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_00.equals(vo.getStatus()) && !ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03.equals(vo.getStatus())){
        	RmProjectHelper.logError("id:"+id+"已结算，不能删除!", "id:"+id+"已结算，不能删除!");
        	return 0;
        }
        List<LadingBillVo> list = ladingBillService.getList(vo.getId());
        
        for (LadingBillVo ladingBillVo : list) {
        
        	ladingBillVo.setSettlementFlag(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
        	ladingBillVo.setInvoiceSettlementId(null);
		
        }
        
        RmVoHelper.markModifyStamp(request, list);
        ladingBillService.updateBatch(list);
        
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(receiptConfLetterDao.update(vo)!=1){
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
     * 新增收货确认函
     * @param vo
     * @param ladingBillId 提货单id
     * @param request
     * @return
     */
	public Map<String, Object> insert(ReceiptConfLetterVo vo,
			String ladingBillId, HttpServletRequest request) {
		//RmVoHelper.markCreateStamp(request,vo);
		
		Map<String, Object> variable = new HashMap<String, Object>();
		
		
		//验证信息
		checkedInfo(vo, ladingBillId, variable);

		if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
			
			return variable;
		}
		
		variable.put("customerId", vo.getCustomerId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		CustomerAvailableAmountVo customerAvailableAmountVo = customerAvailableAmountService.getCustomerAvailableAmountVos(variable);
		
		variable.clear();
		
		if(customerAvailableAmountVo == null ){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "未找到所选客户的业务账户！");
			
			return variable;
		}
		
		
		
		
		List<LadingBillVo> list = null;
		boolean flag = false;
		if(StringHelper.isEmpty(vo.getId())){//新增
			flag = true;
			String[] split = ladingBillId.split(",");
			
			list = getLadingBillVoList(variable, StringHelper.idsListToStr(split), vo.getCustomerId(), ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
			
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
				
				return variable;
			}
			if(list.size() != split.length){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "找到的提货单数量与所选不一致！");
				return variable;
			}
			if(null == vo.getSettlementStartDate()){
				//没有时间 取提货单时间最早的一个出门时间
				vo.setSettlementStartDate(DateUtil.toSqlDate(list.get(0).getOutTime()));
			}
			if(null == vo.getSettlementEndDate()){
				vo.setSettlementEndDate(DateUtil.toSqlDate(list.get(list.size()-1).getOutTime()));
			}
			vo.setDocumentNumber(pubParamService.getSequenceNumber("DOCUMENT_NUMBER"));
			
			vo.setId(RmIdFactory.requestId(TABLE_NAME));
			
			
			ladingBillService.updateLadingBillVo(list, vo.getId(), variable);
		}else{//修改
			if(!StringHelper.isEmpty(ladingBillId)){
				//修改时要先排除上次的提货单 返回提货单id
				List<String> ids = ladingBillService.excludeAlreadySettlement(ladingBillId, vo, variable);
				
				list = getLadingBillVoList(variable, StringHelper.idsListToStr(ids), vo.getCustomerId(), ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
				
				if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
					
					return variable;
				}
				if(list.size() != ids.size()){
					
					variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
					variable.put(ISystemConstant.AJAX_MESSAGE, "找到的提货单数量与所选不一致！");
					return variable;
				}
				ladingBillService.updateLadingBillVo(list, vo.getId(), variable);

				Date date1 = DateUtil.toSqlDate(list.get(0).getOutTime());
				Date date2 = DateUtil.toSqlDate(list.get(list.size()-1).getOutTime());
				if(!vo.getSettlementStartDate().before(date1)){
					vo.setSettlementStartDate(date1);
				}
				if(!vo.getSettlementEndDate().after(date2)){
					vo.setSettlementEndDate(date2);
				}
				
				
			}
			
			
		}
		
		vo.setTotalAmount((vo.getTotalAmount() == null?BigDecimal.ZERO:vo.getTotalAmount()).add((BigDecimal)variable.get("totalAmount")));
		vo.setActualSettlementAmount((vo.getActualSettlementAmount()==null?BigDecimal.ZERO:vo.getActualSettlementAmount()).add((BigDecimal)variable.get("actualSettlementAmount")));
		
		
		variable.clear();
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		
		vo.setApplyPersionId(Long.valueOf(userVo.getId()));
		
		vo.setApplyPerson(userVo.getName());
		
		vo.setApplyTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		vo.setStatus(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03);
		
		vo.setBookBalance(customerAvailableAmountVo.getAvailableCredit());
		
		if(list != null){
			ladingBillService.updateBatch(list);
		}
		if(flag){
			RmVoHelper.markCreateStamp(request, vo);
			insert(vo);
		}else{
			RmVoHelper.markModifyStamp(request, vo);
			update(vo);
		}
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "成功生成收货确认函！");
		
		return variable;
	}
	
	

	/**
	 * 查询需要结算的提货单 按出门时间升序排列
	 * @param variable
	 * @param ladingBillId
	 * @param customerId
	 * @param dictionaryLadingBillStatus08
	 */
	private List<LadingBillVo> getLadingBillVoList(Map<String, Object> variable,
			String ladingBillId, Long customerId,
			String status08) {
		
		
		variable.put("ids", ladingBillId);
		variable.put("ladingBillStatus", ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
		variable.put("customerIdHou", customerId);
		
		//查询需要结算的提货单
		List<LadingBillVo> list = ladingBillService.list(variable, "BUS_LADING_BILL.OUT_TIME asc");
		
		if(CollectionUtils.isEmpty(list)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "未找到提货单！");
		}
			
		return list;
	}

	/**
	 * 新增或修改收货确认函信息验证
	 * @param vo
	 * @param ladingBillId
	 */
	private void checkedInfo(ReceiptConfLetterVo vo, String ladingBillId, Map<String, Object> variable) {
		
		if(StringHelper.isEmpty(vo.getId())){//新增
			
			if(null != vo.getCustomerId()){
				
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "未选择客户！");
				
				return;
			}
			
			if(StringHelper.isEmpty(ladingBillId)){
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "未选择需要结算的提货单！");
				
				return;
			}
		}else{
			if(!ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03.equals(vo.getStatus())){
				variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
				variable.put(ISystemConstant.AJAX_MESSAGE, "该收货确认函状态不是待提交，不能修改！");
				
				return;
			}
		}
		
	}

	/**
	 * 收货确认函确认结算
	 * @param result
	 * @param vo
	 * @param request
	 */
	public void confirmSubmit(Map<String, Object> result,
			ReceiptConfLetterVo vo, HttpServletRequest request) {
		if(!ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_00.equals(vo.getStatus()) && !ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03.equals(vo.getStatus())){//待结算
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, "只有待结算的才能结算！");
			
    		return;
		}
		String operation = request.getParameter("operation");
        if("coordination".equals(operation)){
        	if(!vo.getCustomerId().equals(RmProjectHelper.getRmUserId(request))){
        		throw new PjException("只能操作自己的结算单！");
        	}
        }
		
		String remark = request.getParameter("remark");
		
		try {
			accountChangeService.doOrderCalulate(createCalculateVo(vo, request, remark));
			vo.setStatus(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_01);
			RmVoHelper.markModifyStamp(request, vo);
			update(vo);
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    		result.put(ISystemConstant.AJAX_MESSAGE, "结算成功！");
    		
		} catch (Exception e) {
			
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
    		result.put(ISystemConstant.AJAX_MESSAGE, e.getMessage());
			
    		return;
		}
	}

	/**
	 * 结算单操作vo
	 * @return
	 */
	public CalculateVo createCalculateVo(ReceiptConfLetterVo receiptConfLetterVo, HttpServletRequest request, String remark){
		
		CalculateVo vo = new CalculateVo();
		
		vo.setActualChangeAmt(receiptConfLetterVo.getActualSettlementAmount());
		
		vo.setBillsId(receiptConfLetterVo.getId());
		
		vo.setBusinessType(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE_05);
		
		vo.setIp(RmProjectHelper.getIp(request));
		
		vo.setOldChangeAmt(receiptConfLetterVo.getTotalAmount());

		vo.setOperaterId(RmProjectHelper.getRmUserId(request));
		
		vo.setOperaterName(RmProjectHelper.getRmUserVo(request).getName());
		
		vo.setPayerId(String.valueOf(receiptConfLetterVo.getCustomerId()));
		
		vo.setRemark(remark);
		
		return vo;
	}

	/**
	 * 副产品结算
	 * @param request
	 * @param ladingBillIds
	 * @return
	 */
	public Map<String, Object> insertSettlement(HttpServletRequest request,
			String ladingBillIds) {
		
		Map<String, Object> variable = new HashMap<String, Object>();
		
		if(StringHelper.isEmpty(ladingBillIds)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "没有选择提货单！");
			
			return variable;
		}
		
		//查询所有的提货单 并按客户分组
		Map<String, Object> result = ladingBillService.getListAndGroupByCustomer(variable, ladingBillIds);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			
			return result;
		}
		
		List<ReceiptConfLetterVo> receiptConfLetterVos = new ArrayList<ReceiptConfLetterVo>();
		List<LadingBillVo> ladingBillVos = new ArrayList<LadingBillVo>();
		
		for (Map.Entry<String, Object> entry : result.entrySet()) {
			//客户id
//			String customerId = entry.getKey();
			//循环取出每一个客户的提货单 进行结算
			List<LadingBillVo> list = (List<LadingBillVo>) entry.getValue();

			ReceiptConfLetterVo vo = new ReceiptConfLetterVo();
			
			
			createReceiptConfLetterVo(request, vo, list, variable);
			if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
				continue;
			}
			receiptConfLetterVos.add(vo);
			ladingBillVos.addAll(list);
			
			
		}
		
		RmVoHelper.markCreateStamp(request, receiptConfLetterVos);
		insertBatch(receiptConfLetterVos);

		RmVoHelper.markCreateStamp(request, ladingBillVos);
		ladingBillService.updateBatch(ladingBillVos);
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "生成结算单成功！");
		
		return variable;
	}

	
	/**
	 * 创建收货确认函 并不全提货单属性
	 * @param request
	 * @param vo
	 * @param list
	 * @param variable
	 */
	private void createReceiptConfLetterVo(HttpServletRequest request,
			ReceiptConfLetterVo vo, List<LadingBillVo> list,
			Map<String, Object> variable) {
		variable.put("customerId", vo.getCustomerId());
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		CustomerAvailableAmountVo customerAvailableAmountVo = customerAvailableAmountService.getCustomerAvailableAmountVos(variable);
		
		variable.clear();
		
		if(customerAvailableAmountVo == null ){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "未找到所选客户的业务账户！");
			
			RmProjectHelper.logError("生成收货确认函时没有客户的业务账户，失败的提货单 ： " + list.toString(), "生成收货确认函时没有客户的业务账户，失败的提货单 ： " + list.toString());
			return ;
		}
		
		vo.setId(RmIdFactory.requestId(TABLE_NAME));
		
		vo.setSettlementStartDate(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		vo.setDocumentNumber(pubParamService.getSequenceNumber("DOCUMENT_NUMBER"));
		
		vo.setCustomerId(Long.valueOf(list.get(0).getCustomerId()));
		
		vo.setCustomerName(list.get(0).getCustomerName());
		
		RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
		vo.setApplyPersionId(Long.valueOf(userVo.getId()));
		
		vo.setApplyPerson(userVo.getName());
		
		vo.setApplyTime(DateUtil.toSqlDate(DateUtil.getNowDate()));
		
		
		ladingBillService.updateLadingBillVo(list, vo.getId(), variable);
		
	
		vo.setActualSettlementAmount((vo.getActualSettlementAmount()==null?BigDecimal.ZERO:vo.getActualSettlementAmount()).add((BigDecimal)variable.get("actualSettlementAmount")));
	
		vo.setTotalAmount(vo.getActualSettlementAmount());
		
		vo.setStatus(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS_03);
		
		vo.setBookBalance(customerAvailableAmountVo.getAvailableCredit());
		vo.setShipperCode(list.get(0).getShipModeCode());
	}
}
