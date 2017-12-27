package com.Entity;

public class CinemaEntity {
	public int id;
	public String cinemaName;
	public int areaID;
	public String address;
	public String phone;
	public String reamrks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public int getAreaID() {
		return areaID;
	}
	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReamrks() {
		return reamrks;
	}
	public void setReamrks(String reamrks) {
		this.reamrks = reamrks;
	}
	@Override
	public String toString() {
		return "CinemaEntity [id=" + id + ", cinemaName=" + cinemaName + ", areaID=" + areaID + ", address=" + address
				+ ", phone=" + phone + ", reamrks=" + reamrks + "]";
	}
	
	
}
