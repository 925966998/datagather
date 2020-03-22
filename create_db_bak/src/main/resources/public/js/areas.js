/**
 * Created by Administrator on 2017/11/8.
 */
// 加载combox
$("#combox").combo({
    width: '16%',
    height: 26,
    multiple: true

})
obj = {
    // 查询
    find: function () {
        $("#table").datagrid('load', {
            county: $.trim($("#countySearch").val())
        })

    },
    // 添加
    addBox: function () {
        $("#id").val("");
        $("#addBox").dialog({
            closed: false
        });

    },
    // 编辑
    edit: function (id) {
        $("#addBox").dialog({
            closed: false
        })
        $.ajax({
            url: '/ky-ykt/areas/queryById',
            type: 'get',
            dataType: 'json',
            data: {id: id},
            success: function (res) {
                if (res != null) {
                    $('#addForm').form('load', {
                        city: res.city,
                        county: res.county,
                        address: res.address,
                        id: id,
                    })
                }else{
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
    look: function () {
        $("#lookTail").dialog({
            closed: false

        })
    },
    reset: function () {
        $("#addForm").form('clear');
    },
    sum: function () {
        $("#addForm").form('submit', {
            url: "/ky-ykt/areas/saveOrUpdate",
            method: "post",
            onSubmit: function () {
                return $(this).form('validate')
            },
            success: function (data) {
                if (data.code = '10000') {
                    $("#table").datagrid('loaded');
                    $("#table").datagrid('load');
                    $("#addBox").dialog({
                        closed: true

                    })
                    $.messager.show({
                        title: '提示',
                        msg: '信息保存成功'
                    })
                }else{
                    $.messager.show({
                        title: '提示',
                        msg: '信息保存失败'
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
                }else{
                    $.messager.show({
                        title: '提示',
                        msg: '信息保存失败'
                    })
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
                        url: "/ky-ykt/areas/deleteMoney",
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
                            }else{
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
                    url: '/ky-ykt/areas/deleteOne',
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
                        }else{
                            $.messager.show({
                                title: '提示',
                                msg: '信息删除失败'
                            })
                        }
                    }
                })

            }

        })
    }
}
// 加载表格
$("#table").datagrid({
    title: "行政区域列表",
    iconCls: "icon-left02",
    url: '/ky-ykt/areas/queryPage',
    fitColumns: true,
    striped: true,
    method: "GET",
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
            checkbox: true,
            field: 'no',
            width: 100,
            align: 'center'
        },
        {
            field: 'city',
            title: '城市',
            width: 100,
            align: 'center',
        },
        {
            field: 'county',
            title: '县城',
            width: 100,
            align: 'center',
            formatter: function (county) {
                switch (county) {
                    case '2':  return '<div>曲沃县</div>';
                    case '3':  return '<div>翼城县</div>';
                    case '4':  return '<div>襄汾县</div>';
                    case '5':  return '<div>洪洞县</div>';
                    case '6':  return '<div>古县</div>';
                    case '7':  return '<div>安泽县</div>';
                    case '8':  return '<div>浮山县</div>';
                    case '9':  return '<div>吉县</div>';
                    case '10':  return '<div>乡宁县</div>';
                    case '11':  return '<div>大宁县</div>';
                    case '12':  return '<div>隰县</div>';
                    case '13':  return '<div>永和县</div>';
                    case '14':  return '<div>蒲县</div>';
                    case '15':  return '<div>汾西县</div>';
                    case '16':  return '<div>侯马市</div>';
                    case '17':  return '<div>霍州市</div>';
                    default:
                        return '<div>尧都区</div>';
                }
            }
        },
        {
            field: 'address',
            title: '详细地址',
            width: 100,
            align: 'center'
        },
        {
            field: "opr",
            title: '操作',
            width: 100,
            align: 'center',
            formatter: function (val, row) {
                e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">编辑</a> ';
                d = '<a  id="add" data-id="98" class=" operA01"  onclick="obj.delOne(\'' + row.id + '\')">删除</a> ';
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
// 弹出框加载
$("#addBox").dialog({
    title: "信息内容",
    width: 500,
    height: 300,
    closed: true,
    modal: true,
    shadow: true
})

//加载县城下拉框
    $("#countCombo").combobox({
        url: '/ky-ykt/areas/queryByCounty',
        method: 'get',
        valueField: 'id',
        textField: 'cname'
    })