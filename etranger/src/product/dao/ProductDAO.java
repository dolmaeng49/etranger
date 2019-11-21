package product.dao;

import java.sql.Connection;

public class ProductDAO {
	
	private Connection con;
	private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
}
