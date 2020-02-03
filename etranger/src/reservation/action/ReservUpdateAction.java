package reservation.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import reservation.service.ReservDeleteService;
import reservation.service.ReservUpdateService;

public class ReservUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String reservation_progress = request.getParameter("reservation_progress");
		int reservNum = Integer.parseInt(request.getParameter("reservation_num"));

		ReservUpdateService reservUpdateService = new ReservUpdateService();
		boolean isUpdateSuccess = reservUpdateService.updateReserv(reservation_progress, reservNum);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charser=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("예약 수정이 실패했습니다");
		}
		
		
		
		
		
		return forward;
	}

}
