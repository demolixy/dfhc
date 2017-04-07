<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="zc_title_div"> 

     <span>${sessionScope.RM_USER_VO.name }，欢迎您！</span>
     <span class="logout-btn">退出</span>

   </div>
   
<script type="text/javascript">
$(function(){
	$(".logout-btn").click(function(){
		window.location = "${pageContext.request.contextPath }/RmLoginAction.do?cmd=logout&toUrl=/jsp/android/login.jsp";
	})
})
</script>