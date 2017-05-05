package cn.rainier.nian.helper;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import cn.rainier.nian.dao.ResourceDao;
import cn.rainier.nian.model.Resource;


@SuppressWarnings("deprecation")
public class ResourceDetailsBuilder {
	private ResourceDao resourceDao;
    
    public FilterInvocationSecurityMetadataSource createUrlSource() {
        return new DefaultFilterInvocationSecurityMetadataSource(this.buildRequestMap());
    }

    public MethodSecurityMetadataSource createMethodSource() {
        return new MapBasedMethodSecurityMetadataSource(this.buildMethodMap());
    }

    @SuppressWarnings("unchecked")
	protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = null;
        requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        Map<String, String> resourceMaps = new LinkedHashMap<String,String>();
        Map<String, String> resourceMap = this.getAllResource("URL");
        resourceMaps.putAll(resourceMap);
        
        for (Map.Entry<String, String> entry : resourceMaps.entrySet()) {
        	RequestMatcher key = new AntPathRequestMatcher(entry.getKey());
            editor.setAsText(entry.getValue());
            requestMap.put(key,
                (Collection<ConfigAttribute>) editor.getValue());
        }

        return requestMap;
    }

    @SuppressWarnings("unchecked")
	protected Map<String, List<ConfigAttribute>> buildMethodMap() {
        Map<String, List<ConfigAttribute>> methodMap = null;
        methodMap = new LinkedHashMap<String, List<ConfigAttribute>>();
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        Map<String, String> resourceMaps = new LinkedHashMap<String,String>();
        Map<String, String> resourceMap = this.getAllResource("METHOD");
        resourceMaps.putAll(resourceMap);
        
        for (Map.Entry<String, String> entry : resourceMaps.entrySet()) {
            editor.setAsText(entry.getValue());
            methodMap.put(entry.getKey(),
                (List<ConfigAttribute>) editor.getValue());
        }

        return methodMap;
    }

    protected Map<String, String> getAllResource(String type) {
        Map<String, String> resourceMap = new LinkedHashMap<String, String>();
        List<Resource> resources = resourceDao.getAllTypeResource(type);
        for (Resource resource : resources) {
            String guName = resource.getRoleName();
            String res_string = resource.getResString();

            if (resourceMap.containsKey(res_string)) {
                String value = resourceMap.get(res_string);
                resourceMap.put(res_string, value + "," + guName);
            } else {
                resourceMap.put(res_string, guName);
            }
        }
        return resourceMap;
    }

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}
