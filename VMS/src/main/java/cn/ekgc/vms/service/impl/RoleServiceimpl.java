package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.dao.RoleDao;
import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.entity.RoleMenu;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service("roleService")
@Transactional
public class RoleServiceimpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private HttpServletRequest request;
	/**
	 * <b>进行分页查询</b>
	 * @param vmsPage
	 * @return
	 * @throws Exception
	 */
	public VmsPage<Role> getRoleVmsPage(VmsPage<Role> vmsPage) throws Exception {
		PageHelper.startPage(vmsPage.getPageNum(),vmsPage.getPageSize());
		List<Role> roleList = roleDao.findListByQuery(null);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
		vmsPage.copyFromPageInfo(pageInfo);
		return vmsPage;
	}

	/**
	 * <b>进行数据存储</b>
	 * @param roleMenu
	 * @return
	 * @throws Exception
	 */
	public boolean forInsertAuth(RoleMenu roleMenu) throws Exception {
		Menu menu = new Menu();
		menu.setText(request.getParameter("ids"));
		boolean index = roleDao.InsertIntoRoleId(roleMenu);
		if (index){
			return true;
		}
		return false;
	}
}
