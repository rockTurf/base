package com.srj.web.datacenter.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.article.mapper.ArticleMapper;
import com.srj.web.datacenter.article.model.Article;

@Service("articleService")
public class ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	
	/*
	 * 分页显示关键词
	 * */
	public PageInfo<Article> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Article> list = articleMapper.findPageInfo(params);
		return new PageInfo<Article>(list);
	}
	/*
	 * 新增文章
	 * */
	public int saveArticle(Map<String, Object> params) {
		return 0;
	}

}
