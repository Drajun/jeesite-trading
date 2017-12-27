package com.Entity;

public class AreaEntity {
	public int id;
	public String areaName;
	public String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "AreaEntity [id=" + id + ", areaName=" + areaName + ", state=" + state + "]";
	}
	
	
}
