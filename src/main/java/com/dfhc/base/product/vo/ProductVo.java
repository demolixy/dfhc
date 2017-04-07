/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  ProductVo.java
 *
 * 功能描述：  产品管理值对象
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.base.product.vo;


import java.math.BigDecimal;
import java.sql.Timestamp;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 产品管理值对象
 * 
 * @author 龙色波
 * @see 参见的类
 */
public class ProductVo extends RmValueObject{

    private static final long serialVersionUID = 1;

    //开始vo的属性
    /**
     * id 表示：ID
     */
    private  String  id;
    /**
     * productTypeId 表示：产品类别ID
     */
    private  String  productTypeId;
    /**
     * productName 表示：产品名称
     */
    private  String  productName;
    /**
     * indexCode   表示：拼音码
     */
    private  String  indexCode;
    /**
     * spellBrevityCode  表示：拼音简码
     */
    private  String spellBrevityCode;
    /**
     * storeMode 表示：储存方式
     */
    private  String  storeMode;
    /**
     * isCtrlBusNum 表示：是否控制车数量
     */
    private  String  isCtrlBusNum;
    /**
     * maxBusNumInFactory 表示：厂内最大车数
     */
    private  Long  maxBusNumInFactory;
    /**
     * maxBusNumInPound 表示：过磅最大车数
     */
    private  Long  maxBusNumInPound;
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
     * mainCode  表示: 主库代码
     */
    private String mainCode;
    /**
     *  main_secondary_product_flag  表示: 主副产品标志
     */
    private String main_secondary_product_flag;
    /**
     * bag_weight  表示: 袋装标重
     */
    private BigDecimal bag_weight;
    /**
     * productTypeName 表示产品类别名称
     */
    private String productTypeName;
    //结束vo的属性
        
    /**
     * 产品价格    
     */
    private BigDecimal price;

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
     * 获取产品类别ID 
     * @return 产品类别ID
     */
    public  String  getProductTypeId(){
        return productTypeId;
    }
    /**
     * 设置产品类别ID
     * @param productTypeId 产品类别ID
     */
    public  void  setProductTypeId(String productTypeId){
        this.productTypeId = productTypeId;
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
     * 获取储存方式 
     * @return 储存方式
     */
    public  String  getStoreMode(){
        return storeMode;
    }
    /**
     * 设置储存方式
     * @param storeMode 储存方式
     */
    public  void  setStoreMode(String storeMode){
        this.storeMode = storeMode;
    }
    /**
     * 获取是否控制车数量 
     * @return 是否控制车数量
     */
    public  String  getIsCtrlBusNum(){
        return isCtrlBusNum;
    }
    /**
     * 设置是否控制车数量
     * @param isCtrlBusNum 是否控制车数量
     */
    public  void  setIsCtrlBusNum(String isCtrlBusNum){
        this.isCtrlBusNum = isCtrlBusNum;
    }
    /**
     * 获取厂内最大车数 
     * @return 厂内最大车数
     */
    public  Long  getMaxBusNumInFactory(){
        return maxBusNumInFactory;
    }
    /**
     * 设置厂内最大车数
     * @param maxBusNumInFactory 厂内最大车数
     */
    public  void  setMaxBusNumInFactory(Long maxBusNumInFactory){
        this.maxBusNumInFactory = maxBusNumInFactory;
    }
    /**
     * 获取过磅最大车数 
     * @return 过磅最大车数
     */
    public  Long  getMaxBusNumInPound(){
        return maxBusNumInPound;
    }
    /**
     * 设置过磅最大车数
     * @param maxBusNumInPound 过磅最大车数
     */
    public  void  setMaxBusNumInPound(Long maxBusNumInPound){
        this.maxBusNumInPound = maxBusNumInPound;
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
     * 获取主库代码 
     * @return 主库代码 
     */
    public String getMainCode() {
  		return mainCode;
  	}
      
      /**
       * 设置主库代码 
       * @param mainCode 主库代码 
       */
     
  	public void setMainCode(String mainCode) {
  		this.mainCode = mainCode;
  	}
  	  /**
  	   * 获取拼音码
  	   * @return indexCode 拼音码
  	   */
	public String getIndexCode() {
		return indexCode;
	}
	/**
	 * 设置拼音码
	 * @param indexCode 拼音码
	 */
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	/**
	 * 获取拼音简码
	 * @return spellBrevityCode 拼音简码
	 */
	  public String getSpellBrevityCode() {
		return spellBrevityCode;
	}
	  /**
	   * 设置拼音简码
	   * @param spellBrevityCode 拼音简码 
	   */
	public void setSpellBrevityCode(String spellBrevityCode) {
		this.spellBrevityCode = spellBrevityCode;
	}

	/**
	   * 获取主副产品标志
	   * @return
	   */
	public String getMain_secondary_product_flag() {
		return main_secondary_product_flag;
	}
	  /**
	   * 设置主副产品标志
	   * @param main_secondary_product_flag 主副产品标志
	   */
	public void setMain_secondary_product_flag(String main_secondary_product_flag) {
		this.main_secondary_product_flag = main_secondary_product_flag;
	}
	/**
	 * 获取袋装标重
	 * @return bag_weight
	 */
	public BigDecimal getBag_weight() {
		return bag_weight;
	}
	/**
	 * 设置袋装标重
	 * @param bag_weight 袋装标重
	 */
	public void setBag_weight(BigDecimal bag_weight) {
		this.bag_weight = bag_weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取 产品类别名称
	 * @return 产品类别名称
	 */
	public String getProductTypeName() {
		return productTypeName;
	}
	/**
	 * 设置 产品类别名称
	 * @param productTypeName 产品类别名称
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
  	
  	
    //结束vo的setter和getter方法    
}
