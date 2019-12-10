package product.svc;

import static common.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import manager.vo.CategoryBean;
import product.dao.ProductDAO;

public class CategorySearchListService {

	public int getListCount(String keyword) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);

		int listCount = productDAO.selectListCount(keyword);

		close(con);

		return listCount;
	}

	public ArrayList<CategoryBean> getCategoryList(int page, int limit, String keyword) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		ArrayList<CategoryBean> productList = null;

		productList = productDAO.selectCategoryList(page, limit, keyword);

		close(con);

		return productList;
	}


}
