package com.srj.web.datacenter.news.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.news.mapper.NewsMapper;
import com.srj.web.datacenter.news.model.News;
import com.srj.web.datacenter.stock.mapper.StockDataMapper;
import com.srj.web.datacenter.stock.mapper.StockMapper;
import com.srj.web.datacenter.stock.model.StockData;

@Service("newsService")
public class NewsService {

	@Resource
	private NewsMapper newsMapper;
	/*
	 * 分页显示列表
	 * */
	public PageInfo<News> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<News> list = newsMapper.findPageInfo(params);
		return new PageInfo<News>(list);
	}

      public void insertList(List<News> newsList) {
		for(News news:newsList){
			newsMapper.insert(news);
		}
      }
}
