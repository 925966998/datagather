<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="static/css/default.css" />
    
    <!-- 表图 -->
    <link rel="stylesheet" type="text/css" href="static/tubiao/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="static/tubiao/css/default.css">
	<style type="text/css">
		#canvas-holder {
        width: 100%;
        margin-top: 50px;
        text-align: center;
	    }
	    #chartjs-tooltip {
	        opacity: 1;
	        position: absolute;
	        background: rgba(0, 0, 0, .7);
	        color: white;
	        padding: 3px;
	        border-radius: 3px;
	        -webkit-transition: all .1s ease;
	        transition: all .1s ease;
	        pointer-events: none;
	        -webkit-transform: translate(-50%, 0);
	        transform: translate(-50%, 0);
	    }
	    #chartjs-tooltip.below {
	        -webkit-transform: translate(-50%, 0);
	        transform: translate(-50%, 0);
	    }
	    #chartjs-tooltip.below:before {
	        border: solid;
	        border-color: #111 transparent;
	        border-color: rgba(0, 0, 0, .8) transparent;
	        border-width: 0 8px 8px 8px;
	        bottom: 1em;
	        content: "";
	        display: block;
	        left: 50%;
	        position: absolute;
	        z-index: 99;
	        -webkit-transform: translate(-50%, -100%);
	        transform: translate(-50%, -100%);
	    }
	    #chartjs-tooltip.above {
	        -webkit-transform: translate(-50%, -100%);
	        transform: translate(-50%, -100%);
	    }
	    #chartjs-tooltip.above:before {
	        border: solid;
	        border-color: #111 transparent;
	        border-color: rgba(0, 0, 0, .8) transparent;
	        border-width: 8px 8px 0 8px;
	        bottom: 1em;
	        content: "";
	        display: block;
	        left: 50%;
	        top: 100%;
	        position: absolute;
	        z-index: 99;
	        -webkit-transform: translate(-50%, 0);
	        transform: translate(-50%, 0);
	    }
	    span{
	    	font-size:15px;
	    }
	</style>
	<script src="static/tubiao/js/Chart.js"></script>
	<!-- <script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script> -->
	<script src="static/js/jquery-1.9.1.min.js"></script>
	
	

    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <!-- jsp文件头和头部 -->
    <%@ include file="top.jsp"%>
</head>

<script type="text/javascript">
    $(top.hangge());
</script>

<body>
<div class="critic-top">
    <h1 style="font-size:16px; color:#2c6aa0;">—— 统计列表——</h1>
    <ul class="critic_cnn">
                            <li>
                                <a style="cursor:pointer;">
                                    <dl>
                                        <dt>今日新增会员数</dt>
                                        <dd>${accountCount}</dd>
                                    </dl>
                                </a>
                             </li>    
                            <li>
                                <a style="cursor:pointer;">
                                    <dl>
                                        <dt>今日新增订单数</dt>
                                        <dd>${orderCount}</dd>
                                    </dl>
                                </a>
                             </li>   
                            <li>
                                <a style="cursor:pointer;">
                                    <dl>
                                        <dt>今日商品销售数</dt>
                                        <dd>${sellCount}</dd>
                                    </dl>
                                </a>
                             </li>   
                            <li>
                                <a style="cursor:pointer;">
                                    <dl>
                                        <dt>今日订单总金额</dt>
                                        <dd>${incomeCount}</dd>
                                    </dl>
                                </a>
                             </li>            
    </ul>
<div class="clearfix"></div>
</div>


<!-- 图表 -->
<form action="login_default" id="from1" method="post">

<div style="width: 700px;height: 600px; float: left; margin-left: 25px;">
<h1 style="font-size:16px; color:#2c6aa0;">—— 支付方法统计 ——</h1>

<span style="float:left; display: block;width: 15px;height: 15px; background-color: #F7464A; margin-left: 10px;"></span>
<span style="float:left;margin-left: 5px;">支付宝:<fmt:formatNumber type="number" value="${zfbTotal }"></fmt:formatNumber> </span>

<span style="float:left; display: block;width: 15px;height: 15px; background-color: #46BFBD; margin-left: 10px;"></span>
<span style="float:left;margin-left: 5px;">微信:<fmt:formatNumber type="number" value="${wxTotal }"></fmt:formatNumber> </span>

<span style="float:left; display: block;width: 15px;height: 15px; background-color: #FDB45C; margin-left: 10px;"></span>
<span style="float:left;margin-left: 5px;">银联:<fmt:formatNumber type="number" value="${ylTotal }"></fmt:formatNumber> </span>

<span style="float:left; display: block;width: 15px;height: 15px; background-color: #4D5360; margin-left: 10px;"></span>
<span style="float:left;margin-left: 5px;">线下:<fmt:formatNumber type="number" value="${xxTotal }"></fmt:formatNumber> </span>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>合计:<fmt:formatNumber type="number" value="${total }"></fmt:formatNumber> </span>
<div class="htmleaf-content" style="width: 400px;">
<select name="sel" id="sel" onchange="selaa();">
    <option value="1" <c:if test="${sel==1}">selected</c:if>>所有</option>
    <option value="2" <c:if test="${sel==2}">selected</c:if>>当天</option>
    <option value="3" <c:if test="${sel==3}">selected</c:if>>一个月内</option>
</select>
    <div id="lala">
			<div id="canvas-holder">
		        <canvas id="chart-area1" width="300" height="300" />
		    </div>
		    <div id="chartjs-tooltip"></div>
		</div>
	</div>
</div>
</form>
<br>
<div class="critic-top" style="float: left;">
<h1 style="font-size:16px; color:#2c6aa0;">—— 快速导航——</h1>
    <ul class="critic-center">
        <c:forEach items="${menuList}" var="menu" varStatus="vs">
            <c:if test="${menu.hasMenu}">

                <c:forEach items="${menu.subMenu}" var="sub" >
                    <c:choose>
                        <c:when test="${not empty sub.MENU_URL}">
                            <li>
                                <a style="cursor:pointer;" target="mainFrame"  onclick="top.parent.siMenu('z${sub.MENU_ID }','lm${menu.MENU_ID }','${sub.MENU_NAME }','${sub.MENU_URL }')">
                                    <dl class="critic-left rent-${vs.index+1}">
                                        <dt><i  style="margin-top: 15px; font-size: 32px; color: rgb(245, 245, 245);" class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}"></i></dt>
                                        <dd>${sub.MENU_NAME }</dd>
                                    </dl>
                                </a></li>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </c:if>
        </c:forEach>
    </ul><div class="clearfix"></div>
</div>

</body>
<script type="text/javascript">
	Chart.defaults.global.customTooltips = function(tooltip) {

    	// Tooltip Element
        var tooltipEl = $('#chartjs-tooltip');

        // Hide if no tooltip
        if (!tooltip) {
            tooltipEl.css({
                opacity: 0
            });
            return;
        }

        // Set caret Position
        tooltipEl.removeClass('above below');
        tooltipEl.addClass(tooltip.yAlign);

        // Set Text
        tooltipEl.html(tooltip.text);

        // Find Y Location on page
        var top;
        if (tooltip.yAlign == 'above') {
            top = tooltip.y - tooltip.caretHeight - tooltip.caretPadding;
        } else {
            top = tooltip.y + tooltip.caretHeight + tooltip.caretPadding;
        }

        // Display, position, and set styles for font
        tooltipEl.css({
            opacity: 1,
            left: tooltip.chart.canvas.offsetLeft + tooltip.x + 'px',
            top: tooltip.chart.canvas.offsetTop + top + 'px',
            fontFamily: tooltip.fontFamily,
            fontSize: tooltip.fontSize,
            fontStyle: tooltip.fontStyle,
        });
    };

    var pieData = [{
        value: '${zfbTotal }',
        color: "#F7464A",
        highlight: "#FF5A5E",
        label: "支付宝 "
    }, {
        value: '${wxTotal }',
        color: "#46BFBD",
        highlight: "#5AD3D1",
        label: "微信"
    }, {
        value: '${ylTotal }',
        color: "#FDB45C",
        highlight: "#FFC870",
        label: "银联"
    }, {
        value: '${xxTotal }',
        color: "#4D5360",
        highlight: "#616774",
        label: "线下"
    }];

    window.onload = function() {
        var ctx1 = document.getElementById("chart-area1").getContext("2d");
        window.myPie = new Chart(ctx1).Pie(pieData);

        
    };

    function selaa(){
    	
    	$("#from1").submit();
    	
    }
    
    /* window.onload=function(){
    	var zfb='${zfbTotal }';
    	var wx='${wxTotal }';
    	var yl='${ylTotal }';
    	var xx='${xxTotal }';
    	if(zfb==0.0&&wx==0.0&&yl==0.0&&xx==0.0){
    		alert("asd");
    		document.getElementById("lala").style.display="none";
    	}
    } */

	</script>
</html>

