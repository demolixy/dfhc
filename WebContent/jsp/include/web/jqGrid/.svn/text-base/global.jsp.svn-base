<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>

	
	<!-- basic styles -->

		<link href="<%=request.getContextPath()%>/css/jqGrid/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/jquery-ui-1.10.3.full.min.css" />

		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/ui.jqgrid.css" />

		<!-- fonts -->

		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/ace-fonts.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/ace.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/ace-skins.min.css" />
		
	
	
		

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/ace-ie.min.css" />
		<![endif]-->
       <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jqGrid/css/pub.css" />

		<!-- inline styles related to this page -->
		
		<!-- jquery -->
		<script src="<%=request.getContextPath()%>/js/jqGrid/jquery-1.10.2.min.js"></script>
		<!-- js  获取项目路径 -->
		<script src="<%=request.getContextPath()%>/js/jqGrid/requestPath.js"></script>

		<!-- ace settings handler -->

		<script src="<%=request.getContextPath()%>/js/jqGrid/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="<%=request.getContextPath()%>/js/jqGrid/html5shiv.js"></script>
		<script src="<%=request.getContextPath()%>/js/jqGrid/respond.min.js"></script>
		
		<![endif]-->
		
			<!-- basic scripts -->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=request.getContextPath()%>/js/jqGrid/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=request.getContextPath()%>/js/jqGrid/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=request.getContextPath()%>/js/jqGrid/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="<%=request.getContextPath()%>/js/jqGrid/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jqGrid/typeahead-bs2.min.js"></script>
		

		<!-- page specific plugin scripts -->

		<script src="<%=request.getContextPath()%>/js/jqGrid/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jqGrid/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jqGrid/jqGrid/i18n/grid.locale-en.js"></script>

		<!-- ace scripts -->

		<script src="<%=request.getContextPath()%>/js/jqGrid/ace-elements.min.js"></script>
<!--		<script src="<%=request.getContextPath()%>/js/jqGrid/ace.min.js"></script>-->
		<!--20161230 增加  -->
		<script src="<%=request.getContextPath()%>/js/jqGrid/uncompressed/ace.js"></script>
		
		<script src="<%=request.getContextPath()%>/js/jqGrid/app-pub.js"></script>
        
       
    <!-- 增加日期 -->
	    <script src="<%=request.getContextPath()%>/	js/jqGrid/laydate/laydate.js"></script>
	    <script src="<%=request.getContextPath()%>/	js/jqGrid/jquery.md5.js"></script>
	
	 
		<!-- inline scripts related to this page -->
		
</head>
<body>

<%@include file="/WEB-INF/jsp/common/status.jsp" %> 

<div class="modalDiv-backdrop" id="mapDiv" name="mapDiv">
	<div class="modalDiv">
<!-- 		<button class="close" onclick="javascript:hiddeMap()"></button> -->
<!-- 		<div style="padding: 9px 9px 9px 9px;">	 -->
           <iframe src="<%=request.getContextPath()%>/jsp/include/web/jqGrid/globalShowDiv.jsp" id="showDiv"  style="border:none;"  name="showDiv" height="460" width="100%" allowtransparency="true" scrolling="no" frameborder="0"></iframe>
           <form name="searchDialogForm">
           <input name="searchParam" value="" type="hidden"></input>
           </form>
<!-- 		</div>								 -->
	</div>
</div>

<script>

$(function(){
	
	$(".date-picker").on("click",function(){

		$(this).addClass("laydate-icon");
		laydate();
		
	});
	
});



</script>

</body>
</html>		
		