package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;

public class ReviewModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int review_num = Integer.parseInt(request.getParameter("review_num"));

		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article = reviewDetailService.getArticleForComment(review_num);

		request.setAttribute("article", article);

		ActionForward forward = new ActionForward();
		forward.setPath("review/review_modify.jsp");

		return forward;
	}

}
