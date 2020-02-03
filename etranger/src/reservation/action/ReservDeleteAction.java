package reservation.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import reservation.service.ReservDeleteService;
import reservation.vo.ReservationBean;

public class ReservDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
//		ReservationBean rb = new ReservationBean();
		
		int reservNum = Integer.parseInt(request.getParameter("reservation_num"));
		
		ReservDeleteService reservDeleteService = new ReservDeleteService();
		boolean isDeleteSuccess = reservDeleteService.deleteReserv(reservNum);
		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charser=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("예약 삭제가 실패했습니다");
		}
		
		return forward;
	}

}
