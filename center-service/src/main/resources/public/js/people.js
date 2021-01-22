// 加载树
function doQuery(url) {
    $("#table").datagrid({
        method: "get",
        iconCls: "icon-left02",
        url: url,
        queryParams: {flag: 2, projectType: 0},
        fitColumns: true,
        striped: true,
        pagination: true,
        pageSize: 10,
        width: '100%',
        rownumbers: true,
        pageNumber: 1,
        nowrap: true,
        height: 'auto',
        sortName: 'id',
        checkOnSelect: true,
        sortOrder: 'asc',
        toolbar: '#tabelBut',
        columns: [[
            {
                checkbox: true,
                field: 'no',
                width: 100,
                align: 'center'
            },
            {
                field: 'name',
                title: '姓名',
                width: 100,
                align: 'center'
            },
            {
                field: 'phone',
                title: '手机号',
                width: 100,
                align: 'center'
            },
            {
                field: 'idCardNo',
                title: '身份证号',
                width: 100,
                align: 'center'
            },
            {
                field: 'openingBank',
                title: '开户行',
                width: 100,
                align: 'center'
            },
            {
                field: 'bankCardNo',
                title: '银行卡号',
                width: 100,
                align: 'center'
            },
            {
                field: 'countyName',
                title: '所属区县',
                width: 100,
                align: 'center'
            },
            {
                field: 'townName',
                title: '所属乡镇',
                width: 100,
                align: 'center'
            },
            {
                field: 'villageName',
                title: '所属村组',
                width: 100,
                align: 'center'
            },
            {
                field: "opr",
                title: '操作',
                width: 120,
                align: 'center',
                formatter: function (val, row) {
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">编辑</a> ';
                    d = '<a  id="del" data-id="98" class=" operA01"  onclick="obj.del(\'' + row.id + '\')">删除</a> ';
                    return e + d;
                }
            }
        ]],
        onLoadError: function (request) {
            if (request.status == 401) {
                $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                    if (r) {
                        parent.location.href = "/login.html";
                    }
                });
            }
        }
    })
}

$("#areaId").combotree({
    url: '/ky-ykt/areas/queryByParentId',
    method: "get",
    height: 26,
    valueField: 'id',
    textField: 'text',
    onSelect: function () {
        var t = $("#areaId").combotree('tree');
        var n = t.tree('getSelected');
        var text = n.id;
        $("#areaId").combotree('setValue', text);
    },
    //默认树节点是关闭状态
    onLoadSuccess: function () {
        $("#areaId").combotree('tree').tree("collapseAll");
    },
})

$(function () {
    doQuery('/ky-ykt/personDetail/queryPage');
})

obj = {
    // 查询
    find: function () {
        $("#table").datagrid('load', {
            name: $("#roleNameSearch").val()
        })
        // doQuery('/ky-ykt/personDetail/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false
        });
        $("#addForm").form('clear');
    },
    excel: function () {
        window.location.href = '/ky-ykt/personUpload/personUploadExport?flag=2&' + $("#tableFindForm").serialize()
    },
    // 编辑
    edit: function (id) {
        $("#addBox").dialog({
            closed: false,
        });
        $("#addForm").form('clear');
        console.log(id);
        $.ajax({
            url: '/ky-ykt/personDetail/queryById?id=' + id,
            type: 'get',
            dataType: 'json',
            beforeSend: function () {
                $.messager.progress();
            },
            success: function (data) {
                $.messager.progress('close');
                var data = data.data;
                console.log(data);
                if (data) {
                    $("#id").val(data.id);
                    $("#name").val(data.name);
                    $("#phone").val(data.phone);
                    $("#grantAmount").val(data.grantAmount);
                    $("#idCardNo").val(data.idCardNo);
                    $("#bankCardNo").val(data.bankCardNo);
                    $("#openingBank").val(data.openingBank);
                    $("#countCombo").combobox('setValue', data.county);
                    $("#townCombo").combobox('setValue', data.town);
                    $("#villageCombo").combobox('setValue', data.village);
                    $("#address").val(data.address);
                }
            },
            error: function (request) {
                $.messager.progress('close');
                if (request.status == 401) {
                    $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                        if (r) {
                            parent.location.href = "/login.html";
                        }
                    });
                }
            }
        })
    },
    // 提交表单
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                if (lag == true) {
                    $.ajax({
                        url: '/ky-ykt/personDetail/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
                            console.log($("#id").val())
                            $("#table").datagrid('reload');
                            if ($("#id").val()) {
                                $.messager.show({
                                    title: '提示',
                                    msg: '修改成功'
                                })
                            } else {
                                $.messager.show({
                                    title: '提示',
                                    msg: '新增成功'
                                })
                            }
                        },
                        error: function (request) {
                            if (request.status == 401) {
                                $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                                    if (r) {
                                        parent.location.href = "/login.html";
                                    }
                                });
                            }
                        }
                    })
                } else
                    return false;
            },
            success: function () {
                $.messager.progress('close');
                $("#addBox").dialog({
                    closed: true
                })
                $("#table").datagrid('reload')
            }
        });
    },
    // 重置表单
    res: function () {
        $("#addForm").form('clear');
    },
    // 取消表单
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    // 删除多个
    del: function () {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length > 0) {
            $.messager.confirm('确定删除', '你确定要删除你选择的记录吗？', function (flg) {
                if (flg) {
                    var ids = [];
                    for (i = 0; i < rows.length; i++) {
                        ids.push(rows[i].id);
                    }
                    var num = ids.length;
                    $.ajax({
                        type: 'get',
                        url: "/ky-ykt/personDetail/deleteForce?id=" + ids.join(','),
                        beforeSend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            if (data) {
                                $("#table").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个用户被删除'
                                })
                            } else {
                                $.messager.show({
                                    title: '警示信息',
                                    msg: "信息删除失败"
                                })
                            }
                        },
                        error: function (request) {
                            if (request.status == 401) {
                                $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                                    if (r) {
                                        parent.location.href = "/login.html";
                                    }
                                });
                            }
                        }
                    })
                }
            })
        } else {
            $.messager.alert('提示', '请选择要删除的记录', 'info');
        }
    },
}

// 弹出框加载
$("#addBox").dialog({
    title: "新增数据",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})

//加载区县下拉框
$("#countCombo").combobox({
    url: '/ky-ykt/areas/queryByLevel?level=2',
    method: 'get',
    valueField: 'id',
    textField: 'name',
    onChange: function (newValue, oldValue) {
        $("#townCombo").combobox({
            url: '/ky-ykt/areas/queryByLevel?level=3&parentId=' + newValue,
            method: 'get',
            valueField: 'id',
            textField: 'name',
            onChange: function (newValue, oldValue) {
                //加载村组下拉框
                $("#villageCombo").combobox({
                    url: '/ky-ykt/areas/queryByLevel?level=4&parentId=' + newValue,
                    method: 'get',
                    valueField: 'id',
                    textField: 'name'
                });
            }
        });
    }
});


