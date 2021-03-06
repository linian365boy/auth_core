package cn.rainier.nian.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightengold.common.vo.RequestParam;

import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Role;

/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.dao
 * @Author: ln
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
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	public List<Menu> findParentMenuByRole(@Param("roles") Collection<Role> roles);
	/**
	 * @FunName: getChildldByParentAndRoles
	 * @Description:  根据所属角色与父菜单查找子菜单
	 * @param pid
	 * @param roles
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	public List<Menu> getChildldByParentAndRoles(@Param("pid") Integer pid,@Param("roles") Collection<Role> roles);
	/**
	 * @FunName: findParentMenuByRole
	 * @Description:  查找所有父菜单，没有根据角色
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	public List<Menu> findParentMenu();
	/**
	 * @FunName: loadMenuByUrl
	 * @Description:  根据url查询菜单
	 * @param url
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-4-16
	 */
	public Menu loadMenuByUrl(String url);
	
	public List<Menu> findParentByAjax();
	
	public void delete(Integer id);
	
	public int save(Menu m);
	
	public Menu findOne(Integer id);
	
	public long findAllCount(RequestParam param);
	
	public List<Menu> findList(RequestParam param);
	
	/**
	 * @FunName: loadMenuByResourceId
	 * @Description:  查找权限资源所属的子菜单
	 * @param resourceId
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	public Menu loadMenuByResourceId(Integer resourceId);
	
	public void update(Menu menu);
	
	public void insertRoleMenu(@Param("roleName") String roleName,@Param("menus") List<Menu> menus);
	
	public void deleteRoleMenu(String roleName);
	
	public List<Menu> findMenuByRole(String roleName);
	
	public long findChildMenuCount(Integer menuId);
}

