package com.srj.web.datacenter.article.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.article.mapper.KeywordMapper;
import com.srj.web.datacenter.article.model.Keyword;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.DateUtils;

@Service("keywordService")
public class KeywordService {
	
	@Autowired
	private KeywordMapper keyMapper;

	
	/*
	 * 分页显示关键词
	 * */
	public PageInfo<Keyword> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Keyword> list = keyMapper.findPageInfo(params);
		return new PageInfo<Keyword>(list);
	}

	/*
	 * 保存关键字
	 * */
	public int addKeyword(Map<String, Object> params, SysUser u) {
		Keyword k = new Keyword();
		k.setName((String) params.get("name"));
		k.setCreate_name(u.getName());
		k.setCreate_time(DateUtils.formatDateTime(new Date()));
		int count = keyMapper.insertSelective(k);
		return count;
	}
	/*
	 * 检查是否有重复关键字(true==有，false==没有)
	 * */
	public boolean checkKeyword(Map<String, Object> params) {
		boolean b = true;
		Long l = keyMapper.checkKeyword(params);
		if(l!=null){
			b = false;
		}
		return b;
	}
	
	

}
