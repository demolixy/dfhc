<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>榆林-待检验车辆</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="/jsp/android/include.jsp" %>
<script type="text/javascript">
$(function(){
      var screenWidth=$("body").width();
      /*640px下面字体是100px,320px字体是50px,如果需要设置640下div高度为400就是4rem,相应字体也可以设置*/
      var fontSize=10*screenWidth/64;
       $("html").css({ "font-size": fontSize + "px" });
        var screenHeight = document.documentElement.clientHeight;
        $("body").css({ "min-height": screenHeight + "px" });
    })
</script>
</head>

<body class="djWrap">

<!--begin 头-->
 <div class="zc_header">

   <%@ include file="/jsp/android/header.jsp" %>

   <div class="dj_title">
     
     <span>装车单详细信息</span>

   </div>

   
 </div>
 <!--end 头-->

<!--begin 表单内容-->
 <form name="form" id="form" method="post">
  <div class="dj_content">

    <!--begin 表单列展示-->
	<input type="hidden" name="id" value="<c:out value="${bean.id }"></c:out>"/>
      <div class="dj_list">

    <!--begin 表单元素1-->
        <div class="dj_item">
          <div class="item_title">车牌号：</div>
          <div class="item_text">
            <c:out value="${bean.licensePlate }"></c:out>  
          </div>          
        </div>
     <!--end 表单元素1-->   
     <!--begin 表单元素2-->
        <div class="dj_item">
          <div class="item_title">产品：</div>
          <div class="item_text">
            <c:out value="${bean.productName }"></c:out>
          </div>          
        </div>
     <!--end 表单元素2-->  
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">产品等级：</div>
          <div class="item_text">
             <c:out value="${bean.productLevel }"></c:out>    
          </div>          
        </div>
     <!--end 表单元素3-->  
   

      <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">装车吨数：</div>
          <div class="item_text">
             <c:out value="${bean.toneNum }"></c:out>    
          </div>          
        </div>
     <!--end 表单元素3-->  
    <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">装车位置：</div>
          <div class="item_text">
          <c:out value="${bean.gateNum }"></c:out>门第<c:out value="${bean.positionNum }"></c:out>仓库位
          </div>          
        </div>
     <!--end 表单元素3-->
     <c:forEach items="${beans }" var="list">
   		<!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">产品批次：</div>
          <div class="item_text">
          <c:out value="${list.productBatchNumber }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
    <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">数量(吨)：</div>
          <div class="item_text">
          <c:out value="${list.num }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
	</c:forEach>
     
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">破袋数量：</div>
          <div class="item_text">
          	<c:out value="${bean.truckInputBreakBagNum }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">序号：</div>
          <div class="item_text">
          	<c:out value="${bean.attribute1 }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">补袋人：</div>
          <div class="item_text">
          	<c:out value="${bean.attribute2 }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
    

    
      </div>


    <!--end 表单列展示-->

    <div class="big_btn_div" >
<!--       <a href="javascript:void();" onclick="addBreakBag();" class="big_btn">+添加破袋数量</a> -->
	<c:if test="${not empty isOperation }">
      <a href="javascript:void();" class="big_btn">装车确认</a>
	</c:if>
    </div>

  </div>

 </form>
 <!--end 表单内容-->

<script type="text/javascript">
	$(function(){
		$(".big_btn").click(function(){
			$.ajax({
		        type: "POST",
		        url: "<%=request.getContextPath()%>/api/loadingnotice/toBeIdentifiedConfirm?date="+new Date(),
		        data:{"loadingNoticeId":$("input[name=id]").val().toString()},		 
		        dataType: "json",
		        async:false , //false为同步
		        success: function(data,status) {
			   	   if(data.status=="0"){
			   		zfalert('系统提示',data.message,function(){ 
				          $(this).hide();
				    });
			   	   }else if(data.status == '2'){
			   			widnow.location = "<%=request.getContextPath()%>/jsp/android/login.jsp";
			   	   }else{
				   		zfalert('系统提示',data.message,function(){ 
					          $(this).hide();
		    	   		 	window.location = "<%=request.getContextPath()%>/api/loadingnotice/supervisor";;
					    });
			   	   }
		       },
		       error:function(xhr,s1,s2){
		       	var message = xhr.responseText;
					eval("var json = "+message);
					if($.trim(json.error).length==0){
						zfalert('系统提示','数据异常,请刷新页面重试',function(){ 
					          $(this).hide();
					    });
					}else{
						zfalert('系统提示',json.error,function(){ 
					          $(this).hide();
					    });
					}
		  	   }
		    });
		});
	})
</script> 

</body>
</html>