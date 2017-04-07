<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：qb5&activiti
   *
   * 文件名称：detailFormTemplate.jsp
   *
   * 功能描述：表单模板详细信息页面
   * 
   * 版本历史：
   * 2016-11-25   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@page import="org.quickbundle.tools.helper.RmStringHelper" %>
<%@page import="com.dfhc.wk.formtemplate.vo.FormTemplateVo" %>
<%@page import="com.dfhc.wk.formtemplate.IFormTemplateConstants" %>
<%  //判断是否只读
    boolean isReadOnly = false;
    if("1".equals(request.getAttribute(IFormTemplateConstants.REQUEST_IS_READ_ONLY))) {
        isReadOnly = true;
    } else if("1".equals(request.getParameter(IFormTemplateConstants.REQUEST_IS_READ_ONLY))){
        isReadOnly = true;
    } 
%>
<%  //取出本条记录
    FormTemplateVo resultVo = null;  //定义一个临时的vo变量
    resultVo = (FormTemplateVo)request.getAttribute(IFormTemplateConstants.REQUEST_BEAN);  //从request中取出vo, 赋值给resultVo
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
        window.location.href="<%=request.getContextPath()%>/formtemplate/update/" + $("#head_id").val();
    }
    function delete_onClick(){  //直接点删除单条记录
        if(!getConfirm()) {  //如果用户在确认对话框中点"取消"
            return false;
        }
        form.action="<%=request.getContextPath()%>/formtemplate/delete";
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
		<td align="right"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateName")%>：</td>

		<td>${bean.templateName}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateHtml")%>：</td>

		<td>${bean.templateHtml}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateFreemark")%>：</td>

		<td>${bean.templateFreemark}&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	</table>

<input id="head_id" type="hidden" name="id" value="<%=RmStringHelper.prt(resultVo.getId())%>" />
</form>
</body>
</html>	
