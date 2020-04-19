package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.RoleMenu;
import cn.ekgc.vms.pojo.vo.VmsPage;

public interface RoleService {
	/**
	 * <b>进行分页查询</b>
	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	VmsPage<Role> getRoleVmsPage(VmsPage<Role> vmsPage)throws Exception;

	/**
	 * <b>进行数据存储</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	boolean forInsertAuth(RoleMenu roleId)throws Exception;
}
