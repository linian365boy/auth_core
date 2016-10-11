package cn.rainier.nian.service.impl;

import java.util.List;

import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.model.Resource;
import cn.rainier.nian.service.ResourceService;

public class ResourceServiceImpl implements ResourceService{
	private ResourceDao resourceDao;
	/**
	 * 通过父菜单得到资源
	 * （通过二级菜单得到三级资源）
	 * 只获取可以显示的资源
	 */
	public List<Resource> findResourceByParentId(Integer menuId){
		return resourceDao.findResourceByParentId(menuId);
	}
	/**
	 * @FunName: findAllResourceByParentId
	 * @Description:  获取全部子资源
	 * @param menuId
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-6-5
	 */
	public List<Resource> findAllResourceByParentId(Integer menuId){
		return resourceDao.findAllResourceByParentId(menuId);
	}
	/**
	 * 通过角色拿到所有能访问的资源
	 */
	public List<Resource> findResourceByRole(String name) {
		return resourceDao.findResourceByRole(name);
	}
	@Override
	public Resource loadResourceByResource(Integer id) {
		return resourceDao.loadResourceByResourceId(id);
	}
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}
