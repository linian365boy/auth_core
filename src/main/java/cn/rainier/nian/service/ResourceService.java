package cn.rainier.nian.service;

import java.util.List;

import cn.rainier.nian.dao.MenuDao;
import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.model.Resource;

public abstract class ResourceService {
	
	private ResourceDao resourceDao;
	private MenuDao menuDao;
	/**
	 * @FunName: findResourceByParentId
	 * @Description:  根据二级菜单查询资源,只获取能够显示的资源
	 * @param menuId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public List<Resource> findResourceByParentId(Long menuId){
		return resourceDao.findResourceByParentId(menuId);
	}
	/**
	 * @FunName: findResourceByParentId
	 * @Description:  根据二级菜单查询全部资源
	 * @param menuId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public List<Resource> findAllResourceByParentId(Long menuId){
		return resourceDao.findAllResourceByParentId(menuId);
	}
	/**
	 * @FunName: findResourceByRole
	 * @Description:  根据角色查看能访问的资源
	 * @param name
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public List<Resource> findResourceByRole(String name) {
		return resourceDao.findResourceByRole(name);
	}
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	/**
	 * @FunName: loadResourceByResource
	 * @Description:  根据资源Id查询资源对象
	 * @param id
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Resource loadResourceByResource(Long id){
		return this.getResourceDao().loadResourceByResourceId(id,menuDao.findOne(id));
	}
	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
}
