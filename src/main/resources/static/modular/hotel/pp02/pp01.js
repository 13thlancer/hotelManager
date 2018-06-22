/**
 * 代码主档管理初始化
 */
var pp01 = {
    id: "pp01Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

$(function() {
	
    var defaultColunms = pp01.initColumn();
    var table = new BSTable(pp01.id, "/pp02/list", defaultColunms);
    table.setPaginationType("server");
    table.isShowRefresh(false);
    table.isShowColumns(false);
    table.setQueryParams(pp01.queryParams());
    pp01.table = table.init();
});

pp01.queryParams = function(){
	var queryData = {};
	queryData['configCode'] = $("#configCode").val();
	queryData['configName'] = $("#configName").val();
	return queryData;
}

/**
 * 查询代码主档列表
 */
pp01.search = function () {
    pp01.table.refresh({query: pp01.queryParams()});
};

pp01.reset = function () {
	$("#configCode").val("");
	$("#configName").val("");
};

/**
 * 初始化表格的列
 */
pp01.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'tbpp01Id', visible: false, align: 'center', valign: 'middle'},
        {title: '集团名称', field: 'groupName', align: 'center', valign: 'middle', sortable: false},
        {title: '酒店名称', field: 'hotelName', align: 'center', valign: 'middle', sortable: false},
        {title: '配置项目代码', field: 'configCode', align: 'center', valign: 'middle', sortable: false},
        {title: '配置项目名称', field: 'configName', align: 'center', valign: 'middle', sortable: false},
        {title: '备注', field: 'notice', align: 'center', valign: 'middle', sortable: false}
        ];
};

/**
 * 检查是否选中
 */
pp01.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        pp01.seItem = selected[0];
        return true;
    }
};

/**
 * 代码主档录入
 */
pp01.openAddpp01 = function () {
	
//	layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    var index = layer.open({
        type: 2,
        title: '添加代码主档信息',
        area: ['900px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pp02/toadd'
    });
    this.layerIndex = index;
};


/**
 * 修改
 */
pp01.modify = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '代码主档修改',
			area: ['900px', '420px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/pp02/tomodify/' + pp01.seItem.tbpp01Id
		});
		this.layerIndex = index;
	}
};

/**
 * 代码主档删除
 */
pp01.del = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/pp02/delete", function (data) {
                Feng.success("删除成功!"); 
                pp01.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("tbpp01Id", pp01.seItem.tbpp01Id);
            ajax.start();
        };

        Feng.confirm("是否刪除代码主档 ?", operation);
    }
};



/**
 * 二级菜单 
 */
pp01.openPp01a = function () {
    if (this.check()) {
    	var index = layer.open({
			type: 2,
			title: '二级菜单',
			area: ['900px', '420px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/pp01/pp01a/' + pp01.seItem.tbpp01Id+'?configCode='+pp01.seItem.configCode
		});
		this.layerIndex = index;
    	
    }
   }

