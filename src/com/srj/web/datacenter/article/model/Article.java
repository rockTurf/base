package com.srj.web.datacenter.article.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;
import com.srj.web.sys.model.SysFile;

public class Article extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String create_name;
	private String create_time;
	
	
	@Transient
	private List<SysFile> fileList;
	@Transient
	private Long[] kids;//关键词列表
	
	@Transient
	private String keywords;//关键词字符串
	
	public String getTitle() {
		return this.getString("title");
	}
	public void setTitle(String title) {
		this.set("title",title);
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
	public Long[] getKids() {
		return (Long[])this.get("kids");
	}
	public void setKids(Long[] kids) {
		this.set("kids",kids);
	}
	public String getKeywords() {
		return this.getString("keywords");
	}
	public List<SysFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<SysFile> fileList) {
		this.set("fileList", fileList);
	}
	
	
	
	
}
