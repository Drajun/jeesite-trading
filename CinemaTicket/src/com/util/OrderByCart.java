package com.util;

public class OrderByCart{
	private int cID;
	private int movieID;
	private String movieName;
	private String seats;
	private String playTime;
	private float totalPrice;
	private String cinemaName;
	private int cinemaID;
	private String area;
	private String imgUrl;
	
	public OrderByCart(int movieID, String movieName, String seats, String playTime, float totalPrice,
			String cinemaName,String area, int cinemaID,int cID,String imgUrl) {
		super();
		this.movieID = movieID;
		this.movieName = movieName;
		this.seats = seats;
		this.playTime = playTime;
		this.totalPrice = totalPrice;
		this.cinemaName = cinemaName;
		this.cinemaID = cinemaID;
		this.area=area;
		this.cID=cID;
		this.imgUrl=imgUrl;
	}
	
	public OrderByCart() {
		
	}
	
	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public int getCinemaID() {
		return cinemaID;
	}
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}
	@Override
	public String toString() {
		return "OrderByCart [movieID=" + movieID + ", movieName=" + movieName + ", seats=" + seats + ", playTime="
				+ playTime + ", totalPrice=" + totalPrice + ", cinemaName=" + cinemaName + ", cinemaID=" + cinemaID
				+ "]";
	}
	
}