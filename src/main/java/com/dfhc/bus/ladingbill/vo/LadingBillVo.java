/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LadingBillVo.java
 *
 * 功能描述：  司机提货单值对象
 * 
 * 版本历史：
 * 
 * 2017-01-05   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.ladingbill.vo;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 司机提货单值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class LadingBillVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * orderPlanId 表示：订单ID或计划ID
     */
    private  String  orderPlanId;
    /**
     * orderPlanCode 表示：订单单号或计划单号
     */
    private  String  orderPlanCode;
    /**
     * ladingBillCode 表示：提货单号
     */
    private  String  ladingBillCode;
    /**
     * billType 表示：单据类型
     */
    private  String  billType;
    /**
     * shipMode 表示：交货地点
     */
    private  String  deliveryStation;
	/**
     * shipMode 表示：发运方式
     */
    private  String  shipMode;
    /**
     * shipModeId 表示：发运方式ID
     */
    private  String  shipModeId;
    /**
     * signTime 表示：签到时间
     */
    private String signTime;

	/**
     * productType 表示：产品类型
     */
    private  String  productType;
    /**
     * productTypeId 表示：产品类型ID
     */
    private  String  productTypeId;
    /**
     * productId 表示：产品ID
     */
    private  String  productId;
    /**
     * productName 表示：产品名称
     */
    private  String  productName;
    /**
     * productModelNumber 表示：产品型号
     */
    private  String  productModelNumber;
    /**
     * phoneNumber 表示：手机号
     */
    private  String  phoneNumber;
    /**
     * licensePlate 表示：车牌号码
     */
    private  String  licensePlate;
	/**
     * carType 表示：车型
     */
    private  String  carType;
    /**
     * driverName 表示：司机姓名
     */
    private  String  driverName;
    /**
     * driverIdCardNumber 表示：司机身份证号
     */
    private  String  driverIdCardNumber;
    /**
     * ladingBillStatus 表示：状态
     */
    private  String  ladingBillStatus;
    /**
     * auditTime 表示：审核时间
     */
    private  java.sql.Date  auditTime;
    /**
     * auditPerson 表示：审核人
     */
    private  String  auditPerson;
    /**
     * auditPersonId 表示：审核人ID
     */
    private  Long  auditPersonId;
    /**
     * estimatedLoadingTime 表示：预计装车时间
     */
    private  java.sql.Date  estimatedLoadingTime;
    /**
     * takeNoTime 表示：取号时间
     */
    private  java.sql.Date  takeNoTime;
    /**
     * deliveryWarehouse 表示：发货仓库
     */
    private  String deliveryWarehouse;
	/**
     * actualShippingWeight 表示：实际发货重量
     */
    private  java.math.BigDecimal  actualShippingWeight;
    /**
     * deliveryNumber 表示：实际发货件数
     */
    private  String deliveryNumber;
    /**
     * receivingUnit 表示：收货单位
     */
    private  String receivingUnit;
	/**
     * nuclearLoad 表示：核载量
     */
    private  java.math.BigDecimal  nuclearLoad;
    /**
     * inGate 表示：进门门岗
     */
    private  String  inGate;
    /**
     * inTime 表示：进门时间
     */
    private  java.sql.Timestamp  inTime;
    /**
     * emptyWeight 表示：空车磅
     */
    private  java.math.BigDecimal  emptyWeight;
    /**
     * emptyPoundTime 表示：空车过磅时间
     */
    private  java.sql.Date  emptyPoundTime;
    /**
     * fullWeight 表示：重车磅
     */
    private  java.math.BigDecimal  fullWeight;
    /**
     * fullPoundTime 表示：重车过磅时间
     */
    private  java.sql.Date  fullPoundTime;
    /**
     * outGate 表示：出门门岗
     */
    private  String  outGate;
    /**
     * outTime 表示：出门时间
     */
    private  java.sql.Timestamp  outTime;
    /**
     * inputPersonId 表示：录入人ID
     */
    private  Long  inputPersonId;
    /**
     * inputPerson 表示：录入人姓名
     */
    private  String  inputPerson;
    /**
     * inputTime 表示：录入时间
     */
    private  java.sql.Date  inputTime;
    /**
     * invalidPersonId 表示：作废人ID
     */
    private  Long  invalidPersonId;
    /**
     * invalidPerson 表示：作废人
     */
    private  String  invalidPerson;
    /**
     * invalidTime 表示：作废时间
     */
    private  java.sql.Date  invalidTime;
    /**
     * invalidReason 表示：作废原因
     */
    private  String  invalidReason;
    /**
     * isBringBelt 表示：是否带托盘
     */
    private  String  isBringBelt;
    /**
     * isLipolysis 表示：是否有溶脂要求
     */
    private  String  isLipolysis;
    /**
     * isSince 表示：是否为自提
     */
    private  String  isSince;
    /**
     * isUrgent 表示：是否为加急
     */
    private  String  isUrgent;
    /**
     * plannedMileage 表示：计划里程
     */
    private  java.math.BigDecimal  plannedMileage;
    /**
     * factMileage 表示：实际里程
     */
    private  java.math.BigDecimal  factMileage;
    /**
     * valuationMileage 表示：计价里程
     */
    private  java.math.BigDecimal  valuationMileage;
    /**
     * factValuationMileage 表示：实际计价里程
     */
    private  java.math.BigDecimal  factValuationMileage;
    /**
     * settlementFreight 表示：结算运价
     */
    private  java.math.BigDecimal  settlementFreight;
    /**
     * yuanKmTon 表示：运价系数(元/吨公里)
     */
    private  java.math.BigDecimal  yuanKmTon;
    /**
     * freight 表示：运费
     */
    private  java.math.BigDecimal  freight;
    /**
     * receivingPerson 表示：收货人
     */
    private  String  receivingPerson;
    /**
     * receivingAddress 表示：收货地址
     */
    private  String  receivingAddress;
    /**
     * receivingPersonPhone 表示：收货人联系方式
     */
    private  String  receivingPersonPhone;
    /**
     * freightSettlementId 表示：运费结算ID
     */
    private  String  freightSettlementId;
    /**
     * invoiceSettlementId 表示：发票结算ID
     */
    private  String  invoiceSettlementId;
    /**
     * shipperCode 表示：发货公司编码
     */
    private  String  shipperCode;
    /**
     * printTime 表示：打印时间
     */
    private  java.sql.Date  printTime;
    /**
     * printTimes 表示：打印次数
     */
    private  Long  printTimes;
    /**
     * remark 表示：备注
     */
    private  String  remark;
    /**
     * logisticsSpecialistId 表示：物流专员ID
     */
    private  Long  logisticsSpecialistId;
    /**
     * logisticsSpecialistName 表示：物流专员
     */
    private  String  logisticsSpecialistName;
    /**
     * importTime 表示：导入时间
     */
    private  java.sql.Date  importTime;
    /**
     * distributionTime 表示：分配物流时间
     */
    private  java.sql.Date  distributionTime;
    /**
     * logisticsCompanyClerkId 表示：物流公司业务员ID
     */
    private  Long  logisticsCompanyClerkId;
    /**
     * logisticsCompanyClerk 表示：物流公司业务员
     */
    private  String  logisticsCompanyClerk;
    /**
     * allocationVehicleTime 表示：分车时间
     */
    private  java.sql.Timestamp  allocationVehicleTime;
    /**
     * isPound 表示：是否过磅
     */
    private  String  isPound;
    /**
     * isLoading 表示：是否装车
     */
    private  String  isLoading;
    /**
     * usable_status 表示: 数据可用状态
     */
    private String usable_status;

    /**
     * create_time 表示: 创建时间
     */
    private Timestamp create_time;

    /**
     * create_ip 表示: 创建IP
     */
    private String create_ip;

    /**
     * create_user_id 表示: 创建用户ID
     */
    private String create_user_id;

    /**
     * create_user_name 表示: 创建用户名
     */
    private String create_user_name;

    /**
     * modify_time 表示: 修改时间
     */
    private Timestamp modify_time;

    /**
     * modify_ip 表示: 修改IP
     */
    private String modify_ip;

    /**
     * modify_user_id 表示: 修改用户ID
     */
    private String modify_user_id;

    /**
     * modify_user_name 表示: 修改用户名
     */
    private String modify_user_name;

    /**
     * delete_time 表示: 删除时间
     */
    private Timestamp delete_time;

    /**
     * delete_ip 表示: 删除ip
     */
    private String delete_ip;

    /**
     * delete_flag 表示: 删除标志
     */
    private String delete_flag;

    /**
     * delete_user_id 表示: 删除用户id
     */
    private String delete_user_id;

    /**
     * delete_user_name 表示: 删除用户名
     */
    private String delete_user_name;

    /**
     * order_code 表示: 排序编码
     */
    private String order_code;

    /**
     * attribute1 表示: 扩展字段1
     */
    private String attribute1;

    /**
     * attribute2 表示: 扩展字段2
     */
    private String attribute2;

    /**
     * attribute3 表示: 扩展字段3
     */
    private String attribute3;

    /**
     * attribute4 表示: 扩展字段4
     */
    private String attribute4;

    /**
     * attribute5 表示: 扩展字段5
     */
    private String attribute5;
    
    /**
     * 实际发货金额
     */
    private BigDecimal actualShippingAmount;
    /**
     * 计划发货金额
     */
    private BigDecimal plannedShipmentAmount;
    
    /**
     * 产品单价
     */
    private BigDecimal productUnitPrice;
    
    /**
     * 发运方式编码
     */
    private String shipModeCode;
    //结束vo的属性
        
    
    
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 计划发运量
     */
    private String plannedVolume;
    /**
     * 剩余发运量
     */
    private String remarkVolume;
    /**
     * 
     * 剩余车数
     */
    private String remainderCarNum;
    /**
     * 制表日期
     */    
    private Date tabDate;
    /**
     * 订单（合同）编号
     */
    private String orderNumber;
    /**
     * 行号
     */
    private String lineNumber;
	/**
     * 提货开始日期
     */
    private Date deliveryStartDate;
    /**
     * 提货结束日期
     */
    private Date deliveryEndDate;
    
    /**
     * 客户id
     */
    private String customerId;
    
    /**
     * 标识  用来区分发票结算id是否为空
     */
    private String settlementFlag;
 
    /**
     * 物流公司id
     */
    private String logisticsCompanyId;
    /**
     * 物流公司
     */
    private String logisticsCompany;
    
    /**
     * 安检时间
     */
    private Timestamp safeCheckTime;
    /**
     * 安检状态
     */
    private String safeCheckStatus;
    /**
     * 区域
     */
    private String regionName;
    /**
     * 等级
     */
    private String grade;
    /**
     * 省份
     */
    private String province;
    /**
     * 地区
     */
    private String region;
    /**
     * 序号
     */
    private String sequenceNumber;
    /**
     * 接收订单时间
     */
    private String receiveTime;
    /**
     * 价格
     */
    private String price;
    /**
     * 取整价格
     */
    private String roundingUnit;
    /**
     * 结算单价
     */
    private String settlementPrice;
    /**
     * 装车状态 
     */
    private String ladingBillNoticeStatus;
    
	//开始vo的setter和getter方法
    
    /**
     * 获得装车状态 
     * 
     * @return 装车状态 
     */
    public String getLadingBillNoticeStatus() {
		return ladingBillNoticeStatus;
	}
    /**
     * 设置装车状态 
     * 
     * @param ladingBillNoticeStatus 装车状态 
     */
	public void setLadingBillNoticeStatus(String ladingBillNoticeStatus) {
		this.ladingBillNoticeStatus = ladingBillNoticeStatus;
	}
	/**
     * 获得结算单价
     * 
     * @return 结算单价
     */
    public String getSettlementPrice() {
		return settlementPrice;
	}
    /**
     * 设置结算单价
     * 
     * @param settlementPrice 结算单价
     */
	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	/**
     * 获得取整价格
     * 
     * @return 取整价格
     */
    public String getRoundingUnit() {
		return roundingUnit;
	}
    /**
     * 设置取整价格
     * 
     * @param roundingUnit 取整价格
     */
	public void setRoundingUnit(String roundingUnit) {
		this.roundingUnit = roundingUnit;
	}
	/**
     * 获得价格
     * 
     * @return 价格
     */
	public String getPrice() {
		return price;
	}
	 /**
     * 设置价格
     * 
     * @param price 价格
     */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
     * 获得接收订单时间
     * 
     * @return 接收订单时间
     */
    public String getReceiveTime() {
		return receiveTime;
	}
    /**
     * 设置接收订单时间
     * 
     * @param receiveTime 接收订单时间
     */
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
     * 获得序号
     * 
     * @return 序号
     */
    public String getSequenceNumber() {
		return sequenceNumber;
	}
    /**
     * 设置序号
     * 
     * @param sequenceNumber 序号
     */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
     * 获得地区
     * 
     * @return 地区
     */
    public String getRegion() {
		return region;
	}
    /**
     * 设置地区
     * 
     * @param region 地区
     */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
     * 获得省份
     * 
     * @return 省份
     */
	public String getProvince() {
		return province;
	}
	/**
     * 设置省份
     * 
     * @param province 省份
     */
	public void setProvince(String province) {
		this.province = province;
	}
    /**
     * 获得等级
     * 
     * @return 等级
     */
	public String getGrade() {
		return grade;
	}
	/**
     * 设置等级
     * 
     * @param grade 等级
     */
	public void setGrade(String grade) {
		this.grade = grade;
	}
    /**
     * 获得区域
     * 
     * @return 区域
     */
    public String getRegionName() {
		return regionName;
	}
    /**
     * 设置区域
     * 
     * @param regionName 区域
     */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

    /**
     * 获得数据可用状态
     * 
     * @return 数据可用状态
     */
    public String getUsable_status(){
        return usable_status;
    }
	
    /**
     * 设置数据可用状态
     * 
     * @param usable_status 数据可用状态
     */
    public void setUsable_status(String usable_status){
        this.usable_status = usable_status;
    }
	
    /**
     * 获得创建时间
     * 
     * @return create_time
     */
    public Timestamp getCreate_time(){
        return create_time;
    }
	
    /**
     * 设置create_time
     * 
     * @param create_time create_time
     */
    public void setCreate_time(Timestamp create_time){
        this.create_time = create_time;
    }
	
    /**
     * 获得创建IP
     * 
     * @return 创建IP
     */
    public String getCreate_ip(){
        return create_ip;
    }
	
    /**
     * 设置创建IP
     * 
     * @param create_ip 创建IP
     */
    public void setCreate_ip(String create_ip){
        this.create_ip = create_ip;
    }
	
    /**
     * 获得创建用户ID
     * 
     * @return 创建用户ID
     */
    public String getCreate_user_id(){
        return create_user_id;
    }
	
    /**
     * 设置创建用户ID
     * 
     * @param create_user_id 创建用户ID
     */
    public void setCreate_user_id(String create_user_id){
        this.create_user_id = create_user_id;
    }
	
    /**
     * 获得create_user_name
     * 
     * @return create_user_name
     */
    public String getCreate_user_name(){
        return create_user_name;
    }
	
    /**
     * 设置create_user_name
     * 
     * @param create_user_name create_user_name
     */
    public void setCreate_user_name(String create_user_name){
        this.create_user_name = create_user_name;
    }
	
    /**
     * 获得modify_time
     * 
     * @return modify_time
     */
    public Timestamp getModify_time(){
        return modify_time;
    }
	
    /**
     * 设置modify_time
     * 
     * @param modify_time modify_time
     */
    public void setModify_time(Timestamp modify_time){
        this.modify_time = modify_time;
    }
	
    /**
     * 获得修改IP
     * 
     * @return 修改IP
     */
    public String getModify_ip(){
        return modify_ip;
    }
	
    /**
     * 设置修改IP
     * 
     * @param modify_ip 修改IP
     */
    public void setModify_ip(String modify_ip){
        this.modify_ip = modify_ip;
    }
	
    /**
     * 获得修改用户ID
     * 
     * @return 修改用户ID
     */
    public String getModify_user_id(){
        return modify_user_id;
    }
	
    /**
     * 设置修改用户ID
     * 
     * @param modify_user_id 修改用户ID
     */
    public void setModify_user_id(String modify_user_id){
        this.modify_user_id = modify_user_id;
    }
	
    /**
     * 获得modify_user_name
     * 
     * @return modify_user_name
     */
    public String getModify_user_name(){
        return modify_user_name;
    }
	
    /**
     * 设置modify_user_name
     * 
     * @param modify_user_name modify_user_name
     */
    public void setModify_user_name(String modify_user_name){
        this.modify_user_name = modify_user_name;
    }
	
    /**
     * 获得delete_time
     * 
     * @return delete_time
     */
    public Timestamp getDelete_time(){
        return delete_time;
    }
	
    /**
     * 设置delete_time
     * 
     * @param delete_time delete_time
     */
    public void setDelete_time(Timestamp delete_time){
        this.delete_time = delete_time;
    }
	
    /**
     * 获得delete_ip
     * 
     * @return delete_ip
     */
    public String getDelete_ip(){
        return delete_ip;
    }
	
    /**
     * 设置delete_ip
     * 
     * @param delete_ip delete_ip
     */
    public void setDelete_ip(String delete_ip){
        this.delete_ip = delete_ip;
    }
	
    /**
     * 获得delete_flag
     * 
     * @return delete_flag
     */
    public String getDelete_flag(){
        return delete_flag;
    }
	
    /**
     * 设置delete_flag
     * 
     * @param delete_flag delete_flag
     */
    public void setDelete_flag(String delete_flag){
        this.delete_flag = delete_flag;
    }
	
    /**
     * 获得delete_user_id
     * 
     * @return delete_user_id
     */
    public String getDelete_user_id(){
        return delete_user_id;
    }
	
    /**
     * 设置delete_user_id
     * 
     * @param delete_user_id delete_user_id
     */
    public void setDelete_user_id(String delete_user_id){
        this.delete_user_id = delete_user_id;
    }
	
    /**
     * 获得delete_user_name
     * 
     * @return delete_user_name
     */
    public String getDelete_user_name(){
        return delete_user_name;
    }
	
    /**
     * 设置delete_user_name
     * 
     * @param delete_user_name delete_user_name
     */
    public void setDelete_user_name(String delete_user_name){
        this.delete_user_name = delete_user_name;
    }
	
    /**
     * 获得排序编码
     * 
     * @return 排序编码
     */
    public String getOrder_code(){
        return order_code;
    }
	
    /**
     * 设置排序编码
     * 
     * @param order_code 排序编码
     */
    public void setOrder_code(String order_code){
        this.order_code = order_code;
    }
	
    /**
     * 获得扩展字段1
     * 
     * @return 扩展字段1
     */
    public String getAttribute1(){
        return attribute1;
    }
	
    /**
     * 设置扩展字段1
     * 
     * @param attribute1 扩展字段1
     */
    public void setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
	
    /**
     * 获得扩展字段2
     * 
     * @return 扩展字段2
     */
    public String getAttribute2(){
        return attribute2;
    }
	
    /**
     * 设置扩展字段2
     * 
     * @param attribute2 扩展字段2
     */
    public void setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
	
    /**
     * 获得扩展字段3
     * 
     * @return 扩展字段3
     */
    public String getAttribute3(){
        return attribute3;
    }
	
    /**
     * 设置扩展字段3
     * 
     * @param attribute3 扩展字段3
     */
    public void setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
	
    /**
     * 获得扩展字段4
     * 
     * @return 扩展字段4
     */
    public String getAttribute4(){
        return attribute4;
    }
	
    /**
     * 设置扩展字段4
     * 
     * @param attribute4 扩展字段4
     */
    public void setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
	
    /**
     * 获得扩展字段5
     * 
     * @return 扩展字段5
     */
    public String getAttribute5(){
        return attribute5;
    }
	
    /**
     * 设置扩展字段5
     * 
     * @param attribute5 扩展字段5
     */
    public void setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
	
    /**
     * 获取ID 
     * @return ID
     */
    public  String  getId(){
        return id;
    }
    /**
     * 设置ID
     * @param id ID
     */
    public  void  setId(String id){
        this.id = id;
    }
    /**
     * 获取订单ID或计划ID 
     * @return 订单ID或计划ID
     */
    public  String  getOrderPlanId(){
        return orderPlanId;
    }
    /**
     * 设置订单ID或计划ID
     * @param orderPlanId 订单ID或计划ID
     */
    public  void  setOrderPlanId(String orderPlanId){
        this.orderPlanId = orderPlanId;
    }

	 /**
     * 获取交货地点
     * @return 交货地点
     */
    public String getDeliveryStation() {
		return deliveryStation;
	}
    /**
     * 设置交货地点
     * @param deliveryStation 交货地点
     */
	public void setDeliveryStation(String deliveryStation) {
		this.deliveryStation = deliveryStation;
	}
    /**
     * 获取订单单号或计划单号 
     * @return 订单单号或计划单号
     */
    public  String  getOrderPlanCode(){
        return orderPlanCode;
    }
    /**
     * 设置订单单号或计划单号
     * @param orderPlanCode 订单单号或计划单号
     */
    public  void  setOrderPlanCode(String orderPlanCode){
        this.orderPlanCode = orderPlanCode;
    }
    /**
     * 获取提货单号 
     * @return 提货单号
     */
    public  String  getLadingBillCode(){
        return ladingBillCode;
    }
    /**
     * 设置提货单号
     * @param ladingBillCode 提货单号
     */
    public  void  setLadingBillCode(String ladingBillCode){
        this.ladingBillCode = ladingBillCode;
    }
    /**
     * 获取单据类型 
     * @return 单据类型
     */
    public  String  getBillType(){
        return billType;
    }
    /**
     * 设置单据类型
     * @param billType 单据类型
     */
    public  void  setBillType(String billType){
        this.billType = billType;
    }
    /**
     * 获取发运方式 
     * @return 发运方式
     */
    public  String  getShipMode(){
        return shipMode;
    }
    /**
     * 设置发运方式
     * @param shipMode 发运方式
     */
    public  void  setShipMode(String shipMode){
        this.shipMode = shipMode;
    }
    /**
     * 获取发运方式ID 
     * @return 发运方式ID
     */
    public  String  getShipModeId(){
        return shipModeId;
    }
    /**
     * 设置发运方式ID
     * @param shipModeId 发运方式ID
     */
    public  void  setShipModeId(String shipModeId){
        this.shipModeId = shipModeId;
    }
    /**
     * 获取产品类型 
     * @return 产品类型
     */
    public  String  getProductType(){
        return productType;
    }
    /**
     * 设置产品类型
     * @param productType 产品类型
     */
    public  void  setProductType(String productType){
        this.productType = productType;
    }
    /**
     * 获取产品类型ID 
     * @return 产品类型ID
     */
    public  String  getProductTypeId(){
        return productTypeId;
    }
    /**
     * 设置产品类型ID
     * @param productTypeId 产品类型ID
     */
    public  void  setProductTypeId(String productTypeId){
        this.productTypeId = productTypeId;
    }
    /**
     * 获取产品ID 
     * @return 产品ID
     */
    public  String  getProductId(){
        return productId;
    }
    /**
     * 设置产品ID
     * @param productId 产品ID
     */
    public  void  setProductId(String productId){
        this.productId = productId;
    }
    /**
     * 获取产品名称 
     * @return 产品名称
     */
    public  String  getProductName(){
        return productName;
    }
    /**
     * 设置产品名称
     * @param productName 产品名称
     */
    public  void  setProductName(String productName){
        this.productName = productName;
    }
    /**
     * 获取产品型号 
     * @return 产品型号
     */
    public  String  getProductModelNumber(){
        return productModelNumber;
    }
    /**
     * 设置产品型号
     * @param productModelNumber 产品型号
     */
    public  void  setProductModelNumber(String productModelNumber){
        this.productModelNumber = productModelNumber;
    }
    /**
     * 获取手机号 
     * @return 手机号
     */
    public  String  getPhoneNumber(){
        return phoneNumber;
    }
    /**
     * 设置手机号
     * @param phoneNumber 手机号
     */
    public  void  setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    /**
     * 获取车牌号码 
     * @return 车牌号码
     */
    public  String  getLicensePlate(){
        return licensePlate;
    }
    /**
     * 设置车牌号码
     * @param licensePlate 车牌号码
     */
    public  void  setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }
    /**
     * 获取行号
     * @return 行号
     */
	public String getLineNumber() {
		return lineNumber;
	}
	/**
     * 设置行号
     * @param lineNumber 行号
     */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
    /**
     * 获取车型 
     * @return 车型
     */
    public  String  getCarType(){
        return carType;
    }
    /**
     * 设置车型
     * @param carType 车型
     */
    public  void  setCarType(String carType){
        this.carType = carType;
    }
    /**
     * 获取司机姓名 
     * @return 司机姓名
     */
    public  String  getDriverName(){
        return driverName;
    }
    /**
     * 设置司机姓名
     * @param driverName 司机姓名
     */
    public  void  setDriverName(String driverName){
        this.driverName = driverName;
    }
    /**
     * 获取司机身份证号 
     * @return 司机身份证号
     */
    public  String  getDriverIdCardNumber(){
        return driverIdCardNumber;
    }
    /**
     * 设置司机身份证号
     * @param driverIdCardNumber 司机身份证号
     */
    public  void  setDriverIdCardNumber(String driverIdCardNumber){
        this.driverIdCardNumber = driverIdCardNumber;
    }
    /**
     * 获取状态 
     * @return 状态
     */
    public  String  getLadingBillStatus(){
        return ladingBillStatus;
    }
    /**
     * 设置状态
     * @param ladingBillStatus 状态
     */
    public  void  setLadingBillStatus(String ladingBillStatus){
        this.ladingBillStatus = ladingBillStatus;
    }
    /**
     * 获取审核时间 
     * @return 审核时间
     */
    public  java.sql.Date  getAuditTime(){
        return auditTime;
    }
    /**
     * 设置审核时间
     * @param auditTime 审核时间
     */
    public  void  setAuditTime(java.sql.Date auditTime){
        this.auditTime = auditTime;
    }
    /**
     * 获取审核人 
     * @return 审核人
     */
    public  String  getAuditPerson(){
        return auditPerson;
    }
    /**
     * 设置审核人
     * @param auditPerson 审核人
     */
    public  void  setAuditPerson(String auditPerson){
        this.auditPerson = auditPerson;
    }
    /**
     * 获取审核人ID 
     * @return 审核人ID
     */
    public  Long  getAuditPersonId(){
        return auditPersonId;
    }
    /**
     * 设置审核人ID
     * @param auditPersonId 审核人ID
     */
    public  void  setAuditPersonId(Long auditPersonId){
        this.auditPersonId = auditPersonId;
    }
    /**
     * 获取制表日期 
     * @return 制表日期
     */
    public Date getTabDate() {
		return tabDate;
	}
    /**
     * 设置制表日期 
     * @return tabDate 制表日期 
     */
	public void setTabDate(Date tabDate) {
		this.tabDate = tabDate;
	}
	/**
     * 获取订单编号
     * @return 订单编号
     */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
     * 设置订单编号
     * @return orderNumber 订单编号
     */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

    /**
     * 获取预计装车时间 
     * @return 预计装车时间
     */
    public  java.sql.Date  getEstimatedLoadingTime(){
        return estimatedLoadingTime;
    }
    /**
     * 设置预计装车时间
     * @param estimatedLoadingTime 预计装车时间
     */
    public  void  setEstimatedLoadingTime(java.sql.Date estimatedLoadingTime){
        this.estimatedLoadingTime = estimatedLoadingTime;
    }
    /**
     * 获取取号时间 
     * @return 取号时间
     */
    public  java.sql.Date  getTakeNoTime(){
        return takeNoTime;
    }
    /**
     * 设置取号时间
     * @param takeNoTime 取号时间
     */
    public  void  setTakeNoTime(java.sql.Date takeNoTime){
        this.takeNoTime = takeNoTime;
    }
    /**
     * 获取发货仓库
     * @return 发货仓库
     */
    public String getDeliveryWarehouse() {
		return deliveryWarehouse;
	}
    /**
     * 设置发货仓库
     * @param deliveryWarehouse 发货仓库
     */
	public void setDeliveryWarehouse(String deliveryWarehouse) {
		this.deliveryWarehouse = deliveryWarehouse;
	}
    /**
     * 获取实际发货重量 
     * @return 实际发货重量
     */
    public  java.math.BigDecimal  getActualShippingWeight(){
        return actualShippingWeight;
    }
    /**
     * 设置实际发货重量
     * @param actualShippingWeight 实际发货重量
     */
    public  void  setActualShippingWeight(java.math.BigDecimal actualShippingWeight){
        this.actualShippingWeight = actualShippingWeight;
    }
    /**
     * 获取实际发货件数
     * @return 实际发货件数
     */
    public String getDeliveryNumber() {
		return deliveryNumber;
	}
    /**
     * 设置实际发货件数
     * @return deliveryNumber 实际件数
     */
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
    /**
     * 获取核载量 
     * @return 核载量
     */
    public  java.math.BigDecimal  getNuclearLoad(){
        return nuclearLoad;
    }
    /**
     * 设置核载量
     * @param nuclearLoad 核载量
     */
    public  void  setNuclearLoad(java.math.BigDecimal nuclearLoad){
        this.nuclearLoad = nuclearLoad;
    }
    /**
     * 获取进门门岗 
     * @return 进门门岗
     */
    public  String  getInGate(){
        return inGate;
    }
    /**
     * 设置进门门岗
     * @param inGate 进门门岗
     */
    public  void  setInGate(String inGate){
        this.inGate = inGate;
    }
    /**
     * 获取签到时间
     * @return 签到时间
     */
    public String getSignTime() {
		return signTime;
	}
    /**
     * 设置签到时间
     * @return 签到时间
     */
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

    /**
     * 获取进门时间 
     * @return 进门时间
     */
    public  java.sql.Timestamp  getInTime(){
        return inTime;
    }
    /**
     * 设置进门时间
     * @param inTime 进门时间
     */
    public  void  setInTime(java.sql.Timestamp inTime){
        this.inTime = inTime;
    }
    /**
     * 获取空车磅 
     * @return 空车磅
     */
    public  java.math.BigDecimal  getEmptyWeight(){
        return emptyWeight;
    }
    /**
     * 设置空车磅
     * @param emptyWeight 空车磅
     */
    public  void  setEmptyWeight(java.math.BigDecimal emptyWeight){
        this.emptyWeight = emptyWeight;
    }
    /**
     * 获取空车过磅时间 
     * @return 空车过磅时间
     */
    public  java.sql.Date  getEmptyPoundTime(){
        return emptyPoundTime;
    }
    /**
     * 设置空车过磅时间
     * @param emptyPoundTime 空车过磅时间
     */
    public  void  setEmptyPoundTime(java.sql.Date emptyPoundTime){
        this.emptyPoundTime = emptyPoundTime;
    }
    /**
     * 获取重车磅 
     * @return 重车磅
     */
    public  java.math.BigDecimal  getFullWeight(){
        return fullWeight;
    }
    /**
     * 设置重车磅
     * @param fullWeight 重车磅
     */
    public  void  setFullWeight(java.math.BigDecimal fullWeight){
        this.fullWeight = fullWeight;
    }
    /**
     * 获取重车过磅时间 
     * @return 重车过磅时间
     */
    public  java.sql.Date  getFullPoundTime(){
        return fullPoundTime;
    }
    /**
     * 设置重车过磅时间
     * @param fullPoundTime 重车过磅时间
     */
    public  void  setFullPoundTime(java.sql.Date fullPoundTime){
        this.fullPoundTime = fullPoundTime;
    }
    /**
     * 获取出门门岗 
     * @return 出门门岗
     */
    public  String  getOutGate(){
        return outGate;
    }
    /**
     * 设置出门门岗
     * @param outGate 出门门岗
     */
    public  void  setOutGate(String outGate){
        this.outGate = outGate;
    }
    /**
     * 获取出门时间 
     * @return 出门时间
     */
    public  java.sql.Timestamp  getOutTime(){
        return outTime;
    }
    /**
     * 设置出门时间
     * @param outTime 出门时间
     */
    public  void  setOutTime(java.sql.Timestamp outTime){
        this.outTime = outTime;
    }
    /**
     * 获取录入人ID 
     * @return 录入人ID
     */
    public  Long  getInputPersonId(){
        return inputPersonId;
    }
    /**
     * 设置录入人ID
     * @param inputPersonId 录入人ID
     */
    public  void  setInputPersonId(Long inputPersonId){
        this.inputPersonId = inputPersonId;
    }
    /**
     * 获取录入人姓名 
     * @return 录入人姓名
     */
    public  String  getInputPerson(){
        return inputPerson;
    }
    /**
     * 设置录入人姓名
     * @param inputPerson 录入人姓名
     */
    public  void  setInputPerson(String inputPerson){
        this.inputPerson = inputPerson;
    }
    /**
     * 获取录入时间 
     * @return 录入时间
     */
    public  java.sql.Date  getInputTime(){
        return inputTime;
    }
    /**
     * 设置录入时间
     * @param inputTime 录入时间
     */
    public  void  setInputTime(java.sql.Date inputTime){
        this.inputTime = inputTime;
    }
    /**
     * 获取作废人ID 
     * @return 作废人ID
     */
    public  Long  getInvalidPersonId(){
        return invalidPersonId;
    }
    /**
     * 设置作废人ID
     * @param invalidPersonId 作废人ID
     */
    public  void  setInvalidPersonId(Long invalidPersonId){
        this.invalidPersonId = invalidPersonId;
    }
    /**
     * 获取作废人 
     * @return 作废人
     */
    public  String  getInvalidPerson(){
        return invalidPerson;
    }
    /**
     * 设置作废人
     * @param invalidPerson 作废人
     */
    public  void  setInvalidPerson(String invalidPerson){
        this.invalidPerson = invalidPerson;
    }
    /**
     * 获取作废时间 
     * @return 作废时间
     */
    public  java.sql.Date  getInvalidTime(){
        return invalidTime;
    }
    /**
     * 设置作废时间
     * @param invalidTime 作废时间
     */
    public  void  setInvalidTime(java.sql.Date invalidTime){
        this.invalidTime = invalidTime;
    }
    /**
     * 获取作废原因 
     * @return 作废原因
     */
    public  String  getInvalidReason(){
        return invalidReason;
    }
    /**
     * 设置作废原因
     * @param invalidReason 作废原因
     */
    public  void  setInvalidReason(String invalidReason){
        this.invalidReason = invalidReason;
    }
    /**
     * 获取是否带托盘 
     * @return 是否带托盘
     */
    public  String  getIsBringBelt(){
        return isBringBelt;
    }
    /**
     * 设置是否带托盘
     * @param isBringBelt 是否带托盘
     */
    public  void  setIsBringBelt(String isBringBelt){
        this.isBringBelt = isBringBelt;
    }
    /**
     * 获取是否有溶脂要求 
     * @return 是否有溶脂要求
     */
    public  String  getIsLipolysis(){
        return isLipolysis;
    }
    /**
     * 设置是否有溶脂要求
     * @param isLipolysis 是否有溶脂要求
     */
    public  void  setIsLipolysis(String isLipolysis){
        this.isLipolysis = isLipolysis;
    }
    /**
     * 获取是否为自提 
     * @return 是否为自提
     */
    public  String  getIsSince(){
        return isSince;
    }
    /**
     * 设置是否为自提
     * @param isSince 是否为自提
     */
    public  void  setIsSince(String isSince){
        this.isSince = isSince;
    }
    /**
     * 获取是否为加急 
     * @return 是否为加急
     */
    public  String  getIsUrgent(){
        return isUrgent;
    }
    /**
     * 设置是否为加急
     * @param isUrgent 是否为加急
     */
    public  void  setIsUrgent(String isUrgent){
        this.isUrgent = isUrgent;
    }
    /**
     * 获取计划里程 
     * @return 计划里程
     */
    public  java.math.BigDecimal  getPlannedMileage(){
        return plannedMileage;
    }
    /**
     * 设置计划里程
     * @param plannedMileage 计划里程
     */
    public  void  setPlannedMileage(java.math.BigDecimal plannedMileage){
        this.plannedMileage = plannedMileage;
    }
    /**
     * 获取实际里程 
     * @return 实际里程
     */
    public  java.math.BigDecimal  getFactMileage(){
        return factMileage;
    }
    /**
     * 设置实际里程
     * @param factMileage 实际里程
     */
    public  void  setFactMileage(java.math.BigDecimal factMileage){
        this.factMileage = factMileage;
    }
    /**
     * 获取计价里程 
     * @return 计价里程
     */
    public  java.math.BigDecimal  getValuationMileage(){
        return valuationMileage;
    }
    /**
     * 设置计价里程
     * @param valuationMileage 计价里程
     */
    public  void  setValuationMileage(java.math.BigDecimal valuationMileage){
        this.valuationMileage = valuationMileage;
    }
    /**
     * 获取实际计价里程 
     * @return 实际计价里程
     */
    public java.math.BigDecimal getFactValuationMileage() {
		return factValuationMileage;
	}

    /**
     * 设置实际计价里程
     * @param valuationMileage 实际计价里程
     */
	public void setFactValuationMileage(java.math.BigDecimal factValuationMileage) {
		this.factValuationMileage = factValuationMileage;
	}
    /**
     * 获取结算运价 
     * @return 结算运价
     */
    public  java.math.BigDecimal  getSettlementFreight(){
        return settlementFreight;
    }
    /**
     * 设置结算运价
     * @param settlementFreight 结算运价
     */
    public  void  setSettlementFreight(java.math.BigDecimal settlementFreight){
        this.settlementFreight = settlementFreight;
    }
    /**
     * 获取运价系数(元/吨公里) 
     * @return 运价系数(元/吨公里)
     */
    public  java.math.BigDecimal  getYuanKmTon(){
        return yuanKmTon;
    }
    /**
     * 设置运价系数(元/吨公里)
     * @param yuanKmTon 运价系数(元/吨公里)
     */
    public  void  setYuanKmTon(java.math.BigDecimal yuanKmTon){
        this.yuanKmTon = yuanKmTon;
    }
    /**
     * 获取收货单位
     * @return 收货单位
     */
    public String getReceivingUnit() {
		return receivingUnit;
	}
    /**
     * 设置收货单位
     * @param receivingUnit 收货单位
     */
	public void setReceivingUnit(String receivingUnit) {
		this.receivingUnit = receivingUnit;
	}
    /**
     * 获取运费 
     * @return 运费
     */
    public  java.math.BigDecimal  getFreight(){
        return freight;
    }
    /**
     * 设置运费
     * @param freight 运费
     */
    public  void  setFreight(java.math.BigDecimal freight){
        this.freight = freight;
    }
    /**
     * 获取收货人 
     * @return 收货人
     */
    public  String  getReceivingPerson(){
        return receivingPerson;
    }
    /**
     * 设置收货人
     * @param receivingPerson 收货人
     */
    public  void  setReceivingPerson(String receivingPerson){
        this.receivingPerson = receivingPerson;
    }
    /**
     * 获取收货地址 
     * @return 收货地址
     */
    public  String  getReceivingAddress(){
        return receivingAddress;
    }
    /**
     * 设置收货地址
     * @param receivingAddress 收货地址
     */
    public  void  setReceivingAddress(String receivingAddress){
        this.receivingAddress = receivingAddress;
    }
    /**
     * 获取收货人联系方式 
     * @return 收货人联系方式
     */
    public  String  getReceivingPersonPhone(){
        return receivingPersonPhone;
    }
    /**
     * 设置收货人联系方式
     * @param receivingPersonPhone 收货人联系方式
     */
    public  void  setReceivingPersonPhone(String receivingPersonPhone){
        this.receivingPersonPhone = receivingPersonPhone;
    }
    /**
     * 获取运费结算ID 
     * @return 运费结算ID
     */
    public  String  getFreightSettlementId(){
        return freightSettlementId;
    }
    /**
     * 设置运费结算ID
     * @param freightSettlementId 运费结算ID
     */
    public  void  setFreightSettlementId(String freightSettlementId){
        this.freightSettlementId = freightSettlementId;
    }
    /**
     * 获取发票结算ID 
     * @return 发票结算ID
     */
    public  String  getInvoiceSettlementId(){
        return invoiceSettlementId;
    }
    /**
     * 设置发票结算ID
     * @param invoiceSettlementId 发票结算ID
     */
    public  void  setInvoiceSettlementId(String invoiceSettlementId){
        this.invoiceSettlementId = invoiceSettlementId;
    }
    /**
     * 获取发货公司编码 
     * @return 发货公司编码
     */
    public  String  getShipperCode(){
        return shipperCode;
    }
    /**
     * 设置发货公司编码
     * @param shipperCode 发货公司编码
     */
    public  void  setShipperCode(String shipperCode){
        this.shipperCode = shipperCode;
    }
    /**
     * 获取打印时间 
     * @return 打印时间
     */
    public  java.sql.Date  getPrintTime(){
        return printTime;
    }
    /**
     * 设置打印时间
     * @param printTime 打印时间
     */
    public  void  setPrintTime(java.sql.Date printTime){
        this.printTime = printTime;
    }
    /**
     * 获取打印次数 
     * @return 打印次数
     */
    public  Long  getPrintTimes(){
        return printTimes;
    }
    /**
     * 设置打印次数
     * @param printTimes 打印次数
     */
    public  void  setPrintTimes(Long printTimes){
        this.printTimes = printTimes;
    }
    /**
     * 获取备注 
     * @return 备注
     */
    public  String  getRemark(){
        return remark;
    }
    /**
     * 设置备注
     * @param remark 备注
     */
    public  void  setRemark(String remark){
        this.remark = remark;
    }
    /**
     * 获取物流专员ID 
     * @return 物流专员ID
     */
    public  Long  getLogisticsSpecialistId(){
        return logisticsSpecialistId;
    }
    /**
     * 设置物流专员ID
     * @param logisticsSpecialistId 物流专员ID
     */
    public  void  setLogisticsSpecialistId(Long logisticsSpecialistId){
        this.logisticsSpecialistId = logisticsSpecialistId;
    }
    /**
     * 获取物流专员 
     * @return 物流专员
     */
    public  String  getLogisticsSpecialistName(){
        return logisticsSpecialistName;
    }
    /**
     * 设置物流专员
     * @param logisticsSpecialistName 物流专员
     */
    public  void  setLogisticsSpecialistName(String logisticsSpecialistName){
        this.logisticsSpecialistName = logisticsSpecialistName;
    }
    /**
     * 获取导入时间 
     * @return 导入时间
     */
    public  java.sql.Date  getImportTime(){
        return importTime;
    }
    /**
     * 设置导入时间
     * @param importTime 导入时间
     */
    public  void  setImportTime(java.sql.Date importTime){
        this.importTime = importTime;
    }
    /**
     * 获取分配物流时间 
     * @return 分配物流时间
     */
    public  java.sql.Date  getDistributionTime(){
        return distributionTime;
    }
    /**
     * 设置分配物流时间
     * @param distributionTime 分配物流时间
     */
    public  void  setDistributionTime(java.sql.Date distributionTime){
        this.distributionTime = distributionTime;
    }
    /**
     * 获取物流公司业务员ID 
     * @return 物流公司业务员ID
     */
    public  Long  getLogisticsCompanyClerkId(){
        return logisticsCompanyClerkId;
    }
    /**
     * 设置物流公司业务员ID
     * @param logisticsCompanyClerkId 物流公司业务员ID
     */
    public  void  setLogisticsCompanyClerkId(Long logisticsCompanyClerkId){
        this.logisticsCompanyClerkId = logisticsCompanyClerkId;
    }
    /**
     * 获取物流公司业务员 
     * @return 物流公司业务员
     */
    public  String  getLogisticsCompanyClerk(){
        return logisticsCompanyClerk;
    }
    /**
     * 设置物流公司业务员
     * @param logisticsCompanyClerk 物流公司业务员
     */
    public  void  setLogisticsCompanyClerk(String logisticsCompanyClerk){
        this.logisticsCompanyClerk = logisticsCompanyClerk;
    }
    /**
     * 获取分车时间 
     * @return 分车时间
     */
    public  java.sql.Timestamp  getAllocationVehicleTime(){
        return allocationVehicleTime;
    }
    /**
     * 设置分车时间
     * @param allocationVehicleTime 分车时间
     */
    public  void  setAllocationVehicleTime(java.sql.Timestamp allocationVehicleTime){
        this.allocationVehicleTime = allocationVehicleTime;
    }
    /**
     * 获取是否过磅 
     * @return 是否过磅
     */
    public  String  getIsPound(){
        return isPound;
    }
    /**
     * 设置是否过磅
     * @param isPound 是否过磅
     */
    public  void  setIsPound(String isPound){
        this.isPound = isPound;
    }
    /**
     * 获取是否装车 
     * @return 是否装车
     */
    public  String  getIsLoading(){
        return isLoading;
    }
    /**
     * 设置是否装车
     * @param isLoading 是否装车
     */
    public  void  setIsLoading(String isLoading){
        this.isLoading = isLoading;
    }
    /**
     * 实际发货金额
     */
	public BigDecimal getActualShippingAmount() {
		return actualShippingAmount;
	}
	/**
     * 实际发货金额
     */
	public void setActualShippingAmount(BigDecimal actualShippingAmount) {
		this.actualShippingAmount = actualShippingAmount;
	}
	/**
     * 计划发货金额
     */
	public BigDecimal getPlannedShipmentAmount() {
		return plannedShipmentAmount;
	}
	/**
     * 计划发货金额
     */
	public void setPlannedShipmentAmount(BigDecimal plannedShipmentAmount) {
		this.plannedShipmentAmount = plannedShipmentAmount;
	}

	public BigDecimal getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setProductUnitPrice(BigDecimal productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	/**
     * 发运方式编码
     */
	public String getShipModeCode() {
		return shipModeCode;
	}
	/**
     * 发运方式编码
     */
	public void setShipModeCode(String shipModeCode) {
		this.shipModeCode = shipModeCode;
	}
	/**
     * 客户名称
     */
	public String getCustomerName() {
		return customerName;
	}
	/**
     * 客户名称
     */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPlannedVolume() {
		return plannedVolume;
	}

	public void setPlannedVolume(String plannedVolume) {
		this.plannedVolume = plannedVolume;
	}

	public String getRemarkVolume() {
		return remarkVolume;
	}

	public void setRemarkVolume(String remarkVolume) {
		this.remarkVolume = remarkVolume;
	}

	public String getRemainderCarNum() {
		return remainderCarNum;
	}

	public void setRemainderCarNum(String remainderCarNum) {
		this.remainderCarNum = remainderCarNum;
	}

	public Date getDeliveryStartDate() {
		return deliveryStartDate;
	}

	public void setDeliveryStartDate(Date deliveryStartDate) {
		this.deliveryStartDate = deliveryStartDate;
	}

	public Date getDeliveryEndDate() {
		return deliveryEndDate;
	}

	public void setDeliveryEndDate(Date deliveryEndDate) {
		this.deliveryEndDate = deliveryEndDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSettlementFlag() {
		return settlementFlag;
	}

	public void setSettlementFlag(String settlementFlag) {
		this.settlementFlag = settlementFlag;
	}
    

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsCompany() {
		return logisticsCompany;
	}

	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}

	public Timestamp getSafeCheckTime() {
		return safeCheckTime;
	}

	public void setSafeCheckTime(Timestamp safeCheckTime) {
		this.safeCheckTime = safeCheckTime;
	}

	public String getSafeCheckStatus() {
		return safeCheckStatus;
	}

	public void setSafeCheckStatus(String safeCheckStatus) {
		this.safeCheckStatus = safeCheckStatus;
	}

    //结束vo的setter和getter方法    
}
