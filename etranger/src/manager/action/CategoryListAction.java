package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.RegionListService;
import manager.svc.ThemeListService;
import manager.vo.CategoryBean;

public class CategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RegionListService regionListService = new RegionListService();

		ArrayList<CategoryBean> regionList = new ArrayList<CategoryBean>();
		regionList = regionListService.getRegionList();
		
		request.setAttribute("regionList", regionList);
		
		
		ThemeListService themeListService = new ThemeListService();
		
		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();
		themeList = themeListService.getThemeList();
		
		request.setAttribute("themeList", themeList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_main.jsp");
		
		return forward;
	}

}
