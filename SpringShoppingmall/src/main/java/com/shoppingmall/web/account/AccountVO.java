package com.shoppingmall.web.account;

import java.sql.Date;

public class AccountVO {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String usertype;
	private String address;
	private int postnumber;
	private int phonenumber;
	private Date birthday;
	private String packagename;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostnumber() {
		return postnumber;
	}
	public void setPostnumber(int postnumber) {
		this.postnumber = postnumber;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "AccountVO [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", usertype=" + usertype + ", address=" + address + ", postnumber=" + postnumber + ", phonenumber="
				+ phonenumber + ", birthday=" + birthday + "]";
	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	
	

}
