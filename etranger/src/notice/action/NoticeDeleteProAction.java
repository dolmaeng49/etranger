package notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import notice.service.NoticeDeleteProService;

public class NoticeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeDeleteProAction");
		ActionForward forward = null;


		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		String notice_member_id = (String)request.getParameter("notice_member_id");
		String page = request.getParameter("page");

		NoticeDeleteProService noticeDeleteProService = new NoticeDeleteProService();
		boolean isRightUser = noticeDeleteProService.isArticleWriter(notice_num, notice_member_id);
		// 만약, 게시물 삭제 권한이 없는 경우(=패스워드가 틀린 경구)
		// 자바스크립트를 사용하여 "삭제 권한이 없습니다" 출력
		if(!isRightUser) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다');");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			// 삭제 권한이 있는 경우(= 패스워드가 일치하는 경우)
			// 글 번호(board_num)를 사용하여 글 삭제 => Service 클래스의 removeArticle() 메서드 호출
			// 파라미터 : board_num, 리턴타입 : boolean(isDeleteSuccess)
			boolean isDeleteSuccess = noticeDeleteProService.removeArticle(notice_num);
			
			// isDeleteSuccess 가 false 일 경우 자바스크립트를 사용하여 "삭제 실패!" 출력
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back()");
				out.println("</script>");
				
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
				
				out.println("<script>");
				out.println("alert('삭제 성공');");
				out.println("</script>");
				// ReviewList 주소로 새로운 요청이 발생해야하므로 Redirect 방식으로 포워딩 설정
				
				forward = new ActionForward();
				forward.setRedirect(true); // true = Redirect 방식
				forward.setPath("NoticeList.no?page="+page);
		}
	}

		return forward;
	}

}
