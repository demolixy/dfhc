<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>榆林-装车单</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="/jsp/android/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqGrid/app-pub.js"></script>
</head>

<body class="zcWrap">

<!--begin 头-->
 <div class="zc_header">

   <%@ include file="/jsp/android/header.jsp" %>
   <%@ include file="/jsp/android/labels.jsp" %>

   
   
 </div>
 <!--end 头-->

 <!--begin 内容-->

  <div class="zc_content">

    <div class="zc_list">

    </div>

  </div>

 <!--end 内容-->

<div class="lazy" style="display:block;" >
    <p><i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i> <span class="sr-only">Loading...</span></p>
    <p>Loading...</p>
</div>
<input type="hidden" name="errMsg" value="${errMsg }"/>
</body>
<script type="text/javascript">

var scroll;
$(function(){
    scroll=new scroll_load();
    scroll.setting.data_url = "${pageContext.request.contextPath}/api/loadingnotice/handList";
    scroll.setting.callback = function (data) {
    	
        /*通常result返回的json 这里再进行拼接处理*/
    	var dataJson = data.list;
    	//获取模板上的HTML  
        var html; 
    	if('${operation}' == '07'){
    		html = $('script[type="text/template1"]').html(); 
    	}else{
    		html = $('script[type="text/template"]').html(); 
    	}
    	
        //定义一个数组，用来接收格式化合的数据  
        var arr = [];  
        //对数据进行遍历  
        var seq = (scroll.setting.pageIndex-1)*scroll.setting.pageSize;
        $.each(dataJson, function(i, o) {  
        	seq += 1;
        	o.seq = seq;
            o.label = "";
            if(o.safeCheckStatus == '1'){
            	o.safeCheckStatus = "通过";
            }else{
            	o.safeCheckStatus = "不通过";
            }
            if(o.safeCheckTime && o.safeCheckTime.length > 0){
            	o.safeCheckTime = formatDate(o.safeCheckTime, "yyyy-MM-dd HH:mm:ss");
            }
            if(o.ladingBillStatus == '2'){
            	o.label = '<a href="javascript:void(0);" status="' + o.ladingBillStatus + '" class="item_btn" id="' + o.id + '">检查</a>';
            }else{
            	o.label = '<a href="javascript:void(0);" class="item_btn" id="' + o.id + '">查看</a>';
            }
            arr.push(formatTemplate(o, html));  
        });  
//         var aList = $('.zc_list');
    	 $('.zc_list').append(arr.join(''));
    	
        
    }
    scroll.Init();
})
$(function(){
	var errMsg = $("input[name=errMsg]").val();
	
	if(errMsg && errMsg.length > 0){
		zfalert('系统提示',errMsg,function(){ 
	          $(this).hide();
	    });
	}
	$(".zc_list").delegate(".zc_list_item .item_btn", "click", function(){
		
		var id = $(this).attr("id");
		var status = $(this).attr("status");
		if(status && status=='2'){
			window.location = "${pageContext.request.contextPath }/api/vehiclesafetyinspection/gotoSecurity?id=" + id.toString()+"&checkType="+${operation};
		}else{
			window.location = "${pageContext.request.contextPath }/api/vehiclesafetyinspection/safeCheck?ladingBillId=" + id.toString();
		}
	
	});
})
</script>
</html>
<script type="text/template">
	<div class="zc_list_item">
        
          <div class="item_content">

            <div>
               
               <span>序号：<i>{seq}</i> &nbsp;&nbsp;</span>
               <span>车牌号：<i>{licensePlate}</i></span>
				
				
            </div>
            <div>
              <span>产品：<i>{productName}</i></span>
            </div>
            
          </div>
          <div class="item_btn_div">

           {label}
            
          </div>

      </div>
</script>
<script type="text/template1">
	<div class="zc_list_item">
        
          <div class="item_content">

            <div>
               
               <span>序号：<i>{seq}</i> &nbsp;&nbsp;</span>
               <span>车牌号：<i>{licensePlate}</i>&nbsp;&nbsp;</span>
				<span>产品：<i>{productName}</i>&nbsp;&nbsp;</span>
				
            </div>
            <div>
              <span>状态：<i>{safeCheckStatus}</i>&nbsp;&nbsp;</span>
              <span>时间：<i>{safeCheckTime}</i></span>
            </div>
            
          </div>
          <div class="item_btn_div">

           {label}
            
          </div>

      </div>
</script>