/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  IVehicleSafetyInspectionConstants.java
 *
 * 功能描述：  车辆安全检查常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.vehiclesafetyinspection;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 车辆安全检查 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IVehicleSafetyInspectionConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "bus_vehicle_safety_inspection";
    public final static String TABLE_NAME_DISPLAY = "车辆安全检查";
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
		put("ladingBillId","提货单ID");
		put("ladingBillCode","提货单号");
		put("licensePlate","车牌号码");
		put("checkMan","检查人");
		put("checkTime","检查日期时间");
		put("firstCheckMan","第一次检查人");
		put("secondCheckMan","第二次检查人");
		put("threeCheckMan","第三次检查人");
		put("firstcheckTime","第一次检查日期时间");
		put("secondcheckTime","第二次检查日期时间");
		put("threecheckTime","第三次检查日期时间");
		put("status","状态");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
