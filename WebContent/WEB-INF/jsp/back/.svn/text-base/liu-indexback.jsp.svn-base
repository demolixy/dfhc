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

	<style type="text/css">
	
	 .table-check, .table{ display: table;width: 100%;padding: 0;border-collapse: collapse;}
	  .table-row{display:table-row ;width: 100%;min-height: 30px;}
	  .table-row>div{border:1px solid #333; display: table-cell;vertical-align: middle;text-align: center;}
	  .table-row:first-child .table-cell>div:first-child{border-bottom: 1px solid #333;}
	  .mutil_div{display: -webkit-flex; display: flex;}
	  .mutil_div span{display: inline-block;text-align: center;border-right: 1px solid #333;line-height: 25px;}
	  .mutil_div span:last-child{border-right:none;}
      .span1{flex:1;}
      .span2{flex:2;}
      .span4{flex:4;}
      .table-cell input{height: 20px;width: 100%;border: none;text-align: center;}
      .inp{border: none !important;border-bottom: 1px solid #666!important;width: 200px!important;text-align: center;}
	 
	 
	 
	 .table-check-head{display:table-row ;width: 100%;line-height: 36px;}
	 .table-check-cell{display: table-cell;border:1px solid #333; text-align: center;width: 20%;vertical-align: middle;}
	 .table-check-cell-big{display: table-cell;border:1px solid #333; text-align: center;width:40%;vertical-align: middle;}
	 .table-check-row{display:table-row ;width: 100%;height: 30px;}
	 
	 .chk_div{display: block;height: 60px;line-height:60px;}
	 a.chk_btn{text-decoration:none;color:#fff;background-color: #7399f5; padding:5px 10px;-ms-border-radius:5px!important;-moz-border-radius:5px!important;border-radius:5px!important;margin-left:30px;}
	 .chk_btn:hover{color:#fff;text-decoration:none;background-color: #2e4a8d;}
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
					
					<div class="row" style="padding:1% 2% 2%;min-width: 1000px;" >
					
					  <div class="col-xs-12">
					  
						     <div style="text-align: center;font-size: 18px;font-weight: 600;line-height: 40px;"> 11月 02号 副产品价格执行审批单</div>
					 
					  </div>
					  
					  <div  class="col-xs-12">
					  
					     <div  class="col-xs-4" style="font-size: 16px;line-height: 30px;">单位：<input class="inp" type="text"  placeholder="陕西公司" /> </div>
					     <div  class="col-xs-4" style="font-size: 16px;line-height: 30px;">单位：<input class="inp" type="text" placeholder="（元 /吨）" /> </div>
					     <div  class="col-xs-4" style="font-size: 16px;line-height: 30px;">制表日期：<input class="inp" type="text" placeholder="2017-02-05" /> </div>
					  
					  </div>
					  
					  <div class="col-xs-12">
					  
					  
					    <div class="table">
					    
					    
					    <!-- begin row -->
					    
					      <div class="table-row">
					      
					       <div class="table-cell"> 产品名称</div>
					  
					        <div class="table-cell" style="width: 15%;">
					          
						          <div > <span>建议价格</span>  </div>                           
						          
						             <div class="mutil_div">
						              
						              <span class="span1">价格</span>  
						              <span class="span2">涨(+)/跌(-)</span>                       
			             
						             </div>
					          
					          </div>
					       
					          
					        
					        <div class="table-cell"> 
					        
					           <div><span>其他企业定价情况</span></div>
					           
					           <div class="mutil_div">  
					           
					            <span class="span2"> 神华 </span>
					            <span class="span2"> 延长 </span>
					            <span class="span1"> 宝丰 </span>
					            <span class="span2"> 蒲城新能源 </span>
					            <span class="span2"> 延安炼厂 </span>
					           
					           
					           </div>
					        
					        
					        
					        </div>
					        <div class="table-cell"> 
					        
					         <div> <span>库存情况</span> </div>
					         
					         <div class="mutil_div">  
					           
					            <span class="span2"> 库容 </span>
					            <span class="span2"> 实时库存 </span>
					            <span class="span1"> 产量 </span>
					            <span class="span2">液位 </span>
					            <span class="span4">剩余库存能力（天） </span>
					           
					           
					          </div>
					          
					        </div>
					      
					      </div>
					      
					      <!-- end row1 -->
					      
					      
					      <!-- begin row2 -->
					      
					      <div class="table-row">
					      
					       <div class="table-cell"> <input value="产品名称"/> </div>
					  
					        <div class="table-cell" style="width: 15%;">
					          
						          
						             <div class="mutil_div">
						              
						              <span class="span1"><input value="" placeholder="价格"/></span>  
						              <span class="span2"><input value="" placeholder="100"/> </span>                       
			             
						             </div>
					          
					          </div>
					       
					          
					        
					        <div class="table-cell"> 
					        
					           
					           <div class="mutil_div">  
					           
					            <span class="span2"><input value="" placeholder="神华"/>  </span>
					            <span class="span2"><input value="" placeholder="延长"/>  </span>
					            <span class="span1"><input value="" placeholder="宝丰"/>   </span>
					            <span class="span2"><input value="" placeholder="蒲城新能源"/>  </span>
					            <span class="span2"><input value=" " placeholder="延安炼厂"/> </span>
					           
					           
					           </div>
					        
					        
					        
					        </div>
					        <div class="table-cell"> 
					        
					         
					         <div class="mutil_div">  
					           
					            <span class="span2"> <input value=""/></span>
					            <span class="span2"><input value=""/> </span>
					            <span class="span1"><input value=""/> </span>
					            <span class="span2"><input value=""/></span>
					            <span class="span4"><input value=""/> </span>
					           
					           
					          </div>
					          
					        </div>
					      
					      
					      </div>
					      
					      
					      <!-- begin row2 -->
					      
					  
					      
					    
					    
					    </div>
					  
					  
					  </div>
				
					
					  
					  <div class="col-xs-12">
					  
					    <div class="col-xs-1">
					            注：
					   </div>
					    <div class="col-xs-11" style="height: 100px;">
					    
					      <textarea style="width: 100%; height: 100%;"></textarea>
					   
					   </div>
					  
					  
					  </div>
					  
					  <!-- begin 审核tab -->
					  <div class="col-xs-12" style="margin-top: 20px;">
					  
					     <div class="table-check">
						     
						     <div class="table-check-head">
						     
							       <div class="table-check-cell">
							                                      审核人
							          
							       </div>
							       
							       <div class="table-check-cell">
							                                     审核时间 
							       </div>
							       
							       <div class="table-check-cell">
							                                       审核状态
							       </div>
							       <div class="table-check-cell-big">
						                                                审核意见
							       </div>
							       
						       
						       
						     </div>
						     <div class="table-check-row">
						     
							       <div class="table-check-cell">
							                                      李三
							          
							       </div>
							       
							       <div class="table-check-cell">
							              2016-12-12 15：15
							       </div>
							       
							       <div class="table-check-cell">
							                                       审核通过
							       </div>
							       <div class="table-check-cell-big">
						                                                审核意见
							       </div>
							       
						       
						       
						     </div>
						     
					
					     
					     
					     </div>
					  
					  
					  </div>
					  <!-- end 审核tab -->
					  
					  <!-- begin   意见 -->
					  
					  <div class="col-xs-12">
					  
					   <div style="line-height:30px;margin-top:20px;">审核意见：</div>
					   
					   <div style="border:1px solid #333;height: 100px;">
					   
					     <textarea  style="width: 100%;resize:none;border:none;height: 100%;line-height:1.5;"></textarea>
					   
					    </div>
					    
					    <div style="text-align: center;" class="chk_div">
					    
					      <a href="#" class="chk_btn">审核通过</a>
					      <a href="#" class="chk_btn">审核不通过</a>
					    
					    </div>
					  
					  
					  
					  </div>
					  
					  
					  <!-- end  意见 -->
					  
					  
					  
					
					</div>	


				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

	

	</body>
</html>
