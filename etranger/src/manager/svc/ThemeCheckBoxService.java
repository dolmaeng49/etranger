package manager.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class ThemeCheckBoxService {

	public ArrayList<CategoryBean> getThemeList(){
		Connection con = getConnection();
		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);
//		System.out.println("ThemeCheckBoxService");
		
		ArrayList<CategoryBean> themeArrayList = null;
		themeArrayList = mdao.selectThemeList();
		
		close(con);
		
		return themeArrayList;
	}
}
