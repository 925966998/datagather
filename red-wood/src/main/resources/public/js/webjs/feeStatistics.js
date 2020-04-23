// 加载树
function doQuery(url1, url2) {
    $("#table").datagrid({
        method: "get",
        iconCls: "icon-left02",
        url: url1,
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
        //sortName: 'id',
        checkOnSelect: true,
        //sortOrder: 'asc',
        toolbar: '#tabelBut',
        singleSelect: true,
        remoteSort: false,
        onSortColumn: function (sort, order) {
            mySort('table', sort, order);
        },
        columns: [[
            {
                field: 'productName',
                title: '商品名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'jgFee',
                title: '加工费用',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'productTime',
                title: '入库时间',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (val, row) {
                    if (val.indexOf("T") > -1)
                        return val.substr(0, val.indexOf(".")).replace("T", " ");
                    return val;

                }
            }
        ]],
        onLoadSuccess: function (data) {
            $.ajax({
                url: '/ky-redwood/feeStatistics/queryJgFeeCount?' + $("#tableFindForm").serialize(),
                type: 'GET',
                success: function (data) {
                    $('#table').datagrid('appendRow', {
                        productName: '<b>统计</b>',
                        jgFee: '<div style="color: red">' + data.jgFee + '</div>',
                        productTime: ''
                    });
                }
            });
        },
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

    $("#table1").datagrid({
        method: "get",
        iconCls: "icon-left02",
        url: url2,
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
        //sortName: 'id',
        checkOnSelect: true,
        //sortOrder: 'asc',
        toolbar: '#tabelBut1',
        singleSelect: true,
        remoteSort: false,
        onSortColumn: function (sort, order) {
            mySort('table1', sort, order);
        },
        columns: [[
            {
                field: 'productName',
                title: '商品名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'jgFee',
                title: '加工费用',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'jwlFee',
                title: '机物料消耗',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'sdFee',
                title: '水电费',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'fzFee',
                title: '房租开销',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'xlFee',
                title: '修理费用',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'qtFee',
                title: '其他费用',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'ftFee',
                title: '分摊费用',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'productTime',
                title: '入库时间',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (val, row) {
                    if (val.indexOf("T") > -1)
                        return val.substr(0, val.indexOf(".")).replace("T", " ");
                    return val;

                }
            }
        ]],
        onLoadSuccess: function (data) {
            $.ajax({
                url: '/ky-redwood/feeStatistics/queryAllFeeCount?' + $("#tableFindForm").serialize(),
                type: 'GET',
                success: function (data) {
                    $('#table1').datagrid('appendRow', {
                        productName: '<b>统计</b>',
                        jgFee: '<div style="color: red">' + data.jgFee + '</div>',
                        jwlFee: '<div style="color: red">' + data.jwlFee + '</div>',
                        sdFee: '<div style="color: red">' + data.sdFee + '</div>',
                        fzFee: '<div style="color: red">' + data.fzFee + '</div>',
                        xlFee: '<div style="color: red">' + data.xlFee + '</div>',
                        qtFee: '<div style="color: red">' + data.qtFee + '</div>',
                        ftFee: '<div style="color: red">' + data.ftFee + '</div>',
                        productTime: ''
                    });
                }
            });
        },
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
    //doQuery('/ky-redwood/feeStatistics/queryNoSharing');
});
obj = {
    addBox: function () {
        if (!$('#startTime').val() || !$('#endTime').val()) {
            $.messager.alert('提示', '请选择时间区间', 'info');
            return;
        }
        $("#addBox").dialog({
            closed: false
        });
        $("#addForm").form('clear');
        $("#addFormStartTime").val($("#startTime").val());
        $("#addFormEndTime").val($("#endTime").val());
    },
    find: function () {
        if (!$('#startTime').val() || !$('#endTime').val()) {
            $.messager.alert('提示', '请选择时间区间', 'info');
            return;
        } else {
            doQuery('/ky-redwood/feeStatistics/queryNoSharing?' + $("#tableFindForm").serialize(),
                '/ky-redwood/feeStatistics/querySharing?' + $("#tableFindForm").serialize())
        }
    },
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $("#addForm").form('validate');
                console.log(lag)
                if (lag == true) {
                    $.ajax({
                        url: '/ky-redwood/feeStatistics/costSharing?' + $('#tableFindForm').serialize(),
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
                            $("#table").datagrid('reload');
                            $("#table1").datagrid('reload');
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
                $("#table1").datagrid('reload');
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
    }
}
// 弹出框加载
$("#addBox").dialog({
    title: "费用分摊",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})
