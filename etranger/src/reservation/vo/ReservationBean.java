package reservation.vo;

public class ReservationBean {
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
	private int reservation_num;
	private String reservation_member_id;
	private String reservation_product_num;
	private String reservation_time;
	private int reservation_price;
	private int reservation_headcount;
	private String reservation_pay_way;
	private String reservation_ispayment;

	public int getReservation_num() {
		return reservation_num;
	}
	public void setReservation_num(int reservation_num) {
		this.reservation_num = reservation_num;
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
	public String getReservation_time() {
		return reservation_time;
	}
	public void setReservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
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
	public String getReservation_ispayment() {
		return reservation_ispayment;
	}
	public void setReservation_ispayment(String reservation_ispayment) {
		this.reservation_ispayment = reservation_ispayment;
	}
	
	
}
