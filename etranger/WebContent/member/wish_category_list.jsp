<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="common.vo.PageInfo"%>
<%@page import="review.dao.ReviewDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 전달받은 request 객체로부터 데이터 가져오기
	// "pageInfo" 객체와 "articleList" 객체를 request 객체로부터 꺼내서 저장
	// "pageInfo" 객체로부터 페이지 관련 값들을 꺼내서 변수에 저장
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
	
	CategoryBean[] cbArr = (CategoryBean[])request.getAttribute("categoryList");
	
	String sessionId = null;
	if(session.getAttribute("member_id")!=null){
	  sessionId = (String)session.getAttribute("member_id");
	}
	
%>  
<!DOCTYPE html>
<html>
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp"/>
  </head>
  <body>
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="/include/top_menu.jsp"/>
<input type="hidden" id="session_Id" value=<%=sessionId %>>

    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_1.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="../main/index.jsp">Home</a></span></p>
              <h1 class="mb-3">찜 목록</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->
    
    <!-- 추천여행지 -->
    <section class="ftco-section bg-light">
      <div class="container">
        <div class="row">
			<%
			if(cbArr.length != 0 && listCount > 0) {%>
			<!--리스트에서 보여지는 게시물 썸네일 한 덩이 시작 -->
				<% for (CategoryBean cb : cbArr) {
					if(cb == null){
						// CategoryBean 의 내용이 비어있을 경우 반복문 밖으로
						break;
					}
					String detailURL = "CategoryDetail.pr?package_category_code=" + cb.getPackage_category_code();
				
				%>
          <div class="col-md-6 col-lg-3 ftco-animate">
            <div class="blog-entry" onclick="location.href='<%=detailURL %>'">
              <a href="<%=detailURL%>" class="block-20" style="background-image: url('ManagerImgUpload/<%=cb.getPackage_category_image().split("\\*")[0]%>');">
              </a>
              <div class="text p-4" >
             <!--  stars --> 
               <div id="stars"> 
			<%if(cb.getReview_star_avg() > 0) {
			double review_star = cb.getReview_star_avg() / 2;
			if(review_star < 0.25) {
				%><img src="images/rating0.png" align="middle" /><%
			} else if(review_star < 0.75) {
				%><img src="images/rating01.png" align="middle" /><%
			} else if(review_star <  1.25) {
				%><img src="images/rating02.png" align="middle" /><%
			} else if(review_star < 1.75) {
				%><img src="images/rating03.png" align="middle" /><%
			} else if(review_star < 2.25) {
				%><img src="images/rating04.png" align="middle" /><%
			} else if(review_star < 2.75) {
				%><img src="images/rating05.png" align="middle" /><%
			} else if(review_star < 3.25) {
				%><img src="images/rating06.png" align="middle" /><%
			} else if(review_star < 3.75) {
				%><img src="images/rating07.png" align="middle" /><%
			} else if(review_star < 4.25) {
				%><img src="images/rating08.png" align="middle" /><%
			} else if(review_star < 4.75) {
				%><img src="images/rating09.png" align="middle" /><%
			} else {
				%><img src="images/rating10.png" align="middle" /><%
			}}
			%>
			</div>
            <!-- end stars --> 
                <div class="meta" onclick="<%=detailURL%>">
                  <div><a href="<%=detailURL%>">￦<%=cb.getMin_price() %>~</a></div>
                </div>
                <h3 class="heading"><a href="<%=detailURL%>"><%=cb.getPackage_category_name()%></a></h3>
                <p class="clearfix">
                  <a href="<%=detailURL%>" class="float-right meta-chat"><span class="icon-chat"></span><%if(cb.getReview_star_avg() > 0) {%><%=cb.getReview_count() %><%} %> </a>
                </p>
              </div>
            </div>
          </div>
				<%}%>
          <!-- 리스트에서 보여지는 게시물 썸네일 한 덩이 끝 -->
        </div> <!-- <div class="row">의 끝 -->
        
        	<!-- 게시글 검색 & 태그-->
        	<!-- 검색창 축소 및 태그 검색창 정렬 작업 -->
            <div class="sidebar-box-list">
            <div>
            </div>
            </div>
      <!-- end 게시글 검색 -->
        
		<!-- 페이지 부분 -->
        <div class="row mt-5">
          <div class="col text-center">
            <div class="block-27">
              <ul>
            <%if(nowPage<=1){%>
            <li><a>&lt;</a></li>
            <%}else
            {%><li><a href="WishCategoryList.me.rv?page=<%=nowPage -1%>">&lt;</a></li><%} %>
            	<%for(int i=startPage; i<=endPage; i++) {
            	if(i== nowPage) {%><li class="active"><span><%=i %></span></li>
            	<%}else{%>
                <li><a href="WishCategoryList.me.rv?page=<%=i%>"><%=i %></a></li>
                <%}%>
                <%}%>
                <%if(nowPage>=maxPage){%><li><a>&gt;</a></li>
                <%}else{ %>
                <li><a href="WishCategoryList.me.rv?page=<%=nowPage +1%>">&gt;</a></li>
                <%} %>
              </ul>
            </div>
	<%}else{%><div id="emptyArticle">해당하는 상품이 없습니다</div><%} %>
          </div>
        </div>
        <!-- 페이지 부분 -->
      </div> <!-- <div class="container">의 끝 -->
    </section>

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
<script type="text/javascript">
	var sessionId = $('#session_Id').val();
$('#review_write').click(function () {
		if(sessionId=="null"){
			alert('먼저 로그인 해주세요!');
			return false;
		}else{
			location.href="./ReservationInfo.rs";
		}
		
	});
    
$('h3.heading').each(function(){
	  var length = 18; //표시할 글자수 정하기
	  var iframe = "</iframe>"
	  //전체 옵션을 자를 경우
	  $(this).each(function(){
	   if( $(this).text().length >= length ){
	    $(this).text($(this).text().substr(0,length)+'...'); //지정한 글자수 이후 표시할 텍스트(...)
	   }
	  });
	  
	 });
</script>
  </body>
</html>