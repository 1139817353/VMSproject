package cn.ekgc.vms.util.security;

import cn.ekgc.vms.base.enums.StatusEnum;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.service.UserService;
import cn.ekgc.vms.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <b>认证域</b>
 */
public class ShiroDBRealm extends AuthorizingRealm {
   @Autowired
	private UserService userService;
   @Autowired
   private HttpSession session;
	/**
 * <b>当需要授权的时候，会调用该方法</b>
 * @param principalCollection
 * @return
 */
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
	   System.out.println("调用ShiroDBRealm2的doGetAutheenticationInfo进行系统登录…" + new Date());

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
      System.out.println("登录!");
     //因为登录所使用的方式是”登录名+登录密码“，因此需要把AuthenticationToken进行转换
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		//通过UsernamePasswordToken能够获得登录的用户名、密码当前登录用户的IP地址等等信息
		//通过用户名获得对应的用户对象
		try {
			//token在前端取出username属性也就是cellphone
			User user = userService.getUserByCellphone(token.getUsername());
			System.out.println("主页列表集合:"+token.getUsername());
			System.out.println("主页列表总集合:"+user.getCellphone());
			//判断User对象是否为空,且状态为启用状态
			if (user != null && StatusEnum.STATUS_ENABLE.getCode() == user.getStatus()){
			    System.out.println("进入校验界面!");
				//获得用户登录密码然后加密
				String password = MD5Util.encrypt(new String(token.getPassword()));
				//加密后的密码绑定到token
				token.setPassword(password.toCharArray());
				//通过手机号码查询出正确用户信息，以及将用户登录所提交的密码进行了加密处理，并没有进行密码比对
				// 对于密码是否正确，只需要交给Shiro，其自身能够比对
				//getName()是 token 里的值，和两者进行比较
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getCellphone(),user.getPassword(),getName());
                //默认登录成功，绑定session中
				session.setAttribute("user",user);
				return info;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
