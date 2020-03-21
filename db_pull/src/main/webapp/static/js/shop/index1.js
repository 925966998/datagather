// JavaScript Document
$(function(){
    //右侧导航
    $(".gps_b a").hover(function(){
        $(this).find(".box").css("backgroundColor","#fb4b4b").stop().delay(200).animate({"width":60},300).parent().siblings().find(".box").css("backgroundColor","#000000").stop().animate({"width":0},300);
    },function(){
        $(".gps_b a .box").css("backgroundColor","#000000").stop().animate({"width":0},300);
    })
	 $(".treasure-top li").mouseover(function(){
				 $(this).addClass("current")
			       .siblings().removeClass("current");	
				    var num=$(this).index();
      $(".di-ty .di-left").eq(num).show().siblings().hide();
							 
	 })	 
	 
	 $(".de-t li").mouseover(function(){
				 $(this).addClass("current")
			       .siblings().removeClass("current");	
				    var num=$(this).index();
      $(".one-top .one-topleft").eq(num).show().siblings().hide();
							 
	 })	 
	 
	 $(".Stock-top a").click(function(){
            var name=$(this).attr("class");
            var num=Number($(this).parent().find("i").html());
            if(name=='plus'){
                num=num+1;
            }else{
                num=num-1;
            }
            if(num<1){
                num=1;
            }
            $(this).parent().find("i").html(num);

        });
});