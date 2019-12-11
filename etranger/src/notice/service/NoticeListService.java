package notice.service;


import static common.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import notice.dao.NoticeDAO;
import notice.vo.NoticeBean;

public class NoticeListService {
	
	public int getListCount() {
		
		Connection con =getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		
		int listCount = noticeDAO.selectListCount();
		
		System.out.println("총 게시물 수(Service) : "+listCount);
		
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
	
}
