package cn.rainier.nian.dao;

import java.util.List;
import cn.rainier.nian.model.Role;

/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.dao
 * @Author: ln
 * @Create Date: 2013-3-28
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public interface RoleDao {
		/**
		 * @FunName: finAllByAjax
		 * @Description:  ajax异步拿到所有角色的name与desc属性
		 * @return List<Object[]>
		 * @Author: ln
		 * @CreateDate: 2013-3-28
		 */
		//@Query("select r.name,r.desc from Role r where r.defaultOrNo = false")
		public List<Role> finAllByAjax();
		/**
		 * @FunName: findRoleByUser
		 * @Description:  查找此用户所在的角色
		 * @param userId
		 * @return
		 * @Author: ln
		 * @CreateDate: 2013-3-28
		 */
		//@Query("select r from Role r join r.users u where u.id =?")
		public List<Role> findRoleByUser(Integer userId);
		/**
		 * @FunName: findDefaultRole
		 * @Description:  查找默认的角色l，默认的角色只有一个！
		 * @return
		 * @Author: ln
		 * @CreateDate: 2013-4-7
		 */
		//@Query("select r from Role r where r.defaultOrNo = true")
		public Role findDefaultRole();
		
		public Role findOne(String roleName);
		
		public void save(Role role);
		
		public void delete(String roleId);
}
