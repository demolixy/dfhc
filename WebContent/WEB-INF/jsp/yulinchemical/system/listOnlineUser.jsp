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
<c:forEach items="${adminTypeMap}" var="listStatus"> 
	 var keyL ='${listStatus.key}';
	 statusConvert[keyL]='${listStatus.value}';
</c:forEach> 	



</script>
	</head>
	


	<body>
	
	 <form name="form" method="post">
	 
	 </form>
	
	
  
	
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
					 			 	 	  	 	  	
					url: getRootPath()+'/user/ajaxListOnlineUser',
					datatype: "json",
				    mtype: 'POST',  
					height:"auto",
					colNames:['姓名','登录名 ','权限类型 ', '访问时间', '用户IP','累计登录','最后操作时间','思考时间','距超时退出','服务器名'],
					colModel:[
                        {name:'name',index:'name',sortable:false,sorttype :"text"},
						{name:'login_id',sortable:false,index:'login_id'},
						{name:'admin_type',index:'admin_type' ,editable: false,formatter:convert},
						{name:'last_login_date',index:'last_login_date',  editable: true,edittype:"date",formatter: onlineTimeFormat},
						{name:'last_login_ip',index:'last_login_ip', editable: true,edittype:"text"},
						{name:'login_sum',index:'login_sum', sortable:false,editable: true,edittype:"text"} ,
						{name:'lastAccessedTime',index:'lastAccessedTime', sortable:false,editable: true,edittype:"date",formatter: formatDateRow} ,
						{name:'lastAccessedTime',index:'lastAccessedTime', sortable:false,editable: true,edittype:"date",formatter:onlineTimeFormat} ,
						{name:'maxInactiveInterval',index:'maxInactiveInterval', sortable:false,editable: true,edittype:"text",formatter:outOfTime} ,
						{name:'clusterNodeId',index:'clusterNodeId', sortable:false,editable: false,edittype:"text",width:280}
					], 
				     shrinkToFit:false,//不随列数量改变列宽度
					viewrecords : true,
					rownumbers:true,
					rowNum:15,
					rowList:[15,30],
					pager : pager_selector,
					sortname : 'id',//初始化的时候排序的字段
					sortorder : "desc",//排序方式,可选desc,asc
					altRows: true,
					//toppager: true,
					
					multiselect: false,
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
					caption: "在线用户记录",
					autowidth: true
					
// 					,ondblClickRow: function(rowid,iRow,iCol,e){	}
			
				});
				
				
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
				
				function outOfTime(cellvalue,options,cell){
				
					var out=Number(cellvalue);
					if(!out) return "0";
					var th=new Date().getTime()-Number(cell.lastAccessedTime);
					th=out-th;
			
					if(!th)
						return "0";
					
					th=parseInt(th/1000);//转为秒
					
					var ret='';
					
					var d=parseInt(th/(24*60*60));
					
					if(d>0){
						
						ret=ret+d+"天";
						
					}
					
					th=th-d*(24*60*60);
					
					var h=parseInt(th/(60*60));
					if(h>0){
						
						ret=ret+h+"小时 ";
					}
					
					th=th-h*(60*60);
					
					var min=parseInt(th/(60));//
					if(min>0){
						
						ret=ret+min+"分钟";
					}
				
					return ret;
					
					
				}
				
				//在线时间
				function  onlineTimeFormat(cellvalue,options,cell){
				
					var thinNum=Number(cellvalue);
					if(thinNum==0){
						return "0";
					}
					var th=new Date().getTime()-thinNum;//
					
					th=parseInt(th/1000);//转为秒
					
					var ret='';
					
					var d=parseInt(th/(24*60*60));
					
					if(d>0){
						
						ret=ret+d+"天";
						
					}
					
					th=th-d*(24*60*60);
					
					var h=parseInt(th/(60*60));
					if(h>0){
						
						ret=ret+h+"小时 ";
					}
					
					th=th-h*(60*60);
					
					var min=parseInt(th/(60));//
					if(min>0){
						
						ret=ret+min+"分钟";
					}
					
					th=th-min*60;
					
					var sec=th;
					
					if(sec>0){
						
						ret=ret+sec+"秒";
					}
					
					
					
					return ret;
					
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
						refresh: true,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey',
					
					}
				)
				
			
				
			
			     
			 
			     
			     
			     
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
