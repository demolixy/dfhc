/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  PriceService.java
 *
 * 功能描述：  价格管理服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.price.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.base.price.IPriceConstants;
import com.dfhc.base.price.dao.PriceDao;
import com.dfhc.base.price.vo.PriceVo;
import com.dfhc.base.price.vo.TodayCarTabVo;
import com.dfhc.PjException;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.project.login.RmUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 价格管理服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class PriceService implements IPriceConstants {

    @Autowired
    /**
     * 价格管理数据访问对象
     */
    private PriceDao priceDao;
    @Autowired
    /**
     * 通用流程业务表服务
     */
    private GeneralBusinessProcessService generalBusinessProcessService;
    @Autowired
    RuntimeService  runtimeService;
    @Autowired
    HistoryService historyService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(PriceVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = priceDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "价格管理插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(PriceVo[] vos) {
        String[] ids = priceDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "价格管理插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = priceDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "价格管理删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = priceDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "价格管理删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(PriceVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = priceDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "价格管理更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(PriceVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "价格管理批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(PriceVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<PriceVo> lInsert = new ArrayList<PriceVo>();
        List<PriceVo> lUpdate = new ArrayList<PriceVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new PriceVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new PriceVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public PriceVo get(String id) {
        PriceVo vo = priceDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = priceDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<PriceVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<PriceVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<PriceVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<PriceVo> lResult = priceDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<PriceVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<PriceVo> lResult = priceDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(PriceVo vo) {
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
    private void verifyInsertVo(PriceVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<PriceVo> vos) {
        for(PriceVo vo:vos){
           verifyUpdateVo(vo);
        }
        priceDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<PriceVo> vos) {
        for(PriceVo vo:vos){
           verifyInsertVo(vo);
        }
        priceDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        PriceVo vo = priceDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(priceDao.update(vo)!=1){
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
     * 根据通用表数据,批量新增和修改价格
     * @param request
     * @param parentId 
     */
    public void doInsertFromProc(HttpServletRequest request, String parentId){
    	List<GeneralBusinessProcessVo> generalBusinessProcessVos = generalBusinessProcessService.getByParentId(parentId);
    	List<PriceVo> insertPriceVos = new ArrayList<PriceVo>();
    	List<PriceVo> updatePriceVos = new ArrayList<PriceVo>();
    	Map<String, Object> searchMap = new HashMap<String, Object>();
    	searchMap.put("statuss", "'"+ISystemConstant.DICTIONARY_PRICE_STATUS_2+"','"+ISystemConstant.DICTIONARY_PRICE_STATUS_3+"'");
    	for (GeneralBusinessProcessVo generalBusinessProcessVo : generalBusinessProcessVos) {
			PriceVo priceVo = new PriceVo();
			priceVo.setProductId(generalBusinessProcessVo.getBusinessAttribute70());//产品id
			priceVo.setProductName(generalBusinessProcessVo.getBusinessAttribute57());//产品名称
			priceVo.setPrice(new BigDecimal(generalBusinessProcessVo.getBusinessAttribute69()));//价格
			priceVo.setStartTime(DateUtil.getNowTimestamp());//开始时间
			priceVo.setStatus(ISystemConstant.DICTIONARY_PRICE_STATUS_2);
			RmUserVo userVo = RmProjectHelper.getRmUserVo(request);
			priceVo.setCreate_user_name(userVo.getName());
			priceVo.setAttribute1(generalBusinessProcessVo.getId());//作为关联
			RmVoHelper.markCreateStamp(request, priceVo);
			insertPriceVos.add(priceVo);
			
			searchMap.put("productId", generalBusinessProcessVo.getBusinessAttribute70());
			List<PriceVo> oldPriceVos = this.list(searchMap, null);
			if(oldPriceVos.size()>0){
				for (PriceVo priceVo2 : oldPriceVos) {
					priceVo2.setStatus(ISystemConstant.DICTIONARY_PRICE_STATUS_4);
					priceVo2.setEndTime(DateUtil.getNowTimestamp());
					updatePriceVos.add(priceVo2);
				}
			}
		}
    	if(updatePriceVos.size()>0){
    		this.updateBatch(updatePriceVos);
    	}
    	this.insertBatch(insertPriceVos);
    }
    
    /**
     * 配合工作流操作数据库
     * @param gbVos
     * @param insertPriceVos
     * @param updatePriceVos
     */
    public void doInsertAndUpdateForm(List<GeneralBusinessProcessVo> gbVos, List<PriceVo> insertPriceVos, List<PriceVo> updatePriceVos){
    	System.out.println("开始插入数据。。。");
    	for (GeneralBusinessProcessVo generalBusinessProcessVo : gbVos) {
    		generalBusinessProcessService.insert(generalBusinessProcessVo);
		}
		
		System.out.println("通用表数据插入成功!");
		this.insertBatch(insertPriceVos);
		System.out.println("批量价格插入成功！");
		this.updateBatch(updatePriceVos);
		System.out.println("批量价格更新成！");
    }
    
    /**
     * 配合工作流操作数据库
     * @param gbVos
     * @param insertPriceVos
     * @param updatePriceVos
     */
    public void doUpdateAndUpdateForm(List<GeneralBusinessProcessVo> gbVos, List<PriceVo> insertPriceVos, List<PriceVo> updatePriceVos){
    	System.out.println("开始插入数据。。。");
    	for (GeneralBusinessProcessVo generalBusinessProcessVo : gbVos) {
    		generalBusinessProcessService.update(generalBusinessProcessVo);
		}
		System.out.println("通用表数据插入成功!");
		this.insertBatch(insertPriceVos);
		System.out.println("批量价格插入成功！");
		this.updateBatch(updatePriceVos);
		System.out.println("批量价格更新成！");
    }
    
    
    /**
     * 根据价格vo同时更新价格表和通用表
     * @param vo 价格vo
     */
    public void doUpdateGengeral(PriceVo vo){
    	this.update(vo);
    	PriceVo priceVo = this.get(vo.getId());
    	String generalBusinessProcessId = priceVo.getAttribute1();
    	if(!StringHelper.isEmpty(generalBusinessProcessId)){
    		GeneralBusinessProcessVo generalBusinessProcessVo = generalBusinessProcessService.get(generalBusinessProcessId);
    		generalBusinessProcessVo.setBusinessAttribute69(vo.getPrice().toString());
    		generalBusinessProcessService.update(generalBusinessProcessVo);
    	}
    }
    
    
    /**
     * 获取产品当前价格
     */
    public PriceVo getProductPrice(HttpServletRequest request,String productId) {
        Map<String, Object>  searchMap=new HashMap<String, Object>();
        searchMap.put("productId", productId);
        //有效时间
        searchMap.put("isEffectiveTime","isEffectiveTime");
        //审核通过
        searchMap.put("status",ISystemConstant.DICTIONARY_PRICE_STATUS_3);
        searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);

        List<PriceVo>  list  = list(searchMap, null);
        
        if(list.isEmpty()){
            throw new PjException("请维护当前产品有效时间的价格");
        }
        return list.get(0) ;
    }
    /**
     * 根据实例id删除流程实例并修改价格状态
     * @param processInstanceId 流程实例id
     */
    public void doDeleteProcessInstanceAndUpdatePrice(String processInstanceId){
  	  	GeneralBusinessProcessVo generalBusinessProcessVo = generalBusinessProcessService.getGeneralBusinessProcessVoByProcInstId(processInstanceId);
  	  	if(generalBusinessProcessVo!=null){
	  	  	runtimeService.deleteProcessInstance(processInstanceId, "");
	  	  	historyService.deleteHistoricProcessInstance(processInstanceId);
	  	  	
	  	  	
  	  	}
    }
    /**
     * 查询首页今日车辆数等信息
     * @return
     */
    public TodayCarTabVo getTodayCarMsg(){
    	
    	StringBuilder sql=new StringBuilder();
    	sql.append(" select count(BUS_ROW_NUMBER.LADING_BILL_ID) num from BUS_ROW_NUMBER where trunc(ROW_DATE)=trunc(sysdate)");
    	sql.append("  group by BUS_ROW_NUMBER.LADING_BILL_ID ");
    	
    	int todayCount=0;
    	List<RmCommonVo> list=RmProjectHelper.getCommonServiceInstance().doQuery(sql.toString());//今日车辆数量
    	
    	if(list!=null && list.size()>0){
    		
    		BigDecimal com=(BigDecimal)list.get(0).get("num");
    		todayCount=com.intValue();
    	}
    	
    	sql= sql.delete( 0, sql.length() );//清空
    	//查今日过磅车辆
    	sql.append(" select  count(BUS_LADING_BILL.ID) num from BUS_LADING_BILL ");
    	sql.append(" inner join ( ");
    	sql.append(" select BUS_ROW_NUMBER.LADING_BILL_ID as billId from BUS_ROW_NUMBER ");
    	sql.append(" where trunc(ROW_DATE)=trunc(sysdate) group by BUS_ROW_NUMBER.LADING_BILL_ID ");
    	sql.append(" ) nl on nl.billId=BUS_LADING_BILL.Id ");
    	
    	StringBuilder bakSql=new StringBuilder();
    	bakSql.append(sql);//备份查询
    	
    	sql.append(" where BUS_LADING_BILL.IS_POUND='");
    	sql.append(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
    	sql.append("' ");
    	int isPoundCarNum=0;
    	list=RmProjectHelper.getCommonServiceInstance().doQuery(sql.toString());//今日过磅车辆
        if(list!=null && list.size()>0){
    		
    		BigDecimal com=(BigDecimal)list.get(0).get("num");
    		isPoundCarNum=com.intValue();
    	}
    	
    	sql.append(" and BUS_LADING_BILL.LADING_BILL_STATUS in ('");
    	sql.append(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06);
    	sql.append("','");
    	sql.append(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
    	sql.append("') ");
    	//今日已经过磅车辆
    	int isPounedCarNum=0;
    	list=RmProjectHelper.getCommonServiceInstance().doQuery(sql.toString());
        if(list!=null && list.size()>0){
    		
    		BigDecimal com=(BigDecimal)list.get(0).get("num");
    		isPounedCarNum=com.intValue();
    	}
    	
    	//查询今日装车车数
    	sql= sql.delete( 0, sql.length() );//清空
    	sql.append(bakSql);
    	sql.append(" where BUS_LADING_BILL.IS_LOADING='");
    	sql.append(ISystemConstant.DICTIONARY_RM_YES_NOT_1);
    	sql.append("' ");
    	int isLoadingCarNum=0;
    	//查询装车的车数
    	list=RmProjectHelper.getCommonServiceInstance().doQuery(sql.toString());
        if(list!=null && list.size()>0){
    		
    		BigDecimal com=(BigDecimal)list.get(0).get("num");
    		isLoadingCarNum=com.intValue();
    	}
    	
    	sql.append(" and BUS_LADING_BILL.LADING_BILL_STATUS in ('");
    	sql.append(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_06);
    	sql.append("','");
    	sql.append(ISystemConstant.DICTIONARY_LADING_BILL_STATUS_08);
    	sql.append("') ");
    	
    	int isLoadedCarNum=0;
    	list=RmProjectHelper.getCommonServiceInstance().doQuery(sql.toString());//查询已装车的车数
        if(list!=null && list.size()>0){
    		
    		BigDecimal com=(BigDecimal)list.get(0).get("num");
    		isLoadedCarNum=com.intValue();
    	}
    	
    	TodayCarTabVo vo=new TodayCarTabVo(todayCount, isPoundCarNum, isPounedCarNum, isLoadingCarNum, isLoadedCarNum);
    	
    	return vo;
    	
    }
    
    /**
     * 副产品今日价格
     * @param searchPara
     * @param orderStr
     * @param startIndex
     * @param size
     * @return
     */
    public List<PriceVo> listSubProPrice(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<PriceVo> lResult = priceDao.listSubProPrice(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 副产品价格趋势
     * @param searchPara
     * @param orderStr
     * @param startIndex
     * @param size
     * @return
     */
    public List<PriceVo> listPriceTrend(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
    	List<PriceVo> lResult = priceDao.listPriceTrend(searchPara, orderStr, startIndex, size);
    	return lResult;
    }
    
}
