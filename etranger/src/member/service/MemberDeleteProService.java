package member.service;

import java.sql.Connection;
import static common.db.JdbcUtil.*;
import member.dao.MemberDAO;

public class MemberDeleteProService {

	public boolean isArticleWriter(String member_id, String member_passwd) {
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		
		boolean isArticleWriter=memberDAO.ismemberArticleWriter(member_id,member_passwd);
		System.out.println(member_passwd);
		
		
		
		close(con);
		
		return isArticleWriter;
	}

	public static boolean removeArticle(String member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean isDeleteSuccess =false;
		
		
		int deleteCount= memberDAO.deleteARticle(member_id);
		
		if(deleteCount>0) {
			isDeleteSuccess=true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}

	
}
