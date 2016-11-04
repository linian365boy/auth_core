package cn.rainier.nian.service.impl;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brightengold.common.vo.RequestParam;

import cn.rainier.nian.dao.MenuDao;
import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.service.MenuService;
import cn.rainier.nian.utils.PageRainier;

public class MenuServiceImpl implements MenuService {
	private MenuDao menuDao;
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	/**
	 * @FunName: findParentMenuByRole
	 * @Description:  根据所属角色查找能访问的一级菜单
	 * @param roles
	 * @param flag
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public List<Menu> findParentMenuByRole(Set<Role> roles,boolean flag) {
		List<Menu> parentM = null;
		if(!flag){//flag=false
			parentM =  menuDao.findParentMenuByRole(roles);
			for(Menu pare : parentM){
				pare.setChildren(menuDao.getChildldByParentAndRoles(pare.getId(),roles));
			}
		}else{//flag=true
			parentM = menuDao.findParentMenu();
		}
		return parentM;
	}
	/**
	 * @FunName: loadMenuByResourceId
	 * @Description:  查询资源所属的二级菜单
	 * @param resourceId
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public Menu loadMenuByResourceId(Integer resourceId) {
		return menuDao.loadMenuByResourceId(resourceId);
	}
	/**
	 * @FunName: loadMenuByUrl
	 * @Description:  根据URL判断所属的菜单模块
	 * @param url
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public Menu loadMenuByUrl(String url) {
		return menuDao.loadMenuByUrl(url);
	}
	
	/**
	 * @Description:菜单列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageRainier<Menu> findAll(RequestParam param) {
		long count = menuDao.findAllCount();
		PageRainier<Menu> page = new PageRainier<Menu>(count);
		page.setResult(menuDao.findList(param));
		return page;
	}
	@Override
	public void delMenu(Integer id) {
		menuDao.delete(id);
	}
	@Override
	public boolean saveMenu(Menu m) {
		try{
			menuDao.save(m);
			return true;
		}catch(Exception e){
			logger.error("新增菜单报错",e);
		}
		return false;
	}
	@Override
	public boolean updateMenu(Menu menu) {
		try{
			menuDao.update(menu);
			return true;
		}catch(Exception e){
			logger.error("修改菜单报错",e);
		}
		return false;
	}
	@Override
	public Menu loadMenuById(Integer id) {
		return menuDao.findOne(id);
	}
	@Override
	public List<Menu> findParentByAjax() {
		return menuDao.findParentByAjax();
	}
	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
}
