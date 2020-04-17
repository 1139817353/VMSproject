package cn.ekgc.vms.dao;

import cn.ekgc.vms.pojo.entity.Menu;
import cn.ekgc.vms.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuDao {
	/**
	 * <b>查询用户角色信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Menu> findListByQuery(Map<String, Object> query)throws Exception;

	/**
	 * <b>查询菜单列表</b>
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Menu> findNodeListByQuery(Map<String,Object> paramMap)throws Exception;

}
