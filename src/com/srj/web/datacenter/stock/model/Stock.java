package com.srj.web.datacenter.stock.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class Stock extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;//股票编码
	private String name;//股票名称
	private String industry;//细分行业
	private String area;//地区
	public String getCode() {
		return this.getString("code");
	}
	public void setCode(String code) {
		this.set("code",code);
	}
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
	}
	public String getIndustry() {
		return this.getString("industry");
	}
	public void setIndustry(String industry) {
		this.set("industry",industry);
	}
	public String getArea() {
		return this.getString("area");
	}
	public void setArea(String area) {
		this.set("area",area);
	}
	
	
	
	
}
