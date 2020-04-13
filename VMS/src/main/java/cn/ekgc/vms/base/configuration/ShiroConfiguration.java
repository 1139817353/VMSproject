package cn.ekgc.vms.base.configuration;

import cn.ekgc.vms.util.security.ShiroDBRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>Shiro 配置类</b>
 */
@Configuration
public class ShiroConfiguration {
	//配置当需要登录的时候，所采用的认证业务流程
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
   //配置认证的规则
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		//创建过滤器对象
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		//配置 Shiro 的核心对象 SecurityManager
		shiroFilter.setSecurityManager(securityManager());
		//当发现请求未登录时，需要跳转登录界面
		shiroFilter.setLoginUrl("/user/login");
        //当登录成功后，如果之前没有确定的请求地址，那么设置默认请求地址
		shiroFilter.setSuccessUrl("/");

		//配置那些请求需要登录，那些请求不需要登录
		//配置使用Map 集合来完成，不过需要注意 shiro 匹配时，是从上到下有顺序的匹配
		Map<String,String> filterChainMap = new LinkedHashMap<String,String>();

		//配置不需要登录请求
		//静态资源
		filterChainMap.put("/css/*","anon");
		filterChainMap.put("/js/*","anon");
		filterChainMap.put("/img/*","anon");
		filterChainMap.put("/fontawsome/*", "anon");
		//配置退出规则
		filterChainMap.put("/user/logout","logout");
		//配置需要进行登录的规则
		filterChainMap.put("/**","authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainMap);

		return shiroFilter;
	}
}
