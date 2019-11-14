/**
 * 톡상담 메뉴 구현
 */

$(document).ready(function() {
	var floatPosition = parseInt($("#floatMenu").css('top'));
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		var newPosition = scrollTop + floatPosition + "px";
		$("#floatMenu").stop().animate({
			"top" : newPosition
		}, 500);
	}).scroll();
});
