/**
 * 代码从档管理初始化
 */
var pp01a = {
    id: "pp01aTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

$(function() {
	
    var defaultColunms = pp01a.initColumn();
    var table = new BSTable(pp01a.id, "/pp01/listpp01a", defaultColunms);
    table.setPaginationType("server");
    table.isShowRefresh(false);
    table.isShowColumns(false);
    table.setHeight(330);
    table.setPageSize(5);
    table.setQueryParams(pp01a.queryParams());
    pp01a.table = table.init();
});

pp01a.queryParams = function(){
	var queryData = {};
	queryData['tbpp01Id'] = $("#tbpp01Id").val();
	return queryData;
}

/**
 * 初始化表格的列
 */
pp01a.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'tbpp01aId', visible: false, align: 'center', valign: 'middle'},
        {title: '配置项目代码', field: 'configCode', align: 'center', valign: 'middle', sortable: false},
        {title: '顺序号', field: 'seq', align: 'center', valign: 'middle', sortable: false},
        {title: '中文', field: 'zhName', align: 'center', valign: 'middle', sortable: false},
        {title: '英文', field: 'enName', align: 'center', valign: 'middle', sortable: false}
        ];
};

/**
 * 检查是否选中
 */
pp01a.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        pp01a.seItem = selected[0];
        return true;
    }
};

/**
 * 代码从档录入
 */
pp01a.openAddpp01a = function () {
	
//	layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    var index = layer.open({
        type: 2,
        title: '添加代码从档信息',
        area: ['700px', '360px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pp01/toaddpp01a?configCode='+$("#configCode").val()+"&tbpp01Id="+$("#tbpp01Id").val()
    });
    this.layerIndex = index;
};


/**
 * 修改
 */
pp01a.modify = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '代码从档修改',
			area: ['700px', '360px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/pp01/tomodifypp01a/' + pp01a.seItem.tbpp01aId
		});
		this.layerIndex = index;
	}
};

/**
 * 代码从档删除
 */
pp01a.del = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/pp01/deletepp01a", function (data) {
                Feng.success("删除成功!"); 
                pp01a.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("tbpp01aId", pp01a.seItem.tbpp01aId);
            ajax.start();
        };

        Feng.confirm("是否刪除代码从档 ?", operation);
    }
};


