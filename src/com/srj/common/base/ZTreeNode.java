package com.srj.common.base;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srj.web.sys.model.SysResource;

/**
 * zTree菜单栏，工具类
 * */
public class ZTreeNode {

	private Long id;
	private Long pId; //父级id
	private String name; //名称
	private boolean open;//会否打开节点
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	/**
	 * 资源栏list转zTreeList 输出JSON格式（带选项框）
	 * menuList:树结构
	 * checkedList:已选择的节点id
	 * */
	public static JSONArray menu2zTree(List<SysResource> menuList,List<Long> checkedList) {
		JSONArray array = new JSONArray();
		//循环
		for(SysResource item:menuList) {
			JSONObject obj = new JSONObject();
			obj.put("id", item.getId());
			obj.put("pId",item.getParent_id());
			obj.put("name",item.getName());
			obj.put("open", true);
			
			//判断是否需要判断选中
			if(checkedList != null) {
				//判断是否选中
				if(checkedList.contains(item.getId())){
					obj.put("checked",true);
				}
			}
			
			array.add(obj);
		}
		
		return array;
	}
	
	/**
	 * 资源栏list转zTreeList 输出JSON格式（不带选项框）
	 * menuList:树结构
	 * */
	public static JSONArray menu2zMenuTree(List<SysResource> menuList) {
		JSONArray array = new JSONArray();
		//循环
		for(SysResource item:menuList) {
			JSONObject obj = new JSONObject();
			obj.put("id", item.getId());
			obj.put("pId",item.getParent_id());
			obj.put("name",item.getName());
			obj.put("open", true);
			array.add(obj);
		}
		return array;
	}
	
}
