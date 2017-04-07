<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>登录页面</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->
			
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



		<!-- ace scripts -->

		<script src="<%=request.getContextPath()%>/js/jqGrid/ace-elements.min.js"></script>
<!--		<script src="<%=request.getContextPath()%>/js/jqGrid/ace.min.js"></script>-->
		<!--20161230 增加  -->
		<script src="<%=request.getContextPath()%>/js/jqGrid/uncompressed/ace.js"></script>
		
	    <script src="<%=request.getContextPath()%>/	js/jqGrid/jquery.md5.js"></script>
	
	 
		<!-- inline scripts related to this page -->
		<!-- 全局js 和css包 -->
	 <%@ include file="/project/sample/login/loginRedirect.jsp" %>
	    <script src="<%=request.getContextPath()%>/js/jqGrid/login.js"></script>
	   

	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row login">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								
								<h4 class="blue">&copy; 中煤陕西榆林能源化工有限公司</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h5 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												产品销售智能管理系统
											</h5>

											<div class="space-6"></div>

											<form name="loginForm" id="loginForm" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="login_id" id="login_id" placeholder="登录名" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="pwd" id="pwd"  placeholder="密码" />
															
															<i class="icon-lock"></i>
														</span>
													</label>
													<input type="hidden" name="password"  value="" id="password">
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name=verifyCode   placeholder="验证码输入" />
															<img onload="writeBackVerifyCode();" style="cursor:pointer" title="看不清？再点一下" onclick="javascript:this.src='<%=request.getContextPath()%>/jsp/support/verify/buildVerifyCode.jsp?height=24&width=70&rmrandom=' + new Date()" src="<%=request.getContextPath()%>/jsp/support/verify/buildVerifyCode.jsp?height=24&width=70" />
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline"  style="color:red">
															${alertStr }
															
														</label>

														<a href="javascript:void(0);" onclick="javascript:login()" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															登录
														</a>
													</div>

													<div class="space-4"></div>
												</fieldset>
												
												<input type="hidden" name="isSubmit" value="1">
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">系统登录</span>
											</div>

										</div><!-- /widget-main -->

										
									</div><!-- /widget-body -->
								</div><!-- /login-box -->

							

								
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->


		<!-- inline scripts related to this page -->

		<script type="text/javascript">
		
		</script>
	</body>
</html>
