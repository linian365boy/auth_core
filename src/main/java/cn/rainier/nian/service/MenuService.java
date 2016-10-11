package cn.rainier.nian.service;

import java.util.List;
import java.util.Set;

import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.utils.PageRainier;

public interface MenuService {
	
	public void delMenu(Integer id);
	
	public boolean saveMenu(Menu m);
	
	public Menu loadMenuById(Integer id);
	
	public List<Menu> findParentByAjax();

	/**
	 * @FunName: loadMenuByResourceId
	 * @Description:  查找权限资源所属的二级菜单
	 * @param resourceId 资源Id
	 * @return 所属的二级菜单
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public Menu loadMenuByResourceId(Integer resourceId);
	/**
	 * findAll:分页查询菜单 
	 * @author tanfan 
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 * @since JDK 1.7
	 */
	public PageRainier<Menu> findAll(Integer pageNo, Integer pageSize);
	
	/**
	 * findParentMenuByRole:根据角色查第一级菜单
	 * @author tanfan 
	 * @param roles
	 * @param flag
	 * @return 
	 * @since JDK 1.7
	 */
	public List<Menu> findParentMenuByRole(Set<Role> roles, boolean flag);
}
