// 加载树
function doQuery(url) {
    $("#table").datagrid({
        method: "get",
        iconCls: "icon-left02",
        url: url,
        fitColumns: true,
        striped: true,
        pagination: true,
        pageSize: 10,
        width: '100%',
        rownumbers: true,
        pageList: [10, 20],
        pageNumber: 1,
        nowrap: true,
        height: 'auto',
        checkOnSelect: true,
        toolbar: '#tabelBut',
        remoteSort: false,
        onSortColumn: function (sort, order) {
            mySort('table', sort, order);
        },
        columns: [[
            {
                field: 'productName',
                title: '商品名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'productNum',
                title: '商品数量',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'productPrice',
                title: '商品价格',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'storageTime',
                title: '商品入库时间',
                width: 100,
                align: 'center',
                sortable: true
            },
        ]],
        onLoadError: function (request) {
            if (request.status == 401) {
                $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                    if (r) {
                        parent.location.href = "/login.html";
                    }
                });
                s
            }
        }
    })
}

$(function () {
    doQuery('/ky-redwood/stock/queryPage');
});
obj = {
    // 查询
    find: function () {
        doQuery('/ky-redwood/stock/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false
        });
        $("#addForm").form('clear');
        $("#productId").combobox({
            url:'/ky-redwood/material/queryByParams',
            method: 'get',
            valueField: 'id',
            textField: 'materialName'
        });
    },
    // 编辑
    edit: function () {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length > 0) {
            id = $("#table").datagrid("getSelected").id;
            $("#addBox").dialog({
                closed: false,
            })
            $.ajax({
                url: '/ky-redwood/stock/queryById?id=' + id,
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    $.messager.progress('close');
                    var data = data.data;
                    if (data) {
                        $('#addForm').form('load', {
                            id: data.id,
                            productId:data.productId,
                            productName: data.productName,
                            productNum:data.productNum,
                            productPrice:data.productPrice,
                            storageTime:data.storageTime,
                        });
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
        } else {
            $.messager.alert('提示', '请选择要编辑的单据', 'info');
        }
    },
    // 提交表单
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $("#addForm").form('validate');
                console.log(lag)
                console.log(form2Json("addForm"));
                if (lag == true) {
                    $.ajax({
                        url: '/ky-redwood/stock/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
                            if ($("#id").val()) {
                                $.messager.show({
                                    title: '提示',
                                    msg: '修改成功'
                                })
                                $("#table").datagrid('reload');
                                $("#tableShow").datagrid('reload');
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
                } else {
                    return false;
                }
            },
            success: function () {
                $.messager.progress('close');
                $("#addBox").dialog({
                    closed: true
                })
                $("#table").datagrid('reload');
                $("#tableShow").datagrid('reload');
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
        console.log(rows);
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
                        url: "/ky-redwood/stock/deleteForce?id=" + ids.join(','),
                        beforeSend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            console.log(data);
                            if (data) {
                                $("#table").datagrid('reload');
                                $("#tableShow").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个用户被删除'
                                })
                                $("#table").datagrid('reload');
                                $("#tableShow").datagrid('reload');
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

