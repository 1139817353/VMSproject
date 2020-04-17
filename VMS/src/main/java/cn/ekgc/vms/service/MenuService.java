package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.vo.Node;

import java.util.List;

public interface MenuService {
	/**
	 * <b>查询用户角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Menu> getIndexMenuList(Role role)throws Exception;
	/**
	 * <b>为授权页面查询Node集合</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Node> getNodeListForAuth(Long roleId)throws Exception;
}
