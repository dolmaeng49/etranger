package reservation.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import reservation.dao.ReservationDAO;
import reservation.vo.ReservationBean;

public class ReservationInfoService {

	public ArrayList<ReservationBean> ReservationInfo(String id) {
		System.out.println("/MemberReservationService.me");
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);

		ArrayList<ReservationBean> rb = reservationDAO.ReservationInfo(id);
			
		
		
		close(con);
		
		return rb;

	}

}
