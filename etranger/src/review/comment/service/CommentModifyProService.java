package review.comment.service;
import static common.db.JdbcUtil.*;

import java.sql.Connection;

import review.comment.dao.CommentDAO;
import review.comment.vo.CommentBean;

public class CommentModifyProService {

	public boolean modifyComment(CommentBean cb) {
		System.out.println("들어왔니? CommentModifyProService-modifyComment()");
		
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnnection(con);
		
		int updateCount = commentDAO.updateComment(cb);
		
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
