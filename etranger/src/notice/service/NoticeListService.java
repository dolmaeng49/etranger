package notice.service;


import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import notice.dao.NoticeDAO;
import notice.vo.NoticeBean;
import review.dao.ReviewDAO;
import review.vo.ReviewBean;

public class NoticeListService {
	
	public int getListCount() {
		
		Connection con =getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		
		int listCount = noticeDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}

	public ArrayList<NoticeBean> getArticleList(int page, int limit) {
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		ArrayList<NoticeBean> articleList = null;
		
		articleList = noticeDAO.selectArticleList(page, limit);
		
		close(con);
		
		return articleList;
	}
		
		
		public NoticeBean getArticle(int notice_num) {
//			System.out.println("ReviewDetailService - getArticle");
			
			Connection con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			
			NoticeBean article = noticeDAO.selectArticle(notice_num);
			
			
			int updateCount = noticeDAO.updateReadcount(notice_num);
			
			// 조회 수 증가 후 리턴받은 updateCount 가 0보다 크면 commit, 아니면 rollback 수행
			if(updateCount > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
			// 공통작업-3 Connection 객체 반환하기
			close(con);
			
			return article;			
		
	}
	
}
