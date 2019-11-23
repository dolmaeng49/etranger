<%@page import="review.vo.ReviewBean"%>
<%@page import="comment.vo.CommentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 String nowPage = (String)request.getParameter("page");
	CommentBean comment = (CommentBean) request.getAttribute("comment");
	ReviewBean article = (ReviewBean) request.getAttribute("article");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp"/>
  </head>
  <body>
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="/include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('../images/bg_3.jpg');" data-stellar-background-ratio="0.5">
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
              <!-- comment 작성폼 div -->
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">댓글 수정</h3>
                <form action="CommentModifyPro.cm" method="post" class="p-5 bg-light">
                  <div class="form-group">
                  <input type="hidden" name="page" value="<%=nowPage%>" />
                  <input type="hidden" name="review_num" value="<%=article.getReview_num() %>">
                  <input type="hidden" name="review_comment_num" value="<%=comment.getReview_comment_num()%>">	
                    <label for="message">내용</label>
                    <textarea name="review_comment_content" id="review_comment_content" cols="30" rows="10" class="form-control"><%=comment.getReview_comment_content() %></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="댓글 수정" class="btn py-3 px-4 btn-primary">
                  </div>
                </form>
              </div>
              <!-- comment 작성폼 div -->
          <!-- 코멘트 전체 패널 끝-->

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
    
  </body>
</html>