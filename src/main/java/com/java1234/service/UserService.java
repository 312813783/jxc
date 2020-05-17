package com.java1234.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.java1234.entity.User;

/**
 * <br> 用户Service接口
 * @author admin
 *
 */
public interface UserService {

	/**
	 * <br>根据用户名查找用户实体
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);
	
	/**
	 * <br>根据id查询用户实体
	 * @param id
	 * @return
	 */
	public User findById(Integer id);
	
	/**
	 * <br>修改或者修改用户信息
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * <br>根据条件分页查询用户信息
	 * @param user
	 * @param page
	 * @param pageSize
	 * @param direction
	 * @param properties
	 * @return
	 */
	public List<User> list(User user,Integer page,Integer pageSize,Direction direction,String... properties);
	
	/**
	 * <br>获取总记录数
	 * @param user
	 * @return
	 */
	public Long getCount(User user);
	
	/**
	 * <br>根据id删除用户
	 * @param id
	 */
	public void delete(Integer id);
}
