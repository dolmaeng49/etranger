package comment.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import comment.service.CommentListService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;

public class CommentListAjax implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		int page = 1;
		int limit = 10;
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String sessionId = request.getParameter("sessionId");
		String sessionName = request.getParameter("sessionName");
		System.out.println("review_num :" +review_num); //값 넘어오는지 확인
		System.out.println("sessionId :" +sessionId);
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		CommentListService commentListService = new CommentListService();
		int listCount = commentListService.getCommentCount();
		
		ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
		commentList =commentListService.getCommentList();
		
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article =reviewDetailService.getArticle(review_num);
		
		
		//페이징
		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		int nowPage = pageInfo.getPage();
		maxPage = pageInfo.getMaxPage();
		startPage = pageInfo.getStartPage();
		endPage = pageInfo.getEndPage();
		listCount = pageInfo.getListCount();
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		<ul class="comment-list"> 아래부터 채워넣을 내용.
		
//		<div id="forInsert">아래부터.
		out.print("<h4 class=\"rv-5\">"+article.getReview_comment_count()+" Comments</h4>");
		out.print("<ul class=\"comment-list\">");
		if(commentList!=null) {
			for(int i =0; i < commentList.size(); i++) {
				if(article.getReview_num()==commentList.get(i).getReview_comment_review_num()) {
					if(commentList.get(i).getReview_comment_lev()>0) {
						out.print("<ul class=\"children\">");
						out.print("<li class=\"comment-re\" id=cr"+commentList.get(i).getReview_comment_num()+">");
					}else {
						out.print("<li class=\"comment-parent\" id=cp"+commentList.get(i).getReview_comment_num()+">");
					}
					String print1="";
					print1+="<div class=\"vcard bio\" >"
							+"<img src=\"images/person_1.jpg\" alt=\"Image placeholder\"></div>"
							+"<div class=\"comment-body\" id=cb"+commentList.get(i).getReview_comment_num()+"><h3>"+commentList.get(i).getReview_comment_member_name()+"</h3>"
							+"<div class=\"meta\">"+commentList.get(i).getReview_comment_date()+"</div>"
							+"<p>"+commentList.get(i).getReview_comment_content()+"</p>"
							+"<input type=\"hidden\" id=\"nowPage\" name=\"nowPage\" value="+nowPage+">"
							+"<form action=\"CommentReplyForm.cm\">"
							+"<input type=\"hidden\" id=\"review_num\" name=\"review_num\" value="+article.getReview_num()+">"
							+"<input type=\"hidden\" id=\"review_comment_member_id\" name=\"review_comment_member_id\"value="+sessionId+">"
							+"<input type=\"hidden\" id=\"review_comment_member_name\" name=\"review_comment_member_name\" value="+sessionName+">"
							+"<input type=\"hidden\" id=\"review_comment_review_num\" name=\"review_comment_review_num\" value="+article.getReview_num()+">"
							+"<input type=\"hidden\" id=\"review_comment_num\" name=\"review_comment_num\" value="+commentList.get(i).getReview_comment_num()+">"
							+"<input type=\"hidden\" id=\"review_comment_ref\" name=\"review_comment_ref\" value="+commentList.get(i).getReview_comment_num()+">"
							+"<input type=\"hidden\" id=\"review_comment_lev\" name=\"review_comment_lev\" value="+commentList.get(i).getReview_comment_lev()+">"
							+"<input type=\"hidden\" id=\"review_comment_seq\" name=\"review_comment_seq\" value="+commentList.get(i).getReview_comment_seq()+">";
						out.print(print1);
						if(commentList.get(i).getReview_comment_lev()<1) {
							out.print("<input type=\"submit\" class=\"reply\" value=\"Reply\">");
						}
						String print2="";
						print2+="</form><input type=\"hidden\" id=\"cmt_num\" value="+commentList.get(i).getReview_comment_num()+">"
								+"<input type=\"hidden\" id=\"re_cmt_m_name\" value="+sessionName+">"
								+"<input type=\"hidden\" id=\"re_cmt_m_id\" value="+commentList.get(i).getReview_comment_member_id()+">";
								out.print(print2);
								if(sessionId!=null) {
									if(sessionId.equals(commentList.get(i).getReview_comment_member_id())){
						String print3="";
						print3+="<a class=\"comment_update_btn\" href=\"#comment_update_btn\" onclick=\"updateComment('"+commentList.get(i).getReview_comment_num()+"')\">수정</a>"
								+"<a class=\"comment_del_btn\" href=\"#comment_del_btn\" onclick=\"deleteComment('"+commentList.get(i).getReview_comment_num()+"')\">삭제</a>";
								out.print(print3);
									}
								}
						out.print("</div></li>");
						if(commentList.get(i).getReview_comment_lev()>0) {
						out.print("</ul>");	
						}
				}
			}
		}
						out.print("</ul>");

		
						
		return null;
	}

}
