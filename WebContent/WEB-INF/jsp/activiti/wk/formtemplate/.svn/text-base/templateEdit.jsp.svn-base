<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/jsp/cipher/global.jsp" %>
<title>freemark模板编辑</title>
</head>
<body>
<div  style="text-align: center;">
	<form action="" name="form" id="form">
	<input type="hidden" name="id" value="${bean.id }"/>
	<div>
		<textarea rows="25" cols="" style="width: 1000px;margin-left: 20px;" name="templateFreemark"><c:out value="${bean.templateFreemark }"></c:out></textarea>
	</div>
	<input type="button" onclick="subForm(this);" value="确定修改"/>
</form>
	
</div>
</body>
<script type="text/javascript">
function subForm(obj){
	$.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/formtemplate/updateTemplate?date="+new Date(),
        data:$("#form").serialize(),	
        dataType: "json",
        async:false , //false为同步
        success: function(data,status) {
	   	   alert(data.message);
	   	   if(data.status == '1'){
	   		   window.location = "<%=request.getContextPath()%>/formtemplate";
	   	   }
       },
       error:function(xhr,s1,s2){
       	var message = xhr.responseText;
			eval("var json = "+message);
			if($.trim(json.error).length==0){
				alert('数据异常,请刷新页面重试');
			}else{
				alert(json.error);
			}
  	   }
    });
}
</script>
</html>