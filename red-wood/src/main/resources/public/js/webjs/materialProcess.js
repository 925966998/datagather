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
                        case 1:  return '<div>开料</div>';
                        case 2:  return '<div>木工定型</div>';
                        case 3:  return '<div>机雕</div>';
                        case 4:  return '<div>手雕</div>';
                        case 5:  return '<div>木工组装</div>';
                        case 6:  return '<div>刮磨</div>';
                        case 7:  return '<div>组装铜件</div>';
                        case 8:  return '<div>上蜡</div>';
                        case 9:  return '<div>加工完毕</div>';
                        default:
                            return '<div>未加工</div>';
                    }
                }
            },

           {
               field: 'type',
               title: '是否成品',
               width: 100,
               align: 'center',
               sortable: true,
               formatter: function (type) {
                   if(type == 1){
                       return '<div>成品</div>';
                   }else{
                       return '<div>半成品</div>';
                   }
               }
           },

           {
               field: 'amount',
               title: '数量',
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
                });s
            }
        }
    })
}

$(function () {
    doQuery('/ky-redwood/process/queryPage');
    $("#tabelShowBox").hide();
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

    //加价
    submitPrice:function(){
        $("#supplementBox").dialog({
            closed: false
        });
        $("#supplementForm").form('clear');
    },
    // 编辑
    edit: function () {
        var rows = $("#table").datagrid("getSelections");
        if(rows.length>0){
            id = $("#table").datagrid("getSelected").id;
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
        }else{
            $.messager.alert('提示', '请选择要编辑的单据', 'info');
        }
    },
    // 查看
    show: function () {
        var rows = $("#table").datagrid("getSelections");
        if(rows.length>0){
            id = $("#table").datagrid("getSelected").id;
            console.log(id);
             //doQueryShow('/ky-redwood/process/queryById?id='+id);
            $("#tableShow").datagrid({
                method:"get",
                url:'/ky-redwood/process/querySelectId?id='+id,
                'fitColumns': true,//自动适应列大小
                'autoRowHeight': true,//自动调整行的高度
                'pagination': false,//设置分页
                /*
                'pageSize': 10,//设置显示页面数据行数
                'pageList': [10, 20],//设置显示页面的行数的选择
                */
                'rownumbers': true,//是否在行前面添加序号
                'toolbar': '#tabelShowBar',//添加工具栏，制定一个容器的id
                'columns': [[    //指定数据的key值，以及列的名称
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
                                case 0:
                                    return '<div>未加工</div>';
                                case 1:
                                    return '<div>开料</div>';
                                case 2:
                                    return '<div>木工定型</div>';
                                case 3:
                                    return '<div>机雕</div>';
                                case 4:
                                    return '<div>手雕</div>';
                                case 5:
                                    return '<div>木工组装</div>';
                                case 6:
                                    return '<div>刮磨</div>';
                                case 7:
                                    return '<div>组装铜件</div>';
                                case 8:
                                    return '<div>上蜡</div>';
                                default:
                                    return '<div>加工完毕</div>';
                            }
                        }
                    },
                    {
                        field: 'type',
                        title: '是否成品',
                        width: 100,
                        align: 'center',
                        formatter: function (type) {
                            if (type == 1) {
                                return '<div>成品</div>';
                            } else {
                                return '<div>半成品</div>';
                            }
                        }
                    },
                    {
                        field: 'amount',
                        title: '数量',
                        width: 100,
                        align: 'center',
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
            $("#tabelShowBox").show();
        }else{
            $.messager.alert('提示', '请选择要查看的单据', 'info');
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
    // 提交加价
    supplementSum: function () {
        var rows = $("#table").datagrid("getSelections");
        if(rows.length>0){
            id = $("#table").datagrid("getSelected").id;
            console.log(id);
            $("#supplementId").val(id);
            $('#supplementForm').form('submit', {
                onSubmit: function () {
                    var lag = $("#supplementForm").form('validate');
                    console.log(lag)
                    console.log(form2Json("supplementForm"));
                    if (lag == true) {
                        $.ajax({
                            url: '/ky-redwood/process/saveAddFee',
                            type: 'POST',
                            dataType: "json",
                            contentType: "application/json; charset=utf-8",
                            data: form2Json("supplementForm"),
                            success: function (data) {
                                console.log(data);
                                if (data.code==10000) {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '加价成功'
                                    })
                                } else {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '加价失败'
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
                    $("#supplementBox").dialog({
                        closed: true
                    })
                    $("#table").datagrid('reload')
                }
            });
        }else{
            $.messager.alert('提示', '请选择要加价的记录', 'info');
        }

    },

    // 提交加工表单
    continueSum: function () {
        $('#continueProcessingForm').form('submit', {
            onSubmit: function () {
                    $.ajax({
                        url: '/ky-redwood/process/doSubmitAudit',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("continueProcessingForm"),
                        success: function (data) {
                            console.log(data);
                            if (data.code==10000) {
                                $.messager.show({
                                    title: '提示',
                                    msg: '加工成功'
                                })
                            } else {
                                $.messager.show({
                                    title: '提示',
                                    msg: '加工失败'
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
            },
            success: function () {
                $.messager.progress('close');
                $("#continueProcessingBox").dialog({
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
    continueRes: function () {
        $("#continueProcessingForm").form('clear');
    },
    supplementRes: function () {
        $("#supplementForm").form('clear');
    },
    // 取消表单
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    continueCan: function () {
        $("#continueProcessingBox").dialog({
            closed: true
        })
    },
    supplementCan: function () {
        $("#supplementBox").dialog({
            closed: true
        })
    },
    showClose: function () {
        $("#tabelShowBox").hide();
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
                        url: "/ky-redwood/process/deleteForce?id=" + ids.join(','),
                        beforeSend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            console.log(data);
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
    //加工
    submitAudit: function () {
        var rows = $("#table").datagrid("getSelections");
        if(rows.length>0){
            id = $("#table").datagrid("getSelected").id;
            console.log(id);
            $("#continueProcessingId").val(id);
            $("#continueProcessingBox").dialog({
                closed: false,
            });
            $("#continueFlowStatusCombo").combobox({
                url:'/ky-redwood/processFlow/querySmallId?id=' + id,
                method: 'get',
                valueField: 'id',
                textField: 'processFlowName',
                onLoadSuccess : function(){
                    $('#continueFlowStatusCombo').combobox('setValue','-请选择加工流程-');
                },
            })
        }else{
            $.messager.alert('提示', '请选择要加工的单据', 'info');
        }

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

$("#supplementBox").dialog({
    title: "加价",
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



