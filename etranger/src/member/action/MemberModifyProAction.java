package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberModifyDeleteService;
import member.vo.MemberBean;

public class MemberModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_id=request.getParameter("member_id");
		
		// 회원정보 수정 권한 여부를 판단하기 위해 패스워드를 DB의 회원정보와 비교
		MemberModifyDeleteService boardModifyProService = new MemberModifyDeleteService();
		int userCheck = boardModifyProService.userCheck(member_id, request.getParameter("member_passwd"));
				
		if(userCheck < 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
			out.println("<script>");
			out.println("alert('수정 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			// 회원정보 수정 권한이 있는 경우
			// MemberBean 객체에 수정할 회원정보를 담아 Service 클래스의 modifyMemberInfo() 메서드를 호출하여 전달
			MemberBean memberBean = new MemberBean();
			memberBean.setMember_id(member_id);
			memberBean.setMember_name(request.getParameter("member_name"));
			memberBean.setMember_addr(request.getParameter("member_addr"));
			memberBean.setMember_addr2(request.getParameter("member_addr2"));
			memberBean.setMember_addr3(request.getParameter("member_addr3"));
			memberBean.setMember_addr4(request.getParameter("member_addr4"));
			memberBean.setMember_phone(request.getParameter("member_phone"));
			memberBean.setMember_email(request.getParameter("member_email"));
			memberBean.setMember_birth(request.getParameter("member_birth"));
			memberBean.setMember_gender(request.getParameter("member_gender"));
			
			boolean isModifySuccess = boardModifyProService.modifyMemberInfo(memberBean);
			
			// 회원정보수정 실패했을 경우 자바스크립트를 사용하여 "회원정보 수정 실패!" 출력하고
			// 성공했을 경우 메인페이지로 포워딩 => Redirect 방식 사용
			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
				out.println("<script>");
				out.println("alert('회원정보 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true); // 새로운 서블릿 주소를 요청하므로 Redirect 방식 포워딩
				forward.setPath("."); // 경로 메인페이지로 설정
			}
		}
		return forward;
	}

}
