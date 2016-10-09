package cn.rainier.nian.service;

import java.util.List;
import cn.rainier.nian.model.Menu;

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
}
