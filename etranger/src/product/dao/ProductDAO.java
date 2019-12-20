package product.dao;

import static common.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import manager.vo.CategoryBean;
import product.vo.SearchBean;

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

	public int insertWish(String member_id, String category_code) {
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
		String sql = "SELECT wish_category_code FROM wish WHERE wish_member_id=?";
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
		String sql = "UPDATE package_category SET package_category_wish_count=package_category_wish_count+?"
				+ " WHERE package_category_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wishCount);
			pstmt.setString(2, category_code);
			System.out.println(pstmt);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateCategoryWishCount 실패 : " + e.getMessage());
		}
		return updateCount;
	}

	// 아이디와 카테고리 코드를 전달받아 해당 정보와 일치하는 행 삭제
	public int deleteWish(String member_id, String category_code) {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM wish WHERE wish_member_id=? AND wish_category_code=?";
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, category_code);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteWish 실패 : " + e.getMessage());
		}
		
		return deleteCount;
	}

	
	//------------------ManagerDAO의 selectListCount, selectCategoryList 불러옴.
	
	// --- selectListCount
		public int selectListCount(SearchBean searchBean) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int listCount = 0;
			
			String keyword = searchBean.getKeyword();
			String depart_date = searchBean.getDepart_date();
			String arriv_date = searchBean.getArriv_date();
			String region = searchBean.getRegion();
			String city = searchBean.getCity();
			
			keyword = keyword == null ? "" : keyword;
			arriv_date = arriv_date == null ? "" : arriv_date;
			region = region == null ? "" : region;
			city = city == null ? "" : city; 
			
			// 검색 조건 입력 여부 배열에 저장
			boolean[] isNulls = {keyword.length()==0,arriv_date.length()==0,region.length()==0,city.length()==0};
			
			// SQL 구문의 ? 인덱스를 저장하는 변수
			int index = 1;

			try {
				
				String sql = "SELECT COUNT(DISTINCT c.package_category_code) FROM package_product p"
						+ " JOIN package_category c"
						+ " ON p.package_category_code = c.package_category_code"
						+ " WHERE p.package_product_depart_date >= ?";
				if(!isNulls[0]) {
					// 키워드
					sql += " AND (c.package_category_name like ?"
						 + " OR c.package_category_theme like ?"
						 + " OR c.package_category_content like ?)";
				} if(!isNulls[1]) {
					// 도착날짜
					sql += " AND p.package_product_arriv_date < ?";
				} if(!isNulls[2]) {
					// 지역
					sql += " AND c.package_category_region=?";
				} if(!isNulls[3]) {
					// 도시
					sql += " AND c.package_category_city=?";
				}

				pstmt = con.prepareStatement(sql);
				pstmt.setString(index++, depart_date);
				if(!isNulls[0]) {
					// 키워드
					pstmt.setString(index++, "%"+keyword+"%");
					pstmt.setString(index++, "%"+keyword+"%");
					pstmt.setString(index++, "%"+keyword+"%");
				}
				if(!isNulls[1]) {
					// 도착날짜
					pstmt.setString(index++, arriv_date);
				} if(!isNulls[2]) {
					// 지역
					pstmt.setInt(index++, Integer.parseInt(region));
				} if(!isNulls[3]) {
					// 도시
					pstmt.setInt(index++, Integer.parseInt(city));
				}
				rs = pstmt.executeQuery();
				// 
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
		public ArrayList<CategoryBean> selectCategoryList(int page, int limit, SearchBean searchBean) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();
			
			String keyword = searchBean.getKeyword();
			String depart_date = searchBean.getDepart_date();
			String arriv_date = searchBean.getArriv_date();
			String region = searchBean.getRegion();
			String city = searchBean.getCity();
			
			
			keyword = keyword == null ? "" : keyword;
			arriv_date = arriv_date == null ? "" : arriv_date;
			region = region == null ? "" : region;
			city = city == null ? "" : city; 
			
//			System.out.println("keyword : "+keyword);
//			System.out.println("depart_date : "+depart_date);
//			System.out.println("arriv_date : "+arriv_date);
//			System.out.println("region : "+region);
//			System.out.println("dao city : "+city);
			
			// 검색 조건 입력 여부 배열에 저장
			boolean[] isNulls = {keyword.length()==0,arriv_date.length()==0,region.length()==0,city.length()==0};
			
			// SQL 구문의 ? 인덱스를 저장하는 변수
			int index = 1;
			
			// 결과를 조회할 첫번째 row 인덱스
			int startRow = (page - 1) * 8;
			

			try {
				// 입력 받은 검색어에 따른 카테고리 리스트 조회
				String sql = "SELECT c.package_category_code, c.package_category_name, c.package_category_theme, c.package_category_image, c.package_category_content,"
						+ "c.package_category_region, c.package_category_city, c.package_category_wish_count, MIN(p.package_product_price) AS min_price"
						+ ", count(r.review_num) AS review_count, avg(r.review_star) AS review_star_avg"
						+ " FROM package_product p"
						// 조인
						+ " JOIN package_category c"
						+ " ON p.package_category_code = c.package_category_code"
						// 리뷰 LEFT 조인 : 뷰페이지에서 리뷰 없을 경우(null) 제어 필요
						+ " LEFT JOIN review r ON r.review_package_category_code = p.package_category_code"
						+ " WHERE p.package_product_depart_date > ?";
				if(!isNulls[0]) {
					// 키워드
					sql += " AND (c.package_category_name like ?"
						 + " OR c.package_category_theme like ?"
						 + " OR c.package_category_content like ?)";
				}
				if(!isNulls[1]) {
					// 도착날짜
					sql += " AND p.package_product_arriv_date < ?";
				} if(!isNulls[2]) {
					// 지역
					sql += " AND c.package_category_region=?";
				} if(!isNulls[3]) {
					// 도시
					sql += " AND c.package_category_city=?";
				}
				// 마지막
				sql += " GROUP BY c.package_category_code ORDER BY p.package_product_depart_date LIMIT ?,?";
				

				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(index++, depart_date);
				if(!isNulls[0]) {
					// 키워드
					pstmt.setString(index++, "%"+keyword+"%");
					pstmt.setString(index++, "%"+keyword+"%");
					pstmt.setString(index++, "%"+keyword+"%");
				}
				 if(!isNulls[1]) {
					// 도착날짜
					pstmt.setString(index++, arriv_date);
				} if(!isNulls[2]) {
					// 지역
					pstmt.setInt(index++, Integer.parseInt(region));
				} if(!isNulls[3]) {
					// 도시
					pstmt.setInt(index++, Integer.parseInt(city));
				}
				// 마지막 공통 작업
				pstmt.setInt(index++, startRow);
				pstmt.setInt(index++, limit);
//				System.out.println("startRow from dao : " + startRow + "depart_date : " + depart_date + "limit : " + limit);
//				System.out.println(pstmt);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					CategoryBean cb = new CategoryBean();
					cb.setPackage_category_code(rs.getString("c.package_category_code"));
					cb.setPackage_category_name(rs.getString("c.package_category_name"));
					cb.setPackage_category_theme(rs.getString("c.package_category_theme"));
					cb.setPackage_category_image(rs.getString("c.package_category_image"));
					cb.setPackage_category_content(rs.getString("c.package_category_content"));
					cb.setPackage_category_region(rs.getInt("c.package_category_region"));
					cb.setPackage_category_city(rs.getInt("c.package_category_city"));
					cb.setPackage_category_wish_count(rs.getInt("c.package_category_wish_count"));
					cb.setMin_price(rs.getInt("min_price"));
					cb.setReview_count(rs.getInt("review_count"));
					cb.setReview_star_avg(rs.getDouble("review_star_avg"));
					productList.add(cb);
				}
				
				// 카테고리의 리뷰 조회하기
//				sql = "SELECT count(reaview_num) AS review_count, avg(review_star) AS review_star_avg"
//						+ " FROM review WHERE review_package_category_code=?";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, x);
				// category_code 로 review 테이블 조회하는 구문 하나 더 만들어
				// rs.next 가 있다면 리뷰 개수와 평균 리뷰점수 빈에 담기
				
			} catch (SQLException e) {
				System.out.println("selectCategoryList(search) 오류! - " + e.getMessage());
			} finally {
				close(pstmt);
				close(rs);
			}
			return productList;
		}
		// selectCategoryList ---

		// 메인페이지 로그인한 회원의 정보로 상품을 조회하는 메서드
		public ArrayList<CategoryBean> selectPersonalizedList(int page, int limit, String gender, String search_birth_start,
				String search_birth_end) {
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();

			int startRow = (page - 1) * limit;

			try {

				String sql = "SELECT c.package_category_code, c.package_category_name, c.package_category_image, c.package_category_content,"
						+ " MIN(p.package_product_price) AS min_price, COUNT(r.review_num) AS review_count, COUNT(rs.reservation_num) AS rsv_count,"
						+ " AVG(r.review_star) AS review_star_avg"
						+ " FROM package_product p"
						// 조인
						+ " JOIN package_category c"
						+ " ON p.package_category_code = c.package_category_code"
						// 예약 테이블 조인
						+ " LEFT JOIN reservation rs ON rs.reservation_category_code = c.package_category_code"
						// 멤버테이블 x 예약 테이블 조인
						+ " LEFT JOIN member m ON rs.reservation_member_id = m.member_id"
						// 리뷰 LEFT 조인 : 뷰페이지에서 리뷰 없을 경우(null) 제어 필요
						+ " LEFT JOIN review r ON r.review_package_category_code = p.package_category_code"
						// 조회 조건
						+ " WHERE p.package_product_depart_date > now()"
						+ " AND m.member_birth>? AND m.member_birth<?"
						+ " AND m.member_gender=?"
						+ " GROUP BY c.package_category_code ORDER BY rsv_count DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search_birth_start);
				pstmt.setString(2, search_birth_end);
				pstmt.setString(3, gender);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, limit);
				rs = pstmt.executeQuery();
//				System.out.println(pstmt);
				while (rs.next()) {
					CategoryBean cb = new CategoryBean();
					cb.setPackage_category_code(rs.getString("package_category_code"));
					cb.setPackage_category_name(rs.getString("package_category_name"));
					cb.setPackage_category_image(rs.getString("package_category_image"));
					cb.setMin_price(rs.getInt("min_price"));
					productList.add(cb);
				}
			} catch (SQLException e) {
				System.out.println("selectNewList() 오류! - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return productList;
		}

		public int selectWishListCount(String member_id) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int listCount = 0;
			
			// SQL 구문의 ? 인덱스를 저장하는 변수
			int index = 1;

			try {
				
				String sql = "SELECT COUNT(DISTINCT c.package_category_code) FROM package_product p"
						+ " JOIN package_category c"
						+ " ON p.package_category_code = c.package_category_code"
						+ " JOIN wish w"
						+ " ON w.wish_category_code = c.package_category_code"
						+ " WHERE p.package_product_depart_date >= now()"
						+ " AND w.wish_member_id=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
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

		public CategoryBean[] selectWishCategoryList(int page, int limit, String member_id) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int listCount = 0;
			
			CategoryBean[] cbArr = new CategoryBean[limit];

			try {
				
				String sql = "SELECT c.package_category_code, c.package_category_name, c.package_category_theme, c.package_category_image, c.package_category_content," 
						+ " c.package_category_region, c.package_category_city, c.package_category_wish_count, MIN(p.package_product_price) AS min_price," 
						+ " COUNT(DISTINCT r.review_num) AS review_count, avg(r.review_star) AS review_star_avg"
						+ " FROM package_category c"
						+ " JOIN package_product p ON p.package_category_code = c.package_category_code"
						+ " LEFT JOIN review r ON r.review_package_category_code = c.package_category_code"
						+ " JOIN wish w ON w.wish_category_code = c.package_category_code"
						+ " WHERE w.wish_member_id=?"
						+ " GROUP BY c.package_category_code";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
				rs = pstmt.executeQuery();
				int index = 0;
				while (rs.next()) {
					CategoryBean cb = new CategoryBean();
					cb.setPackage_category_code(rs.getString("c.package_category_code"));
					cb.setPackage_category_name(rs.getString("c.package_category_name"));
					cb.setPackage_category_theme(rs.getString("c.package_category_theme"));
					cb.setPackage_category_image(rs.getString("c.package_category_image"));
					cb.setPackage_category_content(rs.getString("c.package_category_content"));
					cb.setPackage_category_region(rs.getInt("c.package_category_region"));
					cb.setPackage_category_city(rs.getInt("c.package_category_city"));
					cb.setPackage_category_wish_count(rs.getInt("c.package_category_wish_count"));
					cb.setMin_price(rs.getInt("min_price"));
					cb.setReview_count(rs.getInt("review_count"));
					cb.setReview_star_avg(rs.getDouble("review_star_avg"));
					cbArr[index++] = cb;
				}
			} catch (SQLException e) {
				System.out.println("selectListCount(String keyword) 오류! - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return cbArr;
		}

}