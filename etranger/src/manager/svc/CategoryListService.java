package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class CategoryListService {

	public int getListCount() {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		int listCount = managerDAO.selectListCount();

		close(con);

		return listCount;
	}

	public ArrayList<CategoryBean> getCategoryList(int page, int limit) {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		ArrayList<CategoryBean> productList = null;

		productList = managerDAO.selectCategoryList(page, limit);

		close(con);

		return productList;
	}
	
	// 최신순
	public ArrayList<CategoryBean> getNewList(int page, int limit) {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		ArrayList<CategoryBean> newList = null;

		newList = managerDAO.selectNewList(page, limit);

		close(con);

		return newList;
	}
	// 인기순
	public ArrayList<CategoryBean> getPopularList(int page, int limit) {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		ArrayList<CategoryBean> popularList = null;
		
		popularList = managerDAO.selectPopularList(page, limit);

		close(con);

		return popularList;
	}
	
	// 추천순 (떨이상품) 
	public ArrayList<CategoryBean> getRecommendedList(int page, int limit) {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		ArrayList<CategoryBean> recommendedList = null;

		recommendedList = managerDAO.selectRecommendedList(page, limit);

		close(con);

		return recommendedList;
	}

	
}
