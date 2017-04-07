/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  GeneralBusinessProcessVo.java
 *
 * 功能描述：  通用流程业务表值对象
 * 
 * 版本历史：
 * 
 * 2016-12-06   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.generalbusinessprocess.vo;


import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 通用流程业务表值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class GeneralBusinessProcessVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * procDefId 表示：流程定义ID
     */
    private  String  procDefId;
    /**
     * procInstId 表示：流程实例ID
     */
    private  String  procInstId;
    /**
     * procName 表示：流程名称
     */
    private  String  procName;
    /**
     * procKey 表示：流程KEY
     */
    private  String  procKey;
    /**
     * initiator 表示：发起人
     */
    private  String  initiator;
    /**
     * initiatorId 表示：发起人ID
     */
    private  String  initiatorId;
    /**
     * sendOrganizationDepartment 表示：发起机构部门
     */
    private  String  sendOrganizationDepartment;
    /**
     * initiatorOrganizationId 表示：发起机构部门ID
     */
    private  String  initiatorOrganizationId;
    /**
     * initiatorTime 表示：发起时间
     */
    private  java.sql.Timestamp  initiatorTime;
    /**
     * businessType 表示：业务类型
     */
    private  String  businessType;
    /**
     * businessGroup 表示：业务组
     */
    private  String  businessGroup;
    /**
     * parentId 表示：上级ID
     */
    private  String  parentId;
    /**
     * businessAttribute70 表示：业务扩展字段70
     */
    private  String  businessAttribute70;
    /**
     * businessAttribute69 表示：业务扩展字段69
     */
    private  String  businessAttribute69;
    /**
     * businessAttribute68 表示：业务扩展字段68
     */
    private  String  businessAttribute68;
    /**
     * businessAttribute67 表示：业务扩展字段67
     */
    private  String  businessAttribute67;
    /**
     * businessAttribute66 表示：业务扩展字段66
     */
    private  String  businessAttribute66;
    /**
     * businessAttribute65 表示：业务扩展字段65
     */
    private  String  businessAttribute65;
    /**
     * businessAttribute64 表示：业务扩展字段64
     */
    private  String  businessAttribute64;
    /**
     * businessAttribute63 表示：业务扩展字段63
     */
    private  String  businessAttribute63;
    /**
     * businessAttribute62 表示：业务扩展字段62
     */
    private  String  businessAttribute62;
    /**
     * businessAttribute61 表示：业务扩展字段61
     */
    private  String  businessAttribute61;
    /**
     * businessAttribute60 表示：业务扩展字段60
     */
    private  String  businessAttribute60;
    /**
     * businessAttribute59 表示：业务扩展字段59
     */
    private  String  businessAttribute59;
    /**
     * businessAttribute58 表示：业务扩展字段58
     */
    private  String  businessAttribute58;
    /**
     * businessAttribute57 表示：业务扩展字段57
     */
    private  String  businessAttribute57;
    /**
     * businessAttribute56 表示：业务扩展字段56
     */
    private  String  businessAttribute56;
    /**
     * businessAttribute55 表示：业务扩展字段55
     */
    private  String  businessAttribute55;
    /**
     * businessAttribute54 表示：业务扩展字段54
     */
    private  String  businessAttribute54;
    /**
     * businessAttribute53 表示：业务扩展字段53
     */
    private  String  businessAttribute53;
    /**
     * businessAttribute52 表示：业务扩展字段52
     */
    private  String  businessAttribute52;
    /**
     * businessAttribute51 表示：业务扩展字段51
     */
    private  String  businessAttribute51;
    /**
     * businessAttribute50 表示：业务扩展字段50
     */
    private  String  businessAttribute50;
    /**
     * businessAttribute49 表示：业务扩展字段49
     */
    private  String  businessAttribute49;
    /**
     * businessAttribute48 表示：业务扩展字段48
     */
    private  String  businessAttribute48;
    /**
     * businessAttribute47 表示：业务扩展字段47
     */
    private  String  businessAttribute47;
    /**
     * businessAttribute46 表示：业务扩展字段46
     */
    private  String  businessAttribute46;
    /**
     * businessAttribute45 表示：业务扩展字段45
     */
    private  String  businessAttribute45;
    /**
     * businessAttribute44 表示：业务扩展字段44
     */
    private  String  businessAttribute44;
    /**
     * businessAttribute43 表示：业务扩展字段43
     */
    private  String  businessAttribute43;
    /**
     * businessAttribute42 表示：业务扩展字段42
     */
    private  String  businessAttribute42;
    /**
     * businessAttribute41 表示：业务扩展字段41
     */
    private  String  businessAttribute41;
    /**
     * businessAttribute40 表示：业务扩展字段40
     */
    private  String  businessAttribute40;
    /**
     * businessAttribute39 表示：业务扩展字段39
     */
    private  String  businessAttribute39;
    /**
     * businessAttribute38 表示：业务扩展字段38
     */
    private  String  businessAttribute38;
    /**
     * businessAttribute37 表示：业务扩展字段37
     */
    private  String  businessAttribute37;
    /**
     * businessAttribute36 表示：业务扩展字段36
     */
    private  String  businessAttribute36;
    /**
     * businessAttribute35 表示：业务扩展字段35
     */
    private  String  businessAttribute35;
    /**
     * businessAttribute34 表示：业务扩展字段34
     */
    private  String  businessAttribute34;
    /**
     * businessAttribute33 表示：业务扩展字段33
     */
    private  String  businessAttribute33;
    /**
     * businessAttribute32 表示：业务扩展字段32
     */
    private  String  businessAttribute32;
    /**
     * businessAttribute31 表示：业务扩展字段31
     */
    private  String  businessAttribute31;
    /**
     * businessAttribute30 表示：业务扩展字段30
     */
    private  String  businessAttribute30;
    /**
     * businessAttribute29 表示：业务扩展字段29
     */
    private  String  businessAttribute29;
    /**
     * businessAttribute28 表示：业务扩展字段28
     */
    private  String  businessAttribute28;
    /**
     * businessAttribute27 表示：业务扩展字段27
     */
    private  String  businessAttribute27;
    /**
     * businessAttribute26 表示：业务扩展字段26
     */
    private  String  businessAttribute26;
    /**
     * businessAttribute25 表示：业务扩展字段25
     */
    private  String  businessAttribute25;
    /**
     * businessAttribute24 表示：业务扩展字段24
     */
    private  String  businessAttribute24;
    /**
     * businessAttribute23 表示：业务扩展字段23
     */
    private  String  businessAttribute23;
    /**
     * businessAttribute22 表示：业务扩展字段22
     */
    private  String  businessAttribute22;
    /**
     * businessAttribute21 表示：业务扩展字段21
     */
    private  String  businessAttribute21;
    /**
     * businessAttribute20 表示：业务扩展字段20
     */
    private  String  businessAttribute20;
    /**
     * businessAttribute19 表示：业务扩展字段19
     */
    private  String  businessAttribute19;
    /**
     * businessAttribute18 表示：业务扩展字段18
     */
    private  String  businessAttribute18;
    /**
     * businessAttribute17 表示：业务扩展字段17
     */
    private  String  businessAttribute17;
    /**
     * businessAttribute16 表示：业务扩展字段16
     */
    private  String  businessAttribute16;
    /**
     * businessAttribute15 表示：业务扩展字段15
     */
    private  String  businessAttribute15;
    /**
     * businessAttribute14 表示：业务扩展字段14
     */
    private  String  businessAttribute14;
    /**
     * businessAttribute13 表示：业务扩展字段13
     */
    private  String  businessAttribute13;
    /**
     * businessAttribute12 表示：业务扩展字段12
     */
    private  String  businessAttribute12;
    /**
     * businessAttribute11 表示：业务扩展字段11
     */
    private  String  businessAttribute11;
    /**
     * businessAttribute10 表示：业务扩展字段10
     */
    private  String  businessAttribute10;
    /**
     * businessAttribute9 表示：业务扩展字段9
     */
    private  String  businessAttribute9;
    /**
     * businessAttribute8 表示：业务扩展字段8
     */
    private  String  businessAttribute8;
    /**
     * businessAttribute7 表示：业务扩展字段7
     */
    private  String  businessAttribute7;
    /**
     * businessAttribute6 表示：业务扩展字段6
     */
    private  String  businessAttribute6;
    /**
     * businessAttribute5 表示：业务扩展字段5
     */
    private  String  businessAttribute5;
    /**
     * businessAttribute4 表示：业务扩展字段4
     */
    private  String  businessAttribute4;
    /**
     * businessAttribute3 表示：业务扩展字段3
     */
    private  String  businessAttribute3;
    /**
     * businessAttribute1 表示：业务扩展字段1
     */
    private  String  businessAttribute1;
    /**
     * businessAttribute2 表示：业务扩展字段2
     */
    private  String  businessAttribute2;
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
     * 获取流程定义ID 
     * @return 流程定义ID
     */
    public  String  getProcDefId(){
        return procDefId;
    }
    /**
     * 设置流程定义ID
     * @param procDefId 流程定义ID
     */
    public  void  setProcDefId(String procDefId){
        this.procDefId = procDefId;
    }
    /**
     * 获取流程实例ID 
     * @return 流程实例ID
     */
    public  String  getProcInstId(){
        return procInstId;
    }
    /**
     * 设置流程实例ID
     * @param procInstId 流程实例ID
     */
    public  void  setProcInstId(String procInstId){
        this.procInstId = procInstId;
    }
    /**
     * 获取流程名称 
     * @return 流程名称
     */
    public  String  getProcName(){
        return procName;
    }
    /**
     * 设置流程名称
     * @param procName 流程名称
     */
    public  void  setProcName(String procName){
        this.procName = procName;
    }
    /**
     * 获取流程KEY 
     * @return 流程KEY
     */
    public  String  getProcKey(){
        return procKey;
    }
    /**
     * 设置流程KEY
     * @param procKey 流程KEY
     */
    public  void  setProcKey(String procKey){
        this.procKey = procKey;
    }
    /**
     * 获取发起人 
     * @return 发起人
     */
    public  String  getInitiator(){
        return initiator;
    }
    /**
     * 设置发起人
     * @param initiator 发起人
     */
    public  void  setInitiator(String initiator){
        this.initiator = initiator;
    }
    /**
     * 获取发起人ID 
     * @return 发起人ID
     */
    public  String  getInitiatorId(){
        return initiatorId;
    }
    /**
     * 设置发起人ID
     * @param initiatorId 发起人ID
     */
    public  void  setInitiatorId(String initiatorId){
        this.initiatorId = initiatorId;
    }
    /**
     * 获取发起机构部门 
     * @return 发起机构部门
     */
    public  String  getSendOrganizationDepartment(){
        return sendOrganizationDepartment;
    }
    /**
     * 设置发起机构部门
     * @param sendOrganizationDepartment 发起机构部门
     */
    public  void  setSendOrganizationDepartment(String sendOrganizationDepartment){
        this.sendOrganizationDepartment = sendOrganizationDepartment;
    }
    /**
     * 获取发起机构部门ID 
     * @return 发起机构部门ID
     */
    public  String  getInitiatorOrganizationId(){
        return initiatorOrganizationId;
    }
    /**
     * 设置发起机构部门ID
     * @param initiatorOrganizationId 发起机构部门ID
     */
    public  void  setInitiatorOrganizationId(String initiatorOrganizationId){
        this.initiatorOrganizationId = initiatorOrganizationId;
    }
    /**
     * 获取发起时间 
     * @return 发起时间
     */
    public  java.sql.Timestamp  getInitiatorTime(){
        return initiatorTime;
    }
    /**
     * 设置发起时间
     * @param initiatorTime 发起时间
     */
    public  void  setInitiatorTime(java.sql.Timestamp initiatorTime){
        this.initiatorTime = initiatorTime;
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
     * 获取业务组 
     * @return 业务组
     */
    public  String  getBusinessGroup(){
        return businessGroup;
    }
    /**
     * 设置业务组
     * @param businessGroup 业务组
     */
    public  void  setBusinessGroup(String businessGroup){
        this.businessGroup = businessGroup;
    }
    /**
     * 获取上级ID 
     * @return 上级ID
     */
    public  String  getParentId(){
        return parentId;
    }
    /**
     * 设置上级ID
     * @param parentId 上级ID
     */
    public  void  setParentId(String parentId){
        this.parentId = parentId;
    }
    /**
     * 获取业务扩展字段70 
     * @return 业务扩展字段70
     */
    public  String  getBusinessAttribute70(){
        return businessAttribute70;
    }
    /**
     * 设置业务扩展字段70
     * @param businessAttribute70 业务扩展字段70
     */
    public  void  setBusinessAttribute70(String businessAttribute70){
        this.businessAttribute70 = businessAttribute70;
    }
    /**
     * 获取业务扩展字段69 
     * @return 业务扩展字段69
     */
    public  String  getBusinessAttribute69(){
        return businessAttribute69;
    }
    /**
     * 设置业务扩展字段69
     * @param businessAttribute69 业务扩展字段69
     */
    public  void  setBusinessAttribute69(String businessAttribute69){
        this.businessAttribute69 = businessAttribute69;
    }
    /**
     * 获取业务扩展字段68 
     * @return 业务扩展字段68
     */
    public  String  getBusinessAttribute68(){
        return businessAttribute68;
    }
    /**
     * 设置业务扩展字段68
     * @param businessAttribute68 业务扩展字段68
     */
    public  void  setBusinessAttribute68(String businessAttribute68){
        this.businessAttribute68 = businessAttribute68;
    }
    /**
     * 获取业务扩展字段67 
     * @return 业务扩展字段67
     */
    public  String  getBusinessAttribute67(){
        return businessAttribute67;
    }
    /**
     * 设置业务扩展字段67
     * @param businessAttribute67 业务扩展字段67
     */
    public  void  setBusinessAttribute67(String businessAttribute67){
        this.businessAttribute67 = businessAttribute67;
    }
    /**
     * 获取业务扩展字段66 
     * @return 业务扩展字段66
     */
    public  String  getBusinessAttribute66(){
        return businessAttribute66;
    }
    /**
     * 设置业务扩展字段66
     * @param businessAttribute66 业务扩展字段66
     */
    public  void  setBusinessAttribute66(String businessAttribute66){
        this.businessAttribute66 = businessAttribute66;
    }
    /**
     * 获取业务扩展字段65 
     * @return 业务扩展字段65
     */
    public  String  getBusinessAttribute65(){
        return businessAttribute65;
    }
    /**
     * 设置业务扩展字段65
     * @param businessAttribute65 业务扩展字段65
     */
    public  void  setBusinessAttribute65(String businessAttribute65){
        this.businessAttribute65 = businessAttribute65;
    }
    /**
     * 获取业务扩展字段64 
     * @return 业务扩展字段64
     */
    public  String  getBusinessAttribute64(){
        return businessAttribute64;
    }
    /**
     * 设置业务扩展字段64
     * @param businessAttribute64 业务扩展字段64
     */
    public  void  setBusinessAttribute64(String businessAttribute64){
        this.businessAttribute64 = businessAttribute64;
    }
    /**
     * 获取业务扩展字段63 
     * @return 业务扩展字段63
     */
    public  String  getBusinessAttribute63(){
        return businessAttribute63;
    }
    /**
     * 设置业务扩展字段63
     * @param businessAttribute63 业务扩展字段63
     */
    public  void  setBusinessAttribute63(String businessAttribute63){
        this.businessAttribute63 = businessAttribute63;
    }
    /**
     * 获取业务扩展字段62 
     * @return 业务扩展字段62
     */
    public  String  getBusinessAttribute62(){
        return businessAttribute62;
    }
    /**
     * 设置业务扩展字段62
     * @param businessAttribute62 业务扩展字段62
     */
    public  void  setBusinessAttribute62(String businessAttribute62){
        this.businessAttribute62 = businessAttribute62;
    }
    /**
     * 获取业务扩展字段61 
     * @return 业务扩展字段61
     */
    public  String  getBusinessAttribute61(){
        return businessAttribute61;
    }
    /**
     * 设置业务扩展字段61
     * @param businessAttribute61 业务扩展字段61
     */
    public  void  setBusinessAttribute61(String businessAttribute61){
        this.businessAttribute61 = businessAttribute61;
    }
    /**
     * 获取业务扩展字段60 
     * @return 业务扩展字段60
     */
    public  String  getBusinessAttribute60(){
        return businessAttribute60;
    }
    /**
     * 设置业务扩展字段60
     * @param businessAttribute60 业务扩展字段60
     */
    public  void  setBusinessAttribute60(String businessAttribute60){
        this.businessAttribute60 = businessAttribute60;
    }
    /**
     * 获取业务扩展字段59 
     * @return 业务扩展字段59
     */
    public  String  getBusinessAttribute59(){
        return businessAttribute59;
    }
    /**
     * 设置业务扩展字段59
     * @param businessAttribute59 业务扩展字段59
     */
    public  void  setBusinessAttribute59(String businessAttribute59){
        this.businessAttribute59 = businessAttribute59;
    }
    /**
     * 获取业务扩展字段58 
     * @return 业务扩展字段58
     */
    public  String  getBusinessAttribute58(){
        return businessAttribute58;
    }
    /**
     * 设置业务扩展字段58
     * @param businessAttribute58 业务扩展字段58
     */
    public  void  setBusinessAttribute58(String businessAttribute58){
        this.businessAttribute58 = businessAttribute58;
    }
    /**
     * 获取业务扩展字段57 
     * @return 业务扩展字段57
     */
    public  String  getBusinessAttribute57(){
        return businessAttribute57;
    }
    /**
     * 设置业务扩展字段57
     * @param businessAttribute57 业务扩展字段57
     */
    public  void  setBusinessAttribute57(String businessAttribute57){
        this.businessAttribute57 = businessAttribute57;
    }
    /**
     * 获取业务扩展字段56 
     * @return 业务扩展字段56
     */
    public  String  getBusinessAttribute56(){
        return businessAttribute56;
    }
    /**
     * 设置业务扩展字段56
     * @param businessAttribute56 业务扩展字段56
     */
    public  void  setBusinessAttribute56(String businessAttribute56){
        this.businessAttribute56 = businessAttribute56;
    }
    /**
     * 获取业务扩展字段55 
     * @return 业务扩展字段55
     */
    public  String  getBusinessAttribute55(){
        return businessAttribute55;
    }
    /**
     * 设置业务扩展字段55
     * @param businessAttribute55 业务扩展字段55
     */
    public  void  setBusinessAttribute55(String businessAttribute55){
        this.businessAttribute55 = businessAttribute55;
    }
    /**
     * 获取业务扩展字段54 
     * @return 业务扩展字段54
     */
    public  String  getBusinessAttribute54(){
        return businessAttribute54;
    }
    /**
     * 设置业务扩展字段54
     * @param businessAttribute54 业务扩展字段54
     */
    public  void  setBusinessAttribute54(String businessAttribute54){
        this.businessAttribute54 = businessAttribute54;
    }
    /**
     * 获取业务扩展字段53 
     * @return 业务扩展字段53
     */
    public  String  getBusinessAttribute53(){
        return businessAttribute53;
    }
    /**
     * 设置业务扩展字段53
     * @param businessAttribute53 业务扩展字段53
     */
    public  void  setBusinessAttribute53(String businessAttribute53){
        this.businessAttribute53 = businessAttribute53;
    }
    /**
     * 获取业务扩展字段52 
     * @return 业务扩展字段52
     */
    public  String  getBusinessAttribute52(){
        return businessAttribute52;
    }
    /**
     * 设置业务扩展字段52
     * @param businessAttribute52 业务扩展字段52
     */
    public  void  setBusinessAttribute52(String businessAttribute52){
        this.businessAttribute52 = businessAttribute52;
    }
    /**
     * 获取业务扩展字段51 
     * @return 业务扩展字段51
     */
    public  String  getBusinessAttribute51(){
        return businessAttribute51;
    }
    /**
     * 设置业务扩展字段51
     * @param businessAttribute51 业务扩展字段51
     */
    public  void  setBusinessAttribute51(String businessAttribute51){
        this.businessAttribute51 = businessAttribute51;
    }
    /**
     * 获取业务扩展字段50 
     * @return 业务扩展字段50
     */
    public  String  getBusinessAttribute50(){
        return businessAttribute50;
    }
    /**
     * 设置业务扩展字段50
     * @param businessAttribute50 业务扩展字段50
     */
    public  void  setBusinessAttribute50(String businessAttribute50){
        this.businessAttribute50 = businessAttribute50;
    }
    /**
     * 获取业务扩展字段49 
     * @return 业务扩展字段49
     */
    public  String  getBusinessAttribute49(){
        return businessAttribute49;
    }
    /**
     * 设置业务扩展字段49
     * @param businessAttribute49 业务扩展字段49
     */
    public  void  setBusinessAttribute49(String businessAttribute49){
        this.businessAttribute49 = businessAttribute49;
    }
    /**
     * 获取业务扩展字段48 
     * @return 业务扩展字段48
     */
    public  String  getBusinessAttribute48(){
        return businessAttribute48;
    }
    /**
     * 设置业务扩展字段48
     * @param businessAttribute48 业务扩展字段48
     */
    public  void  setBusinessAttribute48(String businessAttribute48){
        this.businessAttribute48 = businessAttribute48;
    }
    /**
     * 获取业务扩展字段47 
     * @return 业务扩展字段47
     */
    public  String  getBusinessAttribute47(){
        return businessAttribute47;
    }
    /**
     * 设置业务扩展字段47
     * @param businessAttribute47 业务扩展字段47
     */
    public  void  setBusinessAttribute47(String businessAttribute47){
        this.businessAttribute47 = businessAttribute47;
    }
    /**
     * 获取业务扩展字段46 
     * @return 业务扩展字段46
     */
    public  String  getBusinessAttribute46(){
        return businessAttribute46;
    }
    /**
     * 设置业务扩展字段46
     * @param businessAttribute46 业务扩展字段46
     */
    public  void  setBusinessAttribute46(String businessAttribute46){
        this.businessAttribute46 = businessAttribute46;
    }
    /**
     * 获取业务扩展字段45 
     * @return 业务扩展字段45
     */
    public  String  getBusinessAttribute45(){
        return businessAttribute45;
    }
    /**
     * 设置业务扩展字段45
     * @param businessAttribute45 业务扩展字段45
     */
    public  void  setBusinessAttribute45(String businessAttribute45){
        this.businessAttribute45 = businessAttribute45;
    }
    /**
     * 获取业务扩展字段44 
     * @return 业务扩展字段44
     */
    public  String  getBusinessAttribute44(){
        return businessAttribute44;
    }
    /**
     * 设置业务扩展字段44
     * @param businessAttribute44 业务扩展字段44
     */
    public  void  setBusinessAttribute44(String businessAttribute44){
        this.businessAttribute44 = businessAttribute44;
    }
    /**
     * 获取业务扩展字段43 
     * @return 业务扩展字段43
     */
    public  String  getBusinessAttribute43(){
        return businessAttribute43;
    }
    /**
     * 设置业务扩展字段43
     * @param businessAttribute43 业务扩展字段43
     */
    public  void  setBusinessAttribute43(String businessAttribute43){
        this.businessAttribute43 = businessAttribute43;
    }
    /**
     * 获取业务扩展字段42 
     * @return 业务扩展字段42
     */
    public  String  getBusinessAttribute42(){
        return businessAttribute42;
    }
    /**
     * 设置业务扩展字段42
     * @param businessAttribute42 业务扩展字段42
     */
    public  void  setBusinessAttribute42(String businessAttribute42){
        this.businessAttribute42 = businessAttribute42;
    }
    /**
     * 获取业务扩展字段41 
     * @return 业务扩展字段41
     */
    public  String  getBusinessAttribute41(){
        return businessAttribute41;
    }
    /**
     * 设置业务扩展字段41
     * @param businessAttribute41 业务扩展字段41
     */
    public  void  setBusinessAttribute41(String businessAttribute41){
        this.businessAttribute41 = businessAttribute41;
    }
    /**
     * 获取业务扩展字段40 
     * @return 业务扩展字段40
     */
    public  String  getBusinessAttribute40(){
        return businessAttribute40;
    }
    /**
     * 设置业务扩展字段40
     * @param businessAttribute40 业务扩展字段40
     */
    public  void  setBusinessAttribute40(String businessAttribute40){
        this.businessAttribute40 = businessAttribute40;
    }
    /**
     * 获取业务扩展字段39 
     * @return 业务扩展字段39
     */
    public  String  getBusinessAttribute39(){
        return businessAttribute39;
    }
    /**
     * 设置业务扩展字段39
     * @param businessAttribute39 业务扩展字段39
     */
    public  void  setBusinessAttribute39(String businessAttribute39){
        this.businessAttribute39 = businessAttribute39;
    }
    /**
     * 获取业务扩展字段38 
     * @return 业务扩展字段38
     */
    public  String  getBusinessAttribute38(){
        return businessAttribute38;
    }
    /**
     * 设置业务扩展字段38
     * @param businessAttribute38 业务扩展字段38
     */
    public  void  setBusinessAttribute38(String businessAttribute38){
        this.businessAttribute38 = businessAttribute38;
    }
    /**
     * 获取业务扩展字段37 
     * @return 业务扩展字段37
     */
    public  String  getBusinessAttribute37(){
        return businessAttribute37;
    }
    /**
     * 设置业务扩展字段37
     * @param businessAttribute37 业务扩展字段37
     */
    public  void  setBusinessAttribute37(String businessAttribute37){
        this.businessAttribute37 = businessAttribute37;
    }
    /**
     * 获取业务扩展字段36 
     * @return 业务扩展字段36
     */
    public  String  getBusinessAttribute36(){
        return businessAttribute36;
    }
    /**
     * 设置业务扩展字段36
     * @param businessAttribute36 业务扩展字段36
     */
    public  void  setBusinessAttribute36(String businessAttribute36){
        this.businessAttribute36 = businessAttribute36;
    }
    /**
     * 获取业务扩展字段35 
     * @return 业务扩展字段35
     */
    public  String  getBusinessAttribute35(){
        return businessAttribute35;
    }
    /**
     * 设置业务扩展字段35
     * @param businessAttribute35 业务扩展字段35
     */
    public  void  setBusinessAttribute35(String businessAttribute35){
        this.businessAttribute35 = businessAttribute35;
    }
    /**
     * 获取业务扩展字段34 
     * @return 业务扩展字段34
     */
    public  String  getBusinessAttribute34(){
        return businessAttribute34;
    }
    /**
     * 设置业务扩展字段34
     * @param businessAttribute34 业务扩展字段34
     */
    public  void  setBusinessAttribute34(String businessAttribute34){
        this.businessAttribute34 = businessAttribute34;
    }
    /**
     * 获取业务扩展字段33 
     * @return 业务扩展字段33
     */
    public  String  getBusinessAttribute33(){
        return businessAttribute33;
    }
    /**
     * 设置业务扩展字段33
     * @param businessAttribute33 业务扩展字段33
     */
    public  void  setBusinessAttribute33(String businessAttribute33){
        this.businessAttribute33 = businessAttribute33;
    }
    /**
     * 获取业务扩展字段32 
     * @return 业务扩展字段32
     */
    public  String  getBusinessAttribute32(){
        return businessAttribute32;
    }
    /**
     * 设置业务扩展字段32
     * @param businessAttribute32 业务扩展字段32
     */
    public  void  setBusinessAttribute32(String businessAttribute32){
        this.businessAttribute32 = businessAttribute32;
    }
    /**
     * 获取业务扩展字段31 
     * @return 业务扩展字段31
     */
    public  String  getBusinessAttribute31(){
        return businessAttribute31;
    }
    /**
     * 设置业务扩展字段31
     * @param businessAttribute31 业务扩展字段31
     */
    public  void  setBusinessAttribute31(String businessAttribute31){
        this.businessAttribute31 = businessAttribute31;
    }
    /**
     * 获取业务扩展字段30 
     * @return 业务扩展字段30
     */
    public  String  getBusinessAttribute30(){
        return businessAttribute30;
    }
    /**
     * 设置业务扩展字段30
     * @param businessAttribute30 业务扩展字段30
     */
    public  void  setBusinessAttribute30(String businessAttribute30){
        this.businessAttribute30 = businessAttribute30;
    }
    /**
     * 获取业务扩展字段29 
     * @return 业务扩展字段29
     */
    public  String  getBusinessAttribute29(){
        return businessAttribute29;
    }
    /**
     * 设置业务扩展字段29
     * @param businessAttribute29 业务扩展字段29
     */
    public  void  setBusinessAttribute29(String businessAttribute29){
        this.businessAttribute29 = businessAttribute29;
    }
    /**
     * 获取业务扩展字段28 
     * @return 业务扩展字段28
     */
    public  String  getBusinessAttribute28(){
        return businessAttribute28;
    }
    /**
     * 设置业务扩展字段28
     * @param businessAttribute28 业务扩展字段28
     */
    public  void  setBusinessAttribute28(String businessAttribute28){
        this.businessAttribute28 = businessAttribute28;
    }
    /**
     * 获取业务扩展字段27 
     * @return 业务扩展字段27
     */
    public  String  getBusinessAttribute27(){
        return businessAttribute27;
    }
    /**
     * 设置业务扩展字段27
     * @param businessAttribute27 业务扩展字段27
     */
    public  void  setBusinessAttribute27(String businessAttribute27){
        this.businessAttribute27 = businessAttribute27;
    }
    /**
     * 获取业务扩展字段26 
     * @return 业务扩展字段26
     */
    public  String  getBusinessAttribute26(){
        return businessAttribute26;
    }
    /**
     * 设置业务扩展字段26
     * @param businessAttribute26 业务扩展字段26
     */
    public  void  setBusinessAttribute26(String businessAttribute26){
        this.businessAttribute26 = businessAttribute26;
    }
    /**
     * 获取业务扩展字段25 
     * @return 业务扩展字段25
     */
    public  String  getBusinessAttribute25(){
        return businessAttribute25;
    }
    /**
     * 设置业务扩展字段25
     * @param businessAttribute25 业务扩展字段25
     */
    public  void  setBusinessAttribute25(String businessAttribute25){
        this.businessAttribute25 = businessAttribute25;
    }
    /**
     * 获取业务扩展字段24 
     * @return 业务扩展字段24
     */
    public  String  getBusinessAttribute24(){
        return businessAttribute24;
    }
    /**
     * 设置业务扩展字段24
     * @param businessAttribute24 业务扩展字段24
     */
    public  void  setBusinessAttribute24(String businessAttribute24){
        this.businessAttribute24 = businessAttribute24;
    }
    /**
     * 获取业务扩展字段23 
     * @return 业务扩展字段23
     */
    public  String  getBusinessAttribute23(){
        return businessAttribute23;
    }
    /**
     * 设置业务扩展字段23
     * @param businessAttribute23 业务扩展字段23
     */
    public  void  setBusinessAttribute23(String businessAttribute23){
        this.businessAttribute23 = businessAttribute23;
    }
    /**
     * 获取业务扩展字段22 
     * @return 业务扩展字段22
     */
    public  String  getBusinessAttribute22(){
        return businessAttribute22;
    }
    /**
     * 设置业务扩展字段22
     * @param businessAttribute22 业务扩展字段22
     */
    public  void  setBusinessAttribute22(String businessAttribute22){
        this.businessAttribute22 = businessAttribute22;
    }
    /**
     * 获取业务扩展字段21 
     * @return 业务扩展字段21
     */
    public  String  getBusinessAttribute21(){
        return businessAttribute21;
    }
    /**
     * 设置业务扩展字段21
     * @param businessAttribute21 业务扩展字段21
     */
    public  void  setBusinessAttribute21(String businessAttribute21){
        this.businessAttribute21 = businessAttribute21;
    }
    /**
     * 获取业务扩展字段20 
     * @return 业务扩展字段20
     */
    public  String  getBusinessAttribute20(){
        return businessAttribute20;
    }
    /**
     * 设置业务扩展字段20
     * @param businessAttribute20 业务扩展字段20
     */
    public  void  setBusinessAttribute20(String businessAttribute20){
        this.businessAttribute20 = businessAttribute20;
    }
    /**
     * 获取业务扩展字段19 
     * @return 业务扩展字段19
     */
    public  String  getBusinessAttribute19(){
        return businessAttribute19;
    }
    /**
     * 设置业务扩展字段19
     * @param businessAttribute19 业务扩展字段19
     */
    public  void  setBusinessAttribute19(String businessAttribute19){
        this.businessAttribute19 = businessAttribute19;
    }
    /**
     * 获取业务扩展字段18 
     * @return 业务扩展字段18
     */
    public  String  getBusinessAttribute18(){
        return businessAttribute18;
    }
    /**
     * 设置业务扩展字段18
     * @param businessAttribute18 业务扩展字段18
     */
    public  void  setBusinessAttribute18(String businessAttribute18){
        this.businessAttribute18 = businessAttribute18;
    }
    /**
     * 获取业务扩展字段17 
     * @return 业务扩展字段17
     */
    public  String  getBusinessAttribute17(){
        return businessAttribute17;
    }
    /**
     * 设置业务扩展字段17
     * @param businessAttribute17 业务扩展字段17
     */
    public  void  setBusinessAttribute17(String businessAttribute17){
        this.businessAttribute17 = businessAttribute17;
    }
    /**
     * 获取业务扩展字段16 
     * @return 业务扩展字段16
     */
    public  String  getBusinessAttribute16(){
        return businessAttribute16;
    }
    /**
     * 设置业务扩展字段16
     * @param businessAttribute16 业务扩展字段16
     */
    public  void  setBusinessAttribute16(String businessAttribute16){
        this.businessAttribute16 = businessAttribute16;
    }
    /**
     * 获取业务扩展字段15 
     * @return 业务扩展字段15
     */
    public  String  getBusinessAttribute15(){
        return businessAttribute15;
    }
    /**
     * 设置业务扩展字段15
     * @param businessAttribute15 业务扩展字段15
     */
    public  void  setBusinessAttribute15(String businessAttribute15){
        this.businessAttribute15 = businessAttribute15;
    }
    /**
     * 获取业务扩展字段14 
     * @return 业务扩展字段14
     */
    public  String  getBusinessAttribute14(){
        return businessAttribute14;
    }
    /**
     * 设置业务扩展字段14
     * @param businessAttribute14 业务扩展字段14
     */
    public  void  setBusinessAttribute14(String businessAttribute14){
        this.businessAttribute14 = businessAttribute14;
    }
    /**
     * 获取业务扩展字段13 
     * @return 业务扩展字段13
     */
    public  String  getBusinessAttribute13(){
        return businessAttribute13;
    }
    /**
     * 设置业务扩展字段13
     * @param businessAttribute13 业务扩展字段13
     */
    public  void  setBusinessAttribute13(String businessAttribute13){
        this.businessAttribute13 = businessAttribute13;
    }
    /**
     * 获取业务扩展字段12 
     * @return 业务扩展字段12
     */
    public  String  getBusinessAttribute12(){
        return businessAttribute12;
    }
    /**
     * 设置业务扩展字段12
     * @param businessAttribute12 业务扩展字段12
     */
    public  void  setBusinessAttribute12(String businessAttribute12){
        this.businessAttribute12 = businessAttribute12;
    }
    /**
     * 获取业务扩展字段11 
     * @return 业务扩展字段11
     */
    public  String  getBusinessAttribute11(){
        return businessAttribute11;
    }
    /**
     * 设置业务扩展字段11
     * @param businessAttribute11 业务扩展字段11
     */
    public  void  setBusinessAttribute11(String businessAttribute11){
        this.businessAttribute11 = businessAttribute11;
    }
    /**
     * 获取业务扩展字段10 
     * @return 业务扩展字段10
     */
    public  String  getBusinessAttribute10(){
        return businessAttribute10;
    }
    /**
     * 设置业务扩展字段10
     * @param businessAttribute10 业务扩展字段10
     */
    public  void  setBusinessAttribute10(String businessAttribute10){
        this.businessAttribute10 = businessAttribute10;
    }
    /**
     * 获取业务扩展字段9 
     * @return 业务扩展字段9
     */
    public  String  getBusinessAttribute9(){
        return businessAttribute9;
    }
    /**
     * 设置业务扩展字段9
     * @param businessAttribute9 业务扩展字段9
     */
    public  void  setBusinessAttribute9(String businessAttribute9){
        this.businessAttribute9 = businessAttribute9;
    }
    /**
     * 获取业务扩展字段8 
     * @return 业务扩展字段8
     */
    public  String  getBusinessAttribute8(){
        return businessAttribute8;
    }
    /**
     * 设置业务扩展字段8
     * @param businessAttribute8 业务扩展字段8
     */
    public  void  setBusinessAttribute8(String businessAttribute8){
        this.businessAttribute8 = businessAttribute8;
    }
    /**
     * 获取业务扩展字段7 
     * @return 业务扩展字段7
     */
    public  String  getBusinessAttribute7(){
        return businessAttribute7;
    }
    /**
     * 设置业务扩展字段7
     * @param businessAttribute7 业务扩展字段7
     */
    public  void  setBusinessAttribute7(String businessAttribute7){
        this.businessAttribute7 = businessAttribute7;
    }
    /**
     * 获取业务扩展字段6 
     * @return 业务扩展字段6
     */
    public  String  getBusinessAttribute6(){
        return businessAttribute6;
    }
    /**
     * 设置业务扩展字段6
     * @param businessAttribute6 业务扩展字段6
     */
    public  void  setBusinessAttribute6(String businessAttribute6){
        this.businessAttribute6 = businessAttribute6;
    }
    /**
     * 获取业务扩展字段5 
     * @return 业务扩展字段5
     */
    public  String  getBusinessAttribute5(){
        return businessAttribute5;
    }
    /**
     * 设置业务扩展字段5
     * @param businessAttribute5 业务扩展字段5
     */
    public  void  setBusinessAttribute5(String businessAttribute5){
        this.businessAttribute5 = businessAttribute5;
    }
    /**
     * 获取业务扩展字段4 
     * @return 业务扩展字段4
     */
    public  String  getBusinessAttribute4(){
        return businessAttribute4;
    }
    /**
     * 设置业务扩展字段4
     * @param businessAttribute4 业务扩展字段4
     */
    public  void  setBusinessAttribute4(String businessAttribute4){
        this.businessAttribute4 = businessAttribute4;
    }
    /**
     * 获取业务扩展字段3 
     * @return 业务扩展字段3
     */
    public  String  getBusinessAttribute3(){
        return businessAttribute3;
    }
    /**
     * 设置业务扩展字段3
     * @param businessAttribute3 业务扩展字段3
     */
    public  void  setBusinessAttribute3(String businessAttribute3){
        this.businessAttribute3 = businessAttribute3;
    }
    /**
     * 获取业务扩展字段1 
     * @return 业务扩展字段1
     */
    public  String  getBusinessAttribute1(){
        return businessAttribute1;
    }
    /**
     * 设置业务扩展字段1
     * @param businessAttribute1 业务扩展字段1
     */
    public  void  setBusinessAttribute1(String businessAttribute1){
        this.businessAttribute1 = businessAttribute1;
    }
    /**
     * 获取业务扩展字段2 
     * @return 业务扩展字段2
     */
    public  String  getBusinessAttribute2(){
        return businessAttribute2;
    }
    /**
     * 设置业务扩展字段2
     * @param businessAttribute2 业务扩展字段2
     */
    public  void  setBusinessAttribute2(String businessAttribute2){
        this.businessAttribute2 = businessAttribute2;
    }
    //结束vo的setter和getter方法    
}
