var Util = new Object();

Util._alertSetting={title:"提示",content:"",button:"确定",cancel:"取消",callback:null,cancelback:null};

Util.Alert = function(setting) {
	var s= $.extend({},Util._alertSetting,setting);
	var d = $('<div id="alertModel" class="gray"><div class="tishi"><div class="ts_top">'
			+ s.title
			+ '</div>'
			+ '<div class="ts_main">'
			+ s.content
			+ '</div><a class="confirm" href="javascript:;">'
			+ s.button
			+ '</a></div></div>');
	$('.confirm', d).click(function() {
		if (s.callback) {
		s.callback();
		}
		d.remove();
	});
	$('body').append(d);
	d.show();
};

Util.Confirm=function(setting){
	var s= $.extend({},Util._alertSetting,setting);
	var d = $('<div id="alertModel" class="gray"><div class="tishi"><div class="ts_top">'
			+ s.title
			+ '</div>'
			+ '<div class="ts_main">'
			+ s.content
			+ '</div><a class="confirm" href="javascript:;">'
			+ s.button
			+ '</a> <a class="close" href="javascript:;">'
			+ s.cancel
			+ '</a></div></div>');
	
		$('.confirm', d).click(function() {
			if (s.callback) {
			s.callback();
			}
			d.remove();
		});
		$('.close', d).click(function() {
			if (s.cancelback) {
				s.cancelback();
				}
				d.remove();
		});
	$('body').append(d);
	d.show();
};

Util.AlertWaitting=function()
{
	if($("#alertWaitting").length>0){
		$("#alertWaitting").show();
	}
	else{
		$("body").append($('<div id="alertWaitting"><div  class="bg-grey"></div><div class="loading"><div class="jz-logo"><img src="./static/images/systemlogo.png" width="100%" height="100%"></div><div class="spinner"><div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div></div></div></div>'));
		}
	};
Util.CloseWaitting=function(){
	$("#alertWaitting").hide();
};

Date.prototype.format=function(fmt) {        
    var o = {        
    "M+" : this.getMonth()+1, //月份        
    "d+" : this.getDate(), //日        
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
    "H+" : this.getHours(), //小时        
    "m+" : this.getMinutes(), //分        
    "s+" : this.getSeconds(), //秒        
    "q+" : Math.floor((this.getMonth()+3)/3), //季度        
    "S" : this.getMilliseconds() //毫秒        
    };        
    var week = {        
    "0" : "\u65e5",        
    "1" : "\u4e00",        
    "2" : "\u4e8c",        
    "3" : "\u4e09",        
    "4" : "\u56db",        
    "5" : "\u4e94",        
    "6" : "\u516d"       
    };        
    if(/(y+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
    }        
    if(/(E+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
    }        
    for(var k in o){        
        if(new RegExp("("+ k +")").test(fmt)){        
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
        }        
    }        
    return fmt;        
};