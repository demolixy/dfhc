/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CustomerService.java
 *
 * 功能描述：  客户管理服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.customer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.python.modules.itertools.count;
import org.quickbundle.orgauth.custom.impl.RmCustomOrgService;
import org.quickbundle.orgauth.rmuser.service.impl.RmUserService;
import org.quickbundle.orgauth.rmuser.vo.RmUserVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.security.util.Password;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.base.customer.ICustomerConstants;
import com.dfhc.base.customer.dao.CustomerDao;
import com.dfhc.base.customer.vo.CustomerVo;
import com.dfhc.base.producttype.vo.ProductTypeVo;
import com.dfhc.base.requisitionwarehouse.vo.RequisitionWarehouseVo;
import com.dfhc.pub.service.AccountChangeService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.rm.user.service.UserService;
import com.dfhc.rm.user.vo.UserVo;
import com.dfhc.util.MD5Util;
import com.dfhc.util.StringHelper;
/**
 * 客户管理服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class CustomerService implements ICustomerConstants  {

    @Autowired
    /**
     * 客户管理数据访问对象
     */
    private CustomerDao customerDao;
   
    @Autowired
    
    private  AccountChangeService accountChangeService;
  
    @Autowired
    
    private UserService userService;
    @Autowired
    
    private RmUserService rmUserService;
    
    @Autowired
    
    private PubParamService pubParamService;
    
    @Autowired
    
    private CustomerService customerService;

	private String id;
    /**
     * 用户密码加密方式前台MD5加密后的密码串，后台随机生一个随机数再次加密，
     * 随机码存入扩展字段attribute2
     * 
     * 客户新增，
     * 1.增加客户管理记录，2、新增rmUserVo（邮箱、登录名验证唯一） 3、初始化账户
     * @param vo
     * @param request
     * @return
     */
    public String insert(CustomerVo vo,HttpServletRequest request){    	
   
    	//--------------begin 插入rmuser
	    String userName=request.getParameter("userName");//用户名
	    
	    String name=request.getParameter("name");//客户名称
    	
    	String email=request.getParameter("email");//邮箱
    	
    	String password=request.getParameter("hidPas");//一次加密后的密码
    	
    	String abbreviation=request.getParameter("abbreviation");
    	
    	String indexCode=request.getParameter("indexCode");
    	
    	vaildUserMsg(name,abbreviation,indexCode,userName,email, password); //验证用户是否存在
    	
		String pratyViewId = request.getParameter("view_id");
		
    	String partyTypeId = request.getParameter("party_type_id");
    	
    	String isInherit = request.getParameter("isInherit");
    	
    	String admin_type=ISystemConstant.DICTIONARY_RM_ADMIN_TYPE_5;
    	
    	String user_type=ISystemConstant.DICTIONARY_USER_TYPE_0;
    	
    	String usable_status=vo.getUsable_status();
    	
    	//插入rm_user
    	String userId=doInsertRmUser(usable_status,admin_type,user_type,name,userName, password, email, pratyViewId, partyTypeId, isInherit);
 
		//--------------end 插入rmUser
   	
//    	//验证手机
//    	String tel=vo.getTel();
//    	
//    	if(!pubParamService.checkTelephone(tel)){
//    		throw new PjException("请输入正确的手机号!");
//    	}
//    	//验证邮编
//    	String postCode=vo.getPostCode();
//    	
//    	if(!pubParamService.checkPostCode(postCode)){
//    		throw new PjException("请输入正确的邮编!");
//    	}

    	//初始化账户
    	accountChangeService.doInitAcc(userId, name);
    	
    	vo.setUserId(Long.valueOf(userId));
    	//插入customer
	    vo.setStatus(ISystemConstant.DICTIONARY_CUST_STATUS_1);//默认新增正式
	   
//	    vo.setUsable_status("1");//
    	
    	String id=customerDao.insert(vo);
    	
    	return id;
    }
    
    /**
     * 新增rmUserVo
     * 
     * @param admin_type 		权限类型
     * @param user_type	   		用户类型
     * @param name        		名称
     * @param loginId			用户名
     * @param psw		   		密码
     * @param email		    	邮箱
     * @param pratyViewId
     * @param partyTypeId 		
     * @param isInherit 
     * @return
     */

    public String doInsertRmUser(String usable_status,String admin_type,String user_type,String name,String loginId,String psw,String email,String pratyViewId ,String partyTypeId,String isInherit){
    	
    
    	String[] parentPartyIds = null;
    	String[] organizationNames = null;
    	
    	RmUserVo rmVo=new RmUserVo();
    	
    	rmVo.setLogin_id(loginId);
    	rmVo.setName(name);
    	rmVo.setEmail(email);
    	
    	rmVo.setUser_type(user_type);//用户类型
    	rmVo.setAdmin_type(admin_type);//用户权限
    	
		rmVo.setUsable_status(usable_status);
		
    	rmVo.setLock_status(ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);//激活
    	
    	rmVo.setPassword(psw);
    	
    	pubParamService.signRmUserPwd(rmVo);//加密用户密码
    	
//    	String uuid=UUID.randomUUID().toString();
//    	rmVo.setAttribute2(uuid);
//    	
//    	psw=MD5Util.md5Encode(psw,uuid);//加密后的密码
//    	
//    	rmVo.setPassword(psw);//密码
    	
      	//插入用户信息记录
        String   rmId = RmCustomOrgService.getInstance().insertParty(rmVo, pratyViewId, partyTypeId, parentPartyIds,organizationNames,isInherit);  //插入单条记录


     return rmId;
    }
    
 
    /**
     * 验证该用户名和邮箱是不是已被注册
     * @param userName 用户名
     * @param email	         邮箱
     * @param password 密码
     */
    private void vaildUserMsg(String name,String abbreviation,String indexCode,String userName,String email,String password){
    	
    	if(StringHelper.isEmpty(userName)){
    		
    		throw new PjException("用户名不可为空！");
    	}
    	if(StringHelper.isEmpty(email))
    		
    		throw new PjException("邮箱不能为空");
    	
    	if(StringHelper.isEmpty(password))
    		
    		throw  new PjException("密码不能为空");
    	
    	Map<String,Object> params=new HashMap<String, Object>();
    
    	params.put("loginId", userName);
    	
    	params.put("lockStatus", ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);
    	   	
    	int count=userService.getCount(params);
    	if(count>0)
    		
    		throw new PjException(userName+":该用户名称已被使用！");
   	
    	params.clear();
    	
    	params.put("name", name);
    	
    	params.put("lockStatus", ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);
    	
    	params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		 
    	count=userService.getCount(params);
    	if(count>0)
    		
    		throw new PjException(name+":该客户名称已被使用！");
    	params.clear();
    	
    	params.put("abbreviation", abbreviation);
    	
    	params.put("lockStatus", ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);
    	
    	params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		   	
    	count=customerService.getCount(params);
    	if(count>0)
    		
    		throw new PjException(abbreviation+":该客户简称已被使用！");
   	
    	params.clear();
    	
    	params.put("indexCode", indexCode);
    	
    	params.put("lockStatus", ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);
    	
    	params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		   	
    	count=customerService.getCount(params);
    	if(count>0)
    		
    		throw new PjException(indexCode+":该客户拼音码已被使用！");
   	
    	params.clear();
    	
    	params.put("email", email);
    	params.put("lockStatus", ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);
    	count=userService.getCount(params);
    	if(count>0)
    		throw new PjException(email+":该邮箱已被注册！");
    
  	
    }
    /**
     * 修改操作
     * 
     * @param vo
     * @param request
     * @return
     */
    public int update(CustomerVo vo,HttpServletRequest request){
    	
    	String customerId = vo.getId();
    	
    	CustomerVo oldCustomerVo = this.get(customerId);
    	
    	String userId = oldCustomerVo.getUserId();
    	
    	vo.setUserId(Long.valueOf(userId));
    	
//    	String userName=request.getParameter("userName");//用户名
  
    	String name=request.getParameter("name");//名称
     	
     	String email=request.getParameter("email");//邮箱
     	
     	String password=request.getParameter("hidPas");//密码
     	
     	String abbreviation=request.getParameter("abbreviation");
     	
     	String indexCode=request.getParameter("indexCode");
     	
     	vaildUserMsg1(vo,request);//验证
    	
     	doUpdateRmUser(name, password, email);
    	
   
//		String tel=vo.getTel();
//		    	
//		if(!pubParamService.checkTelephone(tel)){
//		    throw new PjException("请输入正确的手机号!");
//		}
//	 	//验证邮编
//    	String postCode=vo.getPostCode();
//    	
//    	if(!pubParamService.checkPostCode(postCode)){
//    		throw new PjException("请输入正确的邮编!");
//    	}
    	
	    vo.setStatus(ISystemConstant.DICTIONARY_CUST_STATUS_1);//默认新增正式
    	
    	int id=customerDao.update(vo);
    	
    	return id;   	
    	
    }
    /**
     * 更新
     * 
     * @param name
     * @param psw
     * @param email
     */
    private void doUpdateRmUser( String name,String psw, String email) {

    	
    	RmUserVo rmVo=new RmUserVo();
    	
    	rmVo.setName(name);
    	
    	rmVo.setEmail(email);

    	String uuid=UUID.randomUUID().toString();
    	
    	rmVo.setAttribute2(uuid);
    	
    	psw=MD5Util.md5Encode(psw,uuid);//加密后的密码
    	
    	rmVo.setPassword(psw);//密码   


}

    private void vaildUserMsg1(CustomerVo vo,HttpServletRequest request){
    	
    	Map<String,Object> params=new HashMap<String, Object>();
        
    	params.put("notId", vo.getId());
    	
        params.put("abbreviation", vo.getAbbreviation());
    	
    	params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	
    	int count =customerService.getCount(params);
    	if(count>0)
    		
    		throw new PjException(vo.getAbbreviation()+":该客户简称已被使用！");
   	
    	params.clear();
    	params.put("notId", vo.getId());
    	
        params.put("indexCode", vo.getIndexCode());
    	
    	params.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
    	
    	count =customerService.getCount(params);
    	if(count>0)
    		
    		throw new PjException(vo.getIndexCode()+":该客户拼音码已被使用！");
   	
    	params.clear();
    	
    }
	
    
   /**
    * 删除操作 更改状态
    * @param vo
    * @param request
    * @return
    */
    public int  doDelete(String id) {
    	
    	CustomerVo vo = this.get(id);
    	
    	String userId=vo.getUserId();
    	
    	RmUserVo rmUserVo = rmUserService.find(userId);
    	
    	rmUserVo.setLock_status( ISystemConstant.DICTIONARY_RM_LOCK_STATUS_0);
  	
    	rmUserService.update(rmUserVo);
    	
    	vo.setStatus(ISystemConstant.DICTIONARY_CUST_STATUS_2); //停用状态
    	
    	return customerDao.update(vo);
    			
	}
//    /**
//     * 多条删除操作 更改状态
//     * @param vo
//     * @param request
//     * @return
//     */
//    public int  doDelete(String[] ids) {
//        
//     	CustomerVo vo = this.get(id);
//     	
//     	String userId=vo.getUserId();
//     	
//     	RmUserVo rmUserVo = rmUserService.find(userId);
//     	
//     	rmUserVo.setLock_status( ISystemConstant.DICTIONARY_RM_LOCK_STATUS_0);
//     	
//     	rmUserService.update(rmUserVo);
//     	
//     	vo.setStatus(ISystemConstant.DICTIONARY_CUST_STATUS_2); //停用状态
//     	
//     	return customerDao.update(vo);
//     			
// 	}
	/**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(CustomerVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = customerDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "客户管理插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(CustomerVo[] vos) {
        String[] ids = customerDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "客户管理插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = customerDao.delete(id);
        
        RmProjectHelper.log(LOG_TYPE_NAME, "客户管理删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = customerDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "客户管理删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(CustomerVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = customerDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "客户管理更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(CustomerVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "客户管理批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(CustomerVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<CustomerVo> lInsert = new ArrayList<CustomerVo>();
        List<CustomerVo> lUpdate = new ArrayList<CustomerVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new CustomerVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new CustomerVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public CustomerVo get(String id) {
        CustomerVo vo = customerDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = customerDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<CustomerVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<CustomerVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<CustomerVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<CustomerVo> lResult = customerDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<CustomerVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<CustomerVo> lResult = customerDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(CustomerVo vo) {
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
    private void verifyInsertVo(CustomerVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }

    }

    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<CustomerVo> vos) {
        for(CustomerVo vo:vos){
           verifyUpdateVo(vo);
        }
        customerDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<CustomerVo> vos) {
        for(CustomerVo vo:vos){
           verifyInsertVo(vo);
        }
        customerDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        CustomerVo vo = customerDao.get(id);
        this.doDelete(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(customerDao.update(vo)!=1){
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
//        	   this.doDelete(id); 
           }
           return ids.length;
        }else{
           throw new PjException("ids 为空!");
        }
    }
 
    


    public CustomerVo getCustomerVo(String userId){
    	return customerDao.getCustomerVo(userId);
    			
    }
    //不要传送密码去页面
    public CustomerVo get(String id,HttpServletRequest request) {
    	
        CustomerVo vo = customerDao.get(id);
        
        vo.setPassword(null);
        
        return vo;
    }
    public List<CustomerVo> getI(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<CustomerVo> lResult = customerDao.getI(searchPara, orderStr, startIndex, size);
        return lResult;
    }
}
