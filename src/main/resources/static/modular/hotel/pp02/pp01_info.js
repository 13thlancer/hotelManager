/**
 * 代码主档详情对话框（可用于添加和修改对话框）
 */
var pp01InfoDlg = {
	pp01InfoData : {},
	validateFields : {
		hotelGroupId : {
			validators : {
				notEmpty : {
					message : '集团编号不能为空'
				}
			}
		},
		hotelId : {
			validators : {
				notEmpty : {
					message : '酒店编号不能为空'
				}
			}
		},
		configCode : {
			validators : {
				notEmpty : {
					message : '配置项目代码不能为空'
				}
			}
		},
		configName : {
			validators : {
				notEmpty : {
					message : '配置项目名称不能为空'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
pp01InfoDlg.clearData = function() {
	this.pp01InfoData = {};
};

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
pp01InfoDlg.set = function(key, val) {
	this.pp01InfoData[key] = (typeof value == "undefined") ? $("#" + key).val()
			: val;
	return this;
};

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
pp01InfoDlg.get = function(key) {
	return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
pp01InfoDlg.close = function() {
	parent.layer.close(window.parent.pp01.layerIndex);
};

/**
 * 收集数据
 */
pp01InfoDlg.collectData = function() {
	this.set('tbpp01Id').set('hotelGroupId').set('hotelId').set('configName')
			.set('configCode').set('configValue').set('notice').set('zhName').set('enName');
};

/**
 * 验证数据是否为空
 */
pp01InfoDlg.validate = function() {
	$('#pp01InfoForm').data("bootstrapValidator").resetForm();
	$('#pp01InfoForm').bootstrapValidator('validate');
	return $("#pp01InfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加代码主档
 */
pp01InfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}

	// 提交信息
	var ajax = new $ax_json(Feng.ctxPath + "/pp02/pp01Add", function(data) {

		if (data.code == 403) {
			Feng.error(data.message + "!");
		} else {
			Feng.success("添加成功!");
			window.parent.pp01.table.refresh();
			pp01InfoDlg.close();
		}
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.pp01InfoData);
	ajax.start();
};

/**
 * 提交修改
 */
pp01InfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}

	// 提交信息
	var ajax = new $ax_json(Feng.ctxPath + "/pp02/pp01Edit", function(data) {
		if (data.code == 403) {
			Feng.error("修改失败! " + data.message + "!");
		} else {
			Feng.success("修改成功!");
		}
		if (window.parent.pp01 != undefined) {
			window.parent.pp01.table.refresh();
			pp01InfoDlg.close();
		}
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.pp01InfoData);
	ajax.start();
};

$(function() {
	Feng.initValidator("pp01InfoForm", pp01InfoDlg.validateFields);
});
