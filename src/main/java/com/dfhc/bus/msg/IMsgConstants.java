/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  IMsgConstants.java
 *
 * 功能描述：  消息表常量
 * 
 * 版本历史：
 * 
 * 2017-03-27   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.msg;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 消息表 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IMsgConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "bus_msg";
    public final static String TABLE_NAME_DISPLAY = "消息表";
    public final static String TABLE_PK = "id";
    public final static String CUSTOMERIDS = "customerIds";
    public final static String CUSTOMERNAMES = "customerNames";
    public final static String LOGISTICSCOMPANYIDS = "logisticsCompanyIds";
    public final static String LOGISTICSCOMPANYNAMES = "logisticsCompanyNames";
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
		put("senderId","发送人ID");
		put("title","标题");
		put("content","内容");
		put("expirationTime","过期时间");
		put("messageType","消息类型");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
