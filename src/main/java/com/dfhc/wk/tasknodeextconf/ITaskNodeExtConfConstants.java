/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  qb5&activiti
 *
 * 文件名称：  ITaskNodeExtConfConstants.java
 *
 * 功能描述：  任务节点扩展配置常量
 * 
 * 版本历史：
 * 
 * 2016-11-25   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.wk.tasknodeextconf;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;
/**
 * 任务节点扩展配置 常量类
 * 
 * @author 龙色波
 * @see 参见的类
 */
public interface ITaskNodeExtConfConstants extends IGlobalConstants {

    //表名、显示名
    public final static String TABLE_NAME = "wk_task_node_ext_conf";
    public final static String TABLE_NAME_DISPLAY = "任务节点扩展配置";
    public final static String TABLE_PK = "id";
    //列名汉化
    @SuppressWarnings({ "unchecked", "serial" })
    public final static Map<String, String> TABLE_COLUMN_DISPLAY = new CaseInsensitiveMap(){{
		put("usable_status","数据可用状态");
		put("create_time","create_time");
		put("create_ip","创建IP");
		put("create_user_id","创建用户ID");
		put("create_user_name","create_user_name");
		put("modify_time","modify_time");
		put("modify_ip","修改IP");
		put("modify_user_id","修改用户ID");
		put("modify_user_name","modify_user_name");
		put("delete_time","delete_time");
		put("delete_ip","delete_ip");
		put("delete_flag","delete_flag");
		put("delete_user_id","delete_user_id");
		put("delete_user_name","delete_user_name");
		put("order_code","排序编码");
		put("attribute1","扩展字段1");
		put("attribute2","扩展字段2");
		put("attribute3","扩展字段3");
		put("attribute4","扩展字段4");
		put("attribute5","扩展字段5");
		put("id","ID");
		put("procDefId","流程定义名称");
		put("taskId","任务名称");
		put("taskDefinitionKey","任务key");
		put("readTaskDataScript","读取任务数据脚本");
		put("readTaskDataScriptType","读取任务数据脚本类型");
		put("writeTaskDataScript","写入任务数据脚本");
		put("writeTaskDataScriptType","写入任务脚本类型");
    }};

    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}
