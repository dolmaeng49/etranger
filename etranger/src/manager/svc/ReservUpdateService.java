package manager.svc;

import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;

public class ReservUpdateService {


	public boolean updateReserv(String reservation_progress, int reservNum) {
		boolean isUpdateSuccess = false;
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		int updateCount = managerDAO.updateReserv(reservation_progress,reservNum);
		
		if(updateCount>0) {
			commit(con);
			isUpdateSuccess=true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isUpdateSuccess;
	}

}
