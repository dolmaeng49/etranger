package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manager.vo.CategoryBean;
import manager.vo.DatachartBean;
import manager.vo.ProductBean;
import reservation.vo.ReservationBean;
import review.vo.ReviewBean;

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

	// =============================== 카테고리 추가
	// ========================================
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

	// =============================== 카테고리 출력
	// ========================================
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

			while (rs.next()) {
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

	// =============================== 카테고리 끝
	// ========================================

	// --- insertCategory
	public int CategoryInsert(CategoryBean cb, String theme) {
		int insertCount = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO package_category VALUES (?,?,?,?,?,?,?,0,0)";

		try {
			pstmt = con.prepareStatement(sql);

			/*
			 * 11/15 java시간에 배운걸로(컬럼타입네임) 수정 예정 :-) table : package_category
			 * package_category_code varchar(100) package_category_name varchar(50)
			 * package_category_region int(11) package_category_city int(11)
			 * package_category_theme varchar(100) package_category_image varchar(100)
			 * package_category_image varchar(100)
			 * 
			 */

			pstmt.setString(1, cb.getPackage_category_region() + "-" + cb.getPackage_category_city() + "-" + theme);
			pstmt.setString(2, cb.getPackage_category_name());
			pstmt.setInt(3, cb.getPackage_category_region());
			pstmt.setInt(4, cb.getPackage_category_city());
			pstmt.setString(5, theme);
			pstmt.setString(6, cb.getPackage_category_image());
			pstmt.setString(7, cb.getPackage_category_content());

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CategoryInsert() 오류! - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	// insertCategory ---

	// updateCategory ---
	public int CategoryUpdate(CategoryBean cb) {
//		System.out.println("카테고리업뎃");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE package_category SET package_category_name=?, package_category_content=?, package_category_image=? WHERE package_category_code=?;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getPackage_category_name());
			pstmt.setString(2, cb.getPackage_category_content());
			pstmt.setString(3, cb.getPackage_category_image());
			pstmt.setString(4, cb.getPackage_category_code());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}

	public int deleteCategory(String code) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;

		String sql = "delete from package_category where package_category_code=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return deleteCount;
	}

	// --- selectListCount
	public int selectListCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;

		try {
			String sql = "select count(*) from package_category";
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

	// --- selectProductList
	public ArrayList<CategoryBean> selectCategoryList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();

		int startRow = (page - 1) * limit;

		try {

			String sql = "select * from package_category ORDER BY package_category_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
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
				cb.setPackage_category_wish_count(rs.getInt("package_category_wish_count"));
				productList.add(cb);
			}
		} catch (SQLException e) {
			System.out.println("selectCategoryList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}

	// --- selectNewList --최신순 & 최저가격
	public ArrayList<CategoryBean> selectNewList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();

		int startRow = (page - 1) * limit;

		try {

			String sql = "select c.package_category_code, c.package_category_name, c.package_category_image, MIN(p.package_product_price) AS min_price from package_category c join package_product p on p.package_category_code = c.package_category_code group by c.package_category_name ORDER BY package_category_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();

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

	// --- selectPopularList --- 인기순
	public ArrayList<CategoryBean> selectPopularList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryBean> popularList = new ArrayList<CategoryBean>();

		int startRow = (page - 1) * limit;
		try {

			String sql = "select c.package_category_code, c.package_category_name,c.package_category_image, count(*),"
					+ "sum(r.reservation_headcount) AS total_headcount, MIN(p.package_product_price) AS min_price,"
					+ "count(DISTINCT rv.review_num) AS review_count, avg(rv.review_star) AS review_star_avg from reservation r "
					+ "join package_category c on r.reservation_category_code = c.package_category_code "
					+ "join package_product p on p.package_category_code = c.package_category_code "
					+ "LEFT JOIN review rv ON rv.review_package_category_code = p.package_category_code "
					+ "group by c.package_category_name order by 5 DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setPackage_category_code(rs.getString("package_category_code"));
				cb.setPackage_category_name(rs.getString("package_category_name"));
				cb.setPackage_category_image(rs.getString("package_category_image"));
				cb.setTotal_headcount(rs.getInt("total_headcount"));
				cb.setMin_price(rs.getInt("min_price"));
				cb.setReview_count(rs.getInt("review_count"));
				cb.setReview_star_avg(rs.getDouble("review_star_avg"));
				popularList.add(cb);
			}
		} catch (SQLException e) {
			System.out.println("selectPopularList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return popularList;
	}

	// --- selectRecommendedList 추천순
	public ArrayList<CategoryBean> selectRecommendedList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();

		int startRow = (page - 1) * limit;

		try {

//			select c.package_category_code, c.package_category_name, c.package_category_image,
//			MIN(p.package_product_price) AS min_price, MIN(p.package_product_depart_date) AS min_date,
//			count(rv.review_num) AS review_count, avg(rv.review_star) AS review_star_avg from package_category c 
//			join package_product p on p.package_category_code = c.package_category_code 
//			LEFT JOIN review rv ON rv.review_package_category_code = p.package_category_code 
//			group by c.package_category_name order by 5 ASC LIMIT ?,?

//			String sql = "select c.package_category_code, c.package_category_name, c.package_category_image, MIN(p.package_product_price) AS min_price, MIN(p.package_product_depart_date) AS min_date from package_category c join package_product p on p.package_category_code = c.package_category_code group by c.package_category_name order by 5 ASC LIMIT ?,?";
			String sql = "select c.package_category_code, c.package_category_name, c.package_category_image,"
					+ "MIN(p.package_product_price) AS min_price, MIN(p.package_product_depart_date) AS min_date,"
					+ "count(DISTINCT rv.review_num) AS review_count, avg(rv.review_star) AS review_star_avg from package_category c "
					+ "join package_product p on p.package_category_code = c.package_category_code "
					+ "LEFT JOIN review rv ON rv.review_package_category_code = p.package_category_code "
					+ "group by c.package_category_name order by 5 ASC LIMIT ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setPackage_category_code(rs.getString("package_category_code"));
				cb.setPackage_category_name(rs.getString("package_category_name"));
				cb.setPackage_category_image(rs.getString("package_category_image"));
				cb.setMin_price(rs.getInt("min_price"));
				cb.setMin_date(rs.getString("min_date"));
				cb.setReview_count(rs.getInt("review_count"));
				cb.setReview_star_avg(rs.getDouble("review_star_avg"));
				productList.add(cb);
			}
		} catch (SQLException e) {
			System.out.println("selectRecommendedList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}

// --- selectRecommendedList
	public int ProductInsert(ProductBean pb) {
		int insertCount = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO package_product VALUES (?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, pb.getProductNum());
			pstmt.setString(2, pb.getCategoryCode());
			pstmt.setString(3, pb.getProductDepartDate());
			pstmt.setString(4, pb.getProductArrivDate());
			pstmt.setInt(5, pb.getProductPrice());
			pstmt.setInt(6, pb.getProductTotal());
//			pstmt.setInt(2, pb.getProductCurrent());
			pstmt.setInt(7, 0);
//			pstmt.setInt(1, pb.getProductWishCount());

			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public CategoryBean ProductDetailList(String pcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoryBean cb = null;

		try {

			String sql = "select * from package_category where package_category_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cb = new CategoryBean();
				cb.setPackage_category_code(rs.getString("package_category_code"));
				cb.setPackage_category_name(rs.getString("package_category_name"));
				cb.setPackage_category_theme(rs.getString("package_category_theme"));
				cb.setPackage_category_image(rs.getString("package_category_image"));
				cb.setPackage_category_content(rs.getString("package_category_content"));
				cb.setPackage_category_region(rs.getInt("package_category_region"));
				cb.setPackage_category_city(rs.getInt("package_category_city"));
				cb.setPackage_category_wish_count(rs.getInt("package_category_wish_count"));
			}

		} catch (SQLException e) {
			System.out.println("ProductDetailList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return cb;
	}

	public ArrayList<ProductBean> ProductList(String pcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		try {

			String sql = "select * from package_product where package_category_code=? order by package_product_depart_date ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				/*
				 * package_product package_product_num ★ package_category_code
				 * package_product_depart_date ★ package_product_arriv_date ★
				 * package_product_price ★ package_product_total ★ package_product_current
				 * package_product_wish_count
				 */
				ProductBean pb = new ProductBean();
				pb.setProductNum(rs.getString("package_product_num"));
				pb.setCategoryCode(rs.getString("package_category_code"));
				pb.setProductDepartDate(rs.getString("package_product_depart_date"));
				pb.setProductArrivDate(rs.getString("package_product_arriv_date"));
				pb.setProductPrice(rs.getInt("package_product_price"));
				pb.setProductTotal(rs.getInt("package_product_total"));
				pb.setProductCurrent(rs.getInt("package_product_current"));

				productList.add(pb);
			}

		} catch (SQLException e) {
			System.out.println("ProductDetailList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}

	// 상품삭제
	public int DeleteProduct(String deletepcode) {
		int deleteCount = 0;

		PreparedStatement pstmt = null;

		String sql = "delete from package_product where package_product_num=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, deletepcode);

			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public ArrayList<ProductBean> DeleteListDAO(String dpcode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductBean> dplist = new ArrayList<ProductBean>();

		try {

			String sql = "select * from package_product where package_category_code =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dpcode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductBean pb = new ProductBean();
//					System.out.println(rs.getString("package_product_num"));
//					System.out.println(rs.getString("package_product_depart_date"));
//					System.out.println(rs.getString("package_product_arriv_date"));
//					System.out.println(rs.getInt("package_product_price"));
//					System.out.println(rs.getInt("package_product_total"));

				pb.setProductNum(rs.getString("package_product_num"));
				pb.setProductDepartDate(rs.getString("package_product_depart_date"));
				pb.setProductArrivDate(rs.getString("package_product_arriv_date"));
				pb.setProductPrice(rs.getInt("package_product_price"));
				pb.setProductTotal(rs.getInt("package_product_total"));
				pb.setCategoryCode(rs.getString("package_category_code"));

				dplist.add(pb);

			}

		} catch (SQLException e) {
			System.out.println("ProductDeleteList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return dplist;
	}

	// --- selectReservList
	public ArrayList<ReservationBean> selectReservList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationBean> reservList = new ArrayList<ReservationBean>();

		int startRow = (page - 1) * limit;

		try {
			String sql = "SELECT r.reservation_num,r.reservation_category_code,r.reservation_member_id,r.reservation_product_num,r.reservation_date,r.reservation_price,r.reservation_headcount,r.reservation_ispayment,r.reservation_progress,r.reservation_member_id,p.package_product_depart_date,p.package_product_arriv_date,c.package_category_name FROM reservation r JOIN package_product p ON r.reservation_product_num = p.package_product_num JOIN package_category c ON r.reservation_category_code = c.package_category_code"
					+ " limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 고객아이디 예약일(결제시한) 예약번호 예약상품(이름) 예약인원 출발날짜/도착날짜 금액 진행상태
				ReservationBean rb = new ReservationBean();
				rb.setReservation_member_id(rs.getString("reservation_member_id")); // 고객 아이디
				rb.setReservation_date(rs.getString("reservation_date")); // 예약일
				rb.setReservation_num(rs.getInt("reservation_num")); // 예약번호
				rb.setPackage_category_name(rs.getString("package_category_name")); // 예약상품
				rb.setReservation_category_code(rs.getString("reservation_category_code"));
				rb.setReservation_headcount(rs.getInt("reservation_headcount")); // 예약인원
				rb.setPackage_product_depart_date(rs.getString("package_product_depart_date")); // 출발날짜
				rb.setPackage_product_arriv_date(rs.getString("package_product_arriv_date")); // 도착날짜
				rb.setReservation_price(rs.getInt("reservation_price")); // 가격
				rb.setReservation_ispayment(rs.getString("reservation_ispayment"));
				rb.setReservation_progress(rs.getString("reservation_progress"));
				reservList.add(rb);
			}
		} catch (SQLException e) {
			System.out.println("selectArticleList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return reservList;
	}

	public int deleteReserv(int reservNum) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;

		String sql = "delete from reservation where reservation_num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reservNum);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return deleteCount;
	}

	public int updateReserv(String reservation_progress, int reservNum) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reservation set reservation_progress=? where reservation_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reservation_progress);
			pstmt.setInt(2, reservNum);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}

	public int updateIsReserv(String isReserv, int reservNum) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE reservation set reservation_ispayment =? where reservation_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isReserv);
			pstmt.setInt(2, reservNum);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}

	// --- selectListCount
	public int reservListCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;

		try {
			String sql = "select count(*) from reservation";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("reservListCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<CategoryBean> selectThemeListLimit() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryBean> themeList = new ArrayList<CategoryBean>();

		try {

			String sql = "SELECT category_theme_code, category_theme_name FROM category_theme ORDER BY category_theme_code DESC LIMIT 10";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setThemeCode(rs.getInt("category_theme_code"));
				cb.setThemeName(rs.getString("category_theme_name"));

				themeList.add(cb);
			}
		} catch (SQLException e) {

			System.out.println("selectThemeListLimit() 오류 - " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		return themeList;
	}

	// =========================== 시각화 =================================

	// 전체 판매량, 매출액
	public DatachartBean TotalSales() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatachartBean db = null;

		try {
			String sql = "SELECT SUM(reservation_price), COUNT(*) FROM reservation";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				db = new DatachartBean();
				db.setSalesVolume(rs.getInt("SUM(reservation_price)"));
				db.setSales(rs.getInt("COUNT(*)"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return db;
	}

	// 지역별 상품예약 수
	public ArrayList RegionReservationCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList categoryRegionName = new ArrayList(); // 지역이름
		ArrayList packageProductCurrent = new ArrayList(); // 지역별 예약상품 수
		ArrayList regionReservationList = new ArrayList(); // 지역이름, 지역별 예약상품 수 저장

		try {

			String sql = "SELECT cr.category_region_name ,sum(r.reservation_headcount) " + "FROM package_product pp "
					+ "JOIN package_category pc ON pp.package_category_code = pc.package_category_code "
					+ "JOIN category_region cr ON pc.package_category_region = cr.category_region_code "
					+ "JOIN reservation r ON pp.package_category_code = r.reservation_category_code "
					+ "GROUP BY cr.category_region_name";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				categoryRegionName.add("\"" + rs.getString("cr.category_region_name") + "\"");
				packageProductCurrent.add(rs.getInt("sum(r.reservation_headcount)"));

			}
			regionReservationList.add(categoryRegionName);
			regionReservationList.add(packageProductCurrent);

		} catch (SQLException e) {
			System.out.println("RegionReservationCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return regionReservationList;
	}

	// 일별 결제건수, 금액
	public ArrayList TotalPayCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList totalPayCount = new ArrayList();
		ArrayList reservationDate = new ArrayList();
		ArrayList reservationPrice = new ArrayList();
		ArrayList paymentNum = new ArrayList();

		try {
			String sql = "SELECT reservation_date, sum(reservation_price), count(*) FROM reservation GROUP BY reservation_date LIMIT 0,30";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reservationDate.add("\"" + rs.getString("reservation_date") + "\"");
				reservationPrice.add(rs.getInt("sum(reservation_price)"));
				paymentNum.add(rs.getInt("count(*)"));
			}

			totalPayCount.add(reservationDate);
			totalPayCount.add(reservationPrice);
			totalPayCount.add(paymentNum);

		} catch (SQLException e) {
			System.out.println("TotalPayCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return totalPayCount;
	}

	// 성별 총 결제금액
	public ArrayList GenderPayment() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList genderPayment = new ArrayList();
		ArrayList gender = new ArrayList();
		ArrayList payment = new ArrayList();

		try {
			String sql = "SELECT m.member_gender, sum(r.reservation_price) " 
					+ "FROM member m "
					+ "JOIN reservation r ON m.member_id = r.reservation_member_id "
					+ "WHERE m.member_gender = 'm' OR m.member_gender = 'f' " 
					+ "GROUP BY m.member_gender "
					+ "ORDER BY 'm'";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				payment.add(rs.getInt("sum(r.reservation_price)"));
			}

//			genderPayment.add(gender);
			genderPayment.add(payment);

		} catch (SQLException e) {
			System.out.println("RegionReservationCount() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return genderPayment;
	}

	public ArrayList MostProduct() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList mostProduct = new ArrayList();
		
		ArrayList most = new ArrayList();
		ArrayList count = new ArrayList();
		
		try {
			String sql = "SELECT pc.package_category_name, count(*) "
						+ "FROM package_category pc " 
						+ "JOIN reservation r ON pc.package_category_code = r.reservation_category_code "
						+ "GROUP BY pc.package_category_name " 
						+ "ORDER BY count(*) DESC LIMIT 10";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				most.add("\""+rs.getString("pc.package_category_name")+"\"");
				count.add(rs.getInt("count(*)"));
			}
			
			mostProduct.add(most);
			mostProduct.add(count);

		} catch (SQLException e) {
			System.out.println("GenderAgeMost() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return mostProduct;
	}
	
	public ArrayList<DatachartBean> AgeList(int age, String gender) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<DatachartBean> ageList = new ArrayList<DatachartBean>();
		
		try {
			String sql = "SELECT pc.package_category_name, count(*) " 
						+ "FROM package_category pc " 
						+ "JOIN reservation r ON pc.package_category_code = r.reservation_category_code " 
						+ "JOIN member m ON r.reservation_member_id = m.member_id " 
						+ "WHERE  floor((year(now())-year(m.member_birth))/10)*10= ? AND member_gender = ? "
						+ "GROUP BY pc.package_category_name " 
						+ "ORDER BY count(*) DESC LIMIT 5"; 

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, age);
			pstmt.setString(2, gender);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DatachartBean al = new DatachartBean();
				al.setAgeMostPick(rs.getString("pc.package_category_name"));
				al.setAgeMostCount(rs.getInt("count(*)"));
				ageList.add(al);
			}
			

		} catch (SQLException e) {
			System.out.println("AgeList() 오류! - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return ageList;
	}
	
	
// ================================ 시각화 끝 ============================================

}
