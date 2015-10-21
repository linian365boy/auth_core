package cn.rainier.nian.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.ConfigAttributeEditor;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;


@SuppressWarnings("deprecation")
public class ResourceDetailsBuilder {
    private DataSource dataSource;
    
    public ResourceDetailsBuilder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public FilterInvocationSecurityMetadataSource createUrlSource(String... query) {
        return new DefaultFilterInvocationSecurityMetadataSource(this.buildRequestMap(query));
    }

    public MethodSecurityMetadataSource createMethodSource(String... query) {
        return new MapBasedMethodSecurityMetadataSource(this.buildMethodMap(query));
    }

    @SuppressWarnings("unchecked")
	protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap(
        String... query) {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = null;
        requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        Map<String, String> resourceMaps = new LinkedHashMap<String,String>();
        for(int i=0;i<query.length;i++){
        	Map<String, String> resourceMap = this.findResources(query[i]);
        	resourceMaps.putAll(resourceMap);
        }
        
        for (Map.Entry<String, String> entry : resourceMaps.entrySet()) {
        		RequestMatcher key = new AntPathRequestMatcher(entry.getKey());
            editor.setAsText(entry.getValue());
            requestMap.put(key,
                (Collection<ConfigAttribute>) editor.getValue());
        }

        return requestMap;
    }

    @SuppressWarnings("unchecked")
	protected Map<String, List<ConfigAttribute>> buildMethodMap(
        String... query) {
        Map<String, List<ConfigAttribute>> methodMap = null;
        methodMap = new LinkedHashMap<String, List<ConfigAttribute>>();

        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        Map<String, String> resourceMaps = new LinkedHashMap<String,String>();
        for(int i=0;i<query.length;i++){
        	Map<String, String> resourceMap = this.findResources(query[i]);
        	resourceMaps.putAll(resourceMap);
        }
        for (Map.Entry<String, String> entry : resourceMaps.entrySet()) {
            editor.setAsText(entry.getValue());
            methodMap.put(entry.getKey(),
                (List<ConfigAttribute>) editor.getValue());
        }

        return methodMap;
    }

    protected Map<String, String> findResources(String query) {
    		ResourceDetailsMapping resourceDetailsMapping = new ResourceDetailsMapping(dataSource,query);
        Map<String, String> resourceMap = new LinkedHashMap<String, String>();
        List<Resource> resources = resourceDetailsMapping.execute();

        for (Resource resource : resources) {
            String guName = resource.getRuName();
            String res_string = resource.getRes_string();

            if (resourceMap.containsKey(res_string)) {
                String value = resourceMap.get(res_string);
                resourceMap.put(res_string, value + "," + guName);
            } else {
                resourceMap.put(res_string, guName);
            }
        }

        return resourceMap;
    }
}
