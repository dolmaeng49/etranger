package reservation.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import reservation.dao.ReservationDAO;
import reservation.vo.ReservationBean;

public class ReservationInfoService {

	public ReservationBean ReservationInfo(String id) {
		System.out.println("/MemberReservationService.me");
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);

		ReservationBean rb = reservationDAO.ReservationInfo(id);

		close(con);
		System.out.println("rb headcount" + " " + rb.getReservation_headcount());

		return rb;

	}

}
