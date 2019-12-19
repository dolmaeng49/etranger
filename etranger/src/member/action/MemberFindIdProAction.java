package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberFindIdProService;
import member.vo.MemberBean;

public class MemberFindIdProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberBean memberBean = new MemberBean();
		String member_name = request.getParameter("member_name");
		String member_birth = request.getParameter("member_birth");
		String member_gender = request.getParameter("member_gender");
		memberBean.setMember_name(member_name);
		memberBean.setMember_birth(member_birth);
		memberBean.setMember_gender(member_gender);
		
		MemberFindIdProService findIdProService = new MemberFindIdProService();
		// 아이디 조회 결과를 저장 // null : 조회결과없음
		String selectedId = findIdProService.findId(memberBean);
		if(selectedId.length() == 0) {
			// 해당 회원정보 없으면 alert & 입력정보 초기화(아이디찾기 페이지 새로고침)
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력하신 정보와 일치하는 아이디가 없습니다!')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			// 해당 회원정보 있으면 아이디 팝업창 & 로그인 페이지 요청
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
//			out.println("alert('회원님의 아이디는 ' + '" + selectedId + "' + ' 입니다');");
			StringBuilder message = new StringBuilder();
			message = message.append("alert('회원님의 아이디는 ' + '").append(selectedId).append("' + ' 입니다');");
			out.println(message);
			out.println("location.href='LoginForm.me';");
			out.println("</script>");
			
		}
		return forward;
	}

}
