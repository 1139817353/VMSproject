package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.vo.VmsPage;

import java.util.List;

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
	boolean auth(Long roleId, Long[] menuIds) throws Exception;

	/**
	 * <b>转发至保存页面</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Role> getRoleListByQuery(Role query)throws Exception;
}
