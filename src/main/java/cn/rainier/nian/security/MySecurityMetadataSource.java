package cn.rainier.nian.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.model.Resource;

public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private ResourceDao resourceDao;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * @PostConstruct是Java EE 5引入的注解， Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 *
	 *                                        //加载所有资源与权限的关系
	 */
	private void loadResourceDefine() {
		// System.err.println("-----------MySecurityMetadataSource loadResourceDefine ----------- ");
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = this.resourceDao.getAllResource();
			for (Resource resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				//通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_"+resource.getRoleName());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getResString(), configAttributes);
			}
		}
	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// System.err.println("-----------MySecurityMetadataSource getAttributes ----------- ");
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		// System.out.println("requestUrl is " + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		// System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap
				.get(requestUrl);
		// if(configAttributes == null){
		// Collection<ConfigAttribute> returnCollection = new
		// ArrayList<ConfigAttribute>();
		// returnCollection.add(new SecurityConfig("ROLE_NO_USER"));
		// return returnCollection;
		// }
		return configAttributes;
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public static Map<String, Collection<ConfigAttribute>> getResourceMap() {
		return resourceMap;
	}

	public static void setResourceMap(
			Map<String, Collection<ConfigAttribute>> resourceMap) {
		MySecurityMetadataSource.resourceMap = resourceMap;
	}
}
