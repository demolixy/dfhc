<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>榆林 - 登录</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="/jsp/android/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqGrid/jquery.md5.js"></script>
</head>

<body class="login">
<div class="loginCon">
<form name="loginForm" id="loginForm"  method="post">
  <div class="loginArea">
     <div class="dlItem">
       <i class="fa fa-user"></i><input type="text" name="login_id" class="dlInput" placeholder="请输入您的用户名">
     </div>
     <div class="dlItem">
       <i class="fa fa-unlock-alt"></i><input type="password" name="pwd" class="dlInput" placeholder="请输入您的密码">
       	<input type="hidden" name="password"/>
     </div>
  </div>
  <div class="remenber">
<!--     <input type="checkbox"><label>记住密码</label> -->
  </div>
  <div class="remenber">
<!--     <input type="checkbox"><label>自动登录</label> -->
  </div>

  <div class="clear"></div>
  <div class="loginSubmit"><button class="loginBtn">登&nbsp;&nbsp;录</button></div>
  </form>
</div>

</body>
<script type="text/javascript">
	$(function(){
		$(".loginBtn").click(function(){
			var userName=$("#loginForm").find("input[name='login_id']").val();
			
			userName=$.trim(userName);
			if(userName.length==0){
				alert("请输入用户名！");
				return false;
			}
			
			var pwd=$("#loginForm").find("input[name='pwd']").val();
			pwd=$.trim(pwd);
			
			if(pwd.length==0){
				alert("请输入密码！");
				return false;
				
			}
			
			$("#loginForm").find("input[name='password']").val($.md5(pwd));
			loginForm.action="${pageContext.request.contextPath }/RmLoginAction.do?cmd=loginHandheld";
			loginForm.submit();
		});
		
	});
</script>
</html>