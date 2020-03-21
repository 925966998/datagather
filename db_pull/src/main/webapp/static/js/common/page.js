var pageIndex=1;
var totalPage=1;
function getJson(url,data,run){
	if(pageIndex>totalPage){
		return;
	}
  	$.ajax({
		type: "POST",
		url: url,
    	data: data,
		dataType:'json',
		cache: false,
		success: function(data){
			totalPage=data.totalPage;
			run(data);
			pageIndex++;
		},
		error:function(data){
			
		}
	});
}
function pageinit(run){
	run();
	$(function(){
		   var winH = $(window).height(); //页面可视区域高度  
	       $(window).scroll(function() {  
	           var pageH = $(document.body).height();  
	           var scrollT = $(window).scrollTop(); //滚动条top  
	           var aa = (pageH - winH - scrollT) / winH;  
	           if (aa < 0.02) {  
	        	  run();
	           }  
	       });  
	});
	
}
