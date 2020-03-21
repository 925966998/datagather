layui.extend({
    admin: '{/}/layui/static/js/admin'
});

layui.use(['laydate', 'jquery', 'form', 'admin'], function () {
    laydate = layui.laydate,
        $ = layui.jquery,
        admin = layui.admin,
        form = layui.form;

    //执行一个laydate实例
    laydate.render({
        elem: '#startTime' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#endTime' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#returnedMoneyDate' //指定元素
    });

});

//将表单数据转为json
function form2Json(id) {
    var arr = $("#" + id).serializeArray();
    var jsonStr = "";

    jsonStr += '{';
    for (var i = 0; i < arr.length; i++) {
        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}';

    var json = JSON.parse(jsonStr)
    return JSON.stringify(json);
}

function skipPage(obj, module) {
    console.log($(obj).attr("data-url"));
    sessionStorage.setItem(module + "Id", $(obj).attr("data-id"));
    console.log(sessionStorage.getItem(module + "Id"));
    location.href = $(obj).attr("data-url");
}

function doDelete(obj, module) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            url: "/ky-datagather/" + module + "/deleteForce?id=" + $(obj).attr("data-id"),
            type: "GET",
            cache: false,
            success: function (returnData) {
                if (returnData.code == 10000) {
                    parent.layer.msg("操作成功", {time: 500}, function () {
                        if ("employeeAchievementDetail" == module){
                            location.reload();
                        }  else {
                            parent.location.reload();
                        }
                    });
                } else {
                    parent.layer.msg(returnData.data);
                }

            },
            error: function (request) {
                if (request.status == 401) {
                    parent.layer.msg("登陆失效,请重新登陆");
                    parent.location.href = "/web/login.html";
                }
            }
        });
    });
}

function doSubmit(module, form) {
    layer.confirm('确认要提交吗？', function (index) {
        $.ajax({
            url: "/ky-datagather/" + module + "/saveOrUpdate",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(form),
            success: function (returnData) {
                parent.layer.msg("操作成功", {time: 500}, function () {
                    parent.location.reload();
                });
            },
            error: function (request) {
                if (request.status == 401) {
                    parent.layer.msg("登陆失效,请重新登陆");
                    parent.location.href = "/web/login.html";
                }
            }
        });
        return false;
    });
}

function doQuery(pageNumber, module, formId, templateId) {
    $.ajax({
        url: "/ky-datagather/" + module + "/queryPage?currentPage=" + pageNumber + "&pageSize=10",
        type: "GET",
        data: $("#" + formId).serialize(),
        cache: false,
        success: function (returnData) {
            $("#tableTbody").html(template(templateId, {data: returnData.data.items}));
            $("#pager").pager({
                pagenumber: pageNumber,
                pagecount: returnData.data.pagesCount,
                totalcount: returnData.data.totalItemsCount,
                buttonClickCallback: PageClick
            });
        },
        error: function (request) {
            if (request.status == 401) {
                parent.layer.msg("登陆失效,请重新登陆");
                parent.location.href = "/web/login.html";
            }
        }
    });
    //回调函数
    PageClick = function (currentPage) {
        doQuery(currentPage,module,formId,templateId);
    }
}

function doGetById(id, module, formId, templateId) {
    $.ajax({
        url: "/ky-datagather/" + module + "/queryById?id=" + id,
        type: "GET",
        success: function (returnData) {
            sessionStorage.setItem("employeeAchievement",JSON.stringify(returnData.data));
            $("#" + formId).html(template(templateId, {data: returnData.data}));
            layui.form.render();
        },
        error: function (request) {
            if (request.status == 401) {
                parent.layer.msg("登陆失效,请重新登陆");
                parent.location.href = "/web/login.html";
            }
        }
    });
}

function getPercent(num, total) {
    /// <summary>
    /// 求百分比
    /// </summary>
    /// <param name="num">当前数</param>
    /// <param name="total">总数</param>
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    var res = total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00) + "%";
    return res.replace("%", "") / 100;
}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}