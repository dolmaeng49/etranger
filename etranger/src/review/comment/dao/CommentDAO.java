package review.comment.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import review.comment.vo.CommentBean;

public class CommentDAO {

	private CommentDAO() {
	}

	private static CommentDAO instance;

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	Connection con;

	public void setConnnection(Connection con) {
		this.con = con;
	}

	
	//댓글 삽입
	public int insertComment(CommentBean cb) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		int ref_num = 0;

		try {
			String sql = "SELECT max(review_comment_num) from review_comment";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ref_num = rs.getInt("max(review_comment_num)") + 1;
			}

			sql = "INSERT INTO review_comment values(0,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getReview_comment_member_id());
			pstmt.setString(2, cb.getReview_comment_member_name());
			pstmt.setInt(3, cb.getReview_comment_review_num()); // 테이블 수정
			pstmt.setString(4, cb.getReview_comment_content());
			pstmt.setInt(5, ref_num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			System.out.println("날짜:" + cb.getReview_comment_date());
			insertCount = pstmt.executeUpdate();
			System.out.println(cb.getReview_comment_review_num());
			if (insertCount != 0) {

				sql = "UPDATE review set review_comment_count=review_comment_count+1 where review_num="
						+ cb.getReview_comment_review_num();
				pstmt = con.prepareStatement(sql);
//					pstmt.setInt(1, cb.getReview_comment_review_num());  // 만능문자 ? 로 받아와서 처리하면 에러가 나는 이유 알아보기
				pstmt.executeUpdate(sql);
				System.out.println("review 테이블 값 변경 완료?");
			}

		} catch (Exception e) {
			System.out.println("insertComment() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	//댓글 개수 가져오기
	public int selectCommentCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int commentCount = 0;

		try {
			String sql = "select count(*) from review_comment";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				commentCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectCommentCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return commentCount;
	}

	
	//댓글 목록 가져오기
	public ArrayList<CommentBean> selectCommentList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();

		try {

			String sql = "select * from review_comment order by review_comment_ref, review_comment_seq desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CommentBean cb = new CommentBean();
				cb.setReview_comment_num(rs.getInt("review_comment_num"));
				cb.setReview_comment_member_id(rs.getString("review_comment_member_id"));
				cb.setReview_comment_member_name(rs.getString("review_comment_member_name"));
				cb.setReview_comment_review_num(rs.getInt("review_comment_review_num"));
				cb.setReview_comment_content(rs.getString("review_comment_content"));
				cb.setReview_comment_ref(rs.getInt("review_comment_ref"));
				cb.setReview_comment_lev(rs.getInt("review_comment_lev"));
				cb.setReview_comment_seq(rs.getInt("review_comment_seq"));
				cb.setReview_comment_date(rs.getTimestamp("review_comment_date"));
				commentList.add(cb);
			}
		} catch (SQLException e) {
			System.out.println("selectCommentList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return commentList;
	}

	
	//댓글 가져오기
	public CommentBean selectComment(int comment_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentBean cb = null;

		try {
			String sql = "SELECT * FROM review_comment WHERE review_comment_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cb = new CommentBean();
				cb.setReview_comment_num(rs.getInt("review_comment_num"));
				cb.setReview_comment_member_id(rs.getString("review_comment_member_id"));
				cb.setReview_comment_member_name(rs.getString("review_comment_member_name"));
				cb.setReview_comment_review_num(rs.getInt("review_comment_review_num"));
				cb.setReview_comment_content(rs.getString("review_comment_content"));
				cb.setReview_comment_ref(rs.getInt("review_comment_ref"));
				cb.setReview_comment_ref(rs.getInt("review_comment_lev"));
				cb.setReview_comment_ref(rs.getInt("review_comment_seq"));
				cb.setReview_comment_date(rs.getTimestamp("review_comment_date"));
			}
		} catch (SQLException e) {
			System.out.println("selectComment() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return cb;
	}

	//댓글 수정
	public int updateComment(CommentBean cb) {
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			String sql = "UPDATE review_comment SET review_comment_content=? WHERE review_comment_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getReview_comment_content());
			pstmt.setInt(2, cb.getReview_comment_num());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateComment() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	
	//댓글 작성자 확인
	public boolean isCommentWriter(int review_comment_num, String review_comment_member_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isCommentWriter = false;

		try {
			String sql = "SELECT review_comment_member_id FROM review_comment WHERE review_comment_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_comment_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (review_comment_member_id.equals(rs.getString("review_comment_member_id"))) {
					isCommentWriter = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("isCommentWriter() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return isCommentWriter;
	}

	
	
	//댓글 삭제
	public int deleteComment(int review_comment_num, int review_comment_review_num) {

		PreparedStatement pstmt = null;
		int deleteCount = 0;

		try {
			String sql = "DELETE from review_comment WHERE review_comment_num= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_comment_num);
			deleteCount = pstmt.executeUpdate();

			if (deleteCount != 0) {
				sql = "UPDATE review set review_comment_count=review_comment_count-1 where review_num="
						+ review_comment_review_num;
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate(sql);
				System.out.println("review 테이블 값 변경 완료?");
			}

		} catch (SQLException e) {
			System.out.println("deleteComment() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return deleteCount;
	}

	
	//대댓글 삽입
	public int insertReplyToComment(CommentBean comment) {
		PreparedStatement pstmt = null;
		int insertCount = 0;

		int comment_ref = comment.getReview_comment_ref();
		int comment_lev = comment.getReview_comment_lev();
		int comment_seq = comment.getReview_comment_seq();

		try {

			String sql = "UPDATE review_comment SET review_comment_seq=review_comment_seq+1 WHERE review_comment_ref=? AND review_comment_seq > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_ref);
			pstmt.setInt(2, comment_seq);

			int updateCount = pstmt.executeUpdate();

			// 답글 순서 번호에 대한 업데이트가 성공할 경우(updateCount > 0) commit 수행
			if (updateCount > 0) {
				commit(con);

			} else {
				rollback(con);
			}
			comment_lev = comment_lev + 1; // 새 답글의 들여쓰기 레벨+1
			comment_seq = comment_seq + 1; // 새 답글의 순서번호 +1

			sql = "INSERT INTO review_comment values(0,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comment.getReview_comment_member_id());
			pstmt.setString(2, comment.getReview_comment_member_name());
			pstmt.setInt(3, comment.getReview_comment_review_num());
			pstmt.setString(4, comment.getReview_comment_content());
			pstmt.setInt(5, comment_ref); // 댓글의 댓글이니 ref 값은 그대로 둠.
			pstmt.setInt(6, comment_lev);
			pstmt.setInt(7, comment_seq);

			insertCount = pstmt.executeUpdate();

			if (insertCount != 0) {
				sql = "UPDATE review set review_comment_count=review_comment_count+1 where review_num="
						+ comment.getReview_comment_review_num();
				pstmt.setInt(1, comment.getReview_comment_review_num());
				pstmt.executeUpdate(sql);
				System.out.println("review에 넘어가나?");
			}
		} catch (Exception e) {
			System.out.println("insertReplytoComment() 오류! - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

}
