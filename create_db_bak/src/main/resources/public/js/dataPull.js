obj = {
    // 添加
    caiji: function () {
        var node = $("#orgTree").tree('getSelected');
        if (!node) {
            $.messager.alert('警告', '请选择组织机构', 'warning');
            return;
        }
        if (parseInt($('#checck').val()) == 2) {
            $.messager.alert('警告', '请避免重复操作,切换表继续采集', 'warning');
            return;
        }
        $('#checck').val(2);
        if (caijiurl != null && caijiurl != '') {
            this.truncate()
            $.ajax({
                type: 'get',
                url: caijiurl,
                data: {KJDZZBBH: $('#areaCode').val()},
                beforeSend: function () {
                    $.messager.progress({
                        text: '正在采集。。。'
                    });
                },
                success: function (data) {
                    $.messager.progress('close');
                    if (data = 'success') {
                        checkTarget($('#checkTarget').val());
                        $('#checck').val(2);
                        $.messager.show({
                            title: '提示',
                            msg: "采集成功"
                        })
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: "采集失败"
                        })
                    }
                },
                error: function (request) {
                    $.messager.progress('close');
                    if (request.status == 401) {
                        $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                            if (r) {
                                parent.location.href = "/login.html";
                            }
                        });
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: '删除失败'
                        })
                    }
                }
            })
        } else {
            $.messager.alert('警告', '请选择采集表', 'warning');
            return;
        }
    },
    // 编辑
    truncate: function () {
        var node = $("#orgTree").tree('getSelected');
        console.log(node);
        if (!node) {
            $.messager.alert('警告', '请选择组织机构', 'warning');
            return;
        }
        if ($('#checkTarget').val() == null || $('#checkTarget').val() == "" || $('#checkTarget').val() == 'undefined') {
            $.messager.alert('警告', '请选择采集表', 'warning');
            return;
        }
        $.ajax({
            type: 'get',
            url: '/ky-datagather/tableList/truncate',
            data: {KJDZZBBH: $('#areaCode').val(), tableName: $('#checkTarget').val()},
            beforeSend: function () {
                $.messager.progress({
                    text: '正在采集。。。'
                });
            },
            success: function (data) {
                $.messager.progress('close');
                if (data = 'success') {
                    checkTarget($('#checkTarget').val());
                    $.messager.show({
                        title: '提示',
                        msg: "清空成功"
                    })
                } else {
                    $.messager.show({
                        title: '提示',
                        msg: "清空失败"
                    })
                }
            },
            error: function (request) {
                $.messager.progress('close');
                if (request.status == 401) {
                    $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                        if (r) {
                            parent.location.href = "/login.html";
                        }
                    });
                } else {
                    $.messager.show({
                        title: '提示',
                        msg: '清空失败'
                    })
                }
            }
        })
    },
    reset: function () {
        $("#addForm").form('clear');
    },
    sum: function () {
        $('#addForm').form('submit', {
            onSubmit: function () {
                var lag = $("#addForm").form('validate');
                console.log(lag)
                if (lag == true) {
                    $.ajax({
                        url: '/ky-datagather/org/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: form2Json("addForm"),
                        success: function (data) {
                            $("#orgTree").tree('reload');
                        },
                        error: function (request) {
                            $("#orgTree").tree('reload');
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
                $("#orgTree").tree('reload');
            }
        });

    },
    del: function () {
        var node = $("#orgTree").tree('getSelected');
        console.log(node);
        if (!node) {
            $.messager.alert('警告', '请选择组织机构', 'warning');
            return;
        }
        $.messager.confirm('提示信息', '是否删除所选择记录', function (flg) {
            if (flg) {
                $.ajax({
                    type: 'get',
                    url: '/ky-datagather/org/deleteForce/' + node.id,
                    success: function (data) {
                        if (data.code = '1000') {
                            $("#orgTree").tree('reload');
                            $.messager.show({
                                title: '提示信息',
                                msg: "删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: "删除失败"
                            })
                        }
                        $("#orgTree").tree('reload');
                    },
                    error: function (request) {
                        if (request.status == 401) {
                            $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
                                if (r) {
                                    parent.location.href = "/login.html";
                                }
                            });
                        } else {
                            $.messager.show({
                                title: '提示',
                                msg: '删除失败'
                            })
                        }
                    }
                })
            }
        })
    }
}
var caijiurl = '';

function checkTarget(strFlag) {
    var node = $("#orgTree").tree('getSelected');
    if (!node) {
        $.messager.alert('警告', '请选择组织机构', 'warning');
        return;
    }
    $('#checck').val(1);
    $('#checkTarget').val(strFlag);
    var columns = [];
    var array = [];
    $.ajax({
        url: '/ky-datagather/tableList/queryDescription?tableName=' + strFlag,
        type: 'get',
        success: function (data) {
            if (data != null) {
                $(data).each(function () {
                    array.push({field: '', title: '', width: '', align: '',formatter:''});
                });
                columns.push(array);
                $(data).each(function (index, el) {
                    columns[0][index]['field'] = el['strName'];
                    columns[0][index]['title'] = el['strDes'];
                    columns[0][index]['width'] = "100";
                    columns[0][index]['align'] = "center";
                    if(el['strDes']=='是否含有预算账'){
                        columns[0][index]['formatter'] = function (value, row, index) {
                            if (value == 0) {
                                return '否'
                            } else if (value == 1) {
                                return '是'
                            }
                        }
                    }
                });
            }
            console.log(columns)
            if (strFlag == 'DZZBXX' || strFlag == 'YSDW') {
                $('#acjgb').hide();
                $('#qkgb').text('清空' + strFlag + '表');
            } else {
                $('#acjgb').show();
                $('#cjgb').text('采集' + strFlag + '表');
                $('#qkgb').text('清空' + strFlag + '表');
            }
            var url = "";
            switch (strFlag) {
                case "DZZBXX":
                    url = "/ky-datagather/tableList/queryPageDZZBXX";
                    caijiurl = "";
                    break;
                case "FZLX":
                    url = "/ky-datagather/tableList/queryPageFzlx";
                    caijiurl = "/ky-datagather/fzlx/fzlx";
                    break;
                case "FZNCS":
                    url = "/ky-datagather/tableList/queryPageFZNCS";
                    caijiurl = "/ky-datagather/db/fzncs";
                    break;
                case "FZXX":
                    url = "/ky-datagather/tableList/queryPageFzxx";
                    caijiurl = "/ky-datagather/fzlx/fzxx";
                    break;
                case "FZYE":
                    url = "/ky-datagather/tableList/queryPageFZYE";
                    caijiurl = "/ky-datagather/Fzye/fzye";
                    break;
                case "PZFZMX":
                    url = "/ky-datagather/tableList/queryPagePZFZMX";
                    caijiurl = "/ky-datagather/db/pzfzmx";
                    break;
                case "KJKM":
                    url = "/ky-datagather/tableList/queryPageKJKM";
                    caijiurl = "/ky-datagather/fzlx/kjkm";
                    break;
                case "KJQJDY":
                    url = "/ky-datagather/tableList/queryPageKjqjdy";
                    caijiurl = "/ky-datagather/dby/kjqjdy";
                    break;
                case "JZPZ":
                    url = "/ky-datagather/tableList/queryPageJZPZ";
                    caijiurl = "/ky-datagather/dby/jzpz";
                    break;
                case "KMNCS":
                    url = "/ky-datagather/tableList/queryPageKMNCS";
                    caijiurl = "/ky-datagather/dby/kmncs";
                    break;
                case "KMYE":
                    url = "/ky-datagather/tableList/queryPageKMYE";
                    caijiurl = "/ky-datagather/dby/kmye";
                    break;
                case "YSDW":
                    url = "/ky-datagather/tableList/queryPageYSDW";
                    caijiurl = "/ky-datagather/db/ysdw";
                    break;
            }
            $("#table").datagrid({
                title: "数据列表",
                iconCls: "icon-left02",
                url: url,
                queryParams: {KJDZZBBH: $('#areaCode').val()},
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
                columns: columns,
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
            $("#table").datagrid('load');
            columns = [];

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

$("#orgTree").tree({
    url: "/ky-datagather/org/queryTree",
    method: "get",
    animate: true,
    lines: true,
    onClick: function (node) {
        if (node.id != 0 || node.text != '组织机构') {
            $.ajax({
                url: '/ky-datagather/org/queryById/' + node.id,
                type: 'get',
                success: function (res) {
                    if (res != null) {
                        $('#areaCode').val(res.areaCode+ res.orgCode + res.zt + res.ztlx + res.kjnd);
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
});

function doBak() {
    $.ajax({
        url: '/ky-datagather/bak/queryIpAndPort',
        type: 'get',
        success: function (res) {
            window.location.href = "http://" + res.ip + ":" + res.port + "/ky-datagather/bak/do";
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

// $(function () {
//     $("#addForm").hide();
// });