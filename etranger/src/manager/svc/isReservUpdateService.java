package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;

public class isReservUpdateService {

	public boolean isupdateReserv(String isReserv, int reservNum) {
		boolean isUpdateSuccess = false;
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		int updateCount = managerDAO.updateIsReserv(isReserv,reservNum);
		
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
