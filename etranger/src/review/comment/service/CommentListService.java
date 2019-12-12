package review.comment.service;
import static common.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import review.comment.dao.CommentDAO;
import review.comment.vo.CommentBean;

public class CommentListService {

	public int getCommentCount() {
		System.out.println("들어왔니? -CommentListService");
		
		Connection con =getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnnection(con);
		
		int commentCount = commentDAO.selectCommentCount();
		System.out.println("총 댓글 수(Service) : "+commentCount);
		
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
