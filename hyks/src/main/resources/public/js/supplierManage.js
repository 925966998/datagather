obj = {
    // 查询
    find: function () {
        console.log($("#supplierType").combobox('getValue'));
        doQuery('/ky-supplier/supplierManage/queryContent?supplierType=' + $("#supplierType").combobox('getValue') + "&queryContent=" + $("#queryContent").val());
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false
        });
        $("#addForm").form('clear')
    },
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    // 编辑
    changeSupplier: function (id) {
        id = $("#table").datagrid('getSelected').id;
        if (id != null && id != "") {
            $.messager.defaults = {ok: "确定", cancel: "取消", width: 300};
            $.messager.confirm('继续操作', '确定变更吗?',
                function (data) {
                    if (data) {
                        $.ajax({
                            url: '/ky-supplier/supplierManage/changeSupplier',
                            type: 'get',
                            dataType: 'json',
                            data: {id: id},
                            success: function (res) {
                                $("#addBox").dialog({
                                    closed: false
                                })
                                $.ajax({
                                    url: '/ky-supplier/supplierManage/queryById',
                                    type: 'get',
                                    dataType: 'json',
                                    data: {id: id},
                                    success: function (res) {
                                        console.log(res.data)
                                        if (res.data != null) {
                                            $('#addForm').form('load', {
                                                id: res.data.id,
                                                code: res.data.code,
                                                name: res.data.name,
                                                taxNum: res.data.taxNum,
                                                legalPerson: res.data.legalPerson,
                                                phone: res.data.phone,
                                                telePhone: res.data.telePhone,
                                                contact: res.data.contact,
                                                recordDate: res.data.recordDate,
                                                supplierMark: res.data.supplierMark,
                                            })
                                            $("#supplierTypeId").combobox('setValue', res.data.supplierTypeId);
                                        } else {
                                            $.messager.show({
                                                title: '提示',
                                                msg: '更新失败'
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
                        $("#addBox").dialog({
                            closed: true
                        })
                    }
                })
        } else {
            $.messager.show({
                title: '提示',
                msg: '请选择一条记录'
            })
        }
    },
    edit: function (id) {
        id = $("#table").datagrid('getSelected').id;
        if (id != null && id != "") {
            $("#addBox").dialog({
                closed: false
            })
            $.ajax({
                url: '/ky-supplier/supplierManage/queryById',
                type: 'get',
                dataType: 'json',
                data: {id: id},
                success: function (res) {
                    console.log(res.data)
                    if (res.data != null) {
                        $('#addForm').form('load', {
                            id: res.data.id,
                            code: res.data.code,
                            name: res.data.name,
                            taxNum: res.data.taxNum,
                            legalPerson: res.data.legalPerson,
                            phone: res.data.phone,
                            telePhone: res.data.telePhone,
                            contact: res.data.contact,
                            recordDate: res.data.recordDate,
                            supplierMark: res.data.supplierMark,
                        })
                        $("#supplierTypeId").combobox('setValue', res.data.supplierTypeId);
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: '更新失败'
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
            $.messager.show({
                title: '提示',
                msg: '请选择一条记录'
            })
        }
    },
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                if (lag == true) {
                    $.ajax({
                        url: '/ky-supplier/supplierManage/saveOrUpdate',
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
                        url: "/ky-supplier/supplierManage/deleteForce",
                        data: {
                            id: ids.join(',')
                        },
                        beforesend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            if (data) {
                                $("#table").datagrid('loaded');
                                $("#table").datagrid('load');
                                $("#table").datagrid('unselectAll');
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
                    url: '/ky-supplier/supplierManage/deleteForce',
                    data: {
                        id: id
                    },
                    beforesend: function () {
                        $("#table").datagrid('loading');
                    },
                    success: function (data) {
                        if (data) {
                            $("#table").datagrid("loaded");
                            $("#table").datagrid("load");
                            $("#table").datagrid("unselectRow");
                            $.messager.show({
                                title: '提示信息',
                                msg: "信息删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "信息删除失败"
                            })
                        }
                    }
                })
            }
        })
    },
    upload: function () {
        $.ajax({
            type: 'post',
            url: '/ky-supplier/supplierManage/import',
            processData: false,
            cache: false,
            contentType: false,
            data: new FormData($('#uploadForm')[0]),
            beforeSend: function () {
                $.messager.progress({
                    text: '上传中。。。'
                });
            },
            success: function (data) {
                console.log(data)
                $.messager.progress('close');
                $("#table").datagrid('reload')
                if (data.code != 10000) {
                    $.messager.alert('提示', data.data, 'error');
                } else {
                    $.messager.show({
                        title: '提示信息',
                        msg: "上传成功"
                    })
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
    excel: function () {
        window.location.href = '/ky-supplier/supplierManage/export?supplierType=' + $("#supplierType").combobox('getValue') + "&queryContent=" + $("#queryContent").val();
    },
}
$(function () {
    doQuery('/ky-supplier/supplierManage/queryContent');
    $.ajax({
        type: 'get',
        url: '/ky-supplier/supplierManage/getSessionRoleCode',
        success: function (data) {
            console.log(data)
            if (data != null && data != '' && data != 'undefined') {
                if (parseInt(data) == 3) {
                    $('#uploadForm').hide();
                    $('#excel').hide();
                    $('#supplierAdd').hide();
                    $('#supplierEdit').hide();
                    $('#changeSupplier').hide();
                    $('#supplierDel').hide();
                }
            } else {
                $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                    if (r) {
                        parent.location.href = "/login.html";
                    }
                });
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

})

function doQuery(url) {
    $("#table").datagrid({
        title: "客商列表",
        // pageList: [10, 20, 50],
        method: "get",
        iconCls: "icon-left02",
        url: url,
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
                field: 'code',
                title: '客商编码',
                width: 100,
                align: 'center'
            },
            {
                field: 'name',
                title: '客商名称',
                width: 100,
                align: 'center'
            },

            {
                field: 'taxNum',
                title: '税号',
                width: 100,
                align: 'center'
            },
            {
                field: 'legalPerson',
                title: '法人',
                width: 100,
                align: 'center'
            },
            {
                field: 'phone',
                title: '联系电话',
                width: 100,
                align: 'center'
            },
            {
                field: 'telePhone',
                title: '手机号',
                width: 100,
                align: 'center'
            },
            {
                field: 'contact',
                title: '联系人',
                width: 100,
                align: 'center'
            },
            {
                field: 'recordDate',
                title: '建档日期',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    if (value != null) {
                        return new Date(value).Format("yyyy-MM-dd")
                    }
                }
            },
            {
                field: 'supplierTypeName',
                title: '客商类型',
                width: 100,
                align: 'center',
            },
            {
                field: 'supplierMark',
                title: '客商标志',
                width: 100,
                align: 'center',
            },
            {
                field: "change",
                title: '变更记录',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    a = '<a  id="look"   class=" operA" class="easyui-linkbutton"  href="../web/supplierChange.html?supplierManageId=' + row.id + '">变更记录</a> ';
                    return a;
                }
            },
            // {
            //     field: "visit",
            //     title: '访客记录',
            //     width: 100,
            //     align: 'center',
            //     formatter: function (val, row) {
            //         a = '<a  id="look"   class=" operA" class="easyui-linkbutton"  href="../web/visit.html?supplierManageId=' + row.id + '">访客记录</a> ';
            //         return a;
            //     }
            // },
            {
                field: "opr",
                title: '资质信息',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    e = '<a  id="look"   class=" operA"  class="easyui-linkbutton"  href="../web/qualification.html?supplierManageId=' + row.id + '">资质信息/' + row.qualificationNum + '个</a>';
                    return e;
                }
            },
            {
                field: "oprt",
                title: '违规信息',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    d = '<a  id="look"   class=" operA" class="easyui-linkbutton"  href="../web/violation.html?supplierManageId=' + row.id + '">违规信息/' + row.violationNum + '个</a> ';
                    return d;
                }
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
        },
        onClickRow: function (rowIndex, rowData) {
            var rows = $("#table").datagrid("getSelections");
            if (rows.length > 1) {
                $.messager.alert('提示', '每次选择一条记录', 'info');
            }
        }
    })
}

// 弹出框加载
$("#addBox").dialog({
    title: "信息内容",
    width: 600,
    height: 350,
    closed: true,
    modal: true,
    shadow: true
})

$("#supplierTypeId").combobox({
    url: '/ky-supplier/supplierType/queryByParams',
    method: 'get',
    height: 26,
    width: '25%',
    valueField: 'id',
    textField: 'supplierType',
});
$("#supplierType").combobox({
    url: '/ky-supplier/supplierType/queryByParams',
    method: 'get',
    height: 20,
    width: '15%',
    valueField: 'id',
    textField: 'supplierType',
});
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}