package cn.rainier.nian.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.dao.UserDao;
import cn.rainier.nian.model.Resource;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.model.User;
import cn.rainier.nian.service.OutCSVForUser;
import cn.rainier.nian.service.UserService;
import cn.rainier.nian.utils.PageRainier;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private UserCache userCache = new NullUserCache();
	private OutCSVForUser outCSV ;
	@Autowired
	private ResourceDao resourceDao ;
	/**
	 * 根据用户名查询用户，用户名唯一
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User user =  this.loadUserByName(username);
		return user;
	}
	/**
	 * 查询用户列表，根据Id排序，降序
	 * @param userId 
	 */
	public PageRainier<User> findAllUser(Integer pageNo,Integer pageSize,Long userId, boolean flag) {
		PageRainier<User> page = null;
		if(flag){
			Page<User> tempPage = userDao.findAll(userSpecification(userId),new PageRequest(pageNo-1, pageSize,new Sort(Direction.DESC, "id")));
			page = new PageRainier<User>(tempPage.getTotalElements(), pageNo, pageSize);
			List<User> users = tempPage.getContent();
			page.setResult(users);
		}else{
			page = new PageRainier<User>();
			page.setResult(userDao.findAll(new Sort(Direction.ASC, "id")));
		}
		return page;
	}
	
	private Specification<User> userSpecification(final Long id){
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.notEqual(root.get("id"), id);
			}
		};
	}
	
	public User loadUserByName(String userid) {
		return userDao.findByName(userid);
	}
	
	/**
	 * 保存用户
	 */
	public User saveUser(User model) {
		try{
			if(model.getId()==null){	//新增用户
				model.setPassword(new Md5PasswordEncoder().encodePassword(model.getPassword(), null));
			}
			User u =  userDao.save(model);
			if(u!=null){
				return u;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除用户
	 */
	public void deleteUser(User model) {
		try{
			userDao.delete(model);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 根据Id删除用户
	 */
	public void deleteUserById(Serializable id) {
		try {
			userDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据Id删除密码
	 */
	public String getPaswordById(Serializable id) {
		return userDao.getPasswordById(id);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 批量添加
	 */
	public List<User> batchAdd(List<User> lists){
		return userDao.save(lists);
	}
	
	/**
	 * 批量删除
	 */
	public void batchDel(List<User> users){
		userDao.deleteInBatch(users);
	}
	
	public Long findCount() {
		return userDao.count();
	}
	/**
	 * 模糊查询用户
	 */
	public PageRainier<User> findUserByLike(Specification<User> speci,String field,String condition,Integer pageNo,Integer pageSize){
		Page<User> tempPage = userDao.findAll(speci, 
				new PageRequest(pageNo-1,pageSize,new Sort(Direction.DESC,"id")));
		PageRainier<User> page = new PageRainier<User>(tempPage.getTotalElements(),pageNo,pageSize);
		page.setResult(tempPage.getContent());
		return page;
	}
	
	private Specification<User> findUserByRoleLikeSpeci(final String role){
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Role,String> join = root.joinList("roles");
				//root.fetch("name");
				return cb.like(join.<String>get("desc"), role);
			}
		};
	}
	/**
	 * 根据角色模糊查询用户
	 */
	public PageRainier<User> findUserByRoleLike(final String role,Integer pageNo,Integer pageSize){
		Specification<User> speci = findUserByRoleLikeSpeci(role);
		Page<User> tempPage = userDao.findAll(speci, new PageRequest(pageNo-1,pageSize));
		PageRainier<User> page = new PageRainier<User>(tempPage.getTotalElements(),pageNo,pageSize);
		page.setResult(tempPage.getContent());
		return page;
	}
	/**
	 * @FunName: changePassword
	 * @Description:  修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param currentUser 当前用户
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public void changePassword(String oldPassword, String newPassword, Authentication currentUser){
		currentUser = SecurityContextHolder.getContext().getAuthentication();
		if(currentUser ==null){
			throw new AccessDeniedException("修改密码错误，不存在此用户！");
		}
		String username = currentUser.getName();
		userDao.changePassword(username,newPassword);
		SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(currentUser,newPassword));
		userCache.removeUserFromCache(username);
	}
	
	protected Authentication createNewAuthentication(Authentication currentAuth, String newPassword) {
        UserDetails user = loadUserByUsername(currentAuth.getName());
        UsernamePasswordAuthenticationToken newAuthentication =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());
        return newAuthentication;
    }
	
	public UserCache getUserCache() {
		return userCache;
	}
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}
	/**
	 * @FunName: resetPassword
	 * @Description:  重置密码
	 * @param username
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public void resetPassword(String username){
		userDao.changePassword(username, new Md5PasswordEncoder().encodePassword(username,null));
	}

	public User loadUserById(Serializable id) {
		return userDao.findOne(id);
	}
	/**
	 * 注销用户
	 */
	public void unsubscribe(User model) {
		userDao.unsubscribe(model.getUsername());
	}
	/**
	 * 注销用户
	 */
	public void unsubscribe(Serializable id) {
		userDao.unsubscribe(id);
	}
	/**
	 * 导出csv文件中
	 */
	public void exportToCSV(List<User> users, String fileName,
			String[] headers, HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			outCSV.exportCSV(headers, users, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	public OutCSVForUser getOutCSV() {
		return outCSV;
	}
	public void setOutCSV(OutCSVForUser outCSV) {
		this.outCSV = outCSV;
	}
	
	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		//List<Resource> resources = resourceDao.findResourceByRole(user.getRoles());
		List<Resource> resources = null;
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resource res : resources) {
			// TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			// 关联代码：applicationContext-security.xml
			// 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getRes_string()));
		}
		return authSet;
	}
}
