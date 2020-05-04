package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.dao.UserDao;
import cn.ekgc.vms.pojo.entity.User;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

	/**
	 * <b>保存信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean save(User user) throws Exception {
		int index = userDao.save(user);
		if (index > 0){
			return true;
		}
		return false;
	}

	/**
	 * <b>查询角色信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> getRoleListByQuery(User user) throws Exception {
		List<User> userList = userDao.findListByQuery(user);
		if (userList != null){
			return userList;
		}
		return new ArrayList<User>();
	}

	/**
	 * <b>更改用户信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public boolean update(User query) throws Exception {
		int update = userDao.update(query);
		if (update > 0){
			System.out.println("id是:"+query.getId());
			System.out.println(query.getPassword());
			return true;
		}
		return false;
	}


}
