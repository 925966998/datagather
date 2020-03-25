obj = {
    // 添加
    addBox: function () {
        var node = $("#orgTree").tree('getSelected');
        console.log(node);
        if (!node) {
            $.messager.alert('警告', '请选择组织机构', 'warning');
            return;
        }
        $("#addForm").form('clear');

        $("input[type='text']").removeAttr("disabled");
        $("select").removeAttr("disabled");
        $(".aaaa").show();
        $("#addForm").show();
        $("#pid").val(node.id);
    },
    // 编辑
    edit: function () {
        var node = $("#orgTree").tree('getSelected');
        console.log(node);
        if (!node) {
            $.messager.alert('警告', '请选择组织机构', 'warning');
            return;
        }
        if (node.id == 0) {
            $.messager.alert('警告', '请点击添加按钮', 'warning');
            return;
        }
        $("input[type='text']").removeAttr("disabled");
        $("select").removeAttr("disabled");
        $(".aaaa").show();
        $("#addForm").show();
        queryById(node.id)
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


$("#provinceId").combobox({
    url: '/ky-datagather/area/queryByAreaLevel/1',
    method: 'get',
    valueField: 'areaId',
    textField: 'areaName',
    onChange: function (newValue, oldValue) {
        setAreaCode(newValue);
        $("#cityId").combobox({
            url: '/ky-datagather/area/queryByPid/' + newValue,
            method: 'get',
            valueField: 'areaId',
            textField: 'areaName',
            onChange: function (newValue, oldValue) {
                setAreaCode(newValue);
                $("#areaId").combobox({
                    url: '/ky-datagather/area/queryByPid/' + newValue,
                    method: 'get',
                    valueField: 'areaId',
                    textField: 'areaName',
                    onChange: function (newValue, oldValue) {
                        setAreaCode(newValue);
                    }
                });
            }
        });
    }
});

$("#hyfl").combotree({
    method: "get",
    url: '/ky-datagather/industry/queryTree',
    required: true
});

$("#cc").combotree({
    method: "get",
    url: '/ky-datagather/area/queryTree',
    onChange: function () {
        var t = $('#cc').combotree('tree');	// get the tree object
        var n = t.tree('getSelected');
        setAreaCode(n.id);
    }
});
$("#orgTree").tree({
    url: "/ky-datagather/org/queryTree",
    method: "get",
    animate: true,
    lines: true,
    onClick: function (node) {
        if (node.id != 0 || node.text != '组织机构') {
            $("#addForm").show();
            queryById(node.id)
            $("input[type='text']").attr("disabled", "disabled");
            $("select").attr("disabled", "disabled");
            $(".aaaa").hide();
        } else {
            $("#addForm").form('clear');
        }
    }
});


$(function () {
    //var node = $("#orgTree").tree('getSelected');
    $("#addForm").hide();
});


function queryById(id) {
    $.ajax({
        url: '/ky-datagather/org/queryById/' + id,
        type: 'get',
        success: function (res) {
            if (res != null) {
                $('#addForm').form('load', {
                    id: id,
                    pid: res.pid,
                    provinceId: res.provinceId,
                    cityId: res.cityId,
                    areaId: res.areaId,
                    areaCode: res.areaCode,
                    areaName: res.areaName,
                    orgName: res.areaName,
                    orgCode: res.orgCode,
                    orgLevel: res.orgLevel,
                    zt: res.zt,
                    ztlx: res.ztlx,
                    kjnd: res.kjnd,
                    zzjgdm: res.zzjgdm,
                    dwxz: res.dwxz,
                    hyfl: res.hyfl,
                    kfdw: res.kfdw,
                    bbh: res.bbh,
                    bwb: res.bwb,
                    sfhyysz: res.sfhyysz,
                    sjdm: res.sjdm,
                    dmjc: res.dmjc,
                    sfmj: res.sfmj,
                    xzjb: res.xzjb,
                    ysglfs: res.ysglfs,
                    zgksdm: res.zgksdm,
                    dwlb: res.dwlb
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

function setAreaCode(id) {
    $.ajax({
        type: 'get',
        url: '/ky-datagather/area/queryById/' + id,
        success: function (data) {
            $("#areaCode").val(data.data.areaCode);
            $("#areaName").val(data.data.areaName);
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
