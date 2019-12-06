package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentDeleteProService;
import common.action.Action;
import common.vo.ActionForward;

public class CommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? CommentDeleteProAction");
		
		ActionForward forward = null;
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		int review_comment_num = Integer.parseInt(request.getParameter("review_comment_num"));
		String review_comment_member_id = (String)request.getParameter("review_comment_member_id");
		String page = request.getParameter("page");
		int review_comment_review_num = Integer.parseInt(request.getParameter("review_comment_review_num"));
//		System.out.println(review_comment_num + review_comment_member_id + page);

		CommentDeleteProService commentDeleteProService = new CommentDeleteProService();
		boolean isRightUser = commentDeleteProService.isArticleWriter(review_comment_num, review_comment_member_id);
		
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
			boolean isDeleteSuccess = commentDeleteProService.removeArticle(review_comment_num, review_comment_review_num);
			
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
				// BoardList.bo 주소로 새로운 요청이 발생해야하므로 Redirect 방식으로 포워딩 설정
				// => URL 뒤에 파라미터값으로 page 전달
				forward = new ActionForward();
				forward.setRedirect(true); // true = Redirect 방식
				forward.setPath("ReviewDetail.rv?review_num="+review_num+"&page="+page);
		}
	}
		forward = null;
		return forward;
	}

}
