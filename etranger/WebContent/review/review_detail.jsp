<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// page, ReviewBean 객체 파라미터 가져오기
	String nowPage = (String)request.getParameter("page");
	ReviewBean article = (ReviewBean)request.getAttribute("article");
	
	String sessionId = null;
	if(session.getAttribute("member_id")!=null){
	  sessionId = (String)session.getAttribute("member_id");
	}
	
	//날짜 포맷 판별
	SimpleDateFormat sdfOrigin=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfToday=new SimpleDateFormat("HH 시 mm 분");
	SimpleDateFormat sdfCalculation= new SimpleDateFormat("yyyyMMdd");
	
	Calendar calToday = Calendar.getInstance();
	Calendar calWritingPoint = Calendar.getInstance();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
<style type="text/css">
/* div{ */
/* 	border: 1px solid red; */
/* } */
</style>
  </head>
  <body>
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
  <input type="hidden" id="review_num" name="review_num" value="<%=article.getReview_num()%>"> <!--맨 처음리스트 불러올 때 필요한 값  -->
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_3.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span><a href="blog.html">Blog</a></span> <span>Single Blog</span></p>
              <h1 class="mb-3">Blog details</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section">
			<!--     	본문 내용 들어가는 곳 -->
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate" style="border: 1px solid #f1f1f1;">
			<div id="article-info">
			<h2><%=article.getReview_subject() %></h2>
			<div class="writer"><strong><%=article.getReview_member_name() %></strong> 님</div>
			</div> <!-- article-info끝 -->
			<div id="article-content-wrapper">
			<div class="content-header">
			<div id="article-time">
			<%calWritingPoint.setTime(article.getReview_date());
    		String today = sdfCalculation.format(calToday.getTime());
    		String writingPoint = sdfCalculation.format(calWritingPoint.getTime());
   			String checkedDate=(today.compareTo(writingPoint)==0)?sdfToday.format(article.getReview_date()):sdfOrigin.format(article.getReview_date());%><%=checkedDate %>
			</div>
			<div id="article-readcnt">조회수 : <%=article.getReview_readcount() %></div>
			<div id="article-commentcnt">댓글 : <%=article.getReview_comment_count() %></div>
			</div><!-- content-header끝 -->
			
			<div class="content-stararea">
			<div id="stars"> 
			<%=((double)article.getReview_star()/2.0) + ""%> 점을 받음!
			<%if(article.getReview_star() == 0) {
				%><img src="images/rating0.png" align="middle" /><%
			} else if(article.getReview_star() == 1) {
				%><img src="images/rating01.png" align="middle" /><%
			} else if(article.getReview_star() == 2) {
				%><img src="images/rating02.png" align="middle" /><%
			} else if(article.getReview_star() == 3) {
				%><img src="images/rating03.png" align="middle" /><%
			} else if(article.getReview_star() == 4) {
				%><img src="images/rating04.png" align="middle" /><%
			} else if(article.getReview_star() == 5) {
				%><img src="images/rating05.png" align="middle" /><%
			} else if(article.getReview_star() == 6) {
				%><img src="images/rating06.png" align="middle" /><%
			} else if(article.getReview_star() == 7) {
				%><img src="images/rating07.png" align="middle" /><%
			} else if(article.getReview_star() == 8) {
				%><img src="images/rating08.png" align="middle" /><%
			} else if(article.getReview_star() == 9) {
				%><img src="images/rating09.png" align="middle" /><%
			} else {
				%><img src="images/rating10.png" align="middle" /><%
			}
			%>
			</div>
			</div><!-- content-star끝 -->
			
			<div class="content-view">
<%-- 			<p><img src="reviewUpload/<%=article.getReview_image()%>" style="max-width: 100%; height: auto;"></p> --%>
			<p><%=article.getReview_content() %></p>
			</div><!-- content-view끝 -->
<!-- 			<div class="like-area"> -->
<!-- 			<button type="button" class="btnreview btnreview-outline-success vote-btn"> -->
<!-- 			<i class="fa fa-thumbs-up"></i> 추천 <span id="up">ajax로 뿌려줄 값(누적 추천수)들어갈 곳</span></button> -->
<!-- 			</div>like-area끝 -->
			<div class="content-footer">
			
				<%
   					if(sessionId != null) { // 세션값 아이디 가 있을시
					if(sessionId.equals(article.getReview_member_id())) { // 글쓴이와 일치 할경우
					%>
					<div id="modi-del">
					<a href="ReviewModifyForm.rv?review_num=<%=article.getReview_num()%>&page=<%=nowPage%>" class="btncontrol btncontrol-sm btn-default"><i class="fa fa-pencil-alt"></i>수정</a>
					<a id="review_del_button" href="ReviewDeletePro.rv?review_num=<%=article.getReview_num() %>&review_member_id=<%=sessionId %>&page=<%=nowPage%>" class="btncontrol btnforshare-rv btn-default"><i class="fa fa-trash"></i>삭제</a>
   					</div>
   					<%
					}
					}
					%>
					<a href="ReviewList.rv" class="btncontrol btnforshare-rv btn-default"><i class="fa fa-list"></i>목록</a>
			</div>
			</div><!-- article-content-wrapper끝  -->
			
            <!--     	본문 내용 들어가는 곳  끝-->
            <div class="tag-widget post-tag-container mb-5 mt-5">
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
              </div>
            </div>
			<!-- 댓글시작 -->
            <div class="pt-5 mt-5">
              <jsp:include page="commentlist.jsp"/>
            </div> <!-- 댓글 끝 -->

          </div> <!-- .col-md-8 -->
          <div class="col-md-4 sidebar">
          <!--검색창  -->
            <div class="sidebar-box">
          <form action="ReviewSearch.rv" method="get" class="search-form-detail" name="search">
                <fieldset id="search-fieldset-detail" >
                <input type="text"  id="search_input" class="search_border" name="search" placeholder="Search">
                <button type="submit" id="search_button" ><i class="fa fa-search"></i></button>
                </fieldset>
                </form>
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

        </div>
      </div>
    </section> <!-- .section -->

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
	$.getJSON('ThemeCheckBox.ma', function(data) {

		$.each(data, function(index, value) {
			$('#side_theme').append(
					"<a href='CategoryListSearch.pr?keyword="+ value.themeName +"' class='tag-cloud-link'>" +value.themeName + "</a>");
		});
	});
	
}

</script>
    
  </body>
</html>