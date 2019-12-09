package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.svc.CategoryListService;
import manager.svc.ReservListService;
import reservation.vo.ReservationBean;

public class MemberManagementAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int page = 1;
		int limit = 15;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		
		ReservListService reservListService = new ReservListService();
		int listCount = reservListService.getListCount();
		
		ArrayList<ReservationBean> reservList = new ArrayList<ReservationBean>();
		reservList = reservListService.getReservList(page, limit);
		
		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("reservList", reservList);
		

		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_member.jsp");
		return forward;
	}

}
