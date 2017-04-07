/**
 * 应用公共的js方法放这
 */
//ajax提交插入
function submitInsertAjax(url,param,successToUrl){
 $.ajax({
    type:'post',//可选get
    url:url,
    data:param,//送评表的表格id属性值
    dataType:'json',
    async:false , //false为同步
    success:function(data,status){
	  if(data.status=="0"){
		  alert(data.message);
   	   }else{
   		alert(data.message);
	   	   window.location.href=successToUrl;
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
//ajax提交修改
function submitUpdateAjax(url,param,successToUrl){
 $.ajax({
    type:'post',//可选get
    url:url,
    data:param,//送评表的表格id属性值
    dataType:'json',
    async:false , //false为同步
    success:function(data,status){
	  if(data.status=="0"){
   	   	    alert(data.message);
   	   }else{
   		   alert(data.message); 
	   	   window.location.href=successToUrl;
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
//删除方法
function _delete(url) {
	var allcheck = $(".checkmain");
	var deletes = "";
	for (var g = 0; g < allcheck.length; g++) {
		var b = $(allcheck[g]).val();
		var c = $(allcheck[g]).attr("ischecked");
		if (c == "true") {
			deletes += b;
			if (g != allcheck.length -1) {
				deletes += ",";
			}
		}
	}
	if (deletes == "") {
		$.teninedialog({
             title:'请选择记录',
             content:'请至少选择一条记录'
         });
		return false;
	}
	 $.teninedialog({
         title:'系统提示',
         content:'确认删除所选记录?',
         otherButtons:["确定","取消"],
         otherButtonStyles:['btn-primary','btn-primary'],
         bootstrapModalOption:{keyboard: true},
         clickButton:function(sender,modal,index){
			if (index == 0) {
				form.action=url+"?ids=" + deletes;
		        form.submit();
			}
			$(this).closeDialog(modal);
         }
     });
}
//list页面排序：RM_ORDER_STR当前排序字段
function orderBy(RM_ORDER_STR){
	
	 if(RM_ORDER_STR!=''){
		 $(".sorting").each(function(){
            var sorting_str=$(this).attr("RM_ORDER_STR");
            if(RM_ORDER_STR.indexOf(sorting_str)>-1){
                if(RM_ORDER_STR.toLowerCase().indexOf("desc")>-1){
                	 $(this).addClass("sorting sorting_desc");
                    }else{
                    	 $(this).addClass("sorting sorting_asc");
                        }
              
                }
			 })
		 }
}

//给class=sorting绑定click方法
function sorting(url){
	$(".sorting").bind("click",function(){
		var sort=$(this).attr("class");
		var order_str='';
		if(sort.indexOf("sorting_desc")>-1){
			order_str=" ASC";
			}else{
				order_str=" DESC";
				}
		var orderBy_str=$(this).attr("RM_ORDER_STR");
		if(url.indexOf("?")>0){
			window.location=url+"&RM_ORDER_STR="+orderBy_str+order_str;
		}else{
			window.location=url+"?RM_ORDER_STR="+orderBy_str+order_str;
		}
		
	  })
}

//新增成功后调整到详细页面
function submitInsertAjaxToDetial(url,param,successToUrl){
	$.ajax({
	    type:'post',//可选get
	    url:url,
	    data:param,//送评表的表格id属性值
	    dataType:'json',
	    async:false , //false为同步
	    success:function(data,status){
		  if(data.status=="0"){
	   	   	    alert(data.message);
	   	   }else{
	   		   alert(data.message);
		   	   window.location.href=successToUrl+data.id;
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


/**
 * 隐藏map
 */
 function hiddeMap(){
	$(".modalDiv-backdrop").css({"display":"none"});
	$(".modalDiv").css({"display":"none"});
	
}
/**
 * 弹出层
 * @param url
 * @param queryBackParams
 */
function getNewReference(url,queryBackParams){
    	//"insertForm.associatedId,insertForm.fileType"
	    var searchParam=queryBackParams;
	    var commonUrl= $("#showDiv").attr("src")
	    $("#showDiv").attr("src",commonUrl);
	    $("#showDiv").attr("redirectUrl",url);
		$(".modalDiv-backdrop").css({"display":"inline"});
		$(".modalDiv").css({"display":"inline"});
		$("form[name='searchDialogForm']").find("input[name='searchParam']").val(searchParam)
	}
function getParty(url,formInputName,formName){
    //formInputName定义想要回写的名称和id的input的name

window.childWindow=window.open(url+'?cmd=department&show_bk=RM_ROOT,RM_dfhc,company,department &lazy_init=false&objKey='+formInputName+'&formName='+formName,'newwindow','height=500,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	  // getPartyWindowOpen(new Array(insertForm.branchCompanyId, insertForm.organization_name), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/orgauth/tree/orgWindowOpen.jsp?cmd=department&enableCookie=true&inputType=radio&show_bk=RM_ROOT,RM_dfhc')
window.childWindow.focus();   

}
window.onfocus = function focus(){//重写父窗口window对象中的onfocus，使其在获得焦点时执行以下代码
 if( typeof(window.childWindow)!="undefined"){//如果子窗口存在，将焦点转到子窗口
  window.childWindow.focus();
 }
}


window.onclick=function (){
if( typeof(window.childWindow)!="undefined"){//如果子窗口存在，将焦点转到子窗口
	   window.childWindow.focus();
	  }
}
//打开挂接用户party窗口
function getHangingUserParty(url,formInputName,formName){
    //formInputName定义想要回写的名称和id的input的name
	window.childWindow=window.open(url+'?cmd=department&is_allow_hanging_user=1&lazy_init=false&objKey='+formInputName+'&formName='+formName,'newwindow','height=500,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	window.childWindow.focus();   
}
//打开挂接角色party窗口
function getHangingRoleParty(url,formInputName,formName){
    //formInputName定义想要回写的名称和id的input的name
	window.childWindow=window.open(url+'?cmd=department&is_allow_hanging_role=1&lazy_init=false&objKey='+formInputName+'&formName='+formName,'newwindow','height=500,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	window.childWindow.focus();   
}
