package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;

public class MemberIdDupCheckService {

	public boolean checkDup(String id) {
		boolean isDup = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// ID 중복 여부를 확인하는 메서드 호출, 결과 리턴
		isDup = memberDAO.checkDup(id);
		
		
		close(con);
		
		return isDup;
	}
	
}
