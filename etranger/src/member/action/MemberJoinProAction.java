package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberJoinProService;
import member.vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String passwd=request.getParameter("pass");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		MemberBean memberBean=new MemberBean();
		memberBean.setId(request.getParameter("id"));
		memberBean.setPasswd(request.getParameter("pass"));
		memberBean.setName(request.getParameter("name"));
		memberBean.setAddr(request.getParameter("addr"));
		memberBean.setPhone(request.getParameter("phone"));
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setGender(request.getParameter("Gender"));
		
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		boolean isWriteSuccess=memberJoinProService.registMember(memberBean);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
//			out.println("history.go(-1)");
			out.println("</script>");
		}else {	
			forward = new ActionForward();
			forward.setPath("LoginForm.me");
			forward.setRedirect(true);//Redirect 방식 = true, Dispatch 방식 = false 전달
		}
		

			
			
		return forward;
	}

}
