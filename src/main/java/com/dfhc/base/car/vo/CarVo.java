/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CarVo.java
 *
 * 功能描述：  车辆信息值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.car.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 车辆信息值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class CarVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * truckNo 表示：车牌号
     */
    private  String  truckNo;
    /**
     * grand 表示：品牌
     */
    private  String  grand;
    /**
     * model 表示：型号
     */
    private  String  model;
    /**
     * color 表示：颜色
     */
    private  String  color;
    /**
     * modelSerial 表示：车型系列
     */
    private  String  modelSerial;
    /**
     * engineNo 表示：发动机号
     */
    private  String  engineNo;
    /**
     * certDept 表示：发证机关
     */
    private  String  certDept;
    /**
     * emptyWeight 表示：实际自重
     */
    private  java.math.BigDecimal  emptyWeight;
    /**
     * owner 表示：户主
     */
    private  String  owner;
    /**
     * driveNo 表示：驾驶证号
     */
    private  String  driveNo;
    /**
     * driveType 表示：驾驶证类别
     */
    private  String  driveType;
    /**
     * contact 表示：联系方式
     */
    private  String  contact;
    /**
     * gender 表示：性别
     */
    private  String  gender;
    /**
     * idNo 表示：身份证号
     */
    private  String  idNo;
    /**
     * axleNum 表示：轴数
     */
    private  Long  axleNum;
    /**
     * picFront 表示：车前图片
     */
    private  String  picFront;
    /**
     * picBack 表示：车后图片
     */
    private  String  picBack;
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
     * 获取品牌 
     * @return 品牌
     */
    public  String  getGrand(){
        return grand;
    }
    /**
     * 设置品牌
     * @param grand 品牌
     */
    public  void  setGrand(String grand){
        this.grand = grand;
    }
    /**
     * 获取型号 
     * @return 型号
     */
    public  String  getModel(){
        return model;
    }
    /**
     * 设置型号
     * @param model 型号
     */
    public  void  setModel(String model){
        this.model = model;
    }
    /**
     * 获取颜色 
     * @return 颜色
     */
    public  String  getColor(){
        return color;
    }
    /**
     * 设置颜色
     * @param color 颜色
     */
    public  void  setColor(String color){
        this.color = color;
    }
    /**
     * 获取车型系列 
     * @return 车型系列
     */
    public  String  getModelSerial(){
        return modelSerial;
    }
    /**
     * 设置车型系列
     * @param modelSerial 车型系列
     */
    public  void  setModelSerial(String modelSerial){
        this.modelSerial = modelSerial;
    }
    /**
     * 获取发动机号 
     * @return 发动机号
     */
    public  String  getEngineNo(){
        return engineNo;
    }
    /**
     * 设置发动机号
     * @param engineNo 发动机号
     */
    public  void  setEngineNo(String engineNo){
        this.engineNo = engineNo;
    }
    /**
     * 获取发证机关 
     * @return 发证机关
     */
    public  String  getCertDept(){
        return certDept;
    }
    /**
     * 设置发证机关
     * @param certDept 发证机关
     */
    public  void  setCertDept(String certDept){
        this.certDept = certDept;
    }
    /**
     * 获取实际自重 
     * @return 实际自重
     */
    public  java.math.BigDecimal  getEmptyWeight(){
        return emptyWeight;
    }
    /**
     * 设置实际自重
     * @param emptyWeight 实际自重
     */
    public  void  setEmptyWeight(java.math.BigDecimal emptyWeight){
        this.emptyWeight = emptyWeight;
    }
    /**
     * 获取户主 
     * @return 户主
     */
    public  String  getOwner(){
        return owner;
    }
    /**
     * 设置户主
     * @param owner 户主
     */
    public  void  setOwner(String owner){
        this.owner = owner;
    }
    /**
     * 获取驾驶证号 
     * @return 驾驶证号
     */
    public  String  getDriveNo(){
        return driveNo;
    }
    /**
     * 设置驾驶证号
     * @param driveNo 驾驶证号
     */
    public  void  setDriveNo(String driveNo){
        this.driveNo = driveNo;
    }
    /**
     * 获取驾驶证类别 
     * @return 驾驶证类别
     */
    public  String  getDriveType(){
        return driveType;
    }
    /**
     * 设置驾驶证类别
     * @param driveType 驾驶证类别
     */
    public  void  setDriveType(String driveType){
        this.driveType = driveType;
    }
    /**
     * 获取联系方式 
     * @return 联系方式
     */
    public  String  getContact(){
        return contact;
    }
    /**
     * 设置联系方式
     * @param contact 联系方式
     */
    public  void  setContact(String contact){
        this.contact = contact;
    }
    /**
     * 获取性别 
     * @return 性别
     */
    public  String  getGender(){
        return gender;
    }
    /**
     * 设置性别
     * @param gender 性别
     */
    public  void  setGender(String gender){
        this.gender = gender;
    }
    /**
     * 获取身份证号 
     * @return 身份证号
     */
    public  String  getIdNo(){
        return idNo;
    }
    /**
     * 设置身份证号
     * @param idNo 身份证号
     */
    public  void  setIdNo(String idNo){
        this.idNo = idNo;
    }
    /**
     * 获取轴数 
     * @return 轴数
     */
    public  Long  getAxleNum(){
        return axleNum;
    }
    /**
     * 设置轴数
     * @param axleNum 轴数
     */
    public  void  setAxleNum(Long axleNum){
        this.axleNum = axleNum;
    }
    /**
     * 获取车前图片 
     * @return 车前图片
     */
    public  String  getPicFront(){
        return picFront;
    }
    /**
     * 设置车前图片
     * @param picFront 车前图片
     */
    public  void  setPicFront(String picFront){
        this.picFront = picFront;
    }
    /**
     * 获取车后图片 
     * @return 车后图片
     */
    public  String  getPicBack(){
        return picBack;
    }
    /**
     * 设置车后图片
     * @param picBack 车后图片
     */
    public  void  setPicBack(String picBack){
        this.picBack = picBack;
    }
    //结束vo的setter和getter方法    
}
