package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Products {

	private int productNo; //PK
	private int productCategoryNo; //FK
	private String productName;
	private String productPrice;
	private int productState;
	private Date productRegdate;
	private int productHitcount;
	private int productSellcount;


	private String categoryName;

	private MultipartFile[] battach;
	private int imgNo;
	private String imgOname;
	private String imgSname;
	private String imgType;
	private int imgState;

	private String keyword;

	private int[] state = new int[5];
	
	
	public int[] getState() {
		return state;
	}
	public void setState(int[] state) {
		this.state = state;
	}
	public MultipartFile[] getBattach() {
		return battach;
	}
	public void setBattach(MultipartFile[] battach) {
		this.battach = battach;
	}
	public int getImgState() {
		return imgState;
	}
	public void setImgState(int imgState) {
		this.imgState = imgState;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getProductCategoryNo() {
		return productCategoryNo;
	}
	public void setProductCategoryNo(int productCategoryNo) {
		this.productCategoryNo = productCategoryNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductState() {
		return productState;
	}
	public void setProductState(int productState) {
		this.productState = productState;
	}
	public Date getProductRegdate() {
		return productRegdate;
	}
	public void setProductRegdate(Date productRegdate) {
		this.productRegdate = productRegdate;
	}
	public int getProductHitcount() {
		return productHitcount;
	}
	public void setProductHitcount(int productHitcount) {
		this.productHitcount = productHitcount;
	}
	public int getProductSellcount() {
		return productSellcount;
	}
	public void setProductSellcount(int productSellcount) {
		this.productSellcount = productSellcount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getImgOname() {
		return imgOname;
	}
	public void setImgOname(String imgOname) {
		this.imgOname = imgOname;
	}
	public String getImgSname() {
		return imgSname;
	}
	public void setImgSname(String imgSname) {
		this.imgSname = imgSname;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}	
}
