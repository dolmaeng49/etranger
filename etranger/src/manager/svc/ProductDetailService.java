package manager.svc;

import static common.db.JdbcUtil.close;

import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;

public class ProductDetailService {
	
	public ArrayList<CategoryBean> GetProductDetailList(CategoryBean cb, String pcode) {
		
		
		System.out.println("ProductDetailService");

		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);
		
		ArrayList<CategoryBean> pdetail = null;
		
		
		// pcode(선택한 상품의 코드)를 db에 전달 
		pdetail = managerDAO.ProductDetailList(pcode);
		
		
		
		
	
		
		
		close(con);

		return pdetail; // 상품 상세리스트를 리턴 

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
