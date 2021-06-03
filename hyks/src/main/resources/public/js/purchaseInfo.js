obj = {
    // 查询
    find: function () {
        doQuery('/ky-supplier/orderListInfo/queryPage?' + $("#tableFindForm").serialize())
    },
    can: function () {
        $("#addBox").dialog({
            closed: true
        })
    },
    // 提交表单
    // sum: function () {
    //     $('#addForm').form('submit', {
    //         onSubmit: function () {
    //             var lag = $(this).form('validate');
    //             if (lag == true) {
    //                 $.ajax({
    //                     url: '/ky-supplier/orderInfo/saveSupplier',
    //                     type: 'POST',
    //                     dataType: "json",
    //                     contentType: "application/json; charset=utf-8",
    //                     data: form2Json("addForm"),
    //                     success: function (data) {
    //                         console.log($("#id").val())
    //                         $("#table").datagrid('reload');
    //                         if ($("#id").val()) {
    //                             $.messager.show({
    //                                 title: '提示',
    //                                 msg: '修改成功'
    //                             })
    //                         } else {
    //                             $.messager.show({
    //                                 title: '提示',
    //                                 msg: '新增成功'
    //                             })
    //                         }
    //                     },
    //                     error: function (request) {
    //                         if (request.status == 401) {
    //                             $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
    //                                 if (r) {
    //                                     parent.location.href = "/login.html";
    //                                 }
    //                             });
    //                         }
    //                     }
    //                 })
    //             } else
    //                 return false;
    //         },
    //         success: function () {
    //             $.messager.progress('close');
    //             $("#addBox").dialog({
    //                 closed: true
    //             })
    //             $("#table").datagrid('reload')
    //         }
    //     });
    // },
    findTable: function () {
        // doQuery('/ky-supplier/orderInfo/queryPage?' + $("#tableFindForm").serialize())
        $("#table2").datagrid('load', {
            name: $("#name").val(),
            supprop: $("#supprop").val(),
            enablestate: $("#enablestate").val(),
            supstate: $("#supstate").val(),
        })
    },
    chooseSupplier: function (id) {
        $("#auditBox").dialog({
            closed: false
        });
        $("#table2").datagrid({
            title: "供应商列表",
            method: "get",
            iconCls: "icon-left02",
            url: '/ky-supplier/company/queryPage',
            fitColumns: false,
            striped: true,
            pagination: true,
            pageSize: 10,
            width: '100%',
            rownumbers: true,
            pageNumber: 1,
            nowrap: true,
            height: 'auto',
            sortName: 'pk_supplier',
            checkOnSelect: true,
            sortOrder: 'asc',
            toolbar: '#tabelBut1',
            columns: [[
                {
                    field: 'code',
                    title: '编码',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'legalbody',
                    title: '法人',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'name',
                    title: '名称',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'supprop',
                    title: '类型',
                    width: 100,
                    align: 'center',
                    formatter: function (val, row) {
                        switch (val) {
                            case 0:
                                return '外部单位';
                            case 1:
                                return '内部单位';
                        }
                        // if (val == 0) return
                        // else if (val == 1) return '内部单位'
                    }
                },
                {
                    field: 'memo',
                    title: '备注',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'buslicensenum',
                    title: '营业执照号码',
                    width: 100,
                    align: 'center',
                }, {
                    field: 'taxpayerid',
                    title: '纳税人登记号',
                    width: 100,
                    align: 'center',
                }, {
                    field: 'corpaddress',
                    title: '地址',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'enablestate',
                    title: '启用状态',
                    width: 100,
                    align: 'center',
                    formatter: function (val, row) {
                        if (val == 1) return '未启用'
                        else if (val == 2) return '已启用'
                        else if (val == 3) return '已停用'
                    }
                },
                {
                    field: 'supstate',
                    title: '供应商状态',
                    width: 100,
                    align: 'center',
                    formatter: function (val, row) {
                        if (val == 0) return '潜在'
                        else if (val == 1) return '核准'
                    }
                },
                {
                    field: 'mnecode',
                    title: '助记码',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'pk_supplierclass',
                    title: '供应商基本分类',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'pk_suptaxes',
                    title: '供应商税类',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'cellNum',
                    title: '电话',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'tellPhone',
                    title: '手机号',
                    width: 100,
                    align: 'center',
                },
                {
                    field: 'cellName',
                    title: '联系人',
                    width: 100,
                    align: 'center',
                },
            ]],
            onClickRow: function (rowIndex, rowData) {
                var rows1 = $("#table").datagrid("getSelections");
                // if (rows1.length > 1) {
                //     $.messager.alert('提示', '每次选择一条记录', 'info');
                // }
            }
        })

        // $("#addBox").dialog({
        //     closed: false
        // });
        // // id = $("#table").datagrid('getSelected').id;
        $('#id').val(id)
        // $.ajax({
        //     url: '/ky-supplier/orderInfo/queryById',
        //     type: 'get',
        //     dataType: 'json',
        //     data: {id: id},
        //     success: function (res) {
        //         console.log(res)
        //         $('#id').val(id)
        //         if (res.data != null) {
        //             $('#addForm').form('load', {
        //                 id: id,
        //                 matterName: res.data.matterName,
        //                 nastNum: res.data.nastNum,
        //             })
        //             querySupplier();
        //         } else {
        //             $.messager.show({
        //                 title: '提示',
        //                 msg: '更新失败'
        //             })
        //         }
        //     },
        //     error: function (request) {
        //         if (request.status == 401) {
        //             $.messager.confirm('登录失效', '您的身份信息已过期请重新登录', function (r) {
        //                 if (r) {
        //                     parent.location.href = "/login.html";
        //                 }
        //             });
        //         }
        //     }
        // })
    },
    sum: function () {
        var orderInfoId = $("#id").val();
        var rows1 = $("#table2").datagrid("getSelections");
        console.log(rows1)
        if (rows1.length > 0) {
            $.messager.confirm('确定提交', '你确定要提交你选择的记录吗？', function (flg) {
                if (flg) {
                    var ids = [];
                    for (i = 0; i < rows1.length; i++) {
                        ids.push(rows1[i].pk_supplier);
                    }
                    var num = ids.length;
                    $.ajax({
                        type: 'get',
                        url: "/ky-supplier/companyOrder/saveCompany",
                        data: {
                            id: ids.join(','),
                            orderInfoId: $("#id").val()
                        },
                        beforesend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            if (data.code = '10000') {
                                $("#table").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个记录提交'
                                })
                            } else {
                                $.messager.show({
                                    title: '警示信息',
                                    msg: "信息提交失败"
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
                            } else {
                                $.messager.show({
                                    title: '提示',
                                    msg: '信息提交失败'
                                })
                            }
                        }
                    })
                }
            })
        } else {
            $.messager.alert('提示', '请选择要提交的记录', 'info');
        }
    },
    del: function () {
        var rows = $("#table").datagrid("getSelections");
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
                        url: "/ky-supplier/orderListInfo/deleteOrderInfo",
                        data: {
                            id: ids.join(',')
                        },
                        beforesend: function () {
                            $("#table").datagrid('loading');
                        },
                        success: function (data) {
                            if (data.code = '10000') {
                                $("#table").datagrid('reload');
                                $.messager.show({
                                    title: '提示',
                                    msg: num + '个记录被删除'
                                })
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
                            } else {
                                $.messager.show({
                                    title: '提示',
                                    msg: '信息删除失败'
                                })
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


$(function () {
    doQuery('/ky-supplier/orderListInfo/queryPage');
})

function doQuery(url) {
    $("#table").edatagrid({
        title: "公司列表",
        iconCls: "icon-left02",
        url: url,
        queryParams: {orderListId: getUrlParam('orderId')},
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
                field: 'orderListName',
                title: '询价单名称',
                width: 75,
                align: 'center',
            },
            {
                field: 'matterName',
                title: '物料名称',
                width: 75,
                align: 'center',
            },
            {
                field: 'matterSpec',
                title: '规格',
                width: 75,
                align: 'center',
            },
            {
                field: 'nastNum',
                title: '数量',
                width: 75,
                align: 'center',
            },
            {
                field: 'matterType',
                title: '单位',
                width: 75,
                align: 'center',
            },
            {
                field: 'opr',
                title: '操作',
                width: 100,
                align: 'center',
                formatter: function (val, row) {
                    e = '<a  id="add" data-id="98" class=" operA"  onclick="obj.chooseSupplier(\'' + row.orderInfoId + '\')">选择供应商</a> ';
                    // a = '<a  id="look"   class=" operA" class="easyui-linkbutton"  href="../web/orderSupplier.html?orderId=' + row.orderInfoId + '">供应商列表</a> ';
                    return e;
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
$("#auditBox").dialog({
    title: "信息内容",
    width: 800,
    height: 500,
    closed: true,
    modal: true,
    shadow: true
})

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
