package manager.svc;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

import static common.db.JdbcUtil.*;

public class MangerMainService {

	public int getListCount() {
		Connection con = getConnection();
		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);
		
		int listCount = mdao.selectListCount();
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<CategoryBean> getCategoryList(int page, int limit) {
		Connection con = getConnection();

		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);

		ArrayList<CategoryBean> articleList = null;
		articleList = mdao.selectCategoryList(page, limit);

		close(con);

		return articleList;
	}


}
