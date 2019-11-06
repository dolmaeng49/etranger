package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.action.MemberLoginProAction;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/LoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/member_login_form.jsp");
		} else if (command.equals("/LoginPro.me")) {
			action = new MemberLoginProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/MemberJoinForm.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/member/member_join_form2.jsp");
		}
	}
}