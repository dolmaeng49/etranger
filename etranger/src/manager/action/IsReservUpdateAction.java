package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ReservUpdateService;
import manager.svc.isReservUpdateService;

public class IsReservUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String isReserv = request.getParameter("isReserv");
		int reservNum = Integer.parseInt(request.getParameter("reservNum"));

		isReservUpdateService isReservUpdateService = new isReservUpdateService();
		boolean isUpdateSuccess = isReservUpdateService.isupdateReserv(isReserv, reservNum);
		
		if(!isUpdateSuccess) {
			response.setContentType("text/html; charser=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("결제 실패했습니다");
		}
		return forward;
	}

}
