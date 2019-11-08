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
		String content = "가입을 환영합니다!";
		
//		memberEmailService.mailSend(email, subject, content);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("인증코드발송성공");
		forward = null;
		return forward;
	}

}
