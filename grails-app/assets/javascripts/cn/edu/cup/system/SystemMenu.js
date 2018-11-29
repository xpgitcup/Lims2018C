/**
 * Created by LiXiaoping on 2017/2/26.
 */

var displayTreeSystemMenuDiv;
var paginationSystemMenuDiv;

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    displayTreeSystemMenuDiv = $("#displayTreeSystemMenuDiv");
    paginationSystemMenuDiv = $("#paginationSystemMenuDiv");

    //获取当前页
    var currentPgaeSystemMenu = readCookie("currentPgaeSystemMenu", 1);
    var pageSizeSystemMenu = readCookie("pageSizeSystemMenu", pageSize);
    var totalSystemMenu = countSystemMenu();
    console.info("记录总数：" + totalSystemMenu);

    //加载数据
    displayTreeSystemMenuDiv.tree({
        //url: "/operation4SystemMenu/getTreeSystemMenu" + getParams(currentPgaeSystemMenu, pageSizeSystemMenu),
        //url: "operation4SystemMenu/getTreeSystemMenu" + getParams(currentPgaeSystemMenu, pageSizeSystemMenu),
        url: "operation4SystemMenu/getTreeSystemMenu" + getParams(currentPgaeSystemMenu, pageSizeSystemMenu),
        onSelect: function (node) {
            showSystemMenu(node);
            $("#createSystemMenu").attr('href', 'javascript: createSystemMenu(' + node.attributes[0] + ')');
            console.info(node);
            console.info("当前节点：" + node.target.id);
            $.cookie("currentSystemMenu", node.target.id);
        },
        onLoadSuccess: function () {
            displayTreeSystemMenuDiv.tree("collapseAll");
            var nodeid = $.cookie("currentSystemMenu");
            console.info("当初扩展到" + nodeid);
            if (nodeid) {
                var cNode = $("#" + nodeid);
                displayTreeSystemMenuDiv.tree("expandTo", cNode);
                displayTreeSystemMenuDiv.tree("select", cNode);
            }
        }
    });
    //分页
    paginationSystemMenuDiv.pagination({
        pageSize: pageSizeSystemMenu,
        total: totalSystemMenu,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            displayTreeSystemMenuDiv.tree({
                url: "getTreeSystemMenu" + getParams(pageNumber, pageSize)
            })
        }
    });
});

/*
 * 新建
 * */
function createSystemMenu(id) {
    console.info("创建SystemMenu. 上级节点：" + id);
    ajaxRun("operation4SystemMenu/createSystemMenu", id, "showSystemMenuDiv");
}

/*
 * 编辑
 * */
function editSystemMenu(id) {
    console.info("编辑SystemMenu." + id);
    ajaxRun("operation4SystemMenu/editSystemMenu", id, "showSystemMenuDiv");
}

/*
 * 统计记录总数
 * */
function countSystemMenu() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemMenu/countSystemMenu");
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showSystemMenu(node) {
    console.info("显示当前系统属性" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("operation4SystemMenu/getSystemMenu", id, "showSystemMenuDiv");
    }
}
