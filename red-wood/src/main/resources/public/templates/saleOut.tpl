<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>产品出库</title>
</head>
<body>
<div>
    <div>
        <label style="text-align: center;margin-left: 35%;font-size: 17px">临汾桂蒲木业销售出库单</label>
    </div>
    <div>
        <label>客户:</label><span><u>${customName}</u></span>
        <label style="margin-left:10%;">出货日期:</label><span><u>${sellDate}</u></span>
        <label style="margin-left:8%;">单据编号:</label><span><u>${invoiceId}</u></span>
    </div>
    <table style="border-collapse: collapse;border:1px solid #444">
        <thead style="text-align: center;border:1px solid #444">
        <tr style="text-align: center;border:1px solid #444">
            <th style="width: 120px;text-align: center;border:1px solid #444">名称</th>
            <th style="width: 100px;text-align: center;border:1px solid #444">规格</th>
            <th style="width: 100px;text-align: center;border:1px solid #444">型号</th>
            <th style="width: 80px;text-align: center;border:1px solid #444">单位</th>
            <th style="width: 80px;text-align: center;border:1px solid #444">数量</th>
            <th style="width: 80px;text-align: center;border:1px solid #444">单价</th>
            <th style="width: 100px;text-align: center;border:1px solid #444">金额</th>
        </tr>
        </thead>
        <tbody style="text-align: center;border:1px solid #444">
        <tr style="text-align: center;border:1px solid #444">
            <#list list as data>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.productName}</td>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.goodsSpecs}</td>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.goodsModel}</td>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.goodsUnit}</td>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.goodsNum}</td>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.unitPrice}</td>
            <td contenteditable="true" style="text-align: center;border:1px solid #444">${data.sumPrice}</td>
            </#list>
        </tr>
        </tbody>
    </table>
    <label>主管:</label><span><u>${manager}</u></span>
    <label style="margin-left: 5%;">会计:</label><span><u>${accountant}</u></span>
    <label style="margin-left: 5%;">保管员:</label><span><u>${curator}</u></span>
    <label style="margin-left: 5%;">经手人:</label><span><u>${operator}</u></span>
</div>
</body>
</html>