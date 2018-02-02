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
	private String total_all;//全部
	private String total_B;//全部买
	private String total_S;//全部卖
	private String huge_B;//特大单买
	private String huge_S;//特大单卖
	private String large_B;//大单买
	private String large_S;//大单卖
	private String middle_B;//中单买
	private String middle_S;//中单卖
	private String small_B;//小单买
	private String small_S;//小单卖
	
	private String stock_id;//股票id
	
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

	public Long getStock_id() {
		return this.getLong("stockId");
	}

	public void setStock_id(Long stock_id) {
		this.set("stock_id",stock_id);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getData_time() {
		return this.getString("dataTime");
	}
	public String getTotal_all() {
		return this.getString("totalAll");
	}
	public String getTotal_B() {
		return this.getString("totalB");
	}
	public String getHuge_B() {
		return this.getString("hugeB");
	}
	public String getHuge_S() {
		return this.getString("hugeS");
	}
	public String getTotal_S() {
		return this.getString("totalS");
	}
	public String getLarge_B() {
		return this.getString("largeB");
	}
	public String getLarge_S() {
		return this.getString("largeS");
	}
	public String getMiddle_B() {
		return this.getString("middleB");
	}
	public String getMiddle_S() {
		return this.getString("middleS");
	}
	public String getSmall_B() {
		return this.getString("smallB");
	}
	public String getSmall_S(){ 
		return this.getString("small_S");
	}
	
	
}
