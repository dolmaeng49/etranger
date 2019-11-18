package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class ProductListService {

	public int getListCount() {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		int listCount = managerDAO.selectListCount();

		close(con);

		return listCount;
	}

	public ArrayList<CategoryBean> getProductList(int page, int limit) {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		ArrayList<CategoryBean> productList = null;

		productList = managerDAO.selectProductList(page, limit);

		close(con);

		return productList;
	}

}
