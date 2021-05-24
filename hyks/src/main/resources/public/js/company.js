obj = {
    // 查询
    find: function () {
        doQuery('/ky-supplier/company/queryPage?' + $("#tableFindForm").serialize())
    },
    companyOrder: function (id) {
        $("#addOrderBox").dialog({
            closed: false
        })
    },
    canOrder: function () {
        $("#addOrderBox").dialog({
            closed: true
        })
    },
    // 提交表单
    sumOrder: function () {
        var rows = $("#table").datagrid("getSelections");
        var ids = [];
        for (i = 0; i < rows.length; i++) {
            ids.push(rows[i].pk_supplier);
        }
        var num = ids.length;
        $('#addOrderForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                if (lag == true) {
                    $.ajax({
                        url: '/ky-supplier/company/assign',
                        type: 'GET',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: {
                            id: ids.join(','),
                            orderId: $('#orderId').val(),
                            totalAmount: $('#totalAmount').val(),
                        },
                        success: function (data) {
                            console.log(data.code)
                            if (data.code == 50000) {
                                $.messager.show({
                                    title: '提示',
                                    msg: data.data
                                })
                            } else {
                                $("#table").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个客商指派成功'
                                })
                                $("#addOrderBox").dialog({
                                    closed: true
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
                $("#table").datagrid('reload')
            }
        });
    },
}
// 加载表格

$(function () {
    doQuery('/ky-supplier/company/queryPage');
})

$("#orderId").combobox({
    url: '/ky-supplier/orderInfo/queryByParams',
    method: 'get',
    height: 26,
    width: '70%',
    valueField: 'id',
    textField: 'name',
});

function doQuery(url) {
    $("#table").datagrid({
        title: "供应商列表",
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
        sortName: 'pk_supplier',
        checkOnSelect: true,
        sortOrder: 'asc',
        toolbar: '#tabelBut',
        columns: [[
            // {
            //     checkbox: true,
            //     field: 'no',
            //     width: 100,
            //     align: 'center'
            // },
            {
                field: 'code',
                title: '编码',
                width: 100,
                align: 'center',
            },
            {
                field: 'legalbody',
                title: '法人',
                width: 100,
                align: 'center',
            },
            {
                field: 'name',
                title: '名称',
                width: 100,
                align: 'center',
            },
            // {
            //     field: 'shortname',
            //     title: '简称',
            //     width: 100,
            //     align: 'center',
            // },
            {
                field: 'supprop',
                title: '类型',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    switch (val) {
                        case 0:return '外部单位';
                        case 1:return '内部单位';
                    }
                    // if (val == 0) return
                    // else if (val == 1) return '内部单位'
                }
            },
            {
                field: 'memo',
                title: '备注',
                width: 100,
                align: 'center',
            },
            {
                field: 'buslicensenum',
                title: '营业执照号码',
                width: 100,
                align: 'center',
            }, {
                field: 'taxpayerid',
                title: '纳税人登记号',
                width: 100,
                align: 'center',
            }, {
                field: 'corpaddress',
                title: '地址',
                width: 100,
                align: 'center',
            },
            {
                field: 'enablestate',
                title: '启用状态',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    if (val == 1) return '未启用'
                    else if (val == 2) return '已启用'
                    else if (val == 3) return '已停用'
                }
            },
            {
                field: 'supstate',
                title: '供应商状态',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    if (val == 0) return '潜在'
                    else if (val == 1) return '核准'
                }
            },
            {
                field: 'mnecode',
                title: '助记码',
                width: 100,
                align: 'center',
            },
            {
                field: 'pk_supplierclass',
                title: '供应商基本分类',
                width: 100,
                align: 'center',
            },
            {
                field: 'pk_suptaxes',
                title: '供应商税类',
                width: 100,
                align: 'center',
            },
            {
                field: 'cellNum',
                title: '电话',
                width: 100,
                align: 'center',
            },
            {
                field: 'tellPhone',
                title: '手机号',
                width: 100,
                align: 'center',
            },
            {
                field: 'cellName',
                title: '联系人',
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
}

// 弹出框加载
$("#addBox").dialog({
    title: "信息内容",
    width: 400,
    height: 300,
    closed: true,
    modal: true,
    shadow: true
})

$("#addOrderBox").dialog({
    title: "信息内容",
    width: 400,
    height: 300,
    closed: true,
    modal: true,
    shadow: true
})