package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.dao.UserDao;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

	/**
	 * <b>分页查询用户列表</b>
	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	public VmsPage<User> getUserListByPage(VmsPage<User> vmsPage) throws Exception {
		//使用PageHelper 进行分页查询
		PageHelper.startPage(vmsPage.getPageNum(),vmsPage.getPageSize());
		List<User> userList = userDao.findListByQuery(null);
		//将userList进行类型转换
		PageInfo<User> page = new PageInfo<User>(userList);
		//提取PageInfo中的信息,填入VmsPage中
		vmsPage.copyFromPageInfo(page);

		return vmsPage;


	}


}
