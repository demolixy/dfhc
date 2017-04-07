<%@page import="java.util.Map"%>
<%@page import="com.dfhc.ISystemConstant"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
var ladingBillStatuss = [];
<%
	Map<String, Object> map = RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	ladingBillStatuss[keyL]='<%=entry.getValue()%>';
<%
	}
%>

//提货单状态翻译
function ladingBillStatusConvert(cellvalue, options, cell){
	return ladingBillStatuss[cellvalue];
}
var billTypes= [];
billTypes['O']='销售订单';
billTypes['D']='汽运配送计划';
billTypes['R']='汽运调拨计划';
billTypes['T']='铁运调拨计划';
billTypes['Z']='主产品自提计划';

<%--
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_BILL_TYPE);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	billTypes[keyL]='<%=entry.getValue()%>';
<%
	}
--%>
//提货单单据类型翻译
function billTypeConvert(cellvalue, options, cell){
	return billTypes[cellvalue.substring(0,1)];
}
var yesOrNos = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	yesOrNos[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//是否
function yesOrNo(cellvalue, options, cell){
	return yesOrNos[cellvalue];
}
var amountDirections = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	amountDirections[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//是否
function amountDirection(cellvalue, options, cell){
	return amountDirections[cellvalue];
}

var priceStatus = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_PRICE_STATUS);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	priceStatus[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//价格管理状态类型翻译
function priceStatusConvert(cellvalue, options, cell){
	return priceStatus[cellvalue];
}	
	
var rowTypes = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_TYPE);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	rowTypes[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//排号表类型翻译
function rowTypeConvert(cellvalue, options, cell){
	return rowTypes[cellvalue];
}	

var rowStatus = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_STATUS);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	rowStatus[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//排号表状态翻译
function rowStatusConvert(cellvalue, options, cell){
	return rowStatus[cellvalue];
}	

var gateStatus = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_STATUS);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	gateStatus[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//门岗状态翻译
function gateStatusConvert(cellvalue, options, cell){
	return gateStatus[cellvalue];
}
var loadingStatus = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS);
	for(Map.Entry<String, Object> entry : map.entrySet()){
%>		
	var keyL ='<%=entry.getKey()%>';
	loadingStatus[keyL]='<%=entry.getValue()%>';
<%
	}
%>
//装车状态翻译
function loadingStatusConvert(cellvalue, options, cell){
	return loadingStatus[cellvalue];
}

//拼接url
function splicedUrl(cellvalue, options, cell){
	var arr = cellvalue.split("@");
	var url = '<a href="${pageContext.request.contextPath}/workflow/diagram-viewer/index.html?processDefinitionId='+arr[0]+'&processInstanceId='+arr[1]+'" >流程监控</a>';
	return url;
}

var targetTypes = [];
<%
	map = RmGlobalReference.get(ISystemConstant.DICTIONARY_TARGET_TYPE);
	for(Map.Entry<String, Object> entry : map.entrySet()){
	%>		
	var keyL ='<%=entry.getKey()%>';
	targetTypes[keyL]='<%=entry.getValue()%>';
	<%
	}
%>
//目标类型翻译
function targetTypeConvert(cellvalue, options, cell){
	return targetTypes[cellvalue];
}
//检查类型翻译
var CheckTypeValue=[];
CheckTypeValue['01']='充装检查前';
CheckTypeValue['02']='充装检查中';
CheckTypeValue['03']='充装检查后';
function formatterCheckType(cellvalue, options, cell){
	return CheckTypeValue[cellvalue];
}
//检查类型控件翻译
var CheckResultCtrlTypeValue=[];
CheckResultCtrlTypeValue['01']='录入框';
CheckResultCtrlTypeValue['02']='复选框';
function formatterCheckResultCtrlType(cellvalue, options, cell){
	return CheckResultCtrlTypeValue[cellvalue];
}
//检查状态翻译
var inspectStatusValue=[];
inspectStatusValue['0']='不通过';
inspectStatusValue['1']='通过';
inspectStatusValue['2']='待检查';
function inspectStatus(cellvalue, options, cell){
	return inspectStatusValue[cellvalue];
}
</script>
</body>
</html>