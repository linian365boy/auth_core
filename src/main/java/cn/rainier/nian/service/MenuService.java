package cn.rainier.nian.service;

import java.io.Serializable;
import java.util.List;

import cn.rainier.nian.dao.MenuDao;
import cn.rainier.nian.model.Menu;

public abstract class MenuService {
	private MenuDao menuDao;
	
	public void delMenu(Serializable id){
		menuDao.delete(id);
	}
	
	public Menu saveMenu(Menu m){
		return menuDao.save(m);
	}
	
	public Menu loadMenuById(Serializable id){
		return menuDao.findOne(id);
	}
	
	public List<Object[]> findParentByAjax(){
		return menuDao.findParentByAjax();
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
}
