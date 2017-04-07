/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ICustomerConstants.java
 *
 * 功能描述：  客户管理常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.customer;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 客户管理 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface ICustomerConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "base_customer";
    public final static String TABLE_NAME_DISPLAY = "客户管理";
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
		put("userId","用户ID");
		put("abbreviation","简称");
		put("indexCode","拼音码");
		put("regAddr","注册地址");
		put("officeAddr","办公地址");
		put("postAddr","通信地址");
		put("postCode","邮编");
		put("fax","传真");
		put("tel","公司电话");
		put("provinceId","省ID");
		put("provinceIdName","省");
		put("cityId","市ID");
		put("cityName","市");
		put("bank","开户银行");
		put("accountName","银行账号");
		put("taxNo","税号");
		put("legalPerson","法人");
		put("remark","备注");
		put("status","状态");
		put("regionId","区域ID");
		put("contacts","联系人");
		put("contactPhone","联系人电话");
		put("mainCode","主库代码");
		put("isByProduct","是否副产品客户");
		put("isMainProduct","是否主产品客户");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
