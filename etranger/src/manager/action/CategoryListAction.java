package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CategoryListService;
import manager.svc.ThemeListService;
import manager.vo.CategoryBean;

public class CategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CategoryListService categoryListService = new CategoryListService();

		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();
		articleList = categoryListService.getArticleList();
		
		request.setAttribute("articleList", articleList);
		
		
		ThemeListService themeListService = new ThemeListService();
		
		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();
		themeList = themeListService.getThemeList();
		
		request.setAttribute("themeList", themeList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_main.jsp");
		
		return forward;
	}

}
