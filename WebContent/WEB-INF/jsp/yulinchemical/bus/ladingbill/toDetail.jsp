<%@page import="com.dfhc.bus.orderproductdetail.IOrderProductDetailConstants" %>
<%@page import="com.dfhc.bus.order.IOrderConstants" %>

<%@page import="org.quickbundle.tools.helper.RmJspHelper" %>
<%@page import="org.quickbundle.project.RmGlobalReference" %>
<%@page import="com.dfhc.ISystemConstant" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
	   <!-- 全局js 和css包 -->
	   <%@ include file="/jsp/include/web/jqGrid/global.jsp" %>
		<meta charset="utf-8" />
		<title><bean:message key="qb.web_title"/></title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
.appeal-loading-mask{
        position:fixed;
        left:0;
        top:0;
        width:100%;
        height:100%;
        z-index:20000;
        background-color:#808080;
		filter: alpha(opacity=80);
		opacity:0.8;
		-moz-opacity:0.8;  
		display:none;
    }
.appeal-loading{
	position:absolute;
	left:10%;
	top:1%;
	bottom:1%;
	padding:2px;
	z-index:20001;
	height:auto;
	display:none;
	
}
.appeal-loading .appeal-loading-indicator{
	background:white;
	color:#444;
	font:bold 20px tahoma,arial,helvetica;
  
	margin:0;
	height:auto;
	min-width:900px;
	min-height:600px;
	height:auto;
	width:95%;
	
}

.closebtn {
    position: absolute;
    width: 15px;
    height: 15px;
    background: transparent url(../../../../../images/icon/delete.png) no-repeat scroll 50% 50%;
	right: 5px;
    top: 5px;
	display:none;
   }
 
.appeal-main-div{height:40px;line-height:40px;border-bottom:1px solid #B9BBB5;background-color:#EFEFEF; }
.appeal-main-div span{margin-left:1%;font: bold 400 16px/40px Microsoft Yahei,微软雅黑;}
.img-div{float:left;width:100%; margin-top:10px; text-aglin:center;margin-left: 10%;}  
.img-div-show{float:left;width:400px;border:1px solid #E7E7E7;min-width: 300px;height: 300px;border-radius: 5px;padding: 0px;overflow: hidden;}  
.img-span{position: relative;  float: left; display: block;width: auto; height: auto;"}
.mouseover{
    position: absolute;
    z-index: 9999999999;
    top:100px;
    left: 100px;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/getDriverInfo.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<script type="text/javascript">
function ajaxPicUpload(){
	$.ajaxFileUpload({ 
	      //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)  
	     url: '<%=request.getContextPath()%>/pubController/picUpload?id=${bean.orderId}&billType=01',  
	     secureuri:false,                       //是否启用安全提交,默认为false   
	     fileElementId:'appealPic',           //文件选择框的id属性  
	     dataType:'json',                       //服务器返回的格式,可以是json或xml等  
	     success:function(data){        //服务器响应成功时的处理函数
	       	if(!data.message || data.message.length == 0){
	       		var length = $("#showImg").children("span").length;
	       		if(length == 0){
	        		$("#showImg").html("<span  class='img-span'><img  src='<%=request.getContextPath()%>/pubController/showImg?relaPath=" + data.path + "'></img> <a href='javascript:void(0)' fileName='" +  data.fileName + "' class='closebtn'  onclick='doDelete(this)'></a></span>");
	        		$("#hidInp").html("<input type='hidden' name='path'  fileName='" +  data.fileName + "'  value='" + data.path + "'/>");
	        	}
	        	if(length>0 && length <9){
	        		$("#showImg").find("span:last").after("<span  class='img-span'><img src='<%=request.getContextPath()%>/pubController/showImg?relaPath=" + data.path + "'></img> <a href='javascript:void(0)'  fileName='" +  data.fileName + "'   class='closebtn'  onclick='doDelete(this)'></a></span>");
	        		$("#hidInp").find("input:last").after("<input type='hidden'  fileName='" +  data.fileName + "'  name='path' value='" + data.path + "'/>");
	        	}
	        	var showImgLength = $("#imgDiv").find("a");
	        	if(showImgLength.length == 0){ 
	        		$("#imgDiv").append('<a target="_blank" fileName="'+data.fileName+'"  href="<%=request.getContextPath()%>/pubController/showImg?relaPath='+data.path+'"><img   style="width: 200px;height: 200px;" src="<%=request.getContextPath()%>/pubController/showImg?relaPath=' + data.path + '"/></a>');
	        	}else{
	        		$("#imgDiv").find("a:last").after('<a target="_blank" fileName="' + data.fileName + '" href="<%=request.getContextPath()%>/pubController/showImg?relaPath='+data.path+'"><img   style="width: 200px;height: 200px;" src="<%=request.getContextPath()%>/pubController/showImg?relaPath=' + data.path + '"/></a>');
	        	}
	        	
	       		//上传成功 调用 九宫格
	        	uploadImg("showImg");
	       		
	       	}else{
	       		alert(data.message);
	       	}
	       		
	     } ,
	      error:function(xhr,s1,s2){
   			//去掉提示层
   			$("#id-ts").attr("style","display:none;");

	    	var message = xhr.responseText;
			eval("var json = "+message);
	    }  
	 });
}  
	

function uploadImg(imgbox){
	   
    //获取span标签
    var img_up=$("#showImg").children("span");
	var num_p=img_up.length;
	  
	if(num_p==1){
		$("#"+imgbox+" img").css("width","380px");
		$("#"+imgbox+" img").css("height","300px");
		$("#"+imgbox+" img").css("marginLeft","0");
		$("#"+imgbox+" img").css("marginBottom","0");
		$("#"+imgbox).css("background","#fff");
		$("#"+imgbox).css("boxShadow","");
	}

else if(num_p>1&&num_p<=4){
	
	$("#"+imgbox+" img").css("width","190px");
	$("#"+imgbox+" img").css("height","150px");
	$("#"+imgbox+" img").css("marginBottom","4px");
	$("#"+imgbox+" img").css("marginRight","4px");	   
}
else if(num_p>4){

	$("#"+imgbox+" img").css("width","120px");
	$("#"+imgbox+" img").css("height","95px");
 $("#"+imgbox+" img").css("marginBottom","4px");
	$("#"+imgbox+" img").css("marginRight","4px");
	}
	else{

	$("#"+imgbox+" img").css("boxShadow","0 0 2px 0 rgba(0, 0, 0, 0.2)");
	}

	  
	  
	  //显示图片上的删除按钮
	   img_up.mouseenter(function(){
		$(this).children("a").css("display","block");
	   });
	   img_up.mouseleave(function(){
		$(this).children("a").css("display","none");
	   });
 
 } 


//删除数据
function _delete(urlData){
      //var pointer=$(".cbox:checked").parents("tr");
      
      var  ids="";
      var len = $(".cbox:checked").length;
      var count=1;
      $(".cbox:checked").each(function(){
      	
      	var dataId =$(this).parents("tr").attr("id");
      	ids+=dataId
      	if(len!=count){
      		ids+=","
      	}
      	count++;
      	
      });
  
      deleteForm.action=urlData+"&ids="+ids;
      deleteForm.submit();		
     
	 
}
		     
		     
//查询操作
function  searchShi(){
	
	//计划发运量
	var plannedVolume = $("#form").find("input[name=plannedVolume]").val();
	if(plannedVolume.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (!regu.test(plannedVolume)) {
	    	alert("请重新输入计划发运量(大于0,最多四位小数)!");
			return false;
	    }
	}
	//单价
	var unitPrice = $("#form").find("input[name=unitPrice]").val();
	if(unitPrice.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (!regu.test(unitPrice)) {
	    	alert("请重新输入金额(大于0,最多四位小数)!");
			return false;
	    }
	}
	//车数
	var carNum = $("#form").find("input[name=carNum]").val();
	if(carNum.length>0){
		var reg = /^[1-9]\d*$/;
		if(!reg.test(carNum)){
			 alert("请重新输入车数量(必须为整数)!");
	         return false;
		}
	}
	//剩余发运量
	var remarkVolume = $("#form").find("input[name=remarkVolume]").val();
	if(remarkVolume.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (!regu.test(remarkVolume)) {
	    	alert("请重新输入剩余发运量(大于0,最多四位小数)!");
			return false;
	    }
	}
	//剩余车数
	var remainderCarNum = $("#form").find("input[name=remainderCarNum]").val();
	if(remainderCarNum.length>0){
		var reg = /^[1-9]\d*$/;
		if(!reg.test(remainderCarNum)){
			 alert("请重新输入剩余车数量(必须为整数)!");
	         return false;
		}
	}
	
	jQuery("#grid-table").jqGrid("setGridParam",
	{
	       datatype:"json",
	       postData:$(form).serialize(), //发送数据  
	       page:"1"
	}).trigger("reloadGrid");
		 
	   
	$(".ui_inner_tan").hide();
   	$(".ui_out_tan").hide();	 
}

function deleteLadingBill(){
	 var pointer=$(".cbox:checked").parents("tr");
     var updateDate=$(pointer).attr("id");
 	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
 	var id = rowData.id;
 	$(".ui_out_tan").hide();
    $("#portlet-config-delete").hide();
 	if(id.length > 19){
 		jqData.remove(id);
 		$("#grid-table").jqGrid('delRowData', rowData.id);
 		var $_assignedCarNumBer = $("input[name=assignedCarNumBer]");
    	$_assignedCarNumBer.val(Number($_assignedCarNumBer.val()) - 1);
 	}else if(id.length == 19){
 		$.ajax({
             type: "POST",
             url: "<%=request.getContextPath()%>/ladingbill/deleteLadingBill?date="+new Date(),
             data:{"orderDetailId":orderDetailId, "ladingBillId":id},		 
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
         	   		 alert(data.message);
         	   		 jqData.remove(id);
         	   		$("#grid-table").jqGrid('delRowData', rowData.id);
	         	   	var $_assignedCarNumBer = $("input[name=assignedCarNumBer]");
	            	$_assignedCarNumBer.val(Number($_assignedCarNumBer.val()) - 1);
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
 	
}
function closeAddDiv(){
	$(".appeal-loading-mask").hide();
	$(".appeal-loading").hide();
}
function openUploadImg(){
	$(".appeal-loading-mask").show();
	$(".appeal-loading").show();
}

function   doDelete(obj){
		debugger;
	var $_this = $(obj);
	var srcStr = $_this.prev().attr("src");
	var imgPath = srcStr.substring(srcStr.indexOf("=")+1);

	$.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/pubController/deleteImg",
        data:{"imgPath":imgPath, 'id':'${bean.orderId}','billType':'01'},		 
        dataType: "json",
        async:false , //false为同步
        success: function(data,status) {
	   	   if(data.status=="0"){
	   	       alert(data.message);
	   	   }else{
    		$_this.parents(".img-span").remove();
    		var fileName = $_this.attr("fileName");
	   		   
    		$("#hidInp").find("input[name=path]").each(function(){
    			if($(this).attr("fileName") == fileName){
    				$(this).remove();
    				return;
    			}
    		});
    		$("#imgDiv").find("img").each(function(){
    			if($(this).attr("fileName") == fileName){
    				$(this).remove();
    				return;
    			}
    		});
    		alert(data.message);
    		
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
</head>
<body>
	
	
 <!--begin  新增 -->
	
  <div class="ui-widget-overlay ui_out_tan" ></div>

  <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="add_form" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
  <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">新增</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="insertForm" id="insertForm" class="FormGrid ui_inner_form" >   
   <div class="control-group">
			 <div class="controls">
							

							<div class="input-prepend">
							  <span class="add-on">司机姓名</span>
							  <input class="m-wrap" name="driverName"  type="text"  		maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on">车牌号</span>
							  <input class="m-wrap" name="licensePlate"  type="text"  						maxLength="13" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on">手机号</span>
							  <input class="m-wrap" name="phoneNumber"  type="text"  onchange="getDriverInfo(this, 'driverName', 'licensePlate', 'driverIdCardNumber');"							maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on">司机身份证</span>
							  <input class="m-wrap" name="driverIdCardNumber"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">产品型号</span>
							  <input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">核载量</span>
							  <input class="m-wrap" name="nuclearLoad"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">车型</span>
							  <input class="m-wrap" name="carType"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							 
							  <input name="isBringBelt"  type="checkbox"  						maxLength="200" />
							   <span class="span-check-radio" style="font-size:14px;">是否带托盘</span>
							
							  
							  <input   name="isLipolysis"  type="checkbox"  						maxLength="200" />
						      <span class="span-check-radio" >是否有溶脂要求</span>
						      
							  <input   name="isUrgent"  type="checkbox"  						maxLength="200" />
							  <span class="span-check-radio" >是否为加急</span>
							</div>
							<div class="input-prepend text-prepend">
							  <span class="add-on">备注</span>
							  <textarea  name="remark">${bean.remark}</textarea>
							</div>

							
							

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="saveLocalData('#insertForm');">
       <i class="icon-ok"></i>
               保存</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
	
	<!-- end 新增 -->
	
	<!-- begin 修改 -->
	
	 <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-edit" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">修改</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="updateForm" id="updateForm" class="FormGrid ui_inner_form" >
   
           <div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />

							<div class="input-prepend">
							  <span class="add-on">司机姓名</span>
							  <input class="m-wrap" name="driverName" value=""  type="text"  		maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on">车牌号</span>
							  <input class="m-wrap" name="licensePlate"  type="text"  						maxLength="13" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on">手机号</span>
							  <input class="m-wrap" name="phoneNumber"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on">司机身份证</span>
							  <input class="m-wrap" name="driverIdCardNumber"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">产品型号</span>
							  <input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">核载量</span>
							  <input class="m-wrap" name="nuclearLoad"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">车型</span>
							  <input class="m-wrap" name="carType"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							 
							  <input name="isBringBelt"  type="checkbox"  						maxLength="200" />
							   <span class="span-check-radio" style="font-size:14px;">是否带托盘</span>
							
							  
							  <input   name="isLipolysis"  type="checkbox"  						maxLength="200" />
						      <span class="span-check-radio" >是否有溶脂要求</span>
						      
							  <input   name="isUrgent"  type="checkbox"  						maxLength="200" />
							  <span class="span-check-radio" >是否为加急</span>
							</div>
							<div class="input-prepend text-prepend">
							  <span class="add-on">备注</span>
							  <textarea rows="200" cols="200" name="remark"></textarea>
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="updateLocalData();">
       <i class="icon-ok"></i>
            更新</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
	

    <!-- end  修改 -->	
    <!-- begin 查询 -->
    
     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">查询</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="form" id="form" class="FormGrid ui_inner_form" method="post" >
   
           <div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />
							<input type="hidden" class="m-wrap"  name="orderId" value="${bean.id }"/>

			<%--				<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("orderId")%></span>						<input type="text" class="m-wrap"  name="orderId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/order/reference?referenceInputType=radio','insertForm.orderId,insertForm.orderIdName');">选择</span>
							</div>
 --%>
							<div class="input-prepend">
							  <span class="add-on">司机姓名</span>
							  <input class="m-wrap" name="driverName"  type="text"  		maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on">车牌号</span>
							  <input class="m-wrap" name="licensePlate"  type="text"  						maxLength="13" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on">手机号</span>
							  <input class="m-wrap" name="phoneNumber"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on">司机身份证</span>
							  <input class="m-wrap" name="driverIdCardNumber"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">产品型号</span>
							  <input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">核载量</span>
							  <input class="m-wrap" name="nuclearLoad"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							  <span class="add-on">车型</span>
							  <input class="m-wrap" name="carType"  type="text"  						maxLength="200" />
							</div>
							<div class="input-prepend">
							 
							  <input name="isBringBelt"  type="checkbox"  						maxLength="200" />
							   <span class="span-check-radio" style="font-size:14px;">是否带托盘</span>
							
							  
							  <input   name="isLipolysis"  type="checkbox"  						maxLength="200" />
						      <span class="span-check-radio" >是否有溶脂要求</span>
						      
							  <input   name="isUrgent"  type="checkbox"  						maxLength="200" />
							  <span class="span-check-radio" >是否为加急</span>
							</div>
							<div class="input-prepend text-prepend">
							  <span class="add-on textarea_span">备注</span>
							  <textarea rows="200" class="m-wrap" cols="200" name="remark"></textarea>
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi();">
       <i class="icon-search"></i>
           查询</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div>
     
     </div>
     
    
    
    <!-- end 查询 -->

    <!-- begin 删除 -->
      <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-delete" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">删除选中的数据</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="deleteForm" id="deleteForm" class="FormGrid ui_inner_form"  method="post"  >
   
           <div class="control-group">
				<span>确认删除选中的数据？</span>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="deleteLadingBill();">
       <i class="icon-trash"></i>
           确认删除</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
     
    <!-- end 删除 -->
	
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	
		
	   <!-- end 头部 -->

		<div class="main-container" id="main-container">
			<script type="text/javascript">
                      	     try{ ace.settings.check('main-container' , 'fixed')
                             } catch(e){
                             }
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				
				
                <!-- begin 左侧菜单 -->
			   
			   <%@ include file="/jsp/include/web/jqGrid/leftSide.jsp"%>
			   
			   

                 <!-- end 左侧菜单 -->
                 
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{
							  ace.settings.check('breadcrumbs' , 'fixed')
							}catch(e){
                                                           }
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<c:choose>
								<c:when test="${flag == '01' }">
									<li>
										<a href="#">客户协同</a>
									</li>
									<li class="active">客户提货单办理</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="#">提货单管理</a>
									</li>
									<li class="active">副产品提货单关联</li>
								</c:otherwise>
							</c:choose>
								
						</ul><!-- .breadcrumb -->

			<!--			<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div> #nav-search -->
					</div>

					<div class="page-content" style="border-bottom: none;">
						<!-- <div class="page-header">
							<h1>
								销售订单产品明细
								<small>
									<i class="icon-double-angle-right"></i>
									数据列表展示
								</small>
							</h1>
						</div> page-header -->

 <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 <form	name="zongForm" id="zongForm">
								<input type="hidden" name="jqData"/>

		                     <div class="widget-header">
		                      
		                      <h5>销售订单详情</h5>
		                      <div class="widget-toolbar">
		                      
		         <%--               <a href="javascript:void(0)" title="审核通过">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                         
		                         <a href="javascript:void(0)" title="审核驳回" >
		                           
		                           <i class="icon-ban-circle"></i>
		                         
		                         </a>
		                  --%>         
		                         <a href="javascript:history.go(-1);" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                         <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
			                         <tbody>
			                         
			                      <tr>
		                           
			                             <th><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%>:</th>
			                             <td><c:out value="${bean.orderNum}"></c:out>   </td>
			                             <th>客户名称:</th>
			                             <td>
										<c:out value="${bean.customerName}"></c:out>
			                              </td>
			                             <th>产品名称:</th>
			                             <td><c:out value="${bean.productName}"></c:out>   </td>
			                          
			                          </tr>
			                           

			                          <tr>
		                           
			                             <th>发运方式:</th>
			                             <td><c:out value="${bean.shipMode}"></c:out>   </td>
			                             <th>计划发运量:</th>
			                             <td><c:out value="${bean.plannedVolume}"></c:out>  </td>
			                             <th>车数:</th>
			                             <td  id="carNum"><c:out value="${bean.carNum}"></c:out>  </td>
			                          
			                          </tr>

			                          <tr>
		                           
			                             <th>已分配车数:</th>
			                             <td><input type="text" class="tab-inp"  readonly="readonly"  name="assignedCarNumBer" value="<c:out value="${bean.assignedCarNumBer == null ? 0 : bean.assignedCarNumBer}"></c:out>"/>
			                             <th>计划提货日期:</th>
			                             <td>
			                             	<fmt:formatDate value="${bean.deliveryStarDate}" pattern="yyyy-MM-dd"/>
			                             </td>
			                          
			                          	<th>有效期至:</th>
			                             <td><fmt:formatDate value="${bean.deliveryEndDate}" pattern="yyyy-MM-dd"/> </td>
			                          </tr>


			                          <tr>
		                           
			                             
			                             <th>备注:</th>
			                             <td colspan="5"><c:out value="${bean.remark}"></c:out>   </td>
			                          
			                          </tr>

			                          
			                           
			                           
			                           
			                         </tbody>
		                      
		                         
		                         </table>
		                   
		                   <div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
		                   
		                   
		                       
		                       </div>
		                     <div class="opt-div">
		                         <c:choose>
									<c:when test="${flag == '01' }">
										<a href="javascript:void(0);" class="save-btn-shi" id="save" onclick="javascript:doSaveOrCommit('save')">保存</a>
										<a href="javascript:void(0);" class="sub-btn-shi"  id="commit"   onclick="javascript:doSaveOrCommit('commit')" > 提交</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0);" class="sub-btn-shi"  id="commit"   onclick="javascript:doSaveOrCommit('commit')" > 保存</a>
		                          <a href="javascript:void(0);" class="sub-btn-shi"  id="audit"   onclick="javascript:doSaveOrCommit('audit')" > 审核通过</a>
									</c:otherwise>	
								 </c:choose>
<!-- 		                          <a href="javascript:void(0);" class="save-btn-shi" id="save" onclick="javascript:doSaveOrCommit('save')">保存</a> -->
		                          
		                         
		                         </div>
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 </form>
		                
		                 </div>
		
		                   
 						<!-- end 详情 -->
						<div style="margin-top: 20px;">
            <c:choose>
            	<c:when test="${operation=='coordination'}">
		                 	<!-- 上传文件存放位置 -->
		                 	<a href="javascript:void(0);" class="sub-btn-shi"  id="audit"   onclick="openUploadImg();" >上传提货委托书</a>
            	</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>    
		                 	<div id="imgDiv" style="width: 1000px;height: 300px;margin-top: 10px;">
		                 		
		                 		<c:forEach items="${attacmentList}"  var="bean"> 
		                 			<a href="<%=request.getContextPath()%>/pubController/showImg?relaPath=${bean}" target="_blank>"  ><img style="width: 200px;height: 200px;" src="<%=request.getContextPath()%>/pubController/showImg?relaPath=${bean}"/></a>
		                 		</c:forEach>
		                 		
		                 	</div>
		                 </div>
  						
						
					</div><!-- /.page-content -->
					
					
				</div><!-- /.main-content -->

						

			</div><!-- /.main-container-inner -->
			

		
		</div><!-- /.main-container -->
	<!-- 上传文件开始  -->
<div  id="add-appeal" class="appeal-loading-mask"></div>
<div  id="add-appeal-loading" class="appeal-loading">
    <div class="appeal-loading-indicator">
      
      <div style="width:100%;">
     
       <div class="appeal-main-div">
        <span >上传图片</span>
        <div style="float:right;"><img src="${pageContext.request.contextPath }/images/icon/close.png" onClick="closeAddDiv()"></img></div>
       </div>
       <!--form action="${pageContext.request.contextPath }/appeal/saveAppealInfo.action" method="post" name="form"-->
             <span id="instructions" style="font:11px/25px Microsoft Yahei,微软雅黑;color:#FF5100;position: relative;left: 500px;top: 10px;display: none;">*申诉材料说明不能为空</span>
       <div style="margin-top:20px;margin-left:5%;">
           <div id="hidInp">
           	
           </div>
            
            <!--图片上传 <img src="../img/icons/upload.png"></img>-->
            <div class="img-div">
                <!--begain :图片展示-->
                <div id="showImg" class="img-div-show" >
                    
                 </div>
                <!--end :图片展示-->  
                <!--上传按钮-->
                 <div style="float:left; " title="上传资料" >
                   <input title="上传资料"  type="button" style="background:url(${pageContext.request.contextPath }/images/icon/upload.png) center no-repeat;border:none;width:200px;height:200px;" id="uploadBtn"></input>
                  
                 </div>
                 
                 <div style=" float: left;  width: 100px; margin-top: 80px;margin-left: -130px;height: 100px;opacity: 0;">
                   <input type="file" style="line-height:60px;height:60px;width:60px;" id="appealPic" name="appealPic" onchange="ajaxPicUpload();"></input>
                 </div>
             
                 <!--end 上传按钮-->
            
            </div>
                <div style="float: left;margin-left: 55%;margin-top: -50px;">
                <span style="font:11px/25px Microsoft Yahei,微软雅黑;color:#FF5100;"></span>
                <span style="font:11px/25px Microsoft Yahei,微软雅黑;color:red;display: block;" id="prompt"></span>
                </div>
            <!--end图片上传-->
            <div  class="pingjia-btn" style="float:left;margin-left:30%">
<!--             <input type="button" value="提交" onclick="closeBox();" class="btn"></input> -->
            
            </div>
           </div> 
       <!--/form-->
       </div>
      
  
        <!-- end-->
    </div>
      
       
    </div>		 
	<!-- 上传文件结束  -->	 
		<script type="text/javascript">
			//var grid_data = ${grid_data};	
			var orderDetailId = '${bean.id}';
			var carNum = '${bean.carNum}';
			var productId = '${bean.productId}';
			var statusConvert = [];
			
			 <c:forEach items="${ladingBillStatusMap}" var="map"> 
			     var keyL ='${map.key}';
			     statusConvert[keyL]='${map.value}';
			 </c:forEach>
			 <c:forEach items="${yesNot}" var="map"> 
			     var keyL ='${map.key}';
			     statusConvert[keyL]='${map.value}';
			 </c:forEach>
			
			
			var jqData = [];
				<c:forEach items="${beans}" var="bean"> 
				var dataObj1=new Object();
				dataObj1.id= '${bean.id}';
				dataObj1.productId= '${bean.productId}';
				dataObj1.licensePlate= '${bean.licensePlate}';
				dataObj1.driverName= '${bean.driverName}';
				dataObj1.phoneNumber= '${bean.phoneNumber}';
				dataObj1.driverIdCardNumber= '${bean.driverIdCardNumber}';
				dataObj1.productModelNumber= '${bean.productModelNumber}';
				dataObj1.nuclearLoad= '${bean.nuclearLoad}';
				dataObj1.carType= '${bean.carType}';
				dataObj1.remark= '${bean.remark}';
				dataObj1.status= '${bean.ladingBillStatus}';
				dataObj1.ladingBillStatus= '${bean.ladingBillStatus}';
				dataObj1.isBringBelt= ('${bean.isBringBelt}'.length==0)?'0':'${bean.isBringBelt}';
				dataObj1.isLipolysis= ('${bean.isLipolysis}'.length==0)?'0':'${bean.isLipolysis}';
				dataObj1.isSince= ('${bean.isSince}'.length==0)?'0':'${bean.isSince}';
				dataObj1.isUrgent= ('${bean.isUrgent}'.length==0)?'0':'${bean.isUrgent}';
				//添加一个随机码 用来后面 区分是那一条数据
				jqData.push(dataObj1);
			   </c:forEach> 	
				
				
			
			
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";

				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					data:jqData,
					datatype: "local",
				    mtype: 'POST',  
					height: "100%",
					colNames:['id','productId','status',
'车牌号','司机姓名','手机号','身份证号', '产品型号', '核载量','车型','状态','备注'
],
					colModel:[
{name:'id',index:'id',hidedlg:true,hidden:true, width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
{name:'productId',index:'productId',hidedlg:true,hidden:true, width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
{name:'status',index:'status',hidedlg:true,hidden:true, width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
{name:'licensePlate',index:'licensePlate', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
{name:'driverName',index:'driverName', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
{name:'phoneNumber',index:'phoneNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
{name:'driverIdCardNumber',index:'driverIdCardNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'productModelNumber',index:'productModelNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'nuclearLoad',index:'nuclearLoad', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'carType',index:'carType', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'ladingBillStatus',index:'ladingBillStatus', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
//{name:'isBringBelt',index:'isBringBelt', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
//{name:'isLipolysis',index:'isLipolysis', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
//{name:'isSince',index:'isSince', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
//{name:'isUrgent',index:'isUrgent', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
					], 
					// shrinkToFit:false,不随列数量改变列宽度
					viewrecords : true,
					rownumbers:true,
					rowNum:15,
					rowList:[15,30],
					pager : pager_selector,
					sortname : 'id',//初始化的时候排序的字段
					sortorder : "desc",//排序方式,可选desc,asc
					altRows: true,
					//toppager: true,
					
					multiselect: true,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
			
					editurl: getRootPath()+'/orderproductdetail/update',//nothing is saved
					caption: "提货单列表",
					autowidth: true
			
				});
				
				
				
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
			
				//enable datepicker
				function pickDate( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=text]')
								.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
					}, 0);
				}
			
			
				//navButtons
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	//navbar options
						edit: false,
						editicon : 'icon-pencil blue',
						add: false,
						addicon : 'icon-plus-sign purple',
						del: false,
						delicon : 'icon-trash red',
						search: false,
						searchicon : 'icon-search orange',
						refresh: false,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey',
					
					}
				)
				
				
				//新增
 			   jQuery(grid_selector) .navButtonAdd(pager_selector,
 					{ caption:"", title:"新增", buttonicon:"icon-plus-sign purple",
 				      onClickButton: addShow, position:"last" }) ;
			
				//修改
 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
 					{ caption:"", title:"修改", buttonicon:"icon-pencil blue",
 				      onClickButton:updateShow, 
 				      position:"last" }) ;
 				//删除
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", title:"删除", buttonicon:"icon-trash red",
				      onClickButton:deleteShow , 
				      position:"last" }) ;
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", title:"查询",buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
			     //新增弹出
			     
				 function addShow(){
// 			    	 var trArr = $(grid_selector).find("tr");
// 			    	 if(trArr && (trArr.length-1) == Number(carNum)){
			    		 
// 			    		 alert("已分配完车辆！");
// 			    		 return false;
// 			    	 }

					 var carNum=$("#carNum").text();
					 var assignedCarNumBer=$("#zongForm").find("input[name=assignedCarNumBer]").val();
			    	 if(Number(assignedCarNumBer)>=Number(carNum)){
			    		 alert("已分配完车辆!请保存");
			    		 return false;
			    	 }

			    	 $(".ui_out_tan").show();
			    	 $("#add_form").show();
					 
				 }
			     
			     //  修改弹出
			     function updateShow(){
			    	var checkedLen= $(".cbox:checked").length;
			    	if(checkedLen==0){
			    		
			    		alert("请至少选择一条数据！");
			    		return false;
			    	}
                    if(checkedLen>1){
			    		
			    		alert("最多只能选择一条数据！");
			    		return false;
			    	}
                    var pointer=$(".cbox:checked").parents("tr");
                    var updateDate=$(pointer).attr("id");
			    	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
                    if(rowData.status != '<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00%>'){
                    	
                    	alert("该提货单不能修改！");
                    	return false;
                    }
    				
    				
    				
			    	$("#updateForm").find("input[name=id]").val(rowData.id);
			    	$("#updateForm").find("input[name=productId]").val(rowData.productId);
			    	$("#updateForm").find("input[name=licensePlate]").val(rowData.licensePlate);
			    	$("#updateForm").find("input[name=driverName]").val(rowData.driverName);
			    	$("#updateForm").find("input[name=phoneNumber]").val(rowData.phoneNumber);
			    	$("#updateForm").find("input[name=driverIdCardNumber]").val(rowData.driverIdCardNumber);
			    	$("#updateForm").find("input[name=productModelNumber]").val(rowData.productModelNumber);
			    	$("#updateForm").find("input[name=nuclearLoad]").val(rowData.nuclearLoad);
			    	$("#updateForm").find("input[name=carType]").val(rowData.carType);
			    	$("#updateForm").find("input[name=status]").val(rowData.status);
					if(rowData.isBringBelt=='是'){
						$("#updateForm").find("input[name=isBringBelt]").attr("checked","checked");
						
					}else{
						$("#updateForm").find("input[name=isBringBelt]").removeAttr("checked");
					}
					if(rowData.isLipolysis=='是'){
						$("#updateForm").find("input[name=isLipolysis]").attr("checked","checked");
						
					}else{
						$("#updateForm").find("input[name=isLipolysis]").removeAttr("checked");
					}
					if(rowData.isSince=='是'){
						$("#updateForm").find("input[name=isSince]").attr("checked","checked");
						
					}else{
						$("#updateForm").find("input[name=isSince]").removeAttr("checked");
					}
					if(rowData.isUrgent=='是'){
						$("#updateForm").find("input[name=isUrgent]").attr("checked","checked");
						
					}else{
						$("#updateForm").find("input[name=isUrgent]").removeAttr("checked");
					}
			    	$("#updateForm").find("textarea[name=remark]").val(rowData.remark);
			    	 $(".ui_out_tan").show();
                     $("#portlet-config-edit").show();
			    	 
			     }
			     
			     //删除弹出
			     function deleteShow (){
			    	 
			    	 var checkedLen= $(".cbox:checked").length;
				    	if(checkedLen==0){
				    		
				    		alert("请至少选择一条数据！");
				    		return false;
				    	}
				    	$(".ui_out_tan").show();
				        $("#portlet-config-delete").show(); 
				    	
			    	 
			     }
			     
			  
                //查询层
			     
			     function search_div(){
			    	 $(".ui_out_tan").show();
			    	 $("#portlet-config-query").show();
			     }
			     
			     
			 
			     
			     
			     
				//unlike navButtons icons, action icons in rows seem to be hard-coded
				//you can change them like this in here if you want
				function updateActionIcons(table) {
					/**
					var replacement = 
					{
						'ui-icon-pencil' : 'icon-pencil blue',
						'ui-icon-trash' : 'icon-trash red',
						'ui-icon-disk' : 'icon-ok green',
						'ui-icon-cancel' : 'icon-remove red'
					};
					$(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
					*/
				}
				
				//replace icons with FontAwesome icons like above
				function updatePagerIcons(table) {
					var replacement = 
					{
						'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
						'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
						'ui-icon-seek-next' : 'icon-angle-right bigger-140',
						'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
					};
					$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
				function createladingBill(){
					var selectedIdarr = $(grid_selector).jqGrid("getGridParam", "selarrrow");
					if(!selectedIdarr || selectedIdarr.length == 0){
						alert("请选择一条数据！");
						return false;
					}
					
					if(selectedIdarr.length > 1){
						alert("只能选择一条数据！");
						return false;
					}
					var rowData = $("#grid-table").jqGrid('getRowData',selectedIdarr[0]);
					var carNum = rowData.carNum;
					var productId = rowData.productId;
					
					if(0 == Number(carNum)){
						
						alert("该订单明细已生成提货单！");
						
						return false;
						
					}
					for(var i=0;i<carNum;i++){
						var dataObj1=new Object();
						dataObj1.productId= productId;
						dataObj1.licensePlate= '';
						dataObj1.driverName= '';
						dataObj1.phoneNumber= '';
						dataObj1.driverIdCardNumber= '';
						jqData.push(dataObj1);
					}
					jQuery(grid_selector).jqGrid("addRowData", '', jqData, "last");
					
				}
			
				function saveOrUpdateLadingBill(){
					/*var selectedIdarr = $(grid_selector2).jqGrid("getGridParam", "selarrrow");
					var obj = $(grid_selector2).jqGrid("getRowData");
					var driverIdCardNumber = obj[0].driverIdCardNumber;
					var driverName = obj[0].driverName;
					var licensePlate = obj[0].licensePlate;
					var phoneNumber = obj[0].phoneNumber;*/
					
					driverName.val();
					console.log();
					/*$.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/orderproductdetail/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                	   		   
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
                    });*/
				}
			});
			
			function saveLocalData(obj){
				//"driverName=&licensePlate=&phoneNumber=&
				//driverIdCardNumber=&driverIdCardNumber=&nuclearLoad=&carType=&remark="
				
				
				var $_form = $("#insertForm");
				if(!checkedForm($_form)){
					return false;
				}		
				
				var dataObj1=new Object();
				dataObj1.id= guid();
				setValue($_form, dataObj1);
				//添加一个随机码 用来后面 区分是那一条数据
				jqData.push(dataObj1);
				// $("#grid-table").jqGrid('setRowData', productId, dataObj);
				jQuery("#grid-table").jqGrid("addRowData", dataObj1.id, dataObj1, "last");
				$(".ui_out_tan").hide();
		    	$("#add_form").hide();
		    	
		    	
		    	clearForm($_form);
		    	var $_assignedCarNumBer = $("input[name=assignedCarNumBer]");
		    	$_assignedCarNumBer.val(Number($_assignedCarNumBer.val()) + 1);				
		    	
		    	
			}
			
			
			//验证输入的信息
			function checkedForm($_form){
				var flag = true,
					licensePlate = $_form.find("input[name=licensePlate]").val(),
					driverName= $_form.find("input[name=driverName]").val(),
					phoneNumber= $_form.find("input[name=phoneNumber]").val(),
					driverIdCardNumber= $_form.find("input[name=driverIdCardNumber]").val(),
					nuclearLoad= $_form.find("input[name=nuclearLoad]").val();
					//carType= $_form.find("input[name=carType]").val();
				
				if(!driverIdCardNumber || driverIdCardNumber.length==0){
					alert("请输入司机身份证号！");
					flag = false;
					return false;
				}else{
					if(!isCardNo(driverIdCardNumber)){
						alert("身份证号输入错误！");
						flag = false;
						return false;
					}	
				}
					
				if(!licensePlate || licensePlate.length == 0){
					alert("请输入车牌号！");
					flag = false;
					return false;
				}else{
					if(!regCarNum(licensePlate)){
						alert("车牌号输入错误！");
						flag = false;
						return false;
					}
				}	
				
				
				if(!driverName || driverName.length == 0){
					alert("请输入司机姓名！");
					flag = false;
					return false;
				}else{
					if(!regName(driverName)){
						alert("司机姓名输入错误！");
						flag = false;
						return false;
					}
				}
				
				if(!phoneNumber || phoneNumber.length == 0){
					alert("请输入司机电话！");
					flag = false;
					return false;
				}else{
					if(!regPhoneNumber(phoneNumber)){
						alert("司机电话输入错误！");
						flag = false;
						return false;
					}	
				}
				
				if(!nuclearLoad || nuclearLoad.length == 0){
					alert("请输入车辆核载量！");
					flag = false;
					return false;
				}else{
					if(!regNumber(nuclearLoad)){
						alert("车辆核载量输入错误！");
						flag = false;
						return false;
					}
				}
				return flag;
				
			}
			
			function setValue($_form, dataObj1){
				dataObj1.productId= productId;
				dataObj1.licensePlate= $_form.find("input[name=licensePlate]").val();
				dataObj1.driverName= $_form.find("input[name=driverName]").val();
				dataObj1.phoneNumber= $_form.find("input[name=phoneNumber]").val();
				dataObj1.driverIdCardNumber= $_form.find("input[name=driverIdCardNumber]").val();
				dataObj1.productModelNumber= $_form.find("input[name=productModelNumber]").val();
				dataObj1.nuclearLoad= $_form.find("input[name=nuclearLoad]").val();
				dataObj1.carType= $_form.find("input[name=carType]").val();
				dataObj1.ladingBillStatus= '<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00%>';
				dataObj1.status= '<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00%>';
				
				if($_form.find("input[name=isBringBelt]").is(":checked")){
					
					dataObj1.isBringBelt = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_1%>';  
				}else{
					dataObj1.isBringBelt = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
					
				}
				if($_form.find("input[name=isLipolysis]").is(":checked")){
					
					dataObj1.isLipolysis = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_1%>';  
				}else{
					dataObj1.isLipolysis = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
					
				}
				if($_form.find("input[name=isUrgent]").is(":checked")){
					
					dataObj1.isUrgent = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_1%>';  
				}else{
					dataObj1.isUrgent = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
					
				}
				  
				dataObj1.isSince = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
				dataObj1.remark = $_form.find("textarea[name=remark]").val();
			}
			
			function updateLocalData(){
				//"driverName=&licensePlate=&phoneNumber=&
				//driverIdCardNumber=&driverIdCardNumber=&nuclearLoad=&carType=&remark="
				var $_form = $("#updateForm");
				if(!checkedForm($_form)){
					return false;
				}		
				var dataObj1=new Object();
				dataObj1.id= $_form.find("input[name=id]").val();
		
				setValue($_form, dataObj1);
				//添加一个随机码 用来后面 区分是那一条数据
				//移除修改前的数据
				jqData.remove(dataObj1.id);
				jqData.push(dataObj1);
				$("#grid-table").jqGrid('setRowData', dataObj1.id, dataObj1);
				$(".ui_out_tan").hide();
                $("#portlet-config-edit").hide();
			}
			Array.prototype.indexOf = function(val) {
				for (var i = 0; i < this.length; i++) {
				if (this[i].id == val) return i;
				}
				return -1;
			};
			Array.prototype.remove = function(val) {
				var index = this.indexOf(val);
				if (index > -1) {
				this.splice(index, 1);
				}
			};
			function guid() {
			    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
			        return v.toString(16);
			    });
			}
			
			function doSaveOrCommit(flag){
				var jqDataStr=JSON.stringify(jqData); ;
				$("#zongForm").find("input[name='jqData']").val(jqDataStr);
				 $("#save").attr("disabled");
       			 $("#commit").attr("disabled");
       			 $("#audit").attr("disabled");
				$.ajax({
				    type:'post',//可选get
				    url:"${pageContext.request.contextPath}/orderproductdetail/orderDetailToLadingBillList?orderDetailId=" + orderDetailId + "&flag=" + flag + "&date=" + new Date(),
				    data:$(zongForm).serialize(),
				    dataType:'json',
				    async:false , //false为同步
				    success:function(data,status){
					  if(data.status=="0"){
		    	   	     //将按钮设置为可用
		       			 $("#save").removeAttr("disabled");
		       			 $("#audit").removeAttr("disabled");
		       			 $("#commit").removeAttr("disabled");
						  alert(data.message);
				   	   }else{
				   	     	alert(data.message);
		                    window.location.href="${pageContext.request.contextPath}/orderproductdetail/orderDateil?flag=${flag}&operation=${operation}";
				   	   }
				   },
				   error:function(xhr,s1,s2){
				    	var message = xhr.responseText;
						eval("var json = "+message);
		    	   	     //将按钮设置为可用
		       			 $("#save").removeAttr("disabled");
		       			 $("#commit").removeAttr("disabled");
						if($.trim(json.error).length==0){
							alert('数据异常,请刷新页面重试');
						}else{
							alert(json.error);
						}
				   }
			});
			}
		</script>
	</body>
</html>
