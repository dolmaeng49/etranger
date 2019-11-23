package comment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentListService;
import comment.service.CommentModifyFormService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import review.service.ReviewListService;
import review.vo.ReviewBean;

public class CommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니?-CommentListAction");
		
				
				CommentListService commentListService = new CommentListService();
				int commentCount = commentListService.getCommentCount();
				System.out.println("총 댓글 수(Action)" +commentCount);
				
				ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
				commentList =commentListService.getCommentList();
				
				
				request.setAttribute("commentList", commentList);
				
				ActionForward forward = new ActionForward();
				forward.setPath("/review/commentlist.jsp");
				
				
				return forward;
	}

}
