<%@page import="com.dfhc.bus.cardistributionplan.ICarDistributionPlanConstants" %>
<%@page import="com.dfhc.bus.ladingbill.ILadingBillConstants" %>
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

<script type="text/javascript" src="<%=request.getContextPath()%>/js/getDriverInfo.js"></script>
<script type="text/javascript">

//删除数据
function _delete(){
	 var pointer=$(".cbox:checked").parents("tr");
     var updateDate=$(pointer).attr("id");
 	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
 	var id = rowData.id;
 	$(".ui_out_tan").hide();
    $("#portlet-config-delete").hide();
 	if(id.length > 19){
 		deletelu();
 	}else if(id.length == 19){
 		$.ajax({
             type: "POST",
             url: "<%=request.getContextPath()%>/ladingbill/deleteLadingBills?date="+new Date(),
             data:{"ladingBillId":id},		 
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
         	   		 alert(data.message);
         	   		deletelu();
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
	
//删除数据
function deletelu(){
		var selectedId = $("#grid-table").jqGrid("getGridParam", "selarrrow");
		var copyNum=new Array();
		for(var i=0;i<selectedId.length;i++){
			copyNum.push(selectedId[i]);
		}
		for(var num=0;num<copyNum.length;num++){
			for(var i=0;i<jqData.length;i++){
				var vo=jqData[i];
				var productId=vo.id;
				if(productId==copyNum[num]){
					jqData.splice(i,1);
					break ;
				}
			}
			$("#grid-table").jqGrid('delRowData', copyNum[num]);
			
		}
		//回填剩余车数
		 var remainderCarNum=$("#remainderCarNum").text();
		$("#remainderCarNum").html(Number(remainderCarNum)+Number(copyNum.length));

}
		     
//查询操作
function  searchShi(){
	jQuery("#grid-table").jqGrid("setGridParam",
	{
	       datatype:"json",
	       postData:$(form).serialize(), //发送数据  
	       page:"1"
	}).trigger("reloadGrid");
		 
	   
	$(".ui_inner_tan").hide();
   	$(".ui_out_tan").hide();	 
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
							   <span class="span-check-radio" style="font-size:14px;">带托盘</span>
							
							  
							  <input   name="isLipolysis"  type="checkbox"  						maxLength="200" />
						      <span class="span-check-radio" >有溶脂要求</span>
						      
							  <input   name="isUrgent"  type="checkbox"  						maxLength="200" />
							  <span class="span-check-radio" >加急</span>
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
							  <input class="m-wrap" name="phoneNumber"  type="text"  					maxLength="19" />
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
							   <span class="span-check-radio" style="font-size:14px;">带托盘</span>
							
							  
							  <input   name="isLipolysis"  type="checkbox"  						maxLength="200" />
						      <span class="span-check-radio" >有溶脂要求</span>
						      
							  <input   name="isUrgent"  type="checkbox"  						maxLength="200" />
							  <span class="span-check-radio" >加急</span>
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
							   <span class="span-check-radio" style="font-size:14px;">带托盘</span>
							
							  
							  <input   name="isLipolysis"  type="checkbox"  						maxLength="200" />
						      <span class="span-check-radio" >有溶脂要求</span>
						      
							  <input   name="isUrgent"  type="checkbox"  						maxLength="200" />
							  <span class="span-check-radio" >加急</span>
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete();">
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

							<li>
								<a href="#">提货单管理</a>
							</li>
							<li class="active">主产品自提提货单详情</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">


 <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 <form	name="zongForm" id="zongForm">
								<input type="hidden" name="jqData"/>

		                     <div class="widget-header">
		                      
		                      <h5>主产品销售自提详情</h5>
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
			                        	<input  name="id"  value="${bean.id}" type="hidden" />
			                         
			                    <tr>
			                    
		                           
			                             <th><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%>:</th>
			                             <td><c:out value="${bean.planCode}"></c:out>   </td>
			                             <th>客户名称:</th>
			                             <td>
										<c:out value="${bean.customerName}"></c:out>
			                              </td>
			                             <th>货物名称:</th>
			                             <td><c:out value="${bean.goodsName}"></c:out>   </td>
			                          
			                          </tr>
			                           
			                    <tr>
			                    
		                           
			                             <th>制表日期:</th>
			                             <td><c:out value="${bean.makeDate}"></c:out>   </td>
			                             <th>接货人:</th>
			                             <td>
										<c:out value="${bean.receivingPerson}"></c:out>
			                              </td>
			                             <th>接货人电话:</th>
			                             <td><c:out value="${bean.receivingPersonPhone}"></c:out>   </td>
			                          
			                          </tr>

			                          <tr>
		                           
			                             <th>发运方式:</th>
			                             <td><c:out value="${bean.shipMode}"></c:out>   </td>
			                             <th>数量(吨):</th>
			                             <td><c:out value="${bean.num}"></c:out>  </td>
			                             <th>计划车数:</th>
			                             <td><c:out value="${bean.planCarNum}"></c:out>  </td>
			                          
			                          </tr>

			                          <tr>
		                           
			                             <th>剩余车数:</th>
			                             <td id="remainderCarNum"><c:out value="${bean.remainderCarNum}"></c:out>   </td>
			                             <th>发车日期:</th>
			                             <td>
			                             	<fmt:formatDate value="${bean.sendCarDate}" pattern="yyyy-MM-dd"/>
			                             </td>
			                          
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
		                         
		               <%--          <a href="javascript:void(0);" class="save-btn-shi" id="save" onclick="javascript:doSaveOrCommit('save')">保存</a>
		                    --%>        <a href="javascript:void(0);" class="sub-btn-shi"  id="commit"   onclick="javascript:doSaveOrCommit('commit')" >保存</a>
		                          <a href="javascript:void(0);" class="sub-btn-shi"  id="audit"   onclick="javascript:doSaveOrCommit('audit')" >审核通过</a>
		                         
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
			//var grid_data = ${grid_data};	
			var orderDetailId = '${bean.id}';
			var carNum = '${bean.planCarNum}';
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
				dataObj1.status= '${bean.ladingBillStatus}';
				dataObj1.ladingBillStatus= '${bean.ladingBillStatus}';
				dataObj1.remark= '${bean.remark}';
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
'车牌号','司机姓名','手机号','身份证号', '产品型号', '核载量','车型','状态','带托盘','有溶脂要求','自提','加急','备注'
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
{name:'isBringBelt',index:'isBringBelt', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
{name:'isLipolysis',index:'isLipolysis', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
{name:'isSince',index:'isSince', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
{name:'isUrgent',index:'isUrgent', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:convert},
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
 
				//作废
 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
 					{ caption:"", title:"作废", buttonicon:"icon-pencil blue",
 				      onClickButton:invalidShow, 
 				      position:"last" }) ;
				
				
				//查询
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", title:"查询",buttonicon:"icon-search orange",
// 				      onClickButton:search_div , 
// 				      position:"last" }) ;
				
				//删除
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"删除",buttonicon:"icon-trash red",
				      onClickButton:deleteShow , 
				      position:"last" }) ;
				
				     //作废
				     function invalidShow(){
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
							
					    	if(rowData.ladingBillStatus != convert('<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02%>','','')
		                    		&& rowData.ladingBillStatus != convert('<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03%>','','')){
		                    	alert("该提货单不能作废！");
		                    	return false;
		                    }
		    				
		             		$.ajax({
		                        type: "POST",
		                        url: "<%=request.getContextPath()%>/ladingbill/doInvalidLadingBills?date="+new Date(),
		                        data:{"ladingBillId":updateDate},		 
		                        dataType: "json",
		                        async:false , //false为同步
		                        success: function(data,status) {
		                	   	   if(data.status=="0"){
		                	   	       alert(data.message);
		                	   	   }else{
		                    	   		alert(data.message);
		               				   window.location.href="${pageContext.request.contextPath}/cardistributionplan/toUpdateZhuProductLift?id=${bean.id}&pcode=${pcode}";
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

			     //新增弹出
				 function addShow(){
// 			    	 var trArr = $(grid_selector).find("tr");
// 			    	 if(trArr && (trArr.length-1) == Number(carNum)){
			    		 
// 			    		 alert("已分配完车辆！");
// 			    		 return false;
// 			    	 }

					 var remainderCarNum=$("#remainderCarNum").text();
			    	 if(Number(remainderCarNum)==0){
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
			    	if(rowData.ladingBillStatus != convert('<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00%>','','')){
                    	
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
	                    if(checkedLen>1){
				    		
				    		alert("最多只能选择一条数据！");
				    		return false;
				    	}
	                    var pointer=$(".cbox:checked").parents("tr");
	                    var updateDate=$(pointer).attr("id");
				    	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
	                    if(rowData.status != '<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_00%>'){
	                    	
	                    	alert("该提货单不能删除！");
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
			
			
			});
			
			function saveLocalData(){
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
				//回填剩余车数
				var remainderCarNum=$("#remainderCarNum").text();
				$("#remainderCarNum").html(Number(remainderCarNum)-1);

				$(".ui_out_tan").hide();
		    	$("#add_form").hide();
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
				
				var remark = $_form.find("textarea[name=remark]").val();
				if($_form.find("input[name=isBringBelt]").is(":checked")){
					
					dataObj1.isBringBelt = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_1%>';  
					remark='带托盘、'+remark;
				}else{
					dataObj1.isBringBelt = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
					
				}
				if($_form.find("input[name=isLipolysis]").is(":checked")){
					
					dataObj1.isLipolysis = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_1%>';  
					remark='有溶脂要求、'+remark;
				}else{
					dataObj1.isLipolysis = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
					
				}
				if($_form.find("input[name=isUrgent]").is(":checked")){
					
					dataObj1.isUrgent = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_1%>';  
					remark='加急、'+remark;		
				}else{
					dataObj1.isUrgent = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
					
				}
				  
				dataObj1.isSince = '<%=ISystemConstant.DICTIONARY_RM_YES_NOT_0%>';  
				dataObj1.remark = remark;
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
			    if(Number(carNum)>0 && jqData.length==0){
			    	alert("请分配车辆");
					return false;
			    }

			    var jqDataStr=JSON.stringify(jqData); ;
				$("#zongForm").find("input[name='jqData']").val(jqDataStr);
				 $("#save").attr("disabled");
       			 $("#commit").attr("disabled");
       			 $("#audit").attr("disabled");
				$.ajax({
				    type:'post',//可选get
				    url:"${pageContext.request.contextPath}/cardistributionplan/doZhuToLadingBillList?flag=" + flag + "&date=" + new Date(),
				    data:$(zongForm).serialize(),
				    dataType:'json',
				    async:false , //false为同步
				    success:function(data,status){
					  if(data.status=="0"){
		    	   	     //将按钮设置为可用
		       			 $("#save").removeAttr("disabled");
		       			 $("#commit").removeAttr("disabled");
		       			 $("#audit").removeAttr("disabled");
						  alert(data.message);
				   	   }else{
				   	     	alert(data.message);
		                    window.location.href="${pageContext.request.contextPath}/cardistributionplan/listZhuProductLift";
				   	   }
				   },
				   error:function(xhr,s1,s2){
				    	var message = xhr.responseText;
						eval("var json = "+message);
		    	   	     //将按钮设置为可用
		       			 $("#save").removeAttr("disabled");
		       			 $("#commit").removeAttr("disabled");
		       			 $("#audit").removeAttr("disabled");
						if($.trim(json.error).length==0){
							alert('数据异常,请刷新页面重试');
						}else{
							alert(json.error);
						}
				   }
			});
			}
		</script>	</body>
</html>
