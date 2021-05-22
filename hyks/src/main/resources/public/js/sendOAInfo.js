obj = {
    // 查询
    find: function () {
        doQuery('/ky-supplier/orderInfo/queryPage?' + $("#tableFindForm").serialize())
    },
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    // 提交表单
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                if (lag == true) {
                    $.ajax({
                        url: '/ky-supplier/orderInfo/saveSupplier',
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
    chooseSupplier: function (id) {
        $("#addBox").dialog({
            closed: false
        });
        // id = $("#table").datagrid('getSelected').id;
        $.ajax({
            url: '/ky-supplier/orderInfo/queryById',
            type: 'get',
            dataType: 'json',
            data: {id: id},
            success: function (res) {
                console.log(res)
                if (res.data != null) {
                    $('#addForm').form('load', {
                        id: id,
                        name: res.data.name,
                        totalAmount: res.data.totalAmount,
                    })
                    querySupplier(id);
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
    }
}

// 加载表格
function querySupplier(id) {
    $("#supplierId").combobox({
        url: '/ky-supplier/company/queryByParams',
        method: 'get',
        height: 26,
        width: '75%',
        multiple: true,
        valueField: 'pk_supplier',
        textField: 'name',
    });
}

$(function () {
    doQuery('/ky-supplier/orderInfo/queryPage');
})

function doQuery(url) {
    $("#table").edatagrid({
        title: "审批单列表",
        iconCls: "icon-left02",
        url: url,
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
            // {
            //     checkbox: true,
            //     field: 'no',
            //     width: 100,
            //     align: 'center'
            // },
            {
                field: 'orderNum',
                title: '编号',
                width: 75,
                align: 'center',
            },
            {
                field: 'name',
                title: '名称',
                width: 75,
                align: 'center',
            },
            {
                field: 'specs',
                title: '规格',
                width: 75,
                align: 'center',
            },
            {
                field: 'totalAmount',
                title: '数量',
                width: 75,
                align: 'center',
            },
            {
                field: 'unit',
                title: '单位',
                width: 75,
                align: 'center',
            },
            {
                field: 'orderType',
                title: '请购类型',
                width: 75,
                align: 'center',
            },
            {
                field: 'oddNum',
                title: '请购单号',
                width: 75,
                align: 'center',
            },
            {
                field: 'orderTime',
                title: '请购日期',
                width: 75,
                align: 'center',
            },
            // {
            //     field: 'orderOrg',
            //     title: '库存组织',
            //     width: 100,
            //     align: 'center',
            // },
            // {
            //     field: 'needTime',
            //     title: '需求日期',
            //     width: 100,
            //     align: 'center',
            //     editor: {type: 'datetimebox', options: 'showSeconds:false',}
            // },
            // {
            //     field: 'opr',
            //     title: '操作',
            //     width: 100,
            //     align: 'center',
            //     formatter: function (val, row) {
            //         e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.chooseSupplier(\'' + row.id + '\')">选择供应商</a> ';
            //         a = '<a  id="look"   class=" operA" class="easyui-linkbutton"  href="../web/orderSupplier.html?orderId=' + row.id + '">供应商列表</a> ';
            //         return e;
            //     }
            // },
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

