package cn.rainier.nian.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;

public class CglibBean{  
  /** 
    * 实体Object 
    */  
  public Object object = null;  
  /** 
    * 属性map 
    */  
  public BeanMap beanMap = null;
  public CglibBean() {  
    super();  
  }  
  
  public CglibBean(Map<String, Class> propertyMap) {  
    this.object = generateBean(propertyMap);  
    this.beanMap = BeanMap.create(this.object);  
  } 
  
  public CglibBean(String className,Map<String, Class> propertyMap) {
		this.object = generateBean(className,propertyMap);
		this.beanMap = BeanMap.create(this.object);
	}
  /** 
    * 给bean属性赋值 
    * @param property 属性名 
    * @param value 值 
    */  
  public void setValue(String property, Object value) {  
    beanMap.put(property, value);  
  }  
  /** 
    * 通过属性名得到属性值 
    * @param property 属性名 
    * @return 值 
    */  
  public Object getValue(String property) {  
    return beanMap.get(property);  
  }  
  /** 
    * 得到该实体bean对象 
    * @return 
    */  
  public Object getObject() {  
    return this.object;  
  }  
  
  private Object generateBean(Map<String, Class> propertyMap) {  
    BeanGenerator generator = new BeanGenerator();  
    Set<String> keySet = propertyMap.keySet();  
    for (Iterator<String> i = keySet.iterator(); i.hasNext();) {  
    	String key = i.next();  
    	generator.addProperty(key, propertyMap.get(key));  
    }  
    return generator.create();  
  }
  
  private Object generateBean(final String className,Map<String, Class> propertyMap) {
		BeanGeneratorObj generator = new BeanGeneratorObj();
		
		generator.setNamingPolicy(new NamingPolicy() {
			public String getClassName(String prefix, String source, Object key, Predicate names) {
				return className;
			}
		});
		Set<String> keySet = propertyMap.keySet();
		for (Iterator<String> i = keySet.iterator(); i.hasNext();) {
			String key = i.next();
			generator.addProperty(key, propertyMap.get(key));
		}
		return generator.create();
	}
  
  }  
