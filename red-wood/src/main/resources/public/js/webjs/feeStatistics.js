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
        //sortName: 'id',
        checkOnSelect: true,
        //sortOrder: 'asc',
        //toolbar: '#tabelBut',
        singleSelect: true,
        remoteSort: false,
        onSortColumn: function (sort, order) {
            mySort('table', sort, order);
        },
        columns: [[
            {
                field: 'processName',
                title: '单据名称',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'feeSum',
                title: '单据费用',
                width: 100,
                align: 'center',
                sortable: true
            },
            {
                field: 'startTime',
                title: '单据日期',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (val, row) {
                    val = val.substr(0, val.indexOf(".")).replace("T", " ");
                    return val;

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

}

$(function () {
    doQuery('/ky-redwood/feeStatistics/queryFeeStatistics');
});
obj = {
    // 查询
    find: function () {
        doQuery('/ky-redwood/feeStatistics/queryFeeStatistics?' + $("#tableFindForm").serialize())
    }
}
