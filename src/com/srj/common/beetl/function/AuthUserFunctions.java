package com.srj.common.beetl.function;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.srj.common.beetl.utils.BeetlUtils;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.sys.model.SysResource;



@Component
public class AuthUserFunctions {

	/**
	 * 判断用户是否具有指定权限
	 */
	public boolean hasPermission(String url) {
		Map<String, SysResource> allRes = BeetlUtils
				.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
		SysResource sysResource = allRes.get(url);
		if (sysResource == null) {
			return true;
		}
		Map<String, SysResource> userRes = SysUserUtil.getUserButton();
		if (userRes.containsKey(url)) return true;
		return false;
	}
	
	/**
	 * 登录用户
	* @return
	 *//*
	public SysUser getLoginUser(){
		return SysUserUtils.getCacheLoginUser();
	}
	
	*//**
	 * 是否为超级管理员
	* @return
	 *//*
	public boolean isSuper(){
		return getLoginUser().getUserType().equals(Constant.SUPER_ADMIN)?true:false;
	}
	
	*//**
	 * 是否持有所有数据(数据范围，可认为是总管理)
	 *//*
	public boolean hasAllDataScope(){
		return SysUserUtils.getUserDataScope().contains(Constant.DATA_SCOPE_ALL);
	}*/

}
