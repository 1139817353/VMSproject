package cn.ekgc.vms.controller;

import cn.ekgc.vms.base.controller.BaseController;
import cn.ekgc.vms.base.enums.StatusEnum;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.RoleService;
import cn.ekgc.vms.service.UserService;
import cn.ekgc.vms.util.MD5Util;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Savepoint;

/**
 * <b>系统用户控制层</b>
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	 private UserService userService;
	@Autowired
	private RoleService roleService;
	/**
	 * <b>转发到登录界面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/login")
	public String forwardLoginPage() throws Exception {
		return "user/user_login";
	}

	/**
	 * <b>登录失败后访问地址</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	public String loginError() throws Exception {
		// 只需要重定向到退出即可
		return "redirect:/user/logout";
	}

	/**
	 * <b>加载列表页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/index")
	public String index() throws Exception {
		return "user/user_index";
	}

	/**
	 * <b>分页查询用户信息列表</b>
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/page")
	@ResponseBody
	public VmsPage<User> getUserListByPage(Integer pageNum, Integer pageSize, int draw) throws Exception {
		// 创建 VmsPage 对象
		VmsPage<User> vmsPage = new VmsPage<User>(pageNum, pageSize, draw);
		// 使用 Service 进行分页查询
		vmsPage = userService.getUserListByPage(vmsPage);
		return vmsPage;
	}

	/**
	 * <b>转发至保存页面</b>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/save")
	public String forwardSave(ModelMap map)throws Exception{
		//获得所有的角色列表
		Role query = new Role();
		query.setStatus(StatusEnum.STATUS_ENABLE.getCode());
		map.put("roleList", roleService.getRoleListByQuery(query));
		return "user/user_save";
	}

	/**
	 * <b>保存信息</b>
	 * @param user
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	@ResponseBody
	public boolean save(User user,Long roleId)throws Exception{
		//检查手机号码和密码
		if (user.getCellphone() != null && !"".equals(user.getCellphone().trim()) && user.getPassword() != null && !"".equals(user.getPassword().trim()))
	   //校验必须存在角色
			if (roleId != null &&roleId != 0){
				user.setPassword(MD5Util.encrypt(user.getPassword()));
				//设定状态为启用状态
				user.setStatus(StatusEnum.STATUS_ENABLE.getCode());
				return userService.save(user);
			}
		return false;
	}

}
