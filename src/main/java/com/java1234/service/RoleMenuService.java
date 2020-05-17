package com.java1234.service;

import com.java1234.entity.RoleMenu;

/**
 * <br>角色权限关联Service接口
 * @author admin
 *
 */
public interface RoleMenuService {

	/**
	 * <br>根据角色id删除所有关联信息
	 * @param id
	 */
	public void deleteByRoleId(Integer roleId);
	
	/**
	 * <br>保存
	 * @param roleMenu
	 */
	public void save(RoleMenu roleMenu);
}
