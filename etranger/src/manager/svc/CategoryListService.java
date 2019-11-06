package manager.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static common.db.JdbcUtil.*;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class CategoryListService {
	public ArrayList<CategoryBean> getArticleList() {
		Connection con = getConnection();
		
		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);
		
		ArrayList<CategoryBean> artiArrayList = null;
		artiArrayList = mdao.selectArticleList();
		
		close(con);
		
		return artiArrayList;
		
	}
}
