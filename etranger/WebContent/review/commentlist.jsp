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
<h4 class="rv-5"><%=article.getReview_comment_count()%> Comments</h4>
              <ul class="comment-list">
                <%
                if(commentList != null){%>
                  <%for(int i = 0; i < commentList.size(); i++) {%>
                  <%if(article.getReview_num()==commentList.get(i).getReview_comment_review_num()){%>
                  <%if(commentList.get(i).getReview_comment_lev()>0) { %>
                  <ul class="children">
                  <li class="comment-re" id="cr<%=commentList.get(i).getReview_comment_num()%>">
                  <%}else {%>
                <li class="comment-parent" id="cp<%=commentList.get(i).getReview_comment_num()%>"><%} %>
                  <div class="vcard bio" >
                    <img src="images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body" id="cb<%=commentList.get(i).getReview_comment_num()%>">
                    <h3><%=commentList.get(i).getReview_comment_member_name()%></h3>
                    <div class="meta"><%=commentList.get(i).getReview_comment_date() %></div>
                    <p><%=commentList.get(i).getReview_comment_content()%></p>
                    <!-- 대댓글 위해 값 넘겨주는 작업 -->
                  	<input type="hidden" id="nowPage" name="nowPage" value=<%=nowPage %>>
                    <form action="#">
                  	<input type="hidden" id="review_num" name="review_num" value="<%=article.getReview_num()%>">
              		<input type="hidden" id="review_comment_member_id" name="review_comment_member_id"value="<%=sessionId %>">
              		<input type="hidden" id="review_comment_member_name" name="review_comment_member_name" value="<%=sessionName %>"> <!-- 임시 -->
              		<input type="hidden" id="review_comment_review_num" name="review_comment_review_num" value="<%=article.getReview_num() %>">
              		<input type="hidden" id="review_comment_num" name="review_comment_num" value="<%=commentList.get(i).getReview_comment_num()%>">
              		<input type="hidden" id="review_comment_ref" name="review_comment_ref" value="<%=commentList.get(i).getReview_comment_num()%>">
              		<input type="hidden" id="review_comment_lev" name="review_comment_lev" value="<%=commentList.get(i).getReview_comment_lev()%>">
              		<input type="hidden" id="review_comment_seq" name="review_comment_seq" value="<%=commentList.get(i).getReview_comment_seq()%>">
              		<%if(commentList.get(i).getReview_comment_lev()<1) { %>
              		<input type="button" class="reply" value="Reply" onclick="replyComment('<%=commentList.get(i).getReview_comment_num() %>')"><%} %>
                </form>
                <input type="hidden" id="cmt_num" value="<%=commentList.get(i).getReview_comment_num()%>">
                <input type="hidden" id="re_cmt_m_name" value="<%=sessionName%>">
                <input type="hidden" id="re_cmt_m_id" value="<%=commentList.get(i).getReview_comment_member_id() %>">
                <%if(sessionId!=null){ %>
                <%if(sessionId.equals(commentList.get(i).getReview_comment_member_id())){ %>
                	<a class="comment_update_btn" href="#comment_update_btn" onclick="updateComment('<%=commentList.get(i).getReview_comment_num() %>')">수정</a>
<%--                     <p><a href="CommentModifyForm.cm?review_num=<%=article.getReview_num()%>&review_comment_num=<%=commentList.get(i).getReview_comment_num()%>&page=<%=nowPage%>">수정</a> --%>
                    <a class="comment_del_btn" href="#comment_del_btn" onclick="deleteComment('<%=commentList.get(i).getReview_comment_num() %>')">삭제</a>
                  	<%} }%>
                  </div>
                  <div id="forReply<%=commentList.get(i).getReview_comment_num()%>"></div>
                </li>
                
                  <%if(commentList.get(i).getReview_comment_lev()>0) { %>
                  </ul><%} %>
                  <% } 
                  	 }
                  }%>
              </ul>
              </div>
              <!-- END comment-list -->
              
              <%if(sessionId != null) {%>
              <div class="comment-form-wrap">
              	<!-- 댓글 작성 폼 -->
                <h3>코멘트를 남겨주세요!</h3>
                    <label for="name"><%=sessionId %>님</label>
<!--                 <form action="CommentWrite.cm" class="p-5 bg-light"> -->
                  <div class="form-group">
                  	<input type="hidden" id="nowPage" name="nowPage" value="<%=nowPage %>">
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
      <script src="js/jquery.min.js"></script> <!-- 작업용 임시 로드 -->
      <script type="text/javascript">
      
      

      
      </script>
      
              