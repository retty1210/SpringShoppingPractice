package com.shoppingmall.web.order.model;

public class OrderAndSellerVO {
	
	private OrderSellerVO orderseller;
	private OrderVO order;
	public OrderSellerVO getOrderseller() {
		return orderseller;
	}
	public void setOrderseller(OrderSellerVO orderseller) {
		this.orderseller = orderseller;
	}
	public OrderVO getOrder() {
		return order;
	}
	public void setOrder(OrderVO order) {
		this.order = order;
	}
	
	

}
