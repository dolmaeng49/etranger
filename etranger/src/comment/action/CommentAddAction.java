package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentWriteProService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;

public class CommentAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? -AddAction");
		ActionForward forward = null;
		CommentBean cb = null;
		request.setCharacterEncoding("UTF-8");
//		11/18할 것 : 뷰페이지에서 값 넘겨주기
//		위지윅 콜백함수 수정
//		플로팅메뉴 js 둘러보기
//		댓글 화면처리 구상
//		팝업js 뜯어보기
//		짬짬히 백엔드 틀 완성
		cb= new CommentBean();
		cb.setReview_reply_num(Integer.parseInt(request.getParameter("review_reply_num")));
		cb.setReview_reply_member_id(request.getParameter("review_reply_member_id"));
		cb.setReview_reply_review_num(Integer.parseInt(request.getParameter("review_reply_review_num")));
		cb.setReview_reply_member_id(request.getParameter("review_reply_content"));
		
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
//			forward.setPath("CommentList.rv");			//수정할 것
			forward.setRedirect(true); 
		}
		
		return forward;
		
	}

}
