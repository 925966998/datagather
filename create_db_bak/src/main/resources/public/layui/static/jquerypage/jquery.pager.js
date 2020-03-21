(function($) {   
	$.fn.pager = function(options) {
		var opts = $.extend({}, $.fn.pager.defaults, options);  
		return this.each(function() {       // empty out the destination element and then render out the pager with the supplied options    
			$(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), options.buttonClickCallback));                        // specify correct cursor activity
			$('.paging a').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto";});  
		});
	};    // render and return the pager with the supplied options
	
	function renderpager(pagenumber, pagecount, buttonClickCallback) {        // setup $pager to hold render     
		var $pager = $('<ul class="paging"></ul>');        // add in the previous and next buttons 
		$pager.append(renderButton('首页', pagenumber, pagecount, buttonClickCallback)).append(renderButton('上一页', pagenumber, pagecount, buttonClickCallback));        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases     
		var startPoint = 1; 
		var endPoint = 5;
		var thpoint="<span>...</span>";
		if (pagenumber > 2) {
			startPoint = pagenumber - 2;
			endPoint = pagenumber + 2; 
		}
		if (endPoint > pagecount) { 
			startPoint = pagecount - 5;
			endPoint = pagecount;
			thpoint = "";
		}
		if (startPoint < 1) {
			startPoint = 1;
		}        // loop thru visible pages and render buttons
		for (var page = startPoint; page <= endPoint; page++) {
			var currentButton = $('<a class="page-number">' + (page) + '</a>');
			page == pagenumber ? currentButton.addClass('pgCurrent') : currentButton.click(function() {
				buttonClickCallback(this.firstChild.data);
			});
			currentButton.appendTo($pager);
		}        // render in the next and last buttons before returning the whole rendered control back.
		$pager.append(thpoint).append(renderButton('下一页', pagenumber, pagecount, buttonClickCallback)).append(renderButton('末页', pagenumber, pagecount, buttonClickCallback));
		$pager.append("<a class='thpoint' style='margin-top:3px;'>共: "+pagecount+"页</a>");
		var strgoto = $("<a class='thpoint'>当前第<input class='input-text' type='text' value='"+pagenumber+"'maxlength='6' id='gotoval' style='width:20px; height:20px;line-height:20px;margin:-3px 5px 0px;text-align:center '/>页</a>");
		$pager.append(strgoto);
		$pager.append(changepage('确定',pagecount,buttonClickCallback));
		return $pager;
}    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button

function changepage(buttonLabel,pagecount,buttonClickCallback){
	var $btngoto = $('<a class="pgNext">'+ buttonLabel+'</a>');
	$btngoto.click(function() {
		var gotoval = $('#gotoval').val();
		var patrn = /^[1-9]\d*$/;
		if (!patrn.exec(gotoval)){
			alert("请输入非零的正整数！");
			return false;
		}
		var intval = parseInt(gotoval);
		if(intval > pagecount){
			alert("您输入的页面超过总页数 "+pagecount);
			return ;
		}
		buttonClickCallback(intval);
	});
	return $btngoto;
}

function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback) {     
	var $Button = $('<a class="pgNext">' + buttonLabel + '</a>');   
	var destPage = 1;        // work out destination page for required button type   
	switch (buttonLabel) {
		case "首页":
			destPage = 1;
			break;
		case "上一页":   
			destPage = pagenumber - 1;
			break;
		case "下一页":
			destPage = pagenumber + 1;          
		break;
		case "末页":
			destPage = pagecount;        
		break;     
	}        // disable and 'grey' out buttons if not needed.       
	if (buttonLabel == "首页" || buttonLabel == "上一页") {     
		pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });     
	}       
	else {     
		pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); }); 
	}     
	return $Button;  
 }    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version

 $.fn.pager.defaults = {   
	 pagenumber: 1,     
	 pagecount: 1};
 })(jQuery);