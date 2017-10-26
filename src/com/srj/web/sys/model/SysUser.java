package com.srj.web.sys.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;
import com.srj.common.constant.Constant;

public class SysUser  extends BaseEntity implements Serializable{
	private String username ;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String delFlag;
/*	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}*/
	public String getUsername() {
		return this.getString("username");
	}
	public void setUsername(String username) {
		this.set("username",username);
	}
	
	public String getPassword() {
		return this.getString("password");
	}
	public void setPassword(String password) {
		this.set("password",password);
	}
	public String getName() {
		return this.getString("name");
	}
	public void setName(String name) {
		this.set("name",name);
	}
	public String getEmail() {
		return this.getString("email");
	}
	public void setEmail(String email) {
		this.set("email",email);
	}
	public String getPhone() {
		return this.getString("phone");
	}
	public void setPhone(String phone) {
		this.set("phone",phone);
	}
	public String getDelFlag() {
		return this.getString("delFlag");
	}
	public void setDelFlag(String delFlag) {
		this.set("delFlag",delFlag);
	}
	public boolean isAdmin() {
		return Constant.SUPER_ADMIN==(this.getId())?true:false;
	}
}
