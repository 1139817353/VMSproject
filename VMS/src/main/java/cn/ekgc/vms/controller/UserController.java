package cn.ekgc.vms.controller;

import cn.ekgc.vms.base.controller.BaseController;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <b>系统用户控制层</b>
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	 private UserService userService;
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
}
