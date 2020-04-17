package cn.ekgc.vms.service.impl;

import cn.ekgc.vms.base.enums.StatusEnum;
import cn.ekgc.vms.dao.MenuDao;
import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;
import cn.ekgc.vms.pojo.vo.Node;
import cn.ekgc.vms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

	/**
	 * <b>为授权页面查询Node集合</b>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Node> getNodeListForAuth(Long roleId) throws Exception {
		List<Node> nodeList = new ArrayList<Node>();
		//查询所有的菜单信息
		List<Menu> menuList = menuDao.findNodeListByQuery(null);
		//根据角色查询角色已有的权限
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("roleId", roleId);
		List<Menu> roleMenuList = menuDao.findNodeListByQuery(paramMap);

		//需要将MenuList切换成nodeList
		if (menuList != null && menuList.size() > 0 ){
			for (Menu menu : menuList){
				//创建Node对象
				Node node = new Node();
				node.setId(menu.getId());
				//设置pId(父Id)
				node.setId((menu.getParent() == null)? 0 : menu.getParent().getId());
                //name
				node.setName(menu.getText());
				//open
				if (menu.getParent() == null){
					//说明是一级菜单
					//将菜单展开
					node.setOpen(true);
				}
				//判断是否选中
				//判断roleMenuList是否包含menu菜单中的选项，如果包含就选中
				if (roleMenuList.contains(menu)){
					node.setChecked(true);
				}
				nodeList.add(node);
			}
		}
		return nodeList;
	}
}
