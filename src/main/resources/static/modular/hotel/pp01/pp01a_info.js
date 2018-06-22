/**
 * 配置从档详情对话框（可用于添加和修改对话框）
 */
var pp01aInfoDlg = {
    pp01aInfoData: {},
    validateFields: {
    	seq: {
            validators: {
            	 notEmpty: {
                     message: '顺序号不能为空'
                 },
                 regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                     regexp: /^\d{1,}$/,
                     message: '只能填写数字'
                 }
            }
        },
        desc: {
            validators: {
                notEmpty: {
                    message: '描述不能为空'
                }
            }
        },
        desc1: {
            validators: {
                notEmpty: {
                    message: '描述1不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
pp01aInfoDlg.clearData = function () {
    this.pp01aInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
pp01aInfoDlg.set = function (key, val) {
    this.pp01aInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
pp01aInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
pp01aInfoDlg.close = function () {
    parent.layer.close(window.parent.pp01a.layerIndex);
};



/**
 * 收集数据
 */
pp01aInfoDlg.collectData = function () {
    this.set('tbpp01aId').set('tbpp01Id').set('configCode').set('seq').set('zhName').set('enName').set('desc2').set('desc3').set('desc4')
        .set('desc5').set('desc6').set('desc7').set('desc8').set('desc9');
};


/**
 * 验证数据是否为空
 */
pp01aInfoDlg.validate = function () {
    $('#pp01aInfoForm').data("bootstrapValidator").resetForm();
    $('#pp01aInfoForm').bootstrapValidator('validate');
    return $("#pp01aInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加代码从档
 */
pp01aInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/pp01/pp01aAdd", function (data) {
    			
        if(data.code == 403){
            Feng.error( data.message + "!");
        }else{
            Feng.success("添加成功!");
            window.parent.pp01a.table.refresh();
            pp01aInfoDlg.close();
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pp01aInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
pp01aInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax_json(Feng.ctxPath + "/pp01/pp01aEdit", function (data) {
        if(data.code == 403){
            Feng.error("修改失败! " + data.message + "!");
        }else{
            Feng.success("修改成功!");
        }
        if (window.parent.pp01a != undefined) {
            window.parent.pp01a.table.refresh();
            pp01aInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pp01aInfoData);
    ajax.start();
};


$(function () {
    Feng.initValidator("pp01aInfoForm", pp01aInfoDlg.validateFields);
});

