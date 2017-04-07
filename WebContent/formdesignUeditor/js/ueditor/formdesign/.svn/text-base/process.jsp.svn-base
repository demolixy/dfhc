<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>选择流程</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <meta name="generator" content="www.leipi.org" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-ie6.css">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/ie.css">
    <![endif]-->
    <link rel="stylesheet" href="leipi.style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/dialogs/internal.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/jquery-1.7.2.min.js?2023"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/ueditor.config.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/ueditor.all.js?2023"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/lang/zh-cn/zh-cn.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/formdesign/leipi.formdesign.v4.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/data-template.js"></script>
</head>
<body>
<form action="" enctype="application/x-www-form-urlencoded"></form>
<div class="content">
    <table class="table table-bordered table-striped table-hover">
     <tr>
     	<th></th>
        <th><span>流程定义id</span></th>
        <th><span>流程名称</span> </th>
        <th><span>key</span> </th>
        <th><span>版本号</span> </th>
    </tr>
    </table>
    <div style="text-align: center;">
    	<input type="button" name="sure" value="确定"/>
   	 	<input type="button" name="close" value="关闭"/>
    </div>
</div>

<script type="text/javascript">
$(function(){
	$.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath }/formtemplate/showProcessDefinitionList",
        dataType: "json",
        async:true , // false为同步
        success: function(data) {
        	if(data.status != '1'){
        		alert(data.message);
        	}else{
        		var dataJson = data.list;
            	//获取模板上的HTML  
                var html = $('script[type="text/template"]').html(); 
                //定义一个数组，用来接收格式化合的数据  
                var arr = [];  
                //对数据进行遍历  
                $.each(dataJson, function(i, o) {  
                    arr.push(formatTemplate(o, html));  
                });  
                $('.content table').find("tr:last").after(arr.join(''));
        		
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
	
	$("input[name=close]").click(function(){
		
		window.parent.document.getElementById("cxcc").setAttribute("style", "display: none;"); 
	})
	$("input[name=sure]").click(function(){
		var processDefinitionId = "";
		var processKey = "";
		var processName = "";
		$("input[name=processDefinition]").each(function(){
			if($(this).is(":checked")){
				processDefinitionId = $(this).val();
				processName = $(this).attr("processName");
				processKey = $(this).attr("processKey");
				return;
			}
			
		});
		window.parent.intermediary(processDefinitionId, processName, processKey);
		window.parent.document.getElementById("cxcc").setAttribute("style", "display: none;"); 
		
	})
})


</script>
<script type="text/template">

	<tr>
        <td><input name="processDefinition" type="radio" processName="{name}" processKey="{key}" value="{id}" ></td>
        <td><span>{id}</span></td>
        <td><span>{name}</span></td>
        <td><span>{key}</span></td>
        <td><span>{version}</span></td>
    </tr>

</script>
</body>
</html>