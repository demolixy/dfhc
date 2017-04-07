/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ICarRequisitionPlanConstants.java
 *
 * 功能描述：  汽运调拨计划常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.carrequisitionplan;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 汽运调拨计划 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface ICarRequisitionPlanConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "bus_car_requisition_plan";
    //汽运调拨计划单号
    public final static String CAR_REQUISITION_PLAN_CODE = "CAR_REQUISITION_PLAN_CODE";
    public final static String TABLE_NAME_DISPLAY = "汽运调拨计划";
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
		put("planCode","计划单号");
		put("makeDate","制表日期");
		put("logisticsCompany","物流公司");
		put("logisticsCompanyId","物流公司ID");
		put("goodsName","货物名称");
		put("productId","产品名称");
		put("requisitionWarehouse","调拨库房");
		put("requisitionWarehouseId","调拨库房ID");
		put("shippingAddress","发货地址");
		put("num","数量(吨)");
		put("planCarNum","计划车数");
		put("plannedMileage","计划里程");
		put("factMileage","实际里程");
		put("valuationMileage","计价里程");
		put("yuanTon","运价(元/吨)");
		put("freightYuanTon","运费(元/吨)");
		put("unitPrice","货物单价(元/吨)");
		put("remark","备注");
		put("sendCarPlanImage","派车计划图片");
		put("remainderNum","剩余数量(吨)");
		put("remainderCarNum","剩余车数");
		put("shipperCode","发货公司");
		put("flowToId","流向名称");
		put("status","状态");
		put("invalidCarNumber","作废车数");
		put("invalidNumber","作废数量");
		put("shippingVehiclesNumber","已发运车数");
		put("shippingNumber","已发运数量");
		put("sendCarDate","发车日期");
		put("shipMode","发运方式");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
