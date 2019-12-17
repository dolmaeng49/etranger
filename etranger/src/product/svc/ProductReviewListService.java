package product.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ProductReviewListService {
	public int getListCount(String packageCategoryCode) {
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);

		int listCount = reviewDAO.selectProductListCount(packageCategoryCode);

		close(con);

		return listCount;
	}

	public ArrayList<ReviewBean> getReviewList(String packageCategoryCode) {
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		ArrayList<ReviewBean> reviewList = null;

		reviewList = reviewDAO.selectReviewList(packageCategoryCode);

		close(con);

		return reviewList;
	}

}
