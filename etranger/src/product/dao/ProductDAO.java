package product.dao;

import static common.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manager.vo.CategoryBean;

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

	public int insertWishList(String member_id, String category_code) {
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

	// product_category 테이블의 wish_count 컬럼을 UPDATE
	public int updateCategoryWishCount(String category_code, int wishCount) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "UPDATE product_category SET wish_count=? WHERE wish_category_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wishCount);
			pstmt.setString(2, category_code);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateCategoryWishCount 실패 : " + e.getMessage());
		}
		return updateCount;
	}

	public int deleteWishList(String member_id, String category_code) {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM wish WHERE wish_member_id=? AND wish_category_code=?";
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, member_id);
			pstmt.setString(3, category_code);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addWishList 실패 : " + e.getMessage());
		}
		
		return deleteCount;
	}

	
	//------------------ManagerDAO의 selectListCount, selectCategoryList 불러옴.
	
	// --- selectListCount
		public int selectListCount(String keyword) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int listCount = 0;

			try {
				String sql = "select count(*) from package_category"
						+ " where package_category_name like ? or"
						+ " package_category_region like ? or"
						+ " package_category_city like ? or"
						+ " package_category_theme like ? or"
						+ " package_category_content like ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setString(4, "%"+keyword+"%");
				pstmt.setString(5, "%"+keyword+"%");
				rs = pstmt.executeQuery();

				if (rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("selectListCount(String keyword) 오류! - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return listCount;
		}
		// selectListCount ---
	
		
		
		// selectCategoryList ---
		public ArrayList<CategoryBean> selectCategoryList(int page, int limit, String keyword) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();

			int startRow = (page - 1) * 8;

			try {

				String sql = "select * from package_category"
						+ " where package_category_name like ? or"
						+ " package_category_region like ? or"
						+ " package_category_city like ? or"
						+ " package_category_theme like ? or"
						+ " package_category_content like ?"
						+ " order by package_category_region desc LIMIT ?,?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, "%"+keyword+"%");
				pstmt.setString(4, "%"+keyword+"%");
				pstmt.setString(5, "%"+keyword+"%");
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, limit);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					CategoryBean cb = new CategoryBean();
					cb.setPackage_category_code(rs.getString("package_category_code"));
					cb.setPackage_category_name(rs.getString("package_category_name"));
					cb.setPackage_category_theme(rs.getString("package_category_theme"));
					cb.setPackage_category_image(rs.getString("package_category_image"));
					cb.setPackage_category_content(rs.getString("package_category_content"));
					cb.setPackage_category_region(rs.getInt("package_category_region"));
					cb.setPackage_category_city(rs.getInt("package_category_city"));
					productList.add(cb);
				}
			} catch (SQLException e) {
				System.out.println("selectCategoryList(search) 오류! - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return productList;
		}
		// selectCategoryList ---
	
	
	
	
	
	
}
