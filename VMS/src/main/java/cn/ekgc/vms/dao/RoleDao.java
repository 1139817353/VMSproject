package cn.ekgc.vms.dao;

import cn.ekgc.vms.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao {
	/**
	 * <b>进行分页查询</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Role> findListByQuery(Role query)throws Exception;

	/**
	 * <b>保存信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int save(Role entity) throws Exception;

	/**
	 * <b>更改信息</b>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int update(Role entity)throws Exception;



	/**
	 * <b>修改信息</b>
	 * @param
	 * @return
	 * @throws Exception
	 */
	int deleteForAuth(Long roleId) throws Exception;

	int saveAuth(Map<String, Object> paramMap) throws Exception;

}
