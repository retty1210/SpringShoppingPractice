package com.shoppingmall.web.order.model;

public class OrderVO {
	
	private int id;
	private String orderlist;
	private String buyername;
	//private String sellername;
	private String packagename;
	private String address;
	private int postnumber;
	private int phonenumber;
	private String paymethod;
	private int price;
	private String orderno;
	private int orderstate;//INT?
	private int packageno;
	
	/*
	 * id: PK용 id
orderlist: String. item id값의 리스트. 구분자 _
buyername: 구매자 아이디
sellername: 판매자 아이디
packagename: 주문자이름
address: 주소
postnumber: 우편번호
phonenumber: 전화번호
paymethod: 결제방법(무통장입금/ 카드)
priceAll?: 총 결제금액(배송비 제외?) <-그냥 배송비까지 합쳐서 해도 될거같아
orderno: 주문번호(비회원의 경우 이 값으로 주문목록에 접속해야함)
orderstate: 주문상태
packageno: 송장번호
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(String orderlist) {
		this.orderlist = orderlist;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
//	public String getSellername() {
//		return sellername;
//	}
//	public void setSellername(String sellername) {
//		this.sellername = sellername;
//	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
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
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public int getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(int orderstate) {
		this.orderstate = orderstate;
	}
	public int getPackageno() {
		return packageno;
	}
	public void setPackageno(int packageno) {
		this.packageno = packageno;
	}
	
	
	
	

}
