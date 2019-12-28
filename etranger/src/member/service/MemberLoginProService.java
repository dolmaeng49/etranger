package member.service;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import member.vo.MemberBean;

import static common.db.JdbcUtil.*;

public class MemberLoginProService {

	// 전달받은 ID, PASSWORD로 로그인
	public int memberLogin(String member_id, String member_passwd) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// loginResult => 로그인 성공 : 1 / 아이디없음 : 0 / 비밀번호 불일치 : -1 
		int loginResult=memberDAO.selectMemberLogin(member_id,member_passwd);
		
		if(loginResult > 0) { // 로그인 성공시 최근 로그인 시간 update 를 위해
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return loginResult;
	}	
	
	// 아이디를 이용해 회원의 이름 조회
	public MemberBean getMemberInfo(String member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		// 아이디를 이용해 회원의 이름 조회
		// 아이디에 해당하는 정보가 없으면 null
		MemberBean memberBean = memberDAO.getMemberInfo(member_id);
		
		close(con);
		
		return memberBean;
	}


}
