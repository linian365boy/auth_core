package cn.rainier.nian.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName: UserRole  
 * @Description: 用户角色关联表 
 * @date: 2016年10月10日 下午2:42:27 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class UserRole implements Serializable {
	/** 
	 * serialVersionUID:用户角色关联表
	 * @since JDK 1.7 
	 */ 
	private static final long serialVersionUID = 2359628642336504695L;
	/**
	 * 用户主键
	 */
	private Integer userId;
	/**
	 * 角色主键
	 */
	private String roleId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
