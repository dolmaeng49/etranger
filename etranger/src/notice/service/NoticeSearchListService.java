package notice.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import notice.dao.NoticeDAO;
import notice.vo.NoticeBean;


public class NoticeSearchListService {
	public int getListCount(String search) {
		
		Connection con =getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		
		int listCount = noticeDAO.selectListCount(search);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<NoticeBean> getArticleList(int page, int limit, String search) {
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		ArrayList<NoticeBean> articleList = null;
		
		articleList = noticeDAO.selectArticleList(page, limit, search);
		
		close(con);
		
		return articleList;
	}

}

