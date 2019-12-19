package product.svc;

import static common.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import manager.vo.CategoryBean;
import product.dao.ProductDAO;
import product.vo.SearchBean;

public class CategoryListService {
	
	public int getListCount(SearchBean searchBean) {
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
	
	// 로그인 회원의 성별, 연령대 정보를 활용한 추천 리스트
	public ArrayList<CategoryBean> getPersonalizedList(int page, int limit, String gender, String search_birth_start, String search_birth_end) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		ArrayList<CategoryBean> newList = null;

		newList = productDAO.selectPersonalizedList(page, limit, gender, search_birth_start, search_birth_end);

		close(con);

		return newList;
	}

	public int getWishListCount(String member_id) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
	
		int listCount = productDAO.selectWishListCount(member_id);
	
		close(con);
	
		return listCount;
	}

	public CategoryBean[] getWishCategoryList(int page, int limit, String member_id) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		CategoryBean[] wishCategoryList = null;
		wishCategoryList = productDAO.selectWishCategoryList(page, limit, member_id);

		close(con);

		return wishCategoryList;
	}
	

}
