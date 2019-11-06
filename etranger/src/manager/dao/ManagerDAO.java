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
			pstmt.setString(1, cb.getCityName());
//			pstmt.setInt(2, cb.getCityCode());
			
			insertCount = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public int selectListCount() {
int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT count(*) FROM category_region";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있을 경우(rs.next() 가 true 일 경우)
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("selectListCount() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<CategoryBean> selectCategoryList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();
		
		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (page - 1) * 10;
		
		try {
			// 게시물 조회
			// 참조글번호(ref)를 기준으로 내림차순, 순차번호(seq)를 기준으로 오름차순 정렬
			// 조회 시작 게시물 번호(startRow) 부터 10개(limit)만큼 조회
			String sql = "SELECT * FROM category_region ORDER BY category_region_code asc,LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			// 읽어올 게시물이 존재할 경우
			while(rs.next()) {
				// BoardBean 객체에 읽어온 게시물 1개씩 저장(패스워드 제외) => ArrayList 객체에 추가
				CategoryBean categoryBean = new CategoryBean();
				categoryBean.setRegionCode(rs.getInt("category_region_code"));
				categoryBean.setRegionName(rs.getString("category_region_name"));
				
				articleList.add(categoryBean);
			}
			
		} catch (SQLException e) {
			System.out.println("selectArticleList() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

}
