package cn.rainier.nian.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class ResourceDetailsMonitor implements InitializingBean{
	
	private String queryUrl = "select re.res_string,r.name"
      + "                      from role r"
      + "                      join role_resource rr"
      + "                        on r.name=rr.roleId"
      + "                      join resource re"
      + "                        on re.id=rr.resourceId"
      + "                     where re.res_type='URL'"
      + "                  		order by re.priority";
  private String queryMethod = "select re.res_string,r.name"
      + "                      from role r"
      + "                      join role_resource rr"
      + "                        on r.name=rr.roleId"
      + "                      join resource re"
      + "                        on re.id=rr.resourceId"
      + "                     where re.res_type='METHOD'"
      + "                  		order by re.priority";
  
  private FilterSecurityInterceptor filterSecurityInterceptor;
  private AccessDecisionManager accessDecisionManager;
  private DelegatingMethodSecurityMetadataSource delegatingMethodDefinitionSource;
  
  private ResourceDetailsBuilder resourceDetailsBuilder;
  private MethodSecurityInterceptor methodSecurityInterceptor;
  private Collection<? extends ConfigAttribute> hasMethodAttribute;
  private Collection<? extends ConfigAttribute> hasUrlAttribute;
	
	public void setFilterSecurityInterceptor(
	    FilterSecurityInterceptor filterSecurityInterceptor) {
	    this.filterSecurityInterceptor = filterSecurityInterceptor;
	}
	
	public void setDelegatingMethodDefinitionSource(
			DelegatingMethodSecurityMetadataSource delegatingMethodDefinitionSource) {
	    this.delegatingMethodDefinitionSource = delegatingMethodDefinitionSource;
	}
	//spring 加载该bean后调用
	public void afterPropertiesSet() throws Exception {
		//resourceDetailsBuilder = new ResourceDetailsBuilder(dataSource);
		//修改默认的accessDecisionManager
		filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
		//保存已经有的方法资源配置
		this.hasMethodAttribute = delegatingMethodDefinitionSource.getAllConfigAttributes();
		//保存所有的url资源配置
		this.hasUrlAttribute = filterSecurityInterceptor.getSecurityMetadataSource().getAllConfigAttributes();
    //从数据库读取其他资源
		refresh();
	}
	
	public void refresh() {
    if (filterSecurityInterceptor != null) {
        FilterInvocationSecurityMetadataSource source = resourceDetailsBuilder
            .createUrlSource(queryUrl);
        source.getAllConfigAttributes().addAll(hasUrlAttribute);
        filterSecurityInterceptor.setSecurityMetadataSource(source);
    }
    if (delegatingMethodDefinitionSource != null) {
        MethodSecurityMetadataSource source = resourceDetailsBuilder
            .createMethodSource(queryMethod);
        delegatingMethodDefinitionSource.getMethodSecurityMetadataSources().clear();
        delegatingMethodDefinitionSource.getMethodSecurityMetadataSources().add(source);
        List<MethodSecurityMetadataSource> list = new ArrayList<MethodSecurityMetadataSource>();
        source.getAllConfigAttributes().addAll(hasMethodAttribute);
        list.add(source);
        //为何要new一个，而不是在原有基础上添加，
        //因为DelegatingMethodSecurityMetadataSource存在缓存
        DelegatingMethodSecurityMetadataSource delegatingMethodDefinitionSource = 
        		new DelegatingMethodSecurityMetadataSource(list);
        methodSecurityInterceptor.setSecurityMetadataSource(delegatingMethodDefinitionSource);
    }
	}

	public ResourceDetailsBuilder getResourceDetailsBuilder() {
		return resourceDetailsBuilder;
	}

	public void setResourceDetailsBuilder(
			ResourceDetailsBuilder resourceDetailsBuilder) {
		this.resourceDetailsBuilder = resourceDetailsBuilder;
	}

	public MethodSecurityInterceptor getMethodSecurityInterceptor() {
		return methodSecurityInterceptor;
	}

	public void setMethodSecurityInterceptor(
			MethodSecurityInterceptor methodSecurityInterceptor) {
		this.methodSecurityInterceptor = methodSecurityInterceptor;
	}

	public Collection<? extends ConfigAttribute> getHasMethodAttribute() {
		return hasMethodAttribute;
	}
	public void setHasMethodAttribute(Collection<? extends ConfigAttribute> hasMethodAttribute) {
		this.hasMethodAttribute = hasMethodAttribute;
	}
	public Collection<? extends ConfigAttribute> getHasUrlAttribute() {
		return hasUrlAttribute;
	}
	public void setHasUrlAttribute(Collection<? extends ConfigAttribute> hasUrlAttribute) {
		this.hasUrlAttribute = hasUrlAttribute;
	}

	public FilterSecurityInterceptor getFilterSecurityInterceptor() {
		return filterSecurityInterceptor;
	}

	public DelegatingMethodSecurityMetadataSource getDelegatingMethodDefinitionSource() {
		return delegatingMethodDefinitionSource;
	}

	public AccessDecisionManager getAccessDecisionManager() {
		return accessDecisionManager;
	}

	public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
		this.accessDecisionManager = accessDecisionManager;
	}
}
