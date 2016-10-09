package cn.rainier.nian.init;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.rainier.nian.utils.CglibUtils;
import cn.rainier.nian.utils.PackageScan;

/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 初始化，查找pojo是否被扩展
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.init
 * @Author: ln
 * @Create Date: 2013-3-28
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public class InitListener implements ServletContextListener{
	/**
	 * @FunName: initComponent
	 * @Description:  查找pojo是否被扩展
	 * @throws IOException
	 * @Author: ln
	 * @CreateDate: 2013-3-28
	 */
	public static void initComponent() throws IOException {
		Map<String,Class> propertyMap = null;
		//Set<String> properties = null;
		//List<Class> clazz = null;
		Properties pro = new Properties();
		pro.load(InitListener.class.getClassLoader().getResourceAsStream("component.properties"));
		String clazzesStr = pro.getProperty("component");
		String[] clazzes = clazzesStr.split(",");
		try {
			//auth_core.jar包中查找cn.rainier.nian.model.component下所有的类
			Set<String> componentStr = PackageScan.getClasses("cn.rainier.nian.model.component");
			for(String clazzSubStr : clazzes){
				for(Iterator<String> it = componentStr.iterator();it.hasNext();){
					String strParent = it.next();
					String s = strParent.substring(strParent.lastIndexOf("."));
					if(clazzSubStr.endsWith(s)){
						Class clazzSub = Class.forName(clazzSubStr);
						Field[] fields = clazzSub.getDeclaredFields();
						Field.setAccessible(fields, true);
						propertyMap = new HashMap<String,Class>();
						for(Field field : fields){
							propertyMap.put(field.getName(), field.getType());
						}
						CglibUtils.addPropertiesAndMethod(strParent, propertyMap);
						it.remove();
					}
				}
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			initComponent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
