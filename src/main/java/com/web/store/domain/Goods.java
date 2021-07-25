package com.web.store.domain;

public class Goods {

	//商品id
	private long id;
	//商品名稱
	private String name;
	//價格
	private double price;
	//產品描述
	private String description;
	//電腦品牌
	private String brand;
	//cup品牌
	private String cpuBrand;
	//cpu型號
	private String cpuType;
	
	//快取
	private String memoryCapacity;
	//硬碟大小
	private String hdCapacity;
	//顯卡型號
	private String cardModel;
	//螢幕
	private String displaysize;
	//電腦圖片
	private String image;
	
	
	
//	private List<OrderLineItem> orderLineItems;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCpuBrand() {
		return cpuBrand;
	}
	public void setCpuBrand(String cpuBrand) {
		this.cpuBrand = cpuBrand;
	}
	public String getCpuType() {
		return cpuType;
	}
	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}
	public String getMemoryCapacity() {
		return memoryCapacity;
	}
	public void setMemoryCapacity(String memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}
	public String getHdCapacity() {
		return hdCapacity;
	}
	public void setHdCapacity(String hdCapacity) {
		this.hdCapacity = hdCapacity;
	}
	public String getCardModel() {
		return cardModel;
	}
	public void setCardModel(String cardModel) {
		this.cardModel = cardModel;
	}
	public String getDisplaysize() {
		return displaysize;
	}
	public void setDisplaysize(String displaysize) {
		this.displaysize = displaysize;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

	
	
	
	
	
	
	
}
