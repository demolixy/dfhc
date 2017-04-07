/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  activiti工作流
 *
 * 文件名称：  IRoleConstants.java
 *
 * 功能描述：  RM_ROLE常量
 * 
 * 版本历史：
 * 
 * 2016-12-07   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.rm.role;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * RM_ROLE 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IRoleConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "rm_role";
    public final static String TABLE_NAME_DISPLAY = "RM_ROLE";
    public final static String TABLE_PK = "id";
    //列名汉化
    @SuppressWarnings({ "unchecked", "serial" })
    public final static Map<String, String> TABLE_COLUMN_DISPLAY = new CaseInsensitiveMap(){{
		put("usable_status","数据可用状态");
		put("create_time","create_time");
		put("create_ip","创建IP");
		put("create_user_id","创建用户ID");
		put("create_user_name","create_user_name");
		put("modify_time","modify_time");
		put("modify_ip","修改IP");
		put("modify_user_id","修改用户ID");
		put("modify_user_name","modify_user_name");
		put("delete_time","delete_time");
		put("delete_ip","delete_ip");
		put("delete_flag","delete_flag");
		put("delete_user_id","delete_user_id");
		put("delete_user_name","delete_user_name");
		put("order_code","排序编码");
		put("attribute1","扩展字段1");
		put("attribute2","扩展字段2");
		put("attribute3","扩展字段3");
		put("attribute4","扩展字段4");
		put("attribute5","扩展字段5");
		put("id","ID");
		put("roleCode","ROLE_CODE");
		put("name","NAME");
		put("enableStatus","ENABLE_STATUS");
		put("isSystemLevel","IS_SYSTEM_LEVEL");
		put("ownerOrgId","OWNER_ORG_ID");
		put("isRecursive","IS_RECURSIVE");
		put("matrixCode","MATRIX_CODE");
		put("description","DESCRIPTION");
		put("functionPermission","FUNCTION_PERMISSION");
		put("dataPermission","DATA_PERMISSION");
		put("usableStatus","USABLE_STATUS");
		put("modifyDate","MODIFY_DATE");
		put("modifyIp","MODIFY_IP");
		put("modifyUserId","MODIFY_USER_ID");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
