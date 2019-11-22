package review.service;

import java.sql.Connection;
import java.util.ArrayList;
import static common.db.JdbcUtil.*;


import review.dao.ReviewDAO;
import review.vo.ReviewBean;


public class ReviewSearchListService {

	public int getListCount(String search) {
		System.out.println("ReviewSearchListService");
		
		Connection con =getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		int listCount = reviewDAO.selectListCount(search);
		
		System.out.println("검색한 게시물 수(Service) : "+listCount);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<ReviewBean> getArticleList(int page, int limit) {
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		ArrayList<ReviewBean> articleList = null;
		
		articleList = reviewDAO.selectArticleList(page, limit);
		
		close(con);
		
		return articleList;
	}

	
//	public int getCommentNumber() {
//		System.out.println("getCommentCount");
//		Connection con = getConnection();
//		ReviewDAO reviewDAO = ReviewDAO.getInstance();
//		reviewDAO.setConnection(con);
//		
//		commentNumber = reviewDAO.getCommentNumber();
//		
//		close(con);
//		
//		return commentNumber
//	}

}
