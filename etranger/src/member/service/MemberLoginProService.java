package member.service;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;

import static common.db.JdbcUtil.*;

public class MemberLoginProService {

	public int memberLogin(String member_id, String member_passwd) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// loginResult => 로그인 성공 : 1 / 아이디없음 : 0 / 비밀번호 불일치 : -1 
		int loginResult=memberDAO.selectMemberLogin(member_id,member_passwd);
		
		close(con);
		
		return loginResult;
	}

	public String getMemberName(String member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		// 아이디를 이용해 회원의 이름 조회
		String member_name = memberDAO.getMemberName(member_id);
		
		close(con);
		
		return member_name;
	}

	public ArrayList<String> getMemberWishList(String member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		// 아이디를 이용해 회원의 찜목록 조회
		ArrayList<String> member_wishList = memberDAO.getMemberWishList(member_id);
		
		close(con);
		
		return member_wishList;
	}
	
	

}
