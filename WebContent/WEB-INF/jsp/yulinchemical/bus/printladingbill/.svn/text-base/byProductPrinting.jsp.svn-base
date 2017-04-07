<%@page import="com.dfhc.bus.orderproductdetail.IOrderProductDetailConstants" %>
<%@page import="com.dfhc.bus.order.IOrderConstants" %>
<%@page import="org.quickbundle.tools.helper.RmJspHelper" %>
<%@page import="org.quickbundle.project.RmGlobalReference" %>
<%@page import="com.dfhc.ISystemConstant" %>
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
<div id="printTab">
<div align="center">
	<h2>中煤陕西榆林能源化工有限公司</h2>
</div>
<div align="center">
	<h2>销售提货单</h2>
</div>
<div align="center"> 
<div style="font-size:10px;float:left;margin-right:120px;">日期${bean.tabDate}</div> 
<div style="font-size:10px;float:left;">订单编号${bean.orderNumber}</div> 
<div style="font-size:10px;">行号${bean.lineNumber}</div>
<table align="center" bordercolor="#000000" width="100%" border="1">
  <tr>
    <td height="30">客户名称</td>
    <td colspan="8">${bean.customerName}</td>
    </tr>
     <tr>
    <td width="89" height="74">产品名称</td>
    <td width="84">计划发运量（吨）</td>
    <td width="126">实际发运量（吨）</td>
    <td width="104">单价（元）</td>
    <td width="129">金额（元）</td>
    <td width="82">司机姓名</td>
    <td width="121">联系方式</td>
    <td width="209">车牌号</td>
    <td width="291">身份证号</td>
  </tr>
  <tr>
    <td height="45">${bean.productName}</td>
    <td>${bean.nuclearLoad}</td>
    <td>${bean.actualShippingWeight}</td>
    <td>${bean.productUnitPrice}</td>
    <td>${bean.actualShippingAmount}</td>
    <td>${bean.driverName}</td>
    <td>${bean.phoneNumber}</td>
    <td>${bean.licensePlate}</td>
    <td>${bean.driverIdCardNumber}</td>
  </tr>
  <tr>
    <td height="30">发运方式</td>
    <td colspan="3">${bean.shipMode}</td>
    <td colspan="2">交货地点</td>
    <td colspan="3">${bean.receivingAddress}</td>
    </tr>
  <tr>
    <td height="30">仓储</td>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">审核人</td>
    <td colspan="2">&nbsp;</td>
    <td>制单人</td>
    <td>&nbsp;</td>
    </tr>
</table>
</div> 
<div align="center">
<p style="font-size:8px">一式六联分为：一联销售中心：一联财务：一联仓储储运中心：一联出厂凭证：一联司机留存：一联客户回执</p>
</div>
</div>
<div align="center"><input type="button" value="打印" onClick="doPrint()"/>		
  <input type="button" value="返回" onclick="javascript:history.back(-1);"/>
</div>
</body> 
</html> 