package com.srj.web.datacenter.stock.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;

public class StockTrade extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String time;//时间
	private Float price;//价格
	private String deal;//成交
	private Integer count;//笔数
	private String bs;//BS
	private Long stock_id;//股票id
	
	@Transient
	private String stock_name;//股票名
	@Transient
	private String stock_code;//股票代码
	
	public String getStock_name() {
		return this.getString("stockName");
	}


	public String getStock_code() {
		return this.getString("StockCode");
	}
	
	public String getTime() {
		return this.getString("time");
	}
	public void setTime(String time) {
		this.set("time",time);
	}
	public Float getPrice() {
		return this.getFloat("price");
	}
	public void setPrice(Float price) {
		this.set("price",price);
	}
	public String getDeal() {
		return this.getString("code");
	}
	public void setDeal(String deal) {
		this.set("deal",deal);
	}
	public Integer getCount() {
		return this.getInteger("deal");
	}
	public void setCount(Integer count) {
		this.set("count",count);
	}
	public String getBs() {
		return this.getString("bs");
	}
	public void setBs(String bs) {
		this.set("bs",bs);
	}
	public Long getStock_id() {
		return this.getLong("stockId");
	}
	public void setStock_id(Long stock_id) {
		this.set("stock_id",stock_id);
	}
	
	
	
}
