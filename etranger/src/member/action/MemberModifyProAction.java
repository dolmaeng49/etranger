package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberModifyProService;
import member.vo.MemberBean;

public class MemberModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_id=request.getParameter("member_id");
		
		// 게시물 수정 권한 여부를 판단하기 위해 전달받은 데이터 중 패스워드를 비교
		MemberModifyProService boardModifyProService = new MemberModifyProService();
		boolean isRightUser = boardModifyProService.isArticleWriter(member_id, request.getParameter("member_passwd"));
				
		if(!isRightUser) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
			out.println("<script>");
			out.println("alert('수정 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			// 게시물 수정 권한이 있는 경우(= 패스워드가 일치하는 경우)
			// BoardBean 객체에 수정할 게시물 정보(번호, 제목, 작성자, 내용)를 저장한 뒤
			// Service 클래스의 modifyArticle() 메서드를 호출하여 전달
			MemberBean article = new MemberBean();
			article.setMember_id(member_id);
			article.setMember_name(request.getParameter("member_name"));
			article.setMember_addr(request.getParameter("member_addr"));
			article.setMember_phone(request.getParameter("member_phone"));
			article.setMember_email(request.getParameter("member_email"));
			article.setMember_birth(request.getParameter("member_birth"));
			article.setMember_gender(request.getParameter("member_gender"));
			
			boolean isModifySuccess = boardModifyProService.modifyArticle(article);
			
			// 글 수정 성공 여부를 판별하여 실패했을 경우 자바스크립트를 사용하여 "글 수정 실패!" 출력하고
			// 성공했을 경우 "BoardDetail.bo" 로 포워딩 => Redirect 방식 사용, board_num 과 page 전달
			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
				out.println("<script>");
				out.println("alert('글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true); // 새로운 서블릿 주소를 요청하므로 Redirect 방식 포워딩
				forward.setPath("MemberDetail.bo?member_id=" + member_id);
			}
			
		}
		
		
		return forward;
	}

}
