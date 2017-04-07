/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  VehicleSafetyInspectionVo.java
 *
 * 功能描述：  车辆安全检查值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.vehiclesafetyinspection.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 车辆安全检查值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class VehicleSafetyInspectionVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * ladingBillId 表示：提货单ID
     */
    private  String  ladingBillId;
    /**
     * ladingBillCode 表示：提货单号
     */
    private  String  ladingBillCode;
    /**
     * licensePlate 表示：车牌号码
     */
    private  String  licensePlate;
    /**
     * shippingOilVariety 表示：装运油品种
     */
    private  String  shippingOilVariety;
    /**
     * tankVolume 表示：槽车容积
     */
    private  String  tankVolume;
    /**
     * maxWorkingPressureTank 表示：槽车最大工作压力
     */
    private  String  maxWorkingPressureTank;
    /**
     * maxLoadCarryingTank 表示：槽车最大载负荷量
     */
    private  String  maxLoadCarryingTank;
    /**
     * beforeResidualPressureTank 表示：卸载前的罐体余压
     */
    private  String  beforeResidualPressureTank;
    /**
     * isConsistentOriOil 表示：与原装油品种是否一致
     */
    private  String  isConsistentOriOil;
    /**
     * brakeWell 表示：紧急切断装置、根部阀完好
     */
    private  String  brakeWell;
    /**
     * staticGroundingZone 表示：静电接地带完好
     */
    private  String  staticGroundingZone;
    /**
     * pressureGaugeLevelGauge 表示：压力表、液位计等完好
     */
    private  String  pressureGaugeLevelGauge;
    /**
     * gps 表示：车载GPS定位系统
     */
    private  String  gps;
    /**
     * safetyValveIsGood 表示：安全阀完好
     */
    private  String  safetyValveIsGood;
    /**
     * normalVehicleBrake 表示：车辆制动正常(手刹)
     */
    private  String  normalVehicleBrake;
    /**
     * fireExtinguisher 表示：车载灭火器 8kg x 2完好
     */
    private  String  fireExtinguisher;
    /**
     * flashHider 表示：防火罩完好，阀关闭
     */
    private  String  flashHider;
    /**
     * driverLicensePass 表示：司机驾驶证合格
     */
    private  String  driverLicensePass;
    /**
     * ownerName 表示：行车证合格(车主姓名)
     */
    private  String  ownerName;
    /**
     * dangerousQualifiedEscort 表示：危险品押运员证合格
     */
    private  String  dangerousQualifiedEscort;
    /**
     * dangerousTransCertificate 表示：危化品运输证合格
     */
    private  String  dangerousTransCertificate;
    /**
     * pressureVesselCalibration 表示：压力容器校验证合格
     */
    private  String  pressureVesselCalibration;
    /**
     * withoutFireworks 表示：驾驶室及司乘不带烟火
     */
    private  String  withoutFireworks;
    /**
     * checkMan 表示：检查人
     */
    private  String  checkMan;
    /**
     * checkTime 表示：检查日期时间
     */
    private  Timestamp  checkTime;
    /**
     * status 表示：状态
     */
    private  String  status;
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
     * 产品名称
     */
    private String productName;
    /**
     * checkMan 表示：第一次检查人
     */
    private  String  firstCheckMan;
    /**
     * checkTime 表示：第一次检查日期时间
     */
    private  Timestamp  firstcheckTime;
    /**
     * checkMan 表示：第二次检查人
     */
    private  String  secondCheckMan;
    /**
     * checkTime 表示：第二次检查日期时间
     */
    private  Timestamp  secondcheckTime;
    /**
     * checkMan 表示：第三次检查人
     */
    private  String  threeCheckMan;
    /**
     * checkTime 表示：第三次检查日期时间
     */
    private  Timestamp  threecheckTime;

    //结束vo的属性
        
        

	//开始vo的setter和getter方法
    /**
     * 获得第一次检查人
     * 
     * @return firstCheckMan
     */
    public String getFirstCheckMan() {
		return firstCheckMan;
	}
    /**
     * 设置第一次检查人
     * 
     * @param firstCheckMan 第一次检查人
     */
	public void setFirstCheckMan(String firstCheckMan) {
		this.firstCheckMan = firstCheckMan;
	}
	/**
     * 获得第一次检查时间
     * 
     * @return firstheckTime
     */
	public Timestamp getFirstcheckTime() {
		return firstcheckTime;
	}
	 /**
     * 设置第一次检查时间
     * 
     * @param firstcheckTime 第一次检查时间
     */
	public void setFirstcheckTime(Timestamp firstcheckTime) {
		this.firstcheckTime = firstcheckTime;
	}
	 /**
     * 获得第二次检查人
     * 
     * @return secondCheckMan
     */
	public String getSecondCheckMan() {
		return secondCheckMan;
	}
	 /**
     * 设置第二次检查人
     * 
     * @param secondCheckMan 第二次检查人
     */
	public void setSecondCheckMan(String secondCheckMan) {
		this.secondCheckMan = secondCheckMan;
	}
	/**
     * 获得第二次检查时间
     * 
     * @return secondcheckTime
     */
	public Timestamp getSecondcheckTime() {
		return secondcheckTime;
	}
	 /**
     * 设置第二次检查时间
     * 
     * @param secondcheckTime 第二次检查时间
     */
	public void setSecondcheckTime(Timestamp secondcheckTime) {
		this.secondcheckTime = secondcheckTime;
	}
	 /**
     * 获得第三次检查人
     * 
     * @return threeCheckMan
     */
	public String getThreeCheckMan() {
		return threeCheckMan;
	}
	 /**
     * 设置第三次检查人
     * 
     * @param threeCheckMan 第三次检查人
     */
	public void setThreeCheckMan(String threeCheckMan) {
		this.threeCheckMan = threeCheckMan;
	}
	/**
     * 获得第三次检查时间
     * 
     * @return threecheckTime
     */
	public Timestamp getThreecheckTime() {
		return threecheckTime;
	}
	 /**
     * 设置第三次检查时间
     * 
     * @param threecheckTime 第三次检查时间
     */
	public void setThreecheckTime(Timestamp threecheckTime) {
		this.threecheckTime = threecheckTime;
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
     * 获取提货单ID 
     * @return 提货单ID
     */
    public  String  getLadingBillId(){
        return ladingBillId;
    }
    /**
     * 设置提货单ID
     * @param ladingBillId 提货单ID
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
     * 获取装运油品种 
     * @return 装运油品种
     */
    public  String  getShippingOilVariety(){
        return shippingOilVariety;
    }
    /**
     * 设置装运油品种
     * @param shippingOilVariety 装运油品种
     */
    public  void  setShippingOilVariety(String shippingOilVariety){
        this.shippingOilVariety = shippingOilVariety;
    }
    /**
     * 获取槽车容积 
     * @return 槽车容积
     */
    public  String  getTankVolume(){
        return tankVolume;
    }
    /**
     * 设置槽车容积
     * @param tankVolume 槽车容积
     */
    public  void  setTankVolume(String tankVolume){
        this.tankVolume = tankVolume;
    }
    /**
     * 获取槽车最大工作压力 
     * @return 槽车最大工作压力
     */
    public  String  getMaxWorkingPressureTank(){
        return maxWorkingPressureTank;
    }
    /**
     * 设置槽车最大工作压力
     * @param maxWorkingPressureTank 槽车最大工作压力
     */
    public  void  setMaxWorkingPressureTank(String maxWorkingPressureTank){
        this.maxWorkingPressureTank = maxWorkingPressureTank;
    }
    /**
     * 获取槽车最大载负荷量 
     * @return 槽车最大载负荷量
     */
    public  String  getMaxLoadCarryingTank(){
        return maxLoadCarryingTank;
    }
    /**
     * 设置槽车最大载负荷量
     * @param maxLoadCarryingTank 槽车最大载负荷量
     */
    public  void  setMaxLoadCarryingTank(String maxLoadCarryingTank){
        this.maxLoadCarryingTank = maxLoadCarryingTank;
    }
    /**
     * 获取卸载前的罐体余压 
     * @return 卸载前的罐体余压
     */
    public  String  getBeforeResidualPressureTank(){
        return beforeResidualPressureTank;
    }
    /**
     * 设置卸载前的罐体余压
     * @param beforeResidualPressureTank 卸载前的罐体余压
     */
    public  void  setBeforeResidualPressureTank(String beforeResidualPressureTank){
        this.beforeResidualPressureTank = beforeResidualPressureTank;
    }
    /**
     * 获取与原装油品种是否一致 
     * @return 与原装油品种是否一致
     */
    public  String  getIsConsistentOriOil(){
        return isConsistentOriOil;
    }
    /**
     * 设置与原装油品种是否一致
     * @param isConsistentOriOil 与原装油品种是否一致
     */
    public  void  setIsConsistentOriOil(String isConsistentOriOil){
        this.isConsistentOriOil = isConsistentOriOil;
    }
    /**
     * 获取紧急切断装置、根部阀完好 
     * @return 紧急切断装置、根部阀完好
     */
    public  String  getBrakeWell(){
        return brakeWell;
    }
    /**
     * 设置紧急切断装置、根部阀完好
     * @param brakeWell 紧急切断装置、根部阀完好
     */
    public  void  setBrakeWell(String brakeWell){
        this.brakeWell = brakeWell;
    }
    /**
     * 获取静电接地带完好 
     * @return 静电接地带完好
     */
    public  String  getStaticGroundingZone(){
        return staticGroundingZone;
    }
    /**
     * 设置静电接地带完好
     * @param staticGroundingZone 静电接地带完好
     */
    public  void  setStaticGroundingZone(String staticGroundingZone){
        this.staticGroundingZone = staticGroundingZone;
    }
    /**
     * 获取压力表、液位计等完好 
     * @return 压力表、液位计等完好
     */
    public  String  getPressureGaugeLevelGauge(){
        return pressureGaugeLevelGauge;
    }
    /**
     * 设置压力表、液位计等完好
     * @param pressureGaugeLevelGauge 压力表、液位计等完好
     */
    public  void  setPressureGaugeLevelGauge(String pressureGaugeLevelGauge){
        this.pressureGaugeLevelGauge = pressureGaugeLevelGauge;
    }
    /**
     * 获取车载GPS定位系统 
     * @return 车载GPS定位系统
     */
    public  String  getGps(){
        return gps;
    }
    /**
     * 设置车载GPS定位系统
     * @param gps 车载GPS定位系统
     */
    public  void  setGps(String gps){
        this.gps = gps;
    }
    /**
     * 获取安全阀完好 
     * @return 安全阀完好
     */
    public  String  getSafetyValveIsGood(){
        return safetyValveIsGood;
    }
    /**
     * 设置安全阀完好
     * @param safetyValveIsGood 安全阀完好
     */
    public  void  setSafetyValveIsGood(String safetyValveIsGood){
        this.safetyValveIsGood = safetyValveIsGood;
    }
    /**
     * 获取车辆制动正常(手刹) 
     * @return 车辆制动正常(手刹)
     */
    public  String  getNormalVehicleBrake(){
        return normalVehicleBrake;
    }
    /**
     * 设置车辆制动正常(手刹)
     * @param normalVehicleBrake 车辆制动正常(手刹)
     */
    public  void  setNormalVehicleBrake(String normalVehicleBrake){
        this.normalVehicleBrake = normalVehicleBrake;
    }
    /**
     * 获取车载灭火器 8kg x 2完好 
     * @return 车载灭火器 8kg x 2完好
     */
    public  String  getFireExtinguisher(){
        return fireExtinguisher;
    }
    /**
     * 设置车载灭火器 8kg x 2完好
     * @param fireExtinguisher 车载灭火器 8kg x 2完好
     */
    public  void  setFireExtinguisher(String fireExtinguisher){
        this.fireExtinguisher = fireExtinguisher;
    }
    /**
     * 获取防火罩完好，阀关闭 
     * @return 防火罩完好，阀关闭
     */
    public  String  getFlashHider(){
        return flashHider;
    }
    /**
     * 设置防火罩完好，阀关闭
     * @param flashHider 防火罩完好，阀关闭
     */
    public  void  setFlashHider(String flashHider){
        this.flashHider = flashHider;
    }
    /**
     * 获取司机驾驶证合格 
     * @return 司机驾驶证合格
     */
    public  String  getDriverLicensePass(){
        return driverLicensePass;
    }
    /**
     * 设置司机驾驶证合格
     * @param driverLicensePass 司机驾驶证合格
     */
    public  void  setDriverLicensePass(String driverLicensePass){
        this.driverLicensePass = driverLicensePass;
    }
    /**
     * 获取行车证合格(车主姓名) 
     * @return 行车证合格(车主姓名)
     */
    public  String  getOwnerName(){
        return ownerName;
    }
    /**
     * 设置行车证合格(车主姓名)
     * @param ownerName 行车证合格(车主姓名)
     */
    public  void  setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    /**
     * 获取危险品押运员证合格 
     * @return 危险品押运员证合格
     */
    public  String  getDangerousQualifiedEscort(){
        return dangerousQualifiedEscort;
    }
    /**
     * 设置危险品押运员证合格
     * @param dangerousQualifiedEscort 危险品押运员证合格
     */
    public  void  setDangerousQualifiedEscort(String dangerousQualifiedEscort){
        this.dangerousQualifiedEscort = dangerousQualifiedEscort;
    }
    /**
     * 获取危化品运输证合格 
     * @return 危化品运输证合格
     */
    public  String  getDangerousTransCertificate(){
        return dangerousTransCertificate;
    }
    /**
     * 设置危化品运输证合格
     * @param dangerousTransCertificate 危化品运输证合格
     */
    public  void  setDangerousTransCertificate(String dangerousTransCertificate){
        this.dangerousTransCertificate = dangerousTransCertificate;
    }
    /**
     * 获取压力容器校验证合格 
     * @return 压力容器校验证合格
     */
    public  String  getPressureVesselCalibration(){
        return pressureVesselCalibration;
    }
    /**
     * 设置压力容器校验证合格
     * @param pressureVesselCalibration 压力容器校验证合格
     */
    public  void  setPressureVesselCalibration(String pressureVesselCalibration){
        this.pressureVesselCalibration = pressureVesselCalibration;
    }
    /**
     * 获取驾驶室及司乘不带烟火 
     * @return 驾驶室及司乘不带烟火
     */
    public  String  getWithoutFireworks(){
        return withoutFireworks;
    }
    /**
     * 设置驾驶室及司乘不带烟火
     * @param withoutFireworks 驾驶室及司乘不带烟火
     */
    public  void  setWithoutFireworks(String withoutFireworks){
        this.withoutFireworks = withoutFireworks;
    }
    /**
     * 获取检查人 
     * @return 检查人
     */
    public  String  getCheckMan(){
        return checkMan;
    }
    /**
     * 设置检查人
     * @param checkMan 检查人
     */
    public  void  setCheckMan(String checkMan){
        this.checkMan = checkMan;
    }
    /**
     * 获取检查日期时间 
     * @return 检查日期时间
     */
    public  Timestamp  getCheckTime(){
        return checkTime;
    }
    /**
     * 设置检查日期时间
     * @param checkTime 检查日期时间
     */
    public  void  setCheckTime(Timestamp checkTime){
        this.checkTime = checkTime;
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
    //结束vo的setter和getter方法    

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
    
    
}
