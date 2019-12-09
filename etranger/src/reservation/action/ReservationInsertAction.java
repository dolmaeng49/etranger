package reservation.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import reservation.service.ReservationInsertService;
import reservation.vo.ReservationBean;

public class ReservationInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ActionForward forward;
			forward = null;
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String print = "";
			
			ReservationBean rb = new ReservationBean();
			rb.setReservation_category_code(request.getParameter("category_code"));
			rb.setReservation_progress("예약대기");
			rb.setReservation_pay_way("N");
			rb.setReservation_headcount(Integer.parseInt(request.getParameter("headCount")));
			rb.setReservation_price(Integer.parseInt(request.getParameter("price")));
			rb.setReservation_product_num(request.getParameter("product_num"));
			rb.setReservation_member_id(request.getParameter("member_id"));

			ReservationInsertService ris = new ReservationInsertService();
			boolean isInsertSuccess = ris.insertReservation(rb);

			if (Integer.parseInt(request.getParameter("totalCount")) > 0) {
				if (!isInsertSuccess) {
					print += "<script>";
					print += "alert('상품예약 실패!')";
					print += "history.back()";
					print += "</script>";
					out.println(print);
				} else {
					System.out.println("action");
					forward = new ActionForward();
					forward.setPath("ReservationInfo.rs");
					forward.setRedirect(true);// Redirect 방식 = true, Dispatch 방식 = false 전달
				}
			} else {
				print = "";
				print += "<script>";
				print += "alert('정원초과 입니다!')";
				print += "history.back()";
				print += "</script>";
				out.println(print);
			}
			return forward;
		} 
	}

