package com.srj.web.datacenter.article.controller;

import java.util.List;
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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.article.model.Article;
import com.srj.web.datacenter.article.model.Keyword;
import com.srj.web.datacenter.article.service.ArticleService;
import com.srj.web.datacenter.article.service.KeywordService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysFileService;

@Controller
@RequestMapping("article")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	@Resource
	private KeywordService keywordService;
	@Resource
	private SysFileService sysFileService;
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		//关键词列表
		List<Keyword> klist = keywordService.getAllKeyword();
		params.put("klist", klist);
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
	
	/**
	 * 新增文章
	 */
	@RequestMapping(value = "save")
	public @ResponseBody Integer save(@ModelAttribute Article record,@RequestParam Map<String, Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser();
		//新增文章
		int count = articleService.saveArticle(record,u);
		//存入附件
		sysFileService.saveFile(Constant.FILE_FLAG_ARTICLE, record.getId(), (String) params.get("filepath"), u);
		return count;
	}
	
	/**
	 * 删除文章
	 */
	@RequestMapping(value = "delete")
	public @ResponseBody Integer delete(@RequestParam Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		//删除文章
		int count = articleService.deleteArticle(id);
		return count;
	}
	
	/**
	 * 推送文章
	 */
	@RequestMapping(value = "push")
	public @ResponseBody List<Article> pushArticle(@RequestParam Long userId){
		//根据用户id发出推送文章
		List<Article> list = articleService.selectTop(userId);
		
		return list;
	}

	/**
	 * 扫描最新文章
	 */
	@RequestMapping(value = "lastst")
	public @ResponseBody Integer checkArticle(@RequestParam Long userId){
		//根据用户id发出推送文章
		boolean b = articleService.checkArticle(userId);
		if(b==true){
			return 1;
		}
		return 0;
	}
}
