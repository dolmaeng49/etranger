package reservation.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;
import reservation.dao.ReservationDAO;
import reservation.vo.ReservationBean;

public class ReservDeleteService {

	public boolean deleteReserv(int reservNum) {
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		reservationDAO.setConnection(con);
		
		int deleteCount = reservationDAO.deleteReserv(reservNum);
		
		if(deleteCount>0) {
			commit(con);
			isDeleteSuccess = true;
			
		}else {
			rollback(con);
		}
		
		close(con);
		
		
		
		return isDeleteSuccess;
	}

}
