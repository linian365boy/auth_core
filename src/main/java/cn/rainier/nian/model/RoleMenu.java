package cn.rainier.nian.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName: RoleMenu  
 * @Description: 角色菜单关联表 
 * @date: 2016年10月10日 下午2:31:28 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class RoleMenu implements Serializable {
	/** 
	 * serialVersionUID:序列化
	 * @since JDK 1.7 
	 */ 
	private static final long serialVersionUID = -9198332864530933873L;
	/**
	 * 角色主键
	 */
	private String roleId;
	/**
	 * 菜单主键
	 */
	private Integer menuId;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
