package manager.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class RegionReservationCountService {

	public ArrayList<CategoryBean> getregionReservationList() {
		
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		ArrayList<CategoryBean> regionReservationList = null;
		regionReservationList = managerDAO.RegionReservationCount();
		
		close(con);
		
		return regionReservationList;
	}

}
