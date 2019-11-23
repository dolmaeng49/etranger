package review.service;


import review.vo.ReviewBean;

import java.sql.Connection;
import static common.db.JdbcUtil.*;
import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewDetailService {

	public ReviewBean getArticle(int review_num) {
		System.out.println("ReviewDetailService - getArticle");
		
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		ReviewBean article = reviewDAO.selectArticle(review_num);
		
		// BoardDAO 의 updateReadcount() 메서드를 호출하여 조회수 증가
		// => 파라미터 : board_num, 리턴타입 : 
		int updateCount = reviewDAO.updateReadcount(review_num);
		
		// 조회 수 증가 후 리턴받은 updateCount 가 0보다 크면 commit, 아니면 rollback 수행
		if(updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		
		close(con);
		
		
		return article;
	}


}
