package cn.rainier.nian.service.impl;

import java.util.List;

import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Resource;
import cn.rainier.nian.service.ResourceService;
public class ResourceServiceImpl extends ResourceService{
	/**
	 * 通过父菜单得到资源
	 * （通过二级菜单得到三级资源）
	 * 只获取可以显示的资源
	 */
	public List<Resource> findResourceByParentId(Long menuId){
		return this.getResourceDao().findResourceByParentId(menuId);
	}
	/**
	 * @FunName: findAllResourceByParentId
	 * @Description:  获取全部子资源
	 * @param menuId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-6-5
	 */
	public List<Resource> findAllResourceByParentId(Long menuId){
		return this.getResourceDao().findAllResourceByParentId(menuId);
	}
	/**
	 * 通过角色拿到所有能访问的资源
	 */
	public List<Resource> findResourceByRole(String name) {
		return this.getResourceDao().findResourceByRole(name);
	}
	/**
	 * @FunName: loadMenuByResourceId
	 * @Description:  查找权限资源所属的二级菜单
	 * @param resourceId 资源Id
	 * @return 所属的二级菜单
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Menu loadMenuByResourceId(Long resourceId){
		return this.getMenuDao().loadMenuByResourceId(resourceId);
	}
}
