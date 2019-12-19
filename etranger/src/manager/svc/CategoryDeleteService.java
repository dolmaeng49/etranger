package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;

public class CategoryDeleteService {

	public boolean deleteCategory(String code) {
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		int deleteCount = managerDAO.deleteCategory(code);
		
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
