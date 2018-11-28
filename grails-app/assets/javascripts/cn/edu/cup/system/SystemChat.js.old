/**
 * Created by LiXiaoping on 2017/3/27.
 */

var operation4SystemChatDiv

var listSystemChatDiv;
var paginationSystemChatDiv;

var listSystemChatISayDiv
var paginationListSystemChatISayDiv

var listSystemChatListeningDiv
var paginationListSystemChatListeningDiv

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前tabs的变量
    operation4SystemChatDiv = $("#operation4SystemChatDiv");

    //读取上次所停留的页面
    var currentTabOperation4SystemChatDiv = readCookie("currentTabOperation4SystemChatDiv", "我在听");
    //页面跳转
    operation4SystemChatDiv.tabs("select", currentTabOperation4SystemChatDiv);

    operation4SystemChatDiv.tabs({
        onSelect: function (title) {
            if (title != "对话") {
                $.cookie("currentTabOperation4SystemChatDiv", title);
            }
        }
    })

    //------------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listSystemChatListeningDiv = $("#listSystemChatListeningDiv");
    paginationListSystemChatListeningDiv = $("#paginationListSystemChatListeningDiv");

    //获取当前页
    var currentPgaeSystemChatListening = readCookie("currentPgaeSystemChatListening", 1);
    var pageSizeSystemChatListening = readCookie("pageSizeSystemChatListening", pageSize);
    var totalSystemChatListening = countSystemChatListening();
    console.info("记录总数Listening：" + totalSystemChatListening);

    //加载数据
    listSystemChatListening(currentPgaeSystemChatListening, pageSizeSystemChatListening);

    //分页
    paginationListSystemChatListeningDiv.pagination({
        pageSize: pageSizeSystemChatListening,
        total: totalSystemChatListening,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemChatListening(pageNumber, pageSize);
            $.cookie("currentPgaeSystemChatListening", pageNumber);
        }
    });
    paginationListSystemChatListeningDiv.pagination("select", currentPgaeSystemChatListening);

    //------------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listSystemChatISayDiv = $("#listSystemChatISayDiv");
    paginationListSystemChatISayDiv = $("#paginationListSystemChatISayDiv");

    //获取当前页
    var currentPgaeSystemChatISay = readCookie("currentPgaeSystemChatISay", 1);
    var pageSizeSystemChatISay = readCookie("pageSizeSystemChatISay", pageSize);
    var totalSystemChatISay = countSystemChatISay();
    console.info("记录总数ISay：" + totalSystemChatISay);

    //加载数据
    listSystemChatISay(currentPgaeSystemChatISay, pageSizeSystemChatISay);

    //分页
    paginationListSystemChatISayDiv.pagination({
        pageSize: pageSizeSystemChatISay,
        total: totalSystemChatISay,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemChatISay(pageNumber, pageSize);
            $.cookie("currentPgaeSystemChatISay", pageNumber);
        }
    });

    paginationListSystemChatISayDiv.pagination("select", currentPgaeSystemChatISay);

    //------------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listSystemChatDiv = $("#listSystemChatDiv");
    paginationSystemChatDiv = $("#paginationSystemChatDiv");

    //获取当前页
    var currentPgaeSystemChat = readCookie("currentPgaeSystemChat", 1);
    var pageSizeSystemChat = readCookie("pageSizeSystemChat", pageSize);
    var totalSystemChat = countSystemChat();
    console.info("记录总数：" + totalSystemChat);

    //加载数据
    listSystemChat(currentPgaeSystemChat, pageSizeSystemChat);

    //分页
    paginationSystemChatDiv.pagination({
        pageSize: pageSizeSystemChat,
        total: totalSystemChat,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemChat(pageNumber, pageSize);
            $.cookie("currentPgaeSystemChat", pageNumber);
        }
    });

    paginationSystemChatDiv.pagination("select", currentPgaeSystemChat);

});

/*
 * 统计记录总数
 * */
function countSystemChatListening() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemChat/countSystemChatListening");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listSystemChatListening(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4SystemChat/listSystemChatListening" + getParams(pageNumber, pageSize), 0, "listSystemChatListeningDiv");
}

//----------------------------------------------------------------------------------------------------------------------
/*
 * 统计记录总数
 * */
function countSystemChatISay() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemChat/countSystemChatISay");
    //console.info("统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listSystemChatISay(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4SystemChat/listSystemChatISay" + getParams(pageNumber, pageSize), 0, "listSystemChatISayDiv");
}

//----------------------------------------------------------------------------------------------------------------------
/*
 * 新建
 * */
function createSystemChat(id) {
    operation4SystemChatDiv.tabs("select", "对话");
    ajaxRun("operation4SystemChat/createSystemChat", id, "showSystemChatDiv");
    /**
    if (id>0) {
        operation4SystemChatDiv.tabs("select", "我在听");
    } else {
        operation4SystemChatDiv.tabs("select", "我的发言");
    }
     **/
}

/*
 * 编辑
 * */
function editSystemChat(id) {
    //console.info("编辑SystemChat." + id);
    ajaxRun("operation4SystemChat/editSystemChat", id, "showSystemChatDiv");
}

/*
 * 统计记录总数
 * */
function countSystemChat() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemChat/countSystemChat");
    console.info("统计结果：" + total);
    return total;
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

/*
* 列表显示当前所有对象
* */
function listSystemChat(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4SystemChat/listSystemChat" + getParams(pageNumber, pageSize), 0, "listSystemChatDiv");
}

/*
* 列表显示当前所有对象
* */
function listSystemChatIsay(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4SystemChat/listSystemChatISay" + getParams(pageNumber, pageSize), 0, "listSystemChatDiv");
}

