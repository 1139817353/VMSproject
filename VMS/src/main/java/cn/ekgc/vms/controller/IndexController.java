package cn.ekgc.vms.controller;

import cn.ekgc.vms.base.controller.BaseController;
import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("indexController")
public class IndexController extends BaseController {
	@Autowired
	private MenuService menuService;
	/**
	 * <b>加载首页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/")
	public String index(ModelMap map)throws Exception{
		System.out.println("进入控制层");
		//获得当前用户信息
		User user = (User) session.getAttribute("user");
		//根据用户获得角色信息
		Role role = user.getRole();
		//取出人物角色查询
		List<Menu> roleList = menuService.getIndexMenuList(role);
		map.put("menuList", roleList);
		return "index";
	}
}
