package member.service;

import java.sql.Connection;

import member.dao.MemberDAO;

import static common.db.JdbcUtil.*;

public class MemberLoginProService {

	public int memberLogin(String member_id, String member_passwd) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int loginResult=memberDAO.selectMemberLogin(member_id,member_passwd);
		
		close(con);
		
		return loginResult;
	}

}
