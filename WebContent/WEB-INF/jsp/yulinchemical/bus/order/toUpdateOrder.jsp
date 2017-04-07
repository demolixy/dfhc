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

<script src="<%=request.getContextPath()%>/js/jqGrid/json2.js"></script>
<script type="text/javascript">

//增加产品名称

function  addProDetail(){
	if(checkNumber("insertForm")){
		var dataObj=getFormData("insertForm");
		var id=dataObj.productId;
		var productLen=$("#"+id).length;
		if(productLen>0){
			alert("该类型产品已经 选择,请选择其他类型产品");
			return  false;
		}
		orderAllPrice(dataObj.allPrice);
		jqData.push(dataObj);
		jQuery("#grid-table").jqGrid("addRowData", id, dataObj, "last");
		clearClose();//关闭层
		$("#insertForm").find("input").each(function(){
			
			$(this).val("");
		});
	}

	
}

function getFormData(formid){
	var dataObj=new Object();
	$("#"+formid).find("input").each(function(){
		
		var ketN=$(this).attr("name");
		var ketV=$(this).val();
		
		dataObj[ketN]= ketV;
	});

	return dataObj;
}


function checkNumber(formid){
	var productName=$("#"+formid).find("input[name=productName]").val();
	if($.trim(productName).length==0){
		alert("产品名称为空");
		return false;
	}
	 var plannedVolume=$("#"+formid).find("input[name=plannedVolume]").val();
	 if(!regAmt(plannedVolume)){
		 alert("发运量只能为数值，最多可有4位小数");
		 return false;
	 }
	 var unitPrice=$("#"+formid).find("input[name=unitPrice]").val();
	 if(!regAmt(unitPrice)){
		 alert("单价只能为数值，最多可有4位小数");
		 return false;
	 }
	 var carNum=$("#"+formid).find("input[name=carNum]").val();
	 if(!regNum(carNum)){
		 alert("车辆数只能为整数");
		 return false;
	 }
	 
	 
// 	 var remarkVolume=$("#"+formid).find("input[name=remarkVolume]").val();
// 	 if(!regNum(remarkVolume)){
// 		 alert("剩余发运量只能为整数");
// 		 return false;
// 	 }

	 var remainderCarNum=$("#"+formid).find("input[name=remainderCarNum]").val();
	 if(!regNum(remainderCarNum)){
		 alert("剩余车辆数只能为整数");
		 return false;
	 }
	return true;
}

//关闭
function clearClose(){
	
	$(".ui_inner_tan").hide();
	$(".ui_out_tan").hide();
}

//删除数据
function _delete(){
  
	var selectedId = $("#grid-table").jqGrid("getGridParam", "selarrrow");
   
	if(selectedId.length==0){
		alert("请选择要删除的数据!");
		return  false ;
		
	}else{
		var copyNum=new Array();
		
		for(var i=0;i<selectedId.length;i++){
			
			copyNum.push(selectedId[i]);
		}
		var data={};
		for(var i=0;i<jqData.length;i++){
			
			var productId=jqData[i].productId;
			if(selectedId==productId){
				data=jqData[i];
				break;
			}
		}
		for(var num=0;num<copyNum.length;num++){
		
			for(var i=0;i<jqData.length;i++){
				
				var vo=jqData[i];
				var productId=vo.productId;
				if(productId==copyNum[num]){
					jqData.splice(i,1);
					break ;
				}
				
			}
			
			$("input[name=orderAllPrice]").val(Number($("input[name=orderAllPrice]").val())-Number(data.allPrice));
    		
    		$("input[name=amount]").val(Number($("input[name=amount]").val()) + Number(data.allPrice));
			
			$("#grid-table").jqGrid('delRowData', copyNum[num]);
			
		}
		
	}

	clearClose();//隐藏删除的数据
}

//修改table 中的数据
function edit(){
	if(checkNumber("updateForm")){
	
		 var dataObj=getFormData('updateForm');
		 var productId=dataObj.productId;//选择产品id
		 
		 for(var i=0;i<jqData.length;i++){
			 
			 var proId=jqData[i].productId;
			 if(proId==productId){
				 
				 jqData.splice(i,1);//删除该条记录
				 break;
				 
				 
			 }
			 
		 }
  		jqData.push(dataObj);
    	 $("#grid-table").jqGrid('setRowData', productId, dataObj);
 
         	clearClose();//关闭
    		$("#updataForm").find("input").each(function(){
    			
    			$(this).val("");
    		});
    		 //修改前金额	 
            var prePrice = Number($("form[name=updateForm]").find("input[name=allPrice2]").val());	
            //修改后金额
            var sufPrice = Number($("form[name=updateForm]").find("input[name=allPrice]").val());	
            
            orderAllPrice(sufPrice-prePrice);    
	}
}


//同步剩余车辆数
function changeCarNum(obj){
	
	var carNum=$(obj).val();
	if(carNum.length>0){
		var reg = /^[1-9]\d*$/;
		if(!reg.test(carNum)){
			 alert("请重新输入车数量(必须为整数)!");
	         return false;
		}
	}

	
	$(obj).parents("form").find("input[name='remainderCarNum']").val(carNum);
	//自动算出计划发运量
// 	var  plannedVolume=Number(carNum)*25;
	
// 	$(obj).parents("form").find("input[name='plannedVolume']").val(plannedVolume);
	
	//计划发运量改变相关数据
// 	changePlannedVolume($(obj).parents("form").find("input[name='plannedVolume']"));
	
}
//发运量同步到剩余发运量
function changePlannedVolume(obj){
	
	var plannedVolume=$(obj).val();
	if(plannedVolume.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (!regu.test(plannedVolume)) {
	    	alert("请重新输入计划发运量(大于0,最多四位小数)!");
			return false;
	    }
	}
	var $_form = $(obj).parents("form");
	$($_form).find("input[name='remarkVolume']").val(plannedVolume);

	//自动计算车数量
	var carNum=Math.ceil(plannedVolume/25);
	$($_form).find("input[name='carNum']").val(carNum);
	$($_form).find("input[name='remainderCarNum']").val(carNum);
	
	var unitPrice = $($_form).find("input[name=unitPrice]").val();
	if(!unitPrice || unitPrice.length == 0){
		return false;
	}
	if(!plannedVolume || plannedVolume.length == 0){
		return false;
	}
	getAllPrice(unitPrice, plannedVolume, $_form);
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

//保存 提交
function doSaveOrCommit(isSaveOrCommit){
	var msg = "本次订单合同金额 " + $("input[name=orderAllPrice]").val() + "元，剩余业务金额 " + $("input[name=amount]").val() + "，是否继续？";
	if(!confirm(msg)){
		return false;
	}
	//表单验证通过
	if(doSaveOrCommitCheckForm()){
	
		$("#save").attr("disabled","disabled");
		$("#commit").attr("disabled","disabled");
		$("#audit").attr("disabled","disabled");
		
		if(jqData.length==0){
			
			alert("请选择至少一条产品信息！");
			return false;
		}
		
		var jqDataStr=JSON.stringify(jqData); ;
		$("#zongForm").find("input[name='jqData']").val(jqDataStr);
	
		 $.ajax({
			    type:'post',//可选get
			    url:"${pageContext.request.contextPath}/order/doAddOrUpdateOrder?operation=${operation}&isSaveOrCommit="+isSaveOrCommit,
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
	                    window.location.href="${pageContext.request.contextPath}/order?typeLu=saleOrderManager&operation=${operation}&pcode=${pcode}";
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
}

//保存提交验证主表信息
function doSaveOrCommitCheckForm(){
	return true;
}
function calculateAllPrice(obj){
	var unitPrice = $(obj).val();
	if(!unitPrice || unitPrice.length == 0){
		return false;
	}
	var plannedVolume = $(obj).parents("form").find("input[name=plannedVolume]").val();
	if(!plannedVolume || plannedVolume.length == 0){
		return false;
	}
	getAllPrice(unitPrice, plannedVolume);
	
	
}
function getAllPrice(obj1, obj2, obj3){
	var unitPrice = Number(obj1);
	var plannedVolume = Number(obj2);
	if(!obj3){
		$("form[name=insertForm]").find("input[name=allPrice]").val(unitPrice*plannedVolume);
	}else{
		$(obj3).find("input[name=allPrice]").val(unitPrice*plannedVolume);
	}
}
function orderAllPrice(price){
	var orderAllPrice = $("input[name=orderAllPrice]").val();
	
	var amount = $("input[name=amount]").val();
	
	var subPrice = Number(amount)-Number(price);
	//隐藏总价 用来最后提示
	orderAllPrice = Number(orderAllPrice) + Number(price);
	$("input[name=orderAllPrice]").val(orderAllPrice);
	$("input[name=amount]").val(subPrice);
	if(subPrice > 0){
		$("input[name=amount]").removeAttr("style");
	}else{
		$("input[name=amount]").attr("style", "color: red;");
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
							
							
<%--						<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("orderId")%></span><input type="text" class="m-wrap"  name="orderId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/order/reference?referenceInputType=radio','insertForm.orderId,insertForm.orderIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%></span><input class="m-wrap" name="orderNum"  type="text"  						maxLength="13" />
							</div>
 --%>	
							
							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span>
							  <input type="text" class="m-wrap"  name="productName" readonly="readonly" />
							  <input type="hidden" class="m-wrap"  name="productId" />
							    <input type="hidden" class="m-wrap"  name="productTypeId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio&main_secondary_product_flag=02&operation=productPrice','insertForm.productId,insertForm.productName,insertForm.productTypeId,insertForm.unitPrice');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("plannedVolume")%></span>
							  
							  <input class="m-wrap" name="plannedVolume"  type="text"  	maxLength="14" onchange="javascript:changePlannedVolume(this)"/>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("unitPrice")%></span><input class="m-wrap" name="unitPrice"  type="text"  		readonly="readonly"	onchange="calculateAllPrice(this);"			maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("carNum")%></span>
							  
							  <input class="m-wrap" name="carNum"  type="text" 	maxLength="10"  onchange="javascript:changeCarNum(this)"/>
							</div>
							<div class="input-prepend">
							 <span class="add-on">合同金额</span>
							  
							  <input class="m-wrap" name="allPrice"  type="text" readonly="readonly"	maxLength="10"  />
							</div>

											<div class="input-prepend" style="display: none;">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remarkVolume")%></span><input class="m-wrap" name="remarkVolume"  type="text"  	  readonly='readonly'						maxLength="14" />
							</div>

							
							<div class="input-prepend" style="display: none;">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%></span><input class="m-wrap" name="remainderCarNum"  type="text"   readonly='readonly'						maxLength="10" />
							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:addProDetail()">
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

	

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span>
							  <input type="text" class="m-wrap"  name="productName" readonly="readonly" />
							  <input type="hidden" class="m-wrap"  name="productId" />
							    <input type="hidden" class="m-wrap"  name="productTypeId" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("plannedVolume")%></span>
							  
							  <input class="m-wrap" name="plannedVolume"  type="text"  	maxLength="14" onchange="javascript:changePlannedVolume(this)"/>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("unitPrice")%></span><input class="m-wrap" name="unitPrice"  type="text"  		readonly="readonly"	onchange="calculateAllPrice(this);"			maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("carNum")%></span>
							  
							  <input class="m-wrap" name="carNum"  type="text" 	maxLength="10"  onchange="javascript:changeCarNum(this)"/>
							</div>
							<div class="input-prepend">
							 <span class="add-on">合同金额</span>
							  
							  <input class="m-wrap" name="allPrice"  type="text" readonly="readonly"	maxLength="10"  />
							  <input class="m-wrap" name="allPrice2"  type="hidden" readonly="readonly"	maxLength="10"  />
							</div>

											<div class="input-prepend" style="display: none;">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remarkVolume")%></span><input class="m-wrap" name="remarkVolume"  type="text"  	  readonly='readonly'						maxLength="14" />
							</div>

							
							<div class="input-prepend" style="display: none;">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%></span><input class="m-wrap" name="remainderCarNum"  type="text"   readonly='readonly'						maxLength="10" />
							</div>



							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:edit()">
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
 
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%></span><input class="m-wrap" name="orderNum"  type="text"  						maxLength="13" />
							</div>
--%>
							<div class="input-prepend">
							  <span class="add-on">产品类别</span>	<input type="text" class="m-wrap"  readonly="readonly" name="productTypeIdName" />			<input type="hidden" class="m-wrap"  name="productTypeId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/producttype/reference?referenceInputType=radio','form.productTypeId,form.productTypeIdName');">选择</span>
							</div>

			<%-- 			<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" />
							</div>
--%>	
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span>	<input type="text" class="m-wrap" readonly="readonly" name="productName" />			<input type="hidden" class="m-wrap"  name="productId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','form.productId,form.productName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("plannedVolume")%></span><input class="m-wrap" name="plannedVolume"  type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("unitPrice")%></span><input class="m-wrap" name="unitPrice"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("carNum")%></span><input class="m-wrap" name="carNum"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remarkVolume")%></span><input class="m-wrap" name="remarkVolume"  type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%></span><input class="m-wrap" name="remainderCarNum"  type="text"  						maxLength="10" />
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
								<a href="#">副产品销售管理</a>
							</li>
							<li class="active">销售订单管理</li>
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
		          <form name="zongForm" id="zongForm"  method="post"  >
		                     
		                     <!-- 存放jqGrid -->
		                     <input type="hidden" value="" name="jqData" id="jqData"/>
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                 <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
		                        			<input  type="hidden"  name="id"  value="${bean.id}"/>
			                         <tbody>
			                         
			                      <tr>
		                           
			                             <th><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%>:</th>
			                             <td><c:out value="${bean.orderNum}"></c:out>   </td>
			                             <th><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("status")%>:</th>
			                             <td><bean:define id="status" name="bean" property="status"/>
										<c:out value="<%=RmGlobalReference.get(ISystemConstant.DICTIONARY_ORDER_STATUS, status)%>"></c:out>
			                              </td>
			                             <th><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>:</th>
			                            
			                             <td><input class="tab-inp" name="customerName"  type="text" readonly="readonly" maxLength="19"  value="<c:out value="${bean.customerName}"></c:out>" />   </td>
			                 		    <input  type="hidden"  name="customerId"  value="${bean.customerId}"/>
			                          
			                          </tr>

			                          <tr>
		                           
 			                             <th><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryStartDate")%>:</th>
			                             <td colspan="2"><input type="text"  class="tab-inp date-picker" readonly name="deliveryStartDate"   value="<c:out value="${bean.deliveryStartDate}"></c:out>" />   </td>
			                            
			                             <th><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%>:</th>
			                             <td colspan="2"><input type="text" class="tab-inp"  name="shipMode"  readonly="readonly" value="${bean.shipMode}"/> <input type="hidden" value="${bean.shipModeId }"  class="tab-inp" name="shipModeId"/> 
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipmode/reference?referenceInputType=radio','zongForm.shipModeId,zongForm.shipMode');">选择</span>
			                             </td>
			                          </tr>
			                          <tr>
			                          	<th>合同金额:</th>
			                          	<td colspan="2">
			                          	<input type="text" class="tab-inp"  readonly="readonly" name="orderAllPrice"/>
			                          	</td>
			                          	<th>业务余额:</th>
			                          	<td colspan="2">
			                          		<input type="text" class="tab-inp"  readonly="readonly" value="${lineOfCredit}"  name="amount2"/>
			                          	</td>
			                          	
			                          </tr>
			                          <tr>
			                          	<th>剩余业务余额:</th>
			                          	<td colspan="2">
			                          		<input type="text" class="tab-inp"  name="amount" readonly="readonly"/>
			                          	</td>
			                          	
			                          	
			                          </tr>
			                         </tbody>
		                         </table>


							<div class="row" style="height: 300px;">
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
		                     

		                      <!-- begin 操作按钮 -->
		                         <div class="opt-div">
		                         
		                          <a href="javascript:void(0);" class="save-btn-shi" id="save" onclick="javascript:doSaveOrCommit('save')">保存</a>
				<c:choose>
					<c:when test="${empty operation }">
		                          <a href="javascript:void(0);" class="sub-btn-shi"  id="audit"   onclick="javascript:doSaveOrCommit('audit')" >审核</a>
					</c:when>
					<c:otherwise>
		                          <a href="javascript:void(0);" class="sub-btn-shi"  id="commit"   onclick="javascript:doSaveOrCommit('commit')" > 提交</a>
					</c:otherwise>
				</c:choose>		                         
		                         
		                         </div>
		                         <!-- end 操作按钮 -->
		                          

		                     
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
		
			 var jqData=[];
			 var allPrice = 0;
			 <c:forEach items="${beans}" var="bean"> 
				var dataObj1=new Object();
				allPrice += Number('${bean.plannedVolume}')*Number('${bean.unitPrice}');
				dataObj1['id']= '${bean.productId}';
				dataObj1['productTypeId']= '${bean.productTypeId}';
				dataObj1['productId']= '${bean.productId}';
				dataObj1['productName']= '${bean.productName}';
				dataObj1['plannedVolume']= '${bean.plannedVolume}';
				dataObj1['unitPrice']= '${bean.unitPrice}';
				dataObj1['carNum']= '${bean.carNum}';
				dataObj1['remarkVolume']= '${bean.remarkVolume}';
				dataObj1['remainderCarNum']= '${bean.remainderCarNum}';
				jqData.push(dataObj1);
		   </c:forEach> 
		   
		   $(function(){
			   $("input[name=orderAllPrice]").val(allPrice);
			   var amount = $("input[name=amount2]").val();
			   amount = Number(amount) - Number(allPrice);
			   if(amount > 0){
				   $("input[name=amount]").removeAttr("style");
			   }else{
				   $("input[name=amount]").attr("style", "color:red;");
			   }
			   $("input[name=amount]").val(amount);
		   })
			jQuery(function($) {

					
			   
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";
				
				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					data:jqData,
					datatype: "local",
				    mtype: 'POST',  
					height: "100%",
					colNames:[
							    '<%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
								,'<%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("plannedVolume")%>'
								,'<%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("unitPrice")%>'
								,'<%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("carNum")%>'
								,'<%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remarkVolume")%>'
								,'<%=IOrderProductDetailConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%>'
								],
					colModel:[
								
							
								{name:'productName',index:'productName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
								{name:'plannedVolume',index:'plannedVolume', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
								{name:'unitPrice',index:'unitPrice', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
								{name:'carNum',index:'carNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
								{name:'remarkVolume',index:'remarkVolume', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
								{name:'remainderCarNum',index:'remainderCarNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}}
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
					caption: "销售订单产品明细列表展示",
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
			    	 
		    		var selectedId = $("#grid-table").jqGrid("getGridParam", "selarrrow");
		    	
		    		if(selectedId.length!=1){
		    			alert('请选择一条数据进行修改');
		    			return false;
		    		}
		    		var data={};
		    		for(var i=0;i<jqData.length;i++){
		    			
		    			var productId=jqData[i].productId;
		    			if(selectedId==productId){
		    				data=jqData[i];
		    				break;
		    			}
		    		}
		    		 
	           
		    		$("#portlet-config-edit").find("input[name=productTypeId]").val(data.productTypeId);
                    $("#portlet-config-edit").find("input[name=productId]").val(data.productId);
                    $("#portlet-config-edit").find("input[name=productName]").val(data.productName);
                    $("#portlet-config-edit").find("input[name=plannedVolume]").val(data.plannedVolume);
                    $("#portlet-config-edit").find("input[name=unitPrice]").val(data.unitPrice);
                    $("#portlet-config-edit").find("input[name=carNum]").val(data.carNum);
                    $("#portlet-config-edit").find("input[name=remarkVolume]").val(data.remarkVolume);
                    $("#portlet-config-edit").find("input[name=remainderCarNum]").val(data.remainderCarNum);
                    $("#portlet-config-edit").find("input[name=allPrice]").val(Number(data.plannedVolume)*Number(data.unitPrice));
                    $("#portlet-config-edit").find("input[name=allPrice2]").val(Number(data.plannedVolume)*Number(data.unitPrice));
                  
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
			
			
			});
		</script>
	</body>
</html>
