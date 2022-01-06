package com.shoppingmall.web.item.model;

import java.sql.Date;

public class ItemVO {
	private int id;
	private String itemname;
	private String sellername;
	private Date uploadtime;
	private String thumURL;
	private String infoURL;
	private String infotext;
	
	public ItemVO() {}
	public ItemVO(String itemname, String sellername, String infotext) {
		this.itemname = itemname;
		this.sellername = sellername;
		this.infotext = infotext;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getThumURL() {
		return thumURL;
	}
	public void setThumURL(String thumURL) {
		this.thumURL = thumURL;
	}
	public String getInfoURL() {
		return infoURL;
	}
	public void setInfoURL(String infoURL) {
		this.infoURL = infoURL;
	}
	public String getInfotext() {
		return infotext;
	}
	public void setInfotext(String infotext) {
		this.infotext = infotext;
	}
	
	

}
