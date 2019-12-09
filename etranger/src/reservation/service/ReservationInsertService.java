package reservation.service;

import java.sql.Connection;
import static common.db.JdbcUtil.*;

import reservation.dao.ReservationDAO;
import reservation.vo.ReservationBean;

public class ReservationInsertService {

	public boolean insertReservation(ReservationBean rb) {
		System.out.println("ReservationInsertService");
		boolean isInsertSuccess = false;
		
		Connection con = getConnection();
		ReservationDAO rdao = ReservationDAO.getInstance();
		rdao.setConnection(con);
		
		int insertCount = rdao.insertReservation(rb);
		if(insertCount > 0) {
			commit(con);
			isInsertSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isInsertSuccess;
	}
	
}
