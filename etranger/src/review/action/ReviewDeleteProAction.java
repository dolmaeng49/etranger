package review.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDeleteProService;
import review.vo.ReviewBean;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDeleteProAction");
		ActionForward forward = null;


		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String review_member_id = (String)request.getParameter("review_member_id");
		String page = request.getParameter("page");
		System.out.println(review_num + review_member_id + page);

		ReviewDeleteProService reviewDeleteProService = new ReviewDeleteProService();
		boolean isRightUser = reviewDeleteProService.isArticleWriter(review_num, review_member_id);
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
			boolean isDeleteSuccess = reviewDeleteProService.removeArticle(review_num);
			
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
				forward.setPath("ReviewList.rv?page="+page);
		}
	}

		return forward;
	}

}
