package member.service;

import java.sql.Connection;

import member.dao.MemberDAO;

import static common.db.JdbcUtil.*;

public class MemberLoginProService {

	public int memberLogin(String id, String passwd) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int loginResult=memberDAO.selectMemberLogin(id,passwd);
		close(con);
		
		return loginResult;
	}

}
