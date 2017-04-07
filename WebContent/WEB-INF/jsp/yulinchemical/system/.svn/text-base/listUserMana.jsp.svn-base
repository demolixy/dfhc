<%@page import="org.quickbundle.orgauth.rmuser.util.IRmUserConstants"%>
<%@page import="com.dfhc.rm.user.IUserConstants"%>
<%@page import="com.dfhc.quota.IQuotaConstants"%>
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

<% String ssId=request.getSession(true).getId(); %>


<script type="text/javascript">

var  statusConvert=[]; 
<c:forEach items="${lockStatusMap}" var="listStatus"> 
	 var keyL ='${listStatus.key}';
	 statusConvert[keyL]='${listStatus.value}';
</c:forEach> 	

var adminType=[];

<c:forEach items="${adminTypeMap}" var="listStatus"> 
var keyL ='${listStatus.key}';
adminType[keyL]='${listStatus.value}';
</c:forEach> 	

//状态汉化
function convertAdminType(cellvalue, options, cell){
	return adminType[cellvalue];
}

function db_delete(){
// 	var selectedId = $("#grid-table").jqGrid("getGridParam", "selrow");
// 	var rowData = $("#grid-table").jqGrid('getRowData',selectedId);
	var selectedRows=jQuery("#grid-table").jqGrid("getGridParam","selarrrow");
	var ids=selectedRows.join(",");
	var url='${pageContext.request.contextPath}/user/ajaxDeleteUsers';
	$.ajax({
	    type:'post',//可选get
	    url:url,
	    data:{"ids":ids},//送评表的表格id属性值
	    dataType:'json',
	    async:false , //false为同步
	    success:function(data,status){
	    alert(data.msg);
		$(".ui_inner_tan").hide();
   		$(".ui_out_tan").hide();
	   	 
		//刷新表单
		   jQuery("#grid-table").trigger("reloadGrid");
	   },
	   error:function(xhr,s1,s2){
	    	var message = xhr.responseText;
			eval("var json = "+message);
			if($.trim(json.error).length==0){
				alert('数据异常,请刷新页面重试');
			}else{
				alert(json.error);
			}
			$(".ui_inner_tan").hide();
			$(".ui_out_tan").hide();
	   }
	   });
	
	
	
	
}

//查询
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
							  <span class="add-on"><%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("login_id")%></span>
							  <input class="m-wrap" name="login_id" id="login_id" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("name")%></span>
							  <input class="m-wrap" name="name" id="name" type="text"  	maxLength="200" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("lock_status")%></span>
							  <select name="enable_status" style="width: 207px;">
							  <option value="">--不限制--</option>
							  <c:forEach items="${lockStatusMap}" var="listStatus"> 
							  <option value="${listStatus.key}">${listStatus.value}</option>
							
							 </c:forEach> 
							  </select>
							</div>
							
						  <div class="input-prepend">
							  <span class="add-on"><%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("admin_type")%></span>
							  <select name="admin_type" style="width: 190px;">
							  <option value="">--不限制--</option>
							  <c:forEach items="${adminTypeMap}" var="listStatus"> 
							  <option value="${listStatus.key}">${listStatus.value}</option>
							
							 </c:forEach> 
							  </select>
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:db_delete();">
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
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
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
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">系统管理</a>
							</li>
						
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">
<!-- 						<div class="page-header"> -->
<!-- 							<h1> -->
<!-- 								账户查询模块 -->
<!-- 								<small> -->
<!-- 									<i class="icon-double-angle-right"></i> -->
<!-- 									数据列表展示 -->
<!-- 								</small> -->
<!-- 							</h1> -->
<!-- 						</div>/.page-header -->

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
					 			 	 	  	 	  	
					url: getRootPath()+'/user/ajaxListUserMana',
					datatype: "json",
				    mtype: 'POST',  
					height:"auto",
					colNames:['姓名','所属组织 ','激活/锁定状态', '登录名', '电子邮箱','用户权限类型','最后登录时间','最后登录IP','累计登录次数'],
					colModel:[
                        {name:'name',index:'name',sortable:false,sorttype :"text"},
						{name:'attribute1',sortable:false,index:'attribute1'},
						{name:'lock_status',index:'lock_status' ,editable: false,formatter:convert},
						{name:'login_id',index:'login_id',  editable: true},
						{name:'email',index:'email', editable: true,edittype:"text"},
						{name:'admin_type',index:'admin_type', sortable:false,editable: true,formatter:convertAdminType},
						{name:'last_login_date',index:'last_login_date', sortable:false,formatter:formatDateRow},
						{name:'last_login_ip',index:'last_login_ip', sortable:false,editable: true,edittype:"text"},
						{name:'login_sum',index:'login_sum', sortable:false,editable: true,edittype:"text"}
						
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
			
					editurl: getRootPath()+'/user/list',//nothing is saved
					caption: "人员管理",
					autowidth: true
					
// 					,ondblClickRow: function(rowid,iRow,iCol,e){	}
			
				});
				
				
			
				
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
						refresh: true,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey',
					
					}
				)
				
			
              //自定义标签
				
			
				
			
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"first" }) ;
				
				
				//删除
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-trash red",
				      onClickButton:deleteShow , 
				      position:"first" }) ;
				
				//修改
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-pencil blue",
				      onClickButton:updateShow, 
				      position:"first" }) ;
					//新增
				   jQuery(grid_selector) .navButtonAdd(pager_selector,
						{  caption:"",title:"新增", buttonicon:"icon-plus-sign purple",
					      onClickButton: addShow, position:"first" }) ;
				
					//角色设置页面
					  jQuery(grid_selector) .navButtonAdd(pager_selector,
						{ caption:"",title:"授权", buttonicon:"icon-group red",
					      onClickButton:toSetRole , 
					      position:"first" }) ;
					
					//角色设置页面
					    function toSetRole(){
						
							var  selectRows=jQuery(grid_selector).jqGrid("getGridParam","selarrrow");//获取选中
							
							if(selectRows.length!=1){
								
								alert("请选择一条数据进行授权");
								return false;
								
							}
							
							var dataId=selectRows[0];
							
							window.location.href="${pageContext.request.contextPath}/user/toSetRole/"+dataId+"/"+new Date();
					    	
					    	
					    }
						
					     //新增弹出
					     
						 function addShow(){
					    	 
							 window.location.href="${pageContext.request.contextPath}/user/toInsertUser";
						 }
					     
					     //  修改弹出
					     function updateShow(){
					   
					    	 var selectedRows=jQuery(grid_selector).jqGrid("getGridParam","selarrrow");//查询选中的行
					    	 
					    	 if(selectedRows.length==0){
					    		 
					    		 alert("请选择一条要修改的程序！");
					    		 return false;
					    	 }
					    	 if(selectedRows.length>1){
					    		 alert("至多只能选择一条数据！");
					    		 return false;
					    		 
					    	 }
					    	 
					    	 var id=selectedRows[0];
					    	 
					    	 window.location.href="${pageContext.request.contextPath}/user/toUpdateUser?id="+id.toString();
					    	 
					    	 
					    	 
					    	 
					    	 
					     }
					     
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
					});
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
