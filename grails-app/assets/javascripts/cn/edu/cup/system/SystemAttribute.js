/**
 * Created by LiXiaoping on 2017/2/23.
 */

var displayTreeSystemAttributeDiv;
var paginationSystemAttributeDiv;

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    displayTreeSystemAttributeDiv = $("#displayTreeSystemAttributeDiv");
    paginationSystemAttributeDiv = $("#paginationSystemAttributeDiv");

    //获取当前页
    var currentPgaeSystemAttribute = readCookie("currentPgaeSystemAttribute", 1);
    var pageSizeSystemAttribute = readCookie("pageSizeSystemAttribute", pageSize);
    var totalSystemAttribute = countSystemAttribute();
    console.info("记录总数：" + totalSystemAttribute);

    //加载数据
    displayTreeSystemAttributeDiv.tree({
        url: "operation4SystemAttribute/getTreeSystemAttribute" + getParams(currentPgaeSystemAttribute, pageSizeSystemAttribute),
        onSelect: function (node) {
            showSystemAttribute(node);
            $("#createSystemAttribute").attr('href', 'javascript: createSystemAttribute(' + node.attributes[0] + ')');
            console.info(node);
            console.info("当前节点：" + node.target.id);
            $.cookie("currentSystemAttribute", node.target.id);
        },
        onLoadSuccess: function () {
            displayTreeSystemAttributeDiv.tree("collapseAll");
            var nodeid = $.cookie("currentSystemAttribute");
            console.info("当初扩展到" + nodeid);
            var cNode = $("#" + nodeid);
            displayTreeSystemAttributeDiv.tree("expandTo", cNode);
            displayTreeSystemAttributeDiv.tree("select", cNode);
        }
    });
    //分页
    paginationSystemAttributeDiv.pagination({
        pageSize: pageSizeSystemAttribute,
        total: totalSystemAttribute,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            displayTreeSystemAttributeDiv.tree({
                url: "operation4SystemAttribute/getTreeSystemAttribute" + getParams(pageNumber, pageSize)
            })
        }
    });
});

/*
* 新建
* */
function createSystemAttribute(id) {
    console.info("创建SystemAttribute. 上级节点：" + id);
    ajaxRun("operation4SystemAttribute/createSystemAttribute", id, "showSystemAttributeDiv");
}

/*
* 编辑
* */
function editSystemAttribute(id) {
    console.info("编辑SystemAttribute." + id);
    ajaxRun("operation4SystemAttribute/editSystemAttribute", id, "showSystemAttributeDiv");
}

/*
* 统计记录总数
* */
function countSystemAttribute() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemAttribute/countSystemAttribute");
    console.info("统计结果：" + total);
    return total;
}

/*
* 显示当前属性
* */
function showSystemAttribute(node) {
    console.info("显示当前系统属性" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("operation4SystemAttribute/getSystemAttribute", id, "showSystemAttributeDiv");
    }
}
