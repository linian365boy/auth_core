package cn.rainier.nian.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rainier.nian.dao.RoleDao;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.model.User;
import cn.rainier.nian.service.RoleService;
import cn.rainier.nian.utils.PageRainier;

@Service
public class RoleServiceImpl implements RoleService{
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * @FunName: findAllByAjax
	 * @Description:  ajax异步拿到所有角色的name与desc属性
	 * @return
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	public List<Role> findAllByAjax() {
		return roleDao.finAllByAjax();
	}
	/**
	 * @FunName: exportToCSV
	 * @Description:  角色导出csv文件，包括了所有的三级资源（显示与不显示的）。
	 * @param roles
	 * @param fileName
	 * @param headers
	 * @param response
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	/*public void exportToCSV(List<Role> roles, String fileName,
			String[] headers, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			this.getOutCSV().exportCSV(headers, roles, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}*/

	public PageRainier<Role> findAll(Integer pageNo, Integer pageSize, boolean b) {
		return null;
	}
	@Override
	public Role loadRoleByName(String roleName) {
		return roleDao.findOne(roleName);
	}
	@Override
	public boolean saveRole(Role role) {
		try {
			roleDao.save(role);
			return true;
		} catch (Exception e) {
			logger.error("新增角色报错",e);
		}
		return false;
	}
	@Override
	public void delRole(String roleId) {
		try {
			roleDao.delete(roleId);
		} catch (Exception e) {
			logger.error("删除角色报错",e);
		}
	}
	@Override
	public List<Role> findRoleByUser(User u) {
		return roleDao.findRoleByUser(u.getId());
	}
	@Override
	public Role findDefault() {
		return roleDao.findDefaultRole();
	}
	
	/**
	 * @FunName: exportToCSVExNoDisplay
	 * @Description:  角色导出csv文件，只导出能显示的三级资源。
	 * @param roles
	 * @param fileName
	 * @param headers
	 * @param response
	 * @Author: ln
	 * @CreateDate: 2013-6-6
	 */
	/*public void exportToCSVExNoDisplay(List<Role> roles, String fileName,
			String[] headers, HttpServletResponse response){
		PrintWriter out = null;
		try{
			out = response.getWriter();
			this.getOutCSV().exportCSVExNoDisplay(headers, roles, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}*/
	
	/**
	 * @FunName: exportToCSV
	 * @Description:  角色导出csv文件
	 * @param roles 导出的角色列表
	 * @param fileName 导出的文件名
	 * @param headers 列名
	 * @param response
	 * @param l 特殊对待的二级菜单Id，如果此Id不为空，则（三级菜单即资源会导出！否则不导出）
	 * @Author: ln
	 * @CreateDate: 2013-5-24
	 */
	/*public void exportToCSV(List<Role> roles, String fileName,
			String[] headers, HttpServletResponse response, Long l) {
		if(l==null){
			exportToCSV(roles,fileName,headers,response);
		}else{
			PrintWriter out = null;
			try {
				out = response.getWriter();
				this.getOutCSV().exportCSV(headers, roles, out,l);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(out!=null){
					out.close();
				}
			}
		}
	}*/
}
