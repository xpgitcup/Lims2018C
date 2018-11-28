/**
 * Created by LiXiaoping on 2018/8/25.
 */

var pageSize = 10

/*
* 说明：
*     listFunction -- 数据列表函数，用户自定义的
*     countFunction --- 数据统计函数，用户自定义
* */

/*
* 通用的tab页管理函数---包括翻页控制--包括当前记录指示
* */
function tabPagesManagerA(tabsName, tabNameList, idList, listFunction, countFunction) {

    // 初始设置
    var defaultTab = tabNameList[0];
    var currentTab = readCookie("current" + tabsName, defaultTab);
    var tabsDiv = $("#" + tabsName);

    var countFunction = eval(countFunction);
    var listFunction = eval(listFunction);

    //动态创建各个标签页
    console.info(tabNameList);

    /*
    * 抽象出来的设置分页的代码
    * */
    function setupPaginationDiv(paginationDiv, title) {
        console.info("进入嵌套函数..." + title);
        //分页设置
        var total = countFunction(title)
        var currentPage = readCookie("currentPage" + title, 1);
        paginationDiv.pagination({
            pagesize: pageSize,
            total: total,
            pageNumber: currentPage,
            onSelectPage: function (pageNumber, pageSize) {
                var ct = tabsDiv.tabs('getSelected').panel('options').title;    //这一句是关键啊
                console.info("翻页：" + ct);
                listFunction(ct, pageNumber, pageSize)
            }
        })
        console.info("当前页：" + currentPage + ",   总页数：" + total);
        return {total: total, currentPage: currentPage};
    }

    // 创建各个标签页
    for (x in tabNameList) {
        console.info("创建：" + x);
        var title = tabNameList[x];

        tabsDiv.tabs('add', {
            title: title,
            closable: false
        })

        //插入到tab中
        tabsDiv.tabs('select', x)
        var tab = tabsDiv.tabs('getSelected');
        //显示页面
        var listDiv = $('<div>' + title + '</div>');
        listDiv.attr('id', 'list' + title + 'Div');
        listDiv.appendTo(tab)
        //分页Div
        var paginationDiv = $('<div class="easyui-pagination"></div>');
        paginationDiv.attr('id', 'pagination' + title + 'Div');
        paginationDiv.appendTo(tab)
        var __ret = setupPaginationDiv(paginationDiv, title);
        var total = __ret.total;
        var currentPage = __ret.currentPage;
    }

    // 设置标签管理函数
    tabsDiv.tabs({
            onSelect: function (title, index) {
                //记录tabs的缺省页面，所以采用tabsName
                console.info(tabsName + "--选择标签：" + title + "--" + index);
                $.cookie("current" + tabsName, title, {path: '/'});
                //------------------------------------------------------------------------------------------------------
                loadFirstData(title, listFunction);
                setupPaginationDiv(paginationDiv, title);      //切换到某一页的时候，需要更新分页机制。
                //------------------------------------------------------------------------------------------------------
                var id = readCookie("currentKey" + title, "0")
                $("#" + idList[index]).html(id)
            }
        }
    );

    // 打开缺省的标签
    tabsDiv.tabs("select", currentTab);
    loadFirstData(currentTab, listFunction);

}

/*
* 通用的tab页管理函数---包括翻页控制
* */

function tabPagesManagerWithPagination(tabsName, tabNameList, listFunction, countFunction) {

    // 初始设置
    var defaultTab = tabNameList[0];
    var currentTab = readCookie("current" + tabsName, defaultTab);
    var tabsDiv = $("#" + tabsName);

    var countFunction = eval(countFunction);
    var listFunction = eval(listFunction);

    //动态创建各个标签页
    console.info(tabNameList);

    /*
    * 抽象出来的设置分页的代码
    * */
    function setupPaginationDiv(paginationDiv, title) {
        console.info("进入嵌套函数..." + title);
        //分页设置
        var total = countFunction(title)
        var currentPage = readCookie("currentPage" + title, 1);
        paginationDiv.pagination({
            pagesize: pageSize,
            total: total,
            pageNumber: currentPage,
            onSelectPage: function (pageNumber, pageSize) {
                var ct = tabsDiv.tabs('getSelected').panel('options').title;    //这一句是关键啊
                console.info("翻页：" + ct);
                listFunction(ct, pageNumber, pageSize)
            }
        })
        console.info("当前页：" + currentPage + ",   总页数：" + total);
        return {total: total, currentPage: currentPage};
    }

    for (x in tabNameList) {
        console.info("创建：" + x);
        var title = tabNameList[x];

        tabsDiv.tabs('add', {
            title: title,
            closable: false
        })

        //插入到tab中
        tabsDiv.tabs('select', x)
        var tab = tabsDiv.tabs('getSelected');
        //显示页面
        var listDiv = $('<div>' + title + '</div>');
        listDiv.attr('id', 'list' + title + 'Div');
        listDiv.appendTo(tab)
        //分页Div
        var paginationDiv = $('<div class="easyui-pagination"></div>');
        paginationDiv.attr('id', 'pagination' + title + 'Div');
        paginationDiv.appendTo(tab)
        var __ret = setupPaginationDiv(paginationDiv, title);
        var total = __ret.total;
        var currentPage = __ret.currentPage;
    }

    // 设置标签管理函数
    tabsDiv.tabs({
            onSelect: function (title, index) {
                //记录tabs的缺省页面，所以采用tabsName
                console.info(tabsName + "--选择标签：" + title + "--" + index);
                $.cookie("current" + tabsName, title, {path: '/'});
                //------------------------------------------------------------------------------------------------------
                loadFirstData(title, listFunction);
                setupPaginationDiv(paginationDiv, title);      //切换到某一页的时候，需要更新分页机制。
            }
        }
    );

    // 打开缺省的标签
    tabsDiv.tabs("select", currentTab);
    loadFirstData(currentTab, listFunction);

}

/*
* 通用的tab页管理函数---不包括翻页控制
* */

function tabPagesManager(tabsName, tabNameList, listFunction) {
    // 初始设置
    var defaultTab = tabNameList[0];
    var currentTab = readCookie("current" + tabsName, defaultTab);
    var tabsDiv = $("#" + tabsName);

    // 设置标签管理函数
    tabsDiv.tabs({
            onSelect: function (title, index) {
                //记录tabs的缺省页面，所以采用tabsName
                console.info(tabsName + "--选择标签：" + title + "--" + index);
                $.cookie("current" + tabsName, title, {path: '/'});
                //------------------------------------------------------------------------------------------------------
                loadFirstData(title, listFunction);
            }
        }
    );

    // 打开缺省的标签
    tabsDiv.tabs("select", currentTab);
    loadFirstData(currentTab, listFunction);
}

function loadFirstData(title, listFunction) {
    console.info("第一次加载tab...");
    var page = readCookie("currentPage" + title, 1);
    console.info("当前：" + title + "   -->页码：" + page);
    var listFunction = eval(listFunction);
    listFunction(title, page, pageSize);
}

/*
* 加载页面的数据
* */
function loadTabPageDefaultData(title, listFunction, countFunction) {
    console.info("加载缺省页面数据...");

    var countFunction = eval(countFunction);
    var listFunction = eval(listFunction);

    //首先获取缺省的页面，获取页大小，获取总数
    var currentPage = readCookie("currentPage" + title, 1);
    var cpageSize = readCookie("pageSize" + title, pageSize);
    var totalCount = countFunction(title);
    console.info("当前页：" + title + ":" + currentPage + "总数：" + totalCount);
    listFunction(title, currentPage, cpageSize)
}


