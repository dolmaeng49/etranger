package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberModifyDeleteService {

	// 회원정보 수정, 탈퇴 권한 확인
	// 아이디 비밀번호 정보가 일치하는 회원 존재 여부 리턴
	public int userCheck(String member_id, String member_passwd) {
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// 입력받은 아이디, 비밀번호로 정보가 일치하는 회원 있는지 조회
		int userCheck = memberDAO.userCheck(member_id, member_passwd);
		
		close(con);
		
		return userCheck;
	}

	// 회원 정보 수정
	public boolean modifyMemberInfo(MemberBean memberBean) {
		// DB 작업 성공시 commit 후 true 리턴
		
		Connection con = getConnection();
		MemberDAO memberDAO= MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// 작업 성공 여부를 저장할 변수
		boolean isModifySuccess = false;
		// DAO의 회원정보수정 메서드 호출해 결과 리턴 받음 
		int updateCount = memberDAO.updateMemberInfo(memberBean);
		// 수정된 row가 1개 이상이면 commit 후 true 리턴
		if(updateCount > 0) {
			isModifySuccess = true;
			commit(con);
		} else { // 수정결과 없으면 롤백 후 false 리턴
			rollback(con);
		}
		
		close(con);
		
		return isModifySuccess;
	}
	
	// 전달받은 id 에 해당하는 회원탈퇴
	public boolean deleteMember(String member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean isDeleteSuccess =false;
		
		int deleteCount= memberDAO.deleteMember(member_id);
		
		if(deleteCount > 0) {
			isDeleteSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}

}
