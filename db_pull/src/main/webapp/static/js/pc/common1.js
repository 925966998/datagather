/*Created by Administrator on 2016/9/6.*/
$(document).ready(function(){
    //卖家中心
    function sellerCencre(){
        $(".m_sellerLeft li h3").click(function(){
            var name=$(this).attr("state");
            console.log(name);
            if(name==0){
                $(this).parent().find(".list").slideUp(300);
                $(this).addClass("cur");
                $(this).attr("state","1");
            }else{
                $(this).attr("state","0");
                $(this).parent().find(".list").slideDown(300);
                $(this).removeClass("cur");
            }

        })
    }
    sellerCencre();
})
































