package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.MangerMainService;
import manager.vo.CategoryBean;
import manager.vo.PageInfo;

public class ManagerMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		MangerMainService mms = new MangerMainService();

		int listCount = mms.getListCount();

		ArrayList<CategoryBean> managerList = new ArrayList<CategoryBean>();
		managerList = mms.getCategoryList(page, limit);

		int maxPage = (int) ((double) listCount / limit + 0.95);

		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;

		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("managerList", managerList);

		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_main.jsp");

		return forward;
	}

}
