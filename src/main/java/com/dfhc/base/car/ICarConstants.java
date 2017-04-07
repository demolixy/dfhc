/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ICarConstants.java
 *
 * 功能描述：  车辆信息常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.car;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 车辆信息 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface ICarConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "base_car";
    public final static String TABLE_NAME_DISPLAY = "车辆信息";
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
		put("truckNo","车牌号");
		put("grand","品牌");
		put("model","型号");
		put("color","颜色");
		put("modelSerial","车型系列");
		put("engineNo","发动机号");
		put("certDept","发证机关");
		put("emptyWeight","实际自重");
		put("owner","户主");
		put("driveNo","驾驶证号");
		put("driveType","驾驶证类别");
		put("contact","联系方式");
		put("gender","性别");
		put("idNo","身份证号");
		put("axleNum","轴数");
		put("picFront","车前图片");
		put("picBack","车后图片");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
