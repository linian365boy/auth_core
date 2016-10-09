package cn.rainier.nian.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Role;

/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.dao
 * @Author: 李年
 * @Create Date: 2013-3-28
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public interface MenuDao{
	/**
	 * @FunName: findParentMenuByRole
	 * @Description:  根据所属角色查找能访问的一级菜单
	 * @param roles
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select distinct m from Menu m join m.roles r where m.parentMenu = null and r in ?1")
	public List<Menu> findParentMenuByRole(Collection<Role> roles);
	/**
	 * @FunName: getChildldByParentAndRoles
	 * @Description:  根据所属角色与父菜单查找子菜单
	 * @param pid
	 * @param roles
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select distinct m from Menu m join m.roles r where m.parentMenu.id = ?1 and r in ?2")
	public List<Menu> getChildldByParentAndRoles(Serializable pid, Collection<Role> roles);
	/**
	 * @FunName: loadMenuByResourceId
	 * @Description:  查找权限资源所属的子菜单
	 * @param resourceId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select m from Resource r join r.menu m where r.id = ?")
	public Menu loadMenuByResourceId(Serializable resourceId);
	/**
	 * @FunName: findParentMenuByRole
	 * @Description:  查找所有父菜单，没有根据角色
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select distinct m from Menu m where m.parentMenu = null")
	public List<Menu> findParentMenuByRole();
	/**
	 * @FunName: loadMenuByUrl
	 * @Description:  根据url查询菜单
	 * @param url
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-4-16
	 */
	//@Query("select m from Menu m where m.url like ?1")
	public Menu loadMenuByUrl(String url);
	//@Query("select id,name from Menu m where m.parentMenu = null")
	public List<Object[]> findParentByAjax();
	
	public void delete(Serializable id);
	
	public Menu save(Menu m);
	
	public Menu findOne(Serializable id);
	
	public long findAllCount();
	
	public List<Menu> findList(Integer pageNo, Integer pageSize);
}

