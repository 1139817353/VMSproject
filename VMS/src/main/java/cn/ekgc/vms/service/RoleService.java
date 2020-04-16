package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.vo.VmsPage;

public interface RoleService {
	/**
	 * <b>进行分页查询</b>
	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	VmsPage<Role> getRoleVmsPage(VmsPage<Role> vmsPage)throws Exception;
}
