package com.srj.web.datacenter.article.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.datacenter.article.model.Article;

public interface ArticleMapper extends Mapper<Article>{

	List<Article> findPageInfo(Map<String, Object> params);

	Integer insertArticleKeyword(Map<String, Object> params);

	Integer deleteArticleKeyword(Long id);

	List<Article> selectTop();
	
	Article selectTopOne();

	Article selectTopOne();
}
