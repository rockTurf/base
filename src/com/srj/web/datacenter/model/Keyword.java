package com.srj.web.datacenter.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.srj.common.base.BaseEntity;

public class Keyword extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String create_name;
	private String create_time;
	
	
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
	}
	public String getCreate_name() {
		return this.getString("createName");
	}
	public void setCreate_name(String create_name) {
		this.set("create_name",create_name);
	}
	public String getCreate_time() {
		return this.getString("createTime");
	}
	public void setCreate_time(String create_time) {
		this.set("create_time",create_time);
	}
	
	
}
