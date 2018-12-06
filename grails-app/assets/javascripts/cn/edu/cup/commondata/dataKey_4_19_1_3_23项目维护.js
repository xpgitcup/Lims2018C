/*
* 通用的数据管理程序
* 首页功能列表
* 标签列表
* */

var operation4CommonDataBDiv;
var dataKeys;
var functionName;
var idList;

$(function () {
    console.info("通用的数据管理...dataKey_4_19_1_3_23项目维护...");
    var tabList = JSON.parse($("#tabNameList").text());
    idList = JSON.parse($("#idList").text());
    dataKeys = JSON.parse($("#dataKeys").text());
    functionName = $("#functionName").text();
    console.info("tabList: " + tabList);
    console.info("idList: " + idList);
    console.info("dataKeys: " + dataKeys);
    console.info("functionName: " + functionName);
    operation4CommonDataBDiv = $("#operation4CommonDataBDiv");
    tabPagesManagerA("operation4CommonDataBDiv", tabList, idList, loadDataItem, countDataItem);
});

function getCurrentIndex() {
    var tab = operation4CommonDataBDiv.tabs('getSelected');
    var index = operation4CommonDataBDiv.tabs('getTabIndex', tab);
    return index;
}

function getCurrentKey() {
    var index = getCurrentIndex();
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
    var currentKey = getCurrentKey();
    console.info("读入数据..." + title + "  dataKey=" + currentKey);
    var aux = ""
    var params = getParams(page, pageSize) + "&title=" + title + "&dataKey=" + currentKey + "&functionName=" + functionName;    //getParams必须是放在最最前面！！
    console.info(params)
    ajaxRun("operation4CommonDataB/list" + params, 0, "list" + title + "Div");
    $.cookie("currentPage" + title, page);
}

//----------------------------------------------------------------------------------------------------------------------

function selectCurrentKey(id) {
    var index = getCurrentIndex()
    //$.cookie("currentKey" + , id)
    console.info("点击：" + idList[index]);
    $("#" + idList[index]).html(id)
}

function import2systemUserGrade(grade) {

}