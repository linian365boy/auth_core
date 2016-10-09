package cn.rainier.nian.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.service.MenuService;
import cn.rainier.nian.utils.PageRainier;

public class MenuServiceImpl extends MenuService {
	/**
	 * @FunName: findParentMenuByRole
	 * @Description:  根据所属角色查找能访问的一级菜单
	 * @param roles
	 * @param flag
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public List<Menu> findParentMenuByRole(Set<Role> roles,boolean flag) {
		List<Menu> parentM = null;
		if(!flag){//flag=false
			parentM =  this.getMenuDao().findParentMenuByRole(roles);
			for(Menu pare : parentM){
				//pare.setChildren(getChildldByParentAndRoles(pare.getId(),roles));
			}
		}else{//flag=true
			parentM = this.getMenuDao().findParentMenuByRole();
		}
		return parentM;
	}
	/**
	 * @FunName: getChildldByParentAndRoles
	 * @Description:  根据角色与一级菜单，查询能访问的二级菜单
	 * @param id
	 * @param roles
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	private List<Menu> getChildldByParentAndRoles(Serializable id, Set<Role> roles) {
		return this.getMenuDao().getChildldByParentAndRoles(id,roles);
	}
	/**
	 * @FunName: loadMenuByResourceId
	 * @Description:  查询资源所属的二级菜单
	 * @param resourceId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Menu loadMenuByResourceId(Long resourceId) {
		return this.getMenuDao().loadMenuByResourceId(resourceId);
	}
	/**
	 * @FunName: loadMenuByUrl
	 * @Description:  根据URL判断所属的菜单模块
	 * @param url
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Menu loadMenuByUrl(String url) {
		return this.getMenuDao().loadMenuByUrl(url);
	}
	
	/**
	 * @Description:菜单列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageRainier<Menu> findAll(Integer pageNo, Integer pageSize) {
		//Page<Menu> tempPage = this.getMenuDao().findAll(new PageRequest(pageNo-1,pageSize,new Sort(Direction.DESC,"id","priority")));
		long count = this.getMenuDao().findAllCount();
		PageRainier<Menu> page = new PageRainier<Menu>(count);
		page.setResult(this.getMenuDao().findList(pageNo,pageSize));
		return page;
	}
}
