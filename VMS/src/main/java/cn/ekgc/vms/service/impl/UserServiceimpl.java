package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.dao.UserDao;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceimpl implements UserService {
	@Autowired
	private UserDao userDao;
	/**
	 * <b>查询用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	public User getUserByCellphone(String cellphone) throws Exception {
		User user = new User();
		user.setCellphone(cellphone);
		List<User> userList = userDao.findListByQuery(user);
        System.out.println("进入Service层:"+userList);
		if (userList != null && userList.size() > 0){
			System.out.println("结果:"+userList.get(0));
			return userList.get(0);
		}
		return null;
	}
}
