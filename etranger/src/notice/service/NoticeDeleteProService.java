package notice.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import notice.dao.NoticeDAO;

public class NoticeDeleteProService {

	public boolean isArticleWriter(int notice_num, String notice_member_id) {
		System.out.println("NoticeDeleteProService - isArticleWriter");
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		
		boolean isArticleWriter = noticeDAO.isNoticeArticleWriter(notice_num, notice_member_id);
		close(con);
		
		return isArticleWriter;
	}

	public boolean removeArticle(int notice_num) {
		System.out.println("removeArticle");
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		
		boolean isDeleteSuccess = false;
		
		int deleteCount = noticeDAO.deleteArticle(notice_num);
		
		if(deleteCount > 0) {
			isDeleteSuccess = true;
			commit(con);
			
		} else {
			rollback(con);
		}		

		close(con);
		
		return isDeleteSuccess;
	}
	
	


}

