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


<script type="text/javascript">
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
							  <textarea  name="remark"></textarea>
							</div>

							
							

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="saveLocalData($(insertForm).serialize());">
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

    <!-- begin 作废 -->
      <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-delete" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">作废选中的提货单</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="deleteForm" id="deleteForm" class="FormGrid ui_inner_form"  method="post"  >
   
           <div class="control-group">
				<span>确认作废选中的提货单？</span>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="updateLadingBill('invaild');">
       <i class="icon-trash"></i>
           确认作废</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div>
     </div>
     <div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div>
     </div>
     
    <!-- end 删除 -->
	<div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-audit" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">审核通过</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="deleteForm" id="deleteForm" class="FormGrid ui_inner_form"  method="post"  >
   
           <div class="control-group">
				<span>确认审核通过选中的提货单？</span>
			 </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="updateLadingBill('audit');">
       <i class="icon-trash"></i>
           审核通过</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div>
     </div>
     <div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div>
     </div>
	
	
	
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

							<li>
								<a href="#">提货单管理</a>
							</li>
							<li class="active">副产品提货单关联</li>
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

					<div class="page-content">
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
		                      
		                      <h5>提货单详情</h5>
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
			                             <td><c:out value="${bean.orderPlanCode}"></c:out>   </td>
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
			                             <td> </td>
			                          
			                          </tr>

			                          <tr>
		                           
			                             <th>剩余发运量:</th>
			                             <td><c:out value="${bean.remarkVolume}"></c:out>   </td>
			                             <th>剩余车数:</th>
			                             <td><c:out value="${bean.remainderCarNum}"></c:out>   </td>
			                             <th>计划提货日期:</th>
			                             <td>
			                             	<fmt:formatDate value="${bean.deliveryStartDate}" pattern="yyyy-MM-dd"/>
			                             </td>
			                          
			                          </tr>


			                          <tr>
		                           
			                             <th>有效期至:</th>
			                             <td><fmt:formatDate value="${bean.deliveryEndDate}" pattern="yyyy-MM-dd"/> </td>
			                             <th>车牌号:</th>
			                          	 <td><c:out value="${bean.licensePlate}"></c:out></td>
			                             <th>司机姓名:</th>
			                          	 <td><c:out value="${bean.driverName}"></c:out></td>
			                          </tr>
			                          <tr>
		                           
			                             <th>司机手机号:</th>
			                             <td><c:out value="${bean.phoneNumber}"></c:out> </td>
			                             <th>身份证号:</th>
			                          	 <td><c:out value="${bean.driverIdCardNumber}"></c:out></td>
			                             
			                          </tr>
									  <tr>
									  
									  	<th>备注:</th>
			                             <td colspan="5"><c:out value="${bean.remark}"></c:out>   </td>
									  </tr>	
			                          
									  <tr>
									  
									  	<th>作废原因:</th>
			                             <td colspan="5">
			                             	<textarea name="invalidReason" rows="" cols="120"></textarea>
			                             </td>
									  </tr>	
			                          
			                           
			                           
			                           
			                         </tbody>
		                      
		                         
		                         </table>
		                   
		                   
		                   
		                       
		                       </div>
		                     <div class="opt-div">
		                         
		                          <a href="javascript:void(0);" class="save-btn-shi" id="save" onclick="javascript:invalidShow();">作废</a>
		                          <a href="javascript:void(0);" class="sub-btn-shi"  id="audit"   onclick="auditPassShow();" > 审核通过</a>
		                         
		                         </div>
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 </form>
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						


						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->
			

		
		</div><!-- /.main-container -->
<script type="text/javascript">
//删除弹出
function invalidShow (){
	var invalidReason = $("textarea[name=invalidReason]").val();
	if(!invalidReason || invalidReason.length == 0){
		
		alert("请输入作废原因！");
		return false;
	}
   	$(".ui_out_tan").show();
    $("#portlet-config-delete").show(); 
}
//审核通过
function auditPassShow (){
   	$(".ui_out_tan").show();
    $("#portlet-config-audit").show(); 
}
function updateLadingBill(flag){
	$.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/ladingbill/updateLadingBill?date=" + new Date(),
        data:{"operateFlag":flag, "ladingBillId":"${bean.id}", "invalidReason":$("textarea[name=invalidReason]").val()},
        dataType: "json",
        async:false , //false为同步
        success: function(data,status) {
	   	   if(data.status=="0"){
	   	       alert(data.message);
	   	    $(".ui_out_tan").hide();
	   	    $("#portlet-config-audit").hide(); 
	   	 $("#portlet-config-delete").hide(); 
	   	   }else{
	   		   alert(data.message);
	   		   window.location.reload();
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
	</body>
</html>
