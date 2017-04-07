﻿﻿<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ include file="funcTree_orgauth.jsp" %>

//workflow begin
node_workflow = insFld(foldersTree, gFld ("&nbsp;工作流", "", "ftv2folderopen.gif", "ftv2folderclosed.gif"));
    node_workflow_inbox = insDoc(node_workflow, gLnk("0","&nbsp;流程模型", "<%=request.getContextPath()%>/workflow/model/listmodel", "ftv2link.gif"));
	node_workflow_inbox = insDoc(node_workflow, gLnk("0","&nbsp;流程定义", "<%=request.getContextPath()%>/workflow/model/listprocedef", "ftv2link.gif"));
	node_workflow_monitor = insDoc(node_workflow, gLnk("0","&nbsp;流程实例", "<%=request.getContextPath()%>/workflow/model/listinstance", "ftv2link.gif"));
	node_workflow_bussiness_appliction = insDoc(node_workflow, gLnk("0","&nbsp;业务申请", "<%=request.getContextPath()%>/workflow/model/businessApplication", "ftv2link.gif"));
	node_workflow_general_inbox = insDoc(node_workflow, gLnk("0","&nbsp;通用待办任务", "<%=request.getContextPath()%>/generalbusinessprocess/taskList", "ftv2link.gif"));
	node_workflow_inbox = insDoc(node_workflow, gLnk("0","&nbsp;待办任务", "<%=request.getContextPath()%>/sampleleave/task/list", "ftv2link.gif"));
	node_workflow_sampleLeave = insDoc(node_workflow, gLnk("0","&nbsp;请假申请", "<%=request.getContextPath()%>/sampleleave/apply", "ftv2link.gif"));
	node_workflow_templatedef = insDoc(node_workflow, gLnk("0","&nbsp;模板定义", "<%=request.getContextPath()%>/formtemplate", "ftv2link.gif"));
	node_workflow_task_node_ext_conf = insDoc(node_workflow, gLnk("0","&nbsp;任务节点扩展配置", "<%=request.getContextPath()%>/tasknodeextconf", "ftv2link.gif"));
	node_workflow_process_data_items = insDoc(node_workflow, gLnk("0","&nbsp;流程数据项", "<%=request.getContextPath()%>/processdataitems", "ftv2link.gif"));
	node_workflow_data_set = insDoc(node_workflow, gLnk("0","&nbsp;数据集", "<%=request.getContextPath()%>/dataset", "ftv2link.gif"));
	//node_workflow_doing = insDoc(node_workflow, gLnk("0","&nbsp;在办箱", "#", "ftv2link.gif"));
	//node_workflow_sent = insDoc(node_workflow, gLnk("0","&nbsp;历史任务", "#", "ftv2link.gif"));
	//toClickNodes.push("node_workflow");
//workflow end
	
//quartz begin
node_scheduler_folder = insFld(foldersTree, gFld ("&nbsp;调度管理", "", "ftv2folderopen.gif", "ftv2folderclosed.gif"));
	rmCodeGenerationJobDetail_maintenance = insDoc(node_scheduler_folder, gLnk("0","&nbsp;作业定义", "<%=request.getContextPath()%>/JobDetailAction.do?cmd=queryAll", "ftv2link.gif"));
	rmCodeGenerationJobTrigger_maintenance = insDoc(node_scheduler_folder, gLnk("0","&nbsp;作业部署", "<%=request.getContextPath()%>/JobTriggerAction.do?cmd=queryAll", "ftv2link.gif"));
	rmCodeGenerationJobExecuting_maintenance = insDoc(node_scheduler_folder, gLnk("0","&nbsp;执行中作业", "<%=request.getContextPath()%>/JobExecutingAction.do?cmd=queryAll", "ftv2link.gif"));
	rmCodeGenerationRmSchedulerEvent_maintenance = insDoc(node_scheduler_folder, gLnk("0","&nbsp;调度事件", "<%=request.getContextPath()%>/RmSchedulerEventAction.do?cmd=queryAll", "ftv2link.gif"));
//quartz end

node_monitor_log = insFld(foldersTree, gFld ("&nbsp;监控日志", "", "ftv2folderopen.gif", "ftv2folderclosed.gif"));
	node_buffer = insDoc(node_monitor_log, gLnk("0","&nbsp;缓冲器监控", "<%=request.getContextPath()%>/admin/buffer/listBuffer.jsp", "ftv2link.gif"));
	node_javamelody = insDoc(node_monitor_log, gLnk("0","&nbsp;系统监控", "<%=request.getContextPath()%>/admin/monitoring/index.jsp", "ftv2link.gif"));
	node_proxool = insDoc(node_monitor_log, gLnk("0","&nbsp;连接池监控", "<%=request.getContextPath()%>/admin/proxool", "ftv2link.gif"));
	//org_log begin
	rmCodeGenerationRmLogType_maintenance = insDoc(node_monitor_log, gLnk("0","&nbsp;日志类型", "<%=request.getContextPath()%>/RmLogTypeAction.do?cmd=queryAll", "ftv2link.gif"));
	rmCodeGenerationRmLog_maintenance = insDoc(node_monitor_log, gLnk("0","&nbsp;日志", "<%=request.getContextPath()%>/RmLogAction.do?cmd=queryAll&RM_ORDER_STR=ACTION_DATE DESC", "ftv2link.gif"));
	rmCodeGenerationFileLog_maintenance = insDoc(node_monitor_log, gLnk("0","&nbsp;文件日志", "<%=request.getContextPath()%>/fileLogMonitor", "ftv2link.gif"));	
	//org_log end

node_extendFunction = insFld(foldersTree, gFld ("&nbsp;扩展功能", "", "ftv2folderopen.gif", "ftv2folderclosed.gif"));
	rmCodeGenerationRmCodeType_maintenance = insDoc(node_extendFunction, gLnk("0","&nbsp;编码表", "<%=request.getContextPath()%>/RmCodeTypeAction.do?cmd=queryAll", "ftv2link.gif"));	
	rmCodeGenerationRmAffix_maintenance = insDoc(node_extendFunction, gLnk("0","&nbsp;附件", "<%=request.getContextPath()%>/RmAffixAction.do?cmd=queryAll", "ftv2link.gif"));

/*
node_profiler = insFld(foldersTree, gFld ("&nbsp;性能分析", "", "ftv2folderopen.gif", "ftv2folderclosed.gif"));
    node_sqlProfiler = insDoc(node_profiler, gLnk("0","&nbsp;Request分析器", "<%=request.getContextPath()%>/admin/log/request_profiler.py", "ftv2link.gif"));
    node_sqlProfiler = insDoc(node_profiler, gLnk("0","&nbsp;SQL分析器", "<%=request.getContextPath()%>/admin/log/sql_profiler.py", "ftv2link.gif"));
    node_sqlProfiler = insDoc(node_profiler, gLnk("0","&nbsp;Method分析器", "<%=request.getContextPath()%>/admin/log/method_profiler.py", "ftv2link.gif"));
*/
