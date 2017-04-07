<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" href="${pageContext.request.contextPath }/css/android/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/android/css/common.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/android/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/android/data-template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/android/scrollload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqGrid/requestPath.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/android/myalert.js"></script>

<script type="text/javascript">
$(function(){
	  var screenWidth=$("body").width();
	  /*640px下面字体是100px,320px字体是50px,如果需要设置640下div高度为400就是4rem,相应字体也可以设置*/
	  var fontSize=10*screenWidth/64;
	   $("html").css({ "font-size": fontSize + "px" });
	    var screenHeight = document.documentElement.clientHeight;
        $("body").css({ "min-height": screenHeight + "px" });
});
</script>
