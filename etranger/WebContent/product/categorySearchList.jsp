<%@page import="product.vo.SearchBean"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
	SearchBean searchBean = (SearchBean)request.getAttribute("searchBean");
	
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
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
                <a href="CategoryDetail.pr?package_category_code=<%=categoryList.get(i).getPackage_category_code() %>&package_category_theme=<%=categoryList.get(i).getPackage_category_theme() %>"
                 class="block-5" style="background-image: url('ManagerImgUpload/<%=categoryList.get(i).getPackage_category_image()%>');">
                </a>
                  <div class="text">
                    <span class="price">￦<%=categoryList.get(i).getMin_price() %> ~</span>
                    <h3 class="heading"><%=categoryList.get(i).getPackage_category_name()%></h3>
                    <div class="post-meta" style="text-align: right;" onclick="wishFunction(this,'<%=categoryList.get(i).getPackage_category_code()%>')">
                      <span>찜숫자 <span id="wish_count"><%=categoryList.get(i).getPackage_category_wish_count() %></span>
								<i
								<%if(member_wishList != null && member_wishList.contains(categoryList.get(i).getPackage_category_code())) {%>
									class="fa fa-heart" id="1" 
								<%} else {%>
									class="fa fa-heart-o" id="0"
								<%} %>
								style="font-size:24px;color:red;"></i></span>
                    </div>
                    <p class="star-rate">
                        <!-- 리뷰 별점 스크립틀릿으로 표시할 부분,  -->
                        <%
                           // 리뷰 별점 평균 표시, 리뷰 개수 표시
                              if (categoryList.get(i).getReview_count() != 0) {
                                 // 10점 만점 평균 별점 반올림 처리
                                 byte avg_int = (byte)Math.round(categoryList.get(i).getReview_star_avg());
                                 for (int j = 1; j <= avg_int / 2; j++) {
                        %><span class="icon-star"></span>
                        <%
                           }
                                 if (avg_int % 2f >= 0.4) {
                        %><span class="icon-star-half-full"></span>
                        <%
                           }
                                 for (int j = (int) ((avg_int + 1) / 2); j < 5; j++) {
                        %><span class="icon-star-o"></span>
                        <%
                           }
                        %>
                        <span><%=categoryList.get(i).getReview_count()%> reviews</span>
                     <%
                        }
                     %>
                     </p>
	                  </div>
	              </div>
                   <% }} %>
            
<!-- PageController -->  
            </div>
            <div class="row mt-5">
          <div class="col text-center">
            <div class="block-27">
              <ul>
            <%if(nowPage <= 1){%>
            <li><a>&lt;</a></li>
            <%}else
            {%><li><a href="javascript:void(0);" onclick="pageForward('<%=nowPage - 1%>'); return false;">&lt;</a></li><%} %>
            	<%for(int i=startPage; i<=endPage; i++) {
	            	if(i== nowPage) {
	            		%><li class="active"><span><%=i %></span></li>
	            	<%}else{%>
	                	<li><a href="javascript:void(0);" onclick="pageForward('<%=i%>'); return false;"><%=i %></a></li>
	                <%}%>
                <%}%>
                <%if(nowPage >= maxPage){
                	%><li><a>&gt;</a></li>
                <%}else{ %>
                	<li><a href="javascript:void(0);" onclick="pageForward('<%=nowPage + 1%>'); return false;">&gt;</a></li>
                <%} %>
              </ul>
            </div>
          </div>
        </div>
        <!-- PageController 끝 -->
          </div>
          <!-- END -->

<!-- SideBar - SearchBox -->
          <div class="col-lg-4 sidebar"> <!-- 사이드바 전체  -->
            <div class="sidebar-box ftco-animate">  
              <div class="search-tours bg-light p-4"><!-- -->
                <h3>Find your tour</h3>
                <form action="CategoryListSearch.pr" method="post" id="categorySearchForm">
                  <div class="fields">
                    <div class="row flex-column"><!-- 검색바 묶음 -->

                      <div class="textfield-search col-sm-12 group mb-3">
                      	<input type="text" class="form-control" placeholder="상품명" name="keyword" value="<%=searchBean.getKeyword()%>"></div> <!-- 키워드 검색창 -->

                      <div class="check-in col-sm-12 group mb-3">
                      	<input type="text" class="form-control pick_start_date" placeholder="출발 날짜"
                      	name="depart_date" readonly="readonly" value="<%=searchBean.getDepart_date()%>"><!--출발일 검색  -->
                      </div>

                      <div class="check-out col-sm-12 group mb-3">
                      	<input type="text" id="checkout_date" class="form-control" placeholder="도착 날짜"
                      	name="arriv_date" readonly="readonly" value="<%=searchBean.getArriv_date()%>"></div> <!--도착일 검색  -->
                      
                      
                      
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
                      <!-- 페이지 이동할 때 검색 정보를 다음 페이지로 전달하기 위한 input[type="hidden"] 태그들 -->
                        <input type="hidden" id="region_code_search" value="<%=searchBean.getRegion()%>">
                        <input type="hidden" id="city_code_search" value="<%=searchBean.getCity()%>">
                        <input type="hidden" id="fowarding_page" name="page" value="1">
                      <!--  -->
                      
                      <div class="col-sm-12 group mb-3">
                        <input type="submit" class="search-submit btn btn-primary" value="Find Packages">
                      </div>
                    </div>
                  </div>
                    </form>
              </div>
            </div>
			
			<!--추천 지역 -->
            <div class="sidebar-box ftco-animate">
              <div class="categories">
                <h3>추천 지역</h3>
                <div id="side_region" class="side_region">
                
                </div>
              </div>
            </div>
            <!--  -->
            
			<!-- 추천 테마 -->
            <div class="sidebar-box ftco-animate">
              <h3>추천 테마</h3>
              <div id="side_theme" class="tagcloud">
              </div>
            </div>
            <!--  -->
            
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

displaySideRegion();
displaySideTheme();

// 사이드바 추천 지역 
function displaySideRegion() {
	
	$('#side_region').empty();
	// JSON으로 가져온 데이터 #side_region에 뿌려주기
	$.getJSON('RegionSelect.ma', function(data) {

		$.each(data, function(index, value) {
			$('#side_region').append(
					"<li><a href='CategoryListSearch.pr?region="+value.regionCode+"'>" + value.regionName + "</a></li>");
		});
	});
}

//사이드바 추천 테마
function displaySideTheme() {
	$('#side_theme').empty();
	// JSON으로 가져온 데이터 #side_theme에 뿌려주기
	$.getJSON('GetThemeListAjax.ma', function(data) {

		$.each(data, function(index, value) {
			$('#side_theme').append(
					"<a href='CategoryListSearch.pr?keyword="+ value.themeName +"' class='tag-cloud-link'>" +value.themeName + "</a>");
		});
	});
}

function getRegion() {
	// 검색한 지역코드 가져오기(Number 타입으로 형변환)
	var region_code_search = Number($('#region_code_search').val());
	var city_code_search = Number($('#region_code_search').val());
	// #selectRegion에 있는 내용 지우기
	$('#selectRegion').empty();
	$('#selectRegion').append("<option value=''>지역선택</option>");
	// JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
	$.getJSON('RegionSelect.ma', function(data) {
		$.each(data, function(index, value) {
			// JSON 형태로 받은 지역 코드, 이름을 <option> 태그 형태로 출력
			// 검색한 지역 코드가 있을 경우 해당 지역 코드가 선택되도록 selected 속성 추가
			if(region_code_search != null && region_code_search == value.regionCode){
				$('#selectRegion').append('<option value=' + value.regionCode + ' selected="selected"> 지역이름 : '
						+ value.regionName + '</option>');
				if(city_code_search != null){
					getCity(); // 도시코드도 입력된 경우 getCity() 함수 호출
				}
			} else {
				$('#selectRegion').append("<option value=" + value.regionCode + "> 지역이름 : "
						+ value.regionName + "</option>");
			}
		});
	});
}

// 도시 목록 불러오기
function getCity() {
	var code = $('#selectRegion').val();
	var city_code_search = Number($('#region_code_search').val());
	
	$('#selectCity').empty();
	$('#selectCity').append("<option value=''>도시선택</option>");
	
	$.getJSON('CitySelect.ma?code=' + code, function(data) {
		$.each(data, function(index, value) {
			// 검색한 도시 코드가 있을 경우 해당 지역 코드가 선택되도록 selected 속성 추가
			if(city_code_search != null && city_code_search == value.cityCode){
				$('#selectCity').append('<option value=' + value.cityCode + ' selected="selected"> 도시이름 : '
						+ value.cityName + '</option>');
			}
			$('#selectCity').append("<option value=" + value.cityCode + "> 도시이름 : "
					+ value.cityName + "</option>");
		});
	});
}

getRegion(); // 페이지 로드될 때 함수 호출

// 하트를 누르면 호출되는 함수
// 파라미터로 해당 패키지카테고리의 package_category_code 를 전달 받음
// 하트 태그의 이름이 1 이면 속이 찬 하트, 0 이면 속이 빈하트
function wishFunction(domain,code) {
	if(!isThereLoginSession()){
		alert('로그인이 필요합니다!');
		return;
	}
	// isOHeart = '0' : 하트 속이 비어있는 상태 , '1' : 속이 꽉 찬 하트
	var isOHeart = $(domain).find('i').attr('id');
	// 표시된 좋아요 숫자를 변수에 저장
	var wish_count = $(domain).find('#wish_count').text();
	
	if(isOHeart=='0') {
		// back-end : ajax로 insertWish DB 작업
		// 액션클래스로 하트가 클릭된 상품(category) 코드 전달
		$.ajax('InsertWish.pr',{
			data : {category_code : code},
			success : function() {
				// front-end : 하트의 모양을 결정하는 클래스 add & remove, 모양을 저장하는 속성(id) 변경
				$(domain).find('i').removeClass('fa-heart-o');
				$(domain).find('i').addClass('fa-heart');
				$(domain).find('i').attr('id',"1");
				// 좋아요숫자 표시를 가져와 Number로 형변환 후 1을 더해 다시 표시
				// DB 에서 가져오는 것 아님, 눈속임. 새로고침하면 DB에서 가져오기 때문에 큰 문제 없음. 더 좋은 방법 있으면,,
				$(domain).find('#wish_count').text(Number(wish_count)+1);
			},
			error : function(){alert("찜 추가 실패!");}
		});
	} else if(isOHeart=='1') {
		$.ajax('DeleteWish.pr',{
			data : {category_code : code},
			success : function() {
				$(domain).find('i').removeClass('fa-heart');
				$(domain).find('i').addClass('fa-heart-o');
				$(domain).find('i').attr('id',"0");
				$(domain).find('#wish_count').text(Number(wish_count)-1);
			},
			error : function(){alert("찜 삭제 실패!");}
		});
		
	}
}

function isThereLoginSession(){
	if($('#sid').val().length==0){
		return false;
	}
	return true;
}

function pageForward(forwarding_page) {
	$('#fowarding_page').val(forwarding_page);
	$('#categorySearchForm').submit();
}
</script>
  </body>
</html>