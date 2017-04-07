<%@page import="com.dfhc.bus.orderproductdetail.IOrderProductDetailConstants" %>
<%@page import="com.dfhc.bus.order.IOrderConstants" %>
<%@page import="org.quickbundle.tools.helper.RmJspHelper" %>
<%@page import="org.quickbundle.project.RmGlobalReference" %>
<%@page import="com.dfhc.ISystemConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE html> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta charset="utf-8" /> 
<title>提货单打印</title> 
<style> 
table{ 
 color:#000;
 border-collapse:collapse;
 text-align:center;
 font-size:10px;
 } 
table,th, td
  {
  border: 1px solid black;
  }
</style> 
</head> 
<body> 
<script language=javascript> 
function doPrint() { 
oldhtml = window.document.body.innerHTML;
bdhtml=document.getElementById("printTab").innerHTML;
window.document.body.innerHTML=bdhtml; 
window.print(); 
window.document.body.innerHTML = oldhtml;
} 
</script> 
<div id="printTab" >
<div align="center">
	<h2>汽车装车通知单</h2>
</div>
<div align="center"> 
<table align="center" bordercolor="#000000" width="100%" border="1">
  <tr>
    <td height="40">作业时间：</td>
    <td height="40" colspan="2">${bean.jobTime}</td>
    <td colspan="2">派单时间：</td>
    <td>${bean.dispatchTime}</td>
    <td width="35%" rowspan="8">
      <div>装车作业安全须知</div>
      <div>&nbsp;</div>
      <div align="left">1、熟知《特种作业人员安全作业标准》。</div>
      <div>&nbsp;</div>
      <div align="left">2、叉车作业过程中严格按照《特种作业人员安全作业标准》进行作业。</div>
      <div>&nbsp;</div>
      <div align="left">3、装卸作业过程中严格按照《装卸人员安全作业标准》进行作业。</div>
      <div>&nbsp;</div>
      <div align="left">注：在此通知单签字，视为在作业过程中遵守该须知。拒绝在此通知单签字，取消作业资格。</div>
    </td>
    </tr>
    <tr>
    <td width="11%" height="40">装车车牌号：</td>
    <td colspan="2">${bean.licensePlate}</td>
    <td colspan="2">提货单号：</td>
    <td>${bean.ladingBillCode}</td>
    </tr>
  <tr>
    <td height="40">产品名称：</td>
    <td width="10%">${bean.productName}</td>
    <td width="10%">产品型号：</td>
    <td width="9%">${bean.productModelNumber}</td>
    <td width="10%">吨数：</td>
    <td>${bean.toneNum}</td>
    </tr>
  <tr>
    <td height="40">产品等级：</td>
    <td colspan="2">${bean.productLevel}</td>
    <td colspan="2">门号：</td>
    <td>${bean.gateNum}</td>
    </tr>
  <tr>
    <td height="40" colspan="6">配装情况</td>
    </tr>
  <tr>
    <td height="30" colspan="2">产品批次</td>
    <td height="30" colspan="2">仓位号</td>
    <td height="30" colspan="2">数量(吨)</td>
    </tr>
  	<c:forEach items="${beans}" begin="0" end="2" var="beanvo">
  	<tr>
    <td height="30" colspan="2">${beanvo.productBatchNumber}</td>
    <td height="30" colspan="2">${beanvo.positionNum}</td>
    <td height="30" colspan="2">${beanvo.num}</td>
    </tr>
	</c:forEach>
</table>
<div><p style="font-size:8px">说明：本单据一式三联：一联：供销仓储中心保管员：一联：叉车班长：一联：保运主管。</p></div>
<div style="font-size:10px;float:left;margin-right:180px;">保管员：${bean.storeman}</div> 
<div style="font-size:10px;float:left">叉车班长：${bean.forkliftMonitor}</div> 
<div style="font-size:10px">保运主管：${bean.operationSupervisor}</div> 
</div> 
</div>
<div align="center"><input type="button" value="打印" onClick="doPrint()"/>		
  <input type="button" value="返回" onclick="javascript:history.back(-1);"/>
</div>
</body> 
</html> 