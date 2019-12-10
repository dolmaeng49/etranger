package reservation.vo;

public class ReservationBean {
//	reservation table
//	+---------------------------+--------------+------+-----+---------+----------------+
//	| Field                     | Type         | Null | Key | Default | Extra          |
//	+---------------------------+--------------+------+-----+---------+----------------+
//	| reservation_num           | int(11)      | NO   | PRI | NULL    | auto_increment |
//	| reservation_category_code | varchar(100) | YES  | MUL | NULL    |                |
//	| reservation_member_id     | varchar(12)  | NO   | MUL | NULL    |                |
//	| reservation_product_num   | varchar(100) | NO   | MUL | NULL    |                |
//	| reservation_date          | date         | NO   |     | NULL    |                |
//	| reservation_price         | int(11)      | NO   |     | NULL    |                |
//	| reservation_headcount     | int(11)      | NO   |     | NULL    |                |
//	| reservation_pay_way       | varchar(45)  | NO   |     | NULL    |                |
//	| reservation_progress      | varchar(10)  | YES  |     | NULL    |                |
//	+---------------------------+--------------+------+-----+---------+----------------+
	private int reservation_num;
	private String reservation_category_code;
	private String reservation_member_id;
	private String reservation_product_num;
	private String reservation_date;
	private int reservation_price;
	private int reservation_headcount;
	private String reservation_pay_way;
	private String reservation_progress;
	private String package_category_name;
	private String package_category_image;
	private String package_product_arriv_date;
	private String package_product_depart_date;
	
	public int getReservation_num() {
		return reservation_num;
	}
	public void setReservation_num(int reservation_num) {
		this.reservation_num = reservation_num;
	}
	public String getReservation_category_code() {
		return reservation_category_code;
	}
	public void setReservation_category_code(String reservation_category_code) {
		this.reservation_category_code = reservation_category_code;
	}
	public String getReservation_member_id() {
		return reservation_member_id;
	}
	public void setReservation_member_id(String reservation_member_id) {
		this.reservation_member_id = reservation_member_id;
	}
	public String getReservation_product_num() {
		return reservation_product_num;
	}
	public void setReservation_product_num(String reservation_product_num) {
		this.reservation_product_num = reservation_product_num;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
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
	public String getReservation_pay_way() {
		return reservation_pay_way;
	}
	public void setReservation_pay_way(String reservation_pay_way) {
		this.reservation_pay_way = reservation_pay_way;
	}
	public String getReservation_progress() {
		return reservation_progress;
	}
	public void setReservation_progress(String reservation_progress) {
		this.reservation_progress = reservation_progress;
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
