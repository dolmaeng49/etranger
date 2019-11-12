<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// //현재 세션 객체에 "sId" 세션값이 존재하지 않을 경우
// 	// alert 창에 "로그인이 필요합니다" 출력 후 LoginForm.me 로 이동
// 	String sid= null; 
// 	if(session.getAttribute("sid") !=null) {
// 		sid=(String)session.getAttribute("sid");
// 	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
  </head>
<%-- <body onload="checkSession(<%=sid%>)"> --%>  
  <body>
    
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
    
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
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">여행후기 작성</h3>
                <form action="ReviewWritePro.rv" method="post" enctype="multipart/form-data" name="review_write_form">
                <input type="hidden" id="review_num" value=0>
				<input type="hidden" id="review_package_category_code" name="review_package_category_code" value="1">
				<input type="hidden" id="review_readcount" value=0>
				
                  <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="review_member_id"id="review_member_id"/>
                  </div>
<!--                   <div class="form-group"> -->
<!--                     <label for="email">Email</label> -->
<!--                     <input type="text" class="form-control"> -->
<!--                   </div> -->
                  <div class="form-group">
                    <label for="website">Subject</label>
                    <input type="text" class="form-control" name="subject" id="subject"/>
                  </div>
                  <div class="form-group">
                    <label for="image">이미지 첨부</label>
                    <input name="image" type="file" class="form-control" multiple="multiple" accept="image/*" id="image"/>
                  </div>
                  <div class="form-group">
                    <label for="message">Message</label>
                    <textarea name="content" id="content" cols="30" rows="20" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="등록" class="btn py-3 px-4 btn-primary">
                    <input type="reset" value="다시쓰기" class="btn py-3 px-4 btn-primary">
                    <input type="button" value="뒤로가기" class="btn py-3 px-4 btn-primary" onclick="history.back()">
                    <input type="submit" value="Join" class="btn py-3 px-4 btn-primary">
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