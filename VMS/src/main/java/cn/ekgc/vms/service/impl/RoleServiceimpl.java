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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * <b>进行数据存储</b>
	 * @param roleId,roleids
	 * @return
	 * @throws Exception
	 */
	public boolean auth(Long roleId, Long[] menuIds) throws Exception {
		// 根据角色主键清空现有权限
		if (roleDao.deleteForAuth(roleId) >= 0) {
			// 循环授权
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (Long menuId : menuIds) {
				// 保存权限
				paramMap.put("roleId", roleId);
				paramMap.put("menuId", menuId);

				roleDao.saveAuth(paramMap);
			}
			return true;
		}

		return false;
	}


	/**
	 * <b>转发至保存页面</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListByQuery(Role query) throws Exception {
		List<Role> list = roleDao.findListByQuery(query);
		if (list != null && list.size() > 0 ){
			return list;
		}
		return new ArrayList<Role>();
	}
}
