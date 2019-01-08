package com.srj.web.datacenter.stock.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;

public class StockData extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String data_time;//时间
	private String stock_id;//股票id
	private String rise;//涨幅
	private String price;//现价
	private String turnover;//换手
	private String inflow;//净流入
	private String day_huge;//当日超大单
	private String day_large;//当日大单
	private String day_middle;//当日中单
	private String day_small;//当日小单
	
	
	
	
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
	public String getStock_id() {
		return this.getString("stockId");
	}
	public void setStock_id(String stock_id) {
		this.set("stock_id",stock_id);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getData_time() {
		return this.getString("dataTime");
	}
	public void setData_time(String data_time) {
		this.set("data_time",data_time);
	}
	
	
	public String getRise() {
		return this.getString("rise");
	}
	public void setRise(String rise) {
		this.set("rise",rise);
	}
	public String getPrice() {
		return this.getString("price");
	}
	public void setPrice(String price) {
		this.set("price",price);
	}
	public String getTurnover() {
		return this.getString("turnover");
	}
	public void setTurnover(String turnover) {
		this.set("turnover",turnover);
	}
	public String getInflow() {
		return this.getString("inflow");
	}
	public void setInflow(String inflow) {
		this.set("inflow",inflow);
	}
	public String getDay_huge() {
		return this.getString("dayHuge");
	}
	public void setDay_huge(String day_huge) {
		this.set("day_huge",day_huge);
	}
	public String getDay_large() {
		return this.getString("dayLarge");
	}
	public void setDay_large(String day_large) {
		this.set("day_large",day_large);
	}
	public String getDay_middle() {
		return this.getString("dayMiddle");
	}
	public void setDay_middle(String day_middle) {
		this.set("day_middle",day_middle);
	}
	public String getDay_small() {
		return this.getString("daySmall");
	}
	public void setDay_small(String day_small) {
		this.set("day_small",day_small);
	}
	
	
	
	
}
