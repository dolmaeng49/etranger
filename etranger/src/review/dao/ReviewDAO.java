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
			String sql = "INSERT INTO review values(0,?,?,?,?,?,now(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rb.getReview_member_id());
			pstmt.setString(2, rb.getReview_member_name());
			pstmt.setString(3, rb.getReview_subject());
			pstmt.setString(4, rb.getReview_image());
			pstmt.setString(5, rb.getReview_content());
			pstmt.setInt(6, rb.getReview_readcount());
			pstmt.setString(7, rb.getReview_package_catagory_code());
			pstmt.setInt(8, rb.getReview_star());
			pstmt.setInt(9, rb.getReview_comment_count());
		
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
			rb.setReview_member_name(rs.getString("review_member_name"));
			rb.setReview_star(rs.getInt("review_star"));
			rb.setReview_comment_count(rs.getInt("review_comment_count"));
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
			
			if(rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setReview_num(rs.getInt("review_num"));
				reviewBean.setReview_member_id(rs.getString("review_member_id"));
				reviewBean.setReview_member_name(rs.getString("review_member_name"));
				reviewBean.setReview_subject(rs.getString("review_subject"));
				reviewBean.setReview_image(rs.getString("review_image"));
				reviewBean.setReview_content(rs.getString("review_content"));
				reviewBean.setReview_date(rs.getTimestamp("review_date"));
				reviewBean.setReview_readcount(rs.getInt("review_readcount"));
				reviewBean.setReview_package_catagory_code(rs.getString("review_package_category_code"));
				reviewBean.setReview_star(rs.getInt("review_star"));
				reviewBean.setReview_comment_count(rs.getInt("review_comment_count"));
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
		// review_num 에 해당하는 readcount 1 증가
		PreparedStatement pstmt = null;
		int updateCount = 0;
				
		try {
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


	// 글번호 와 글쓴이 일치 여부 확인
	public boolean isReviewArticleWriter(int review_num, String review_member_id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null; // 조회할 시 필요함
		boolean isArticleWriter = false;
		
		try {
			// review_num이 전달받은 값과 일치하는지 여부 판별
			String sql = "SELECT review_member_id FROM review WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
			if(review_member_id.equals(rs.getString("review_member_id"))) {
				isArticleWriter = true; // 일치시 isArticle 값 true
			}
			
		}
			
		} catch (SQLException e) {
			System.out.println("isReviewArticleWriter() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isArticleWriter;
	} // end isReviewArticleWriter


	// 글 삭제
	public int deleteArticle(int review_num) {

		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		try {
			// review_num 에 해당하는 레코드 삭제
			String sql = "DELETE from review WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
		
	} // end deleteArticle

	// 게시판 글 검색
	public int selectListCount(String search) {

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int listCount = 0;
		try {
			con=getConnection();
//			String sql = "select count(*) from board where subject like '%검색어%'";
			String sql = "select count(*) from review where review_subject like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%"); // setString 시 '' 자동으로 생김
			rs=pstmt.executeQuery();
			if(rs.next()){
				listCount =  rs.getInt("count(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}	
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex) {}
			if(con!=null) try {con.close();}catch(SQLException ex) {}
		}
		return listCount;		
		
	} // end selectListCount(String search)
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////JWoo 작업영역 끝/////////////////////////////////////
	
	
	
	
}
