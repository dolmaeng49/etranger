package comment.service;
import static common.db.JdbcUtil.*;

import java.sql.Connection;

import comment.dao.CommentDAO;
import comment.vo.CommentBean;

public class CommentModifyFormService {

	public CommentBean getComment(int comment_num) {
		System.out.println("들어왔니? CommentModifyFormService-getComment()");
		
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);
		
		CommentBean comment = commentDAO.selectComment(comment_num);
		
//		commit(con);
		
		close(con);
		
		return comment;
	}

}
