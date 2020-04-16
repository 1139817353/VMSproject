package cn.ekgc.vms.controller;

import cn.ekgc.vms.pojo.entity.Role;
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
	public String index()throws Exception{
		System.out.println("进入角色控制层!");
		return "role/role_index";
	}

	/**
	 * <b>分页查询</b>
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/page")
	@ResponseBody
	public VmsPage<Role> getRoleVmsPage(Integer pageNum,Integer pageSize,Integer draw)throws Exception{
		System.out.println("进入角色分页控制层!");
     //创建VmsPage对象
		VmsPage<Role> vmsPage = new VmsPage<Role>(pageNum,pageSize,draw);
		//业务层进行分页查询
		vmsPage = roleService.getRoleVmsPage(vmsPage);
		return vmsPage;
	}

    @GetMapping(value = "/auth")
	public String auth(@RequestParam Long id,ModelMap map)throws Exception{
	    Role role = new Role();
	    role.setId(id);
	    String Id = String.valueOf(role.getId());
	    map.put("id",id);
		return "role/role_index";
	}
}
