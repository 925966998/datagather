$(function(){

	$('.menu ul li ul li').click(function(e) {
        $(this).addClass('current').siblings().removeClass('current');
		$(this).parent().parent().siblings().find('li').removeClass('current');
    });
	
})
	