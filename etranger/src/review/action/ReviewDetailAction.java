package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;


public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDetailAction");
		//
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		// => page 파라미터는 현재 클래스에서 사용되지 않고 다음 페이지로 포워딩을 위해 전달만 할 목적이라
		// 그냥 바로 String으로 받아 사용.
//		System.out.println("board_num: "+board_num + ", page : "+page);
		
		//BoardDetailService 클래스 인스턴스 생성 후 getArticle() 메서드
		// 파라미터 : board_num, 리턴타입 : BoardBean
		
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article =reviewDetailService.getArticle("review_num");
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/review/reviewDetail.jsp");
		//dispatch 방식으로 보낼거라 생략
		return forward;
	}

}
