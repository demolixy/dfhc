/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  IProductConstants.java
 *
 * 功能描述：  产品管理常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.product;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 产品管理 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface IProductConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "base_product";
    public final static String TABLE_NAME_DISPLAY = "产品管理";
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
		put("productTypeId","产品类别ID");
		put("productName","产品名称");
		put("indexCode","拼音码");
		put("spellBrevityCode","拼音简码");
		put("storeMode","储存方式");
		put("isCtrlBusNum","是否控制车数量");
		put("maxBusNumInFactory","厂内最大车数");
		put("maxBusNumInPound","过磅最大车数");
		put("isPound","是否过磅");
		put("isLoading","是否装车");
		put("mainCode","主库代码");
		put("main_secondary_product_flag","主副产品标志");
		put("bag_weight","袋装标重");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
