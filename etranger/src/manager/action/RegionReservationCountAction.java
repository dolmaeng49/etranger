package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.RegionReservationCountService;
import manager.vo.CategoryBean;

public class RegionReservationCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RegionReservationCountService regionReservationCountService = new RegionReservationCountService();
		ArrayList<CategoryBean> regionReservationList = new ArrayList<CategoryBean>();
		
		regionReservationList = regionReservationCountService.getregionReservationList();
		request.setAttribute("regionReservationList", regionReservationList);
			
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/datachart.jsp");
		
		return forward;
	}

}
