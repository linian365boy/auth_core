package cn.rainier.nian.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2414711442165502235L;
	private Integer id;
	private String realName;			//真实姓名
	private String username;			//用户名
	private String email;				//邮箱
	private String password;			//密码
	private boolean enabled;			//账号是否可用  true为可用，false不可用
	private boolean accountNonLocked;	//账号是否被锁！true为没锁，false为已锁
	private Date lastCloseDate;			//最近一次禁用或者注销时间
	
	/**
	 * 所属角色
	 */
	private Set<Role> roles;	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@SuppressWarnings("deprecation")
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();
		if(roles!=null){
			for(Role r:roles){
				ga.add(new GrantedAuthorityImpl(r.getName()));
			}
		}
		return ga;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}
	
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User){
			User u = (User)obj;
			return (u.id==id)?true:false;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id.intValue();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public Date getLastCloseDate() {
		return lastCloseDate;
	}
	public void setLastCloseDate(Date lastCloseDate) {
		this.lastCloseDate = lastCloseDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
}
