package manager.action;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberEmailService;

public class ReceiveEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		MemberEmailService memberEmailService = new MemberEmailService();
		//받아서 가공할 값
		String receiveEamil = request.getParameter("receiveEmail");
		String rowContent = request.getParameter("content");
		System.out.println(rowContent);
		
		//서비스로 넘겨줄 값
		
		String email = "etrangermanager@gmail.com";
		String subject = request.getParameter("subject");
		String content = "회신받을 주소 : "+ receiveEamil + " \n 문의 내용 : " + rowContent;
		
		boolean isSendEmailSuccess = memberEmailService.mailSend(email, subject, content);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!isSendEmailSuccess) {
			out.println("<script>");
			out.println("alert('문의메일 발송이 실패하였습니다! 다시 시도해주세요! ')");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('소중한 의견 감사드립니다. 빠른 시일 내에 답변드리겠습니다.')");
			out.println("location.href='./contact.jsp'");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
