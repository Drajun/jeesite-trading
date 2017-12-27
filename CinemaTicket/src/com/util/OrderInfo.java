package com.util;

public class OrderInfo {
	public String imgUrl;
	public String movieName;
	public String cinemaName;
	public String areaName;
	public String playTime;
	public String movieTotalPrice;
	
	public OrderInfo(String imgUrl, String movieName, String cinemaName, String areaName, String playTime,
			String movieTotalPrice) {
		super();
		this.imgUrl = imgUrl;
		this.movieName = movieName;
		this.cinemaName = cinemaName;
		this.areaName = areaName;
		this.playTime = playTime;
		this.movieTotalPrice = movieTotalPrice;
	}
	
	
}
