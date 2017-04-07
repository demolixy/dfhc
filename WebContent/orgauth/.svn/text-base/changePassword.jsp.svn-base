<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" />
<%@ include file="/jsp/cipher/global.jsp" %>
  <script src="<%=request.getContextPath()%>/js/jqGrid/jquery.md5.js"></script>
<title>修改密码</title>
<style>
 body.login{background-color:#000;}
 .content {
background-color: #fff;
width: 291px;
margin: 0 auto;
margin-bottom: 0px;
padding: 30px;
padding-top: 20px;
padding-bottom: 15px;
}

</style>
</head>
<!-- BEGIN BODY -->


<body class="login" >

<div style="background-color:#000;margin: 0;width: 100%;height: 100%;min-height:1000px;overflow-y:auto; position: fixed; padding:100px">

<div style="width:100%;"></div>

   <div class="content">
   
   <form class="form-vertical login-form" action="" novalidate="novalidate" name="form">

			<h3 class="form-title">修改用户密码</h3>

			<div class="alert alert-error hide">

				<button class="close" data-dismiss="alert"></button>

				<span id="alertMsg">输入旧密码和要新密码.</span>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">旧密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="旧密码" name="old_password_m"  value="">

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">新密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="新密码" name="password_m" value="">
						<input type="hidden" name="password" value=""/>

					</div>

				</div>

			</div>
			
			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">再次输入新密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="再次输入新密码" name="password_again_m" value="">
						<input class="m-wrap placeholder-no-fix" type="hidden" name="password_again" value=""/>

					</div>

				</div>

			</div>

			<div class="form-actions">


				<input type="button" class="btn green pull-right" value="确认修改 " onclick="updatePsd()">


				            

			</div>

		
			<input type="hidden" name="old_password" value=""/>
			<input type="hidden" name="password" value=""/>
			<input type="hidden" name="password_again" value=""/>

		</form>
		
		
   
   </div>


</div>


<script type="text/javascript">

function updatePsd(){

	  var  old_password_m=$("input[name='old_password_m']").val();
	  old_password_m=$.trim(old_password_m);

      if(old_password_m==''){

    	   $("div.alert").removeClass("hide");
    	   $("#alertMsg").text("请输入旧密码");
          return false;
          }else{
        	  $("div.alert").addClass("hide");

           }
      $("form").find("input[name='old_password']").val($.md5(old_password_m));
	   old_password_m=$("input[name='password_m']").val();
	   old_password_m=$.trim(old_password_m);

      if(old_password_m==''){

    	   $("div.alert").removeClass("hide");
    	   $("#alertMsg").text("请输入新密码");
          return false;
          }else{
        	  $("div.alert").addClass("hide");

           }

      if(!(/^[A-Za-z0-9]+$/.test( old_password_m ) && old_password_m.length<21 && old_password_m.length>5 ) ){
    	   $("div.alert").removeClass("hide");
    	   $("#alertMsg").text("密码为6-20个字符，请使用字母或数字的组合密码，请注意区分大小写!");
    	   return false;
      }else{
    	  $("div.alert").addClass("hide");
          }

      
	  var  password_again_m=$("input[name='password_again_m']").val();
	  password_again_m=$.trim(password_again_m);

      if(password_again_m!=old_password_m){

    	   $("div.alert").removeClass("hide");
    	   $("#alertMsg").text("两次输入的密码不一致");
          return false;
          }else{
        	  $("div.alert").addClass("hide");

         }
      
      $("form").find("input[name='password']").val($.md5(old_password_m));
      $("form").find("input[name='password_again']").val($.md5(password_again_m));
    
   
       $.ajax({
          type:'post',//可选get
          url:'${pageContext.request.contextPath}/pubController/updateSysPsd',
          data:$("form").serialize(),//送评表的表格id属性值
          dataType:'json',
          async:false , //false为同步
          success:function(data,status){
    
              alert("密码修改成功");
        	  window.location.href="${pageContext.request.contextPath}/index.jsp";
         },
         error:function(xhr,s1,s2){
          	var message = xhr.responseText;
          	var json=$.parseJSON(message);
      		
      		if($.trim(json.exception).length==0){
      			alert('数据异常,请刷新页面重试');
      		}else{
      			alert(json.exception);
      		}
         }
         });
      

	
}
  


</script>


</body>
	
<!-- END BODY -->

</html>