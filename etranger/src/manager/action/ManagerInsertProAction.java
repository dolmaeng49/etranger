package manager.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ManagerInsertProService;
import manager.vo.CategoryBean;

public class ManagerInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		CategoryBean cb = new CategoryBean();
		cb.setCityName(request.getParameter("city_name"));
		cb.setCityCode(Integer.parseInt(request.getParameter("city_code")));
		cb.setCityName(request.getParameter("region_name"));
		cb.setCityCode(Integer.parseInt(request.getParameter("region_code")));
		
		ManagerInsertProService mips = new ManagerInsertProService();
		boolean isInsertSuccess = mips.InsertCategory(cb);
		
//		if(!isRegistSuccess) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
//			out.println("<script>");
//			out.println("alert('회원 가입 실패!')");
//			out.println("history.back()");
//			out.println("</script>");
//		} else {
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("MemberJoinResult.me");
//		}
		
		return forward;
	}

}
