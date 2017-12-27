package com.Entity;

public class RolesEntity {
	private int id;
	private String roleName;
	private String remarks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + ", remarks=" + remarks + "]";
	}
	
	
}
