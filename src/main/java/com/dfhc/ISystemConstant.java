/**
 * 软件著作权：
 *
 * 系统名称：  企银通业务管理平台
 *
 * 文件名称：  ISystemConstant
 *
 * 功能描述：  系统常量类
 * 
 * 版本历史：
 * 
 * 2015-07-07   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc;

public interface ISystemConstant {

	/**
	 * 字典类型:激活、锁定状态
	 */
	public static final String DICTIONARY_RM_LOCK_STATUS = "RM_LOCK_STATUS";
	/**
	 * 锁定
	 */
	public static final String DICTIONARY_RM_LOCK_STATUS_0 = "0";
	/**
	 * 激活
	 */
	public static final String DICTIONARY_RM_LOCK_STATUS_1 = "1";
	

	public static final String DEFAULT_PAGE_SIZE = "15";// 默认页大小
	// AJAX处理结果
	// 处理结果标志
	/**
	 * 成功
	 */
	public static final String AJAX_RESULT_SUCCESS = "1";
	/**
	 * 失败
	 */
	public static final String AJAX_RESULT_FAIL = "0";
	/**
	 * 处理消息
	 */
	public static final String AJAX_MESSAGE = "message";
	/**
	 * 结果状态key
	 */
	public static final String AJAX_STATUS = "status";
	/**
	 * 结果列表key
	 */
	public static final String AJAX_LIST = "list";
	/**
	 * 结果bean key
	 */
	public static final String AJAX_BEAN = "bean";
	// web显示图片格式列表
	/**
	 * GIF
	 */
	public static final String GIF = "image/gif;charset=GB2312";
	/**
	 * JPG
	 */
	public static final String JPG = "image/jpeg;charset=GB2312";

	/**
	 * PNG
	 */
	public static final String PNG = "image/x-png;charset=GB2312";
	/**
	 * BMP
	 */
	public static final String BMP = "image/bmp;charset=GB2312";
	/**
	 * PDF
	 */
	public static final String PDF = "application/pdf";
	/**
	 * PDF
	 */
	public static final String DICTIONARY_PDF = "pdf";
	// 序列值周期更新类型
	/**
	 * 无周期,一直递增
	 */
	public static final String CYCLE_TYPE_NO = "0";
	/**
	 * 以年为周期
	 */
	public static final String CYCLE_TYPE_YEAR = "1";
	/**
	 * 以月为周期
	 */
	public static final String CYCLE_TYPE_MONTH = "2";
	/**
	 * 以日为周期
	 */
	public static final String CYCLE_TYPE_DAY = "3";
	/**
	 * 缺省短日期格式
	 */
	public static final String DEFAULT_SHORT_DATE_FMT = "yyyyMMdd";
	/**
	 * 缺省长日期格式
	 */
	public static final String DEFAULT_LONG_DATE_FMT = "yyyy-MM-dd";
	/**
	 * MD5加密附加串
	 */
	public static final String DIGEST_MD5 = "";
	/**
	 * 显示图片的相对路径参数
	 */
	public static final String RELA_PATH = "relaPath";
	/**
	 * 字典类型:路径
	 */
	public static final String DICTIONARY_PATH = "PATH";
	/**
	 * 根目录
	 */
	public static final String DICTIONARY_ROOT_PATH = "ROOT_PATH";

	/**
	 * 汽运配送计划图片  放在根目录后边
	 */
	public static final String DICTIONARY_ROOT_PATH_01 = "01";
	/**
	 * 工作流附件上传临时目录
	 */
	public static final String DICTIONARY_ROOT_PATH_02 = "02";
	/**
	 * 工作流附件上传正式目录
	 */
	public static final String DICTIONARY_ROOT_PATH_03 = "03";
	
	/**
	 * 模板
	 */
	public static final String TEMPLET = "TEMPLET";
	/**
	 * 验证码验证
	 */
	public static final String TEMPLET_MESSAGE_YS = "TEMPLET_MESSAGE_YS";
	/**
	 * 字典类型:用户类型
	 */
	public static final String DICTIONARY_USER_TYPE = "USER_TYPE";
	/**
	 * 客户
	 */
	public static final String DICTIONARY_USER_TYPE_0 = "0";
	/**
	 * 叉车班长
	 */
	public static final String DICTIONARY_USER_TYPE_1 = "1";
	/**
	 * 保运主管
	 */
	public static final String DICTIONARY_USER_TYPE_2 = "2";
	/**
	 * 安检员
	 */
	public static final String DICTIONARY_USER_TYPE_3 = "3";
	/**
	 * 物流公司
	 */
	public static final String DICTIONARY_USER_TYPE_4 = "4";
	/**
	 * 司磅员
	 */
	public static final String DICTIONARY_USER_TYPE_5 = "5";
	/**
	 * RM_PARTY_RELATION下级编码规则（发号器)
	 */
	public static final String RM_PARTY_RELATION_SEQUENCE_RULE = "%value";
	/**
	 * 产生RM_PARTY_RELATION下级编码备注
	 */
	public static final String RM_PARTY_RELATION_SEQUENCE_REMARK = "产生RM_PARTY_RELATION下级编码";
	
	/**
	 * 字典类型:消息类型
	 */
	public static final String DICTIONARY_MESSAGE_TYPE = "MESSAGE_TYPE";
	/**
	 * 系统公告
	 */
	public static final String DICTIONARY_MESSAGE_TYPE_01 = "01";
	
	/**
	 * 字典类型:消息状态
	 */
	public static final String DICTIONARY_MESSAGE_STATUS = "MESSAGE_STATUS";
	/**
	 * 已读
	 */
	public static final String DICTIONARY_MESSAGE_STATUS_01 = "01";
	/**
	 * 未读
	 */
	public static final String DICTIONARY_MESSAGE_STATUS_02 = "02";
	
	/**
	 * 字典类型:否、是
	 */
	public static final String DICTIONARY_RM_YES_NOT = "RM_YES_NOT";
	/**
	 * 否
	 */
	public static final String DICTIONARY_RM_YES_NOT_0 = "0";
	/**
	 * 是
	 */
	public static final String DICTIONARY_RM_YES_NOT_1 = "1";
	/**
	 * 字典类型:销售订单状态
	 */
	public static final String DICTIONARY_ORDER_STATUS = "ORDER_STATUS";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_ORDER_STATUS_00 = "00";
	/**
	 * 未审核
	 */
	public static final String DICTIONARY_ORDER_STATUS_01 = "01";
	/**
	 * 已审核
	 */
	public static final String DICTIONARY_ORDER_STATUS_02 = "02";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_ORDER_STATUS_03 = "03";
	/**
	 * 已过期
	 */
	public static final String DICTIONARY_ORDER_STATUS_04 = "04";
	/**
	 * 已驳回
	 */
	public static final String DICTIONARY_ORDER_STATUS_05 = "05";
	// 字符集编码
	/**
	 * UTF-8字符集
	 */
	public static final String ENCODE_UTF_8 = "UTF-8";

	/**
	 * 模板带替换前缀
	 */
	public static final String PENDING_REPLACE_PREFIX = "DFHCYJHOUPREFIX";

	/**
	 * 模板带替换后缀
	 */
	public static final String PENDING_REPLACE_SUFFIX = "DFHCYJHOUSUFFIX";
	/**
	 * el表达式前缀
	 */
	public static final String EL_PREFIX = "${";
	/**
	 * el表达式后缀
	 */
	public static final String EL_SUFFIX = "}";
	/**
	 * 文件路径分割符合
	 */
	public static final String DICTIONARY_SYSTEM_SEPARATOR = "/";
	//工作流预定义参数
	/**
	 * 预定义后台获取网站路径
	 */
	public static final String PREDEFINED_BACK_CONTEXT_PATH = "_ctx_";
	/**
	 * processDefinitionId 表示：预定义流程定义id
	 */
	public static final String PREDEFINED_PROCESS_DEFINITION_ID = "processDefinitionId";
	/**
	 * 预定义任务id
	 */
	public static final String PREDEFINED_TASK_ID = "taskId";
	/**
	 * 预定义错误信息
	 */
	public static final String PREDEFINED_ERROR_MSG = "errMsg";
    /**
     * 业务类型
     */
    public static final String PREDEFINED_BUSINESS_TYPE = "businessType";
    /**
     * 业务组
     */
    public static final String PREDEFINED_BUSINESS_GROUP = "businessGroup";
    /**
     * 指定id查bean(用在修改上)
     */
    public static final String PREDEFINED_ZHIDING_ID = "zdId";
    /**
     * parentId 表示：上级id
     */
    public static final String PREDEFINED_PARENT_ID = "parentId";
    /**
     * taskDefinitionKey 表示：任务定义key
     */
    public static final String PREDEFINED_TASK_DEFINITIONKEY = "taskDefinitionKey";
    /**
     * nextFreemarkId 表示：下步要跳转的模板id
     */
    public static final String PREDEFINED_NEXT_FREEMARK_ID = "nextFreemarkId";
    /**
     * randomNum 表示：临时目录随机数
     */
    public static final String PREDEFINED_TEMP_PATH_RANDOM_NUM = "randomNum";
    /**
     * processTempPath 表示：临时目录标示
     */
    public static final String PREDEFINED_TEMP_PATH = "processTempPath";
    /**
     * processOfficialPath 表示：正式目录标示
     */
    public static final String PREDEFINED_OFFICIAL_PATH = "processOfficialPath";
    /**
     * isPass 表示：审核状态标识
     */
    public static final String PREDEFINED_IS_PASS = "isPass";
    /**
     * 审核通过的中文显示:审核通过
     */
    public static final String PREDEFINED_IS_PASS_YES_CHINESE = "审核通过";
    /**
     * 审核不通过的中文显示：审核不通过
     */
    public static final String PREDEFINED_IS_PASS_NOT_CHINESE = "审核不通过";
    /**
     * 审核意见标识
     */
    public static final String PREDEFINED_AUDIT_OPINION = "auditOpinion";
    /**
     * 页面要带的id
     */
    public static final String PREDEFINED_BEAN_ID = "id";
	/**
	 * 前台可能用到
	 */
	public static final String BUSINESS_KEY = "_businessKey";

	/**
	 * 用户权限类型
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE = "RM_ADMIN_TYPE";
	/**
	 * 安检app用户
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE_0 = "0";
	/**
	 * 叉车app用户
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE_1 = "1";
	/**
	 * 监装app用户
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE_2 = "2";
	/**
	 * 普通用户
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE_3 = "3";
	/**
	 * 物流前台用户
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE_4 = "4";
	/**
	 * 客户前台用户
	 */
	public static final String DICTIONARY_RM_ADMIN_TYPE_5 = "5";
	
	/**
	 * 字典类型:数据集类型
	 */
	public static final String DICTIONARY_DATA_SET_TYPE = "DATA_SET_TYPE";
	/**
	 * 01=SQL,
	 */
	public static final String DICTIONARY_DATA_SET_TYPE_01 = "01";
	/**
	 * 02=REST API
	 */
	public static final String DICTIONARY_DATA_SET_TYPE_02 = "02";
	/**
	 * 字典类型:代理状态
	 */
	public static final String DICTIONARY_RM_AGENT_STATUS = "RM_AGENT_STATUS";
	/**
	 * 未代理
	 */
	public static final String DICTIONARY_RM_AGENT_STATUS_0 = "0";
	/**
	 * 已代理
	 */
	public static final String DICTIONARY_RM_AGENT_STATUS_1 = "1";
	// 工作流常量
	/**
	 * 会签节点描述
	 */
	public static final String JOINT_PROCESS = "jointProcess";
	/**
	 * 父节点code
	 */
	public static final String RM_PARENT_CODE = "pcode";
	/**
	 * 子节点来源
	 */
	public static final String RM_CHILD_SOURCE = "childSource";

	/**
	 * 业务组
	 */
	public static final String DICTIONARY_BUSINESS_GROUP = "BUSINESS_GROUP";
	/**
	 * 请假
	 */
	public static final String DICTIONARY_BUSINESS_GROUP_01 = "01";
	/**
	 * 出差
	 */
	public static final String DICTIONARY_BUSINESS_GROUP_02 = "02";
	/**
	 * 流程跳转路径
	 */
	public static final String DICTIONARY_PROCESS_FORWARD_URL = "PROCESS_FORWARD_URL";
	/**
     * 保存通用流程业务表数据
     */
	public static final String DICTIONARY_PROCESS_FORWARD_URL_01 = "01";
	/**
	 * 业务类型
	 */
	public static final String DICTIONARY_BUSINESS_TYPE = "BUSINESS_TYPE";
	/**
	 * 测试类型价格
	 */
	public static final String DICTIONARY_BUSINESS_TYPE_01 = "01";
	/**
	 * 获取rm.xml获取根目录
	 */
	public static final String WEBAPPNAME_ROOT_HOME = "webAppName.root.home";
	/**
	 * 日志相对目录
	 */
	public static final String LOG_RELA_PATH = "logs";
	/**
	 * 回车换行
	 */
	public static final String CRLF = "\r\n";

	/**
	 * 序列服务bean name (rm.project.applicationContext.xml中配置)
	 */
	public static final String SEQUENCE_SERVICE_BEAN = "SequenceService";
	/**
	 * 字典类型:单据类型
	 */
	public static final String DICTIONARY_BILL_TYPE = "BILL_TYPE";
	/**
	 * 销售订单
	 */
	public static final String DICTIONARY_BILL_TYPE_01 = "01";
	/**
	 * 汽运配送计划
	 */
	public static final String DICTIONARY_BILL_TYPE_02 = "02";
	/**
	 * 汽运调拨计划
	 */
	public static final String DICTIONARY_BILL_TYPE_03 = "03";
	/**
	 * 铁运调拨&配送计划
	 */
	public static final String DICTIONARY_BILL_TYPE_04 = "04";
	/**
	 * 序号
	 */
	public static final String DICTIONARY_SEQUENCE_NUMBER = "sequenceNumber";
	/**
	 * 省份
	 */
	public static final String DICTIONARY_PROVINCE = "province";
	/**
	 * 区域
	 */
	public static final String DICTIONARY_REGION = "region";
	/**
	 * 收货仓库/地址
	 */
	public static final String DICTIONARY_RECEIVING_ADDRESS = "receivingAddress";
	/**
	 * 司机身份证号
	 */
	public static final String DICTIONARY_DRIVER_IDCARD_NUMBER = "driverIdCardNumber";
	/**
	 * 运输公司
	 */
	public static final String DICTIONARY_LOGISTICSCOMPANY = "logisticsCompany";
	/**
	 * 字典类型:排号状态
	 */
	public static final String DICTIONARY_ROW_STATUS="ROW_STATUS";
	/**
	 * 待办
	 */
	public static final String DICTIONARY_ROW_STATUS_00="00";
	/**
	 * 待审核
	 */
	public static final String DICTIONARY_ROW_STATUS_01="01";
	/**
	 * 等候入厂
	 */
	public static final String DICTIONARY_ROW_STATUS_02="02";
	/**
	 * 准备入厂
	 */
	public static final String DICTIONARY_ROW_STATUS_07="07";
	/**
	 * 当前入厂
	 */
	public static final String DICTIONARY_ROW_STATUS_03="03";
	/**
	 * 作废
	 */
	public static final String DICTIONARY_ROW_STATUS_04="04";
	/**
	 * 已入厂
	 */
	public static final String DICTIONARY_ROW_STATUS_05="05";
	/**
	 * 已出厂
	 */
	public static final String DICTIONARY_ROW_STATUS_06="06";

	/**
	 * 字典类型:装车通知单状态
	 */
	public static final String DICTIONARY_LOADING_NOTICE_STATUS = "LOADING_NOTICE_STATUS";
	/**
	 * 待抢
	 */
	public static final String DICTIONARY_LOADING_NOTICE_STATUS_01 = "01";
	/**
	 * 已占用
	 */
	public static final String DICTIONARY_LOADING_NOTICE_STATUS_02 = "02";
	/**
	 * 装车完成待确认
	 */
	public static final String DICTIONARY_LOADING_NOTICE_STATUS_03 = "03";
	/**
	 * 已确认
	 */
	public static final String DICTIONARY_LOADING_NOTICE_STATUS_04 = "04";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_LOADING_NOTICE_STATUS_05 = "05";
	/**
	 * 字典类型：主副产品标志
	 */
	public static final String DICTIONARY_MAIN_SECONDARY_PRODUCT_FLAG="MAIN_SECONDARY_PRODUCT_FLAG";
	/**
	 * 主产品
	 */
	public static final String DICTIONARY_MAIN_SECONDARY_PRODUCT_FLAG_01="01";
	/**
	 * 副产品
	 */
	public static final String DICTIONARY_MAIN_SECONDARY_PRODUCT_FLAG_02="02";
	/**
	 * 字典类型:目标类型
	 */
	public static final String DICTIONARY_TARGET_TYPE = "TARGET_TYPE";
	/**
	 * 客户收货地址
	 */
	public static final String DICTIONARY_TARGET_TYPE_00 = "00";
	/**
	 * 调拨仓库
	 */
	public static final String DICTIONARY_TARGET_TYPE_01 = "01";
	/**
	 * 火车到站
	 */
	public static final String DICTIONARY_TARGET_TYPE_02 = "02";

	/**
	 * 字典类型:结算类型
	 */
	public static final String DICTIONARY_SETTLEMENT_TYPE = "SETTLEMENT_TYPE";
	/**
	 * 汽配
	 */
	public static final String DICTIONARY_SETTLEMENT_TYPE_01 = "01";
	/**
	 * 汽调
	 */
	public static final String DICTIONARY_SETTLEMENT_TYPE_02 = "02";
	/**
	 * 铁调
	 */
	public static final String DICTIONARY_SETTLEMENT_TYPE_03 = "03";

	/**
	 * 字典类型:物流费用结算审批单状态
	 */
	public static final String DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS = "LOGISTICS_COST_SETTLEMENT_STATUS";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS_00 = "00";
	/**
	 * 已提交
	 */
	public static final String DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS_01 = "01";

	/**
	 * 字典类型:铁运调拨计划状态
	 */
	public static final String DICTIONARY_TRAINS_REQUISITION_STATUS = "TRAINS_REQUISITION_STATUS";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_TRAINS_REQUISITION_STATUS_00 = "00";
	/**
	 * 已提交
	 */
	public static final String DICTIONARY_TRAINS_REQUISITION_STATUS_01 = "01";
	/**
	 * 分配车辆中
	 */
	public static final String DICTIONARY_TRAINS_REQUISITION_STATUS_02 = "02";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_TRAINS_REQUISITION_STATUS_03 = "03";
	/**
	 * 已完成
	 */
	public static final String DICTIONARY_TRAINS_REQUISITION_STATUS_04 = "04";

	/**
	 * 字典类型:汽运调拨计划状态
	 */
	public static final String DICTIONARY_CAR_REQUISITION_STATUS = "CAR_REQUISITION_STATUS";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_CAR_REQUISITION_STATUS_00 = "00";
	/**
	 * 已提交
	 */
	public static final String DICTIONARY_CAR_REQUISITION_STATUS_01 = "01";
	/**
	 * 分配车辆中
	 */
	public static final String DICTIONARY_CAR_REQUISITION_STATUS_02 = "02";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_CAR_REQUISITION_STATUS_03 = "03";
	/**
	 * 已完成
	 */
	public static final String DICTIONARY_CAR_REQUISITION_STATUS_04 = "04";

	/**
	 * 字典类型:配送计划状态
	 */
	public static final String DICTIONARY_PLAN_STATUS = "PLAN_STATUS";
	/**
	 * 已导入
	 */
	public static final String DICTIONARY_PLAN_STATUS_00 = "00";
	/**
	 * 已分配物流公司
	 */
	public static final String DICTIONARY_PLAN_STATUS_01 = "01";
	/**
	 * 待分配车辆
	 */
	public static final String DICTIONARY_PLAN_STATUS_02 = "02";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_PLAN_STATUS_03 = "03";
	/**
	 * 已完成
	 */
	public static final String DICTIONARY_PLAN_STATUS_04 = "04";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_PLAN_STATUS_11 = "11";
	/**
	 * 已提交
	 */
	public static final String DICTIONARY_PLAN_STATUS_12 = "12";

	/**
	 * 字典类型:检查状态
	 */
	public static final String DICTIONARY_CHECK_STATUS = "CHECK_STATUS";
	/**
	 * 待检查
	 */
	public static final String DICTIONARY_CHECK_STATUS_2 = "2";
	/**
	 * 通过
	 */
	public static final String DICTIONARY_CHECK_STATUS_1 = "1";
	/**
	 * 不通过
	 */
	public static final String DICTIONARY_CHECK_STATUS_0 = "0";

	/**
	 * 字典类型:排号类型
	 */
	public static final String DICTIONARY_ROW_TYPE="ROW_TYPE";
	/**
	 * 优先
	 */
	public static final String DICTIONARY_ROW_TYPE_01="01";
	/**
	 * 普通
	 */
	public static final String DICTIONARY_ROW_TYPE_02="02";
	/**
	 * 铁运
	 */
	public static final String DICTIONARY_ROW_TYPE_03="03";

	/**
	 * 字典类型:账户变动业务类型
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE="ACC_CHANGE_BUSTYPE";
	/**
	 * 销售订单
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE_01="01";
	/**
	 * 过磅确认
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE_02="02";
	/**
	 * 装车确认
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE_03="03";
	/**
	 * 收款
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE_04="04";
	/**
	 * 发票审核
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE_05="05";
	/**
	 * 系统后台调整
	 */
	public static final String DICTIONARY_ACC_CHANGE_BUSTYPE_06="06";

	/**
	 * 字典类型:金额方向
	 */
	public static final String DICTIONARY_AMOUNT_DIRECTION = "AMOUNT_DIRECTION";
	/**
	 * 收入
	 */
	public static final String DICTIONARY_AMOUNT_DIRECTION_01 = "01";
	/**
	 * 支出
	 */
	public static final String DICTIONARY_AMOUNT_DIRECTION_02 = "02";
	/**
	 * 冻结
	 */
	public static final String DICTIONARY_AMOUNT_DIRECTION_03 = "03";
	/**
	 * 解冻
	 */
	public static final String DICTIONARY_AMOUNT_DIRECTION_04 = "04";

	/**
	 * 字典类型:收货确认函状态
	 */
	public static final String DICTIONARY_CONFIRMATION_LETTER_STATUS = "CONFIRMATION_LETTER_STATUS";
	/**
	 * 待结算
	 */
	public static final String DICTIONARY_CONFIRMATION_LETTER_STATUS_00 = "00";
	/**
	 * 已结算
	 */
	public static final String DICTIONARY_CONFIRMATION_LETTER_STATUS_01 = "01";
	/**
	 * 已开发票
	 */
	public static final String DICTIONARY_CONFIRMATION_LETTER_STATUS_02 = "02";
	/**
	 * 待提交
	 */
	public static final String DICTIONARY_CONFIRMATION_LETTER_STATUS_03 = "03";

	/**
	 * 字典类型:过磅单状态
	 */
	public static final String DICTIONARY_WEIGHING_BILL_STATUS = "WEIGHING_BILL_STATUS";
	/**
	 * 第一次过磅
	 */
	public static final String DICTIONARY_WEIGHING_BILL_STATUS_01 = "01";
	/**
	 * 第二次过磅
	 */
	public static final String DICTIONARY_WEIGHING_BILL_STATUS_02 = "02";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_WEIGHING_BILL_STATUS_03 = "03";

	/**
	 * 字典类型:提货单状态
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS = "LADING_BILL_STATUS";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_00 = "00";
	/**
	 * 已提交待审核
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_01 = "01";
	/**
	 * 审核通过
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_02 = "02";
	/**
	 * 待进厂
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_03 = "03";
	/**
	 * 车辆安检
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_04 = "04";
	/**
	 * 装车待分配
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_05 = "05";
	/**
	 * 装货完成
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_06 = "06";
	/**
	 * 装货失败
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_07 = "07";
	/**
	 * 装车完成出厂
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_08 = "08";
	/**
	 * 作废
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_09 = "09";
	/**
	 * 装车已分配
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_10 = "10";
	/**
	 * 装车失败出厂
	 */
	public static final String DICTIONARY_LADING_BILL_STATUS_11 = "11";

	/**
	 * 字典类型:客户状态
	 */
	public static final String DICTIONARY_CUST_STATUS = "CUST_STATUS";
	/**
	 * 草稿
	 */
	public static final String DICTIONARY_CUST_STATUS_0 = "0";
	/**
	 * 正式
	 */
	public static final String DICTIONARY_CUST_STATUS_1 = "1";
	/**
	 * 停用
	 */
	public static final String DICTIONARY_CUST_STATUS_2 = "2";

	/**
	 * 字典类型:价格状态
	 */
	public static final String DICTIONARY_PRICE_STATUS = "PRICE_STATUS";
	/**
	 * 未提交
	 */
	public static final String DICTIONARY_PRICE_STATUS_1 = "1";
	/**
	 * 待审核
	 */
	public static final String DICTIONARY_PRICE_STATUS_2 = "2";
	/**
	 * 已审核通过
	 */
	public static final String DICTIONARY_PRICE_STATUS_3 = "3";
	/**
	 * 已作废
	 */
	public static final String DICTIONARY_PRICE_STATUS_4 = "4";

	/**
	 * 字典类型:储存方式
	 */
	public static final String DICTIONARY_STORE_MODE = "STORE_MODE";
	/**
	 * 罐装
	 */
	public static final String DICTIONARY_STORE_MODE_00 = "00";
	/**
	 * 袋装
	 */
	public static final String DICTIONARY_STORE_MODE_01 = "01";
	/**
	 * 字典类型:启用/禁用
	 */
	public static final String DICTIONARY_RM_ENABLE_STATUS = "RM_ENABLE_STATUS";
	/**
	 * 启用
	 */
	public static final String DICTIONARY_RM_ENABLE_STATUS_1 = "1";
	/**
	 * 禁用
	 */
	public static final String DICTIONARY_RM_ENABLE_STATUS_0 = "0";
	/**
     * 读取任务数据脚本类型
     */
    public static final String DICTIONARY_SCRIPT_TYPE = "SCRIPT_TYPE";
    /**
     * 0=bean shell,
     */
    public static final String DICTIONARY_SCRIPT_TYPE_0 = "0";
    /**
     * 1=sql
     */
    public static final String DICTIONARY_SCRIPT_TYPE_1 = "1";
    /**
     * 字典类型:门岗状态
     */
    public static final String DICTIONARY_GATE_STATUS="GATE_STATUS";
    /**
     * 暂停
     */
    public static final String DICTIONARY_GATE_STATUS_01="01";
    /**
     * 启用
     */
    public static final String DICTIONARY_GATE_STATUS_02="02";
    /**
     * 字典类型:控制车数方式
     */
    public static final String DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE="CONTROL_NUMBER_VEHICLES_MODE";
    /**
     * 不控制
     */
    public static final String DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_00="00";
    /**
     * 按产品控制
     */
    public static final String DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_01="01";
    /**
     * 按产品类别控制
     */
    public static final String DICTIONARY_CONTROL_NUMBER_VEHICLES_MODE_02="02";

    /**
     * 榆林发货方
     */
    public static final String DICTIONARY_SHIPPER_CODE_01 = "01";
    /**
     * 蒙大发货方
     */
    public static final String DICTIONARY_SHIPPER_CODE_02 = "02";
    
    /**
     * 字典类型:发运方式
     */
    public static final String DICTIONARY_SHIP_MODE="SHIP_MODE";
    /**
     * 汽运自提
     */
    public static final String DICTIONARY_SHIP_MODE_00="00";
    /**
     * 汽运配送
     */
    public static final String DICTIONARY_SHIP_MODE_01="01";
    /**
     * 铁路自提
     */
    public static final String DICTIONARY_SHIP_MODE_02="02";

 
    /**
     * 字典类型:配送计划类型
     */
    public static final String DICTIONARY_PLAN_TYPE="PLAN_TYPE";
    /**
     * 主产品配送计划
     */
    public static final String DICTIONARY_PLAN_TYPE_00="00";
    /**
     *主产品客户自提计划
     */
    public static final String DICTIONARY_PLAN_TYPE_01="01";

    /**
     * 字典类型:默认时间
     */
    public static final String DICTIONARY_DEFAULT_TIME="DEFAULT_TIME";
    /**
     * 订单默认提货结束时间
     */
    public static final String DICTIONARY_DEFAULT_DELIVERY_END_TIME="DEFAULT_DELIVERY_END_TIME";
    /**
     * 字典类型:默认系数
     */
    public static final String DICTIONARY_DEFAULT_COEFFICIENT="DEFAULT_COEFFICIENT";
    /**
     * 距离系数1.1
     */
    public static final String DICTIONARY_DEFAULT_COEFFICIENT_01="01";
    /**
     * 运价系数（一般为0.31）
     */
    public static final String DICTIONARY_DEFAULT_COEFFICIENT_02="02";

    /**
	 * 成功
	 */
	public static final String INTERFACE_RESULT_SUCCESS = "00";
	/**
	 * 失败
	 */
	public static final String INTERFACE_RESULT_FAIL = "01";
	
	public static final String INTERFACE_RESULT_STATUS = "status";
	public static final String INTERFACE_RESULT_ERRMSG = "errmsg";
	
	/**
	 * 入厂放行
	 */
	public static final String INTERFACE_OPERATION_CODE_01 = "01";
	/**
	 * 入厂不放行
	 */
	public static final String INTERFACE_OPERATION_CODE_02 = "02";
	/**
	 * 入厂弃号
	 */
	public static final String INTERFACE_OPERATION_CODE_03 = "03";
	/**
	 * 出厂放行
	 */
	public static final String INTERFACE_OPERATION_CODE_11 = "11";
	/**
	 * 出厂不放行
	 */
	public static final String INTERFACE_OPERATION_CODE_12 = "12";
	
	/**
	 * 抢单
	 */
	public static final String DICTIONARY_OPERATION_01 = "01";
	/**
	 * 保运主管待确认装车（已分配待确认--》都未确认）
	 */
	public static final String DICTIONARY_OPERATION_02 = "02";
	/**
	 * 已确认待装车（叉车班长未确认）
	 */
	public static final String DICTIONARY_OPERATION_03 = "03";
	/**
	 * 今日已完成装车单
	 */
	public static final String DICTIONARY_OPERATION_04 = "04";
	/**
	 * 待确认装车(叉车)
	 */
	public static final String DICTIONARY_OPERATION_05 = "05";
	/**
	 * 我的待装车(叉车)
	 */
	public static final String DICTIONARY_OPERATION_08 = "08";
	/**
	 * 待检验车辆
	 */
	public static final String DICTIONARY_OPERATION_06 = "06";	
	
	/**
	 * 已检验车辆
	 */
	public static final String DICTIONARY_OPERATION_07 = "07";
	
	/**
	 * 操作标识
	 */
	public static final String DICTIONARY_OPERATION = "operation";
	
	/**
	 * 操作标识
	 */
	public static final String DICTIONARY_OPERATIONLU = "operationLu";
	/**
	 * 协同操作
	 */
	public static final String DICTIONARY_OPERATION_COORDINATION = "coordination";
	/**
	 * 协同物流公司
	 */
	public static final String DICTIONARY_OPERATION_LOGISTICS_COMPANY = "logisticsCompany";
	/**
	 * 协同物流公司历史
	 */
	public static final String DICTIONARY_OPERATION_LOGISTICS_COMPANY_HISTORY = "logisticsCompanyHistory";
	/**
	 * 客户财务信息
	 */
	public static final String DICTIONARY_OPERATION_LOGISTICS_CUSTOMER_FINANCE = "customerFinance";
	/**
	 * 产品单价
	 */
	public static final String DICTIONARY_OPERATION_LOGISTICS_PRODUCT_PRICE = "productPrice";
	
	/**
	 * 操作标识
	 */
	public static final String DICTIONARY_OPERATION_FLAG = "flag";
	
	public static final String DICTIONARY_OPERATION_FLAG_01 = "01";
	
	/**
	 * ajax分页页码
	 */
	public static final String DICTIONARY_PAGE_INDEX="pageIndex";
	
	/**
	 * socket大屏操作标识
	 */
	public static final String DICTIONARY_SOCKET_OPERATION = "operation";
	/**
	 * 门岗大屏
	 */
	public static final String DICTIONARY_SOCKET_01 = "01";
	/**
	 * 磅房大屏
	 */
	public static final String DICTIONARY_SOCKET_02 = "02";
	/**
	 * 仓库大屏
	 */
	public static final String DICTIONARY_SOCKET_03 = "03";
	
	/**
	 * 操作标识
	 */
	public static final String DICTIONARY_SOCKET_SIGN = "SOCKET_SIGN";
	
	/**
	 * 前缀
	 */
	public static final String DICTIONARY_SOCKET_SIGN_01 = "01";
	/**
	 * 后缀
	 */
	public static final String DICTIONARY_SOCKET_SIGN_02 = "02";
	/**
	 * uuid
	 */
	public static final String DICTIONARY_SOCKET_SIGN_03 = "03";
	
	/**
	 * 编码
	 */
	public static final String DICTIONARY_SOCKET_SIGN_04 = "04";
	/**
	 * 前后缀长度
	 */
	public static final String DICTIONARY_SOCKET_SIGN_05 = "05";
	/**
	 * 长度位数
	 */
	public static final String DICTIONARY_SOCKET_SIGN_06 = "06";
	
	/**
	 * 时长  客户端接入后 等待数据的写入最晚时间 单位秒
	 */
	public static final String DICTIONARY_SOCKET_SIGN_07 = "07";
	/**
	 * 时长 需要推送时延迟的时间 单位秒
	 */
	public static final String DICTIONARY_SOCKET_SIGN_08 = "08";
	/**
	 * 推送侦听端口
	 */
	public static final String DICTIONARY_SOCKET_SIGN_09 = "09";	
	/**
	 * 发送心跳包周期
	 */
	public static final String DICTIONARY_HEARTBEAT_CYCLE="10";
	/**
	 * 默认需要推送时延迟的时间 ：30秒
	 */
	public static final int DEFAULT_RELAY_TIME = 30;
	/**
	 * 默认推送侦听端口:7777
	 */
	public static final int DEFAULT_PUSH_LISTEN_PORT=7777;
	//********************** 这两个常量不存编码表 ****************************//
	/**
	 * 状态成功
	 */
	public static final String DICTIONARY_SOCKET_SIGN_SUCCESS = "01";
	/**
	 * 状态失败
	 */
	public static final String DICTIONARY_SOCKET_SIGN_FAIL = "00";

	//********************** 这两个常量不存编码表 ****************************//
	/**
	 * 获取json数据key
	 */
	public static final String DICTIONARY_SOCKET_JSON_DATA_KEY = "jsonData";
	
	/**
	 * 标识 告诉socket数据发完了
	 */
	public static final String DICTIONARY_SOCKET_OVER_FLAG = "\n";
	
	/**
	 * 入场
	 */
	public static final String CAR_ADMISSION = "carAdmission";
	/**
	 * 出厂（有净重时）
	 */
	public static final String CAR_LEAVE_FACTORY = "carLeaveFactory";
	/**
	 * 拼音码
	 */
	public static final String SPELL_BREVITY_CODE="spellBrevityCode";
	// 许可的相关信息
	/**
	 * 许可证 key 
	 */
	public static final String LICENSE_KEY="D757C0935C01DE26919E4EEEF8AC2C26";
	/**
	 * 许可证项目名称
	 */
	public static final String LICENSE_PROJECT_NAME="yulinchemical";
	/**
	 * 许可证文件名
	 */
	public static final String LICENSE_FILE_NAME="com/dfhc/yulinchemical.key";
	//安检指标类维护
	/**
	 * 安全检查类型
	 */
	public static final String DICTIONARY_SECURITY_CHECK_TYPE="SECURITY_CHECK_TYPE";
	//充装前检查
	public static final String DICTIONARY_SECURITY_CHECK_TYPE_01="10";
	//充装中检查
	public static final String DICTIONARY_SECURITY_CHECK_TYPE_02="11";
	//充装后检查
	public static final String DICTIONARY_SECURITY_CHECK_TYPE_03="12";
	/**
	 * 检查结果控件类型
	 */
	public static final String DICTIONARY_SECURITY_CHECK_RESULT_CTRL_TYPE="SECURITY_CHECK_RESULT_CTRL_TYPE";
	//录入框
	public static final String DICTIONARY_SECURITY_CHECK_RESULT_CTRL_TYPE_01="01";
	//复选框
	public static final String DICTIONARY_SECURITY_CHECK_RESULT_CTRL_TYPE_02="02";
}
