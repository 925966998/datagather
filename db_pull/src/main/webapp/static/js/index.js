// JavaScript Document
$(document).ready(function(){
	$("#M_more").hover(function(){
		$(this).find('.M_moreList').show();
	},function(){
		$(this).find('.M_moreList').hide();
	})
	
	$('#search1').click(function(){
		$(this).hide();
		$(this).parent().find('.searchCon').show();
	})
	
	$('.searchCon a').click(function(){
		var s = $(this).text();
		$('#search1').html(s + '<i></i>');
		$('.searchCon').hide();
		$('#search1').show();
		
	})
	var count = 0;
	$('#navPlus').click(function(){
		
		if(count == 0){
			$(this).parent().find('.navPlusListBox').fadeIn(300);
			count++;
		}else if(count == 1){
			$(this).parent().find('.navPlusListBox').fadeOut(300);
			count--;
		}		
	})
	
	$('#nav a').hover(function(){
		$('#nav').find('a').removeClass("cur");
		$(this).addClass("cur");
	})
	
})

