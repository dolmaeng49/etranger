package comment.service;

import static common.db.JdbcUtil.*;


import java.sql.Connection;

import comment.dao.CommentDAO;
import comment.vo.CommentBean;

public class CommentReplyProService {
	public boolean ReplyToComment(CommentBean comment) {
		System.out.println("들어왔니? CommentReplyProService- ReplyToComment()");
		boolean isReplySuccess = false;
		int insertCount = 0;
		
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);
		
		insertCount=commentDAO.insertReplyToComment(comment);
		
		if(insertCount>0) {
			commit(con);
			isReplySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		
		
		return isReplySuccess;
	}

}
