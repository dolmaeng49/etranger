package manager.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import manager.dao.ManagerDAO;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;

public class ProductDetailService {

	public ArrayList<CategoryBean> GetProductDetailList(String pcode) {

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

	public ArrayList<ProductBean> GetProductList(ProductBean pb, String pcode) {

		System.out.println("GetProductList Service");

		Connection con = getConnection();
		ManagerDAO managerDAO = ManagerDAO.getInstance();
		managerDAO.setConnection(con);

		ArrayList<ProductBean> productList = null;

		// pcode(선택한 상품의 코드)를 db에 전달
		productList = managerDAO.ProductList(pcode);

		close(con);

		return productList;
	}

}
