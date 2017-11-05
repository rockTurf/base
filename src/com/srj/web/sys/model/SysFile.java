package com.srj.web.sys.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.srj.common.base.BaseEntity;


@SuppressWarnings({ "unused"})
@Table(name="sys_file")
public class SysFile extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long table_id;//表id
	private String flag;//表标识
	private String filename;//文件名
	private String fileurl;//路径
	private String create_time;//创建时间
	private String create_name;//创建人
	public Long getTable_id() {
		return this.getLong("table_id");
	}
	public void setTable_id(Long table_id) {
		this.set("table_id", table_id) ;
	}
	public String getFlag() {
		return this.getString("flag");
	}
	public void setFlag(String flag) {
		this.set("flag", flag) ;
	}
	public String getFilename() {
		return this.getString("filename");
	}
	public void setFilename(String filename) {
		this.set("filename", filename) ;
	}
	public String getFileurl() {
		return this.getString("fileurl");
	}
	public void setFileurl(String fileurl) {
		this.set("fileurl", fileurl) ;
	}
	public String getCreate_time() {
		return this.getString("createTime");
	}
	public void setCreate_time(String create_time) {
		this.set("create_time", create_time) ;
	}
	public String getCreate_name() {
		return this.getString("createName");
	}
	public void setCreate_name(String create_name) {
		this.set("create_name", create_name) ;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
