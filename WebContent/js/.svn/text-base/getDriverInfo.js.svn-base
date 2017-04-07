/**
 * 获取司机信息 
 * @param objForm 当前元素
 * @param name1 姓名name
 * @param name2 车牌号name
 * @param name3 身份证号name
 * @param phoneNumber
 */
function getDriverInfo(obj, name1, name2, name3){
	
	$.ajax({
	    type:'GET',//可选get
	    url:getRootPath() + '/ladingbill/getDriverInfo',
	    data:{'phoneNumber':$(obj).val().toString()},
	    dataType:'json',
	    async:true , //false为同步
	    success:function(data,status){
		  if(data.status=="1"){
			  var $_form = $(obj).parents("form");
			  var $_name1 = $_form.find("input[name=" + name1 + "]");
			  var $_name1_value = $_name1.val();
			  if(!$_name1_value || $_name1_value.length == 0){
				  $_name1.val(data.bean.driverName);
			  }
			  var $_name2 = $_form.find("input[name=" + name2 + "]");
			  var $_name2_value = $_name2.val();
			  if(!$_name2_value || $_name2_value.length == 0){
				  $_name2.val(data.bean.licensePlate);
			  }
			  var $_name3 = $_form.find("input[name=" + name3 + "]");
			  var $_name3_value = $_name3.val();
			  if(!$_name3_value || $_name3_value.length == 0){
				  $_name3.val(data.bean.driverIdCardNumber);
			  }
//			  $_form.find("input[name=" + name2 + "]").val(data.bean.licensePlate);
//			  $_form.find("input[name=" + name3 + "]").val(data.bean.driverIdCardNumber);
		  }

	   }
});
	
}