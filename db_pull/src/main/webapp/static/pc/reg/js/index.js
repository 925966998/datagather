// JavaScript Document
$(function(){  
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

        })   
})