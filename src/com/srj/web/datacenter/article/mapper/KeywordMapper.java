package com.srj.web.datacenter.article.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.article.model.Keyword;

public interface KeywordMapper extends Mapper<Keyword>{

	List<Keyword> findPageInfo(Map<String, Object> params);

	Long checkKeyword(Map<String, Object> params);

	
}
