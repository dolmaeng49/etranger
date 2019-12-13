package review.service;

import java.sql.Connection;
import java.util.ArrayList;
import static common.db.JdbcUtil.*;


import review.dao.ReviewDAO;
import review.vo.ReviewBean;


public class ReviewListService {

	public int getListCount()
	
	{
		Connection con =getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		int listCount = reviewDAO.selectListCount();
		
		System.out.println("총 게시물 수(Service) : "+listCount);
		
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
	
}
