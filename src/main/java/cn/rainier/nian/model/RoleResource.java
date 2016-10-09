package cn.rainier.nian.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName: RoleResource  
 * @Description: 角色资源关联表 
 * @date: 2016年10月10日 下午2:41:44 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class RoleResource implements Serializable {
	/** 
	 * serialVersionUID:序列化
	 * @since JDK 1.7 
	 */ 
	private static final long serialVersionUID = -7493214493982592311L;
	/**
	 * 角色主键
	 */
	private String roleId;
	/**
	 * 资源主键
	 */
	private Integer resourceId;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
