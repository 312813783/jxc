package com.java1234.service;

import java.util.List;

import com.java1234.entity.OverflowListGoods;

/**
 * <br>报溢单商品Service接口
 * @author admin
 *
 */
public interface OverflowListGoodsService {

	/**
	 * <br>根据报溢单id查询所有报溢单商品
	 * @param overflowListId
	 * @return
	 */
	public List<OverflowListGoods> listByOverflowListId(Integer overflowListId);


}
