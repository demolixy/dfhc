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
	<h2>中煤能源股份公司煤化工产品提货单</h2>
</div>
<div align="center"> 
<div style="font-size:10px;float:left;margin-right:250px;">日期：${bean.tabDate}</div> 
<div style="font-size:10px">单号：${bean.orderPlanCode}</div> 
<table align="center" bordercolor="#000000" width="100%" border="1">
  <tr>
    <td height="30">客户名称</td>
    <td colspan="6">${bean.customerName}</td>
    <td colspan="3" rowspan="2">运输方式</td>
    <td rowspan="2">${bean.shipMode}</td>
    </tr>
  <tr>
    <td height="30">发货仓库</td>
    <td colspan="6">${bean.deliveryWarehouse}</td>
    </tr>
  <tr>
    <td height="30">承运公司</td>
    <td colspan="6">${bean.logisticsCompany};</td>
    <td colspan="3">交货地点</td>
    <td>${bean.receivingAddress}</td>
    </tr>
  <tr>
    <td width="11%">产品名称</td>
    <td width="10%">规格/型号</td>
    <td width="9%">开票数量(吨)</td>
    <td width="10%">实际发运量（吨）</td>
    <td width="10%">实际发货件数（袋）</td>
    <td width="9%">司机姓名</td>
    <td width="10%">车牌号</td>
    <td colspan="3">联系方式</td>
    <td width="19%">身份证号</td>
  </tr>
  <tr>
    <td height="45">${bean.productName}</td>
    <td>${bean.productModelNumber}</td>
    <td>${bean.nuclearLoad}</td>
    <td>${bean.actualShippingWeight}</td>
    <td>${bean.deliveryNumber}</td>
    <td>${bean.driverName}</td>
    <td>${bean.licensePlate}</td>
    <td colspan="3">${bean.phoneNumber}</td>
    <td>${bean.driverIdCardNumber}</td>
  </tr>
  <tr>
    <td height="40">备注</td>
    <td colspan="10">${bean.remark}</td>
    </tr>
  <tr>
    <td height="18" colspan="2"><div align="right">办事处签字：</div></td>
    <td>&nbsp;</td>
    <td>发运仓库签字：</td>
    <td>&nbsp;</td>
    <td>营业员：</td>
    <td colspan="2">&nbsp;</td>
    <td width="7%">其他：</td>
    <td colspan="2">&nbsp;</td>
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