/*
* 通用的数据管理程序
* 首页功能列表
* 标签列表
* */

var operation4CommonDataBDiv;
var dataKeys;
var functionName;

$(function () {
    console.info("通用的数据管理...dataKey_3学生维护...");
    var tabList = JSON.parse($("#tabNameList").text());
    var idList = JSON.parse($("#idList").text());
    dataKeys = JSON.parse($("#dataKeys").text());
    functionName = $("#functionName").text();
    console.info("tabList: " + tabList);
    console.info("idList: " + idList);
    console.info("dataKeys: " + dataKeys);
    console.info("functionName: " + functionName);
    operation4CommonDataBDiv = $("#operation4CommonDataBDiv");
    tabPagesManagerA("operation4CommonDataBDiv", tabList, idList, loadDataItem, countDataItem);
});

function getCurrentKey() {
    var tab = operation4CommonDataBDiv.tabs('getSelected');
    var index = operation4CommonDataBDiv.tabs('getTabIndex', tab);
    var currentKey = dataKeys[index]
    return currentKey;
}

function countDataItem(title) {
    console.info("统计数据..." + title);
    var currentKey = getCurrentKey();
    var total = 0;
    total = ajaxCalculate("operation4CommonDataB/count?dataKey=" + currentKey);
    return total;
}

function loadDataItem(title, page, pageSize) {
    console.info("读入数据..." + title);
    var currentKey = getCurrentKey();
    var aux = ""
    var params = getParams(page, pageSize) + "&title=" + title + "&dataKey=" + currentKey + "&functionName=" + functionName;    //getParams必须是放在最最前面！！
    console.info(params)
    ajaxRun("operation4CommonDataB/list" + params, 0, "list" + title + "Div");
    $.cookie("currentPage" + title, page);
}

//----------------------------------------------------------------------------------------------------------------------

function selectCurrentKey(id) {
    $.cookie("currentKey" + "学生列表", id)
    $("#当前学生").html(id)
}

function import2systemUserGrade(grade) {

}