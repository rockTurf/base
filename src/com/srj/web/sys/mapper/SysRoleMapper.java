package com.srj.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysRole;



public interface SysRoleMapper extends Mapper<SysRole>{
	
	public List<SysRole> findPageInfo(Map<String, Object> params);

	public List<SysRole> getAllRole();

	public List<Long> getRoleResourceById(Long id);
	
}