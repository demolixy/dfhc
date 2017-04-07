<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	   <!-- 全局js 和css包 -->
	   <%@ include file="/jsp/include/web/jqGrid/global.jsp" %>
		<meta charset="utf-8" />
		<title><bean:message key="qb.web_title"/></title>
		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	</head>
	<body>
	   <!-- begin  头部 -->
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	   <!-- end 头部 -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
                <!-- begin 左侧菜单 -->
			   <%@ include file="/jsp/include/web/jqGrid/leftSide.jsp"%>
                 <!-- end 左侧菜单 -->
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li>
								<a href="#">价格调整申请</a>
							</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="page-content">
		                <!-- begin  详情 -->
		                 <div class="widget-box">
		                      <div class="widget-header">
		                      <h5>价格调整申请</h5>
		                      <div class="widget-toolbar">
		                         <a href="javascript:void(0)" title="返回">
		                           <i class="icon-share-alt"></i>
		                         </a>
		                      </div>
		                     </div>
									${templateFreemark }
		                 </div>
 						<!-- end 详情 -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->
	</body>
</html>
