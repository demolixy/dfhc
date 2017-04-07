<%@page import="org.quickbundle.project.RmProjectHelper"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@ page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@ page import="org.quickbundle.tools.helper.RmStringHelper" %>
<%@ page import="org.quickbundle.modules.log.rmlog.vo.RmLogVo" %>
<%@ page import="org.quickbundle.modules.log.rmlog.util.IRmLogConstants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/jsp/cipher/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="qb.web_title"/></title>

</head>
<body>
<form name="form" method="post">
<input type="hidden"  name="selectLogFileName" value="${fullFileName}"/>
<table  align="center" width="100%">
	<tr >
		<td width="100%" align="center">日志文件名${shortFileName}内容:</td>
		
	</tr>
	<tr >
		<td width="100%">
		  <textarea rows="30" cols="60" readonly="readonly" style="width:90%;">
		     ${logContent}
		  </textarea>
		</td>
	</tr>
</table>
<jsp:include page="/jsp/include/page.jsp"></jsp:include>

<table align="center">
	<tr>
		<td>
			<input type="button" class="button_ellipse" id="button_back" value="返回"  onClick="javascript:history.go(-1);" >
		</td>
	</tr>
</table>

</form>
</body>
</html>