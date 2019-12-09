package reservation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import reservation.service.ReservationInfoService;
import reservation.vo.ReservationBean;

public class ReservationInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("member_id");
		System.out.println("/MemberReservationInfoAction.me");
		ReservationInfoService rs = new ReservationInfoService();
		ReservationBean rb = new ReservationBean();
		rb.setReservation_member_id(id);
		rb = rs.ReservationInfo(id);

		request.setAttribute("ReservationInfo", rb);

		ActionForward forward = new ActionForward();
		forward.setPath("/reservation/reservation_reservation_info.jsp");

		return forward;
	}

}
