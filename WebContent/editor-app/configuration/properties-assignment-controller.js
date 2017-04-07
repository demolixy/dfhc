/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl = [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/assignment-popup.jsp?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmAssignmentPopupCtrl = [ '$scope', function($scope) {

    	
    // Put json representing assignment on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null) 
    {
        $scope.assignment = $scope.property.value.assignment;//说明分配人里面有值
        
        var candidateGroups=$scope.property.value.assignment.candidateGroups;//放入用户组
    
        //20161227增加
        //begin 将候选组区分成角色数据和部门20161227增加
         $scope.assignment.roles=[];//添加角色属性存放角色选项
		 $scope.assignment.depts=[];//添加部门属性存放组织结构
    	if(candidateGroups!=undefined && candidateGroups.length>0){
    		
    		 for(var i=0;i<candidateGroups.length;i++){
    			 
    			 var   candidateGroupValue =candidateGroups[i].value.toUpperCase();//转大写
    			 
    			 if(candidateGroupValue.indexOf('2011')>-1 || candidateGroupValue.indexOf('ROLE')>-1){//说明是角色过来的
    				
    				   var len=$scope.assignment.roles.length;
    				   $scope.assignment.roles.splice(len, 0, candidateGroups[i]);
    				 
    			 }else{
    					
    					var len= $scope.assignment.depts.length;
    				    $scope.assignment.depts.splice(len, 0, candidateGroups[i]);
    				 
    			 }
    			 
    		 }
    		
    		
    	}
    	//end 将候选组区分成角色数据和部门20161227增加
        
        
    } else {
    	   //20170107 初始化
      
        $scope.assignment = {};
        $scope.assignment.roles=[];//添加角色属性存放角色选项
   	    $scope.assignment.depts=[];//添加部门属性存放组织结构
    }


    
    if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
    {
    	// 20161226注释 $scope.assignment.candidateUsers = [{value: ''}];
    	$scope.assignment.candidateUsers = [];
    }
    
    
    
    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
    };
    
    //20161227增加 该功能 ：增加角色
    $scope.addRoles = function(index) {
    	 $scope.assignment.roles.splice(index + 1, 0, {value: 'ROLE' + userValueIndex++});
    };
    //20161227增加 该功能 ：移除角色
    $scope.removeRoles = function(index) {
    	
   	  $scope.assignment.roles.splice(index,1);
   	  
    };
    
    //20161227增加 该功能:保存用户角色、组织机构 到候选组
    $scope.saveRoles=function(){
    	
    	packGrops($scope);
    	$scope.save ();
    	
    };
    
    //20161227新增，将角色和组织机构数据放入候选组选项中
    var packGrops = function($scope) {
    	
    	var roles=$scope.assignment.roles;//选中的角色类型
    	
    	$scope.assignment.candidateGroups=[];//初始化
    	
    	if(roles!=null && roles!='undefined' && roles.length>0){
    		
    		
    		if(roles!=null && roles!='undefined' && roles.length>0){
        		
    			
        		var len=$scope.assignment.candidateGroups.length;
        		
        		for(var i=0;i<roles.length;i++){
        			
        			$scope.assignment.candidateGroups.splice(len, 0,roles[i]);
        			
        			len++;
        					
        		}
        		
        	}
    		
    	
    		
    	}
    	
    	var depts=$scope.assignment.depts;//选中的组织机构类型
    	
    	if(depts!=null && depts!='undefined' && depts.length>0){
    		
    		
    		var len=$scope.assignment.candidateGroups.length;
    		
    		for(var i=0;i<depts.length;i++){
    			
    			$scope.assignment.candidateGroups.splice(len, 0,depts[i]);
    			
    			len++;
    					
    		}
    		
    	}

    	
    };
    
    //20161227增加 该功能 ：增加组织机构
    $scope.addDepts = function(index) {
    	
    	 $scope.assignment.depts.splice(index + 1, 0, {value: 'depts' + userValueIndex++});
    };
    //20161228增加 该功能 ：删除组织机构
    $scope.removeDepts=function(index){
    	
    	$scope.assignment.depts.splice(index, 1);
    };

    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function(index) {
        $scope.assignment.candidateUsers.splice(index, 1);
    };
    
    if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
    {
    	$scope.assignment.candidateGroups = [{value: ''}];
    }
    
    var groupValueIndex = 1;
    $scope.addCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function(index) {
        $scope.assignment.candidateGroups.splice(index, 1);
    };

    $scope.save = function() {

        $scope.property.value = {};
        handleAssignmentInput($scope);
        $scope.property.value.assignment = $scope.assignment;
        
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

  
    
    // Close button handler
    $scope.close = function() {
    	handleAssignmentInput($scope);
    	$scope.property.mode = 'read';
    	$scope.$hide();
    };
    
 
    	

    
    var handleAssignmentInput = function($scope) {
    	if ($scope.assignment.candidateUsers)
    	{
	    	var emptyUsers = true;
	    	var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateUsers.length; i++)
	        {
	        	if ($scope.assignment.candidateUsers[i].value != '')
	        	{
	        		emptyUsers = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyUsers)
	        {
	        	$scope.assignment.candidateUsers = undefined;
	        }
    	}
        
    	if ($scope.assignment.candidateGroups)
    	{
	        var emptyGroups = true;
	        var toRemoveIndexes = [];
	        for (var i = 0; i < $scope.assignment.candidateGroups.length; i++)
	        {
	        	if ($scope.assignment.candidateGroups[i].value != '')
	        	{
	        		emptyGroups = false;
	        	}
	        	else
	        	{
	        		toRemoveIndexes[toRemoveIndexes.length] = i;
	        	}
	        }
	        
	        for (var i = 0; i < toRemoveIndexes.length; i++)
	        {
	        	$scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
	        }
	        
	        if (emptyGroups)
	        {
	        	$scope.assignment.candidateGroups = undefined;
	        }
    	}
    };
}];