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
       /* sortName: 'id',
        sortOrder: 'asc',*/
        checkOnSelect: true,
        singleSelect: true,
        toolbar: '#tabelBut',
        remoteSort: false,
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
                field: 'flowStatus',
                title: '加工流程',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (flowStatus) {
                    switch (flowStatus) {
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
            /*
           {
               field: 'type',
               title: '是否成品',
               width: 100,
               align: 'center',
               sortable: true
           },
             */
           {
               field: 'amount',
               title: '数量',
               width: 100,
               align: 'center',
               sortable: true
           },
           /*
                         field: 'status',
               title: '操作',
               width: 100,
               align: 'center',
               sortable: true,
               formatter: function (val, row) {
                   if (val == '0') {
                       return '<div style="color: green">修改</div>';
                   } else {
                       return '<div style="color: red">删除</div>';
                   }

               }
           }
           */
            {
                field: "opr",
                title: '操作',
                width: 120,
                align: 'center',
                formatter: function (val, row) {
                    s = '<a  id="add" data-id="98" class=" operA"  onclick="obj.show(\'' + row.id + '\')">查看</a> ';
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">编辑</a> ';
                    c = '<a  id="sub" data-id="98" class=" operA"  onclick="obj.submitAudit(\'' + row.id + '\')">加工</a> ';
                    d = '<a  id="del" data-id="98" class=" operA01"  onclick="obj.delOne(\'' + row.id + '\')">删除</a> ';
                    return s + e + c + d;
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

// 查看单据流程
function doShow(url) {
    $("#tableShow").datagrid({
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
        sortName: 'id',
        checkOnSelect: true,
        sortOrder: 'asc',
        toolbar: '#tabelBut',
        columns: [[
            {
                field: 'productName',
                title: '产品名称',
                width: 100,
                align: 'center',
            },
            {
                field: 'flowStatus',
                title: '加工流程',
                width: 100,
                align: 'center',
                formatter: function (flowStatus) {
                    switch (flowStatus) {
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
            /*
           {
               field: 'type',
               title: '是否成品',
               width: 100,
               align: 'center',
               sortable: true
           },
             */
            {
                field: 'amount',
                title: '数量',
                width: 100,
                align: 'center',
            },
            /*
            {
                field: "opr",
                title: '操作',
                width: 120,
                align: 'center',
                formatter: function (val, row) {
                    s = '<a  id="add" data-id="98" class=" operA"  onclick="obj.show(\'' + row.id + '\')">查看</a> ';
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">编辑</a> ';
                    c = '<a  id="sub" data-id="98" class=" operA"  onclick="obj.submitAudit(\'' + row.id + '\')">继续加工</a> ';
                    d = '<a  id="del" data-id="98" class=" operA01"  onclick="obj.delOne(\'' + row.id + '\')">删除</a> ';
                    return s + e + c + d;
                }
            }
            */
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

function doContinue(id){
    $("#continueFlowStatusCombo").combobox({
        url: '/ky-redwood/processFlow/querySmallId?id=' + id,
        type: 'get',
        dataType: 'json',
        success: function (data) {

        }
    })
}

$(function () {
    doQuery('/ky-redwood/process/queryPage');
});
obj = {
    // 查询
    find: function () {
        doQuery('/ky-redwood/process/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false

        });
        $("#addForm").form('clear');
    },
    // 编辑
    edit: function (id) {
        $("#addBox").dialog({
            closed: false,
        })
        $.ajax({
            url: '/ky-redwood/process/queryById?id=' + id,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $.messager.progress('close');
                var data = data.data;
                console.log(data);
                $("#id").val(data.id);
                $("#processParentId").val(data.processParentId);
                $("#productName").val(data.productName);
                var type = data.type;
                $("input[name='type']").each(function(){
                    if(type==$(this).val()){
                        $(this).prop("checked",true);
                    }
                });
                $("#amount").numberbox('setValue',data.amount);
                $("#fee").numberbox('setValue',data.fee);
                $("#add_fee").numberbox('setValue',data.add_fee);
                var flowStatus = data.flowStatus;
                $("#flowStatusCombo").combobox("setValue",flowStatus);
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
    },
    // 查看
    show: function (id) {
        $("#addBox").dialog({
            closed: false,
        })
        $.ajax({
            url: '/ky-redwood/process/queryById?id=' + id,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                var data = data.data;
                if (data) {
                    $('#addForm').form('load', {
                        id: data.id,
                        userName: data.userName,
                        phone: data.phone,
                        fullName: data.fullName,
                        idCardNo: data.idCardNo
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
    },
    // 提交表单
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $("#addForm").form('validate');
                console.log(lag)
                if (lag == true) {
                    $.ajax({
                        url: '/ky-redwood/process/saveOrUpdate',
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
    repass: function (id) {
        $.messager.confirm('提示信息', '是否重置密码', function (flag) {
            if (flag) {
                $.ajax({
                    type: 'POST',
                    url: "/ky-redwood/reset/" + id,
                    beforeSend: function () {
                        $("#table").datagrid('loading');
                    },
                    success: function (data) {
                        $("#table").datagrid('reload');
                        if (data.code == 10000) {
                            $.messager.show({
                                title: '提示',
                                msg: '密码重置成功123456'
                            })
                        } else {
                            $.messager.show({
                                title: '提示',
                                msg: '密码重置失败'
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
                        url: "/ky-redwood/process/deleteForce?id=" + ids.join(','),
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
    delOne: function (id) {
        $.messager.confirm('提示信息', '是否删除所选择记录', function (flg) {
            if (flg) {
                $.ajax({
                    type: 'get',
                    url: '/ky-redwood/process/deleteForce?id=' + id,
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
    },

    //加工
    submitAudit: function (id) {
        $("#continueProcessingBox").dialog({
            closed: false,
        });
        $.ajax({
            url: '/ky-redwood/process/queryById?id=' + id,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                $.messager.progress('close');
                var data = data.data;
                console.log(data);
                $("#continueProductName").val(data.productName);
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
}

// 弹出框加载
$("#addBox").dialog({
    title: "编辑数据",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})

$("#continueProcessingBox").dialog({
    title: "加工",
    width: 500,
    height: 200,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})

/*
//加载加工流程下拉框
$("#flowStatusCombo").combobox({
    url: '/ky-redwood/processFlow/queryByParams',
    method: 'get',
    valueField: 'id',
    textField: 'processFlowName',
})
*/



