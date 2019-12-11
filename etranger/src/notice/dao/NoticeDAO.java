package notice.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import notice.vo.NoticeBean;


public class NoticeDAO {
	
	private NoticeDAO() {}
	
	private static NoticeDAO instance;
	
	public static NoticeDAO getInstance() {
		if(instance== null) {
			instance = new NoticeDAO();
		}
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
		//////////////////////////////////Bong 작업영역 시작////////////////////////////////////////////
	public int insertArticle(NoticeBean nb) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			String sql = "INSERT INTO notice values(0,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nb.getNotice_member_id());
			pstmt.setString(2, nb.getNotice_subject());
			pstmt.setString(3, nb.getNotice_content());
			pstmt.setString(4, nb.getNotice_image());
			pstmt.setInt(5, nb.getNotice_readcount());
//			pstmt.setString(7, nb.getReview_package_catagory_code());
//			pstmt.setInt(8, nb.getReview_star());
//			pstmt.setInt(9, nb.getReview_comment_count());
		
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
			String sql="select count(*) from notice";
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


	public ArrayList<NoticeBean> selectArticleList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeBean> articleList = new ArrayList<NoticeBean>();
		
		int startRow = (page - 1) * 10;	//시작 게시물 번호 계산
		
		try {
			
			String sql = "select * from notice order by notice_num desc LIMIT ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			
		while(rs.next()) {
			NoticeBean nb = new NoticeBean();
			nb.setNotice_num(rs.getInt("notice_num"));
			nb.setNotice_member_id(rs.getString("notice_member_id"));
			nb.setNotice_subject(rs.getString("notice_subject"));
			nb.setNotice_content(rs.getString("notice_content"));
			nb.setNotice_image(rs.getString("notice_image"));
			nb.setNotice_readcount(rs.getInt("notice_readcount"));
			nb.setNotice_date(rs.getTimestamp("notice_date"));
			articleList.add(nb);
			}
		} catch (SQLException e) {
			System.out.println("selectArticleList() 오류! - " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public NoticeBean selectArticle(int notice_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeBean noticeBean = null;
		try {
			String sql = "SELECT * FROM notice WHERE notice_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeBean = new NoticeBean();
				noticeBean.setNotice_num(rs.getInt("notice_num"));
				noticeBean.setNotice_member_id(rs.getString("notice_member_id"));
				noticeBean.setNotice_subject(rs.getString("notice_subject"));
				noticeBean.setNotice_content(rs.getString("notice_content"));
				noticeBean.setNotice_image(rs.getString("notice_image"));
				noticeBean.setNotice_readcount(rs.getInt("notice_readcount"));
				noticeBean.setNotice_date(rs.getTimestamp("notice_date"));
			}
		} catch (SQLException e) {
			System.out.println("selectArticle() 오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeBean;
	}



	public int updateReadcount(int notice_num) {
		// review_num 에 해당하는 readcount 1 증가
		PreparedStatement pstmt = null;
		int updateCount = 0;
				
		try {
		String sql = "UPDATE notice SET notice_readcount=notice_readcount+1 WHERE notice_num=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, notice_num);
		updateCount = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("updateReadcount() 오류 - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	

//	public int updateArticle(ReviewBean rb) { // 글수정
//		PreparedStatement pstmt = null;
//		int updateCount = 0;
//		
//		try {
//			String sql = "UPDATE review SET review_subject=?, review_image=?, review_content=? WHERE review_num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, rb.getReview_subject());
//			pstmt.setString(2, rb.getReview_image());
//			pstmt.setString(3, rb.getReview_content());
//			pstmt.setInt(4, rb.getReview_num());
//			updateCount = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println("updateArticle() 오류 - " + e.getMessage());
//		} finally {
//			close(pstmt);
//		}
//		return updateCount;
//	}	// end updateArticle()


	// 글번호 와 글쓴이 일치 여부 확인
//	public boolean isReviewArticleWriter(int review_num, String review_member_id) {
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; // 조회할 시 필요함
//		boolean isArticleWriter = false;
//		
//		try {
//			// review_num이 전달받은 값과 일치하는지 여부 판별
//			String sql = "SELECT review_member_id FROM review WHERE review_num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, review_num);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				
//			if(review_member_id.equals(rs.getString("review_member_id"))) {
//				isArticleWriter = true; // 일치시 isArticle 값 true
//			}
//			
//		}
//			
//		} catch (SQLException e) {
//			System.out.println("isReviewArticleWriter() 오류 - " + e.getMessage());
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		return isArticleWriter;
//	} // end isReviewArticleWriter


	// 글 삭제
//	public int deleteArticle(int review_num) {
//
//		PreparedStatement pstmt = null;
//		int deleteCount = 0;
//		
//		try {
//			// review_num 에 해당하는 레코드 삭제
//			String sql = "DELETE from review WHERE review_num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, review_num);
//			deleteCount = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("deleteArticle() 오류 - " + e.getMessage());
//		} finally {
//			close(pstmt);
//		}
//		
//		return deleteCount;
//		
//	} // end deleteArticle

	// 게시판 글 검색
//	public int selectListCount(String search) {
//
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		int listCount = 0;
//		try {
//			con=getConnection();
//			
//			String sql="select count(*) from review"
//					+ " where review_member_name like ? or"
//					+ " where review_subject like ? or"
//					+ " where review_content like ?";
//			
//			pstmt=con.prepareStatement(sql);
//			pstmt.setString(1, "%"+search+"%");
//			pstmt.setString(2, "%"+search+"%");
//			pstmt.setString(3, "%"+search+"%");
//			rs=pstmt.executeQuery();
//			if(rs.next()){
//				listCount =  rs.getInt("count(*)");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(rs!=null) try {rs.close();}catch(SQLException ex) {}	
//			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex) {}
//			if(con!=null) try {con.close();}catch(SQLException ex) {}
//		}
//		return listCount;		
//		
//	} // end selectListCount(String search)
	public ArrayList<NoticeBean> selectArticleList(int page, int limit, String search) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeBean> articleList = new ArrayList<NoticeBean>();
		
		
		int startRow = (page - 1) * 10;	//시작 게시물 번호 계산
		
		try {
			
			String sql = "select * from notice order by notice_num desc LIMIT ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			
		while(rs.next()) {
			NoticeBean nb = new NoticeBean();
			nb.setNotice_num(rs.getInt("notice_num"));
			nb.setNotice_member_id(rs.getString("notice_member_id"));
			nb.setNotice_subject(rs.getString("notice_subject"));
			nb.setNotice_content(rs.getString("notice_content"));
			nb.setNotice_image(rs.getString("notice_image"));
			nb.setNotice_readcount(rs.getInt("notice_readcount"));
			nb.setNotice_date(rs.getTimestamp("notice_date"));
			articleList.add(nb);
			}
		} catch (SQLException e) {
			System.out.println("selectArticleList() 오류! - " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	
	
	
	
	
	
	
	
	
}
