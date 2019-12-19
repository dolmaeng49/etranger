package reservation.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import member.dao.MemberDAO;
import member.vo.MemberBean;
import reservation.dao.ReservationDAO;
import reservation.vo.ReservationBean;
import review.dao.ReviewDAO;

public class ReservationInfoService {

	public int getListCount(String id)

	{
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);

		int listCount = reservationDAO.selectReservationListCount(id);

		close(con);

		return listCount;
	}

	public ArrayList<ReservationBean> ReservationInfo(int page, int limit, String id) {
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);

		ArrayList<ReservationBean> rb = reservationDAO.ReservationInfo(page, limit, id);

		close(con);

		return rb;

	}
}
