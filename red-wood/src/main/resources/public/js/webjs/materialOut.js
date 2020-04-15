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
        /*sortName: 'id',*/
        checkOnSelect: true,
        /*sortOrder: 'asc',*/
        toolbar: '#tabelBut',
        singleSelect: true,
        onSortColumn: function (sort, order) {
            mySort('table', sort, order);
        },
        columns: [[
            {
                field: 'materialName',
                title: '材料名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'amount',
                title: '数量',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'status',
                title: '是否补料',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (status) {
                    if (status==1){
                        return '<div>补料</div>';
                    }else {
                        return '<div>正常</div>';
                    }
                }
            },
            {
                field: 'processStatus',
                title: '补料状态',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (processStatus) {
                    switch (processStatus) {
                        case 0:  return '<div>未加工</div>';
                        case 1:  return '<div>开料</div>';
                        case 2:  return '<div>木工定型</div>';
                        case 3:  return '<div>机雕</div>';
                        case 4:  return '<div>手雕</div>';
                        case 5:  return '<div>木工组装</div>';
                        case 6:  return '<div>刮磨</div>';
                        case 7:  return '<div>组装铜件</div>';
                        case 8:  return '<div>上蜡</div>';
                        default:
                            return '<div>加工完毕</div>';
                    }
                }
            },
            {
                field: 'processName',
                title: '单据名称',
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
$('#amount').bind('blur',function(){
    alert('测试一下');
    console.log("131313")
});
// $("#amount").blur(function() {
//     var amount = $("#amount").val();
//     var materialName = $("#materialName").val();
//     $.ajax({
//         url: '/ky-redwood/material/queryById?id=' + materialName,
//         type: 'get',
//         dataType: 'json',
//         success: function (data) {
//             var materialAmount=data.data.amount;
//             if (amount>materialAmount){
//                 $("#amountsp").html("用户名格式不正确");
//                 return;
//             }
//         },
//     })
// });

$(function () {
    doQuery('/ky-redwood/materialOut/queryPage');
});
obj = {
    // 查询
    find: function () {
        doQuery('/ky-redwood/materialOut/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false

        });
        $("#addForm").form('clear');
        $("#materialName").combobox({
            url:'/ky-redwood/material/queryByParams',
            method: 'get',
            valueField: 'id',
            textField: 'materialName'
        });
    },
    // 编辑

    edit: function () {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length>0){
            $("#addBox").dialog({
                closed: false,
            })
            var id = $("#table").datagrid('getSelected').id;
            $.ajax({
                url: '/ky-redwood/materialOut/queryById?id=' + id,
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    var data = data.data;
                    if (data) {
                        $('#addForm').form('load', {
                            id: data.id,
                            materialName: data.materialName,
                            amount: data.amount,
                            status: data.status,
                            processStatus: data.processStatus,
                            processName: data.processName,
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
                        url: '/ky-redwood/materialOut/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
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
    sub: function(){
        var rows = $("#table").datagrid("getSelections");
        if (rows.length>0){
            $("#editBox").dialog({
                closed: false,
            })
            var id = $("#table").datagrid('getSelected').id;
            $.ajax({
                url: '/ky-redwood/materialOut/queryById?id=' + id,
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    var data = data.data;
                    if (data) {
                        $('#editForm').form('load', {
                            id: data.id,
                            materialId: data.materialId,
                            materialName: data.materialName,
                            amount: data.amount,
                            processName: data.processName,
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
        }else {
            $.messager.alert('提示', '请选择要补料的记录', 'info');
        }

    },

    editsum: function () {
        $('#editForm').form('submit', {
            onSubmit: function () {
                var lag = $("#editForm").form('validate');
                console.log(lag)
                if (lag == true) {
                    $.ajax({
                        url: '/ky-redwood/materialOut/subMaterial',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("editForm"),
                        success: function (data) {
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
                $("#editBox").dialog({
                    closed: true
                })
                $("#table").datagrid('reload')
            }
        });
    },
    editres: function () {
        $("#editForm").form('clear');
    },
    // 取消表单
    editcan: function () {
        $("#editBox").dialog({
            closed: true
        })
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
                        url: "/ky-redwood/materialOut/deleteForce?id=" + ids.join(','),
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
                    url: '/ky-redwood/materialOut/deleteForce?id=' + id,
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
}

// 弹出框加载
$("#addBox").dialog({
    title: "更新数据",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})

// 弹出框加载
$("#editBox").dialog({
    title: "补料数据",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})

