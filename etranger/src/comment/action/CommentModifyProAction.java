package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentModifyProService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;

public class CommentModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? -CommentModifyProAction");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = null;
		CommentBean cb = null;
		
		int comment_num = Integer.parseInt(request.getParameter("review_comment_num"));
		
		cb = new CommentBean();
		cb.setReview_comment_num(comment_num);
		cb.setReview_comment_content(request.getParameter("review_comment_content"));
		CommentModifyProService commentModifyProService = new CommentModifyProService();
		
		boolean isModifySuccess = commentModifyProService.modifyComment(cb);
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String page = request.getParameter("page");
		
		if(!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
			out.println("<script>");
			out.println("alert('글 수정 실패!');");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			forward = new ActionForward();
			forward.setRedirect(true); // 새로운 서블릿 주소를 요청하므로 Redirect 방식 포워딩
			forward.setPath("ReviewDetail.rv?review_num=" + review_num + "&page=" + page);
//			forward.setPath("ReviewList.rv");
		}
		
		
		
		
		return forward;
	}

}
