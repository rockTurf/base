package com.srj.common.constant;

import java.util.List;
import java.util.Properties;

import com.google.common.collect.Lists;

public class Constant {
	
	private static Properties props = null;

	// 用cookie 记录登录用户的用户名 和 密码
	public static final String SET_COOKIE_USERNAME = "CookieRemUserName";
	public static final String SET_COOKIE_PASSWORD = "CookieRemUserPwd";
	
	
	// 删除标记（0：正常；1：删除；2：审核；）
	public static final String FIELD_DEL_FLAG = "delFlag";
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";

	public static final Integer USER_STATUS_NORMAL = 1; //正常
	public static final Integer USER_STATUS_STOP = 2;//禁用
	public static final Integer USER_STATUS_LOCK = 3;//锁定
	
	// 资源
	public static final String RESOURCE_STATUS_NORMAL = "0"; // 正常
	public static final String RESOURCE_STATUS_DISABLE = "1"; // 禁用
	public static final String RESOURCE_TYPE_MENU = "0"; // 菜单类型
	public static final String RESOURCE_TYPE_BUTTON = "1"; // 按钮类型
	public static final String RESOURCE_COMMON = "1"; // 公共资源

	// 用户
	public static final String SESSION_LOGIN_USER = "loginUser"; // session中的用户key
	public static final Long SUPER_ADMIN = 1L; // 超级管理员ID

	// 缓存key
	public static final String CACHE_SYS_RESOURCE = "sysResource_cache"; // 资源的缓存名称
	public static final String CACHE_SYS_OFFICE = "sysOffice_cache"; //机构的缓存名称
	public static final String CACHE_SYS_ROLE = "sysRole_cache"; //角色的缓存名称
	public static final String CACHE_SYS_USER = "sysUser_cache"; //用户缓存名称
	
	public static final String CACHE_ALL_RESOURCE = "allSysResource"; // 全部资源key
	public static final String CACHE_USER_RESOURCE = "userSysResource"; // 用户权限
	public static final String CACHE_USER_MENU = "userMenuTree"; // 用户菜单树
	public static final String CACHE_USER_ROLE = "userRole"; // 用户角色
	public static final String CACHE_USER_DATASCOPE = "userDataScope"; //用户数据范围
	public static final String CACHE_USER_OFFICE = "userOffice"; //用户机构

	// 显示/隐藏
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	// 是/否
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 数据库表默认的5个字段
	 */
	public static final List<String> DEFAULT_PROP = Lists.newArrayList("createDate","createBy",
			"updateDate","updateBy","delFlag");
	
	public static final Integer COMMON_FILE = 1;
	public static final Integer ATTACH_FILE = 2;
	public static final int TIME=24*60*60;
	
	/**
	 *附件表flag参数设置
	 */
	public static final String FILE_FLAG_ARTICLE = "article";//文档附件
	
	/**
	 *session存放
	 */
	public static final String STOCK_TRADE_PROGRESS = "stock_trade_progress";//单日交易批量文件上传进度条
	
}
