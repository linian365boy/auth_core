package cn.rainier.nian.utils;

import java.util.UUID;
/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: 生成UUID
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.utils
 * @Author: 李年
 * @Create Date: 2013-5-24
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public class UUIDGenerator {
	
	public UUIDGenerator(){}
	/**
	 * @FunName: getUUID
	 * @Description:  生成UUID，把生成的"-"换成了""
	 * @return UUID
	 * @Author: 李年
	 * @CreateDate: 2013-5-24
	 */
	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		return s.replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDGenerator.getUUID());
	}
	
}
