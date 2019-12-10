package product.vo;

public class SearchBean {
	
	private String keyword;
	private String depart_date;
	private String arriv_date;
	private String region;
	private String city;
	
	
	public SearchBean() {
	}

	public SearchBean(String keyword, String depart_date, String arriv_date, String region, String city) {
		this.keyword = keyword;
		this.depart_date = depart_date;
		this.arriv_date = arriv_date;
		this.region = region;
		this.city = city;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDepart_date() {
		return depart_date;
	}
	public void setDepart_date(String depart_date) {
		this.depart_date = depart_date;
	}
	public String getArriv_date() {
		return arriv_date;
	}
	public void setArriv_date(String arriv_date) {
		this.arriv_date = arriv_date;
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	

}
