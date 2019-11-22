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

//	| package_category_code | 
//	package_category_name | 
//	package_category_region | 
//	package_category_city | 
//	package_category_theme | 
//	package_category_image | 
//	package_category_content |

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

}
