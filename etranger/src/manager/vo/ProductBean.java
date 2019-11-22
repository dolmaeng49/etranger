package manager.vo;

import java.util.Date;

public class ProductBean {
	private int productWishCount;
	private int productCurrent;
	private int productTotal;
	private int productPrice;
	private String productArrivDate;
	private String productDepartDate;
	private String categoryCode;
	private String productNum;

	public int getProductWishCount() {
		return productWishCount;
	}

	public void setProductWishCount(int productWishCount) {
		this.productWishCount = productWishCount;
	}

	public int getProductCurrent() {
		return productCurrent;
	}

	public void setProductCurrent(int productCurrent) {
		this.productCurrent = productCurrent;
	}

	public int getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(int productTotal) {
		this.productTotal = productTotal;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductArrivDate() {
		return productArrivDate;
	}

	public void setProductArrivDate(String productArrivDate) {
		this.productArrivDate = productArrivDate;
	}

	public String getProductDepartDate() {
		return productDepartDate;
	}

	public void setProductDepartDate(String productDepartDate) {
		this.productDepartDate = productDepartDate;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
}
