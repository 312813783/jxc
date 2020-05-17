package com.java1234.service;

import java.util.List;

import com.java1234.entity.ReturnListGoods;

/**
 * <br>退货单商品Service接口
 * @author admin
 *
 */
public interface ReturnListGoodsService {

	/**
	 * <br>根据退货单id查询所有退货单商品
	 * @param returnListId
	 * @return
	 */
	public List<ReturnListGoods> listByReturnListId(Integer returnListId);
	
	/**
	 * <br>根据条件查询退货单所有商品
	 * @param returnListGoods
	 * @return
	 */
	public List<ReturnListGoods> list(ReturnListGoods returnListGoods);
}
