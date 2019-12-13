package review.comment.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import review.comment.dao.CommentDAO;
import review.comment.vo.CommentBean;

public class CommentReplyProService {
	public boolean ReplyToComment(CommentBean comment) {
		boolean isReplySuccess = false;
		int insertCount = 0;

		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);

		insertCount = commentDAO.insertReplyToComment(comment);

		if (insertCount > 0) {
			commit(con);
			isReplySuccess = true;
		} else {
			rollback(con);
		}
		close(con);

		return isReplySuccess;
	}

}
