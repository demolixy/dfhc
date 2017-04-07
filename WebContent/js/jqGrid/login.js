

$(function(){
	
	function writeBackVerifyCode() {
		if(document.getElementById("onload_js")){  
		    var el=document.getElementById("onload_js");  
		    var p=el.parentNode;    
		    p.removeChild(el);     
		}  
		var head = document.getElementsByTagName("head").item(0);    
		var script = document.createElement("script");    
		script.src = getRootPath()+"/jsp/support/verify/onLoad_initValue.jsp";  
		script.id = "onload_js";
		head.appendChild(script);  
}
	
	
})



function login(){
	
	var userName=$("#loginForm").find("input[name='login_id']").val();
	
	userName=$.trim(userName);
	if(userName.length==0){
		alert("请填写用户名！");
		return false;
	}
	
	var pwd=$("#loginForm").find("input[name='pwd']").val();
	pwd=$.trim(pwd);
	
	if(pwd.length==0){
		alert("请密码！");
		return false;
		
	}
	var verifyCode=$("#loginForm").find("input[name='verifyCode']").val();
	verifyCode=$.trim(verifyCode);
	
	if(verifyCode.length==0){
		
		alert("请填写用户密码");
		return false;
	}
	
	$("#loginForm").find("input[name='password']").val($.md5(pwd));
	
	
	loginForm.action=getRootPath()+"/RmLoginAction.do?cmd=login";
	loginForm.submit();
	
	
}