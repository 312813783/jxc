package com.java1234.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.java1234.entity.ReturnList;
import com.java1234.entity.ReturnListGoods;

/**
 * <br>退货单Service接口
 * @author admin
 *
 */
public interface ReturnListService {

	/**
	 * <br>根据id查询实体
	 * @param id
	 * @return
	 */
	public ReturnList findById(Integer id);
	
	/**
	 * <br>获取当天最大退货单号
	 * @return
	 */
	public String getTodayMaxReturnNumber();
	
	/**
	 * <br>添加退货单 以及所有退货单商品
	 * @param returnList 退货单
	 * @param ReturnListGoodsList 退货单商品
	 */
	public void save(ReturnList returnList,List<ReturnListGoods> returnListGoodsList);
	
	/**
	 * <br>根据条件查询退货单信息
	 * @param returnList
	 * @param page
	 * @param pageSize
	 * @param direction
	 * @param properties
	 * @return
	 */
	public List<ReturnList> list(ReturnList returnList,Direction direction,String... properties);
	
	/**
	 * <br>根据id删除退货单信息 包括退货单里的商品
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * <br>更新退货单
	 * @param returnList
	 */
	public void update(ReturnList returnList);
}
