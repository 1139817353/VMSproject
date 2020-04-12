package cn.ekgc.vms.util.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Date;

/**
 * <b>认证域</b>
 */
public class ShiroDBRealm extends AuthorizingRealm {
/**
 * <b>当需要授权的时候，会调用该方法</b>
 * @param principalCollection
 * @return
 */
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
   	  return null;
   }

	/**
	 * <b>当需要认证的时候，会调用该方法</b>
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("调用ShiroDBRealm的doGetAutheenticationInfo进行系统登录…" + new Date());
		return null;
	}
}
