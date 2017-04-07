//启动任务
function start(processDefinitionId,context,formObj){
	formObj.action = context+"/businessAppController/start?processDefinitionId="+processDefinitionId;
	formObj.submit();
}

//办理任务
function finish(taskId,context,formObj){
	formObj.action = context+"/generalbusinessprocess/complete/"+taskId;
	formObj.submit();
}