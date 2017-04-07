<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>组织树</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="x-ua-compatible" content="ie=11" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cipher/jquery-1.10.1.min.js"></script>	
<link href="<%=request.getContextPath()%>/css/cipher/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/cipher/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/cipher/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/cipher/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/cipher/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/cipher/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/cipher/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=request.getContextPath()%>/css/cipher/uniform.default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/cipher/select2_metro.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cipher/DT_bootstrap.css" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/cipher/favicon.ico" />
<link href="<%=request.getContextPath()%>/css/cipher/halflings.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function showChild(obj,total_code){
		$("span[name='arraw']").each(function(){
			var classValue=$(this).attr("class");
			var idValue=$(this).parent().next().attr("id");
			if(classValue.indexOf("open")>-1 && idValue!=total_code){
				 $(this).removeClass("open");
	             $(this).parent().next().hide();
				}
			    if(idValue==total_code && classValue.indexOf("open")>-1 ){
					 $(this).addClass("open");
					}
               
			});
		var classValue=$(obj).children().last().attr("class");
		if(classValue.indexOf("open")<0){
			  $(obj).children().last().addClass("open");
			  ajaxTree(obj,total_code);
			  $("#"+total_code).show();
			}else{
			 $(obj).children().last().removeClass("open");
		     $("#"+total_code).hide();
				}
       
		}
	function ajaxTree(obj,total_code){
		 $.ajax({
			    type:'post',//可选get
			    url:'<%=request.getContextPath()%>/pubController/getTreeOrgAjax',
			    data:{'total_code':total_code},
			    dataType:'json',
			    async:false , //false为同步
			    success:function(data,status){
				 var lvos=data.lvos;
				 var inHtml='';
				 if(lvos.length==0){
					  $("#"+total_code).html("<li>无数据</li>");
					  $("#"+total_code).show();
					 }else{
                     
                      for(var i=0;i<lvos.length;i++){
                    	  inHtml+="<li ><a href='<%=request.getContextPath()%>"+lvos[i].data_value+"'  target='contentFrame'>"
                    	         +lvos[i].name+"</a></li>"  ;
                          }
                        $("#"+total_code).html(inHtml);
                        $("#"+total_code).show();

						 }
				 
			   },
			   error:function(xhr,s1,s2){
			    	var message = xhr.responseText;
					eval("var json = "+message);
					if($.trim(json.error).length==0){
						alert('数据异常,请刷新页面重试');
					}else{
						alert(json.error);
					}
			   }
			   });
		
		}
	</script>
</head>

<body>
<!--<div width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">-->
<div class="page-container">
<div class="page-sidebar " >
			<!-- BEGIN SIDEBAR MENU -->        

			<ul class="page-sidebar-menu">
			<c:forEach items="${lvos}" var="lvo" >
			<li>

					<a href="javascript:void(0);" onclick="showChild(this,'${lvo.total_code}')">

					<i class="icon-cogs"></i> 

					<span class="title">${lvo.name}</span>

					<span class="arrow" name="arraw"></span>

					</a>

					<ul class="sub-menu" style="display:none;background-color: #615C6F" id="${lvo.total_code }" name="childTree">

						<li >

							<a href="<%=request.getContextPath()%>/factory" target="contentFrame" >

							Content Loading via Ajax</a>

						</li>

					</ul>

				</li>
			</c:forEach>
			
            </ul>
        </div>
     </div>  
</body>
</html>