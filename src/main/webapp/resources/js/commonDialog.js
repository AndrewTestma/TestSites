//模块弹出框
var redirectDialog;
function redirectAction() {
    redirectDialog = $.dialog({
        animationSpeed: 600,
        width: 900,
        title: '模块信息',
        content: 'url:/module/index',
        onContentReady: function () {
            initMaterialInput();
        }
    })
};