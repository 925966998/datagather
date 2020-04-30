// 加载树
function doQuery(url) {
    $("#table").datagrid({
        method: "get",
        iconCls: "icon-left02",
        url: url,
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
        checkOnSelect: true,
        singleSelect: true,
        toolbar: '#tabelBut',
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
                field: 'flowStatus',
                title: '加工阶段',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (flowStatus) {
                    switch (flowStatus) {
                        case 1:
                            return '<div>开料</div>';
                        case 2:
                            return '<div>木工定型</div>';
                        case 3:
                            return '<div>机雕</div>';
                        case 4:
                            return '<div>手雕</div>';
                        case 5:
                            return '<div>木工组装</div>';
                        case 6:
                            return '<div>刮磨</div>';
                        case 7:
                            return '<div>组装铜件</div>';
                        case 8:
                            return '<div>上蜡</div>';
                        case 9:
                            return '<div>加工完毕</div>';
                        default:
                            return '<div>未加工</div>';
                    }
                }
            },

            {
                field: 'type',
                title: '是否半成品入库',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (type) {
                    if (type == 1) {
                        return '<div>否</div>';
                    } else {
                        return '<div>是</div>';
                    }
                }
            },
            {
                field: 'amount',
                title: '用料数量',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'processingPersonnel',
                title: '加工人员',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'fee',
                title: '加工费',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'add_fee',
                title: '补价费',
                width: 100,
                align: 'center',
                sortable: true
            },

        ]],
        onLoadError: function (request) {
            if (request.status == 401) {
                $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                    if (r) {
                        parent.location.href = "/login.html";
                    }
                });
                s
            }
        }
    })
}

$(function () {
    //doQuery('/ky-redwood/process/queryPage');
    statistics1();
});
obj = {
    // 查询
    find: function () {
        doQuery('/ky-redwood/process/queryFinancial?' + $("#tableFindForm").serialize())
        statistics1();
        statistics2();
        statistics3();
        statistics4();
    },
    // 添加
    addBox: function () {
        $("#addBox").dialog({
            closed: false

        });
        $("#addForm").form('clear');
    },
    // 编辑
    edit: function () {
        var rows = $("#table").datagrid("getSelections");
        if (rows.length > 0) {
            id = $("#table").datagrid("getSelected").id;
            $("#addBox").dialog({
                closed: false,
            })
            $.ajax({
                url: '/ky-redwood/process/queryById?id=' + id,
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    $.messager.progress('close');
                    var data = data.data;
                    console.log(data);
                    $("#id").val(data.id);
                    $("#processParentId").val(data.processParentId);
                    $("#productName").val(data.productName);
                    var type = data.type;
                    $("input[name='type']").each(function () {
                        if (type == $(this).val()) {
                            $(this).prop("checked", true);
                        }
                    });
                    $("#amount").numberbox('setValue', data.amount);
                    $("#fee").numberbox('setValue', data.fee);
                    $("#add_fee").numberbox('setValue', data.add_fee);
                    var flowStatus = data.flowStatus;
                    $("#flowStatusCombo").combobox("setValue", flowStatus);
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
            $.messager.alert('提示', '请选择要编辑的单据', 'info');
        }
    },
    // 提交表单
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $("#addForm").form('validate');
                console.log(lag)
                console.log(form2Json("addForm"));
                if (lag == true) {
                    $.ajax({
                        url: '/ky-redwood/process/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
                            if ($("#id").val()) {
                                $.messager.show({
                                    title: '提示',
                                    msg: '修改成功'
                                })
                                $("#table").datagrid('reload');
                                $("#tableShow").datagrid('reload');
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
                } else {
                    return false;
                }
            },
            success: function () {
                $.messager.progress('close');
                $("#addBox").dialog({
                    closed: true
                })
                $("#table").datagrid('reload');
                $("#tableShow").datagrid('reload');
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
    // 删除多个
    del: function () {
        var rows = $("#table").datagrid("getSelections");
        console.log(rows);
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
                        url: "/ky-redwood/process/deleteForce?id=" + ids.join(','),
                        beforeSend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            console.log(data);
                            if (data) {
                                $("#table").datagrid('reload');
                                $("#tableShow").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个用户被删除'
                                })
                                $("#table").datagrid('reload');
                                $("#tableShow").datagrid('reload');
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
}

// 弹出框加载
$("#addBox").dialog({
    title: "编辑数据",
    width: 500,
    height: 400,
    resizable: true,
    minimizable: true,
    maximizable: true,
    closed: true,
    modal: true,
    shadow: true
})

//加工阶段展示占比
function statistics1() {
    $.ajax({
        url: '/ky-redwood/process/queryFinancial',
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            console.log(res.processFlowName);
            console.log(res.num);

            var myChart = echarts.init(document.getElementById('chart01'));
            var dataAxis = [];
            var data = [];
            for (var i = 0; i < res.length; i++) {
                dataAxis.push(res[i].processFlowName);
                data.push(res[i].num);
            }
            console.log(dataAxis);
            console.log(data);
            //指定图表的配置项和数据
            option = {
                title: {
                    text: '加工流程情况'
                },
                //设置绝对大小
                grid:{
                    left:30,
                    right:'10%',
                    height:'75%',
                    bottom:30
                },
                //提示框组件
                tooltip: {},
                //图列组件
                legend: {},
                xAxis: {
                    data: dataAxis,
                    //通过 data 设置类目数据
                    type: 'category',
                },
                yAxis: {},
                series: [{
                    name:'当前加工阶段',
                    type: 'bar',
                    data: data,
                },
                ]
            };

            myChart.setOption(option);
        }
    });

};

//加工阶段展示占比
function statistics2() {
    $.ajax({
        url: '/ky-redwood/process/queryFinancial',
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            console.log(res.processFlowName);
            console.log(res.num);

            var myChart = echarts.init(document.getElementById('chart02'));
            var dataAxis = [];
            var data = [];
            for (var i = 0; i < res.length; i++) {
                dataAxis.push(res[i].processFlowName);
                data.push(res[i].num);
            }
            console.log(dataAxis);
            console.log(data);
            //指定图表的配置项和数据
            option = {
                title: {
                    text: '加工流程情况'
                },
                //设置绝对大小
                grid:{
                    left:30,
                    right:'10%',
                    height:'75%',
                    bottom:30
                },
                //提示框组件
                tooltip: {},
                //图列组件
                legend: {},
                xAxis: {
                    data: dataAxis,
                    //通过 data 设置类目数据
                    type: 'category',
                },
                yAxis: {},
                series: [{
                    name:'当前加工阶段',
                    type: 'bar',
                    data: data,
                },
                ]
            };

            myChart.setOption(option);
        }
    });

};

//加工阶段展示占比
function statistics3() {
    $.ajax({
        url: '/ky-redwood/process/queryFinancial',
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            console.log(res.processFlowName);
            console.log(res.num);

            var myChart = echarts.init(document.getElementById('chart03'));
            var dataAxis = [];
            var data = [];
            for (var i = 0; i < res.length; i++) {
                dataAxis.push(res[i].processFlowName);
                data.push(res[i].num);
            }
            console.log(dataAxis);
            console.log(data);
            //指定图表的配置项和数据
            option = {
                title: {
                    text: '加工流程情况'
                },
                //设置绝对大小
                grid:{
                    left:30,
                    right:'10%',
                    height:'75%',
                    bottom:30
                },
                //提示框组件
                tooltip: {},
                //图列组件
                legend: {},
                xAxis: {
                    data: dataAxis,
                    //通过 data 设置类目数据
                    type: 'category',
                },
                yAxis: {},
                series: [{
                    name:'当前加工阶段',
                    type: 'bar',
                    data: data,
                },
                ]
            };

            myChart.setOption(option);
        }
    });

};

//加工阶段展示占比
function statistics4() {
    $.ajax({
        url: '/ky-redwood/process/queryFinancial',
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            console.log(res.processFlowName);
            console.log(res.num);
            var myChart = echarts.init(document.getElementById('chart04'));
            var dataAxis = [];
            var data = [];
            for (var i = 0; i < res.length; i++) {
                dataAxis.push(res[i].processFlowName);
                data.push(res[i].num);
            }
            console.log(dataAxis);
            console.log(data);
            //指定图表的配置项和数据
            option = {
                title: {
                    text: '加工流程情况'
                },
                //设置绝对大小
                grid:{
                    left:30,
                    right:'10%',
                    height:'75%',
                    bottom:30
                },
                //提示框组件
                tooltip: {},
                //图列组件
                legend: {},
                xAxis: {
                    data: dataAxis,
                    //通过 data 设置类目数据
                    type: 'category',
                },
                yAxis: {},
                series: [{
                    name:'当前加工阶段',
                    type: 'bar',
                    data: data,
                },
                ]
            };
            myChart.setOption(option);
        }
    });
};

