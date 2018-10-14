package com.srj.web.datacenter.news.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.news.model.News;

public interface NewsMapper extends Mapper<News>{

	List<News> findPageInfo(Map<String, Object> params);


}
