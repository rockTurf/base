

package com.srj.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.beetl.utils.BeetlUtils;
import com.srj.common.constant.Constant;
import com.srj.common.utils.TreeUtils;
import com.srj.web.sys.mapper.SysResourceMapper;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysUser;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author
 */

@Service("sysResourceService")
public class SysResourceService {

	@Resource
	private SysResourceMapper sysResourceMapper;



	/**
	 * 根据用户id得到用户持有的菜单资源
	 * @param userId
	 * @return
	 */
	public List<SysResource> findUserMenuByUserId(SysUser sysUser) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", Constant.RESOURCE_TYPE_MENU);
		List<SysResource> reslist = new ArrayList<SysResource>();
		if (sysUser.isAdmin()) {
			reslist = sysResourceMapper.getAllResource(params);
		}else{
			params.put("userId", sysUser.getId());
			reslist = sysResourceMapper.findUserResourceByUserId(params);
		}
		return reslist;
	}
	
	/**
	 * 根据用户id得到用户持有的按钮资源
	 * @param userId
	 * @return
	 */
	public List<SysResource> findUserButtonByUserId(SysUser sysUser) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", Constant.RESOURCE_TYPE_BUTTON);
		List<SysResource> reslist = new ArrayList<SysResource>();
		if (sysUser.isAdmin()) {
			reslist = sysResourceMapper.getAllResource(params);
		}
		params.put("userId", sysUser.getId());
		reslist = sysResourceMapper.findUserResourceByUserId(params);
		return reslist;
	}

	/**
	 * 菜单管理分页显示筛选查询
	 * @param params {"name":"菜单名字","id":"菜单id"}
	 * @return
	 */
	public PageInfo<SysResource> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysResource> list = sysResourceMapper.findPageInfo(params);
		return new PageInfo<SysResource>(list);
	}


	/**
	 * 获取全部资源list形式
	 * @return
	 */
	public List<SysResource> getAllResourcesList() {
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysResource> reslist = sysResourceMapper.getAllResource(params);
		return reslist;
	}
	/**
	 * 获取菜单树
	 */
	public List<SysResource> getMenuTree(){
		List<SysResource> list = TreeUtils.toTreeNodeList(getAllResourcesList(),SysResource.class);
		return list;
	}

	/**
	 * 新增菜单栏
	 * */
	public Integer saveRecord(SysResource record) {
		return sysResourceMapper.insertSelective(record);
	}

}
