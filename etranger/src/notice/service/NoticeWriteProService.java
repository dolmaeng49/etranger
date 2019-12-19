package notice.service;

import java.sql.Connection;

import notice.dao.NoticeDAO;
import notice.vo.NoticeBean;

import static common.db.JdbcUtil.*;


public class NoticeWriteProService {

	public boolean registArticle(NoticeBean nb) {
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		
		noticeDAO.setConnection(con);
		
		int insertCount= noticeDAO.insertArticle(nb);
		
		if(insertCount>0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isWriteSuccess;
	}

}
