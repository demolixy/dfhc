<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<%@ include file="/jsp/include/rmGlobal.jsp" %>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>选择参照</title>
<style type="text/css">

.ref_div{
width: 100%;text-align: center;height: 10%;line-height:50px;
}
.ref_div a{

text-decoration: none;
 background-color: #329cb9;
color: #fff;
padding: 5px 20px;
}
.ref_div a:hover{
   background-color: #10708a;

}

</style>
</head>
<body class="referencePage">
<div style="width: 100%;height: 100%">
 
 <div style="width: 100%;height: 90%">
 
   <iframe name="referenceContent" width="100%" height="100%" >
		
  </iframe>
 </div>
 
 <div class="ref_div">
 
        <input type="hidden" name="redirectUrl" id="redirectUrl">
	    <a href="javascript:void(0);"  onClick="javascript:ok_onClick();">确认选择</a>&nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="javascript:void(0);"  onClick="javascript:cancel_onClick();">取消</a>
 
 </div>


</div>

<form id="form" name="form" method="post">
	<input type="hidden" id="rmCheckReturnValue" name="rmCheckReturnValue" value="">
	
</form>
</body>
</html>
<script type="text/javascript">

<!--

$(function(){
	var parentDocumentDiv=window.parent.document;
	var url=$(parentDocumentDiv).find("iframe[name='showDiv']").attr('redirectUrl');
	$("#redirectUrl").val(url);
});
     //获取父窗口对象
    var parentDocument=window.parent.document;
    // 获取需要返回值得参数，
    var searchParam=$(parentDocument).find("form[name='searchDialogForm']").find("input[name='searchParam']").val();
    //通过","分割，转为数组型参数name
    var paramArray=searchParam.split(',');
	function ok_onClick(){
		var rtObject = null;

		var thisInputObj = referenceContent.document.getElementsByClassName('cbox');
		try {
			rtObject = document.frames['referenceContent'].toDoReadReference(thisInputObj);
			
		} catch(e) {
			rtObject = toDoReadReference(thisInputObj);
		}
		
		//给传递过来的参数赋值
		if(rtObject) {
			returnValue(rtObject);
		}
		
	}
	function returnValue(rtObject){
		var returnArrayValue=new Array();
		for(var rto in rtObject) {
			 var s=rtObject[rto];
			 returnArrayValue.push(s);
		}
		// 循环参数
	 	for(var i=0;i<paramArray.length;i++){
	 		var returnArray=paramArray[i].split('.');
	 		var formName=returnArray[0];//要获取返回值的form名称
	 		var inputName=returnArray[1];// 要获取返回值的input名称
			$(parentDocument).find("form[name='"+formName+"']").find("input[name='"+inputName+"']").val(returnArrayValue[i]);
		}
	 	cancel_onClick();//关闭层
		
	}
	function toDoReadReference(thisInputObj) {
		var ids = findSelectionByGrid(thisInputObj,"id");  //使用使用jqgrid 展示后获取页面

		if(ids == null) {  //如果ids为空
	  		alert("请选择一条记录!");
	  		return false;
		}
		if(referenceContent.document.form.referenceInputType.value == "radio" && ids.length>1){
			alert("只能选择一条记录!");
	  		return false;
			}
		var objKeys = ["displayName"];
		if(referenceContent.objKeys != null) {
			objKeys = referenceContent.objKeys;
		}
		
		var rtObject = new Object();
		rtObject.realValue = ids + "";
		var objs = {};
		for(var k=0; k<objKeys.length; k++) {
			objs[objKeys[k]] = [];
		}
		for(var i=0; i<ids.length; i++) {
			for(var j=0; j<thisInputObj.length; j++){  //循环checkbox组

                var ele=thisInputObj[j];

                if($(ele).is(":checked")){//选中

                   var pointer=$(ele).parents("tr");//保存选中行的指针
     				var checkedId=$(pointer).attr("id");//选中行的id
     				var noVisiableTd=$(pointer).find("td").not(":visible");
     				if(checkedId== ids[i]) {
     					for(var k=0; k<objKeys.length; k++) {
         					var tdText=noVisiableTd[k].innerText;
         					tdText=$.trim(tdText);
         					var objKey=objKeys[k];
     						objs[objKey].push(tdText);
     					}
     					break;
     				}
     				
                    
                }

               
			}
		}
		for(var k=0; k<objKeys.length; k++) {
			rtObject[objKeys[k]] = objs[objKeys[k]] + "";
		}

		return rtObject;
	}
	//bootStrap 获取当前选中的选项
	function findSelectionByGrid(checkboxName, idName) {  //从列表中找出选中的id值列表

		var elementCheckbox = checkboxName;  //通过name取出所有的checkbox
	
		var ids = null;  //定义id值的数组
		for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
			var ele=elementCheckbox[i];
			 
            if($(ele).is(":checked")){
                
           	 if(ids == null) {
					ids = new Array(0);
				}
 				var checkedId=$(ele).parents("tr").attr("id");//选中行的id
 				ids.push(checkedId);
                
            }
		}
	
		return ids;
	}
	
	
	
	
	//关闭弹出层
	function cancel_onClick(){
		$(parentDocument).find(".modalDiv-backdrop").css({"display":"none"});
		$(parentDocument).find(".modalDiv").css({"display":"none"});
		//初始化请求
		$(parentDocument).find("iframe[name='showDiv']").attr('src','<%=request.getContextPath()%>/jsp/include/web/jqGrid/globalShowDiv.jsp');
	}


	//页面加载完成执行
	window.onload=function(){
		var url=$("#redirectUrl").val();
	    //获取父窗口对象
		$("iframe[name='referenceContent']").attr('src',url);
	}
	
//-->
</script>
