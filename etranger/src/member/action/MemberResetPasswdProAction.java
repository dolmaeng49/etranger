package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberResetPasswdProService;
import member.vo.MemberBean;

public class MemberResetPasswdProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_id(request.getParameter("member_id"));
		memberBean.setMember_passwd(request.getParameter("member_passwd"));
		MemberResetPasswdProService resetPasswdProService = new MemberResetPasswdProService();
		boolean isUpdateSuccess = resetPasswdProService.updatePasswd(memberBean);
		if(isUpdateSuccess) { // 비밀번호 변경에 성공한 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경에 성공했습니다!');");
			out.println("</script>");
			forward = new ActionForward();
			forward.setPath("LoginForm.me");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패했습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
