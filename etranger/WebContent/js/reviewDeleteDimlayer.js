$('#btn_ReviewDelete').click(function(){
    var $href = $(this).attr('href');
    layer_popupReviewDelete($href);
});
function layer_popupReviewDelete(el){

    var $el = $(el);        //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('dimBgReviewDelete');   //dimmed 레이어를 감지하기 위한 boolean 변수

    isDim ? $('.dim-layerReviewDelete').fadeIn() : $el.fadeIn();

    var $elWidth = ~~($el.outerWidth()),
        $elHeight = ~~($el.outerHeight()),
        docWidth = $(document).width(),
        docHeight = $(document).height();

    // 화면의 중앙에 레이어를 띄운다.
    if ($elHeight < docHeight || $elWidth < docWidth) {
        $el.css({
            marginTop: -$elHeight /2,
            marginLeft: -$elWidth/2
        })
    } else {
        $el.css({top: 0, left: 0});
    }

    $el.find('#btnPopUpCloseReviewDelete').click(function(){
        isDim ? $('.dim-layerReviewDelete').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });

    $('.layerReviewDelete .dimBgReviewDelete').click(function(){
        $('.dim-layerReviewDelete').fadeOut();
        return false;
    });

}