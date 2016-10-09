package cn.rainier.nian.service;

import java.io.Serializable;
import java.util.List;

import cn.rainier.nian.dao.RoleDao;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.model.User;

public abstract class RoleService {
	private RoleDao roleDao;
	/**
	 * @FunName: loadRoleByName
	 * @Description:  根据角色名获取角色信息
	 * @param roleName
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Role loadRoleByName(String roleName){
		return roleDao.findOne(roleName);
	}
	/**
	 * @FunName: saveRole
	 * @Description:  保存角色
	 * @param role
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Role saveRole(Role role){
		try {
			Role r = roleDao.save(role);
			if(r!=null){
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @FunName: delRole
	 * @Description:  删除角色
	 * @param role
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public void delRole(Role role){
		try {
			roleDao.delete(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @FunName: findAllSpecification
	 * @Description:  自定义的Specification
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	/*public Specification<Role> findAllSpecification(){
		return new Specification<Role>(){
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("defaultOrNo"), false);
			}};
	}*/
	/**
	 * @FunName: findAll
	 * @Description:  查询全部角色，角色列表
	 * @param pageNo
	 * @param pageSize
	 * @param flag
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	/*public PageRainier<Role> findAll(Integer pageNo,Integer pageSize,boolean flag){
		PageRainier<Role> page = null;
		if(flag){
			Specification<Role> rs = findAllSpecification();
			Page<Role> tempPage = roleDao.findAll(rs,new PageRequest(pageNo-1,pageSize,new Sort(Direction.DESC, "createDate")));
			List<Role> roles = tempPage.getContent();
			page = new PageRainier<Role>(tempPage.getTotalElements(),pageNo,pageSize);
			page.setResult(roles);
			return page;
		}else{
			page = new PageRainier<Role>();
			page.setResult(roleDao.findAll(new Sort(Direction.DESC, "createDate")));
			return page;
		}
	}*/
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	/**
	 * @FunName: findRoleByUser
	 * @Description:  查看此用户的所有角色
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Role> findRoleByUser(User u){
		return roleDao.findRoleByUser(u.getId());
	}
	/**
	 * @FunName: findDefault
	 * @Description:  查询默认的角色，只有一个！
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public Role findDefault(){
		return roleDao.findDefaultRole();
	}
	/**
	 * @FunName: findUserByRole
	 * @Description:  查询某角色下的用户对象
	 * @param roleId
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public List<User> findUserByRole(Serializable roleId){
		return roleDao.findUserByRole(roleId);
	}
}
