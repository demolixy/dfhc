/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  IRowNumberConstants.java
 *
 * 功能描述：  排号表常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.rownumber;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 排号表 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IRowNumberConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "bus_row_number";
    //普通
    public final static String ROW_NUMBER_COMMON = "ROW_NUMBER_COMMON";
    //优先
    public final static String ROW_NUMBER_PRIOR = "ROW_NUMBER_PRIOR";
    //最大车数
    public final static String MAX_BUS_NUM_IN_FACTORY = "carNumber";
    
    /**
     * list排序 字段
     */
    public final static String ORDER_BY_FILED = "getRowDate";

    public final static String ORDER_BY_MODE_ASC = "asc";
    public final static String ORDER_BY_MODE_DESC = "desc";
    
    
    
    public final static String TABLE_NAME_DISPLAY = "排号表";
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
		put("ladingBillId","提货单id");
		put("rowType","类型");
		put("rowDate","日期");
		put("sequence","次序");
		put("status","状态");
		put("truckNo","车牌号码");
		put("phoneNumber","手机号");
		put("sequenceId","产品ID/产品分类ID");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
