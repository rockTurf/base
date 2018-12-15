package com.srj.web.datacenter.news.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.article.model.Article;
import com.srj.web.datacenter.news.model.News;
import com.srj.web.datacenter.news.service.NewsService;
import com.srj.web.sys.model.SysRole;
import com.srj.web.sys.model.SysUser;


@Controller
@RequestMapping("news")
public class NewsController {

	@Resource
	private NewsService newsService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/news/news-manager";
	}
	

	/**
	 * 分页显示列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<News> page = newsService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/news/news-list";
	}
	
	
	/**
	 * 跳转编辑角色页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String showDetail(Long id,@RequestParam Map<String, Object> params,Model model){
		News news = newsService.getById(id);
		model.addAttribute("news", news);
		return "datacenter/news/news-detail";
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value = "save")
	public @ResponseBody Integer save(@ModelAttribute News record,@RequestParam Map<String, Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser();
		//新增
		int count = newsService.saveArticle(record,u);
		return count;
	}
	
}
