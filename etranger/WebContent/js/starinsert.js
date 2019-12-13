$('.starRev span').click(function(){
  $(this).parent().children('span').removeClass('on');
  $(this).addClass('on').prevAll('span').addClass('on');
  
  $('#review_star').val($(this).text());
  
  return false;
});

//공지삭제 confirm 창으로 제어
$('#notice_del_button').click(function () {
	var delConfirm = confirm('정말 삭제하시겠습니까?');
	if (delConfirm){
	}else{
		return false;
	}
	
});