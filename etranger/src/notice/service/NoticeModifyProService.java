package notice.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import notice.dao.NoticeDAO;
import notice.vo.NoticeBean;

public class NoticeModifyProService {

	public boolean modifyArticle(NoticeBean nb) {
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		
		noticeDAO.setConnection(con);
		
		int updateCount= noticeDAO.updateArticle(nb);
		
		if(updateCount>0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isModifySuccess;
		
	}

}
