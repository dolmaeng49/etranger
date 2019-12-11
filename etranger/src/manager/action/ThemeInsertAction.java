package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.RegionInsertService;
import manager.svc.ThemeInsertService;
import manager.vo.CategoryBean;

public class ThemeInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		CategoryBean cb = new CategoryBean();
		cb.setThemeName(request.getParameter("theme_name"));

		ThemeInsertService themeInsertService = new ThemeInsertService();
		boolean isInsertSuccess = themeInsertService.InsertTheme(cb);
		
		if(!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("테마 추가가 실패하였습니다.");
		}
		
		return forward;
	}

}
