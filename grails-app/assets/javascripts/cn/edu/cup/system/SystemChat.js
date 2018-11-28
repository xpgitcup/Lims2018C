/**
 * Created by LiXiaoping on 2017/3/27.
 * 对js文件进行改造---引入通用tab管理
 */

var operation4SystemChatDiv

$(function(){

    console.info($("title").text() + "加载成功...");

    var tabList = ["我在听", "我在说", "整体显示", "对话"]

    tabPagesManagerWithPagination("operation4SystemChatDiv", tabList, loadSystemChatData, countSystemChatData);
});

function countSystemChatData(title) {
    console.info("自定义统计函数：" + title);
    var total = ajaxCalculate("operation4SystemChat/count?title=" + title);
    console.info("统计结果：" + total);
    return total;

}

function loadSystemChatData(title, page, pageSize) {
    console.info("自定义列表函数：" + title + " 页码 " + page + "页大小" + pageSize);
    var params = getParams(page, pageSize) + "&title=" + title;    //getParams必须是放在最最前面！！
    console.info(params)
    ajaxRun("operation4SystemChat/list" + params, 0, "list" + title + "Div");
    $.cookie("currentPage" + title, page);
}


//----------------------------------------------------------------------------------------------------------------------
/*
 * 新建
 * */
function createSystemChat(id) {
    operation4SystemChatDiv.tabs("select", "对话");
    ajaxRun("operation4SystemChat/createSystemChat", id, "showSystemChatDiv");
}

/*
 * 编辑
 * */
function editSystemChat(id) {
    //console.info("编辑SystemChat." + id);
    ajaxRun("operation4SystemChat/editSystemChat", id, "showSystemChatDiv");
}

/*
 * 显示当前属性
 * */
function showSystemChat(id) {
    //console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4SystemChat/getSystemChat", id, "showSystemChatDiv");
    }
}


