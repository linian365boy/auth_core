package cn.rainier.nian.dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.rainier.nian.model.User;
import cn.rainier.nian.dao.base.*;
/**
 * @param <ID>
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
public interface UserDao extends AbstractDao<User,Serializable>{
	/**
	 * @FunName: findByName
	 * @Description:  通过用户名获得User对象
	 * @param username
	 * @return User
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	@Query("select u from User u where u.username = :username")
	public User findByName(@Param("username") String username);
	/**
	 * @FunName: findUserByLike
	 * @Description:  模糊查询
	 * @param username
	 * @return List<User>
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	@Query("select u from User u where u.username like :un")
	public List<User> findUserByLike(@Param("un") String username);
	/**
	 * @FunName: getPasswordById
	 * @Description:  得到密码（已经加密）
	 * @param id
	 * @return String
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	@Query("select u.password from User u where u.id = ?")
	public String getPasswordById(Serializable id);
	@Modifying
	@Query("update User set password = ?2 where username = ?1")
	public void changePassword(String username,String password);
	/**
	 * @FunName: unsubscribe
	 * @Description:  通过username注销用户
	 * @param username
	 * @Author: 李年
	 * @CreateDate: 2013-5-8
	 */
	@Modifying
	@Query("update User set accountNonLocked = false,lastCloseDate=NOW() where username = ?1")
	public void unsubscribe(String username);
	/**
	 * @FunName: unsubscribe
	 * @Description:  通过User主键注销用户
	 * @param id
	 * @Author: 李年
	 * @CreateDate: 2013-5-8
	 */
	@Modifying
	@Query("update User set accountNonLocked = false,lastCloseDate=NOW() where id = ?1")
	public void unsubscribe(Serializable id);
}
