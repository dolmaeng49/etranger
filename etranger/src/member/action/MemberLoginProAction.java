package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberLoginProService;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		int loginResult = memberLoginProService.memberLogin(id,passwd);
		
		if(loginResult==0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ID가 존재하지 않습니다')");
			out.println("history.back()");
			out.println("</script>");
		}else if(loginResult==-1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드가 틀렸습니다')");
			out.println("history.back()");
			out.println("</script>");
		}else if(loginResult==1) {
			HttpSession session = request.getSession();
			session.setAttribute("sId", id);
			forward = new ActionForward();
			forward.setPath("BoardMain.bo");
			forward.setRedirect(true);
			
		}
		return forward;
	}

}
