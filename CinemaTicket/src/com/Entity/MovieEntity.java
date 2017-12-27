package com.Entity;

import java.util.Date;

public class MovieEntity {
	public int id;
	public String name;
	public int typeID;
	public String imgUrl;
	public String area;
	public Date showTime;
	public float price;
	public String remarks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "MovieEntity [id=" + id + ", name=" + name + ", typeID=" + typeID + ", imgUrl=" + imgUrl + ", area=" + area
				+ ", showTime=" + showTime + ", price=" + price + ", remarks=" + remarks + "]";
	}
	
	
}
