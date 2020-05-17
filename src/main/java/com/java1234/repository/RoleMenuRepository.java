package com.java1234.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.java1234.entity.RoleMenu;

/**
 * <br> 角色权限菜单Repository接口
 * @author admin
 *
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer>,JpaSpecificationExecutor<RoleMenu>{

	/**
	 * 根据用户id删除所有关联信息
	 * @param id
	 */
	@Query(value="delete from t_role_menu where role_id=?1",nativeQuery=true)
	@Modifying
	public void deleteByRoleId(Integer roleId);
}
