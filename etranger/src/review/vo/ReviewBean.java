package review.vo;

import java.sql.Timestamp;



public class ReviewBean {
	
	private int review_num;
	private String review_member_id;
	private String review_subject;
	private String review_image;
	private String review_content;
	private Timestamp review_date;
	private int review_readcount;
	private String review_package_catagory_code;
	private String review_member_name;
	private int review_star;
	private int review_comment_count;
	
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getReview_member_id() {
		return review_member_id;
	}
	public void setReview_member_id(String review_member_id) {
		this.review_member_id = review_member_id;
	}
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public int getReview_readcount() {
		return review_readcount;
	}
	public void setReview_readcount(int review_readcount) {
		this.review_readcount = review_readcount;
	}
	public String getReview_package_catagory_code() {
		return review_package_catagory_code;
	}
	public void setReview_package_catagory_code(String review_package_catagory_code) {
		this.review_package_catagory_code = review_package_catagory_code;
	}
	public String getReview_member_name() {
		return review_member_name;
	}
	public void setReview_member_name(String review_member_name) {
		this.review_member_name = review_member_name;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}
	public int getReview_comment_count() {
		return review_comment_count;
	}
	public void setReview_comment_count(int review_comment_count) {
		this.review_comment_count = review_comment_count;
	}
	
	
	
	
	
	

	
}
