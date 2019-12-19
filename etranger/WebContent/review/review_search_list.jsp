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
	ArrayList<ReviewBean> articleList = (ArrayList<ReviewBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
	
	
	String search = request.getParameter("search");
	
	ReviewBean article = (ReviewBean)request.getAttribute("article");

	String sessionId = null;
	if(session.getAttribute("member_id")!=null){
	  sessionId = (String)session.getAttribute("member_id");
	}
	
// 	날짜 변환 작업

	SimpleDateFormat sdfOrigin=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfToday=new SimpleDateFormat("HH 시 mm 분");
	SimpleDateFormat sdfCalculation= new SimpleDateFormat("yyyyMMdd");
	
	Calendar calToday = Calendar.getInstance();
	
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
              <h1 class="mb-3">여행후기 모음</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->
    
    <!-- 추천여행지 -->
    <section class="ftco-section bg-light">
      <div class="container">
			<%
			if(articleList != null && listCount > 0) {%>
        <div class="row">
			<!--리스트에서 보여지는 게시물 썸네일 한 덩이 시작 -->
				<% for (ReviewBean rb : articleList) {
					String detailURL = "ReviewDetail.rv?review_num=" + rb.getReview_num() + "&page="+nowPage;
				
				%>
          <div class="col-md-6 col-lg-3 ftco-animate">
            <div class="blog-entry" onclick="location.href='ReviewDetail.rv?review_num=<%=rb.getReview_num()%>&page=<%=nowPage %>'">
              <a href="ReviewDetail.rv?review_num=<%=rb.getReview_num()%>&page=<%=nowPage %>" class="block-20" style="background-image: url('reviewUpload/<%=rb.getReview_image()%>');">
              </a>
              <div class="text p-4" >
             <!--  stars --> 
               <div id="stars"> 
			<%if(rb.getReview_star() == 0) {
				%><img src="images/rating0.png" align="middle" /><%
			} else if(rb.getReview_star() == 1) {
				%><img src="images/rating01.png" align="middle" /><%
			} else if(rb.getReview_star() == 2) {
				%><img src="images/rating02.png" align="middle" /><%
			} else if(rb.getReview_star() == 3) {
				%><img src="images/rating03.png" align="middle" /><%
			} else if(rb.getReview_star() == 4) {
				%><img src="images/rating04.png" align="middle" /><%
			} else if(rb.getReview_star() == 5) {
				%><img src="images/rating05.png" align="middle" /><%
			} else if(rb.getReview_star() == 6) {
				%><img src="images/rating06.png" align="middle" /><%
			} else if(rb.getReview_star() == 7) {
				%><img src="images/rating07.png" align="middle" /><%
			} else if(rb.getReview_star() == 8) {
				%><img src="images/rating08.png" align="middle" /><%
			} else if(rb.getReview_star() == 9) {
				%><img src="images/rating09.png" align="middle" /><%
			} else {
				%><img src="images/rating10.png" align="middle" /><%
			}
			%>
			</div>
            <!-- end stars --> 
                <div class="meta" onclick="<%=detailURL%>">
                  <div><a href="<%=detailURL%>"><%=rb.getReview_member_name() %></a></div>
                </div>
                <h3 class="heading"><a href="<%=detailURL%>"><%=rb.getReview_subject()%></a></h3>
                <p class="clearfix">
                  <a href="<%=detailURL%>" class="float-left">
                  <% 
                  Calendar calWritingPoint = Calendar.getInstance();
                  	calWritingPoint.setTime(rb.getReview_date());
    				String today = sdfCalculation.format(calToday.getTime());
    				String writingPoint = sdfCalculation.format(calWritingPoint.getTime());
   					String checkedDate=(today.compareTo(writingPoint)==0)?sdfToday.format(rb.getReview_date()):sdfOrigin.format(rb.getReview_date());%><%=checkedDate %></a>
                  <a href="<%=detailURL%>" class="float-right meta-chat"><span class="icon-chat"></span><%=rb.getReview_comment_count() %> </a>
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
             <div class="tagcloud">
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
                <a href="#" class="tag-cloud-link">Life</a>
                <a href="#" class="tag-cloud-link">Sport</a>
                <a href="#" class="tag-cloud-link">Tech</a>
                <a href="#" class="tag-cloud-link">Travel</a>
              
              <form action="ReviewSearch.rv" method="get" class="search-form-list" name="search">
                <fieldset id="search_fieldset" >
                <input type="text"  id="search_input" name="search" placeholder="Search"  style="width: 100px;">
                <button type="submit" id="search_button" ><i class="fa fa-search""></i></button>
                </fieldset>
                </form>
            </div>
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
            {%><li><a href="ReviewList.rv?page=<%=nowPage -1%>">&lt;</a></li><%} %>
            	<%for(int i=startPage; i<=endPage; i++) {
            	if(i== nowPage) {%><li class="active"><span><%=i %></span></li>
            	<%}else{%>
                <li><a href="ReviewList.rv?page=<%=i%>"><%=i %></a></li>
                <%}%>
                <%}%>
                <%if(nowPage>=maxPage){%><li><a>&gt;</a></li>
                <%}else{ %>
                <li><a href="ReviewList.rv?page=<%=nowPage +1%>">&gt;</a></li>
                <%} %>
              </ul>
            </div>
          </div>
        </div>
        <!-- 페이지 부분 -->
	<%}else{%><div id="emptyArticle">검색결과와 일치하는 리뷰가 없습니다.</div><%} %>
      </div> <!-- <div class="container">의 끝 -->
      <div align="right"><input type="button" id="review_write" value="리뷰 작성" class="btn py-3 px-4 btn-primary"></div>
    </section>
<!--     <li class="active"><span>1</span></li> -->

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
<script type="text/javascript">
	var sessionId = $('#session_id').val();
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