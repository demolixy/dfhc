/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ProductBatchNumberService.java
 *
 * 功能描述：  产品批次记录服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.productbatchnumber.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.loadingnotice.service.LoadingNoticeService;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.bus.productbatchnumber.IProductBatchNumberConstants;
import com.dfhc.bus.productbatchnumber.dao.ProductBatchNumberDao;
import com.dfhc.bus.productbatchnumber.vo.ProductBatchNumberVo;
import com.dfhc.bus.productinventory.service.ProductInventoryService;
import com.dfhc.bus.productinventory.vo.ProductInventoryVo;
import com.dfhc.PjException;
import com.dfhc.pub.server.SocketCacheVo;
import com.dfhc.pub.server.SocketQueue;
import com.dfhc.util.StringHelper;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.base.beans.factory.RmIdFactory;
import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.login.RmUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 产品批次记录服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class ProductBatchNumberService implements IProductBatchNumberConstants {

    @Autowired
    /**
     * 产品批次记录数据访问对象
     */
    private ProductBatchNumberDao productBatchNumberDao;
    @Autowired
    private ProductInventoryService  productInventoryService;
    @Autowired
    private   LoadingNoticeService  loadingNoticeService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(ProductBatchNumberVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = productBatchNumberDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品批次记录插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(ProductBatchNumberVo[] vos) {
        String[] ids = productBatchNumberDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品批次记录插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = productBatchNumberDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品批次记录删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = productBatchNumberDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品批次记录删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ProductBatchNumberVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = productBatchNumberDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品批次记录更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(ProductBatchNumberVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "产品批次记录批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(ProductBatchNumberVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<ProductBatchNumberVo> lInsert = new ArrayList<ProductBatchNumberVo>();
        List<ProductBatchNumberVo> lUpdate = new ArrayList<ProductBatchNumberVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new ProductBatchNumberVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new ProductBatchNumberVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ProductBatchNumberVo get(String id) {
        ProductBatchNumberVo vo = productBatchNumberDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = productBatchNumberDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<ProductBatchNumberVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<ProductBatchNumberVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<ProductBatchNumberVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<ProductBatchNumberVo> lResult = productBatchNumberDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<ProductBatchNumberVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<ProductBatchNumberVo> lResult = productBatchNumberDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(ProductBatchNumberVo vo) {
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
    private void verifyInsertVo(ProductBatchNumberVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<ProductBatchNumberVo> vos) {
        for(ProductBatchNumberVo vo:vos){
           verifyUpdateVo(vo);
        }
        productBatchNumberDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<ProductBatchNumberVo> vos) {
        for(ProductBatchNumberVo vo:vos){
           verifyInsertVo(vo);
        }
        productBatchNumberDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        ProductBatchNumberVo vo = productBatchNumberDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(productBatchNumberDao.update(vo)!=1){
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
     * 生成批次
     * @param request 页面请求
     * @param result 
     * @param ladingBillVo   提货单vo  更改实际发货数量 
     * @param loadingNoticeVo   通知单vo 
     * @param jqData  页面传来的产品批次 
     * @return 成功删除的记录数
     */
    public Map<String, Object> insertProductBath(HttpServletRequest request,Map<String, Object> result,LadingBillVo ladingBillVo,
    		LoadingNoticeVo loadingNoticeVo,String jqData) {
		List<ProductBatchNumberVo> listProductBatchNumberVo = JSONObject.parseArray(jqData,ProductBatchNumberVo.class);
		if(listProductBatchNumberVo.isEmpty()){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有批次数据");
			return result;
		}
		
		BigDecimal  actualShippingWeight=BigDecimal.ZERO;
		for(ProductBatchNumberVo batchNumberVo:listProductBatchNumberVo){
			if(batchNumberVo.getNum()>0){
				batchNumberVo.setId(null);
				
				batchNumberVo.setLoadingNoticeId(loadingNoticeVo.getId());
				
				batchNumberVo.setProductId(loadingNoticeVo.getProductId());
				
				batchNumberVo.setProductName(loadingNoticeVo.getProductName());
				
				batchNumberVo.setProductModelNumber(loadingNoticeVo.getProductModelNumber()==null?"":loadingNoticeVo.getProductModelNumber());
				
				//修改对应的库存
				productInventoryService.updateOutNum(request, result, batchNumberVo, ILadingBillConstants.FORMULA_ADD,null);

				RmVoHelper.markCreateStamp(request, batchNumberVo);
				insert(batchNumberVo);
				
				actualShippingWeight =actualShippingWeight.add(new BigDecimal(batchNumberVo.getNum()));
			}
		}
		
		//提货单的实际发货数量===产品批次数量累加
		ladingBillVo.setActualShippingWeight(actualShippingWeight);	
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		return result;
		
    }

    /**
     * 修改批次
     * @param request 页面请求
     * @param result 
     * @param ladingBillVo   提货单vo  更改实际发货数量 
     * @param loadingNoticeVo   通知单vo 
     * @param jqData  页面传来的产品批次 
     * @return 成功删除的记录数
     */
    public void updateProductBath(HttpServletRequest request,Map<String, Object> result,LadingBillVo ladingBillVo,
    		LoadingNoticeVo loadingNoticeVo,String jqData) {
    	//页面传来的产品批次
		List<ProductBatchNumberVo> listProductBatchNumberVo = JSONObject.parseArray(jqData,ProductBatchNumberVo.class);
		if(listProductBatchNumberVo.isEmpty()){
			result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			result.put(ISystemConstant.AJAX_MESSAGE, "没有批次数据");
			return ;
		}
		
		BigDecimal  actualShippingWeight=BigDecimal.ZERO;
		//循环页面的产品批次
		for(ProductBatchNumberVo batchNumberVo:listProductBatchNumberVo){
			if(batchNumberVo.getId().length()>19){
				//说明批次表里没有
				if(batchNumberVo.getNum()>0){
					//需要出库
					
					batchNumberVo.setId(null);
					
					batchNumberVo.setLoadingNoticeId(loadingNoticeVo.getId());
					
					batchNumberVo.setProductId(loadingNoticeVo.getProductId());
					
					batchNumberVo.setProductName(loadingNoticeVo.getProductName());
					
					batchNumberVo.setProductModelNumber(loadingNoticeVo.getProductModelNumber());
					
					//修改对应的库存
					productInventoryService.updateOutNum(request, result, batchNumberVo, ILadingBillConstants.FORMULA_ADD, null);

					//生成批次表
					RmVoHelper.markCreateStamp(request, batchNumberVo);
					insert(batchNumberVo);
					
					actualShippingWeight =actualShippingWeight.add(new BigDecimal(batchNumberVo.getNum()));
				}
			}else{
				//说明批次表里有这条数据
				if(batchNumberVo.getNum()>0){
					//需要出库
					//获取老的批次号
					ProductBatchNumberVo  oldBatchNumberVo=get(batchNumberVo.getId());
					if(oldBatchNumberVo.getNum().compareTo(batchNumberVo.getNum())  != 0){
						//老批次号的数量   != 页面传过来的数量
						
						//修改对应的库存   库存数量 +(新批次数量-旧批次数量)
						productInventoryService.updateOutNum(request, result, batchNumberVo, ILadingBillConstants.FORMULA_ADD,new BigDecimal(batchNumberVo.getNum()-oldBatchNumberVo.getNum()));

						//修改批次表
						batchNumberVo.setNum(batchNumberVo.getNum());
						RmVoHelper.markModifyStamp(request, batchNumberVo);
						update(batchNumberVo);
					}	
					
					actualShippingWeight =actualShippingWeight.add(new BigDecimal(batchNumberVo.getNum()));
				}else{
					//不需要出库 就删除这条记录
					delete(batchNumberVo.getId());
				}
			}

		}
		
		//提货单的实际发货数量===产品批次数量累加
		ladingBillVo.setActualShippingWeight(actualShippingWeight);	
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		return ;
		
    }

   
	   
    /**
     * 提货单提货失败时    回填库存出库数量
     * @param request
     * @param jqData
     * @return
     */
	public Map<String, Object> updateOutNum(HttpServletRequest request,Map<String, Object> result,LoadingNoticeVo oldLoadingNoticeVo) {
		result.clear();
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		result.put("loadingNoticeId",oldLoadingNoticeVo.getId());
		List<ProductBatchNumberVo> list = list(result, null);
		if(!list.isEmpty()){
			for(ProductBatchNumberVo vo:list){
				
				//修改对应的出库的出库数量
				productInventoryService.updateOutNum(request, result, vo, ILadingBillConstants.FORMULA_SUBTRACTION,null);

				//TODO   是否删除产品批次
				delete(vo.getId());
			}
		}
		
		//修改装车通知单
		//TODO  是否修改通知单的状态
		oldLoadingNoticeVo.setStatus(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS_05);
		loadingNoticeService.update(oldLoadingNoticeVo);
		
		result.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		return result;
	}
	
	
	
	
    /***
     * 二次过磅 调用   更改数量
     * @param  subNum   净重-实际发过重量
     * */
    public  void  updateNum(LoadingNoticeVo  loadingNoticeVo,BigDecimal  subNum){
    	Map<String, Object> result=new HashMap<String, Object>();
    	result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	result.put("loadingNoticeId", loadingNoticeVo.getId());
    	List<ProductBatchNumberVo>  list=list(result, " BUS_PRODUCT_BATCH_NUMBER.NUM desc");
    	
    	if(!list.isEmpty()){
    		BigDecimal yuNum=BigDecimal.ZERO;
    		BigDecimal oldNum=BigDecimal.ZERO;
    		BigDecimal sheYuNum=BigDecimal.ZERO;
    		BigDecimal changeNum=BigDecimal.ZERO;
    		//循环批次
    		for(ProductBatchNumberVo  vo:list){
    			oldNum=new BigDecimal(vo.getNum());
	   			yuNum=oldNum.add(subNum);
	   			if(yuNum.compareTo(oldNum)==0){
	   				break;
	   			}
    			if(yuNum.compareTo(oldNum)==1){
    				//加完之和大于原来的数量  就是增加出库的    修改库存的出库数量+
    				result.clear();
    				result.put("productId",vo.getProductId());
    		        result.put("productBatchNumber", vo.getProductBatchNumber());
    		        result.put("storageLocation", vo.getPositionNum());
    		        result.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    		        List<ProductInventoryVo>  beans=productInventoryService.list(result, " (BUS_PRODUCT_INVENTORY.STORAGE_NUMBER-BUS_PRODUCT_INVENTORY.OUTBOUND_NUMBER)  desc");
    		        ProductInventoryVo productInventoryVo=beans.get(0);
    		        //库存剩余数量
    		        sheYuNum=productInventoryVo.getStorageNumber().subtract(productInventoryVo.getOutboundNumber());
    		        if(sheYuNum.compareTo(subNum)==-1){
    		        	//库存数量 小于 要改变得数量
    		        	changeNum=sheYuNum;
    		        	subNum=subNum.subtract(sheYuNum);
    		        	vo.setNum(sheYuNum.longValue()+vo.getNum());
    		        }else{
    					changeNum=subNum;
    					subNum=BigDecimal.ZERO;
    		        	vo.setNum(yuNum.longValue());
    		        }
    			}else{
    				//加完之和小于原来的数量  就是减少出库的    修改库存的出库数量-
    				if(yuNum.compareTo(BigDecimal.ZERO)== -1){
    					//加完之和小于原来的数量
    					subNum=yuNum;
    					changeNum=BigDecimal.ZERO.subtract(oldNum);
    		        	vo.setNum(yuNum.longValue());
    				}else{
    					changeNum=subNum;
    					subNum=BigDecimal.ZERO;
    		        	vo.setNum(Long.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_0));
    				}
    			}
    		
    			//修改库存
				productInventoryService.updateOutNum(null, result, vo, ILadingBillConstants.FORMULA_ADD,changeNum);
				//修改批次
				update(vo);
				
    		}	
    	}
    	
    }

}
