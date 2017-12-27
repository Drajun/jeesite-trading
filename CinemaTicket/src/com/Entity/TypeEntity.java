package com.Entity;

public class TypeEntity {
	public int id;
	public String typeName;
	public String remarks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "TypeEntity [id=" + id + ", typeName=" + typeName + ", remarks=" + remarks + "]";
	}
	
	
}
