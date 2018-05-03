//模块弹出框
var redirectDialog;
function redirectAction() {
    var url='url:/module/index';
    redirectDialog = $.dialog({
        animationSpeed: 600,
        title: '模块信息',
        content: url,
        onContentReady: function () {
            initMaterialInput();
        }
    })
};