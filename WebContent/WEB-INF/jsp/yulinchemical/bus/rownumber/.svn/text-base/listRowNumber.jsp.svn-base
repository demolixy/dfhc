<%@page import="com.dfhc.bus.rownumber.IRowNumberConstants" %>
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
	   <span class="ui-jqdialog-title" style="float: left;">签到</span>
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
							  <span class="add-on">手机号</span>
							  <input class="m-wrap" name="phoneNumber" id="phoneNumber" type="text"  						maxLength="10" />
							  
							</div>
     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/rownumber/sign',$(insertForm).serialize())">
       <i class="icon-ok"></i>
               签到</a>
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
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" id="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','updateForm.ladingBillId,updateForm.ladingBillIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowType")%></span><%=RmJspHelper.getSelectField("rowType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_TYPE), "", "inputName='" + IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowDate")%></span><input name="rowDate" id="rowDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("sequence")%></span><input class="m-wrap" name="sequence" id="sequence" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_STATUS), "", "inputName='" + IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span><input class="m-wrap" name="truckNo" id="truckNo" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on">主产品</span><input class="m-wrap" name="productId" id="productId" type="hidden"  						maxLength="19" /><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/producttype/reference?referenceInputType=radio','updateForm.productId,updateForm.productName');">选择</span>
							</div>
							<div class="input-prepend">
							  <span class="add-on">副产品</span><input class="m-wrap" name="productTypeId" id="productTypeId" type="hidden"  						maxLength="19" /><input class="m-wrap" name="productTypeName" id="productTypeName" type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','updateForm.productTypeId,updateForm.productTypeName');">选择</span>
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/rownumber/update',$(updateForm).serialize())">
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

							<div class="input-prepend">
							  <span class="add-on">提货单号</span>						
							  		<input type="hidden" class="m-wrap"  name="ladingBillId" /><input type="text" class="m-wrap"  name="ladingBillIdName" />
									<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','form.ladingBillId,form.ladingBillIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowType")%></span><%=RmJspHelper.getSelectField("rowType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_TYPE), "", "inputName='" + IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowDate")%></span><input name="rowDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("rowDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("sequence")%></span><input class="m-wrap" name="sequence"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ROW_STATUS), "", "inputName='" + IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRowNumberConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span><input class="m-wrap" name="truckNo"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on">主产品</span><input class="m-wrap" name="productId" id="productId" type="hidden"  						maxLength="19" /><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/producttype/reference?referenceInputType=radio','updateForm.productId,updateForm.productName');">选择</span>
							</div>
							<div class="input-prepend">
							  <span class="add-on">副产品</span><input class="m-wrap" name="productTypeId" id="productTypeId" type="hidden"  						maxLength="19" /><input class="m-wrap" name="productTypeName" id="productTypeName" type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','form.productTypeId,form.productTypeName');">选择</span>
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/rownumber/logicDelete?pcode=${pcode }');">
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
								首页
							</li>

							<li>
								签到排号
							</li>
							<li class="active"><a href="${pageContext.request.contextPath }/rownumber?pcode=${pcode}">
							<c:choose>
								<c:when test="${alreadySign == 'all' }">排队管理</c:when>
								<c:otherwise>签到登记</c:otherwise>
							</c:choose>
							</a></li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
<!-- 								<span class="input-icon"> -->
<!-- 									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> -->
<!-- 									<i class="icon-search nav-search-icon"></i> -->
<!-- 								</span> -->
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								排号表
								<small>
									<i class="icon-double-angle-right"></i>
									数据列表展示
								</small>
							</h1>
						</div> page-header -->

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
					
					url: getRootPath()+'/rownumber/ajaxList?${queryString}',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:['truckNo',
'手机号','签到时间','提货单号','车牌号码','类别','状态','产品次序'
],
					colModel:[ {name:'truckNo',index:'truckNo',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'phoneNumber',index:'phoneNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'rowDate',index:'rowDate',sorttype :"date",formatter: formatDateRow, width:90,editable: true,editoptions:{size:"20",maxLength:"2"},formatter:formatDateRow},
			{name:'ladingBillCode',index:'ladingBillCode', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'truckNo',index:'truckNo', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'rowType',index:'rowType', width:90,editable: true,editoptions:{size:"20",maxLength:"2"},formatter:rowTypeConvert},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"},formatter:rowStatusConvert},
			{name:'productSequence',index:'productSequence', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			
					], 
			              
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
			
					editurl: getRootPath()+'/rownumber/update',//nothing is saved
					caption: "排号表列表展示",
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
				
				/*//签到
			   jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"签到", buttonicon:"icon-bullhorn purple",
				      onClickButton: sign, position:"last" }) ;
				*/
				//修改
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{caption:"",title:"入场", buttonicon:"icon-pencil blue",
				      onClickButton:ru, 
				      position:"last" }) ;
				//修改
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{caption:"",title:"出场", buttonicon:"icon-pencil blue",
				      onClickButton:chu, 
				      position:"last" }) ;
				/*	//删除
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"删除", buttonicon:"icon-trash red",
				      onClickButton:deleteShow , 
				      position:"last" }) ;*/
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"查询", buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
			     
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
                        url: "<%=request.getContextPath()%>/rownumber/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=ladingBillId]").val(data.bean.ladingBillId);
                      $("#portlet-config-edit").find("input[id=rowType]").val(data.bean.rowType);
                      $("#portlet-config-edit").find("input[id=rowDate]").val(data.bean.rowDate);
                      $("#portlet-config-edit").find("input[id=sequence]").val(data.bean.sequence);
                      $("#portlet-config-edit").find("input[id=status]").val(data.bean.status);
                      $("#portlet-config-edit").find("input[id=truckNo]").val(data.bean.truckNo);
                      $("#portlet-config-edit").find("input[id=sequenceId]").val(data.bean.sequenceId);
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
				//签到
				function sign(){
					 $(".ui_out_tan").show();
			    	 $("#add_form").show();
				}
				
				
				
				//入场
				function ru(){
			    	 
			    	var checkedLen= $(".cbox:checked").length;
			    	if(checkedLen==0){
			    		
			    		alert("请至少选择一条数据！");
			    		return false;
			    	}
                    if(checkedLen>1){
			    		
			    		alert("最多只能选择一条数据！");
			    		return false;
			    	}
//                     var pointer=$(".cbox:checked").parents("tr");
//                     var updateDate=$(pointer).attr("id");

		    		var updateDate = $("#grid-table").jqGrid("getGridParam", "selarrrow");
		    		var rowData = $("#grid-table").jqGrid("getRowData", updateDate);
                    var truckNo=rowData.truckNo;
				   		 $.ajax({
							    type:'post',//可选get
							    url:"${pageContext.request.contextPath}/api/gate/gateOperation?truckNumber="+truckNo+"&operationCode=01",
							    dataType:'json',
							    async:false , //false为同步
							    success:function(data,status){
								  if(data.status=="0"){
					    	   	     //将按钮设置为可用
									  alert(data.message);
							   	   }else{
							   	     	alert(data.message);
					                    window.location.href="${pageContext.request.contextPath}/rownumber?alreadySign=all&pcode=${pcode}";
							   	   }
							   },
							   error:function(xhr,s1,s2){
							    	var message = xhr.responseText;
									eval("var json = "+message);
					    	   	     //将按钮设置为可用
									if($.trim(json.error).length==0){
										alert('数据异常,请刷新页面重试');
									}else{
										alert(json.error);
									}
							   }
						});
			    		
				}

				
				
				//出厂
				function chu(){
			    	 
			    	var checkedLen= $(".cbox:checked").length;
			    	if(checkedLen==0){
			    		
			    		alert("请至少选择一条数据！");
			    		return false;
			    	}
                    if(checkedLen>1){
			    		
			    		alert("最多只能选择一条数据！");
			    		return false;
			    	}
		    		var updateDate = $("#grid-table").jqGrid("getGridParam", "selarrrow");
		    		var rowData = $("#grid-table").jqGrid("getRowData", updateDate);
                    var truckNo=rowData.truckNo;
				   		 $.ajax({
							    type:'post',//可选get
							    url:"${pageContext.request.contextPath}/api/gate/gateOperation?truckNumber="+truckNo+"&operationCode=11",
							    dataType:'json',
							    async:false , //false为同步
							    success:function(data,status){
								  if(data.status=="0"){
					    	   	     //将按钮设置为可用
									  alert(data.message);
							   	   }else{
							   	     	alert(data.message);
					                    window.location.href="${pageContext.request.contextPath}/rownumber?alreadySign=all&pcode=${pcode}";
							   	   }
							   },
							   error:function(xhr,s1,s2){
							    	var message = xhr.responseText;
									eval("var json = "+message);
					    	   	     //将按钮设置为可用
									if($.trim(json.error).length==0){
										alert('数据异常,请刷新页面重试');
									}else{
										alert(json.error);
									}
							   }
						});
			    		
				}

			});
		</script>
	</body>
</html>
