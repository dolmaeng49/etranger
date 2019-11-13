package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CityInsertService;
import manager.svc.ManagerInsertProService;
import manager.vo.CategoryBean;

public class CityInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		CategoryBean cb = new CategoryBean();
		cb.setCityName(request.getParameter("city_name"));
		cb.setCityRegionCode(Integer.parseInt(request.getParameter("region_code")));

		CityInsertService cis = new CityInsertService();
		boolean isInsertSuccess = cis.CityInsert(cb);

		if (!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('도시 등록 실패!')");
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
