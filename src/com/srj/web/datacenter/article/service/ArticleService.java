package com.srj.web.datacenter.article.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.web.datacenter.article.mapper.ArticleMapper;
import com.srj.web.datacenter.article.model.Article;
import com.srj.web.sys.model.SysFile;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysFileService;
import com.srj.web.util.DateUtils;

@Service("articleService")
public class ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;
	@Resource
	private SysFileService sysFileService;
	
	/*
	 * 分页显示关键词
	 * */
	public PageInfo<Article> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Article> list = articleMapper.findPageInfo(params);
		for(Article a:list){
			List<SysFile> fileList = sysFileService.selectByParams(Constant.FILE_FLAG_ARTICLE,a.getId());
			a.setFileList(fileList);
		}
		return new PageInfo<Article>(list);
	}
	/*
	 * 新增文章
	 * */
	public int saveArticle(Article record,SysUser u) {
		//先把文章添加到文章表
		record.setCreate_name(u.getName());
		record.setCreate_time(DateUtils.formatDateTime(new Date()));
		int count = articleMapper.insertSelective(record);
		//再把文章id,关键词id存入中间表
		if(count>0){
			count = articleMapper.insertArticleKeyword(record);	
		}
		return count;
	}

}
