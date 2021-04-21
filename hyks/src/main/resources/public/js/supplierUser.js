$(function () {
    doQuery('/ky-supplier/supplierUser/queryPage');
})
// 弹出框加载
$("#addBox").dialog({
    title: "信息内容",
    width: 400,
    height: 300,
    closed: true,
    modal: true,
    shadow: true
})

$("#userId").combobox({
    url: '/ky-supplier/sysUser/queryByParams',
    method: 'get',
    height: 26,
    width: '70%',
    valueField: 'id',
    textField: 'fullName',
});
$("#supplierType").combobox({
    url: '/ky-supplier/supplierType/queryByParams',
    method: 'get',
    height: 20,
    width: '15%',
    valueField: 'id',
    textField: 'supplierType',
});

// $("#supplierManage").combobox({
//     url: '/ky-supplier/supplierUser/queryByParams',
//     method: 'get',
//     height: 20,
//     width: '15%',
//     valueField: 'supplierManageId',
//     textField: 'name',
// });

function doQuery(url) {
    $("#table").datagrid({
        title: "客商指派列表",
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
                field: 'supplierState',
                title: '是否为正式客商',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '0') {
                        return "正式客商"
                    }else {
                        return "预备客商"
                    }
                }
            },
            // {
            //     field: "opr",
            //     title: '操作',
            //     width: 100,
            //     align: 'center',
            //     formatter: function (val, row) {
            //         e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.edit(\'' + row.id + '\')">指派</a> ';
            //         return e;
            //     }
            // }
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

obj = {
    // 查询
    find: function () {
        doQuery('/ky-supplier/supplierUser/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    edit: function (id) {
        $("#addBox").dialog({
            closed: false
        })
    },
    reset: function () {
        $("#addForm").form('clear');
    },
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    // 提交表单
    sum: function () {
        var rows = $("#table").datagrid("getSelections");
        var ids = [];
        var typeIds = [];
        for (i = 0; i < rows.length; i++) {
            ids.push(rows[i].supplierManageId);
            typeIds.push(rows[i].supplierTypeId);
        }
        var num = ids.length;
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                if (lag == true) {
                    $.ajax({
                        url: '/ky-supplier/supplierUser/assign',
                        type: 'GET',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: {
                            id: ids.join(','),
                            userId: $('#userId').val(),
                            typeId: typeIds.join(','),
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

}
// 加载表格
