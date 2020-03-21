// JavaScript Document
$(document).ready(function(){
    $(".module a>input").click(function(){
        if($(this).parent().parent().attr("class")=='module_a'){
            if($(this).attr("checked")=='checked'){
                var name=$(this).parent().attr("index");
                $(".module .module_b a").each(function(){
                    if($(this).attr("index").substr(0,1)==name){
                        $(this).css("display","block");
                    }
                })

            }else{
                var name=$(this).parent().attr("index");
                $(".module .module_b a,.module .module_c a").each(function(){
                    if($(this).attr("index").substr(0,1)==name){
                        $(this).css("display","none");
                        $(this).find("input").attr("checked",false);
                    }
                })
            }
        }
        if($(this).parent().parent().attr("class")=='module_b'){
            if($(this).attr("checked")=='checked'){
                var name=$(this).parent().attr("index");
                $(".module .module_c a").each(function(){
                    if($(this).attr("index")==name){
                        $(this).css("display","block");
                    }
                })

            }else{
                var name=$(this).parent().attr("index");
                $(".module .module_c a").each(function(){
                    if($(this).attr("index")==name){
                        $(this).css("display","none");
                        $(this).find("input").attr("checked",false);
                    }
                })
            }
        }
        if($(this).parent().parent().attr("class")=='module_c'){
            if($(this).attr("checked")=='checked'){
                var name=$(this).parent().attr("index");
                $(this).css("display","block");
            }else{
                $(this).parent().css("color","#333");
            }
        }
    });
    $(".module dl .module_a a").click(function(){
				 $(this).addClass("current").siblings().removeClass("current");			 
	})
	$(".module dl .module_b a").click(function(){
				 $(this).addClass("current").siblings().removeClass("current");			 
	})
	$(".module dl .module_c a").click(function(){
				 $(this).addClass("current").siblings().removeClass("current");			 
	})
	$(".module dl .module_d a").click(function(){
				 $(this).addClass("current").siblings().removeClass("current");			 
	})
	
	$(".module dl .module_a .mod-a").click(function(){
				 $(".mod-a1").show();
				 $(".mod-b1").hide();
				 $(".mod-c1").hide();
				 $(".mod-d1").hide();			 
	})	
	
	$(".module dl .module_a .mod-b").click(function(){
				 $(".mod-b1").show();
				 $(".mod-a1").hide();
				 $(".mod-c1").hide();
				 $(".mod-d1").hide();			 
	})		
	
	$(".module dl .module_a .mod-c").click(function(){
				 $(".mod-c1").show();
				 $(".mod-a1").hide();
				 $(".mod-b1").hide();
				 $(".mod-d1").hide();			 
	})		
	
	$(".module dl .module_a .mod-d").click(function(){
				 $(".mod-d1").show();
				 $(".mod-a1").hide();
				 $(".mod-b1").hide();
				 $(".mod-b1").hide();			 
	})
	
	
	
	
	$(".module dl .module_b .mod-a1").click(function(){
				 $(".mod-a2").show();
				 $(".mod-b2").hide();
				 $(".mod-c2").hide();
				 $(".mod-d2").hide();			 
	})	
	
	$(".module dl .module_b .mod-b1").click(function(){
				 $(".mod-b2").show();
				 $(".mod-a2").hide();
				 $(".mod-c2").hide();
				 $(".mod-d2").hide();			 
	})		
	
	$(".module dl .module_b .mod-c1").click(function(){
				 $(".mod-c2").show();
				 $(".mod-a2").hide();
				 $(".mod-b2").hide();
				 $(".mod-d2").hide();			 
	})		
	
	$(".module dl .module_b .mod-d1").click(function(){
				 $(".mod-d2").show();
				 $(".mod-a2").hide();
				 $(".mod-b2").hide();
				 $(".mod-C2").hide();			 
	})	 
});
