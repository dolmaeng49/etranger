package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class ThemeInsertService {

	public boolean InsertTheme(CategoryBean cb) {
		boolean isInsertSuccess = false;
		
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		int insertCount = managerDAO.insertTheme(cb);
		
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
