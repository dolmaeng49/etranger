package comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentModifyFormService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;

public class CommentModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니?-CommentModifyFormAction");
		
		//review도 같이 get 해온다.
		int comment_num=Integer.parseInt(request.getParameter("review_comment_num"));
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		CommentModifyFormService commentModifyFormService = new CommentModifyFormService();
		CommentBean comment = commentModifyFormService.getComment(comment_num);
		
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article = reviewDetailService.getArticle(review_num);
		
		request.setAttribute("comment", comment);
		request.setAttribute("article", article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("review/comment_modify.jsp");
		return forward;
		
		
	}

}
