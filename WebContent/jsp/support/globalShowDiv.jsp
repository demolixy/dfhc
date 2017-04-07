<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<%@ include file="/jsp/include/rmGlobal.jsp" %>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>选择参照</title>
</head>
<body class="referencePage">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr height="95%">
    <td>
		<iframe name="referenceContent" width="100%" height="100%" >
		
		</iframe>
	</td>
  </tr>
  <tr height="5%">
    <td align="center" style="padding-top: 10px">
       <span style="padding-top: 10px">
        <input type="hidden" name="redirectUrl" id="redirectUrl">
	    <input type="button" class="button_ellipse" name="button_ok" value="确定" onClick="ok_onClick();">&nbsp;&nbsp;&nbsp;&nbsp;
	    <input type="button" class="button_ellipse" name="button_cancel" value="取消" onClick="cancel_onClick();">
	    </span>
    </td>
  </tr>
</table>
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
})
     //获取父窗口对象
    var parentDocument=window.parent.document;
    // 获取需要返回值得参数，
    var searchParam=$(parentDocument).find("form[name='searchDialogForm']").find("input[name='searchParam']").val();
    //通过","分割，转为数组型参数name
    var paramArray=searchParam.split(',');
	function ok_onClick(){
		var rtObject = null;
		var thisInputObj = referenceContent.document.getElementsByName('checkbox_template');
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
		var ids = findSelectionByBoot(thisInputObj,"id");  //取得多选框的选择项(bootstrap)
		if(ids == null) {  //如果ids为空
	  		alert("请选择一条记录!")
	  		return false;
		}
		if(referenceContent.document.form.referenceInputType.value == "radio" && ids.length>1){
			alert("只能选择一条记录!")
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
				//thisInputObj[j].value
				if(thisInputObj[j].attributes['checkedId'].nodeValue== ids[i]) {
					for(var k=0; k<objKeys.length; k++) {
						objs[objKeys[k]].push(thisInputObj[j].getAttribute(objKeys[k]));
					}
					break;
				}
			}
		}
		for(var k=0; k<objKeys.length; k++) {
			rtObject[objKeys[k]] = objs[objKeys[k]] + "";
		}

		return rtObject;
	}
	//bootStrap 获取当前选中的选项
	function findSelectionByBoot(checkboxName, idName) {  //从列表中找出选中的id值列表
		var elementCheckbox = checkboxName;  //通过name取出所有的checkbox
		var number = 0;  //定义游标
		var ids = null;  //定义id值的数组
		for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
			 //如果被选中
			if(elementCheckbox[i].attributes['ischecked'].nodeValue==true || elementCheckbox[i].attributes['ischecked'].nodeValue=='true') {  //如果被选中
				number += 1;  //游标加1
				if(ids == null) {
					ids = new Array(0);
				}
			//	ids.push(elementCheckbox[i].value);  //加入选中的checkbox
		        ids.push(elementCheckbox[i].attributes['checkedId'].nodeValue);  //加入选中的checkbox
			}
		}
	
		return ids;
	}
	
	
	
	
	//关闭弹出层
	function cancel_onClick(){
		$(parentDocument).find(".modalDiv-backdrop").css({"display":"none"});
		$(parentDocument).find(".modalDiv").css({"display":"none"});
		//初始化请求
		$(parentDocument).find("iframe[name='showDiv']").attr('src','<%=request.getContextPath()%>/jsp/support/globalShowDiv.jsp');
	}


	//页面加载完成执行
	window.onload=function(){
		var url=$("#redirectUrl").val();
	    //获取父窗口对象
		$("iframe[name='referenceContent']").attr('src',url);
	}
	
//-->
</script>
