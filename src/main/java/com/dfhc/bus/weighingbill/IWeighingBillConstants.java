/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  IWeighingBillConstants.java
 *
 * 功能描述：  过磅单常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.weighingbill;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 过磅单 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IWeighingBillConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "bus_weighing_bill";
    public final static String TABLE_NAME_DISPLAY = "过磅单";
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
		put("ladingBillId","司机提货单ID");
		put("ladingBillCode","提货单号");
		put("truckNo","车牌号");
		put("phoneNumber","手机号");
		put("productId","产品ID");
		put("productName","产品名称");
		put("productModelNumber","产品型号");
		put("tare","第一次检斤重量");
		put("grossWeight","第二次检斤重量");
		put("netWeight","净重");
		put("firstWeighingTime","第一次过磅时间");
		put("twoWeighingTime","第二次过磅时间");
		put("weighmanId","第一次司磅员ID");
		put("weighman","第一次司磅员");
		put("weighmanId2","第二次司磅员ID");
		put("weighman2","第二次司磅员");
		put("shipperCode","发货公司编码");
		put("shipper","发货公司");
		put("receivingUnit","收货单位");
		put("transportUnit","运输单位");
		put("timeToGoOut","出门时间");
		put("printDate","打印日期");
		put("storageTransportationMan","储运负责人");
		put("status","状态");
		put("storageTransportationId","储运负责人ID");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
