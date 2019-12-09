package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import reservation.vo.ReservationBean;


public class ReservListService {
	public int getListCount() {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		int listCount = managerDAO.selectListCount();

		close(con);

		return listCount;
	}

//	ArrayList<ReservationBean> reservList = (ArrayList<ReservationBean>)request.getAttribute("reservList");
	public ArrayList<ReservationBean> getReservList(int page, int limit) {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		ArrayList<ReservationBean> reservList = null;

		reservList = managerDAO.selectReservList(page, limit);

		close(con);

		return reservList;
	}
}
