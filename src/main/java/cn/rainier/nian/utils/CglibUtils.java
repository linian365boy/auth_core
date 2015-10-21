package cn.rainier.nian.utils;

import java.util.Map;
  
/** 
 * Cglib测试类 
 * @author cuiran 
 * @version 1.0 
 */  
public class CglibUtils {  
    @SuppressWarnings("unchecked")  
    public static void addPropertiesAndMethod(String clazzes,Map<String ,Class> propertyMap) 
    		throws ClassNotFoundException {  
        //propertyMap.put("address", Class.forName("java.lang.String"));  
        // 生成动态 Bean  
        //CglibBean bean = 
        new CglibBean(clazzes,propertyMap);
        /*// 给 Bean 设置值  
        bean.setValue("phone", "454");  
        bean.setValue("address", "789");  
        // 从 Bean 中获取值，当然了获得值的类型是 Object  
        System.out.println("  >> phone    = " + bean.getValue("phone"));  
        System.out.println("  >> address = " + bean.getValue("address"));  
        // 获得bean的实体  
        Object object = bean.getObject();  
        // 通过反射查看所有方法名  
        Class clazz = object.getClass();  
        Method[] methods = clazz.getDeclaredMethods();  
        for (int i = 0; i < methods.length; i++) {  
            System.out.println(methods[i].getName());  
        }  */
    }  
}
