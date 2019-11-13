package manager.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class CityListService {

	public ArrayList<CategoryBean> getCityList(int code) {
		Connection con = getConnection();

		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);

		ArrayList<CategoryBean> cityArrayList = null;
		cityArrayList = mdao.selectCityList(code);

		close(con);

		return cityArrayList;
	}

}
