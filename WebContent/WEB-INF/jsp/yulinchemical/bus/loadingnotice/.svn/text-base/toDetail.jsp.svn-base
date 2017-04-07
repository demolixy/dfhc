<%@page import="com.dfhc.bus.loadingnotice.ILoadingNoticeConstants"%>
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
jqData = [];


	$(function(){
		numStr="<input  name=\"num\" onblur=\"checkNum(this)\" value=\"";
		numEnd="\"/>";
	})
		    
	function  checkNum(obj){
		 var num=$(obj).val();
		 if($.trim(num).length==0){
			 num=0;
		 }else{
			var reg = /^[1-9]\d*$/;
			if(!reg.test(num)){
				 num=0;
			}
		 }
	     var updateDate=$(obj).parents("tr").attr("id");
// 	 	 var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
// 	 	 var sheYuNum = rowData.sheYuNum;
// 		 if(Number(num)>Number(sheYuNum)){
// 			 num=Number(sheYuNum);
// 		 }
		 
		 $(obj).val(num);
		 
		 for(var i=0;i<jqData.length;i++){
			 if(updateDate == jqData[i].id){
				 jqData[i].num=num;
			 }
		 }
		 
		 var numLu=0;
	     $("input[name=num]").each(function(){
		 	numLu = Number(numLu)+Number($(this).val()) ;
	     });
	     $("#actualShippingWeight").text(numLu);
	     
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

function deleteLadingBill(){
	 var pointer=$(".cbox:checked").parents("tr");
     var updateDate=$(pointer).attr("id");
 	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
 	var id = rowData.id;
 	$(".ui_out_tan").hide();
    $("#portlet-config-delete").hide();
 		 
//  	$("#grid-table").jqGrid('updateGridRows',rowData, rowData.id, false);
 	jqData.remove(id);
 	$("#grid-table").jqGrid('delRowData', rowData.id);
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

							<li>
								<a href="#">装车业务</a>
							</li>
							<li class="active">提货单装车分配</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">

 <!-- begin  详情 -->
		                
		                 <div class="widget-box">
								
		                     <div class="widget-header">
		                      
		                      <h5>提货单详情</h5>
		                      <div class="widget-toolbar">
		                      
		                         <a href="javascript:history.go(-1);" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                 <form	name="zongForm" id="zongForm">
		                 <input type="hidden" name="jqData"/>
								<input type="hidden" name="ladingBillId"  value="${bean.id}"/>
		                 
		                         <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
			                         <tbody>
			                         
			                      <tr>
		                           
			                             <th>提货单号:</th>
			                             <td><c:out value="${bean.ladingBillCode}"></c:out>   </td>
			                             <th>产品名称:</th>
			                             <td><c:out value="${bean.productName}"></c:out>   </td>
			                          	 <th>核载量:</th>
			                          	 <td><c:out value="${bean.nuclearLoad}"></c:out> </td>
			                          </tr>
			                           
			                          <tr>
		                           
			                             <th>车牌号:</th>
			                             <td><c:out value="${bean.licensePlate}"></c:out>   </td>
			                             <th>司机姓名:</th>
			                             <td><c:out value="${bean.driverName}"></c:out>  </td>
			                             <th>司机手机号:</th>
			                             <td><c:out value="${bean.phoneNumber}"></c:out>  </td>
			                          
			                          </tr>

			                          <tr>
			                          	 <th>实际发货重量:</th>
			                          	 <td  id="actualShippingWeight"><c:out value="${bean.nuclearLoad}"></c:out> </td>
		                           
			                             <td><input name="isNeedWeigh"  type="checkbox"  maxLength="200"  <c:if test="${bean.isPound=='1'}">checked="checked"</c:if>   value="1" />
							 				  <span class="span-check-radio" style="font-size:14px;">是否过磅</span>
			                              </td>
			                          	 <th>门号:</th>
			                          	 <td><input name="gateNum" /></td>
			                          </tr>
			                           
			                           
				                      <tr>
			                          	 <th>备注:</th>
			                          	 <td  colspan="5">${bean.remark}</td>
			                          </tr>
			                           
			                         </tbody>
		                      
		                         
		                         </table>
		                 </form>
		                   
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
		                         
		                          <a href="javascript:void(0);" class="sub-btn-shi"  id="commit"   onclick="javascript:doSaveOrCommit('commit')" > 提交</a>
		                         
		                         </div>
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						


						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->
			

		
		</div><!-- /.main-container -->

		<script type="text/javascript">
			//var grid_data = ${grid_data};	
			//核载量
			var  nuclearLoad='${bean.nuclearLoad}';
			
				<c:forEach items="${beans}" var="bean"> 
					var dataObj1=new Object();
					dataObj1.id= guid();
					dataObj1.productId='${bean.productId}';
					dataObj1.productBatchNumber= '${bean.productBatchNumber}';
					dataObj1.positionNum='${bean.storageLocation}';
					dataObj1.sheYuNum='${bean.storageNumber-bean.outboundNumber}';
					if(Number(dataObj1.sheYuNum)>=Number(nuclearLoad)){
						dataObj1.num= Number(nuclearLoad);
						nuclearLoad=0;
					}else{
						dataObj1.num= Number(dataObj1.sheYuNum);
						nuclearLoad=Number(nuclearLoad)-Number(dataObj1.sheYuNum);
					}
					
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
					colNames:['id', '产品批次','仓位号','库存数量','预出库数量'
],
					colModel:[
{name:'id',index:'id',hidedlg:true,hidden:true, width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
{name:'productBatchNumber',index:'productBatchNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'positionNum',index:'positionNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'sheYuNum',index:'sheYuNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
{name:'num',index:'num', width:90,editable: true,editoptions:{size:"20",maxLength:"10"},formatter:numShow},
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
			
					caption: "产品批次",
					autowidth: true
			
				});
				
				
				
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
			
			
				function numShow(cellvalue, options, cell) {
					if(!cellvalue){
						cellvalue='0';
					}
					return numStr+cellvalue+numEnd;
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
				//删除
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", title:"删除", buttonicon:"icon-trash red",
// 				      onClickButton:deleteShow , 
// 				      position:"last" }) ;
// 				//新增
//  			   jQuery(grid_selector) .navButtonAdd(pager_selector,
//  					{ caption:"", title:"新增", buttonicon:"icon-plus-sign purple",
//  				      onClickButton: addShow, position:"last" }) ;
			
// 				//修改
//  				  jQuery(grid_selector) .navButtonAdd(pager_selector,
//  					{ caption:"", title:"修改", buttonicon:"icon-pencil blue",
//  				      onClickButton:updateShow, 
//  				      position:"last" }) ;
				
// 				//查询
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", title:"查询",buttonicon:"icon-search orange",
// 				      onClickButton:search_div , 
// 				      position:"last" }) ;
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
			    	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
			    	
			    	$("#updateForm").find("input[name=productId]").val(productId);
			    	$("#updateForm").find("input[name=licensePlate]").val(licensePlate);
			    	$("#updateForm").find("input[name=jobTime]").val(Date.parse(rowData.jobTime));
			    	$("#updateForm").find("input[name=productName]").val(productName);
			    	$("#updateForm").find("input[name=productModelNumber]").val(rowData.productModelNumber);
			    	$("#updateForm").find("input[name=productBatchNumber]").val(rowData.productBatchNumber);
			    	$("#updateForm").find("input[name=productLevel]").val(rowData.productLevel);
			    	$("#updateForm").find("input[name=num]").val(rowData.num);
			    	$("#updateForm").find("input[name=gateNum]").val(rowData.gateNum);
			    	$("#updateForm").find("input[name=positionNum]").val(rowData.positionNum);
			    	$("#updateForm").find("input[name=toneNum]").val(rowData.toneNum);
			    	$("#updateForm").find("input[name=isNeedWeigh]").val(rowData.isNeedWeigh);
			    	$("#updateForm").find("input[name=isNeedWeigh2]").val(rowData.isNeedWeigh);
					if(rowData.isNeedWeigh=='是'){
						$("#updateForm").find("input[name=isNeedWeigh]").attr("checked","checked");
						
					}else{
						$("#updateForm").find("input[name=isNeedWeigh]").removeAttr("checked");
					}
					
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
				}
				
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
			
			
			function guid() {
			    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
			        return v.toString(16);
			    });
			}
			
			function doSaveOrCommit(flag){
				var count=0;
			     $("input[name=num]").each(function(){
					 	if(Number($(this).val())>0){
					 		count++;
					 	}
				 });

			     if(count>3){
			    	 alert("尽量填写三个批次");
			    	 return false;
			     }

			     var numLu=0;
			     $("input[name=num]").each(function(){
				 	numLu = Number(numLu)+Number($(this).val()) ;
			     });
				if(!confirm("预出库数量为:"+numLu+"确定提交吗?")){
					return false;
				}
			     
				var gateNum=$("input[name=gateNum]").val();
				if($.trim(gateNum).length==0){
					alert("请填写门号");
					return  false;
				}
				
				var jqDataStr=JSON.stringify(jqData);
				$("#zongForm").find("input[name='jqData']").val(jqDataStr);
       			 $("#commit").attr("disabled");
       			 $("#audit").attr("disabled");
				$.ajax({
				    type:'post',//可选get
				    url:"${pageContext.request.contextPath}/loadingnotice/insert?date=" + new Date(),
				    data:$(zongForm).serialize(),
				    dataType:'json',
				    async:false , //false为同步
				    success:function(data,status){
					  if(data.status=="0"){
		    	   	     //将按钮设置为可用
		       			 $("#audit").removeAttr("disabled");
		       			 $("#commit").removeAttr("disabled");
						  alert(data.message);
				   	   }else{
				   	     	alert(data.message);
		                    window.location.href="${pageContext.request.contextPath}/ladingbill?operation=ladingBillManage&ladingBillStatus=05";
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
