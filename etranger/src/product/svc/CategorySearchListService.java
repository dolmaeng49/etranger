package product.svc;

import static common.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import manager.vo.CategoryBean;
import product.dao.ProductDAO;
import product.vo.SearchBean;

public class CategorySearchListService {
	
	public int getListCount(SearchBean searchBean) {
		System.out.println("getListCount");
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);

		int listCount = productDAO.selectListCount(searchBean);

		close(con);

		return listCount;
	}

	public ArrayList<CategoryBean> getCategoryList(int page, int limit, SearchBean searchBean) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		ArrayList<CategoryBean> productList = null;
		productList = productDAO.selectCategoryList(page, limit, searchBean);

		close(con);

		return productList;
	}


}
