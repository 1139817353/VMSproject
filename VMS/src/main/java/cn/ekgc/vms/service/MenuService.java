package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;

import java.util.List;

public interface MenuService {
	/**
	 * <b>查询用户角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Menu> getIndexMenuList(Role role)throws Exception;
}
