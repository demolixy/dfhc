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

   <!-- begin 1-->
<!--       <div class="zc_list_item"> -->
        
<!--           <div class="item_content"> -->

<!--             <div> -->
               
<!--                <span>车牌号：<i>京N.25541</i> &nbsp;&nbsp;</span> -->
<!--                <span>产品：<i>聚乙烯</i></span> -->

<!--             </div> -->
<!--             <div> -->
              
<!--               <span>仓库门：<i>13门第三仓库位</i></span> -->

<!--             </div> -->
            
<!--           </div> -->
<!--           <div class="item_btn_div"> -->

<!--            <a href="#" class="item_btn">确认</a> -->
            
<!--           </div> -->

<!--       </div> -->
   <!--end 1-->    
      

      

    </div>
    

  </div>

 <!--end 内容-->

<div class="lazy" style="display:block;" >
    <p><i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i> <span class="sr-only">Loading...</span></p>
    <p>Loading...</p>
</div>

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
        var html = $('script[type="text/template"]').html(); 
        //定义一个数组，用来接收格式化合的数据  
        var arr = [];  
    
        //对数据进行遍历  
        $.each(dataJson, function(i, o) {  
            o.label = "";
            if(o.supervisorIsConfirm != '1'){
            	o.label = '<a href="javascript:void(0);" class="item_btn" onclick="confirmOver(\'' + o.id + '\');">查看</a>';
            }
            arr.push(formatTemplate(o, html));  
        });  
       
          $('.zc_list').append(arr.join(''));
    	
        
    }
    scroll.Init();
})
//确认
function confirmOver(obj){
	window.location = "${pageContext.request.contextPath}/api/loadingnotice/loadingCarDetail?ladingNoticeId=" + obj.toString();
	
}
</script>
</html>
<script type="text/template">
	<div class="zc_list_item">
        
          <div class="item_content">

            <div>
               
               <span>车牌号：<i>{licensePlate}</i> &nbsp;&nbsp;</span>
               <span>产品：<i>{productName}</i></span>

            </div>
            <div>
              
              <span>仓库门：<i>{gateNum}门第{positionNum}仓库位</i></span>

            </div>
            
          </div>
          <div class="item_btn_div">

           {label}
            
          </div>

      </div>
</script>