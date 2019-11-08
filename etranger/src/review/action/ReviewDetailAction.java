package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDetailAction");
<<<<<<< HEAD
		//
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		// => page 파라미터는 현재 클래스에서 사용되지 않고 다음 페이지로 포워딩을 위해 전달만 할 목적이라
		// 그냥 바로 String으로 받아 사용.
//		System.out.println("board_num: "+board_num + ", page : "+page);
=======
>>>>>>> refs/remotes/origin/master
		
<<<<<<< HEAD
		//BoardDetailService 클래스 인스턴스 생성 후 getArticle() 메서드
		// 파라미터 : board_num, 리턴타입 : BoardBean
=======
		// request 객체를 통해 전달받은 파라미터 가져오기
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String page = request.getParameter("page"); 
>>>>>>> refs/remotes/origin/master
		
<<<<<<< HEAD
		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article =reviewDetailService.getArticle("review_num");
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/review/reviewDetail.jsp");
		//dispatch 방식으로 보낼거라 생략
=======
//		System.out.println("board_num :" +review_num+ ", page : " + page);
		
		ReviewDetailService ReviewDetailService = new ReviewDetailService();
		ReviewBean article = ReviewDetailService.getArticle(review_num);
		
		// request 객체에 포워딩 시킬 파라미터(pagem BoardBean)를 저장
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
		// ActionForward 객체에 값 저장
		ActionForward forward = new ActionForward();
		// setRedirect() : request 객체를 유지한 채 현재 요청 주소 그대로 포워딩 = dispatch 방식 
		//                 => 방식 지정 생략 가능
		// setPath() : review 폴더 내의 review_detail.jsp 페이지 지정
		forward.setRedirect(false);
		forward.setPath("review/review_detail.jsp");		
		
>>>>>>> refs/remotes/origin/master
		return forward;
	}

}
