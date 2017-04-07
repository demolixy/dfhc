<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="org.quickbundle.project.IGlobalConstants"%>
<%
  	String total_code = request.getParameter("total_code");
  	if(total_code == null){
  		total_code = "default";
  	}
%>
<html>
<head>
<title>rm-based architecture project</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" >
</head>
<frameset cols="200,*" frameborder="NO" border="0"  framespacing="0" name="mainFrameSet" id="mainFrameSet">
	<frame src="<%=request.getContextPath()%>/pubController/getTreeOrg" scrolling="AUTO" name="leftFrame" />
	<frameset rows="*,0" frameborder="NO" framespacing="0" name="contentFrameSet" id="contentFrameSet">
	  	<frame src="<%=request.getContextPath()%>/jsp/welcome.jsp" scrolling="auto" name="contentFrame" />
	  	<frame src="info.jsp" scrolling="no" name="infoFrame" />
  	</frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>
