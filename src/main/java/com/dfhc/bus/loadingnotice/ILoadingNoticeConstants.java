/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ILoadingNoticeConstants.java
 *
 * 功能描述：  装车通知单常量
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.loadingnotice;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 装车通知单 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface ILoadingNoticeConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "bus_loading_notice";
    public final static String TABLE_NAME_DISPLAY = "装车通知单";
    public final static String TABLE_PK = "id";
    //装车待分配列表操作标识
    public final static String LOADING_NOTICE_LOADING = "loadingNoticeLoading";
    public final static String LOADING_NOTICE_LOADING_LU = "loadingNoticeLoadingLu";
    public final static String BILL_TYPE_ONE = "billTypeOne";
    public final static String BILL_TYPE = "billType";
    public final static String BILL_TYPE_05 = "05";
    public final static String LIST_WAIT_SETTLE_LU = "listWaitSettleLu";
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
		put("jobTime","作业时间");
		put("dispatchTime","派单时间");
		put("licensePlate","装车车牌号");
		put("productId","产品ID");
		put("productName","产品名称");
		put("productModelNumber","产品型号");
		put("productBatchNumber","产品批次");
		put("productLevel","产品等级");
		put("productLevelId","产品等级ID");
		put("num","数量(吨)");
		put("gateNum","门号");
		put("positionNum","仓位号");
		put("toneNum","吨数");
		put("storemanId","保管员ID");
		put("storeman","保管员");
		put("forkliftMonitorId","叉车班长ID");
		put("forkliftMonitor","叉车班长");
		put("operationSupervisorId","保运主管ID");
		put("operationSupervisor","保运主管");
		put("status","状态");
		put("printDate","打印日期");
		put("forkliftTruckIsConfirm","叉车工是否确认");
		put("supervisorIsConfirm","保运主管是否确认");
		put("forkliftTruckConfirmTime","叉车工确认时间");
		put("supervisorConfirmTime","保运主管确认时间");
		put("truckInputBreakBagNum","叉车工录入破袋数量");
		put("supervisorInputBreakBagNum","保运主管录入破袋数量");
		put("actualDeliveryNumber","实际发货件数(袋)");
		put("isNeedWeigh","是否需要过磅");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
