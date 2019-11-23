package comment.service;

import static common.db.JdbcUtil.*;


import java.sql.Connection;

import comment.dao.CommentDAO;
import comment.vo.CommentBean;

public class CommentWriteProService {

	public boolean AddComment(CommentBean cb) {
		System.out.println("들어왔니?-CommentWriteProService");
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnnection(con);
		
		int insertCount= commentDAO.insertComment(cb);
		
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
