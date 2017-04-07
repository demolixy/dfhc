/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ReceiptConfLetterVo.java
 *
 * 功能描述：  收货确认函值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.acc.receiptconfletter.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 收货确认函值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class ReceiptConfLetterVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * documentNumber 表示：单据编号
     */
    private  String  documentNumber;
    /**
     * customerId 表示：客户ID
     */
    private  Long  customerId;
    /**
     * customerName 表示：客户名称
     */
    private  String  customerName;
    /**
     * settlementStartDate 表示：结算开始日期
     */
    private  java.sql.Date  settlementStartDate;
    /**
     * settlementEndDate 表示：结算结束日期
     */
    private  java.sql.Date  settlementEndDate;
    /**
     * applyPerson 表示：申请人
     */
    private  String  applyPerson;
    /**
     * applyPersionId 表示：申请人ID
     */
    private  Long  applyPersionId;
    /**
     * applyTime 表示：申请时间
     */
    private  java.sql.Date  applyTime;
    /**
     * totalAmount 表示：合计金额
     */
    private  java.math.BigDecimal  totalAmount;
    /**
     * bookBalance 表示：账面余额
     */
    private  java.math.BigDecimal  bookBalance;
    /**
     * shipperCode 表示：发货公司编码
     */
    private  String  shipperCode;
    /**
     * status 表示：状态
     */
    private  String  status;
    /**
     * actualSettlementAmount 表示：实际结算金额
     */
    private  java.math.BigDecimal  actualSettlementAmount;
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
     * 获取申请人 
     * @return 申请人
     */
    public  String  getApplyPerson(){
        return applyPerson;
    }
    /**
     * 设置申请人
     * @param applyPerson 申请人
     */
    public  void  setApplyPerson(String applyPerson){
        this.applyPerson = applyPerson;
    }
    /**
     * 获取申请人ID 
     * @return 申请人ID
     */
    public  Long  getApplyPersionId(){
        return applyPersionId;
    }
    /**
     * 设置申请人ID
     * @param applyPersionId 申请人ID
     */
    public  void  setApplyPersionId(Long applyPersionId){
        this.applyPersionId = applyPersionId;
    }
    /**
     * 获取申请时间 
     * @return 申请时间
     */
    public  java.sql.Date  getApplyTime(){
        return applyTime;
    }
    /**
     * 设置申请时间
     * @param applyTime 申请时间
     */
    public  void  setApplyTime(java.sql.Date applyTime){
        this.applyTime = applyTime;
    }
    /**
     * 获取合计金额 
     * @return 合计金额
     */
    public  java.math.BigDecimal  getTotalAmount(){
        return totalAmount;
    }
    /**
     * 设置合计金额
     * @param totalAmount 合计金额
     */
    public  void  setTotalAmount(java.math.BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }
    /**
     * 获取账面余额 
     * @return 账面余额
     */
    public  java.math.BigDecimal  getBookBalance(){
        return bookBalance;
    }
    /**
     * 设置账面余额
     * @param bookBalance 账面余额
     */
    public  void  setBookBalance(java.math.BigDecimal bookBalance){
        this.bookBalance = bookBalance;
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
    /**
     * 获取实际结算金额 
     * @return 实际结算金额
     */
    public  java.math.BigDecimal  getActualSettlementAmount(){
        return actualSettlementAmount;
    }
    /**
     * 设置实际结算金额
     * @param actualSettlementAmount 实际结算金额
     */
    public  void  setActualSettlementAmount(java.math.BigDecimal actualSettlementAmount){
        this.actualSettlementAmount = actualSettlementAmount;
    }
    //结束vo的setter和getter方法    
}
