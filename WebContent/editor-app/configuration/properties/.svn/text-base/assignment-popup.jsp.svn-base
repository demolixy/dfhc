<%@ page contentType="text/html; charset=UTF-8" session="false" language="java" %>


<div class="modal" ng-controller="KisBpmAssignmentPopupCtrl">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="close()">&times;</button>
                 <h4 translate>PROPERTY.ASSIGNMENT.TITLE </h4>
            </div>
            <div class="modal-body">
            
            <!-- begin 修改20161205 -->
            
            <div class="tabbable tabbable-custom">
            
             <ul class="nav nav-tabs">
              
              <li class="active"><a data-toggle="tab"  href="#usertab">用户</a> </li>
              <li><a data-toggle="tab"  href="#roletab">角色</a> </li>
              <li><a data-toggle="tab"  href="#govtab">机构</a> </li>
              
             
             </ul>
             
             <!-- begin 内容 -->
             <div class="tab-content">
              
              <!-- begin  用户列表 -->
               <div id="usertab" class="tab-pane active">
                
                   <div class="shi-tab">
                   
                   <!-- begin table 头 -->
                     <div class="portlet-title">
                        
                        <div class="caption">
                     		 <span class="list-tab-span"> 用户列表</span>  
                        </div>
                        
                        <div class="actions">
                          <a href="javascript:void(0);" class="shi-btn" onclick="javascript:db_add('user_list')">  + 新增 </a>
                        
<!--                      onclick="javascript:add_line('user_list')" -->
                          <a href="javascript:void(0);" class="shi-btn " ng-click="addCandidateUserValue( assignment.candidateGroups.length)" >  +增加表达式</a>
                          <a href="javascript:void(0);" class="shi-btn" onclick="javascript:db_delete('user_list')">  - 删除 </a>
                        </div>
                     
                     </div>
                     
                     <!--end table 头 -->
                     
                     <!-- begin  table 体 -->
                     <div  class="shi-tab-body">
                       
                       <table class="shi-table" id="user_list">
                       
                         <thead>
                            <th>
	                            <div class="checker select_all_chk">
	                              <span>
	                               <input type="checkbox" name="select_btn"  class=" main_check" checkflag="false" ></input>
	                              </span>
	                            </div>
                             </th>
                            <th>是否主办</th>
                            <th>选中的用户</th>
                         </thead>
                         
                         <tbody >
                         
                           
                          <tr  ng-if="assignment.assignee!=null && assignment.assignee!='' " >
                            
                             <td > 
								   <div class="checker select_chk">
									 <span>
									    <input type="checkbox" name="select_btn " checkflag="false" value="{{assignment.assignee}}"  assiflag="main"></input>
									 </span> 
									</div>
								</td>
								
								<td> 
								  <div class="checker check_main"> 
								    <span>
									   <input type="checkbox" name="selectmain" class="setMainFlag" value="{{assignment.assignee}}"   checked="checked" assiflag="main"></input>
								    </span>
								  </div>
								</td> 
								
								<td>
								    <input id="userField" class="form-control" type="text" readonly="readonly" ng-model="assignment.assignee"  />
								</td>
                           
                          </tr>
                         
                           <tr  ng-repeat="candidateUser in assignment.candidateUsers">
                           
	                           <td> 
								   <div class="checker select_chk">
									 <span>
									    <input type="checkbox" name="select_btn " checkflag="false" value="{{candidateUser.value}}" index="{{ $index + 1 }}" assiflag="flow"></input>
									 </span> 
									</div>
								</td>
								
								<td> 
								  <div class="checker check_main"> 
								    <span>
									   <input type="checkbox" name="select_btn" class="setMainFlag" value="{{candidateUser.value}}"  assiflag="flow"></input>
								    </span>
								  </div>
								</td> 
							
								<td>
								 
								 <input id="userField"  class="form-control" type="text"  value="{{candidateUser.value}}"   ng-model="candidateUser.value"  />
								    
								</td>
                           
                           
                           </tr>
                           
                        
                          
	                       
                         </tbody>
                         
                       </table>
                     
                     </div>
                     <!-- end  table 体 -->
                     
                   
                   </div>
                	
	                <div class="modal-footer" style="border-top:none;">
	                   <button ng-click="close()" class="btn btn-primary" translate>ACTION.CANCEL</button>
	                   <button ng-click="save()" class="btn btn-primary" translate>ACTION.SAVE</button>
	               </div>
               
               </div>
               <!--end  用户table -->
               
               
             <!-- begin 角色列表 -->
                <div id="roletab" class="tab-pane">
                
                   <div class="shi-tab">
                   
                   <!-- begin table 头 -->
                     <div class="portlet-title">
                        
                        <div class="caption">
                     		 <span class="list-tab-span"> 角色列表</span>  
                        </div>
                        
                        <div class="actions">
                          <a href="javascript:void(0);" class="shi-btn"  onclick="javascript:db_add('role_list')">  + 新增 </a>
                          <a href="javascript:void(0);" class="shi-btn " ng-click="addRoles( assignment.roles.length)">  +增加表达式</a>
                        </div>
                     
                     </div>
                     
                     <!--end table 头 -->
                     
                     <!-- begin  table 体 -->
                     <div  class="shi-tab-body">
                       
                       <table class="shi-table" id="role_list">
                       
                         <thead>
                            <th style="width: 50%">id</th>
                            <th style="width: 50%">操作</th>
                         </thead>
                         
                         <tbody >
                    
                           <tr  ng-repeat="role in assignment.roles">
                           
								<td>
								    <input id="userField" class="form-control" type="text"  ng-model="role.value" />
								</td>
								
								<td>
								   <a href="javascript:void(0)"  ng-click="removeRoles($index)">删除</a>
								</td>
                           
                           
                           </tr>
                          
	                       
                         </tbody>
                         
                       </table>
                     
                     </div>
                     <!-- end  table 体 -->
                     
                   
                   </div>
                	
                  <!-- begin 操作按钮 -->	
                   <div class="modal-footer" style="border-top:none;">
                     <button ng-click="close()" class="btn btn-primary" translate>ACTION.CANCEL</button>
                    <button ng-click="saveRoles()" class="btn btn-primary" translate>ACTION.SAVE</button>
                  </div>
                  <!--end 操作按钮 -->
                  
               </div>
               <!--end  角色列表 -->
               
               
               
               <!-- begin 机构tab -->
               <div id="govtab" class="tab-pane ">
                
                	<div class="shi-tab">
                	
                	
                	 <!-- begin table 头 -->
                     <div class="portlet-title">
                        
                        <div class="caption">
                     		 <span class="list-tab-span"> 组织机构列表</span>  
                        </div>
                        
                        <div class="actions">
                          <a href="javascript:void(0);" class="shi-btn" onclick="javascript:db_add('dept_list')">  + 新增 </a>
                          <a href="javascript:void(0);" class="shi-btn " ng-click="addDepts(assignment.depts.length)" >  +增加表达式</a>
                        </div>
                     
                     </div>
                     
                     <!--end table 头 -->
                     
                     <!-- begin  table 体 -->
                     <div  class="shi-tab-body">
                       
                       <table class="shi-table" id="dept_list">
                       
                         <thead>
                            <th style="width: 50%">选择的组织机构</th>
                            <th style="width: 50%">操作</th>
                         </thead>
                         
                         <tbody >
                    
                           <tr  ng-repeat="dept in assignment.depts">
                           
								<td>
								    <input id="userField" class="form-control" type="text"  ng-model="dept.value" />
								</td>
								
								<td>
								   <a href="javascript:void(0)"  ng-click="removeDepts($index)">删除</a>
								</td>
                           
                           
                           </tr>
                          
	                       
                         </tbody>
                         
                       </table>
                     
                     </div>
                     <!-- end  table 体 -->
                     
                	
                	
                	</div>
               
                    <!-- begin 操作按钮 -->	
                    <div class="modal-footer" style="border-top:none;">
                      <button ng-click="close()" class="btn btn-primary" translate>ACTION.CANCEL</button>
                     <button ng-click="saveRoles()" class="btn btn-primary" translate>ACTION.SAVE</button>
                   </div>
                  <!--end 操作按钮 -->
                  
               </div>
              
              <!-- end 机构tab --> 
              
              
              
              
                           
             </div>
             
              <!-- end 内容 -->
            
            
            </div>
            
            
            
            
            <!-- end 修改20161205 -->
            
            
            </div>
            
           
        </div>
    </div>
</div>

<!--begin编辑 输入框弹出层 -->

  <div id="outer" class="outer-div" >  
  </div>  
  <div id="inner" class="inner-div" > 
  
  <div class="content-div">
	  <div class="tan_close_div">
	     <a href="javascript:void(0);"  onclick="javascript:close_tan()" >×</a>
	  </div>
	  
	  <!-- 加载iframe -->
	  <div class="frame_div" id="frame_div">
	  
	   <iframe  name="assignee_frame" id="assignee_frame">
	   
	
	   
	   </iframe>
	  
	  </div>
      <!-- end 加载iframe -->
      
	  <div class="tan_btn_div">
	    <a href="javascript:void(0);" class="assignee_ok" onclick="javascript:assignee_ok()"> 确定选择</a>
	    <a href="javascript:void(0);" class="assignee_cancel" onclick="javascript:close_tan()"> 取消选择</a>
	  </div>
 
  </div>
     
  </div> 
  
<!--end 编辑 输入框弹出层 --> 




<script type="text/javascript">


//设置代理用户/取消用户，只有一个代理用户，其他事候选用户或者候选组
//assiflag=main说明是主办人
jQuery(".shi-tab-body").delegate(".check_main .setMainFlag","click",function(){

	var checkedFlag=jQuery(this).attr("assiflag");
	
	var checkedVal=jQuery(this).val();//选中节点的值
	
	 var appElement = document.querySelector('[ng-controller=KisBpmAssignmentPopupCtrl]');
	 var _scope = angular.element(appElement).scope(); 
	
	if(checkedFlag=='main'){//说明是主办人,再次选中转为候选人,将对应的值从angular js 的候选节点移除
	
		_scope.assignment.assignee=null;//将angular js 主办人置为空，将其对应的值放入候选人
	
		var flowUsers= _scope.assignment.candidateUsers;//候选人
		flowUsers.splice(flowUsers.length, 0, {'value':checkedVal});
	
	}else{//候选人转为主办人，查看之前有没有主办信息，要是没有直接改为主办人,将该节点增加到angular js的主办人变量中及将对应的 angular js的对应候选人移除。
		
	   var  mainLength= jQuery(".setMainFlag:checked").length;
	   
	   if(mainLength>1){//说明已经有主办人了,将该主办人改为候选人，将本次选中的改为主办人
		   
		    var candidateUserIndex=0;//本次选择的要移除的候选人位置
		    var prevInputNode=jQuery(this).parents("td").prev().find(".select_chk input");//查找上个节点input节点	

			var index=jQuery(prevInputNode).attr("index");
			candidateUserIndex=Number(index)-1;//候选人下标		

		   //操作angular js model值
		    var  assigneeVal= _scope.assignment.assignee;//旧主办人值
			
		    _scope.assignment.assignee=checkedVal;//给主办人附上新值

			_scope.removeCandidateUserValue(candidateUserIndex);
			
		    var flowUsers= _scope.assignment.candidateUsers;//候选人
		    flowUsers.splice(flowUsers.length+1, 0, {'value':assigneeVal });//将之前的主办改成候选
		   
		   
	   }else{//之前没有主办人，
		   

			jQuery(this).prop("checked",true);//选中
		
			var prevInputNode=jQuery(this).parents("td").prev().find(".select_chk input");//查找上个节点input节点
			
			var index=jQuery(prevInputNode).attr("index");
			index =Number(index)-1;
			
			_scope.removeCandidateUserValue(index);//删除改候选
			_scope.assignment.assignee=checkedVal;
		
		   
	     }

	
	}
	

    _scope.$apply();	
	
    jQuery(".setMainFlag:checked").prop("checked",true);
	
});


//全选、取消选中

jQuery(".select_all_chk").delegate(".main_check","click",function(){

	  var _parent=jQuery(this);//保存this
	  var _that=jQuery(_parent).parents("thead").next("tbody");
	  var checkedFlag=jQuery(_parent).attr("checkflag");
	 
	  if(checkedFlag=='true'){//取消
		
		  jQuery(_parent).attr("checkflag","false");
		  jQuery(_that).find(".select_chk").find("input[type='checkbox']:checked").each(function(){
			  
				jQuery(this).prop("checked",false);
				
			});
		  
		
	}else{//选中
		
		 jQuery(_parent).attr("checkflag","true");
		   jQuery(_that).find(".select_chk").find("input[type='checkbox']").each(function(){
				jQuery(this).prop("checked",true);
				
		});
	
	}
	
});



//增加用户组，显示弹出层


function db_add(obj){
	
	if(!obj){
		console.info("要操作删除的table的id不为空");
		return false;
	}
	if(obj=='user_list'){//用户选择
		
		
		jQuery("#frame_div").find("iframe").prop("src","${pageContext.request.contextPath }/user/assigneeUser");
		jQuery("#outer").addClass("show");
		jQuery("#inner").addClass("show");
		
		//jQuery("#inner").find(".assignee_ok").prop("onclick","javascript:assignee_ok('')");
	}else if(obj=='role_list'){
		
		jQuery("#frame_div").find("iframe").prop("src","${pageContext.request.contextPath }/role/assigneeRole");
		jQuery("#outer").addClass("show");
		jQuery("#inner").addClass("show");
		
	}else{	
		jQuery("#frame_div").find("iframe").prop("src","${pageContext.request.contextPath }/pubController/showAssinDetp?cmd=org");
		jQuery("#inner").addClass("inner-div-dept");
		jQuery("#frame_div").addClass("frame_div_dept");
		jQuery("#outer").addClass("show");
		jQuery("#inner").addClass("show");
		
	}
	
	
}
//关闭弹出层
//取消选择用户、角色、机构
function close_tan(){
	
	jQuery("#outer").hide().removeClass("show");
	jQuery("#inner").hide().removeClass("show");
	
	jQuery("#inner").removeClass("inner-div-dept");
	jQuery("#frame_div").removeClass("frame_div_dept");
	
	jQuery("#frame_div").find("iframe").html("");
	jQuery("#frame_div").find("iframe").prop("scr","");
}



//选择需要的用户
function assignee_ok(){
	
    var frame_pointer=jQuery("#assignee_frame").contents().find(".checkmain");//获取iframe中的input内容保存指正指向

    var select_num=0;
    var data=new Array();
    var userVo;
	jQuery(frame_pointer).each(function(){
		var ischecked=jQuery(this).attr("ischecked");
		if(ischecked=='true'){
			userVo={"id":"","name":""};
			select_num=select_num+1;
			userVo.id=jQuery(this).attr("checkedId");
			userVo.name=jQuery(this).attr("displayName");
			
			data.push(userVo);
		}
		
		
	});
	if(select_num==0){
		alert("请至少选择一条数据");
		return false;
	}
	
	
	addUserHtml(data);//将选中的对象加入要显示的tab
	close_tan();
}



/**
 * 将选中的用户、角色、机构加入到对应的table显示页，且级联加入到对应angular js 参数中去
 */

function addUserHtml(obj){
	
     var that_div=jQuery(".tab-content div:visible");
  
	 var showDivId=jQuery(that_div).attr("id");//显示的页签id，id=usertab：说明是用户组
	 var appElement = document.querySelector('[ng-controller=KisBpmAssignmentPopupCtrl]');
	 var _scope = angular.element(appElement).scope(); 
	 
	 var operateUsers=[];//存放数据
	 
	 if(showDivId=='usertab'){//代理人、候选用户
	   operateUsers=_scope.assignment.candidateUsers;//默认存放候选人
	   
	 }else if(showDivId=='roletab'){//角色tab 过来的数据
		 
		 operateUsers=_scope.assignment.roles;//获取已有的角色
	 }else if(showDivId=='govtab'){//组织机构tab 过来的数据
		 
		 operateUsers=_scope.assignment.depts;//获取已有的组织机构
	 }else{
		 
		 alert("数据异常");
	 }
     
	 var  sel_user= angularJsToArray(operateUsers);//转为数组数据
	
	 for(var i=0;i<obj.length;i++){
		if(sel_user.indexOf(obj[i].id)<0){
		   
		   cascadeAdd(obj[i].id);//级联加入
		}
		
		_scope.$apply();
	
	}
	
}

//将angular js 中的候选人、或者候选人组选出，组装成普通jsArray
function  angularJsToArray(objs){

 var arr=new Array();
  if(objs){	
	for(var i=0;i<objs.length;i++){
	
	   var f= objs[i].value;//将val值放入js
       arr.push(f);	
	
	}

  }
  return  arr;

}

/**
 *级联增加代办人，根据table判断数据来源，将用户id，放入对应angular js变量中，如果是人员默认增加到候选人列表中，
 * 如何增加的是角色或者机构就级联增加到候选组中
 */
function cascadeAdd(dateId){
	
	 var showDivId=jQuery(".tab-content div:visible").attr("id");//显示的页签id，id=usertab：说明是用户组
	 var appElement = document.querySelector('[ng-controller=KisBpmAssignmentPopupCtrl]');
	 var _scope = angular.element(appElement).scope(); 
	 
	if(showDivId=='usertab'){//代理人、候选用户
	
		var flowUsers= _scope.assignment.candidateUsers;//候选人
		flowUsers.splice(flowUsers.length+1, 0, {'value':dateId });
		
		
	}else{//候选组
		
		if(showDivId=='roletab'){//角色tab过来额
			
			  var roles= _scope.assignment.roles;//角色选项
			  roles.splice(roles.length, 0, {'value':dateId });
			  
		}else if(showDivId=='govtab'){//组织机构
			
			  var depts= _scope.assignment.depts;//角色选项
			  depts.splice(depts.length, 0, {'value':dateId });
			  
		}
		
	  
	
	}

	
}


/**
 * 如何删除的是手动增加的tr直接移除不操作angular js 的数据
 * hand_assign：标志手动增加tr标签
 */
function db_delete(obj){

	if(!obj){
		console.info("要操作删除的table的id不为空");
		return false;
	}
	
	var _that=jQuery("#"+obj).find("tbody").find(".select_chk").find("input[type='checkbox']:checked");//存放选中的checkedbox
	var len=jQuery(_that).length;
	 
	if(len==0){
		alert("请选择要删除的数据");
		return false;
	}
	var unchecklen=jQuery("#"+obj).find("tbody").find(".select_chk").find("input[type='checkbox']").length;//table 的总长度
	
	if(unchecklen==len){//全选

		var assiflag="user";//用户
		
		if(obj!='user_list'){//用户
			
			assiflag="group";//组
			
		}
		
		var alldelete="all";
		
		cascadeDelete(assiflag, 0, alldelete);
		
		
	}else{
		
		jQuery(_that).each(function(){
			var trClass=jQuery(this).parents("tr").attr("class");
			
			if(trClass.indexOf("hand_assign")>-1){//说明是手动添加的行直接移除
				jQuery(this).parents("tr").remove();
			}else{
				var assiflag=jQuery(this).attr("assiflag");//人员类型标志 assiflag=main:主代理，否则是从代理
				var index=jQuery(this).attr("index");
				index=Number(index)-1;
				alldelete="sub";
				cascadeDelete(assiflag, index, alldelete);
				//jQuery(this).parents("tr").remove();
			}
		
			
		});
		
	}
	
}


//删除选中的用户、角色、机构,联级修改angular js中数据
/*
 * 一、alldelete=all{说明全部删除，assiflag=user 说明是用户table，assiflag=group 说明是来自角色、机构}
 * 二、alldelete！=all{说明部分删除、assiflag=main说明删除主代理，assiflag=flow 说明删除候选用户、否则是删除候选组。index：要删除数据的下标}
 * 
 */
function cascadeDelete(assiflag,index,alldelete){
	 
	 var appElement = document.querySelector('[ng-controller=KisBpmAssignmentPopupCtrl]');
	 var _scope = angular.element(appElement).scope(); 

	if(alldelete=='all'){//全部清空
		
		if(assiflag=="user"){//用户删除
		
			_scope.assignment.assignee =null;
			_scope.assignment.candidateUsers= [];
			
		}else{//组
		
			_scope.assignment.candidateGroups= [];
			
		}
		
		
	}else{//部分删除
		
		 
		if(assiflag=='main'){//主代理

			_scope.assignment.assignee =null;
			
		}else if(assiflag=='flow'){//候选人

			_scope.removeCandidateUserValue(index);
			
		}else{//候选组
			
			_scope.removeCandidateGroupValue(index);
			
		}
		
	}
	
	_scope.$apply();
	
}



 Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
	if (this[i] == val) return i;
	}
	return -1;
	};
	
 Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
	this.splice(index, 1);
	}
};



</script>