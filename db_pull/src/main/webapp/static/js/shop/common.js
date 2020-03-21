/**
 * Created by Administrator on 2016/8/25.
 */
$(document).ready(function(){
    //产品分类页
    function product(){
        //二级导航展开
        $(".zk").on('mouseover',function(){
            $(this).css({"height":"545px","overflow":"inherit"});
        });
        $(".zk").on('mouseout',function(){
            $(this).css({"height":"50px","overflow":"hidden"});
        });
        $(".zk").on('mouseover','.box',function(){
            $(this).find('.list').show().parent().siblings().find('.list').hide();
        });
        $(".zk").on('mouseout','.box',function(){
            $(this).find('.list').hide();
        });

        //分类展开
        $(".classify li:gt(2)").hide();
        $(".classify li:nth-child(3)").css({"borderBottom":"none"});
        $(".classify .screen").click(function(){
            var name=$(this).html();
            if(name =='展开筛选  ∨'){
                $(".classify li:gt(2)").show();
                $(".classify li:nth-child(3)").css({"borderBottom":"1px solid #eee"});
                $(this).html("收起筛选  ∧");
            }else{
                $(".classify li:gt(2)").hide();
                $(".classify li:nth-child(3)").css({"borderBottom":"none"});
                $(this).html("展开筛选  ∨");
            }
        });
        $(".classify ul li").on("click",".more",function(){
            var name=$(this).html();
            if(name =="更多∨"){
                $(this).html("收起∧");
                $(this).parent().find(".text").css("height","auto");
            }else{
                $(this).html("更多∨");
                $(this).parent().find(".text").css("height","45px");
            }
        });

        //发货地
        $(".goodsList>.title>.box>.a1").hover(function(){
            $(this).find(".list").show();
        },function(){
            $(this).find(".list").hide();
        })
    }
    product();

    //我的购物车页
    function shopCart(){
        $(".goodsOrder .sign,.goodsOrder .plus").click(function(){
            var name=$(this).attr("class");
            var num= parseFloat($(this).parent().find("em").html());
            var item=parseFloat($(this).parent().parent().find(".ml190 em").html());
            var itemNum=parseFloat($(this).parent().parent().find(".money em").html());
            if(name=='sign'){
                if(num>0){
                    --num;
                    itemNum=itemNum-item;
                    if($(this).parent().parent().find(".check").attr("class")=="check fl cur"){
                        numsign(item);
                        countsign(1);
                    }
                }else{
                    num=0;
                }
            }else{
                ++num;
                itemNum=itemNum+item;
                if($(this).parent().parent().find(".check").attr("class")=="check fl cur"){
                    numplus(item);
                    countplus(1);
                }
            }
            $(this).parent().find("em").html(num);
            $(this).parent().parent().find(".money em").html(itemNum);
        })

        $(".goodsOrder .check").click(function(){
            var name=$(this).attr("check");
            if(name=='dd'){
                var itemNum=parseFloat($(this).parent().find(".money em").html());
                var countNum=parseFloat($(this).parent().find(".num em").html());
                if($(this).attr("class")=='check fl'){
                    $(this).addClass("cur");
                    $(this).parents("dd").addClass("cur");
                    $(this).parents("dl").find("dt .check").addClass("cur");
                    numplus(itemNum);
                    countplus(countNum);
                }else{
                    $(this).removeClass("cur");
                    $(this).parents("dd").removeClass("cur");
                    $(this).parents(".goodsOrder").find(".title_b>.check,.title_a>.check").removeClass("cur");
                    numsign(itemNum);
                    countsign(countNum);
                    var num=$(this).parents("dl").find("dd").find(".check.cur").length;
                    if(num==0){
                        $(this).parents("dl").find("dt .check").removeClass("cur");
                    }
                }
            }
            if(name=='dt'){
                if($(this).attr("class")=='check fl') {
                    $(this).addClass("cur");
                    $(this).parents("dl").find("dd .check,dd").addClass("cur");
                    $(this).parent().parent().find("dd").each(function(){
                        var itemNum=parseFloat($(this).find(".money em").html());
                        var countNum=parseFloat($(this).find(".num em").html());
                        numplus(itemNum);
                        countplus(countNum);
                    })
                }else {
                    $(this).removeClass("cur");
                    $(this).parents(".goodsOrder").find(".title_b>.check,.title_a>.check").removeClass("cur");
                    $(this).parent().parent().find("dd").each(function(){
                        if($(this).find(".check").attr("class")=='check fl cur'){
                            var itemNum=parseFloat($(this).parent().find(".money em").html());
                            var countNum=parseFloat($(this).parent().find(".num em").html());
                             numsign(itemNum);
                             countsign(countNum);
                            $(this).removeClass("cur").find(".check").removeClass("cur");
                        }
                    })
                }
            }
            if(name=='all'){
                if($(this).attr("class")=='check fl') {
                    $(this).addClass("cur");
                    $(this).parents(".goodsOrder").find(".check").addClass("cur");
                    $(".countMoney em").html("0");
                    $(this).parents(".goodsOrder").find("dd").each(function(){
                        var itemNum=parseFloat($(this).find(".money em").html());
                        var countNum=parseFloat($(this).find(".num em").html());
                        numplus(itemNum);
                        countplus(countNum);
                        $(this).addClass("cur");
                    })
                }else {
                    $(this).removeClass("cur");
                    $(this).parents(".goodsOrder").find("dd").each(function(){
                        if($(this).find(".check").attr("class")=='check fl cur'){
                            var itemNum=parseFloat($(this).parent().find(".money em").html());
                            $(".countMoney em").html("0");
                            $(".shopNum em").html("0");
                            $(this).removeClass("cur").find(".check").removeClass("cur");
                        }
                    });
                    $(this).parents(".goodsOrder").find("dt .check").removeClass("cur");
                    $(this).parents(".goodsOrder").find(".title_b>.check,.title_a>.check").removeClass("cur");
                }
            }
        });
        $(".goodsOrder>.title_b>.del").click(function(){
            $(".goodsOrder .check").each(function(){
                if($(this).attr("class")=="check fl cur"){
                    if($(this).attr("check")=='dd'){
                        if($(this).parents("dl").find("dd").length==1){
                            $(this).parents("dl").find("dt").remove();
                        };
                        $(this).parent().parent().remove();
                        var countNum=parseFloat($(this).parent().find(".num em").html());
                        var itemNum=parseFloat($(this).parent().find(".money em").html());
                        countsign(countNum);
                        numsign(itemNum);
                        $(this).parents(".goodsOrder").find(".title_b>.check,.title_a>.check").removeClass("cur");
                    }
                }
            });
        });
        $(".goodsOrder dd>.con>.del").click(function(){
            if($(this).parents("dl").find("dd").length==1){
                $(this).parents("dl").find("dt").remove();
            }
            $(this).parent().parent().remove();
            if($(this).parent().find(".check").attr("class")=='check fl cur'){
                var countNum=parseFloat($(this).parent().find(".num em").html());
                var itemNum=parseFloat($(this).parent().find(".money em").html());
                countsign(countNum);
                numsign(itemNum);
            }

        });

        function numsign(sign){
            var num=parseFloat($(".countMoney em").html());
            num=num-sign;
            $(".countMoney em").html(num);
        }
        function numplus(plus){
            var num=parseFloat($(".countMoney em").html());
            num=num+plus;
            $(".countMoney em").html(num);
        }
        function countsign(sign){
            var num=parseFloat($(".shopNum em").html());
            num=num-sign;
            $(".shopNum em").html(num);
        }
        function countplus(plus){
            var num=parseFloat($(".shopNum em").html());
            num=num+plus;
            $(".shopNum em").html(num);
        }
    }
    shopCart();

    //活动详情页
    function activity(){
        var step=0;
        function textScroll(){
            step-=1;
            $(".activity>.module_a>.scroll ul").css("top",step);
            if(step==-30){
                $(".activity>.module_a>.scroll ul li:lt(2)").insertAfter("li:last()");
                $(".activity>.module_a>.scroll ul").css("top",0);
                step=0;
            }
        }
        var s=setInterval(textScroll,50);

    }
    activity();

})
