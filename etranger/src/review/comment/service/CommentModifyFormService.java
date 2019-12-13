package review.comment.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import review.comment.dao.CommentDAO;
import review.comment.vo.CommentBean;

public class CommentModifyFormService {

	public CommentBean getComment(int comment_num) {

		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);

		CommentBean comment = commentDAO.selectComment(comment_num);

		close(con);
		return comment;
	}

}
