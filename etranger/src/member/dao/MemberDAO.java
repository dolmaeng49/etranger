package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.MemberBean;

import static common.db.JdbcUtil.*;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private Connection con = null;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectMemberLogin(String member_id, String member_passwd) {
		int loginResult = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select member_id from member where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "select member_passwd from member where member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (member_passwd.equals(rs.getString("member_passwd"))) {
						loginResult = 1;
					}else {
						loginResult = -1;
					}
				} 
			}
		} catch (SQLException e) {
			System.out.println("selectMemberLogin 오류 -"+e.getMessage());
		} finally {
			close(rs);
			close(pstmt);

		}
		return loginResult;
	}

	public int insertMember(MemberBean memberBean) {
		PreparedStatement pstmt = null;
		
		int insertCount = 0;
		
		try {
			String sql = "insert into member(member_id,member_passwd,member_name,member_addr,member_phone,member_email,member_gender,member_leg_date,member_grade,member_birth)"
					+ "values(?,?,?,?,?,?,?,?,'bronze',?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberBean.getMember_id());
			pstmt.setString(2, memberBean.getMember_passwd());
			pstmt.setString(3, memberBean.getMember_name());
			pstmt.setString(4, memberBean.getMember_addr());
			pstmt.setString(5, memberBean.getMember_phone());
			pstmt.setString(6, memberBean.getMember_email());
			pstmt.setString(7, memberBean.getMember_gender());
			pstmt.setTimestamp(8, memberBean.getMember_leg_date());
			pstmt.setString(9, memberBean.getMember_birth());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertArticle() 오류 -"+e.getMessage());
			
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	// ID 중복검사
	public boolean checkDup(String id) {
		boolean isDup = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT member_id FROM member WHERE member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 같은 아이디가 존재하는 경우
				isDup = true; // true 리턴
			}
		} catch (SQLException e) {
			System.out.println("checkDup 실패! : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isDup;
	}
	
	// ID 찾기 : 이름, 생년월일, 성별 정보를 이용해
	public String findId(MemberBean memberBean) {
		String selectedId = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT member_id FROM member WHERE member_name=? AND member_birth=? AND member_gender=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getMember_name());
			pstmt.setString(2, memberBean.getMember_birth());
			pstmt.setString(3, memberBean.getMember_gender());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회결과가 존재하는 경우 해당 ID 리턴
				selectedId = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("findId 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		// 조회결과가 없는 경우 null 리턴
		return selectedId;
	}

	public int isCorrectMemberEmail(MemberBean memberBean) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id FROM member WHERE member_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getMember_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "SELECT member_email FROM member WHERE member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, memberBean.getMember_id());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1).equals(memberBean.getMember_email())) {
						return 1; // 아이디, email 정보 일치할 경우
					}
				}
				return -1; // 아이디 존재하지만, email 정보 불일치
			}
			
		} catch (SQLException e) {
			System.out.println("isCorrectMemberEmail 실패 : " + e.getMessage());
		} finally {
			close(rs);
		}
		
		return result;
	}
	
	// ID 가 일치하는 회원정보의 비밀번호를 변경하는 메서드
	public boolean updatePasswd(MemberBean memberBean) {
		boolean isUpdateSuccess = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET member_passwd=? WHERE member_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getMember_passwd());
			pstmt.setString(2, memberBean.getMember_id());
			int result = pstmt.executeUpdate();
			if(result > 0) {
//				isUpdateSuccess = true;
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("updatePasswd 실패! : " + e.getMessage());
		}
		
		
		
		
		return isUpdateSuccess;
	}

}































