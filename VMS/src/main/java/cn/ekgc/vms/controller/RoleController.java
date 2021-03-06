package cn.ekgc.vms.controller;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.RoleService;
import cn.ekgc.vms.service.UserService;
import cn.ekgc.vms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/index")
	public String index() throws Exception {
		return "role/role_index";
	}

	/**
	 * <b>分页查询角色信息</b>
	 *
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
	 *
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
	 *
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth")
	@ResponseBody
	public boolean forAuth(Long roleId, String ids) throws Exception {
		// 判断此时的授权信息是否存在
		if (ids != null && !"".equals(ids.trim())) {
			// 使用“-”进行字符串切割
			String[] strs = ids.split("-");
			// 将对应的String[] 变成Long[]
			Long[] menuIds = new Long[strs.length];
			for (int i = 0; i < strs.length; i++) {
				menuIds[i] = Long.parseLong(strs[i]);
			}

			// 进行保存
			return roleService.auth(roleId, menuIds);
		}
		return false;
	}

	/**
	 * <b>授权到修改密码页面</b>
	 *
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/password")
	public String getPassword(Long id, ModelMap map) throws Exception {
		map.put("id", id);
		return "role/role_update";
	}

	/**
	 * <b>查询用户信息</b>
	 *
	 * @param cellphone
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/password")
	@ResponseBody
	public List<User> getSeletPassword(Long id,String cellphone, String password) throws Exception {
		if (cellphone != null && password != null) {
			User query = new User();
			query.setId(id);
			query.setCellphone(cellphone);
			query.setPassword(MD5Util.encrypt(password));
			List<User> userList = userService.getRoleListByQuery(query);
			if (userList.size() > 0){
				if (query.getPassword().equals(MD5Util.encrypt(password))){
					System.out.println("查询id是"+query.getId());
					System.out.println(userList.get(0));
					return userList;
				}
			}
		}
		return new ArrayList<User>();

	}

	@GetMapping(value = "/update")
	public String Update(Long id,ModelMap map)throws Exception{
		User query = new User();
		query.setId(id);
		map.put("id", id);
		System.out.println(map);
		return "role/role_updatetwo";
	}

	@PostMapping(value = "/update")
	@ResponseBody
	public boolean update(Long id,String password)throws Exception{
		if (password !=null){
			User query = new User();
			query.setId(id);
			query.setPassword(MD5Util.encrypt(password));
			return userService.update(query);

		}
		return false;
	}


}