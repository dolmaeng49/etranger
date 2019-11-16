package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class ProductListService {

	public ArrayList<CategoryBean> getProductList() {
		Connection con = getConnection();

		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);

		ArrayList<CategoryBean> productArrayList = null;
		productArrayList = mdao.selectProductList();

		close(con);

		return productArrayList;
	}

}
