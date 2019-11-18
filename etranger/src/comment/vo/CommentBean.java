package comment.vo;

import java.sql.Timestamp;

public class CommentBean {

	private int review_reply_num;
	private String review_reply_member_id;
	private int review_reply_review_num; //원글번호를 가져오는 ref
	private int review_reply_ref;	//대댓글이 참조할 ref
	private int review_reply_lev;
	private int review_reply_seq;
	private Timestamp review_reply_date;
	private String review_reply_content;
	
	
	
	public int getReview_reply_num() {
		return review_reply_num;
	}
	public void setReview_reply_num(int review_reply_num) {
		this.review_reply_num = review_reply_num;
	}
	public String getReview_reply_member_id() {
		return review_reply_member_id;
	}
	public void setReview_reply_member_id(String review_reply_member_id) {
		this.review_reply_member_id = review_reply_member_id;
	}
	public int getReview_reply_review_num() {
		return review_reply_review_num;
	}
	public void setReview_reply_review_num(int review_reply_review_num) {
		this.review_reply_review_num = review_reply_review_num;
	}
	public int getReview_reply_ref() {
		return review_reply_ref;
	}
	public void setReview_reply_ref(int review_reply_ref) {
		this.review_reply_ref = review_reply_ref;
	}
	public int getReview_reply_lev() {
		return review_reply_lev;
	}
	public void setReview_reply_lev(int review_reply_lev) {
		this.review_reply_lev = review_reply_lev;
	}
	public int getReview_reply_seq() {
		return review_reply_seq;
	}
	public void setReview_reply_seq(int review_reply_seq) {
		this.review_reply_seq = review_reply_seq;
	}
	public Timestamp getReview_reply_date() {
		return review_reply_date;
	}
	public void setReview_reply_date(Timestamp review_reply_date) {
		this.review_reply_date = review_reply_date;
	}
	public String getReview_reply_content() {
		return review_reply_content;
	}
	public void setReview_reply_content(String review_reply_content) {
		this.review_reply_content = review_reply_content;
	}
	
	
	
	
	
	
}
