package member.action;

import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberEmailService;
import member.service.MemberResetPasswdFormService;
import member.vo.MemberBean;

public class MemberResetPasswdFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_id(request.getParameter("member_id"));
		memberBean.setMember_email(request.getParameter("member_email"));
		
		MemberResetPasswdFormService resetPasswdFormService = new MemberResetPasswdFormService();
		int result = resetPasswdFormService.isCorrectMemberEmail(memberBean);
		
//		if(result == 0) {
//			// 해당 아이디가 존재하지 않는 경우
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('존재하지 않는 아이디 입니다')");
//			out.println("history.back();");
//			out.println("</script>");
//		}else if(result < 0) {
//			// 아이디가 존재하지만 Email 정보가 일치하지 않는 경우
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('Email 주소가 일치하지 않습니다')");
//			out.println("history.back();");
//			out.println("</script>");
//		}else {
//			// 아이디, Email 정보가 일치하는 경우
//			// ajax통해 전달 받은 email 주소 저장
//			String email = request.getParameter("member_email");
//			int codeLength = 8; // 생성할 인증코드의 길이
//			MemberEmailService memberEmailService = new MemberEmailService();
//			String checkCode = memberEmailService.createCheckCode(codeLength); // codeLength 자리의 인증코드를 생성해 저장
//					
//			String subject = "etranger 비밀번호 변경 인증 코드입니다.";
//			String pattern = "E-mail 인증을 위해 아래의 {0}자리코드를 비밀번호 찾기 페이지에 입력해주세요. \n <{1}>";
//			String src = Integer.toString(codeLength).concat(":").concat(checkCode);
//			
//			String content = MessageFormat.format(pattern, src.split(":"));
//			memberEmailService.mailSend(email, subject, content);
//			
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print(checkCode);
//		}
		
		
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 결과 출력, ajax로 전달
		// 결과에 따른 제어는 script로
		out.print("<findId>");
		out.print("<result>");
		out.print(result);
		out.print("</result>");
		out.print("<checkCode>");
		if(result == 1) {
			// 아이디, Email 정보가 일치하는 경우
			// ajax통해 전달 받은 email 주소 저장
			String email = request.getParameter("member_email");
			int codeLength = 8; // 생성할 인증코드의 길이
			MemberEmailService memberEmailService = new MemberEmailService();
			String checkCode = memberEmailService.createCheckCode(codeLength); // codeLength 자리의 인증코드를 생성해 저장
					
			String subject = "etranger 비밀번호 변경 인증 코드입니다.";
			String pattern = "E-mail 인증을 위해 아래의 {0}자리코드를 비밀번호 찾기 페이지에 입력해주세요. \n <{1}>";
			String src = Integer.toString(codeLength).concat(":").concat(checkCode);
			
			String content = MessageFormat.format(pattern, src.split(":"));
			memberEmailService.mailSend(email, subject, content);
			
			// 생성한 인증코드 전달
			out.print(checkCode);
		}
		out.print("</checkCode>");
		out.print("</findId>");
		
		return forward;
	}

}
