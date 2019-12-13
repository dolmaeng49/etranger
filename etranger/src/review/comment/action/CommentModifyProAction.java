package review.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.comment.service.CommentModifyProService;
import review.comment.vo.CommentBean;

public class CommentModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? -CommentModifyProAction");
		request.setCharacterEncoding("utf-8");
		CommentBean cb = null;
		
		int comment_num = Integer.parseInt(request.getParameter("review_comment_num"));
		
		cb = new CommentBean();
		cb.setReview_comment_num(comment_num);
		cb.setReview_comment_content(request.getParameter("review_comment_content"));
		CommentModifyProService commentModifyProService = new CommentModifyProService();
		
		boolean isModifySuccess = commentModifyProService.modifyComment(cb);
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String page = request.getParameter("page");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		if(!isModifySuccess) {
			out.print("false");
		} else {
			out.print("success");
		}
		
		return null;
		
	}

}
