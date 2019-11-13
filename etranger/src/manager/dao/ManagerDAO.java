package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manager.vo.CategoryBean;

import static common.db.JdbcUtil.*;

public class ManagerDAO {

	private ManagerDAO() {
	}

	private static ManagerDAO instance;

	public static ManagerDAO getInstance() {
		if (instance == null) {
			instance = new ManagerDAO();
		}

		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 카테고리 추가
	public int insertCategory(CategoryBean cb) {
		int insertCount = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO category_region VALUES (null,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getRegionName());
//			pstmt.setInt(2, cb.getCityCode());

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}

	public ArrayList<CategoryBean> selectArticleList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();

		try {

			String sql = "SELECT * FROM category_region ORDER BY category_region_code DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setRegionCode(rs.getInt("category_region_code"));
				cb.setRegionName(rs.getString("category_region_name"));

				articleList.add(cb);
			}
		} catch (SQLException e) {

			System.out.println("selectArticleList() 오류 - " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	public ArrayList<CategoryBean> selectRegionList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();

		try {

			String sql = "SELECT * FROM category_region ORDER BY category_region_code DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setRegionCode(rs.getInt("category_region_code"));
				cb.setRegionName(rs.getString("category_region_name"));

				articleList.add(cb);
			}
		} catch (SQLException e) {

			System.out.println("selectArticleList() 오류 - " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	public ArrayList<CategoryBean> selectThemeList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();

		try {

			String sql = "SELECT category_theme_code, category_theme_name FROM category_theme";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setThemeCode(rs.getInt("category_theme_code"));
				cb.setThemeName(rs.getString("category_theme_name"));

				themeList.add(cb);
			}
		} catch (SQLException e) {

			System.out.println("selectThemeList() 오류 - " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		return themeList;
	}

}
