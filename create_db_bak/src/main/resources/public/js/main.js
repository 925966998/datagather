$("#mainBox").layout({
    fit: true,
    border: false
})
$("#mean").menu('show', {
    left: 200,
    top: 100
})
$("#left01").accordion({
    border: false

})
$("#con").tabs({
    fit: true,
    border: false
})
$("#myMes").dialog({
    title: "个人信息详情",
    width: 400,
    height: 420,
    modal: true,
    iconCls: 'icon-mes',
    maximizable: true,
    closed: true

})


function loginOut() {
    $.messager.confirm('退出确认', '你是否退出系统？', function (r) {
        if (r) {
            $.ajax({
                url: "/ky-datagather/loginOut",
                type: "POST",
                success: function (returnData) {
                    window.location.href = "login.html";
                },
                error: function (request) {
                    window.location.href = "login.html";
                }
            });
        }
    })

}


$(".topText a").click(function () {
    $(this).addClass('textActive').siblings().removeClass('textActive');

})
$(function () {

    $("#mb").text(sessionStorage.getItem("userName"))
});

function targetFrame(obj) {
    var menuC = $(".menuC");
    for (var i = 0; i < menuC.length; i++) {
        $(menuC).removeAttr("style");
    }
    $(obj).parent("p").attr("style", "background-color:rgba(188, 218, 233, 0.93)");
    var testVal = $(obj).text();
    var thisUrl = $(obj).attr('href');
    console.log(testVal, thisUrl);
    var con = '<iframe scrolling="no" frameborder="0"  src="' + thisUrl + '" style="width:100%;height:100%;">';
    if ($('#con').tabs('exists', testVal)) {
        $('#con').tabs('select', testVal);
        var currTab = $('#con').tabs('getSelected');
        /* var url = $(currTab.panel('options').content).attr('src');
         $('#con').tabs('update', {
             tab: currTab,
             options: {
                 href: url
             }
         });
         currTab.panel('refresh');*/
        currTab.panel('open').panel('refresh', url);
    } else {
        $('#con').tabs('add', {
            title: testVal,
            selected: true,
            closable: true,
            cache: false,
            fit: true,
            content: con
        });
    }
}

function closeAllTabs() {
    var tabs = $('#con').tabs('tabs');
    console.log(tabs)
    console.log(tabs.length);
    for (var i = 1; i < tabs.length; i++) {
        var index = $('#con').tabs('getTabIndex', tabs[i]);
        if (index != 0)
            $('#con').tabs('close', index);
    }
}

$("#left01 a").click(function () {
    var testVal = $(this).text();
    var thisUrl = $(this).attr('href');
    console.log(testVal, thisUrl);
    var con = '<iframe scrolling="no" frameborder="0"  src="' + thisUrl + '" style="width:100%;height:100%;">';
    if ($('#con').tabs('exists', testVal)) {
        $('#con').tabs('select', testVal);
        var tab = $('#con').tabs('getSelected');
        if (testVal != '行政区域设置') {
            $('#con').tabs('update', {
                tab: tab,
                options: {
                    content: con
                }
            });
        }
    } else {
        $('#con').tabs('add', {
            title: testVal,
            selected: true,
            closable: true,
            fit: true,
            cache: false,
            pill: true,
            content: con
        });
    }
})
$("#con").tabs({
    onSelect: function (tit, ind) {
        if (ind == 0) {
            $("#ifDiv").attr('src', "/web/areaManage.html");
        }
        if (tit == '数据采集'){
            var con = '<iframe scrolling="no" frameborder="0"  src="/web/dataPull.html" style="width:100%;height:100%;">';
            var tab = $('#con').tabs('getSelected');
            $('#con').tabs('update', {
                tab: tab,
                options: {
                    content: con
                }
            });
        }
    }
})
