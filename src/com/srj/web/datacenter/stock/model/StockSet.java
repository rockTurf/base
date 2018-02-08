package com.srj.web.datacenter.stock.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;

public class StockSet extends BaseEntity implements Serializable{
	
	/**
	 * 设置特大单，大单，中单，小单的数值，供统计之用
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long stock_id;//股票id
	private String huge;//特大单数
	private String large;//大单数
	private String middle;//中单数
	private String small;//小单数
	
	public Long getStock_id() {
		return this.getLong("stockId");
	}
	public void setStock_id(Long stock_id) {
		this.set("stock_id",stock_id);
	}
	public String getHuge() {
		return this.getString("huge");
	}
	public void setHuge(String huge) {
		this.set("huge",huge);
	}
	public String getLarge() {
		return this.getString("large");
	}
	public void setLarge(String large) {
		this.set("large",large);
	}
	public String getMiddle() {
		return this.getString("middle");
	}
	public void setMiddle(String middle) {
		this.set("middle",middle);
	}
	public String getSmall() {
		return this.getString("small");
	}
	public void setSmall(String small) {
		this.set("small",small);
	}

}
