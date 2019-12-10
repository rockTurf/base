package com.srj.web.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.srj.common.base.ZTreeNode;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysRole;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.web.sys.service.SysRoleService;
import com.srj.web.sys.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sysUser")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 跳转编辑页面
	 *
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String showDetail(Long id,@RequestParam Map<String, Object> params,Model model){
		SysUser u = SysUserUtil.getSessionLoginUser();
		SysUser user = sysUserService.getUserById(id);
		//角色列表
		List<SysRole> roleList = sysRoleService.getAllRole();
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		return "sys/user/user-detail";
	}

	/**
	 * 修改
	 *
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public @ResponseBody Integer recordEdit(@ModelAttribute SysUser record,Model model,HttpServletRequest request,HttpServletResponse response){
		int count = sysUserService.editUser(record);
		return count;
	}
}
