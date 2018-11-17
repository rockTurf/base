package com.srj.web.datacenter.news.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class News extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;//标题
	private String content;//内容
	private String source;//来源
	private String author;//作者
	private String news_time;//新闻时间
	private String create_time;//创建时间
	public String getTitle() {
		return this.getString("title");
	}
	public void setTitle(String title) {
		this.set("title",title);
	}
	public String getContent() {
		return this.getString("content");
	}
	public void setContent(String content) {
		this.set("content",content);
	}
	public String getSource() {
		return this.getString("source");
	}
	public void setSource(String source) {
		this.set("source",source);
	}
	public String getAuthor() {
		return this.getString("author");
	}
	public void setAuthor(String author) {
		this.set("author",author);
	}
	public String getCreate_time() {
		return this.getString("createTime");
	}
	public void setCreate_time(String create_time) {
		this.set("create_time",create_time);
	}

	public String getNews_time() {
		return this.getString("newsTime");
	}

	public void setNews_time(String news_time) {
		this.set("news_time",news_time);
	}
}
