/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ProductTypeService.java
 *
 * 功能描述：  产品类别服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.producttype.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.base.product.service.ProductService;
import com.dfhc.base.product.vo.ProductVo;
import com.dfhc.base.producttype.IProductTypeConstants;
import com.dfhc.base.producttype.dao.ProductTypeDao;
import com.dfhc.base.producttype.vo.ProductTypeVo;
import com.dfhc.PjException;
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
 * 产品类别服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class ProductTypeService implements IProductTypeConstants {

    @Autowired
    /**
     * 产品类别数据访问对象
     */
    private ProductTypeDao productTypeDao;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(ProductTypeVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = productTypeDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品类别插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(ProductTypeVo[] vos) {
        String[] ids = productTypeDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品类别插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = productTypeDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品类别删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = productTypeDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品类别删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ProductTypeVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = productTypeDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "产品类别更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(ProductTypeVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "产品类别批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(ProductTypeVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<ProductTypeVo> lInsert = new ArrayList<ProductTypeVo>();
        List<ProductTypeVo> lUpdate = new ArrayList<ProductTypeVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new ProductTypeVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new ProductTypeVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ProductTypeVo get(String id) {
        ProductTypeVo vo = productTypeDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = productTypeDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<ProductTypeVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<ProductTypeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<ProductTypeVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<ProductTypeVo> lResult = productTypeDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<ProductTypeVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<ProductTypeVo> lResult = productTypeDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(ProductTypeVo vo) {
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
    private void verifyInsertVo(ProductTypeVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<ProductTypeVo> vos) {
        for(ProductTypeVo vo:vos){
           verifyUpdateVo(vo);
        }
        productTypeDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<ProductTypeVo> vos) {
        for(ProductTypeVo vo:vos){
           verifyInsertVo(vo);
        }
        productTypeDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        ProductTypeVo vo = productTypeDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(productTypeDao.update(vo)!=1){
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
     * 是否限制车数量
     * @param productId
     * @return
     */
    public boolean isLimitLength(String productId){
    	ProductTypeVo productTypeVo = get(productId);
    	if(ISystemConstant.DICTIONARY_RM_YES_NOT_0.equals(productTypeVo.getIsCtrlBusNum())){
    		return false;
    	}
    	return true;
    }
    
    /**
     * 获取产品类别名称 
     * @param productId 产品ID/产品分类ID
     * @return
     */
    public String getProductTypeName(String productId){
    	
    	ProductTypeVo productTypeVo = get(productId);
    	
    	if(null != productTypeVo){
    		
    		return productTypeVo.getProductTypeName();
    	}
    	
    	ProductVo productVo = productService.get(productId);
    	
    	productTypeVo = get(productVo.getProductTypeId());
    	
    	return productTypeVo.getProductTypeName();
    }
    /**
     * 验证增加时 产品类别、拼音简码
     * @param vo
    /**
     * 新增产品
     * @param vo
     * @param request
     * @return
     */
	public Map<String, Object> insert(ProductTypeVo vo, HttpServletRequest request) {
		
		
		Map<String, Object> variable = new HashMap<String, Object>();
		
		//验证
		checkInfo(variable, vo);
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
			return variable;
		}
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("spellBrevityCode", vo.getSpellBrevityCode());
		
		ProductTypeVo productTypeVo = getProductTypeVo(variable);
		
		if(null != productTypeVo){
			variable.clear();
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "产品拼音简码已存在！");
			return variable;
		}
		
		variable.remove("spellBrevityCode");
		
		variable.put("productTypeName", vo.getProductTypeName());
		
		productTypeVo= getProductTypeVo(variable);
		
		if(null != productTypeVo){
			variable.clear();
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "产品名称已存在！");
			return variable;
		}
		
		RmVoHelper.markCreateStamp(request, vo);
		insert(vo);
		
		RmProjectHelper.getCommonServiceInstance().doUpdate("INSERT INTO BASE_IMPULSE_SENDER ( SEQUENCE_NAME, UPDATE_DATE, CYCLE_TYPE, CURRENT_VALUE, CURRENT_FMT_VALUE, SEQUENCE_RULE, SEQUENCE_LEN, REMARK, USABLE_STATUS, CREATE_TIME, CREATE_IP, CREATE_USER_ID, CREATE_USER_NAME, MODIFY_TIME, MODIFY_IP, MODIFY_USER_ID, MODIFY_USER_NAME, DELETE_TIME, DELETE_IP, DELETE_FLAG, DELETE_USER_ID, DELETE_USER_NAME, ORDER_CODE, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5 ) VALUES ( '" + vo.getProductTypeName() + "', TIMESTAMP '2017-01-06 14:04:09', '3', 0, NULL, '%value', 3, '" + vo.getProductTypeName() + "签到排号次序，每天从1开始', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL )");
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "新增成功！");
		return variable;
	}

	/**
	 * 简单验证 后续补全
	 * @param variable
	 * @param vo
	 */
	//TODO 后续补全验证信息
	private void checkInfo(Map<String, Object> variable, ProductTypeVo vo) {
		if(null == vo){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "新增产品对象为空！");
			
			return ;
		}
		
		if(StringHelper.isEmpty(vo.getSpellBrevityCode())){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "拼音简码为空！");
			
			return ;
		}
		if(StringHelper.isEmpty(vo.getProductTypeName())){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "产品名称为空！");
			
			return ;
		}
		
		
	}
	
	public ProductTypeVo getProductTypeVo(Map<String, Object> variable, boolean flag){
		List<ProductTypeVo> list = list(variable, null, 1, -1, flag);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
		
	}
	public ProductTypeVo getProductTypeVo(Map<String, Object> variable){
		return getProductTypeVo(variable, false);
		
	}

	/**
	 * 产品修改
	 * @param vo
	 * @param request
	 * @return
	 */
	public Map<String, Object> update(ProductTypeVo vo, HttpServletRequest request) {
		Map<String, Object> variable = new HashMap<String, Object>();
		
		checkInfo(variable, vo);
		
		variable.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		variable.put("spellBrevityCode", vo.getSpellBrevityCode());
		
		ProductTypeVo productTypeVo = getProductTypeVo(variable);
		
		if(null != productTypeVo && !vo.getId().equals(productTypeVo.getId())){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "产品简码已存在！");
			
			return variable;
		}
		variable.remove("spellBrevityCode");
		
		variable.put("productTypeName", vo.getProductTypeName());
		
		productTypeVo = getProductTypeVo(variable);
		
		if(null != productTypeVo && !vo.getId().equals(productTypeVo.getId())){
			variable.clear();
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_FAIL);
			variable.put(ISystemConstant.AJAX_MESSAGE, "产品名称已存在！");
			return variable;
		}
		if(null == productTypeVo){
			productTypeVo = get(vo.getId());
			
		}
		if(!vo.getProductTypeName().equals(productTypeVo.getProductTypeName())){
			RmProjectHelper.getCommonServiceInstance().doUpdate("delete from BASE_IMPULSE_SENDER  where SEQUENCE_NAME='" + productTypeVo.getProductTypeName() +"'");
			RmProjectHelper.getCommonServiceInstance().doUpdate("INSERT INTO BASE_IMPULSE_SENDER ( SEQUENCE_NAME, UPDATE_DATE, CYCLE_TYPE, CURRENT_VALUE, CURRENT_FMT_VALUE, SEQUENCE_RULE, SEQUENCE_LEN, REMARK, USABLE_STATUS, CREATE_TIME, CREATE_IP, CREATE_USER_ID, CREATE_USER_NAME, MODIFY_TIME, MODIFY_IP, MODIFY_USER_ID, MODIFY_USER_NAME, DELETE_TIME, DELETE_IP, DELETE_FLAG, DELETE_USER_ID, DELETE_USER_NAME, ORDER_CODE, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5 ) VALUES ( '" + vo.getProductTypeName() + "', TIMESTAMP '2017-01-06 14:04:09', '3', 0, NULL, '%value', 3, '" + vo.getProductTypeName() + "签到排号次序，每天从1开始', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL )");
			
		}
		
		RmVoHelper.markModifyStamp(request, vo);
		update(vo);
		
		variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.AJAX_RESULT_SUCCESS);
		variable.put(ISystemConstant.AJAX_MESSAGE, "修改成功！");
		return variable;
	}
	
}
