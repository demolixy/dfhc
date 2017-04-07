/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  LogisticsCostSettlementVo.java
 *
 * 功能描述：  物流费用结算审批单值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.acc.logisticscostsettlement.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 物流费用结算审批单值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class LogisticsCostSettlementVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * settlementType 表示：结算类型
     */
    private  String  settlementType;
    /**
     * documentNumber 表示：单据编号
     */
    private  String  documentNumber;
    /**
     * settlementStartDate 表示：结算开始日期
     */
    private  java.sql.Date  settlementStartDate;
    /**
     * settlementEndDate 表示：结算结束日期
     */
    private  java.sql.Date  settlementEndDate;
    /**
     * logisticsCompany 表示：第三方物流公司名称
     */
    private  String  logisticsCompany;
    /**
     * logisticsCompanyId 表示：第三方物流公司ID
     */
    private  String  logisticsCompanyId;
    /**
     * taxNo 表示：纳税人识别号
     */
    private  String  taxNo;
    /**
     * address 表示：地址
     */
    private  String  address;
    /**
     * tel 表示：电话
     */
    private  String  tel;
    /**
     * bank 表示：开户银行
     */
    private  String  bank;
    /**
     * accountName 表示：账号
     */
    private  String  accountName;
    /**
     * amountPayable 表示：应付款金额
     */
    private  java.math.BigDecimal  amountPayable;
    /**
     * amountPayableCapital 表示：应付金额(大写)
     */
    private  String  amountPayableCapital;
    /**
     * remark 表示：备注
     */
    private  String  remark;
    /**
     * totalQuantity 表示：数量合计(吨)
     */
    private  java.math.BigDecimal  totalQuantity;
    /**
     * totalValuationMileage 表示：计价总里程
     */
    private  java.math.BigDecimal  totalValuationMileage;
    /**
     * productTotalCost 表示：产品总费用(元)
     */
    private  java.math.BigDecimal  productTotalCost;
    /**
     * shipperCode 表示：发货公司编码
     */
    private  String  shipperCode;
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
     * 获取结算类型 
     * @return 结算类型
     */
    public  String  getSettlementType(){
        return settlementType;
    }
    /**
     * 设置结算类型
     * @param settlementType 结算类型
     */
    public  void  setSettlementType(String settlementType){
        this.settlementType = settlementType;
    }
    /**
     * 获取单据编号 
     * @return 单据编号
     */
    public  String  getDocumentNumber(){
        return documentNumber;
    }
    /**
     * 设置单据编号
     * @param documentNumber 单据编号
     */
    public  void  setDocumentNumber(String documentNumber){
        this.documentNumber = documentNumber;
    }
    /**
     * 获取结算开始日期 
     * @return 结算开始日期
     */
    public  java.sql.Date  getSettlementStartDate(){
        return settlementStartDate;
    }
    /**
     * 设置结算开始日期
     * @param settlementStartDate 结算开始日期
     */
    public  void  setSettlementStartDate(java.sql.Date settlementStartDate){
        this.settlementStartDate = settlementStartDate;
    }
    /**
     * 获取结算结束日期 
     * @return 结算结束日期
     */
    public  java.sql.Date  getSettlementEndDate(){
        return settlementEndDate;
    }
    /**
     * 设置结算结束日期
     * @param settlementEndDate 结算结束日期
     */
    public  void  setSettlementEndDate(java.sql.Date settlementEndDate){
        this.settlementEndDate = settlementEndDate;
    }
    /**
     * 获取第三方物流公司名称 
     * @return 第三方物流公司名称
     */
    public  String  getLogisticsCompany(){
        return logisticsCompany;
    }
    /**
     * 设置第三方物流公司名称
     * @param logisticsCompany 第三方物流公司名称
     */
    public  void  setLogisticsCompany(String logisticsCompany){
        this.logisticsCompany = logisticsCompany;
    }
    /**
     * 获取第三方物流公司ID 
     * @return 第三方物流公司ID
     */
    public  String  getLogisticsCompanyId(){
        return logisticsCompanyId;
    }
    /**
     * 设置第三方物流公司ID
     * @param logisticsCompanyId 第三方物流公司ID
     */
    public  void  setLogisticsCompanyId(String logisticsCompanyId){
        this.logisticsCompanyId = logisticsCompanyId;
    }
    /**
     * 获取纳税人识别号 
     * @return 纳税人识别号
     */
    public  String  getTaxNo(){
        return taxNo;
    }
    /**
     * 设置纳税人识别号
     * @param taxNo 纳税人识别号
     */
    public  void  setTaxNo(String taxNo){
        this.taxNo = taxNo;
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
     * 获取电话 
     * @return 电话
     */
    public  String  getTel(){
        return tel;
    }
    /**
     * 设置电话
     * @param tel 电话
     */
    public  void  setTel(String tel){
        this.tel = tel;
    }
    /**
     * 获取开户银行 
     * @return 开户银行
     */
    public  String  getBank(){
        return bank;
    }
    /**
     * 设置开户银行
     * @param bank 开户银行
     */
    public  void  setBank(String bank){
        this.bank = bank;
    }
    /**
     * 获取账号 
     * @return 账号
     */
    public  String  getAccountName(){
        return accountName;
    }
    /**
     * 设置账号
     * @param accountName 账号
     */
    public  void  setAccountName(String accountName){
        this.accountName = accountName;
    }
    /**
     * 获取应付款金额 
     * @return 应付款金额
     */
    public  java.math.BigDecimal  getAmountPayable(){
        return amountPayable;
    }
    /**
     * 设置应付款金额
     * @param amountPayable 应付款金额
     */
    public  void  setAmountPayable(java.math.BigDecimal amountPayable){
        this.amountPayable = amountPayable;
    }
    /**
     * 获取应付金额(大写) 
     * @return 应付金额(大写)
     */
    public  String  getAmountPayableCapital(){
        return amountPayableCapital;
    }
    /**
     * 设置应付金额(大写)
     * @param amountPayableCapital 应付金额(大写)
     */
    public  void  setAmountPayableCapital(String amountPayableCapital){
        this.amountPayableCapital = amountPayableCapital;
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
     * 获取数量合计(吨) 
     * @return 数量合计(吨)
     */
    public  java.math.BigDecimal  getTotalQuantity(){
        return totalQuantity;
    }
    /**
     * 设置数量合计(吨)
     * @param totalQuantity 数量合计(吨)
     */
    public  void  setTotalQuantity(java.math.BigDecimal totalQuantity){
        this.totalQuantity = totalQuantity;
    }
    /**
     * 获取计价总里程 
     * @return 计价总里程
     */
    public  java.math.BigDecimal  getTotalValuationMileage(){
        return totalValuationMileage;
    }
    /**
     * 设置计价总里程
     * @param totalValuationMileage 计价总里程
     */
    public  void  setTotalValuationMileage(java.math.BigDecimal totalValuationMileage){
        this.totalValuationMileage = totalValuationMileage;
    }
    /**
     * 获取产品总费用(元) 
     * @return 产品总费用(元)
     */
    public  java.math.BigDecimal  getProductTotalCost(){
        return productTotalCost;
    }
    /**
     * 设置产品总费用(元)
     * @param productTotalCost 产品总费用(元)
     */
    public  void  setProductTotalCost(java.math.BigDecimal productTotalCost){
        this.productTotalCost = productTotalCost;
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
}
