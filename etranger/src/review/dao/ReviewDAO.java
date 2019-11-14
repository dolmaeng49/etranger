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
	public ReviewBean selectArticle(int review_num) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ReviewBean reviewBean = null;
		
		try {
			String sql = "SELECT * FROM review WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			
			// 게시물이 존재할 경우 ReviewBean 객체에 모든 데이터 저장
			if(rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setReview_num(rs.getInt("review_num"));
				reviewBean.setReview_member_id(rs.getString("review_member_id"));
				reviewBean.setReview_subject(rs.getString("review_subject"));
				reviewBean.setReview_image(rs.getString("review_image"));
				reviewBean.setReview_content(rs.getString("review_content"));
				reviewBean.setReview_date(rs.getTimestamp("review_date"));
				reviewBean.setReview_readcount(rs.getInt("review_readcount"));
				reviewBean.setReview_package_catagory_code(rs.getString("review_package_category_code"));
			}
			
		} catch (SQLException e) {
			System.out.println("selectArticle() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reviewBean;
	}



	public int updateReadcount(int review_num) {
		// review_num 에 해당하는 게시물 조회수(readcount) 1 증가
		PreparedStatement pstmt = null;
				
		int updateCount = 0;
				
		try {
		// 조회수를 증가시킬 게시물 번호(board_num)를 SQL 구문 작성 시 바로 결합해도 되고
//		String sql = "UPDATE board SET board_readcount=board_readcount+1 WHERE board_num=" + board_num;
					
		// 만능문자(?)를 사용하여 파라미터 값 지정해도 됨(setXXX() 필수)
		String sql = "UPDATE review SET review_readcount=review_readcount+1 WHERE review_num=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, review_num);
		updateCount = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("updateReadcount() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return updateCount;
	}



	public int updateArticle(ReviewBean rb) { // 글수정
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		try {
			// 객체에 저장되어 있는 게시물 수정 정보(작성자, 제목, 내용)을
			// 객체에 저장되어 있는 게시물 번호에 해당하는 레코드에 수정
			String sql = "UPDATE review SET review_subject=?, review_image=?, review_content=? WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rb.getReview_subject());
			pstmt.setString(2, rb.getReview_image());
			pstmt.setString(3, rb.getReview_content());
			pstmt.setInt(4, rb.getReview_num());
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}	// end updateArticle()
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////JWoo 작업영역 끝/////////////////////////////////////
	
	
	
	
}
