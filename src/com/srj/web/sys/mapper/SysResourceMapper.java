

package com.srj.web.sys.mapper;


import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysResource;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

public interface SysResourceMapper extends Mapper<SysResource>{
	
	public List<SysResource> findPageInfo(Map<String, Object> params);
	
	//根据userId获得持有的权限
	public List<SysResource> getAllResource(Map<String, Object> params);

	public List<SysResource> findUserResourceByUserId(Map<String, Object> params);
	
}
