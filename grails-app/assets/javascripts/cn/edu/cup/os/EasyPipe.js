/**
 * Created by LiXiaoping on 2017/5/30.
 */

var listSystemLogDiv;
var paginationListSystemLogDiv;
var listSystemChatDiv;
var paginationListSystemChatDiv;

$(function() {
    console.info("这是首页...");

    //有关systemLog的设置------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listSystemLogDiv = $("#listSystemLogDiv");
    paginationListSystemLogDiv = $("#paginationListSystemLogDiv")

    //获取当前页
    var currentPgaeSystemLog = readCookie("currentPgaeSystemLog", 1);
    var pageSizeSystemLog = readCookie("pageSizeSystemLog", 5);
    //var totalSystemLog = countSystemLog();        //建立分页的时候已经设置了。
    //console.info("记录总数：" + totalSystemLog);

    //加载数据
    listSystemLog(currentPgaeSystemLog, pageSizeSystemLog);

    //分页
    paginationListSystemLogDiv.pagination({
        pageSize: pageSizeSystemLog,
        //total: totalSystemLog,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemLog(pageNumber, pageSize);
        }
    });

    //endof 有关systemLog的设置------------------------------------------------------------------------------------------

    //获取Div
    listSystemChatDiv = $("#listSystemChatDiv");
    paginationListSystemChatDiv = $("#paginationListSystemChatDiv");

    //获取当前页
    var currentPgaeSystemChat = readCookie("currentPgaeSystemChat", 1);
    var pageSizeSystemChat = readCookie("pageSizeSystemChat", 5);
    //var totalSystemChat = countSystemChat();
    //console.info("记录总数：" + totalSystemChat);

    //加载数据
    listSystemChat(currentPgaeSystemChat, pageSizeSystemChat);

    //分页显示
    paginationListSystemChatDiv.pagination({
        pageSize: pageSizeSystemChat,
        //total: totalSystemChat,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemChat(pageNumber, pageSize);
        }
    });

});


/*
 * 列表显示当前所有对象
 * */
function listSystemLog(pageNumber, pageSize) {
    console.info("列表显示对象：");
    ajaxRun("home/listSystemLog" + getParams(pageNumber, pageSize), 0, "listSystemLogDiv");
}

function listSystemChat(pageNumber, pageSize) {
    console.info("list systemChat ...");
    ajaxRun("home/listSystemChat" + getParams(pageNumber, pageSize), 0, "listSystemChatDiv");
}

