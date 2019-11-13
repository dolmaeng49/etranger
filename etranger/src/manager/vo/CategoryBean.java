package manager.vo;

public class CategoryBean {
	
	private int cityRegionCode;
	

	private int cityCode;
	private String cityName;
	private int regionCode;
	private String regionName;
	private int themeCode;
	private String themeName;

	
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
