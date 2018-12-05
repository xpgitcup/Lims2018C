/*
* 通用的数据管理程序
* 首页功能列表
* 标签列表
* */

var operation4CommonDataADiv;

$(function () {
    console.info("通用的数据管理...dataKey_4...")
    var tabList = JSON.parse($("#commonTabList").text())
    var idList = JSON.parse($("#commonIdList").text())
    console.info(tabList)
    console.info(idList)
    operation4CommonDataADiv = $("#operation4CommonDataBDiv");

    tabPagesManagerA("operation4CommonDataADiv", tabList, idList, loadDataItem, countDataItem);

});

function countDataItem(title) {
    console.info("统计数据..." + title);
    var currentKey = 0
    var total = 0;
    total = ajaxCalculate("operation4CommonDataA/count");
    return total;
}

function loadDataItem(title, page, pageSize) {
    console.info("读入数据..." + title);
    var currentKey = 0
    var aux = ""
    var params = getParams(page, pageSize) + "&title=" + title;    //getParams必须是放在最最前面！！
    console.info(params)
    ajaxRun("operation4CommonDataA/list" + params, 0, "list" + title + "Div");
    $.cookie("currentPage" + title, page);
}

//----------------------------------------------------------------------------------------------------------------------

function selectCurrentKey(id) {
    var currentTab = operation4CommonDataADiv.tabs('getSelected').panel('options').title;    //这一句是关键啊
    console.info("当前标签：" + currentTab);
    $.cookie("currentKey" + currentTab, id);
    $("#" + currentTab).html(id)

    switch (currentTab) {
        case "项目":
            operation4CommonDataADiv.tabs("select", "人员")
            break;
        case "人员":
            operation4CommonDataADiv.tabs("select", "进度")
            break;
        case "进度":
            operation4CommonDataADiv.tabs("select", "项目")
            break;
    }
}

