package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.svc.CategoryListService;
import manager.svc.RegionListService;
import manager.svc.RegionReservationCountService;
import manager.svc.ThemeListService;
import manager.vo.CategoryBean;

public class ManagerMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// --- 지역 리스트 ---
		RegionListService regionListService = new RegionListService();

		ArrayList<CategoryBean> regionList = new ArrayList<CategoryBean>();
		regionList = regionListService.getRegionList();
		
		request.setAttribute("regionList", regionList);
		// -------------------
		
		// --- 테마 리스트 ---
		ThemeListService themeListService = new ThemeListService();
		
		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();
		themeList = themeListService.getThemeList();
		
		request.setAttribute("themeList", themeList);
		// -------------------
		
		// --- 패키지 리스트 ---
		int page = 1;
		int limit = 8;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		CategoryListService categoryListService = new CategoryListService();
		int listCount = categoryListService.getListCount();

		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		categoryList = categoryListService.getCategoryList(page, limit);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("categoryList", categoryList);
		// -------------------
		
		// --- 데이터 차트 ---
		RegionReservationCountService regionReservationCountService = new RegionReservationCountService();
		ArrayList<CategoryBean> regionReservationList = new ArrayList<CategoryBean>();
		
		regionReservationList = regionReservationCountService.getregionReservationList();
		request.setAttribute("regionReservationList", regionReservationList);
		// -------------------
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_main.jsp");
		
		return forward;
	}

}
