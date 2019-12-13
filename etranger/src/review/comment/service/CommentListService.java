package review.comment.service;

import static common.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import review.comment.dao.CommentDAO;
import review.comment.vo.CommentBean;

public class CommentListService {

	public int getCommentCount() {

		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);

		int commentCount = commentDAO.selectCommentCount();

		close(con);

		return commentCount;
	}

	public ArrayList<CommentBean> getCommentList() {
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);

		ArrayList<CommentBean> commentList = null;

		commentList = commentDAO.selectCommentList();

		close(con);

		return commentList;
	}

}
