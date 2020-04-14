package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.base.enums.StatusEnum;
import cn.ekgc.vms.dao.MenuDao;
import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("menuService")
@Transactional
public class MenuServiceimpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	/**
	 * <b>查询用户角色信息</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getIndexMenuList(Role role) throws Exception {
		Map<String,Object> query = new HashMap<String,Object>();
		//根据角色查询所有一级菜单
		query.put("parentId",null);
		query.put("roleId", role.getId());
		query.put("status", StatusEnum.STATUS_ENABLE.getCode());

		List<Menu> menuList = menuDao.findListByQuery(query);
		if (menuList != null && menuList.size() > 0 ){
			for (Menu first : menuList ) {
              query.put("parentId",first.getId());
              List<Menu> childList = menuDao.findListByQuery(query);
              //将二级菜单装进列表中
              first.setChildList(childList);
			}
		}
		return menuList;
	}
}
