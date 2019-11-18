package comment.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import comment.dao.CommentDAO;
import comment.vo.CommentBean;
import review.dao.ReviewDAO;

public class CommentWriteProService {

	public boolean AddComment(CommentBean cb) {
		System.out.println("들어왔니? WriteProService-AddComment");
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		int insertCount= CommentDAO.insertComment(cb);
		
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
