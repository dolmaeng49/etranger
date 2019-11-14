package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberResetPasswdFormService {

	public int isCorrectMemberEmail(MemberBean memberBean) {
		int result = 0;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		result = memberDAO.isCorrectMemberEmail(memberBean);
		
		close(con);
		return result;
	}
	
}
