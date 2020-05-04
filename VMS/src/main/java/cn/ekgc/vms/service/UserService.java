package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;

import java.util.List;


public interface UserService {
	/**
	 * <b>查询用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	User getUserByCellphone(String cellphone)throws Exception;

	/**
	 * <b>分页查询用户列表</b>
 	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	VmsPage<User> getUserListByPage(VmsPage<User> vmsPage)throws Exception;

	/**
	 * <b>保存信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean save(User user)throws Exception;

	/**
	 * <b>查询角色信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> getRoleListByQuery(User user)throws Exception;

	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	boolean update(User query)throws Exception;
}
