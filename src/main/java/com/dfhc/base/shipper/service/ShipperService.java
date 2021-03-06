/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ShipperService.java
 *
 * 功能描述：  发货方维护服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.shipper.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dfhc.base.shipper.IShipperConstants;
import com.dfhc.base.shipper.dao.ShipperDao;
import com.dfhc.base.shipper.vo.ShipperVo;
import com.dfhc.PjException;
import com.dfhc.util.StringHelper;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import com.dfhc.ISystemConstant;
import org.quickbundle.tools.helper.RmVoHelper;
/**
 * 发货方维护服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class ShipperService implements IShipperConstants {

    @Autowired
    /**
     * 发货方维护数据访问对象
     */
    private ShipperDao shipperDao;
    @Autowired
    /**
     * 发货方管理维护服务
     */
    private ShipperService shipperService;
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(ShipperVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = shipperDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "发货方维护插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(ShipperVo[] vos) {
        String[] ids = shipperDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "发货方维护插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = shipperDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "发货方维护删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = shipperDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "发货方维护删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(ShipperVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = shipperDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "发货方维护更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(ShipperVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "发货方维护批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(ShipperVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<ShipperVo> lInsert = new ArrayList<ShipperVo>();
        List<ShipperVo> lUpdate = new ArrayList<ShipperVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new ShipperVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new ShipperVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public ShipperVo get(String id) {
        ShipperVo vo = shipperDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = shipperDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<ShipperVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<ShipperVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<ShipperVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<ShipperVo> lResult = shipperDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<ShipperVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<ShipperVo> lResult = shipperDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(ShipperVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
        if(StringHelper.isEmpty(vo.getCode())){
           throw new PjException("编码为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(ShipperVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getCode())){
           throw new PjException("编码为空!");
        }	
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<ShipperVo> vos) {
        for(ShipperVo vo:vos){
           verifyUpdateVo(vo);
        }
        shipperDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<ShipperVo> vos) {
        for(ShipperVo vo:vos){
           verifyInsertVo(vo);
        }
        shipperDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        ShipperVo vo = shipperDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
        	
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(shipperDao.update(vo)!=1){
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
     * 查询默认的一条数据
     */
    public  ShipperVo  getIsDefaultShipperVo(HttpServletRequest request) {
        Map<String, Object> searchMap  = new HashMap<String, Object>();
        searchMap.put("isDefault",ISystemConstant.DICTIONARY_RM_YES_NOT_1);
        searchMap.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
        List<ShipperVo>  list  =list(searchMap,null);
        if(list.isEmpty()){
            throw new PjException("没有维护发货方!");
        }
        
        ShipperVo  shipperVo=list.get(0);
        return shipperVo;
    }
    
    public List<ShipperVo> list(){
    	Map<String, Object>  params = new HashMap<String, Object>();
    	params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	
    	return list(params, null);
    	
    }
    public void list(Model model){
    	
    	model.addAttribute("list", list());
    	
    }
    /**
     * 新增时验证发货方唯一
     * 
     */
    public String insert(ShipperVo vo,HttpServletRequest request) {
    	vaildUserMsg(vo,request);
    	
        String id = shipperDao.insert(vo);
        
        return id;
    }

	private void vaildUserMsg(ShipperVo vo, HttpServletRequest request) {
		Map<String,Object> params=new HashMap<String, Object>();
		
		params.put("name",vo.getName());
		
		params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		
		int count=shipperService.getCount(params);
		
		if(count>0)
    		
    		throw new PjException("发货方名称唯一！");	
		
		
	}
    
}
