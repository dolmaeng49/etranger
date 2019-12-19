<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="review.vo.ReviewBean"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.action.MainPageAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp" />
<%

   // 메인페이지 작업처리를 위해 MainPageAction 클래스의 execute() 메서드 호출
   MainPageAction mainPageAction = new MainPageAction();
   mainPageAction.execute(request, response);
   // 
   ArrayList<CategoryBean> newList = (ArrayList<CategoryBean>) request.getAttribute("newList");
   ArrayList<CategoryBean> popularList =  (ArrayList<CategoryBean>) request.getAttribute("popularList");
   ArrayList<CategoryBean> recommendedList =  (ArrayList<CategoryBean>) request.getAttribute("recommendedList");
   ArrayList<ReviewBean> reviewList = (ArrayList<ReviewBean>) request.getAttribute("reviewList");
   
   String newList_name = (String)request.getAttribute("newList_name");

   
//	날짜 변환 작업
	SimpleDateFormat sdfOrigin=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfToday=new SimpleDateFormat("HH 시 mm 분");
	SimpleDateFormat sdfCalculation= new SimpleDateFormat("yyyyMMdd");
	
	Calendar calToday = Calendar.getInstance();
%>

</head>
<body>
   <!-- 탑메뉴 인클루드 -->
   <jsp:include page="/include/top_menu.jsp" />

   <section class="home-slider owl-carousel">

      <div class="slider-item"
         style="background-image: url('images/bg_main1.jpg');">
         <div class="overlay"></div>
         <div class="container">
            <div class="row slider-text align-items-center">
               <div class="col-md-7 col-sm-12 ftco-animate">
                  <h1 class="mb-3">Travel with etranger!</h1>
               </div>
            </div>
         </div>
      </div>

      <div class="slider-item"
         style="background-image: url('images/bg_main2.jpg');">
         <div class="overlay"></div>
         <div class="container">
            <div class="row slider-text align-items-center">
               <div class="col-md-7 col-sm-12 ftco-animate">
                  <h1 class="mb-3">Experience the best trip ever</h1>
               </div>
            </div>
         </div>
      </div>

      <div class="slider-item"
         style="background-image: url('images/bg_main3.jpg');">
         <div class="overlay"></div>
         <div class="container">
            <div class="row slider-text align-items-center">
               <div class="col-md-7 col-sm-12 ftco-animate">
                  <h1 class="mb-3">Making the most out of your holiday</h1>
               </div>
            </div>
         </div>
      </div>

      <div class="slider-item"
         style="background-image: url('images/bg_main4.jpg');">
         <div class="overlay"></div>
         <div class="container">
            <div class="row slider-text align-items-center">
               <div class="col-md-7 col-sm-12 ftco-animate">
                  <h1 class="mb-3">Travel Operator Just For You</h1>
               </div>
            </div>
         </div>
      </div>
   </section>
   <!-- END slider -->

   <div class="ftco-section-search">
      <div class="container">
         <div class="row">
            <div class="col-md-12 tabulation-search">
               <div class="element-animate">
                  <div class="nav nav-pills" id="v-pills-tab" role="tablist"
                     aria-orientation="vertical">
                     <a class="nav-link p-3 active" id="v-pills-home-tab"
                        data-toggle="pill" href="#v-pills-home" role="tab"
                        aria-controls="v-pills-home" aria-selected="true"> 맞춤 검색</a> <a
                        class="nav-link p-3" id="v-pills-messages-tab"
                        data-toggle="pill" href="#v-pills-messages" role="tab"
                        aria-controls="v-pills-messages" aria-selected="false"> 상세
                        검색</a>
                  </div>
               </div>

               <div class="tab-content py-5" id="v-pills-tabContent">
                  <!-- 첫번째 검색 탭 (메인 검색 탭) -->
                  <div class="tab-pane fade show active" id="v-pills-home"
                     role="tabpanel" aria-labelledby="v-pills-home-tab">
                     <div class="block-17">
                        <form action="CategoryListSearch.pr" method="post"
                           class="d-block d-lg-flex">
                           <div class="fields d-block d-lg-flex" id="event_period">

                              <div class="textfield-search one-third one-third-1">
                                 <input type="text" name="keyword" class="form-control"
                                    placeholder="키워드 검색">
                              </div>

                              <div class="check-in one-third one-third-1">
                                 <input type="text" name="depart_date" autocomplete="off"
                                    class="form-control actual_range" placeholder="출발일 검색">
                              </div>

                              <div class="check-out one-third one-third-1">
                                 <input type="text" name="arriv_date" autocomplete="off"
                                    class="form-control actual_range" placeholder="도착일 검색">
                              </div>
                           </div>
                           <input type="submit" class="search-submit btn btn-primary"
                              value="검색하기">
                        </form>
                     </div>
                  </div>
                  <!-- 두번째 검색 탭 -->
                  <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                     aria-labelledby="v-pills-messages-tab">
                     <div class="block-17">
                        <form action="CategoryListSearch.pr" method="post"
                           class="d-block d-lg-flex">
                           <div class="fields d-block d-lg-flex" id="event_period2">

                              <div class="check-in one-third one-third-1">
                                 <input type="text" name="depart_date" autocomplete="off"
                                    class="form-control actual_range" placeholder="출발일 검색">
                              </div>

                              <div class="check-out one-third one-third-1">
                                 <input type="text" name="arriv_date" autocomplete="off"
                                    class="form-control actual_range" placeholder="도착일 검색">
                              </div>

                              <div class="select-wrap one-third one-third-1">
                                 <div class="icon">
                                    <span class="ion-ios-arrow-down"></span>
                                 </div>
                                 <select name="region" id="selectRegion" class="form-control"
                                    onChange="getCity()">
                                    <option id="bg_gray" value="">지역선택</option>
                                 </select>
                              </div>

                              <div class="select-wrap one-third one-third-1">
                                 <div class="icon">
                                    <span class="ion-ios-arrow-down"></span>
                                 </div>
                                 <div class="icon">
                                    <span class="ion-ios-arrow-down"></span>
                                 </div>
                                 <select name="city" id="selectCity" class="form-control">
                                    <option id="bg_gray" value="">도시선택</option>
                                 </select>
                              </div>
                           </div>
                           <input type="submit" class="search-submit btn btn-primary"
                              value="검색하기">
                        </form>
                     </div>
                  </div>

               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- 검색부분 끝 -->
   
   <!-- 메인 홍보영상-->
   <section class="ftco-section-2">
      <div class="container-fluid d-flex">
         <div class="section-2-blocks-wrapper row no-gutters">
            <div class="img col-sm-12 col-lg-6">

            <div class="embed-responsive embed-responsive-16by9" style="margin-top: 9rem;">
                  <video autoplay muted loop class="embed-responsive-item">
                     <source src="images/intro.mp4" type="video/mp4">
                  </video>
               </div>
<!--                <a href="https://vimeo.com/371024892" class="button popup-vimeo"><span -->
<!--                   class="ion-ios-play"></span></a> -->
                  
            </div>
            <div class="text col-lg-6 ftco-animate">
               <div class="text-inner align-self-start">

                  <h3>Bonjour tout le monde, Bienvenue! vous pouvez voyager
                     avec l'étranger?</h3>
                  <p>We are the best team You've ever seen. We were established
                     for 6 month ago. Albert Camus, he is the muse of us. We feel
                     we're the completely etranger when we're in the foreign country.
                     We all agree to say for sure. you know. that's why we decided our
                     team name to etranger.</p>

                  <p>모두들 안녕하세요, étranger 웹사이트에 오신 것을 환영합니다. 여행을 계획하고 계신가요? 에트랑제와
                     함께 믿음직한 가이드와 합리적인 가격으로 여행을 떠나보세요. 저희는 업계 최저 수수료를 추구합니다. 2030년 글로벌
                     No.1 문화관광 유통그룹으로 발돋움하기위해 고객에게 세계 최고의 문화관광 유통 서비스를 제공하는 기업으로
                     성장하겠습니다.</p>
               </div>
            </div>
         </div>
      </div>
   </section>
   <!-- 동그라미 상품 4가지 -->
   <section class="ftco-section">
      <div class="container">
         <div class="row">
            <%
               for (CategoryBean cb : newList) {
                  String detailURL = "CategoryDetail.pr?package_category_code=".concat(cb.getPackage_category_code());
            %>
            <div class="col-lg-3 promo ftco-animate">
               <a href="<%=detailURL%>" class="promo-img mb-4"
                  style="background-image: url(ManagerImgUpload/<%=cb.getPackage_category_image()%>);"></a>
               <div class="text text-center">
                  <a href="<%=detailURL%>"><h2><%=cb.getPackage_category_name()%></h2></a>
                  <h3 class="price">
                     <%
                        int price = cb.getMin_price();
                           String prices = String.format("%,d", price); // 천단위 , 표시
                     %>
                     <span>from</span>
                     <%=prices%>￦
                  </h3>

                  <a href="<%=detailURL%>" class="read">Read more</a>
               </div>
            </div>
            <%
               }
            %>

         </div>
      </div>
   </section>
   <!-- 추천 8개 -->
   <section class="ftco-section">
      <div class="container-fluid">
         <div
            class="row no-gutters justify-content-center mb-5 pb-5 ftco-animate">
            <div class="col-md-7 text-center heading-section">
               <h2><%=newList_name %></h2>
            </div>
         </div>
         <div class="row no-gutters">
            <%
               for (CategoryBean cb : popularList) {
                  String detailURL = "CategoryDetail.pr?package_category_code=".concat(cb.getPackage_category_code());
            %>
            <div class="col-md-6 col-lg-3 ftco-animate">
               <a href="<%=detailURL%>" class="block-5"
                  style="background-image: url(ManagerImgUpload/<%=cb.getPackage_category_image()%>);">
                  <div class="text">
                  <% 
                  int price = cb.getMin_price(); 
                  String prices = String.format("%,d", price); // 천단위 , 표시
                  %>
                     <span class="price"><%=prices %>&nbsp;￦부터</span>
                     <h3 class="heading"><%=cb.getPackage_category_name()%></h3>
                     <div class="post-meta">
                        <% String code = cb.getPackage_category_code();
                           if(code.contains("!")){
                                code = code.substring(code.indexOf("!")); // category code 느낌표 없애기      
                           }
                         
                           %><span><%=code.replace('!', '#') %></span> 
                           

                     </div>
                     <p class="star-rate">
                        <!-- 리뷰 별점 스크립틀릿으로 표시할 부분,  -->
                        <%
                           // 리뷰 별점 평균 표시, 리뷰 개수 표시
                              if (cb.getReview_count() != 0) {
                                 // 10점 만점 평균 별점 반올림 처리
                                 byte avg_int = (byte)Math.round(cb.getReview_star_avg());
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
                        <span><%=cb.getReview_count()%> reviews</span>
                     <%
                        }
                     %>
                     </p>
                  </div>
               </a>
            </div>
            <%
               }
            
            %>
         </div>
      </div>
   </section>


   <!-- 블로그 5개 -->
   <section class="ftco-section bg-light">
      <div class="container">
         <div class="row justify-content-center mb-5 pb-5">
            <div class="col-md-7 text-center heading-section ftco-animate">
               <h2>Recent Reviews</h2>
            </div>
         </div>
         <div class="row ftco-animate">
            <div class="carousel1 owl-carousel ftco-owl">
               <%
                  for (ReviewBean rb : reviewList) {
                     String detailURL = "ReviewDetail.rv?review_num=" + rb.getReview_num();
               %>
               <div class="item">
                  <div class="blog-entry">
                     <a href=<%=detailURL%> class="block-20"
                        style="background-image: url('reviewUpload/<%=rb.getReview_image()%>');">
                     </a>
                     <div class="text p-4">
                        <div class="meta">
                           <div>
                              <a href="<%=detailURL%>">
                              <% 
                              //날짜 포맷 변경
                  Calendar calWritingPoint = Calendar.getInstance();
                  	calWritingPoint.setTime(rb.getReview_date());
    				String today = sdfCalculation.format(calToday.getTime());
    				String writingPoint = sdfCalculation.format(calWritingPoint.getTime());
   					String checkedDate=(today.compareTo(writingPoint)==0)?sdfToday.format(rb.getReview_date()):sdfOrigin.format(rb.getReview_date());%>
   					<%=checkedDate %></a>
                           </div>
                           <div>
                              <a href="<%=detailURL%>"><%=rb.getReview_member_name()%></a>
                           </div>
                        </div>
                        <h3 class="heading">
                           <a href="<%=detailURL%>"><%=rb.getReview_subject()%></a>
                        </h3>
                        <p class="clearfix">
                           <a href="<%=detailURL%>" class="float-left">Read more</a> <a
                              href="<%=detailURL%>" class="float-right meta-chat"><span
                              class="icon-chat"></span> 3</a>
                        </p>
                     </div>
                  </div>
               </div>
               <%
                  }
               %>
            </div>
         </div>
      </div>
   </section>

   <!-- 추천 상품3개 -->
   <section class="ftco-section">
      <div class="container-fluid">
         <div class="row justify-content-center mb-5 pb-5">
            <div class="col-md-7 text-center heading-section ftco-animate">
               <h2 class="mb-1 p-2 pb-3 ftco-animate">Highly Recommended
                  Tours</h2>
               <h5 class="mb-3 p-2 pb-3 ftco-animate">어디로 가야 할지 아직 못 정하셨나요?</h5>

               <div class="row no-gutters d-flex">
                  <%
                     for (CategoryBean cb : recommendedList) {
                        String detailURL = "CategoryDetail.pr?package_category_code=".concat(cb.getPackage_category_code());
                  %>
                  <div class="col-md-4 ftco-animate">
                     <a href="<%=detailURL%>" class="block-5"
                        style="background-image: url(ManagerImgUpload/<%=cb.getPackage_category_image()%>);">
                        <div class="text">
                           <%
                              int price = cb.getMin_price();
                                 String prices = String.format("%,d", price); // 천단위 , 표시
                           %>
                           <span class="price"><%=prices%>&nbsp;￦부터</span>
                           <h3 class="heading"><%=cb.getPackage_category_name()%></h3>
                           <div class="post-meta">
                              <%
                                 String code = cb.getPackage_category_code();
                                    if (code.contains("!")) {
                                       code = code.substring(code.indexOf("!")); // category code 느낌표 없애기      
                                    }
                              %><span><%=code.replace('!', '#')%></span>
                           </div>
                           <p class="star-rate">
                        <!-- 리뷰 별점 스크립틀릿으로 표시할 부분,  -->
                        <%
                           // 리뷰 별점 평균 표시, 리뷰 개수 표시
                              if (cb.getReview_count() != 0) {
                                 // 10점 만점 평균 별점 반올림 처리
                                 byte avg_int = (byte)Math.round(cb.getReview_star_avg());
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
                        <span><%=cb.getReview_count()%> reviews</span>
                     <%
                        }
                     %>
                     </p>
                        </div>
                     </a>
                  </div>
                  <%
                     }
                  %>
               </div>
            </div>
         </div>
      </div>
   </section>

   <!-- footer 인클루드 -->
   <jsp:include page="/include/footer.jsp" />

   <!-- loader 인클루드 -->
   <jsp:include page="/include/loader.jsp" />

   <script type="text/javascript">
      getRegion();
      function getRegion() {
         //    $('#selectRegion').hide();
         // #selectRegion에 있는 내용 지우기
         $('#selectRegion').empty();
         $('#selectRegion').append(
               "<option id='bg_gray' value=''>지역선택</option>");
         // JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
         $.getJSON('RegionSelect.ma', function(data) {
            $.each(data, function(index, value) {
               $('#selectRegion').append(
                     "<option id='bg_gray' value=" + value.regionCode + "> 지역이름 : "
                           + value.regionName + "</option>");
            });
         });
      }
      // 도시 목록 불러오기
      function getCity() {
         $('#selectCity').empty();
         var code = $('#selectRegion').val();
         $('#selectCity').append(
               "<option id='bg_gray' value=''>도시선택</option>");
         $.getJSON('CitySelect.ma?code=' + code, function(data) {
            $.each(data, function(index, value) {
               $('#selectCity').append(
                     "<option id='bg_gray' value=" + value.cityCode + "> 도시이름 : "
                           + value.cityName + "</option>");
            });
         });
      }
   </script>

</body>
</html>