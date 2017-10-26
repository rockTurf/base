package com.srj.web.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysRole;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.web.sys.service.SysRoleService;
import com.srj.web.sys.service.SysUserService;

@Controller
@RequestMapping("userCenter")
public class UserCenterController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		//角色列表
		List<SysRole> roleList = sysRoleService.getAllRole();
		params.put("roleList", roleList);
		model.addAttribute("params", params);
		return "sys/user/user-manager";
	}
	
	/**
	 * 分页显示用户列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<SysUser> page = sysUserService.findPageInfo(params);
		model.addAttribute("page", page);
		return "sys/user/user-list";
	}
	/**
	 * 注册用户
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userRegist")
	public @ResponseBody Map<String, Object> userRegist(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser user= sysUserService.CheckUser(params);
		//数据库取到对应的用户信息不为空 判断
		if(user==null){
			int count = sysUserService.addUser(params);
			if(count>0){
				msg.put("success", "注册成功！");
			}else{
				msg.put("error", "未知错误！");
			}
		}else{
			msg.put("error", "用户名已存在！");
		}
		return msg;
	}
	/**
	 * 添加用户
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody Integer userSave(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> msg = new HashMap<String, Object>();
		int count = sysUserService.addUser(params);
		return count;
	}
	
	/**
	 * 检验用户名
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkUser")
	public @ResponseBody Integer checkUser(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser user= sysUserService.CheckUser(params);
		int count = 1;
		//数据库取到对应的用户信息不为空 判断
		if(user==null){
			count = 0;
		}
		return count;
	}
}
