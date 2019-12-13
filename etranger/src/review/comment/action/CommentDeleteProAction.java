package review.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.comment.service.CommentDeleteProService;

public class CommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int review_num = Integer.parseInt(request.getParameter("review_num"));
		int review_comment_num = Integer.parseInt(request.getParameter("review_comment_num"));
		String review_comment_member_id = (String) request.getParameter("review_comment_member_id");
		String page = request.getParameter("page");
		int review_comment_review_num = Integer.parseInt(request.getParameter("review_comment_review_num"));

		
		CommentDeleteProService commentDeleteProService = new CommentDeleteProService();
		boolean isRightUser = commentDeleteProService.isArticleWriter(review_comment_num, review_comment_member_id);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (!isRightUser) {
			out.print("accessDenied");
		} else {
			boolean isDeleteSuccess = commentDeleteProService.removeArticle(review_comment_num,
					review_comment_review_num);

			if (!isDeleteSuccess) {
				out.print("false");
			} else {
				out.print("success");
			}
		}
		return null;
	}

}
