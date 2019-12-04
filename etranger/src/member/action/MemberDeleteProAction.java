package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberModifyDeleteService;

public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		String member_id = request.getParameter("member_id");
		
		// 서비스 객체 생성해 삭제 권한 확인하는 메서드(userCheck(String)) 호출
		MemberModifyDeleteService service = new MemberModifyDeleteService();
		int userCheck = service.userCheck(member_id, request.getParameter("member_passwd"));
		
		if(userCheck < 1) { // 삭제 권한이 없는 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			// 삭제 권한이 있을 경우, 회원 삭제 메서드 호출
			boolean isDeleteSuccess = service.deleteMember(member_id);
			
			if(!isDeleteSuccess) { // 회원 탈퇴 실패 
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
			}else { // 회원 탈퇴 성공
				request.getSession().invalidate(); // 세션 비활성화
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("index.jsp");
			}
		}
		return forward;
	}

}