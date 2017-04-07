
<%@page import="org.quickbundle.orgauth.IOrgauthConstants"%>
<%@page import="org.quickbundle.orgauth.cache.RmPartyTypeCache"%>
<%@page import="org.quickbundle.orgauth.rmrole.vo.RmRoleVo"%>
<%@page import="org.quickbundle.orgauth.rmrole.util.IRmRoleConstants"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/ztree/demo.css" type="text/css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
	
	   <!-- 全局js 和css包 -->
	   <%@ include file="/jsp/include/web/jqGrid/global.jsp" %>
	   	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
		<meta charset="utf-8" />
		<title><bean:message key="qb.web_title"/></title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
	

	<style type="text/css">
	   
		ul.ztree {
	       margin-top: 10px;
	       border: none;
		    background: #fff;
		    width: inherit;
		    height: inherit;
	       overflow-y: auto;
	       overflow-x: auto; 
	    }
	
	</style>

	</head>
	


	<body>
	
	 <!-- begin 组织机构 -->

     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" style="min-width:800px;width: 80%;top:5%;left:10%;" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="min-width:800px;width: 100%;cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">团体类型</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table" style="min-width:800px;width: 100%;"><div>
      <div class="modal-body" style="padding:0;">
      
        <iframe id="partyIframe" style="border:none;min-height:470px;width:100%;height: auto;max-height: 500px;" src="${pageContext.request.contextPath}/pubController/getPartyReference?referenceInputType=radio">
        
        </iframe>
   
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:referenceSub();">
       <i class="icon-ok"></i>
                         确认选择</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div>
     
     </div>

    
    <!-- end 组织机构 -->
    

	<form name="form" method="post">   
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	  
	  <input type="hidden" name="old_code_id" value="${old_code_id }">
		<input type="hidden" value="" name="function_node_ids" id="function_node_ids">
	    <input type="hidden" value="" name="function_node_id_names" id="function_node_id_names">
	    <input type="hidden" value="${rmRoleVo.id }" name="role_id"> 
		
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

		                <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 
		                     <div class="widget-header">
		                      
		                      <h5>组织结构树</h5>
		                     
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                     
		                       <div class="widget-main">
		                       
		                       
		                       
		                        <div class="modal-body clearfix" style="padding:0;">
		                        
		                        
		                           <!-- begin 组织机构视图 -->
		                           
		                           <div id="content" class="party_left">
		                           
		                            <div class="party_left_title"> 机构组织树视图 </div>
		                            
		                            <!-- begin 组织机构树 -->
		                             <div>
		                               
		                               <ul id="treeDemo" class="ztree" ></ul>
		                               
		                             </div>
		                             <!-- end 组织机构树 -->
							       
		                           
		                           
		                           </div>
		                           
		                           
		                           
		                           <!-- end 组织机构视图 -->
		                           
		                           <!-- begin 详情 -->
		                           
		                           <div id="content2" class="party_right" >
		                           
		                           <div class="childParty">
		                             
		                             <span>本节点名称:</span>
		                             <span id="child_party_name_span">价格管理专员</span>
		                             <span id="child_party_id_span">(ID:2011201701090000003)</span>
		                           
		                           </div>
		                           
		                           <div class="childParty">
		                             <span>本关系:</span>
		                             <span id="parent_party_name_span">父节点</span>
		                              <span>(子编码:<i id="child_party_code_span">A003</i>) </span>
		                            </div>
		                           
		                           <div class="childParty">
		                             
		                             <span>本节点名称:</span>
		                             <span> <input name="this_party_name" value=""/> </span>
		                             <span>
		                                <a href="javascript:void(0);" onclick="javascript:updateThisPartyName_onClick()">更新节点名称</a>
		                             </span>
		                            
		                           </div>
		                           
		                          <div class="childParty">
		                             <span>排序值:</span>
		                             <span> <input name="order_code" value=""/> </span>
		                             <span>
		                                <a href="javascript:void(0)" onclick="javascript:updateOrderCode_onClick()">更新排序值</a>
		                             </span>
		                          </div>
		                          
		                          <div>
		                            <span> <i class="notNull">*</i> 团体类型id:</span>
		                            <span>
		                               <select name="party_type_id" id="party_type_id" value="">
		                                
		                                  <option>--请选择团体类型--</option>
		                               
		                               </select>
		                            </span>
		                          </div>
		                          
		                          <div>
		                          <span> <i class="notNull">*</i> 名称:</span>
		                          <span> <input name="name" value="" placeholder="请输入要新增的节点名"/>  </span>
		                          
		                          </div>
		                          
		                          <div>
			                          <span>描述:</span>
			                          <span>
			                           <textarea name="description" rows="3" cols="60"></textarea>
			                          </span>
		                          </div>
		                          
		                          <div class="operate_div">
		                           
		                           <div class="child_party_div">
			                           <a href="javascript:void(0);" onclick="javascript:insertChildNode()">创建子节点</a>
			                           <a href="javascript:void(0);" onclick="javascript:relationChildNode()">关联子节点</a>
			                           <a href="javascript:void(0);" onclick="javascript:delete_onclick('deleteChildNode')">删除本节点</a>
			                           <a href="javascript:void(0);"  onclick="javascript:delete_onclick('deleteChildRelation')">删除本关系</a>
		                           </div>
		                          
		                           <a href="javascript:void(0);" onclick="javascript:addViewRootNode()">创建根节点</a>
		                           <a href="javascript:void(0);"onclick="javascript:relationViewRootNode()">关联根节点</a>
		                          
		                          
		                          </div>
		                          
		          
		                           
		                           
		                           </div>
		                           
		                           
		                           
		                           <!-- end  详情 -->
		                        
		                        
		                        
		                        
      
							   
							     </div>
		                          
		                       
		                       </div>
		                     
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						
 						
 						
						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->
		
		
		<input type="hidden" name="view_id" id="view_id" value="${view_id }">
		<input type="hidden" name="parent_party_id" id="parent_party_id" value="">
		<input type="hidden" name="parent_party_name" id="parent_party_name" value="">
		<input type="hidden" name="parent_party_code" id="parent_party_code" value="${child_party_code }">
		<input type="hidden" name="child_party_id" id="child_party_id" value="">
		<input type="hidden" name="child_party_name" id="child_party_name" value="">
	
		<input type="hidden" name="is_inherit" value="1">
			
		<input type="hidden" name="cmdInput" id="cmdInput" value="">
		<input name="child_party_code" value="${child_party_code }" type="hidden" id="child_party_code"/>
		<input name="cmd" id="cmd" type="hidden">
		

</form>

<script type="text/javascript">



//关联跟节点
function relationViewRootNode(){
	
	$("#sData").attr("cmd","relationViewRootNode");
	$(".ui_inner_tan").show();
	$(".ui_out_tan").show();
	
}
//关联子节点
function relationChildNode(){
	
	$("#sData").attr("cmd","relationChildNode");
	
	//getNewReference('','form.child_party_id, form.child_party_name');
	$(".ui_inner_tan").show();
	$(".ui_out_tan").show();
	
   
	
}

//确认选择
function referenceSub(){

   var cmd=$("#sData").attr("cmd");

	selectRefernce();
    //getNewReference('','form.child_party_id, form.child_party_name');
     
	 var flag=commonSub(cmd,"");
		
	if(flag){
	     alert("关联完成！");
		window.location.href="${pageContext.request.contextPath}/pubController/managePartyTab";
	}
}
//选择团体类型
function  selectRefernce(){
	
	 $("#child_party_id").val("");
	 $("#child_party_name").val("");
	 var pointer=$("#partyIframe").contents().find("#grid-table");
	 var checkedVal=$(pointer).jqGrid("getGridParam", "selarrrow");

	 var checkedLen= checkedVal.length;
	
	 if(checkedLen==0){
		
		alert("请至少选择一条数据！");
		return false;
	 }
     if(checkedLen>1){
		
		alert("最多只能选择一条数据！");
		return false;
 	 }
     
     var selectedId=checkedVal[0];
     var rowData = $(pointer).jqGrid('getRowData',selectedId);
     
     $("#child_party_id").val(selectedId);
     $("#child_party_name").val(rowData.name);
     $(".ui_inner_tan").hide();
	  $(".ui_out_tan").hide();
}

//创建跟节点
function addViewRootNode(){
	
	if($("#party_type_id").val() == '<%=RmPartyTypeCache.getPartyType(IOrgauthConstants.OrgTree.USER.value()).getId()%>') {
		alert("请在“员工管理”节点中新增用户！");
		return false;
	}
	if(!validateForm()){
		
		return false;
	}

	
    var flag=commonSub("addViewRootNode","");
	
	if(flag){
		
		window.location.href="${pageContext.request.contextPath}/pubController/managePartyTab";
	}
	
	

	
	
}
//删除数据
function delete_onclick(cmd){
	
	$("#cmdInput").val("");
	$("#cmd").val(cmd);
	var param=$("form").serialize();
	ajaxGetParty('${pageContext.request.contextPath}/pubController/ajaxGetChildParty',param);

	window.location.href="${pageContext.request.contextPath}/pubController/managePartyTab";
	
}

//创建子节点
function insertChildNode(){
	
	var party_type_id=$("#party_type_id").val();
	debugger
	if(party_type_id == '<%=RmPartyTypeCache.getPartyType(IOrgauthConstants.OrgTree.USER.value()).getId()%>') {
		alert("请在“员工管理”节点中新增用户！");
		return false;
	}

	if(!validateForm()){
		
		return false;
	}
	
	var flag=commonSub("insertChildNode","");
	if(flag){
		
		var child_party_code=$("#child_party_code").val();
		window.location.href="${pageContext.request.contextPath}/pubController/managePartyTab?child_party_code="+child_party_code;
	}
	
}

//验证表单
function validateForm(){

	var selectedVal=$("select[name='party_type_id']").find("option:selected").val();
	if(!selectedVal){
		alert("请选择团体类型");
		return false;
	}
	var nameInp=$("input[name='name']").val();
	nameInp=$.trim(nameInp);
	if(!nameInp){
		alert("请输入节点名称！");
		return false;
		
	}
	
	return true;
}

//更新排序
function updateOrderCode_onClick(){
	
	//更新节点排队顺序
	if(!confirm("确认更新节点排序？")){
		return false;
	}
	
	commonSub("","updateOrderCode");

}

//更新树节点名称

function updateThisPartyName_onClick(){
	

	if(!confirm("确认更新选中的节点名称？")){
		return false;
	}
	
	commonSub("","updateThisPartyName");
	
}
   
   //公共提交部分

  function commonSub(cmd,cmdInp){
	  
	    $("#cmd").val(cmd);
		$("#cmdInput").val(cmdInp);
		var param=$("form").serialize();
		var flag=ajaxGetParty('${pageContext.request.contextPath}/pubController/ajaxGetChildParty',param);
		return flag;
  }


//获取组织树
function ajaxGetParty(url,param){
	
	var  flag=true;
	 $.ajax({
		    type:'post',//可选get
		    url:url,
		    data:param,//送评表的表格id属性值
		    dataType:'json',
		    async:false , //false为同步
		    success:function(data,status){
		    	var cvo=data.childPartyRelationVo;
		    	
		    	if(!cvo){//子节点团体关系
		    		
		    		$(".childParty").hide();
		    	    
		    	    $(".child_party_div").hide();
		    		
		    	}else{
		    		
		    		var child_party_name=cvo.child_party_name;
		    		
		    		var child_party_id=cvo.child_party_id;
		    		var parent_party_name=cvo.parent_party_name;
		    		
		    		var child_party_code=cvo.child_party_code;
		    		
		    		var order_code=cvo.order_code;
		    		var child_party_type_id=cvo.child_party_type_id;
		    	
		    		
		    		$("#child_party_name_span").text(child_party_name);
		    		$("#child_party_id_span").text(" (ID"+child_party_id+")");
		    		$("#parent_party_name_span").text(parent_party_name);
		    		$("#child_party_code_span").text(child_party_code);
		    		
		    		$("input[name='this_party_name']").val(child_party_name);
		    		$("input[name='order_code']").val(order_code);
		    		$("#party_type_id").val(child_party_type_id);
		    		$("#parent_party_id").val(child_party_id);
		    		$("#parent_party_name").val(child_party_name);
		    		$("#parent_party_code").val(child_party_code);
		    		
		    		$(".childParty").show();
		    	    
		    	    $(".child_party_div").show();
		    		
		    		
		    	}
		    	
		    	
		    	var listRmPartyTypeVos=data.listRmPartyTypeVos;
		    	var party_type_idSel="<option value=''>--请选择团体类型--</option>";
		    	
		    	if(listRmPartyTypeVos!=null){
		    	
		    		for(var i=0;i<listRmPartyTypeVos.length;i++){
		    			
		    			party_type_idSel+="<option value='"+listRmPartyTypeVos[i].id+"'id='"+listRmPartyTypeVos[i].bs_keyword+"' >"+listRmPartyTypeVos[i].name+"</option>";
		    		}
		    		
		    	}
		    	$("select[name='party_type_id']").html(party_type_idSel);
		    	
		    	flag=true;
		    	return flag;
			  
		   },
		   error:function(xhr,s1,s2){
				var  message= xhr.responseText;
		    	var  json = jQuery.parseJSON(message);

				if($.trim(json.exception).length==0){
					alert('数据异常,请刷新页面重试');
				}else{
					alert(json.exception);
				}
				
				flag=false;
				return flag;
				
		    }
		 });
	 
	 
	 
}


$(function(){
	
	var param=$("form").serialize();
	ajaxGetParty('${pageContext.request.contextPath}/pubController/ajaxGetChildParty',param);
});

</script>

<script type="text/javascript">
		<!--
		
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onDblClick: zTreeOnDblClick				
			}
		};

		var zNodes =[
		    <c:forEach items="${beans}" var="bean" varStatus="status">
			{ id:"${bean.child_party_id}", pId:"${bean.parent_party_id}", name:"${bean.child_party_name}",
				child_party_code:"${bean.child_party_code}", open:false}
			,
			</c:forEach>
		];
		
		$(document).ready(function(){

			window.focus();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
	//	${view_id}&amp;child_party_code=${bean.child_party_code}&amp;party_id=",
		var array = new Array();
		var myObjKeyValue = new Array(5);
		function onClick(event, treeId, treeNode, clickFlag) {
			
			//<input type="hidden" name="view_id" id="view_id" value="${view_id }">
			//<input type="hidden" name="parent_party_id" id="parent_party_id" value="">
			//<input type="hidden" name="parent_party_name" id="parent_party_name" value="">
			//<input type="hidden" name="parent_party_code" id="parent_party_code" value="${child_party_code }">
			//<input type="hidden" name="child_party_id" id="child_party_id" value="">
			//<input type="hidden" name="child_party_name" id="child_party_name" value="">
			var party_id=treeNode.id;
			$('#party_id').val(party_id);
			var party_name=treeNode.name;
			$('#party_name').val(party_name);
			var child_party_code=treeNode.child_party_code;
			$('#child_party_code').val(child_party_code);
			myObjKeyValue[0]=party_id;
			myObjKeyValue[1]=party_name;
			
			var view_id=$("#view_id").val().toString();
			
			var param={'view_id':view_id,'child_party_code':child_party_code,'party_id':''};
			ajaxGetParty('${pageContext.request.contextPath}/pubController/ajaxGetChildParty',param);
		}
		
		function zTreeOnDblClick(event, treeId, treeNode) {
			var party_id=treeNode.id;
			$('#party_id').val(party_id);
			var party_name=treeNode.name;
			$('#party_name').val(party_name);
			var child_party_code=treeNode.child_party_code;
			$('#child_party_code').val(child_party_code);
			myObjKeyValue[0]=party_id;
			myObjKeyValue[1]=party_name;
		};
		
		//-->
	</script>

	</body>
</html>
