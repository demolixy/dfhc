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
   		$(".ui_inner_tan").hide();
   		$(".ui_out_tan").hide();
   		$(".ui_inner_tan").find("form input").each(function(){
   			$(this).val("");
   			
   		});
   	   }
	//刷新表单
	   jQuery("#grid-table").trigger("reloadGrid");
   },
   error:function(xhr,s1,s2){
		var  message= xhr.responseText;
    	var  json = jQuery.parseJSON(message);

		if($.trim(json.exception).length==0){
			alert('数据异常,请刷新页面重试');
		}else{
			alert(json.exception);
		}
		$(".ui_inner_tan").hide();
		$(".ui_out_tan").hide();
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

$(function(){
	
	//影藏弹出层
	$("body").delegate(".cancel","click",function(){
		$(".ui_inner_tan").hide();
		$(".ui_out_tan").hide();
		
		//清空input
		$(".ui_inner_tan").find("input").each(function(){
   		
   			$(this).val("");
   			
   		});
		
	});

	
});

/**
 * 弹出层reference参照层
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

 //格式化列表中的日期字段
function formatDateRow(cellvalue, options, cell){
	
	return formatDate(cellvalue,'yyyy-MM-dd HH:mm:ss');
	
}
//格式化列表中的日期字段
function formatDateRow2(cellvalue, options, cell){
	
	return formatDate(cellvalue,'yyyy-MM-dd');
	
}
//根据毫秒数格式化时间
var formatDate = function(time, format){
	time=Number(time);

	if(time=='' || time==0 || isNaN(time)){
		
		return "";
	}
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
//状态汉化
function convert(cellvalue, options, cell){
	return statusConvert[cellvalue];
}
//值cellvalue, options:选中的列, row：行
function inputHou(cellvalue, options, row){

	return "<input name='"+options.colModel.name+"' value='' class='m-wrap'/>";
}

function isEmail(s) {  //是电子邮件

	if(s.length ==0 ) 
		return true;	

	if (s.length > 100)	{
		alert("email地址长度不能超过100位!");
       	return false;
	}

	var regu = /^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$/;

	if (regu.exec(s)) {		
		return true;
	} else {
		alert ("请输入有效合法的E-mail地址 ！");
		return false;
	}
}


//正则匹配金额(四位小数)
function regAmt(obj){
	
	var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
    if (!regu.test(obj)) {
    
		return false;
    }
    return true;
}

//正则匹配整数
function regNum(obj){
	var reg = /^[1-9]\d*$/;
	if(!reg.test(obj)){
		
         return false;
	}
	return true;
}
//验证车牌号
function regCarNum(obj){
	var reg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	return reg.test(obj);
	
}
//简单验证姓名是否是汉字
function regName(obj){
	var reg = /^[\u4e00-\u9fa5]{2,}$/;
	return reg.test(obj);
	
}
//验证手机号
function regPhoneNumber(obj){
	var reg = /^(13[0-9])|(147)|(17[0,6-9])|(15[^4,\D])|(18[0-9])\d{8}$/;
	return reg.test(obj);
	
}
//验证不是0开头的数字
function regNumber(obj){
	var reg = /^[1-9]{1}[0-9]{0,}$/;

	return reg.test(obj);
}

//验证身份证号
function isCardNo(card){  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  

   return  reg.test(card);
}
//清空全部form表单数据
function clearForm(obj){
	$(obj).find(":input").not(":button, :submit, :reset").val('').removeAttr("checked").remove("selected");
}
//清空form表单数据 （隐藏值不请空）
function clearForm2(obj){
	$(obj).parents().find(":input").not(":button, :submit, :reset, :hidden").val('').removeAttr("checked").remove("selected");
}