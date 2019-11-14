package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberResetPasswdProService {

	public boolean updatePasswd(MemberBean memberBean) {
		boolean isUpdateSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// ID 가 일치하는 회원정보의 비밀번호를 변경하는 메서드 호출
		isUpdateSuccess = memberDAO.updatePasswd(memberBean);
		if(isUpdateSuccess) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isUpdateSuccess;
	}
}
