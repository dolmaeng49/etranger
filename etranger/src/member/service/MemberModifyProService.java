package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberModifyProService {

	public boolean isArticleWriter(String member_id, String member_passwd) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean isArticleWriter = memberDAO.isMemberArticleWriter(member_id, member_passwd);
		
		close(con);
		
		return isArticleWriter;
		
	}

	
	public boolean modifyArticle(MemberBean article) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean isModifySuccess = false;
		
		int updateCount = memberDAO.updateArticle(article);
		
		if(updateCount > 0) {
			isModifySuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
	}

}
