package com.java1234.service;


import com.java1234.entity.UserRole;

/**
 * <br> 用户角色关联Service接口
 * @author admin
 *
 */
public interface UserRoleService {

	/**
	 * <br>添加或者修改用户角色关联
	 * @param userRole
	 */
	public void save(UserRole userRole);
	
	/**
	 * <br>删除用户角色关联实体
	 * @param id
	 */
	public void delete(UserRole userRole);
	

	
	/**
	 * <br>根据ID查询用户角色关联实体
	 * @param id
	 * @return
	 */
	public UserRole findById(Integer id);
	
	/**
	 * <br>根据用户id删除所有关联信息
	 * @param id
	 */
	public void deleteByUserId(Integer userId);
	
	/**
	 * <br>根据角色id删除所有关联信息
	 * @param id
	 */
	public void deleteByRoleId(Integer userId);
}
