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

	public int selectMemberLogin(String member_id, String member_passwd) {
		int loginResult = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select id from member where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "select passwd from member where member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (member_passwd.equals(rs.getString("passwd")))
						loginResult = 1;
				} else {
					loginResult = -1;
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
		ResultSet rs = null;
		
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

}































