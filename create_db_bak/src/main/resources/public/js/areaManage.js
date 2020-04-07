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
                console.log(session);
                if(session == 'G版'){
                    $('#kfdw').combobox('setValue','北京用友政务股份有限公司');
                    $('#bbh').combobox('setValue','用友GRP-U8行政事业内控管理软件(G版)v10.5');
                }else{
                    $("#kfdw").combobox('setValue',"北京用友政务股份有限公司");
                    $("#bbh").combobox('setValue',"用友GRP-U8行政事业内控管理软件(B版)v10.5");
                }
                var lag = $("#addForm").form('validate');
                var t = $('#hyfl').combotree('tree');	// get the tree object
                var n = t.tree('getSelected');
                console.log(lag)
                var json = JSON.parse(form2Json("addForm"));
                json['hyflmc'] = n.text;
                console.log("json ============ " + JSON.stringify(json))
                if (lag == true) {
                    $.ajax({
                        url: '/ky-datagather/org/saveOrUpdate',
                        type: 'POST',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(json),
                        success: function (data) {
                            $("#orgTree").tree('reload');
                            $("input[type='text']").attr("disabled", "disabled");
                            $("select").attr("disabled", "disabled");
                            $(".aaaa").hide();
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
                        if (data.code = 10000) {
                            $("#orgTree").tree('reload');
                            $.messager.show({
                                title: '提示信息',
                                msg: "删除成功"
                            })
                        } else {
                            $.messager.show({
                                title: '警示信息',
                                msg: data.data
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
    },
/*

    banbenSelect:function () {
        //alert(1111);
        var bbh = $("#bbh").combobox("getValue");
        var orgCode = $("#orgCode").val();
        console.log(bbh);
        console.log(orgCode);
        data1=[{"id":"1", "text":"1"},{"id":"2", "text":"2"},{"id":"3", "text":"3"},{"id":"4", "text":"4"},{"id":"5", "text":"5"},
            {"id":"6", "text":"6"},{"id":"7", "text":"7"},{"id":"8", "text":"8"},{"id":"9", "text":"9"},{"id":"10", "text":"10"},
            {"id":"11", "text":"11"},{"id":"12", "text":"12"},{"id":"13", "text":"13"},{"id":"14", "text":"14"},{"id":"15", "text":"15"},
            {"id":"16", "text":"16"},{"id":"17", "text":"17"},{"id":"18", "text":"18"},{"id":"19", "text":"19"},{"id":"20", "text":"20"},
            {"id":"21", "text":"21"},{"id":"22", "text":"22"},{"id":"23", "text":"23"},{"id":"24", "text":"24"},{"id":"25", "text":"25"},
            {"id":"26", "text":"26"},{"id":"27", "text":"27"},{"id":"28", "text":"28"},{"id":"29", "text":"29"},{"id":"30", "text":"30"}
        ];
        if (bbh != null){
            if (orgCode != null){
                switch (bbh) {
                    case '用友GRP-U8行政事业内控管理软件(B版)v10.5':
                        $("#zt").combobox({
                            data:data1,
                            valueField: "id",
                            textField: "text",
                            panelHeight:"auto"
                        });
                        $("zt").combobox('setValue',text);
                        break;
                    case '用友GRP-U8行政事业内控管理软件(G版)v10.5':
                        $.ajax({
                            url: '/ky-datagather/area/queryZTH?hsdwdm=' + $("#orgCode").val(),
                            type: 'get',
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            //data: {"hsdwdm": $("#orgCode").val()},
                            beforeSend: function () {
                                $.messager.progress();
                            },
                            success: function (data) {
                                $.messager.progress('close');
                                console.log(data);
                                $("#zt").combobox({
                                    data: data,
                                    valueField: "ztbh",
                                    textField: "ztbh",
                                });
                                $("zt").combobox('setValue',ztbh);
                            },
                        });
                        break;
                }
            }else{
                $.messager.show({
                    title: '提示',
                    msg: '请填写单位代码'
                });
            }
        }else{
            $.messager.show({
                title: '提示',
                msg: '请选择版本号'
            });
        }
    }
    */
}




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
    getSession();
});


function queryById(id) {
    $("#addForm").form('clear');
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
                    orgName: res.orgName,
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
/*

function selectZTH() {
    $("#zt").combobox().next().children(":text").click(function(){
        console.log(111);
        //$("#zt").empty();
        //$("#zt").find("option").remove();
        //$('#zt').combobox("clear");//清空选中项
        // $('#zt').combobox("loadData", {});//清空option选项
        //alert("onblur事件");
        //$('#zt').combobox('select',"");
        $("#zt").combobox('reload');
        var bbh = $("#bbh").combobox("getValue");
        var orgCode = $("#orgCode").val();
        console.log(bbh);
        console.log(orgCode);
        data1=[{"id":"1", "text":"1"},{"id":"2", "text":"2"},{"id":"3", "text":"3"},{"id":"4", "text":"4"},{"id":"5", "text":"5"},
            {"id":"6", "text":"6"},{"id":"7", "text":"7"},{"id":"8", "text":"8"},{"id":"9", "text":"9"},{"id":"10", "text":"10"},
            {"id":"11", "text":"11"},{"id":"12", "text":"12"},{"id":"13", "text":"13"},{"id":"14", "text":"14"},{"id":"15", "text":"15"},
            {"id":"16", "text":"16"},{"id":"17", "text":"17"},{"id":"18", "text":"18"},{"id":"19", "text":"19"},{"id":"20", "text":"20"},
            {"id":"21", "text":"21"},{"id":"22", "text":"22"},{"id":"23", "text":"23"},{"id":"24", "text":"24"},{"id":"25", "text":"25"},
            {"id":"26", "text":"26"},{"id":"27", "text":"27"},{"id":"28", "text":"28"},{"id":"29", "text":"29"},{"id":"30", "text":"30"}
        ];
        if (bbh != null){
            if (orgCode != null){
                switch (bbh) {
                    case '用友GRP-U8行政事业内控管理软件(B版)v10.5':
                        $("#zt").combobox({
                            data:data1,
                            valueField: "id",
                            textField: "text",
                            panelHeight:"auto"
                        });
                        break;
                    case '用友GRP-U8行政事业内控管理软件(G版)v10.5':
                        $.ajax({
                            url: '/ky-datagather/area/queryZTH?hsdwdm=' + $("#orgCode").val(),
                            type: 'get',
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            //data: {"hsdwdm": $("#orgCode").val()},
                            beforeSend: function () {
                                $.messager.progress();
                            },
                            success: function (data) {
                                $.messager.progress('close');
                                console.log(data);
                                $("#zt").combobox({
                                    data: data,
                                    valueField: "ztbh",
                                    textField: "ztbh",
                                });
                            },
                        });
                        break;
                }
            }else{
                $.messager.show({
                    title: '提示',
                    msg: '请填写单位代码'
                })
            }
        }else{
            $.messager.show({
                title: '提示',
                msg: '请选择版本号'
            })
        }
    });
}
*/

function selectDwmc() {
    //单位名称下拉框
    $("#orgName2").combobox({
        url: '/ky-datagather/area/queryOrgname',
        method: 'get',
        valueField: 'dwmc',
        textField: 'dwmc',
        onHidePanel:function () {
            $("#orgCode2").combobox("setValue",' ');//清空单位代码
            $("#zt").combobox("setValue",' ');//清空账套号
            var dwmc = $('#orgName2').combobox('getValue');
            //alert(dwmc);
            $.ajax({
                url: '/ky-datagather/area/queryOrgCode?dwmc=' + dwmc,
                type:'post',
                cache:false,
                dataType:'json',
                success:function (data) {
                    console.log(data);
                    $("#orgCode2").combobox({
                      data:data,
                        valueField: 'dwdm',
                        textField: 'dwdm',
                        onHidePanel:function () {
                            $("#zt").combobox("setValue",' ');//清空账套号
                            var dwdm = $('#orgCode2').combobox('getValue');//获得单位代码
                            //alert(dwdm);
                            $.ajax({
                                url: '/ky-datagather/area/queryZTH?hsdwdm=' + dwdm,
                                type:'get',
                                cache:false,
                                dataType:'json',
                                success:function (org) {
                                    console.log(org);
                                    $("#zt").combobox({
                                        data: org,
                                        valueField: "ztbh",
                                        textField: "ztbh",
                                        onLoadSuccess : function(){
                                            $('#zt').combobox('setValue','-请选择账套号-');
                                        }
                                    });

                                }
                            })
                        }
                    })
                }
            })
        }
    })
}


var session;
function getSession() {
    $.ajax({
        url: '/ky-datagather/getSession',
        type:'get',
        success:function (sessioninfo) {
            console.log(sessioninfo.valueOf());
           session = sessioninfo.valueOf();
            if(session == 'G版'){
                $("#kfdwDiv").hide();
                $("#dwBox2").css("display","");
                $("#dwBox1").remove();
                selectDwmc();
            }else{
                $("#kfdwDiv").hide();
                $("#dwBox1").css("display","");
                $("#dwBox2").remove();
            }
        }
    })

}