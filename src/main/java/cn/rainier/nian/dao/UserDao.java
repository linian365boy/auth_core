package cn.rainier.nian.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rainier.nian.model.User;
/**
 * @param <ID>
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
public interface UserDao {
	/**
	 * @FunName: findUserByRole
	 * @Description:  查询此角色下的所有用户
	 * @param roleId
	 * @return List<User>
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select distinct u from User u,Role g where u.id in elements(g.users) and g.id = ?") //有用的
	//@Query("select distinct u from User u,Role g where u in elements(g.users) and g.id = ?")		//有用的
	//@Query("select distinct u from User u join u.roles r where r.id = ? order by u.id desc")		//有用的
	public List<User> findUserByRole(String roleId);
	/**
	 * @FunName: findByName
	 * @Description:  通过用户名获得User对象
	 * @param username
	 * @return User
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select u from User u where u.username = :username")
	public User findByName(@Param("username") String username);
	/**
	 * @FunName: findUserByLike
	 * @Description:  模糊查询
	 * @param username
	 * @return List<User>
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select u from User u where u.username like :un")
	public List<User> findUserByLike(@Param("un") String username);
	/**
	 * @FunName: getPasswordById
	 * @Description:  得到密码（已经加密）
	 * @param id
	 * @return String
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	//@Query("select u.password from User u where u.id = ?")
	public String getPasswordById(Integer id);
	//@Modifying
	//@Query("update User set password = ?2 where username = ?1")
	public void changePassword(@Param("username") String username,@Param("password") String password);
	/**
	 * @FunName: unsubscribe
	 * @Description:  通过username注销用户
	 * @param username
	 * @Author: ln
	 * @CreateDate: 2013-5-8
	 */
	//@Modifying
	//@Query("update User set accountNonLocked = false,lastCloseDate=NOW() where username = ?1")
	public void unsubscribe(String username);
	/**
	 * @FunName: unsubscribe
	 * @Description:  通过User主键注销用户
	 * @param id
	 * @Author: ln
	 * @CreateDate: 2013-5-8
	 */
	//@Modifying
	//@Query("update User set accountNonLocked = false,lastCloseDate=NOW() where id = ?1")
	public void unsubscribe(Integer id);
	
	public void save(User model);
	
	public void delete(Integer id);
	
	public User findOne(Integer id);
	/**
	 * findAllCount:排除当前登录者，统计用户数
	 * @author tanfan 
	 * @param userId
	 * @return 
	 * @since JDK 1.7
	 */
	public long findAllCount(Integer userId);
	
	public List<User> findList(@Param("loginId") Integer userId,@Param("start") int start,@Param("pageSize") Integer pageSize);
	
}
