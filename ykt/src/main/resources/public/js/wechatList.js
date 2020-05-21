$(function () {
    $('.weui-tab__bd-item').eq(0).show().siblings().hide();
    $('.weui-tabbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        //获得点击元素的id
        var index = $(this).index();
        $('.weui-tab__bd-item').eq(index).show().siblings().hide();
    })
})