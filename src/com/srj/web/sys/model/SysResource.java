

package com.srj.web.sys.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import com.srj.common.base.BaseEntity;


/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
@Table(name="sys_resource")
public class SysResource extends BaseEntity {

	private static final long serialVersionUID = 1L;

    private String icon; //icon <图标>

    private String name; //name <资源名称>

    private Long parent_id; //parent_id <父级id>

    private Integer sort; //sort <排序号>

    private String type; //type <类型(0.菜单 1.按钮)>

    private String url; //url <链接>
    
    private String delFlag; //del_flag <删除标记(0.正常  1.删除)>
    
	public String getIcon() {
		return this.getString("icon");
    }
   
    public void setIcon(String icon) {
		this.set("icon", icon);
    }

	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
		this.set("name", name);
    }

	

	public Long getParent_id() {
		return this.getLong("parentId");
	}

	public void setParent_id(Long parent_id) {
		this.set("parent_id", parent_id);
	}

	public Integer getSort() {
		return this.getInteger("sort");
    }
   
    public void setSort(Integer sort) {
		this.set("sort", sort);
    }

	public String getType() {
		return this.getString("type");
    }
   
    public void setType(String type) {
		this.set("type", type);
    }

	public String getUrl() {
		return this.getString("url");
    }
   
    public void setUrl(String url) {
		this.set("url", url);
    }
    
    public String getDelFlag() {
		return this.getString("delFlag");
    }
   
    public void setDelFlag(String delFlag) {
		this.set("delFlag", delFlag);
    }

}
