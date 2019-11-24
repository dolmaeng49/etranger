<%@page import="javafx.geometry.Side"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
</head>
  <body>
    
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
<%
String sessionId = (String)session.getAttribute("member_id");
String sessionName = (String)session.getAttribute("member_name");
if(sessionId==null) {
	response.sendRedirect("LoginForm.me");
}
%>
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_3.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span><a href="blog.html">Blog</a></span> <span>Single Blog</span></p>
              <h1 class="mb-3">여행후기 작성</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate">
	
             <!--  글 작성 폼 시작 -->
              <div class="comment-form-wrap">
                <h3 class="mb-5">여행후기 작성</h3>
                <div style="height:90px"></div>
                <form action="ReviewWritePro.rv" method="post" enctype="multipart/form-data" name="review_write_form">
                <input type="hidden" id="review_num" value=0>
				<input type="hidden" id="review_package_category_code"  name="review_package_category_code" value="1">
				<!-- input type 내에서 왜 id값의 value로 문자열 인식이 안되는지 알아보기 -->
				<input type="hidden" id="review_readcount" value=0>
				<input type="hidden" id="review_star" value=0>
				<input type="hidden" name="review_member_id" value=<%=sessionId %>>
				<input type="hidden" name="review_member_name" value="tester"> <!-- 임시방편 -->
<%-- 				<input type="hidden" name="review_member_name" value=<%=sessionName %>> --%>
                  <div class="writeform-group">
                    <input type="text" class="form-control-subject" name="review_subject" id="review_subject" maxlength="40" placeholder="제목을 입력해주세요"/>
                  </div>
                  <div class="writeform-group">
                  <div style="height:20px"></div>
                  <div class="starRev">
  					<span class="starR1 on">1</span>
  					<span class="starR2">2</span>
  					<span class="starR1">3</span>
  					<span class="starR2">4</span>
  					<span class="starR1">5</span>
 					<span class="starR2">6</span>
 					<span class="starR1">7</span>
  					<span class="starR2">8</span>
 					<span class="starR1">9</span>
  					<span class="starR2">10</span>
				   </div>
                    <div style="height:50px"></div>
                    <textarea id="summernote" name="review_content" cols="30" rows="20" class="form-control"></textarea>
                  </div>
                  <div class="writeform-group">
                    <label for="image">이미지 첨부</label>
                    <div id="test"></div>
                    <input name="review_image" type="file" class="form-control" multiple="multiple" accept="image/*" id="review_image"/>
                  </div>
                  <div class="form-group"> 
                    <input type="submit" value="등록" class="btn py-3 px-4 btn-primary">
                    <input type="button" value="취소" class="btn py-3 px-4 btn-primary" onclick="history.back()"> <!-- 나중에 팝업띄워줄 것 -->
                  </div>

                </form>
              </div>
<!--             </div> -->

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