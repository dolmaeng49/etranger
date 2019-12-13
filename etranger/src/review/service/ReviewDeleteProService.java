package review.service;

import static common.db.JdbcUtil.*;

import java.io.PrintWriter;
import java.sql.Connection;

import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewDeleteProService {

	public boolean isArticleWriter(int review_num, String review_member_id) {

		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);

		boolean isArticleWriter = reviewDAO.isReviewArticleWriter(review_num, review_member_id);
		close(con);

		return isArticleWriter;
	}

	public boolean removeArticle(int review_num) {
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);

		boolean isDeleteSuccess = false;

		int deleteCount = reviewDAO.deleteArticle(review_num);

		if (deleteCount > 0) {
			isDeleteSuccess = true;
			commit(con);

		} else {
			rollback(con);
		}

		close(con);

		return isDeleteSuccess;
	}

}
