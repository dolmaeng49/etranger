package comment.vo;

import java.sql.Timestamp;

public class CommentBean {

	private int review_comment_num;
	private String review_comment_member_id;
	private String review_comment_member_name;
	private int review_comment_review_num; //원글번호를 가져오는 ref
	private int review_comment_ref;	//대댓글이 참조할 ref
	private int review_comment_lev;
	private int review_comment_seq;
	private Timestamp review_comment_date;
	private String review_comment_content;
	
	
	public int getReview_comment_num() {
		return review_comment_num;
	}
	public void setReview_comment_num(int review_comment_num) {
		this.review_comment_num = review_comment_num;
	}
	public String getReview_comment_member_id() {
		return review_comment_member_id;
	}
	public void setReview_comment_member_id(String review_comment_member_id) {
		this.review_comment_member_id = review_comment_member_id;
	}
	public String getReview_comment_member_name() {
		return review_comment_member_name;
	}
	public void setReview_comment_member_name(String review_comment_member_name) {
		this.review_comment_member_name = review_comment_member_name;
	}
	public int getReview_comment_review_num() {
		return review_comment_review_num;
	}
	public void setReview_comment_review_num(int review_comment_review_num) {
		this.review_comment_review_num = review_comment_review_num;
	}
	public int getReview_comment_ref() {
		return review_comment_ref;
	}
	public void setReview_comment_ref(int review_comment_ref) {
		this.review_comment_ref = review_comment_ref;
	}
	public int getReview_comment_lev() {
		return review_comment_lev;
	}
	public void setReview_comment_lev(int review_comment_lev) {
		this.review_comment_lev = review_comment_lev;
	}
	public int getReview_comment_seq() {
		return review_comment_seq;
	}
	public void setReview_comment_seq(int review_comment_seq) {
		this.review_comment_seq = review_comment_seq;
	}
	public Timestamp getReview_comment_date() {
		return review_comment_date;
	}
	public void setReview_comment_date(Timestamp review_comment_date) {
		this.review_comment_date = review_comment_date;
	}
	public String getReview_comment_content() {
		return review_comment_content;
	}
	public void setReview_comment_content(String review_comment_content) {
		this.review_comment_content = review_comment_content;
	}
	
	
}
