package member.service;

import java.sql.Connection;
import static common.db.JdbcUtil.*;
import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberJoinProService {

	public boolean registMember(MemberBean memberBean) {
		System.out.println("MemberJoinProService");
		boolean isWriteSuccess = false; 
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int insertCount = memberDAO.insertMember(memberBean);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isWriteSuccess;
	}

}
