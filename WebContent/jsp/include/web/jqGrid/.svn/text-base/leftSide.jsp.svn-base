<%@page import="com.dfhc.ISystemConstant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.quickbundle.project.common.vo.RmCommonVo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" session="false" language="java" isELIgnored="false"%> 


<%
List<RmCommonVo>  lvos=( List<RmCommonVo>)request.getSession(true).getAttribute("leftSides");//获取左侧一级菜单

String pcode=(String)request.getSession(true).getAttribute(ISystemConstant.RM_PARENT_CODE);//来自父节点标志

String childSource=(String)request.getSession(false).getAttribute(ISystemConstant.RM_CHILD_SOURCE);//来自子节点

childSource=childSource==null?"-1":childSource;

%>
	<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>
                  

					<ul class="nav nav-list">
						
					
						
						<%if(lvos==null || lvos.size()==0){ %>
							<li >
							<a href="#" class="dropdown-toggle">
								<i class="icon-list-alt"></i>
								<span class="menu-text"> 该用户没有授权菜单</span>

								<b class="arrow icon-angle-down"></b>
							</a>

					
						  </li>
						  <%} else {%>
						
							<% for(RmCommonVo vo:lvos){  %>
							
								<li id="<%=vo.get("total_code")%>">
								<a href="#" class="dropdown-toggle">
									<i class="icon-list-alt"></i>
									<span class="menu-text"><%=vo.get("name")%></span>
	
									<b class="arrow icon-angle-down"></b>
								</a>
	
						
							  </li>
							
							
							<%} %>
						
						
					     
					     <%} %>

				
				
					
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>
		
		
				<script type="text/javascript">
				
				$(function(){
					
					var pcode='<%=pcode%>';//父节点标志
					var childSource='<%=childSource%>';//字节点
					if(pcode!='undefined' && pcode!=''){
						
						var pointer=$("#"+pcode);
						ace.showChildTree($,pointer,pcode);//展开子节点
						$(pointer).attr("reqFlag",true);//设置标志已经加载过了
						$(pointer).find("li").each(function(){
							
							var a_href=	$(this).find("a").attr("href");//子字典url
							
							var currentUrl=window.location.href;
							if(a_href.indexOf(childSource)>-1 || a_href.indexOf(currentUrl)>-1){
					
								$(this).addClass('active');
								
								return ;
								
							}
							
							
						});
						
						$(pointer).slideToggle(200).toggleClass('open');
						$(pointer).slideToggle(200).toggleClass('active');
						
					}
					
				})
				
				
				</script>
		
				
			
