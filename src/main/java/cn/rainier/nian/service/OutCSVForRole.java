package cn.rainier.nian.service;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.model.Menu;
import cn.rainier.nian.model.Resource;
import cn.rainier.nian.model.Role;
import cn.rainier.nian.model.component.RoleComponent;
/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 角色导出CSV文件
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.utils
 * @Author: 李年
 * @Create Date: 2013-5-24
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public class OutCSVForRole {
	/**
	 * 全部导出三级菜单（即资源）
	 */
	private ResourceDao resourceDao;
	
	public void exportCSV(String[] headers, List<Role> result,
			PrintWriter out) {
		Role role = null;
		RoleComponent component = null;
		List<Field> fields = new ArrayList<Field>();
		List<Resource> resources = null;
		List<Menu> menus = null;
		boolean flag = false;
		for(short i = 0; i < headers.length; i++){
			if(i<headers.length-1){
				out.write("\""+headers[i]+"\",");
			}else{
				out.write("\""+headers[i]+"\"\n");
			}
		}
		for(int i=0;i<result.size();i++){
			role = result.get(i);
			component = role.getRoleComponent();
			menus = role.getMenus();
			out.write("\""+role.getDesc()+"\",");
			out.write("\"");
			resources = role.getResources();
			for(Menu menu : menus){
				if(menu.getParentMenu()!=null){
					for(Resource res : resources){
						if(res.getMenu().getId()==menu.getId()){
							out.write(res.getDescn());
							out.write("，");
						}
					}
				}
			}
			out.write("\"");
			if(component!=null){
				Field[] field = component.getClass().getDeclaredFields();
				fields = new ArrayList<Field>();
				for(Field f : field){
					if(!(f.getName().startsWith("temp"))){
						fields.add(f);
					}
				}
				for(int n=0;n<fields.size();n++){
					try {
						fields.get(n).setAccessible(true);
						Object value = fields.get(n).get(component);
						if(value==null||"".equals(value)){
							value="";
						}
						if(n<fields.size()-1){
							out.write("\""+value.toString()+"\",");
						}else{
							out.write("\""+value.toString()+"\"\n");
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				flag = true;
			}else{
				for(int m=0;m<headers.length-2;m++){
					flag = true;
					if(m<headers.length-3){
						out.write("\""+""+"\",");
					}else{
						out.write("\""+""+"\"\n");
					}
				}
			}
			if(!flag){
				out.write("\n");
			}
			flag = false;
			fields.removeAll(fields);
		}
	}

	public void exportCSV(String[] headers, List<Role> results,
			PrintWriter out, Long l) {
		if(l!=null){//除l导出三级菜单（即资源），其余导出到二级菜单
			Role role = null;
			RoleComponent component = null;
			List<Field> fields = new ArrayList<Field>();
			List<Resource> resources = null;
			List<Menu> menus = null;
			boolean flag = false;
			for(short i = 0; i < headers.length; i++){
				if(i<headers.length-1){
					out.write("\""+headers[i]+"\",");
				}else{
					out.write("\""+headers[i]+"\"\n");
				}
			}
			for(int i=0;i<results.size();i++){
				role = results.get(i);
				component = role.getRoleComponent();
				resources = role.getResources();
				out.write("\""+role.getDesc()+"\",");
				out.write("\"");
				menus = role.getMenus();
				for(Menu menu : menus){
					if(menu.getParentMenu()!=null){
						if(menu.getParentMenu().getId()!=l){
							out.write(menu.getName());
							out.write("，");
						}else{
							for(Resource res : resources){
								if(res.getMenu().getId()==menu.getId()){
									out.write(res.getDescn());
									out.write("，");
								}
							}
						}
					}
				}
				out.write("\"");
				if(component!=null){
					Field[] field = component.getClass().getDeclaredFields();
					fields = new ArrayList<Field>();
					for(Field f : field){
						if(!(f.getName().startsWith("temp"))){
							fields.add(f);
						}
					}
					for(int n=0;n<fields.size();n++){
						try {
							fields.get(n).setAccessible(true);
							Object value = fields.get(n).get(component);
							if(value==null||"".equals(value)){
								value="";
							}
							if(n<fields.size()-1){
								out.write("\""+value.toString()+"\",");
							}else{
								out.write("\""+value.toString()+"\"\n");
							}
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					flag = true;
				}else{
					for(int m=0;m<headers.length-2;m++){
						flag = true;
						if(m<headers.length-3){
							out.write("\""+""+"\",");
						}else{
							out.write("\""+""+"\"\n");
						}
					}
				}
				if(!flag){
					out.write("\n");
				}
				flag = false;
				fields.removeAll(fields);
			}
		}else{//全部导出到三级菜单（即资源）
			exportCSV(headers, results,out);
		}
	}
	
	public void exportCSVExNoDisplay(String[] headers, List<Role> results,
			PrintWriter out){
		Role role = null;
		RoleComponent component = null;
		List<Field> fields = new ArrayList<Field>();
		List<Resource> resources = null;
		List<Resource> resoucesExNoDisplay = null;
		List<Menu> menus = null;
		boolean flag = false;
		for(short i = 0; i < headers.length; i++){
			if(i<headers.length-1){
				out.write("\""+headers[i]+"\",");
			}else{
				out.write("\""+headers[i]+"\"\n");
			}
		}
		for(int i=0;i<results.size();i++){
			role = results.get(i);
			resources = role.getResources();
			component = role.getRoleComponent();
			out.write("\""+role.getDesc()+"\",");
			out.write("\"");
			menus = role.getMenus();
			for(Menu menu : menus){
				if(menu.getParentMenu()!=null){
					resoucesExNoDisplay = resourceDao.findResourceByParentId(menu.getId());
					if(resoucesExNoDisplay!=null&&resoucesExNoDisplay.size()==0){
						out.write(menu.getName());
						out.write("，");
					}else{
						for(Resource res : resources){
							if(res.getMenu().getId()==menu.getId()){
								out.write(res.getDescn());
								out.write("，");
							}
						}
					}
				}
			}
			out.write("\"");
			if(component!=null){
				Field[] field = component.getClass().getDeclaredFields();
				fields = new ArrayList<Field>();
				for(Field f : field){
					if(!(f.getName().startsWith("temp"))){
						fields.add(f);
					}
				}
				for(int n=0;n<fields.size();n++){
					try {
						fields.get(n).setAccessible(true);
						Object value = fields.get(n).get(component);
						if(value==null||"".equals(value)){
							value="";
						}
						if(n<fields.size()-1){
							out.write("\""+value.toString()+"\",");
						}else{
							out.write("\""+value.toString()+"\"\n");
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				flag = true;
			}else{
				for(int m=0;m<headers.length-2;m++){
					flag = true;
					if(m<headers.length-3){
						out.write("\""+""+"\",");
					}else{
						out.write("\""+""+"\"\n");
					}
				}
			}
			if(!flag){
				out.write("\n");
			}
			flag = false;
			fields.removeAll(fields);
		}
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
}
