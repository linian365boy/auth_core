package cn.rainier.nian.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.rainier.nian.model.component.RoleComponent;

@Entity
@Table(name="ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = -3498056750436845009L;
	private String name;				//角色名
	private String desc;				//角色描述
	@Deprecated
	private String marking;				//角色标识，自动生成，没有意义，可废弃
	private boolean defaultOrNo;		//是否默认角色
	private Date createDate;			//创建角色日期
	private List<User> users;			//角色下用户
	private List<Resource> resources;	//角色能操作的资源
	private List<Menu> menus;			//角色能展现的菜单
	
	
	//可扩展的组件
	private RoleComponent roleComponent;
	
	public Role(){
	}
	
	public Role(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	@Id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="describes")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	//只需要设置mappedBy="games"表明role实体是关系被维护端就可以了
    //级联保存、级联删除等之类的属性在多对多关系中是不需要设置
    //不能说删了角色,把用户也删掉,用户还可以有其他的角色
	@ManyToMany(mappedBy="roles")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinTable(name="role_resource",
				joinColumns=@JoinColumn(name="roleId"),
				inverseJoinColumns=@JoinColumn(name="resourceId")
			)
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	@Override
	public String toString() {
		return "name:"+name+",describle:"+desc;
	}
	@Deprecated
	public String getMarking() {
		return marking;
	}
	@Deprecated
	public void setMarking(String marking) {
		this.marking = marking;
	}
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(name="role_menu",
				joinColumns=@JoinColumn(name="roleId"),
				inverseJoinColumns=@JoinColumn(name="menuId")
			)
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Embedded
	public RoleComponent getRoleComponent() {
		return roleComponent;
	}
	public void setRoleComponent(RoleComponent roleComponent) {
		this.roleComponent = roleComponent;
	}

	public boolean isDefaultOrNo() {
		return defaultOrNo;
	}
	public void setDefaultOrNo(boolean defaultOrNo) {
		this.defaultOrNo = defaultOrNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
