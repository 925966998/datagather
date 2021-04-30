$(function () {
    doQuery('/ky-supplier/companyOrder/queryPage');
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

$("#orderInfoId").combobox({
    url: '/ky-supplier/orderInfo/queryByParams',
    method: 'get',
    height: 26,
    width: '70%',
    valueField: 'id',
    textField: 'name',
});


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
                field: 'companyName',
                title: '公司名称',
                width: 100,
                align: 'center'
            },
            {
                field: 'orderName',
                title: '订单名称',
                width: 100,
                align: 'center'
            },
            {
                field: 'amount',
                title: '数量',
                width: 100,
                align: 'center'
            },
            // {
            //     field: 'taxNum',
            //     title: '税号',
            //     width: 100,
            //     align: 'center'
            // },
            // {
            //     field: 'legalPerson',
            //     title: '法人',
            //     width: 100,
            //     align: 'center'
            // },
            // {
            //     field: 'telePhone',
            //     title: '手机号',
            //     width: 100,
            //     align: 'center'
            // },
            // {
            //     field: 'contact',
            //     title: '联系人',
            //     width: 100,
            //     align: 'center'
            // },
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
        doQuery('/ky-supplier/companyOrder/queryPage?' + $("#tableFindForm").serialize())
    },
    // 添加
    edit: function (id) {
        $("#addBox").dialog({
            closed: false
        })
        id = $("#table").datagrid('getSelected').id;
        $.ajax({
            url: '/ky-supplier/companyOrder/queryById',
            type: 'get',
            dataType: 'json',
            data: {id: id},
            success: function (res) {
                console.log(res)
                if (res.data != null) {
                    $('#addForm').form('load', {
                        id: id,
                        companyName:res.data.companyName,
                        orderName:res.data.orderName,
                        amount:res.data.amount,
                        price:res.data.price,
                        // orderNum: res.data.orderNum,
                        // name: res.data.name,
                        // state: res.data.state,
                        // endTime: res.data.endTime,
                        // totalAmount: res.data.totalAmount,
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
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $(this).form('validate');
                if (lag == true) {
                    $.ajax({
                        url: '/ky-supplier/companyOrder/saveOrUpdate',
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

}
// 加载表格
