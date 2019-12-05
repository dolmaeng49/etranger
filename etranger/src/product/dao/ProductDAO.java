package product.dao;

import static common.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
	
	private Connection con;
	private ProductDAO() {}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int addWishList(String member_id, String category_code) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO wish VALUES(?,?,?)";
		int addCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, member_id);
			pstmt.setString(3, category_code);
			
			addCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addWishList 실패 : " + e.getMessage());
		}
		
		return addCount;
	}
	
	public ArrayList<String> getMemberWishList(String member_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> member_wishList = new ArrayList<String>();
		String sql = "SELECT wish_category_code FROM wishr WHERE wish_member_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			while(rs.next()) { // 조회결과 존재할경우 반복 접근
				member_wishList.add(rs.getString(1));// 조회 결과 ArrayList 객체에 저장
			}
		} catch (SQLException e) {
			System.out.println("getMemberName 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return member_wishList;
	}

}
