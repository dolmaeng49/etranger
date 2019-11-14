package member.action;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberEmailService;

public class MemberEmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward;
		MemberEmailService memberEmailService = new MemberEmailService();
		String email = "";
		// ajax통해 전달 받은 email 주소 저장
		if(request.getParameter("email")!=null) {
			email = request.getParameter("email");
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('메일주소를 입력해주세요!')");
			out.println("</script>");
		}
		int codeLength = 6; // 생성할 인증코드의 길이
		String checkCode = memberEmailService.createCheckCode(codeLength); // codeLength 자리의 인증코드를 생성해 저장
				
		String subject = "etranger 회원가입 인증 코드입니다.";
		String pattern = "etranger 회원가입을 환영합니다! E-mail 인증을 위해 아래의 {0}자리코드를 회원가입 페이지에 입력해주세요. \n <{1}>";
		String src = Integer.toString(codeLength).concat(":").concat(checkCode);
		
		String content = MessageFormat.format(pattern, src.split(":"));
		memberEmailService.mailSend(email, subject, content);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(checkCode);
		forward = null;
		return forward;
	}

}
