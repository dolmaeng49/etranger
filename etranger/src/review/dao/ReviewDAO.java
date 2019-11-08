package review.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import review.vo.ReviewBean;

public class ReviewDAO {
	
	private ReviewDAO() {}
	
	private static ReviewDAO instance;
	
	public static ReviewDAO getInstance() {
		if(instance== null) {
			instance = new ReviewDAO();
		}
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	
		//////////////////////////////////Bong 작업영역 시작////////////////////////////////////////////
	public int insertArticle(ReviewBean rb) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			String sql = "INSERT INTO review(review_num, review_member_id, review_subject, review_image, review_content, review_date, review_readcount, review_package_category_code) values(?,?,?,?,?,now(),?,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0); // 테스트 필요
			pstmt.setString(2, rb.getReview_member_id());
			pstmt.setString(3, rb.getReview_subject());
			pstmt.setString(4, rb.getReview_image());
			pstmt.setString(5, rb.getReview_content());
			pstmt.setInt(6, rb.getReview_readcount());
			pstmt.setString(7, rb.getReview_package_catagory_code());
		
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertArticle() 오류! - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return insertCount;
	}



	public int selectListCount() {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		
		try {
			String sql="select count(*) from review";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}



	public ArrayList<ReviewBean> selectArticleList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		
		int startRow = (page - 1) * 10;	//시작 게시물 번호 계산
		
		try {
			
			String sql = "select * from review order by review_num desc LIMIT ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			
		while(rs.next()) {
			ReviewBean rb = new ReviewBean();
			rb.setReview_num(rs.getInt("review_num"));
			rb.setReview_member_id(rs.getString("review_member_id"));
			rb.setReview_subject(rs.getString("review_subject"));
			rb.setReview_image(rs.getString("review_image"));
			rb.setReview_content(rs.getString("review_content"));
			rb.setReview_date(rs.getTimestamp("review_date"));
			rb.setReview_readcount(rs.getInt("review_readcount"));
			rb.setReview_package_catagory_code(rs.getString("review_package_category_code"));
			articleList.add(rb);
			}
		} catch (SQLException e) {
			System.out.println("selectArticleList() 오류! - " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	



//	public int getCommentNumber() {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int commentNumber=0;
//		//selectArticleList에서 한번에 들고오는 방법 생각해보기
//		
//		
//		
//		return commentNumber;
//	} 
	
	
	
	
	
	
	
	//////////////////////////////////Bong 작업영역 끝////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	////////////////////////////////JWoo 작업영역 시작/////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////JWoo 작업영역 끝/////////////////////////////////////
	
	
	
	
}
