package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class CategoryUpdateService {

	public boolean updateCategory(CategoryBean cb) {
//		System.out.println("카테고리서비스");
		boolean isUpdateSuccess = false;

		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		int updateCount = managerDAO.CategoryUpdate(cb);

		if (updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		}
		else {
			rollback(con);
		}

		close(con);

		return isUpdateSuccess;

	}
	
	

}
