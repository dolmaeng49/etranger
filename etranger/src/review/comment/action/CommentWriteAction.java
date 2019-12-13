package review.comment.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import review.comment.service.CommentListService;
import review.comment.service.CommentWriteProService;
import review.comment.vo.CommentBean;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		CommentBean cb = null;

		request.setCharacterEncoding("UTF-8");
		cb = new CommentBean();
		cb.setReview_comment_member_id(request.getParameter("review_comment_member_id"));
		cb.setReview_comment_member_name(request.getParameter("review_comment_member_name"));
		cb.setReview_comment_review_num(Integer.parseInt(request.getParameter("review_comment_review_num")));
		cb.setReview_comment_content(request.getParameter("review_comment_content"));
		CommentWriteProService commentWriteProService = new CommentWriteProService();

		boolean isAddSuccess = commentWriteProService.AddComment(cb);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (!isAddSuccess) { // 실패시
			out.print("false");

		} else { // 성공시
			CommentListService commentListService = new CommentListService();
			int listCount = commentListService.getCommentCount();

			ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
			commentList = commentListService.getCommentList();
		}

		return null;
	}
}
