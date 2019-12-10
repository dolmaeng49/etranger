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

	public ArrayList<ReservationBean> ReservationInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ReservationBean rb = new ReservationBean();
		System.out.println(id);
		ArrayList<ReservationBean> daorb = new ArrayList<ReservationBean>();
		try {

			String sql = "SELECT rs.reservation_member_id,pc.package_category_name,pc.package_category_image,pd.package_product_arriv_date, " + 
					"    pd.package_product_depart_date,rs.reservation_headcount,rs.reservation_price,reservation_category_code,rs.reservation_num,"
					+ "  rs.reservation_pay_way,rs.reservation_progress,rs.reservation_date " + 
					"    FROM reservation rs JOIN package_category pc " + 
					"    ON rs.reservation_category_code = pc.package_category_code JOIN package_product pd " + 
					"    ON rs.reservation_product_num = pd.package_product_num where rs.reservation_member_id=?;";
				System.out.println("DB");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("DB2");
			while (rs.next()) {

				ReservationBean rb = new ReservationBean();
				rb.setReservation_member_id(rs.getString("reservation_member_id"));
				rb.setReservation_num(rs.getInt("reservation_num"));
				rb.setReservation_pay_way(rs.getString("reservation_pay_way"));
				rb.setReservation_date(rs.getString("reservation_date"));
				rb.setReservation_progress(rs.getString("reservation_progress"));
				rb.setReservation_headcount(rs.getInt("reservation_headcount"));
				rb.setReservation_price(rs.getInt("reservation_price"));
				rb.setReservation_category_code(rs.getString("reservation_category_code"));
				rb.setPackage_category_image(rs.getString("package_category_image"));
				rb.setPackage_category_name(rs.getString("package_category_name"));
				rb.setPackage_product_arriv_date(rs.getString("package_product_arriv_date"));
				rb.setPackage_product_depart_date(rs.getString("package_product_depart_date"));
				daorb.add(rb);
			}
			
			for(int i=0;i<daorb.size();i++) {
				
				System.out.println(daorb.get(i));
				
				
				
				
			}
			

		} catch (SQLException e) {

			System.out.println("selectArticleList() 오류 - " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		return daorb;
	}

	public int insertReservation(ReservationBean rb) {
		System.out.println("insertReservation");
		PreparedStatement pstmt = null;

		int insertCount = 0;

		try {

//			String sql = "INSERT INTO reservation(reservation_member_id,reservation_product_num,reservation_time,reservation_price,"
//					+ "reservation_headcount,reservation_pay_way,reservation_ispayment,reservation_category_code)"
//					+ "VALUES (?,?,CURRENT_TIMESTAMP,?,?,?,?,?)";
			String sql = "INSERT INTO reservation VALUES(null,?,?,?,CURRENT_TIMESTAMP,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, rb.getReservation_category_code());
			pstmt.setString(2, rb.getReservation_member_id());
			pstmt.setString(3, rb.getReservation_product_num());
			pstmt.setInt(4, rb.getReservation_price());
			pstmt.setInt(5, rb.getReservation_headcount());
			pstmt.setString(6, rb.getReservation_pay_way());
			pstmt.setString(7, rb.getReservation_progress());

			insertCount = pstmt.executeUpdate();

			sql = "UPDATE package_product SET package_product_total = package_product_total - ?, package_product_current = package_product_current + ? WHERE package_product_num = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, rb.getReservation_headcount());
			pstmt.setInt(2, rb.getReservation_headcount());
			pstmt.setString(3, rb.getReservation_product_num());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insertArticle() 오류 -" + e.getMessage());

		} finally {
			close(pstmt);
		}

		return insertCount;
	}

}
