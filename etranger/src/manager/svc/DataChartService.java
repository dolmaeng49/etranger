package manager.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;
import manager.vo.DatachartBean;

public class DataChartService {
	
	public DatachartBean getTotalSalesCount() {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		DatachartBean totalSales = null;
		totalSales = managerDAO.TotalSales();

		close(con);

		return totalSales;
	}

	public ArrayList getregionReservationList() {

		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		ArrayList regionReservationList = null;
		regionReservationList = managerDAO.RegionReservationCount();

		close(con);
		return regionReservationList;
	}

	public ArrayList getTotalPayCount() {

		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		ArrayList totalPayCount = null;
		totalPayCount = managerDAO.TotalPayCount();

		close(con);

		return totalPayCount;
	}

	public ArrayList getGenderPayment() {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		ArrayList genderPayment = null;
		genderPayment = managerDAO.GenderPayment();

		close(con);

		return genderPayment;
	}

	public ArrayList getMostProduct() {
		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		ArrayList mostProduct = null;
		mostProduct = managerDAO.MostProduct();

		close(con);
		return mostProduct;
	}

	

}
