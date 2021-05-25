$(function () {
    doQuery('/ky-supplier/companyOrder/queryPage');
})

// 弹出框加载


function doQuery(url) {
    $("#table").datagrid({
        title: "报价列表",
        iconCls: "icon-left02",
        url: url,
        fitColumns: true,
        striped: false,
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
        // checkOnSelect: true,
        singleSelect:'true',
        sortOrder: 'asc',
        toolbar: '#tabelBut',
        columns: [[
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
            {
                field: 'talkNum',
                title: '谈判次数',
                width: 100,
                align: 'center'
            },
            {
                field: 'priceNum',
                title: '已报次数',
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
                field: "opr",
                title: '操作',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    return '<a  id="add" data-id="98" class=" operA"  onclick="obj.lookPrice(\'' + row.id + '\')">查看价格</a> ';
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
        },
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
                if (res.data != null) {
                    $('#addForm').form('load', {
                        id: id,
                        companyName: res.data.companyName,
                        orderName: res.data.orderName,
                        amount: res.data.amount,
                        price: res.data.price,
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
                        url: '/ky-supplier/companyOrder/savePrice',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
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
    lookPrice: function (id) {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length > 1) {
            $.messager.alert('提示', '每次选择一条记录', 'info');
        } else {
            $("#priceBox").dialog({
                closed: false
            })
            $.ajax({
                url: '/ky-supplier/companyOrder/queryPrice',
                type: 'get',
                dataType: 'json',
                data: {id: id},
                success: function (res) {
                    //console.log(res)
                    $("#priceDetail").html("");
                    if (res.length > 0) {
                        for (var i = 0; i < res.length; i++) {
                            var a = i + 1;
                            $("#priceDetail").append(
                                "<label style='width: 25%;margin-left: 10%'>" + "第" + a + "次报价:" + "</label>" +
                                "<span  style='margin-left: 3%' > " + res[i].price + "</span>"
                            );
                            //每三个进行换行
                            if ((i + 1) % 1 == 0) {
                                $("#priceDetail").append("<br>");
                            }
                        }
                    } else {
                        $("#priceDetail").append(
                            "暂无报价"
                        );
                    }
                }
            });
        }

    }
}
// 加载表格
$("#addBox").dialog({
    title: "信息内容",
    width: 400,
    height: 300,
    closed: true,
    modal: true,
    shadow: true
})
$("#priceBox").dialog({
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