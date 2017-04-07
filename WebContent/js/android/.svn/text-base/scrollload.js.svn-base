
/*用法 pageIndex,pageSize 2个参数每次都会post服务器
$(function () {
    var scroll = new scroll_load();
    scroll.setting.data_url = "/tools/mobile_submit_ajax.ashx?action=mobile_get_anlizhanshi";
    scroll.setting.callback = function (result) {
        /*通常result返回的json 这里再进行拼接处理
        $(".caseList").append(result);
    }
    scroll.Init();
})*/

var scroll_load = function () {
    var _this = this;
    this.setting = {
        pageIndex: 1,  //开始页索引
        pageSize: 15,   //每次加载数量
        post_data: "", //同步发送的post数据
        data_url: "",  //ajax请求url
        callback: false//请求成功之后的回调方法
    };

    this.isend = 0;
    this.isloading = 0;
    this.winH = 500;

    this.Init = function () {
    	if(_this.setting.data_url.indexOf("?operation") != -1){
    		_this.setting.data_url = _this.setting.data_url.substring(0, _this.setting.data_url.lastIndexOf("?")) + "?operation=" + $(".zc_nav").find(".on").attr("operation")
    	}else{
    		_this.setting.data_url = _this.setting.data_url + "?operation=" + $(".zc_nav").find(".on").attr("operation")
    	}
    	
    	
        _this.winH = $(window).height(); //页面可视区域高度
        get_data(_this.setting.callback, function () {
            bind_scroll_event(_this.setting.callback);
        });
    };
    get_data = function (callback1, callback2) {
      	$(".lazy").show();
        _this.isloading = 1;
        var post_data = "pageIndex=" + _this.setting.pageIndex + "&pageSize=" + _this.setting.pageSize;
        if (_this.setting.post_data) {
            post_data = post_data+"&" + _this.setting.post_data;
        }
        $.ajax({
            type: "POST",//?operation=" + operation
            url: _this.setting.data_url,
            data: post_data,
            success: function (result) {
        	$(".lazy").hide();
                if (result.status == '0') {
             
//            		$(".noneData").show();
//            		$(".nonesj").show();
            		
                	_this.isend = 1;
                    $(window).unbind('scroll');
                    return;
                }else if(result.status == '2'){
                	window.location = getRootPath() + "/jsp/android/login.jsp";
                }else {
//                	$(".noneData").hide();
//            		$(".nonesj").hide()
                	
                    if (callback1) {
                        callback1(result);
                    }
                    _this.setting.pageIndex = _this.setting.pageIndex + 1;
                    
                }
                if(_this.setting.pageIndex*_this.setting.pageSize>=Number(result.totalCount)){
                    $(window).unbind('scroll');
                    return;
                }

                
                _this.isloading = 0;
                if (callback2)
                    callback2(callback1);
            }
        });
    };
    bind_scroll_event = function (callback1) {
        $(window).scroll(function () {
            if (_this.isend == 1) {
                return;
            }
            if (_this.isloading == 1) {
                return;
            }
            var pageH = $(document.body).height();
            var scrollT = $(window).scrollTop();
            var offset_bottom = (pageH - _this.winH - scrollT) / _this.winH;
            if (offset_bottom < 0.02) {
                get_data(callback1, null);
            }
        });
    }
};








