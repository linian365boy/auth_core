package cn.rainier.nian.dao;

import java.io.Serializable;
import java.util.List;

import cn.rainier.nian.model.Role;
import cn.rainier.nian.model.User;

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
public interface RoleDao {
		/**
		 * @FunName: findUserByRole
		 * @Description:  查询此角色下的所有用户
		 * @param roleId
		 * @return List<User>
		 * @Author: 李年
		 * @CreateDate: 2013-3-28
		 */
		//@Query("select distinct u from User u,Role g where u.id in elements(g.users) and g.id = ?") //有用的
		//@Query("select distinct u from User u,Role g where u in elements(g.users) and g.id = ?")		//有用的
		//@Query("select distinct u from User u join u.roles r where r.id = ? order by u.id desc")		//有用的
		public List<User> findUserByRole(Serializable roleId);
		/**
		 * @FunName: finAllByAjax
		 * @Description:  ajax异步拿到所有角色的name与desc属性
		 * @return List<Object[]>
		 * @Author: 李年
		 * @CreateDate: 2013-3-28
		 */
		//@Query("select r.name,r.desc from Role r where r.defaultOrNo = false")
		public List<Object[]> finAllByAjax();
		/**
		 * @FunName: findRoleByUser
		 * @Description:  查找此用户所在的角色
		 * @param userId
		 * @return
		 * @Author: 李年
		 * @CreateDate: 2013-3-28
		 */
		//@Query("select r from Role r join r.users u where u.id =?")
		public List<Role> findRoleByUser(Serializable userId);
		/**
		 * @FunName: findDefaultRole
		 * @Description:  查找默认的角色l，默认的角色只有一个！
		 * @return
		 * @Author: 李年
		 * @CreateDate: 2013-4-7
		 */
		//@Query("select r from Role r where r.defaultOrNo = true")
		public Role findDefaultRole();
		
		public Role findOne(String roleName);
		
		public Role save(Role role);
		
		public void delete(Role role);
}
