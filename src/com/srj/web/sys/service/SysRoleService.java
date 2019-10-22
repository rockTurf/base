package com.srj.web.sys.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.sys.mapper.SysRoleMapper;
import com.srj.web.sys.model.SysRole;

@Service("sysRoleService")
public class SysRoleService {

	@Autowired
	public SysRoleMapper sysRoleMapper;

	//分页显示角色列表
	public PageInfo<SysRole> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysRole> list = sysRoleMapper.findPageInfo(params);
		return new PageInfo<SysRole>(list);
	}
	//所有角色名
	public List<SysRole> getAllRole() {
		return sysRoleMapper.getAllRole();
	}
	//新增角色
	public int addRole(SysRole record) {
		return sysRoleMapper.insertSelective(record);
	}
	//修改角色
	public int editRole(SysRole record) {
		return sysRoleMapper.updateByPrimaryKey(record);
	}
	//根据id取出角色信息
	public SysRole getRoleById(Long id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}
	//返回已拥有的权限信息
	public List<Long> getRoleResourceById(Long id) {
		return sysRoleMapper.getRoleResourceById(id);
	}


	//保存角色的权限
	public int saveRoleResource(Long id,String resIds) {
		//先把逗号分隔为list
		List<String> reslist = Arrays.asList(resIds.split(","));
		//添加前先删除原先的权限
		sysRoleMapper.deleteRoleResByRoleId(id);
		//再添加新权限
		return sysRoleMapper.insertRoleResource(id,reslist);
	}
}
