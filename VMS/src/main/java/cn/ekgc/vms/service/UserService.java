package cn.ekgc.vms.service;

import cn.ekgc.vms.pojo.entity.User;


public interface UserService {
	/**
	 * <b>查询用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	User getUserByCellphone(String cellphone)throws Exception;
}
