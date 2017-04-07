/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ProductInventoryService.java
 *
 * 功能描述：  产品库存中间表服务
 * 
 * 版本历史：
 * 
 * 2017-03-09   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.productinventory.service;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.base.product.vo.ProductVo;
import com.dfhc.base.producttype.vo.ProductTypeVo;
import com.dfhc.bus.cardistributionplan.vo.CarDistributionPlanVo;
import com.dfhc.bus.ladingbill.ILadingBillConstants;
import com.dfhc.bus.ladingbill.vo.LadingBillVo;
import com.dfhc.bus.loadingnotice.vo.LoadingNoticeVo;
import com.dfhc.bus.productbatchnumber.vo.ProductBatchNumberVo;
import com.dfhc.bus.productinventory.IProductInventoryConstants;
import com.dfhc.bus.productinventory.dao.ProductInventoryDao;
import com.dfhc.bus.productinventory.vo.ProductInventoryVo;
/**
 * 产品库存中间表服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class ProductInventoryService implements IProductInventoryConstants {

    @Autowired
    /**
     * 产品库存中间表数据访问对象
     */
    private ProductInventoryDao productInventoryDao;

    
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回结果
     */
	public Map<String, Object> insert(ProductInventoryVo vo) {
		
		Map<String, Object> variable = new HashMap<String, Object>();
    	
        //检查插入vo合法性
        verifyInsertVo(vo);
        
        //验证数据是否存在
        variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        variable.put("productId", vo.getProductId());
        variable.put("productBatchNumber", vo.getProductBatchNumber());
        variable.put("storageLocation", vo.getStorageLocation());
        ProductInventoryVo productInventoryVo = getVo(variable, false);
        if(null != productInventoryVo){
        	variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
        	variable.put(ISystemConstant.AJAX_MESSAGE, "记录已经存在！" );
        	return variable;
        }
        
        vo.setOutboundNumber(vo.getOutboundNumber()==null?BigDecimal.ZERO:vo.getOutboundNumber());
        productInventoryDao.insert(vo);
        
        variable.clear();
        variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
    	variable.put(ISystemConstant.AJAX_MESSAGE, "新增成功 " );
		return variable;
	}
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(ProductInventoryVo[] vos) {
        String[] ids = productInventoryDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品库存中间表插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = productInventoryDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品库存中间表删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = productInventoryDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品库存中间表删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ProductInventoryVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = productInventoryDao.update(vo);
        //RmProjectHelper.log(LOG_TYPE_NAME, "产品库存中间表更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(ProductInventoryVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "产品库存中间表批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(ProductInventoryVo[] vos) {
        return null;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ProductInventoryVo get(String id) {
        ProductInventoryVo vo = productInventoryDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = productInventoryDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<ProductInventoryVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<ProductInventoryVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<ProductInventoryVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<ProductInventoryVo> lResult = productInventoryDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<ProductInventoryVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<ProductInventoryVo> lResult = productInventoryDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(ProductInventoryVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(ProductInventoryVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<ProductInventoryVo> vos) {
        for(ProductInventoryVo vo:vos){
           verifyUpdateVo(vo);
        }
        productInventoryDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<ProductInventoryVo> vos) {
        for(ProductInventoryVo vo:vos){
           verifyInsertVo(vo);
        }
        productInventoryDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        ProductInventoryVo vo = productInventoryDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(productInventoryDao.update(vo)!=1){
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
   public ProductInventoryVo getVo(Map<String, Object> params, boolean flag){
        List<ProductInventoryVo> list = list(params, null, 1, -1, flag);
	 
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
   }
   
   
   
   /**
    * 更改对应的 出库数量
    * @param request
    * @param vo
    * @param variable
    */
	public void updateOutNum(HttpServletRequest request,Map<String, Object> result,
			ProductBatchNumberVo productBatchNumberVo,String type,BigDecimal num) {
		result.clear();
		result.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		result.put("productId", productBatchNumberVo.getProductId());
		result.put("productBatchNumber",productBatchNumberVo.getProductBatchNumber());//批次号
		result.put("storageLocation", productBatchNumberVo.getPositionNum());//仓位号
		
		List<ProductInventoryVo> list = list(result, null);
		
		String  ExcepString="批次号为:"+productBatchNumberVo.getProductBatchNumber()+" 仓位号为:"+productBatchNumberVo.getPositionNum();
		
		if(list.isEmpty()){
	           throw new PjException("找不到该产品"+ExcepString+"对应库存数据,请维护");
		}

		ProductInventoryVo productInventoryVo = list.get(0);
		
		//num  要修改的值    如果为空  默认为批次的数量
		if(num == null){
			num=new  BigDecimal(productBatchNumberVo.getNum());
		}
		
		BigDecimal  storageNumber=productInventoryVo.getStorageNumber()==null?BigDecimal.ZERO:productInventoryVo.getStorageNumber();
		BigDecimal  outboundNumber=productInventoryVo.getOutboundNumber()==null?BigDecimal.ZERO:productInventoryVo.getOutboundNumber();
		
		if(ILadingBillConstants.FORMULA_ADD.equals(type)){
//			if(outboundNumber.add(num).compareTo(storageNumber) ==1){
//		           throw new PjException("该产品对应的库存可用数量 小于 预出库数量");
//			}
			//加法
			productInventoryVo.setOutboundNumber(outboundNumber.add(num));
		}else{
			//减法
			productInventoryVo.setOutboundNumber(outboundNumber.subtract(num));
		}
		
//		RmVoHelper.markModifyStamp(request, productInventoryVo);
		update(productInventoryVo);
	}



}
