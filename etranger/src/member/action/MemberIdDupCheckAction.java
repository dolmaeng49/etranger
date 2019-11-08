package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberIdDupCheckService;

public class MemberIdDupCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		
		String id = request.getParameter("id");
		MemberIdDupCheckService dupCheckService = new MemberIdDupCheckService();
		boolean isDup = dupCheckService.checkDup(id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(!isDup) { // ID 중복이 아닐 경우
			out.println("<script>");
			out.println("alert('사용 가능한 아이디입니다!')");
			out.println(id); // 스크립트에 중복체크 완료한 id 출력(ajax 으로 리턴)
			out.println("</script>");
		}else { // 중복일 경우
			out.println("<script>");
			out.println("alert('이미 존재하는 아이디입니다!')");
			out.println("사용 불가능한 아이디 중복 방지"); // 스크립트에 출력(ajax 으로 리턴)
			out.println("</script>");
		}
		// ajax 으로 출력결과를 가져가기 때문에 포워딩은 하지 않음
		forward = null;
		return forward;
	}

}
