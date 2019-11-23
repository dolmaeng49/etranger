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
%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
<style type="text/css">
div{
	border: 1px solid red;
}
</style>


  </head>
  <body>
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
    
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
          <div class="col-md-8 ftco-animate">
			<div id="article-info">
			<h2><%=article.getReview_subject() %></h2>
			<div class="writer"><strong><%=article.getReview_member_id() %><!-- 이름으로 교체할 곳 --></strong> 님</div>
			</div> <!-- article-info끝 -->
			<div id="article-content-wrapper">
			<div class="content-header">
			<div id="article-time"><%=article.getReview_date() %></div>
			<div id="article-readcnt">조회수 : <%=article.getReview_readcount() %></div>
			<div id="article-commentcnt">댓글 : <%=article.getReview_comment_count() %></div>
			</div><!-- content-header끝 -->
			<div class="content-stararea">
			<div id="stars">★★★★★</div>
			</div><!-- content-star끝 -->
			<div class="content-view">
<%-- 			<p><img src="reviewUpload/<%=article.getReview_image()%>" style="max-width: 100%; height: auto;"></p> --%>
			<p><%=article.getReview_content() %></p>
			</div><!-- content-view끝 -->
			<div class="like-area">
			<button type="button" class="btnreview btnreview-outline-success vote-btn">
			<i class="fa fa-thumbs-up"></i> 추천 <span id="up"><!-- ajax로 뿌려줄 값(누적 추천수)들어갈 곳--></span></button>
			</div><!-- like-area끝 -->
			<div class="content-footer">
				<%
   					if(sessionId != null) { // 세션값 아이디 가 있을시
					if(sessionId.equals(article.getReview_member_id())) { // 글쓴이와 일치 할경우
					%>
					<div id="modi-del">
					<a href="ReviewModifyForm.rv?review_num=<%=article.getReview_num()%>&page=<%=nowPage%>" class="btncontrol btncontrol-sm btn-default"><i class="fa fa-pencil-alt"></i>수정</a>
					<a href="ReviewDeletePro.rv?review_num=<%=article.getReview_num() %>&review_member_id=<%=sessionId %>&page=<%=nowPage%>" class="btncontrol btnforshare-rv btn-default"><i class="fa fa-trash"></i>삭제</a>
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
            <div class="sidebar-box">
              <form action="#" class="search-form">
                <div class="form-group">
                  <span class="icon fa fa-search"></span>
                  <input type="text" class="form-control" placeholder="Type a keyword and hit enter">
                </div>
              </form>
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

        </div>
      </div>
    </section> <!-- .section -->

	<!-- footer 인클루드 -->
<jsp:include page="../include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="../include/loader.jsp"/>
    
  </body>
</html>