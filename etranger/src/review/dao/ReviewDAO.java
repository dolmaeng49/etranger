package review.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import review.vo.ReviewBean;

public class ReviewDAO {

	private ReviewDAO() {
	}

	private static ReviewDAO instance;

	public static ReviewDAO getInstance() {
		if (instance == null) {
			instance = new ReviewDAO();
		}
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 글쓰기
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

	// 글 개수 가져오기
	public int selectListCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;

		try {
			String sql = "select count(*) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	// 글 목록 가져오기
	public ArrayList<ReviewBean> selectArticleList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();

		int startRow = (page - 1) * 10; // 시작 게시물 번호 계산

		try {

			String sql = "select * from review order by review_num desc LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	// 글 가져오기
	public ReviewBean selectArticle(int review_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBean reviewBean = null;
		try {
			String sql = "SELECT * FROM review WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
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

	// 조회수 증가
	public int updateReadcount(int review_num) {
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

	// 글 수정
	public int updateArticle(ReviewBean rb) {
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			String sql = "UPDATE review SET review_subject=?, review_image=?, review_content=?, review_star=? WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rb.getReview_subject());
			pstmt.setString(2, rb.getReview_image());
			pstmt.setString(3, rb.getReview_content());
			pstmt.setInt(4, rb.getReview_star());
			pstmt.setInt(5, rb.getReview_num());
			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateArticle() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 작성자 일치 여부 확인
	public boolean isReviewArticleWriter(int review_num, String review_member_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isArticleWriter = false;

		try {
			String sql = "SELECT review_member_id FROM review WHERE review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (review_member_id.equals(rs.getString("review_member_id"))) {
					isArticleWriter = true;
				}

			}

		} catch (SQLException e) {
			System.out.println("isReviewArticleWriter() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return isArticleWriter;
	}

	// 글 삭제
	public int deleteArticle(int review_num) {

		PreparedStatement pstmt = null;
		int deleteCount = 0;

		try {
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

	}

	// 게시판 글 검색
	public int selectListCount(String search) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int listCount = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from review" + " where review_member_name like ? or"
					+ " review_subject like ? or" + " review_content like ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt("count(*)");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return listCount;

	}

	// 검색 글 리스트 가져오기
	public ArrayList<ReviewBean> selectArticleList(int page, int limit, String search) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();

		int startRow = (page - 1) * 10; // 시작 게시물 번호 계산

		try {

			String sql = "select * from review" + " where review_member_name like ? or" + " review_subject like ? or"
					+ " review_content like ?" + " order by review_num desc LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			System.out.println("selectArticleList(search) 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	
	
	
	// ============= CategoryDetail =========================
	
	public int selectProductListCount(String packageCategoryCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;

		try {
			String sql = "select count(*) from review where review_package_category_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, packageCategoryCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectListCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<ReviewBean> selectReviewList(String packageCategoryCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewBean> ReviewList = new ArrayList<ReviewBean>();

		try {
			String sql = "SELECT * FROM review WHERE review_package_category_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, packageCategoryCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				ReviewList.add(rb);
			}

		} catch (SQLException e) {
			System.out.println("selectReviewList(packageCategoryCode) 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return ReviewList;
	}

}
