package comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentModifyFormService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;

public class CommentReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? - CommentReplyFormAction");
		
		ActionForward forward = new ActionForward();
		
		//전달받은 파라미터 가져오기  
		int comment_num = Integer.parseInt(request.getParameter("review_comment_num"));
		String nowPage= request.getParameter("nowPage");
		System.out.println("review_comment_num = " +comment_num+", page ="+nowPage);
		
		//getComment => modifyService안에 넣어놨음.
		CommentModifyFormService commentModifyFormService = new CommentModifyFormService();
		CommentBean comment = commentModifyFormService.getComment(comment_num);
		
		// review도 같이 get
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article = reviewDetailService.getArticle(review_num);
		
		request.setAttribute("article", article);
		request.setAttribute("comment", comment);
		request.setAttribute("page", nowPage);
		
		
		
		System.out.println("확인");
		System.out.println(comment.getReview_comment_num());
		System.out.println(comment.getReview_comment_member_id());
		System.out.println(comment.getReview_comment_member_name());
		
		
		forward.setPath("/review/comment_reply.jsp");
		
		return forward;
	}

}
