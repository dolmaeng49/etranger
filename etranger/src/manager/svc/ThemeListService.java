package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class ThemeListService {
	public ArrayList<CategoryBean> getThemeList() {
//		System.out.println("themeService");
		Connection con = getConnection();
		
		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);
		
		ArrayList<CategoryBean> themeList = null;
		themeList = mdao.selectThemeList();
		
		close(con);
		
		return themeList;
		
	}
}
