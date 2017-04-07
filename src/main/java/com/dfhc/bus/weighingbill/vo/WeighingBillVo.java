/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  WeighingBillVo.java
 *
 * 功能描述：  过磅单值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.weighingbill.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 过磅单值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class WeighingBillVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * ladingBillId 表示：司机提货单ID
     */
    private  String  ladingBillId;
    /**
     * ladingBillCode 表示：提货单号
     */
    private  String  ladingBillCode;
    /**
     * truckNo 表示：车牌号
     */
    private  String  truckNo;
    /**
     * phoneNumber 表示：手机号
     */
    private  String  phoneNumber;
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
     * tare 表示：第一次检斤重量
     */
    private  java.math.BigDecimal  tare;
    /**
     * grossWeight 表示：第二次检斤重量
     */
    private  java.math.BigDecimal  grossWeight;
    /**
     * netWeight 表示：净重
     */
    private  java.math.BigDecimal  netWeight;
    /**
     * firstWeighingTime 表示：第一次过磅时间
     */
    private  java.sql.Date  firstWeighingTime;
    /**
     * twoWeighingTime 表示：第二次过磅时间
     */
    private  java.sql.Date  twoWeighingTime;
    /**
     * weighmanId 表示：第一次司磅员ID
     */
    private  String  weighmanId;
    /**
     * weighman 表示：第一次司磅员
     */
    private  String  weighman;
    /**
     * weighmanId2 表示：第二次司磅员ID
     */
    private  String  weighmanId2;
    /**
     * weighman2 表示：第二次司磅员
     */
    private  String  weighman2;
    /**
     * shipperCode 表示：发货公司编码
     */
    private  String  shipperCode;
    /**
     * shipper 表示：发货公司
     */
    private  String  shipper;
    /**
     * receivingUnit 表示：收货单位
     */
    private  String  receivingUnit;
    /**
     * transportUnit 表示：运输单位
     */
    private  String  transportUnit;
    /**
     * timeToGoOut 表示：出门时间
     */
    private  java.sql.Date  timeToGoOut;
    /**
     * printDate 表示：打印日期
     */
    private  java.sql.Date  printDate;
    /**
     * storageTransportationMan 表示：储运负责人
     */
    private  String  storageTransportationMan;
    /**
     * status 表示：状态
     */
    private  String  status;
    /**
     * storageTransportationId 表示：储运负责人ID
     */
    private  Long  storageTransportationId;
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

    //结束vo的属性
        
        
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
     * 获取司机提货单ID 
     * @return 司机提货单ID
     */
    public  String  getLadingBillId(){
        return ladingBillId;
    }
    /**
     * 设置司机提货单ID
     * @param ladingBillId 司机提货单ID
     */
    public  void  setLadingBillId(String ladingBillId){
        this.ladingBillId = ladingBillId;
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
     * 获取车牌号 
     * @return 车牌号
     */
    public  String  getTruckNo(){
        return truckNo;
    }
    /**
     * 设置车牌号
     * @param truckNo 车牌号
     */
    public  void  setTruckNo(String truckNo){
        this.truckNo = truckNo;
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
     * 获取第一次检斤重量 
     * @return 第一次检斤重量
     */
    public  java.math.BigDecimal  getTare(){
        return tare;
    }
    /**
     * 设置第一次检斤重量
     * @param tare 第一次检斤重量
     */
    public  void  setTare(java.math.BigDecimal tare){
        this.tare = tare;
    }
    /**
     * 获取第二次检斤重量 
     * @return 第二次检斤重量
     */
    public  java.math.BigDecimal  getGrossWeight(){
        return grossWeight;
    }
    /**
     * 设置第二次检斤重量
     * @param grossWeight 第二次检斤重量
     */
    public  void  setGrossWeight(java.math.BigDecimal grossWeight){
        this.grossWeight = grossWeight;
    }
    /**
     * 获取净重 
     * @return 净重
     */
    public  java.math.BigDecimal  getNetWeight(){
        return netWeight;
    }
    /**
     * 设置净重
     * @param netWeight 净重
     */
    public  void  setNetWeight(java.math.BigDecimal netWeight){
        this.netWeight = netWeight;
    }
    /**
     * 获取第一次过磅时间 
     * @return 第一次过磅时间
     */
    public  java.sql.Date  getFirstWeighingTime(){
        return firstWeighingTime;
    }
    /**
     * 设置第一次过磅时间
     * @param firstWeighingTime 第一次过磅时间
     */
    public  void  setFirstWeighingTime(java.sql.Date firstWeighingTime){
        this.firstWeighingTime = firstWeighingTime;
    }
    /**
     * 获取第二次过磅时间 
     * @return 第二次过磅时间
     */
    public  java.sql.Date  getTwoWeighingTime(){
        return twoWeighingTime;
    }
    /**
     * 设置第二次过磅时间
     * @param twoWeighingTime 第二次过磅时间
     */
    public  void  setTwoWeighingTime(java.sql.Date twoWeighingTime){
        this.twoWeighingTime = twoWeighingTime;
    }
    /**
     * 获取第一次司磅员ID 
     * @return 第一次司磅员ID
     */
    public  String  getWeighmanId(){
        return weighmanId;
    }
    /**
     * 设置第一次司磅员ID
     * @param weighmanId 第一次司磅员ID
     */
    public  void  setWeighmanId(String weighmanId){
        this.weighmanId = weighmanId;
    }
    /**
     * 获取第一次司磅员 
     * @return 第一次司磅员
     */
    public  String  getWeighman(){
        return weighman;
    }
    /**
     * 设置第一次司磅员
     * @param weighman 第一次司磅员
     */
    public  void  setWeighman(String weighman){
        this.weighman = weighman;
    }
    /**
     * 获取第二次司磅员ID 
     * @return 第二次司磅员ID
     */
    public  String  getWeighmanId2(){
        return weighmanId2;
    }
    /**
     * 设置第二次司磅员ID
     * @param weighmanId2 第二次司磅员ID
     */
    public  void  setWeighmanId2(String weighmanId2){
        this.weighmanId2 = weighmanId2;
    }
    /**
     * 获取第二次司磅员 
     * @return 第二次司磅员
     */
    public  String  getWeighman2(){
        return weighman2;
    }
    /**
     * 设置第二次司磅员
     * @param weighman2 第二次司磅员
     */
    public  void  setWeighman2(String weighman2){
        this.weighman2 = weighman2;
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
     * 获取发货公司 
     * @return 发货公司
     */
    public  String  getShipper(){
        return shipper;
    }
    /**
     * 设置发货公司
     * @param shipper 发货公司
     */
    public  void  setShipper(String shipper){
        this.shipper = shipper;
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
     * 获取运输单位 
     * @return 运输单位
     */
    public  String  getTransportUnit(){
        return transportUnit;
    }
    /**
     * 设置运输单位
     * @param transportUnit 运输单位
     */
    public  void  setTransportUnit(String transportUnit){
        this.transportUnit = transportUnit;
    }
    /**
     * 获取出门时间 
     * @return 出门时间
     */
    public  java.sql.Date  getTimeToGoOut(){
        return timeToGoOut;
    }
    /**
     * 设置出门时间
     * @param timeToGoOut 出门时间
     */
    public  void  setTimeToGoOut(java.sql.Date timeToGoOut){
        this.timeToGoOut = timeToGoOut;
    }
    /**
     * 获取打印日期 
     * @return 打印日期
     */
    public  java.sql.Date  getPrintDate(){
        return printDate;
    }
    /**
     * 设置打印日期
     * @param printDate 打印日期
     */
    public  void  setPrintDate(java.sql.Date printDate){
        this.printDate = printDate;
    }
    /**
     * 获取储运负责人 
     * @return 储运负责人
     */
    public  String  getStorageTransportationMan(){
        return storageTransportationMan;
    }
    /**
     * 设置储运负责人
     * @param storageTransportationMan 储运负责人
     */
    public  void  setStorageTransportationMan(String storageTransportationMan){
        this.storageTransportationMan = storageTransportationMan;
    }
    /**
     * 获取状态 
     * @return 状态
     */
    public  String  getStatus(){
        return status;
    }
    /**
     * 设置状态
     * @param status 状态
     */
    public  void  setStatus(String status){
        this.status = status;
    }
    /**
     * 获取储运负责人ID 
     * @return 储运负责人ID
     */
    public  Long  getStorageTransportationId(){
        return storageTransportationId;
    }
    /**
     * 设置储运负责人ID
     * @param storageTransportationId 储运负责人ID
     */
    public  void  setStorageTransportationId(Long storageTransportationId){
        this.storageTransportationId = storageTransportationId;
    }
    //结束vo的setter和getter方法    
}
