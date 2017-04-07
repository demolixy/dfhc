<%@page import="com.dfhc.base.freight.IFreightConstants" %>
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
	 
    var  ids=jQuery("#grid-table").jqGrid("getGridParam","selarrrow");
    if(typeof ids=='undefined' || ids.length==0){
  	  
  	  alert("至少选择一条数据");
  	  return false;
  	  
    }
    ids=ids.join(",");

    deleteForm.action=urlData+"&ids="+ids;
    deleteForm.submit();		
   
	  
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
//新增保存
function _saveInsert(){	
	
	var flag=true;//标志错误信息
	
	$(".notNullX").each(function(){		
		var  val=$(this).val();
		val=$.trim(val);
		if(val==''){
			alert("标注必填的字段不可为空");
			flag= false;
			return false;
		}	
	});	
	if(!flag){
		return flag;
	}
	
	submitInsertAjax('<%=request.getContextPath()%>/freight/insert',$(insertForm).serialize());
 	
	//刷新表单
	 
   	window.location.reload(true);
   
	}
//修改
function _saveUpdate(){	
	
	var flag=true;//标志错误信息
	
	$(".notNullX").each(function(){		
		var  val=$(this).val();
		val=$.trim(val);
		if(val==''){
			alert("标注必填的字段不可为空");
			flag= false;
			return false;
		}		
	});	
	if(!flag){
		return flag;
	}
	submitInsertAjax('<%=request.getContextPath()%>/freight/update',$(updateForm).serialize());
}

$("body").delegate("#insertForm select[name='targetType']","change",function(){
	
	$("#requisitionWarehouse").hide();$("#requisitionWarehouse").find("input").val("");
	
	$("#trainStation").hide();$("#trainStation").find("input").val("");
	
	$("#receiptAddress").hide();$("#receiptAddress").find("input").val("");
	
	var selectVal=$(this).find("option:selected").val();
	if(selectVal=='<%=ISystemConstant.DICTIONARY_TARGET_TYPE_00%>'){
		$("#receiptAddress").show();
		getNewReference('<%=request.getContextPath()%>/receiptaddress/reference?referenceInputType=radio','insertForm.provinceId,insertForm.province,insertForm.city');
	}else if(selectVal=='<%=ISystemConstant.DICTIONARY_TARGET_TYPE_01%>'){
		$("#requisitionWarehouse").show();
		getNewReference('<%=request.getContextPath()%>/requisitionwarehouse/reference?referenceInputType=radio','insertForm.targetId,insertForm.targetIdName');
	}else if(selectVal=='<%=ISystemConstant.DICTIONARY_TARGET_TYPE_02%>'){
		$("#trainStation").show();
		getNewReference('<%=request.getContextPath()%>/trainstation/reference?referenceInputType=radio','insertForm.targetId,insertForm.targetIdName');
	}

	
});
$("body").delegate("#updateForm select[name='targetType']","change",function(){
	
	$("#warehouse").hide();$("#warehouse").find("input").val("");
	
	$("#station").hide();$("#station").find("input").val("");
	
	$("#address").hide();$("#address").find("input").val("");
	
	var selectVal=$(this).find("option:selected").val();
	if(selectVal=='<%=ISystemConstant.DICTIONARY_TARGET_TYPE_00%>'){
		$("#address").show();
		getNewReference('<%=request.getContextPath()%>/receiptaddress/reference?referenceInputType=radio','updateForm.provinceId,updateForm.province,updateForm.city');
	}else if(selectVal=='<%=ISystemConstant.DICTIONARY_TARGET_TYPE_01%>'){
		$("#warehouse").show();
		getNewReference('<%=request.getContextPath()%>/requisitionwarehouse/reference?referenceInputType=radio','updateForm.targetId,updateForm.targetIdName');
	}else if(selectVal=='<%=ISystemConstant.DICTIONARY_TARGET_TYPE_02%>'){
		$("#station").show();
		getNewReference('<%=request.getContextPath()%>/trainstation/reference?referenceInputType=radio','updateForm.targetId,updateForm.targetIdName');
	}
})
//状态汉化

    orderStatusShow=[]; 

	 <c:forEach items="${statusType}" var="listStatus"> 
	  
	  orderStatusShow['${listStatus.key}']='${listStatus.value}';
	     
	 </c:forEach> 	
	 
	 function orderStatus(cellvalue, options, cell){

			return orderStatusShow[cellvalue];
		}

</script>
</head>
<body>
	
	
 <!--begin  新增 -->
	
  <div class="ui-widget-overlay ui_out_tan" ></div>

  <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="add_form" dir="ltr" tabindex="-1" role="dialog" 

aria-labelledby="edithdgrid-table" aria-hidden="false" >
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
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span>						
							  <input type="text" class="m-wrap notNullX"  name="shipperCode" id="shipperCode" />
							<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipper/reference?referenceInputType=radio','insertForm.shipperCode,insertForm.shipperCode');">选择</span>
							<i class="notNull">*</i>
							</div>	
								
					<%-- 		<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">发货方名称</span>
							<%=RmJspHelper.getSelectField("shipperId",-1, RmProjectHelper.getCommonServiceInstance().paseToArrays("select id as data_key,name as data_value from BASE_SHIPPER where nvl(BASE_SHIPPER.DELETE_FLAG,'0') = 0"),"", "    inputName=\""+IFreightConstants.TABLE_COLUMN_DISPLAY.get("SHIPPERId")+"\" validate='notNull;'  ", true , "--请选择--") %>										
							</div> --%>
							
							 <input type="hidden" class="m-wrap"  name="targetId" value="" />
							<div class="input-prepend" style="height: 45px">
							  <span class="add-on "><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType")%></span><%=RmJspHelper.getSelectField("targetType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_TARGET_TYPE), "", "inputName='" + IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType") + "'  ", true) %>							
							</div>
						<%-- 
							<div class="input-prepend"   id="requisitionWarehouse"  style="display: none" >
							  <span class="add-on">仓库名称</span>
							  <input type="text" class="m-wrap"  name="targetIdName" readonly="readonly"/>
							  <input type="hidden" class="m-wrap"  name="targetId" />
							</div>
						
							<div class="input-prepend"   id="trainStation"  style="display: none" >
							  <span class="add-on">站点名称</span>
							  <input type="text" class="m-wrap"  name="targetIdName" readonly="readonly"/>
							  <input type="hidden" class="m-wrap"  name="targetId" />
							</div>
							
							<div class="input-prepend"   id="receiptAddress"  style="display: none" >
							<div class="input-prepend" >
							  <span class="add-on">省</span>
							  <input type="text" class="m-wrap"  name="province" readonly="readonly"/>
							</div>
							<div class="input-prepend"   >
							  <span class="add-on">市</span>
							  <input type="text" class="m-wrap"  name="city" readonly="readonly"/>
							</div>
							</div>
							 --%>		
							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("yuanTon")%></span>
							  <input class="m-wrap" name="yuanTon"  type="text"  		readOnly				maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("yuanKmTon")%></span><input class="m-wrap" name="yuanKmTon"  type="text"  			style=" width: 180px;"			maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("mileage")%></span><input class="m-wrap" name="mileage"  type="text"  						maxLength="12" />
							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/freight/insert',$(insertForm).serialize())">
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
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span>						
							  <input type="text" class="m-wrap notNullX"  name="shipperCode" id="shipperCode" />
							<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipper/reference?referenceInputType=radio','updateForm.shipperCode,updateForm.shipperCode');">选择</span>
							<i class="notNull">*</i>
							</div>	
							
					  <div class="input-prepend" style="height: 45px">
							  <span class="add-on "><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType")%></span><%=RmJspHelper.getSelectField("targetType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_TARGET_TYPE), "", "inputName='" + IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType") + "'  ", true) %>							
							
							</div>
							
						<%-- 	<div class="input-prepend"   id="warehouse"  style="display: none" >
							  <span class="add-on">发货方名称</span>
							  <input type="text" class="m-wrap"  name="targetIdName" readonly="readonly"/>
							  <input type="hidden" class="m-wrap"  name="targetId" />
							</div>
						
							<div class="input-prepend"   id="station"  style="display: none" >
							  <span class="add-on">站点名称</span>
							  <input type="text" class="m-wrap"  name="targetIdName" readonly="readonly"/>
							  <input type="hidden" class="m-wrap"  name="targetId" />
							</div>
							
							<div class="input-prepend"   id="address"  style="display: none" >
							<div class="input-prepend" >
							  <span class="add-on">省</span>
							  <input type="text" class="m-wrap"  name="province" readonly="readonly"/>
							</div>
							<div class="input-prepend"   >
							  <span class="add-on">市</span>
							  <input type="text" class="m-wrap"  name="city" readonly="readonly"/>
							</div>
							</div>
							 --%>		

							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("yuanTon")%></span><input class="m-wrap" name="yuanTon" id="yuanTon" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("yuanKmTon")%></span><input class="m-wrap" name="yuanKmTon" id="yuanKmTon" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("mileage")%></span><input class="m-wrap" name="mileage" id="mileage" type="text"  						maxLength="12" />
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/freight/update',$(updateForm).serialize())">
       <i class="icon-ok"></i>
            更新</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
	

    <!-- end  修改 -->	
    <!-- begin 查询 -->
    
     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" dir="ltr" tabindex="-1" 

role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
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

							<div class="input-prepend" >
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span>						
							  <input type="text" class="m-wrap "  name="shipperCode" style="width:180px"/>
							<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipper/reference?referenceInputType=radio','form.shipperCode,form.shipperCode');">选择</span>
							</div>	

						<%-- <div class="input-prepend" style="height: 45px">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType")%></span><%=RmJspHelper.getSelectField("targetType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_TARGET_TYPE), "", "inputName='" + IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType") + "'  ", true) %>							

							</div>  --%>	

							<div class="input-prepend">
							  <span class="add-on"><%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("mileage")%></span><input class="m-wrap" name="mileage"  type="text"  style="width:180px" 						maxLength="12" />
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/freight/logicDelete?pcode=${pcode }');">
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
								<a href="#">基础信息</a>
							</li>
							<li class="active">目的地运费维护</li>
						</ul><!-- .breadcrumb -->

						
					</div>

					<div class="page-content">
					

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
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

		<script type="text/javascript">
			//var grid_data = ${grid_data};	
		
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";

				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					url: getRootPath()+'/freight/ajaxList',
					datatype: "json",
				    mtype: 'POST',  
					height: "100%",
					colNames:[
					          '发货方名称',
'<%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetId")%>'
,'<%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("targetType")%>'
,'<%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("yuanTon")%>'
,'<%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("yuanKmTon")%>'
,'<%=IFreightConstants.TABLE_COLUMN_DISPLAY.get("mileage")%>'
],
					colModel:[
			{name:'name',index:'name', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'targetId',index:'targetId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"19"}},
			{name:'targetType',index:'targetType', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}, formatter:targetTypeConvert},
			{name:'yuanTon',index:'yuanTon', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'yuanKmTon',index:'yuanKmTon', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'mileage',index:'mileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}}
					], 
			                shrinkToFit:true,//不随列数量改变列宽度
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
			
					editurl: getRootPath()+'/freight/update',//nothing is saved
					caption: "运费维护列表展示",
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
					{ caption:"", buttonicon:"icon-plus-sign purple",
				      onClickButton: addShow, position:"last" }) ;
			
				//修改
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-pencil blue",
				      onClickButton:updateShow, 
				      position:"last" }) ;
				//删除
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-trash red",
				      onClickButton:deleteShow , 
				      position:"last" }) ;
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
			     //新增弹出
			     
				 function addShow(){
					 
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
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/freight/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=shipperCode]").val(data.bean.shipperCode);
                      $("#portlet-config-edit").find("input[id=targetId]").val(data.bean.targetId);
                      $("#portlet-config-edit").find("input[id=targetType]").val(data.bean.targetType);
                      $("#portlet-config-edit").find("input[id=yuanTon]").val(data.bean.yuanTon);
                      $("#portlet-config-edit").find("input[id=yuanKmTon]").val(data.bean.yuanKmTon);
                      $("#portlet-config-edit").find("input[id=mileage]").val(data.bean.mileage);
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
			    	 $(".ui_out_tan").show();
                     $("#portlet-config-edit").show();
			    	 
			     }
			     
/* 			     //删除弹出
			     function deleteShow (){
			    	 
			    	 var checkedLen= $(".cbox:checked").length;
				    	if(checkedLen==0){
				    		
				    		alert("请至少选择一条数据！");
				    		return false;
				    	}
				    	$(".ui_out_tan").show();
				        $("#portlet-config-delete").show(); 
				    	
			    	  
			     }*/
			 //删除弹出
		     function deleteShow (){
		    	 
		    		var selectedRows=jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
		    		
		    		if(selectedRows.length==0){
		    			
		    			alert("请选择要删除的数据");
		    			
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
				
		
			});
		</script>
	</body>
</html>
