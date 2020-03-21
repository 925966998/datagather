<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
    <title>选择业务单元</title>
    <script>
        $(function () {

            //加载列表
            loadTable();


            var o = new Object();
            $("#mytable").ajaxgridLoad();
        });


        function loadTable() {
            var t = $("#mytable").ajaxgrid({
                colModel: [{
                    display: 'id',
                    name: 'MATERIAL_CODE',
                    width: '10%',
                    sortable: false,
                    align: 'center',
                    type: 'checkbox'
                }, {
                    display: '物料基本分类名称',
                    name: 'MARBASCLASS_NAME',
                    width: '10%',
                    sortable: true,
                    align: 'left'
                }, {
                    display: '物料名称',
                    name: 'MATERIAL_NAME',
                    width: '20%',
                    sortable: true,
                    align: 'left'
                }, {
                    display: '规格型号',
                    name: 'MATERIAL_SPEC',
                    width: '20%',
                    sortable: true,
                    align: 'left'
                }, {
                    display: '主计量单位名称',
                    name: 'MEASDOC_NAME',
                    width: '10%',
                    sortable: true,
                    align: 'left'
                }, {
                    display: '更新时间',
                    name: 'MATERIAL_TS',
                    width: '20%',
                    sortable: true,
                    align: 'left'
                }],
                width: '100%',
                height: 520,
                managerName: "formPlugFieldDesignManager",
                managerMethod: "getMateriels"
            });
        }


        function OK() {
            var v = $("#mytable").formobj({
                gridFilter: function (data, row) {
                    return $("input:checkbox", row)[0].checked;
                }
            });

            if (v.length < 1) {
                $.alert("请选择需要的数据！");
                return;
            } else if (v.length > 1) {
                $.alert("请选择一条需要的数据！");
                return;
            } else {
                var json = '${map}';
                if (json != "") {
                    var param = JSON.parse(json);
                    var nodes = window.dialogArguments.fillField.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("input");
                    for (var i = 0; i < nodes.length; i++) {
                        for (var key in param) {
                            if (nodes[i].id == param[key]) {
                                nodes[i].value = v[0][key];
                            }
                        }
                    }
                }
                return {dataValue: v[0]["MATERIAL_NAME"]};
            }
        }

        function search() {
            var option = $("#select  option:selected").val();
            var value = $("#value").val();
            var o = new Object();
            if (value != "") {
                if (option == "name")
                    o.name = value;
                if (option == "code")
                    o.code = value;
                if (option == "spec")
                    o.spec = value;

            }
            $("#mytable").ajaxgridLoad(o);
        }
    </script>
</head>
<body class="padding5">
<div id="toolbars"></div>
<div class="seeyon-portal-body" style="font-size:12px">
    <div>
        <table width="100%" border="0" cellspacing="8" cellpadding="0" align="center">
            <tr>
                <td width="25%" align="right"><label class="margin_r_10" for="text"><select id="select">
                    <option value="name" selected>物料名称</option>
                    <option value="code">物料编码</option>
                    <option value="spec">规格型号</option>
                </select></label>
                </td>
                <td width="10%">
                    <input id="value" type="text" value="">
                </td>
                <td align="left" width="10%">
                    <a id="btnsearch" class="common_button common_button_gray"
                       href="javascript:search()">${ctp:i18n('common.button.condition.search.label')}</a></td>
                <td>
            </tr>
        </table>
    </div>
    <div class="list">
        <div class="button_box clearfix">
            <table id="mytable" style="display: none"></table>
        </div>
    </div>
</div>
</body>
</html>