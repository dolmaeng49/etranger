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
		
		ArrayList<CategoryBean> themeArrayList = null;
		themeArrayList = mdao.selectThemeList();
		
		close(con);
		
		return themeArrayList;
	}

	//side, footer에 뿌려주기 위한 메서드(dao단에서 Limit 10으로 10개만 불러오기 위함)
	public ArrayList<CategoryBean> getThemeListAjax() {
		Connection con = getConnection();
		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);
		
		ArrayList<CategoryBean> themeArrayList = null;
		themeArrayList = mdao.selectThemeListLimit();
		
		close(con);
		
		return themeArrayList;
	}
}
