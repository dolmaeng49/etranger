<%@page import="comment.vo.CommentBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.vo.PageInfo"%>
<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<CommentBean> commentList = (ArrayList<CommentBean>)request.getAttribute("commentList");
	
	// page, ReviewBean 객체 파라미터 가져오기
	String nowPage = (String)request.getParameter("page"); // 댓글쓰기 시 ReviewDetailAction으로 넘겨줄 현재 페이지
	ReviewBean article = (ReviewBean)request.getAttribute("article");
	String sessionId = null;
	if(session.getAttribute("member_id")!=null){
	  sessionId = (String)session.getAttribute("member_id");
	}
	String sessionName = null;
	if(session.getAttribute("member_name")!=null){
	  sessionName = (String)session.getAttribute("member_name");
	}
	
%>
<!-- ajax 송신 위한 값 저장 -->
<input type="hidden" id="sessionName" value=<%=sessionName %>>
<input type="hidden" id="sessionId" value=<%=sessionId %>>
<input type="hidden" id="nowPage" value=<%=nowPage %>>
<input type="hidden" id="cmt_re_num" value=<%=article.getReview_num() %>>


    <!-- 확인차 text. 나중에 hidden 처리 -->
    
              <div id="forInsert">

              </div>
              <!-- END comment-list -->
              <%if(sessionId != null) {%>
              <div class="comment-form-wrap">
              	<!-- 댓글 작성 폼 -->
                <h3>코멘트를 남겨주세요!</h3>
                    <label for="name"><%=sessionId %>님</label>
<!--                 <form action="CommentWrite.cm" class="p-5 bg-light"> -->
                  <div class="form-group">
              		<input type="hidden" id="review_comment_member_id" name="review_comment_member_id"value="<%=sessionId %>">
              		<input type="hidden" id="review_comment_member_name" name="review_comment_member_name" value="<%=sessionName%>">
                    <textarea name="review_comment_content" id="review_comment_content" cols="30" rows="5" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="button" value="댓글쓰기" id="comment_add_btn" class="btn py-3 px-4 btn-primary">
                  </div>
<!--                 </form> -->
              </div>
              <%}else{%>
              <h3 class="mb-5" align="center">코멘트를 남기시려면 로그인 해주세요!</h3>
              <%}%>
<!----------------------------------------------------------------------------------------->
      <script src="js/jquery.min.js"></script> 
      <script type="text/javascript">
      $(document).ready(function(){				//창 열릴때 함수호출
    		getCommentList();
    	});
      </script>
      
              