package cn.rainier.nian.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.rainier.nian.model.component.MenuComponent;

@Entity
@Table(name="MENU")
public class Menu extends IdEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;			//菜单名，展示在菜单栏上的信息
	private String mark;			//权限分配时显示的字，可能跟name一致，也可能不一致，只是为了分配权限时看得明白，清楚。
	private String url;				//点击菜单的链接
	private Integer priority;		//菜单展示的优先级
	private Menu parentMenu;		//父菜单
	private List<Menu> children;	//父菜单下的所有子菜单
	private List<Role> roles;		//能操作此菜单的所有角色
	
	//扩展使用
	private MenuComponent menuComponent;
	
	public Menu(){
	}
	
	public Menu(String name,boolean leafOrNo,String url,Integer priority){
		this.name = name;
		this.url = url;
		this.priority = priority;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH })
	@JoinColumn(name="pid")
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	@OneToMany(mappedBy="parentMenu",cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.REMOVE})
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@ManyToMany(mappedBy="menus",cascade=CascadeType.PERSIST)
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "id："+this.id+"name："+this.name+"parentMenu：";
	}
	@Embedded
	public MenuComponent getMenuComponent() {
		return menuComponent;
	}

	public void setMenuComponent(MenuComponent menuComponent) {
		this.menuComponent = menuComponent;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}
