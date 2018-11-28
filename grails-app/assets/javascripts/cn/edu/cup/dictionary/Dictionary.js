var operation4DataDiv;
var tabList = ["数据字典", "数据模型", "数据项"];
var displayDataKeyTreeDiv;
var paginationDataKeyDiv;

$(function () {
    operation4DataDiv = $("#operation4DataDiv");
    displayDataKeyTreeDiv = $("#displayDataKeyTreeDiv");
    paginationDataKeyDiv = $("#paginationDataKeyDiv");

    console.info("数据字典维护...");
    tabPagesManagerWithPagination("operation4DataDiv", tabList, loadDictionaryData, countDictionaryData);

    var dictionary = readCookie("currentDictionary", 1);
    $("#currentDictionary").html(dictionary);

    var dataKey = readCookie("currentDataKey", 1);
    $("#currentDataKey").html(dataKey);

    var current = readCookie("current", "")
    if (current) {
        switch (current) {
            case "DataDictionary":
                var id = readCookie("currentDictionary", 1);
                maintainDataDictionary(id);
                break;
            case "DataKey":
                var id = readCookie("currentDataKey", 1);
                maintainDataKey(id);
                break;
        }
    } else {
        switchTabs(false)
    }
})

//======================================================================================================================
// tab页面维护

function switchTabs(isTree) {
    var lastTimeTab = readCookie("current" + "operation4DataDiv", tabList[0]);
    console.info("上一次的页面：" + lastTimeTab);
    if (isTree) {
        operation4DataDiv.tabs("enableTab", "数据模型维护")
        tabList.forEach(function (value) {
            operation4DataDiv.tabs("disableTab", value)
        });
        operation4DataDiv.tabs("select", "数据模型维护");
        $.cookie("current" + "operation4DataDiv", lastTimeTab, {path: "/"});
        $.cookie("dataKeyView", "editDataKeyDiv");
    } else {
        operation4DataDiv.tabs("disableTab", "数据模型维护")
        tabList.forEach(function (value) {
            operation4DataDiv.tabs("enableTab", value)
        });
        operation4DataDiv.tabs("select", lastTimeTab);
        $.cookie("dataKeyView", "");
    }
}

//======================================================================================================================
// 通用的一些处理函数

/*
* 处理日期+时间的输入
* */
function processDateTime() {
    $("input.datePicker").datepicker({
        showButtonPanel: true,
        dateFormat: "yy-mm-dd",
        defaultDate: 0
    });

    $("input.dateTimePicker").datetimepicker({
        format: 'Y-m-d H:i',
        defaultDate: new Date()
    });

    console.info("处理日期时间......")
}

/*
* 处理文件上传
* */
function updateUploadFileName(id) {
    console.info(id);
    console.info("文件上传...");
    var ainput = "input_" + id;
    var afile = "file_" + id;
    var aainput = document.getElementById(ainput);
    var aafile = document.getElementById(afile);
    var fn = aainput.value
    var k = fn.lastIndexOf('/')
    console.info(aainput);
    console.info(aainput.files.length);
    console.info(aafile);
    aafile.value = aainput.files[0].name;
    console.info(aainput.value);
}

//======================================================================================================================
// 数据模型基本处理函数


//======================================================================================================================
// 数据项处理函数

/*
* 创建数据项
* */
function createDataItem(id) {
    // 输入首先要选择。
    //selectDataKey(id) //不要直接调用，把代码插进来。
    console.info("记录当前模型：" + id);
    console.info("createDataItem: " + id);
    $.cookie("currentDataKey", id)
    $("#currentDataKey").html(id);
    // 然后才能输入
    operation4DataDiv.tabs("select", "数据项");
    ajaxRunEx("operation4Data/createDataItem", id, "list" + "数据项" + "Div", processDateTime);
}


/*
* 维护当前数据模型
* */
function maintainDataKey(id) {
    console.info("进入 数据模型 维护模式...");
    $.cookie("current", "DataKey");
    selectDataKey(id)
    switchTabs(true);
    //设置分页机制
    paginationDataKeyDiv.pagination({
        pagesize: pageSize,
        total: 1,
        pageNumber: 1,
        displayMsg: "",
        layout: ["first", "prev", "next", "last"]
    });
    //显示树形结构
    var getDataUrl = "operation4Data/getTreeDataKey/" + id;
    displayDataKeyTreeDiv.tree({
        url: getDataUrl,
        onSelect: function (node) {
            console.info(node);
            console.info("当前节点：" + node.target.id);
            showDataKey(node.attributes[0], "editDataKeyDiv");
            $("#createDataKey").attr('href', 'javascript: createDataKey(' + node.attributes[0] + ')');
        }
    });
}

/*
* 创建数据模型
* */
function createDataKey(id) {
    var divName = readCookie("dataKeyView", "")
    var dictionary = readCookie("currentDictionary", 0);
    console.info("创建数据模型:" + id + "   " + divName);
    if (divName) {
        console.info("创建数据模型，在标签页中...")
        ajaxRun("operation4Data/createDataKey/?dictionary=" + dictionary, id, divName);
    } else {
        ajaxRun("operation4Data/createDataKey/?dictionary=" + dictionary, id, "list" + "数据模型" + "Div");
    }
}

/*
* 编辑当前数据模型
* */
function editDataKey(id) {
    console.info("编辑数据模型:" + id);
    var divName = readCookie("dataKeyView", "")
    if (divName) {
        ajaxRun("operation4Data/editDataKey?id=" + id, 0, divName);
    } else {
        ajaxRun("operation4Data/editDataKey?id=" + id, 0, "list" + "数据模型" + "Div");
    }
}

/*
* 显示当前数据模型
* */
function showDataKey(id, divName) {
    console.info("显示数据模型:" + id);
    if (divName) {
        $.cookie("dataKeyView", divName);
        ajaxRun("operation4Data/showDataKey", id, divName);
    } else {
        $.cookie("dataKeyView", "");
        ajaxRun("operation4Data/showDataKey", id, "list" + "数据模型" + "Div");
    }
}

/*
* 选择当前数据模型
* */
function selectDataKey(id) {
    $.cookie("currentDataKey", id)
    console.info("记录当前模型：" + id);
    operation4DataDiv.tabs("select", "数据项");
    $("#currentDataKey").html(id);
}

//======================================================================================================================
// 数据字典基本处理函数

/*
* 维护当前数据字典的数据模型
* */
function maintainDataDictionary(id) {
    console.info("进入 数据字典 维护模式...");
    $.cookie("current", "DataDictionary");

    selectCurrentDictionary(id)

    var total = ajaxCalculate("operation4Data/countDataKey?id=" + id);

    switchTabs(true);

    //设置分页机制
    paginationDataKeyDiv.pagination({
        pagesize: pageSize,
        total: total,
        pageNumber: 1,
        displayMsg: "",
        layout: ["first", "prev", "next", "last"]
    });
    //显示树形结构
    var getDataUrl = "operation4Data/getTreeDataDictionary/" + id;
    displayDataKeyTreeDiv.tree({
        url: getDataUrl,
        onSelect: function (node) {
            console.info(node);
            console.info("当前节点：" + node.target.id);
            //showDataKeyInTab(node.attributes[0]);
            showDataKey(node.attributes[0], "editDataKeyDiv");
            $("#createDataKey").attr('href', 'javascript: createDataKey(' + node.attributes[0] + ')');
            //$.cookie("currentSystemMenu", node.target.id);
        }
    });
}

/*
 *创建数据字典
 */
function createDataDictionary() {
    console.info("创建数据字典...")
    ajaxRun("operation4Data/createDataDictionary", 0, "list" + "数据字典" + "Div");
}

/*
* 显示编辑数据字典
* */
function editDataDictionary(id) {
    console.info("编辑数据字典:" + id);
    ajaxRun("operation4Data/editDataDictionary/" + id, 0, "list" + "数据字典" + "Div");
}

/*
* 显示当前数据字典
* */
function showDataDictionary(id) {
    console.info("显示数据字典:" + id);
    ajaxRun("operation4Data/showDataTictionary?id=" + id, 0, "list" + "数据字典" + "Div");
}

/*
* 选择当前数据字典
* */
function selectCurrentDictionary(id) {
    $.cookie("currentDictionary", id)
    console.info("记录当前字典：" + id);
    operation4DataDiv.tabs("select", "数据模型");
    $("#currentDictionary").html(id);
}

//======================================================================================================================
// 通用函数

function turnToDisplay() {
    console.info("返回浏览模式...");
    $.cookie("current", "");
    switchTabs(false);
}

/*
* 统计数据
* */
function countDictionaryData(title) {
    console.info("统计数据:" + title + "...");
    var total = 0
    switch (title) {
        case "数据字典":
            total = ajaxCalculate("operation4Data/countDictionary");
            break;
        case "数据模型":
            var dictionary = readCookie("currentDictionary", 1)
            total = ajaxCalculate("operation4Data/countDataKey?id=" + dictionary);
            break;
        case "数据项":
            var dataKey = readCookie("currentDataKey", 1)
            total = ajaxCalculate("operation4Data/countDataItem?id=" + dataKey);
            break;
    }
    console.info("统计结果：" + total);
    return total;
}

/*
* 调取数据
* */
function loadDictionaryData(title, page, pageSize) {
    console.info("调取数据：" + title + " 页码 " + page + "，页大小" + pageSize);
    var params = getParams(page, pageSize) + "&title=" + title;    //getParams必须是放在最最前面！！
    console.info(params)
    switch (title) {
        case "数据字典":
            ajaxRun("operation4Data/listDataDictionary" + params, 0, "list" + title + "Div");
            break;
        case "数据模型":
            var dictionary = readCookie("currentDictionary", 1)
            ajaxRun("operation4Data/listDataKey" + params, dictionary, "list" + title + "Div");
            break;
        case "数据项":
            var dataKey = readCookie("currentDataKey", 1)
            ajaxRun("operation4Data/listDataItem" + params, dataKey, "list" + title + "Div");
            break;
    }
    $.cookie("currentPage" + title, page);
}

