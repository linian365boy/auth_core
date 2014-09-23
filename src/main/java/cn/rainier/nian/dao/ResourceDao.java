package cn.rainier.nian.dao;

import java.util.List;

import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Resource;

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
public interface ResourceDao {
	/**
	 * @FunName: getAllResource
	 * @Description:  拿到所有资源
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> getAllResource();
	/**
	 * @FunName: findResourceByParentId
	 * @Description:  通过二级菜单获取三级可以显示的资源
	 * @param menuId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> findResourceByParentId(Long menuId);
	/**
	 * @FunName: findResourceByParentId
	 * @Description:  通过二级菜单获取全部三级资源
	 * @param menuId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> findAllResourceByParentId(Long menuId);
	/**
	 * @FunName: findResourceByRole
	 * @Description:  通过角色拿到所有资源
	 * @param name
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> findResourceByRole(String name);
	/**
	 * @FunName: loadResourceByResourceId
	 * @Description:  查找权限菜单所属的子菜单
	 * @param id
	 * @param menu
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public Resource loadResourceByResourceId(Long id,Menu menu);
}
