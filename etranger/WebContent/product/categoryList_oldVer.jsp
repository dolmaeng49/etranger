<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
<%
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
	
	// 세션에서 로그인 회원의 찜목록 받아오기
	Object memberWishObject = session.getAttribute("member_wishList");
	ArrayList<String> member_wishList = null;
	if(session.getAttribute("member_wishList") != null){
		member_wishList = (ArrayList<String>)memberWishObject;
	}
	
	// 세션에서 로그인 회원의 아이디 가져오기
	String sid = "";
	if(session.getAttribute("member_id") != null) {
		sid = (String)session.getAttribute("member_id");
	}
	
%>
// 하트를 누르면 호출되는 함수
// 파라미터로 해당 패키지카테고리의 package_category_code 를 전달 받음
// 하트 태그의 이름이 1 이면 속이 찬 하트, 0 이면 속이 빈하트
function wishFunction(domain,code) {
	if(!isThereLoginSession()){
		alert('로그인이 필요합니다!');
		return;
	}
	// isOHeart = '0' : 하트 속이 비어있는 상태 , '1' : 속이 꽉 찬 하트
	var isOHeart = $(domain).attr('id');
	// 표시된 좋아요 숫자를 변수에 저장
	var wish_count = $('#wish_count').text();
	
	if(isOHeart=='0') {
		// back-end : ajax로 insertWish DB 작업
		// 액션클래스로 하트가 클릭된 상품(category) 코드 전달
		$.ajax('InsertWish.pr',{
			data : {category_code : code},
			success : function() {
				// front-end : 하트의 모양을 결정하는 클래스 add & remove, 모양을 저장하는 속성(id) 변경
				$(domain).removeClass('fa-heart-o');
				$(domain).addClass('fa-heart');
				$(domain).attr('id',"1");
				// 좋아요숫자 표시를 가져와 Number로 형변환 후 1을 더해 다시 표시
				// DB 에서 가져오는 것 아님, 눈속임. 새로고침하면 DB에서 가져오기 때문에 괜찮음
				$('#wish_count').text(Number(wish_count)+1);
			},
			error : function(){}
		});
	} else if(isOHeart=='1') {
		$.ajax('DeleteWish.pr',{
			data : {category_code : code},
			success : function() {
				$(domain).removeClass('fa-heart');
				$(domain).addClass('fa-heart-o');
				$(domain).attr('id',"0");
				$('#wish_count').text(Number(wish_count)-1);
			},
			error : function(){}
		});
		
	}
}
function isThereLoginSession(){
	if($('#sid').val().length==0){
		return false;
	}
	return true;
}
</script>
  </head>
  <body>
<input type="hidden" id="sid" value="<%=sid%>">
<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="./index.jsp">Home</a></span> <span>Packages</span></p>
              <h1 class="mb-3">Packages</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->
    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <div class="row">
			<%if (categoryList != null && listCount > 0) { %>
            <%for (int i = 0; i < categoryList.size(); i++) { %>
              <div class="col-md-6 col-lg-6 mb-4 ftco-animate">
                <a href="CategoryDetail.pr?package_category_code=<%=categoryList.get(i).getPackage_category_code() %>+"&package_category_theme=<%=categoryList.get(i).getPackage_category_theme() %>"
                 class="block-5" style="background-image: url('ManagerImgUpload/<%=categoryList.get(i).getPackage_category_image()%>');">
                </a>
                  <div class="text">
                    <span class="price">$399</span>
                    <h3 class="heading"><%=categoryList.get(i).getPackage_category_name()%></h3>
                    <div class="post-meta" style="text-align: right;">
                      <span>좋아요숫자 <span id="wish_count"><%=categoryList.get(i).getPackage_category_wish_count() %></span>
								<i onclick="wishFunction(this,'<%=categoryList.get(i).getPackage_category_code()%>')"
								<%if(member_wishList != null && member_wishList.contains(categoryList.get(i).getPackage_category_code())) {%>
									class="fa fa-heart" id="1" 
								<%} else {%>
									class="fa fa-heart-o" id="0"
								<%} %>
								style="font-size:24px;color:red;"></i></span>
                    </div>
                    <p class="star-rate"><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star-half-full"></span> <span>500 reviews</span></p>
                  </div>
              </div>
            <%}} %>
<!-- 상품한칸           
              <div class="col-md-6 col-lg-6 mb-4 ftco-animate">
                <a href="#" class="block-5" style="background-image: url('images/tour-1.jpg');">
                  <div class="text">
                    <span class="price">$399</span>
                    <h3 class="heading">Group Tour in Maldives</h3>
                    <div class="post-meta">
                      <span>Ameeru Ahmed Magu Male’, Maldives</span>
                    </div>
                    <p class="star-rate"><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star-half-full"></span> <span>500 reviews</span></p>
                  </div>
                </a>
              </div>
 -->           
<!-- PageController -->  
            </div>
            <div class="row mt-5">
              <div class="col text-center">
                <div class="block-27">
                  <ul>
                    <li><a href="#">&lt;</a></li>
                    <li class="active"><span>1</span></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&gt;</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- END -->

<!-- SideBar - SearchBox -->
          <div class="col-lg-4 sidebar"> <!-- 사이드바 전체  -->
            <div class="sidebar-box ftco-animate">  
              <div class="search-tours bg-light p-4"><!-- -->
                <h3>Find your tour</h3>
                <form action="CategoryListSearch.pr" method="post">
                  <div class="fields">
                    <div class="row flex-column"><!-- 검색바 묶음 -->

                      <div class="textfield-search col-sm-12 group mb-3">
                      	<input type="text" class="form-control" placeholder="상품명" name="keyword"></div> <!-- 키워드 검색창 -->

                      <div class="check-in col-sm-12 group mb-3">
                      	<input type="text" id="checkin_date"class="form-control pick_start_date" placeholder="출발 날짜" name="depart_date" autocomplete="off"><!--출발일 검색  -->
                      </div>

                      <div class="check-out col-sm-12 group mb-3">
                      	<input type="text" id="checkout_date" class="form-control" placeholder="도착 날짜" name="arriv_date" autocomplete="off"></div> <!--도착일 검색  -->
                      
                      
                      
                      <div class="select-wrap col-sm-12 group mb-3">
                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                        <select name="region" id="selectRegion" class="form-control" onChange="getCity()"><!--지역명  -->
                        	<option value="">지역선택</option>
                        </select>
                      </div>
                      
                      <div class="select-wrap col-sm-12 group mb-3">
                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                        <select name="city" id="selectCity" class="form-control"><!--도시명  -->
                        <option value="">도시선택</option>
                        </select>
                      </div>
                      
                      <!--  -->
                      
                      <div class="col-sm-12 group mb-3">
                        <input type="submit" class="search-submit btn btn-primary" value="검색하기">
                      </div>
                    </div>
                    </div>
                    </form>
                  </div>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <div class="categories">
                <h3>Categories</h3>
                <li><a href="#">Tours <span>(12)</span></a></li>
                <li><a href="#">Hotels <span>(22)</span></a></li>
                <li><a href="#">Cruises <span>(37)</span></a></li>
                <li><a href="#">Restaurant <span>(42)</span></a></li>
                <li><a href="#">Destination <span>(14)</span></a></li>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3>Tag Cloud</h3>
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3>Paragraph</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
            </div>
          </div>
<!-- End of SideBar  -->          
          
        </div>
      </div>
    </section>

	<!-- footer 인클루드 -->
<jsp:include page="../include/footer.jsp"/>
   <!-- loader 인클루드 -->
<jsp:include page="../include/loader.jsp"/>
<script type="text/javascript">

getRegion();
getRegion2();

function getRegion() {
// 	$('#selectRegion').hide();
	// #selectRegion에 있는 내용 지우기
	$('#selectRegion').empty();
	$('#selectRegion').append("<option value=''>지역선택</option>");
	// JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
	$.getJSON('RegionSelect.ma', function(data) {

		$.each(data, function(index, value) {
			$('#selectRegion').append(
					"<option value=" + value.regionCode + "> 지역이름 : " + value.regionName
							+ "</option>");
		});
	});
}

// 도시 목록 불러오기
function getCity() {
	$('#selectCity').empty();
	var code = $('#selectRegion').val();
	$('#selectCity').append("<option value=''>도시선택</option>");
	$.getJSON('CitySelect.ma?code=' + code, function(data) {
		$.each(data, function(index, value) {

			$('#selectCity').append(
					"<option value=" + value.cityCode + "> 도시이름 : " + value.cityName
							+ "</option>");
		});
	});
}

//
function getCity2() {
	$('#selectCity2').empty();
	var code = $('#selectRegion2').val();
	$('#selectCity2').append("<option value=''>도시선택</option>");
	$.getJSON('CitySelect.ma?code=' + code, function(data) {
		$.each(data, function(index, value) {

			$('#selectCity2').append(
					"<option value=" + value.cityCode + "> 도시이름 : " + value.cityName
							+ "</option>");
		});
	});
}

function getRegion2() {
// 	$('#selectRegion').hide();
	// #selectRegion에 있는 내용 지우기
	$('#selectRegion2').empty();
	$('#selectRegion2').append("<option value=''>지역선택</option>");
	// JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
	$.getJSON('RegionSelect.ma', function(data) {

		$.each(data, function(index, value) {
			$('#selectRegion2').append(
					"<option value=" + value.regionCode + "> 지역이름 : " + value.regionName
							+ "</option>");
		});
	});
}

</script>
    
  </body>
</html>