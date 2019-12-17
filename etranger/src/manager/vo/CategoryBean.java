package manager.vo;

public class CategoryBean {

	private int cityRegionCode;

	private int cityCode;
	private String cityName;
	private int regionCode;
	private String regionName;
	private int themeCode;
	private String themeName;
	private String package_category_code;
	private int package_category_region;
	private int package_category_city;
	private String package_category_theme;
	private String package_category_name;
	private String package_category_image;
	private String package_category_content;
	private int package_category_wish_count;
	// join 으로 들고올 값들
	private int review_count;
	private double review_star_avg; 
	private int package_product_current; // 현재 인원
	private int min_price; // 최저가 
	private int total_headcount; // 카테고리 당 총 예약인원
	private String min_date; // 날짜 임박

//	package_category_code | 
//	package_category_name | 
//	package_category_region | 
//	package_category_city | 
//	package_category_theme | 
//	package_category_image | 
//	package_category_content |

	public int getTotal_headcount() {
		return total_headcount;
	}

	public String getMin_date() {
		return min_date;
	}

	public void setMin_date(String min_date) {
		this.min_date = min_date;
	}

	public void setTotal_headcount(int total_headcount) {
		this.total_headcount = total_headcount;
	}

	public int getMin_price() {
		return min_price;
	}

	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}

	public int getReview_count() {
		return review_count;
	}

	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	public double getReview_star_avg() {
		return review_star_avg;
	}

	public void setReview_star_avg(double review_star_avg) {
		this.review_star_avg = review_star_avg;
	}

	public int getPackage_category_wish_count() {
		return package_category_wish_count;
	}

	public void setPackage_category_wish_count(int package_category_wish_count) {
		this.package_category_wish_count = package_category_wish_count;
	}

	public int getCityRegionCode() {
		return cityRegionCode;
	}

	public void setCityRegionCode(int cityRegionCode) {
		this.cityRegionCode = cityRegionCode;
	}

	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;

	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getThemeCode() {
		return themeCode;
	}

	public void setThemeCode(int themeCode) {
		this.themeCode = themeCode;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public void setPackage_category_code(String package_category_code) {
		this.package_category_code = package_category_code;
	}

	public String getPackage_category_code() {
		return package_category_code;
	}

	public int getPackage_category_region() {
		return package_category_region;
	}

	public void setPackage_category_region(int package_category_region) {
		this.package_category_region = package_category_region;
	}

	public int getPackage_category_city() {
		return package_category_city;
	}

	public void setPackage_category_city(int package_category_city) {
		this.package_category_city = package_category_city;
	}

	public String getPackage_category_theme() {
		return package_category_theme;
	}

	public void setPackage_category_theme(String package_category_theme) {
		this.package_category_theme = package_category_theme;
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

	public String getPackage_category_content() {
		return package_category_content;
	}

	public void setPackage_category_content(String package_category_content) {
		this.package_category_content = package_category_content;
	}

	public int getPackage_product_current() {
		return package_product_current;
	}

	public void setPackage_product_current(int package_product_current) {
		this.package_product_current = package_product_current;
	}

}
