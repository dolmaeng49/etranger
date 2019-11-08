package member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.IntData;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberJoinProService;
import member.vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("MemberJoinProAction");
		
		Timestamp leg_date = new Timestamp(System.currentTimeMillis());
		
		
		MemberBean memberBean=new MemberBean();
		memberBean.setMember_id(request.getParameter("member_id"));
		memberBean.setMember_passwd(request.getParameter("member_passwd"));
		memberBean.setMember_name(request.getParameter("member_name"));
		String member_addr = request.getParameter("member_addr") + request.getParameter("member_addr2")
				+ request.getParameter("member_addr3") + request.getParameter("member_addr4");
		memberBean.setMember_addr(member_addr);
		memberBean.setMember_phone(request.getParameter("member_phone"));
		memberBean.setMember_email(request.getParameter("member_email"));
		memberBean.setMember_gender(request.getParameter("member_gender"));
		memberBean.setMember_leg_date(leg_date);
		memberBean.setMember_birth(request.getParameter("member_birth"));
		
		
		
		
		
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		boolean isWriteSuccess=memberJoinProService.registMember(memberBean);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}else {	
			forward = new ActionForward();
			forward.setPath("LoginForm.me");
			forward.setRedirect(true);//Redirect 방식 = true, Dispatch 방식 = false 전달
		}
		

			
			
		return forward;
	}

}
