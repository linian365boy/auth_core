package cn.rainier.nian.service;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.rainier.nian.model.Role;
import cn.rainier.nian.model.User;
import cn.rainier.nian.model.component.UserComponent;
/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 用户导出CSV格式
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.utils
 * @Author: 李年
 * @Create Date: 2013-5-24
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public class OutCSVForUser{
	
	/**
	 * 用户列表导出csv文件
	 */
	public void exportCSV(String[] headers,
			List<User> result, PrintWriter out) {
		User user = null;
		UserComponent component = null;
		List<Role> userRoles = null;
		List<Field> fields = new ArrayList<Field>();
		boolean flag = false;
		for (short i = 0; i < headers.length; i++) {
			if(i<headers.length-1){
				out.write("=\""+headers[i]+"\",");
			}else{
				out.write("=\""+headers[i]+"\"\n");
			}
	    }
		for(int i=0;i<result.size();i++){
				user = result.get(i);
				component = user.getUserComponent();
				out.write("=\""+user.getRealName()+"\",");
				out.write("=\""+user.getUsername()+"\",");
				
				if(!user.getAccountNonLocked()){
					out.write("=\""+"注销"+"\",");
				}else{
					if(user.getEnabled()){
						out.write("=\""+"正常"+"\",");
					}else{
						out.write("=\""+"禁用"+"\",");
					}
				}
				out.write("=\""+user.getLastCloseDate()+"\",");
				userRoles = user.getRoles();
				if(userRoles!=null){
					for(Role role : userRoles){
						if(!role.isDefaultOrNo()){
							flag = true;
							out.write("=\""+role.getDesc()+"\",");
						}
					}
				}else{
					flag = true;
					out.write("=\"\",");
				}
				if(!flag){
					out.write("=\"\",");
				}
				if(component!=null){
					Field[] field = component.getClass().getDeclaredFields();
					for(Field f : field){
						if(!(f.getName().startsWith("temp"))){
							fields.add(f);
						}
					}
					for(int n=0;n<fields.size();n++){
						try {
							fields.get(n).setAccessible(true);
							Object value = fields.get(n).get(component);
							if(value instanceof Date){
								Date date = (Date)value;
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								value = sdf.format(date);
							}else if(value==null||"".equals(value)){
								value="";
							}
							if(n<fields.size()-1){
								out.write("=\""+value.toString()+"\",");
							}else{
								out.write("=\""+value.toString()+"\"\n");
							}
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}else{
					for(int m=0;m<headers.length-5;m++){
						if(m<headers.length-6){
							out.write("=\"\",");
						}else{
							out.write("=\"\"\n");
						}
					}
				}
				flag = false;
				fields.removeAll(fields);
			}
	}
}
