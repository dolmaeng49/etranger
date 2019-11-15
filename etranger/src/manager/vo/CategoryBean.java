package manager.vo;

public class CategoryBean {
	
	private int cityRegionCode;
	

	private int cityCode;
	private String cityName;
	private int regionCode;
	private String regionName;
	private int themeCode;
	private String themeName;
	private int  package_category_region;
	private int  package_category_city;
	private int package_category_code;

	
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

	public int getPackage_region_code() {
		return package_category_code;
	}
	
	public void setPackage_region_code(int package_region_code) {
		this.package_category_code = package_region_code;
		
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

}
