package cn.rainier.nian.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import cn.rainier.nian.model.component.UserComponent;

@SuppressWarnings("deprecation")
@Entity
@Table(name="user")
@JsonIgnoreProperties("roles")
public class User extends IdEntity implements UserDetails{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2414711442165502235L;
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
	
	
	private UserComponent userComponent;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Transient
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
	
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	//关系维护端，负责多对多关系的绑定和解除
    //@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(Player)
    //inverseJoinColumns指定外键的名字，要关联的关系被维护端(Game)
    //其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为user_role
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即userId
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即roleId
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
	//@ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns=@JoinColumn(name="userId"),
				inverseJoinColumns=@JoinColumn(name="roleId")
			)
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
		return "id: "+this.id+" "+"name: "+this.username+" "+"realName: "+this.realName;
	}
	
	/**
	 * @FunName: getUserComponent
	 * @Description:  可扩展的组件
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	@Embedded
	public UserComponent getUserComponent() {
		return userComponent;
	}
	public void setUserComponent(UserComponent userComponent) {
		this.userComponent = userComponent;
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
	
	@Temporal(TemporalType.DATE)
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

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
}
