package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentReplyProService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;

public class CommentReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? -CommentReplyProAction");
		ActionForward forward = null;
		String nowPage= request.getParameter("nowPage");
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		CommentBean comment = new CommentBean();
		comment.setReview_comment_num(Integer.parseInt(request.getParameter("review_comment_num")));
		comment.setReview_comment_member_id(request.getParameter("review_comment_member_id"));
		comment.setReview_comment_member_name(request.getParameter("review_comment_member_name"));
		comment.setReview_comment_review_num(Integer.parseInt(request.getParameter("review_comment_review_num")));
		comment.setReview_comment_content(request.getParameter("review_comment_content"));
		comment.setReview_comment_ref(Integer.parseInt(request.getParameter("review_comment_ref")));
		comment.setReview_comment_lev(Integer.parseInt(request.getParameter("review_comment_lev")));
		comment.setReview_comment_seq(Integer.parseInt(request.getParameter("review_comment_seq")));
		
		System.out.println("ref값 : " + comment.getReview_comment_ref());
		
		CommentReplyProService commentReplyProService = new CommentReplyProService();
		
		boolean isReplySuccess = commentReplyProService.ReplyToComment(comment);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!isReplySuccess) {
			out.print("false");
//			out.println("<script>");
//			out.println("alert('대댓글 등록 실패!')");
//			out.println("history.back()");
//			out.println("</script>");
		}else {
			forward= new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("ReviewDetail.rv?review_num=" + review_num + "&page=" + nowPage);
		}
		
		forward = null;
		return forward;
	}

}
