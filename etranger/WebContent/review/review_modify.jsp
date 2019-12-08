<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String nowPage = (String)request.getParameter("page");
	ReviewBean article = (ReviewBean) request.getAttribute("article");
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
      <div class="slider-item" style="background-image: url('images/bg_3.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span><a href="blog.html">Blog</a></span> <span>Single Blog</span></p>
              <h1 class="mb-3">Blog details</h1>
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
            <h2 class="mb-5">작성글 수정</h2> <!-- 볼드체 제목 -->
           		<!-- 글 수정 폼 시작 -->
                <form action="ReviewModifyPro.rv" method="post" enctype="multipart/form-data" name="review_write_form" onsubmit="return validCheck()">
                <input type="hidden" name="review_num" value="<%=article.getReview_num() %>">
                <input type="hidden" name="page" value="<%=nowPage%>" />
				<input type="hidden" id="review_package_category_code" name="review_package_category_code" value="1">
				<input type="hidden" id="review_readcount" value=0>
				
                  
                  <div class="form-group">
                    <label for="subject">제목</label>
                    <input type="text" class="form-control" name="subject" id="subject" value="<%=article.getReview_subject()%>"/>
                  </div>
                  <div class="form-group">
                    <label for="message">내용</label>
                    <textarea name="content" id="content" cols="30" rows="20" class="form-control"><%=article.getReview_content() %></textarea>
                  </div>
                  <div class="form-group">
                  
                    <label for="image">이미지 첨부</label>
                    <input name="image" type="file" class="form-control" multiple="multiple" accept="image/*" id="image" value="<%=article.getReview_image()%>"/>
                  </div>
              
                  <div class="form-group">
                    <input type="submit" value="수정" class="btn py-3 px-4 btn-primary">
                    <input type="reset" value="글목록" class="btn py-3 px-4 btn-primary" onclick="location.href='ReviewList.rv'">
                  </div>

                </form>
              </div>
            
            

        </div> <!-- row 끝 -->
      </div><!-- container 끝 -->
    </section> <!-- .section -->

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
<script type="text/javascript">

function validCheck() {
// 	const review_star = $('#review_star').val();
	const subject = $('#subject').val();
	const content = $('#content').val();
	const img = $('#image').val();
	
	if(subject.length<2 || subject.length>30){
		alert('제목이 너무 짧거나 깁니다!');
		return false;
	}if(content.length<10){
		alert('내용은 10글자 이상 적어주세요 :)');
		return false;
	}if(img.length==0){
		alert('썸네일로 사용할 이미지를 업로드해주세요 :)');
		return false;
	}
	
}


// if(review_star==0){
// 	alert('별점을 매겨주세요 :)');  		//종우형님 별점 넣으시면 추가할 것
// 	return false;
// }


</script>    
  </body>
</html>