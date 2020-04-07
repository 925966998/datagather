$("#userName").validatebox({
    required: true,
    missingMessage: "请输入用户名",
    invalidMessage: "用户名不能为空"
})
$("#password").validatebox({
    required: true,
    missingMessage: "请输入密码",
    invalidMessage: "密码不能为空"
})
//加载时验证
if (!$("#userName").validatebox('isValid')) {
    $("#userName").focus();

} else if (!$("#password").validatebox('isValid')) {
    $("#password").focus();
}

$(document).keypress(function (event) {
    if ((event.keyCode || event.which) == 13) {
        $("#btn").click();
    }
})
//点击提交
$("#btn").click(function () {
    if (!$("#userName").validatebox('isValid')) {
        $("#userName").focus();

    } else if (!$("#password").validatebox('isValid')) {
        $("#password").focus();
    } else {
        $.ajax({
            url: "/ky-datagather/login",
            type: "POST",
            data: {
                userName: $("#userName").val().trim(),
                password: hex_md5($("#password").val().trim()),
                bbh:$("#bbh").val().trim()
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
                    sessionStorage.setItem("userName", $("#userName").val());
                    window.location.href = "/main.html";
                } else {
                    $.messager.alert("登录失败", data.data, 'info');
                }
            },
            error: function (request) {
                if (request.status == 402) {
                    $.messager.confirm('登录失效', '你的证书已过期,请购买正版授权', function (r) {
                        if (r) {
                            parent.location.href = "/login.html";
                        }
                    });
                }
                $.messager.progress('close');
                $.messager.alert("登录失败", data.data, 'info');
            }
        })
    }
});
// 弹出框加载
$("#dbBox").dialog({
    title: "配置数据源",
    width: 500,
    height: 500,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true,
})

// 弹出框加载
$("#bbhBox").dialog({
    title: "配置版本号",
    width: 500,
    height: 200,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true,
})

$("#cancel").click(function () {
    $("#dbBox").dialog({
        closed: true
    });
});

$("#saveBbh").click(function () {
    $("#bbhBox").dialog({
        closed: true
    });
});

$("#db").click(function () {
    $("#dbBox").dialog({
        closed: false
    });
    $.ajax({
        url: "/ky-datagather/properties/read",
        type: "GET",
        success: function (data) {
            $('#dbForm').form('load', {
                dbip: data.data.dbip,
                dbport: data.data.dbport,
                dbname: data.data.dbname,
                name: data.data.name,
                dbpass: data.data.dbpass,
                localname: data.data.localname,
                localpass: data.data.localpass,
                localport: data.data.localport
            });
        },
        error: function (err) {

        }
    })
});

$("#pzbbh").click(function () {
    $("#bbhBox").dialog({
        closed: false
    });
});

$("#test").click(function () {
    var lag = $('#dbForm').form('validate');
    if (lag) {
        $.ajax({
            url: "/ky-datagather/properties/test",
            type: "GET",
            data: $("#dbForm").serialize(),
            success: function (data) {
                if (data.code == 10000) {
                    $.messager.alert("提示", data.data, 'info');
                } else {
                    $.messager.alert("提示", data.data, 'error');
                }
            },
            error: function (err) {
                $.messager.alert("提示", data.data, 'error');
            }
        })
    } else {
        $.messager.alert("警告", "数据不能为空", 'WARN');
    }
});
$("#subdbconfig").click(function () {
    var lag = $('#dbForm').form('validate');
    if (lag) {
        $.ajax({
            url: "/ky-datagather/properties/update",
            type: "GET",
            data: $("#dbForm").serialize(),
            beforeSend: function () {
                $.messager.progress({
                    text: '配置生效中。。。'
                });
            },
            success: function (data) {
                $("#dbBox").dialog({
                    closed: true
                });
                $.messager.progress('close');
                if (data.code == 10000) {
                    $.messager.alert("提示", "配置已生效", 'info');
                    $.ajax({
                        url: "/actuator/refresh",
                        type: "post",
                        beforeSend: function () {
                            $.messager.progress({
                                text: '正在重新装载配置。。。'
                            });
                        },
                        success: function (data) {
                            $.messager.progress('close');
                        },
                        error: function (err) {
                            $.messager.progress('close');
                        }
                    })
                } else {
                    $.messager.alert("配置失败", data.data, 'error');
                }
            },
            error: function (err) {
                $("#dbBox").dialog({
                    closed: true
                });
                $.messager.progress('close');
                $.messager.alert("配置失败", data.data, 'error');
            }
        })
    } else {
        $.messager.alert("警告", "数据不能为空", 'WARN');
    }
});
obj={
    reset: function () {
        $("#bbhForm").form('clear');
    }
}