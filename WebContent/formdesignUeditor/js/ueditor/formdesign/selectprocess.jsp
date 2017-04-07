<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>文本框</title>
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
<style type="text/css">
.popup {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 100;
}
.popup-back {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 101;
  background: #000;
  opacity: 0.3;
}
.popup-panel {
  position: relative;
  z-index: 1001;
  width: 800px;
  height: 500px;
  margin: 80px auto;
  background: #fff;
}
.tableHidBorder{
	
}
.tableHidBorder tr td{
	border: none;
}
</style>
</head>
<body>
<form action="" enctype="application/x-www-form-urlencoded"></form>
<div class="content">
    <table id="table1" class="table table-bordered table-striped table-hover">
     <tr>
        <th><span>选择流程</span><span class="label label-important">*</span></th>
    </tr>
    <tr>
        <td><input type="text"  name="processName"  placeholder="选择流程">
        <input type="hidden"  name="processId"  />
        	<input type="button" value="流程" name="selectProcess"/>
        </td>
    </tr>

    </table> 
    
    
</div>
<script type="text/javascript">
//赋值   
/**
 * id 流程id
 * name 流程name
 * key 流程key
 */
 var processId;
function voluation(id, name, key){
	$("input[name=processName]").val(name);
	$("input[name=processId]").val(id);
	processId = id.toString();
	//查询并展示数据项
	showBusinessGroup(id);
}
function showBusinessGroup(id){
	$.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath }/processdataitems/getBusinessGroupList",
        data:{"procDefId":id.toString()},
        dataType: "json",
        async:true , // false为同步
        success: function(data) {
        	if(data.status != '1'){
        		alert(data.message);
        	}else{
        		addSelectedDataTermHtml(data.list);
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

function addSelectedDataTermHtml(list){
	//获取模板上的HTML  
    var html = $('script[type="text/template"]').html(); 
    //定义一个数组，用来接收格式化合的数据  
    var arr = [];  
    arr.push('<td><select name="businessGroup"><option>选择业务组</option>');
    //对数据进行遍历  
    $.each(list, function(i, o) {
    	if(o.businessGroup != '999'){
	        arr.push(formatTemplate(o, html));  
    	}
    });  
    arr.push('</select></td>');
    
    var $_table = $("#table1");
	$_table.find("th").not("th:first").remove();
	$_table.find("tr:first").find("th:first").after('<th><span>选择业务组</span><span class="label label-important">*</span></th>');
	
	$_table.find("tr:last").find("td").not("td:first").remove();
	$_table.find("tr:last").find("td:last").after(arr.join(''));
	
}
$(function(){
	$("#table1").delegate("select[name=businessGroup]", "change", function(){
		var value = $(this).val();
		$.ajax({
	        type: "POST",
	        url: "${pageContext.request.contextPath }/processdataitems/getBusinessList",
	        data:{"procDefId":processId.toString(), "businessGroup":value.toString()},
	        dataType: "json",
	        async:true , // false为同步
	        success: function(data) {
	        	if(data.status != '1'){
	        		alert(data.message);
	        	}else{
	        		//处理页面
	        		showDataTerm(data.list);
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
	})
	
})
function showDataTerm(list){
	//获取模板上的HTML  
    var html2 = $('script[type="text/template2"]').html(); 
    var html3 = $('script[type="text/template3"]').html(); 
    //定义一个数组，用来接收格式化合的数据  
    var arr = [];  
    arr.push(html2);
    arr.push('<table id="table3" class="table table-bordered table-striped table-hover">');
    //对数据进行遍历  
    $.each(list, function(i, o) {
	     arr.push(formatTemplate(o, html3));  
    });  
    arr.push('</table>');
    var $_content = $(".content");
    
    $_content.find("table").not("table:first").remove();
    
    $_content.find("table:first").after(arr.join(''));
}
dialog.onok = function (){
    var html = getTemplateHtml();
	//情况内容
    //editor.execCommand('cleardoc');
	//插入内容
    editor.execCommand('insertHtml',html);
};

function getTemplateHtml(){
	var table = $("#table3");

	//拿到所有的tr数组  没两个tr是一个完整的数据项
	var trArr = table.find("tr");
	var index = 0;
	var isShowBorder = $("select[name=isShowBorder]").val();
	var tableHtml;
	var tableStyle = '';
	var trStyle = '';
	var tdStyle = '';
	if(isShowBorder != '02'){
		tableStyle = 'style="border-spacing: 0;border-collapse: collapse;"';
		tdStyle = 'style=" border: 1px solid #ccc;text-align: center;"';		
		trStyle = 'style=" background-color: #f1f1f1;"';		
	}
		tableHtml = '<table>';
	
	var count = 0;	
	while(index<trArr.length-1){
		var tr1;
		var tr2;
		if(index == 0){
			tr1 = trArr[index];
			index = index+1;
			tr2 = trArr[index];
		}else{
			index = index+2;
			tr1 = trArr[index-1];
			tr2 = trArr[index];
		}
		if((count%2) == 0){
			tableHtml += handleTableOfTr(tr1, tr2, tableStyle, trStyle, tdStyle);
			
		}else{
			tableHtml += handleTableOfTr(tr1, tr2, tableStyle, '', tdStyle);
			
		}
		count ++;
	}
	tableHtml += '</table>';
	return tableHtml;
}
//处理table的tr tr1是标签名称  tr2是显示内容
function handleTableOfTr(tr1, tr2, tableStyle, trStyle, tdStyle){
	
	//标签名称
	var thHtml = $(tr1).find("th:first").html();
	var tdHtml = $(tr2).find("td:first").html();
	
	//获取字段名称
	var reg = /<span>(.*?)<\/span>/;
	var arr = reg.exec(thHtml);
	reg = /name="(.*?)"/;
	var arr2 = reg.exec(tdHtml);
	//显示类型
	var tr2Type = $(tr2).find("td:last").find("select[name=" + arr2[1] + "types]").val();
	if(tr2Type == '01'){//文本框
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + tdHtml + '</td></tr>'
	}else if(tr2Type == '02'){//文本域
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + '<textarea leipiplugins="textarea" rows="" cols="" name="' + name.exec(tdContext)[1] + '"></textarea>' + '</td></tr>';
	}else if(tr2Type == '03'){
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<input type="hidden"  name="' + name.exec(tdContext)[1] + '"/>';
	}else if(tr2Type == '04'){
		return '';
	}else if(tr2Type == '05'){//单选框
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + '<input name="' + name.exec(tdContext)[1] + '" title="1" type="radio"  orgchecked="" leipiplugins="radio"/>' + '</td></tr>';
	}else if(tr2Type == '06'){//单选框组
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + '<span leipiplugins="radios" title="1" name="leipiNewField"><input name="' + name.exec(tdContext)[1] + '" value="" type="radio"/>&nbsp;</span>' + '</td></tr>';
	}else if(tr2Type == '07'){//复选框
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + ' <input name="' + name.exec(tdContext)[1] + '" title="" leipiplugins="checkbox" type="checkbox"/>' + '</td></tr>';
	}else if(tr2Type == '08'){//复选框组
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + '<span leipiplugins="checkboxs" title="2"><input name="' + name.exec(tdContext)[1] + '" value="" type="checkbox"/>&nbsp;</span>' + '</td></tr>';
	}else if(tr2Type == '09'){
		//td里面的内容
		var tdContext = $(tr2).find("td:first").html();
		var name = /<.*?name="(.*?)".*?>/;
		return '<tr ' + trStyle + '><td ' + tdStyle + '>' + arr[1] + '</td>' + '<td ' + tdStyle + '>' + '<input name="' + name.exec(tdContext)[1] + '" title="1" type="file" value=""  orgchecked="" leipiplugins="file"/>' + '</td></tr>';
	}
}



function getContextHtml(parse_form){
	var template = parse_form;
	var cssReg = /<link.*?>/;
	var jsReg = /<script.*?><\/script>|<script.*?\/>/;
	var cssHtml = parse_form.match(cssReg);
	var jsHtml = parse_form.match(jsReg);
	
	if(cssHtml){
		for(var i = 0; i < cssHtml.length; i++){
			template = template.replace(cssHtml[i], '');
		}
	}	
	if(jsHtml){
		for(var i = 0; i < jsHtml.length; i++){
			template = template.replace(jsHtml[i], '');
		}
	}
	return template;
}
$(function(){
	$("input[name=selectProcess]").click(function(){
		window.parent.document.getElementById("cxcc").removeAttribute("style"); 
	
	})
	
})
</script>
<script type="text/template">

<option value="{BUSINESSGROUP}">{DATAVALUE}</option>
</script>
<script type="text/template2">
<table id="table2" class="table table-bordered table-striped table-hover">
     <tr>
        <th><span>数据项展示</span></th>
        <th><span>是否去掉table边框</span>
        	<select name="isShowBorder">
        		<option value="01">否</option>
        		<option value="02">是</option>
        	</select>
        
        </th>
    </tr>
</table>
</script>
<script type="text/template3">

     <tr>
        <th><span>{dataItemCnName}</span></th>
        <th><span>选择类型</span></th>
     </tr>
	<tr>
        <td><input type="text" leipiplugins="text" name="{dataItemEnName}" value=""/></td>
		<td>
			<select name="{dataItemEnName}types">
    			<option value="01">文本框</option>
    			<option value="02">文本域</option>
    			<option value="03">隐藏</option>
				<option value="04">删除</option>
				<option value="09">上传</option>
    		</select>
		</td>
     </tr>
</script>

</body>
</html>