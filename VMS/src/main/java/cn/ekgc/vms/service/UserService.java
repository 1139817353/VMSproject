package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;


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


}
