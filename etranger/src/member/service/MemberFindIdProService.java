package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberFindIdProService {

	public String findId(MemberBean memberBean) {
		String selectedId = "";
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// 이름, 생일, 성별 정보를 이용해 ID 조회하는 메서드 호출, 결과 리턴
		selectedId = memberDAO.findId(memberBean);
		close(con);
		return selectedId;
	}
	
}
