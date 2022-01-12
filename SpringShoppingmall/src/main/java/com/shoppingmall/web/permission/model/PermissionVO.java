package com.shoppingmall.web.permission.model;

public class PermissionVO {
	
	private int id;
	private String usertype;
	private String tablename;
	private String insertpm;
	private String updatepm;
	private String deletepm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getInsertpm() {
		return insertpm;
	}
	public void setInsertpm(String insertpm) {
		this.insertpm = insertpm;
	}
	public String getUpdatepm() {
		return updatepm;
	}
	public void setUpdatepm(String updatepm) {
		this.updatepm = updatepm;
	}
	public String getDeletepm() {
		return deletepm;
	}
	public void setDeletepm(String deletepm) {
		this.deletepm = deletepm;
	}
	@Override
	public String toString() {
		return "PermissionVO [id=" + id + ", usertype=" + usertype + ", tablename=" + tablename + ", insertpm="
				+ insertpm + ", updatepm=" + updatepm + ", deletepm=" + deletepm + "]";
	}
	
	

}
