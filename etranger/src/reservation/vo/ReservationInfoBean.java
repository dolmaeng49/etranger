package reservation.vo;

public class ReservationInfoBean {
//	reservation table
//	+-------------------------+--------------+------+-----+-------------------+-----------------------------+
//	| Field                   | Type         | Null | Key | Default           | Extra                       |
//	+-------------------------+--------------+------+-----+-------------------+-----------------------------+
//	| reservation_num         | int(11)      | NO   | PRI | NULL              | auto_increment              |
//	| reservation_member_id   | varchar(12)  | NO   | MUL | NULL              |                             |
//	| reservation_product_num | varchar(100) | NO   | MUL | NULL              |                             |
//	| reservation_time        | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
//	| reservation_price       | int(11)      | NO   |     | NULL              |                             |
//	| reservation_headcount   | int(11)      | NO   |     | NULL              |                             |
//	| reservation_pay_way     | varchar(45)  | NO   |     | NULL              |                             |
//	| reservation_ispayment   | varchar(45)  | NO   |     | NULL              |                             |
//	+-------------------------+--------------+------+-----+-------------------+-----------------------------+
	private int reservation_product_num;
	private String reservation_member_id;
	private int reservation_price;
	private int reservation_headcount;
	private String reservation_category_code;
	private String package_category_name;
	private String package_category_image;
	private String package_product_arriv_date;
	private String package_product_depart_date;
	
	
	
	
	public int getReservation_product_num() {
		return reservation_product_num;
	}
	public void setReservation_product_num(int reservation_product_num) {
		this.reservation_product_num = reservation_product_num;
	}
	public String getReservation_member_id() {
		return reservation_member_id;
	}
	public void setReservation_member_id(String reservation_member_id) {
		this.reservation_member_id = reservation_member_id;
	}
	public int getReservation_price() {
		return reservation_price;
	}
	public void setReservation_price(int reservation_price) {
		this.reservation_price = reservation_price;
	}
	public int getReservation_headcount() {
		return reservation_headcount;
	}
	public void setReservation_headcount(int reservation_headcount) {
		this.reservation_headcount = reservation_headcount;
	}
	public String getReservation_category_code() {
		return reservation_category_code;
	}
	public void setReservation_category_code(String reservation_category_code) {
		this.reservation_category_code = reservation_category_code;
	}
	public String getPackage_category_name() {
		return package_category_name;
	}
	public void setPackage_category_name(String package_category_name) {
		this.package_category_name = package_category_name;
	}
	public String getPackage_category_image() {
		return package_category_image;
	}
	public void setPackage_category_image(String package_category_image) {
		this.package_category_image = package_category_image;
	}
	public String getPackage_product_arriv_date() {
		return package_product_arriv_date;
	}
	public void setPackage_product_arriv_date(String package_product_arriv_date) {
		this.package_product_arriv_date = package_product_arriv_date;
	}
	public String getPackage_product_depart_date() {
		return package_product_depart_date;
	}
	public void setPackage_product_depart_date(String package_product_depart_date) {
		this.package_product_depart_date = package_product_depart_date;
	}
	
	
	
	
	
	
	

	
	
}
