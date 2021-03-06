package cn.rainier.nian.security;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;

public class MySecurityFilter extends AbstractSecurityInterceptor implements
		Filter {
	// 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，
	// 其他的两个组件，已经在AbstractSecurityInterceptor定义
	private MySecurityMetadataSource securityMetadataSource;
	private MyAccessDecisionManager accessDecisionManager;
	private AuthenticationManager autheticationManager;

	/*
	 * public void initPre(){
	 * //System.err.println(" --------------- MySecurityFilter init--------------- "
	 * ); super.setAuthenticationManager(autheticationManager);
	 * super.setAccessDecisionManager(accessDecisionManager); }
	 */
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	private void invoke(FilterInvocation fi) throws IOException,
			ServletException {
		// object为FilterInvocation对象
		// super.beforeInvocation(fi);源码
		// 1.获取请求资源的权限
		// 执行Collection<ConfigAttribute> attributes =
		// SecurityMetadataSource.getAttributes(object);
		// 2.是否拥有权限
		// this.accessDecisionManager.decide(authenticated, object, attributes);
		// System.err.println(" --------------- MySecurityFilter invoke--------------- ");
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		// 下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误
		return FilterInvocation.class;
	}

	public MySecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			MySecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	public MyAccessDecisionManager getAccessDecisionManager() {
		return accessDecisionManager;
	}

	public void setAccessDecisionManager(
			MyAccessDecisionManager accessDecisionManager) {
		this.accessDecisionManager = accessDecisionManager;
	}

	public AuthenticationManager getAutheticationManager() {
		return autheticationManager;
	}

	public void setAutheticationManager(
			AuthenticationManager autheticationManager) {
		this.autheticationManager = autheticationManager;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
	}
}
