package review.service;

import java.sql.Connection;
import static common.db.JdbcUtil.*;

import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewWriteProService {

	public boolean registArticle(ReviewBean rb) {
		System.out.println("ReviewWriteProService - registArticle");
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		int insertCount= reviewDAO.insertArticle(rb);
		
		if(insertCount>0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isWriteSuccess;
	}

}
