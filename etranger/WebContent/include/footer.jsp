    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<div id="floatdiv"><a href="javascript:void chatChannel()">
  <img src="https://developers.kakao.com/assets/img/about/logos/channel/consult_small_yellow_pc.png"/>
</a></div>
    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Bonjour tout le monde, Bienvenue!</h2>
              <p>Thank you for visiting our Web site. You've never seen before like this project. everyone works hard. 
             <br> 부득이한 사정에 의해 여행일정이 변경되는 경우 여행자의 사전 동의를 받습니다.</p>
            </div>
          </div>
          
              <!--추천 지역  -->
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
             <h2 class="ftco-heading-2">Most Popular Destination</h2>
              <ul class="list-unstyled" id="footer_region">
              </ul>
            </div>
          </div>
              <!--  -->
          
          <!--추천 테마  -->
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Recommended travel concept</h2>
              <ul class="list-unstyled" id="footer_theme">
                
              </ul>
            </div>
          </div>
          <!--  -->
          
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Contact Information</h2>
              <ul class="list-unstyled">

                <li><a href="./contact.jsp" class="py-2 d-block"> <span class="icon icon-map-marker">부산 부산진구 부전동 112-3</span></a></li>
                <li><a href="./contact.jsp" class="py-2 d-block"><span class="icon icon-phone">051)803-0909</span></a></li>
                <li><a href="./contact.jsp" class="py-2 d-block"><span class="icon icon-web">etranger.com</span></a></li>
                <li><a href="./contact.jsp" class="py-2 d-block"><span class="icon icon-envelope">etrangermanager@gmail.com</span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <ul class="ftco-footer-social list-unstyled float-md-right float-lft">
                <li class="ftco-animate"><a href="https://twitter.com/etrangerdev"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                <li class="ftco-animate"><a href="javascript:void chatChannel()">
  <img src="./images/Kakao.png">
</a></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
	
            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">

displayFooterRegion();
displayFooterTheme();

//푸터 추천 지역 
function displayFooterRegion() {
	$('#footer_region').empty();
	// JSON으로 가져온 데이터 #side_region에 뿌려주기
	$.getJSON('RegionSelect.ma', function(data) {
		$.each(data, function(index, value) {
			$('#footer_region').append(
					"<li><a href='CategoryListSearch.pr?region="+value.regionCode+"'>" + value.regionName + "</a></li>");
		});
	});
}

//푸터 추천 테마
function displayFooterTheme() {
	
$('#footer_theme').empty();
	// JSON으로 가져온 데이터 #side_theme에 뿌려주기
	$.getJSON('GetThemeListAjax.ma', function(data) {

		$.each(data, function(index, value) {
			$('#footer_theme').append(
					"<li><a href='CategoryListSearch.pr?keyword="+ value.themeName +"'>" + value.themeName + "</a></li>");
		});
	});
	
}
      
</script>
    </footer>