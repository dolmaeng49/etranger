package reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import reservation.vo.*;

import static common.db.JdbcUtil.*;

public class ReservationDAO {
	private ReservationDAO() {
	}

	private static ReservationDAO instance;

	public static ReservationDAO getInstance() {
		if (instance == null) {
			instance = new ReservationDAO();
		}

		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ReservationBean ReservationInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationBean rb = new ReservationBean();
		System.out.println(id);

		try {

			String sql = "SELECT rs.reservation_member_id,pc.package_category_name,pc.package_category_image,pd.package_product_arriv_date,"
					+ "pd.package_product_depart_date,rs.reservation_headcount,rs.reservation_price,reservation_category_code "
					+ "FROM reservation rs JOIN " + "package_category pc "
					+ "ON rs.reservation_category_code = pc.package_category_code " + "JOIN package_product pd "
					+ "ON rs.reservation_product_num = pd.package_product_num " + "AND rs.reservation_member_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rb.setReservation_member_id(rs.getString("reservation_member_id"));
				// rib.setReservation_product_num(rs.getInt("reservation_product_num"));
				rb.setReservation_headcount(rs.getInt("reservation_headcount"));
				rb.setReservation_price(rs.getInt("reservation_price"));
				rb.setReservation_category_code(rs.getString("reservation_category_code"));
				rb.setPackage_category_image(rs.getString("package_category_image"));
				rb.setPackage_category_name(rs.getString("package_category_name"));
				rb.setPackage_product_arriv_date(rs.getString("package_product_arriv_date"));
				rb.setPackage_product_depart_date(rs.getString("package_product_depart_date"));
			}

		} catch (SQLException e) {

			System.out.println("selectArticleList() 오류 - " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		return rb;
	}

	public int insertReservation(ReservationBean rb) {
		System.out.println("insertReservation");
		PreparedStatement pstmt = null;

		int insertCount = 0;

		try {
			
//			+-----------------------------+--------------+------+-----+---------+----------------+
//			| Field                       | Type         | Null | Key | Default | Extra          |
//			+-----------------------------+--------------+------+-----+---------+----------------+
//			| reservation_num             | int(11)      | NO   | PRI | NULL    | auto_increment |
//			| reservation_category_code   | varchar(100) | YES  | MUL | NULL    |                |
//			| reservation_member_id       | varchar(12)  | NO   | MUL | NULL    |                |
//			| reservation_product_num     | varchar(100) | NO   | MUL | NULL    |                |
//			| reservation_date            | date         | NO   |     | NULL    |                |
//			| reservation_price           | int(11)      | NO   |     | NULL    |                |
//			| reservation_headcount       | int(11)      | NO   |     | NULL    |                |
//			| reservation_pay_way         | varchar(45)  | NO   |     | NULL    |                |
//			| reservation_progress        | varchar(10)  | YES  |     | NULL    |                |
//			| reservation_product_current | int(11)      | YES  |     | NULL    |                |
//			+-----------------------------+--------------+------+-----+---------+----------------+
			String sql = "INSERT INTO reservation VALUES(null,?,?,?,CURRENT_TIMESTAMP,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt");
			
			pstmt.setString(1, rb.getReservation_category_code());
			pstmt.setString(2, rb.getReservation_member_id());
			pstmt.setString(3, rb.getReservation_product_num());
			pstmt.setInt(4, rb.getReservation_price());
			pstmt.setInt(5, rb.getReservation_headcount());
			pstmt.setString(6, rb.getReservation_pay_way());
			pstmt.setString(7, rb.getReservation_progress());
			
			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insertArticle() 오류 -" + e.getMessage());

		} finally {
			close(pstmt);
		}

		return insertCount;
	}

}

