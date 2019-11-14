package manager.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class RegionListService {

	public ArrayList<CategoryBean> getRegionList() {
		Connection con = getConnection();

		ManagerDAO mdao = ManagerDAO.getInstance();
		mdao.setConnection(con);

		ArrayList<CategoryBean> regionArrayList = null;
		regionArrayList = mdao.selectRegionList();

		close(con);

		return regionArrayList;
	}

}
