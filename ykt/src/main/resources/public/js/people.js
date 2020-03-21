// 加载树
function doQuery(url) {
    $("#table").datagrid({
        method: "get",
        iconCls: "icon-left02",
        url: url,
        queryParams: { flag: 2},
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
                field: 'projectName',
                title: '项目资金名称',
                width: 100,
                align: 'center'
            },
            {
                field: 'grantAmount',
                title: '发放金额',
                width: 100,
                align: 'center'
            },
            {
                field: "opr",
                title: '操作',
                width: 120,
                align: 'center',
                formatter: function (val, row) {
                    s = '<a  id="add" data-id="98" class=" operA"  onclick="obj.show(\'' + row.id + '\')">查看</a> ';
                    //e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">编辑</a> ';
                    //c = '<a  id="sub" data-id="98" class=" operA"  onclick="obj.submitAudit(\'' + row.id + '\')">提交</a> ';
                    d = '<a  id="del" data-id="98" class=" operA01"  onclick="obj.delOne(\'' + row.id + '\')">删除</a> ';
                    f = '<a  id="add" data-id="98" class=" operA"  onclick="obj.replace(\'' + row.id + '\')">补发记录</a> ';
                    return s+ d + f;
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

$(function () {
    // 加载表格
    doQuery('/ky-ykt/personUpload/queryPage');
    doQueryProject('findProjectId');
})

function doQueryProject(id) {
    $("#" + id).combobox({
        url: '/ky-ykt/project/queryByParams',
        method: 'get',
        data:{flag:2},
        height: 26,
        width: '15%',
        valueField: 'id',
        textField: 'projectName',
    });

}

function doQueryDepartment(id) {
    $("#id").combobox({
        url: '/ky-ykt/department/queryByParams',
        method: 'get',
        height: 26,
        width: '15%',
        valueField: 'id',
        textField: 'departmentName'
    })

}

obj = {
    // 查询
    find: function () {
        doQuery('/ky-ykt/personUpload/queryPage?' + $("#tableFindForm").serialize())
    },
    show: function (id) {
        $("#showBox").dialog({
            closed: false
        });
        $.ajax({
            url: '/ky-ykt/personUpload/queryById?id=' + id,
            type: 'get',
            dataType: 'json',
            beforeSend: function () {
                $.messager.progress();
            },
            success: function (data) {
                $.messager.progress('close');
                var data = data.data;
                $("#showName").text(data.name);
                $("#showPhone").text(data.phone);
                $("#showGrantAmount").text(data.grantAmount);
                $("#showIdCardNo").text(data.idCardNo);
                $("#showCounty").text(data.cname);
                $("#showAddress").text(data.address);
                $('#showProjectName').text(data.projectName);
                $("#showDepartmentName").text(data.departmentName);
                $("#showBankCardNo").text(data.bankCardNo);
                // $('#showStatus').text(str);
                //$('#showAuditReason').text(data.auditReason);
                // $('#showPushReason').text(data.pushReason);
                //$('#showSubmitTime').text(data.submitTime);
                //$('#showAuditTime').text(data.auditTime);
                //$('#showPushTime').text(data.pushTime);

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
    //发放记录
    replace: function (id) {

        $("#replaceBox").dialog({
            closed: false
        });

        $.ajax({
            url: '/ky-ykt/personPeplacement/queryBypersonId?id=' + id,
            type: 'get',
            dataType: 'json',
            beforeSend: function () {
                $.messager.progress();
            },
            success: function (data) {
                console.log(data);
                $.messager.progress('close');
                for (var i = 0;i<=data.length;i++){
                    console.log(data[i]);
                    $("#replaceTable").append('<tr><td>第'+(i+1)+'次补发</td><td>发放了'+data[i].replacementAmount+'元</td></tr>');
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
        });
        $("#addForm").form('clear');
        $('#project').hide();
        $('#department').hide()
        doQueryProject('projectId');
        doQueryDepartment('departId');
        $.ajax({
            url: '/ky-ykt/personUpload/queryById?id=' + id,
            type: 'get',
            dataType: 'json',
            beforeSend: function () {
                $.messager.progress();
            },
            success: function (data) {
                $.messager.progress('close');
                var data = data.data;
                if (data) {
                    $("#id").val(id);
                    $("#name").val(data.name);
                    $("#phone").val(data.phone);
                    $("#grantAmount").val(data.grantAmount);
                    $("#idCardNo").val(data.idCardNo);
                    $("#bankCardNo").val(data.bankCardNo);
                    $("#grantAmount").val(data.grantAmount);
                    //$("#county").val('data.county');
                    $("#county").combobox('setValue',data.county);
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
                        url: '/ky-ykt/personUpload/saveOrUpdate',
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
    canUpload: function () {
        $("#addUploadBox").dialog({
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
                        url: "/ky-ykt/personUpload/deleteForce?id=" + ids.join(','),
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
        id = $("#table").datagrid('getSelected').id;
        $.messager.confirm('提示信息', '是否删除所选择记录', function (flg) {
            if (flg) {
                $.ajax({
                    type: 'get',
                    url: '/ky-ykt/personUpload/deleteForce?id=' + id,
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
    subAll: function () {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length > 0) {
            $.messager.confirm('确定提交审核', '你确定要提交审核你选择的记录吗？', function (flg) {
                if (flg) {
                    var ids = [];
                    for (i = 0; i < rows.length; i++) {
                        ids.push(rows[i].id);

                    }
                    var num = ids.length;
                    $.ajax({
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        url: "/ky-ykt/personUpload/doSubmitAudit",
                        data: ids.join(","),
                        beforeSend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            if (data) {
                                $("#table").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个用户被提交'
                                })

                            } else {
                                $.messager.show({
                                    title: '警示信息',
                                    msg: "提交失败"
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
            $.messager.alert('提示', '请选择要提交的记录', 'info');
        }

    },
    export: function () {

    }
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

// 弹出框加载
$("#showBox").dialog({
    title: "查看",
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
$("#replaceBox").dialog({
    title: "查看发放记录",
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
$("#county").combobox({
    url: '/ky-ykt/areas/queryByCounty',
    method: 'get',
    valueField: 'id',
    textField: 'cname',
    loadFilter: function (data) {
        var obj = {};
        obj.id = '';
        obj.cname = '请选择'
        //在数组0位置插入obj,不删除原来的元素
        data.splice(0, 0, obj)
        return data;
    }
})


