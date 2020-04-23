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
        singleSelect: true,
        onSortColumn: function (sort, order) {
            mySort('table', sort, order);
        },
        columns: [[
            {
                field: 'productName',
                title: '产品名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'customName',
                title: '客户名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'goodsSpecs',
                title: '规格',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'goodsModel',
                title: '型号',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'goodsUnit',
                title: '单位',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'goodsNum',
                title: '数量',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'unitPrice',
                title: '单价',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'sumPrice',
                title: '金额',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'manager',
                title: '主管',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'accountant',
                title: '会计',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'curator',
                title: '保管员',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'operator',
                title: '经手人',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'sellDate',
                title: '销售日期',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'remarks',
                title: '备注说明',
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
            }
        }
    })

}

$(function () {
    doQuery('/ky-redwood/sale/queryPage');
});
obj = {
    // 查询
    find: function () {
        doQuery('/ky-redwood/sale/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false
        });
        $("#addForm").form('clear');
        $("#productId").combobox({
            url:'/ky-redwood/stock/queryByParams',
            method: 'get',
            valueField:'id',
            textField: 'productName'
        });
    },
    // 编辑
    edit: function () {
        var rows = $("#table").datagrid('getSelections');
        $("#processName").combobox({
            url:'/ky-redwood/ProcessParent/queryByMaterial',
            method: 'get',
            valueField: 'id',
            textField: 'processName'
        });
        if (rows.length>0){
            var id = $("#table").datagrid('getSelected').id;
            $.ajax({
                url: '/ky-redwood/process/getById?id=' + id,
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    if (data) {
                        $('#editOrderForm').form('load', {
                            id: data.id,
                            processParentId:data.processParentId,
                            processName: data.processName,
                            productName: data.productName,
                            amount: data.amount,
                            type:data.type,
                        });
                    }
                    $.ajax({
                        url: '/ky-redwood/process/queryProcess',
                        type: 'get',
                        dataType: 'json',
                        success: function (data) {
                            console.log(data)
                            $("#editOrder").dialog({
                                    closed: false,
                             })

                        },
                    })
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
        }else {
            $.messager.alert('提示', '请选择要修改的记录', 'info');
        }

    },
    // 提交表单
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $("#addForm").form('validate');
                console.log(lag)
                if (lag == true) {
                    $.ajax({
                        url: '/ky-redwood/sale/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
                            console.log(data.code);
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
                } else {
                    return false;
                }
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
                        url: "/ky-redwood/sale/deleteForce?id=" + ids.join(','),
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

    //删除一个
    delOne: function () {
        var id = $("#table").datagrid('getSelected').id;
        $.messager.confirm('提示信息', '是否删除所选择记录', function (flg) {
            if (flg) {
                $.ajax({
                    type: 'get',
                    url: '/ky-redwood/sale/deleteForce?id=' + id,
                    beforeSend: function () {
                        $("#table").datagrid('loading');
                    },
                    success: function (data) {
                        if (data) {
                            $("#table").datagrid("reload");
                            $.messager.show({
                                title: '提示信息',
                                msg: "数据删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "数据删除失败"
                            })
                        }
                    }, error: function (request) {
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
    }
};

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

$("#editOrder").dialog({
    title: "修改数据",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})