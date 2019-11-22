package product.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import product.dao.ProductDAO;

public class CategoryListService {
	// 상품의 총 개수를 리턴하는 메서드
	public int getProductListCount() {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		
		
		
		int productListCount = 0;
		
		close(con);
		
		return productListCount;
	}
	
	public void getProductList() {
		
	}
}
