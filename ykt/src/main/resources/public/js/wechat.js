//点击提交
$("#btn").click(function () {
    $.ajax({
        url: "/ky-ykt/weChat/weChatLogin",
        type: "POST",
        data: {
            name: $("#name").val().trim(),
            idCardNo: $("#idCardNo").val().trim(),
            bankCardNo: $("#bankCardNo").val().trim()
        },
        beforeSend: function () {
            $.messager.progress({
                text: '登录中。。。'
            });
        },
        success: function (data) {
            $.messager.progress('close');
            if (data.code == 10000) {
                console.log(data.data);
                sessionStorage.setItem("person", JSON.stringify(data.data));
                window.location.href = "/wechatList.html";
            } else {
                $.messager.alert("登录失败", data.data, 'info');
                window.location.href = "/wechatError.html";
            }
        },
        error: function (err) {
            $.messager.progress('close');
            $.messager.alert("登录失败", data.data, 'info');
        }
    })
})
/*

function js_method() {
    $.ajax({
        type: 'get',
        url: '/ky-ykt/weChat/weChatSelect',
        data: $("#wechatForm").serialize()
    })
}
*/
