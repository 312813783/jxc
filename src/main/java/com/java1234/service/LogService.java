package com.java1234.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.java1234.entity.Log;

/**
 * <br>系统日志Service接口
 * @author admin
 *
 */
public interface LogService {


	
	/**
	 * <br>修改或者修改日志信息
	 * @param log
	 */
	public void save(Log log);
	
	/**
	 * <br>根据条件分页查询日志信息
	 * @param log
	 * @param page
	 * @param pageSize
	 * @param direction
	 * @param properties
	 * @return
	 */
	public List<Log> list(Log log,Integer page,Integer pageSize,Direction direction,String... properties);
	
	/**
	 * <br>获取总记录数
	 * @param user
	 * @return
	 */
	public Long getCount(Log log);

}
