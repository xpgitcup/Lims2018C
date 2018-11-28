var operation4UserDefinedFunctionDiv;
var tabList4UserDef = ["用户自定义功能", "用户类库", "用户类", "类方法"];
var idList = ["currentUserDefinedFunction", "currentLibrary", "currentClass", "currentMethod"];
//var displayDataKeyTreeDiv;
//var paginationDataKeyDiv;

$(function () {
    operation4UserDefinedFunctionDiv = $("#operation4UserDefinedFunctionDiv");

    console.info("用户自定义功能维护...");
    //tabPagesManagerWithPagination("operation4UserDefinedFunctionDiv", tabList4UserDef, loadUserDefinedFunction, countUserDefinedFunction);
    tabPagesManagerA("operation4UserDefinedFunctionDiv", tabList4UserDef, idList, loadUserDefinedFunction, countUserDefinedFunction);
})

//======================================================================================================================
function selectAndTurnToNext(id) {
    var currentTab = operation4UserDefinedFunctionDiv.tabs('getSelected').panel('options').title;    //这一句是关键啊
    console.info("当前标签：" + currentTab);
    $.cookie("currentKey" + currentTab, id);
    switch (currentTab) {
        case "用户自定义功能":
            operation4UserDefinedFunctionDiv.tabs("select", "用户类库")
            $("#currentUserDefinedFunction").html(id);
            break;
        case "用户类库":
            operation4UserDefinedFunctionDiv.tabs("select", "用户类")
            $("#currentLibrary").html(id);
            break;
        case "用户类":
            operation4UserDefinedFunctionDiv.tabs("select", "类方法")
            break;
        case "类方法":
            //operation4UserDefinedFunctionDiv.tabs("select", "用户类库")
            break;
    }
}

//======================================================================================================================
function createUserClassLibrary() {
    console.info("创建用户类库");
    var id = readCookie("currentKey" + "用户自定义功能", 1)
    ajaxRun("operation4UserDefinedFunction/createUserClassLibrary", id, "list" + "用户类库" + "Div");
}

function createUserDefinedFunction() {
    console.info("创建新的功能");
    ajaxRun("operation4UserDefinedFunction/createUserDefinedFunction", 0, "list" + "用户自定义功能" + "Div");
}

function countUserDefinedFunction(title) {
    console.info("统计数据..." + title);
    var currentKey = 0
    var total = 0;
    switch (title) {
        case "用户自定义功能":
            total = ajaxCalculate("operation4UserDefinedFunction/countUserDefinedFunction");
            break
        case "用户类库":
            currentKey = readCookie("currentKey" + "用户自定义功能", "0");
            aux = "?userDefinedFunction=" + currentKey;
            total = ajaxCalculate("operation4UserDefinedFunction/countUserClassLibrary" + aux);
            break
        case  "用户类":
            total = ajaxCalculate("operation4UserDefinedFunction/countUserClass");
            break
        case  "类方法":
            break
    }
    return total;
}

function loadUserDefinedFunction(title, page, pageSize) {
    console.info("读入数据..." + title);
    var currentKey = 0
    var aux = ""
    var params = getParams(page, pageSize) + "&title=" + title;    //getParams必须是放在最最前面！！
    console.info(params)
    switch (title) {
        case "用户自定义功能":
            ajaxRun("operation4UserDefinedFunction/listUserDefinedFunction" + params, 0, "list" + title + "Div");
            break
        case "用户类库":
            currentKey = readCookie("currentKey" + "用户自定义功能", "0");
            aux = "&userDefinedFunction=" + currentKey;
            ajaxRun("operation4UserDefinedFunction/listUserClassLibrary" + params + aux, 0, "list" + title + "Div");
            break
        case  "用户类":
            ajaxRun("operation4UserDefinedFunction/listUserClass" + params, 0, "list" + title + "Div");
            break
        case  "类方法":
            break
    }
    $.cookie("currentPage" + title, page);
}