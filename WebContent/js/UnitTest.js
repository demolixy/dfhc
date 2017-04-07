//当前测试方法序号，和以上id对应
var index = 0;
var paraList = new Array();

function Sign(req) {
    paraList.push(req);
    //给请求做签名，该地址返回为：{"id":3,"result":"faec677624be6fc8a4597638b57eca03"}
    //$.post("../Home/Sign", { id: index, request: req }, PostSign);
    //如果不检查签名，替换为如下语句：
    PostSign({ result: "NOTSIGNED" });
}

function PostSign(data) {
    console.debug(data);
    TestIn(data.result);
}

function TestIn(digest) {
    $.post(interfaceUrl, { bizData: paraList[paraList.length - 1], digest: digest }, function (data) {
        TestEnd(data, targetFunctions[index - 1].validator);
    });
}

function TestEnd(data, validator) {
    var pass = validator(data);
    $('#Result').append($("<div></div>").html(JSON.stringify(data)));
    $('#Result').append($("<div></div>").html( (new Date()) +" PASS: " + pass).addClass(pass ? "ok" : "fail"));

    if (index < targetFunctions.length) {
        window.setTimeout(targetFunctions[index++].func);
    }
}

function StartTest(bizData) {
    $('#Result').append($("<div></div>").html((new Date()) + " starting TEST :" + index + " " + targetFunctions[index - 1].func.name));
    $('#Result').append($("<div></div>").html(JSON.stringify(bizData)));
    Sign(JSON.stringify(bizData));
}

//默认判断接口正常只看status
function ValidatorBase(data) {
    return ((data.status == "99") &&
        (data.list == null || data.list.length > 0));
}

var startingPoint = {
    address: "启迪科技大厦C座",
    lng: 116.330758,
    lat: 39.992998
};
