package cn.ekgc.vms.base.configuration;

import cn.ekgc.vms.util.security.ShiroDBRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>Shiro 配置类</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class ShiroConfiguration {
	// 配置当需要登录的时候，所采取的登录认证业务流程
	@Bean
	public ShiroDBRealm shiroDBRealm() {
		return new ShiroDBRealm();
	}

	@Bean
	public SecurityManager securityManager() {
		// 创建对应的 Shiro 核心对象 SecurityManager
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置当需要登录认证的时候，使用自定义的 ShiroDBRealm 进行
		securityManager.setRealm(shiroDBRealm());
		return securityManager;
	}

	// 配置认证的规则
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		// 创建过滤器对象
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		// 配置 Shiro 的核心对象 SecurityManager
		shiroFilter.setSecurityManager(securityManager);
		// 当发现请求为登录时，需要跳转到登录界面，配置登录的重定向地址
		shiroFilter.setLoginUrl("/user/login");
		// 当登录成功之后，如果之前没有确定的请求地址，那么设定默认的登录跳转地址
		shiroFilter.setSuccessUrl("/");

		// 配置哪些请求需要登录，哪些请求不需要登录
		// 配置使用 Map 集合来完成，不过需要注意的是，Shiro 在匹配的时候，是从上到下有顺序的匹配
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不需要登录的请求
		// 静态资源
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		// 配置退出规则
		filterChainDefinitionMap.put("/user/logout", "logout");
		// 配置需要进行登录的规则
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("测试结果");
		return shiroFilter;
	}
}
