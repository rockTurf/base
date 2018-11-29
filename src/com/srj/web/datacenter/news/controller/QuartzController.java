/*package com.srj.web.datacenter.news.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.srj.web.datacenter.news.model.News;
import com.srj.web.datacenter.news.service.NewsService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.SpiderUtils;

@Controller
@RequestMapping("quartz")
public class QuartzController {

	@Resource
	private NewsService newsService;
	
	*//**
	 * 爬虫接口
	 *//*
	@RequestMapping("/start")
	public void spider(){
		//凤凰网
		List<JSONObject> list = SpiderUtils.getifeng();
		for(JSONObject obj:list){
				News item = new News();
				item.setTitle(obj.getString("title"));
				item.setContent(obj.getString("content"));
				item.setNews_time(obj.getString("newsTime"));
				item.setCreate_time(DateUtils.formatDateTime(new Date()));
				item.setSource("凤凰网财经专栏");
				newsService.insertItem(item);
		}
	}
	
}
*/