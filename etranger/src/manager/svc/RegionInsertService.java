package manager.svc;

import java.sql.Connection;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

import static common.db.JdbcUtil.*;

public class RegionInsertService {

	public boolean InsertCategory(CategoryBean cb) {
		boolean isInsertSuccess = false;
		
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		int insertCount = managerDAO.insertRegion(cb);
		
		if(insertCount > 0) {
			commit(con);
			isInsertSuccess =true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		
		return isInsertSuccess;
	}
	
		
}
