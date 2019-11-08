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

		

		// request 객체를 통해 전달받은 파라미터 가져오기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String page = request.getParameter("page"); 

		

		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article =reviewDetailService.getArticle(review_num);
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
	
		// ActionForward 객체에 값 저장
		ActionForward forward = new ActionForward();
		// setRedirect() : request 객체를 유지한 채 현재 요청 주소 그대로 포워딩 = dispatch 방식 
		//                 => 방식 지정 생략 가능
		// setPath() : review 폴더 내의 review_detail.jsp 페이지 지정
		forward.setRedirect(false);
		forward.setPath("review/review_detail.jsp");		

		return forward;
	}

}
