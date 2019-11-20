package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberDetailService;
import member.vo.MemberBean;

public class MemberModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String member_id=request.getParameter("member_id");
		MemberDetailService memberDetailService = new MemberDetailService();
		
		MemberBean memberBean = memberDetailService.getArticle(member_id);
//		System.out.println(memberBean.getMember_id());
		request.setAttribute("member_id",member_id);
		request.setAttribute("article",memberBean);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("/member/member_modifyForm.jsp");
		
		
		
		return forward;
	}

}
