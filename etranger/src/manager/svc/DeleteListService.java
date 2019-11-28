package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;

public class DeleteListService {

	public ArrayList<ProductBean> DeleteList(String dpcode) {
		Connection con = getConnection();

		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);

		ArrayList<ProductBean> deletelist = null;
		deletelist = mdao.DeleteListDAO(dpcode);

		close(con);

		return deletelist;
	}
	
}
