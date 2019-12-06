package product.svc;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;

import static common.db.JdbcUtil.*;
import product.dao.ProductDAO;

public class WishListService {

	// wish 테이블에 INSERT
	// product_category 테이블의 wish_count 컬럼을 UPDATE (DB의 트리거로 대체 가능)
	public boolean insertWishList(String member_id, String category_code) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		boolean isSuccess = false;
		
		// wish 테이블에 ISERT 작업
		int insertCount = productDAO.insertWishList(member_id, category_code);
		
		if(insertCount > 0) { // INSERT 성공시
			// product_category 테이블 UPDATE 작업 (wish_count + 1)
			int updateCount = productDAO.updateCategoryWishCount(category_code, insertCount);
			
			if(updateCount > 0) { // UPDATE 성공시
				// 두 개의 작업 모두 성공했을때 commit 후 true 리턴
				commit(con);
				isSuccess = true; 
			} else {
				rollback(con);
			}
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
	
	// wish 테이블에 DELETE
	// product_category 테이블의 wish_count 컬럼을 UPDATE (DB의 트리거로 대체 가능)
	public boolean deleteWishList(String member_id, String category_code) {
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		boolean isSuccess = false;
		
		// wish 테이블에 DELETE 작업
		int deleteCount = productDAO.deleteWishList(member_id, category_code);
		
		if(deleteCount > 0) { // DELETE 성공시
			// product_category 테이블 UPDATE 작업 (wish_count - 1)
			int updateCount = productDAO.updateCategoryWishCount(category_code, -deleteCount);
			
			if(updateCount > 0) { // UPDATE 성공시
				// 두 개의 작업 모두 성공했을때 commit 후 true 리턴
				commit(con);
				isSuccess = true; 
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		return isSuccess;
	}

	
	
}
