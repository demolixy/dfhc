//ajax提交新增
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
   	   successToUrl = successToUrl.replace("_id_",data.bean.id);
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


//ajax提交删除
function submitDeleteAjax(url,successToUrl){
 $.ajax({
    type:'post',//可选get
    url:url,
    dataType:'json',
    async:false , //false为同步
    success:function(data,status){
	  if(data.status=="0"){
   	   	    alert(data.message);
   	   }else{
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


//从表单跳转到页面展示页面
function showForm(context,formObj){
	formObj.action = context+"/generalbusinessprocess/showForm";
	formObj.submit();
}


//从url跳转到页面展示页面
function showFormByUrl(context,params){
	window.location.href =  context+"/generalbusinessprocess/showForm"+params;
}


//启动流程
function startProcess(procDefId,context,formObj){
	formObj.action = context+"/businessAppController/start?processDefinitionId="+procDefId;
	formObj.submit();
}


//任务下一步
function taskNextStep(taskId,context,formObj,params){
	formObj.action = context+"/generalbusinessprocess/complete/"+taskId+"?"+params;
	formObj.submit();
}


function ajaxFileUpload(context,fileNameId,hideIdName,rootPath){
	$("#"+hideIdName).val(rootPath);
    $.ajaxFileUpload({  
        //url:context+'/generalbusinessprocess/fileUpload',  
        url:context+'/generalbusinessprocess/springUpload?path='+rootPath,
        secureuri:false,                           //是否启用安全提交,默认为false   
        fileElementId:fileNameId,                  //文件选择框的id属性  
        dataType:'json',                           //服务器返回的格式,可以是json或xml等  
        success:function(data,status){
			  if(data.status=="0"){
		   	   	    alert(data.message);
		   	   }else{
			   	   alert("上传成功");
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

//获取当前时间yyyyMMdd格式的时间字符串
function getNowFormatDate() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + month + strDate;
    return currentdate;
}

//生成从minNum到maxNum的随机数
function randomNum(minNum,maxNum){ 
    switch(arguments.length){ 
        case 1: 
            return parseInt(Math.random()*minNum+1,10); 
        break; 
        case 2: 
            return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10); 
        break; 
            default: 
                return 0; 
            break; 
    } 
} 

function getFileName(o){
    var pos=o.lastIndexOf("\\");
    return o.substring(pos+1);  
}

function pathdownload(context){
	var path = $("#filePath").val();
	path = decodeURIComponent(path,true);
	path = encodeURI(encodeURI(path));
	window.location.href=context+"/pubController/pathdownload?relaPath="+path;
}