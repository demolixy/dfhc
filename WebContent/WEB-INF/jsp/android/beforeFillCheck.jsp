<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>榆林-</title>
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="/jsp/android/include.jsp"%>
<script type="text/javascript">
	$(function() {
		var screenWidth = $("body").width();
		/*640px下面字体是100px,320px字体是50px,如果需要设置640下div高度为400就是4rem,相应字体也可以设置*/
		var fontSize = 10 * screenWidth / 64;
		$("html").css({
			"font-size" : fontSize + "px"
		});
		var screenHeight = document.documentElement.clientHeight;
		$("body").css({
			"min-height" : screenHeight + "px"
		});
	})
</script>
</head>

<body class="djWrap">

	<!--begin 头-->
	<div class="zc_header">

		<%@ include file="/jsp/android/header.jsp"%>

		<div class="dj_title">

			<span>充装前检查</span>

		</div>


	</div>
	<!--end 头-->

	<!--begin 表单内容-->
	<form name="form" id="form" method="post"
		action="${pageContext.request.contextPath }/api/vehiclesafetyinspection/insertVo">
		<div class="dj_content">

			<!--begin 表单列展示-->
			<input type="hidden" name="ladingBillId"
				value="<c:out value="${bean.id }"></c:out>" /> <input type="hidden"
				name="ladingBillCode"
				value="<c:out value="${bean.ladingBillCode }"></c:out>" />
			<div class="dj_list">

				<!--begin 表单元素1-->
				<div class="dj_item">
					<div class="item_title">车牌号：</div>
					<div class="item_text">
						<input type="text" name="licensePlate"
							value="<c:out value="${bean.licensePlate }"></c:out>"
							readonly="readonly" />
					</div>
				</div>
				<input name="checkType" type="hidden" value="${checkType}"/>
				<c:forEach var="list" items="${securityIndexs}">
					<div class="dj_item">
						<div class="item_title">
							<c:out value="${list.checkItem} "></c:out>
						</div>
						<div class="item_text">
							<c:choose>
								<c:when test="${list.checkResultCtrlType =='01'}">
									<input type="text"
										name='<c:out value="${list.checkResultCtrlName }"></c:out>'
										value="" />
								</c:when>
								<c:when test="${list.checkResultCtrlType=='02' }">
									 <div class="checkboxThree"> 
										<input type="checkbox"  value=0
											name='<c:out value="${list.checkResultCtrlName }" ></c:out>' />
									  <label class=""></label>
									</div> 
								</c:when>
							</c:choose>
						</div>
					</div>
				</c:forEach>

				<div class="big_btn_div">
					<a href="javascript:void();" class="big_btn">提交</a>
				</div>

			</div>
	</form>
	<!--end 表单内容-->
	<script type="text/javascript">
		$(function() {
			$(document).delegate(".checkboxThree", "click", function() {

				var inputChecked = $(this).find("input");

				if ($(inputChecked).val() == 0) {
					$(inputChecked).prop("checked", "checked");
					$(inputChecked).next().addClass("inpChecked");
					$(inputChecked).val(1);
				} else {
					$(inputChecked).removeAttr("checked");
					$(inputChecked).val(0);
					$(inputChecked).next().removeClass("inpChecked");
				}
			});

			$(".big_btn").click(
					function() {
						var status = $("input[name=status]").val();
						var info = "安检状态为【"
								+ ((status == '1') ? '通过状态' : '不通过状态')
								+ "】请确认安检完成。";
						zfconfirm('系统确认框', info, function(r) {
							if (r) {

								form.submit();
							}
						});

					});
		});
	</script>


</body>
</html>