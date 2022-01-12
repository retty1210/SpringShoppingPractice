package com.shoppingmall.web.order.model;

public class OrderSellerVO {
	private int id;
	private int orderid;
	private int sellerid;
	private String orderno;
	private String itemlist;
	
	public OrderSellerVO() {}
	public OrderSellerVO(int orderid, int sellerid, String orderno, String itemlist) {
		this.orderid = orderid;
		this.sellerid = sellerid;
		this.orderno = orderno;
		this.itemlist = itemlist;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getItemlist() {
		return itemlist;
	}
	public void setItemlist(String itemlist) {
		this.itemlist = itemlist;
	}
	@Override
	public String toString() {
		return "OrderSellerVO [id=" + id + ", orderid=" + orderid + ", sellerid=" + sellerid + ", orderno=" + orderno
				+ ", itemlist=" + itemlist + "]";
	}
	
	

}
