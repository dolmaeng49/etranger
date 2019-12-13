package review.service;

import java.sql.Connection;
import java.util.ArrayList;
import static common.db.JdbcUtil.*;

import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class ReviewSearchListService {

	public int getListCount(String search) {
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);

		int listCount = reviewDAO.selectListCount(search);

		close(con);

		return listCount;
	}

	public ArrayList<ReviewBean> getArticleList(int page, int limit, String search) {
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		ArrayList<ReviewBean> articleList = null;

		articleList = reviewDAO.selectArticleList(page, limit, search);

		close(con);

		return articleList;
	}

}
