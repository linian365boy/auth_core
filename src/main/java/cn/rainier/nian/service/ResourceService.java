package cn.rainier.nian.service;

import java.util.List;

import cn.rainier.nian.model.Resource;

public interface ResourceService {
	/**
	 * @FunName: findResourceByParentId
	 * @Description:  根据二级菜单查询资源,只获取能够显示的资源
	 * @param menuId
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public List<Resource> findResourceByParentId(Integer menuId);
	/**
	 * @FunName: findResourceByParentId
	 * @Description:  根据二级菜单查询全部资源
	 * @param menuId
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public List<Resource> findAllResourceByParentId(Integer menuId);
	/**
	 * @FunName: findResourceByRole
	 * @Description:  根据角色查看能访问的资源
	 * @param name
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public List<Resource> findResourceByRole(String name);
	
	/**
	 * @FunName: loadResourceByResource
	 * @Description:  根据资源Id查询资源对象
	 * @param id
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public Resource loadResourceByResource(Integer id);
}
