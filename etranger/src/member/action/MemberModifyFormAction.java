package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberDetailService;
import member.vo.MemberBean;

public class MemberModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 현재 로그인 ID 받아오기
		String member_id = (String)request.getSession().getAttribute("member_id");
		// 세션에 ID 값이 없는 경우
		if(member_id == null) {
			// 뒤로가기?
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인해주세요!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		// 서비스 클래스의 회원정보를 조회하는 메서드 호출 (ID 전달)
		MemberDetailService memberDetailService = new MemberDetailService();
		MemberBean memberBean = memberDetailService.getArticle(member_id);
		
		// 조회한 결과 request에 저장
		request.setAttribute("memberBean",memberBean);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("/member/member_modifyForm.jsp");
		
		return forward;
	}

}
