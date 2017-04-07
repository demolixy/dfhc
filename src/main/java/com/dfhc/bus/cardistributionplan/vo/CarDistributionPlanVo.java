/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  CarDistributionPlanVo.java
 *
 * 功能描述：  汽运配送计划值对象
 * 
 * 版本历史：
 * 
 * 2017-03-10   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.cardistributionplan.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 汽运配送计划值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class CarDistributionPlanVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * planCode 表示：计划单号
     */
    private  String  planCode;
    /**
     * distributionPlanId 表示：配送计划ID
     */
    private  String  distributionPlanId;
    /**
     * businessType 表示：业务类型
     */
    private  String  businessType;
    
    /**
     * sendCarPlanImage 表示：图片
     */
    private  String  sendCarPlanImage;
    /**
     * makeDate 表示：制表日期
     */
    private  java.sql.Date  makeDate;
    /**
     * logisticsCompanyId 表示：物流公司ID
     */
    private  String  logisticsCompanyId;
    /**
     * logisticsCompany 表示：物流公司
     */
    private  String  logisticsCompany;
    /**
     * province 表示：省
     */
    private  String  province;
    /**
     * provinceId 表示：省ID
     */
    private  String  provinceId;
    /**
     * region 表示：区域
     */
    private  String  region;
    /**
     * customerId 表示：客户ID
     */
    private  Long  customerId;
    /**
     * customerName 表示：客户名称
     */
    private  String  customerName;
    /**
     * productId 表示：产品ID
     */
    private  String  productId;
    /**
     * goodsName 表示：货物名称
     */
    private  String  goodsName;
    /**
     * shipMode 表示：发运方式
     */
    private  String  shipMode;
    /**
     * shipModeCode 表示：发运方式编码
     */
    private  String  shipModeCode;
    /**
     * receivingPerson 表示：接货人
     */
    private  String  receivingPerson;
    /**
     * receivingPersonPhone 表示：接货人电话
     */
    private  String  receivingPersonPhone;
    /**
     * area 表示：地区
     */
    private  String  area;
    /**
     * shippingAddress 表示：收货详细地址
     */
    private  String  shippingAddress;
    /**
     * num 表示：数量(吨)
     */
    private  Long  num;
    /**
     * planCarNum 表示：计划车数
     */
    private  Long  planCarNum;
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
     * tonKm 表示：吨/公里
     */
    private  java.math.BigDecimal  tonKm;
    /**
     * settlementFreight 表示：结算运费
     */
    private  java.math.BigDecimal  settlementFreight;
    /**
     * totalFreight 表示：总运费
     */
    private  java.math.BigDecimal  totalFreight;
    /**
     * price 表示：价格
     */
    private  java.math.BigDecimal  price;
    /**
     * remark 表示：备注
     */
    private  String  remark;
    /**
     * sendCarDate 表示：发车日期
     */
    private  java.sql.Date  sendCarDate;
    /**
     * status 表示：状态
     */
    private  String  status;
    /**
     * remainderNum 表示：剩余数量(吨)
     */
    private  Long  remainderNum;
    /**
     * remainderCarNum 表示：剩余车数
     */
    private  Long  remainderCarNum;
    /**
     * shipperCode 表示：发货公司编码
     */
    private  String  shipperCode;
    /**
     * shippingVehiclesNumber 表示：已发运车数
     */
    private  Long  shippingVehiclesNumber;
    /**
     * shippingNumber 表示：已发运数量
     */
    private  Long  shippingNumber;
    /**
     * flowToId 表示：流向ID
     */
    private  String  flowToId;
    /**
     * sequenceNumber 表示：序号
     */
    private  Long  sequenceNumber;
    /**
     * acceptOrderTime 表示：接受订单时间
     */
    private  java.sql.Date  acceptOrderTime;
    /**
     * roundingPrice 表示：取整单价
     */
    private  java.math.BigDecimal  roundingPrice;
    /**
     * productLevel 表示：产品等级
     */
    private  String  productLevel;
    /**
     * productLevelId 表示：产品等级ID
     */
    private  String  productLevelId;
    /**
     * unitPrice 表示：单价(元/吨)
     */
    private  java.math.BigDecimal  unitPrice;
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
     * contractPrice 表示: 元/吨.公里（合同）
     */
    private String contractPrice;

    //结束vo的属性
        
        
    

	//开始vo的setter和getter方法
    
    /**
     * 获得元/吨.公里（合同）
     * 
     * @return 元/吨.公里（合同）
     */
    public String getContractPrice() {
		return contractPrice;
	}
    /**
     * 设置元/吨.公里（合同）
     * 
     * @param contractPrice 元/吨.公里（合同）
     */
	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
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
     * 获取计划单号 
     * @return 计划单号
     */
    public  String  getPlanCode(){
        return planCode;
    }
    /**
     * 设置计划单号
     * @param planCode 计划单号
     */
    public  void  setPlanCode(String planCode){
        this.planCode = planCode;
    }
    /**
     * 获取配送计划ID 
     * @return 配送计划ID
     */
    public  String  getDistributionPlanId(){
        return distributionPlanId;
    }
    /**
     * 设置配送计划ID
     * @param distributionPlanId 配送计划ID
     */
    public  void  setDistributionPlanId(String distributionPlanId){
        this.distributionPlanId = distributionPlanId;
    }
    /**
     * 获取业务类型 
     * @return 业务类型
     */
    public  String  getBusinessType(){
        return businessType;
    }
    /**
     * 设置业务类型
     * @param businessType 业务类型
     */
    public  void  setBusinessType(String businessType){
        this.businessType = businessType;
    }
    /**
     * 获取制表日期 
     * @return 制表日期
     */
    public  java.sql.Date  getMakeDate(){
        return makeDate;
    }
    /**
     * 设置制表日期
     * @param makeDate 制表日期
     */
    public  void  setMakeDate(java.sql.Date makeDate){
        this.makeDate = makeDate;
    }
    /**
     * 获取物流公司ID 
     * @return 物流公司ID
     */
    public  String  getLogisticsCompanyId(){
        return logisticsCompanyId;
    }
    /**
     * 设置物流公司ID
     * @param logisticsCompanyId 物流公司ID
     */
    public  void  setLogisticsCompanyId(String logisticsCompanyId){
        this.logisticsCompanyId = logisticsCompanyId;
    }
    /**
     * 获取物流公司 
     * @return 物流公司
     */
    public  String  getLogisticsCompany(){
        return logisticsCompany;
    }
    /**
     * 设置物流公司
     * @param logisticsCompany 物流公司
     */
    public  void  setLogisticsCompany(String logisticsCompany){
        this.logisticsCompany = logisticsCompany;
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
     * 获取区域 
     * @return 区域
     */
    public  String  getRegion(){
        return region;
    }
    /**
     * 设置区域
     * @param region 区域
     */
    public  void  setRegion(String region){
        this.region = region;
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
     * 获取货物名称 
     * @return 货物名称
     */
    public  String  getGoodsName(){
        return goodsName;
    }
    /**
     * 设置货物名称
     * @param goodsName 货物名称
     */
    public  void  setGoodsName(String goodsName){
        this.goodsName = goodsName;
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
     * 获取发运方式编码 
     * @return 发运方式编码
     */
    public  String  getShipModeCode(){
        return shipModeCode;
    }
    /**
     * 设置发运方式编码
     * @param shipModeCode 发运方式编码
     */
    public  void  setShipModeCode(String shipModeCode){
        this.shipModeCode = shipModeCode;
    }
    /**
     * 获取接货人 
     * @return 接货人
     */
    public  String  getReceivingPerson(){
        return receivingPerson;
    }
    /**
     * 设置接货人
     * @param receivingPerson 接货人
     */
    public  void  setReceivingPerson(String receivingPerson){
        this.receivingPerson = receivingPerson;
    }
    /**
     * 获取接货人电话 
     * @return 接货人电话
     */
    public  String  getReceivingPersonPhone(){
        return receivingPersonPhone;
    }
    /**
     * 设置接货人电话
     * @param receivingPersonPhone 接货人电话
     */
    public  void  setReceivingPersonPhone(String receivingPersonPhone){
        this.receivingPersonPhone = receivingPersonPhone;
    }
    /**
     * 获取地区 
     * @return 地区
     */
    public  String  getArea(){
        return area;
    }
    /**
     * 设置地区
     * @param area 地区
     */
    public  void  setArea(String area){
        this.area = area;
    }
    /**
     * 获取收货详细地址 
     * @return 收货详细地址
     */
    public  String  getShippingAddress(){
        return shippingAddress;
    }
    /**
     * 设置收货详细地址
     * @param shippingAddress 收货详细地址
     */
    public  void  setShippingAddress(String shippingAddress){
        this.shippingAddress = shippingAddress;
    }
    /**
     * 获取数量(吨) 
     * @return 数量(吨)
     */
    public  Long  getNum(){
        return num;
    }
    /**
     * 设置数量(吨)
     * @param num 数量(吨)
     */
    public  void  setNum(Long num){
        this.num = num;
    }
    /**
     * 获取计划车数 
     * @return 计划车数
     */
    public  Long  getPlanCarNum(){
        return planCarNum;
    }
    /**
     * 设置计划车数
     * @param planCarNum 计划车数
     */
    public  void  setPlanCarNum(Long planCarNum){
        this.planCarNum = planCarNum;
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
    public  java.math.BigDecimal  getFactValuationMileage(){
        return factValuationMileage;
    }
    /**
     * 设置实际计价里程
     * @param factValuationMileage 实际计价里程
     */
    public  void  setFactValuationMileage(java.math.BigDecimal factValuationMileage){
        this.factValuationMileage = factValuationMileage;
    }
    /**
     * 获取吨/公里 
     * @return 吨/公里
     */
    public  java.math.BigDecimal  getTonKm(){
        return tonKm;
    }
    /**
     * 设置吨/公里
     * @param tonKm 吨/公里
     */
    public  void  setTonKm(java.math.BigDecimal tonKm){
        this.tonKm = tonKm;
    }
    /**
     * 获取结算运费 
     * @return 结算运费
     */
    public  java.math.BigDecimal  getSettlementFreight(){
        return settlementFreight;
    }
    /**
     * 设置结算运费
     * @param settlementFreight 结算运费
     */
    public  void  setSettlementFreight(java.math.BigDecimal settlementFreight){
        this.settlementFreight = settlementFreight;
    }
    /**
     * 获取总运费 
     * @return 总运费
     */
    public  java.math.BigDecimal  getTotalFreight(){
        return totalFreight;
    }
    /**
     * 设置总运费
     * @param totalFreight 总运费
     */
    public  void  setTotalFreight(java.math.BigDecimal totalFreight){
        this.totalFreight = totalFreight;
    }
    /**
     * 获取价格 
     * @return 价格
     */
    public  java.math.BigDecimal  getPrice(){
        return price;
    }
    /**
     * 设置价格
     * @param price 价格
     */
    public  void  setPrice(java.math.BigDecimal price){
        this.price = price;
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
     * 获取发车日期 
     * @return 发车日期
     */
    public  java.sql.Date  getSendCarDate(){
        return sendCarDate;
    }
    /**
     * 设置发车日期
     * @param sendCarDate 发车日期
     */
    public  void  setSendCarDate(java.sql.Date sendCarDate){
        this.sendCarDate = sendCarDate;
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
     * 获取剩余数量(吨) 
     * @return 剩余数量(吨)
     */
    public  Long  getRemainderNum(){
        return remainderNum;
    }
    /**
     * 设置剩余数量(吨)
     * @param remainderNum 剩余数量(吨)
     */
    public  void  setRemainderNum(Long remainderNum){
        this.remainderNum = remainderNum;
    }
    /**
     * 获取剩余车数 
     * @return 剩余车数
     */
    public  Long  getRemainderCarNum(){
        return remainderCarNum;
    }
    /**
     * 设置剩余车数
     * @param remainderCarNum 剩余车数
     */
    public  void  setRemainderCarNum(Long remainderCarNum){
        this.remainderCarNum = remainderCarNum;
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
     * 获取已发运车数 
     * @return 已发运车数
     */
    public  Long  getShippingVehiclesNumber(){
        return shippingVehiclesNumber;
    }
    /**
     * 设置已发运车数
     * @param shippingVehiclesNumber 已发运车数
     */
    public  void  setShippingVehiclesNumber(Long shippingVehiclesNumber){
        this.shippingVehiclesNumber = shippingVehiclesNumber;
    }
    /**
     * 获取已发运数量 
     * @return 已发运数量
     */
    public  Long  getShippingNumber(){
        return shippingNumber;
    }
    /**
     * 设置已发运数量
     * @param shippingNumber 已发运数量
     */
    public  void  setShippingNumber(Long shippingNumber){
        this.shippingNumber = shippingNumber;
    }
    /**
     * 获取流向ID 
     * @return 流向ID
     */
    public  String  getFlowToId(){
        return flowToId;
    }
    /**
     * 设置流向ID
     * @param flowToId 流向ID
     */
    public  void  setFlowToId(String flowToId){
        this.flowToId = flowToId;
    }
    /**
     * 获取序号 
     * @return 序号
     */
    public  Long  getSequenceNumber(){
        return sequenceNumber;
    }
    /**
     * 设置序号
     * @param sequenceNumber 序号
     */
    public  void  setSequenceNumber(Long sequenceNumber){
        this.sequenceNumber = sequenceNumber;
    }
    /**
     * 获取接受订单时间 
     * @return 接受订单时间
     */
    public  java.sql.Date  getAcceptOrderTime(){
        return acceptOrderTime;
    }
    /**
     * 设置接受订单时间
     * @param acceptOrderTime 接受订单时间
     */
    public  void  setAcceptOrderTime(java.sql.Date acceptOrderTime){
        this.acceptOrderTime = acceptOrderTime;
    }
    /**
     * 获取取整单价 
     * @return 取整单价
     */
    public  java.math.BigDecimal  getRoundingPrice(){
        return roundingPrice;
    }
    /**
     * 设置取整单价
     * @param roundingPrice 取整单价
     */
    public  void  setRoundingPrice(java.math.BigDecimal roundingPrice){
        this.roundingPrice = roundingPrice;
    }
    /**
     * 获取产品等级 
     * @return 产品等级
     */
    public  String  getProductLevel(){
        return productLevel;
    }
    /**
     * 设置产品等级
     * @param productLevel 产品等级
     */
    public  void  setProductLevel(String productLevel){
        this.productLevel = productLevel;
    }
    /**
     * 获取产品等级ID 
     * @return 产品等级ID
     */
    public  String  getProductLevelId(){
        return productLevelId;
    }
    /**
     * 设置产品等级ID
     * @param productLevelId 产品等级ID
     */
    public  void  setProductLevelId(String productLevelId){
        this.productLevelId = productLevelId;
    }
    /**
     * 获取单价(元/吨) 
     * @return 单价(元/吨)
     */
    public  java.math.BigDecimal  getUnitPrice(){
        return unitPrice;
    }
    /**
     * 设置单价(元/吨)
     * @param unitPrice 单价(元/吨)
     */
    public  void  setUnitPrice(java.math.BigDecimal unitPrice){
        this.unitPrice = unitPrice;
    }

	public String getSendCarPlanImage() {
		return sendCarPlanImage;
	}

	public void setSendCarPlanImage(String sendCarPlanImage) {
		this.sendCarPlanImage = sendCarPlanImage;
	}
    
    //结束vo的setter和getter方法    
}
