package notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import notice.service.NoticeListService;
import notice.vo.NoticeBean;
import review.service.ReviewDetailService;
import review.vo.ReviewBean;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 수정할 게시물의 상세 내역을 가져와서 리턴하여 뷰 페이지에서 폼에 상세 내역 출력
		System.out.println("NoticeModifyFormAction");
		
		// request 객체를 통해 전달받은 파라미터(review_num) 가져오기
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		// ReviewDetailService 클래스의 getArticle() 메서드를 호출하여 게시물 상세 내역 가져오기
		// => 파라미터 : ReviewBean, 리턴타입 : ReviewBean
		NoticeListService noticeListService = new NoticeListService();
		NoticeBean article = noticeListService.getArticle(notice_num);
		
		
		// request 객체에 NoticeBean(=article) 객체 저장
		request.setAttribute("article", article);
		
		// 글 수정 게시판 으로 이동
		ActionForward forward = new ActionForward();
//		forward.setRedirect(false);
		forward.setPath("notice/notice_modify.jsp");
		
		return forward;
	}

}