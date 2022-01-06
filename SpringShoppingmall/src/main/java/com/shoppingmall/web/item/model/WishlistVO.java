package com.shoppingmall.web.item.model;

public class WishlistVO {
	
	private int id;
	private int userid;
	private String username;
	private String wishlist;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWishlist() {
		return wishlist;
	}
	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}
	@Override
	public String toString() {
		return "WishlistVO [id=" + id + ", userid=" + userid + ", username=" + username + ", wishlist=" + wishlist
				+ "]";
	}
	
	

}
