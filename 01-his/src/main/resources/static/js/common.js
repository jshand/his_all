window.constantsCache = null;

$(function(){
    //查询系统缓存
    $.get("/hisconfig/getJsonCache",function (cache) {
        constantsCache = cache;
        loaded();//触发页面加载完成
    })
})