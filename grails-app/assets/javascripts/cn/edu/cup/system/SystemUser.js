/**
 * Created by LiXiaoping on 2017/3/27.
 */
var listSystemUserDiv;
var paginationSystemUserDiv;

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    listSystemUserDiv = $("#listSystemUserDiv");
    paginationSystemUserDiv = $("#paginationSystemUserDiv");

    //获取当前页
    var currentPgaeSystemUser = readCookie("currentPgaeSystemUser", 1);
    var pageSizeSystemUser = readCookie("pageSizeSystemUser", pageSize);
    var totalSystemUser = countSystemUser();
    console.info("记录总数：" + totalSystemUser);

    //加载数据
    listSystemUser(currentPgaeSystemUser, pageSizeSystemUser);

    //分页
    paginationSystemUserDiv.pagination({
        pageSize: pageSizeSystemUser,
        total: totalSystemUser,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemUser(pageNumber, pageSize);
        }
    });
});

/*
 * 新建
 * */
function createSystemUser(id) {
    console.info("创建SystemUser. 上级节点：" + id);
    ajaxRun("operation4SystemUser/createSystemUser", id, "showSystemUserDiv");
}

/*
 * 编辑
 * */
function editSystemUser(id) {
    console.info("编辑SystemUser." + id);
    ajaxRun("operation4SystemUser/editSystemUser", id, "showSystemUserDiv");
}

/*
 * 统计记录总数
 * */
function countSystemUser() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemUser/countSystemUser");
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showSystemUser(id) {
    console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4SystemUser/getSystemUser", id, "showSystemUserDiv");
    }
}

/*
* 列表显示当前所有对象
* */
function listSystemUser(pageNumber, pageSize) {
    console.info("列表显示对象：");
    ajaxRun("operation4SystemUser/listSystemUser" + getParams(pageNumber, pageSize), 0, "listSystemUserDiv");
}