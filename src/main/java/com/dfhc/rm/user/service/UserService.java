/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  activiti工作流
 *
 * 文件名称：  UserService.java
 *
 * 功能描述：  RM_USER服务
 * 
 * 版本历史：
 * 
 * 2016-12-07   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.rm.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.orgauth.IOrgauthConstants;
import org.quickbundle.orgauth.custom.impl.RmCustomOrgService;
import org.quickbundle.orgauth.rmrole.service.IRmRoleService;
import org.quickbundle.orgauth.rmrole.util.IRmRoleConstants;
import org.quickbundle.orgauth.rmuser.service.IRmUserService;
import org.quickbundle.orgauth.rmuser.util.IRmUserConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.cache.RmSqlCountCache;
import org.quickbundle.tools.helper.RmStringHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.base.logisticscompany.vo.LogisticsCompanyVo;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.rm.user.IUserConstants;
import com.dfhc.rm.user.dao.UserDao;
import com.dfhc.rm.user.vo.UserVo;
import com.dfhc.util.StringHelper;
/**
 * RM_USER服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class UserService implements IUserConstants {

    @Autowired
    /**
     * RM_USER数据访问对象
     */
    private UserDao userDao;
    
    @Autowired
    /**
     *公共参数服务
     */
    private PubParamService pubParamService;
    
    /**
     * 得到Service对象 角色服务
     * 
     * @return Service对象
     */
    public IRmRoleService getRoleService() {
        return (IRmRoleService) RmBeanFactory.getBean(IRmRoleConstants.SERVICE_KEY);  //得到Service对象,受事务控制
    }

    /**
     * 获取框架UserService
     * @return
     */
    public IRmUserService getUserService() {
        return (IRmUserService) RmBeanFactory.getBean(IRmUserConstants.SERVICE_KEY);  //得到Service对象,受事务控制
    }
    

    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(UserVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = userDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "RM_USER插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(UserVo[] vos) {
        String[] ids = userDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "RM_USER插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = userDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "RM_USER删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = userDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "RM_USER删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(UserVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = userDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "RM_USER更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(UserVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "RM_USER批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(UserVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<UserVo> lInsert = new ArrayList<UserVo>();
        List<UserVo> lUpdate = new ArrayList<UserVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new UserVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new UserVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public UserVo get(String id) {
        UserVo vo = userDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = userDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<UserVo> list(Map<String, Object> searchPara, String orderStr) {
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
    public List<UserVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
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
    public List<UserVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<UserVo> lResult = userDao.list(searchPara, orderStr, startIndex, size, allColumn);
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
    public List<UserVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<UserVo> lResult = userDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(UserVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
        if(StringHelper.isEmpty(vo.getName())){
           throw new PjException("NAME为空!");
        }
        if(StringHelper.isEmpty(vo.getLockStatus())){
           throw new PjException("LOCK_STATUS为空!");
        }
        if(StringHelper.isEmpty(vo.getLoginId())){
           throw new PjException("LOGIN_ID为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(UserVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getName())){
           throw new PjException("NAME为空!");
        }	
        if(StringHelper.isEmpty(vo.getLockStatus())){
           throw new PjException("LOCK_STATUS为空!");
        }	
        if(StringHelper.isEmpty(vo.getLoginId())){
           throw new PjException("LOGIN_ID为空!");
        }	
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<UserVo> vos) {
        for(UserVo vo:vos){
           verifyUpdateVo(vo);
        }
        userDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<UserVo> vos) {
        for(UserVo vo:vos){
           verifyInsertVo(vo);
        }
        userDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        UserVo vo = userDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(userDao.update(vo)!=1){
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
     * 得到缓存中查询条件对应的count(*)记录数，如返回-1则执行查询
     * 
     * @param queryCondition
     * @return
     */
     public  int getRoleCount(String queryCondition) {
    	int count = RmSqlCountCache.getCount(IRmRoleConstants.TABLE_NAME, queryCondition);
    	if(count < 0) {
    		count = getRoleService().getRecordCount(queryCondition);
    		RmSqlCountCache.putCount(IRmRoleConstants.TABLE_NAME, queryCondition, count);
    	}
    	return count;
    }
    
     /**
      * 查询用户记录数
      * @param queryCondition
      * @return
      */
    public int getUserCount(String queryCondition) {
     	int count = RmSqlCountCache.getCount(IRmUserConstants.TABLE_NAME, queryCondition);
     	if(count < 0) {
     		count = getUserService().getRecordCount(queryCondition);
     		RmSqlCountCache.putCount(IRmUserConstants.TABLE_NAME, queryCondition, count);
     	}
     	return count;
     }

    
    
    /**
     * 后台新增用
     * @param vo
     * @param request
     * @return
     */
    public String  doInsertUser(org.quickbundle.orgauth.rmuser.vo.RmUserVo  vo,HttpServletRequest request){
    	
    	 pubParamService.signRmUserPwd(vo);//加密用户的密码
    	 
    	 validUserForm(vo, "insert");//验证用户输入的数据
    	 
    	 String id = null;
         if(IOrgauthConstants.Config.isUserRelationParty.value()) {
         	String pratyViewId = request.getParameter("view_id");
         	String partyTypeId = request.getParameter("party_type_id");
         	String isInherit = request.getParameter("isInherit");
         	String[] parentPartyIds =null;
         	String organizationIds = request.getParameter("organization_id");
         	if(!StringHelper.isEmpty(organizationIds)){
         		parentPartyIds = organizationIds.trim().split(",");
         	}
         	String[] organizationNames = request.getParameter("organization_name").split(",");
         	//vo.setOrganization_id("");
         	id = RmCustomOrgService.getInstance().insertParty(vo, pratyViewId, partyTypeId, parentPartyIds,organizationNames,isInherit);  //插入单条记录
         } else {
         	id = getUserService().insert(vo);  //插入单条记录
         }
    	
         return id;
    }
    
    /**
     * 更新用户信息
     * @param vo
     * @param request
     * @return
     */
    public int  doUpdateUser(org.quickbundle.orgauth.rmuser.vo.RmUserVo  vo,HttpServletRequest request){
    
     String password=request.getParameter("password");	
   	 pubParamService.signRmUserPwd(vo);//加密用户的密码
   	 
   	 validUserForm(vo, "update");//验证用户输入的数据
     int count = 0;
	 if(IOrgauthConstants.Config.isUserRelationParty.value()) {
	 		String pratyViewId = request.getParameter("view_id");
	 		String partyTypeId = request.getParameter("party_type_id");
	 		String isInherit = request.getParameter("isInherit");
	 		String[] parentPartyIds = RmStringHelper.parseToArrayIgnoreEmpty(request.getParameter("organization_id"), ",");
//	 		String[] parentPartyNames = RmStringHelper.parseToArrayIgnoreEmpty(request.getParameter("organization_name"), ",");
	 		String[] oldParentPartyIds = RmStringHelper.parseToArrayIgnoreEmpty(request.getParameter("oldParentPartyIds"), ",");
	 		String oldName = request.getParameter("oldName");
	 		vo.setAttribute1(request.getParameter("organization_name"));
	 		count = RmCustomOrgService.getInstance().updateParty(vo, pratyViewId,oldName, partyTypeId, parentPartyIds,oldParentPartyIds ,isInherit);  //更新单条记录
	 		//vo.setOrganization_id("");2015/4/28
	  } else {
	 		count = getUserService().update(vo);  //更新单条记录
	 	}
 	
   	
        return count;
   }
    
    
    
    
    
    /**
     * 验证页面用户输入数据合法性
     * @param vo
     * @param source：来源  source=insert:说明是新增
     */
    private void validUserForm(org.quickbundle.orgauth.rmuser.vo.RmUserVo  vo,String source){
    	
    	  String  login_id=vo.getLogin_id();//
    	  
    	  if(StringHelper.isEmpty(login_id)){
    		  throw new PjException("用户的登录名不可为空");
    	  }
    	  
    	  String psw=vo.getPassword();
    	  
    	  if(StringHelper.isEmpty(psw)){
    		  
    		  throw new PjException("用户密码不可为空");
    		  
    	   }
    	  
    	  String email=vo.getEmail();//获取用户名
    	  
    	  if(StringHelper.isEmpty(email)){
    		  
    		  throw new PjException("用户邮箱不可为空");
    	  }
    	  
    	  Map<String,Object> params=new HashMap<String,Object>();
    	  
    	  params.put("loginId", login_id);
    	  params.put("lockStatus", ISystemConstant.DICTIONARY_RM_LOCK_STATUS_1);//有效的
    	  
    	  List<UserVo> list= userDao.list(params, "", 1, 1, false);
    	  
		  if(list.size()!=0){
			  if("insert".equals(source)){//新增验证
			      throw new PjException("该用户名被占用！");
			  }else{//更新
				  
				  String id=vo.getId();
				  if(!list.get(0).getId().equals(id)){//说明不是该用户
					  
					  throw  new  PjException("用户名被占用");
					  
				  }
	    		  
	    	  }
		  }
		  
		  params.put("loginId", "");//清空用户名
		  
		  params.put("email", email);//
		  
		  list= userDao.list(params, "", 1, 1, false);
		  
		  if(list.size()!=0){
			  if("insert".equals(source)){//新增验证
			      throw new PjException("该用邮箱已被占用！");
			  }else{//更新
				  
				  String id=vo.getId();
				  if(!list.get(0).getId().equals(id)){//说明不是该用户
					  
					  throw  new  PjException("该用邮箱已被占用");
					  
				  }
	    		  
	    	  }
		  }
		  

    	
    }
    
    
    /**
     * 根据物流d  查出用户id
     * @param vos vo数组
     */
    public String getLogis(String id) {
    	
    	Map<String, Object>  map=new HashMap<String, Object>();
    	map.put("organizationId", id);
    	List<UserVo> list=list(map, null);
    	return list.get(0).getId();
    	
    }

    
}
