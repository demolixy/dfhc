/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  FreightService.java
 *
 * 功能描述：  运费维护服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.freight.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dfhc.base.freight.IFreightConstants;
import com.dfhc.base.freight.dao.FreightDao;
import com.dfhc.base.freight.vo.FreightVo;
import com.dfhc.base.shipper.service.ShipperService;
import com.dfhc.PjException;
import com.dfhc.util.StringHelper;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 运费维护服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class FreightService implements IFreightConstants {

    @Autowired
    /**
     * 运费维护数据访问对象
     */
    private FreightDao freightDao;
    @Autowired
    private ShipperService shipperService;
    @Autowired
    private FreightService freightService;
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(FreightVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = freightDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "运费维护插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(FreightVo[] vos) {
        String[] ids = freightDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "运费维护插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = freightDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "运费维护删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = freightDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "运费维护删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(FreightVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = freightDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "运费维护更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(FreightVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "运费维护批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(FreightVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<FreightVo> lInsert = new ArrayList<FreightVo>();
        List<FreightVo> lUpdate = new ArrayList<FreightVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new FreightVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new FreightVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public FreightVo get(String id) {
        FreightVo vo = freightDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = freightDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<FreightVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<FreightVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<FreightVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<FreightVo> lResult = freightDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<FreightVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<FreightVo> lResult = freightDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(FreightVo vo) {
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
    private void verifyInsertVo(FreightVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<FreightVo> vos) {
        for(FreightVo vo:vos){
           verifyUpdateVo(vo);
        }
        freightDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<FreightVo> vos) {
        for(FreightVo vo:vos){
           verifyInsertVo(vo);
        }
        freightDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        FreightVo vo = freightDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(freightDao.update(vo)!=1){
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
     * 插入
     * @param vo
     * @param request
     * @return
     */
    public String insert(FreightVo vo,HttpServletRequest request) {
    	
    	String yuanKmTon=request.getParameter("yuanKmTon");
    	
    	String mileage=request.getParameter("mileage");
    	
    	BigDecimal b =new BigDecimal(yuanKmTon);
    	BigDecimal c =new BigDecimal(mileage);
    	
    	BigDecimal yuanTon =b.multiply(c);
    	
        vaildUserMsg(vo,request);
        
        String id=freightDao.insert(vo);
        
    	return id;
       
    }
    /**
     * 验证发货方
     * @param vo
     * @param request
     */
	private void vaildUserMsg(FreightVo vo,HttpServletRequest request) {
		
	Map<String,Object> params=new HashMap<String, Object>();
	
		params.put("shipperCode", vo.getShipperCode());

		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);

		params.put("targetId",vo.getTargetId());
		
		int count=freightService.getCount(params);
		
		if(count>0)
    		
    		throw new PjException("请注意选择发货方！");	

//		params.put("yuanTon",vo.getYuanTon());
//		
//		if(vo.getYuanTon()==null || BigDecimal.ZERO.equals(vo.getYuanTon())){
//			
//			throw new PjException("运价(元/吨)不能为空！");
//		}
		params.put("yuanKmTon",vo.getYuanKmTon());
		
		if(vo.getYuanKmTon()==null || BigDecimal.ZERO.equals(vo.getYuanKmTon())){
			
			throw new PjException("运价(元/公里 吨)不能为空！");
		}
		params.put("mileage",vo.getMileage());
		
		if(vo.getMileage()==null || BigDecimal.ZERO.equals(vo.getMileage())){
			
			throw new PjException("运价(元/吨)不能为空！");
		}
		
	
		
	}
	
	public FreightVo doGetFreightVo(String shipperCode,String targetId,String targetType) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		params.put("shipperCode", shipperCode);
		params.put("targetId",targetId);
		params.put("targetType",targetType);
//		params.put("targetType",ISystemConstant.DICTIONARY_TARGET_TYPE_02);
		
		List<FreightVo>  list  =list(params, null);
		 
		if(list.isEmpty()){
    		throw new PjException("请维护对应的运费");
		}
		
		FreightVo  vo= list.get(0);
		
		if(vo.getYuanTon()==null ||  BigDecimal.ZERO.equals(vo.getYuanTon())){
    		throw new PjException("运价(元/吨)为空,请维护");
		}
		
		return  vo;
	}
}
