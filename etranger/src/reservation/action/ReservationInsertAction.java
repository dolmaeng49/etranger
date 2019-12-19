package reservation.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberEmailService;
import member.vo.MemberBean;
import reservation.service.ReservationInfoService;
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
			String member_id = request.getParameter("member_id");
			String product_num = request.getParameter("product_num");
			
			Timestamp reservation_date = new Timestamp(System.currentTimeMillis());
			
			ReservationBean rb = new ReservationBean();
			rb.setReservation_category_code(request.getParameter("category_code"));
			rb.setReservation_progress("예약대기");
			rb.setReservation_ispayment("N");
			rb.setReservation_headcount(Integer.parseInt(request.getParameter("headCount")));
			rb.setReservation_price(Integer.parseInt(request.getParameter("price")));
			rb.setReservation_product_num(product_num);
			rb.setReservation_member_id(member_id);
//			rb.setReservation_date(reservation_date);
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
					// 예약 성공시 메일 발송
					ReservationInfoService rInfoService = new ReservationInfoService();
					ArrayList<ReservationBean> reservBeanArr = rInfoService.ReservationInfo(1, 30, member_id);
					ReservationBean rsvb = null;
					for(ReservationBean reservbean : reservBeanArr) {
						if(reservbean.getReservation_product_num().equals(product_num)) {
							// 회원의 예약 목록중 예약한 상품과 상품코드가 일치하는 예약 정보 가져오기
							rsvb = reservbean;
						}
					}
					if(request.getSession().getAttribute("memberInfo") != null && rsvb != null) {
						MemberBean mb = (MemberBean)(request.getSession().getAttribute("memberInfo"));
						String subject = "etranger 패키지 예약 안내 메일입니다.";
						String pattern = "\netranger 상품을 예약해주셔서 감사합니다!"
								+ "\n\n{0} 회원님께서 예약하신 상품은"
								+ "\n{1} 에 출발해 {2} 에 돌아오는 {3} 상품입니다.  "
								+ "\n예약인원은 {4} 명이며 결제하실 금액은 ￦{5} 입니다. \n"
								+ "\n문의사항이 있으신 경우 etrangermanager@gmail.com 로 연락주시기바랍니다. \n"
								+ "\n\n감사합니다.";
						StringBuilder src = new StringBuilder();
						src.append(mb.getMember_name()).append(':').append(rsvb.getPackage_product_depart_date())
						.append(':').append(rsvb.getPackage_product_arriv_date()).append(':').append(rsvb.getPackage_category_name()).append(':')
						.append(rsvb.getReservation_headcount()).append(':').append(rsvb.getReservation_price());
						
						String content = MessageFormat.format(pattern, src.toString().split(":"));
						
						MemberEmailService.mailSend(mb.getMember_email(), subject, content);
					}
					forward = new ActionForward();
					forward.setPath("ReservationInfo.rs?member_id="+request.getParameter("member_id"));
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

