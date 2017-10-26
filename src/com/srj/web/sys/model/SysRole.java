package com.srj.web.sys.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class SysRole extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String remark;
	
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
	}
	public String getRemark() {
		return this.getString("remark");
	}
	public void setRemark(String remark) {
		this.set("remark",remark);
	}
	
}
