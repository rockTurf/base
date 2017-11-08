package com.srj.web.sys.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;

public class SysRole extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String remark;
	
	@Transient
	private Long[] resourceIds; //持有的资源
	
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
	public Long[] getResourceIds(){
	    return (Long[])this.get("resourceIds");
    }
    public void setResourceIds(Long[] resourceIds){
    	this.set("resourceIds", resourceIds);
    }
}
