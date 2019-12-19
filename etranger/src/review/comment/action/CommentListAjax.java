package review.comment.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import review.comment.service.CommentListService;
import review.comment.vo.CommentBean;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;

public class CommentListAjax implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String sessionId = (String)request.getSession().getAttribute("member_id");
//		String sessionName=(String)request.getSession().getAttribute("member_name");
		String sessionName = request.getParameter("sessionName");

		CommentListService commentListService = new CommentListService();
		int listCount = commentListService.getCommentCount();

		ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
		commentList = commentListService.getCommentList();

		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article = reviewDetailService.getArticleForComment(review_num);

		PageInfo pageInfo = new PageInfo();
		int nowPage = pageInfo.getPage();
		
		SimpleDateFormat sdfComment= new SimpleDateFormat("yyyy-MM-dd일 HH시 mm분");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

//		<ul class="comment-list"> 아래부터 채워넣을 내용.

//		<div id="forInsert">아래부터.
		out.print("<h4 class=\"rv-5\">" + article.getReview_comment_count() + " Comments</h4>");
		out.print("<ul class=\"comment-list\">");
		if (commentList != null) {
			for (int i = 0; i < commentList.size(); i++) {
				if (article.getReview_num() == commentList.get(i).getReview_comment_review_num()) {
					if (commentList.get(i).getReview_comment_lev() > 0) {
						out.print("<ul class=\"children\">");
						
						out.print("<a href=\"#\" class=\"rereply-btn\"><i class=\"fa fa-share\"></i></a><li class=\"comment-re\" id=cr" + commentList.get(i).getReview_comment_num() + ">");
					} else {
						out.print("<li class=\"comment-parent\" id=cp" + commentList.get(i).getReview_comment_num()
								+ ">");
					}
					String print1 = "";
					print1 += "<div class=\"vcard bio\">"
							+ "<img src=\"images/comment1.jpg\" alt=\"Image placeholder\"></div>"
							+ "<div class=\"comment-body\" id=cb" + commentList.get(i).getReview_comment_num() + "><h3>"
							+ commentList.get(i).getReview_comment_member_name() + "</h3>" + "<div class=\"meta\">"
							+ sdfComment.format(commentList.get(i).getReview_comment_date()) + "</div>" + "<p>"
							+ commentList.get(i).getReview_comment_content() + "</p>"
							+ "<input type=\"hidden\" id=\"nowPage\" name=\"nowPage\" value=" + nowPage + ">"
							+ "<form action=\"CommentReplyForm.cm\">"
							+ "<input type=\"hidden\" id=\"review_num\" name=\"review_num\" value="
							+ article.getReview_num() + ">"
							+ "<input type=\"hidden\" id=\"review_comment_member_id\" name=\"review_comment_member_id\"value="
							+ sessionId + ">"
							+ "<input type=\"hidden\" id=\"review_comment_member_name\" name=\"review_comment_member_name\" value="
							+ sessionName + ">"
							+ "<input type=\"hidden\" id=\"review_comment_review_num\" name=\"review_comment_review_num\" value="
							+ article.getReview_num() + ">"
							+ "<input type=\"hidden\" id=\"review_comment_num\" name=\"review_comment_num\" value="
							+ commentList.get(i).getReview_comment_num() + ">"
							+ "<input type=\"hidden\" id=\"review_comment_ref\" name=\"review_comment_ref\" value="
							+ commentList.get(i).getReview_comment_num() + ">"
							+ "<input type=\"hidden\" id=\"review_comment_lev\" name=\"review_comment_lev\" value="
							+ commentList.get(i).getReview_comment_lev() + ">"
							+ "<input type=\"hidden\" id=\"review_comment_seq\" name=\"review_comment_seq\" value="
							+ commentList.get(i).getReview_comment_seq() + ">";
					out.print(print1);
					System.out.println(sessionId);
					System.out.println(sessionName);
					if (sessionId != null) {
					if (commentList.get(i).getReview_comment_lev() < 1) {
						out.print("<a class=\"comment_reply_btn\" href=\"#comment_reply_btn\" onclick=\"replyComment('"
								+ commentList.get(i).getReview_comment_num() + "'); this.onclick='';\"><i class=\"fab fa-replyd\">Re</i></a>");
						}
					}
					String print2 = "";
					print2 += "</form><input type=\"hidden\" id=\"cmt_num\" value="
							+ commentList.get(i).getReview_comment_num() + ">"
							+ "<input type=\"hidden\" id=\"re_cmt_m_name\" value=" + sessionName + ">"
							+ "<input type=\"hidden\" id=\"re_cmt_m_id\" value="
							+ commentList.get(i).getReview_comment_member_id() + ">";
					out.print(print2);
					if (sessionId != null) {
						if (sessionId.equals(commentList.get(i).getReview_comment_member_id())) {
							String print3 = "";
							print3 += "<div id=\"modi_delete\"><a class=\"comment_update_btn\" href=\"#comment_update_btn\" onclick=\"updateComment('"
									+ commentList.get(i).getReview_comment_num() + "','"
									+ commentList.get(i).getReview_comment_content() + "')\"><i class=\"fas fa-pencil-alt\"></i>수정</a>&emsp;"
									+ "<a class=\"comment_del_btn\" href=\"#comment_del_btn\" onclick=\"deleteComment('"
									+ commentList.get(i).getReview_comment_num() + "')\"><i class=\"fas fa-trash\"></i>삭제</a></div>";
							out.print(print3);
						}
					}
					out.print("</div><div id=\"forReply" + commentList.get(i).getReview_comment_num() + "\"></div>");
					out.print("</li>");
					if (commentList.get(i).getReview_comment_lev() > 0) {
						out.print("</ul>");
					}
				}
			}
		}
		out.print("</ul>");
		out.print("</div>");

		return null;
	}

}
