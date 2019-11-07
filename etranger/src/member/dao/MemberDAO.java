package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.MemberBean;

import static common.db.JdbcUtil.*;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private Connection con = null;;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int selectMemberLogin(String id, String passwd) {
		int loginResult = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select id from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "select passwd from member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (passwd.equals(rs.getString("passwd")))
						loginResult = 1;
				} else {
					loginResult = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);

		}
		return loginResult;
	}

	public int insertMember(MemberBean memberBean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int insertCount = 0;
		
		try {
			String sql = "insert into member(id,passwd,name,addr,phone,email,gender,leg_date,last_login,grade,birth) values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPasswd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setString(4, memberBean.getAddr());
			pstmt.setString(5, memberBean.getPhone());
			pstmt.setString(6, memberBean.getEmail());
			pstmt.setString(7, memberBean.getGender());
			pstmt.setTimestamp(8, memberBean.getLeg_date());
			pstmt.setTimestamp(9, memberBean.getLast_login());
			pstmt.setString(1, memberBean.getGrade());
			pstmt.setDate(1, memberBean.getBirth());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertArticle() 오류 -"+e.getMessage());
			
		}finally {
			close(rs);
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
			String sql = "SELECT id FROM member WHERE id=?";
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

}

































