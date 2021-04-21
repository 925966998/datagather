obj = {
    reBack:function(){
        $.ajax({
            url: '/ky-supplier/violation/querySupplierId',
            data:{uploadFormId: getUrlParam('uploadFormId')},
            type:'GET',
            success:function (res) {
                console.log(res)
                window.location.href="../web/violation.html?supplierManageId="+res
            }
        })
    },
    preview: function (name) {
        console.log(name)
        var fileUrl = "../upload/" + name;
        window.open(fileUrl);
    },
    delOne: function (name) {
        $.ajax({
            type: 'get',
            url: '/ky-supplier/supplierManage/getSessionRoleCode',
            success: function (data) {
                console.log(data)
                if (data != null && data != '' && data != 'undefined') {
                    if (parseInt(data) == 3) {
                        $.messager.show({
                            title: '提示',
                            msg: '暂无权限'
                        })
                    }else {
                        $.ajax({
                            url: '/ky-supplier/file/deleteFile',
                            type: 'post',
                            data: {path: name},
                            success: function (data) {
                                console.log(data)
                                if (data = true) {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '删除成功'
                                    })
                                    $("#table").datagrid("loaded");
                                    $("#table").datagrid("load");
                                    $("#table").datagrid("unselectRow");
                                } else {
                                    $.messager.show({
                                        title: '提示',
                                        msg: '删除失败'
                                    })
                                    $("#table").datagrid("loaded");
                                    $("#table").datagrid("load");
                                    $("#table").datagrid("unselectRow");
                                }
                            },
                        })
                    }
                } else {
                    $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                        if (r) {
                            parent.location.href = "/login.html";
                        }
                    });
                }
            }, error: function (request) {
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
$("#table").datagrid({
    method: "get",
    iconCls: "icon-left02",
    url: '/ky-supplier/violation/readFile',
    queryParams: {flag: 2, uploadFormId: getUrlParam('uploadFormId')},
    fitColumns: true,
    striped: true,
    pagination: true,
    pageSize: 10,
    width: '100%',
    rownumbers: true,
    pageNumber: 1,
    nowrap: true,
    height: 'auto',
    sortName: 'id',
    checkOnSelect: true,
    sortOrder: 'asc',
    toolbar: '#tabelBut',
    columns: [[
        {
            field: 'name',
            title: '文件名称',
            width: 100,
            align: 'center'
        },
        {
            field: "opre",
            title: '预览',
            width: 100,
            align: 'center',
            formatter: function (val, row) {
                e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.preview(\'' + getUrlParam('uploadFormId') + '/' + row.name + '\')">预览</a> ';
                return e;
            }
        },
        {
            field: "oprt",
            title: '下载',
            width: 100,
            align: 'center',
            formatter: function (val, row) {
                var url = "../upload/" + getUrlParam('uploadFormId') + '/' + row.name;
                d = '<a href="' + url + '"  download="' + row.name + '"> 下载 </a>'
                return d;
            }
        },
        {
            field: "opra",
            title: '删除',
            width: 100,
            align: 'center',
            formatter: function (val, row) {
                g = '<a  id="add" data-id="98" class=" operA"  onclick="obj.delOne(\'' + getUrlParam('uploadFormId') + '/' + row.name + '\')">删除</a> ';
                return g;
            }
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
    }
})

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}
