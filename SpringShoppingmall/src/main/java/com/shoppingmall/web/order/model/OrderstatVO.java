package com.shoppingmall.web.order.model;

public class OrderstatVO {
	
	private int id;
	private String orderst;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderst() {
		return orderst;
	}
	public void setOrderst(String orderst) {
		this.orderst = orderst;
	}
	@Override
	public String toString() {
		return "OrderstatVO [id=" + id + ", orderst=" + orderst + "]";
	}

}
