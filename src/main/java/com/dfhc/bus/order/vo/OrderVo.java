/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  OrderVo.java
 *
 * 功能描述：  销售订单值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.order.vo;


import java.math.BigDecimal;
import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 销售订单值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class OrderVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * status 表示：STATUS
     */
    private  String  status;
    /**
     * orderNum 表示：订单编号
     */
    private  String  orderNum;
    /**
     * customerId 表示：客户ID
     */
    private  Long  customerId;
    /**
     * customerName 表示：客户名称
     */
    private  String  customerName;
    /**
     * deliveryStartDate 表示：提货开始日期
     */
    private  java.sql.Date  deliveryStartDate;
    /**
     * deliveryEndDate 表示：提货结束日期
     */
    private  java.sql.Date  deliveryEndDate;
    /**
     * amountMoney 表示：计划总金额
     */
    private  java.math.BigDecimal  amountMoney;
    /**
     * plannedSurplus 表示：计划剩余金额
     */
    private  java.math.BigDecimal  plannedSurplus;
    /**
     * actualLoadingAmount 表示：实际装车金额
     */
    private  java.math.BigDecimal  actualLoadingAmount;
    /**
     * actualSurplusAmount 表示：实际剩余金额
     */
    private  java.math.BigDecimal  actualSurplusAmount;
    /**
     * shipMode 表示：发运方式
     */
    private  String  shipMode;
    /**
     * shipModeId 表示：发运方式ID
     */
    private  String  shipModeId;
    /**
     * receivingUnitId 表示：收货单位id
     */
    private  String  receivingUnitId;
    /**
     * receivingUnit 表示：收货单位
     */
    private  String  receivingUnit;
    /**
     * province 表示：省
     */
    private  String  province;
    /**
     * provinceId 表示：省ID
     */
    private  String  provinceId;
    /**
     * city 表示：市
     */
    private  String  city;
    /**
     * cityId 表示：市ID
     */
    private  String  cityId;
    /**
     * address 表示：地址
     */
    private  String  address;
    /**
     * orderAttachment 表示：销售订单附件
     */
    private  String  orderAttachment;
    /**
     * shipperCode 表示：发货公司编码
     */
    private  String  shipperCode;
    /**
     * attorneyLetter 表示：委托书电子版
     */
    private  String  attorneyLetter;
    /**
     * placeOrderTime 表示：下单日期时间
     */
    private  java.sql.Date  placeOrderTime;
    /**
     * inputPerson 表示：录入人
     */
    private  String  inputPerson;
    /**
     * inputPersonId 表示：录入人ID
     */
    private  Long  inputPersonId;
    /**
     * auditPersonId 表示：审核人ID
     */
    private  Long  auditPersonId;
    /**
     * auditPerson 表示：审核人
     */
    private  String  auditPerson;
    /**
     * auditTime 表示：审核时间
     */
    private  java.sql.Date  auditTime;
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
     * listProductName 表示: 产品名称 逗号隔开
     */
    private String listProductName;
    /**
     * carSum 表示: 车辆总数
     */
    private String carSum;
    /**
     * 剩余发运量总数
     */
    private  java.math.BigDecimal  remarkVolumeSum;
    /**
     * 发运方式编码
     */
    private String shipModeCode;

    
    /**
     * 备注
     */
    private String remark;
    //结束vo的属性
    
    /**
     * 产品单价
     */
    private BigDecimal productUnitPrice;
        
    //开始vo的setter和getter方法
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
     * 获取订单编号 
     * @return 订单编号
     */
    public  String  getOrderNum(){
        return orderNum;
    }
    /**
     * 设置订单编号
     * @param orderNum 订单编号
     */
    public  void  setOrderNum(String orderNum){
        this.orderNum = orderNum;
    }
    /**
     * 获取客户ID 
     * @return 客户ID
     */
    public  Long  getCustomerId(){
        return customerId;
    }
    /**
     * 设置客户ID
     * @param customerId 客户ID
     */
    public  void  setCustomerId(Long customerId){
        this.customerId = customerId;
    }
    /**
     * 获取客户名称 
     * @return 客户名称
     */
    public  String  getCustomerName(){
        return customerName;
    }
    /**
     * 设置客户名称
     * @param customerName 客户名称
     */
    public  void  setCustomerName(String customerName){
        this.customerName = customerName;
    }
    /**
     * 获取提货开始日期 
     * @return 提货开始日期
     */
    public  java.sql.Date  getDeliveryStartDate(){
        return deliveryStartDate;
    }
    /**
     * 设置提货开始日期
     * @param deliveryStartDate 提货开始日期
     */
    public  void  setDeliveryStartDate(java.sql.Date deliveryStartDate){
        this.deliveryStartDate = deliveryStartDate;
    }
    /**
     * 获取提货结束日期 
     * @return 提货结束日期
     */
    public  java.sql.Date  getDeliveryEndDate(){
        return deliveryEndDate;
    }
    /**
     * 设置提货结束日期
     * @param deliveryEndDate 提货结束日期
     */
    public  void  setDeliveryEndDate(java.sql.Date deliveryEndDate){
        this.deliveryEndDate = deliveryEndDate;
    }
    /**
     * 获取金额计划总 
     * @return 计划总金额
     */
    public  java.math.BigDecimal  getAmountMoney(){
        return amountMoney;
    }
    /**
     * 设置金额计划总
     * @param amountMoney 计划总金额
     */
    public  void  setAmountMoney(java.math.BigDecimal amountMoney){
        this.amountMoney = amountMoney;
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
     * 获取收货单位id 
     * @return 收货单位id
     */
    public  String  getReceivingUnitId(){
        return receivingUnitId;
    }
    /**
     * 设置收货单位id
     * @param receivingUnitId 收货单位id
     */
    public  void  setReceivingUnitId(String receivingUnitId){
        this.receivingUnitId = receivingUnitId;
    }
    /**
     * 获取收货单位 
     * @return 收货单位
     */
    public  String  getReceivingUnit(){
        return receivingUnit;
    }
    /**
     * 设置收货单位
     * @param receivingUnit 收货单位
     */
    public  void  setReceivingUnit(String receivingUnit){
        this.receivingUnit = receivingUnit;
    }
    /**
     * 获取省 
     * @return 省
     */
    public  String  getProvince(){
        return province;
    }
    /**
     * 设置省
     * @param province 省
     */
    public  void  setProvince(String province){
        this.province = province;
    }
    /**
     * 获取省ID 
     * @return 省ID
     */
    public  String  getProvinceId(){
        return provinceId;
    }
    /**
     * 设置省ID
     * @param provinceId 省ID
     */
    public  void  setProvinceId(String provinceId){
        this.provinceId = provinceId;
    }
    /**
     * 获取市 
     * @return 市
     */
    public  String  getCity(){
        return city;
    }
    /**
     * 设置市
     * @param city 市
     */
    public  void  setCity(String city){
        this.city = city;
    }
    /**
     * 获取市ID 
     * @return 市ID
     */
    public  String  getCityId(){
        return cityId;
    }
    /**
     * 设置市ID
     * @param cityId 市ID
     */
    public  void  setCityId(String cityId){
        this.cityId = cityId;
    }
    /**
     * 获取地址 
     * @return 地址
     */
    public  String  getAddress(){
        return address;
    }
    /**
     * 设置地址
     * @param address 地址
     */
    public  void  setAddress(String address){
        this.address = address;
    }
    /**
     * 获取销售订单附件 
     * @return 销售订单附件
     */
    public  String  getOrderAttachment(){
        return orderAttachment;
    }
    /**
     * 设置销售订单附件
     * @param orderAttachment 销售订单附件
     */
    public  void  setOrderAttachment(String orderAttachment){
        this.orderAttachment = orderAttachment;
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
     * 获取委托书电子版 
     * @return 委托书电子版
     */
    public  String  getAttorneyLetter(){
        return attorneyLetter;
    }
    /**
     * 设置委托书电子版
     * @param attorneyLetter 委托书电子版
     */
    public  void  setAttorneyLetter(String attorneyLetter){
        this.attorneyLetter = attorneyLetter;
    }
    /**
     * 获取下单日期时间 
     * @return 下单日期时间
     */
    public  java.sql.Date  getPlaceOrderTime(){
        return placeOrderTime;
    }
    /**
     * 设置下单日期时间
     * @param placeOrderTime 下单日期时间
     */
    public  void  setPlaceOrderTime(java.sql.Date placeOrderTime){
        this.placeOrderTime = placeOrderTime;
    }
    /**
     * 获取录入人 
     * @return 录入人
     */
    public  String  getInputPerson(){
        return inputPerson;
    }
    /**
     * 设置录入人
     * @param inputPerson 录入人
     */
    public  void  setInputPerson(String inputPerson){
        this.inputPerson = inputPerson;
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
     * 获取产品名称  逗号隔开
     * @return 产品名称
     */
	public String getListProductName() {
		return listProductName;
	}

    /**
     * 设置产品名称
     * @param listProductName 产品名称
     */
	public void setListProductName(String listProductName) {
		this.listProductName = listProductName;
	}

    /**
     * 获取车辆总数 
     * @return 车辆总数
     */
	public String getCarSum() {
		return carSum;
	}

    /**
     * 设置车辆总数
     * @param carSum 车辆总数
     */
	public void setCarSum(String carSum) {
		this.carSum = carSum;
	}

    /**
     * 获取状态
     * @return 状态
     */
	public String getStatus() {
		return status;
	}

    /**
     * 设置状态
     * @param status 状态
     */
	public void setStatus(String status) {
		this.status = status;
	}

    /**
     * 获取计划剩余金额
     * @return 计划剩余金额
     */
	public java.math.BigDecimal getPlannedSurplus() {
		return plannedSurplus;
	}


    /**
     * 设置计划剩余金额
     * @param plannedSurplus 计划剩余金额
     */
	public void setPlannedSurplus(java.math.BigDecimal plannedSurplus) {
		this.plannedSurplus = plannedSurplus;
	}

    /**
     * 获取实际装车金额
     * @return 实际装车金额
     */
	public java.math.BigDecimal getActualLoadingAmount() {
		return actualLoadingAmount;
	}


    /**
     * 设置实际装车金额
     * @param actualLoadingAmount 实际装车金额
     */
	public void setActualLoadingAmount(java.math.BigDecimal actualLoadingAmount) {
		this.actualLoadingAmount = actualLoadingAmount;
	}

    /**
     * 获取实际剩余金额
     * @return 实际剩余金额
     */
	public java.math.BigDecimal getActualSurplusAmount() {
		return actualSurplusAmount;
	}
    /**
     * 设置状态
     * @param actualSurplusAmount 实际剩余金额
     */
	public void setActualSurplusAmount(java.math.BigDecimal actualSurplusAmount) {
		this.actualSurplusAmount = actualSurplusAmount;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
     * 产品单价
     */
	public BigDecimal getProductUnitPrice() {
		return productUnitPrice;
	}
	/**
     * 产品单价
     */
	public void setProductUnitPrice(BigDecimal productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	/**
     *剩余数量总
     */
	public java.math.BigDecimal getRemarkVolumeSum() {
		return remarkVolumeSum;
	}

	/**
     * 剩余数量总
     */
	public void setRemarkVolumeSum(java.math.BigDecimal remarkVolumeSum) {
		this.remarkVolumeSum = remarkVolumeSum;
	}
    
    
    
    //结束vo的setter和getter方法    
}
