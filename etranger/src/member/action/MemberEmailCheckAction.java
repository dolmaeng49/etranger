package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberEmailService;

public class MemberEmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		System.out.println("MemberEmailCheckAction");
		MemberEmailService memberEmailService = new MemberEmailService();
		String email = "dm49@naver.com";
		String subject = "etranger 회원가입 인증 코드입니다.";
		String content = "etranger 회원가입을 환영합니다! E-mail 인증을 위해 아래의 코드를 회원가입 페이지에 입력해주세요. <코드는 아직 없음>";
		
		memberEmailService.mailSend(email, subject, content);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("인증코드발송성공");
		forward = null;
		return forward;
	}

}
