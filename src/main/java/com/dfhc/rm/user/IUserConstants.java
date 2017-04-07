/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  activiti工作流
 *
 * 文件名称：  IUserConstants.java
 *
 * 功能描述：  RM_USER常量
 * 
 * 版本历史：
 * 
 * 2016-12-07   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.rm.user;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * RM_USER 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IUserConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "rm_user";
    public final static String TABLE_NAME_DISPLAY = "RM_USER";
    public final static String TABLE_PK = "id";
    //列名汉化
    @SuppressWarnings({ "unchecked", "serial" })
    public final static Map<String, String> TABLE_COLUMN_DISPLAY = new CaseInsensitiveMap(){{
		put("usable_status","数据可用状态");
		put("create_time","创建时间");
		put("create_ip","创建IP");
		put("create_user_id","创建用户ID");
		put("create_user_name","创建用户姓名");
		put("modify_time","修改时间");
		put("modify_ip","修改IP");
		put("modify_user_id","修改用户ID");
		put("modify_user_name","修改用户姓名");
		put("delete_time","删除时间");
		put("delete_ip","删除IP");
		put("delete_flag","删除标签");
		put("delete_user_id","删除用户ID");
		put("delete_user_name","删除用户姓名");
		put("order_code","排序编码");
		put("attribute1","扩展字段1");
		put("attribute2","扩展字段2");
		put("attribute3","扩展字段3");
		put("attribute4","扩展字段4");
		put("attribute5","扩展字段5");
		put("id","用户ID");
		put("name","姓名");
		put("lockStatus","激活状态");
		put("loginId","账号");
		put("password","密码");
		put("authenType","认证类型");
		put("organizationId","所属组织机构");
		put("employeeId","员工ID");
		put("email","邮箱");
		put("adminType","用户权限类型");
		put("description","用户描述");
		put("agentStatus","代理状态");
		put("loginStatus","登录状态");
		put("lastLoginDate","最后登录时间");
		put("lastLoginIp","最后登录IP");
		put("loginSum","登录次数");
		put("lastCustomCss","LAST_CUSTOM_CSS");
		put("isAffix","是否状态标志");
		put("functionPermission","功能权限");
		put("dataPermission","数据权限");
		put("custom1","CUSTOM1");
		put("custom2","CUSTOM2");
		put("custom3","CUSTOM3");
		put("custom4","CUSTOM4");
		put("custom5","CUSTOM5");
		put("customXml","CUSTOM_XML");
		put("usableStatus","usableStatus");
		put("modifyDate","修改日期");
		put("modifyIp","修改IP");
		put("modifyUserId","修改用户Id");
		put("createTime","createTime");
		put("createUserName","createUserName");
		put("attribute1","扩展字段1");
		put("attribute2","扩展字段2");
		put("attribute3","扩展字段3");
		put("attribute4","扩展字段4");
		put("attribute5","扩展字段5");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
