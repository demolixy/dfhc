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
     
     <span>已检验车辆</span>

   </div>

   
 </div>
 <!--end 头-->

<!--begin 表单内容-->
 <form name="form" id="form" method="post" action="${pageContext.request.contextPath }/api/vehiclesafetyinspection/insertVo">
  <div class="dj_content">

    <!--begin 表单列展示-->
	<input type="hidden" name="ladingBillId" value="<c:out value="${bean.id }"></c:out>"/>
	<input type="hidden" name="ladingBillCode" value="<c:out value="${bean.ladingBillCode }"></c:out>"/>
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
          <div class="item_title">槽车容积：</div>
          <div class="item_text">
             <c:out value="${bean.tankVolume }"></c:out>    
          </div>          
        </div>
     <!--end 表单元素3-->  
   

     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">车辆制动正常(刹车)：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.normalVehicleBrake }"></c:out>" id="checkboxThreeInput" name="normalVehicleBrake" />
              <label  class=""></label>
            </div>
                  
          </div>  

                  
        </div>
     <!--end 表单元素5--> 
    <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">槽车最大工作压力：</div>
          <div class="item_text">
          <c:out value="${bean.maxWorkingPressureTank }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
<!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">车载灭火器 8kg x 2完好：</div>
          <div class="item_text">

            <div class="checkboxThree">
            
              <input  type="checkbox" value="<c:out value="${bean.fireExtinguisher }"></c:out>" id="checkboxThreeInput" name="fireExtinguisher" />
              <label class=""></label>
            </div>
                  
          </div>  

                  
        </div>
     <!--end 表单元素5--> 
<!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">槽车最大载负荷量：</div>
          <div class="item_text">
          <c:out value="${bean.maxLoadCarryingTank }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
     
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">防火罩完好，阀关闭：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input  type="checkbox" value="<c:out value="${bean.flashHider }"></c:out>" id="checkboxThreeInput" name="flashHider" />
              <label  class=""></label>
            </div>
                  
          </div>  

                  
        </div>
     <!--end 表单元素5--> 
     
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">卸载前的罐体余压：</div>
          <div class="item_text">
          	<c:out value="${bean.beforeResidualPressureTank }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">装运油品种：</div>
          <div class="item_text">
          	<c:out value="${bean.shippingOilVariety }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">司机驾驶证合格：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.driverLicensePass }"></c:out>" id="checkboxThreeInput" name="driverLicensePass" />
              <label   class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">与原装油品种是否一致：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.isConsistentOriOil }"></c:out>" id="checkboxThreeInput" name="isConsistentOriOil" />
              <label   class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素3-->
        <div class="dj_item">
          <div class="item_title">行车证合格(车主姓名)：</div>
          <div class="item_text">
          	<c:out value="${bean.ownerName }"></c:out>
          </div>          
        </div>
     <!--end 表单元素3-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">紧急切断装置、根部阀完好：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.brakeWell }"></c:out>" id="checkboxThreeInput" name="brakeWell" />
              <label    class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">危险品押运员证合格：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.dangerousQualifiedEscort }"></c:out>" id="checkboxThreeInput" name="dangerousQualifiedEscort" />
              <label   class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">静电接地带完好：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.staticGroundingZone }"></c:out>" id="checkboxThreeInput" name="staticGroundingZone" />
              <label   class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">危化品运输证合格：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.dangerousTransCertificate }"></c:out>" id="checkboxThreeInput" name="dangerousTransCertificate" />
              <label   class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">压力表、液位计等完好：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.pressureGaugeLevelGauge }"></c:out>" id="checkboxThreeInput" name="pressureGaugeLevelGauge" />
              <label   class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">压力容器校验证合格：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.pressureVesselCalibration }"></c:out>" id="checkboxThreeInput" name="pressureVesselCalibration" />
              <label  class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">车载GPS定位系统：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.gps }"></c:out>" id="checkboxThreeInput" name="gps" />
              <label  class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">驾驶室及司乘不带烟火：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.withoutFireworks }"></c:out>" id="checkboxThreeInput" name="withoutFireworks" />
              <label  class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">安全阀完好：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.safetyValveIsGood }"></c:out>" id="checkboxThreeInput" name="safetyValveIsGood" />
              <label  class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
     <!--begin 表单元素5-->
        <div class="dj_item">
          <div class="item_title">是否通过：</div>
          <div class="item_text">

            <div class="checkboxThree">
              <input type="checkbox" value="<c:out value="${bean.status }"></c:out>" id="checkboxThreeInput" name="status" />
              <label  class=""></label>
            </div>
                  
          </div>  
        </div>
     <!--end 表单元素5-->
    
      </div>


    <!--end 表单列展示-->

    <div class="big_btn_div" >
<!--       <a href="javascript:void();" class="big_btn">提交</a> -->
    </div>

  </div>

 </form>
 <!--end 表单内容-->
<script type="text/javascript">
$(document).ready(function(){
	$("input[type=checkbox]").each(function(){
		if($(this).val() != '0'){
			$(this).next().addClass("inpChecked");
		}
	});
})


</script>
 

</body>
</html>