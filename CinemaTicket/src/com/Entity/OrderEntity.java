package com.Entity;

public class OrderEntity {
	private int id;
	private int movieID;
	private int buyerID;
	private String seats;
	private String playTime;
	private float totalPrice;
	private int getNum;
	private String remarks;
	private int cinemaID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public int getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getGetNum() {
		return getNum;
	}
	public void setGetNum(int getNum) {
		this.getNum = getNum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getCinemaID() {
		return cinemaID;
	}
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", movieID=" + movieID + ", buyerID=" + buyerID + ", seats=" + seats + ", playTime=" + playTime
				+ ", totalPrice=" + totalPrice + ", getNum=" + getNum + ", remarks=" + remarks + ", cinemaID=" + cinemaID
				+ "]";
	}
	
	
}
