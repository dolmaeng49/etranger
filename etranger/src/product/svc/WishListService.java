package product.svc;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;

import static common.db.JdbcUtil.*;
import product.dao.ProductDAO;

public class WishListService {

	public boolean addWishList(String member_id, String category_code) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		// DAO 의 메서드 호출, DB 작업 결과 리턴
		int addCount = productDAO.addWishList(member_id, category_code);
		// 작업 성공여부 저장할 변수
		boolean isSuccess = false;
		
		if(addCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isSuccess;
	}	
	
	// ID 에 해당하는 찜목록(ArrayList<String>) 조회
	public ArrayList<String> getMemberWishList(String member_id) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		// 아이디를 이용해 회원의 찜목록 조회
		ArrayList<String> member_wishList = productDAO.getMemberWishList(member_id);
		
		close(con);
		
		return member_wishList;
	}
	
	
}
