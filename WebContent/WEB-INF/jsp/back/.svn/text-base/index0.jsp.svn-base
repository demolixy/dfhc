<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
	   <!-- 全局js 和css包 -->
	   <%@ include file="/jsp/include/web/jqGrid/global.jsp" %>
		<meta charset="utf-8" />
		<title><bean:message key="qb.web_title"/></title>
	    <link href="${pageContext.request.contextPath }/css/echarts/font-awesome.min.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/bootstrap.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/carousel.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/css/echartsHome.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/css/codemirror.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/css/monokai.css" rel="stylesheet">

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<style type="text/css">
	
	.ui-title{padding-left:20px;line-height:40px;background-color:#307ecc;
	          color:#fff;margin-top:10px;font-size: 16px;font-weight: bold;}
	.ui-content{
			border: 1px dotted #ccc;
			min-height: 80px;
			border-top-style: hidden;
			height: auto;
			padding-bottom: 10px;
	}
	.ui-content div{padding:0px 5px;line-height: 20px;color: #666;}
	
	.ui-content a{color:#f04618;}
	
	.chart {text-align: center;padding-top:10px;}
	.chart img{width: 200px;height: auto;}
	
	.js_tab{margin:auto;text-align: center;padding-top:10px !important;}
	.js_tab table{width: 80%;}
	.js_tab td{border: 1px solid #ccc;height: 30px;}
	.js-con > div{margin-top: 10px ;text-align: center;}
	.js-con select,.js-con input{width:220px;}
	</style>
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
					<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
					
					<div class="row">
					
					  <div class="col-xs-12">
					  
						    <div class="col-xs-6 " >
						       
						       <div class="ui-title">通知公告</div>
						       <div class="ui-content">
						       
					       <c:forEach  items="${list}"  var="bean">
					       		 <div  title="${bean.title}"><a><c:out value="${fn:substring(bean.title,0,20)}"></c:out><c:if test="${fn:length(bean.title)>20}">...</a></c:if>
								<div  style="float: right"><c:out value="${bean.sendTime}"></c:out></div></div>
					       </c:forEach>
						       
						       </div>
						       
						    </div>
						    
						     <div class="col-xs-6" >
							      <div class="ui-title">副产品价格走势&nbsp;&nbsp;&nbsp;
							      
							     <%--  <a href="#" class="icon-refresh white"></a> --%>
							      </div>
							   <div id="echarts" style="width:400px;height:230px;position: relative;">
					        
					           </div>

						    </div>
			
						    <div class="col-xs-6 " >
						       
						       <div class="ui-title">待处理</div>
						       <div class="ui-content">
						    <%--    <div>待提货订单:<a href="#">(one)</a></div>
						        <div>待结算订单:<a href="#">(two)</a></div> --%>
						       
						       </div>
						       
						    </div>
					 
						     <div class="col-xs-6" >
							      <div class="ui-title">副产品今日价格&nbsp;&nbsp;&nbsp;
							      
							     <%--  <a href="#" class="icon-refresh white"></a> --%>
							      
							      </div>
							      <div class="ui-content"    id="todayPrice">
							    <c:forEach var="bean" items="${listPrice }">
					       		 <div ><c:out value="${bean.productName}"></c:out>
								<div  style="float: right"><c:out value="${bean.price}"></c:out>(元/吨)</div></div>
							    </c:forEach>  
							       </div>
						    </div>
					  </div>
					  
				<%--	    <div class="col-xs-12"  style="text-align: center;margin-top:20px;">
					    
						     <div class="col-xs-3">
						     
						                     待提货订单：5
						     
						     </div>
						     <div class="col-xs-2">
						        
						                    待结算订单：5
						     
						     </div>
						     <div class="col-xs-3">
						     
						                     待提货订单：5
						     
						     </div>
						     <div class="col-xs-2">
						        
						                    待结算订单：5
						     
						     </div>
						  
						  
					      
					  
					     </div>
					  
					
					 --%>  
					 
					  
					
					</div>	


				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

	

	</body>
	  <script src="${pageContext.request.contextPath }/js/echarts/echarts.js"></script>
	  <script src="${pageContext.request.contextPath }/js/echarts/esl.js"></script>
<script type="text/javascript">

// 路径配置
require.config({
    paths: {
        echarts: '${pageContext.request.contextPath }/js/echarts'
    }
});

Array.prototype.remove=function(obj){
	 for(var i =0;i <this.length;i++){
	 var temp = this[i];
	 if(!isNaN(obj)){
	 temp=i;
	 }
	 if(temp == obj){
	 for(var j = i;j <this.length;j++){
	 this[j]=this[j+1];
	 }
	 this.length = this.length-1;
	 }
	 }
 } ;

// 使用
require(
	
    [
        'echarts',
        'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('echarts')); 
        
        var title=[];
        var dateList=[];
        var data=[];
        var series=[];
        <c:forEach var="vo" items="${listPro}">
        
           title.push('${vo.name}');
        
        </c:forEach>
           
         var  bakTitle=new Array();
       	  $.extend(true,bakTitle,title) ;
           
        <c:forEach var="vo" items="${listPriceTrend}">
        
          var pro='${vo.productName}';
     
          if(dateList.indexOf('${vo.attribute1}')==-1){
       	   
       	   if(dateList.length>0){//非第一次
   			   
			      if(bakTitle.length!=0){
				  
				    for(var i=0;i<bakTitle.length;i++){
				    	 var tpro=bakTitle[i];
					   var item=data[tpro];
                        if(typeof item!='undefined' && item!=null){
						
						 
							 var val=item.data;
							 val.push('');
							 item.name=bakTitle[i];
							 item.data=val;
							 item.type='line';
							
							  
						   }else{
							  
							  var val=new Array();
							  val.push('');
							  data[tpro]={
									  'name':bakTitle[i],
									  'type':'line',
									  'data':val
							  }
							 var len=data.length;
							 data.length=Number(len)+1;
							}
				  
				  }
        		   
				   $.extend(true,bakTitle,title) ;
        	   }
        	   }
              dateList.push('${vo.attribute1}');
       	   
          }
          bakTitle.remove(pro);
          var item=data[pro];
          if(typeof item!='undefined' && item!=null){
        	  
        	 var val=item.data;
        	 val.push('${vo.price}');
        	 item.name='${vo.productName}';
        	 item.data=val;
        	 item.type='line';
        	
        	  
          }else{
        	  
        	  var val=new Array();
        	  val.push('${vo.price}');
        	  data[pro]={
        			  'name':'${vo.productName}',
        			  'type':'line',
        		      'data':val
        	  }
        	 var len=data.length;
         	 data.length=Number(len)+1;
        	  
          }
        </c:forEach>

     for(var k in data){
   	  
   	//  title.push(k);
       
       	 series.push(data[k]);
        }
       	
        var   option = {
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:title
        	    },
        	    toolbox: {
        	        show : true
        	    },
        	    calculable : true,
        	    dataZoom : {
        	        show : true,
        	        realtime : true,
        	        start : 0,
        	        end :100
        	    },
        	    xAxis : [
        	        {
        	            type : 'category',
        	            boundaryGap : false,
        	            data : dateList
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value'
        	        }
        	    ],
        	    series :series
        	};
   
        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);
</script>	
</html>
