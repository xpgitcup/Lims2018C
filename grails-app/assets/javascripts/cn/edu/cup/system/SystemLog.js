/**
 * Created by LiXiaoping on 2017/3/28.
 */
/**
 * Created by LiXiaoping on 2017/3/27.
 */

var listSystemLogDiv;
var paginationSystemLogDiv;

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    listSystemLogDiv = $("#listSystemLogDiv");
    paginationSystemLogDiv = $("#paginationSystemLogDiv");

    //获取当前页
    var currentPgaeSystemLog = readCookie("currentPgaeSystemLog", 1);
    var pageSizeSystemLog = readCookie("pageSizeSystemLog", pageSize);
    var totalSystemLog = countSystemLog();
    console.info("记录总数：" + totalSystemLog);

    //加载数据
    listSystemLog(currentPgaeSystemLog, pageSizeSystemLog);

    //分页
    paginationSystemLogDiv.pagination({
        pageSize: pageSizeSystemLog,
        total: totalSystemLog,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemLog(pageNumber, pageSize);
        }
    });
});

/*
 * 新建
 * */
function createSystemLog(id) {
    console.info("创建SystemLog. 上级节点：" + id);
    ajaxRun("operation4SystemLog/createSystemLog", id, "showSystemLogDiv");
}

/*
 * 编辑
 * */
function editSystemLog(id) {
    console.info("编辑SystemLog." + id);
    ajaxRun("operation4SystemLog/editSystemLog", id, "showSystemLogDiv");
}

/*
 * 统计记录总数
 * */
function countSystemLog() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemLog/countSystemLog");
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showSystemLog(id) {
    console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4SystemLog/getSystemLog", id, "showSystemLogDiv");
    }
}

/*
 * 列表显示当前所有对象
 * */
function listSystemLog(pageNumber, pageSize) {
    console.info("列表显示对象：");
    ajaxRun("operation4SystemLog/listSystemLog" + getParams(pageNumber, pageSize), 0, "listSystemLogDiv");
}