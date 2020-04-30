//点击提交
$("#btn").click(function () {
    if (!$("#userName").validatebox('isValid')) {
        $("#userName").focus();

    } else if (!$("#password").validatebox('isValid')) {
        $("#password").focus();
    } else {
        $.ajax({
            url: "/ky-ykt/login",
            type: "POST",
            data: {
                userName: $("#userName").val().trim(),
                password: hex_md5($("#password").val().trim())
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
                    sessionStorage.setItem("user", JSON.stringify(data.data));
                    sessionStorage.setItem("userId", data.data.id);
                    sessionStorage.setItem("userName", $("#userName").val());
                    window.location.href = "/main.html";
                } else {
                    $.messager.alert("登录失败", data.data, 'info');
                }
            },
            error: function (err) {
                $.messager.progress('close');
                $.messager.alert("登录失败", data.data, 'info');
            }
        })
    }
})

function js_method() {
    $.ajax({
        type: 'get',
        url: '/ky-ykt/weChat/weChatSelect',
        data: $("#wechatForm").serialize()
    })

}