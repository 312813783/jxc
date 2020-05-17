package com.java1234.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.java1234.entity.CustomerReturnList;
import com.java1234.entity.CustomerReturnListGoods;

/**
 * <br>客户退货单Service接口
 * @author admin
 *
 */
public interface CustomerReturnListService {

	/**
	 * <br>根据id查询实体
	 * @param id
	 * @return
	 */
	public CustomerReturnList findById(Integer id);
	
	/**
	 * <br>获取当天最大客户退货单号
	 * @return
	 */
	public String getTodayMaxCustomerReturnNumber();
	
	/**
	 * <br>添加客户退货单 以及所有客户退货单商品
	 * @param customerReturnList 客户退货单
	 * @param CustomerReturnListGoodsList 客户退货单商品
	 */
	public void save(CustomerReturnList customerReturnList,List<CustomerReturnListGoods> customerReturnListGoodsList);
	
	/**
	 * <br>根据条件查询客户退货单信息
	 * @param customerReturnList
	 * @param page
	 * @param pageSize
	 * @param direction
	 * @param properties
	 * @return
	 */
	public List<CustomerReturnList> list(CustomerReturnList customerReturnList,Direction direction,String... properties);
	
	/**
	 * <br>根据id删除客户退货单信息 包括客户退货单里的商品
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * <br>更新退货单
	 * @param customerReturnList
	 */
	public void update(CustomerReturnList customerReturnList);

}
