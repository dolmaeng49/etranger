package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberReservationInfoService;
import reservation.vo.ReservationInfoBean;

public class MemberReservationInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("member_id");
		System.out.println("/MemberReservationInfoAction.me");
		MemberReservationInfoService ms = new MemberReservationInfoService();
		ReservationInfoBean rib = new ReservationInfoBean();
		rib.setReservation_member_id(id);
		rib = ms.MemberReservationInfo(id);

		request.setAttribute("ReservationInfo", rib);

		ActionForward forward = new ActionForward();
		forward.setPath("/member/member_reservation_info.jsp");

		return forward;
	}

}
