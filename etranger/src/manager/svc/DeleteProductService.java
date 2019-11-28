package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import manager.dao.ManagerDAO;

public class DeleteProductService {

	public boolean DeleteProduct(String deletepcode) {
		boolean isSuccess = false;
		
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		int deleteCount = managerDAO.DeleteProduct(deletepcode);
		
		
		
		if(deleteCount > 0) {
			commit(con);
			isSuccess =true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		
		return isSuccess;
	}

		
		
		
		
		
	}
	
	
	

