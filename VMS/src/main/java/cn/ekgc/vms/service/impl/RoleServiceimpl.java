package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.dao.RoleDao;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.vo.VmsPage;
import cn.ekgc.vms.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceimpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
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
}
