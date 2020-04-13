package cn.ekgc.vms.dao;

import cn.ekgc.vms.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
	/**
	 * <b>查询用户列表</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> findListByQuery(User user)throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int save(User user)throws Exception;

	/**
	 * <b>更新用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int update(User user)throws Exception;
}

