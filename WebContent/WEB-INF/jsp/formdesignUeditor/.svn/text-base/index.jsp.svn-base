<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
    
    <title>WEB表单设计器 </title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="author" content="leipi.org">
    <link href="${pageContext.request.contextPath }/formdesignUeditor/css/bootstrap/css/bootstrap.css?2023" rel="stylesheet" type="text/css" />
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap-ie6.css?2023">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/css/ie.css?2023">
    <![endif]-->
    <link href="css/site.css?2023" rel="stylesheet" type="text/css" />
    
<!--style>
    .list-group-item{padding:0px;}
</style-->
<style type="text/css">
.popup {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 9998;
}
.popup-back {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 9999;
  background: #000;
  opacity: 0.3;
}
.popup-panel {
  position: relative;
  z-index: 10001;
  width: 800px;
  height: 500px;
  margin: 80px auto;
  background: #fff;
}
.span10{
    width: 980px;
}
</style>
 </head>
<body>

<!-- fixed navbar -->
<div class="popup" id="cxcc" style="display: none;">
  <div class="popup-back"></div>
  <div class="popup-panel" style="overflow: auto;min-height: 500px;height: auto;">
   <iframe style="width: 100%;height: 100%;min-height: inherit;border: 1px solid #ccc;" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/formdesign/process.jsp"></iframe>
  </div>
</div>

    


<!-- Docs page layout -->
<div class="container">
<form method="post" id="saveform" name="saveform" action="/index.php?s=/index/parse.html">
<input type="hidden" name="fields" id="fields" value="0">
<input type="hidden" name="id" value="${bean.id }"/>


<div class="row">

<div class="span10">

<script id="myFormDesign" type="text/plain" style="width:100%;">
${bean.templateHtml}
</script>
</div>



</div><!--end row-->

</form>



</div><!--end container-->
    

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/jquery-1.7.2.min.js?2023"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/ueditor.config.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/ueditor.all.js?2023"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/lang/zh-cn/zh-cn.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/formdesign/leipi.formdesign.v4.js"></script>
<!-- script start-->  
<script type="text/javascript">
//子页面传值中介
//iframe值变量 默认124开始每次+4
var variable = 124;
function intermediary(id, name, key){
	try{
		var iframes = document.getElementsByTagName("iframe");
		iframes[iframes.length-1].contentWindow.voluation(id, name, key);
		
	}catch(e){
		variable = variable + 4;
		try{
			document.getElementById("edui" + variable + "_iframe").contentWindow.voluation(id, name, key);
		}catch(e){
			intermediary(id, name, key);
		}
	}
	
}

var leipiEditor = UE.getEditor('myFormDesign',{
            //allowDivTransToP: false,//阻止转换div 为p
            toolleipi:true,//是否显示，设计器的 toolbars
            textarea: 'design_content',   
            //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
            toolbars:[[
            'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|', 'fontfamily', 'fontsize', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', '|', 'inserttable', 'deletetable',  'mergecells',  'splittocells']],
            //focus时自动清空初始化时的内容
            //autoClearinitialContent:true,
            //关闭字数统计
            wordCount:false,
            //关闭elementPath
            elementPathEnabled:false,
            //默认的编辑区域高度
            initialFrameHeight:300
            ///,iframeCssUrl:"css/bootstrap/css/bootstrap.css" //引入自身 css使编辑器兼容你网站css
            //更多其他参数，请参考ueditor.config.js中的配置项
        });
function aa(){
	leipiEditor = UE.getEditor('myFormDesign',{
        //allowDivTransToP: false,//阻止转换div 为p
        toolleipi:true,//是否显示，设计器的 toolbars
        textarea: 'design_content',   
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
        toolbars:[[
        'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|', 'fontfamily', 'fontsize', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', '|', 'inserttable', 'deletetable',  'mergecells',  'splittocells']],
        //focus时自动清空初始化时的内容
        //autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //默认的编辑区域高度
        initialFrameHeight:300
        ///,iframeCssUrl:"css/bootstrap/css/bootstrap.css" //引入自身 css使编辑器兼容你网站css
        //更多其他参数，请参考ueditor.config.js中的配置项
    });
}

 var leipiFormDesign = {
    /*执行控件*/
    exec : function (method) {
        leipiEditor.execCommand(method);
    },
    /*
        Javascript 解析表单
        template 表单设计器里的Html内容
        fields 字段总数
    */
   parse_form:function(template,fields)
    {
        //正则  radios|checkboxs|select 匹配的边界 |--|  因为当使用 {} 时js报错
        var preg =  /(\|-<span(((?!<span).)*leipiplugins=\"(radios|checkboxs|select)\".*?)>(.*?)<\/span>-\||<(img|input|textarea|select).*?(<\/select>|<\/textarea>|\/>))/gi,preg_attr =/(\w+)=\"(.?|.+?)\"/gi,preg_group =/<input.*?\/>/gi;
        if(!fields) fields = 0;

        var template_parse = template,template_data = new Array(),add_fields=new Object(),checkboxs=0;

        var pno = 0;
        template.replace(preg, function(plugin,p1,p2,p3,p4,p5,p6){
            var parse_attr = new Array(),attr_arr_all = new Object(),name = '', select_dot = '' , is_new=false;
            var p0 = plugin;
            var tag = p6 ? p6 : p4;
            //alert(tag + " \n- t1 - "+p1 +" \n-2- " +p2+" \n-3- " +p3+" \n-4- " +p4+" \n-5- " +p5+" \n-6- " +p6);

            if(tag == 'radios' || tag == 'checkboxs')
            {
                plugin = p2;
            }else if(tag == 'select')
            {
                plugin = plugin.replace('|-','');
                plugin = plugin.replace('-|','');
            }
            plugin.replace(preg_attr, function(str0,attr,val) {
                    if(attr=='name')
                    {
                        if(val=='leipiNewField')
                        {
                            is_new=true;
                            fields++;
                            val = 'data_'+fields;
                        }
                        name = val;
                    }
                    
                    if(tag=='select' && attr=='value')
                    {
                        if(!attr_arr_all[attr]) attr_arr_all[attr] = '';
                        attr_arr_all[attr] += select_dot + val;
                        select_dot = ',';
                    }else
                    {
                        attr_arr_all[attr] = val;
                    }
                    var oField = new Object();
                    oField[attr] = val;
                    parse_attr.push(oField);
            }) 
            /*alert(JSON.stringify(parse_attr));return;*/
             if(tag =='checkboxs') /*复选组  多个字段 */
             {
                plugin = p0;
                plugin = plugin.replace('|-','');
                plugin = plugin.replace('-|','');
                var name = 'checkboxs_'+checkboxs;
                attr_arr_all['parse_name'] = name;
                attr_arr_all['name'] = '';
                attr_arr_all['value'] = '';
                
                attr_arr_all['content'] = '<span leipiplugins="checkboxs"  title="'+attr_arr_all['title']+'">';
                var dot_name ='', dot_value = '';
                p5.replace(preg_group, function(parse_group) {
                    var is_new=false,option = new Object();
                    parse_group.replace(preg_attr, function(str0,k,val) {
                        if(k=='name')
                        {
                            if(val=='leipiNewField')
                            {
                                is_new=true;
                                fields++;
                                val = 'data_'+fields;
                            }

                            attr_arr_all['name'] += dot_name + val;
                            dot_name = ',';

                        }
                        else if(k=='value')
                        {
                            attr_arr_all['value'] += dot_value + val;
                            dot_value = ',';

                        }
                        option[k] = val;    
                    });
                    
                    if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                    attr_arr_all['options'].push(option);
                    //if(!option['checked']) option['checked'] = '';
                    var checked = option['checked'] !=undefined ? 'checked="checked"' : '';
                    attr_arr_all['content'] +='<input type="checkbox" name="'+option['name']+'" value="'+option['value']+'"  '+checked+'/>'+option['value']+'&nbsp;';

                    if(is_new)
                    {
                        var arr = new Object();
                        arr['name'] = option['name'];
                        arr['leipiplugins'] = attr_arr_all['leipiplugins'];
                        add_fields[option['name']] = arr;

                    }

                });
                attr_arr_all['content'] += '</span>';

                //parse
                template = template.replace(plugin,attr_arr_all['content']);
                template_parse = template_parse.replace(plugin,'{'+name+'}');
                template_parse = template_parse.replace('{|-','');
                template_parse = template_parse.replace('-|}','');
                template_data[pno] = attr_arr_all;
                checkboxs++;

             }else if(name)
            {
                if(tag =='radios') /*单选组  一个字段*/
                {
                    plugin = p0;
                    plugin = plugin.replace('|-','');
                    plugin = plugin.replace('-|','');
                    attr_arr_all['value'] = '';
                    attr_arr_all['content'] = '<span leipiplugins="radios" name="'+attr_arr_all['name']+'" title="'+attr_arr_all['title']+'">';
                    var dot='';
                    p5.replace(preg_group, function(parse_group) {
                        var option = new Object();
                        parse_group.replace(preg_attr, function(str0,k,val) {
                            if(k=='value')
                            {
                                attr_arr_all['value'] += dot + val;
                                dot = ',';
                            }
                            option[k] = val;    
                        });
                        option['name'] = attr_arr_all['name'];
                        if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                        attr_arr_all['options'].push(option);
                        //if(!option['checked']) option['checked'] = '';
                        var checked = option['checked'] !=undefined ? 'checked="checked"' : '';
                        attr_arr_all['content'] +='<input type="radio" name="'+attr_arr_all['name']+'" value="'+option['value']+'"  '+checked+'/>'+option['value']+'&nbsp;';

                    });
                    attr_arr_all['content'] += '</span>';

                }else
                {
                    attr_arr_all['content'] = is_new ? plugin.replace(/leipiNewField/,name) : plugin;
                }
                //attr_arr_all['itemid'] = fields;
                //attr_arr_all['tag'] = tag;
                template = template.replace(plugin,attr_arr_all['content']);
                template_parse = template_parse.replace(plugin,'{'+name+'}');
                template_parse = template_parse.replace('{|-','');
                template_parse = template_parse.replace('-|}','');
                if(is_new)
                {
                    var arr = new Object();
                    arr['name'] = name;
                    arr['leipiplugins'] = attr_arr_all['leipiplugins'];
                    add_fields[arr['name']] = arr;
                }
                template_data[pno] = attr_arr_all;

               
            }
            pno++;
        })
        var parse_form = new Object({
            'fields':fields,//总字段数
            'template':template,//完整html
            'parse':template_parse,//控件替换为{data_1}的html
            'data':template_data,//控件属性
            'add_fields':add_fields//新增控件
        });
        return JSON.stringify(parse_form);
    },
    /*type  =  save 保存设计 versions 保存版本  close关闭 */
    fnCheckForm : function ( type ) {
    	
    	
        if(leipiEditor.queryCommandState( 'source' ))
            leipiEditor.execCommand('source');//切换到编辑模式才提交，否则有bug
            
        if(leipiEditor.hasContents()){
            leipiEditor.sync();/*同步内容*/
            
            //alert("你点击了保存,这里可以异步提交，请自行处理....");
            //return false;
            //--------------以下仅参考-----------------------------------------------------------------------------------------------------
            var type_value='',formid=0,fields=$("#fields").val(),formeditor='';

            if( typeof type!=='undefined' ){
                type_value = type;
            }
            //获取表单设计器里的内容
            formeditor=leipiEditor.getContent();
            //解析表单设计器控件
            var parse_form = this.parse_form(formeditor,fields);
            parse_form = eval('(' + parse_form + ')').template;
            var templateHtml = "";
            var templateFreemark = "";
            if(type == 'convert'){
        		if(confirm("是否将html代码覆盖源代码？")){
        			templateHtml = parse_form;
        		}	
        		parse_form = disposeForm(parse_form);
        		templateFreemark = parse_form;
        	}else if(type == 'form'){
			//添加form标签
			
        	}else{
        		templateHtml = parse_form;
        	}
            
             //异步提交数据
             $.ajax({
                type: 'POST',
                url : '${pageContext.request.contextPath }/formtemplate/updateTemplate',
                //dataType : 'json',
                data : {'id':'${bean.id}', 'templateHtml':templateHtml, 'templateFreemark':templateFreemark},
                success : function(data){
                   alert(data.message);
                   if(data.status == '1'){
                	   window.location = "<%=request.getContextPath()%>/formtemplate";
                   }
                },
                error:function(xhr,s1,s2){
 		    	   $this.removeAttr("disabled");
 			       	var message = xhr.responseText;
 						eval("var json = "+message);
 						if($.trim(json.error).length==0){
 							$.alert('系统消息', '数据异常,请刷新页面重试');
 						}else{
 							$.alert('系统消息', json.error);
 						}
 			  	   }
            });
            
        } else {
            alert('表单内容不能为空！')
            $('#submitbtn').button('reset');
            return false;
        }
    } ,
    /*预览表单*/
    fnReview : function (){
        if(leipiEditor.queryCommandState( 'source' ))
            leipiEditor.execCommand('source');/*切换到编辑模式才提交，否则部分浏览器有bug*/
            
        if(leipiEditor.hasContents()){
            leipiEditor.sync();       /*同步内容*/
            
             alert("你点击了预览,请自行处理....");
            return false;
            //--------------以下仅参考-------------------------------------------------------------------


            /*设计form的target 然后提交至一个新的窗口进行预览*/
            document.saveform.target="mywin";
            window.open('','mywin',"menubar=0,toolbar=0,status=0,resizable=1,left=0,top=0,scrollbars=1,width=" +(screen.availWidth-10) + ",height=" + (screen.availHeight-50) + "\"");

            document.saveform.action="/index.php?s=/index/preview.html";
            document.saveform.submit(); //提交表单
        } else {
            alert('表单内容不能为空！');
            return false;
        }
    }
};
 //处理模板
function disposeForm(formHtml){
	 //处理input标签
	var formEle = disposeInput(formHtml);
	//处理textarea
	formEle = disposeTextarea(formEle);
	//处理select
	formEle = disposeSelect(formEle);
	//处理单选框组
	formEle = disposeSpanRadios(formEle);
	//处理复选框组
	formEle = disposeSpancheckboxs(formEle);
	return formEle;	
	
}
function disposeSpancheckboxs(formHtml){
	var formEle = formHtml;
	var re = /<span.*?leipiplugins="checkboxs".*?<\/span>/g;
	
	var arr = formEle.match(re);

	if(arr){
		var name = /<input.*?name="(.*?)".*?>/;
		var mapKey = /<input.*?mapkey="(.*?)".*?>/;
		var mapValue = /<input.*?mapvalue="(.*?)".*?>/;
		var preReg = /<span.*?leipiplugins="checkboxs".*?>/g;
		var sufReg = /<\/span>/g;
		for(var i=0; i<arr.length; i++){
			var spanHtml = arr[i];
			var arr2 = name.exec(spanHtml);
			mapKey = mapKey.exec(spanHtml);
			mapValue = mapValue.exec(spanHtml);
			if(!arr2){
				continue;
			}
			
			var preSpan = spanHtml.match(preReg);
			var sufSpan = spanHtml.match(sufReg);
			var radiosTemplate = "";
			radiosTemplate += preSpan;
			radiosTemplate += "<#list " + arr2[1] + " as map>";
			radiosTemplate += "<input name='" + arr2[1] + "' value='DFHCYJHOUPREFIXmap." + mapKey[1] + "DFHCYJHOUSUFFIX' type='checkbox'/>DFHCYJHOUPREFIXmap." + mapValue[1] + "DFHCYJHOUSUFFIX&nbsp;";
			radiosTemplate += "</#list>";
			radiosTemplate += sufSpan;
			
			formEle = replacHtml(spanHtml, radiosTemplate, formEle);
		}
	}
	return formEle;
} 
function disposeSpanRadios(formHtml){
	//<span leipiplugins="radios" title="1" name="leipiNewField">
	var formEle = formHtml;
	var re = /<span.*?leipiplugins="radios".*?<\/span>/g;
	
	var arr = formEle.match(re);

	if(arr){
		var name = /<input.*?name="(.*?)".*?>/;
		var mapKey = /<input.*?mapkey="(.*?)".*?>/;
		var mapValue = /<input.*?mapvalue="(.*?)".*?>/;
		var preReg = /<span.*?leipiplugins="radios".*?>/g;
		var sufReg = /<\/span>/g;
		for(var i=0; i<arr.length; i++){
			var spanHtml = arr[i];
			var arr2 = name.exec(spanHtml);
			mapKey = mapKey.exec(spanHtml);
			mapValue = mapValue.exec(spanHtml);
			if(!arr2){
				continue;
			}
			
			var preSpan = spanHtml.match(preReg);
			var sufSpan = spanHtml.match(sufReg);
			var radiosTemplate = "";
			radiosTemplate += preSpan;
			radiosTemplate += "<#list " + arr2[1] + " as map>";
			radiosTemplate += "<input name='" + arr2[1] + "' value='DFHCYJHOUPREFIXmap." + mapKey[1] + "DFHCYJHOUSUFFIX' type='radio'/>DFHCYJHOUPREFIXmap." + mapValue[2] + "DFHCYJHOUSUFFIX&nbsp;";
			radiosTemplate += "</#list>";
			radiosTemplate += sufSpan;
			
			formEle = replacHtml(spanHtml, radiosTemplate, formEle);
		}
	}
	return formEle;
} 
function disposeSelect(formHtml){
	var formEle = formHtml;
	var re =/<select.*?>.*?<\/select>/g;
	var arr = formHtml.match(re);
	if(arr){
		var pre = /<select.*?>/g;
		var suf = /<\/select>/g;
		for(var i=0; i<arr.length; i++){
			var preHtml = arr[i].match(pre);
			var sufHtml = arr[i].match(suf);
			var name = /name="(.*?)"/g;
			var mapKey = /mapkey="(.*?)"/g;
			var mapValue = /mapvalue="(.*?)"/g;
			
			var nameValue = name.exec(preHtml)[1];
			mapKey = mapKey.exec(preHtml)[1];
			mapValue = mapValue.exec(preHtml)[1];
			
			var options = disposeOption(arr[i], nameValue, mapKey, mapValue);
			
			var newHtml = preHtml + options + sufHtml;
			
			formEle = replacHtml(arr[i], newHtml, formEle);
		}
	}
	return formEle;
	
} 
function disposeOption(formHtml, nameValue, mapKey, mapValue){
	var re =/<option.*?>.*?<\/option>/;
	var arr = formHtml.match(re);
	var optionTemplate = "";
	if(arr){
		var option = arr[0];
		var pre = /<option.*?>/g;
		var suf = /<\/option>/g;
		var preHtml = option.match(pre);
		var sufHtml = option.match(suf);
		var value = /value="(.*?)"/g;
		optionTemplate += "<#list " + nameValue + " as map>";
		optionTemplate += replacHtml(value.exec(preHtml)[0], "value='DFHCYJHOUPREFIXmap." + mapKey + "DFHCYJHOUSUFFIX'", preHtml[0]);
		optionTemplate += "DFHCYJHOUPREFIXmap." + mapValue + "DFHCYJHOUSUFFIX";
		optionTemplate += sufHtml[0];
		optionTemplate += "</#list>";
	}
	return optionTemplate;
} 
//处理texterea
function disposeTextarea(formHtml){
	var formEle = formHtml;
	var re =/<textarea.*?>.*?<\/textarea>/g;
	var arr = formHtml.match(re);
	
	
	if(arr){
		var pre = /<textarea.*?>/g;
		var suf = /<\/textarea>/g;
		
		for(var i=0; i<arr.length; i++){
			var name = /name="(.*?)"/g;
			var preHtml = arr[i].match(pre);
			var sufHtml = arr[i].match(suf);
			formEle = replacHtml(arr[i], preHtml + "DFHCYJHOUPREFIX" + name.exec(preHtml)[1] + "DFHCYJHOUSUFFIX" + sufHtml, formEle);
		}
	}
	return formEle;
} 
 
 //处理文本框  单选框  复选框
function disposeInput(formHtml){
	var formEle = formHtml;
	var re =/<input.*?>/g;
	var arr = formHtml.match(re);
	debugger
	var eleRe = /type="(.*?)"/;
	var eleRe2 = /leipiplugins="(.*?)"/;
	if(arr){
		for(var i=0; i<arr.length; i++){
			var ele = arr[i];
			var type = eleRe.exec(ele);
			if(type && type.length > 0){
				if(type[1] == "text"){
					var inputReplac = inputReplace(type[1], ele);
					formEle = replacHtml(ele, inputReplac, formEle);
				}
				continue;
			}
			var leipiplugins = eleRe2.exec(ele);
			
			if(leipiplugins && leipiplugins.length > 0){
				formEle = replacHtml(ele, inputReplace(leipiplugins[1], ele), formEle);
				continue;
			}
		}
	}
	return formEle;
	
} 
function inputReplace(type, formHtml){
	if(type == "text"){//文本框
		return textDispose(formHtml);
	
	}else if(type == "radio" || type == "checkbox"){//单选框 复选框
		return radioOrCheckboxDispose(formHtml);
	}
}
function replacHtml(ele, inputReplace, formEle){
	if(inputReplace){
		formEle = formEle.replace(ele, inputReplace);
	}	
	return formEle;
}

function textDispose(formHtml){
	var eleRegName = /name="(.*?)"/;
	var name = eleRegName.exec(formHtml);
	name = name[1]?name[1]:name;
	var eleRegValue = /value="(.*?)"/;
	var value = eleRegValue.exec(formHtml);
	value = value[0];
	var newValue = "value='DFHCYJHOUPREFIX" + name + "DFHCYJHOUSUFFIX'";
	return formHtml.replace(value, newValue);
}
function radioOrCheckboxDispose(formHtml){
	var re =/<input(.*?)>/;//排除没有标签的元素
	var isEle = re.exec(formHtml);
	if(isEle && isEle.length > 0){
		//var eleValue = /orgradios="(.*?)"/;
		//eleValue = eleValue[1];
		var eleName = /name="(.*?)"/.exec(formHtml);
		var nameValue = eleName[1];
		var name = eleName[0];
		
		var template = "<#list " + nameValue + " as map><#list map?keys as itemKey>";
			template +=	"DFHCYJHOUPREFIXmap[itemKey]DFHCYJHOUSUFFIX:"+formHtml.replace(name, name + " value='DFHCYJHOUPREFIXitemKeyDFHCYJHOUSUFFIX'");
			template +=	"</#list></#list>";
		return template;
	}else{
		return null;
	}
}
function getHtml(){
	leipiEditor.sync();/*同步内容*/
	 //获取表单设计器里的内容
    formeditor=leipiEditor.getContent();
    //解析表单设计器控件
    var parse_form = leipiFormDesign.parse_form(formeditor,fields);
    parse_form = eval('(' + parse_form + ')').template;
	return parse_form;
}
</script>
<!-- script end -->




</body>
</html>