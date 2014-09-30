package cn.rainier.nian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Resource;
import cn.rainier.nian.model.Role;

public class ResourceDaoImpl extends JdbcDaoSupport implements ResourceDao{
	private DataSource ds;
	public void setDs(DataSource dataSource){
		super.setDataSource(dataSource);
	}
	/**
	 * @FunName: getAllResource
	 * @Description:  拿到所有资源
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> getAllResource(){
		List<Resource> list = null;
		String sql = "select * from resource order by priority desc";
		list = this.getJdbcTemplate().query(sql,new RowMapper<Resource>(){
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource rss = new Resource();
				rss.setId(rs.getLong("id"));
				rss.setName(rs.getString("name"));
				rss.setRes_type(rs.getString("res_type"));
				rss.setRes_string(rs.getString("res_string"));
				rss.setRoles(getRoleByResourceId(rss.getId()));
				return rss;
			}
		});
		return list;
	}
	
	private List<Role> getRoleByResourceId(Long id){
		String sql = "select r.name from role r,resource re,role_resource rr where " +
				"r.name = rr.roleId and re.id = rr.resourceId and re.id = ?";
		List<Role> roles = this.getJdbcTemplate().query(sql, new Long[]{id}, 
				new RowMapper<Role>(){
					public Role mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Role role = new Role();
						role.setName(rs.getString("name"));
						return role;
					}
		});
		return roles;
	}
	/**
	 * @FunName: findResourceByRole
	 * @Description:  通过角色拿到可以显示的资源，授权时有用
	 * @param name
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> findResourceByParentId(Long menuId) {
		String sql = "select r.name,r.descn from resource r where r.menuId = ? and r.display is true order by priority desc";
		return this.getJdbcTemplate().query(sql,new Long[]{menuId},new RowMapper<Resource>(){
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource rss = new Resource();
				rss.setId(rs.getLong("id"));
				rss.setName(rs.getString("name"));
				rss.setDescn(rs.getString("descn"));
				return rss;
			}
		});
	}
	/**
	 * @FunName: findResourceByRole
	 * @Description:  通过角色拿到所有资源
	 * @param name
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> findAllResourceByParentId(Long menuId) {
		String sql = "select r.id,r.name,r.descn from resource r where r.menuId = ? order by priority desc";
		return this.getJdbcTemplate().query(sql,new Long[]{menuId},new RowMapper<Resource>(){
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource rss = new Resource();
				rss.setId(rs.getLong("id"));
				rss.setName(rs.getString("name"));
				rss.setDescn(rs.getString("descn"));
				return rss;
			}
		});
	}
	/**
	 * @FunName: findResourceByRole
	 * @Description:  通过角色拿到所有资源
	 * @param name
	 * @return
	 * @Author: 李年
	 * @CreateDate: 2013-3-28
	 */
	public List<Resource> findResourceByRole(String name) {
		String sql = "select rr.resourceId from role_resource rr where rr.roleId = ?";
		return this.getJdbcTemplate().query(sql, new String[]{name},new RowMapper<Resource>(){
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource res = new Resource();
				res.setId(rs.getLong("resourceId"));
				res.setMenu(loadMenuByResourceId(res.getId()));
				return res;
			}
		});
	}
	
	public Menu loadMenuByResourceId(Long id){
		String sql = "select menuId from resource r where r.id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Long[]{id}, new RowMapper<Menu>(){

			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Menu m = new Menu();
				m.setId(rs.getLong("menuId"));
				return m;
			}
			
		});
	}

	public Resource loadResourceByResourceId(Long id, final Menu menu) {
		String sql = "select r.* from resource r where r.id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Long[]{id}, new RowMapper<Resource>(){
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource r = new Resource();
				r.setId(rs.getLong("id"));
				r.setName(rs.getString("name"));
				r.setMenu(menu);
				return r;
			}
		});
	}
	public DataSource getDs() {
		return ds;
	}
}
