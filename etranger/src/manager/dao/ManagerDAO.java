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

	// =============================== 카테고리 추가 ========================================
	// --- insertRegion
	public int insertRegion(CategoryBean cb) {
		int insertCount = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO category_region VALUES (null,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getRegionName());

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}
	// insertRegion ---
	

	// --- insertCity
	public int insertCity(CategoryBean cb) {
		int insertCount = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO category_city VALUES(null, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getCityName());
			pstmt.setInt(2, cb.getCityRegionCode());

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}
	// insertCity ---
	
	// --- insertTheme
	public int insertTheme(CategoryBean cb) {
		int insertCount = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO category_theme (category_theme_code,category_theme_name) VALUES (null,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getThemeName());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}
	// insertTheme ---
	
	// =============================== 카테고리 출력 ========================================
	// --- selectRegionList
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
	// selectRegionList ---
	
	// --- selectCityList
	public ArrayList<CategoryBean> selectCityList(int code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CategoryBean> CityList = new ArrayList<CategoryBean>();
		
		String sql = "SELECT * from category_city where category_city_region_code=? ORDER BY category_city_code DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setCityCode(rs.getInt("Category_city_code"));
				cb.setCityName(rs.getString("Category_city_name"));
				
				CityList.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return CityList;
	}
	// selectCityList ---
	
	// --- selectThemeList
	public ArrayList<CategoryBean> selectThemeList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();

		try {

			String sql = "SELECT category_theme_code, category_theme_name FROM category_theme ORDER BY category_theme_code DESC";
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
	// selectThemeList ---

	// --insertCategory
		public int CategoryInsert(CategoryBean cb,String theme) {
			int insertCount = 0;

			PreparedStatement pstmt = null;

			String sql = "INSERT INTO package_category VALUES (?,?,?,?,?,?,?)";

			try {
				pstmt = con.prepareStatement(sql);
				
				/*
				 *  11/15 java시간에 배운걸로(컬럼타입네임) 수정 예정 :-)
				 *  table : package_category
				 *  package_category_code    varchar(100) 
				 *  package_category_name    varchar(50)
				 *  package_category_region  int(11)   
				 *  package_category_city    int(11)      
				 *  package_category_theme   varchar(100) 
				 *  package_category_image   varchar(100) 
				 *  package_category_image   varchar(100) 
				 * 
				 */

				pstmt.setString(1,cb.getPackage_category_region()+"-"+cb.getPackage_category_city()+"-"+theme);
				pstmt.setString(2, cb.getRegionName() +cb.getCityName() + cb.getThemeName());
				pstmt.setInt(3, cb.getPackage_category_region());
				pstmt.setInt(4,cb.getPackage_category_city());
				pstmt.setString(5, theme);
				pstmt.setString(6, cb.getPackage_category_image());
				pstmt.setString(7, cb.getPackage_category_content());

				insertCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return insertCount;
		}
		
	// insertCategory--
		
	// =============================== 카테고리 끝 ========================================
		
}






















