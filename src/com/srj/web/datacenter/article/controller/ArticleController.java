package com.srj.web.datacenter.article.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.article.model.Article;
import com.srj.web.datacenter.article.service.ArticleService;
import com.srj.web.sys.model.SysUser;

@Controller
@RequestMapping("article")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/article/article-manager";
	}
	
	/**
	 * 分页显示关键词列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<Article> page = articleService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/article/article-list";
	}
	
	

}
