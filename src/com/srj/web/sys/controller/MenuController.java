package com.srj.web.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.srj.common.base.ZTreeNode;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.stock.model.Stock;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * 
 * @ClassName: MenuController
 * @author
 * @date 2014年10月11日 上午11:38:28
 *
 */

@Controller
@RequestMapping("menu")
public class MenuController{

	@Resource
	private SysResourceService sysResourceService;

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @param model
	 * @return 菜单管理模块html
	 */
	@RequestMapping
	public String toMenu(@RequestParam Map<String, Object> params,Model model) {
		//model.addAttribute("treeList", JSON.toJSONString(sysResourceService.getMenuTree()));
		SysUser u = SysUserUtil.getSessionLoginUser();
		List<SysResource> menuList = sysResourceService.getAllResourcesList();
		//menuList另作处理，再返回
		JSONArray menuArray = ZTreeNode.menu2zMenuTree(menuList);
		params.put("menuList", menuList);
		model.addAttribute("params", params);
		return "sys/menu/menu";
	}



	/**
	 * 分页显示菜单table
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<SysResource> page = sysResourceService.findPageInfo(params);
		model.addAttribute("page", page);
		return "sys/menu/menu-list";
	}
	
	/**
	 * 新增菜单
	 */
	@RequestMapping(value = "save")
	public @ResponseBody Integer save(@ModelAttribute SysResource record,@RequestParam Map<String, Object> params,
			Model model,HttpServletRequest request,HttpServletResponse response){
		SysUser u = SysUserUtil.getSessionLoginUser();
		return sysResourceService.saveRecord(record);
	}

}
