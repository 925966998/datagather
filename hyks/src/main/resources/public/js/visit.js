// 弹出框加载
$("#addBox").dialog({
    title: "资质信息",
    width: 600,
    height: 300,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})
obj = {
    // 查询
    find: function () {
        $("#table").datagrid('load', {
            startTime: $.trim($("#startTime").val()),
        })
    },
    addBox: function () {
        $("#addForm").form('clear');
        $("#addBox").dialog({
            closed: false
        });
    },
    reset: function () {
        $("#addForm").form('clear');
    },
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    edit: function (id) {
        $("#addBox").dialog({
            closed: false
        })
        id = $("#table").datagrid('getSelected').id;
        $.ajax({
            url: '/ky-supplier/visit/queryById',
            type: 'get',
            dataType: 'json',
            data: {id: id},
            success: function (res) {
                console.log(res)
                if (res.data != null) {
                    $('#addForm').form('load', {
                        id: res.data.id,
                        visitName: res.data.visitName,
                        name: res.data.name,
                        visitDate: res.data.visitDate,
                        content: res.data.content,
                    })
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
                        url: "/ky-supplier/visit/deleteForce",
                        data: {
                            ids: ids.join(',')
                        },
                        beforesend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            if (data.code = '10000') {
                                $("#table").datagrid('loaded');
                                $("#table").datagrid('load');
                                $("#table").datagrid('unselectAll');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个访问记录被删除'
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
                            } else {
                                $.messager.show({
                                    title: '提示',
                                    msg: '信息删除失败'
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
        $.messager.confirm('提示信息', '是否删除所选择记录', function (flg) {
            if (flg) {
                $.ajax({
                    type: 'get',
                    url: '/ky-supplier/visit/deleteForce',
                    data: {
                        id: id
                    },
                    beforesend: function () {
                        $("#table").datagrid('loading');
                    },
                    success: function (data) {
                        if (data.code = '1000') {
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
                    },
                    error: function (request) {
                        if (request.status == 401) {
                            $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                                if (r) {
                                    parent.location.href = "/login.html";
                                }
                            });
                        } else {
                            $.messager.show({
                                title: '提示',
                                msg: '信息删除失败'
                            })
                        }
                    }
                })
            }
        })
    },
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                console.log($('#name').val())
                if (lag == true) {
                    $.ajax({
                        url: '/ky-supplier/visit/save',
                        type: 'get',
                        data: {
                            id: $('#id').val(),
                            supplierManageId: getUrlParam('supplierManageId'),
                            name: $('#name').val(),
                            visitName: $('#visitName').val(),
                            visitDate: $('#visitDate').val(),
                            content: $('#content').val(),
                        },
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
    }
}
// 加载表格
$("#table").datagrid({
    method: "get",
    iconCls: "icon-left02",
    url: '/ky-supplier/visit/queryPage',
    queryParams: {flag: 2, supplierManageId: getUrlParam('supplierManageId')},
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
            field: 'name',
            title: '公司名称',
            width: 100,
            align: 'center'
        },
        {
            field: 'visitDate',
            title: '访问日期',
            width: 100,
            align: 'center',
            formatter: function (value, row, index) {
                if (value != null) {
                    return new Date(value).Format("yyyy-MM-dd")
                }
            }
        },
        {
            field: 'content',
            title: '访问内容',
            width: 100,
            align: 'center',
        },
        {
            field: 'visitName',
            title: '访问人',
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
    },
    onClickRow: function (rowIndex, rowData) {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length > 1) {
            $.messager.alert('提示', '每次选择一条记录', 'info');
        }
    }
})

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

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