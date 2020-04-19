package cn.ekgc.vms.controller;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.RoleMenu;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.RoleService;
import cn.ekgc.vms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <b>角色控制层</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	/**
	 * <b>转发角色列表页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/index")
	public String index() throws Exception {
		return "role/role_index";
	}

	/**
	 * <b>分页查询角色信息</b>
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/page")
	@ResponseBody
	public VmsPage<Role> getRoleVmsPage(Integer pageNum, Integer pageSize, Integer draw) throws Exception {
		// 创建VmsPage对象
		VmsPage<Role> vmsPage = new VmsPage<>(pageNum, pageSize, draw);
		// 业务层进行分页查询
		vmsPage = roleService.getRoleVmsPage(vmsPage);

		return vmsPage;
	}

	/**
	 * <b>转发到授权页面</b>
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/auth")
	public String forwardAuthPage(Long id, ModelMap map) throws Exception {
		// 将角色主键绑定到 ModelMap 中
		map.put("roleId", id);
		return "role/role_auth";
	}

	/**
	 * <b>将角色数据进行更新</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth")
	@ResponseBody
	public boolean forAuth(Long roleId)throws Exception{
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		return roleService.forInsertAuth(roleMenu);
	}
}
