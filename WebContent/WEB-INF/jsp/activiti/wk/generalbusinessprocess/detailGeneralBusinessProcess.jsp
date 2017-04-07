<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：qb5&activiti
   *
   * 文件名称：detailGeneralBusinessProcess.jsp
   *
   * 功能描述：通用流程业务表详细信息页面
   * 
   * 版本历史：
   * 2016-12-26   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@page import="org.quickbundle.tools.helper.RmStringHelper" %>
<%@page import="com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo" %>
<%@page import="com.dfhc.wk.generalbusinessprocess.IGeneralBusinessProcessConstants" %>
<%  //判断是否只读
    boolean isReadOnly = false;
    if("1".equals(request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_IS_READ_ONLY))) {
        isReadOnly = true;
    } else if("1".equals(request.getParameter(IGeneralBusinessProcessConstants.REQUEST_IS_READ_ONLY))){
        isReadOnly = true;
    } 
%>
<%  //取出本条记录
    GeneralBusinessProcessVo resultVo = null;  //定义一个临时的vo变量
    resultVo = (GeneralBusinessProcessVo)request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_BEAN);  //从request中取出vo, 赋值给resultVo
    RmVoHelper.replaceToHtml(resultVo);  //把vo中的每个值过滤
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/jsp/include/rmGlobal.jsp" %>
<link href="<%=request.getContextPath() %>/css/index.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><bean:message key="qb.web_title"/></title>
<script type="text/javascript">
<%if(!isReadOnly) {%>
    function find_onClick(){  //直接点到修改页面
        window.location.href="<%=request.getContextPath()%>/generalbusinessprocess/update/" + $("#head_id").val();
    }
    function delete_onClick(){  //直接点删除单条记录
        if(!getConfirm()) {  //如果用户在确认对话框中点"取消"
            return false;
        }
        form.action="<%=request.getContextPath()%>/generalbusinessprocess/delete";
        form.submit();
    } <%} %>
</script>
</head>
<body style="background:#fff;">
<form name="form" method="post">

<div class="button_area">
<%if(!isReadOnly) {%>
    <input type="button" class="onClickToBtn" id="button_back" value="返回"  onclick="javascript:history.go(-1);" />
    <input type="button" class="onClickToBtn" id="button_delete" value="删除" onclickto="javascript:delete_onClick();" /> <%} %>
    <input type="button" class="onClickToBtn" id="button_update" value="修改" onclick="javascript:find_onClick();" />
</div>

<table class="mainTable" style="position:relative;top:10px;">
    <tr>
        <td align="right" width="20%">&nbsp;</td>
        <td width="35%">&nbsp;</td>
        <td align="right" width="20%">&nbsp;</td>
        <td width="25%">&nbsp;</td>
    </tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procName")%>：</td>

		<td>${bean.procName}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procKey")%>：</td>

		<td>${bean.procKey}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiator")%>：</td>

		<td>${bean.initiator}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorOrganizationDepartment")%>：</td>

		<td>${bean.initiatorOrganizationDepartment}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%>：</td>

		<td>${bean.initiatorTime}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessType")%>：</td>

		<td>${bean.businessType}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessGroup")%>：</td>

		<td>${bean.businessGroup}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute70")%>：</td>

		<td>${bean.businessAttribute70}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute69")%>：</td>

		<td>${bean.businessAttribute69}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute68")%>：</td>

		<td>${bean.businessAttribute68}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute67")%>：</td>

		<td>${bean.businessAttribute67}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute66")%>：</td>

		<td>${bean.businessAttribute66}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute65")%>：</td>

		<td>${bean.businessAttribute65}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute64")%>：</td>

		<td>${bean.businessAttribute64}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute63")%>：</td>

		<td>${bean.businessAttribute63}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute62")%>：</td>

		<td>${bean.businessAttribute62}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute61")%>：</td>

		<td>${bean.businessAttribute61}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute60")%>：</td>

		<td>${bean.businessAttribute60}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute59")%>：</td>

		<td>${bean.businessAttribute59}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute58")%>：</td>

		<td>${bean.businessAttribute58}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute57")%>：</td>

		<td>${bean.businessAttribute57}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute56")%>：</td>

		<td>${bean.businessAttribute56}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute55")%>：</td>

		<td>${bean.businessAttribute55}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute54")%>：</td>

		<td>${bean.businessAttribute54}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute53")%>：</td>

		<td>${bean.businessAttribute53}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute52")%>：</td>

		<td>${bean.businessAttribute52}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute51")%>：</td>

		<td>${bean.businessAttribute51}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute50")%>：</td>

		<td>${bean.businessAttribute50}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute49")%>：</td>

		<td>${bean.businessAttribute49}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute48")%>：</td>

		<td>${bean.businessAttribute48}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute47")%>：</td>

		<td>${bean.businessAttribute47}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute46")%>：</td>

		<td>${bean.businessAttribute46}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute45")%>：</td>

		<td>${bean.businessAttribute45}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute44")%>：</td>

		<td>${bean.businessAttribute44}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute43")%>：</td>

		<td>${bean.businessAttribute43}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute42")%>：</td>

		<td>${bean.businessAttribute42}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute41")%>：</td>

		<td>${bean.businessAttribute41}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute40")%>：</td>

		<td>${bean.businessAttribute40}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute39")%>：</td>

		<td>${bean.businessAttribute39}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute38")%>：</td>

		<td>${bean.businessAttribute38}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute37")%>：</td>

		<td>${bean.businessAttribute37}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute36")%>：</td>

		<td>${bean.businessAttribute36}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute35")%>：</td>

		<td>${bean.businessAttribute35}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute34")%>：</td>

		<td>${bean.businessAttribute34}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute33")%>：</td>

		<td>${bean.businessAttribute33}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute32")%>：</td>

		<td>${bean.businessAttribute32}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute31")%>：</td>

		<td>${bean.businessAttribute31}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute30")%>：</td>

		<td>${bean.businessAttribute30}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute29")%>：</td>

		<td>${bean.businessAttribute29}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute28")%>：</td>

		<td>${bean.businessAttribute28}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute27")%>：</td>

		<td>${bean.businessAttribute27}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute26")%>：</td>

		<td>${bean.businessAttribute26}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute25")%>：</td>

		<td>${bean.businessAttribute25}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute24")%>：</td>

		<td>${bean.businessAttribute24}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute23")%>：</td>

		<td>${bean.businessAttribute23}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute22")%>：</td>

		<td>${bean.businessAttribute22}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute21")%>：</td>

		<td>${bean.businessAttribute21}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute20")%>：</td>

		<td>${bean.businessAttribute20}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute19")%>：</td>

		<td>${bean.businessAttribute19}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute18")%>：</td>

		<td>${bean.businessAttribute18}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute17")%>：</td>

		<td>${bean.businessAttribute17}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute16")%>：</td>

		<td>${bean.businessAttribute16}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute15")%>：</td>

		<td>${bean.businessAttribute15}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute14")%>：</td>

		<td>${bean.businessAttribute14}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute13")%>：</td>

		<td>${bean.businessAttribute13}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute12")%>：</td>

		<td>${bean.businessAttribute12}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute11")%>：</td>

		<td>${bean.businessAttribute11}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute10")%>：</td>

		<td>${bean.businessAttribute10}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute9")%>：</td>

		<td>${bean.businessAttribute9}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute8")%>：</td>

		<td>${bean.businessAttribute8}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute7")%>：</td>

		<td>${bean.businessAttribute7}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute6")%>：</td>

		<td>${bean.businessAttribute6}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute5")%>：</td>

		<td>${bean.businessAttribute5}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute4")%>：</td>

		<td>${bean.businessAttribute4}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute3")%>：</td>

		<td>${bean.businessAttribute3}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute1")%>：</td>

		<td>${bean.businessAttribute1}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute2")%>：</td>

		<td>${bean.businessAttribute2}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	</table>

<input id="head_id" type="hidden" name="id" value="<%=RmStringHelper.prt(resultVo.getId())%>" />
</form>
</body>
</html>	
