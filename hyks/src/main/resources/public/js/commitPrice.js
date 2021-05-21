obj = {
    // 查询
    find: function () {
        doQuery('/ky-supplier/companyOrder/queryCommitPrice?' + $("#tableFindForm").serialize())
    },
}

$(function () {
    doQuery('/ky-supplier/companyOrder/queryCommitPrice');
})

function doQuery(url) {
    $("#table").datagrid({
        title: "列表",
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
                field: 'listName',
                title: '采购编号',
                width: 70,
                align: 'center',
            },
            {
                field: 'userName',
                title: '采购员',
                width: 70,
                align: 'center',
            },
            {
                field: 'userCell',
                title: '手机号',
                width: 70,
                align: 'center',
            },
            {
                field: 'talkNum',
                title: '谈判次数',
                width: 50,
                align: 'center',
            },
            {
                field: 'opr',
                title: '操作',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    a = '<a  id="look"   class=" operA" class="easyui-linkbutton"  href="../web/purchaseInfo.html?orderId=' + row.id + '">报价</a> ';
                    return a;
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
