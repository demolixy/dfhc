<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.dfhc.wk.dataset.service.DataSetService"%>
<%@page import="org.quickbundle.base.beans.factory.RmBeanFactory"%>
<%@page import="java.util.List"%>
<%@page import="com.dfhc.wk.dataset.vo.DataSetVo"%>
<%@page import="com.dfhc.ISystemConstant"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>下拉菜单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <meta name="generator" content="www.leipi.org" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-ie6.css">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/ie.css">
    <![endif]-->
    <link rel="stylesheet" href="leipi.style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/dialogs/internal.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/jquery-1.7.2.min.js?2023"></script>
    <script type="text/javascript">
function createElement(type, name)
{     
    var element = null;     
    try {        
        element = document.createElement('<'+type+' name="'+name+'">');     
    } catch (e) {}   
    if(element==null) {     
        element = document.createElement(type);     
        element.name = name;     
    } 
    return element;     
}
function fnSelect( combo ) {
    var iIndex = combo.selectedIndex ;
    oListText.selectedIndex    = iIndex ;
    var olistText    = document.getElementById( "orgtext" ) ;
    olistText.value    = oListText.value ;
}

function fnAdd() {
    var olistText    = document.getElementById( "orgtext" ) ;
    var orgtextHide    = document.getElementById( "orgtextHide" ) ;
    fnAddComboOption( oListText, olistText.value, orgtextHide.value ) ;
    oListText.selectedIndex = oListText.options.length - 1 ;
    olistText.value    = '' ;
    olistText.focus() ;
}

function fnModify() {
    var iIndex = oListText.selectedIndex ;
    if ( iIndex < 0 ) return ;
    var olistText    = document.getElementById( "orgtext" ) ;
    oListText.options[ iIndex ].innerHTML    = fnHTMLEncode( olistText.value ) ;
    oListText.options[ iIndex ].value        = olistText.value ;
    olistText.value    = '' ;
    olistText.focus() ;
}

function fnMove( steps ) {
    fnChangeOptionPosition( oListText, steps ) ;
}

function fnDelete() {
    fnRemoveSelectedOptions( oListText ) ;
}

function fnSetSelectedValue() {
    var iIndex = oListText.selectedIndex ;
    if ( iIndex < 0 ) return ;
    
}

// Moves the selected option by a number of steps (also negative)
function fnChangeOptionPosition( combo, steps ) {
    var iActualIndex = combo.selectedIndex ;
    if ( iActualIndex < 0 ){
        return ;
    }
    var iFinalIndex = iActualIndex + steps ;
    if ( iFinalIndex < 0 ){
        iFinalIndex = 0 ;
    }
    if ( iFinalIndex > ( combo.options.length - 1 ) ) {
        iFinalIndex = combo.options.length - 1 ;
    }
    if ( iActualIndex == iFinalIndex ) {
        return ;
    }
    var oOption = combo.options[ iActualIndex ] ;
    if(oOption.value=="") {
        var sText    = fnHTMLDecode( oOption.value ) ;
    } else {
        var sText    = fnHTMLDecode( oOption.innerHTML ) ;
    }
    combo.remove( iActualIndex ) ;
    oOption = fnAddComboOption( combo, sText, sText, null, iFinalIndex ) ;
    oOption.selected = true ;
}

// Remove all selected options from a SELECT object
function fnRemoveSelectedOptions( combo ) {
    // Save the selected index
    var iSelectedIndex = combo.selectedIndex ;
    var oOptions = combo.options ;
    // Remove all selected options
    for ( var i = oOptions.length - 1 ; i >= 0 ; i-- ) {
        if (oOptions[i].selected) combo.remove(i) ;
    }

    // Reset the selection based on the original selected index
    if ( combo.options.length > 0 ) {
        if ( iSelectedIndex >= combo.options.length ) iSelectedIndex = combo.options.length - 1 ;
        combo.selectedIndex = iSelectedIndex ;
    }
}

// Add a new option to a SELECT object (combo or list)
function fnAddComboOption( combo, optionText, optionValue, documentObject, index ) {
    var oOption ;
    if ( documentObject ) {
        oOption = documentObject.createElement("option") ;
    } else {
        oOption = document.createElement("option") ;
    }
    if ( index != null ) {
        combo.options.add( oOption, index ) ;
    } else {
        combo.options.add( oOption ) ;
    }
    oOption.innerHTML = optionText.length > 0 ? fnHTMLEncode( optionText ) : '&nbsp;' ;
    oOption.value     = optionValue ;
    return oOption ;
}

function fnHTMLEncode( text ) {
    if ( !text ) {
        return '' ;
    }
    text = text.replace( /&/g, '&amp;' ) ;
    text = text.replace( /</g, '&lt;' ) ;
    text = text.replace( />/g, '&gt;' ) ;
    return text ;
}


function fnHTMLDecode( text ) {
    if ( !text ) {
        return '' ;
    }
    text = text.replace( /&gt;/g, '>' ) ;
    text = text.replace( /&lt;/g, '<' ) ;
    text = text.replace( /&amp;/g, '&' ) ;
    return text ;
}

function fnSetAttribute( element, attName, attValue ) {
    if ( attValue == null || attValue.length == 0 ){
        element.removeAttribute( attName, 0 ) ;        
    } else {
        element.setAttribute( attName, attValue, 0 ) ;    
    }
}
    </script>
</head>
<body>
<div class="content">
    <table class="table table-bordered table-striped table-hover">
     <tr>
        <th><span>控件名称</span><span class="label label-important">*</span></th>
        <th><span>数据集</span> </th>
    </tr>
    <tr>
        <td><input id="orgname" placeholder="必填项" type="text"/></td>
        <td>
        	<select id="dataSet" namde="dataSet">
        		<option value="01">-- 请选择数据集 --</option>
        		<%
        		DataSetService dataSetService = (DataSetService)RmBeanFactory.getBean("com.dfhc.wk.dataset.service.DataSetService");
        		
        		Map<String, Object> params = new HashMap<String, Object>();
        		
        		List<DataSetVo> vos = dataSetService.list(params, null);
        		
        		if(!CollectionUtils.isEmpty(vos)){
					for(DataSetVo vo : vos){
				%>		
				
				<option value="<%=vo.getId()%>" id="<%=vo.getId()%>" type="<%=vo.getDataSetType() %>" desc="<%=vo.getName() %>"><%=vo.getName() %></option>
									
				<%		
					}
        		}
        		%>
        	</select>
        
         </td>
    </tr>
  	<tr>
  		<th><span>key</span><span class="label label-important">*</span></th>
        <th><span>value</span> <span class="label label-important">*</span></th>
  	
  	</tr>
  	<tr>
  		<td>
  			<input type="text" name="mapKey" id="mapKey"/>
  		</td>
        <td><input type="text" name="mapValue" id="mapValue"/></td>
  	
  	</tr>
    <tr>
        <th colspan="2">
            <span>列表值</span> 
        </th>
    </tr>
    <tr>
        <td colspan="2">
            <select id="orglist"  multiple="multiple" class="span14"></select>
        </td>
    </tr>
    <tr>
        <td>
            <div class="btn-group pull-right">
                <a title="新增" onclick="fnAdd();" class="btn btn-primary"><i class="icon-white icon-plus"></i></a>
                <a title="修改" onclick="fnModify();" class="btn btn-default"><i class="icon-edit"></i></a>
            </div>
            <input type="text" placeholder="输入列表隐藏值..." class="span2" id="orgtextHide">
            <input type="text" placeholder="输入列表显示值..." class="span2" id="orgtext">
        </td>
        <td>
            <div class="btn-group">
                <button title="上移" onclick="fnMove(-1);" class="btn btn-default"><i class="icon-arrow-up"></i></button>
                <button title="下移" onclick="fnMove(1);" class="btn btn-default"><i class="icon-arrow-down"></i></button>
                <button title="设为初始化时选定值" onclick="fnSetSelectedValue();" class="btn btn-default"><i class="icon-ok-circle"></i></button>
                <button title="删除" onclick="fnDelete();" class="btn btn-default"><i class="icon-ban-circle"></i></button>
            </div>
        </td>
        
    </tr>
    </table>
</div>
<script type="text/javascript">
var oNode = null,oListText='',thePlugins = 'select';
window.onload = function() {
    oListText = $G('orglist');
    
    if( UE.plugins[thePlugins].editdom ){
        oNode = UE.plugins[thePlugins].editdom;
        var gTitle=oNode.getAttribute('title').replace(/&quot;/g,"\"");
        gTitle = gTitle==null ? '' : gTitle;
       
        $G('orgname').value = gTitle;
       
       
        for ( var i = 0 ; i < oNode.options.length ; i++ ) {
            var sText    = oNode.options[i].value ;
            fnAddComboOption( oListText, oNode.options[i].text, sText ) ;
        }
    }
    /*$('#showTips').popover();*/
}
dialog.oncancel = function () {
    if( UE.plugins[thePlugins].editdom ) {
        delete UE.plugins[thePlugins].editdom;
    }
};
dialog.onok = function (){
    if( $G('orgname').value == '') {
        alert('控件名称不能为空');
        return false;
    }
    if( $G('mapKey').value == '') {
        alert('key不能为空');
        return false;
    }
    if( $G('mapValue').value == '') {
        alert('value不能为空');
        return false;
    }
    
   
    
    var datraSet = document.getElementById("dataSet");
    var dataSetValue = datraSet.value;
    if(dataSetValue && dataSetValue == '01'){
    	if( oListText.options.length == 0 ) {
            alert('请添加下拉菜单选项或者选择数据集！');
            return false;
        }
    }
    
   
     
     
    if( !oNode ) {
        try {
            //oNode = document.createElement("select"); 
            oNode = createElement('select',$G('orgname').value);
            oNode.setAttribute('title',$G('orgname').value);
            oNode.setAttribute('leipiPlugins',thePlugins );
            oNode.setAttribute('mapKey',$G('mapKey').value );
            oNode.setAttribute('mapValue',$G('mapValue').value );
            
           
            if(dataSetValue && dataSetValue != '01'){
            	var datraSetId ="",
	            datraSetType = "",
	            datraSetDesc = "";
            	
            	var optionsEle = document.getElementById("dataSet").options;
            	for(var i=0; i<optionsEle.length; i++){
            		if(optionsEle[i].value == dataSetValue){
            			datraSetId = optionsEle[i].getAttribute("id");
            			datraSetType = optionsEle[i].getAttribute("type");
            			datraSetDesc = optionsEle[i].getAttribute("desc");
            			break;
            		}
            	}
            	
	            
	            oNode.setAttribute('dataSetId', datraSetId);
	            oNode.setAttribute('datraSetType', datraSetType);
	            oNode.setAttribute('desc', datraSetDesc);
	            fnAddComboOption( oNode, "测试数据", "ceshi") ;
            }else{
            	// Add all available options.
                for ( var i = 0 ; i < oListText.options.length ; i++ ) {
                	
                    var sText    = oListText.options[i].value ;
                    if ( sText.length == 0 ) {
                        sText = sText ;
                    }
                    var oOption = fnAddComboOption( oNode, oListText.options[i].text, sText ) ;
                   
                }
            }
            
            
            
            
            //firefox要利用span
            editor.execCommand('insertHtml','<span leipiplugins="select">'+oNode.outerHTML+'&nbsp;&nbsp;</span>');
            return true ;
        } catch ( e ) {
            try {
                editor.execCommand('error');
            } catch ( e ) {
                alert('控件异常，请到 [雷劈网] 反馈或寻求帮助！');
            }
            return false;
        }
    } else {
        oNode.setAttribute('title', $G('orgname').value); 
        
        // Remove all options.
        while ( oNode.options.length > 0 ){
            oNode.remove(0) ;
        }
        for ( var i = 0 ; i < $G('orglist').options.length ; i++ ) {
            var sText    = $G('orglist').options[i].value ;
            if ( sText.length == 0 ) {
                sText = sText ;
            }
            var oOption = fnAddComboOption( oNode, $G('orglist').options[i].text, sText ) ;
            
        }
        delete UE.plugins[thePlugins].editdom; 
    }
};
</script>
</body>
</html>