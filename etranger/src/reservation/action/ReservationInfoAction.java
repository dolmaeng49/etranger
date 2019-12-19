package reservation.action;

import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import member.service.MemberDetailService;
import member.vo.MemberBean;
import reservation.service.ReservationInfoService;
import reservation.vo.ReservationBean;
import review.service.ReviewListService;

public class ReservationInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = (String) request.getSession().getAttribute("member_id");

		// 페이징 처리
		int page = 1;
		int limit = 15;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		ReservationInfoService reservaitonInfoListService = new ReservationInfoService();
		int listCount = reservaitonInfoListService.getListCount(id);

		ReservationBean rbb = new ReservationBean();
		ArrayList<ReservationBean> rb = new ArrayList<ReservationBean>();
		ReservationInfoService rs = new ReservationInfoService();
		rbb.setReservation_member_id(id);
		rb = rs.ReservationInfo(page, limit, id);

		MemberDetailService memberDetailService = new MemberDetailService();
		MemberBean memberBean = memberDetailService.getArticle(id);
		
		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("member_id", id);
		request.setAttribute("ReservationInfo", rb);
		request.setAttribute("MemberInfo", memberBean);

		ActionForward forward = new ActionForward();
		forward.setPath("/reservation/reservation_reservation_info.jsp");

		return forward;
	}

}
