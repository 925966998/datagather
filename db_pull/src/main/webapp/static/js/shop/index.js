/**
 * Created by Administrator on 2016/8/15.
 */
$(document).ready(function(){
    var swiper = new Swiper('.banner .swiper-container', {
        pagination: '.banner .swiper-pagination',
        nextButton: '.banner .swiper-button-next',
        prevButton: '.banner .swiper-button-prev',
        slidesPerView: 1,
        paginationClickable: true,
        spaceBetween: 0,
        loop: true,
        autoplay:5000,
        speed:800,
        autoplayDisableOnInteraction : false,
        onlyExternal : true
    });
    $('.banner .swiper-slide').hover(function(){
        swiper.stopAutoplay();
    },function(){
        swiper.startAutoplay();
    })
    var swiper1 = new Swiper('.scroll .module_a .swiper-container', {
        pagination: '.scroll .module_a .swiper-pagination',
        nextButton: '.scroll .module_a .swiper-button-next',
        prevButton: '.scroll .module_a .swiper-button-prev',
        slidesPerView: 4,
        slidesPerGroup : 4,
        paginationClickable: true,
        spaceBetween: 13,
        loop: true,
        //autoplay:5000,
        speed:1000,
        //autoplayDisableOnInteraction : false,
        onlyExternal : true
    });
    function swiper2(){
        var swiper2 = new Swiper('.scroll .module_c .swiper-container', {
            pagination: '.scroll .module_c .swiper-pagination',
            nextButton: '.scroll .module_c .swiper-button-next',
            prevButton: '.scroll .module_c .swiper-button-prev',
            obsever:true,
            slidesPerView: 4,
            slidesPerGroup : 4,
            paginationClickable: true,
            spaceBetween: 13,
            loop: true,
            //autoplay:5000,
            speed:1000,
            //autoplayDisableOnInteraction : false,
            onlyExternal : true
        });
    }
    swiper2();

    var swiper3 = new Swiper('.scroll .module_d .swiper-container', {
        pagination: '.scroll .module_d .swiper-pagination',
        nextButton: '.scroll .module_d .swiper-button-next',
        prevButton: '.scroll .module_d .swiper-button-prev',
        slidesPerView: 4,
        slidesPerGroup : 4,
        paginationClickable: true,
        spaceBetween: 13,
        loop: true,
        //autoplay:5000,
        speed:1000,
        //autoplayDisableOnInteraction : false,
        onlyExternal : true
    });
    var swiper4 = new Swiper('.scroll .module_g .swiper-container', {
        pagination: '.scroll .module_g .swiper-pagination',
        nextButton: '.scroll .module_g .swiper-button-next',
        prevButton: '.scroll .module_g .swiper-button-prev',
        slidesPerView: 6,
        slidesPerGroup : 6,
        paginationClickable: true,
        spaceBetween: 13,
        loop: true,
        //autoplay:5000,
        speed:1000,
        //autoplayDisableOnInteraction : false,
        onlyExternal : true
    });
    var swiper5 = new Swiper('.scroll .module_h .swiper-container', {
        pagination: '.scroll .module_h .swiper-pagination',
        nextButton: '.scroll .module_h .swiper-button-next',
        prevButton: '.scroll .module_h .swiper-button-prev',
        slidesPerView: 6,
        slidesPerGroup : 6,
        paginationClickable: true,
        spaceBetween: 13,
        loop: true,
        //autoplay:5000,
        speed:1000,
        //autoplayDisableOnInteraction : false,
        onlyExternal : true
    });
    $('.subnav').on('mouseover','.box',function(){
        $(this).find('.list').show().parent().siblings().find('.list').hide();
    })
    $('.subnav').on('mouseout','.box',function(){
        $(this).find('.list').hide();
    })

    //秒杀切换
    $("#seckill a").click(function(){
        var index=$(this).index();
        $(this).addClass('cur').siblings().removeClass('cur');
        $(this).parents('.scroll').find(".module_c").hide();
        $(this).parents('.scroll').find(".module_c").eq(index).show();
        swiper2();
    })

    //楼层滚动
    $(".gps_a a").click(function(){
        var name =$(this).parent().attr("class");
        var index=$(this).index();
        $(this).addClass("cur").siblings().removeClass("cur");
        var t =$(".floor").eq(index).offset().top;
        $("body,html").animate({"scrollTop":t},500);
    });

    function sum(){
        var winH=$(window).height();
        var iTop=$(window).scrollTop();

        if(iTop>$(".floor:first").offset().top-20 &&iTop<$(".floor:last").offset().top+20) {
            $(".gps_a").show();
            console.log(iTop-$(".floor:last").offset().top);
        }else{
            $(".gps_a").hide();
        }
        $(".floor").each(function () {
            var oDivHeight=$(this).offset().top;
            if (winH + iTop - $(this).offset().top > winH / 2) {
                $(".gps_a a").eq($(this).index(".floor")).addClass('cur').siblings().removeClass("cur");
            }
        });
    }

    $(window).scroll(function(){
        sum();
    });
    $(window).resize(function(){
        gps_a();
    });
    function gps_a(){
        var wid=($(window).width()-1200)/2-80;
        $(".gps_a").css({"left":wid});
    }
    gps_a();

    //右侧导航
    $(".gps_b a").hover(function(){
        $(this).find(".box").css("backgroundColor","#fb4b4b").stop().delay(200).animate({"width":60},300).parent().siblings().find(".box").css("backgroundColor","#000000").stop().animate({"width":0},300);
    },function(){
        $(".gps_b a .box").css("backgroundColor","#000000").stop().animate({"width":0},300);
    })

    //图片位移
    $(".module_f>.container>.sideBar_b>a>.pic").hover(function(){
        $(this).stop().animate({"marginLeft":-20},300);
    },function(){
        $(this).stop().animate({"marginLeft":0},300);
    })
    $(".module_f>.container>.sideBar_c ul li>a>span").hover(function(){
        $(this).stop().animate({"marginRight":20},300);
    },function(){
        $(this).stop().animate({"marginRight":0},300);
    })
})