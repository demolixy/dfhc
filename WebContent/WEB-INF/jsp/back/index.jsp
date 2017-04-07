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

	    <link href="${pageContext.request.contextPath }/css/echarts/font-awesome.min.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/bootstrap.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/carousel.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/css/echartsHome.css" rel="stylesheet">
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	
	  
	
	    <link href="${pageContext.request.contextPath }/css/echarts/css/codemirror.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/echarts/css/monokai.css" rel="stylesheet">
	    
	    
	<style type="text/css">
	
	.tu_div{text-align: center;margin-top:30px;}
	.tu_div span{display: block;width: 100%;margin: 5px auto ;}
	.tu_div span:first-child{font-size: 16px;font-weight: bold;}
	.tu_div div{width: 100%;text-align: center;margin: auto;}
	.tu_div div table{width: 70%;margin: auto;}
	.tu_div tr{width: 100%;}
	.tu_div td{width: 50%;text-align: center;border: 1px solid #ccc;height: 30px;}
	.tu_div span img{width: 200px;height: auto;}
	div.zr-element{
	position:absolute !important;
	left:75px !important;
	text-align: center;
	width: 250px!important;
	-ms-border-radius:10px!important;
	-moz-border-radius:10px!important;
	border-radius:10px!important;
	}
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
					  
					    <div class="col-xs-3 tu_div" >
					      <span>副产品今日价格</span>
					      <div>
					      
					        <table>
					        
					          <tr>
					           <td>品名</td>
					           <td>价格（元/吨）</td>
					          
					          </tr>
					          
					          <c:forEach var="vo" items="${listPrice }">
					          
					           <tr>
					           
					             <td> <c:out value="${vo.productName }"></c:out>   </td>
					             <td> <c:out value="${vo.price }"></c:out></td>
					           
					           </tr>
					        
					          </c:forEach>
					        
					        
					        </table>
					        
					      </div>
					    </div>
					     <div class="col-xs-4 tu_div" >
					       <span>副产品价格走势</span>
					       <div class="clearfix">
					       
					        <div id="echarts" style="width:400px;height:230px;position: relative;">
					        
					         
					       
					        </div>
					       
					       </div>
					    </div>
					     <div class="col-xs-4 tu_div">
					      <span>今日装车量</span>
					      <div>
					       <table>
					       
					        <tr>
					        
					         <td>类型</td>
					         <td>数量</td>
					        
					        </tr>
					          
					           <tr>
					           
					             <td>今日签到车数  </td>
					             <td> <c:out value="${todayCarTabVo.todayCount }"></c:out></td>
					           
					           </tr>
					           <tr>
					           
					             <td>今日过磅车数  </td>
					             <td> <c:out value="${todayCarTabVo.isPoundCarNum }"></c:out></td>
					           
					           </tr>
					           <tr>
					           
					             <td>今日已过磅车数   </td>
					             <td> <c:out value="${todayCarTabVo.isPounedCarNum }"></c:out></td>
					           
					           </tr>
					           <tr>
					           
					             <td>今日装车数</td>
					             <td> <c:out value="${todayCarTabVo.isLoadingCarNum }"></c:out></td>
					           
					           </tr>
					           <tr>
					           
					             <td>今日已装车数 </td>
					             <td> <c:out value="${todayCarTabVo.isLoadedCarNum }"></c:out></td>
					           
					           </tr>
					        
					     
					      
					       
					       </table>
					      
					     </div>
					     
					    </div>
					 
					  </div>
					
					</div>	

					<div class="page-content">
						<div class="page-header">
							<h1>
<!-- 								jqGrid -->
								<small>
									
									我的待办事项
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

		<script type="text/javascript">
		
		$(function (){
			
			
			
		});
		
		
		
		</script>
	

		<script type="text/javascript">
		jQuery(function($) {
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";

			jQuery(grid_selector).jqGrid({
				//direction: "rtl",
				
				url: getRootPath()+'/pubController/queryHanding',
				datatype: "json",
			    mtype: 'POST',  
				height:'100%',
				colNames:['待办名称','待办处理','待办数量'],
				colModel:[
                    {name:'name',index:'name'},
					{name:'url',index:'url',formatter:opperate},
					{name:'countnum',index:'countnum' ,editable: false}
				], 
			
				viewrecords : true,
				rownumbers:true,
				rowNum:15,
				rowList:[15,30],
				pager : pager_selector,
				sortname : 'name',//初始化的时候排序的字段
				sortorder : "desc",//排序方式,可选desc,asc
				altRows: true,
				//toppager: true,
				
				multiselect: true,
				//multikey: "ctrlKey",
		        multiboxonly: true,
		
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						
						
						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},
		
				editurl: getRootPath()+'/',//nothing is saved
				caption: "待办事项列表",
				autowidth: true
		
			});
			
			function  opperate(cellvalue, options, cell){
			
				var op="<a href='"+getRootPath()+cellvalue+"'>去处理待办"+cell.name+"</a>";
				return op;
				
			}
			//enable search/filter toolbar
			//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
		
			//enable datepicker
			function pickDate( cellvalue, options, cell ) {
				setTimeout(function(){
					$(cell) .find('input[type=text]')
							.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
				}, 0);
			}
		   
			
		
		
			//navButtons
			jQuery(grid_selector).jqGrid('navGrid',pager_selector,
				{ 	//navbar options
					edit: false,
					editicon : 'icon-pencil blue',
					add: false,
					addicon : 'icon-plus-sign purple',
					del: false,
					delicon : 'icon-trash red',
					search: false,
					searchicon : 'icon-search orange',
					refresh: false,
					refreshicon : 'icon-refresh green',
					view: false,
					viewicon : 'icon-zoom-in grey',
				
				}
			)
			
		
		 
		     
		     
		     
			//unlike navButtons icons, action icons in rows seem to be hard-coded
			//you can change them like this in here if you want
			function updateActionIcons(table) {
				
			}
			
			//replace icons with FontAwesome icons like above
			function updatePagerIcons(table) {
				var replacement = 
				{
					'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
					'ui-icon-seek-next' : 'icon-angle-right bigger-140',
					'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				});
			}
		
			function enableTooltips(table) {
				$('.navtable .ui-pg-button').tooltip({container:'body'});
				$(table).find('.ui-pg-div').tooltip({container:'body'});
			}
		
			//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
		
		
		});
		</script>
		
		
    
	</body>
	
	  <script src="${pageContext.request.contextPath }/js/echarts/echarts.js"></script>
	  <script src="${pageContext.request.contextPath }/js/echarts/esl.js"></script>
<%--      <script src="${pageContext.request.contextPath }/js/echarts/codemirror.js"></script> --%>
<%--      <script src="${pageContext.request.contextPath }/js/echarts/echartsExample.js"></script> --%>

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
