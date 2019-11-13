package manager.action;

import java.io.PrintWriter;

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
		cb.setRegionName(request.getParameter("region_name"));
		
		ManagerInsertProService mips = new ManagerInsertProService();
		boolean isInsertSuccess = mips.InsertCategory(cb);
		
		if(!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
			out.println("<script>");
			out.println("alert('지역 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("ManagerMain.ma");
		}
		
		return forward;
	}

}
