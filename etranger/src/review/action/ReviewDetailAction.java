package review.action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.dao.CommentDAO;
import comment.service.CommentListService;
import comment.service.CommentModifyFormService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;


public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDetailAction");
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		

		String viewerIpAddr = request.getRemoteAddr();	//클라이언트 ip 가져오기
		
		boolean readcountDuplFlag= false; //플래그 하나 세움
		Cookie ipDupl = new Cookie("ipDupl"+review_num+"", viewerIpAddr);    // 쿠키객체 생성(이름, 값)
		ipDupl.setMaxAge(1*24*60*60);	//쿠키 유효기간 설정
		ipDupl.setPath("/");			//이 쿠키가 유효한 디렉토리 설정(모든 경로에서 접근가능) 
		response.addCookie(ipDupl);		//클라이언트 응답에 쿠키 추가.
			
		
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) { //반복문 돌려서
			if(cookies[i].getName().equals("ipDupl"+review_num+"")) { // 지금 누른 게시물에 해당하는 쿠키가 있으면, 플래그 true로.
				readcountDuplFlag = true;
			}
		}
		
		// request 객체를 통해 전달받은 파라미터 가져오기
		String page = request.getParameter("page"); //게시글의 페이지임.
		

		ReviewDetailService reviewDetailService = new ReviewDetailService();
		ReviewBean article =reviewDetailService.getArticle(review_num,readcountDuplFlag);
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
	
		ActionForward forward = new ActionForward();
		forward.setPath("review/review_detail.jsp");		

		return forward;
	}

}
