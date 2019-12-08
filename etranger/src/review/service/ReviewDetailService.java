package review.service;


import review.vo.ReviewBean;

import java.sql.Connection;
import static common.db.JdbcUtil.*;
import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewDetailService {

	public ReviewBean getArticle(int review_num, boolean readcountDuplFlag) {
//		System.out.println("ReviewDetailService - getArticle");
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		ReviewBean article = reviewDAO.selectArticle(review_num);
		
		
		if(!readcountDuplFlag) {
			int updateCount = reviewDAO.updateReadcount(review_num);
			
			if(updateCount > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
			
			close(con);
		}
		
		
		
		return article;
	}
	
	// commentlistAjax에 필요한 값 간단 출력
	public ReviewBean getArticleForComment(int review_num) {
		System.out.println("ReviewDetailService - getArticle");
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		ReviewBean article = reviewDAO.selectArticle(review_num);
			
			close(con);
	
		return article;
	}
	

}
