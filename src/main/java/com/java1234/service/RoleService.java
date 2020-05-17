package com.java1234.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.java1234.entity.Role;

/**
 * <br>角色Service实现类
 * @author admin
 *
 */
public interface RoleService {

	/**
	 * <br>根据用户id查询角色集合
	 * @param id
	 * @return
	 */
	public List<Role> findByUserId(Integer id);
	
	/**
	 * <br>根据id查询实体
	 * @param roleId
	 * @return
	 */
	public Role findById(Integer id);
	
	/**
	 * <br>查询所有角色信息
	 * @return
	 */
	public List<Role> listAll();
	
	/**
	 * <br>根据条件分页查询角色信息
	 * @param user
	 * @param page
	 * @param pageSize
	 * @param direction
	 * @param properties
	 * @return
	 */
	public List<Role> list(Role role,Integer page,Integer pageSize,Direction direction,String... properties);
	
	/**
	 * <br>获取总记录数
	 * @param user
	 * @return
	 */
	public Long getCount(Role role);
	
	/**
	 * <br>添加或者修改角色信息
	 * @param role
	 */
	public void save(Role role);
	
	/**
	 * <br>根据id删除角色
	 * @param id
	 */
	public void delete(Integer id);
	
	
}
