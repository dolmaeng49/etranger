package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentWriteProService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? -CommentWriteAction");
		ActionForward forward = null;
		CommentBean cb = null;
		String nowPage=request.getParameter("nowPage");
		System.out.println(nowPage);
		request.setCharacterEncoding("UTF-8");
		cb= new CommentBean();
		cb.setReview_comment_member_id(request.getParameter("review_comment_member_id"));
		cb.setReview_comment_member_name(request.getParameter("review_comment_member_name"));
		cb.setReview_comment_review_num(Integer.parseInt(request.getParameter("review_comment_review_num")));
		cb.setReview_comment_content(request.getParameter("review_comment_content"));
		
		CommentWriteProService commentWriteProService = new CommentWriteProService();
		
		boolean isAddSuccess = commentWriteProService.AddComment(cb);
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		
		if(!isAddSuccess){
			out.println("<script>");
			out.println("alert('댓글 등록 실패!')");
			out.println("</script>");
		}else {
			forward= new ActionForward();
			out.println("<script>");
			out.println("alert('댓글 등록 완료!')");
			out.println("</script>");
			forward.setPath("ReviewDetail.rv?review_num="+cb.getReview_comment_review_num()+"&page="+nowPage);
			forward.setRedirect(true);
		}
		
		return forward;
		
	}

}
