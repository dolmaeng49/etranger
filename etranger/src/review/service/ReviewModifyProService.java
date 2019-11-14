package review.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewModifyProService {

	public boolean modifyArticle(ReviewBean rb) {
		System.out.println("ReviewModifyProService - modifyArticle");
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		int updateCount= reviewDAO.updateArticle(rb);
		
		if(updateCount>0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isModifySuccess;
		
	}

}
