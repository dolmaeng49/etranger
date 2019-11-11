<%@page import="review.vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 전달받은 request 객체로부터 데이터 가져오기
	// "pageInfo" 객체와 "articleList" 객체를 request 객체로부터 꺼내서 저장
	// "pageInfo" 객체로부터 페이지 관련 값들을 꺼내서 변수에 저장
	ArrayList<ReviewBean> articleList = (ArrayList<ReviewBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
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
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('../images/bg_1.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="../main/index.jsp">Home</a></span></p>
              <h1 class="mb-3">여행후기 모음</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section bg-light">
			<%
			if(articleList != null && listCount > 0) {%>
      <div class="container">
        <div class="row">
			<!--리스트에서 보여지는 게시물 썸네일 한 덩이 시작 -->
				<%for(int i=0; i<articleList.size(); i++) {%>
          <div class="col-md-6 col-lg-3 ftco-animate">
            <div class="blog-entry">
              <a href="ReviewDetail.rv?review_num=<%=articleList.get(i).getReview_num() %>&page=<%=nowPage %>" class="block-20" style="background-image: url('reviewUpload/<%=articleList.get(i).getReview_image()%>');">
              </a>
              <div class="text p-4">
                <div class="meta">
                  <div><a href="#"><%=articleList.get(i).getReview_date()%></a></div>
                  <div><a href="#"><%=articleList.get(i).getReview_member_id()%></a></div>
                </div>
                <h3 class="heading"><a href="#"><%=articleList.get(i).getReview_content()%></a></h3>
                <p class="clearfix">
                  <a href="#" class="float-left">Read more</a>
                  <a href="#" class="float-right meta-chat"><span class="icon-chat"></span> 3</a>
                </p>
              </div>
            </div>
          </div>
				<%}
			}
			%>
          <!-- 리스트에서 보여지는 게시물 썸네일 한 덩이 끝 -->
        </div> <!-- <div class="row">의 끝 -->
		<!-- 페이지 부분 -->
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
        <!-- 페이지 부분 -->
      </div> <!-- <div class="container">의 끝 -->
    </section>

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
    
  </body>
</html>