package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ThemeListService;
import manager.vo.CategoryBean;

public class ThemeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("themeAction");
		ThemeListService themeListService = new ThemeListService();
		
		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();
		themeList = themeListService.getThemeList();
		
		request.setAttribute("themeList", themeList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_main.jsp");
		
		return forward;
	}

}
