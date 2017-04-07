
<%@page import="org.quickbundle.project.RmProjectHelper"%>
<%@page import="org.quickbundle.tools.helper.RmStringHelper"%>
<%@ page contentType="text/html; charset=UTF-8" session="false" language="java" %>

<!-- begin 头文件 -->

	<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
				产品销售智能管理系统
<%-- 							<img style="height: 30px;width: auto;" alt="公司logo" src="<%=request.getContextPath()%>/images/logo.png"> --%>
						
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						

						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">1</span>
							</a>

							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-warning-sign"></i>
									要处理的任务
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												 要审核的订单
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

							

								<li>
									<a href="#">
										查看所有任务
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

					

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								
								<span class="user-info">
									<small>&nbsp;欢迎您,&nbsp;</small>
									<%=RmStringHelper.prt(RmProjectHelper.getRmLoginId(request))%>
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
		
                                
							    <li>
									<a href="${pageContext.request.contextPath }/orgauth/changePassword.jsp">
										<i class="icon-cog"></i>
										修改登录密码
									</a>
								</li>

								<li>
									<a href="${pageContext.request.contextPath }/RmLoginAction.do?cmd=logout&toUrl=/project/sample/login/login.jsp">
										<i class="icon-off"></i>
										  注销登陆
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
		

<!-- end  头文件 -->